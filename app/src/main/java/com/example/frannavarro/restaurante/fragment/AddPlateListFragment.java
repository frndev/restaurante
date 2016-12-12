package com.example.frannavarro.restaurante.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.frannavarro.restaurante.R;
import com.example.frannavarro.restaurante.model.Plate;
import com.example.frannavarro.restaurante.model.Plates;
import com.example.frannavarro.restaurante.model.Table;

import java.util.LinkedList;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddPlateListFragment extends Fragment {

    private static final String ARGS_PLATES = "ARGS_PLATES";

    private PlatesListFragment.OnPlateSelectedListener onPlateSelectedListener;

    public static AddPlateListFragment newInstance(Plates p) {
        AddPlateListFragment fragment = new AddPlateListFragment();
        Bundle arguments = new Bundle();
        arguments.putSerializable(ARGS_PLATES,p);
        fragment.setArguments(arguments);

        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_plate_list, container, false);


        ListView listView = (ListView) root.findViewById(android.R.id.list);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,getPlates());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (AddPlateListFragment.this.onPlateSelectedListener != null){
                    Plates p = (Plates) AddPlateListFragment.this.getArguments().getSerializable(ARGS_PLATES);
                    AddPlateListFragment.this.onPlateSelectedListener.onPlateSelected(p.getPlates().get(position),position);
                }
            }
        });

        return root;
    }

    private LinkedList<String> getPlates() {

        LinkedList<String> platesList = new LinkedList<>();
        Plates plates = (Plates) this.getArguments().getSerializable(ARGS_PLATES);
        LinkedList<Plate> plist = plates.getPlates();

        for (int i = 0; i < plist.size(); i++) {
            platesList.add(plist.get(i).getName());
        }

        return platesList;


    }

    public void setOnPlateSelectedListener(PlatesListFragment.OnPlateSelectedListener onPlateSelectedListener){
        this.onPlateSelectedListener = onPlateSelectedListener;
    }

}
