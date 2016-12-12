package com.example.frannavarro.restaurante.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.frannavarro.restaurante.R;
import com.example.frannavarro.restaurante.model.Plate;

import java.io.File;
import java.util.Arrays;

public class PlateDetailActivity extends AppCompatActivity {

    private ImageView plateImage;
    private TextView nameTextView;
    private TextView ingrendientsTextView;
    private TextView priceTextView;

    private Plate plate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plate_detail);

        plateImage = (ImageView) findViewById(R.id.plate_image);

        nameTextView = (TextView) findViewById(R.id.plate_name);

        ingrendientsTextView = (TextView) findViewById(R.id.plate_ingredients);

        priceTextView = (TextView) findViewById(R.id.plate_price);

        setDetail();




        setTitle(getString(R.string.detalle_title));

    }

    private void setDetail() {

        plate = (Plate) getIntent().getSerializableExtra(PlateListActivity.PLATE_EXTRA);

        nameTextView.setText(plate.getName());

        Context context = plateImage.getContext();
        int id = context.getResources().getIdentifier(plate.getImage(), "drawable", context.getPackageName());
        plateImage.setImageResource(id);

        ingrendientsTextView.setText(Arrays.toString(plate.getIngredients()));

        priceTextView.setText(String.format(getString(R.string.price_format),plate.getPrice()));

    }


}
