package com.bumptech.glide.load.resource.gif;

import android.content.Context;
import com.bumptech.glide.load.a;
import com.bumptech.glide.load.b.o;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.resource.a.c;
import java.io.File;
import java.io.InputStream;

/* compiled from: GifDrawableLoadProvider */
public class b implements com.bumptech.glide.e.b<InputStream, GifDrawable> {
    private final h a;
    private final i b;
    private final o c;
    private final c<GifDrawable> d = new c<>(this.a);

    public b(Context context, com.bumptech.glide.load.engine.bitmap_recycle.c cVar) {
        this.a = new h(context, cVar);
        this.b = new i(cVar);
        this.c = new o();
    }

    public d<File, GifDrawable> a() {
        return this.d;
    }

    public d<InputStream, GifDrawable> b() {
        return this.a;
    }

    public a<InputStream> c() {
        return this.c;
    }

    public e<GifDrawable> d() {
        return this.b;
    }
}
