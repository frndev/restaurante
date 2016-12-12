package com.example.frannavarro.restaurante.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.frannavarro.restaurante.fragment.PlatesListFragment;
import com.example.frannavarro.restaurante.R;
import com.example.frannavarro.restaurante.model.Plate;
import com.example.frannavarro.restaurante.model.Plates;
import com.example.frannavarro.restaurante.model.Table;

public class PlateListActivity extends AppCompatActivity implements PlatesListFragment.OnPlateSelectedListener,PlatesListFragment.OnMenuItemSelectedListener {


    public static final String PLATE_EXTRA = "PLATE_EXTRA";

    private Table table;
    private Plates plates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plate_list);

        table = (Table) getIntent().getSerializableExtra(TableActivity.TABLE_EXTRA);
        plates = (Plates) getIntent().getSerializableExtra(TableActivity.PLATES_EXTRA);


        FragmentManager fm = getFragmentManager();

        if(fm.findFragmentById(R.id.plate_list_fragment) == null){
            PlatesListFragment platesListFragment = PlatesListFragment.newInstance(table,plates);
            platesListFragment.setOnPlateSelectedListener(this);
            platesListFragment.setOnMenuItemSelectedListener(this);
            fm.beginTransaction()
                    .add(R.id.plate_list_fragment,platesListFragment)
                    .commit();


        }

        setTitle(String.format(getString(R.string.table_format),table.getNumber()));


    }

    @Override
    public void onPlateSelected(Plate plate, int position) {

        Intent detail = new Intent(getApplicationContext(),PlateDetailActivity.class);

        detail.putExtra(PLATE_EXTRA,plate);

        startActivity(detail);

    }

    @Override
    public void onBillSelected(Table table) {
        Intent detail = new Intent(getApplicationContext(),BillActivity.class);

        detail.putExtra(TableActivity.TABLE_EXTRA,table);

        startActivity(detail);
    }
}
