package com.company.contactsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper  extends SQLiteOpenHelper {

    private static final String DB_NAME="MyDatabase";
    private static final int DB_VERSION=1;
    private static final String TABLE_NAME="contacts";
    private static final String KEY_ID="id";
    private static final String KEY_NAME="name";
    private static final String KEY_PHONE="phone";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_QUERY="CREATE TABLE "+TABLE_NAME+"("+KEY_ID+" INTEGER PRIMARY KEY , "+KEY_NAME+" VARCHAR(50) , "+
                KEY_PHONE+" VARCHAR(50))";
        sqLiteDatabase.execSQL(CREATE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addContact(Contact contact){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_NAME,contact.getName());
        contentValues.put(KEY_PHONE,contact.getPhone());
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }

    public ArrayList<Contact> getContacts(){
        ArrayList<Contact> contacts=new ArrayList<>();
        String SELECT_QUERY="SELECT * FROM "+TABLE_NAME+"";
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(SELECT_QUERY,null);
        if(cursor.moveToFirst()){
            do{
                String name=cursor.getString(cursor.getColumnIndex(KEY_NAME));
                String phone=cursor.getString(cursor.getColumnIndex(KEY_PHONE));
                int id=cursor.getInt(cursor.getColumnIndex(KEY_ID));
                Contact contact=new Contact(id,name,phone);
                contacts.add(contact);
            }while (cursor.moveToNext());
        }

        return contacts;
    }

    public void updateContact(Contact contact){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_NAME,contact.getName());
        contentValues.put(KEY_PHONE,contact.getPhone());

        sqLiteDatabase.update(TABLE_NAME,contentValues,"id=?",new String[] {String.valueOf(contact.getId())});
    }

    public void deleteContact(int id){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();

        sqLiteDatabase.delete(TABLE_NAME,"id=?",new String[]{String.valueOf(id)});
    }
}
