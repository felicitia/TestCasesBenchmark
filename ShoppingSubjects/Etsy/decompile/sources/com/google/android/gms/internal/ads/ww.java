package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.xh.d;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ww {
    static final ww a = new ww(true);
    private static volatile boolean b = false;
    private static final Class<?> c = b();
    private final Map<wx, d<?, ?>> d;

    ww() {
        this.d = new HashMap();
    }

    private ww(boolean z) {
        this.d = Collections.emptyMap();
    }

    public static ww a() {
        return wv.a();
    }

    private static Class<?> b() {
        try {
            return Class.forName("com.google.protobuf.Extension");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public final <ContainingType extends yk> d<ContainingType, ?> a(ContainingType containingtype, int i) {
        return (d) this.d.get(new wx(containingtype, i));
    }
}
