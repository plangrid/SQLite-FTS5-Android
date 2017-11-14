package com.plangrid.android.sqlitefts5example;


import android.app.Application;
import android.content.Context;

import io.requery.android.database.sqlite.SQLiteOpenHelper;

public class FTS5ExampleApp extends Application {
    private DatabaseHelper dbHelper;

    @Override public void onCreate() {
        super.onCreate();
        dbHelper = new DatabaseHelper(this);
    }

    public static SQLiteOpenHelper obtainDatabaseHelper(final Context context) {
        return ((FTS5ExampleApp)context.getApplicationContext()).dbHelper;
    }
}
