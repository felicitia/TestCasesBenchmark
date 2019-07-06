package com.bumptech.glide.load.resource.c;

import android.content.res.Resources;
import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.bitmap_recycle.c;
import com.bumptech.glide.load.engine.i;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.bitmap.j;

/* compiled from: GlideBitmapDrawableTranscoder */
public class b implements c<Bitmap, GlideBitmapDrawable> {
    private final Resources a;
    private final c b;

    public String a() {
        return "GlideBitmapDrawableTranscoder.com.bumptech.glide.load.resource.transcode";
    }

    public b(Resources resources, c cVar) {
        this.a = resources;
        this.b = cVar;
    }

    public i<GlideBitmapDrawable> a(i<Bitmap> iVar) {
        return new j(new GlideBitmapDrawable(this.a, (Bitmap) iVar.b()), this.b);
    }
}
