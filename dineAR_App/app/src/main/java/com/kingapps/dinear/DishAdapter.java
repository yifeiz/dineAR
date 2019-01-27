package com.kingapps.dinear;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class DishAdapter extends RecyclerView.Adapter<DishAdapter.ViewHolder>{

    private ArrayList <Dish>  mDishes = new ArrayList<>();
    private Context mContext;
    private OnDishListener onDishListener;

    public DishAdapter(ArrayList<Dish> mRestaurants, Context mContext, OnDishListener onDishListener) {
        this.mDishes = mRestaurants;
        this.mContext = mContext;
        this.onDishListener = onDishListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites, parent, false);
        ViewHolder holder = new ViewHolder(view, onDishListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.data.setText(mRestaurants.get(position)); // need to do the thing here where we set text according to data we get
    }

    @Override
    public int getItemCount() {
        return mDishes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView data;
        OnDishListener onDishListener;
        ConstraintLayout parentLayout;
        public ViewHolder(View itemView, OnDishListener onDishListener) {
            super(itemView);
            data = itemView.findViewById(R.id.favoriteText);
            parentLayout = itemView.findViewById(R.id.parentLayout);
            this.onDishListener = onDishListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onDishListener.onDishClick(getAdapterPosition());
        }
    }

    public interface OnDishListener {
        void onDishClick (int position);
    }
}
