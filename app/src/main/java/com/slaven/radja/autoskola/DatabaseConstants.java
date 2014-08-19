package com.slaven.radja.autoskola;

/**
 * Created by fgrbac on 19/08/14.
 */
public class DatabaseConstants {

    // Database version
    public static final int DATABASE_VERSION = 7;

    // Database Name
    public static final String DATABASE_NAME = "autoskola.db";

    // Table names
    public static final String TABLE_QUEST = "quest";
    public static final String TABLE_ZNAK_DOPUNSKE_PLOCE = "znakoviDopunskePloce";
    public static final String TABLE_PREDNOST_NA_RASKRIZJU = "PrednostNaRaskrizju";
    public static final String TABLE_PROMETNI_POLICAJAC = "PrometniPolicajac";
    public static final String TABLE_PROMETNI_SEMAFOR = "Prometnisemafor";
    public static final String TABLE_ZNAK_IZRICITIH_NAREDBI = "znakoviIzricitihNaredbi";
    public static final String TABLE_ZNAK_OBAVIJESTI = "znakoviObavijesti";
    public static final String TABLE_ZNAK_OPASNOSTI = "znakoviOpasnosti";
    public static final String TABLE_ZNAK_VODJENJE_PROMETA = "znakoviZaVodjenjePrometa";

    // Table columns names
    public static final String KEY_ID = "id";
    public static final String KEY_QUES = "question";
    public static final String KEY_ANSWER = "answer"; //correct option
    public static final String KEY_OPTA = "opta"; //option a
    public static final String KEY_OPTB = "optb"; //option b
    public static final String KEY_OPTC = "optc"; //option c
    public static final String KEY_IMAGE_ID = "image_id";
    public static final String KEY_HAS_IMAGE = "has_image";
    public static final String KEY_CORRECT_ANSWER = "correct_answer";
    public static final String KEY_NAME = "name";
    public static final String KEY_ID_IMG = "idImage";

}
