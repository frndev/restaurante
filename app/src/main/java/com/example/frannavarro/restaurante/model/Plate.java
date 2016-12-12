package com.example.frannavarro.restaurante.model;

import java.io.Serializable;

/**
 * Created by frannavarro on 10/12/16.
 */

public class Plate  implements Serializable{

    private String name;
    private String image;
    private String[] ingredients;
    private float price;

    public Plate(String name, String image, String[] ingredients, float price) {
        this.name = name;
        this.image = image;
        this.ingredients = ingredients;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
