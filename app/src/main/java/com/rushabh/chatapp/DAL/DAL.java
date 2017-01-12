package com.rushabh.chatapp.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Rushabh on 1/12/2017.
 */

public class DAL extends SQLiteOpenHelper {

    private final static String databaseName = "myDB";
    private Context context = null;
    private SQLiteDatabase sqlDB;
    ContentValues cv = new ContentValues();
    String tableName = "ChatDetails";

    public DAL(Context context) {
        super(context, databaseName, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create table
        sqLiteDatabase.execSQL("create table ChatDetails ( _id integer primary key autoincrement, name text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void openDB(){
        sqlDB = getWritableDatabase();
    }

    public void closeDB(){
        if (sqlDB != null && sqlDB.isOpen()) {
            sqlDB.close();
        }
    }

    public void insertData(String name){
        cv.put("name",name);
        long data = sqlDB.insert(tableName,null,cv);
        if (data == 1){
            Toast.makeText(context, "Data inserted successfully", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Data not inserted", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteData(){
        sqlDB.delete(tableName,null,null);
    }

    public String checkData(){
        String name = null;
        Cursor cursor = sqlDB.query(tableName,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            name = cursor.getString(cursor.getColumnIndex("name"));
        }
        return name;
    }
}
