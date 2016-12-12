package com.example.frannavarro.restaurante.model;

import com.example.frannavarro.restaurante.model.Table;

import java.util.LinkedList;

/**
 * Created by frannavarro on 12/12/16.
 */

public class Data {

    public static LinkedList<Table> getTables() {
        return tables;
    }

    private static LinkedList<Table> tables = new LinkedList<>();


    public static void addTable(Table t){
        tables.add(t);
    }

    public static void addPlate(Plate p, int position) {

        tables.get(position).addPlate(p);

    }

    public static void removeAll(){
        tables = new LinkedList<>();
        Table.setNext(1);
    }

}
