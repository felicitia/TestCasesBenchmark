package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.xh.e;
import java.io.IOException;
import java.util.Arrays;

public final class zw {
    private static final zw a = new zw(0, new int[0], new Object[0], false);
    private int b;
    private int[] c;
    private Object[] d;
    private int e;
    private boolean f;

    private zw() {
        this(0, new int[8], new Object[8], true);
    }

    private zw(int i, int[] iArr, Object[] objArr, boolean z) {
        this.e = -1;
        this.b = i;
        this.c = iArr;
        this.d = objArr;
        this.f = z;
    }

    public static zw a() {
        return a;
    }

    static zw a(zw zwVar, zw zwVar2) {
        int i = zwVar.b + zwVar2.b;
        int[] copyOf = Arrays.copyOf(zwVar.c, i);
        System.arraycopy(zwVar2.c, 0, copyOf, zwVar.b, zwVar2.b);
        Object[] copyOf2 = Arrays.copyOf(zwVar.d, i);
        System.arraycopy(zwVar2.d, 0, copyOf2, zwVar.b, zwVar2.b);
        return new zw(i, copyOf, copyOf2, true);
    }

    private static void a(int i, Object obj, aai aai) throws IOException {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 != 5) {
            switch (i3) {
                case 0:
                    aai.a(i2, ((Long) obj).longValue());
                    return;
                case 1:
                    aai.d(i2, ((Long) obj).longValue());
                    return;
                case 2:
                    aai.a(i2, (zzbah) obj);
                    return;
                case 3:
                    if (aai.a() == e.j) {
                        aai.a(i2);
                        ((zw) obj).b(aai);
                        aai.b(i2);
                        return;
                    }
                    aai.b(i2);
                    ((zw) obj).b(aai);
                    aai.a(i2);
                    return;
                default:
                    throw new RuntimeException(zzbbu.zzadq());
            }
        } else {
            aai.d(i2, ((Integer) obj).intValue());
        }
    }

    static zw b() {
        return new zw();
    }

    /* access modifiers changed from: 0000 */
    public final void a(int i, Object obj) {
        if (!this.f) {
            throw new UnsupportedOperationException();
        }
        if (this.b == this.c.length) {
            int i2 = this.b + (this.b < 4 ? 8 : this.b >> 1);
            this.c = Arrays.copyOf(this.c, i2);
            this.d = Arrays.copyOf(this.d, i2);
        }
        this.c[this.b] = i;
        this.d[this.b] = obj;
        this.b++;
    }

    /* access modifiers changed from: 0000 */
    public final void a(aai aai) throws IOException {
        if (aai.a() == e.k) {
            for (int i = this.b - 1; i >= 0; i--) {
                aai.a(this.c[i] >>> 3, this.d[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.b; i2++) {
            aai.a(this.c[i2] >>> 3, this.d[i2]);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.b; i2++) {
            yn.a(sb, i, String.valueOf(this.c[i2] >>> 3), this.d[i2]);
        }
    }

    public final void b(aai aai) throws IOException {
        if (this.b != 0) {
            if (aai.a() == e.j) {
                for (int i = 0; i < this.b; i++) {
                    a(this.c[i], this.d[i], aai);
                }
                return;
            }
            for (int i2 = this.b - 1; i2 >= 0; i2--) {
                a(this.c[i2], this.d[i2], aai);
            }
        }
    }

    public final void c() {
        this.f = false;
    }

    public final int d() {
        int i = this.e;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.b; i3++) {
            i2 += zzbav.d(this.c[i3] >>> 3, (zzbah) this.d[i3]);
        }
        this.e = i2;
        return i2;
    }

    public final int e() {
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
                        i = zzbav.e(i6, ((Long) this.d[i4]).longValue());
                        break;
                    case 1:
                        i = zzbav.g(i6, ((Long) this.d[i4]).longValue());
                        break;
                    case 2:
                        i = zzbav.c(i6, (zzbah) this.d[i4]);
                        break;
                    case 3:
                        i = (zzbav.e(i6) << 1) + ((zw) this.d[i4]).e();
                        break;
                    default:
                        throw new IllegalStateException(zzbbu.zzadq());
                }
            } else {
                i = zzbav.i(i6, ((Integer) this.d[i4]).intValue());
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
        if (obj == null || !(obj instanceof zw)) {
            return false;
        }
        zw zwVar = (zw) obj;
        if (this.b == zwVar.b) {
            int[] iArr = this.c;
            int[] iArr2 = zwVar.c;
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
                Object[] objArr2 = zwVar.d;
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
