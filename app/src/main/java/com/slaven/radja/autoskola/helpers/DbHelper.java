package com.slaven.radja.autoskola.helpers;

/**
 * Created by Computer on 06/08/2014.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import com.slaven.radja.autoskola.DatabaseConstants;
import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.aplication.AutoskolaApp;
import com.slaven.radja.autoskola.models.Question;
import com.slaven.radja.autoskola.models.Semafor;
import com.slaven.radja.autoskola.models.Znak;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;



public class DbHelper extends SQLiteOpenHelper {


    public static SQLiteDatabase sqLiteDatabase;


    public static DbHelper instance;

    int position;
    int idSign;


    public static DbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DbHelper(context);
        }
        return instance;
    }

    public DbHelper(Context context) {
        super(context, DatabaseConstants.DATABASE_NAME, null, DatabaseConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        sqLiteDatabase = db;
        createAndPopulateQuestionsTable(db);
        createAndPopulateCrossroadAdvantageSignsTable(db);
        createAndPopulateTrafficOfficerSignsTable(db);
        createAndPopulateTrafficSemaforSigns(db);
        createAndPopulateDangerSigns(db);
    }

    private void createAndPopulateQuestionsTable(SQLiteDatabase dbase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + DatabaseConstants.TABLE_QUEST + " ( "
                + DatabaseConstants.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DatabaseConstants.KEY_QUES
                + " TEXT, " + DatabaseConstants.KEY_ANSWER_ONE + " TEXT, " + DatabaseConstants.KEY_ANSWER_TWO + " TEXT, " + DatabaseConstants.KEY_OPTA + " TEXT, "
                + DatabaseConstants.KEY_OPTB + " TEXT, " + DatabaseConstants.KEY_OPTC + " TEXT, " + DatabaseConstants.KEY_IMAGE_ID + " INTEGER, " + DatabaseConstants.KEY_HAS_IMAGE + " INTEGER, "
                + DatabaseConstants.KEY_CORRECT_ANSWER_ONE + " INTEGER, " + DatabaseConstants.KEY_CORRECT_ANSWER_TWO + " INTEGER, " + DatabaseConstants.KEY_ONE_OR_TWO_ANSWERS + " INTEGER)";
        dbase.execSQL(sql);
        addQuestions();
    }


    private void createAndPopulateCrossroadAdvantageSignsTable(SQLiteDatabase dbase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + DatabaseConstants.TABLE_PREDNOST_NA_RASKRIZJU + " ( "
                + DatabaseConstants.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DatabaseConstants.KEY_NAME
                + " TEXT, " + DatabaseConstants.KEY_ID_IMG + " INTEGER, " + DatabaseConstants.KEY_HAS_IMAGE + " INTEGER)";
        dbase.execSQL(sql);
        addRaskrizja();
    }

    private void createAndPopulateTrafficOfficerSignsTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + DatabaseConstants.TABLE_PROMETNI_POLICAJAC + " ( "
                + DatabaseConstants.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DatabaseConstants.KEY_NAME
                + " TEXT, " + DatabaseConstants.KEY_ID_IMG + " INTEGER, " + DatabaseConstants.KEY_HAS_IMAGE + " INTEGER)";
        db.execSQL(sql);
        addTrafficLightsOfficer(DatabaseConstants.TABLE_PROMETNI_POLICAJAC);
    }

    private void createAndPopulateTrafficSemaforSigns(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + DatabaseConstants.TABLE_PROMETNI_SEMAFOR + " ( "
                + DatabaseConstants.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DatabaseConstants.KEY_NAME
                + " TEXT, " + DatabaseConstants.KEY_ID_IMG + " INTEGER, " + DatabaseConstants.KEY_HAS_IMAGE + " INTEGER)";
        db.execSQL(sql);
        addTrafficLightsSemafor(DatabaseConstants.TABLE_PROMETNI_SEMAFOR);
    }

    private void createAndPopulateDangerSigns(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + DatabaseConstants.TABLE_ZNAK_OPASNOSTI + " ( "
                + DatabaseConstants.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DatabaseConstants.KEY_ID_IMG + " BLOB, " + DatabaseConstants.KEY_POSITION + " INTEGER)";
        db.execSQL(sql);
        addDangerSign(DatabaseConstants.TABLE_ZNAK_OPASNOSTI);
    }
    private void createAndPopulateDangerSignFirst(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + DatabaseConstants.TABLE_ZNAK_OPASNOSTI + " ( "
                + DatabaseConstants.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DatabaseConstants.KEY_ID_IMG + " BLOB, " + DatabaseConstants.KEY_POSITION + " INTEGER)";
        db.execSQL(sql);
        addDangerSignFirst(DatabaseConstants.TABLE_ZNAK_OPASNOSTI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseConstants.TABLE_QUEST);
        onCreate(db);
    }

        public void addDangerSign(String tableName) {

        idSign = AutoskolaApp.getPosition();
        position = idSign * 100;
        Bitmap signBitmap = BitmapFactory.decodeResource(AutoskolaApp.getAppContext().getResources(), R.drawable.bitmap_znakovi_opasnosti);
        signBitmap = Bitmap.createBitmap(signBitmap, position, 0, 99, 106);
        ByteArrayOutputStream bosSign = new ByteArrayOutputStream();
        signBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bosSign);
        byte[] byteSign = bosSign.toByteArray();
        Znak z = new Znak(byteSign, idSign);
        this.addSign(z, tableName);

    }

    public void addDangerSignFirst(String tableName) {


        position = 0;
        Bitmap signBitmap = BitmapFactory.decodeResource(AutoskolaApp.getAppContext().getResources(), R.drawable.bitmap_znakovi_opasnosti);
        signBitmap = Bitmap.createBitmap(signBitmap, position, 0, 99, 106);
        ByteArrayOutputStream bosSign = new ByteArrayOutputStream();
        signBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bosSign);
        byte[] byteSign = bosSign.toByteArray();
        Znak z = new Znak(byteSign, position);
        this.addSign(z, tableName);

    }


    private void addTrafficLightsOfficer(String tableName) {
        Semafor z1 = new Semafor("Obavezno zaustavljanje", R.drawable.obvezno_zaustavljanje, true);
        this.addTrafficLight(z1, tableName);
        Semafor z2 = new Semafor("Obavezno zaustavljanje za sva vozila ispred raskrižja", R.drawable.obvezno_zaustavljanje_za_sva_vozila_ispred_raskrizja, true);
        this.addTrafficLight(z2, tableName);
        Semafor z3 = new Semafor("Obavezno zaustavljanje za sva vozila prema kojima je okrenut dlan", R.drawable.obvezno_zaustavljanje_za_sva_vozila_prema_kojima_je_okrenut_dlan, true);
        this.addTrafficLight(z3, tableName);
        Semafor z4 = new Semafor("Priđite prometnom policajcu", R.drawable.pridite_prometnom_policajcu, true);
        this.addTrafficLight(z4, tableName);
        Semafor z5 = new Semafor("Slobodan bocni prolaz", R.drawable.slobodan_bocni_prolaz, true);
        this.addTrafficLight(z5, tableName);
        Semafor z6 = new Semafor("Zabrana prolaska smjerom koji sjece smjer ispruzene ruke", R.drawable.zabrana_prolaska_smjerom_koji_sijece_smjer_ispruzene_ruke, true);
        this.addTrafficLight(z6, tableName);
        Semafor z7 = new Semafor("Obaveza povećanja brzine kretanja", R.drawable.obveza_povecanja_brzine_kretanja, true);
        this.addTrafficLight(z7, tableName);
        Semafor z8 = new Semafor("Obaveza smanjivanja brzine kretanja", R.drawable.obveza_smanjivanja_brzine_kretanja, true);
        this.addTrafficLight(z8, tableName);
        Semafor z9 = new Semafor("Prometni policajac na raskrižju", R.drawable.prometni_policajac_na_raskrizju_slika_1, true);
        this.addTrafficLight(z9, tableName);
        Semafor z10 = new Semafor("Prometni policajac na raskrižju", R.drawable.prometni_policajac_na_raskrizju_slika_2, true);
        this.addTrafficLight(z10, tableName);
        Semafor z11 = new Semafor("Prometni policajac na raskrižju", R.drawable.prometni_policajac_na_raskrizju_slika_3, true);
        this.addTrafficLight(z11, tableName);
    }

    private void addQuestions() {

        Question q1 = new Question("1. Zbog čega je vožnja pod utjecajem alkohola opasna?",
                "Vozač teže procjenjuje udaljenost", "Alkohol skraćuje vrijeme reagiranja vozaču ",
                "Alkohol produžuje vrijeme reagiranja vozača", "Vozač teže procjenjuje udaljenost",
                "Alkohol produžuje vrijeme reagiranja vozača", -1, false, 0, 2, 2);
        this.addQuestion(q1);
        Question q2 = new Question("2. Na što morate paziti kod ovog prometnog znaka?"
                , "Na obilaznu cestu", "Brzina mora biti prilagođena uvjetima vožnje",
                "Nailazi desni zavoj", "Brzina mora biti prilagođena uvjetima vožnje", "Nailazi desni zavoj",
                R.drawable.slikadva, true, 1, 2, 2);
        this.addQuestion(q2);
        Question q3 = new Question("3. Što je vozač obavezan napraviti ako namjerava voziti unatrag?"
                , "Uključiti sve pokazivače smjera", "Uključiti desni pokazivač smjera", "Voziti samo na kratkom dijelu ceste, ako time ne ugrožava ili ne ometa druge sudionike u prometu",
                "Uključiti sve pokazivače smjera",
                "Voziti samo na kratkom dijelu ceste, ako time ne ugrožava ili ne ometa druge sudionike u prometu",
                -1, false, 0, 2, 2);
        this.addQuestion(q3);
        Question q4 = new Question("4. Što pokazuje ovaj prometni znak?"
                , "Obavezan smjer vožnje", "Raskrižje cesta iste važnosti", "Položaj ceste s pravom prednosti prolaska",
                "Položaj ceste s pravom prednosti prolaska", "", R.drawable.slikadvadesetiosam, true, 2, -1, 1);
        this.addQuestion(q4);
        Question q5 = new Question("5. Približavate se ovom raskrižju. Kako ćete postupiti?"
                , "Voziti prije autobusa", "Propustiti autobus", "Propustiti crveni automobil",
                "Propustiti autobus", "", R.drawable.slikadeset, true, 1, -1, 1);
        this.addQuestion(q5);
        Question q6 = new Question("6. Koja svijetla, u pravilu, vozač motornog vozila upotrebljava" +
                " za osvijetljavanje ceste?"
                , "Kratka ili dnevna svijetla", "Pozicijska svijetla",
                "Duga svijetla", "Duga svijetla", "", -1, false, 2, -1, 1);
        this.addQuestion(q6);
        Question q7 = new Question("7. Vozač crnog automobila skreće udesno kao na slici, što mora učiniti?"
                , "Propustiti biciklistu", "Skrenuti prije bicikliste",
                "Potrubiti", "Propustiti biciklistu", "", R.drawable.slikatridesetsest, true, 0, -1, 1);
        this.addQuestion(q7);
        Question q8 = new Question("8. Na što ukazuje ovaj prometni znak?"
                , "Na početak ceste namijenjene isključivo za promet motornih vozila",
                "Na to da je dozovljena vožnja samo osobnim automobilima",
                "Na ograničenje brzine kretanja 110 km/h", "Na početak ceste namijenjene isključivo za promet motornih vozila",
                "Na ograničenje brzine kretanja 110 km/h", R.drawable.slikasesnaest, true, 0, 2, 2);
        this.addQuestion(q8);
        Question q9 = new Question("9. Što je cesta?"
                , "Cesta je svaka javna cesta na kojoj se odvija promet isključivo motornih vozila",
                "Cesta je svaka javna cesta i nerazvrstana cesta na kojoj se obavlja promet",
                "Cesta je svaka javna površina kojom se odvija promet",
                "Cesta je svaka javna cesta i nerazvrstana cesta na kojoj se obavlja promet", "", -1, false, 1, -1, 1);
        this.addQuestion(q9);
        Question q10 = new Question("10. Kako ćete postupiti u ovoj situaciji?"
                , "Propustiti vozilo iz suprotnog smjera", "Proći prije vozila iz suprotnog smjera",
                "Smanjiti brzinu i prema potrebi zaustaviti vozilo", "Propustiti vozilo iz suprotnog smjera",
                "Smanjiti brzinu i prema potrebi zaustaviti vozilo", R.drawable.slikaprva, true, 0, 2, 2);
        this.addQuestion(q10);
        Question q11 = new Question("11. Je li na ovom dijelu ceste, s obzirom na oznake na kolniku pretjecanje dopušteno?"
                , "Da dopušteno je jer ne postoji prometni znak zabrane",
                "Dopušteno je nakon što prođu vozila iz suprotnog smjera",
                "Ne, nije dopušteno", "Ne, nije dopušteno", "", R.drawable.slikasest, true, 2, -1, 1);
        this.addQuestion(q11);
        Question q12 = new Question("12. Na cesti izvan naselja zaustavljeno je vozilo zbog kvara. " +
                "Na kojoj udaljenosti morate postaviti sigurnosni trokut iza zaustavljenog vozila na kolniku?"
                , "Minimalno 80 metara iza vozila", "Minimalno 100 metara iza vozila",
                "Minimalno 50 metara iza vozila", "Minimalno 100 metara iza vozila", "", -1, false, 1, -1, 1);
        this.addQuestion(q12);
        Question q13 = new Question("13. Približavate se ovom raskrižju. Kako ćete postupiti?"
                , "Voziti prije autobusa", "Propustiti motocikl", "Propustiti autobus",
                "Voziti prije autobusa", "Propustiti motocikl", R.drawable.slikatridesetidva, true, 0, 1, 2);
        this.addQuestion(q13);
        Question q14 = new Question("14. Kako djeca najčešće reagiraju u prometu?"
                , "Spontano i nepredvidivo", "U skladu s propisima", "Razborito i staloženo",
                "Spontano i nepredvidivo", "", -1, false, 0, -1, 1);
        this.addQuestion(q14);
        Question q15 = new Question("15. Na instrument ploči upaljena je plava žaruljica kao na slici. Što to znači?"
                , "Da vozim sa svijetlima za maglu", "Da vozim s dugim svijetlima",
                "Da vozim s kratkim svijetlima", "Da vozim s dugim svijetlima", "", R.drawable.slikatrideset, true, 1, -1, 1);
        this.addQuestion(q15);
        Question q16 = new Question("16. Kako ćete postupiti u ovoj situaciji?"
                , "Voziti kroz raskrižje", "Voziti prije crvenog automobila",
                "Propustiti automobil iz suprotnog smjera", "Voziti prije crvenog automobila",
                "Propustiti automobil iz suprotnog smjera", R.drawable.slikadvadeseticetiri, true, 1, 2, 2);
        this.addQuestion(q16);
        Question q17 = new Question("17. S čime morate računati u situaciji kao na slici?"
                , "Na to da me dijete ne vidi", "Na to da će me dijete sigurno vidjeti",
                "Da će dijete pravilno reagirati", "Na to da me dijete ne vidi", "",
                R.drawable.slikatridesetcetiri, true, 0, -1, 1);
        this.addQuestion(q17);
        Question q18 = new Question("18. Vašim osobnim automobilom vučete priključno vozilo bez kočnica autocestom." +
                " Kojom brzinom najviše smijete voziti?"
                , "30 km/h", "60 km/h", "90 km/h", "90 km/h", "", -1, false, 2, -1, 1);
        this.addQuestion(q18);
        Question q19 = new Question("19. Kako ćete postupiti pri vožnji za jakog vjetra?"
                , "Ubrzati i što prije proći dionicu ceste s jakim vjetrom", "Smanjiti brzinu vožnje",
                "Pri mimoilaženju držati što veći bočni razmak", "Smanjiti brzinu vožnje",
                "Pri mimoilaženju držati što veći bočni razmak", -1, false, 1, 2, 2);
        this.addQuestion(q19);
        Question q20 = new Question("20. Kako ćete postupiti nakon ovog prometnog znaka?"
                , "Bez smanjenja brzine nastaviti vožnju jer znak zabranjuje djeci prelazak preko ceste",
                "Očekivati nailazak djece koja namjeravaju prijeći kolnik",
                "Smanjiti brzinu i biti pripravan za kočenje i zaustavljanje",
                "Očekivati nailazak djece koja namjeravaju prijeći kolnik",
                "Smanjiti brzinu i biti pripravan za kočenje i zaustavljanje", R.drawable.slikatridesetosam, true, 1, 2, 2);
        this.addQuestion(q20);

    }

    public void addSign(Znak sign, String tableName) {

        ContentValues values = new ContentValues();
        values.put(DatabaseConstants.KEY_ID_IMG, sign.getBitmap());
        values.put(DatabaseConstants.KEY_POSITION, sign.getPosition());

        sqLiteDatabase.insert(tableName, null, values);
    }

    public void addQuestion(Question quest) {
        ContentValues values = new ContentValues();
        values.put(DatabaseConstants.KEY_QUES, quest.getQUESTION());
        values.put(DatabaseConstants.KEY_OPTA, quest.getOPTA());
        values.put(DatabaseConstants.KEY_OPTB, quest.getOPTB());
        values.put(DatabaseConstants.KEY_OPTC, quest.getOPTC());
        values.put(DatabaseConstants.KEY_ANSWER_ONE, quest.getANSWER_ONE());
        values.put(DatabaseConstants.KEY_ANSWER_TWO, quest.getANSWER_TWO());
        values.put(DatabaseConstants.KEY_IMAGE_ID, quest.getImg_ID());
        values.put(DatabaseConstants.KEY_HAS_IMAGE, quest.hasImage() ? 1 : 0);
        values.put(DatabaseConstants.KEY_CORRECT_ANSWER_ONE, quest.getCorrectAnswerOne());
        values.put(DatabaseConstants.KEY_CORRECT_ANSWER_TWO, quest.getCorrectAnswerTwo());
        values.put(DatabaseConstants.KEY_ONE_OR_TWO_ANSWERS, quest.getOneOrTwoAnswers());
        sqLiteDatabase.insert(DatabaseConstants.TABLE_QUEST, null, values);
    }

    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
        String selectQuery = "SELECT * FROM " + DatabaseConstants.TABLE_QUEST;
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER_ONE(cursor.getString(2));
                quest.setANSWER_TWO(cursor.getString(3));
                quest.setOPTA(cursor.getString(4));
                quest.setOPTB(cursor.getString(5));
                quest.setOPTC(cursor.getString(6));
                quest.setImg_ID(cursor.getInt(7));
                quest.setHasImage(cursor.getInt(8) == 1);
                quest.setCorrectAnswerOne(cursor.getInt(9));
                quest.setCorrectAnswerTwo(cursor.getInt(10));
                quest.setOneOrTwoAnswers(cursor.getInt(11));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        return quesList;
    }

    private void addRaskrizja() {
        Semafor z1 = new Semafor("1.) Na raskrižju cesta iste važnosti ili u susretu s drugim vozilom vozač je dužan propustit vozilo koje nailazi s njegove desne strane.", -1, false);
        this.addRaskrizje(z1);
        Semafor z2 = new Semafor("2.) Vozač vozila koji skreće ulijevo dužan je propustiti vozilo koje, dolazeći iz suprotnog smjera , zadržava smjer svog kretanja ili skreće udesno, osim ako postavljenim prometnim znakom nije drukčije određeno.", -1, false);
        this.addRaskrizje(z2);
        Semafor z3 = new Semafor("3.) Iznimno, od odredaba stavka 1. i 2. ovog članka na raskrižju ili pri susretu s vozilom koje se kreće po tračnicama vozač je dužan propustiti takvo vozilo bez obzira s koje strane dolazi, osim ako postavljenim prometnim znakom nije drukčije određeno.", -1, false);
        this.addRaskrizje(z3);
        Semafor z4 = new Semafor("4.) Vozač koji ulazi vozilom na cestu koja je prometnim znakom označena kao cesta s prednošću prolaska dužan je propustiti sva vozila koja se kreću tom cestom.", -1, false);
        this.addRaskrizje(z4);
        Semafor z5 = new Semafor("5.) Vozač je dužan propustiti sva vozila koja se kreću cestom na koju ulazi i kada ta cesta nije prometnim znakom označena kao cesta s prednošću prolaska, ako vozilom ulazi sa ceste bez suvremenog kolničkog zastora na cestu sa suvremenim kolničkim zastorom.", -1, false);
        this.addRaskrizje(z5);
        Semafor z6 = new Semafor("6.) Vozač koji pri skretanju presijeca biciklističku stazu ili traku koja se pruža uzduž kolnika kojim se kreće, dužan je propustiti bicikle koji se kreću po biciklističkoj stazi ili traci u istom ili suprotnom smjeru.", -1, false);
        this.addRaskrizje(z6);
        Semafor z7 = new Semafor("Autobus ima prednost jer vozi ravno", R.drawable.autobus_ima_prednost_jer_vozi_ravno, true);
        this.addRaskrizje(z7);
        Semafor z8 = new Semafor("Etičnost prilikom skretanja svako vozilo skreće u prvu traku", R.drawable.eticnost_prilikom_skretanja_svako_vozilo_skrece_u_prvu_traku, true);
        this.addRaskrizje(z8);
        Semafor z9 = new Semafor("Lijevi skretac nema prednost", R.drawable.lijevi_skretac_nema_prednost, true);
        this.addRaskrizje(z9);
        Semafor z10 = new Semafor("Neispravan polozaj vozila u traci prilikom voznje ravno", R.drawable.neispravan_polozaj_vozila_u_traci_prilikom_voznje_ravno, true);
        this.addRaskrizje(z10);
        Semafor z11 = new Semafor("Nema prednosti jer vozila nemaju dodirnih tocaka", R.drawable.nema_prednosti_jer_vozila_nemaju_dodirnih_tocaka, true);
        this.addRaskrizje(z11);
        Semafor z12 = new Semafor("Pravilo desnoga", R.drawable.pravilo_desnoga, true);
        this.addRaskrizje(z12);
        Semafor z13 = new Semafor("Pravilo desnoga", R.drawable.pravilo_desnoga_2, true);
        this.addRaskrizje(z13);
        Semafor z14 = new Semafor("Pravilo desnoga", R.drawable.pravilo_desnoga_3, true);
        this.addRaskrizje(z14);
        Semafor z15 = new Semafor("Prednost ima biciklista koji vozi biciklističkom stazom", R.drawable.prednost_ima_biciklista_koji_vozi_biciklistickom_stazom, true);
        this.addRaskrizje(z15);
        Semafor z16 = new Semafor("Vozila koja nemaju znak trokut idu prva", R.drawable.vozila_koja_nemaju_znak_trokut_idu_prva, true);
        this.addRaskrizje(z16);
        Semafor z17 = new Semafor("Vozila koja su na cesti s prednošću prolaska idu prva i nemaju dodirnih točaka", R.drawable.vozila_koja_su_na_cesti_sa_prednoscu_prolaska_idu_prva_nemaju_dodirnih_tocaka, true);
        this.addRaskrizje(z17);
        Semafor z18 = new Semafor("Vozilo koje je na cesti sa prednošću prolaska ide prvo", R.drawable.vozilo_koje_je_na_cesti_sa_prednoscu_prolaska_ide_prvo, true);
        this.addRaskrizje(z18);
        Semafor z19 = new Semafor("Vozilo koje nema nikoga s desne strane ide prvo", R.drawable.vozilo_koje_nema_nikoga_sa_desne_strane_ide_prvo, true);
        this.addRaskrizje(z19);
        Semafor z20 = new Semafor("Vozilo koje nema nikoga s desne strane ide prvo ali mora propustiti vozilo koje se kreće ravno", R.drawable.vozilo_koje_nema_nikoga_sa_desne_strane_ide_prvo_ali_mora_propustiti_vozilo_koje_se_krece_ravno, true);
        this.addRaskrizje(z20);

        Semafor z22 = new Semafor("Vozilo ne smije uću u raskrižje bez obzira na zeleno svijetlo ako bi time moglo ometati druga vozila", R.drawable.vozilo_ne_smije_uci_u_raskrizje, true);
        this.addRaskrizje(z22);
    }

    public void addRaskrizje(Semafor semafor) {

        ContentValues values = new ContentValues();
        values.put(DatabaseConstants.KEY_NAME, semafor.getName());
        values.put(DatabaseConstants.KEY_ID_IMG, semafor.getId_img());
        values.put(DatabaseConstants.KEY_HAS_IMAGE, semafor.isHasImage());
        sqLiteDatabase.insert(DatabaseConstants.TABLE_PREDNOST_NA_RASKRIZJU, null, values);
    }

    public List<Semafor> getAllRaskrizja() {
        List<Semafor> semaforList = new ArrayList<Semafor>();
        String selectQuery = "SELECT * FROM " + DatabaseConstants.TABLE_PREDNOST_NA_RASKRIZJU;
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
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
        return semaforList;
    }

    public List<Semafor> getAllCops() {
        List<Semafor> semaforList = new ArrayList<Semafor>();
        String selectQuery = "SELECT * FROM " + DatabaseConstants.TABLE_PROMETNI_POLICAJAC;
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
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
        return semaforList;
    }

    private void addTrafficLightsSemafor(String tableName) {
        Semafor z1 = new Semafor("Crveno svjetlo - zabrana prolaska", R.drawable.semafor_crveno, true);
        this.addTrafficLight(z1, tableName);
        Semafor z2 = new Semafor("Zeleno svjetlo - slobodan prolazak", R.drawable.semafor_zeleno, true);
        this.addTrafficLight(z2, tableName);
        Semafor z3 = new Semafor("Žuto svjetlo - upaljeno samostalno, znači da vozilo ne smije prijeći crtu zaustavljanja niti smije ući u raskrižje, ako se u trenutku kad se žuto svjetlo pojavi, nalazi na takvoj udaljenosti od prometnog svjetla da se može na siguran način zaustaviti.", R.drawable.semafor_zuto, true);
        this.addTrafficLight(z3, tableName);
        Semafor z4 = new Semafor("Žuto svjetlo upaljeno istovremeno s crvenim svjetlom označava skoru promjenu svjetla i pojavu zelenog svjetla, ali ne mijenja zabranu prolaska koja je data crvenim svjetlom.", R.drawable.semafor_crveno_zuto, true);
        this.addTrafficLight(z4, tableName);
        Semafor z5 = new Semafor("Žuto treptavo svjetlo obvezuje sve sudionike u prometu da se kreću uz povećani oprez.", R.drawable.zuto_trepetavo_svijetlo_img, true);
        this.addTrafficLight(z5, tableName);
        Semafor z6 = new Semafor("Zeleno treptavo svjetlo služi za upozorenje sudionika u prometu na skori prestanak slobodnog prolaska i na pojavu žutoga, odnosno crvenog svjetla.", R.drawable.zeleno_trepetavo_svijetlo_img, true);
        this.addTrafficLight(z6, tableName);
        Semafor z7 = new Semafor("Ako je uređaju za davanje znakova prometnim svjetlima za upravljanje prometom na raskrižju dodano jedno dopunsko prometno svjetlo ili više dopunskih prometnih svjetala u obliku zelene svjetleće strelice, za vrijeme dok je takvo svjetlo upaljeno, vozač može vozilom proći prometno svjetlo i krenuti u smjeru označenom zelenom svjetlećom strelicom i za vrijeme dok je upaljeno crveno ili žuto svjetlo, pri čemu mora propustiti vozila koja se kreću po cesti na koju ulazi i pješake koji prelaze preko kolnika.\n\n" +
                "Ako zeleno svjetlo na uređaju za davanje znakova prometnim svjetlima ima oblik strelice, vozač se vozilom smije kretati samo u smjeru koji pokazuje ta strelica.\n\n" +
                "Vozač koji je vozilom ušao u raskrižje na kojem se upravlja prometom uređajima za davanje znakova prometnim svjetlima, može napustiti raskrižje ne čekajući da prometnim svjetlom promet bude dopušten (otvoren) u smjeru kojim namjerava produžiti kretanje, uz uvjet da propusti sve sudionike u prometu koji se kreću smjerom u kojem je promet dopušten.\n\n" +
                "Vozač ne smije vozilom ući u raskrižje iako mu je prolazak prometnim svjetlom dopušten, ako je gustoća prometa takva da se on s vozilom očito mora zaustaviti u raskrižju ili na obilježenom pješačkom prijelazu i na taj način pri promjeni prometnog svjetla ometati ili onemogućiti promet vozila koja dolaze s bočnih kolnika ili promet pješaka.\n\n"
                , R.drawable.dopunska_strelica_desno, true);
        this.addTrafficLight(z7, tableName);
    }

    public void addTrafficLight(Semafor semafor, String tableName) {
        ContentValues values = new ContentValues();
        values.put(DatabaseConstants.KEY_NAME, semafor.getName());
        values.put(DatabaseConstants.KEY_ID_IMG, semafor.getId_img());
        values.put(DatabaseConstants.KEY_HAS_IMAGE, semafor.isHasImage());
        sqLiteDatabase.insert(tableName, null, values);
    }

    public List<Semafor> getAllTrafficLightsSemafor() {
        List<Semafor> semaforList = new ArrayList<Semafor>();
        String selectQuery = "SELECT * FROM " + DatabaseConstants.TABLE_PROMETNI_SEMAFOR;
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
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
        return semaforList;
    }


    public Znak getSign(String tableName) {

        idSign = AutoskolaApp.getPosition();
        if(AutoskolaApp.getPosition() != -1){
            String selectQuery = " DROP TABLE IF EXISTS " + DatabaseConstants.TABLE_ZNAK_OPASNOSTI;
            sqLiteDatabase = this.getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        }

        if (AutoskolaApp.getPosition() != 0) {
            instance.getWritableDatabase();
            createAndPopulateDangerSigns(sqLiteDatabase);

        }else{
            instance.getWritableDatabase();
            createAndPopulateDangerSignFirst(sqLiteDatabase);
        }
        Znak sign = new Znak();
        Znak provjera = new Znak();
        String selectQuery = "SELECT * FROM " + tableName;
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {
                provjera.setPosition(cursor.getInt(2));
                if (provjera.getPosition() == idSign)
                    break;
            } while (cursor.moveToNext());
        }

        sign.setId(cursor.getInt(0));
        sign.setBitmap(cursor.getBlob(1));
        sign.setPosition(cursor.getInt(2));

        return sign;
    }



}