package com.bumptech.glide.load.resource.bitmap;

import com.bumptech.glide.g.h;
import com.bumptech.glide.load.engine.bitmap_recycle.c;
import com.bumptech.glide.load.resource.drawable.a;

/* compiled from: GlideBitmapDrawableResource */
public class j extends a<GlideBitmapDrawable> {
    private final c b;

    public j(GlideBitmapDrawable glideBitmapDrawable, c cVar) {
        super(glideBitmapDrawable);
        this.b = cVar;
    }

    public int c() {
        return h.a(((GlideBitmapDrawable) this.a).getBitmap());
    }

    public void d() {
        this.b.a(((GlideBitmapDrawable) this.a).getBitmap());
    }
}
