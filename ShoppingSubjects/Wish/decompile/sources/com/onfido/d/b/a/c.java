package com.onfido.d.b.a;

import com.onfido.d.a.b;
import com.onfido.d.h;
import com.onfido.d.m;

final class c {
    private b a;
    private m b;
    private m c;
    private m d;
    private m e;
    private int f;
    private int g;
    private int h;
    private int i;

    c(b bVar, m mVar, m mVar2, m mVar3, m mVar4) {
        if (!(mVar == null && mVar3 == null) && (!(mVar2 == null && mVar4 == null) && ((mVar == null || mVar2 != null) && (mVar3 == null || mVar4 != null)))) {
            a(bVar, mVar, mVar2, mVar3, mVar4);
            return;
        }
        throw h.a();
    }

    c(c cVar) {
        a(cVar.a, cVar.b, cVar.c, cVar.d, cVar.e);
    }

    static c a(c cVar, c cVar2) {
        if (cVar == null) {
            return cVar2;
        }
        if (cVar2 == null) {
            return cVar;
        }
        c cVar3 = new c(cVar.a, cVar.b, cVar.c, cVar2.d, cVar2.e);
        return cVar3;
    }

    private void a(b bVar, m mVar, m mVar2, m mVar3, m mVar4) {
        this.a = bVar;
        this.b = mVar;
        this.c = mVar2;
        this.d = mVar3;
        this.e = mVar4;
        i();
    }

    private void i() {
        if (this.b == null) {
            this.b = new m(0.0f, this.d.b());
            this.c = new m(0.0f, this.e.b());
        } else if (this.d == null) {
            this.d = new m((float) (this.a.b() - 1), this.b.b());
            this.e = new m((float) (this.a.b() - 1), this.c.b());
        }
        this.f = (int) Math.min(this.b.a(), this.c.a());
        this.g = (int) Math.max(this.d.a(), this.e.a());
        this.h = (int) Math.min(this.b.b(), this.d.b());
        this.i = (int) Math.max(this.c.b(), this.e.b());
    }

    /* access modifiers changed from: 0000 */
    public int a() {
        return this.f;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.onfido.d.b.a.c a(int r13, int r14, boolean r15) {
        /*
            r12 = this;
            com.onfido.d.m r0 = r12.b
            com.onfido.d.m r1 = r12.c
            com.onfido.d.m r2 = r12.d
            com.onfido.d.m r3 = r12.e
            if (r13 <= 0) goto L_0x002b
            if (r15 == 0) goto L_0x000f
            com.onfido.d.m r4 = r12.b
            goto L_0x0011
        L_0x000f:
            com.onfido.d.m r4 = r12.d
        L_0x0011:
            float r5 = r4.b()
            int r5 = (int) r5
            int r5 = r5 - r13
            if (r5 >= 0) goto L_0x001a
            r5 = 0
        L_0x001a:
            com.onfido.d.m r13 = new com.onfido.d.m
            float r4 = r4.a()
            float r5 = (float) r5
            r13.<init>(r4, r5)
            if (r15 == 0) goto L_0x0028
            r8 = r13
            goto L_0x002c
        L_0x0028:
            r10 = r13
            r8 = r0
            goto L_0x002d
        L_0x002b:
            r8 = r0
        L_0x002c:
            r10 = r2
        L_0x002d:
            if (r14 <= 0) goto L_0x005d
            if (r15 == 0) goto L_0x0034
            com.onfido.d.m r13 = r12.c
            goto L_0x0036
        L_0x0034:
            com.onfido.d.m r13 = r12.e
        L_0x0036:
            float r0 = r13.b()
            int r0 = (int) r0
            int r0 = r0 + r14
            com.onfido.d.a.b r14 = r12.a
            int r14 = r14.c()
            if (r0 < r14) goto L_0x004c
            com.onfido.d.a.b r14 = r12.a
            int r14 = r14.c()
            int r0 = r14 + -1
        L_0x004c:
            com.onfido.d.m r14 = new com.onfido.d.m
            float r13 = r13.a()
            float r0 = (float) r0
            r14.<init>(r13, r0)
            if (r15 == 0) goto L_0x005a
            r9 = r14
            goto L_0x005e
        L_0x005a:
            r11 = r14
            r9 = r1
            goto L_0x005f
        L_0x005d:
            r9 = r1
        L_0x005e:
            r11 = r3
        L_0x005f:
            r12.i()
            com.onfido.d.b.a.c r13 = new com.onfido.d.b.a.c
            com.onfido.d.a.b r7 = r12.a
            r6 = r13
            r6.<init>(r7, r8, r9, r10, r11)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.d.b.a.c.a(int, int, boolean):com.onfido.d.b.a.c");
    }

    /* access modifiers changed from: 0000 */
    public int b() {
        return this.g;
    }

    /* access modifiers changed from: 0000 */
    public int c() {
        return this.h;
    }

    /* access modifiers changed from: 0000 */
    public int d() {
        return this.i;
    }

    /* access modifiers changed from: 0000 */
    public m e() {
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public m f() {
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public m g() {
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    public m h() {
        return this.e;
    }
}
