package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class zzacg implements Cloneable {
    private Object value;
    private zzace<?, ?> zzbzl;
    private List<zzacl> zzbzm = new ArrayList();

    zzacg() {
    }

    private final byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zza()];
        zza(zzacb.zzj(bArr));
        return bArr;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzvv */
    public final zzacg clone() {
        Object clone;
        zzacg zzacg = new zzacg();
        try {
            zzacg.zzbzl = this.zzbzl;
            if (this.zzbzm == null) {
                zzacg.zzbzm = null;
            } else {
                zzacg.zzbzm.addAll(this.zzbzm);
            }
            if (this.value != null) {
                if (this.value instanceof zzacj) {
                    clone = (zzacj) ((zzacj) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    clone = ((byte[]) this.value).clone();
                } else {
                    int i = 0;
                    if (this.value instanceof byte[][]) {
                        byte[][] bArr = (byte[][]) this.value;
                        byte[][] bArr2 = new byte[bArr.length][];
                        zzacg.value = bArr2;
                        while (i < bArr.length) {
                            bArr2[i] = (byte[]) bArr[i].clone();
                            i++;
                        }
                    } else if (this.value instanceof boolean[]) {
                        clone = ((boolean[]) this.value).clone();
                    } else if (this.value instanceof int[]) {
                        clone = ((int[]) this.value).clone();
                    } else if (this.value instanceof long[]) {
                        clone = ((long[]) this.value).clone();
                    } else if (this.value instanceof float[]) {
                        clone = ((float[]) this.value).clone();
                    } else if (this.value instanceof double[]) {
                        clone = ((double[]) this.value).clone();
                    } else if (this.value instanceof zzacj[]) {
                        zzacj[] zzacjArr = (zzacj[]) this.value;
                        zzacj[] zzacjArr2 = new zzacj[zzacjArr.length];
                        zzacg.value = zzacjArr2;
                        while (i < zzacjArr.length) {
                            zzacjArr2[i] = (zzacj) zzacjArr[i].clone();
                            i++;
                        }
                    }
                }
                zzacg.value = clone;
                return zzacg;
            }
            return zzacg;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzacg)) {
            return false;
        }
        zzacg zzacg = (zzacg) obj;
        if (this.value == null || zzacg.value == null) {
            if (this.zzbzm != null && zzacg.zzbzm != null) {
                return this.zzbzm.equals(zzacg.zzbzm);
            }
            try {
                return Arrays.equals(toByteArray(), zzacg.toByteArray());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else if (this.zzbzl != zzacg.zzbzl) {
            return false;
        } else {
            return !this.zzbzl.zzbze.isArray() ? this.value.equals(zzacg.value) : this.value instanceof byte[] ? Arrays.equals((byte[]) this.value, (byte[]) zzacg.value) : this.value instanceof int[] ? Arrays.equals((int[]) this.value, (int[]) zzacg.value) : this.value instanceof long[] ? Arrays.equals((long[]) this.value, (long[]) zzacg.value) : this.value instanceof float[] ? Arrays.equals((float[]) this.value, (float[]) zzacg.value) : this.value instanceof double[] ? Arrays.equals((double[]) this.value, (double[]) zzacg.value) : this.value instanceof boolean[] ? Arrays.equals((boolean[]) this.value, (boolean[]) zzacg.value) : Arrays.deepEquals((Object[]) this.value, (Object[]) zzacg.value);
        }
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: 0000 */
    public final int zza() {
        int i;
        if (this.value != null) {
            zzace<?, ?> zzace = this.zzbzl;
            Object obj = this.value;
            if (!zzace.zzbzf) {
                return zzace.zzv(obj);
            }
            int length = Array.getLength(obj);
            i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                if (Array.get(obj, i2) != null) {
                    i += zzace.zzv(Array.get(obj, i2));
                }
            }
        } else {
            i = 0;
            for (zzacl zzacl : this.zzbzm) {
                i += zzacb.zzas(zzacl.tag) + 0 + zzacl.zzbtj.length;
            }
        }
        return i;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzacb zzacb) throws IOException {
        if (this.value != null) {
            zzace<?, ?> zzace = this.zzbzl;
            Object obj = this.value;
            if (zzace.zzbzf) {
                int length = Array.getLength(obj);
                for (int i = 0; i < length; i++) {
                    Object obj2 = Array.get(obj, i);
                    if (obj2 != null) {
                        zzace.zza(obj2, zzacb);
                    }
                }
                return;
            }
            zzace.zza(obj, zzacb);
            return;
        }
        for (zzacl zzacl : this.zzbzm) {
            zzacb.zzar(zzacl.tag);
            zzacb.zzk(zzacl.zzbtj);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzacl zzacl) throws IOException {
        Object obj;
        if (this.zzbzm != null) {
            this.zzbzm.add(zzacl);
            return;
        }
        if (this.value instanceof zzacj) {
            byte[] bArr = zzacl.zzbtj;
            zzaca zza = zzaca.zza(bArr, 0, bArr.length);
            int zzvn = zza.zzvn();
            if (zzvn != bArr.length - zzacb.zzao(zzvn)) {
                throw zzaci.zzvw();
            }
            obj = ((zzacj) this.value).zzb(zza);
        } else if (this.value instanceof zzacj[]) {
            zzacj[] zzacjArr = (zzacj[]) this.zzbzl.zzi(Collections.singletonList(zzacl));
            zzacj[] zzacjArr2 = (zzacj[]) this.value;
            Object obj2 = (zzacj[]) Arrays.copyOf(zzacjArr2, zzacjArr2.length + zzacjArr.length);
            System.arraycopy(zzacjArr, 0, obj2, zzacjArr2.length, zzacjArr.length);
            obj = obj2;
        } else {
            obj = this.zzbzl.zzi(Collections.singletonList(zzacl));
        }
        this.zzbzl = this.zzbzl;
        this.value = obj;
        this.zzbzm = null;
    }
}
