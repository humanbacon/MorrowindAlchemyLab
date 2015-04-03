package com.humanbacon.morrowindalchemylab;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by yulapshun on 3/31/15.
 */
public class FakeDBHelper {
    private static final String DB_NAME = "morrowind.db";
    private Context context;
    private static SQLiteDatabase DB;

    public FakeDBHelper(Context c){
        context = c;
        //DB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        
    }

}
