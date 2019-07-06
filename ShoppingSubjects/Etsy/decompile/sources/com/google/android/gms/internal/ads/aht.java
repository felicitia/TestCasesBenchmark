package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class aht extends aam<aht> {
    private String a;
    private ahr[] b;
    private Integer c;
    private Integer d;
    private Integer e;

    public aht() {
        this.a = null;
        this.b = ahr.b();
        this.c = null;
        this.d = null;
        this.e = null;
        this.Y = null;
        this.Z = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public final aht a(aaj aaj) throws IOException {
        int i;
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
                i = aaj.j();
                this.c = Integer.valueOf(ahp.a(aaj.g()));
            } else if (a2 == 32) {
                i = aaj.j();
                this.d = Integer.valueOf(ahp.a(aaj.g()));
            } else if (a2 == 40) {
                i = aaj.j();
                try {
                    this.e = Integer.valueOf(ahp.a(aaj.g()));
                } catch (IllegalArgumentException unused) {
                    aaj.e(i);
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
        if (this.c != null) {
            a2 += aal.b(3, this.c.intValue());
        }
        if (this.d != null) {
            a2 += aal.b(4, this.d.intValue());
        }
        return this.e != null ? a2 + aal.b(5, this.e.intValue()) : a2;
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
        if (this.d != null) {
            aal.a(4, this.d.intValue());
        }
        if (this.e != null) {
            aal.a(5, this.e.intValue());
        }
        super.a(aal);
    }
}
