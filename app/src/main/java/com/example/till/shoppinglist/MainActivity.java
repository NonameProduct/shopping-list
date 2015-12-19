package com.example.till.shoppinglist;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;


public class MainActivity extends AppCompatActivity {

    public String shopping_list_items;
    public String preferenceFieldName = "shopping_list_items";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    @Override
    public void onResume(){
        super.onResume();

        SharedPreferences prefs = getSharedPreferences(getString(R.string.shared_preferences), MODE_PRIVATE);
        String restoredText = prefs.getString(preferenceFieldName, "");
        shopping_list_items = restoredText;
    }

    @Override
    public void onPause(){
        super.onPause();

        SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.shared_preferences), MODE_PRIVATE).edit();
        editor.putString(preferenceFieldName, shopping_list_items);
        editor.commit();
    }

    public void addItemToList(View view){
        TextView tv = (TextView) findViewById(R.id.textView_shopping_list);
        EditText et = (EditText) findViewById(R.id.editText_item_entry);
        shopping_list_items = shopping_list_items + "\n" + et.getText();
        tv.setText(shopping_list_items);
        et.setText(null);
    }
}
