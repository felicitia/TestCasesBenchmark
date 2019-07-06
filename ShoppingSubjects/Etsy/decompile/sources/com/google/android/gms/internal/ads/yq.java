package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class yq extends aam<yq> {
    private Long a;
    private Integer b;
    private Boolean c;
    private int[] d;
    private Long e;

    public yq() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = aau.a;
        this.e = null;
        this.Z = -1;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        if (this.a != null) {
            a2 += aal.d(1, this.a.longValue());
        }
        if (this.b != null) {
            a2 += aal.b(2, this.b.intValue());
        }
        if (this.c != null) {
            this.c.booleanValue();
            a2 += aal.b(3) + 1;
        }
        if (this.d != null && this.d.length > 0) {
            int i = 0;
            for (int a3 : this.d) {
                i += aal.a(a3);
            }
            a2 = a2 + i + (1 * this.d.length);
        }
        return this.e != null ? a2 + aal.c(5, this.e.longValue()) : a2;
    }

    public final /* synthetic */ aar a(aaj aaj) throws IOException {
        while (true) {
            int a2 = aaj.a();
            if (a2 == 0) {
                return this;
            }
            if (a2 == 8) {
                this.a = Long.valueOf(aaj.h());
            } else if (a2 == 16) {
                this.b = Integer.valueOf(aaj.g());
            } else if (a2 == 24) {
                this.c = Boolean.valueOf(aaj.d());
            } else if (a2 == 32) {
                int a3 = aau.a(aaj, 32);
                int length = this.d == null ? 0 : this.d.length;
                int[] iArr = new int[(a3 + length)];
                if (length != 0) {
                    System.arraycopy(this.d, 0, iArr, 0, length);
                }
                while (length < iArr.length - 1) {
                    iArr[length] = aaj.g();
                    aaj.a();
                    length++;
                }
                iArr[length] = aaj.g();
                this.d = iArr;
            } else if (a2 == 34) {
                int c2 = aaj.c(aaj.g());
                int j = aaj.j();
                int i = 0;
                while (aaj.i() > 0) {
                    aaj.g();
                    i++;
                }
                aaj.e(j);
                int length2 = this.d == null ? 0 : this.d.length;
                int[] iArr2 = new int[(i + length2)];
                if (length2 != 0) {
                    System.arraycopy(this.d, 0, iArr2, 0, length2);
                }
                while (length2 < iArr2.length) {
                    iArr2[length2] = aaj.g();
                    length2++;
                }
                this.d = iArr2;
                aaj.d(c2);
            } else if (a2 == 40) {
                this.e = Long.valueOf(aaj.h());
            } else if (!super.a(aaj, a2)) {
                return this;
            }
        }
    }

    public final void a(aal aal) throws IOException {
        if (this.a != null) {
            aal.b(1, this.a.longValue());
        }
        if (this.b != null) {
            aal.a(2, this.b.intValue());
        }
        if (this.c != null) {
            aal.a(3, this.c.booleanValue());
        }
        if (this.d != null && this.d.length > 0) {
            for (int a2 : this.d) {
                aal.a(4, a2);
            }
        }
        if (this.e != null) {
            aal.a(5, this.e.longValue());
        }
        super.a(aal);
    }
}
