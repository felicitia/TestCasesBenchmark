package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.xd;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

final class xb<FieldDescriptorType extends xd<FieldDescriptorType>> {
    private static final xb d = new xb(true);
    private final zg<FieldDescriptorType, Object> a = zg.a(16);
    private boolean b;
    private boolean c = false;

    private xb() {
    }

    private xb(boolean z) {
        c();
    }

    static int a(zzbes zzbes, int i, Object obj) {
        int e = zzbav.e(i);
        if (zzbes == zzbes.GROUP) {
            xj.a((yk) obj);
            e <<= 1;
        }
        return e + b(zzbes, obj);
    }

    public static <T extends xd<T>> xb<T> a() {
        return d;
    }

    private final Object a(FieldDescriptorType fielddescriptortype) {
        Object obj = this.a.get(fielddescriptortype);
        return obj instanceof xn ? xn.a() : obj;
    }

    private static Object a(Object obj) {
        if (obj instanceof ys) {
            return ((ys) obj).a();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    private final void a(FieldDescriptorType fielddescriptortype, Object obj) {
        if (!fielddescriptortype.d()) {
            a(fielddescriptortype.b(), obj);
            r7 = obj;
        } else if (!(obj instanceof List)) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList2.get(i);
                i++;
                a(fielddescriptortype.b(), obj2);
            }
            r7 = arrayList;
        }
        if (r7 instanceof xn) {
            this.c = true;
        }
        this.a.put(fielddescriptortype, r7);
    }

