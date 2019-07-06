package com.google.android.gms.maps;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class a {
    private final IObjectWrapper a;

    a(IObjectWrapper iObjectWrapper) {
        this.a = (IObjectWrapper) Preconditions.checkNotNull(iObjectWrapper);
    }

    public final IObjectWrapper a() {
        return this.a;
    }
}
