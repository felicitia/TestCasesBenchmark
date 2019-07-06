package com.salesforce.marketingcloud.d.a;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public abstract class b {
    protected final SQLiteDatabase b;

    public b(SQLiteDatabase sQLiteDatabase) {
        this.b = sQLiteDatabase;
    }

    /* access modifiers changed from: protected */
    public final int a(ContentValues contentValues, String str, String[] strArr) {
        return this.b.update(c(), contentValues, str, strArr);
    }

    /* access modifiers changed from: protected */
    public final int a(String str) {
        return a(str, (String[]) null);
    }

    /* access modifiers changed from: protected */
    public final int a(String str, String[] strArr) {
        return this.b.delete(c(), str, strArr);
    }

    /* access modifiers changed from: protected */
    public final long a(ContentValues contentValues) {
        return a((String) null, contentValues);
    }

    /* access modifiers changed from: protected */
    public final long a(String str, ContentValues contentValues) {
        return this.b.insert(c(), str, contentValues);
    }

    /* access modifiers changed from: protected */
    public final Cursor a(boolean z, String[] strArr, String str, String[] strArr2, String str2, String str3, String str4, String str5) {
        return this.b.query(z, c(), strArr, str, strArr2, str2, str3, str4, str5);
    }

    /* access modifiers changed from: protected */
    public final Cursor a(String[] strArr, String str, String[] strArr2) {
        return a(strArr, str, strArr2, null, null, null, null);
    }

    /* access modifiers changed from: protected */
    public final Cursor a(String[] strArr, String str, String[] strArr2, String str2, String str3, String str4) {
        return a(false, strArr, str, strArr2, str2, str3, str4, null);
    }

    /* access modifiers changed from: protected */
    public final Cursor a(String[] strArr, String str, String[] strArr2, String str2, String str3, String str4, String str5) {
        return a(false, strArr, str, strArr2, str2, str3, str4, str5);
    }

    /* access modifiers changed from: 0000 */
    public abstract String c();
}
