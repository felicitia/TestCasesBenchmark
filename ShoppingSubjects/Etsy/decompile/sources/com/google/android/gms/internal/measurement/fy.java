package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fy extends e<fy> {
    public long[] c;
    public long[] d;
    public ft[] e;
    private fz[] f;

    public fy() {
        this.c = m.b;
        this.d = m.b;
        this.e = ft.e();
        this.f = fz.e();
        this.a = null;
        this.b = -1;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a = super.a();
        if (this.c != null && this.c.length > 0) {
            int i = 0;
            for (long a2 : this.c) {
                i += d.a(a2);
            }
            a = a + i + (this.c.length * 1);
        }
        if (this.d != null && this.d.length > 0) {
            int i2 = 0;
            for (long a3 : this.d) {
                i2 += d.a(a3);
            }
            a = a + i2 + (1 * this.d.length);
        }
        if (this.e != null && this.e.length > 0) {
            int i3 = a;
            for (ft ftVar : this.e) {
                if (ftVar != null) {
                    i3 += d.b(3, (j) ftVar);
                }
            }
            a = i3;
        }
        if (this.f != null && this.f.length > 0) {
            for (fz fzVar : this.f) {
                if (fzVar != null) {
                    a += d.b(4, (j) fzVar);
                }
            }
        }
        return a;
    }

    public final /* synthetic */ j a(c cVar) throws IOException {
        int i;
        while (true) {
            int a = cVar.a();
            if (a == 0) {
                return this;
            }
            if (a != 8) {
                if (a == 10) {
                    i = cVar.c(cVar.d());
                    int i2 = cVar.i();
                    int i3 = 0;
                    while (cVar.h() > 0) {
                        cVar.e();
                        i3++;
                    }
                    cVar.e(i2);
                    int length = this.c == null ? 0 : this.c.length;
                    long[] jArr = new long[(i3 + length)];
                    if (length != 0) {
                        System.arraycopy(this.c, 0, jArr, 0, length);
                    }
                    while (length < jArr.length) {
                        jArr[length] = cVar.e();
                        length++;
                    }
                    this.c = jArr;
                } else if (a == 16) {
                    int a2 = m.a(cVar, 16);
                    int length2 = this.d == null ? 0 : this.d.length;
                    long[] jArr2 = new long[(a2 + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.d, 0, jArr2, 0, length2);
                    }
                    while (length2 < jArr2.length - 1) {
                        jArr2[length2] = cVar.e();
                        cVar.a();
                        length2++;
                    }
                    jArr2[length2] = cVar.e();
                    this.d = jArr2;
                } else if (a == 18) {
                    i = cVar.c(cVar.d());
                    int i4 = cVar.i();
                    int i5 = 0;
                    while (cVar.h() > 0) {
                        cVar.e();
                        i5++;
                    }
                    cVar.e(i4);
                    int length3 = this.d == null ? 0 : this.d.length;
                    long[] jArr3 = new long[(i5 + length3)];
                    if (length3 != 0) {
                        System.arraycopy(this.d, 0, jArr3, 0, length3);
                    }
                    while (length3 < jArr3.length) {
                        jArr3[length3] = cVar.e();
                        length3++;
                    }
                    this.d = jArr3;
                } else if (a == 26) {
                    int a3 = m.a(cVar, 26);
                    int length4 = this.e == null ? 0 : this.e.length;
                    ft[] ftVarArr = new ft[(a3 + length4)];
                    if (length4 != 0) {
                        System.arraycopy(this.e, 0, ftVarArr, 0, length4);
                    }
                    while (length4 < ftVarArr.length - 1) {
                        ftVarArr[length4] = new ft();
                        cVar.a((j) ftVarArr[length4]);
                        cVar.a();
                        length4++;
                    }
                    ftVarArr[length4] = new ft();
                    cVar.a((j) ftVarArr[length4]);
                    this.e = ftVarArr;
                } else if (a == 34) {
                    int a4 = m.a(cVar, 34);
                    int length5 = this.f == null ? 0 : this.f.length;
                    fz[] fzVarArr = new fz[(a4 + length5)];
                    if (length5 != 0) {
                        System.arraycopy(this.f, 0, fzVarArr, 0, length5);
                    }
                    while (length5 < fzVarArr.length - 1) {
                        fzVarArr[length5] = new fz();
                        cVar.a((j) fzVarArr[length5]);
                        cVar.a();
                        length5++;
                    }
                    fzVarArr[length5] = new fz();
                    cVar.a((j) fzVarArr[length5]);
                    this.f = fzVarArr;
                } else if (!super.a(cVar, a)) {
                    return this;
                }
                cVar.d(i);
            } else {
                int a5 = m.a(cVar, 8);
                int length6 = this.c == null ? 0 : this.c.length;
                long[] jArr4 = new long[(a5 + length6)];
                if (length6 != 0) {
                    System.arraycopy(this.c, 0, jArr4, 0, length6);
                }
                while (length6 < jArr4.length - 1) {
                    jArr4[length6] = cVar.e();
                    cVar.a();
                    length6++;
                }
                jArr4[length6] = cVar.e();
                this.c = jArr4;
            }
        }
    }

    public final void a(d dVar) throws IOException {
        if (this.c != null && this.c.length > 0) {
            for (long a : this.c) {
                dVar.a(1, a);
            }
        }
        if (this.d != null && this.d.length > 0) {
            for (long a2 : this.d) {
                dVar.a(2, a2);
            }
        }
        if (this.e != null && this.e.length > 0) {
            for (ft ftVar : this.e) {
                if (ftVar != null) {
                    dVar.a(3, (j) ftVar);
                }
            }
        }
        if (this.f != null && this.f.length > 0) {
            for (fz fzVar : this.f) {
                if (fzVar != null) {
                    dVar.a(4, (j) fzVar);
                }
            }
        }
        super.a(dVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fy)) {
            return false;
        }
        fy fyVar = (fy) obj;
        if (i.a(this.c, fyVar.c) && i.a(this.d, fyVar.d) && i.a((Object[]) this.e, (Object[]) fyVar.e) && i.a((Object[]) this.f, (Object[]) fyVar.f)) {
            return (this.a == null || this.a.b()) ? fyVar.a == null || fyVar.a.b() : this.a.equals(fyVar.a);
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((((527 + getClass().getName().hashCode()) * 31) + i.a(this.c)) * 31) + i.a(this.d)) * 31) + i.a((Object[]) this.e)) * 31) + i.a((Object[]) this.f)) * 31) + ((this.a == null || this.a.b()) ? 0 : this.a.hashCode());
    }
}
