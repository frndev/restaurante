package com.example.frannavarro.restaurante.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import com.example.frannavarro.restaurante.R;
import com.example.frannavarro.restaurante.activity.SettingsActivity;
import com.example.frannavarro.restaurante.adapter.TableAdapter;
import com.example.frannavarro.restaurante.model.Data;
import com.example.frannavarro.restaurante.model.Plate;
import com.example.frannavarro.restaurante.model.Plates;
import com.example.frannavarro.restaurante.model.Table;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static com.example.frannavarro.restaurante.activity.TableActivity.SETTINGS_RESULT_CODE;


public class TableListFragment extends Fragment {

    private OnTableListSelectedListener onTableListSelectedListener;
    private TableAdapter adapter;
    private GridView gridView;
    private Plates plates;
    // LIFECYCLE

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_table_list,container,false);

        gridView = (GridView) root.findViewById(R.id.table_list_fragment);

        // Plates plates = new Plates();
        Data.addTable(new Table());
        Data.addTable(new Table());
        Data.addTable(new Table());

        adapter = new TableAdapter(getActivity(),R.layout.view_table_item,Data.getTables());

        gridView.setAdapter(adapter);
        onTableListSelectedListener = (OnTableListSelectedListener) getActivity();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (onTableListSelectedListener != null){
                    onTableListSelectedListener.onTableListSelected(Data.getTables().get(position));
                }

            }
        });

        return root;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == SETTINGS_RESULT_CODE) {

            int number = getActivity().getSharedPreferences("Preferences",MODE_PRIVATE).getInt(SettingsActivity.NUMBER_OF_TABLES,1);

            updateTable(number);

        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);

        inflater.inflate(R.menu.settings,menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.settings) {
            Intent settings = new Intent(getActivity().getApplicationContext(),SettingsActivity.class);

            startActivityForResult(settings,SETTINGS_RESULT_CODE);

            return true;
        }

        return false;
    }

    // END LIFECYCLE



    // UTILS




    private void updateTable(int number) {

        final LinkedList<Table> tables = new LinkedList<>();
        Data.removeAll();
        for (int i = 1; i<= number;i++){

            Data.addTable(new Table());

        }

        adapter = new TableAdapter(getActivity(),R.layout.view_table_item,tables);
        gridView.setAdapter(adapter);

    }

    // END UTILS

    // LISTENERS

    public void setOnTableListSelectedListener(OnTableListSelectedListener onTableListSelectedListener){
        this.onTableListSelectedListener = onTableListSelectedListener;
    }

    public interface OnTableListSelectedListener {
        void onTableListSelected(Table table);
    }

}


