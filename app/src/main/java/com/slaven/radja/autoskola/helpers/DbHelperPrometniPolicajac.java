package com.slaven.radja.autoskola.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.models.Semafor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Computer on 18/08/2014.
 */
public class DbHelperPrometniPolicajac extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 11;
    // Database Name
    private static final String DATABASE_NAME = "autoskola.db";
    // tasks table name
    private static final String TABLE_PROMETNI_POLICAJAC = "PrometniPolicajac";
    // tasks Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ID_IMG = "idImage";
    private static final String KEY_HAS_IMAGE = "has_image";

    private SQLiteDatabase dbase;
    public DbHelperPrometniPolicajac(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_PROMETNI_POLICAJAC + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
                + " TEXT, " + KEY_ID_IMG + " INTEGER, " +  KEY_HAS_IMAGE + " INTEGER)";
        Log.e("SLAVEN", sql);
        db.execSQL(sql);
        addTrafficLights();
//db.close();
    }
    private void addTrafficLights()
    {
        Semafor z1=new Semafor("Obavezno zaustavljanje", R.drawable.obvezno_zaustavljanje,true);
        this.addTrafiicLight(z1);
        Semafor z2=new Semafor("Obavezno zaustavljanje za sva vozila ispred raskrižja",R.drawable.obvezno_zaustavljanje_za_sva_vozila_ispred_raskrizja,true);
        this.addTrafiicLight(z2);
        Semafor z3=new Semafor("Obavezno zaustavljanje za sva vozila prema kojima je okrenut dlan",R.drawable.obvezno_zaustavljanje_za_sva_vozila_prema_kojima_je_okrenut_dlan,true);
        this.addTrafiicLight(z3);
        Semafor z4=new Semafor("Priđite prometnom policajcu",R.drawable.pridite_prometnom_policajcu,true);
        this.addTrafiicLight(z4);
        Semafor z5=new Semafor("Slobodan bocni prolaz",R.drawable.slobodan_bocni_prolaz,true);
        this.addTrafiicLight(z5);
        Semafor z6=new Semafor("Zabrana prolaska smjerom koji sjece smjer ispruzene ruke",R.drawable.zabrana_prolaska_smjerom_koji_sijece_smjer_ispruzene_ruke,true);
        this.addTrafiicLight(z6);
        Semafor z7=new Semafor("Obaveza povećanja brzine kretanja",R.drawable.obveza_povecanja_brzine_kretanja,true);
        this.addTrafiicLight(z7);
        Semafor z8=new Semafor("Obaveza smanjivanja brzine kretanja",R.drawable.obveza_smanjivanja_brzine_kretanja,true);
        this.addTrafiicLight(z8);
        Semafor z9=new Semafor("Prometni policajac na raskrižju",R.drawable.prometni_policajac_na_raskrizju_slika_1,true);
        this.addTrafiicLight(z9);
        Semafor z10=new Semafor("Prometni policajac na raskrižju",R.drawable.prometni_policajac_na_raskrizju_slika_2,true);
        this.addTrafiicLight(z10);
        Semafor z11=new Semafor("Prometni policajac na raskrižju",R.drawable.prometni_policajac_na_raskrizju_slika_3,true);
        this.addTrafiicLight(z11);





    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROMETNI_POLICAJAC);
// Create tables again
        onCreate(db);
    }
    // Adding new question
    public void addTrafiicLight(Semafor semafor) {
//SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, semafor.getName());
        values.put(KEY_ID_IMG, semafor.getId_img());
        values.put(KEY_HAS_IMAGE, semafor.isHasImage());

// Inserting Row
        dbase.insert(TABLE_PROMETNI_POLICAJAC, null, values);
    }
    public List<Semafor> getAllCops() {
        List<Semafor> semaforList = new ArrayList<Semafor>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_PROMETNI_POLICAJAC;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Semafor semafor = new Semafor();
                semafor.setId(cursor.getInt(0));
                semafor.setName(cursor.getString(1));
                semafor.setId_img(cursor.getInt(2));
                semafor.setHasImage(cursor.getInt(3) == 1);
                semaforList.add(semafor);
            } while (cursor.moveToNext());
        }
// return quest list
        return semaforList;
    }


}
