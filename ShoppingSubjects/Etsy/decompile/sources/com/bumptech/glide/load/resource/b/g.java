package com.bumptech.glide.load.resource.b;

import android.graphics.Bitmap;
import com.bumptech.glide.e.b;
import com.bumptech.glide.load.a;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.engine.bitmap_recycle.c;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import java.io.File;
import java.io.InputStream;

/* compiled from: ImageVideoGifDrawableLoadProvider */
public class g implements b<com.bumptech.glide.load.b.g, a> {
    private final d<File, a> a;
    private final d<com.bumptech.glide.load.b.g, a> b;
    private final e<a> c;
    private final a<com.bumptech.glide.load.b.g> d;

    public g(b<com.bumptech.glide.load.b.g, Bitmap> bVar, b<InputStream, GifDrawable> bVar2, c cVar) {
        c cVar2 = new c(bVar.b(), bVar2.b(), cVar);
        this.a = new com.bumptech.glide.load.resource.a.c(new e(cVar2));
        this.b = cVar2;
        this.c = new d(bVar.d(), bVar2.d());
        this.d = bVar.c();
    }

    public d<File, a> a() {
        return this.a;
    }

    public d<com.bumptech.glide.load.b.g, a> b() {
        return this.b;
    }

    public a<com.bumptech.glide.load.b.g> c() {
        return this.d;
    }

    public e<a> d() {
        return this.c;
    }
}
