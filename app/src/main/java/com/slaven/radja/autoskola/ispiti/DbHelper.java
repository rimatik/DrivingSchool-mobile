package com.slaven.radja.autoskola.ispiti;

/**
 * Created by Computer on 06/08/2014.
 */


import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.slaven.radja.autoskola.R;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 20;
    // Database Name
    private static final String DATABASE_NAME = "autoskolaQuiz";
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
                +KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT, " + KEY_IMAGE_ID + " INTEGER, " + KEY_HAS_IMAGE + " INTEGER) ";
        Log.e("SLAVEN", sql);
        db.execSQL(sql);
        addQuestions();
//db.close();
    }
    private void addQuestions()
    {

        Question q1=new Question("Which company is the largest manufacturer" +
                " of network equipment?","HP", "IBM", "CISCO", "CISCO", R.drawable.parking_icon, true);
        this.addQuestion(q1);
        Question q2=new Question("Which of the following is NOT " +
                "an operating system?", "SuSe", "BIOS", "DOS", "BIOS");
        this.addQuestion(q2);
        Question q3=new Question("Which of the following is the fastest" +
                " writable memory?","RAM", "FLASH","Register","Register", R.drawable.semafor_icon, true);
        this.addQuestion(q3);
        Question q4=new Question("Which of the following device" +
                " regulates internet traffic?", "Router", "Bridge", "Hub","Router");
        this.addQuestion(q4);
        Question q5=new Question("Which of the following is NOT an" +
                " interpreted language?","Ruby","Python","BASIC","BASIC");
        this.addQuestion(q5);

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