package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class ahz extends aam<ahz> {
    private Integer a;
    private int[] b;

    public ahz() {
        this.a = null;
        this.b = aau.a;
        this.Y = null;
        this.Z = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public final ahz a(aaj aaj) throws IOException {
        while (true) {
            int a2 = aaj.a();
            if (a2 == 0) {
                return this;
            }
            if (a2 == 8) {
                int j = aaj.j();
                try {
                    this.a = Integer.valueOf(ahp.a(aaj.g()));
                } catch (IllegalArgumentException unused) {
                    aaj.e(j);
                    a(aaj, a2);
                }
            } else if (a2 == 16) {
                int a3 = aau.a(aaj, 16);
                int length = this.b == null ? 0 : this.b.length;
                int[] iArr = new int[(a3 + length)];
                if (length != 0) {
                    System.arraycopy(this.b, 0, iArr, 0, length);
                }
                while (length < iArr.length - 1) {
                    iArr[length] = aaj.g();
                    aaj.a();
                    length++;
                }
                iArr[length] = aaj.g();
                this.b = iArr;
            } else if (a2 == 18) {
                int c = aaj.c(aaj.g());
                int j2 = aaj.j();
                int i = 0;
                while (aaj.i() > 0) {
                    aaj.g();
                    i++;
                }
                aaj.e(j2);
                int length2 = this.b == null ? 0 : this.b.length;
                int[] iArr2 = new int[(i + length2)];
                if (length2 != 0) {
                    System.arraycopy(this.b, 0, iArr2, 0, length2);
                }
                while (length2 < iArr2.length) {
                    iArr2[length2] = aaj.g();
                    length2++;
                }
                this.b = iArr2;
                aaj.d(c);
            } else if (!super.a(aaj, a2)) {
                return this;
            }
        }
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        if (this.a != null) {
            a2 += aal.b(1, this.a.intValue());
        }
        if (this.b == null || this.b.length <= 0) {
            return a2;
        }
        int i = 0;
        for (int a3 : this.b) {
            i += aal.a(a3);
        }
        return a2 + i + (1 * this.b.length);
    }

    public final void a(aal aal) throws IOException {
        if (this.a != null) {
            aal.a(1, this.a.intValue());
        }
        if (this.b != null && this.b.length > 0) {
            for (int a2 : this.b) {
                aal.a(2, a2);
            }
        }
        super.a(aal);
    }
}
