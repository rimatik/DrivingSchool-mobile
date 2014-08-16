package com.slaven.radja.autoskola.models;

/**
 * Created by Computer on 09/08/2014.
 */
public class Znak {

    int id;
    String name;
    int id_img;


    public Znak() {
        id = 0;
        name = "";
        id_img = 0;

    }


    public Znak(String name,int id_img) {
        this.name = name;
        this.id_img = id_img;
    }


    public int getId() {
        return id;
    }

    public void setId(int idSign) {
        this.id = idSign;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getId_img() {
        return id_img;
    }

    public void setId_img(int id_img) {
        this.id_img = id_img;
    }

}
