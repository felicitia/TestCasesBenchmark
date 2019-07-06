package com.salesforce.marketingcloud.d.a;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import com.etsy.android.lib.models.ResponseConstants;
import com.salesforce.marketingcloud.d.k;
import com.salesforce.marketingcloud.e.a;
import com.salesforce.marketingcloud.j;
import com.salesforce.marketingcloud.location.b;
import com.salesforce.marketingcloud.messages.e;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public final class g extends b implements k {
    public static final String[] a = {"id", ResponseConstants.LATITUDE, ResponseConstants.LONGITUDE, ResponseConstants.RADIUS, "beacon_guid", "beacon_major", "beacon_minor", "description", ResponseConstants.NAME, "location_type", "is_inside"};
    private static final String c = j.a(g.class);

    public g(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }

    private static e a(Cursor cursor, @NonNull a aVar) {
        try {
            e.a k = e.k();
            k.a(cursor.getString(cursor.getColumnIndex("id")));
            k.a(b.a(Double.valueOf(aVar.b(cursor.getString(cursor.getColumnIndex(ResponseConstants.LATITUDE)))).doubleValue(), Double.valueOf(aVar.b(cursor.getString(cursor.getColumnIndex(ResponseConstants.LONGITUDE)))).doubleValue()));
            k.a(cursor.getInt(cursor.getColumnIndex(ResponseConstants.RADIUS)));
            k.b(aVar.b(cursor.getString(cursor.getColumnIndex("beacon_guid"))));
            k.b(cursor.getInt(cursor.getColumnIndex("beacon_major")));
            k.c(cursor.getInt(cursor.getColumnIndex("beacon_minor")));
            k.d(cursor.getInt(cursor.getColumnIndex("location_type")));
            k.c(aVar.b(cursor.getString(cursor.getColumnIndex(ResponseConstants.NAME))));
            k.d(aVar.b(cursor.getString(cursor.getColumnIndex("description"))));
            e a2 = k.a();
            boolean z = true;
            if (cursor.getInt(cursor.getColumnIndex("is_inside")) != 1) {
                z = false;
            }
            a2.a(z);
            return a2;
        } catch (Exception e) {
            j.c(c, e, "Unable to read region from DB", new Object[0]);
            return null;
        }
    }

    private static String a(String str, Object... objArr) {
        return String.format(Locale.ENGLISH, str, objArr);
    }

    private static ContentValues b(e eVar, @NonNull a aVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", eVar.a());
        contentValues.put(ResponseConstants.LATITUDE, aVar.a(String.valueOf(eVar.b().a())));
        contentValues.put(ResponseConstants.LONGITUDE, aVar.a(String.valueOf(eVar.b().b())));
        contentValues.put(ResponseConstants.RADIUS, Integer.valueOf(eVar.c()));
        contentValues.put("beacon_guid", aVar.a(eVar.d()));
        contentValues.put("beacon_major", Integer.valueOf(eVar.e()));
        contentValues.put("beacon_minor", Integer.valueOf(eVar.f()));
        contentValues.put("description", aVar.a(eVar.i()));
        contentValues.put(ResponseConstants.NAME, aVar.a(eVar.h()));
        contentValues.put("location_type", Integer.valueOf(eVar.g()));
        contentValues.put("is_inside", Integer.valueOf(eVar.l() ? 1 : 0));
        return contentValues;
    }

    public int a() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("is_inside", Integer.valueOf(0));
        return a(contentValues, (String) null, (String[]) null);
    }

    public int a(@NonNull String str, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("is_inside", Integer.valueOf(z ? 1 : 0));
        return a(contentValues, a("%s = ?", "id"), new String[]{str});
    }

    public e a(@NonNull a aVar) {
        Cursor a2 = a(a, a("%s = ?", "id"), new String[]{"~~m@g1c_f3nc3~~"}, null, null, null, "1");
        e eVar = null;
        if (a2 != null) {
            if (a2.moveToFirst()) {
                try {
                    eVar = new e.b(b.a(Double.valueOf(aVar.b(a2.getString(a2.getColumnIndex(ResponseConstants.LATITUDE)))).doubleValue(), Double.valueOf(aVar.b(a2.getString(a2.getColumnIndex(ResponseConstants.LONGITUDE)))).doubleValue()), a2.getInt(a2.getColumnIndex(ResponseConstants.RADIUS)));
                } catch (Exception e) {
                    j.c(c, e, "Unable to read magic region from DB.", new Object[0]);
                }
            }
            a2.close();
        }
        return eVar;
    }

    public e a(String str, @NonNull a aVar) {
        Cursor a2 = a(a, a("%s = ?", "id"), new String[]{str}, null, null, null, "1");
        e eVar = null;
        if (a2 != null) {
            if (a2.moveToFirst()) {
                eVar = a(a2, aVar);
            }
            a2.close();
        }
        return eVar;
    }

    @NonNull
    public List<String> a(int i) {
        List<String> emptyList = Collections.emptyList();
        Cursor a2 = a(new String[]{"id"}, a("%s = ?", "location_type"), new String[]{String.valueOf(i)});
        if (a2 != null) {
            if (a2.moveToFirst()) {
                List<String> arrayList = new ArrayList<>();
                int columnIndex = a2.getColumnIndex("id");
                do {
                    arrayList.add(a2.getString(columnIndex));
                } while (a2.moveToNext());
                emptyList = arrayList;
            }
            a2.close();
        }
        return emptyList;
    }

    @NonNull
    public List<e> a(int i, a aVar) {
        List<e> emptyList = Collections.emptyList();
        Cursor a2 = a(a, a("%s = ?", "location_type"), new String[]{String.valueOf(i)});
        if (a2 != null) {
            if (a2.moveToFirst()) {
                List<e> arrayList = new ArrayList<>(a2.getCount());
                do {
                    e a3 = a(a2, aVar);
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

    public void a(@NonNull e eVar, @NonNull a aVar) {
        ContentValues b = b(eVar, aVar);
        if (a(b, a("%s = ?", "id"), new String[]{eVar.a()}) == 0) {
            a(b);
        }
    }

    @NonNull
    public List<String> b(int i) {
        List<String> emptyList = Collections.emptyList();
        Cursor a2 = a(new String[]{"id"}, a("%s = ? AND %s = ?", "location_type", "is_inside"), new String[]{String.valueOf(i), "1"});
        if (a2 != null) {
            if (a2.moveToFirst()) {
                List<String> arrayList = new ArrayList<>(a2.getCount());
                int columnIndex = a2.getColumnIndex("id");
                do {
                    arrayList.add(a2.getString(columnIndex));
                } while (a2.moveToNext());
                emptyList = arrayList;
            }
            a2.close();
        }
        return emptyList;
    }

    public int c(int i) {
        return a(a("%s = ?", "location_type"), new String[]{String.valueOf(i)});
    }

    /* access modifiers changed from: 0000 */
    public String c() {
        return "regions";
    }
}
