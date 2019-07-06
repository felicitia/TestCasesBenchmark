package com.google.android.gms.internal.measurement;

import android.net.Uri;

public final class gs {
    private final String a;
    /* access modifiers changed from: private */
    public final Uri b;
    /* access modifiers changed from: private */
    public final String c;
    /* access modifiers changed from: private */
    public final String d;
    private final boolean e;
    private final boolean f;

    public gs(Uri uri) {
        this(null, uri, "", "", false, false);
    }

    private gs(String str, Uri uri, String str2, String str3, boolean z, boolean z2) {
        this.a = null;
        this.b = uri;
        this.c = str2;
        this.d = str3;
        this.e = false;
        this.f = false;
    }

    public final gi<Double> a(String str, double d2) {
        return gi.b(this, str, d2);
    }

    public final gi<Integer> a(String str, int i) {
        return gi.b(this, str, i);
    }

    public final gi<Long> a(String str, long j) {
        return gi.b(this, str, j);
    }

    public final gi<String> a(String str, String str2) {
        return gi.b(this, str, str2);
    }

    public final gi<Boolean> a(String str, boolean z) {
        return gi.b(this, str, z);
    }
}
