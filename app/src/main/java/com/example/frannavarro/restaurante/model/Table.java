package com.example.frannavarro.restaurante.model;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by frannavarro on 10/12/16.
 */

public class Table  implements Serializable{

    private int number;
    private LinkedList<Plate> plates;

    public static void setNext(int next) {
        Table.next = next;
    }

    private static int next = 1;

    public Table(int number, LinkedList<Plate> plates) {
        this.number = number;
        this.plates = plates;
    }

    public Table(){
        this(next,new LinkedList<Plate>());
        next++;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LinkedList<Plate> getPlates() {
        return plates;
    }

    public void setPlates(LinkedList<Plate> plates) {
        this.plates = plates;
    }

    public void addPlate(Plate p){
        plates.add(p);
    }

    public float getBill(){
        float total = 0;
        for (int i = 0; i< plates.size();i++){
            total += plates.get(i).getPrice();
        }
        return total;
    }
}
