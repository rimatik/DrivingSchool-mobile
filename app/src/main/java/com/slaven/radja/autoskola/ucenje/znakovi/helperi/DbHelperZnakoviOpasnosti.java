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
    private static final int DATABASE_VERSION = 37;
    // Database Name
    private static final String DATABASE_NAME = "autoskolaQuiz";
    // tasks table name
    private static final String TABLE_ZNAK_OPASNOSTI = "znakoviOpasnosti";
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
        Znak z1=new Znak("Raskrizje cesta iste važnosti",R.drawable.zo_raskrizje_cesta_iste_vaznosti);
        this.addSign(z1);
        Znak z2=new Znak("Dvostruki zavoj ili više uzastopnih zavoja od kojih je prvi udesno",R.drawable.zo_dvostruki_zavoj_ili_vise_uzastopnih_zavoja_od_kojih_je_prvi_udesno);
        this.addSign(z2);
        Znak z3=new Znak("Dvostruki zavoj ili više uzastopnih zavoja od kojih je prvi ulijevo",R.drawable.zo_dvostruki_zavoj_ili_vise_uzastopnih_zavoja_od_kojih_je_prvi_ulijevo);
        this.addSign(z3);
        Znak z4=new Znak("Pada kamenje",R.drawable.zo_kamenje_pada);
        this.addSign(z4);
        Znak z5=new Znak("Kamenje pršti",R.drawable.zo_kamenje_prsti);
        this.addSign(z5);
        Znak z6=new Znak("Nailazak na prometna svijetla",R.drawable.zo_nailazak_na_prometna_svijetla);
        this.addSign(z6);
        Znak z7=new Znak("Neravni kolnik",R.drawable.zo_neravan_kolnik);
        this.addSign(z7);
        Znak z8=new Znak("Opasna nizbrdica",R.drawable.zo_opasna_nizbrdica);
        this.addSign(z8);
        Znak z9=new Znak("Opasna uzbrdica",R.drawable.zo_opasna_uzbrdica);
        this.addSign(z9);
        Znak z10=new Znak("Raskrižje sa sporednom cestom pod pravim kutom",R.drawable.zo_raskrizje_sa_sporednom_cestom_pod_pravim_kutom);
        this.addSign(z10);
        Znak z11=new Znak("Sklizak kolnik",R.drawable.zo_sklizak_kolnik);
        this.addSign(z11);
        Znak z12=new Znak("Spajanje sporedne ceste pod pravim kutom s lijeve strane",R.drawable.zo_spajanje_sporedne_ceste_pod_pravim_kutom_s_lijeve_strane);
        this.addSign(z12);
        Znak z13=new Znak("Spajanje sporedne ceste pod pravim kutom s desne strane",R.drawable.zo_spajanje_sporedne_ceste_pod_pravim_kutom_s_desne_strane);
        this.addSign(z13);
        Znak z14=new Znak("Spajanje sporedne ceste pod oštrim kutom s desne strane",R.drawable.zo_spajanje_sporedne_ceste_pod_ostrim_kutom_s_desne_strane);
        this.addSign(z14);
        Znak z15=new Znak("Spajanje sporedne ceste pod oštrim kutom s lijeve strane",R.drawable.zo_spajanje_sporedne_ceste_pod_ostrim_kutom_s_lijeve_strane);
        this.addSign(z15);
        Znak z16=new Znak("Suženje ceste",R.drawable.zo_suzenje_ceste);
        this.addSign(z16);
        Znak z17=new Znak("Suženje ceste s desne strane",R.drawable.zo_suzenje_ceste_s_desne_strane);
        this.addSign(z17);
        Znak z18=new Znak("Suženje ceste s lijeve strane",R.drawable.zo_suzenje_ceste_s_lijeve_strane);
        this.addSign(z18);
        Znak z19=new Znak("Zavoj u desno",R.drawable.zo_zavoj_u_desno);
        this.addSign(z19);
        Znak z20=new Znak("Zavoj u lijevo",R.drawable.zo_zavoj_u_lijevo);
        this.addSign(z20);
        Znak z21=new Znak("Zavoj u lijevo",R.drawable.zo_opasnost_na_cesti);
        this.addSign(z21);

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



