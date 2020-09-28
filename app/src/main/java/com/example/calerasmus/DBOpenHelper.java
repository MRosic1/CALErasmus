package com.example.calerasmus;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBOpenHelper extends SQLiteOpenHelper {

    private final static String CREATE_EVENTS_TABLE = "create table "+DBStructure.EVENT_TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            +DBStructure.EVENT+" TEXT, "+DBStructure.TIME+" TEXT, "+DBStructure.DATE+" TEXT, "+DBStructure.MONTH+" TEXT, "+DBStructure.YEAR+" TEXT, "+DBStructure.IME+" TEXT, "+DBStructure.LOZINKA+" TEXT) ";
    private static final String DROP_EVENTS_TABLE= "DROP TABLE IF EXISTS "+DBStructure.EVENT_TABLE_NAME;



    public DBOpenHelper(@Nullable Context context) {
        super(context, DBStructure.DB_NAME, null, DBStructure.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EVENTS_TABLE);
        db.execSQL("create table users (username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_EVENTS_TABLE);
        db.execSQL("drop table if exists users");
        onCreate(db);
    }

    public boolean insertData(String username, String password){
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result = db.insert("users",null,contentValues);
        if (result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean checkusername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username = ?", new String[] {username});
        if (cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkusernamePassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});
        if (cursor.getCount() >0)
            return true;
        else
            return false;
    }


    //Spremanje eventova u bazu
    public void SaveEvent(String event,String time,String date,String month,String year,SQLiteDatabase database){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBStructure.EVENT,event);
        contentValues.put(DBStructure.TIME,time);
        contentValues.put(DBStructure.DATE,date);
        contentValues.put(DBStructure.MONTH,month);
        contentValues.put(DBStructure.YEAR,year);
        database.insert(DBStructure.EVENT_TABLE_NAME,null,contentValues);

    }
    //Čitanje eventova iz baze
    public Cursor ReadEvents(String date,SQLiteDatabase database){
        String [] Projections = {DBStructure.EVENT,DBStructure.TIME,DBStructure.DATE,DBStructure.MONTH,DBStructure.YEAR};
        String Selection = DBStructure.DATE + "=?";
        String [] SelectionArgs = {date};
        return database.query(DBStructure.EVENT_TABLE_NAME,Projections,Selection,SelectionArgs,null,null,null);
    }
    //Čitanje po mjesecu i godini
    public Cursor ReadEventsPerMonth(String month,String year,SQLiteDatabase database){
        String [] Projections = {DBStructure.EVENT,DBStructure.TIME,DBStructure.DATE,DBStructure.MONTH,DBStructure.YEAR};
        String Selection = DBStructure.MONTH + "=? and "+DBStructure.YEAR+"=?";
        String [] SelectionArgs = {month,year};
        return database.query(DBStructure.EVENT_TABLE_NAME,Projections,Selection,SelectionArgs,null,null,null);
    }
    //Brisanje eventova iz baze
    public void brisanjeEventa(String event,String date, String time, SQLiteDatabase database){
        String selection = DBStructure.EVENT + "=? and "+DBStructure.DATE+"=? and "+DBStructure.TIME+"=?";
        String[] selectionArg = {event,date,time};
        database.delete(DBStructure.EVENT_TABLE_NAME,selection,selectionArg);
    }
}
