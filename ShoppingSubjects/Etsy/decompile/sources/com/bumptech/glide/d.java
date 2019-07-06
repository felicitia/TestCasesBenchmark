package com.bumptech.glide;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.e.e;
import com.bumptech.glide.load.b.f;
import com.bumptech.glide.load.b.g;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.resource.b.a;
import com.bumptech.glide.load.resource.c.c;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.manager.k;
import java.io.InputStream;

/* compiled from: DrawableTypeRequest */
public class d<ModelType> extends c<ModelType> {
    private final l<ModelType, InputStream> g;
    private final l<ModelType, ParcelFileDescriptor> h;
    private final c i;

    private static <A, Z, R> e<A, g, Z, R> a(Glide glide, l<A, InputStream> lVar, l<A, ParcelFileDescriptor> lVar2, Class<Z> cls, Class<R> cls2, c<Z, R> cVar) {
        if (lVar == null && lVar2 == null) {
            return null;
        }
        if (cVar == null) {
            cVar = glide.a(cls, cls2);
        }
        return new e<>(new f(lVar, lVar2), cVar, glide.b(g.class, cls));
    }

    d(Class<ModelType> cls, l<ModelType, InputStream> lVar, l<ModelType, ParcelFileDescriptor> lVar2, Context context, Glide glide, k kVar, com.bumptech.glide.manager.g gVar, c cVar) {
        e a = a(glide, lVar, lVar2, a.class, GlideDrawable.class, null);
        super(context, cls, a, glide, kVar, gVar);
        this.g = lVar;
        this.h = lVar2;
        this.i = cVar;
    }

    public b<ModelType> h() {
        return (b) this.i.a(new b(this, this.g, this.h, this.i));
    }
}
