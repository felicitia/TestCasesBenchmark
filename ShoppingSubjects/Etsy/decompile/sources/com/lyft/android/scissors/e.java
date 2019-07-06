package com.lyft.android.scissors;

import android.graphics.Bitmap;
import android.graphics.Rect;
import com.bumptech.glide.load.engine.bitmap_recycle.c;
import com.bumptech.glide.load.resource.bitmap.d;

/* compiled from: GlideFillViewportTransformation */
class e extends d {
    private final int a;
    private final int b;

    public e(c cVar, int i, int i2) {
        super(cVar);
        this.a = i;
        this.b = i2;
    }

    /* access modifiers changed from: protected */
    public Bitmap a(c cVar, Bitmap bitmap, int i, int i2) {
        Rect a2 = c.a(bitmap.getWidth(), bitmap.getHeight(), this.a, this.b);
        return Bitmap.createScaledBitmap(bitmap, a2.width(), a2.height(), true);
    }

    public String a() {
        return getClass().getName();
    }

    public static d a(c cVar, int i, int i2) {
        return new e(cVar, i, i2);
    }
}
