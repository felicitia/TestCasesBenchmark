package com.salesforce.marketingcloud.d.a.b;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import com.etsy.android.lib.models.datatypes.ShopHomeSortOption;
import com.salesforce.marketingcloud.j;

@RestrictTo({Scope.LIBRARY})
public class d {
    private static final String a = j.a(d.class);

    private d() {
    }

    public static void a(SQLiteDatabase sQLiteDatabase) {
        try {
            Cursor query = sQLiteDatabase.query("cloud_page_messages", null, null, null, null, null, null);
            if (query != null) {
                if (query.getColumnIndex(ShopHomeSortOption.SORT_CUSTOM) == -1) {
                    b(sQLiteDatabase);
                }
                query.close();
            }
        } catch (Exception unused) {
            j.e(a, "Failed to update cloud_page_messages table.", new Object[0]);
        }
    }

    private static void b(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL("ALTER TABLE cloud_page_messages ADD COLUMN custom VARCHAR;");
            sQLiteDatabase.execSQL("ALTER TABLE cloud_page_messages ADD COLUMN keys VARCHAR;");
            sQLiteDatabase.execSQL("ALTER TABLE cloud_page_messages ADD COLUMN title VARCHAR;");
            sQLiteDatabase.execSQL("ALTER TABLE cloud_page_messages ADD COLUMN alert VARCHAR;");
            sQLiteDatabase.execSQL("ALTER TABLE cloud_page_messages ADD COLUMN sound VARCHAR;");
            sQLiteDatabase.execSQL("ALTER TABLE cloud_page_messages ADD COLUMN mediaUrl VARCHAR;");
            sQLiteDatabase.execSQL("ALTER TABLE cloud_page_messages ADD COLUMN mediaAlt VARCHAR;");
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
            try {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS cloud_page_messages");
                sQLiteDatabase.execSQL("CREATE TABLE cloud_page_messages (id VARCHAR PRIMARY KEY, start_date VARCHAR, end_date VARCHAR, message_type INTEGER, content_type INTEGER, url VARCHAR, subject VARCHAR, read SMALLINT, message_deleted SMALLINT, custom VARCHAR, keys VARCHAR, title VARCHAR, alert VARCHAR, sound VARCHAR, mediaUrl VARCHAR, mediaAlt VARCHAR, message_hash VARCHAR, request_id VARCHAR);");
                sQLiteDatabase.setTransactionSuccessful();
                j.c(a, e, "Unable to update cloud_page_messages table", new Object[0]);
            } catch (SQLException e2) {
                j.c(a, e2, "Unable to create cloud_page_messages table", new Object[0]);
            } catch (Throwable th) {
                sQLiteDatabase.endTransaction();
                throw th;
            }
        }
        sQLiteDatabase.endTransaction();
    }
}
