package com.mi.litepaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;
import org.litepal.exceptions.DataSupportException;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建数据库和表
        Button createDb = findViewById(R.id.create_database);
        createDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LitePal.getDatabase();
                Toast.makeText(MainActivity.this, "createDatabase succeeded", Toast.LENGTH_SHORT).show();
            }
        });

        //添加数据
        Button addData = findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book("The First Line of Code", "Lin Guo", 570, 65.00, "People Post Press");
                book.save();
                Toast.makeText(MainActivity.this, "addData succeeded", Toast.LENGTH_SHORT).show();
            }
        });

        //修改数据
        Button updateData = findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Book book = new Book("Dream of Red Mansions", "Xueqin Cao", 1606, 59.70, "unKnow");
                //book.save();
                //book.setPress("People's Literature Publishing House");
                //book.save();

                Book book = new Book();
                book.setName("The First Line of Code(second hand)");
                book.setPrice(54.90);
                book.update(2);

                //Book book = new Book();
                //book.setName("The First Line of Code");
                //book.setPrice(65.00);
                //book.updateAll("author = ? and pages = ?", "Lin Guo", "570");

                //Book book = new Book();
                //book.setToDefault("pages");//将页数设置为默认值0
                //book.updateAll();
                Toast.makeText(MainActivity.this, "updateData succeeded", Toast.LENGTH_SHORT).show();
            }
        });

        //删除数据
        Button deleteData = findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LitePal.deleteAll(Book.class, "price > ?", "60");
                Toast.makeText(MainActivity.this, "deleteData succeeded", Toast.LENGTH_SHORT).show();
            }
        });

        //查询数据
        Button queryData = findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //List<Book> books = LitePal.findAll(Book.class);
                //for (Book book : books) {
                //    Log.e("queryData", "queryData: " + book);
                //}

                List<Book> books = LitePal.select("name", "author", "price").where("price < ?", "60").order("price").find(Book.class);
                for (Book book : books) {
                    Log.e("queryData", "queryData: " + book);
                }

                Toast.makeText(MainActivity.this, "queryData succeeded", Toast.LENGTH_SHORT).show();
            }
        });
    }
}