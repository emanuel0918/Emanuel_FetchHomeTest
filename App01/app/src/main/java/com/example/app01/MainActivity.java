package com.example.app01;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    private FetchContent process;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.jsonObjectView1);
        process = new FetchContent(getApplicationContext(), listView, R.id.jsonObjectView1);
        process.execute();

    }
}
