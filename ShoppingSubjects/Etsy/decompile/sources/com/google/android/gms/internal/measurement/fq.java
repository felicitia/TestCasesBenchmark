package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fq extends e<fq> {
    public Long c;
    public String d;
    public fr[] e;
    public fp[] f;
    public fj[] g;
    private Integer h;

    public fq() {
        this.c = null;
        this.d = null;
        this.h = null;
        this.e = fr.e();
        this.f = fp.e();
        this.g = fj.e();
        this.a = null;
        this.b = -1;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a = super.a();
        if (this.c != null) {
            a += d.c(1, this.c.longValue());
        }
        if (this.d != null) {
            a += d.b(2, this.d);
        }
        if (this.h != null) {
            a += d.b(3, this.h.intValue());
        }
        if (this.e != null && this.e.length > 0) {
            int i = a;
            for (fr frVar : this.e) {
                if (frVar != null) {
                    i += d.b(4, (j) frVar);
                }
            }
            a = i;
        }
        if (this.f != null && this.f.length > 0) {
            int i2 = a;
            for (fp fpVar : this.f) {
                if (fpVar != null) {
                    i2 += d.b(5, (j) fpVar);
                }
            }
            a = i2;
        }
        if (this.g != null && this.g.length > 0) {
            for (fj fjVar : this.g) {
                if (fjVar != null) {
                    a += d.b(6, (j) fjVar);
                }
            }
        }
        return a;
    }

    public final /* synthetic */ j a(c cVar) throws IOException {
        while (true) {
            int a = cVar.a();
            if (a == 0) {
                return this;
            }
            if (a == 8) {
                this.c = Long.valueOf(cVar.e());
            } else if (a == 18) {
                this.d = cVar.c();
            } else if (a == 24) {
                this.h = Integer.valueOf(cVar.d());
            } else if (a == 34) {
                int a2 = m.a(cVar, 34);
                int length = this.e == null ? 0 : this.e.length;
                fr[] frVarArr = new fr[(a2 + length)];
                if (length != 0) {
                    System.arraycopy(this.e, 0, frVarArr, 0, length);
                }
                while (length < frVarArr.length - 1) {
                    frVarArr[length] = new fr();
                    cVar.a((j) frVarArr[length]);
                    cVar.a();
                    length++;
                }
                frVarArr[length] = new fr();
                cVar.a((j) frVarArr[length]);
                this.e = frVarArr;
            } else if (a == 42) {
                int a3 = m.a(cVar, 42);
                int length2 = this.f == null ? 0 : this.f.length;
                fp[] fpVarArr = new fp[(a3 + length2)];
                if (length2 != 0) {
                    System.arraycopy(this.f, 0, fpVarArr, 0, length2);
                }
                while (length2 < fpVarArr.length - 1) {
                    fpVarArr[length2] = new fp();
                    cVar.a((j) fpVarArr[length2]);
                    cVar.a();
                    length2++;
                }
                fpVarArr[length2] = new fp();
                cVar.a((j) fpVarArr[length2]);
                this.f = fpVarArr;
            } else if (a == 50) {
                int a4 = m.a(cVar, 50);
                int length3 = this.g == null ? 0 : this.g.length;
                fj[] fjVarArr = new fj[(a4 + length3)];
                if (length3 != 0) {
                    System.arraycopy(this.g, 0, fjVarArr, 0, length3);
                }
                while (length3 < fjVarArr.length - 1) {
                    fjVarArr[length3] = new fj();
                    cVar.a((j) fjVarArr[length3]);
                    cVar.a();
                    length3++;
                }
                fjVarArr[length3] = new fj();
                cVar.a((j) fjVarArr[length3]);
                this.g = fjVarArr;
            } else if (!super.a(cVar, a)) {
                return this;
            }
        }
    }

    public final void a(d dVar) throws IOException {
        if (this.c != null) {
            dVar.b(1, this.c.longValue());
        }
        if (this.d != null) {
            dVar.a(2, this.d);
        }
        if (this.h != null) {
            dVar.a(3, this.h.intValue());
        }
        if (this.e != null && this.e.length > 0) {
            for (fr frVar : this.e) {
                if (frVar != null) {
                    dVar.a(4, (j) frVar);
                }
            }
        }
        if (this.f != null && this.f.length > 0) {
            for (fp fpVar : this.f) {
                if (fpVar != null) {
                    dVar.a(5, (j) fpVar);
                }
            }
        }
        if (this.g != null && this.g.length > 0) {
            for (fj fjVar : this.g) {
                if (fjVar != null) {
                    dVar.a(6, (j) fjVar);
                }
            }
        }
        super.a(dVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fq)) {
            return false;
        }
        fq fqVar = (fq) obj;
        if (this.c == null) {
            if (fqVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(fqVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (fqVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(fqVar.d)) {
            return false;
        }
        if (this.h == null) {
            if (fqVar.h != null) {
                return false;
            }
        } else if (!this.h.equals(fqVar.h)) {
            return false;
        }
        if (i.a((Object[]) this.e, (Object[]) fqVar.e) && i.a((Object[]) this.f, (Object[]) fqVar.f) && i.a((Object[]) this.g, (Object[]) fqVar.g)) {
            return (this.a == null || this.a.b()) ? fqVar.a == null || fqVar.a.b() : this.a.equals(fqVar.a);
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((((((((527 + getClass().getName().hashCode()) * 31) + (this.c == null ? 0 : this.c.hashCode())) * 31) + (this.d == null ? 0 : this.d.hashCode())) * 31) + (this.h == null ? 0 : this.h.hashCode())) * 31) + i.a((Object[]) this.e)) * 31) + i.a((Object[]) this.f)) * 31) + i.a((Object[]) this.g)) * 31;
        if (this.a != null && !this.a.b()) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }
}
