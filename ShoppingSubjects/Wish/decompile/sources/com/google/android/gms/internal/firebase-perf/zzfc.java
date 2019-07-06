package com.google.android.gms.internal.firebase-perf;

import com.google.android.gms.internal.firebase-perf.zzcm.zze;
import java.io.IOException;
import java.util.Arrays;

public final class zzfc {
    private static final zzfc zzqe = new zzfc(0, new int[0], new Object[0], false);
    private int count;
    private boolean zzhj;
    private int zzmd;
    private Object[] zzoq;
    private int[] zzqf;

    public static zzfc zzfs() {
        return zzqe;
    }

    static zzfc zzft() {
        return new zzfc();
    }

    static zzfc zza(zzfc zzfc, zzfc zzfc2) {
        int i = zzfc.count + zzfc2.count;
        int[] copyOf = Arrays.copyOf(zzfc.zzqf, i);
        System.arraycopy(zzfc2.zzqf, 0, copyOf, zzfc.count, zzfc2.count);
        Object[] copyOf2 = Arrays.copyOf(zzfc.zzoq, i);
        System.arraycopy(zzfc2.zzoq, 0, copyOf2, zzfc.count, zzfc2.count);
        return new zzfc(i, copyOf, copyOf2, true);
    }

    private zzfc() {
        this(0, new int[8], new Object[8], true);
    }

    private zzfc(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zzmd = -1;
        this.count = i;
        this.zzqf = iArr;
        this.zzoq = objArr;
        this.zzhj = z;
    }

    public final void zzbi() {
        this.zzhj = false;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzfw zzfw) throws IOException {
        if (zzfw.zzcv() == zze.zzmv) {
            for (int i = this.count - 1; i >= 0; i--) {
                zzfw.zza(this.zzqf[i] >>> 3, this.zzoq[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.count; i2++) {
            zzfw.zza(this.zzqf[i2] >>> 3, this.zzoq[i2]);
        }
    }

    public final void zzb(zzfw zzfw) throws IOException {
        if (this.count != 0) {
            if (zzfw.zzcv() == zze.zzmu) {
                for (int i = 0; i < this.count; i++) {
                    zzb(this.zzqf[i], this.zzoq[i], zzfw);
                }
                return;
            }
            for (int i2 = this.count - 1; i2 >= 0; i2--) {
                zzb(this.zzqf[i2], this.zzoq[i2], zzfw);
            }
        }
    }

    private static void zzb(int i, Object obj, zzfw zzfw) throws IOException {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 != 5) {
            switch (i3) {
                case 0:
                    zzfw.zzi(i2, ((Long) obj).longValue());
                    return;
                case 1:
                    zzfw.zzc(i2, ((Long) obj).longValue());
                    return;
                case 2:
                    zzfw.zza(i2, (zzbd) obj);
                    return;
                case 3:
                    if (zzfw.zzcv() == zze.zzmu) {
                        zzfw.zzai(i2);
                        ((zzfc) obj).zzb(zzfw);
                        zzfw.zzaj(i2);
                        return;
                    }
                    zzfw.zzaj(i2);
                    ((zzfc) obj).zzb(zzfw);
                    zzfw.zzai(i2);
                    return;
                default:
                    throw new RuntimeException(zzct.zzeb());
            }
        } else {
            zzfw.zzf(i2, ((Integer) obj).intValue());
        }
    }

    public final int zzfu() {
        int i = this.zzmd;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.count; i3++) {
            i2 += zzbt.zzd(this.zzqf[i3] >>> 3, (zzbd) this.zzoq[i3]);
        }
        this.zzmd = i2;
        return i2;
    }

    public final int zzdg() {
        int i;
        int i2 = this.zzmd;
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.count; i4++) {
            int i5 = this.zzqf[i4];
            int i6 = i5 >>> 3;
            int i7 = i5 & 7;
            if (i7 != 5) {
                switch (i7) {
                    case 0:
                        i = zzbt.zze(i6, ((Long) this.zzoq[i4]).longValue());
                        break;
                    case 1:
                        i = zzbt.zzg(i6, ((Long) this.zzoq[i4]).longValue());
                        break;
                    case 2:
                        i = zzbt.zzc(i6, (zzbd) this.zzoq[i4]);
                        break;
                    case 3:
                        i = (zzbt.zzz(i6) << 1) + ((zzfc) this.zzoq[i4]).zzdg();
                        break;
                    default:
                        throw new IllegalStateException(zzct.zzeb());
                }
            } else {
                i = zzbt.zzj(i6, ((Integer) this.zzoq[i4]).intValue());
            }
            i3 += i;
        }
        this.zzmd = i3;
        return i3;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzfc)) {
            return false;
        }
        zzfc zzfc = (zzfc) obj;
        if (this.count == zzfc.count) {
            int[] iArr = this.zzqf;
            int[] iArr2 = zzfc.zzqf;
            int i = this.count;
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
                Object[] objArr = this.zzoq;
                Object[] objArr2 = zzfc.zzoq;
                int i3 = this.count;
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
    }

    public final int hashCode() {
        int i = (this.count + 527) * 31;
        int[] iArr = this.zzqf;
        int i2 = 17;
        int i3 = 17;
        for (int i4 = 0; i4 < this.count; i4++) {
            i3 = (i3 * 31) + iArr[i4];
        }
        int i5 = (i + i3) * 31;
        Object[] objArr = this.zzoq;
        for (int i6 = 0; i6 < this.count; i6++) {
            i2 = (i2 * 31) + objArr[i6].hashCode();
        }
        return i5 + i2;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            zzdw.zza(sb, i, String.valueOf(this.zzqf[i2] >>> 3), this.zzoq[i2]);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(int i, Object obj) {
        if (!this.zzhj) {
            throw new UnsupportedOperationException();
        }
        if (this.count == this.zzqf.length) {
            int i2 = this.count + (this.count < 4 ? 8 : this.count >> 1);
            this.zzqf = Arrays.copyOf(this.zzqf, i2);
            this.zzoq = Arrays.copyOf(this.zzoq, i2);
        }
        this.zzqf[this.count] = i;
        this.zzoq[this.count] = obj;
        this.count++;
    }
}
