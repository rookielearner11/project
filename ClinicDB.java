package com.example.walkinclinic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ClinicDB extends SQLiteOpenHelper{
    private final static String NAMEOFDB= "Clinic.db"; // NAME OF DATABASE
    private final static int VS= 3;//version;
    private final static String NOMTB= "ClinicTB";//name of my table;
    private final static String Col_1= "ID";
    private final static String Col_2= "Name";
    private final static String Col_3= "Password";
    private final static String Col_4= "Role";





    public ClinicDB(Context context) {

        super(context,NAMEOFDB, null, VS); //create database and table
        SQLiteDatabase db = this.getWritableDatabase();
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table"+ NAMEOFDB + "(ID INTENGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Password INTENGER, Role TEXT )");
        String mysql= "insert values from (0,0,0)";//increment automatically
        db.execSQL(mysql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DEL TABLE IF IT EXISTS" + NOMTB);
        onCreate(db);
    }
}
