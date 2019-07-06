package com.google.zxing.aztec.a;

import com.google.zxing.common.a;

/* compiled from: SimpleToken */
final class e extends g {
    private final short b;
    private final short c;

    e(g gVar, int i, int i2) {
        super(gVar);
        this.b = (short) i;
        this.c = (short) i2;
    }

    /* access modifiers changed from: 0000 */
    public void a(a aVar, byte[] bArr) {
        aVar.a(this.b, this.c);
    }

    public String toString() {
        short s = (this.b & ((1 << this.c) - 1)) | (1 << this.c);
        StringBuilder sb = new StringBuilder("<");
        sb.append(Integer.toBinaryString(s | (1 << this.c)).substring(1));
        sb.append('>');
        return sb.toString();
    }
}
