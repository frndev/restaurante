package com.example.frannavarro.restaurante.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.frannavarro.restaurante.R;
import com.example.frannavarro.restaurante.model.Plate;

import java.util.LinkedList;


/**
 * Created by frannavarro on 12/12/16.
 */

public class PlateListAdapter extends ArrayAdapter<Plate> {

    private Context context;
    private int resource;

    public PlateListAdapter(Context context, int resource, LinkedList<Plate> plates) {
        super(context, resource, plates);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(resource,null);

        Plate plate = getItem(position);

        ImageView plateImage = (ImageView) v.findViewById(R.id.plate_list_image);

        Context context = plateImage.getContext();
        int id = context.getResources().getIdentifier(plate.getImage(), "drawable", context.getPackageName());
        plateImage.setImageResource(id);

        TextView nameTextView = (TextView) v.findViewById(R.id.plate_list_name);

        nameTextView.setText(plate.getName());

        TextView priceTextView = (TextView) v.findViewById(R.id.plate_list_price);

        priceTextView.setText(String.format(context.getString(R.string.price_format),plate.getPrice()));





        return v;

    }
}
