package com.example.imagetester;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class AccessoryListRecyclerViewAdapter extends RecyclerView.Adapter<AccessoryListRecyclerViewAdapter.AccessoryListViewHolder> {

    private Context myContext;
    private ArrayList<VehicleAccessory> myVehicleAccessoryList;
    private AccessoryListRecyclerViewAdapter.OnItemClickListener myAccessoryListListener;

    public interface OnItemClickListener {
        void onAddToCartIconClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        myAccessoryListListener = onItemClickListener;
    }

    public AccessoryListRecyclerViewAdapter(Context myContext, ArrayList<VehicleAccessory> myVehicleAccessoryList) {
        this.myContext = myContext;
        this.myVehicleAccessoryList = myVehicleAccessoryList;
    }

    public class AccessoryListViewHolder extends RecyclerView.ViewHolder {

        public TextView myTextViewAccessoryPartNumber;
        public TextView myTextViewAccessoryName;
        public TextView myTextViewAccessoryDescription;
        public TextView myTextViewBrand;
        public TextView myTextViewManufacturerPartNumber;
        public TextView myTextViewAccessoryPrice;
        public TextView myTextViewVehicleType;
        public TextView myTextViewSpecs;
        public ImageView myImageViewAccessoryView;
        public ImageView myImageViewAddToCartIcon;

        public AccessoryListViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            myTextViewAccessoryPartNumber = itemView.findViewById(R.id.text_view_accessory_part_number);
            myTextViewAccessoryName = itemView.findViewById(R.id.text_view_accessory_name);
            myTextViewAccessoryDescription = itemView.findViewById(R.id.text_view_accessory_description);
            myTextViewBrand = itemView.findViewById(R.id.text_view_manufacturer_name);
            myTextViewManufacturerPartNumber = itemView.findViewById(R.id.text_view_manufacturer_part_number);
            myTextViewAccessoryPrice = itemView.findViewById(R.id.text_view_accessory_cost);
            myTextViewVehicleType = itemView.findViewById(R.id.text_view_vehicle_type);
            myTextViewSpecs = itemView.findViewById(R.id.text_view_specs);
            myImageViewAccessoryView = itemView.findViewById(R.id.image_view_accessory_image);
            myImageViewAddToCartIcon = itemView.findViewById(R.id.add_to_cart_icon);

            myImageViewAddToCartIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onAddToCartIconClick(position);
                        }
                    }
                }
            });

        }
    }
    @NonNull
    @Override
    public AccessoryListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(myContext).inflate(R.layout.accessory_list_item, viewGroup, false);
        AccessoryListViewHolder myViewholder = new AccessoryListViewHolder(v, myAccessoryListListener);
        return myViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull AccessoryListViewHolder holder, int position) {

        VehicleAccessory currentPart = myVehicleAccessoryList.get(position);

        String partNumber = currentPart.getInternalPartNumber();
        String partName = currentPart.getPartName();
        String partDescription = currentPart.getPartDescription();
        String partBrand = currentPart.getPartBrand();
        String manufacturerPartNumber = currentPart.getManufacturerPartNumber();
        String partPrice = currentPart.getPartPrice();
        String vehicleType = currentPart.getVehicleTypeThatPartFits();
        String partSpecs = currentPart.getPartSpecs();
        String imageURL = currentPart.getPartImageURL();
        Bitmap partImage = currentPart.getPartImage();

        holder.myTextViewAccessoryPartNumber.setText(partNumber);
        holder.myTextViewAccessoryName.setText(partName);
        holder.myTextViewAccessoryDescription.setText(partDescription);
        holder.myTextViewBrand.setText(partBrand);
        holder.myTextViewManufacturerPartNumber.setText(manufacturerPartNumber);
        holder.myTextViewAccessoryPrice.setText("$ " + partPrice);
        holder.myTextViewVehicleType.setText("This will fit an " + vehicleType);
        holder.myTextViewSpecs.setText(partSpecs);
        holder.myImageViewAccessoryView.setImageBitmap(partImage);

        //Picasso.get().load(imageURL).fit().centerInside().into(holder.myImageViewAccessoryView);
    }

    @Override
    public int getItemCount() {
        return myVehicleAccessoryList.size();
    }

}
