package com.kingapps.dinear;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private ArrayList <String>  mNames = new ArrayList<>();
    private Context mContext;
    private OnRestaurantListener onRestaurantListener;

    public RecyclerViewAdapter(ArrayList<String> mNames, Context mContext, OnRestaurantListener onRestaurantListener) {
        this.mNames = mNames;
        this.mContext = mContext;
        this.onRestaurantListener = onRestaurantListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites, parent, false);
        ViewHolder holder = new ViewHolder(view, onRestaurantListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.data.setText(mNames.get(position)); // need to do the thing here where we set text according to data we get
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView data;
        OnRestaurantListener onRestaurantListener;
        ConstraintLayout parentLayout;
        public ViewHolder(View itemView, OnRestaurantListener onRestaurantListener) {
            super(itemView);
            data = itemView.findViewById(R.id.favoriteText);
            parentLayout = itemView.findViewById(R.id.parentLayout);
            this.onRestaurantListener = onRestaurantListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onRestaurantListener.onRestaurantClick(getAdapterPosition());
        }
    }

    public interface OnRestaurantListener {
        void onRestaurantClick(int position);
    }
}
