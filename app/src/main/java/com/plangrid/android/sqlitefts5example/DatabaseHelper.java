package com.plangrid.android.sqlitefts5example;


import android.content.Context;

import com.plangrid.android.sqlitefts5.SQLiteFTS5Module;

import io.requery.android.database.sqlite.SQLiteDatabase;
import io.requery.android.database.sqlite.SQLiteDatabaseConfiguration;
import io.requery.android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String NAME = "example_fts5_app";

    private final Context context;

    public DatabaseHelper(final Context context) {
        super(context, NAME, null, VERSION);
        this.context = context;
    }

    @Override public void onCreate(final SQLiteDatabase db) {

    }

    @Override public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {

    }

    @Override protected SQLiteDatabaseConfiguration createConfiguration(final String path, final int openFlags) {
        final SQLiteDatabaseConfiguration config = super.createConfiguration(path, openFlags);
        config.customExtensions.add(new SQLiteFTS5Module().getExtension(context.getApplicationInfo()));
        return config;
    }
}