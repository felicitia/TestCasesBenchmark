package com.google.android.gms.internal.ads;

import java.lang.reflect.Field;
import java.util.Arrays;

final class za {
    private int A;
    private int B;
    private Field C;
    private Object D;
    private Object E;
    private Object F;
    private final zb a;
    private final Object[] b;
    private Class<?> c;
    /* access modifiers changed from: private */
    public final int d;
    /* access modifiers changed from: private */
    public final int e;
    private final int f;
    private final int g;
    /* access modifiers changed from: private */
    public final int h;
    /* access modifiers changed from: private */
    public final int i;
    /* access modifiers changed from: private */
    public final int j;
    /* access modifiers changed from: private */
    public final int k;
    /* access modifiers changed from: private */
    public final int l;
    /* access modifiers changed from: private */
    public final int m;
    /* access modifiers changed from: private */
    public final int[] n;
    private int o;
    private int p;
    private int q = Integer.MAX_VALUE;
    private int r = Integer.MIN_VALUE;
    private int s = 0;
    private int t = 0;
    private int u = 0;
    private int v = 0;
    private int w = 0;
    private int x;
    private int y;
    private int z;

    za(Class<?> cls, String str, Object[] objArr) {
        this.c = cls;
        this.a = new zb(str);
        this.b = objArr;
        this.d = this.a.b();
        this.e = this.a.b();
        int[] iArr = null;
        if (this.e == 0) {
            this.f = 0;
            this.g = 0;
            this.h = 0;
            this.i = 0;
            this.j = 0;
            this.l = 0;
            this.k = 0;
            this.m = 0;
            this.n = null;
            return;
        }
        this.f = this.a.b();
        this.g = this.a.b();
        this.h = this.a.b();
        this.i = this.a.b();
        this.l = this.a.b();
        this.k = this.a.b();
        this.j = this.a.b();
        this.m = this.a.b();
        int b2 = this.a.b();
        if (b2 != 0) {
            iArr = new int[b2];
        }
        this.n = iArr;
        this.o = (this.f << 1) + this.g;
    }

    private static Field a(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(40 + String.valueOf(str).length() + String.valueOf(name).length() + String.valueOf(arrays).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(arrays);
            throw new RuntimeException(sb.toString());
        }
    }

    private final Object p() {
        Object[] objArr = this.b;
        int i2 = this.o;
        this.o = i2 + 1;
        return objArr[i2];
    }

