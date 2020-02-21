package com.hfad.footballpitch;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

class FootballpitchDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "footballpitch"; // Имя базы данных
    private static final int DB_VERSION = 1; // Версия базы данных

    FootballpitchDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static void insertPerson(SQLiteDatabase db, String name, String pass) {
        ContentValues userValues = new ContentValues();
        userValues.put("LOGIN", name);
        userValues.put("PASSWORD", pass);
        db.insert("PEOPLE", null, userValues);
    }

    public static void insertNote(SQLiteDatabase db, String type, String text) {
        ContentValues userValues = new ContentValues();
        userValues.put("CLASS", type);
        userValues.put("NOTETEXT", text);
        db.insert("NOTES", null, userValues);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PEOPLE (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "LOGIN TEXT, " + "PASSWORD TEXT);");
        insertPerson(db, "admin", "admin");
        db.execSQL("CREATE TABLE NOTES (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "CLASS TEXT, " + "NOTETEXT TEXT);");
        insertNote(db, "PITCH", "Let's play football on our pitch on Sunday! 401 Magnetic Drive, Unit 2\n" +
                "for ××××\n" +
                "Toronto, Ontario, M3J 3H9");
        insertNote(db, "PITCH", "Andiamo vo dvor!\n9:00, 8 of March");
        insertNote(db, "PLAYER", "We need a goalkeeper for game on Sunday! Call us +373942298");
        insertNote(db, "PLAYER", "Any strikers here?");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


}
