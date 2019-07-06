package com.salesforce.marketingcloud.d.a;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.ShopHomeSortOption;
import com.salesforce.marketingcloud.d.i;
import com.salesforce.marketingcloud.e.a;
import com.salesforce.marketingcloud.e.g;
import com.salesforce.marketingcloud.j;
import com.salesforce.marketingcloud.messages.c;
import com.salesforce.marketingcloud.messages.c.b;
import java.util.Locale;

public final class f extends b implements i {
    public static final String[] a = {"id", "title", "alert", "sound", "mediaUrl", "mediaAlt", "open_direct", "start_date", "end_date", "message_type", "content_type", "url", ShopHomeSortOption.SORT_CUSTOM, "keys", "period_show_count", "last_shown_date", "next_allowed_show", "show_count", "message_limit", "rolling_period", "period_type", "number_of_periods", "messages_per_period", "proximity", "notify_id"};
    private static final String c = j.a(f.class);

    public f(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }

    @Nullable
    private static c a(@NonNull Cursor cursor, @NonNull a aVar) {
        b t = c.t();
        try {
            t.a(cursor.getString(cursor.getColumnIndex("id")));
            t.b(aVar.b(cursor.getString(cursor.getColumnIndex("title"))));
            t.c(aVar.b(cursor.getString(cursor.getColumnIndex("alert"))));
            t.d(cursor.getString(cursor.getColumnIndex("sound")));
            try {
                t.a(c.a.a(aVar.b(cursor.getString(cursor.getColumnIndex("mediaUrl"))), aVar.b(cursor.getString(cursor.getColumnIndex("mediaAlt")))));
            } catch (IllegalStateException unused) {
            }
            t.a(g.a(cursor.getString(cursor.getColumnIndex("start_date"))));
            t.b(g.a(cursor.getString(cursor.getColumnIndex("end_date"))));
            t.a(cursor.getInt(cursor.getColumnIndex("message_type")));
            t.b(cursor.getInt(cursor.getColumnIndex("content_type")));
            t.e(aVar.b(cursor.getString(cursor.getColumnIndex("url"))));
            t.g(aVar.b(cursor.getString(cursor.getColumnIndex(ShopHomeSortOption.SORT_CUSTOM))));
            t.c(cursor.getInt(cursor.getColumnIndex("messages_per_period")));
            t.d(cursor.getInt(cursor.getColumnIndex("number_of_periods")));
            t.e(cursor.getInt(cursor.getColumnIndex("period_type")));
            boolean z = true;
            if (cursor.getInt(cursor.getColumnIndex("rolling_period")) != 1) {
                z = false;
            }
            t.a(z);
            t.f(cursor.getInt(cursor.getColumnIndex("message_limit")));
            t.g(cursor.getInt(cursor.getColumnIndex("proximity")));
            t.f(aVar.b(cursor.getString(cursor.getColumnIndex("open_direct"))));
            String b = aVar.b(cursor.getString(cursor.getColumnIndex("keys")));
            if (b != null) {
                t.a(g.c(b));
            }
            c a2 = t.a();
            a2.a(cursor.getInt(cursor.getColumnIndex("notify_id")));
            a2.c(cursor.getInt(cursor.getColumnIndex("period_show_count")));
            a2.a(g.a(cursor.getString(cursor.getColumnIndex("next_allowed_show"))));
            a2.b(cursor.getInt(cursor.getColumnIndex("show_count")));
            a2.b(g.a(cursor.getString(cursor.getColumnIndex("last_shown_date"))));
            return a2;
        } catch (Exception e) {
            j.c(c, e, "Unable to read message from DB", new Object[0]);
            return null;
        }
    }

    private static String a(String str, Object... objArr) {
        return String.format(Locale.ENGLISH, str, objArr);
    }

