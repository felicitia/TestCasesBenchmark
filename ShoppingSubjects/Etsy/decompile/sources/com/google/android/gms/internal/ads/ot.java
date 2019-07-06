package com.google.android.gms.internal.ads;

@bu
public final class ot {
    public final int a;
    public final int b;
    private final int c;

    private ot(int i, int i2, int i3) {
        this.c = i;
        this.b = i2;
        this.a = i3;
    }

    public static ot a() {
        return new ot(0, 0, 0);
    }

    public static ot a(int i, int i2) {
        return new ot(1, i, i2);
    }

    public static ot a(zzjn zzjn) {
        return zzjn.zzarc ? new ot(3, 0, 0) : zzjn.zzarf ? new ot(2, 0, 0) : zzjn.zzare ? a() : a(zzjn.widthPixels, zzjn.heightPixels);
    }

    public static ot b() {
        return new ot(4, 0, 0);
    }

    public final boolean c() {
        return this.c == 2;
    }

    public final boolean d() {
        return this.c == 3;
    }

    public final boolean e() {
        return this.c == 0;
    }

    public final boolean f() {
        return this.c == 4;
    }
}
