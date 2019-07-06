package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class h implements Cloneable {
    private f<?, ?> a;
    private Object b;
    private List<l> c = new ArrayList();

    h() {
    }

    private final byte[] b() throws IOException {
        byte[] bArr = new byte[a()];
        a(d.a(bArr));
        return bArr;
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public final h clone() {
        Object clone;
        h hVar = new h();
        try {
            hVar.a = this.a;
            if (this.c == null) {
                hVar.c = null;
            } else {
                hVar.c.addAll(this.c);
            }
            if (this.b != null) {
                if (this.b instanceof j) {
                    clone = (j) ((j) this.b).clone();
                } else if (this.b instanceof byte[]) {
                    clone = ((byte[]) this.b).clone();
                } else {
                    int i = 0;
                    if (this.b instanceof byte[][]) {
                        byte[][] bArr = (byte[][]) this.b;
                        byte[][] bArr2 = new byte[bArr.length][];
                        hVar.b = bArr2;
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
                    } else if (this.b instanceof j[]) {
                        j[] jVarArr = (j[]) this.b;
                        j[] jVarArr2 = new j[jVarArr.length];
                        hVar.b = jVarArr2;
                        while (i < jVarArr.length) {
                            jVarArr2[i] = (j) jVarArr[i].clone();
                            i++;
                        }
                    }
                }
                hVar.b = clone;
                return hVar;
            }
            return hVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /* access modifiers changed from: 0000 */
    public final int a() {
        int i;
        if (this.b != null) {
            f<?, ?> fVar = this.a;
            Object obj = this.b;
            if (!fVar.c) {
                return fVar.a(obj);
            }
            int length = Array.getLength(obj);
            i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                if (Array.get(obj, i2) != null) {
                    i += fVar.a(Array.get(obj, i2));
                }
            }
        } else {
            i = 0;
            for (l lVar : this.c) {
                i += d.d(lVar.a) + 0 + lVar.b.length;
            }
        }
        return i;
    }

    /* access modifiers changed from: 0000 */
    public final void a(d dVar) throws IOException {
        if (this.b != null) {
            f<?, ?> fVar = this.a;
            Object obj = this.b;
            if (fVar.c) {
                int length = Array.getLength(obj);
                for (int i = 0; i < length; i++) {
                    Object obj2 = Array.get(obj, i);
                    if (obj2 != null) {
                        fVar.a(obj2, dVar);
                    }
                }
                return;
            }
            fVar.a(obj, dVar);
            return;
        }
        for (l lVar : this.c) {
            dVar.c(lVar.a);
            dVar.b(lVar.b);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(l lVar) throws IOException {
        Object obj;
        if (this.c != null) {
            this.c.add(lVar);
            return;
        }
        if (this.b instanceof j) {
            byte[] bArr = lVar.b;
            c a2 = c.a(bArr, 0, bArr.length);
            int d = a2.d();
            if (d != bArr.length - d.a(d)) {
                throw zzaci.zzvw();
            }
            obj = ((j) this.b).a(a2);
        } else if (this.b instanceof j[]) {
            j[] jVarArr = (j[]) this.a.a(Collections.singletonList(lVar));
            j[] jVarArr2 = (j[]) this.b;
            Object obj2 = (j[]) Arrays.copyOf(jVarArr2, jVarArr2.length + jVarArr.length);
            System.arraycopy(jVarArr, 0, obj2, jVarArr2.length, jVarArr.length);
            obj = obj2;
        } else {
            obj = this.a.a(Collections.singletonList(lVar));
        }
        this.a = this.a;
        this.b = obj;
        this.c = null;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        if (this.b == null || hVar.b == null) {
            if (this.c != null && hVar.c != null) {
                return this.c.equals(hVar.c);
            }
            try {
                return Arrays.equals(b(), hVar.b());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else if (this.a != hVar.a) {
            return false;
        } else {
            return !this.a.a.isArray() ? this.b.equals(hVar.b) : this.b instanceof byte[] ? Arrays.equals((byte[]) this.b, (byte[]) hVar.b) : this.b instanceof int[] ? Arrays.equals((int[]) this.b, (int[]) hVar.b) : this.b instanceof long[] ? Arrays.equals((long[]) this.b, (long[]) hVar.b) : this.b instanceof float[] ? Arrays.equals((float[]) this.b, (float[]) hVar.b) : this.b instanceof double[] ? Arrays.equals((double[]) this.b, (double[]) hVar.b) : this.b instanceof boolean[] ? Arrays.equals((boolean[]) this.b, (boolean[]) hVar.b) : Arrays.deepEquals((Object[]) this.b, (Object[]) hVar.b);
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
