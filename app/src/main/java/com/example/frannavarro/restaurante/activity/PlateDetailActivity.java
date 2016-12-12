package com.example.frannavarro.restaurante.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.frannavarro.restaurante.R;

public class PlateDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plate_detail);


        setTitle(getString(R.string.detalle_title));

    }
}
