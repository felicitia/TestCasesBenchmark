package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class ahs extends aam<ahs> {
    private String a;
    private ahr[] b;
    private Integer c;

    public ahs() {
        this.a = null;
        this.b = ahr.b();
        this.c = null;
        this.Y = null;
        this.Z = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public final ahs a(aaj aaj) throws IOException {
        while (true) {
            int a2 = aaj.a();
            if (a2 == 0) {
                return this;
            }
            if (a2 == 10) {
                this.a = aaj.e();
            } else if (a2 == 18) {
                int a3 = aau.a(aaj, 18);
                int length = this.b == null ? 0 : this.b.length;
                ahr[] ahrArr = new ahr[(a3 + length)];
                if (length != 0) {
                    System.arraycopy(this.b, 0, ahrArr, 0, length);
                }
                while (length < ahrArr.length - 1) {
                    ahrArr[length] = new ahr();
                    aaj.a((aar) ahrArr[length]);
                    aaj.a();
                    length++;
                }
                ahrArr[length] = new ahr();
                aaj.a((aar) ahrArr[length]);
                this.b = ahrArr;
            } else if (a2 == 24) {
                int j = aaj.j();
                try {
                    this.c = Integer.valueOf(ahp.a(aaj.g()));
                } catch (IllegalArgumentException unused) {
                    aaj.e(j);
                    a(aaj, a2);
                }
            } else if (!super.a(aaj, a2)) {
                return this;
            }
        }
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        if (this.a != null) {
            a2 += aal.b(1, this.a);
        }
        if (this.b != null && this.b.length > 0) {
            for (ahr ahr : this.b) {
                if (ahr != null) {
                    a2 += aal.b(2, (aar) ahr);
                }
            }
        }
        return this.c != null ? a2 + aal.b(3, this.c.intValue()) : a2;
    }

    public final void a(aal aal) throws IOException {
        if (this.a != null) {
            aal.a(1, this.a);
        }
        if (this.b != null && this.b.length > 0) {
            for (ahr ahr : this.b) {
                if (ahr != null) {
                    aal.a(2, (aar) ahr);
                }
            }
        }
        if (this.c != null) {
            aal.a(3, this.c.intValue());
        }
        super.a(aal);
    }
}
