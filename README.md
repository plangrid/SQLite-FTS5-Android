**SQLite FTS5 Module for Android**

This builds and packages the [FTS5 extension](https://sqlite.org/fts5.html) for SQLite for consumption by the [Requery SQLite Support Library](https://github.com/requery/sqlite-android). Load the module by overriding `SQLiteOpenHelper#createConfiguration` to add it to the list of `#customExtensions`:
```

    @Override protected SQLiteDatabaseConfiguration createConfiguration(final String path, final int openFlags) {
        final SQLiteDatabaseConfiguration config = super.createConfiguration(path, openFlags);
        config.customExtensions.add(new SQLiteFTS5Module().getExtension(context.getApplicationInfo()));
        return config;
    }
```

*Known Limitations*
- `mips` and `mips64` ABI's are [deprecated](https://developer.android.com/ndk/guides/mips.html), so the binaries are not provided by this project
