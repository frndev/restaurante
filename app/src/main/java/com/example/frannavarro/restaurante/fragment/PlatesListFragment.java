package com.example.frannavarro.restaurante.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.frannavarro.restaurante.R;
import com.example.frannavarro.restaurante.activity.AddPlateListActivity;
import com.example.frannavarro.restaurante.activity.TableActivity;
import com.example.frannavarro.restaurante.model.Data;
import com.example.frannavarro.restaurante.model.Plate;
import com.example.frannavarro.restaurante.model.Plates;
import com.example.frannavarro.restaurante.model.Table;

import java.util.LinkedList;


/**
 * Created by frannavarro on 9/12/16.
 */

public class PlatesListFragment extends Fragment {

    private static final String ARG_TABLE = "ARG_TABLE";
    private static final String ARG_PLATES = "ARG_PLATES";

    private OnPlateSelectedListener onPlateSelectedListener;
    private OnMenuItemSelectedListener onMenuItemSelectedListener;
    private ListView plateList;

    private Button addPlateButton;

    public static PlatesListFragment newInstance(Table t,Plates p) {
        PlatesListFragment fragment = new PlatesListFragment();

        Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_TABLE, t);
        arguments.putSerializable(ARG_PLATES, p);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_plates_list,container,false);

        plateList = (ListView) root.findViewById(android.R.id.list);

        addPlateButton = (Button) root.findViewById(R.id.add_plate_button);

        addPlateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addPlate = new Intent(getActivity().getApplicationContext(), AddPlateListActivity.class);
                addPlate.putExtra(TableActivity.PLATES_EXTRA,PlatesListFragment.this.getArguments().getSerializable(ARG_PLATES));
                startActivityForResult(addPlate,2);

            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,getPlates());

        plateList.setAdapter(adapter);

        plateList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (PlatesListFragment.this.onPlateSelectedListener != null){
                    Table t = (Table) PlatesListFragment.this.getArguments().getSerializable(ARG_TABLE);
                    PlatesListFragment.this.onPlateSelectedListener.onPlateSelected(t.getPlates().get(position),position);
                }

            }
        });

        return root;
    }

    private LinkedList<String> getPlates() {

        LinkedList<String> platesList = new LinkedList<>();
        LinkedList<Plate> platesArray = ((Table) this.getArguments().getSerializable(ARG_TABLE)).getPlates();

        for (int i = 0; i < platesArray.size(); i++) {
            platesList.add(platesArray.get(i).getName());
        }

        return platesList;


    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

       inflater.inflate(R.menu.create_bill,menu);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.bill) {
            onMenuItemSelectedListener.onBillSelected((Table) this.getArguments().getSerializable(ARG_TABLE));
            return true;
        }

        return false;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == 2) {

            Plate p = (Plate) data.getSerializableExtra(AddPlateListActivity.PLATE_EXTRA);

            Table t = (Table) this.getArguments().getSerializable(ARG_TABLE);

            Data.addPlate(p,t.getNumber() - 1);

            getArguments().putSerializable(ARG_TABLE,Data.getTables().get(t.getNumber() - 1));

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,getPlates());

            plateList.setAdapter(adapter);


        }
    }

    // LISTENERS

    public void setOnMenuItemSelectedListener(OnMenuItemSelectedListener onMenuItemSelectedListener){
        this.onMenuItemSelectedListener = onMenuItemSelectedListener;
    }

    public void setOnPlateSelectedListener(OnPlateSelectedListener onPlateSelectedListener){
        this.onPlateSelectedListener = onPlateSelectedListener;
    }

    public interface OnMenuItemSelectedListener{
        void onBillSelected(Table table);
    }


    public interface OnPlateSelectedListener {
        void onPlateSelected(Plate plate, int position);
    }

}
