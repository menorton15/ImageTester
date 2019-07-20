package com.example.imagetester;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class EmailListRecyclerViewAdapter extends RecyclerView.Adapter<EmailListRecyclerViewAdapter.MyViewHolder> {

    ArrayList<VehicleAccessory> vehicleAccessoryArrayList;

    private OnItemClickListener myListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        myListener = listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        public TextView textView4;

        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.acc_name_email);
            textView2 = itemView.findViewById(R.id.acc_part_number_email);
            textView3 = itemView.findViewById(R.id.acc_description_email);
            textView4 = itemView.findViewById(R.id.acc_price_email);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public EmailListRecyclerViewAdapter(ArrayList<VehicleAccessory> vehicleAccessoryArrayList) {
        this.vehicleAccessoryArrayList = vehicleAccessoryArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.email_item, viewGroup, false);
        EmailListRecyclerViewAdapter.MyViewHolder myViewHolder = new EmailListRecyclerViewAdapter.MyViewHolder(v, myListener);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        VehicleAccessory currentPart = vehicleAccessoryArrayList.get(i);

        myViewHolder.textView1.setText(currentPart.getPartName());
        myViewHolder.textView2.setText(currentPart.getPartSpecs());
        myViewHolder.textView3.setText(currentPart.getPartDescription());
        myViewHolder.textView4.setText("$ " + currentPart.getPartPrice());
    }

    @Override
    public int getItemCount() {
        return vehicleAccessoryArrayList.size();
    }

}
