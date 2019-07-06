package com.google.zxing.aztec.a;

import com.google.zxing.common.a;

/* compiled from: BinaryShiftToken */
final class b extends g {
    private final short b;
    private final short c;

    b(g gVar, int i, int i2) {
        super(gVar);
        this.b = (short) i;
        this.c = (short) i2;
    }

    public void a(a aVar, byte[] bArr) {
        for (int i = 0; i < this.c; i++) {
            if (i == 0 || (i == 31 && this.c <= 62)) {
                aVar.a(31, 5);
                if (this.c > 62) {
                    aVar.a(this.c - 31, 16);
                } else if (i == 0) {
                    aVar.a(Math.min(this.c, 31), 5);
                } else {
                    aVar.a(this.c - 31, 5);
                }
            }
            aVar.a(bArr[this.b + i], 8);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("<");
        sb.append(this.b);
        sb.append("::");
        sb.append((this.b + this.c) - 1);
        sb.append('>');
        return sb.toString();
    }
}
