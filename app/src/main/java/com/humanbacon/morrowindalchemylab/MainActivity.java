package com.humanbacon.morrowindalchemylab;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView ingredientsList = (TextView) findViewById(R.id.ingredients_list);
        final TextView effectsList = (TextView) findViewById(R.id.effects_list);
        ingredientsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Ingredients", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(v.getContext(), IngredientsListActivity.class));
            }
        });
        effectsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Effects", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(v.getContext(), EffectsActivity.class));
            }
        });
        DBHelper helper = new DBHelper(this);
        helper.createDB();
        SQLiteDatabase hihi = helper.getDB();
        //Log.i("hihi", hihi.toString());
        //Cursor c = hihi.rawQuery("SELECT name FROM ingredients",null);
        Cursor c = hihi.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        c.moveToFirst();
        Log.i("hihi", c.getString(0));
        c.moveToNext();
        Log.i("hihi2", c.getString(0));
        c.close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
