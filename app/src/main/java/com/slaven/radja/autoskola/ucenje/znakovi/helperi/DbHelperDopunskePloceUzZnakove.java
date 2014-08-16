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
 * Created by Computer on 16/08/2014.
 */
public class DbHelperDopunskePloceUzZnakove extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 40;
    // Database Name
    private static final String DATABASE_NAME = "autoskolaQuiz";
    // tasks table name
    private static final String TABLE_ZNAK_DOPUNSKE_PLOCE = "ZnakoviDopunskePloce";
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
        Znak z1=new Znak("Obavezno zaustavljanje", R.drawable.zi_obavezno_zaustavljanje);
        this.addSign(z1);
        Znak z2=new Znak("Raskrižje s cestom koja ima prednost prolaza",R.drawable.zi_raskrizje_s_cestom_koja_ima_prednost_prolaza);
        this.addSign(z2);
        Znak z3=new Znak("Zabrana prometa u oba smjera",R.drawable.zi_zabrana_prometa_u_oba_smjera);
        this.addSign(z3);
        Znak z4=new Znak("Zabrana prometa za autobuse",R.drawable.zi_zabrana_prometa_za_autobuse);
        this.addSign(z4);
        Znak z5=new Znak("Zabrana prometa za bicikle",R.drawable.zi_zabrana_prometa_za_bicikle);
        this.addSign(z5);
        Znak z6=new Znak("Zabrana prometa za cisterne",R.drawable.zi_zabrana_prometa_za_cisterne);
        this.addSign(z6);
        Znak z7=new Znak("Zabrana prometa za mopede",R.drawable.zi_zabrana_prometa_za_mopede);
        this.addSign(z7);
        Znak z8=new Znak("Zabrana prometa za mopede i bicikle",R.drawable.zi_zabrana_prometa_za_mopede_i_bicikle);
        this.addSign(z8);
        Znak z9=new Znak("Zabrana prometa za motocikle",R.drawable.zi_zabrana_prometa_za_motocikle);
        this.addSign(z9);
        Znak z10=new Znak("Zabrana prometa za pješake",R.drawable.zi_zabrana_prometa_za_pjesake);
        this.addSign(z10);
        Znak z11=new Znak("Zabrana prometa za ručna kolica",R.drawable.zi_zabrana_prometa_za_rucna_kolica);
        this.addSign(z11);
        Znak z12=new Znak("Zabrana prometa za sva motorna vozila",R.drawable.zi_zabrana_prometa_za_sva_motorna_vozila);
        this.addSign(z12);
        Znak z13=new Znak("Zabrana prometa za sva motorna vozila osim za motocikle bez prikolice i mopede",R.drawable.zi_zabrana_prometa_za_sva_motorna_vozila_osim_za_motocikle_bez_prikolice_i_mopede);
        this.addSign(z13);
        Znak z14=new Znak("Zabrana prometa za teretne automobile",R.drawable.zi_zabrana_prometa_za_teretne_automobile);
        this.addSign(z14);
        Znak z15=new Znak("Zabrana prometa za traktore",R.drawable.zi_zabrana_prometa_za_traktore);
        this.addSign(z15);
        Znak z16=new Znak("Zabrana prometa za vozila čija širina prelazi određenu širinu",R.drawable.zi_zabrana_prometa_za_vozila_cija_sirina_prelazi_odredjenu_sirinu);
        this.addSign(z16);
        Znak z17=new Znak("Zabrana prometa za vozila čija visina prelazi određenu visinu",R.drawable.zi_zabrana_prometa_za_vozila_cija_ukupna_visina_prelazi_odredjenu_visinu);
        this.addSign(z17);
        Znak z18=new Znak("Zabrana prometa za vozila čija ukupna masa prelazi određenu masu",R.drawable.zi_zabrana_prometa_za_vozila_cija_ukupna_masa_prelazi_odredjenu_masu);
        this.addSign(z18);
        Znak z19=new Znak("Zabrana prometa za vozila koja prevoze eksploziv ili neke lako zapaljive tvari",R.drawable.zi_zabrana_prometa_za_vozila_koja_prevoze_eksploziv_ili_neke_lakozapaljive_tvari);
        this.addSign(z19);
        Znak z20=new Znak("Zabrana prometa u jednom smjeru",R.drawable.zi_zabrana_prometa_u_jednom_smjeru);
        this.addSign(z20);
        Znak z21=new Znak("Zabrana za zaprežna vozila",R.drawable.zi_zabrana_prometa_za_zaprezna_vozila);
        this.addSign(z21);

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
