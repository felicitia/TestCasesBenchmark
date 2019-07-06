package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.g.h;
import com.bumptech.glide.load.engine.bitmap_recycle.c;
import com.bumptech.glide.load.engine.i;
import com.bumptech.glide.load.f;

/* compiled from: BitmapTransformation */
public abstract class d implements f<Bitmap> {
    private c a;

    /* access modifiers changed from: protected */
    public abstract Bitmap a(c cVar, Bitmap bitmap, int i, int i2);

    public d(c cVar) {
        this.a = cVar;
    }

    public final i<Bitmap> a(i<Bitmap> iVar, int i, int i2) {
        if (!h.a(i, i2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot apply transformation on width: ");
            sb.append(i);
            sb.append(" or height: ");
            sb.append(i2);
            sb.append(" less than or equal to zero and not Target.SIZE_ORIGINAL");
            throw new IllegalArgumentException(sb.toString());
        }
        Bitmap bitmap = (Bitmap) iVar.b();
        if (i == Integer.MIN_VALUE) {
            i = bitmap.getWidth();
        }
        if (i2 == Integer.MIN_VALUE) {
            i2 = bitmap.getHeight();
        }
        Bitmap a2 = a(this.a, bitmap, i, i2);
        return bitmap.equals(a2) ? iVar : c.a(a2, this.a);
    }
}
