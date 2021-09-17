package com.mi.sharedpreferencestest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button saveData = findViewById(R.id.save_data);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("name", "Stephen");
                editor.putInt("age", 30);
                editor.putBoolean("married", true);
                editor.apply();
            }
        });

        Button restoreData = findViewById(R.id.restore_data);
        restoreData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                String name = sharedPreferences.getString("name", "Damian");
                int age = sharedPreferences.getInt("age", 28);
                boolean isMarried = sharedPreferences.getBoolean("married", false);
                Toast.makeText(MainActivity.this, name + " is " + age + " years old,he has married,it is " + isMarried, Toast.LENGTH_SHORT).show();
            }
        });
    }
}