package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.bumptech.glide.load.engine.bitmap_recycle.c;

/* compiled from: CenterCrop */
public class e extends d {
    public String a() {
        return "CenterCrop.com.bumptech.glide.load.resource.bitmap";
    }

    public e(c cVar) {
        super(cVar);
    }

    /* access modifiers changed from: protected */
    public Bitmap a(c cVar, Bitmap bitmap, int i, int i2) {
        Bitmap a = cVar.a(i, i2, bitmap.getConfig() != null ? bitmap.getConfig() : Config.ARGB_8888);
        Bitmap a2 = o.a(a, bitmap, i, i2);
        if (!(a == null || a == a2 || cVar.a(a))) {
            a.recycle();
        }
        return a2;
    }
}
