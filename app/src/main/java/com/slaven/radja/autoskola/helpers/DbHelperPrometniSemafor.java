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
 * Created by Computer on 14/08/2014.
 */
public class DbHelperPrometniSemafor extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 36;
    // Database Name
    private static final String DATABASE_NAME = "autoskolaQuiz";
    // tasks table name
    private static final String TABLE_PROMETNI_SEMAFOR = "prometniSemafor";
    // tasks Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ID_IMG = "idImage";
    private static final String KEY_HAS_IMAGE = "has_image";

    private SQLiteDatabase dbase;
    public DbHelperPrometniSemafor(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_PROMETNI_SEMAFOR + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
                + " TEXT, " + KEY_ID_IMG + " INTEGER, " +  KEY_HAS_IMAGE + " INTEGER)";
        Log.e("SLAVEN", sql);
        db.execSQL(sql);
        addTrafficLights();
//db.close();
    }
    private void addTrafficLights()
    {
        Semafor z1=new Semafor("Crveno svjetlo - zabrana prolaska", R.drawable.semafor_crveno,true);
        this.addTrafiicLight(z1);
        Semafor z2=new Semafor("Zeleno svjetlo - slobodan prolazak",R.drawable.semafor_zeleno,true);
        this.addTrafiicLight(z2);
        Semafor z3=new Semafor("Žuto svjetlo - upaljeno samostalno, znači da vozilo ne smije prijeći crtu zaustavljanja niti smije ući u raskrižje, ako se u trenutku kad se žuto svjetlo pojavi, nalazi na takvoj udaljenosti od prometnog svjetla da se može na siguran način zaustaviti.",R.drawable.semafor_zuto,true);
        this.addTrafiicLight(z3);
        Semafor z4=new Semafor("Žuto svjetlo upaljeno istovremeno s crvenim svjetlom označava skoru promjenu svjetla i pojavu zelenog svjetla, ali ne mijenja zabranu prolaska koja je data crvenim svjetlom.",R.drawable.semafor_crveno_zuto,true);
        this.addTrafiicLight(z4);
        Semafor z5=new Semafor("Žuto treptavo svjetlo obvezuje sve sudionike u prometu da se kreću uz povećani oprez.",R.drawable.zuto_trepetavo_svijetlo_img,true);
        this.addTrafiicLight(z5);
        Semafor z6=new Semafor("Zeleno treptavo svjetlo služi za upozorenje sudionika u prometu na skori prestanak slobodnog prolaska i na pojavu žutoga, odnosno crvenog svjetla.",R.drawable.zeleno_trepetavo_svijetlo_img,true);
        this.addTrafiicLight(z6);
        Semafor z7=new Semafor("Ako je uređaju za davanje znakova prometnim svjetlima za upravljanje prometom na raskrižju dodano jedno dopunsko prometno svjetlo ili više dopunskih prometnih svjetala u obliku zelene svjetleće strelice, za vrijeme dok je takvo svjetlo upaljeno, vozač može vozilom proći prometno svjetlo i krenuti u smjeru označenom zelenom svjetlećom strelicom i za vrijeme dok je upaljeno crveno ili žuto svjetlo, pri čemu mora propustiti vozila koja se kreću po cesti na koju ulazi i pješake koji prelaze preko kolnika.\n\n" +
                "Ako zeleno svjetlo na uređaju za davanje znakova prometnim svjetlima ima oblik strelice, vozač se vozilom smije kretati samo u smjeru koji pokazuje ta strelica.\n\n" +
                "Vozač koji je vozilom ušao u raskrižje na kojem se upravlja prometom uređajima za davanje znakova prometnim svjetlima, može napustiti raskrižje ne čekajući da prometnim svjetlom promet bude dopušten (otvoren) u smjeru kojim namjerava produžiti kretanje, uz uvjet da propusti sve sudionike u prometu koji se kreću smjerom u kojem je promet dopušten.\n\n" +
                "Vozač ne smije vozilom ući u raskrižje iako mu je prolazak prometnim svjetlom dopušten, ako je gustoća prometa takva da se on s vozilom očito mora zaustaviti u raskrižju ili na obilježenom pješačkom prijelazu i na taj način pri promjeni prometnog svjetla ometati ili onemogućiti promet vozila koja dolaze s bočnih kolnika ili promet pješaka.\n\n"
                ,R.drawable.dopunska_strelica_desno,true);
        this.addTrafiicLight(z7);





    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROMETNI_SEMAFOR);
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
        dbase.insert(TABLE_PROMETNI_SEMAFOR, null, values);
    }
    public List<Semafor> getAllTrafficLights() {
        List<Semafor> semaforList = new ArrayList<Semafor>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_PROMETNI_SEMAFOR;
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
