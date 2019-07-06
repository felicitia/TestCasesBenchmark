package kotlin.jvm.internal;

import kotlin.collections.ab;
import kotlin.collections.ad;
import kotlin.collections.ae;
import kotlin.collections.ap;
import kotlin.collections.l;
import kotlin.collections.m;
import kotlin.collections.n;
import kotlin.collections.z;

/* compiled from: ArrayIterators.kt */
public final class i {
    public static final m a(byte[] bArr) {
        p.b(bArr, "array");
        return new b(bArr);
    }

    public static final n a(char[] cArr) {
        p.b(cArr, "array");
        return new c(cArr);
    }

    public static final ap a(short[] sArr) {
        p.b(sArr, "array");
        return new k(sArr);
    }

    public static final ad a(int[] iArr) {
        p.b(iArr, "array");
        return new f(iArr);
    }

    public static final ae a(long[] jArr) {
        p.b(jArr, "array");
        return new j(jArr);
    }

    public static final ab a(float[] fArr) {
        p.b(fArr, "array");
        return new e(fArr);
    }

    public static final z a(double[] dArr) {
        p.b(dArr, "array");
        return new d(dArr);
    }

    public static final l a(boolean[] zArr) {
        p.b(zArr, "array");
        return new a(zArr);
    }
}
