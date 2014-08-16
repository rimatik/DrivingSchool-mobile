package com.slaven.radja.autoskola.models;

/**
 * Created by Computer on 14/08/2014.
 */
public class Semafor {

    int id;
    String name;
    int id_img;
    private boolean hasImage;

    public Semafor() {
        id = 0;
        name = "";
        id_img = 0;
        hasImage = false;
    }



    public Semafor(String name,int id_img,boolean hasImage) {
        this.name = name;
        this.id_img = id_img;
        this.hasImage = hasImage;

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


    public boolean isHasImage() {
        return hasImage;
    }

    public void setHasImage(boolean hasImage) {
        this.hasImage = hasImage;
    }

}
