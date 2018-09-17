package com.example.a98611.test.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.a98611.test.db.MySQLiteOpenHelper;
import com.example.a98611.test.domain.Person;

public class PersonDaoImpl {
    private MySQLiteOpenHelper helper;
    public PersonDaoImpl(Context context) {
        helper = new MySQLiteOpenHelper(context);
    }
    public void sava(Person p){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("insert into person(name,phone)values(?,?)",new Object[]{p.name,p.phone});
        db.close();
    }
    public void del(String name){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("delete from person where name = ?",new Object[]{name});
        db.close();
    }
    public void updta(Person p){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("update person set phone = ? where name = ?",new Object[]{p.phone,p.name});
        db.close();
    }
    public String query(String name){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select phone from person  where name = ?", new String[]{name});
        String phone = null;
        while (cursor.moveToNext()){
            phone = cursor.getString(cursor.getColumnIndex("phone"));

        }
        cursor.close();
        db.close();
        return phone;
    }
}
