package com.google.android.gms.internal.firebase-perf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class zzgd implements Cloneable {
    private Object value;
    private zzgb<?, ?> zzsy;
    private List<zzgi> zzsz = new ArrayList();

    zzgd() {
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzgi zzgi) throws IOException {
        if (this.zzsz != null) {
            this.zzsz.add(zzgi);
        } else if (this.value instanceof zzgg) {
            byte[] bArr = zzgi.zzhw;
            zzfx zzj = zzfx.zzj(bArr, 0, bArr.length);
            int zzck = zzj.zzck();
            if (zzck != bArr.length - zzfy.zzaa(zzck)) {
                throw zzgf.zzgi();
            }
            zzgg zza = ((zzgg) this.value).zza(zzj);
            this.zzsy = this.zzsy;
            this.value = zza;
            this.zzsz = null;
        } else if (this.value instanceof zzgg[]) {
            Collections.singletonList(zzgi);
            throw new NoSuchMethodError();
        } else {
            Collections.singletonList(zzgi);
            throw new NoSuchMethodError();
        }
    }

    /* access modifiers changed from: 0000 */
    public final int zzax() {
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        int i = 0;
        for (zzgi zzgi : this.zzsz) {
            i += zzfy.zzah(zzgi.tag) + 0 + zzgi.zzhw.length;
        }
        return i;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzfy zzfy) throws IOException {
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        for (zzgi zzgi : this.zzsz) {
            zzfy.zzaz(zzgi.tag);
            zzfy.zzh(zzgi.zzhw);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgd)) {
            return false;
        }
        zzgd zzgd = (zzgd) obj;
        if (this.value == null || zzgd.value == null) {
            if (this.zzsz != null && zzgd.zzsz != null) {
                return this.zzsz.equals(zzgd.zzsz);
            }
            try {
                return Arrays.equals(toByteArray(), zzgd.toByteArray());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else if (this.zzsy != zzgd.zzsy) {
            return false;
        } else {
            if (!this.zzsy.zzst.isArray()) {
                return this.value.equals(zzgd.value);
            }
            if (this.value instanceof byte[]) {
                return Arrays.equals((byte[]) this.value, (byte[]) zzgd.value);
            }
            if (this.value instanceof int[]) {
                return Arrays.equals((int[]) this.value, (int[]) zzgd.value);
            }
            if (this.value instanceof long[]) {
                return Arrays.equals((long[]) this.value, (long[]) zzgd.value);
            }
            if (this.value instanceof float[]) {
                return Arrays.equals((float[]) this.value, (float[]) zzgd.value);
            }
            if (this.value instanceof double[]) {
                return Arrays.equals((double[]) this.value, (double[]) zzgd.value);
            }
            if (this.value instanceof boolean[]) {
                return Arrays.equals((boolean[]) this.value, (boolean[]) zzgd.value);
            }
            return Arrays.deepEquals((Object[]) this.value, (Object[]) zzgd.value);
        }
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private final byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzax()];
        zza(zzfy.zzg(bArr));
        return bArr;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzgh */
    public final zzgd clone() {
        zzgd zzgd = new zzgd();
        try {
            zzgd.zzsy = this.zzsy;
            if (this.zzsz == null) {
                zzgd.zzsz = null;
            } else {
                zzgd.zzsz.addAll(this.zzsz);
            }
            if (this.value != null) {
                if (this.value instanceof zzgg) {
                    zzgd.value = (zzgg) ((zzgg) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    zzgd.value = ((byte[]) this.value).clone();
                } else {
                    int i = 0;
                    if (this.value instanceof byte[][]) {
                        byte[][] bArr = (byte[][]) this.value;
                        byte[][] bArr2 = new byte[bArr.length][];
                        zzgd.value = bArr2;
                        while (i < bArr.length) {
                            bArr2[i] = (byte[]) bArr[i].clone();
                            i++;
                        }
                    } else if (this.value instanceof boolean[]) {
                        zzgd.value = ((boolean[]) this.value).clone();
                    } else if (this.value instanceof int[]) {
                        zzgd.value = ((int[]) this.value).clone();
                    } else if (this.value instanceof long[]) {
                        zzgd.value = ((long[]) this.value).clone();
                    } else if (this.value instanceof float[]) {
                        zzgd.value = ((float[]) this.value).clone();
                    } else if (this.value instanceof double[]) {
                        zzgd.value = ((double[]) this.value).clone();
                    } else if (this.value instanceof zzgg[]) {
                        zzgg[] zzggArr = (zzgg[]) this.value;
                        zzgg[] zzggArr2 = new zzgg[zzggArr.length];
                        zzgd.value = zzggArr2;
                        while (i < zzggArr.length) {
                            zzggArr2[i] = (zzgg) zzggArr[i].clone();
                            i++;
                        }
                    }
                }
            }
            return zzgd;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
