package com.example.parking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class RecyclerParking extends AppCompatActivity {

    private DataBaseHelper mDB;
    private RecyclerView recycler;

    private ParkAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);

        mDB = new DataBaseHelper(this);

        String city="";
        String date="";
        String hour="";
        String user="";


        Intent intent = getIntent();
        city = intent.getStringExtra("city");
        date = intent.getStringExtra("date");
        hour = intent.getStringExtra("hour");
        user = intent.getStringExtra("user");

        recycler = (RecyclerView) findViewById(R.id.parkingplaces);

        setAdapter(city, date, hour, user);



    }

    private void setAdapter(String city, String date, String hour, String user) {

        ParkAdapter adapter = new ParkAdapter(this, mDB, R.layout.parkrow, listener,city, date, hour, user);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(layoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(adapter);
    }
}