package com.bumptech.glide.load.resource.gif;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.bitmap_recycle.c;
import com.bumptech.glide.load.engine.i;
import com.bumptech.glide.load.f;

/* compiled from: GifDrawableTransformation */
public class d implements f<GifDrawable> {
    private final f<Bitmap> a;
    private final c b;

    public d(f<Bitmap> fVar, c cVar) {
        this.a = fVar;
        this.b = cVar;
    }

    public i<GifDrawable> a(i<GifDrawable> iVar, int i, int i2) {
        GifDrawable gifDrawable = (GifDrawable) iVar.b();
        Bitmap firstFrame = ((GifDrawable) iVar.b()).getFirstFrame();
        Bitmap bitmap = (Bitmap) this.a.a(new com.bumptech.glide.load.resource.bitmap.c(firstFrame, this.b), i, i2).b();
        return !bitmap.equals(firstFrame) ? new c(new GifDrawable(gifDrawable, bitmap, this.a)) : iVar;
    }

    public String a() {
        return this.a.a();
    }
}
