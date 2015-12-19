package com.example.till.shoppinglist;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public List<String> shopping_list_items;
    public String sharedPreferenceFieldName_nListEntries = "nListEntries";
    public String sharedPreferenceFieldName_listEntry = "listEntry";

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
        if (id == R.id.delete_list) {
            shopping_list_items = new ArrayList<String>();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume(){
        super.onResume();

        ListView lv = (ListView) findViewById(R.id.listView_shopping_list);
        SharedPreferences prefs = getSharedPreferences(getString(R.string.shared_preferences), MODE_PRIVATE);

        shopping_list_items = new ArrayList<String>();
        int nListEntries = prefs.getInt(sharedPreferenceFieldName_nListEntries, 0);
        for(int i=0; i<nListEntries; i++){
            shopping_list_items.add(prefs.getString(sharedPreferenceFieldName_listEntry + i, ""));
        }


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                shopping_list_items );

        lv.setAdapter(arrayAdapter);

    }

    @Override
    public void onPause(){
        super.onPause();

        SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.shared_preferences), MODE_PRIVATE).edit();
        Log.i(getString(R.string.logging_tag), "length of list: " + String.valueOf(shopping_list_items.size()) + "     ");
        for (int i = 0; i < shopping_list_items.size(); i++) {
            editor.putString(sharedPreferenceFieldName_listEntry + i, shopping_list_items.get(i));
        }
        editor.putInt(sharedPreferenceFieldName_nListEntries, shopping_list_items.size());
        editor.commit();
    }

    public void addItemToList(View view) {
        ListView lv = (ListView) findViewById(R.id.listView_shopping_list);
        EditText et = (EditText) findViewById(R.id.editText_item_entry);
        String text = et.getText().toString();
        shopping_list_items.add(text);
        et.setText(null);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                shopping_list_items );

        lv.setAdapter(arrayAdapter);

    }
}
