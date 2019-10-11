package com.project.eubanksms.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.eubanksms.Models.ItemDetail;
import com.project.eubanksms.R;

import java.util.ArrayList;

public class AdapterDetail extends RecyclerView.Adapter<AdapterDetail.DetailViewHolder> {

    private static final String TAG = "AdapterDetail";

    private ArrayList<ItemDetail> atmDetail;
    // --
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }
    // --

    public static class DetailViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageStatusDetail;
        public TextView textDetail;
        public TextView textDate;

        // -final OnItemClickListener listener
        public DetailViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            imageStatusDetail = itemView.findViewById(R.id.imageStatus);
            textDetail = itemView.findViewById(R.id.textDetail);
            textDate = itemView.findViewById(R.id.textDateDetail);

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

    public AdapterDetail(ArrayList<ItemDetail> atmDetail) {
        this.atmDetail = atmDetail;
        //Log.d(TAG, "AdapterATM: " + atmList.size());
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_details, parent, false);
        // -onItemClickListener
        DetailViewHolder detailViewHolder = new DetailViewHolder(view, onItemClickListener);
        return detailViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
        ItemDetail currentDetail = atmDetail.get(position);
        //Log.d(TAG, "onBindViewHolder: " + position);
        Log.d(TAG, "onBindViewHolder: currentDetail img = " + currentDetail.getImageResource());
        holder.imageStatusDetail.setImageResource(currentDetail.getImageResource());
        holder.textDetail.setText(currentDetail.getTextDetail());
        holder.textDate.setText("Дата/время: " + currentDetail.getTextDate());
    }

    @Override
    public int getItemCount() {
        //Log.d(TAG, "getItemCount: " + atmList.size());
        return atmDetail.size();
    }
}
