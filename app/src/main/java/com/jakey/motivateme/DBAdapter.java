package com.jakey.motivateme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jakey on 10/12/14.
 */

public class DBAdapter {
    public static final String KEY_USERROWID = "_id";
    public static final String KEY_EVENTROWID="_id";
    public static final String KEY_LOGID = "_id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DATE ="date";
    public static final String KEY_GOAL ="goal";
    public static final String KEY_CREATOR = "creator";
    public static final String KEY_DIET = "diet";
    public static final String KEY_WEIGHT = "weight";
    public static final String KEY_WORKOUT = "workout";
    public static final String KEY_LOGDATE = "date";



    private static final String TAG = "DBAdapter";

    private static final String DATABASE_NAME = "motivateDB";
    private static final String DATABASE_USERSTABLE = "users";
    private static final String DATABASE_EVENTSTABLE = "events";
    private static final String DATABASE_LOGTABLE = "log";
    private static final int DATABASE_VERSION = 53;

    private static final String DATABASE_CREATEUSERS =
            "create table if not exists users(_id integer primary key autoincrement, " +
                    "username VARCHAR not null, password VARCHAR not null, email VARCHAR not null);";
    private static final String DATABASE_CREATEEVENTS =
            "create table if not exists events(_id integer primary key autoincrement, " +
                    "title VARCHAR not null, date VARCHAR not null, goal integer not null, creator integer not null);";
    private static final String DATABASE_CREATELOG =
            "create table if not exists log(_id integer primary key autoincrement, " +
                    "date VARCHAR not null, weight VARCHAR not null, diet INTEGER not null, workout INTEGER not null, creator integer not null);";

    private final Context ourContext;
    private DbHelper ourHelper;
    private SQLiteDatabase ourDatabase;

    public DBAdapter(Context ourContext) {

        this.ourContext = ourContext;
    }


    private static class DbHelper extends SQLiteOpenHelper {
        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            sqLiteDatabase.execSQL(DATABASE_CREATEUSERS);
            sqLiteDatabase.execSQL(DATABASE_CREATEEVENTS);
            sqLiteDatabase.execSQL(DATABASE_CREATELOG);

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_USERSTABLE);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_EVENTSTABLE);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_LOGTABLE);
            onCreate(sqLiteDatabase);
        }
    }


    public DBAdapter open() {
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();

        return this;
    }

    public void close() {
        ourHelper.close();
    }


    public long insertUser(String myUsername, String myPass, String myEmail) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_USERNAME, myUsername);
        cv.put(KEY_PASSWORD, myPass);
        cv.put(KEY_EMAIL, myEmail);

        return ourDatabase.insert(DATABASE_USERSTABLE, null, cv);
    }

    public int getUser(String UserName, String Pass) {
        //SELECT
        String[] columns = {"_id"};
        //WHERE clause
        String selection = "username=? AND password=?";
        //WHERE clause arguments
        String[] selectionArgs = {UserName,Pass};
        //SELECT _id FROM login WHERE username=uname AND password=pass
       Cursor cursor = ourDatabase.query(DATABASE_USERSTABLE, columns, selection, selectionArgs, null, null, null);
        cursor.moveToFirst();
        if(cursor.getCount() <= 0)
        {
            return(-1);
        }
        else
        {
            return cursor.getInt(0);
        }
    }

    public long insertEvent(String myTitle, String dateFormat, String myGoal, int currentUserId) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_TITLE, myTitle);
        cv.put(KEY_DATE, dateFormat);
        cv.put(KEY_GOAL, myGoal);
        cv.put(KEY_CREATOR, currentUserId);

        return ourDatabase.insert(DATABASE_EVENTSTABLE, null, cv);
    }

    public Cursor getEvents(int currentUserId)
    {
        Cursor mCursor =
                ourDatabase.query(true, DATABASE_EVENTSTABLE, new String[] {KEY_EVENTROWID,
                                KEY_TITLE, KEY_DATE, KEY_GOAL, KEY_CREATOR},
                        KEY_CREATOR + "=" + currentUserId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public long insertLog(String date, String myWeight, String myDiet, String myWork, int currentUserId) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_LOGDATE, date);
        cv.put(KEY_WEIGHT, myWeight);
        cv.put(KEY_DIET, myDiet);
        cv.put(KEY_WORKOUT, myWork);
        cv.put(KEY_CREATOR, currentUserId);

        return ourDatabase.insert(DATABASE_LOGTABLE, null, cv);
    }

    public Cursor getLogs(int currentUserId)
    {
        Cursor mCursor =
                ourDatabase.query(true, DATABASE_LOGTABLE, new String[] {KEY_LOGID,
                                KEY_LOGDATE, KEY_WEIGHT, KEY_DIET, KEY_WORKOUT, KEY_CREATOR},
                        KEY_CREATOR + "=" + currentUserId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
}
