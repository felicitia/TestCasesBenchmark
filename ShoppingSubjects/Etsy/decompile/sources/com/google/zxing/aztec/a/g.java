package com.google.zxing.aztec.a;

import com.google.zxing.common.a;

/* compiled from: Token */
abstract class g {
    static final g a = new e(null, 0, 0);
    private final g b;

    /* access modifiers changed from: 0000 */
    public abstract void a(a aVar, byte[] bArr);

    g(g gVar) {
        this.b = gVar;
    }

    /* access modifiers changed from: 0000 */
    public final g a() {
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public final g a(int i, int i2) {
        return new e(this, i, i2);
    }

    /* access modifiers changed from: 0000 */
    public final g b(int i, int i2) {
        return new b(this, i, i2);
    }
}
