package com.salesforce.marketingcloud.d.a;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.salesforce.marketingcloud.analytics.e;
import com.salesforce.marketingcloud.d.a.b.b;
import com.salesforce.marketingcloud.e.g;
import com.salesforce.marketingcloud.j;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;

public final class a extends b implements com.salesforce.marketingcloud.d.a {
    public static final String[] a = {"id", "event_date", "analytic_product_type", "analytic_type", ResponseConstants.VALUE, "ready_to_send", "object_ids", "json_payload", "request_id"};
    private static final String c = j.a(a.class);

    public a(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }

    public static String a(String str, Object... objArr) {
        return String.format(Locale.ENGLISH, str, objArr);
    }

    private List<e> a(int i, @Nullable com.salesforce.marketingcloud.e.a aVar) {
        String a2 = a("(%1$s=? OR %1$s=?) AND %2$s=? AND %3$s=? AND %4$s=?", "analytic_type", "analytic_product_type", ResponseConstants.VALUE, "ready_to_send");
        String[] strArr = {String.valueOf(4), String.valueOf(5), String.valueOf(i), String.valueOf(0), String.valueOf(0)};
        return a(a(a, a2, strArr, null, null, a("%s ASC", "id")), aVar);
    }

    private void a(int i, int i2, int i3) {
        a(a("%s IN ( SELECT %s FROM %s WHERE %s=%d ORDER BY %s ASC LIMIT %d )", "id", "id", "analytic_item", "analytic_product_type", Integer.valueOf(i3), "id", Integer.valueOf((i + 1) - i2)));
    }

