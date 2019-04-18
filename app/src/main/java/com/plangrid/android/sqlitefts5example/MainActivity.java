package com.plangrid.android.sqlitefts5example;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(() -> {
            final Cursor c = FTS5ExampleApp.obtainDatabaseHelper(this)
                                           .getReadableDatabase()
                                           .query("searching", null, "1", null, null, null, null);

            Log.d(getClass().getSimpleName(), String.format("Loaded %d items", c.getCount()));
        }).run();
    }
}