package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.e.b;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.a;
import com.bumptech.glide.load.b.o;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.resource.a.c;
import java.io.File;
import java.io.InputStream;

/* compiled from: StreamBitmapDataLoadProvider */
public class m implements b<InputStream, Bitmap> {
    private final n a;
    private final b b;
    private final o c = new o();
    private final c<Bitmap> d;

    public m(com.bumptech.glide.load.engine.bitmap_recycle.c cVar, DecodeFormat decodeFormat) {
        this.a = new n(cVar, decodeFormat);
        this.b = new b();
        this.d = new c<>(this.a);
    }

    public d<File, Bitmap> a() {
        return this.d;
    }

    public d<InputStream, Bitmap> b() {
        return this.a;
    }

    public a<InputStream> c() {
        return this.c;
    }

    public e<Bitmap> d() {
        return this.b;
    }
}
