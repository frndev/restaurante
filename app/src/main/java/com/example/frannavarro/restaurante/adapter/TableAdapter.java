package com.example.frannavarro.restaurante.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.frannavarro.restaurante.R;
import com.example.frannavarro.restaurante.model.Table;

import java.util.LinkedList;

/**
 * Created by fran navarro on 10/12/16.
 */

public class TableAdapter extends ArrayAdapter<Table> {

    private Context context;
    private int resource;

    public TableAdapter(Context context, int resource, LinkedList<Table> tables) {
        super(context, resource, tables);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(resource,null);

        Table t = getItem(position);

        TextView numberTextView = (TextView) v.findViewById(R.id.number_table);

        numberTextView.setText(String.valueOf(t.getNumber()));

        return v;

    }
}