    private static ContentValues b(c cVar, @NonNull a aVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", cVar.a());
        contentValues.put("title", aVar.a(cVar.b()));
        contentValues.put("alert", aVar.a(cVar.c()));
        contentValues.put("sound", cVar.d());
        if (cVar.e() != null) {
            contentValues.put("mediaUrl", aVar.a(cVar.e().a()));
            contentValues.put("mediaAlt", aVar.a(cVar.e().b()));
        }
        contentValues.put("start_date", g.a(cVar.f()));
        contentValues.put("end_date", g.a(cVar.g()));
        contentValues.put("message_type", Integer.valueOf(cVar.h()));
        contentValues.put("content_type", Integer.valueOf(cVar.i()));
        contentValues.put("url", aVar.a(cVar.j()));
        contentValues.put(ShopHomeSortOption.SORT_CUSTOM, aVar.a(cVar.s()));
        contentValues.put("messages_per_period", Integer.valueOf(cVar.k()));
        contentValues.put("number_of_periods", Integer.valueOf(cVar.l()));
        contentValues.put("period_type", Integer.valueOf(cVar.m()));
        contentValues.put("rolling_period", Integer.valueOf(cVar.n() ? 1 : 0));
        contentValues.put("message_limit", Integer.valueOf(cVar.o()));
        contentValues.put("proximity", Integer.valueOf(cVar.p()));
        contentValues.put("open_direct", aVar.a(cVar.q()));
        contentValues.put("keys", aVar.a(g.a(cVar.r())));
        contentValues.put("next_allowed_show", g.a(cVar.v()));
        contentValues.put("period_show_count", Integer.valueOf(cVar.x()));
        contentValues.put("notify_id", Integer.valueOf(cVar.u()));
        contentValues.put("show_count", Integer.valueOf(cVar.w()));
        contentValues.put("last_shown_date", g.a(cVar.y()));
        return contentValues;
    }

    public int a(int i) {
        return a(a("%s = ?", "message_type"), new String[]{String.valueOf(i)});
    }

    public c a(@NonNull String str, @NonNull a aVar) {
        Cursor a2 = a(a, a("%s = ?", "id"), new String[]{str}, null, null, null, "1");
        c cVar = null;
        if (a2 != null) {
            if (a2.moveToFirst()) {
                cVar = a(a2, aVar);
            }
            a2.close();
        }
        return cVar;
    }

    public void a(a aVar, a aVar2, SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery = sQLiteDatabase.rawQuery(a("SELECT * FROM %1$s WHERE %2$s = ? OR %2$s = ? OR %2$s = ?", ResponseConstants.MESSAGES, "message_type"), new String[]{String.valueOf(3), String.valueOf(4), String.valueOf(5)});
        if (rawQuery != null) {
            if (rawQuery.moveToFirst()) {
                do {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("id", rawQuery.getString(rawQuery.getColumnIndex("id")));
                    contentValues.put("alert", aVar2.a(aVar.b(rawQuery.getString(rawQuery.getColumnIndex("alert")))));
                    contentValues.put("sound", rawQuery.getString(rawQuery.getColumnIndex("sound")));
                    contentValues.put("open_direct", aVar2.a(aVar.b(rawQuery.getString(rawQuery.getColumnIndex("open_direct")))));
                    contentValues.put("start_date", rawQuery.getString(rawQuery.getColumnIndex("start_date")));
                    contentValues.put("end_date", rawQuery.getString(rawQuery.getColumnIndex("end_date")));
                    contentValues.put("message_type", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("message_type"))));
                    contentValues.put("content_type", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("content_type"))));
                    contentValues.put("url", aVar2.a(aVar.b(rawQuery.getString(rawQuery.getColumnIndex("url")))));
                    contentValues.put("keys", aVar2.a(aVar.b(rawQuery.getString(rawQuery.getColumnIndex("keys")))));
                    contentValues.put("period_show_count", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("period_show_count"))));
                    contentValues.put("show_count", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("show_count"))));
                    contentValues.put("last_shown_date", rawQuery.getString(rawQuery.getColumnIndex("last_shown_date")));
                    contentValues.put("next_allowed_show", rawQuery.getString(rawQuery.getColumnIndex("next_allowed_show")));
                    contentValues.put("message_limit", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("message_limit"))));
                    contentValues.put("rolling_period", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("rolling_period"))));
                    contentValues.put("period_type", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("period_type"))));
                    contentValues.put("number_of_periods", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("number_of_periods"))));
                    contentValues.put("messages_per_period", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("messages_per_period"))));
                    contentValues.put("proximity", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("proximity"))));
                    contentValues.put("notify_id", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("notify_id"))));
                    a(contentValues);
                } while (rawQuery.moveToNext());
            }
            rawQuery.close();
        }
        new c(this.b).a(aVar, aVar2, sQLiteDatabase);
    }

    public void a(@NonNull c cVar, @NonNull a aVar) {
        ContentValues b = b(cVar, aVar);
        if (a(b, a("%s = ?", "id"), new String[]{cVar.a()}) == 0) {
            a(b);
        }
    }

    public int b(@NonNull String str) {
        return a(a("%s = ?", "id"), new String[]{str});
    }

    /* access modifiers changed from: 0000 */
    public String c() {
        return ResponseConstants.MESSAGES;
    }
}
