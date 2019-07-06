package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.ah.d;
import java.io.IOException;
import java.util.Arrays;

public final class cs {
    private static final cs a = new cs(0, new int[0], new Object[0], false);
    private int b;
    private int[] c;
    private Object[] d;
    private int e;
    private boolean f;

    private cs() {
        this(0, new int[8], new Object[8], true);
    }

    private cs(int i, int[] iArr, Object[] objArr, boolean z) {
        this.e = -1;
        this.b = i;
        this.c = iArr;
        this.d = objArr;
        this.f = z;
    }

    public static cs a() {
        return a;
    }

    static cs a(cs csVar, cs csVar2) {
        int i = csVar.b + csVar2.b;
        int[] copyOf = Arrays.copyOf(csVar.c, i);
        System.arraycopy(csVar2.c, 0, copyOf, csVar.b, csVar2.b);
        Object[] copyOf2 = Arrays.copyOf(csVar.d, i);
        System.arraycopy(csVar2.d, 0, copyOf2, csVar.b, csVar2.b);
        return new cs(i, copyOf, copyOf2, true);
    }

    private static void a(int i, Object obj, df dfVar) throws IOException {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 != 5) {
            switch (i3) {
                case 0:
                    dfVar.a(i2, ((Long) obj).longValue());
                    return;
                case 1:
                    dfVar.d(i2, ((Long) obj).longValue());
                    return;
                case 2:
                    dfVar.a(i2, (zzbi) obj);
                    return;
                case 3:
                    if (dfVar.a() == d.j) {
                        dfVar.a(i2);
                        ((cs) obj).b(dfVar);
                        dfVar.b(i2);
                        return;
                    }
                    dfVar.b(i2);
                    ((cs) obj).b(dfVar);
                    dfVar.a(i2);
                    return;
                default:
                    throw new RuntimeException(zzcs.zzbd());
            }
        } else {
            dfVar.d(i2, ((Integer) obj).intValue());
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(df dfVar) throws IOException {
        if (dfVar.a() == d.k) {
            for (int i = this.b - 1; i >= 0; i--) {
                dfVar.a(this.c[i] >>> 3, this.d[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.b; i2++) {
            dfVar.a(this.c[i2] >>> 3, this.d[i2]);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.b; i2++) {
            bo.a(sb, i, String.valueOf(this.c[i2] >>> 3), this.d[i2]);
        }
    }

    public final void b() {
        this.f = false;
    }

    public final void b(df dfVar) throws IOException {
        if (this.b != 0) {
            if (dfVar.a() == d.j) {
                for (int i = 0; i < this.b; i++) {
                    a(this.c[i], this.d[i], dfVar);
                }
                return;
            }
            for (int i2 = this.b - 1; i2 >= 0; i2--) {
                a(this.c[i2], this.d[i2], dfVar);
            }
        }
    }

    public final int c() {
        int i = this.e;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.b; i3++) {
            i2 += zzbu.d(this.c[i3] >>> 3, (zzbi) this.d[i3]);
        }
        this.e = i2;
        return i2;
    }

    public final int d() {
        int i;
        int i2 = this.e;
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.b; i4++) {
            int i5 = this.c[i4];
            int i6 = i5 >>> 3;
            int i7 = i5 & 7;
            if (i7 != 5) {
                switch (i7) {
                    case 0:
                        i = zzbu.e(i6, ((Long) this.d[i4]).longValue());
                        break;
                    case 1:
                        i = zzbu.g(i6, ((Long) this.d[i4]).longValue());
                        break;
                    case 2:
                        i = zzbu.c(i6, (zzbi) this.d[i4]);
                        break;
                    case 3:
                        i = (zzbu.e(i6) << 1) + ((cs) this.d[i4]).d();
                        break;
                    default:
                        throw new IllegalStateException(zzcs.zzbd());
                }
            } else {
                i = zzbu.i(i6, ((Integer) this.d[i4]).intValue());
            }
            i3 += i;
        }
        this.e = i3;
        return i3;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof cs)) {
            return false;
        }
        cs csVar = (cs) obj;
        if (this.b == csVar.b) {
            int[] iArr = this.c;
            int[] iArr2 = csVar.c;
            int i = this.b;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    z = true;
                    break;
                } else if (iArr[i2] != iArr2[i2]) {
                    z = false;
                    break;
                } else {
                    i2++;
                }
            }
            if (z) {
                Object[] objArr = this.d;
                Object[] objArr2 = csVar.d;
                int i3 = this.b;
                int i4 = 0;
                while (true) {
                    if (i4 >= i3) {
                        z2 = true;
                        break;
                    } else if (!objArr[i4].equals(objArr2[i4])) {
                        z2 = false;
                        break;
                    } else {
                        i4++;
                    }
                }
                return z2;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = (527 + this.b) * 31;
        int[] iArr = this.c;
        int i2 = 17;
        int i3 = 17;
        for (int i4 = 0; i4 < this.b; i4++) {
            i3 = (i3 * 31) + iArr[i4];
        }
        int i5 = (i + i3) * 31;
        Object[] objArr = this.d;
        for (int i6 = 0; i6 < this.b; i6++) {
            i2 = (i2 * 31) + objArr[i6].hashCode();
        }
        return i5 + i2;
    }
}
