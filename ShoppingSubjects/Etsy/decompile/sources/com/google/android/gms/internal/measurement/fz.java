package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fz extends e<fz> {
    private static volatile fz[] c;
    private Integer d;
    private long[] e;

    public fz() {
        this.d = null;
        this.e = m.b;
        this.a = null;
        this.b = -1;
    }

    public static fz[] e() {
        if (c == null) {
            synchronized (i.b) {
                if (c == null) {
                    c = new fz[0];
                }
            }
        }
        return c;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a = super.a();
        if (this.d != null) {
            a += d.b(1, this.d.intValue());
        }
        if (this.e == null || this.e.length <= 0) {
            return a;
        }
        int i = 0;
        for (long a2 : this.e) {
            i += d.a(a2);
        }
        return a + i + (1 * this.e.length);
    }

    public final /* synthetic */ j a(c cVar) throws IOException {
        while (true) {
            int a = cVar.a();
            if (a == 0) {
                return this;
            }
            if (a == 8) {
                this.d = Integer.valueOf(cVar.d());
            } else if (a == 16) {
                int a2 = m.a(cVar, 16);
                int length = this.e == null ? 0 : this.e.length;
                long[] jArr = new long[(a2 + length)];
                if (length != 0) {
                    System.arraycopy(this.e, 0, jArr, 0, length);
                }
                while (length < jArr.length - 1) {
                    jArr[length] = cVar.e();
                    cVar.a();
                    length++;
                }
                jArr[length] = cVar.e();
                this.e = jArr;
            } else if (a == 18) {
                int c2 = cVar.c(cVar.d());
                int i = cVar.i();
                int i2 = 0;
                while (cVar.h() > 0) {
                    cVar.e();
                    i2++;
                }
                cVar.e(i);
                int length2 = this.e == null ? 0 : this.e.length;
                long[] jArr2 = new long[(i2 + length2)];
                if (length2 != 0) {
                    System.arraycopy(this.e, 0, jArr2, 0, length2);
                }
                while (length2 < jArr2.length) {
                    jArr2[length2] = cVar.e();
                    length2++;
                }
                this.e = jArr2;
                cVar.d(c2);
            } else if (!super.a(cVar, a)) {
                return this;
            }
        }
    }

    public final void a(d dVar) throws IOException {
        if (this.d != null) {
            dVar.a(1, this.d.intValue());
        }
        if (this.e != null && this.e.length > 0) {
            for (long b : this.e) {
                dVar.b(2, b);
            }
        }
        super.a(dVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fz)) {
            return false;
        }
        fz fzVar = (fz) obj;
        if (this.d == null) {
            if (fzVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(fzVar.d)) {
            return false;
        }
        if (!i.a(this.e, fzVar.e)) {
            return false;
        }
        return (this.a == null || this.a.b()) ? fzVar.a == null || fzVar.a.b() : this.a.equals(fzVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((527 + getClass().getName().hashCode()) * 31) + (this.d == null ? 0 : this.d.hashCode())) * 31) + i.a(this.e)) * 31;
        if (this.a != null && !this.a.b()) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }
}
