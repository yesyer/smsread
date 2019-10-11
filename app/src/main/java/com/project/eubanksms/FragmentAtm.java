package com.project.eubanksms;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.eubanksms.Adapters.AdapterATM;
import com.project.eubanksms.Helpers.ParserBody;
import com.project.eubanksms.Models.ItemATM;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import me.everything.providers.android.telephony.Sms;
import me.everything.providers.android.telephony.TelephonyProvider;

public class FragmentAtm extends Fragment {

    private View view;
    private RecyclerView recyclerATM;
    private RecyclerView.LayoutManager layoutManagerATM;
    // ++ private RecyclerView.Adapter adapterATM;
    private AdapterATM adapterATM;


    private ArrayList<ItemATM> atmList = new ArrayList<>();

    public FragmentAtm() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_atm,container,false);
        initAtmList();
        initRecyclerATM();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initAtmList() {

        TelephonyProvider telephonyProvider = new TelephonyProvider(getActivity().getApplicationContext());
        List<Sms> textSms = telephonyProvider.getSms(TelephonyProvider.Filter.INBOX).getList();
        int totalSMS = textSms.size();
        String sDate, textATM;
        boolean isFound;

        // цикл по всем смс, начинам со свежих
        for (int j = 0; j < totalSMS; j++) {
            // фильтр только по адесу eubank
            if (textSms.get(j).address.compareToIgnoreCase(ParserBody.ADDRESS_SMS) == 0) {
                //проверка - если первые 3 символа равняються 000, если нет возвращет null
                textATM = ParserBody.getTextAtm(textSms.get(j).body);
                if (textATM!=null) {
                    // переворачиваем дату/время в строку
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                    sDate = sdf.format(textSms.get(j).sentDate);

                    // проверяем есть ли уже в нашем списе такая запись
                    // - первую запись всегда добавляем
                    if (atmList.size() == 0){
                        atmList.add(new ItemATM(ParserBody.getImageStatus(textSms.get(j).body), textATM, ParserBody.getTextStatus(textSms.get(j).body), sDate));
                    } else {
                        // - последующие уже проверяем на совпадения
                        // обнуляем флаг совпадения
                        isFound = false;
                        for (int i = 0; i<atmList.size(); i++){
                            if (atmList.get(i).getTextATM().compareToIgnoreCase(textATM) == 0){
                                // если нет, то выставляем флаг совпадения
                                isFound = true;
                            }
                        }
                        if (isFound == false){
                            // если нет то добавляем
                            atmList.add(new ItemATM(ParserBody.getImageStatus(textSms.get(j).body), textATM, ParserBody.getTextStatus(textSms.get(j).body), sDate));
                        }
                    }
                }
            }
        }
        //initRecyclerATM();
    }

    private void initRecyclerATM() {
        recyclerATM = view.findViewById(R.id.recyclerATM);
        recyclerATM.setHasFixedSize(true);
        layoutManagerATM = new LinearLayoutManager(getActivity().getApplicationContext());
        adapterATM = new AdapterATM(atmList);

        recyclerATM.setLayoutManager(layoutManagerATM);
        recyclerATM.setAdapter(adapterATM);

        adapterATM.setOnItemClickListener(new AdapterATM.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //atmList.get(position).setImageResource(R.drawable.ic_unknown);
                //adapterATM.notifyItemChanged(position);
                //Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                //intent.putExtra("atmNumber", atmList.get(position).getTextATM());
                //startActivity(intent);
            }
        });
    }
}
