package com.bumptech.glide.load.resource.b;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.bitmap_recycle.c;
import com.bumptech.glide.load.engine.i;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.resource.gif.d;

/* compiled from: GifBitmapWrapperTransformation */
public class f implements com.bumptech.glide.load.f<a> {
    private final com.bumptech.glide.load.f<Bitmap> a;
    private final com.bumptech.glide.load.f<GifDrawable> b;

    public f(c cVar, com.bumptech.glide.load.f<Bitmap> fVar) {
        this(fVar, (com.bumptech.glide.load.f<GifDrawable>) new d<GifDrawable>(fVar, cVar));
    }

    f(com.bumptech.glide.load.f<Bitmap> fVar, com.bumptech.glide.load.f<GifDrawable> fVar2) {
        this.a = fVar;
        this.b = fVar2;
    }

    public i<a> a(i<a> iVar, int i, int i2) {
        i b2 = ((a) iVar.b()).b();
        i c = ((a) iVar.b()).c();
        if (b2 != null && this.a != null) {
            i a2 = this.a.a(b2, i, i2);
            if (!b2.equals(a2)) {
                return new b(new a(a2, ((a) iVar.b()).c()));
            }
        } else if (!(c == null || this.b == null)) {
            i a3 = this.b.a(c, i, i2);
            if (!c.equals(a3)) {
                return new b(new a(((a) iVar.b()).b(), a3));
            }
        }
        return iVar;
    }

    public String a() {
        return this.a.a();
    }
}
