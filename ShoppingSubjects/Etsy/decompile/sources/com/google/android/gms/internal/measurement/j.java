package com.google.android.gms.internal.measurement;

import java.io.IOException;

public abstract class j {
    protected volatile int b = -1;

    /* access modifiers changed from: protected */
    public int a() {
        return 0;
    }

    public abstract j a(c cVar) throws IOException;

    public void a(d dVar) throws IOException {
    }

    /* renamed from: b */
    public j clone() throws CloneNotSupportedException {
        return (j) super.clone();
    }

    public final int c() {
        if (this.b < 0) {
            d();
        }
        return this.b;
    }

    public final int d() {
        int a = a();
        this.b = a;
        return a;
    }

    public String toString() {
        return k.a(this);
    }
}
