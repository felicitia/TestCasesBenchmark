package com.etsy.android.vespa;

import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.util.LruCache;

/* compiled from: AdapterCache */
public class a {
    private static a c;
    private final int a = 5;
    private LruCache<String, Parcelable> b = new LruCache<>(5);

    private a() {
    }

    public static a a() {
        if (c == null) {
            c = new a();
        }
        return c;
    }

    public void a(String str, Parcelable parcelable) {
        this.b.put(str, parcelable);
    }

    @Nullable
    public Parcelable a(String str) {
        return (Parcelable) this.b.remove(str);
    }
}
