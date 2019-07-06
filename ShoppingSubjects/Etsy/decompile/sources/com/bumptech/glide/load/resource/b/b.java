package com.bumptech.glide.load.resource.b;

import com.bumptech.glide.load.engine.i;

/* compiled from: GifBitmapWrapperResource */
public class b implements i<a> {
    private final a a;

    public b(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Data must not be null");
        }
        this.a = aVar;
    }

    /* renamed from: a */
    public a b() {
        return this.a;
    }

    public int c() {
        return this.a.a();
    }

    public void d() {
        i b = this.a.b();
        if (b != null) {
            b.d();
        }
        i c = this.a.c();
        if (c != null) {
            c.d();
        }
    }
}
