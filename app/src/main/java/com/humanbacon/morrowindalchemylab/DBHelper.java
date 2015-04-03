package com.humanbacon.morrowindalchemylab;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by yulapshun on 3/30/15.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_PATH = "/data/data/com.humanbacon.morrowindalchemylab/databases/";
    public static final String DB_NAME = "morrowind.db";
    public static final int VERSION = 1;
    private Context context;
    private SQLiteDatabase DB = this.getReadableDatabase();

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
        DBHelper.createDB(context);
    }

    public DBHelper(Context context) {
        this(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase getDB(){
        DB = this.getReadableDatabase();
        return DB;
    }

    public Cursor getIngredients(){
        return DB.rawQuery("SELECT * FROM ingredients", null);
    }

    private static boolean checkDB(SQLiteDatabase db){
        boolean dbExists = false;
        boolean dbOutdated = true;
        dbExists = db != null ? true : false;
        if(dbExists){
            Cursor c = db.rawQuery("SELECT version FROM android_metadata", null);
            c.moveToFirst();
            int version = c.getInt(0);
            dbOutdated = version < VERSION ? true : false;
        }
        return (dbExists && !dbOutdated);
    }

    public static void createDB(Context context){
        SQLiteDatabase db = null;
        String path = DB_PATH + DB_NAME;
        try{
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        }catch(SQLiteException e){
            db = null;
        }
        if(!checkDB(db)){
            try{
                InputStream inDB = context.getAssets().open(DB_NAME);
                OutputStream outDB = new FileOutputStream(path);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inDB.read(buffer))>0){
                    outDB.write(buffer, 0, length);
                }
                outDB.flush();
                outDB.close();
                inDB.close();
                db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
                db.execSQL("UPDATE android_metadata SET version="+VERSION);
            }catch(IOException e){

            }
        }
    }

}
