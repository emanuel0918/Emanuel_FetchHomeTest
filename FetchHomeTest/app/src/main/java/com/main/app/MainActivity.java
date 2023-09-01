
package com.main.app;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.main.app.task.FetchContent;

public class MainActivity extends AppCompatActivity {
    private FetchContent process;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        process = new FetchContent(getApplicationContext());
        process.execute();

    }
}
