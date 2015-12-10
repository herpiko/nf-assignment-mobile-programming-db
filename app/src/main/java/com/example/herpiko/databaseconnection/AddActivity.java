package com.example.herpiko.databaseconnection;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by herpiko on 15-12-11.
 */
public class AddActivity extends MainActivity implements View.OnClickListener {
    EditText title,author;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);
        title = (EditText) findViewById(R.id.editText_title);
        author = (EditText) findViewById(R.id.editText_author);
        Button save  =(Button) findViewById(R.id.button_save);
        save.setOnclickListener(this);

        id = getIntent().getLongExtra("_id",0);
        if (id!=0) {
            update = true;
            // populate form with data
            title.setText(getIntent().getStringExtra("title"));
            author.setText(getIntent().getStringExtra("author"));

        }


    }

    @Override
    public void Onclick(View v) {
        BookHeper helper = new BookHelper(this);
        SQLiteDatabase db = helper.getWritableDtabase();
        ContentValues cv = new ContentValues();
        cv.put("title", title.getText().toString().trim());
        cv.put("author", title.getText().toString().trim());
        if (update) {
            db.update("book_entries", cv, "_id=?", new String[]{String.valueOf(id)});
        } else {
            long newid = db.insert("book_entries", null, cv);
        }
        Intent i = new Intent(this.MainActivity.class);
        startActivity(i);
    }

    private void update_book(long id) {
        //get data to update
        BookHelper helper = new BookHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor e = db.query("book_entries",
                new String[]{"tite","author"},
                "_id=?", new String[]{String.valueOf(id)},
                null, null, null);
        e.moveToFirst();
        Intent ii = new Intent(this.AddActivity.class);
        ii.putExtra("_id", id);
        ii.putExtra("title", e.getString(e.getColumnIndex("title"))));
        ii.putExtra("author", e.getString(e.getColumnIndex("author"))));
        startActivity(ii);


    }
}
