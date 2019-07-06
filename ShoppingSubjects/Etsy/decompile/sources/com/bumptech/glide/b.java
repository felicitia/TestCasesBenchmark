package com.bumptech.glide;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.e.e;
import com.bumptech.glide.load.b.f;
import com.bumptech.glide.load.b.g;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.resource.c.c;
import java.io.InputStream;

/* compiled from: BitmapTypeRequest */
public class b<ModelType> extends a<ModelType, Bitmap> {
    private final l<ModelType, InputStream> g;
    private final l<ModelType, ParcelFileDescriptor> h;
    private final Glide i;
    private final c j;

    private static <A, R> e<A, g, Bitmap, R> a(Glide glide, l<A, InputStream> lVar, l<A, ParcelFileDescriptor> lVar2, Class<R> cls, c<Bitmap, R> cVar) {
        if (lVar == null && lVar2 == null) {
            return null;
        }
        if (cVar == null) {
            cVar = glide.a(Bitmap.class, cls);
        }
        return new e<>(new f(lVar, lVar2), cVar, glide.b(g.class, Bitmap.class));
    }

    b(e<ModelType, ?, ?, ?> eVar, l<ModelType, InputStream> lVar, l<ModelType, ParcelFileDescriptor> lVar2, c cVar) {
        super(a(eVar.c, lVar, lVar2, Bitmap.class, null), Bitmap.class, eVar);
        this.g = lVar;
        this.h = lVar2;
        this.i = eVar.c;
        this.j = cVar;
    }
}
