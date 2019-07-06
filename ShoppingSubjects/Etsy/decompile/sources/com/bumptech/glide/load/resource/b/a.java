package com.bumptech.glide.load.resource.b;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.i;
import com.bumptech.glide.load.resource.gif.GifDrawable;

/* compiled from: GifBitmapWrapper */
public class a {
    private final i<GifDrawable> a;
    private final i<Bitmap> b;

    public a(i<Bitmap> iVar, i<GifDrawable> iVar2) {
        if (iVar != null && iVar2 != null) {
            throw new IllegalArgumentException("Can only contain either a bitmap resource or a gif resource, not both");
        } else if (iVar == null && iVar2 == null) {
            throw new IllegalArgumentException("Must contain either a bitmap resource or a gif resource");
        } else {
            this.b = iVar;
            this.a = iVar2;
        }
    }

    public int a() {
        if (this.b != null) {
            return this.b.c();
        }
        return this.a.c();
    }

    public i<Bitmap> b() {
        return this.b;
    }

    public i<GifDrawable> c() {
        return this.a;
    }
}
