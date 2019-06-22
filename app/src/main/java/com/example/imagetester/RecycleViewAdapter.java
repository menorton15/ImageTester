package com.example.imagetester;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {
    ArrayList<VehiclePart> vehiclePartArrayList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        public TextView textView4;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.acc_name);
            textView2 = itemView.findViewById(R.id.acc_part_number);
            textView3 = itemView.findViewById(R.id.acc_description);
            textView4 = itemView.findViewById(R.id.acc_price);
        }
    }

    public RecycleViewAdapter(ArrayList<VehiclePart> vehiclePartArrayList) {
        this.vehiclePartArrayList = vehiclePartArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        VehiclePart currentPart = vehiclePartArrayList.get(i);

        viewHolder.textView1.setText(currentPart.getPartName());
        viewHolder.textView2.setText(currentPart.getPartID());
        viewHolder.textView3.setText(currentPart.getPartDescription());
        viewHolder.textView4.setText(currentPart.getPriceAsString());
    }

    @Override
    public int getItemCount() {
        return vehiclePartArrayList.size();
    }


}