    private final boolean q() {
        return (this.d & 1) == 1;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00d5, code lost:
        if (q() != false) goto L_0x00d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0161, code lost:
        if (r1 != false) goto L_0x00d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0169, code lost:
        if (q() != false) goto L_0x00d7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a() {
        /*
            r5 = this;
            com.google.android.gms.internal.ads.zb r0 = r5.a
            boolean r0 = r0.a()
            r1 = 0
            if (r0 != 0) goto L_0x000a
            return r1
        L_0x000a:
            com.google.android.gms.internal.ads.zb r0 = r5.a
            int r0 = r0.b()
            r5.x = r0
            com.google.android.gms.internal.ads.zb r0 = r5.a
            int r0 = r0.b()
            r5.y = r0
            int r0 = r5.y
            r0 = r0 & 255(0xff, float:3.57E-43)
            r5.z = r0
            int r0 = r5.x
            int r2 = r5.q
            if (r0 >= r2) goto L_0x002a
            int r0 = r5.x
            r5.q = r0
        L_0x002a:
            int r0 = r5.x
            int r2 = r5.r
            if (r0 <= r2) goto L_0x0034
            int r0 = r5.x
            r5.r = r0
        L_0x0034:
            int r0 = r5.z
            com.google.android.gms.internal.ads.zzbbj r2 = com.google.android.gms.internal.ads.zzbbj.MAP
            int r2 = r2.id()
            r3 = 1
            if (r0 != r2) goto L_0x0045
            int r0 = r5.s
            int r0 = r0 + r3
            r5.s = r0
            goto L_0x005e
        L_0x0045:
            int r0 = r5.z
            com.google.android.gms.internal.ads.zzbbj r2 = com.google.android.gms.internal.ads.zzbbj.DOUBLE_LIST
            int r2 = r2.id()
            if (r0 < r2) goto L_0x005e
            int r0 = r5.z
            com.google.android.gms.internal.ads.zzbbj r2 = com.google.android.gms.internal.ads.zzbbj.GROUP_LIST
            int r2 = r2.id()
            if (r0 > r2) goto L_0x005e
            int r0 = r5.t
            int r0 = r0 + r3
            r5.t = r0
        L_0x005e:
            int r0 = r5.w
            int r0 = r0 + r3
            r5.w = r0
            int r0 = r5.q
            int r2 = r5.x
            int r4 = r5.w
            boolean r0 = com.google.android.gms.internal.ads.zf.a(r0, r2, r4)
            if (r0 == 0) goto L_0x007c
            int r0 = r5.x
            int r0 = r0 + r3
            r5.v = r0
            int r0 = r5.v
            int r2 = r5.q
            int r0 = r0 - r2
        L_0x0079:
            r5.u = r0
            goto L_0x0080
        L_0x007c:
            int r0 = r5.u
            int r0 = r0 + r3
            goto L_0x0079
        L_0x0080:
            int r0 = r5.y
            r0 = r0 & 1024(0x400, float:1.435E-42)
            if (r0 == 0) goto L_0x0088
            r0 = r3
            goto L_0x0089
        L_0x0088:
            r0 = r1
        L_0x0089:
            if (r0 == 0) goto L_0x0097
            int[] r0 = r5.n
            int r2 = r5.p
            int r4 = r2 + 1
            r5.p = r4
            int r4 = r5.x
            r0[r2] = r4
        L_0x0097:
            r0 = 0
            r5.D = r0
            r5.E = r0
            r5.F = r0
            boolean r0 = r5.d()
            if (r0 == 0) goto L_0x00e5
            com.google.android.gms.internal.ads.zb r0 = r5.a
            int r0 = r0.b()
            r5.A = r0
            int r0 = r5.z
            com.google.android.gms.internal.ads.zzbbj r1 = com.google.android.gms.internal.ads.zzbbj.MESSAGE
            int r1 = r1.id()
            int r1 = r1 + 51
            if (r0 == r1) goto L_0x00de
            int r0 = r5.z
            com.google.android.gms.internal.ads.zzbbj r1 = com.google.android.gms.internal.ads.zzbbj.GROUP
            int r1 = r1.id()
            int r1 = r1 + 51
            if (r0 != r1) goto L_0x00c5
            goto L_0x00de
        L_0x00c5:
            int r0 = r5.z
            com.google.android.gms.internal.ads.zzbbj r1 = com.google.android.gms.internal.ads.zzbbj.ENUM
            int r1 = r1.id()
            int r1 = r1 + 51
            if (r0 != r1) goto L_0x0175
            boolean r0 = r5.q()
            if (r0 == 0) goto L_0x0175
        L_0x00d7:
            java.lang.Object r0 = r5.p()
            r5.E = r0
            return r3
        L_0x00de:
            java.lang.Object r0 = r5.p()
        L_0x00e2:
            r5.D = r0
            return r3
        L_0x00e5:
            java.lang.Class<?> r0 = r5.c
            java.lang.Object r2 = r5.p()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.reflect.Field r0 = a(r0, r2)
            r5.C = r0
            boolean r0 = r5.h()
            if (r0 == 0) goto L_0x0101
            com.google.android.gms.internal.ads.zb r0 = r5.a
            int r0 = r0.b()
            r5.B = r0
        L_0x0101:
            int r0 = r5.z
            com.google.android.gms.internal.ads.zzbbj r2 = com.google.android.gms.internal.ads.zzbbj.MESSAGE
            int r2 = r2.id()
            if (r0 == r2) goto L_0x016d
            int r0 = r5.z
            com.google.android.gms.internal.ads.zzbbj r2 = com.google.android.gms.internal.ads.zzbbj.GROUP
            int r2 = r2.id()
            if (r0 != r2) goto L_0x0116
            goto L_0x016d
        L_0x0116:
            int r0 = r5.z
            com.google.android.gms.internal.ads.zzbbj r2 = com.google.android.gms.internal.ads.zzbbj.MESSAGE_LIST
            int r2 = r2.id()
            if (r0 == r2) goto L_0x00de
            int r0 = r5.z
            com.google.android.gms.internal.ads.zzbbj r2 = com.google.android.gms.internal.ads.zzbbj.GROUP_LIST
            int r2 = r2.id()
            if (r0 != r2) goto L_0x012b
            goto L_0x00de
        L_0x012b:
            int r0 = r5.z
            com.google.android.gms.internal.ads.zzbbj r2 = com.google.android.gms.internal.ads.zzbbj.ENUM
            int r2 = r2.id()
            if (r0 == r2) goto L_0x0165
            int r0 = r5.z
            com.google.android.gms.internal.ads.zzbbj r2 = com.google.android.gms.internal.ads.zzbbj.ENUM_LIST
            int r2 = r2.id()
            if (r0 == r2) goto L_0x0165
            int r0 = r5.z
            com.google.android.gms.internal.ads.zzbbj r2 = com.google.android.gms.internal.ads.zzbbj.ENUM_LIST_PACKED
            int r2 = r2.id()
            if (r0 != r2) goto L_0x014a
            goto L_0x0165
        L_0x014a:
            int r0 = r5.z
            com.google.android.gms.internal.ads.zzbbj r2 = com.google.android.gms.internal.ads.zzbbj.MAP
            int r2 = r2.id()
            if (r0 != r2) goto L_0x0175
            java.lang.Object r0 = r5.p()
            r5.F = r0
            int r0 = r5.y
            r0 = r0 & 2048(0x800, float:2.87E-42)
            if (r0 == 0) goto L_0x0161
            r1 = r3
        L_0x0161:
            if (r1 == 0) goto L_0x0175
            goto L_0x00d7
        L_0x0165:
            boolean r0 = r5.q()
            if (r0 == 0) goto L_0x0175
            goto L_0x00d7
        L_0x016d:
            java.lang.reflect.Field r0 = r5.C
            java.lang.Class r0 = r0.getType()
            goto L_0x00e2
        L_0x0175:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.za.a():boolean");
    }

    /* access modifiers changed from: 0000 */
    public final int b() {
        return this.x;
    }

    /* access modifiers changed from: 0000 */
    public final int c() {
        return this.z;
    }

    /* access modifiers changed from: 0000 */
    public final boolean d() {
        return this.z > zzbbj.MAP.id();
    }

    /* access modifiers changed from: 0000 */
    public final Field e() {
        int i2 = this.A << 1;
        Object obj = this.b[i2];
        if (obj instanceof Field) {
            return (Field) obj;
        }
        Field a2 = a(this.c, (String) obj);
        this.b[i2] = a2;
        return a2;
    }

    /* access modifiers changed from: 0000 */
    public final Field f() {
        int i2 = (this.A << 1) + 1;
        Object obj = this.b[i2];
        if (obj instanceof Field) {
            return (Field) obj;
        }
        Field a2 = a(this.c, (String) obj);
        this.b[i2] = a2;
        return a2;
    }

    /* access modifiers changed from: 0000 */
    public final Field g() {
        return this.C;
    }

    /* access modifiers changed from: 0000 */
    public final boolean h() {
        return q() && this.z <= zzbbj.GROUP.id();
    }

    /* access modifiers changed from: 0000 */
    public final Field i() {
        int i2 = (this.f << 1) + (this.B / 32);
        Object obj = this.b[i2];
        if (obj instanceof Field) {
            return (Field) obj;
        }
        Field a2 = a(this.c, (String) obj);
        this.b[i2] = a2;
        return a2;
    }

    /* access modifiers changed from: 0000 */
    public final int j() {
        return this.B % 32;
    }

    /* access modifiers changed from: 0000 */
    public final boolean k() {
        return (this.y & 256) != 0;
    }

    /* access modifiers changed from: 0000 */
    public final boolean l() {
        return (this.y & 512) != 0;
    }

    /* access modifiers changed from: 0000 */
    public final Object m() {
        return this.D;
    }

    /* access modifiers changed from: 0000 */
    public final Object n() {
        return this.E;
    }

    /* access modifiers changed from: 0000 */
    public final Object o() {
        return this.F;
    }
}
