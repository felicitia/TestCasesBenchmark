package com.google.android.gms.maps;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.c.b;
import com.google.android.gms.maps.internal.zzi;
import com.google.android.gms.maps.model.c;

final class r extends zzi {
    private final /* synthetic */ b a;

    r(c cVar, b bVar) {
        this.a = bVar;
    }

    public final IObjectWrapper zzh(zzt zzt) {
        return ObjectWrapper.wrap(this.a.a(new c(zzt)));
    }

    public final IObjectWrapper zzi(zzt zzt) {
        return ObjectWrapper.wrap(this.a.b(new c(zzt)));
    }
}
