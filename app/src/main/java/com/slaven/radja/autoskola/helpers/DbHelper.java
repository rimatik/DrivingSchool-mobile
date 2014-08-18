package com.slaven.radja.autoskola.helpers;

/**
 * Created by Computer on 06/08/2014.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.models.Question;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 7;
    // Database Name
    private static final String DATABASE_NAME = "autoskola.db";
    // tasks table name
    private static final String TABLE_QUEST = "quest";
    // tasks Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer"; //correct option
    private static final String KEY_OPTA= "opta"; //option a
    private static final String KEY_OPTB= "optb"; //option b
    private static final String KEY_OPTC= "optc"; //option c
    private static final String KEY_IMAGE_ID = "image_id";
    private static final String KEY_HAS_IMAGE = "has_image";
    private static final String KEY_CORRECT_ANSWER = "correct_answer";

    private SQLiteDatabase dbase;
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
                +KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT, " + KEY_IMAGE_ID + " INTEGER, " + KEY_HAS_IMAGE + " INTEGER, "
                + KEY_CORRECT_ANSWER + " INTEGER)";
        Log.e("SLAVEN", sql);
        db.execSQL(sql);
        addQuestions();
//db.close();
    }
    private void addQuestions()
    {

        Question q1=new Question("1. Zbog čega je vožnja pod utjecajem alkohola opasna?",
                "Alkohol bitno povećava vozačeve sposobnosti", "Alkohol skraćuje vrijeme reagiranja vozaču ", "Alkohol produžuje vrijeme reagiranja vozača", "Alkohol produžuje vrijeme reagiranja vozača",-1, false, 2);
        this.addQuestion(q1);
        Question q2=new Question("2. Na što morate paziti kod ovog prometnog znaka?"
                , "Na obilaznu cestu", "Moram povećati brzinu", "Nailazi desni zavoj", "Nailazi desni zavoj",R.drawable.slikadva , true, 2);
        this.addQuestion(q2);
        Question q3=new Question("3. Što je vozač obavezan napraviti ako namjerava voziti unatrag?"
                ,"Uključiti sve pokazivače smjera", "Uključiti desni pokazivač smjera","Uključiti lijevi pokazivač smjera","Uključiti sve pokazivače smjera", -1, false, 0);
        this.addQuestion(q3);
        Question q4=new Question("4. Što pokazuje ovaj prometni znak?"
                , "Obavezan smjer vožnje", "Raskrižje cesta iste važnosti", "Položaj ceste s pravom prednosti prolaska","Položaj ceste s pravom prednosti prolaska", R.drawable.slikadvadesetiosam, true, 2);
        this.addQuestion(q4);
        Question q5=new Question("5. Približavate se ovom raskrižju. Kako ćete postupiti?"
                ,"Voziti prije autobusa","Propustiti autobus","Propustiti crveni automobil","Propustiti autobus", R.drawable.slikadeset, true, 1);
        this.addQuestion(q5);
        Question q6=new Question("6. Koja svijetla, u pravilu, vozač motornog vozila upotrebljava za osvijetljavanje ceste?"
                ,"Kratka ili dnevna svijetla","Pozicijska svijetla","Duga svijetla","Duga svijetla", -1, false, 2);
        this.addQuestion(q6);
        Question q7=new Question("7. Vozač crnog automobila skreće udesno kao na slici, što mora učiniti?"
                ,"Propustiti biciklistu","Skrenuti prije bicikliste","Potrubiti","Propustiti biciklistu", R.drawable.slikatridesetsest, true, 0);
        this.addQuestion(q7);
        Question q8=new Question("8. Na što ukazuje ovaj prometni znak?"
                ,"Na početak ceste namijenjene isključivo za promet motornih vozila","Na to da je dozovljena vožnja samo osobnim automobilima","Na ograničenje brzine na 90 km/h","Na početak ceste namijenjene isključivo za promet motornih vozila", R.drawable.slikasesnaest, true, 0);
        this.addQuestion(q8);
        Question q9=new Question("9. Što je cesta?"
                ,"Cesta je svaka javna cesta na kojoj se odvija promet isključivo motornih vozila","Cesta je svaka javna cesta i nerazvrstana cesta na kojoj se obavlja promet","Cesta je svaka javna površina kojom se odvija promet","Cesta je svaka javna cesta i nerazvrstana cesta na kojoj se obavlja promet", -1, false, 1);
        this.addQuestion(q9);
        Question q10=new Question("10. Kako ćete postupiti u ovoj situaciji?"
                ,"Propustiti vozilo iz suprotnog smjera","Proći prije vozila iz suprotnog smjera","Povećati brzinu","Propustiti vozilo iz suprotnog smjera", R.drawable.slikaprva, true, 0);
        this.addQuestion(q10);
        Question q11=new Question("11. Je li na ovom dijelu ceste, s obzirom na oznake na kolniku pretjecanje dopušteno?"
                ,"Da dopušteno je jer ne postoji prometni znak zabrane","Dopušteno je nakon što prođu vozila iz suprotnog smjera","Ne, nije dopušteno","Ne, nije dopušteno", R.drawable.slikasest, true, 2);
        this.addQuestion(q11);
        Question q12=new Question("12. Na cesti izvan naselja zaustavljeno je vozilo zbog kvara. " +
                "Na kojoj udaljenosti morate postaviti sigurnosni trokut iza zaustavljenog vozila na kolniku."
                ,"Minimalno 80 metara iza vozila","Minimalno 100 metara iza vozila","Minimalno 50 metara iza vozila","Minimalno 100 metara iza vozila", -1, false, 1);
        this.addQuestion(q12);
        Question q13=new Question("13. Približavate se ovom raskrižju. Kako ćete postupiti?"
                ,"Propustiti autobus i motocikl","Propustiti autobus i voziti prije motocikla","Voziti prije autobusa i propustiti motocikl","Voziti prije autobusa i propustiti motocikl", R.drawable.slikatridesetidva, true, 2);
        this.addQuestion(q13);
        Question q14=new Question("14. Kako djeca najčešće reagiraju u prometu?"
                ,"Spontano i nepredvidivo","U skladu s propisima","Razborito i staloženo","Spontano i nepredvidivo", -1, false, 0);
        this.addQuestion(q14);
        Question q15=new Question("15. Na instrument ploči upaljena je plava žaruljica kao na slici. Što to znači?"
                ,"Da vozim sa svijetlima za maglu","Da vozim s dugim svijetlima","Da vozim s kratkim svijetlima","Da vozim s dugim svijetlima", R.drawable.slikatrideset, true, 1);
        this.addQuestion(q15);
        Question q16=new Question("16. Kako ćete postupiti u ovoj situaciji?"
                ,"Voziti kroz raskrižje","Propustiti crveni automobil","Propustiti automobil iz suprotnog smjera","Propustiti automobil iz suprotnog smjera", R.drawable.slikadvadeseticetiri, true, 2);
        this.addQuestion(q16);
        Question q17=new Question("17. S čime morate računati u situaciji kao na slici?"
                ,"Na to da me dijete ne vidi","Na to da će me dijete sigurno vidjeti","Da će dijete pravilno reagirati","Na to da me dijete ne vidi", R.drawable.slikatridesetcetiri, true, 0);
        this.addQuestion(q17);
        Question q18=new Question("18. Vašim osobnim automobilom vučete priključno vozilo bez kočnica autocestom. Kojom brzinom najviše smijete voziti?"
                ,"30 km/h","60 km/h","90 km/h","90 km/h", -1, false, 2);
        this.addQuestion(q18);
        Question q19=new Question("19. Kako ćete postupiti pri vožnji za jakog vjetra?"
                ,"Ubrzati i što prije proći dionicu ceste s jakim vjetrom","Smanjiti brzinu vožnje","Pri mimoilaženju držati što manji bočni razmak","Smanjiti brzinu vožnje", -1, false, 1);
        this.addQuestion(q19);
        Question q20=new Question("20. Kako ćete postupiti nakon ovog prometnog znaka?"
                ,"Bez smanjenja brzine nastaviti vožnju jer znak zabranjuje djeci prelazak preko ceste","Očekivati nailazak djece koja namjeravaju prijeći kolnik","Povećati brzinu","Očekivati nailazak djece koja namjeravaju prijeći kolnik", R.drawable.slikatridesetosam, true, 1);
        this.addQuestion(q20);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
// Create tables again
        onCreate(db);
    }
    // Adding new question
    public void addQuestion(Question quest) {
//SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_IMAGE_ID, quest.getImg_ID());
        values.put(KEY_HAS_IMAGE, quest.hasImage() ? 1 : 0);
        values.put(KEY_CORRECT_ANSWER, quest.getCorrectAnswer());

// Inserting Row
        dbase.insert(TABLE_QUEST, null, values);
    }
    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_QUEST;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quest.setANSWER(cursor.getString(2));
                quest.setImg_ID(cursor.getInt(6));
                quest.setHasImage(cursor.getInt(7) == 1);
                quest.setCorrectAnswer(cursor.getInt(8));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
// return quest list
        return quesList;
    }
    public int rowcount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }
}