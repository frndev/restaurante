package com.example.frannavarro.restaurante.model;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by frannavarro on 11/12/16.
 */

public class Plates implements Serializable {

    private LinkedList<Plate> plates;

    public Plates(){
        plates = new LinkedList<>();
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
}
