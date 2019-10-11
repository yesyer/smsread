package com.project.eubanksms.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.eubanksms.Models.ItemATM;
import com.project.eubanksms.R;

import java.util.ArrayList;

public class AdapterATM extends RecyclerView.Adapter<AdapterATM.AtmViewHolder> {

    private static final String TAG = "AdapterATM";

    private ArrayList<ItemATM> atmList;
    // --
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }
    // --

    public static class AtmViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageStatus;
        public TextView textATM;
        public TextView textStatus;
        public TextView textDate;

        // -final OnItemClickListener listener
        public AtmViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            imageStatus = itemView.findViewById(R.id.imageStatus);
            textATM = itemView.findViewById(R.id.textATM);
            textStatus = itemView.findViewById(R.id.textStatus);
            textDate = itemView.findViewById(R.id.textDate);

            //itemView.setOnClickListener(this);
            //-

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

    public AdapterATM(ArrayList<ItemATM> atmList) {
        this.atmList = atmList;
        //Log.d(TAG, "AdapterATM: " + atmList.size());
    }

    @NonNull
    @Override
    public AtmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_atm, parent, false);
        // -onItemClickListener
        AtmViewHolder atmViewHolder = new AtmViewHolder(view, onItemClickListener);
        return atmViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AtmViewHolder holder, int position) {
        ItemATM currentATM = atmList.get(position);
        //Log.d(TAG, "onBindViewHolder: " + position);
        holder.imageStatus.setImageResource(currentATM.getImageResource());
        holder.textATM.setText(currentATM.getTextATM());
        holder.textStatus.setText(currentATM.getTextStatus());
        holder.textDate.setText("Дата/время: " + currentATM.getTextDate());
    }

    @Override
    public int getItemCount() {
        //Log.d(TAG, "getItemCount: " + atmList.size());
        return atmList.size();
    }
}
