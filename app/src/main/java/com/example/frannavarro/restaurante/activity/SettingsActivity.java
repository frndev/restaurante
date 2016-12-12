package com.example.frannavarro.restaurante.activity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import com.example.frannavarro.restaurante.R;

/**
 * Created by frannavarro on 11/12/16.
 */
public class SettingsActivity extends AppCompatActivity{

    public static final String NUMBER_OF_TABLES = "NUMBER_OF_TABLES";

    private NumberPicker nbPicker;
    private Button cancel;
    private Button ok;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setTitle(getString(R.string.ajustes_title));

        // Reference to the views
        nbPicker = (NumberPicker) findViewById(R.id.numberPicker);

        cancel = (Button) findViewById(R.id.button_cancel);

        ok = (Button) findViewById(R.id.button_ok);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setResult(RESULT_CANCELED);
                finish();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent finish = new Intent();
                getSharedPreferences("Preferences",MODE_PRIVATE).edit().putInt(NUMBER_OF_TABLES,nbPicker.getValue()).apply();
                setResult(RESULT_OK,finish);
                finish();
            }
        });

        nbPicker.setMaxValue(50);
        nbPicker.setMinValue(1);
        nbPicker.setWrapSelectorWheel(true);

    }
}
