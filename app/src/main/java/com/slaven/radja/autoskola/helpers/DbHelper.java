package com.slaven.radja.autoskola.helpers;

/**
 * Created by Computer on 06/08/2014.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.slaven.radja.autoskola.DatabaseConstants;
import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.models.Question;
import com.slaven.radja.autoskola.models.Semafor;
import com.slaven.radja.autoskola.models.Znak;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDatabase;

    public static DbHelper instance;

    public static DbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DbHelper(context);
        }
        return instance;
    }

    private DbHelper(Context context) {
        super(context, DatabaseConstants.DATABASE_NAME, null, DatabaseConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        sqLiteDatabase = db;
        createAndPopulateQuestionsTable(db);
        createAndPopulateDopunskePloceUzZnakoveTable(db);
        createAndPopulateCrossroadAdvantageSignsTable(db);
        createAndPopulateTrafficOfficerSignsTable(db);
        createAndPopulateTrafficSemaforSigns(db);
        createAndPopulateMandatorySignsTable(db);
        createAndPopulateInformationSigns(db);
        createAndPopulateDangerSigns(db);
        createAndPopulateTrafficLeadingSigns(db);
    }

    private void createAndPopulateQuestionsTable(SQLiteDatabase dbase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + DatabaseConstants.TABLE_QUEST + " ( "
                + DatabaseConstants.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DatabaseConstants.KEY_QUES
                + " TEXT, " + DatabaseConstants.KEY_ANSWER + " TEXT, " + DatabaseConstants.KEY_OPTA + " TEXT, "
                + DatabaseConstants.KEY_OPTB + " TEXT, " + DatabaseConstants.KEY_OPTC + " TEXT, " + DatabaseConstants.KEY_IMAGE_ID + " INTEGER, " + DatabaseConstants.KEY_HAS_IMAGE + " INTEGER, "
                + DatabaseConstants.KEY_CORRECT_ANSWER + " INTEGER)";
        dbase.execSQL(sql);
        addQuestions();
    }

    private void createAndPopulateDopunskePloceUzZnakoveTable(SQLiteDatabase dbase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + DatabaseConstants.TABLE_ZNAK_DOPUNSKE_PLOCE + " ( "
                + DatabaseConstants.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DatabaseConstants.KEY_NAME
                + " TEXT, " + DatabaseConstants.KEY_ID_IMG + " INTEGER) ";
        dbase.execSQL(sql);
        addExtraBoardSigns(DatabaseConstants.TABLE_ZNAK_DOPUNSKE_PLOCE);
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

    private void createAndPopulateMandatorySignsTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + DatabaseConstants.TABLE_ZNAK_IZRICITIH_NAREDBI + " ( "
                + DatabaseConstants.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DatabaseConstants.KEY_NAME
                + " TEXT, " + DatabaseConstants.KEY_ID_IMG + " INTEGER) ";
        db.execSQL(sql);
        addMandatorySigns(DatabaseConstants.TABLE_ZNAK_IZRICITIH_NAREDBI);
    }

    private void createAndPopulateInformationSigns(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS " + DatabaseConstants.TABLE_ZNAK_OBAVIJESTI + " ( "
                + DatabaseConstants.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DatabaseConstants.KEY_NAME
                + " TEXT, " + DatabaseConstants.KEY_ID_IMG + " INTEGER) ";
        db.execSQL(sql);
        addInformationSigns(DatabaseConstants.TABLE_ZNAK_OBAVIJESTI);
    }


    private void createAndPopulateDangerSigns(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + DatabaseConstants.TABLE_ZNAK_OPASNOSTI + " ( "
                + DatabaseConstants.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DatabaseConstants.KEY_NAME
                + " TEXT, " + DatabaseConstants.KEY_ID_IMG + " INTEGER) ";
        db.execSQL(sql);
        addDangerSigns(DatabaseConstants.TABLE_ZNAK_OPASNOSTI);
    }

    private void createAndPopulateTrafficLeadingSigns(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + DatabaseConstants.TABLE_ZNAK_VODJENJE_PROMETA + " ( "
                + DatabaseConstants.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DatabaseConstants.KEY_NAME
                + " TEXT, " + DatabaseConstants.KEY_ID_IMG + " INTEGER) ";
        db.execSQL(sql);
        addTrafficLeadingSigns(DatabaseConstants.TABLE_ZNAK_VODJENJE_PROMETA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseConstants.TABLE_QUEST);
        onCreate(db);
    }

    private void addDangerSigns(String tableName) {
        Znak z1 = new Znak("Raskrizje cesta iste važnosti", R.drawable.zo_raskrizje_cesta_iste_vaznosti);
        this.addSign(z1, tableName);
        Znak z2 = new Znak("Dvostruki zavoj ili više uzastopnih zavoja od kojih je prvi udesno", R.drawable.zo_dvostruki_zavoj_ili_vise_uzastopnih_zavoja_od_kojih_je_prvi_udesno);
        this.addSign(z2, tableName);
        Znak z3 = new Znak("Dvostruki zavoj ili više uzastopnih zavoja od kojih je prvi ulijevo", R.drawable.zo_dvostruki_zavoj_ili_vise_uzastopnih_zavoja_od_kojih_je_prvi_ulijevo);
        this.addSign(z3, tableName);
        Znak z4 = new Znak("Pada kamenje", R.drawable.zo_kamenje_pada);
        this.addSign(z4, tableName);
        Znak z5 = new Znak("Kamenje pršti", R.drawable.zo_kamenje_prsti);
        this.addSign(z5, tableName);
        Znak z6 = new Znak("Nailazak na prometna svijetla", R.drawable.zo_nailazak_na_prometna_svijetla);
        this.addSign(z6, tableName);
        Znak z7 = new Znak("Neravni kolnik", R.drawable.zo_neravan_kolnik);
        this.addSign(z7, tableName);
        Znak z8 = new Znak("Opasna nizbrdica", R.drawable.zo_opasna_nizbrdica);
        this.addSign(z8, tableName);
        Znak z9 = new Znak("Opasna uzbrdica", R.drawable.zo_opasna_uzbrdica);
        this.addSign(z9, tableName);
        Znak z10 = new Znak("Raskrižje sa sporednom cestom pod pravim kutom", R.drawable.zo_raskrizje_sa_sporednom_cestom_pod_pravim_kutom);
        this.addSign(z10, tableName);
        Znak z11 = new Znak("Sklizak kolnik", R.drawable.zo_sklizak_kolnik);
        this.addSign(z11, tableName);
        Znak z12 = new Znak("Spajanje sporedne ceste pod pravim kutom s lijeve strane", R.drawable.zo_spajanje_sporedne_ceste_pod_pravim_kutom_s_lijeve_strane);
        this.addSign(z12, tableName);
        Znak z13 = new Znak("Spajanje sporedne ceste pod pravim kutom s desne strane", R.drawable.zo_spajanje_sporedne_ceste_pod_pravim_kutom_s_desne_strane);
        this.addSign(z13, tableName);
        Znak z14 = new Znak("Spajanje sporedne ceste pod oštrim kutom s desne strane", R.drawable.zo_spajanje_sporedne_ceste_pod_ostrim_kutom_s_desne_strane);
        this.addSign(z14, tableName);
        Znak z15 = new Znak("Spajanje sporedne ceste pod oštrim kutom s lijeve strane", R.drawable.zo_spajanje_sporedne_ceste_pod_ostrim_kutom_s_lijeve_strane);
        this.addSign(z15, tableName);
        Znak z16 = new Znak("Suženje ceste", R.drawable.zo_suzenje_ceste);
        this.addSign(z16, tableName);
        Znak z17 = new Znak("Suženje ceste s desne strane", R.drawable.zo_suzenje_ceste_s_desne_strane);
        this.addSign(z17, tableName);
        Znak z18 = new Znak("Suženje ceste s lijeve strane", R.drawable.zo_suzenje_ceste_s_lijeve_strane);
        this.addSign(z18, tableName);
        Znak z19 = new Znak("Zavoj u desno", R.drawable.zo_zavoj_u_desno);
        this.addSign(z19, tableName);
        Znak z20 = new Znak("Zavoj u lijevo", R.drawable.zo_zavoj_u_lijevo);
        this.addSign(z20, tableName);
        Znak z21 = new Znak("Opasnost na cesti", R.drawable.zo_opasnost_na_cesti);
        this.addSign(z21, tableName);
    }

    private void addMandatorySigns(String tableName) {
        Znak z1 = new Znak("Obavezno zaustavljanje", R.drawable.zi_obavezno_zaustavljanje);
        this.addSign(z1, tableName);
        Znak z2 = new Znak("Raskrižje s cestom koja ima prednost prolaza", R.drawable.zi_raskrizje_s_cestom_koja_ima_prednost_prolaza);
        this.addSign(z2, tableName);
        Znak z3 = new Znak("Zabrana prometa u oba smjera", R.drawable.zi_zabrana_prometa_u_oba_smjera);
        this.addSign(z3, tableName);
        Znak z4 = new Znak("Zabrana prometa za autobuse", R.drawable.zi_zabrana_prometa_za_autobuse);
        this.addSign(z4, tableName);
        Znak z5 = new Znak("Zabrana prometa za bicikle", R.drawable.zi_zabrana_prometa_za_bicikle);
        this.addSign(z5, tableName);
        Znak z6 = new Znak("Zabrana prometa za cisterne", R.drawable.zi_zabrana_prometa_za_cisterne);
        this.addSign(z6, tableName);
        Znak z7 = new Znak("Zabrana prometa za mopede", R.drawable.zi_zabrana_prometa_za_mopede);
        this.addSign(z7, tableName);
        Znak z8 = new Znak("Zabrana prometa za mopede i bicikle", R.drawable.zi_zabrana_prometa_za_mopede_i_bicikle);
        this.addSign(z8, tableName);
        Znak z9 = new Znak("Zabrana prometa za motocikle", R.drawable.zi_zabrana_prometa_za_motocikle);
        this.addSign(z9, tableName);
        Znak z10 = new Znak("Zabrana prometa za pješake", R.drawable.zi_zabrana_prometa_za_pjesake);
        this.addSign(z10, tableName);
        Znak z11 = new Znak("Zabrana prometa za ručna kolica", R.drawable.zi_zabrana_prometa_za_rucna_kolica);
        this.addSign(z11, tableName);
        Znak z12 = new Znak("Zabrana prometa za sva motorna vozila", R.drawable.zi_zabrana_prometa_za_sva_motorna_vozila);
        this.addSign(z12, tableName);
        Znak z13 = new Znak("Zabrana prometa za sva motorna vozila osim za motocikle bez prikolice i mopede", R.drawable.zi_zabrana_prometa_za_sva_motorna_vozila_osim_za_motocikle_bez_prikolice_i_mopede);
        this.addSign(z13, tableName);
        Znak z14 = new Znak("Zabrana prometa za teretne automobile", R.drawable.zi_zabrana_prometa_za_teretne_automobile);
        this.addSign(z14, tableName);
        Znak z15 = new Znak("Zabrana prometa za traktore", R.drawable.zi_zabrana_prometa_za_traktore);
        this.addSign(z15, tableName);
        Znak z16 = new Znak("Zabrana prometa za vozila čija širina prelazi određenu širinu", R.drawable.zi_zabrana_prometa_za_vozila_cija_sirina_prelazi_odredjenu_sirinu);
        this.addSign(z16, tableName);
        Znak z17 = new Znak("Zabrana prometa za vozila čija visina prelazi određenu visinu", R.drawable.zi_zabrana_prometa_za_vozila_cija_ukupna_visina_prelazi_odredjenu_visinu);
        this.addSign(z17, tableName);
        Znak z18 = new Znak("Zabrana prometa za vozila čija ukupna masa prelazi određenu masu", R.drawable.zi_zabrana_prometa_za_vozila_cija_ukupna_masa_prelazi_odredjenu_masu);
        this.addSign(z18, tableName);
        Znak z19 = new Znak("Zabrana prometa za vozila koja prevoze eksploziv ili neke lako zapaljive tvari", R.drawable.zi_zabrana_prometa_za_vozila_koja_prevoze_eksploziv_ili_neke_lakozapaljive_tvari);
        this.addSign(z19, tableName);
        Znak z20 = new Znak("Zabrana prometa u jednom smjeru", R.drawable.zi_zabrana_prometa_u_jednom_smjeru);
        this.addSign(z20, tableName);
        Znak z21 = new Znak("Zabrana za zaprežna vozila", R.drawable.zi_zabrana_prometa_za_zaprezna_vozila);
        this.addSign(z21, tableName);

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
                "Alkohol bitno povećava vozačeve sposobnosti", "Alkohol skraćuje vrijeme reagiranja vozaču ", "Alkohol produžuje vrijeme reagiranja vozača", "Alkohol produžuje vrijeme reagiranja vozača", -1, false, 2);
        this.addQuestion(q1);
        Question q2 = new Question("2. Na što morate paziti kod ovog prometnog znaka?"
                , "Na obilaznu cestu", "Moram povećati brzinu", "Nailazi desni zavoj", "Nailazi desni zavoj", R.drawable.slikadva, true, 2);
        this.addQuestion(q2);
        Question q3 = new Question("3. Što je vozač obavezan napraviti ako namjerava voziti unatrag?"
                , "Uključiti sve pokazivače smjera", "Uključiti desni pokazivač smjera", "Uključiti lijevi pokazivač smjera", "Uključiti sve pokazivače smjera", -1, false, 0);
        this.addQuestion(q3);
        Question q4 = new Question("4. Što pokazuje ovaj prometni znak?"
                , "Obavezan smjer vožnje", "Raskrižje cesta iste važnosti", "Položaj ceste s pravom prednosti prolaska", "Položaj ceste s pravom prednosti prolaska", R.drawable.slikadvadesetiosam, true, 2);
        this.addQuestion(q4);
        Question q5 = new Question("5. Približavate se ovom raskrižju. Kako ćete postupiti?"
                , "Voziti prije autobusa", "Propustiti autobus", "Propustiti crveni automobil", "Propustiti autobus", R.drawable.slikadeset, true, 1);
        this.addQuestion(q5);
        Question q6 = new Question("6. Koja svijetla, u pravilu, vozač motornog vozila upotrebljava za osvijetljavanje ceste?"
                , "Kratka ili dnevna svijetla", "Pozicijska svijetla", "Duga svijetla", "Duga svijetla", -1, false, 2);
        this.addQuestion(q6);
        Question q7 = new Question("7. Vozač crnog automobila skreće udesno kao na slici, što mora učiniti?"
                , "Propustiti biciklistu", "Skrenuti prije bicikliste", "Potrubiti", "Propustiti biciklistu", R.drawable.slikatridesetsest, true, 0);
        this.addQuestion(q7);
        Question q8 = new Question("8. Na što ukazuje ovaj prometni znak?"
                , "Na početak ceste namijenjene isključivo za promet motornih vozila", "Na to da je dozovljena vožnja samo osobnim automobilima", "Na ograničenje brzine na 90 km/h", "Na početak ceste namijenjene isključivo za promet motornih vozila", R.drawable.slikasesnaest, true, 0);
        this.addQuestion(q8);
        Question q9 = new Question("9. Što je cesta?"
                , "Cesta je svaka javna cesta na kojoj se odvija promet isključivo motornih vozila", "Cesta je svaka javna cesta i nerazvrstana cesta na kojoj se obavlja promet", "Cesta je svaka javna površina kojom se odvija promet", "Cesta je svaka javna cesta i nerazvrstana cesta na kojoj se obavlja promet", -1, false, 1);
        this.addQuestion(q9);
        Question q10 = new Question("10. Kako ćete postupiti u ovoj situaciji?"
                , "Propustiti vozilo iz suprotnog smjera", "Proći prije vozila iz suprotnog smjera", "Povećati brzinu", "Propustiti vozilo iz suprotnog smjera", R.drawable.slikaprva, true, 0);
        this.addQuestion(q10);
        Question q11 = new Question("11. Je li na ovom dijelu ceste, s obzirom na oznake na kolniku pretjecanje dopušteno?"
                , "Da dopušteno je jer ne postoji prometni znak zabrane", "Dopušteno je nakon što prođu vozila iz suprotnog smjera", "Ne, nije dopušteno", "Ne, nije dopušteno", R.drawable.slikasest, true, 2);
        this.addQuestion(q11);
        Question q12 = new Question("12. Na cesti izvan naselja zaustavljeno je vozilo zbog kvara. " +
                "Na kojoj udaljenosti morate postaviti sigurnosni trokut iza zaustavljenog vozila na kolniku."
                , "Minimalno 80 metara iza vozila", "Minimalno 100 metara iza vozila", "Minimalno 50 metara iza vozila", "Minimalno 100 metara iza vozila", -1, false, 1);
        this.addQuestion(q12);
        Question q13 = new Question("13. Približavate se ovom raskrižju. Kako ćete postupiti?"
                , "Propustiti autobus i motocikl", "Propustiti autobus i voziti prije motocikla", "Voziti prije autobusa i propustiti motocikl", "Voziti prije autobusa i propustiti motocikl", R.drawable.slikatridesetidva, true, 2);
        this.addQuestion(q13);
        Question q14 = new Question("14. Kako djeca najčešće reagiraju u prometu?"
                , "Spontano i nepredvidivo", "U skladu s propisima", "Razborito i staloženo", "Spontano i nepredvidivo", -1, false, 0);
        this.addQuestion(q14);
        Question q15 = new Question("15. Na instrument ploči upaljena je plava žaruljica kao na slici. Što to znači?"
                , "Da vozim sa svijetlima za maglu", "Da vozim s dugim svijetlima", "Da vozim s kratkim svijetlima", "Da vozim s dugim svijetlima", R.drawable.slikatrideset, true, 1);
        this.addQuestion(q15);
        Question q16 = new Question("16. Kako ćete postupiti u ovoj situaciji?"
                , "Voziti kroz raskrižje", "Propustiti crveni automobil", "Propustiti automobil iz suprotnog smjera", "Propustiti automobil iz suprotnog smjera", R.drawable.slikadvadeseticetiri, true, 2);
        this.addQuestion(q16);
        Question q17 = new Question("17. S čime morate računati u situaciji kao na slici?"
                , "Na to da me dijete ne vidi", "Na to da će me dijete sigurno vidjeti", "Da će dijete pravilno reagirati", "Na to da me dijete ne vidi", R.drawable.slikatridesetcetiri, true, 0);
        this.addQuestion(q17);
        Question q18 = new Question("18. Vašim osobnim automobilom vučete priključno vozilo bez kočnica autocestom. Kojom brzinom najviše smijete voziti?"
                , "30 km/h", "60 km/h", "90 km/h", "90 km/h", -1, false, 2);
        this.addQuestion(q18);
        Question q19 = new Question("19. Kako ćete postupiti pri vožnji za jakog vjetra?"
                , "Ubrzati i što prije proći dionicu ceste s jakim vjetrom", "Smanjiti brzinu vožnje", "Pri mimoilaženju držati što manji bočni razmak", "Smanjiti brzinu vožnje", -1, false, 1);
        this.addQuestion(q19);
        Question q20 = new Question("20. Kako ćete postupiti nakon ovog prometnog znaka?"
                , "Bez smanjenja brzine nastaviti vožnju jer znak zabranjuje djeci prelazak preko ceste", "Očekivati nailazak djece koja namjeravaju prijeći kolnik", "Povećati brzinu", "Očekivati nailazak djece koja namjeravaju prijeći kolnik", R.drawable.slikatridesetosam, true, 1);
        this.addQuestion(q20);

    }

    private void addExtraBoardSigns(String tableName) {
        Znak z1 = new Znak("", R.drawable.zdp_dodatni_glavna_cesta);
        this.addSign(z1, tableName);
        Znak z2 = new Znak("", R.drawable.zdp_dodatni_sporedna_cesta);
        this.addSign(z2, tableName);
        Znak z3 = new Znak("", R.drawable.zdp_kamjon);
        this.addSign(z3, tableName);
        Znak z4 = new Znak("", R.drawable.zdp_kocka_sesto_metara);
        this.addSign(z4, tableName);
        Znak z5 = new Znak("", R.drawable.zdp_kola_trazi);
        this.addSign(z5, tableName);
        Znak z6 = new Znak("", R.drawable.zdp_osim_stanara);
        this.addSign(z6, tableName);
        Znak z7 = new Znak("", R.drawable.zdp_parking_auto);
        this.addSign(z7, tableName);
        Znak z8 = new Znak("", R.drawable.zdp_parking_invalid);
        this.addSign(z8, tableName);
        Znak z9 = new Znak("", R.drawable.zdp_stop_sesto_metara);
        this.addSign(z9, tableName);
        Znak z10 = new Znak("", R.drawable.zdp_tri_znaka_sesto_metara);
        this.addSign(z10, tableName);
        Znak z11 = new Znak("", R.drawable.zdp_zabrana_parkiranja_cetiri_do_sest);
        this.addSign(z11, tableName);
    }

    public void addSign(Znak sign, String tableName) {
        ContentValues values = new ContentValues();
        values.put(DatabaseConstants.KEY_NAME, sign.getName());
        values.put(DatabaseConstants.KEY_ID_IMG, sign.getId_img());
        sqLiteDatabase.insert(tableName, null, values);
    }

    public void addQuestion(Question quest) {
        ContentValues values = new ContentValues();
        values.put(DatabaseConstants.KEY_QUES, quest.getQUESTION());
        values.put(DatabaseConstants.KEY_OPTA, quest.getOPTA());
        values.put(DatabaseConstants.KEY_OPTB, quest.getOPTB());
        values.put(DatabaseConstants.KEY_OPTC, quest.getOPTC());
        values.put(DatabaseConstants.KEY_ANSWER, quest.getANSWER());
        values.put(DatabaseConstants.KEY_IMAGE_ID, quest.getImg_ID());
        values.put(DatabaseConstants.KEY_HAS_IMAGE, quest.hasImage() ? 1 : 0);
        values.put(DatabaseConstants.KEY_CORRECT_ANSWER, quest.getCorrectAnswer());
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

    private void addInformationSigns(String tableName) {
        Znak z1 = new Znak("Cesta sa jednosmjernim prometom", R.drawable.zob_cesta_sa_jednosmjernim_prometom);
        this.addSign(z1, tableName);
        Znak z2 = new Znak("Cesta sa prednošću prolaska", R.drawable.zob_cesta_sa_prednoscu_prolaska);
        this.addSign(z2, tableName);
        Znak z3 = new Znak("Djeca na cesti", R.drawable.zob_djeca_na_cesti);
        this.addSign(z3, tableName);
        Znak z4 = new Znak("Izbočina na cesti", R.drawable.zob_izbocina_na_cesti);
        this.addSign(z4, tableName);
        Znak z5 = new Znak("Obilježen pješački prelaz", R.drawable.zob_obiljezen_pjesacki_prelaz);
        this.addSign(z5, tableName);
        Znak z6 = new Znak("Obilježen prijelaz biciklističke staze", R.drawable.zob_obiljezen_prijelaz_biciklisticke_staze);
        this.addSign(z6, tableName);
        Znak z7 = new Znak("Podzemni ili nadzemni pješački prijelaz", R.drawable.zob_podezemni_ili_nadzemni_pjesacki_prijelaz);
        this.addSign(z7, tableName);
        Znak z8 = new Znak("Prednost prema vozilima iz suprotnog smjera", R.drawable.zob_prednost_prolaska_prema_vozilima_iz_suprotnog_smjera);
        this.addSign(z8, tableName);
        Znak z9 = new Znak("Prestanak davanja zvučnih signala", R.drawable.zob_prestanak_davanja_zvucnih_signala);
        this.addSign(z9, tableName);
        Znak z10 = new Znak("Prestanak najmanje dopuštene brzine", R.drawable.zob_prestanak_najmanje_dopustene_brzine);
        this.addSign(z10, tableName);
        Znak z11 = new Znak("Prestanak obavezne uporabe zimske opreme", R.drawable.zob_prestanak_obavezne_uporabe_zimske_opreme);
        this.addSign(z11, tableName);
        Znak z12 = new Znak("Prestanak ograničenja brzine", R.drawable.zob_prestanak_ogranicenja_brzine);
        this.addSign(z12, tableName);
        Znak z13 = new Znak("Prestanak svih zabrana", R.drawable.zob_prestanak_svih_zabrana);
        this.addSign(z13, tableName);
        Znak z14 = new Znak("Prestanak zabrane pretjecanja svih motornih vozila osim mopeda", R.drawable.zob_prestanak_zabrane_pretjecanja_svih_motornih_vozila_osim_mopeda);
        this.addSign(z14, tableName);
        Znak z15 = new Znak("Prestanak zabrane pretjecanja za teretne automobile", R.drawable.zob_prestanak_zabrane_pretjecanja_za_teretne_automobile);
        this.addSign(z15, tableName);
        Znak z16 = new Znak("Završetak biciklističke ceste", R.drawable.zob_zavrsetak_biciklisticke_ceste);
        this.addSign(z16, tableName);
        Znak z17 = new Znak("Završetak ceste s prednošću prolaska", R.drawable.zob_zavrsetak_ceste_s_prednoscu_prolaska);
        this.addSign(z17, tableName);
        Znak z18 = new Znak("Završetak pješačke staze", R.drawable.zob_zavrsetak_pjesacke_staze);
        this.addSign(z18, tableName);
        Znak z19 = new Znak("Završetak staze za jahače", R.drawable.zob_zavrsetak_staze_za_jahace);
        this.addSign(z19, tableName);
        Znak z20 = new Znak("Završetak zone u kojoj je ograničena brzina", R.drawable.zob_zavrsetak_zone_u_kojoj_je_ogranicena_brzina);
        this.addSign(z20, tableName);
        Znak z21 = new Znak("Zona u kojoj je ograničena brzina", R.drawable.zob_zona_u_kojoj_je_ogranicena_brzina);
        this.addSign(z21, tableName);
    }

    private void addTrafficLeadingSigns(String tableName) {

        Znak z1 = new Znak("Potvrda smjera", R.drawable.zvp_potvrda_smjera);
        this.addSign(z1, tableName);
        Znak z2 = new Znak("Predputokaz", R.drawable.zvp_predputokaz);
        this.addSign(z2, tableName);
        Znak z3 = new Znak("Predputokaz za čvoriste autocesta s oznakom čvorišta", R.drawable.zvp_predputokaz_za_cvoriste_autocesta_s_oznakom_cvorista);
        this.addSign(z3, tableName);
        Znak z4 = new Znak("Predputokaz za izlaz", R.drawable.zvp_predputokaz_za_izlaz);
        this.addSign(z4, tableName);
        Znak z5 = new Znak("Predputokaz za izlaz s autoceste ili brze ceste s oznakom izlaza", R.drawable.zvp_predputokaz_za_izlaz_s_autoceste_ili_brze_ceste_s_oznakom_izlaza);
        this.addSign(z5, tableName);
        Znak z6 = new Znak("Predputokazna ploča", R.drawable.zvp_predputokazna_ploca);
        this.addSign(z6, tableName);
        Znak z7 = new Znak("Putokaz na portalu iznad dvije putokazne ploče", R.drawable.zvp_putokaz_na_portalu_iznad_dvije_prometne_trake);
        this.addSign(z7, tableName);
        Znak z8 = new Znak("Putokaz na portalu iznad jedne prometne trake", R.drawable.zvp_putokaz_na_portalu_iznad_jedne_prometne_trake);
        this.addSign(z8, tableName);
        Znak z9 = new Znak("Putokazna ploča", R.drawable.zvp_putokazna_ploca);
        this.addSign(z9, tableName);
        Znak z10 = new Znak("Raskrižje", R.drawable.zvp_raskrizje);
        this.addSign(z10, tableName);
        Znak z11 = new Znak("Raskrižje kružnog oblika", R.drawable.zvp_raskrizje_kruznog_oblika);
        this.addSign(z11, tableName);
    }

    public List<Znak> getAllSigns(String tableName) {
        List<Znak> signs = new ArrayList<Znak>();
        String selectQuery = "SELECT * FROM " + tableName;
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Znak znak = new Znak();
                znak.setId(cursor.getInt(0));
                znak.setName(cursor.getString(1));
                znak.setId_img(cursor.getInt(2));
                signs.add(znak);
            } while (cursor.moveToNext());
        }
        return signs;
    }
}
