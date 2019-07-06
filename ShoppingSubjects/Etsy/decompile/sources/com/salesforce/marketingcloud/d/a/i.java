package com.salesforce.marketingcloud.d.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.editable.EditableListing;
import com.salesforce.marketingcloud.d.l;
import com.salesforce.marketingcloud.e.a;
import com.salesforce.marketingcloud.e.g;
import com.salesforce.marketingcloud.registration.c;
import java.util.Locale;

public final class i extends b implements l {
    @VisibleForTesting
    public static final String[] a = {"id", ResponseConstants.PLATFORM, "subscriber_key", "et_app_id", "timezone", "dst", EditableListing.FIELD_TAGS, ResponseConstants.ATTRIBUTES, ResponseConstants.PLATFORM_VERSION, "push_enabled", "location_enabled", "proximity_enabled", "hwid", "system_token", "device_id", "app_version", "sdk_version", "signed_string", "locale"};
    private final Context c;

    public i(SQLiteDatabase sQLiteDatabase, Context context) {
        super(sQLiteDatabase);
        this.c = context;
    }

    private static c a(Cursor cursor, @NonNull a aVar) {
        boolean z = false;
        c.a a2 = c.s().j(aVar.b(cursor.getString(cursor.getColumnIndex("et_app_id")))).a(g.c(aVar.b(cursor.getString(cursor.getColumnIndex(ResponseConstants.ATTRIBUTES))))).a(g.d(aVar.b(cursor.getString(cursor.getColumnIndex(EditableListing.FIELD_TAGS))))).g(aVar.b(cursor.getString(cursor.getColumnIndex("subscriber_key")))).a(aVar.b(cursor.getString(cursor.getColumnIndex("signed_string")))).c(aVar.b(cursor.getString(cursor.getColumnIndex("system_token")))).b(cursor.getString(cursor.getColumnIndex("device_id"))).d(cursor.getInt(cursor.getColumnIndex("push_enabled")) == 1).b(cursor.getInt(cursor.getColumnIndex("location_enabled")) == 1).c(cursor.getInt(cursor.getColumnIndex("proximity_enabled")) == 1).k(cursor.getString(cursor.getColumnIndex("locale"))).a(cursor.getInt(cursor.getColumnIndex("timezone")));
        if (cursor.getInt(cursor.getColumnIndex("dst")) == 1) {
            z = true;
        }
        c b = a2.a(z).i(cursor.getString(cursor.getColumnIndex("hwid"))).h(cursor.getString(cursor.getColumnIndex(ResponseConstants.PLATFORM))).f(cursor.getString(cursor.getColumnIndex(ResponseConstants.PLATFORM_VERSION))).e(cursor.getString(cursor.getColumnIndex("app_version"))).d(cursor.getString(cursor.getColumnIndex("sdk_version"))).b();
        b.a(cursor.getInt(cursor.getColumnIndex("id")));
        return b;
    }

    private static String a(String str, Object... objArr) {
        return String.format(Locale.ENGLISH, str, objArr);
    }

    private static ContentValues c(c cVar, @NonNull a aVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("subscriber_key", aVar.a(cVar.l()));
        contentValues.put("signed_string", aVar.a(cVar.a()));
        contentValues.put("et_app_id", aVar.a(cVar.o()));
        contentValues.put("system_token", aVar.a(cVar.c()));
        contentValues.put(EditableListing.FIELD_TAGS, aVar.a(g.a(cVar.q())));
        contentValues.put(ResponseConstants.ATTRIBUTES, aVar.a(g.a(cVar.r())));
        contentValues.put("device_id", cVar.b());
        contentValues.put(ResponseConstants.PLATFORM, cVar.m());
        contentValues.put("timezone", Integer.valueOf(cVar.k()));
        contentValues.put("dst", Integer.valueOf(cVar.f() ? 1 : 0));
        contentValues.put(ResponseConstants.PLATFORM_VERSION, cVar.i());
        contentValues.put("push_enabled", Integer.valueOf(cVar.j() ? 1 : 0));
        contentValues.put("location_enabled", Integer.valueOf(cVar.g() ? 1 : 0));
        contentValues.put("proximity_enabled", Integer.valueOf(cVar.h() ? 1 : 0));
        contentValues.put("hwid", cVar.n());
        contentValues.put("locale", cVar.p());
        contentValues.put("app_version", cVar.e());
        contentValues.put("sdk_version", cVar.d());
        return contentValues;
    }

    public int a() {
        return a(a("%1$s NOT IN ( SELECT %1$s FROM ( SELECT %1$s FROM %2$s ORDER BY %1$s DESC LIMIT 1))", "id", c()));
    }

    @Nullable
    public c a(@NonNull a aVar) {
        Cursor a2 = a(a, null, null, null, null, a("%s DESC", "id"), "1");
        c cVar = null;
        if (a2 != null) {
            if (a2.moveToFirst()) {
                cVar = a(a2, aVar);
            }
            a2.close();
        }
        return cVar;
    }

    public void a(c cVar, @NonNull a aVar) {
        cVar.a((int) a(c(cVar, aVar)));
    }

    public int b() {
        return a((String) null);
    }

    public int b(c cVar, @NonNull a aVar) {
        return a(c(cVar, aVar), a("%s = ?", "id"), new String[]{String.valueOf(cVar.t())});
    }

    /* access modifiers changed from: 0000 */
    public String c() {
        return "registration";
    }
}
