package com.salesforce.marketingcloud.d.a;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.models.ResponseConstants;
import com.salesforce.marketingcloud.d.g;
import com.salesforce.marketingcloud.e.a;
import com.salesforce.marketingcloud.j;
import com.salesforce.marketingcloud.location.b;
import java.util.Locale;

public final class e extends b implements g {
    public static final String[] a = {"id", ResponseConstants.LATITUDE, ResponseConstants.LONGITUDE};
    private static final String c = j.a(e.class);

    public e(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }

    public int a() {
        return a((String) null);
    }

    @Nullable
    public b a(@NonNull a aVar) {
        Cursor a2 = a(a, String.format(Locale.ENGLISH, "%s = ?", new Object[]{"id"}), new String[]{"0"});
        b bVar = null;
        if (a2 != null) {
            if (a2.moveToFirst()) {
                try {
                    bVar = b.a(Double.valueOf(aVar.b(a2.getString(a2.getColumnIndex(ResponseConstants.LATITUDE)))).doubleValue(), Double.valueOf(aVar.b(a2.getString(a2.getColumnIndex(ResponseConstants.LONGITUDE)))).doubleValue());
                } catch (Exception e) {
                    j.c(c, e, "Unable to read location from database.", new Object[0]);
                }
            }
            a2.close();
        }
        return bVar;
    }

    public void a(@NonNull b bVar, @NonNull a aVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Integer.valueOf(0));
        contentValues.put(ResponseConstants.LATITUDE, aVar.a(Double.toString(bVar.a())));
        contentValues.put(ResponseConstants.LONGITUDE, aVar.a(Double.toString(bVar.b())));
        if (a(contentValues, String.format(Locale.ENGLISH, "%s = ?", new Object[]{"id"}), new String[]{"0"}) == 0) {
            a(contentValues);
        }
    }

    /* access modifiers changed from: 0000 */
    public String c() {
        return "location_table";
    }
}
