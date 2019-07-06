package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.e.b;
import com.bumptech.glide.load.a;
import com.bumptech.glide.load.b.g;
import com.bumptech.glide.load.b.h;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.e;
import java.io.File;
import java.io.InputStream;

/* compiled from: ImageVideoDataLoadProvider */
public class l implements b<g, Bitmap> {
    private final k a;
    private final d<File, Bitmap> b;
    private final e<Bitmap> c;
    private final h d;

    public l(b<InputStream, Bitmap> bVar, b<ParcelFileDescriptor, Bitmap> bVar2) {
        this.c = bVar.d();
        this.d = new h(bVar.c(), bVar2.c());
        this.b = bVar.a();
        this.a = new k(bVar.b(), bVar2.b());
    }

    public d<File, Bitmap> a() {
        return this.b;
    }

    public d<g, Bitmap> b() {
        return this.a;
    }

    public a<g> c() {
        return this.d;
    }

    public e<Bitmap> d() {
        return this.c;
    }
}
