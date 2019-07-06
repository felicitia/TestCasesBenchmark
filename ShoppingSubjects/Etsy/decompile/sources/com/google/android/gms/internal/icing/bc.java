package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.ah.d;

final class bc implements cb {
    private static final bk b = new bd();
    private final bk a;

    public bc() {
        this(new be(ag.a(), a()));
    }

    private bc(bk bkVar) {
        this.a = (bk) aj.a(bkVar, "messageInfoFactory");
    }

    private static bk a() {
        try {
            return (bk) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return b;
        }
    }

    private static boolean a(bj bjVar) {
        return bjVar.a() == d.h;
    }

    public final <T> ca<T> a(Class<T> cls) {
        cc.a(cls);
        bj b2 = this.a.b(cls);
        if (b2.b()) {
            return ah.class.isAssignableFrom(cls) ? bq.a(cc.c(), ab.a(), b2.c()) : bq.a(cc.a(), ab.b(), b2.c());
        }
        if (ah.class.isAssignableFrom(cls)) {
            if (a(b2)) {
                return bp.a(cls, b2, bv.b(), ax.b(), cc.c(), ab.a(), bi.b());
            }
            return bp.a(cls, b2, bv.b(), ax.b(), cc.c(), null, bi.b());
        } else if (a(b2)) {
            return bp.a(cls, b2, bv.a(), ax.a(), cc.a(), ab.b(), bi.a());
        } else {
            return bp.a(cls, b2, bv.a(), ax.a(), cc.b(), null, bi.a());
        }
    }
}
