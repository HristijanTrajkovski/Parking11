package com.example.parking;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Scanner;

public class MapFragment extends Fragment {

    double lat;
    double lng;
    String parkName;

    DataBaseHelper db = new DataBaseHelper(getContext());
    private OnMapReadyCallback callback = new OnMapReadyCallback() {


        public void onMapReady(GoogleMap googleMap) {
            Intent intent = getActivity().getIntent();
            lat = intent.getDoubleExtra("lat", 0);
            lng = intent.getDoubleExtra("lng", 0);
            parkName = intent.getStringExtra("parkName");
            LatLng parking = new LatLng(lat, lng);
            googleMap.addMarker(new MarkerOptions().position(parking).title(parkName));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(parking, 15.0f));
        }


    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_map_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}