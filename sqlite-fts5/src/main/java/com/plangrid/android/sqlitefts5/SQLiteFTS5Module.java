package com.plangrid.android.sqlitefts5;

import android.content.pm.ApplicationInfo;

import io.requery.android.database.sqlite.SQLiteCustomExtension;

public class SQLiteFTS5Module {
    public String getPath(final ApplicationInfo info) {
        return info.nativeLibraryDir + "libsqlite-fts5.so";
    }

    public SQLiteCustomExtension getExtension(final String path) {
        return new SQLiteCustomExtension(path, null);
    }

    public SQLiteCustomExtension getExtension(final ApplicationInfo info) {
        return getExtension(getPath(info));
    }
}