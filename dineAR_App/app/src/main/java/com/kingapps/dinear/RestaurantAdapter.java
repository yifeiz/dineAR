package com.kingapps.dinear;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {
    public ArrayList<Restaurant> mRestaurantList;

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        public ImageButton mImageButton;
        public TextView mTextView;
        public RestaurantViewHolder(View itemView) {
            super(itemView);
            mImageButton = itemView.findViewById(R.id.goButton);
            mTextView = itemView.findViewById(R.id.favoriteText);
        }
    }

    public RestaurantAdapter(ArrayList<Restaurant> restaurantList) {
        mRestaurantList = restaurantList;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites, parent, false);
        RestaurantViewHolder rvh = new RestaurantViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant currentRestaurant = mRestaurantList.get(position);
        holder.mTextView.setText(currentRestaurant.getName());
        holder.mImageButton.setImageResource(currentRestaurant.getImageURLs());
    }

    @Override
    public int getItemCount() {
        return mRestaurantList.size();
    }
}
