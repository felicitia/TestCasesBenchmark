package com.salesforce.marketingcloud.d.a;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.ShopHomeSortOption;
import com.salesforce.marketingcloud.d.a.a.a.d;
import com.salesforce.marketingcloud.d.f;
import com.salesforce.marketingcloud.e.g;
import com.salesforce.marketingcloud.j;
import com.salesforce.marketingcloud.messages.c.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@RestrictTo({Scope.LIBRARY})
public final class c extends b implements f {
    public static final String[] a = {"id", "start_date", "end_date", "message_type", "content_type", "url", ResponseConstants.SUBJECT, "read", "message_deleted", ShopHomeSortOption.SORT_CUSTOM, "keys", "title", "alert", "sound", "mediaUrl", "mediaAlt", "message_hash", "request_id"};
    private static final String c = j.a(c.class);

    public c(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|(1:4)|5|6|7|8|9|10|11|12|13|(1:15)(1:16)|17|(1:19)(1:20)|21|22) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0105 */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0116 A[Catch:{ Exception -> 0x012e }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0118 A[Catch:{ Exception -> 0x012e }] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0128 A[Catch:{ Exception -> 0x012e }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0129 A[Catch:{ Exception -> 0x012e }] */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.salesforce.marketingcloud.messages.c.a a(@android.support.annotation.NonNull android.database.Cursor r5, @android.support.annotation.NonNull com.salesforce.marketingcloud.e.a r6) {
        /*
            r0 = 0
            r1 = 0
            com.salesforce.marketingcloud.messages.a.b$b r2 = com.salesforce.marketingcloud.messages.a.b.q()     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = "id"
            int r3 = r5.getColumnIndex(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = r5.getString(r3)     // Catch:{ Exception -> 0x0130 }
            r2.h(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = "start_date"
            int r3 = r5.getColumnIndex(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = r5.getString(r3)     // Catch:{ Exception -> 0x0130 }
            java.util.Date r3 = com.salesforce.marketingcloud.e.g.a(r3)     // Catch:{ Exception -> 0x0130 }
            r2.a(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = "end_date"
            int r3 = r5.getColumnIndex(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = r5.getString(r3)     // Catch:{ Exception -> 0x0130 }
            java.util.Date r3 = com.salesforce.marketingcloud.e.g.a(r3)     // Catch:{ Exception -> 0x0130 }
            r2.b(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = "message_type"
            int r3 = r5.getColumnIndex(r3)     // Catch:{ Exception -> 0x0130 }
            int r3 = r5.getInt(r3)     // Catch:{ Exception -> 0x0130 }
            r2.a(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = "content_type"
            int r3 = r5.getColumnIndex(r3)     // Catch:{ Exception -> 0x0130 }
            int r3 = r5.getInt(r3)     // Catch:{ Exception -> 0x0130 }
            r2.b(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = "url"
            int r3 = r5.getColumnIndex(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = r5.getString(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = r6.b(r3)     // Catch:{ Exception -> 0x0130 }
            r2.i(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = "subject"
            int r3 = r5.getColumnIndex(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = r5.getString(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = r6.b(r3)     // Catch:{ Exception -> 0x0130 }
            r2.c(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = "custom"
            int r3 = r5.getColumnIndex(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = r5.getString(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = r6.b(r3)     // Catch:{ Exception -> 0x0130 }
            r2.d(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = "keys"
            int r3 = r5.getColumnIndex(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = r5.getString(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = r6.b(r3)     // Catch:{ Exception -> 0x0130 }
            if (r3 == 0) goto L_0x0099
            java.util.Map r3 = com.salesforce.marketingcloud.e.g.c(r3)     // Catch:{ Exception -> 0x0130 }
            r2.a(r3)     // Catch:{ Exception -> 0x0130 }
        L_0x0099:
            java.lang.String r3 = "message_hash"
            int r3 = r5.getColumnIndex(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = r5.getString(r3)     // Catch:{ Exception -> 0x0130 }
            r2.b(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = "request_id"
            int r3 = r5.getColumnIndex(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = r5.getString(r3)     // Catch:{ Exception -> 0x0130 }
            r2.a(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = "title"
            int r3 = r5.getColumnIndex(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = r5.getString(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = r6.b(r3)     // Catch:{ Exception -> 0x0130 }
            r2.e(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = "alert"
            int r3 = r5.getColumnIndex(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = r5.getString(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = r6.b(r3)     // Catch:{ Exception -> 0x0130 }
            r2.f(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = "sound"
            int r3 = r5.getColumnIndex(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = r5.getString(r3)     // Catch:{ Exception -> 0x0130 }
            r2.g(r3)     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = "mediaUrl"
            int r3 = r5.getColumnIndex(r3)     // Catch:{ IllegalStateException -> 0x0105 }
            java.lang.String r3 = r5.getString(r3)     // Catch:{ IllegalStateException -> 0x0105 }
            java.lang.String r3 = r6.b(r3)     // Catch:{ IllegalStateException -> 0x0105 }
            java.lang.String r4 = "mediaAlt"
            int r4 = r5.getColumnIndex(r4)     // Catch:{ IllegalStateException -> 0x0105 }
            java.lang.String r4 = r5.getString(r4)     // Catch:{ IllegalStateException -> 0x0105 }
            java.lang.String r6 = r6.b(r4)     // Catch:{ IllegalStateException -> 0x0105 }
            com.salesforce.marketingcloud.messages.a.b$a r6 = com.salesforce.marketingcloud.messages.a.b.a.a(r3, r6)     // Catch:{ IllegalStateException -> 0x0105 }
            r2.a(r6)     // Catch:{ IllegalStateException -> 0x0105 }
        L_0x0105:
            com.salesforce.marketingcloud.messages.a.b r6 = r2.a()     // Catch:{ Exception -> 0x0130 }
            java.lang.String r1 = "read"
            int r1 = r5.getColumnIndex(r1)     // Catch:{ Exception -> 0x012e }
            int r1 = r5.getInt(r1)     // Catch:{ Exception -> 0x012e }
            r2 = 1
            if (r1 != r2) goto L_0x0118
            r1 = r2
            goto L_0x0119
        L_0x0118:
            r1 = r0
        L_0x0119:
            r6.a(r1)     // Catch:{ Exception -> 0x012e }
            java.lang.String r1 = "message_deleted"
            int r1 = r5.getColumnIndex(r1)     // Catch:{ Exception -> 0x012e }
            int r5 = r5.getInt(r1)     // Catch:{ Exception -> 0x012e }
            if (r5 != r2) goto L_0x0129
            goto L_0x012a
        L_0x0129:
            r2 = r0
        L_0x012a:
            r6.b(r2)     // Catch:{ Exception -> 0x012e }
            return r6
        L_0x012e:
            r5 = move-exception
            goto L_0x0132
        L_0x0130:
            r5 = move-exception
            r6 = r1
        L_0x0132:
            java.lang.String r1 = c
            java.lang.String r2 = "Failed to hydrate a CloudPageMessage from our local storage."
            java.lang.Object[] r0 = new java.lang.Object[r0]
            com.salesforce.marketingcloud.j.c(r1, r5, r2, r0)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.d.a.c.a(android.database.Cursor, com.salesforce.marketingcloud.e.a):com.salesforce.marketingcloud.messages.c.a");
    }

    private static String a(String str, Object... objArr) {
        return String.format(Locale.ENGLISH, str, objArr);
    }

    @NonNull
    private static List<a> b(@NonNull Cursor cursor, @NonNull com.salesforce.marketingcloud.e.a aVar) {
        List<a> emptyList = Collections.emptyList();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                List<a> arrayList = new ArrayList<>();
                do {
                    a a2 = a(cursor, aVar);
                    if (a2 != null) {
                        arrayList.add(a2);
                    }
                } while (cursor.moveToNext());
                emptyList = arrayList;
            }
            cursor.close();
        }
        return emptyList;
    }

    private static ContentValues c(a aVar, @NonNull com.salesforce.marketingcloud.e.a aVar2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", aVar.j());
        contentValues.put("start_date", g.a(aVar.k()));
        contentValues.put("end_date", g.a(aVar.l()));
        contentValues.put("message_type", Integer.valueOf(aVar.m()));
        contentValues.put("content_type", Integer.valueOf(aVar.n()));
        contentValues.put("url", aVar2.a(aVar.o()));
        contentValues.put(ResponseConstants.SUBJECT, aVar2.a(aVar.c()));
        contentValues.put("read", Integer.valueOf(aVar.r() ? 1 : 0));
        contentValues.put("message_deleted", Integer.valueOf(aVar.s() ? 1 : 0));
        contentValues.put(ShopHomeSortOption.SORT_CUSTOM, aVar2.a(aVar.e()));
        contentValues.put("keys", aVar2.a(g.a(aVar.d())));
        contentValues.put("title", aVar2.a(aVar.f()));
        contentValues.put("alert", aVar2.a(aVar.g()));
        contentValues.put("sound", aVar.h());
        if (aVar.p() != null) {
            contentValues.put("mediaUrl", aVar2.a(aVar.p().a()));
            contentValues.put("mediaAlt", aVar2.a(aVar.p().b()));
        }
        contentValues.put("message_hash", aVar.b());
        contentValues.put("request_id", aVar.a());
        return contentValues;
    }

    public int a() {
        return a((String) null);
    }

    @Nullable
    public a a(@NonNull String str, @NonNull com.salesforce.marketingcloud.e.a aVar) {
        Cursor a2 = a(a, a("%s = ?", "id"), new String[]{str}, null, null, null, "1");
        a aVar2 = null;
        if (a2 != null) {
            if (a2.moveToFirst()) {
                aVar2 = a(a2, aVar);
            }
            a2.close();
        }
        return aVar2;
    }

    @NonNull
    public List<a> a(@NonNull com.salesforce.marketingcloud.e.a aVar) {
        String[] strArr = {String.valueOf(8), String.valueOf(2)};
        return b(a(a, a("%s=? AND %s=?", "message_type", "content_type"), strArr), aVar);
    }

    public void a(com.salesforce.marketingcloud.e.a aVar, com.salesforce.marketingcloud.e.a aVar2, SQLiteDatabase sQLiteDatabase) {
        com.salesforce.marketingcloud.e.a aVar3 = aVar;
        com.salesforce.marketingcloud.e.a aVar4 = aVar2;
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        Cursor query = sQLiteDatabase2.query(ResponseConstants.MESSAGES, d.a, a("%s=? AND %s=?", "content_type", "message_type"), new String[]{String.valueOf(2), String.valueOf(1)}, null, null, null);
        if (query != null) {
            if (query.moveToFirst()) {
                do {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(ResponseConstants.SUBJECT, aVar4.a(aVar3.b(query.getString(query.getColumnIndex(ResponseConstants.SUBJECT)))));
                    contentValues.put("url", aVar4.a(aVar3.b(query.getString(query.getColumnIndex("url")))));
                    contentValues.put("id", query.getString(query.getColumnIndex("id")));
                    contentValues.put("content_type", Integer.valueOf(query.getInt(query.getColumnIndex("content_type"))));
                    contentValues.put("message_type", Integer.valueOf(query.getInt(query.getColumnIndex("message_type"))));
                    contentValues.put("start_date", query.getString(query.getColumnIndex("start_date")));
                    contentValues.put("end_date", query.getString(query.getColumnIndex("end_date")));
                    contentValues.put("read", Integer.valueOf(query.getInt(query.getColumnIndex("read"))));
                    contentValues.put("message_deleted", Integer.valueOf(query.getInt(query.getColumnIndex("message_deleted"))));
                    contentValues.put(ShopHomeSortOption.SORT_CUSTOM, aVar4.a(query.getString(query.getColumnIndex(ShopHomeSortOption.SORT_CUSTOM))));
                    contentValues.put("keys", aVar4.a(aVar3.b(query.getString(query.getColumnIndex("keys")))));
                    contentValues.put("alert", aVar4.a(aVar3.b(query.getString(query.getColumnIndex("alert")))));
                    contentValues.put("sound", query.getString(query.getColumnIndex("sound")));
                    contentValues.put("message_hash", "nohash");
                    a(contentValues);
                } while (query.moveToNext());
            }
            query.close();
        }
        new d(this.b).a(aVar3, aVar4, sQLiteDatabase);
    }

    public void a(@NonNull a aVar, @NonNull com.salesforce.marketingcloud.e.a aVar2) {
        ContentValues c2 = c(aVar, aVar2);
        if (a(c2, a("%s = ?", "id"), new String[]{aVar.j()}) == 0) {
            a(c2);
        }
    }

    public int b(@NonNull a aVar, @NonNull com.salesforce.marketingcloud.e.a aVar2) {
        return a(c(aVar, aVar2), a("%s = ?", "id"), new String[]{aVar.j()});
    }

    /* access modifiers changed from: 0000 */
    public String c() {
        return "cloud_page_messages";
    }
}
