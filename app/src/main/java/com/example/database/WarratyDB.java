package com.example.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WarratyDB extends SQLiteOpenHelper {
    public static final String DB_Name = "warraty.db";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "Warraty";
    public  static final String W_ID = "WId";
    public static final String W_NAME = "WName";
    public static final String W_DES = "WDes";
    public static final String W_PHOTO = "WPhoto";



    public WarratyDB (@Nullable Context context){super(context, DB_Name , null , DB_VERSION);}



    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS "+TBL_NAME+" ( "
                + W_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + W_NAME + " VARCHAR(500), "
                + W_DES + " NVARCHAR(500), "
                + W_PHOTO + " BLOB )" ;
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL(" DROP TABLE IF EXISTS " + TBL_NAME);
    onCreate(db);
    }
    public Cursor getData (String Sql){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(Sql, null);
    }
    // INSERT, UPDATE, DELETE,...
    public boolean execSql(String sql){
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(sql);
            return true;

        }catch (Exception e){
            return false;
        }
    }
    public int getNumOfRows(){
        Cursor cursor = getData("SELECT * FROM " + TBL_NAME + " ;");
        int nRows = cursor.getCount();
        cursor.close();
        return nRows;
    }
    //
    public  boolean insertData(String name , String des, byte[] photo){
        try {
            SQLiteDatabase db = getWritableDatabase();
            String sql = "INSERT INTO " + TBL_NAME + "VALUES(null,?,?, ?, ?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.clearBindings();
            statement.bindString(1, name);
            statement.bindString(2, des);
            statement.bindBlob(3, photo);

            statement.executeInsert();
            return true;
        }catch (Exception e ){
            return false;
        }
    }
}
