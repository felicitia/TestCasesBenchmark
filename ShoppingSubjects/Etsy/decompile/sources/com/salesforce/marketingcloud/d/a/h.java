package com.salesforce.marketingcloud.d.a;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import com.salesforce.marketingcloud.d.j;
import com.salesforce.marketingcloud.messages.m;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public final class h extends b implements j {
    public static final String[] a = {"id", "message_id", "region_id"};
    private static final String c = com.salesforce.marketingcloud.j.a(h.class);
    private static final String d = String.format(Locale.ENGLISH, "EXISTS (SELECT 1 FROM %s WHERE %s.%s = %s.%s and %s.%s = ?);", new Object[]{"regions", "region_message", "region_id", "regions", "id", "regions", "location_type"});

    public h(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }

    private static m a(Cursor cursor) {
        try {
            return m.a(cursor.getString(cursor.getColumnIndex("region_id")), cursor.getString(cursor.getColumnIndex("message_id")));
        } catch (Exception e) {
            com.salesforce.marketingcloud.j.c(c, e, "Failed to convert cursor to RegionMessage", new Object[0]);
            return null;
        }
    }

    private static ContentValues b(m mVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("message_id", mVar.b());
        contentValues.put("region_id", mVar.a());
        return contentValues;
    }

    public int a(int i) {
        return a(d, new String[]{String.valueOf(i)});
    }

    public void a(m mVar) {
        a(b(mVar));
    }

    @NonNull
    public List<m> b(String str) {
        List<m> emptyList = Collections.emptyList();
        Cursor a2 = a(a, String.format(Locale.ENGLISH, "%s = ?", new Object[]{"region_id"}), new String[]{str}, null, null, null);
        if (a2 != null) {
            if (a2.moveToFirst()) {
                List<m> arrayList = new ArrayList<>();
                do {
                    m a3 = a(a2);
                    if (a3 != null) {
                        arrayList.add(a3);
                    }
                } while (a2.moveToNext());
                emptyList = arrayList;
            }
            a2.close();
        }
        return emptyList;
    }

    public int c(String str) {
        return a(String.format(Locale.ENGLISH, "%s = ?", new Object[]{"message_id"}), new String[]{str});
    }

    /* access modifiers changed from: 0000 */
    public String c() {
        return "region_message";
    }
}
