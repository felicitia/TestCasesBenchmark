package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import org.json.JSONObject;

@bu
public abstract class akb<T> {
    private final int a;
    private final String b;
    private final T c;

    private akb(int i, String str, T t) {
        this.a = i;
        this.b = str;
        this.c = t;
        ajh.e().a(this);
    }

    /* synthetic */ akb(int i, String str, Object obj, akc akc) {
        this(i, str, obj);
    }

    public static akb<String> a(int i, String str) {
        akb<String> a2 = a(i, str, (String) null);
        ajh.e().b(a2);
        return a2;
    }

    public static akb<Float> a(int i, String str, float f) {
        return new akf(i, str, Float.valueOf(f));
    }

    public static akb<Integer> a(int i, String str, int i2) {
        return new akd(i, str, Integer.valueOf(i2));
    }

    public static akb<Long> a(int i, String str, long j) {
        return new ake(i, str, Long.valueOf(j));
    }

    public static akb<Boolean> a(int i, String str, Boolean bool) {
        return new akc(i, str, bool);
    }

    public static akb<String> a(int i, String str, String str2) {
        return new akg(i, str, str2);
    }

    public static akb<String> b(int i, String str) {
        akb<String> a2 = a(i, str, (String) null);
        ajh.e().c(a2);
        return a2;
    }

    /* access modifiers changed from: protected */
    public abstract T a(SharedPreferences sharedPreferences);

    /* access modifiers changed from: protected */
    public abstract T a(JSONObject jSONObject);

    public final String a() {
        return this.b;
    }

    public abstract void a(Editor editor, T t);

    public final T b() {
        return this.c;
    }

    public final int c() {
        return this.a;
    }
}
