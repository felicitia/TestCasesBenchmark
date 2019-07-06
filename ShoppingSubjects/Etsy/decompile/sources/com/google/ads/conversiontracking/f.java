package com.google.ads.conversiontracking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.Locale;

public class f {
    /* access modifiers changed from: private */
    public static final String a = String.format(Locale.US, "CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, %s TEXT NOT NULL, %s TEXT, %s INTEGER, %s INTEGER, %s TEXT, %s INTEGER, %s INTEGER,%s INTEGER);", new Object[]{"conversiontracking", "conversion_ping_id", "string_url", "preference_key", "is_repeatable", "parameter_is_null", "preference_name", "record_time", "retry_count", "last_retry_time"});

    public class a extends SQLiteOpenHelper {
        public a(Context context, String str) {
            super(context, str, null, 5);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(f.a);
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            StringBuilder sb = new StringBuilder(64);
            sb.append("Database updated from version ");
            sb.append(i);
            sb.append(" to version ");
            sb.append(i2);
            Log.i("GoogleConversionReporter", sb.toString());
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS conversiontracking");
            onCreate(sQLiteDatabase);
        }
    }
}
