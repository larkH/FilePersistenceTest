package com.mi.databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 3);
        Button createDb = findViewById(R.id.create_database);
        createDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.getWritableDatabase();
            }
        });

        Button addData = findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase writableDb = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", "The First Line of Code");
                values.put("author", "lin Guo");
                values.put("pages", 570);
                values.put("price", 65.00);
                writableDb.insert("Book", null, values);
                values.clear();
                values.put("name", "Dream of Red Mansions");
                values.put("author", "Xueqin Cao");
                values.put("pages", 1606);
                values.put("price", 59.70);
                writableDb.insert("Book", null, values);
                Toast.makeText(MainActivity.this, "insert succeeded", Toast.LENGTH_SHORT).show();
            }
        });

        Button updateData = findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase writableDb = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price", 54.90);
                writableDb.update("Book", values, "name = ?", new String[]{"The First Line of Code"});
                Toast.makeText(MainActivity.this, "update succeeded", Toast.LENGTH_SHORT).show();
            }
        });

        Button deleteData = findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase writableDb = dbHelper.getWritableDatabase();
                writableDb.delete("Book", "pages > ?", new String[]{"1000"});
                Toast.makeText(MainActivity.this, "delete succeeded", Toast.LENGTH_SHORT).show();
            }
        });

        Button queryData = findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase readableDb = dbHelper.getReadableDatabase();
                Cursor cursor = readableDb.query("Book", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Book book = new Book(name, author, pages, price);
                        Log.e("MainActivity", "queryData: " + book);
                        Toast.makeText(MainActivity.this, "queryData: " + book, Toast.LENGTH_SHORT).show();
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        });
    }
}