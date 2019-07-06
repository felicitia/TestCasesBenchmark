package com.google.zxing.aztec.a;

import com.google.zxing.common.a;
import java.util.LinkedList;

/* compiled from: State */
final class f {
    static final f a = new f(g.a, 0, 0, 0);
    private final int b;
    private final g c;
    private final int d;
    private final int e;

    private f(g gVar, int i, int i2, int i3) {
        this.c = gVar;
        this.b = i;
        this.d = i2;
        this.e = i3;
    }

    /* access modifiers changed from: 0000 */
    public int a() {
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public int b() {
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public int c() {
        return this.e;
    }

    /* access modifiers changed from: 0000 */
    public f a(int i, int i2) {
        int i3 = this.e;
        g gVar = this.c;
        if (i != this.b) {
            int i4 = d.b[this.b][i];
            int i5 = 65535 & i4;
            int i6 = i4 >> 16;
            gVar = gVar.a(i5, i6);
            i3 += i6;
        }
        int i7 = i == 2 ? 4 : 5;
        return new f(gVar.a(i2, i7), i, 0, i3 + i7);
    }

    /* access modifiers changed from: 0000 */
    public f b(int i, int i2) {
        g gVar = this.c;
        int i3 = this.b == 2 ? 4 : 5;
        return new f(gVar.a(d.c[this.b][i], i3).a(i2, 5), this.b, 0, this.e + i3 + 5);
    }

    /* access modifiers changed from: 0000 */
    public f a(int i) {
        g gVar = this.c;
        int i2 = this.b;
        int i3 = this.e;
        if (this.b == 4 || this.b == 2) {
            int i4 = d.b[i2][0];
            int i5 = 65535 & i4;
            int i6 = i4 >> 16;
            gVar = gVar.a(i5, i6);
            i3 += i6;
            i2 = 0;
        }
        int i7 = (this.d == 0 || this.d == 31) ? 18 : this.d == 62 ? 9 : 8;
        f fVar = new f(gVar, i2, this.d + 1, i3 + i7);
        return fVar.d == 2078 ? fVar.b(i + 1) : fVar;
    }

    /* access modifiers changed from: 0000 */
    public f b(int i) {
        if (this.d == 0) {
            return this;
        }
        return new f(this.c.b(i - this.d, this.d), this.b, 0, this.e);
    }

    /* access modifiers changed from: 0000 */
    public boolean a(f fVar) {
        int i = this.e + (d.b[this.b][fVar.b] >> 16);
        if (fVar.d > 0 && (this.d == 0 || this.d > fVar.d)) {
            i += 10;
        }
        return i <= fVar.e;
    }

    /* access modifiers changed from: 0000 */
    public a a(byte[] bArr) {
        LinkedList<g> linkedList = new LinkedList<>();
        for (g gVar = b(bArr.length).c; gVar != null; gVar = gVar.a()) {
            linkedList.addFirst(gVar);
        }
        a aVar = new a();
        for (g a2 : linkedList) {
            a2.a(aVar, bArr);
        }
        return aVar;
    }

    public String toString() {
        return String.format("%s bits=%d bytes=%d", new Object[]{d.a[this.b], Integer.valueOf(this.e), Integer.valueOf(this.d)});
    }
}
