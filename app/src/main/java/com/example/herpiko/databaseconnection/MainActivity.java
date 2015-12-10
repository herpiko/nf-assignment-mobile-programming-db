package com.example.herpiko.databaseconnection;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv = (ListView) findViewById(R.id.listView);

        BookHelper helper = new BookHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        String[] projection={"_id", "title"};
        Cursor c = db.query("book_entries", projection, null, null, null, null, null);

        ArrayList<String> data = new ArrayList<String>();
        c.moveToFirst();
        while(!c.isAfterLast()) {
            String title = c.getString(c.getColumnIndex("title"));
            data.add(title);
            c.modeToNext();
        }

        if (data.isEmpty) {
            data.add("No book entries. please add");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.android.R, layout.simple_list_item_1, data);
        lv.setAdapter(adapter);
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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContenxtMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);

    }

    @Override
    public boolean onContextItemSelecteed(Menu item) {
        AdapterView.AdapterContextMenuInfo info = (AdaptreView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getItemId() == R.id.context_hapus) {
            delete_book(info.id);
        } else if (item.getItemId() = R.id.context_ubah){

            update_book(info.id);
        }
        return true;
    }
}
