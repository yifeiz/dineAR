package com.kingapps.dinear;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RestaurantActivity extends AppCompatActivity implements RecyclerViewAdapter.OnRestaurantListener {
    private RecyclerView mRecyclerView;
    private RestaurantAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Restaurant> mNames = new ArrayList<>();

    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = mFirebaseDatabase.getReference("restaurant");
//    private FirebaseAuth mAuth;
//    private FirebaseAuth.AuthStateListener mAuthListener;
//    private DatabaseReference myRef;
//    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
//    DatabaseReference restaurantReference = mRootRef.child("restaurants");
//    DatabaseReference nameReference = restaurantReference.child("")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        ArrayList<Restaurant> restaurantList = new ArrayList<>();

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new RestaurantAdapter(restaurantList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        initData();
        readBase();

//        mFirebaseDatabase = FirebaseDatabase.getInstance();
//        mAuth = FirebaseAuth.getInstance();
//        myRef = mFirebaseDatabase.getReference();
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

    public void readBase() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value  = dataSnapshot.getValue(String.class);
                Log.d("HARAMBE", value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("HARAMBE", "Failed", databaseError.toException());
            }
        });
    }
}
