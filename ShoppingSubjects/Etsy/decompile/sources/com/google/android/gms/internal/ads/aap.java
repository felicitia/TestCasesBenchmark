package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class aap implements Cloneable {
    private aan<?, ?> a;
    private Object b;
    private List<aat> c = new ArrayList();

    aap() {
    }

    private final byte[] b() throws IOException {
        byte[] bArr = new byte[a()];
        a(aal.a(bArr));
        return bArr;
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public final aap clone() {
        Object clone;
        aap aap = new aap();
        try {
            aap.a = this.a;
            if (this.c == null) {
                aap.c = null;
            } else {
                aap.c.addAll(this.c);
            }
            if (this.b != null) {
                if (this.b instanceof aar) {
                    clone = (aar) ((aar) this.b).clone();
                } else if (this.b instanceof byte[]) {
                    clone = ((byte[]) this.b).clone();
                } else {
                    int i = 0;
                    if (this.b instanceof byte[][]) {
                        byte[][] bArr = (byte[][]) this.b;
                        byte[][] bArr2 = new byte[bArr.length][];
                        aap.b = bArr2;
                        while (i < bArr.length) {
                            bArr2[i] = (byte[]) bArr[i].clone();
                            i++;
                        }
                    } else if (this.b instanceof boolean[]) {
                        clone = ((boolean[]) this.b).clone();
                    } else if (this.b instanceof int[]) {
                        clone = ((int[]) this.b).clone();
                    } else if (this.b instanceof long[]) {
                        clone = ((long[]) this.b).clone();
                    } else if (this.b instanceof float[]) {
                        clone = ((float[]) this.b).clone();
                    } else if (this.b instanceof double[]) {
                        clone = ((double[]) this.b).clone();
                    } else if (this.b instanceof aar[]) {
                        aar[] aarArr = (aar[]) this.b;
                        aar[] aarArr2 = new aar[aarArr.length];
                        aap.b = aarArr2;
                        while (i < aarArr.length) {
                            aarArr2[i] = (aar) aarArr[i].clone();
                            i++;
                        }
                    }
                }
                aap.b = clone;
                return aap;
            }
            return aap;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /* access modifiers changed from: 0000 */
    public final int a() {
        if (this.b != null) {
            throw new NoSuchMethodError();
        }
        int i = 0;
        for (aat aat : this.c) {
            i += aal.d(aat.a) + 0 + aat.b.length;
        }
        return i;
    }

    /* access modifiers changed from: 0000 */
    public final void a(aal aal) throws IOException {
        if (this.b != null) {
            throw new NoSuchMethodError();
        }
        for (aat aat : this.c) {
            aal.c(aat.a);
            aal.c(aat.b);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(aat aat) throws IOException {
        if (this.c != null) {
            this.c.add(aat);
        } else if (this.b instanceof aar) {
            byte[] bArr = aat.b;
            aaj a2 = aaj.a(bArr, 0, bArr.length);
            int g = a2.g();
            if (g != bArr.length - aal.a(g)) {
                throw zzbfh.zzagq();
            }
            aar a3 = ((aar) this.b).a(a2);
            this.a = this.a;
            this.b = a3;
            this.c = null;
        } else if (this.b instanceof aar[]) {
            Collections.singletonList(aat);
            throw new NoSuchMethodError();
        } else {
            Collections.singletonList(aat);
            throw new NoSuchMethodError();
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof aap)) {
            return false;
        }
        aap aap = (aap) obj;
        if (this.b == null || aap.b == null) {
            if (this.c != null && aap.c != null) {
                return this.c.equals(aap.c);
            }
            try {
                return Arrays.equals(b(), aap.b());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else if (this.a != aap.a) {
            return false;
        } else {
            return !this.a.a.isArray() ? this.b.equals(aap.b) : this.b instanceof byte[] ? Arrays.equals((byte[]) this.b, (byte[]) aap.b) : this.b instanceof int[] ? Arrays.equals((int[]) this.b, (int[]) aap.b) : this.b instanceof long[] ? Arrays.equals((long[]) this.b, (long[]) aap.b) : this.b instanceof float[] ? Arrays.equals((float[]) this.b, (float[]) aap.b) : this.b instanceof double[] ? Arrays.equals((double[]) this.b, (double[]) aap.b) : this.b instanceof boolean[] ? Arrays.equals((boolean[]) this.b, (boolean[]) aap.b) : Arrays.deepEquals((Object[]) this.b, (Object[]) aap.b);
        }
    }

    public final int hashCode() {
        try {
            return 527 + Arrays.hashCode(b());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
