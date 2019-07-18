package com.example.imagetester;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CartListRecyclerViewAdapter extends RecyclerView.Adapter<CartListRecyclerViewAdapter.MyViewHolder> {

    ArrayList<VehicleAccessory> vehicleAccessoryArrayList;
    private OnItemClickListener myListener;

    public interface OnItemClickListener {
        //void onItemClick(int position);

        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        myListener = listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        public TextView textView4;
        public ImageView myDeleteImage;

        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.acc_name);
            textView2 = itemView.findViewById(R.id.acc_part_number);
            textView3 = itemView.findViewById(R.id.acc_description);
            textView4 = itemView.findViewById(R.id.acc_price);
            myDeleteImage = itemView.findViewById(R.id.image_delete);

            /*itemView.setOnClickListener(new View.OnClickListener() {
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
            */

            myDeleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    public CartListRecyclerViewAdapter(ArrayList<VehicleAccessory> vehicleAccessoryArrayList) {
        this.vehicleAccessoryArrayList = vehicleAccessoryArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(v, myListener);
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

