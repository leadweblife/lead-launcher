package com.leadweblife.leadlauncher;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ankesh on 16/4/16.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "dblaunch.db";
    private static final String TABLE_CONTACTS = "tbllaunch";
    private static final String TABLE_FAV = "tblfav";




    /**
     * Apps
     */
     private static final String TAG_favpackagename= "pname";


    /**
     *
     */
    private static final String TAG_name= "name";
    private static final String TAG_packagename= "pname";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     *  Creating Tables
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + TAG_name + " TEXT," + TAG_packagename + " TEXT"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    public	void addApp(DBApp contact){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(TAG_name, contact.get_name());
        values.put(TAG_packagename, contact.get_pname());


        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }




    public List<DBApp> getallApp()
    {

        List<DBApp> contactList=new ArrayList<DBApp>();
        String selectQuery="Select * from "+ TABLE_CONTACTS +" order by name";


        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

/**
 *  looping through all rows and adding to list
 */
        if (cursor.moveToFirst()) {
            do {
                DBApp contact = new DBApp();

                contact.set_name(cursor.getString(0));
                contact.set_pname(cursor.getString(1));

                contactList.add(contact);
            }while(cursor.moveToNext());
        }

        return contactList;
    }



    public List<DBApp> getSelApp(String stext)
    {

        List<DBApp> contactList=new ArrayList<DBApp>();
        String selectQuery="Select * from "+ TABLE_CONTACTS +" where name like 'stext%' order by name";


        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

/**
 *  looping through all rows and adding to list
 */
        if (cursor.moveToFirst()) {
            do {
                DBApp contact = new DBApp();

                contact.set_name(cursor.getString(0));
                contact.set_pname(cursor.getString(1));

                contactList.add(contact);
            }while(cursor.moveToNext());
        }

        return contactList;
    }


    /**
     * Deleting all Product Demand Retail Data
     */
    public void deleteApp()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_CONTACTS);
        db.close();
    }



    public int getAppCount(){

        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int crcount=cursor.getCount();
        cursor.close();


        // return count
        return crcount;


    }


}
