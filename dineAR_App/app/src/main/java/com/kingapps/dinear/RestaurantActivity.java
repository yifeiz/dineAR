package com.kingapps.dinear;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class RestaurantActivity extends AppCompatActivity implements RecyclerViewAdapter.OnRestaurantListener {

    private ArrayList<String> mNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        initData();
    }


    private void initData() {
        mNames.add("Deltahacks");
        mNames.add("McDonalds");
        mNames.add("Second Cup");
        mNames.add("Burger King");
        mNames.add("Starbucks");
        mNames.add("Tim Hortons");
        mNames.add("Wendy's");
        mNames.add("Arby's");
        mNames.add("A&W");
        mNames.add("Boston Pizza");
        mNames.add("Montana's");
        mNames.add("Kelsey's");
        mNames.add("Applebees");
        mNames.add("Chili's");
        mNames.add("Subway");
        mNames.add("Swiss Chalet");
        mNames.add("Mandarin");
        mNames.add("Red Lobster");
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mNames, this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onRestaurantClick(int position) {
        mNames.get(position);
        Intent intent = new Intent(this, FoodActivity.class);
        startActivity(intent);
    }
}
