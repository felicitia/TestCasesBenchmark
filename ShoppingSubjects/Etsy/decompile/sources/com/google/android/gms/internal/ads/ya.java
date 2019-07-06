package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.xh.e;

final class ya implements ze {
    private static final yj b = new yb();
    private final yj a;

    public ya() {
        this(new yc(xg.a(), a()));
    }

    private ya(yj yjVar) {
        this.a = (yj) xj.a(yjVar, "messageInfoFactory");
    }

    private static yj a() {
        try {
            return (yj) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return b;
        }
    }

    private static boolean a(yi yiVar) {
        return yiVar.a() == e.h;
    }

    public final <T> zd<T> a(Class<T> cls) {
        zf.a(cls);
        yi b2 = this.a.b(cls);
        if (b2.b()) {
            return xh.class.isAssignableFrom(cls) ? yr.a(zf.c(), xa.a(), b2.c()) : yr.a(zf.a(), xa.b(), b2.c());
        }
        if (xh.class.isAssignableFrom(cls)) {
            if (a(b2)) {
                return yo.a(cls, b2, yv.b(), xv.b(), zf.c(), xa.a(), yh.b());
            }
            return yo.a(cls, b2, yv.b(), xv.b(), zf.c(), null, yh.b());
        } else if (a(b2)) {
            return yo.a(cls, b2, yv.a(), xv.a(), zf.a(), xa.b(), yh.a());
        } else {
            return yo.a(cls, b2, yv.a(), xv.a(), zf.b(), null, yh.a());
        }
    }
}
