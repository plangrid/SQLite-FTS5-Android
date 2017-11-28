***SQLite FTS5 Module for Android***

This builds and packages the [FTS5 extension](https://sqlite.org/fts5.html) for SQLite for consumption by the [Requery SQLite Support Library](https://github.com/requery/sqlite-android). Load the module by overriding `SQLiteOpenHelper#createConfiguration` to add it to the list of `#customExtensions`:
```

    @Override protected SQLiteDatabaseConfiguration createConfiguration(final String path, final int openFlags) {
        final SQLiteDatabaseConfiguration config = super.createConfiguration(path, openFlags);
        config.customExtensions.add(new SQLiteFTS5Module().getExtension(context.getApplicationInfo()));
        return config;
    }
```

**Building**

To build this module, you must install `gcc`, `tcl`, and `make` to your `PATH`. Alternately, install Docker, and use the `docker-gradle` script to build inside a container.

Build the module with gradle:
```
./gradlew sqlite-fts5:generateRelease
```

Alternately, build the module on a Docker container using gradle:
```
./docker-gradle sqlite-fts5:generateRelease
```

**Using**

Add the PlanGrid repo to your module's `build.gradle`:
```
repositories {
    ...
    maven {
         url 'https://plangrid.jfrog.io/plangrid/libs-release-local'
         
         credentials {
            //Ask @goosemo or @misterrager about credentials
         }
    }
    ...
}
```

As noted above, you need credentials to access the private repo.

Then, add the package to your list of dependencies:
```
dependencies {
    compile 'com.plangrid.android:sqlite-fts5-android:3.21.0'
}
```

*Known Limitations*
- `mips` and `mips64` ABI's are [deprecated](https://developer.android.com/ndk/guides/mips.html), so the binaries are not provided by this project
