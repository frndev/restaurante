package com.example.frannavarro.restaurante.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.frannavarro.restaurante.R;
import com.example.frannavarro.restaurante.fragment.AddPlateListFragment;
import com.example.frannavarro.restaurante.fragment.PlatesListFragment;
import com.example.frannavarro.restaurante.fragment.TableListFragment;
import com.example.frannavarro.restaurante.model.Plate;
import com.example.frannavarro.restaurante.model.Plates;

public class AddPlateListActivity extends AppCompatActivity implements PlatesListFragment.OnPlateSelectedListener{


    private AddPlateListFragment addPlateListFragment;

    public static final String PLATE_EXTRA = "PLATE_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plate_list);

        FragmentManager fm = getFragmentManager();

        if(fm.findFragmentById(R.id.add_plate_list_fragment) == null){
            addPlateListFragment = AddPlateListFragment.newInstance((Plates) getIntent().getSerializableExtra(TableActivity.PLATES_EXTRA));
            addPlateListFragment.setOnPlateSelectedListener(this);
            fm.beginTransaction()
                    .add(R.id.add_plate_list_fragment,addPlateListFragment)
                    .commit();


        }

    }

    @Override
    public void onPlateSelected(Plate plate, int position) {

        Intent returnIntent = new Intent();

        returnIntent.putExtra(PLATE_EXTRA,plate);

        setResult(RESULT_OK,returnIntent);

        finish();

    }
}
