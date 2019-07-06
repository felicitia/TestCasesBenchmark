package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.ae;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

final class ac<FieldDescriptorType extends ae<FieldDescriptorType>> {
    private static final ac d = new ac(true);
    private final cd<FieldDescriptorType, Object> a = cd.a(16);
    private boolean b;
    private boolean c = false;

    private ac() {
    }

    private ac(boolean z) {
        c();
    }

    static int a(zzfl zzfl, int i, Object obj) {
        int e = zzbu.e(i);
        if (zzfl == zzfl.GROUP) {
            aj.a((bl) obj);
            e <<= 1;
        }
        return e + b(zzfl, obj);
    }

    public static <T extends ae<T>> ac<T> a() {
        return d;
    }

    private final Object a(FieldDescriptorType fielddescriptortype) {
        Object obj = this.a.get(fielddescriptortype);
        return obj instanceof ap ? ap.a() : obj;
    }

    private static Object a(Object obj) {
        if (obj instanceof br) {
            return ((br) obj).b();
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
        if (r7 instanceof ap) {
            this.c = true;
        }
        this.a.put(fielddescriptortype, r7);
    }

    static void a(zzbu zzbu, zzfl zzfl, int i, Object obj) throws IOException {
        if (zzfl == zzfl.GROUP) {
            bl blVar = (bl) obj;
            aj.a(blVar);
            zzbu.a(i, 3);
            blVar.a(zzbu);
            zzbu.a(i, 4);
            return;
        }
        zzbu.a(i, zzfl.zzcz());
        switch (ad.b[zzfl.ordinal()]) {
            case 1:
                zzbu.a(((Double) obj).doubleValue());
                return;
            case 2:
                zzbu.a(((Float) obj).floatValue());
                return;
            case 3:
                zzbu.a(((Long) obj).longValue());
                return;
            case 4:
                zzbu.a(((Long) obj).longValue());
                return;
            case 5:
                zzbu.a(((Integer) obj).intValue());
                return;
            case 6:
                zzbu.c(((Long) obj).longValue());
                return;
            case 7:
                zzbu.d(((Integer) obj).intValue());
                return;
            case 8:
                zzbu.a(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((bl) obj).a(zzbu);
                return;
            case 10:
                zzbu.a((bl) obj);
                return;
            case 11:
                if (obj instanceof zzbi) {
                    zzbu.a((zzbi) obj);
                    return;
                } else {
                    zzbu.a((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzbi) {
                    zzbu.a((zzbi) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzbu.c(bArr, 0, bArr.length);
                return;
            case 13:
                zzbu.b(((Integer) obj).intValue());
                return;
            case 14:
                zzbu.d(((Integer) obj).intValue());
                return;
            case 15:
                zzbu.c(((Long) obj).longValue());
                return;
            case 16:
                zzbu.c(((Integer) obj).intValue());
                return;
            case 17:
                zzbu.b(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof am) {
                    zzbu.a(((am) obj).a());
                    return;
                } else {
                    zzbu.a(((Integer) obj).intValue());
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
        if ((r3 instanceof com.google.android.gms.internal.icing.ap) == false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0024, code lost:
        if ((r3 instanceof com.google.android.gms.internal.icing.am) == false) goto L_0x0043;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(com.google.android.gms.internal.icing.zzfl r2, java.lang.Object r3) {
        /*
            com.google.android.gms.internal.icing.aj.a(r3)
            int[] r0 = com.google.android.gms.internal.icing.ad.a
            com.google.android.gms.internal.icing.zzfq r2 = r2.zzcy()
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
            boolean r2 = r3 instanceof com.google.android.gms.internal.icing.bl
            if (r2 != 0) goto L_0x0026
            boolean r2 = r3 instanceof com.google.android.gms.internal.icing.ap
            if (r2 == 0) goto L_0x0043
            goto L_0x0026
        L_0x001e:
            boolean r2 = r3 instanceof java.lang.Integer
            if (r2 != 0) goto L_0x0026
            boolean r2 = r3 instanceof com.google.android.gms.internal.icing.am
            if (r2 == 0) goto L_0x0043
        L_0x0026:
            r1 = r0
            goto L_0x0043
        L_0x0028:
            boolean r2 = r3 instanceof com.google.android.gms.internal.icing.zzbi
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.ac.a(com.google.android.gms.internal.icing.zzfl, java.lang.Object):void");
    }

    private static boolean a(Entry<FieldDescriptorType, Object> entry) {
        ae aeVar = (ae) entry.getKey();
        if (aeVar.c() == zzfq.MESSAGE) {
            if (aeVar.d()) {
                for (bl c2 : (List) entry.getValue()) {
                    if (!c2.c()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof bl) {
                    if (!((bl) value).c()) {
                        return false;
                    }
                } else if (value instanceof ap) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    private static int b(ae<?> aeVar, Object obj) {
        zzfl b2 = aeVar.b();
        int a2 = aeVar.a();
        if (!aeVar.d()) {
            return a(b2, a2, obj);
        }
        int i = 0;
        if (aeVar.e()) {
            for (Object b3 : (List) obj) {
                i += b(b2, b3);
            }
            return zzbu.e(a2) + i + zzbu.l(i);
        }
        for (Object a3 : (List) obj) {
            i += a(b2, a2, a3);
        }
        return i;
    }

    private static int b(zzfl zzfl, Object obj) {
        switch (ad.b[zzfl.ordinal()]) {
            case 1:
                return zzbu.b(((Double) obj).doubleValue());
            case 2:
                return zzbu.b(((Float) obj).floatValue());
            case 3:
                return zzbu.d(((Long) obj).longValue());
            case 4:
                return zzbu.e(((Long) obj).longValue());
            case 5:
                return zzbu.f(((Integer) obj).intValue());
            case 6:
                return zzbu.g(((Long) obj).longValue());
            case 7:
                return zzbu.i(((Integer) obj).intValue());
            case 8:
                return zzbu.b(((Boolean) obj).booleanValue());
            case 9:
                return zzbu.c((bl) obj);
            case 10:
                return obj instanceof ap ? zzbu.a((at) (ap) obj) : zzbu.b((bl) obj);
            case 11:
                return obj instanceof zzbi ? zzbu.b((zzbi) obj) : zzbu.b((String) obj);
            case 12:
                return obj instanceof zzbi ? zzbu.b((zzbi) obj) : zzbu.b((byte[]) obj);
            case 13:
                return zzbu.g(((Integer) obj).intValue());
            case 14:
                return zzbu.j(((Integer) obj).intValue());
            case 15:
                return zzbu.h(((Long) obj).longValue());
            case 16:
                return zzbu.h(((Integer) obj).intValue());
            case 17:
                return zzbu.f(((Long) obj).longValue());
            case 18:
                return obj instanceof am ? zzbu.k(((am) obj).a()) : zzbu.k(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    private final void b(Entry<FieldDescriptorType, Object> entry) {
        ae aeVar = (ae) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof ap) {
            value = ap.a();
        }
        if (aeVar.d()) {
            Object a2 = a((FieldDescriptorType) aeVar);
            if (a2 == null) {
                a2 = new ArrayList();
            }
            for (Object a3 : (List) value) {
                ((List) a2).add(a(a3));
            }
            this.a.put(aeVar, a2);
        } else if (aeVar.c() == zzfq.MESSAGE) {
            Object a4 = a((FieldDescriptorType) aeVar);
            if (a4 == null) {
                this.a.put(aeVar, a(value));
            } else {
                this.a.put(aeVar, a4 instanceof br ? aeVar.a((br) a4, (br) value) : aeVar.a(((bl) a4).i(), (bl) value).d());
            }
        } else {
            this.a.put(aeVar, a(value));
        }
    }

    private static int c(Entry<FieldDescriptorType, Object> entry) {
        ae aeVar = (ae) entry.getKey();
        Object value = entry.getValue();
        return (aeVar.c() != zzfq.MESSAGE || aeVar.d() || aeVar.e()) ? b(aeVar, value) : value instanceof ap ? zzbu.b(((ae) entry.getKey()).a(), (at) (ap) value) : zzbu.b(((ae) entry.getKey()).a(), (bl) value);
    }

    public final void a(ac<FieldDescriptorType> acVar) {
        for (int i = 0; i < acVar.a.c(); i++) {
            b(acVar.a.b(i));
        }
        for (Entry b2 : acVar.a.d()) {
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
        ac acVar = new ac();
        for (int i = 0; i < this.a.c(); i++) {
            Entry b2 = this.a.b(i);
            acVar.a((FieldDescriptorType) (ae) b2.getKey(), b2.getValue());
        }
        for (Entry entry : this.a.d()) {
            acVar.a((FieldDescriptorType) (ae) entry.getKey(), entry.getValue());
        }
        acVar.c = this.c;
        return acVar;
    }

    public final boolean d() {
        return this.b;
    }

    public final Iterator<Entry<FieldDescriptorType, Object>> e() {
        return this.c ? new as(this.a.entrySet().iterator()) : this.a.entrySet().iterator();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ac)) {
            return false;
        }
        return this.a.equals(((ac) obj).a);
    }

    /* access modifiers changed from: 0000 */
    public final Iterator<Entry<FieldDescriptorType, Object>> f() {
        return this.c ? new as(this.a.e().iterator()) : this.a.e().iterator();
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
            i += b((ae) b2.getKey(), b2.getValue());
        }
        for (Entry entry : this.a.d()) {
            i += b((ae) entry.getKey(), entry.getValue());
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
