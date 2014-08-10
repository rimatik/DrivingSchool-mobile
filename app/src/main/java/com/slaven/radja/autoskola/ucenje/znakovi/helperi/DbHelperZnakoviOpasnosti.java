package com.slaven.radja.autoskola.ucenje.znakovi.helperi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.ucenje.znakovi.Znak;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Computer on 09/08/2014.
 */
public class DbHelperZnakoviOpasnosti extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 15;
    // Database Name
    private static final String DATABASE_NAME = "autoskolaQuiz";
    // tasks table name
    private static final String TABLE_ZNAK_OPASNOSTI = "ZnakoviOpasnosti";
    // tasks Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ID_IMG = "idImage";

    private SQLiteDatabase dbase;
    public DbHelperZnakoviOpasnosti(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_ZNAK_OPASNOSTI + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
                + " TEXT, " + KEY_ID_IMG + " INTEGER) ";
        Log.e("SLAVEN", sql);
        db.execSQL(sql);
        addSigns();
//db.close();
    }
    private void addSigns()
    {
        Znak z1=new Znak("Križanje cesta iste važnosti",R.drawable.raskrizje_ceste_iste_vaznosti_img);
        this.addSign(z1);
        Znak z2=new Znak("Zabrana prometa u jednom smjeru",R.drawable.zabrana_prometa_u_jednom_smjeru_img);
        this.addSign(z2);
        Znak z3=new Znak("Raskrižje sa sporednom cestom pod pravim kutom",R.drawable.raskrizje_sa_sporednom_cestom_pod_pravim_kutom_img);
        this.addSign(z3);
        Znak z4=new Znak("Školska patrola",R.drawable.skolska_patrola_img);
        this.addSign(z4);
        Znak z5=new Znak("Spajanje sporedne ceste pod pravim kutom s lijeve strane",R.drawable.spajanje_sporedne_ceste_pod_pravim_kutom_s_lijeve_strane_img);
        this.addSign(z5);



    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ZNAK_OPASNOSTI);
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
        dbase.insert(TABLE_ZNAK_OPASNOSTI, null, values);
    }
    public List<Znak> getAllSigns() {
        List<Znak> signList = new ArrayList<Znak>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_ZNAK_OPASNOSTI;
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
        String selectQuery = "SELECT  * FROM " + TABLE_ZNAK_OPASNOSTI;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }
}



