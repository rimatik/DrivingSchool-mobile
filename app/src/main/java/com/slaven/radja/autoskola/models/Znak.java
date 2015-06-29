package com.slaven.radja.autoskola.models;

/**
 * Created by Computer on 09/08/2014.
 */
public class Znak {

    int id;
    int position;
    byte [] bitmap;


    public Znak() {
        id = 0;

    }
    public Znak(byte[] bitmap, int position) {
        this.position = position;
        this.bitmap = bitmap;
    }

    public Znak(byte [] bitmap) {
        this.bitmap = bitmap;
    }

    public int getId() {
        return id;
    }

    public void setId(int idSign) {
        this.id = idSign;
    }

    public byte[] getBitmap() {
        return bitmap;
    }

    public void setBitmap(byte[] bitmap) {
        this.bitmap = bitmap;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
