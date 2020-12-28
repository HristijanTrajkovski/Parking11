package com.example.parking;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class FragmentConf2 extends Fragment {
    public FragmentConf2() {
    }

    DataBaseHelper db;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button b = (Button) getActivity().findViewById(R.id.navbtn);
        final TextView park = getActivity().findViewById(R.id.help);
        final String parkname = String.valueOf(park);
        db = new DataBaseHelper(getContext());


        b.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View view){
                Toast.makeText(getActivity().getApplicationContext(), "навигација", Toast.LENGTH_SHORT).show();

                double lat = db.latitude(parkname);
                double lng = db.longitude(parkname);
                String navigationUri = "google.navigation:q=" + lat + "," + lng;
                Intent googleMapsNavigation = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(navigationUri));
                googleMapsNavigation.setPackage("com.google.android.apps.maps");
                if(googleMapsNavigation.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(googleMapsNavigation);
                }

            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_fragment_conf2, container, false);
    }

}