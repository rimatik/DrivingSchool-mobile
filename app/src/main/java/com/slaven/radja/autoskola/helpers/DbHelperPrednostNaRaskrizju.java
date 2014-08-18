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
public class DbHelperPrednostNaRaskrizju extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 10;
    // Database Name
    private static final String DATABASE_NAME = "autoskola.db";
    // tasks table name
    private static final String TABLE_PREDNOST_NA_RASKRIZJU = "PrednostNaRaskrizju";
    // tasks Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ID_IMG = "idImage";
    private static final String KEY_HAS_IMAGE = "has_image";

    private SQLiteDatabase dbase;
    public DbHelperPrednostNaRaskrizju(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_PREDNOST_NA_RASKRIZJU + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
                + " TEXT, " + KEY_ID_IMG + " INTEGER, " +  KEY_HAS_IMAGE + " INTEGER)";
        Log.e("SLAVEN", sql);
        db.execSQL(sql);
        addRaskrizja();
//db.close();
    }
    private void addRaskrizja()
    {
        Semafor z1=new Semafor("1.) Na raskrižju cesta iste važnosti ili u susretu s drugim vozilom vozač je dužan propustit vozilo koje nailazi s njegove desne strane.", -1,false);
        this.addRaskrizje(z1);
        Semafor z2=new Semafor("2.) Vozač vozila koji skreće ulijevo dužan je propustiti vozilo koje, dolazeći iz suprotnog smjera , zadržava smjer svog kretanja ili skreće udesno, osim ako postavljenim prometnim znakom nije drukčije određeno.",-1,false);
        this.addRaskrizje(z2);
        Semafor z3=new Semafor("3.) Iznimno, od odredaba stavka 1. i 2. ovog članka na raskrižju ili pri susretu s vozilom koje se kreće po tračnicama vozač je dužan propustiti takvo vozilo bez obzira s koje strane dolazi, osim ako postavljenim prometnim znakom nije drukčije određeno.",-1,false);
        this.addRaskrizje(z3);
        Semafor z4=new Semafor("4.) Vozač koji ulazi vozilom na cestu koja je prometnim znakom označena kao cesta s prednošću prolaska dužan je propustiti sva vozila koja se kreću tom cestom.",-1,false);
        this.addRaskrizje(z4);
        Semafor z5=new Semafor("5.) Vozač je dužan propustiti sva vozila koja se kreću cestom na koju ulazi i kada ta cesta nije prometnim znakom označena kao cesta s prednošću prolaska, ako vozilom ulazi sa ceste bez suvremenog kolničkog zastora na cestu sa suvremenim kolničkim zastorom.",-1,false);
        this.addRaskrizje(z5);
        Semafor z6=new Semafor("6.) Vozač koji pri skretanju presijeca biciklističku stazu ili traku koja se pruža uzduž kolnika kojim se kreće, dužan je propustiti bicikle koji se kreću po biciklističkoj stazi ili traci u istom ili suprotnom smjeru.",-1,false);
        this.addRaskrizje(z6);
        Semafor z7=new Semafor("Autobus ima prednost jer vozi ravno",R.drawable.autobus_ima_prednost_jer_vozi_ravno,true);
        this.addRaskrizje(z7);
        Semafor z8=new Semafor("Etičnost prilikom skretanja svako vozilo skreće u prvu traku",R.drawable.eticnost_prilikom_skretanja_svako_vozilo_skrece_u_prvu_traku,true);
        this.addRaskrizje(z8);
        Semafor z9=new Semafor("Lijevi skretac nema prednost",R.drawable.lijevi_skretac_nema_prednost,true);
        this.addRaskrizje(z9);
        Semafor z10=new Semafor("Neispravan polozaj vozila u traci prilikom voznje ravno",R.drawable.neispravan_polozaj_vozila_u_traci_prilikom_voznje_ravno,true);
        this.addRaskrizje(z10);
        Semafor z11=new Semafor("Nema prednosti jer vozila nemaju dodirnih tocaka",R.drawable.nema_prednosti_jer_vozila_nemaju_dodirnih_tocaka,true);
        this.addRaskrizje(z11);
        Semafor z12=new Semafor("Pravilo desnoga",R.drawable.pravilo_desnoga,true);
        this.addRaskrizje(z12);
        Semafor z13=new Semafor("Pravilo desnoga",R.drawable.pravilo_desnoga_2,true);
        this.addRaskrizje(z13);
        Semafor z14=new Semafor("Pravilo desnoga",R.drawable.pravilo_desnoga_3,true);
        this.addRaskrizje(z14);
        Semafor z15=new Semafor("Prednost ima biciklista koji vozi biciklističkom stazom",R.drawable.prednost_ima_biciklista_koji_vozi_biciklistickom_stazom,true);
        this.addRaskrizje(z15);
        Semafor z16=new Semafor("Vozila koja nemaju znak trokut idu prva",R.drawable.vozila_koja_nemaju_znak_trokut_idu_prva,true);
        this.addRaskrizje(z16);
        Semafor z17=new Semafor("Vozila koja su na cesti s prednošću prolaska idu prva i nemaju dodirnih točaka",R.drawable.vozila_koja_su_na_cesti_sa_prednoscu_prolaska_idu_prva_nemaju_dodirnih_tocaka,true);
        this.addRaskrizje(z17);
        Semafor z18=new Semafor("Vozilo koje je na cesti sa prednošću prolaska ide prvo",R.drawable.vozilo_koje_je_na_cesti_sa_prednoscu_prolaska_ide_prvo,true);
        this.addRaskrizje(z18);
        Semafor z19=new Semafor("Vozilo koje nema nikoga s desne strane ide prvo",R.drawable.vozilo_koje_nema_nikoga_sa_desne_strane_ide_prvo,true);
        this.addRaskrizje(z19);
        Semafor z20=new Semafor("Vozilo koje nema nikoga s desne strane ide prvo ali mora propustiti vozilo koje se kreće ravno",R.drawable.vozilo_koje_nema_nikoga_sa_desne_strane_ide_prvo_ali_mora_propustiti_vozilo_koje_se_krece_ravno,true);
        this.addRaskrizje(z20);

        Semafor z22=new Semafor("Vozilo ne smije uću u raskrižje bez obzira na zeleno svijetlo ako bi time moglo ometati druga vozila",R.drawable.vozilo_ne_smije_uci_u_raskrizje,true);
        this.addRaskrizje(z22);




    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PREDNOST_NA_RASKRIZJU);
// Create tables again
        onCreate(db);
    }
    // Adding new question
    public void addRaskrizje(Semafor semafor) {
//SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, semafor.getName());
        values.put(KEY_ID_IMG, semafor.getId_img());
        values.put(KEY_HAS_IMAGE, semafor.isHasImage());

// Inserting Row
        dbase.insert(TABLE_PREDNOST_NA_RASKRIZJU, null, values);
    }
    public List<Semafor> getAllRaskrizja() {
        List<Semafor> semaforList = new ArrayList<Semafor>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_PREDNOST_NA_RASKRIZJU;
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
