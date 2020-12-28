package com.example.parking;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.gms.dynamic.IFragmentWrapper;

import java.util.Calendar;
import java.util.Date;


public class ReservationFromFragment extends Fragment {



    public ReservationFromFragment() {
        // Required empty public constructor
    }
    private static final String TAG = "ReservationFromFragment";
    private TextView displayDate;
    private DatePickerDialog.OnDateSetListener datesetlistener;



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EditText cityname = getActivity().findViewById(R.id.formcity);


        displayDate = (TextView) getActivity().findViewById(R.id.editTextDate);
        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog picker = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_DeviceDefault_Dialog_MinWidth,
                        datesetlistener,
                        year, month, day

                );
                picker.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                picker.show();
            }

        });

        datesetlistener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;

                String date = dayOfMonth + "/" + month + "/" + year;
                displayDate.setText(date);
            }
        };

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_reservation_form, container, false);
    }

}