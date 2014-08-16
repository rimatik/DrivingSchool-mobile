package com.slaven.radja.autoskola.ucenje.znakovi.helperi;

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
public class DbHelperZnakoviObavijesti extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 38;
    // Database Name
    private static final String DATABASE_NAME = "autoskolaQuiz";
    // tasks table name
    private static final String TABLE_ZNAK_OBAVIJESTI = "ZnakoviObavijesti";
    // tasks Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ID_IMG = "idImage";

    private SQLiteDatabase dbase;
    public DbHelperZnakoviObavijesti(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_ZNAK_OBAVIJESTI + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
                + " TEXT, " + KEY_ID_IMG + " INTEGER) ";
        Log.e("SLAVEN", sql);
        db.execSQL(sql);
        addSigns();
//db.close();
    }
    private void addSigns()
    {
        Znak z1=new Znak("Cesta sa jednosmjernim prometom", R.drawable.zob_cesta_sa_jednosmjernim_prometom);
        this.addSign(z1);
        Znak z2=new Znak("Cesta sa prednošću prolaska",R.drawable.zob_cesta_sa_prednoscu_prolaska);
        this.addSign(z2);
        Znak z3=new Znak("Djeca na cesti",R.drawable.zob_djeca_na_cesti);
        this.addSign(z3);
        Znak z4=new Znak("Izbočina na cesti",R.drawable.zob_izbocina_na_cesti);
        this.addSign(z4);
        Znak z5=new Znak("Obilježen pješački prelaz",R.drawable.zob_obiljezen_pjesacki_prelaz);
        this.addSign(z5);
        Znak z6=new Znak("Obilježen prijelaz biciklističke staze",R.drawable.zob_obiljezen_prijelaz_biciklisticke_staze);
        this.addSign(z6);
        Znak z7=new Znak("Podzemni ili nadzemni pješački prijelaz",R.drawable.zob_podezemni_ili_nadzemni_pjesacki_prijelaz);
        this.addSign(z7);
        Znak z8=new Znak("Prednost prema vozilima iz suprotnog smjera",R.drawable.zob_prednost_prolaska_prema_vozilima_iz_suprotnog_smjera);
        this.addSign(z8);
        Znak z9=new Znak("Prestanak davanja zvučnih signala",R.drawable.zob_prestanak_davanja_zvucnih_signala);
        this.addSign(z9);
        Znak z10=new Znak("Prestanak najmanje dopuštene brzine",R.drawable.zob_prestanak_najmanje_dopustene_brzine);
        this.addSign(z10);
        Znak z11=new Znak("Prestanak obavezne uporabe zimske opreme",R.drawable.zob_prestanak_obavezne_uporabe_zimske_opreme);
        this.addSign(z11);
        Znak z12=new Znak("Prestanak ograničenja brzine",R.drawable.zob_prestanak_ogranicenja_brzine);
        this.addSign(z12);
        Znak z13=new Znak("Prestanak svih zabrana",R.drawable.zob_prestanak_svih_zabrana);
        this.addSign(z13);
        Znak z14=new Znak("Prestanak zabrane pretjecanja svih motornih vozila osim mopeda",R.drawable.zob_prestanak_zabrane_pretjecanja_svih_motornih_vozila_osim_mopeda);
        this.addSign(z14);
        Znak z15=new Znak("Prestanak zabrane pretjecanja za teretne automobile",R.drawable.zob_prestanak_zabrane_pretjecanja_za_teretne_automobile);
        this.addSign(z15);
        Znak z16=new Znak("Završetak biciklističke ceste",R.drawable.zob_zavrsetak_biciklisticke_ceste);
        this.addSign(z16);
        Znak z17=new Znak("Završetak ceste s prednošću prolaska",R.drawable.zob_zavrsetak_ceste_s_prednoscu_prolaska);
        this.addSign(z17);
        Znak z18=new Znak("Završetak pješačke staze",R.drawable.zob_zavrsetak_pjesacke_staze);
        this.addSign(z18);
        Znak z19=new Znak("Završetak staze za jahače",R.drawable.zob_zavrsetak_staze_za_jahace);
        this.addSign(z19);
        Znak z20=new Znak("Završetak zone u kojoj je ograničena brzina",R.drawable.zob_zavrsetak_zone_u_kojoj_je_ogranicena_brzina);
        this.addSign(z20);
        Znak z21=new Znak("Zona u kojoj je ograničena brzina",R.drawable.zob_zona_u_kojoj_je_ogranicena_brzina);
        this.addSign(z21);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ZNAK_OBAVIJESTI);
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
        dbase.insert(TABLE_ZNAK_OBAVIJESTI, null, values);
    }
    public List<Znak> getAllSigns() {
        List<Znak> signList = new ArrayList<Znak>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_ZNAK_OBAVIJESTI;
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
        String selectQuery = "SELECT  * FROM " + TABLE_ZNAK_OBAVIJESTI;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }

}