    private static e b(Cursor cursor, @Nullable com.salesforce.marketingcloud.e.a aVar) {
        e eVar;
        try {
            int i = cursor.getInt(cursor.getColumnIndex("analytic_type"));
            int i2 = cursor.getInt(cursor.getColumnIndex("analytic_product_type")) == 0 ? 0 : 1;
            String string = cursor.getString(cursor.getColumnIndex("request_id"));
            Date a2 = g.a(cursor.getString(cursor.getColumnIndex("event_date")));
            boolean z = cursor.getInt(cursor.getColumnIndex("ready_to_send")) == 1;
            List emptyList = Collections.emptyList();
            JSONArray jSONArray = new JSONArray(cursor.getString(cursor.getColumnIndex("object_ids")));
            if (jSONArray.length() > 0) {
                emptyList = new ArrayList();
                for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                    emptyList.add(jSONArray.getString(i3));
                }
            }
            List list = emptyList;
            if (!TextUtils.isEmpty(string)) {
                eVar = e.a(a2, i2, i, list, string, z);
            } else if (list.size() > 0) {
                eVar = e.a(a2, i2, i, list, z);
            } else {
                eVar = e.a(a2, i2, i);
                eVar.a(z);
            }
            eVar.a(cursor.getInt(cursor.getColumnIndex("id")));
            eVar.b(cursor.getInt(cursor.getColumnIndex(ResponseConstants.VALUE)));
            if (i2 == 1 && aVar != null) {
                String string2 = cursor.getString(cursor.getColumnIndex("json_payload"));
                if (!TextUtils.isEmpty(string2)) {
                    eVar.a(aVar.b(string2));
                    return eVar;
                }
            }
        } catch (Exception e) {
            j.c(c, e, "Failed to create our analytic item from storage.", new Object[0]);
            eVar = null;
        }
        return eVar;
    }

    private static ContentValues c(@NonNull e eVar, @NonNull com.salesforce.marketingcloud.e.a aVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("event_date", g.a(eVar.b()));
        contentValues.put("analytic_product_type", Integer.valueOf(eVar.c()));
        contentValues.put("analytic_type", Integer.valueOf(eVar.d()));
        contentValues.put(ResponseConstants.VALUE, Integer.valueOf(eVar.e()));
        contentValues.put("ready_to_send", Integer.valueOf(eVar.g() ? 1 : 0));
        contentValues.put("object_ids", new JSONArray(eVar.f()).toString());
        if (eVar.c() == 1) {
            contentValues.put("json_payload", aVar.a(eVar.h()));
        } else {
            contentValues.put("json_payload", "");
        }
        contentValues.put("request_id", eVar.i());
        return contentValues;
    }

    private int f(int i) {
        return (int) DatabaseUtils.queryNumEntries(this.b, "analytic_item", a("%s=%s", "analytic_product_type", Integer.valueOf(i)));
    }

    public int a(int i) {
        return a(a("%s = ?", "id"), new String[]{String.valueOf(i)});
    }

    @NonNull
    public List<e> a() {
        return a(a(a, a("%s=? AND %s=?", "analytic_product_type", "ready_to_send"), new String[]{String.valueOf(0), "1"}, null, null, a("%s ASC", "id")), (com.salesforce.marketingcloud.e.a) null);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public List<e> a(Cursor cursor, @Nullable com.salesforce.marketingcloud.e.a aVar) {
        List<e> emptyList = Collections.emptyList();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                List<e> arrayList = new ArrayList<>();
                do {
                    e b = b(cursor, aVar);
                    if (b != null) {
                        arrayList.add(b);
                    } else {
                        int i = cursor.getInt(cursor.getColumnIndex("id"));
                        if (i >= 0) {
                            a(i);
                        }
                    }
                } while (cursor.moveToNext());
                emptyList = arrayList;
            }
            cursor.close();
        }
        return emptyList;
    }

    @NonNull
    public List<e> a(@NonNull com.salesforce.marketingcloud.e.a aVar) {
        String a2 = a("(%1$s=? OR %1$s=? OR %1$s=?) AND %2$s=? AND %3$s=?", "analytic_type", "analytic_product_type", "ready_to_send");
        String[] strArr = {String.valueOf(888), String.valueOf(8888), String.valueOf(88888), String.valueOf(1), String.valueOf(1)};
        return a(a(a, a2, strArr, null, null, a("%s ASC", "event_date")), aVar);
    }

    @NonNull
    public List<e> a(@NonNull com.salesforce.marketingcloud.messages.e eVar, @NonNull com.salesforce.marketingcloud.e.a aVar) {
        return a(a(a, a("(%1$s=? OR %1$s=?) AND %2$s LIKE ? AND %3$s=?", "analytic_type", "object_ids", "ready_to_send"), new String[]{String.valueOf(13), String.valueOf(11), a("%%%s%%", eVar.a()), String.valueOf(0)}), aVar);
    }

    public void a(e eVar, @NonNull com.salesforce.marketingcloud.e.a aVar) {
        int i = eVar.c() == 0 ? 0 : 1;
        int f = f(i);
        if (f + 1 > 1000) {
            a(f, 1000, i);
        }
        eVar.a((int) a(c(eVar, aVar)));
    }

    public void a(com.salesforce.marketingcloud.e.a aVar, com.salesforce.marketingcloud.e.a aVar2, SQLiteDatabase sQLiteDatabase) {
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        Cursor query = sQLiteDatabase2.query("analytic_item", new String[]{"id", "event_date", "analytic_types", "object_ids", ResponseConstants.VALUE, "ready_to_send", "pi_app_key", "json_payload"}, null, null, null, null, null);
        if (query != null) {
            if (query.moveToFirst()) {
                do {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("id", Integer.valueOf(query.getInt(query.getColumnIndex("id"))));
                    contentValues.put("event_date", query.getString(query.getColumnIndex("event_date")));
                    contentValues.put("analytic_product_type", Integer.valueOf(TextUtils.isEmpty(query.getString(query.getColumnIndex("pi_app_key"))) ^ true ? 1 : 0));
                    contentValues.put("analytic_type", Integer.valueOf(b.a(query)));
                    contentValues.put("object_ids", query.getString(query.getColumnIndex("object_ids")));
                    contentValues.put(ResponseConstants.VALUE, query.getString(query.getColumnIndex(ResponseConstants.VALUE)));
                    contentValues.put("ready_to_send", Integer.valueOf(query.getInt(query.getColumnIndex("ready_to_send"))));
                    contentValues.put("json_payload", aVar2.a(aVar.b(query.getString(query.getColumnIndex("json_payload")))));
                    a(contentValues);
                } while (query.moveToNext());
            }
            query.close();
        }
    }

    public int b(int i) {
        return a(a("%s = ?", "analytic_product_type"), new String[]{String.valueOf(i)});
    }

    public int b(e eVar, @NonNull com.salesforce.marketingcloud.e.a aVar) {
        return a(c(eVar, aVar), a("%s = ?", "id"), new String[]{String.valueOf(eVar.a())});
    }

    @NonNull
    public List<e> b() {
        return a(0, (com.salesforce.marketingcloud.e.a) null);
    }

    @NonNull
    public List<e> b(@NonNull com.salesforce.marketingcloud.e.a aVar) {
        String a2 = a("(%1$s=? OR %1$s=? OR %1$s=? OR %1$s=? OR %1$s=? OR %1$s=? OR %1$s=? OR %1$s=? OR %1$s=? OR %1$s=?) AND %2$s=? AND %3$s=?", "analytic_type", "analytic_product_type", "ready_to_send");
        String[] strArr = {String.valueOf(2), String.valueOf(3), String.valueOf(4), String.valueOf(5), String.valueOf(6), String.valueOf(7), String.valueOf(10), String.valueOf(11), String.valueOf(12), String.valueOf(13), String.valueOf(1), String.valueOf(1)};
        return a(a(a, a2, strArr, null, null, a("%s ASC", "event_date")), aVar);
    }

    public int c(int i) {
        return a(a("%s = ? AND %s IN (%s)", "analytic_product_type", "analytic_type", TextUtils.join(",", e.a)), new String[]{String.valueOf(i)});
    }

    /* access modifiers changed from: 0000 */
    public String c() {
        return "analytic_item";
    }

    @NonNull
    public List<e> c(@NonNull com.salesforce.marketingcloud.e.a aVar) {
        return a(1, aVar);
    }

    public int d(int i) {
        return a(a("%s = ? AND %s NOT IN (%s)", "analytic_product_type", "analytic_type", TextUtils.join(",", e.a)), new String[]{String.valueOf(i)});
    }

    @NonNull
    public List<e> d(@NonNull com.salesforce.marketingcloud.e.a aVar) {
        return a(a(a, a("(%1$s=? OR %1$s=?) AND %2$s=?", "analytic_type", "ready_to_send"), new String[]{String.valueOf(13), String.valueOf(11), String.valueOf(0)}), aVar);
    }

    public boolean e(int i) {
        return DatabaseUtils.queryNumEntries(this.b, c(), a("(%1$s=? OR %1$s=?) AND %2$s=? AND %3$s=? AND %4$s=?", "analytic_type", "analytic_product_type", ResponseConstants.VALUE, "ready_to_send"), new String[]{String.valueOf(4), String.valueOf(5), String.valueOf(i), String.valueOf(0), String.valueOf(0)}) > 0;
    }
}