    static void a(zzbav zzbav, zzbes zzbes, int i, Object obj) throws IOException {
        if (zzbes == zzbes.GROUP) {
            yk ykVar = (yk) obj;
            xj.a(ykVar);
            zzbav.a(i, 3);
            ykVar.a(zzbav);
            zzbav.a(i, 4);
            return;
        }
        zzbav.a(i, zzbes.zzagm());
        switch (xc.b[zzbes.ordinal()]) {
            case 1:
                zzbav.a(((Double) obj).doubleValue());
                return;
            case 2:
                zzbav.a(((Float) obj).floatValue());
                return;
            case 3:
                zzbav.a(((Long) obj).longValue());
                return;
            case 4:
                zzbav.a(((Long) obj).longValue());
                return;
            case 5:
                zzbav.a(((Integer) obj).intValue());
                return;
            case 6:
                zzbav.c(((Long) obj).longValue());
                return;
            case 7:
                zzbav.d(((Integer) obj).intValue());
                return;
            case 8:
                zzbav.a(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((yk) obj).a(zzbav);
                return;
            case 10:
                zzbav.a((yk) obj);
                return;
            case 11:
                if (obj instanceof zzbah) {
                    zzbav.a((zzbah) obj);
                    return;
                } else {
                    zzbav.a((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzbah) {
                    zzbav.a((zzbah) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzbav.c(bArr, 0, bArr.length);
                return;
            case 13:
                zzbav.b(((Integer) obj).intValue());
                return;
            case 14:
                zzbav.d(((Integer) obj).intValue());
                return;
            case 15:
                zzbav.c(((Long) obj).longValue());
                return;
            case 16:
                zzbav.c(((Integer) obj).intValue());
                return;
            case 17:
                zzbav.b(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof xk) {
                    zzbav.a(((xk) obj).zzhq());
                    return;
                } else {
                    zzbav.a(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        if ((r3 instanceof byte[]) == false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001b, code lost:
        if ((r3 instanceof com.google.android.gms.internal.ads.xn) == false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0024, code lost:
        if ((r3 instanceof com.google.android.gms.internal.ads.xk) == false) goto L_0x0043;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(com.google.android.gms.internal.ads.zzbes r2, java.lang.Object r3) {
        /*
            com.google.android.gms.internal.ads.xj.a(r3)
            int[] r0 = com.google.android.gms.internal.ads.xc.a
            com.google.android.gms.internal.ads.zzbex r2 = r2.zzagl()
            int r2 = r2.ordinal()
            r2 = r0[r2]
            r0 = 1
            r1 = 0
            switch(r2) {
                case 1: goto L_0x0040;
                case 2: goto L_0x003d;
                case 3: goto L_0x003a;
                case 4: goto L_0x0037;
                case 5: goto L_0x0034;
                case 6: goto L_0x0031;
                case 7: goto L_0x0028;
                case 8: goto L_0x001e;
                case 9: goto L_0x0015;
                default: goto L_0x0014;
            }
        L_0x0014:
            goto L_0x0043
        L_0x0015:
            boolean r2 = r3 instanceof com.google.android.gms.internal.ads.yk
            if (r2 != 0) goto L_0x0026
            boolean r2 = r3 instanceof com.google.android.gms.internal.ads.xn
            if (r2 == 0) goto L_0x0043
            goto L_0x0026
        L_0x001e:
            boolean r2 = r3 instanceof java.lang.Integer
            if (r2 != 0) goto L_0x0026
            boolean r2 = r3 instanceof com.google.android.gms.internal.ads.xk
            if (r2 == 0) goto L_0x0043
        L_0x0026:
            r1 = r0
            goto L_0x0043
        L_0x0028:
            boolean r2 = r3 instanceof com.google.android.gms.internal.ads.zzbah
            if (r2 != 0) goto L_0x0026
            boolean r2 = r3 instanceof byte[]
            if (r2 == 0) goto L_0x0043
            goto L_0x0026
        L_0x0031:
            boolean r0 = r3 instanceof java.lang.String
            goto L_0x0026
        L_0x0034:
            boolean r0 = r3 instanceof java.lang.Boolean
            goto L_0x0026
        L_0x0037:
            boolean r0 = r3 instanceof java.lang.Double
            goto L_0x0026
        L_0x003a:
            boolean r0 = r3 instanceof java.lang.Float
            goto L_0x0026
        L_0x003d:
            boolean r0 = r3 instanceof java.lang.Long
            goto L_0x0026
        L_0x0040:
            boolean r0 = r3 instanceof java.lang.Integer
            goto L_0x0026
        L_0x0043:
            if (r1 != 0) goto L_0x004d
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Wrong object type used with protocol message reflection."
            r2.<init>(r3)
            throw r2
        L_0x004d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.xb.a(com.google.android.gms.internal.ads.zzbes, java.lang.Object):void");
    }

    private static boolean a(Entry<FieldDescriptorType, Object> entry) {
        xd xdVar = (xd) entry.getKey();
        if (xdVar.c() == zzbex.MESSAGE) {
            if (xdVar.d()) {
                for (yk k : (List) entry.getValue()) {
                    if (!k.k()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof yk) {
                    if (!((yk) value).k()) {
                        return false;
                    }
                } else if (value instanceof xn) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    private static int b(xd<?> xdVar, Object obj) {
        zzbes b2 = xdVar.b();
        int a2 = xdVar.a();
        if (!xdVar.d()) {
            return a(b2, a2, obj);
        }
        int i = 0;
        if (xdVar.e()) {
            for (Object b3 : (List) obj) {
                i += b(b2, b3);
            }
            return zzbav.e(a2) + i + zzbav.l(i);
        }
        for (Object a3 : (List) obj) {
            i += a(b2, a2, a3);
        }
        return i;
    }

    private static int b(zzbes zzbes, Object obj) {
        switch (xc.b[zzbes.ordinal()]) {
            case 1:
                return zzbav.b(((Double) obj).doubleValue());
            case 2:
                return zzbav.b(((Float) obj).floatValue());
            case 3:
                return zzbav.d(((Long) obj).longValue());
            case 4:
                return zzbav.e(((Long) obj).longValue());
            case 5:
                return zzbav.f(((Integer) obj).intValue());
            case 6:
                return zzbav.g(((Long) obj).longValue());
            case 7:
                return zzbav.i(((Integer) obj).intValue());
            case 8:
                return zzbav.b(((Boolean) obj).booleanValue());
            case 9:
                return zzbav.c((yk) obj);
            case 10:
                return obj instanceof xn ? zzbav.a((xs) (xn) obj) : zzbav.b((yk) obj);
            case 11:
                return obj instanceof zzbah ? zzbav.b((zzbah) obj) : zzbav.b((String) obj);
            case 12:
                return obj instanceof zzbah ? zzbav.b((zzbah) obj) : zzbav.b((byte[]) obj);
            case 13:
                return zzbav.g(((Integer) obj).intValue());
            case 14:
                return zzbav.j(((Integer) obj).intValue());
            case 15:
                return zzbav.h(((Long) obj).longValue());
            case 16:
                return zzbav.h(((Integer) obj).intValue());
            case 17:
                return zzbav.f(((Long) obj).longValue());
            case 18:
                return obj instanceof xk ? zzbav.k(((xk) obj).zzhq()) : zzbav.k(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    private final void b(Entry<FieldDescriptorType, Object> entry) {
        xd xdVar = (xd) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof xn) {
            value = xn.a();
        }
        if (xdVar.d()) {
            Object a2 = a((FieldDescriptorType) xdVar);
            if (a2 == null) {
                a2 = new ArrayList();
            }
            for (Object a3 : (List) value) {
                ((List) a2).add(a(a3));
            }
            this.a.put(xdVar, a2);
        } else if (xdVar.c() == zzbex.MESSAGE) {
            Object a4 = a((FieldDescriptorType) xdVar);
            if (a4 == null) {
                this.a.put(xdVar, a(value));
            } else {
                this.a.put(xdVar, a4 instanceof ys ? xdVar.a((ys) a4, (ys) value) : xdVar.a(((yk) a4).n(), (yk) value).e());
            }
        } else {
            this.a.put(xdVar, a(value));
        }
    }

    private static int c(Entry<FieldDescriptorType, Object> entry) {
        xd xdVar = (xd) entry.getKey();
        Object value = entry.getValue();
        return (xdVar.c() != zzbex.MESSAGE || xdVar.d() || xdVar.e()) ? b(xdVar, value) : value instanceof xn ? zzbav.b(((xd) entry.getKey()).a(), (xs) (xn) value) : zzbav.b(((xd) entry.getKey()).a(), (yk) value);
    }

    public final void a(xb<FieldDescriptorType> xbVar) {
        for (int i = 0; i < xbVar.a.c(); i++) {
            b(xbVar.a.b(i));
        }
        for (Entry b2 : xbVar.a.d()) {
            b(b2);
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean b() {
        return this.a.isEmpty();
    }

    public final void c() {
        if (!this.b) {
            this.a.a();
            this.b = true;
        }
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        xb xbVar = new xb();
        for (int i = 0; i < this.a.c(); i++) {
            Entry b2 = this.a.b(i);
            xbVar.a((FieldDescriptorType) (xd) b2.getKey(), b2.getValue());
        }
        for (Entry entry : this.a.d()) {
            xbVar.a((FieldDescriptorType) (xd) entry.getKey(), entry.getValue());
        }
        xbVar.c = this.c;
        return xbVar;
    }

    public final boolean d() {
        return this.b;
    }

    public final Iterator<Entry<FieldDescriptorType, Object>> e() {
        return this.c ? new xr(this.a.entrySet().iterator()) : this.a.entrySet().iterator();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof xb)) {
            return false;
        }
        return this.a.equals(((xb) obj).a);
    }

    /* access modifiers changed from: 0000 */
    public final Iterator<Entry<FieldDescriptorType, Object>> f() {
        return this.c ? new xr(this.a.e().iterator()) : this.a.e().iterator();
    }

    public final boolean g() {
        for (int i = 0; i < this.a.c(); i++) {
            if (!a(this.a.b(i))) {
                return false;
            }
        }
        for (Entry a2 : this.a.d()) {
            if (!a(a2)) {
                return false;
            }
        }
        return true;
    }

    public final int h() {
        int i = 0;
        for (int i2 = 0; i2 < this.a.c(); i2++) {
            Entry b2 = this.a.b(i2);
            i += b((xd) b2.getKey(), b2.getValue());
        }
        for (Entry entry : this.a.d()) {
            i += b((xd) entry.getKey(), entry.getValue());
        }
        return i;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final int i() {
        int i = 0;
        for (int i2 = 0; i2 < this.a.c(); i2++) {
            i += c(this.a.b(i2));
        }
        for (Entry c2 : this.a.d()) {
            i += c(c2);
        }
        return i;
    }
}
