package com.bumptech.glide.load.resource.c;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.i;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;

/* compiled from: GifBitmapWrapperDrawableTranscoder */
public class a implements c<com.bumptech.glide.load.resource.b.a, GlideDrawable> {
    private final c<Bitmap, GlideBitmapDrawable> a;

    public String a() {
        return "GifBitmapWrapperDrawableTranscoder.com.bumptech.glide.load.resource.transcode";
    }

    public a(c<Bitmap, GlideBitmapDrawable> cVar) {
        this.a = cVar;
    }

    public i<GlideDrawable> a(i<com.bumptech.glide.load.resource.b.a> iVar) {
        com.bumptech.glide.load.resource.b.a aVar = (com.bumptech.glide.load.resource.b.a) iVar.b();
        i b = aVar.b();
        if (b != null) {
            return this.a.a(b);
        }
        return aVar.c();
    }
}
