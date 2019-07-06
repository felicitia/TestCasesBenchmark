package com.google.android.gms.internal.ads;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class mr {
    private final int a;
    private final List<ajj> b;
    private final int c;
    private final InputStream d;

    public mr(int i, List<ajj> list) {
        this(i, list, -1, null);
    }

    public mr(int i, List<ajj> list, int i2, InputStream inputStream) {
        this.a = i;
        this.b = list;
        this.c = i2;
        this.d = inputStream;
    }

    public final int a() {
        return this.a;
    }

    public final List<ajj> b() {
        return Collections.unmodifiableList(this.b);
    }

    public final int c() {
        return this.c;
    }

    public final InputStream d() {
        return this.d;
    }
}
