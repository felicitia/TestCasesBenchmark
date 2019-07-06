package com.google.zxing.common.reedsolomon;

import java.util.ArrayList;
import java.util.List;

/* compiled from: ReedSolomonEncoder */
public final class c {
    private final a a;
    private final List<b> b = new ArrayList();

    public c(a aVar) {
        this.a = aVar;
        this.b.add(new b(aVar, new int[]{1}));
    }

    private b a(int i) {
        if (i >= this.b.size()) {
            b bVar = (b) this.b.get(this.b.size() - 1);
            for (int size = this.b.size(); size <= i; size++) {
                bVar = bVar.b(new b(this.a, new int[]{1, this.a.a((size - 1) + this.a.b())}));
                this.b.add(bVar);
            }
        }
        return (b) this.b.get(i);
    }

    public void a(int[] iArr, int i) {
        if (i == 0) {
            throw new IllegalArgumentException("No error correction bytes");
        }
        int length = iArr.length - i;
        if (length <= 0) {
            throw new IllegalArgumentException("No data bytes provided");
        }
        b a2 = a(i);
        int[] iArr2 = new int[length];
        System.arraycopy(iArr, 0, iArr2, 0, length);
        int[] a3 = new b(this.a, iArr2).a(i, 1).c(a2)[1].a();
        int length2 = i - a3.length;
        for (int i2 = 0; i2 < length2; i2++) {
            iArr[length + i2] = 0;
        }
        System.arraycopy(a3, 0, iArr, length + length2, a3.length);
    }
}
