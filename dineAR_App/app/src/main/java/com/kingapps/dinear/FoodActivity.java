package com.kingapps.dinear;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity implements DishAdapter.OnDishListener {
    private RecyclerView mRecyclerView;
    private DishAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Dish> mNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        ArrayList<Dish> dishList = new ArrayList<>();

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new DishAdapter(dishList, this, this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDishClick(int position) {
        //Intent intent = new Intent(this, ARActivity.class);
        //startActivity(intent);
    }
}
