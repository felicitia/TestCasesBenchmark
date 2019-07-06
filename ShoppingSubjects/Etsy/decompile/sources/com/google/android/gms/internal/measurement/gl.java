package com.google.android.gms.internal.measurement;

final /* synthetic */ class gl implements gr {
    private final String a;
    private final boolean b = false;

    gl(String str, boolean z) {
        this.a = str;
    }

    public final Object a() {
        return Boolean.valueOf(gd.a(gi.c.getContentResolver(), this.a, this.b));
    }
}
