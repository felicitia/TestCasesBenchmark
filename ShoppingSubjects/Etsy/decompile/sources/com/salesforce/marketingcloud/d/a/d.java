package com.salesforce.marketingcloud.d.a;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.Size;
import com.etsy.android.lib.models.ResponseConstants;
import com.salesforce.marketingcloud.d.e;
import com.salesforce.marketingcloud.j;
import com.salesforce.marketingcloud.messages.c.a;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestrictTo({Scope.LIBRARY})
public final class d extends b implements e {
    public static final String[] a = {"id", "status"};
    private static final String c = j.a(d.class);

    public d(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }

    private static String a(String str, Object... objArr) {
        return String.format(Locale.ENGLISH, str, objArr);
    }

    private static ContentValues b(a aVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", aVar.j());
        String str = "status";
        int i = aVar.s() ? 2 : aVar.r() ? 1 : 0;
        contentValues.put(str, Integer.valueOf(i));
        return contentValues;
    }

    @NonNull
    public Map<String, Integer> a() {
        Cursor rawQuery = this.b.rawQuery("SELECT * FROM inbox_message_status", null);
        Map<String, Integer> emptyMap = Collections.emptyMap();
        if (rawQuery != null) {
            if (rawQuery.moveToFirst()) {
                Map<String, Integer> hashMap = new HashMap<>(rawQuery.getCount());
                do {
                    hashMap.put(rawQuery.getString(rawQuery.getColumnIndex("id")), Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("status"))));
                } while (rawQuery.moveToNext());
                emptyMap = hashMap;
            }
            rawQuery.close();
        }
        return emptyMap;
    }

    public void a(com.salesforce.marketingcloud.e.a aVar, com.salesforce.marketingcloud.e.a aVar2, SQLiteDatabase sQLiteDatabase) {
        Cursor query = sQLiteDatabase.query(ResponseConstants.MESSAGES, com.salesforce.marketingcloud.d.a.a.a.d.a, a("%s=? AND %s=? AND (%s=? OR %s=?)", "content_type", "message_type", "read", "message_deleted"), new String[]{String.valueOf(2), String.valueOf(1), String.valueOf(1), String.valueOf(1)}, null, null, null);
        if (query != null) {
            if (query.moveToFirst()) {
                do {
                    int i = query.getInt(query.getColumnIndex("message_deleted")) == 1 ? 2 : query.getInt(query.getColumnIndex("read")) == 1 ? 1 : 0;
                    ContentValues contentValues = new ContentValues();
                    if (i != 0) {
                        contentValues.put("id", query.getString(query.getColumnIndex("id")));
                        contentValues.put("status", Integer.valueOf(i));
                        a(contentValues);
                    }
                } while (query.moveToNext());
            }
            query.close();
            return;
        }
    }

    public void a(@NonNull a aVar) {
        ContentValues b = b(aVar);
        if (a(b, a("%s = ?", "id"), new String[]{aVar.j()}) == 0) {
            a(b);
        }
    }

    public void a(@Size(min = 1) @NonNull String... strArr) {
        for (String str : strArr) {
            try {
                a("id=?", new String[]{str});
            } catch (Exception e) {
                j.c(c, e, "Failed to delete message %s from %s table.", str, "inbox_message_status");
            }
        }
    }

    public int b() {
        return super.a((String) null);
    }

    /* access modifiers changed from: 0000 */
    public String c() {
        return "inbox_message_status";
    }
}
