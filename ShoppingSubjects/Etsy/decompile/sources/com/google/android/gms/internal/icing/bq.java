package com.google.android.gms.internal.icing;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

final class bq<T> implements ca<T> {
    private final bl a;
    private final cr<?, ?> b;
    private final boolean c;
    private final z<?> d;

    private bq(cr<?, ?> crVar, z<?> zVar, bl blVar) {
        this.b = crVar;
        this.c = zVar.a(blVar);
        this.d = zVar;
        this.a = blVar;
    }

    static <T> bq<T> a(cr<?, ?> crVar, z<?> zVar, bl blVar) {
        return new bq<>(crVar, zVar, blVar);
    }

    public final int a(T t) {
        int hashCode = this.b.a(t).hashCode();
        return this.c ? (hashCode * 53) + this.d.a((Object) t).hashCode() : hashCode;
    }

    public final void a(T t, df dfVar) throws IOException {
        int a2;
        Object value;
        Iterator e = this.d.a((Object) t).e();
        while (e.hasNext()) {
            Entry entry = (Entry) e.next();
            ae aeVar = (ae) entry.getKey();
            if (aeVar.c() != zzfq.MESSAGE || aeVar.d() || aeVar.e()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (entry instanceof ar) {
                a2 = aeVar.a();
                value = ((ar) entry).a().c();
            } else {
                a2 = aeVar.a();
                value = entry.getValue();
            }
            dfVar.a(a2, value);
        }
        cr<?, ?> crVar = this.b;
        crVar.b(crVar.a(t), dfVar);
    }

    public final boolean a(T t, T t2) {
        if (!this.b.a(t).equals(this.b.a(t2))) {
            return false;
        }
        if (this.c) {
            return this.d.a((Object) t).equals(this.d.a((Object) t2));
        }
        return true;
    }

    public final int b(T t) {
        cr<?, ?> crVar = this.b;
        int c2 = 0 + crVar.c(crVar.a(t));
        return this.c ? c2 + this.d.a((Object) t).i() : c2;
    }

    public final void b(T t, T t2) {
        cc.a(this.b, t, t2);
        if (this.c) {
            cc.a(this.d, t, t2);
        }
    }

    public final void c(T t) {
        this.b.b(t);
        this.d.c(t);
    }

    public final boolean d(T t) {
        return this.d.a((Object) t).g();
    }
}
