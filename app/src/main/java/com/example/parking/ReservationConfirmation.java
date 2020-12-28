package com.example.parking;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;

public class ReservationConfirmation extends AppCompatActivity {

    String city = "";
    String date = "";
    String hour = "";
    String user = "";
    String park = "";
    DataBaseHelper db;
    double lat;
    double lng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_confirmation);

        db = new DataBaseHelper(this);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Fragment frag1 = getFragmentManager().findFragmentById(R.id.fragment1);
//            Fragment frag3 = getFragmentManager().findFragmentById(R.id.fragment3);
        } else {
            Fragment frag1 = getFragmentManager().findFragmentById(R.id.fragment1);
            Fragment frag2 = getFragmentManager().findFragmentById(R.id.fragment2);
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            city = extras.getString("city");
            user = extras.getString("user");
            hour = extras.getString("hour");
            date = extras.getString("date");
            park = extras.getString("park");
        }

        TextView citytext = (TextView) findViewById(R.id.cityconf);
        TextView parktext = (TextView) findViewById(R.id.parkconf);
        TextView datetext = (TextView) findViewById(R.id.dateconf);
        TextView timetext = (TextView) findViewById(R.id.hourconf);

//        TextView park = findViewById(R.id.help);

        citytext.setText(city);
        parktext.setText(park);
        datetext.setText(date);
        timetext.setText(hour);

        lat = db.latitude(park);
        lng = db.longitude(park);

    }

    public void functionConfirm(View v) {

        final Button confirm = findViewById(R.id.potvrda);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int r;
//                    r = db.ReservationPerUser(user);
//                if (r < 3) {
                Boolean insert = db.insertReservation(user, city, park, date, hour);
                if (insert == true) {
                    Toast.makeText(getApplicationContext(), "Вашата резервација е успешна ", Toast.LENGTH_SHORT).show();
                }
//                } else
//                    Toast.makeText(getApplicationContext(), "Веќе имате 3 резервации", Toast.LENGTH_SHORT).show();
//            }

            }
        });
    }
    public void functionNavigation (View v){

        final Button navigation = findViewById(R.id.navbtn);

        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "навигација", Toast.LENGTH_SHORT).show();


                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%s,%s", lat, lng);
                i.setData(Uri.parse(uri));
                i.setPackage("com.google.android.apps.maps");
                if(i.resolveActivity(getPackageManager()) != null){
                    startActivity(i);
                }
            }
        });
    }
}
