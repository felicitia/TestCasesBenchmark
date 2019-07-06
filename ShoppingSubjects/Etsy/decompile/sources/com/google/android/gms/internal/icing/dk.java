package com.google.android.gms.internal.icing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

final class dk implements Cloneable {
    private di<?, ?> a;
    private Object b;
    private List<Object> c = new ArrayList();

    dk() {
    }

    private final byte[] b() throws IOException {
        byte[] bArr = new byte[a()];
        a(dg.a(bArr));
        return bArr;
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public final dk clone() {
        Object clone;
        dk dkVar = new dk();
        try {
            dkVar.a = this.a;
            if (this.c == null) {
                dkVar.c = null;
            } else {
                dkVar.c.addAll(this.c);
            }
            if (this.b != null) {
                if (this.b instanceof dm) {
                    clone = (dm) ((dm) this.b).clone();
                } else if (this.b instanceof byte[]) {
                    clone = ((byte[]) this.b).clone();
                } else {
                    int i = 0;
                    if (this.b instanceof byte[][]) {
                        byte[][] bArr = (byte[][]) this.b;
                        byte[][] bArr2 = new byte[bArr.length][];
                        dkVar.b = bArr2;
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
                    } else if (this.b instanceof dm[]) {
                        dm[] dmVarArr = (dm[]) this.b;
                        dm[] dmVarArr2 = new dm[dmVarArr.length];
                        dkVar.b = dmVarArr2;
                        while (i < dmVarArr.length) {
                            dmVarArr2[i] = (dm) dmVarArr[i].clone();
                            i++;
                        }
                    }
                }
                dkVar.b = clone;
                return dkVar;
            }
            return dkVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /* access modifiers changed from: 0000 */
    public final int a() {
        if (this.b != null) {
            throw new NoSuchMethodError();
        }
        Iterator it = this.c.iterator();
        if (!it.hasNext()) {
            return 0;
        }
        it.next();
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: 0000 */
    public final void a(dg dgVar) throws IOException {
        if (this.b != null) {
            throw new NoSuchMethodError();
        }
        Iterator it = this.c.iterator();
        if (it.hasNext()) {
            it.next();
            throw new NoSuchMethodError();
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof dk)) {
            return false;
        }
        dk dkVar = (dk) obj;
        if (this.b == null || dkVar.b == null) {
            if (this.c != null && dkVar.c != null) {
                return this.c.equals(dkVar.c);
            }
            try {
                return Arrays.equals(b(), dkVar.b());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else if (this.a != dkVar.a) {
            return false;
        } else {
            return !this.a.a.isArray() ? this.b.equals(dkVar.b) : this.b instanceof byte[] ? Arrays.equals((byte[]) this.b, (byte[]) dkVar.b) : this.b instanceof int[] ? Arrays.equals((int[]) this.b, (int[]) dkVar.b) : this.b instanceof long[] ? Arrays.equals((long[]) this.b, (long[]) dkVar.b) : this.b instanceof float[] ? Arrays.equals((float[]) this.b, (float[]) dkVar.b) : this.b instanceof double[] ? Arrays.equals((double[]) this.b, (double[]) dkVar.b) : this.b instanceof boolean[] ? Arrays.equals((boolean[]) this.b, (boolean[]) dkVar.b) : Arrays.deepEquals((Object[]) this.b, (Object[]) dkVar.b);
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
