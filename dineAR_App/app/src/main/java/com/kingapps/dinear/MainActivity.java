package com.kingapps.dinear;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.lang.reflect.Array;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnRestaurantListener {
    private RecyclerView mRecyclerView;
    private RestaurantAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ArrayList<Restaurant> mNames = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_main);

        ArrayList<Restaurant> restaurantList = new ArrayList<>();

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new RestaurantAdapter(restaurantList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        ImageButton visualize = findViewById(R.id.goButton);
        visualize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent arIntent = new Intent(MainActivity.this, AugmentReality.class);
                MainActivity.this.startActivity(arIntent);
                //Intent fileIntent = new Intent(MainActivity.this, AugmentReality.class);
                //MainActivity.this.startActivity(fileIntent);
                //FirebaseDatabase database = FirebaseDatabase.getInstance();
                //DatabaseReference myRef = database.getReference("message");
                //myRef.setValue("Hello, World!");

            }
        });
        initData();

    }


    private void initData() {
        mNames.add(new Restaurant("Deltahacks", new ArrayList<Dish>(), 0));
        mNames.add(new Restaurant("Deltahacks", new ArrayList<Dish>(), 0));
        mNames.add(new Restaurant("Deltahacks", new ArrayList<Dish>(), 0));
        mNames.add(new Restaurant("Deltahacks", new ArrayList<Dish>(), 0));
        mNames.add(new Restaurant("Deltahacks", new ArrayList<Dish>(), 0));
        mNames.add(new Restaurant("Deltahacks", new ArrayList<Dish>(), 0));
        mNames.add(new Restaurant("Deltahacks", new ArrayList<Dish>(), 0));
        mNames.add(new Restaurant("Deltahacks", new ArrayList<Dish>(), 0));
        mNames.add(new Restaurant("Deltahacks", new ArrayList<Dish>(), 0));
        mNames.add(new Restaurant("Deltahacks", new ArrayList<Dish>(), 0));
        mNames.add(new Restaurant("Deltahacks", new ArrayList<Dish>(), 0));
        mNames.add(new Restaurant("Deltahacks", new ArrayList<Dish>(), 0));
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
        //mNames.get(position);
        Intent intent = new Intent(this, FoodActivity.class);
        startActivity(intent);
    }
}
