package com.bumptech.glide;

import android.content.Context;
import com.bumptech.glide.e.e;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.resource.c.c;
import com.bumptech.glide.manager.g;
import com.bumptech.glide.manager.k;

/* compiled from: GenericTranscodeRequest */
public class f<ModelType, DataType, ResourceType> extends e<ModelType, DataType, ResourceType, ResourceType> {
    private final l<ModelType, DataType> g;
    private final Class<DataType> h;
    private final Class<ResourceType> i;
    private final c j;

    private static <A, T, Z, R> com.bumptech.glide.e.f<A, T, Z, R> a(Glide glide, l<A, T> lVar, Class<T> cls, Class<Z> cls2, c<Z, R> cVar) {
        return new e(lVar, cVar, glide.b(cls, cls2));
    }

    f(Context context, Glide glide, Class<ModelType> cls, l<ModelType, DataType> lVar, Class<DataType> cls2, Class<ResourceType> cls3, k kVar, g gVar, c cVar) {
        l<ModelType, DataType> lVar2 = lVar;
        Class<DataType> cls4 = cls2;
        Class<ResourceType> cls5 = cls3;
        Glide glide2 = glide;
        super(context, cls, a(glide2, lVar2, cls4, cls5, com.bumptech.glide.load.resource.c.e.b()), cls5, glide2, kVar, gVar);
        this.g = lVar2;
        this.h = cls4;
        this.i = cls5;
        this.j = cVar;
    }
}
