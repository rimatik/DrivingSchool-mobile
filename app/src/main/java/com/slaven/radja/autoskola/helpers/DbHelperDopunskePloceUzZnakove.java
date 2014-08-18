package com.slaven.radja.autoskola.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.models.Znak;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Computer on 16/08/2014.
 */
public class DbHelperDopunskePloceUzZnakove extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "autoskola.db";
    // tasks table name
    private static final String TABLE_ZNAK_DOPUNSKE_PLOCE = "znakoviDopunskePloce";
    // tasks Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ID_IMG = "idImage";

    private SQLiteDatabase dbase;
    public DbHelperDopunskePloceUzZnakove(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_ZNAK_DOPUNSKE_PLOCE + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
                + " TEXT, " + KEY_ID_IMG + " INTEGER) ";
        Log.e("SLAVEN", sql);
        db.execSQL(sql);
        addSigns();
//db.close();
    }
    private void addSigns()
    {
        Znak z1=new Znak("", R.drawable.zdp_dodatni_glavna_cesta);
        this.addSign(z1);
        Znak z2=new Znak("",R.drawable.zdp_dodatni_sporedna_cesta);
        this.addSign(z2);
        Znak z3=new Znak("",R.drawable.zdp_kamjon);
        this.addSign(z3);
        Znak z4=new Znak("",R.drawable.zdp_kocka_sesto_metara);
        this.addSign(z4);
        Znak z5=new Znak("",R.drawable.zdp_kola_trazi);
        this.addSign(z5);
        Znak z6=new Znak("",R.drawable.zdp_osim_stanara);
        this.addSign(z6);
        Znak z7=new Znak("",R.drawable.zdp_parking_auto);
        this.addSign(z7);
        Znak z8=new Znak("",R.drawable.zdp_parking_invalid);
        this.addSign(z8);
        Znak z9=new Znak("",R.drawable.zdp_stop_sesto_metara);
        this.addSign(z9);
        Znak z10=new Znak("",R.drawable.zdp_tri_znaka_sesto_metara);
        this.addSign(z10);
        Znak z11=new Znak("",R.drawable.zdp_zabrana_parkiranja_cetiri_do_sest);
        this.addSign(z11);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ZNAK_DOPUNSKE_PLOCE);
// Create tables again
        onCreate(db);
    }
    // Adding new question
    public void addSign(Znak sign) {
//SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, sign.getName());
        values.put(KEY_ID_IMG, sign.getId_img());


// Inserting Row
        dbase.insert(TABLE_ZNAK_DOPUNSKE_PLOCE, null, values);
    }
    public List<Znak> getAllSigns() {
        List<Znak> signList = new ArrayList<Znak>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_ZNAK_DOPUNSKE_PLOCE;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Znak sign = new Znak();
                sign.setId(cursor.getInt(0));
                sign.setName(cursor.getString(1));
                sign.setId_img(cursor.getInt(2));
                signList.add(sign);
            } while (cursor.moveToNext());
        }
// return quest list
        return signList;
    }
    public int rowcount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + TABLE_ZNAK_DOPUNSKE_PLOCE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }

}
