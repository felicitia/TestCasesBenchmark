package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class ahy extends aam<ahy> {
    private String a;
    private Integer b;
    private int[] c;
    private aih d;

    public ahy() {
        this.a = null;
        this.b = null;
        this.c = aau.a;
        this.d = null;
        this.Y = null;
        this.Z = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public final ahy a(aaj aaj) throws IOException {
        while (true) {
            int a2 = aaj.a();
            if (a2 == 0) {
                return this;
            }
            if (a2 == 10) {
                this.a = aaj.e();
            } else if (a2 == 16) {
                int j = aaj.j();
                try {
                    this.b = Integer.valueOf(ahp.a(aaj.g()));
                } catch (IllegalArgumentException unused) {
                    aaj.e(j);
                    a(aaj, a2);
                }
            } else if (a2 == 24) {
                int a3 = aau.a(aaj, 24);
                int length = this.c == null ? 0 : this.c.length;
                int[] iArr = new int[(a3 + length)];
                if (length != 0) {
                    System.arraycopy(this.c, 0, iArr, 0, length);
                }
                while (length < iArr.length - 1) {
                    iArr[length] = aaj.g();
                    aaj.a();
                    length++;
                }
                iArr[length] = aaj.g();
                this.c = iArr;
            } else if (a2 == 26) {
                int c2 = aaj.c(aaj.g());
                int j2 = aaj.j();
                int i = 0;
                while (aaj.i() > 0) {
                    aaj.g();
                    i++;
                }
                aaj.e(j2);
                int length2 = this.c == null ? 0 : this.c.length;
                int[] iArr2 = new int[(i + length2)];
                if (length2 != 0) {
                    System.arraycopy(this.c, 0, iArr2, 0, length2);
                }
                while (length2 < iArr2.length) {
                    iArr2[length2] = aaj.g();
                    length2++;
                }
                this.c = iArr2;
                aaj.d(c2);
            } else if (a2 == 34) {
                if (this.d == null) {
                    this.d = new aih();
                }
                aaj.a((aar) this.d);
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
        if (this.b != null) {
            a2 += aal.b(2, this.b.intValue());
        }
        if (this.c != null && this.c.length > 0) {
            int i = 0;
            for (int a3 : this.c) {
                i += aal.a(a3);
            }
            a2 = a2 + i + (1 * this.c.length);
        }
        return this.d != null ? a2 + aal.b(4, (aar) this.d) : a2;
    }

    public final void a(aal aal) throws IOException {
        if (this.a != null) {
            aal.a(1, this.a);
        }
        if (this.b != null) {
            aal.a(2, this.b.intValue());
        }
        if (this.c != null && this.c.length > 0) {
            for (int a2 : this.c) {
                aal.a(3, a2);
            }
        }
        if (this.d != null) {
            aal.a(4, (aar) this.d);
        }
        super.a(aal);
    }
}
