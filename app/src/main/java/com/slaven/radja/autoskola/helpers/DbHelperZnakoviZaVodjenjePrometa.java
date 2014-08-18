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
public class DbHelperZnakoviZaVodjenjePrometa extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "autoskola.db";
    // tasks table name
    private static final String TABLE_ZNAK_VODJENJE_PROMETA = "znakoviZaVodjenjePrometa";
    // tasks Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ID_IMG = "idImage";

    private SQLiteDatabase dbase;
    public DbHelperZnakoviZaVodjenjePrometa(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_ZNAK_VODJENJE_PROMETA + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
                + " TEXT, " + KEY_ID_IMG + " INTEGER) ";
        Log.e("SLAVEN", sql);
        db.execSQL(sql);
        addSigns();
//db.close();
    }
    private void addSigns()
    {

        Znak z1=new Znak("Potvrda smjera", R.drawable.zvp_potvrda_smjera);
        this.addSign(z1);
        Znak z2=new Znak("Predputokaz",R.drawable.zvp_predputokaz);
        this.addSign(z2);
        Znak z3=new Znak("Predputokaz za čvoriste autocesta s oznakom čvorišta",R.drawable.zvp_predputokaz_za_cvoriste_autocesta_s_oznakom_cvorista);
        this.addSign(z3);
        Znak z4=new Znak("Predputokaz za izlaz",R.drawable.zvp_predputokaz_za_izlaz);
        this.addSign(z4);
        Znak z5=new Znak("Predputokaz za izlaz s autoceste ili brze ceste s oznakom izlaza",R.drawable.zvp_predputokaz_za_izlaz_s_autoceste_ili_brze_ceste_s_oznakom_izlaza);
        this.addSign(z5);
        Znak z6=new Znak("Predputokazna ploča",R.drawable.zvp_predputokazna_ploca);
        this.addSign(z6);
        Znak z7=new Znak("Putokaz na portalu iznad dvije putokazne ploče",R.drawable.zvp_putokaz_na_portalu_iznad_dvije_prometne_trake);
        this.addSign(z7);
        Znak z8=new Znak("Putokaz na portalu iznad jedne prometne trake",R.drawable.zvp_putokaz_na_portalu_iznad_jedne_prometne_trake);
        this.addSign(z8);
        Znak z9=new Znak("Putokazna ploča",R.drawable.zvp_putokazna_ploca);
        this.addSign(z9);
        Znak z10=new Znak("Raskrižje",R.drawable.zvp_raskrizje);
        this.addSign(z10);
        Znak z11=new Znak("Raskrižje kružnog oblika",R.drawable.zvp_raskrizje_kruznog_oblika);
        this.addSign(z11);


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ZNAK_VODJENJE_PROMETA);
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
        dbase.insert(TABLE_ZNAK_VODJENJE_PROMETA, null, values);
    }
    public List<Znak> getAllSigns() {
        List<Znak> signList = new ArrayList<Znak>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_ZNAK_VODJENJE_PROMETA;
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
        String selectQuery = "SELECT  * FROM " + TABLE_ZNAK_VODJENJE_PROMETA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }

}
