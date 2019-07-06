package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fj extends e<fj> {
    private static volatile fj[] h;
    public Integer c;
    public fn[] d;
    public fk[] e;
    public Boolean f;
    public Boolean g;

    public fj() {
        this.c = null;
        this.d = fn.e();
        this.e = fk.e();
        this.f = null;
        this.g = null;
        this.a = null;
        this.b = -1;
    }

    public static fj[] e() {
        if (h == null) {
            synchronized (i.b) {
                if (h == null) {
                    h = new fj[0];
                }
            }
        }
        return h;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a = super.a();
        if (this.c != null) {
            a += d.b(1, this.c.intValue());
        }
        if (this.d != null && this.d.length > 0) {
            int i = a;
            for (fn fnVar : this.d) {
                if (fnVar != null) {
                    i += d.b(2, (j) fnVar);
                }
            }
            a = i;
        }
        if (this.e != null && this.e.length > 0) {
            for (fk fkVar : this.e) {
                if (fkVar != null) {
                    a += d.b(3, (j) fkVar);
                }
            }
        }
        if (this.f != null) {
            this.f.booleanValue();
            a += d.b(4) + 1;
        }
        if (this.g == null) {
            return a;
        }
        this.g.booleanValue();
        return a + d.b(5) + 1;
    }

    public final /* synthetic */ j a(c cVar) throws IOException {
        while (true) {
            int a = cVar.a();
            if (a == 0) {
                return this;
            }
            if (a == 8) {
                this.c = Integer.valueOf(cVar.d());
            } else if (a == 18) {
                int a2 = m.a(cVar, 18);
                int length = this.d == null ? 0 : this.d.length;
                fn[] fnVarArr = new fn[(a2 + length)];
                if (length != 0) {
                    System.arraycopy(this.d, 0, fnVarArr, 0, length);
                }
                while (length < fnVarArr.length - 1) {
                    fnVarArr[length] = new fn();
                    cVar.a((j) fnVarArr[length]);
                    cVar.a();
                    length++;
                }
                fnVarArr[length] = new fn();
                cVar.a((j) fnVarArr[length]);
                this.d = fnVarArr;
            } else if (a == 26) {
                int a3 = m.a(cVar, 26);
                int length2 = this.e == null ? 0 : this.e.length;
                fk[] fkVarArr = new fk[(a3 + length2)];
                if (length2 != 0) {
                    System.arraycopy(this.e, 0, fkVarArr, 0, length2);
                }
                while (length2 < fkVarArr.length - 1) {
                    fkVarArr[length2] = new fk();
                    cVar.a((j) fkVarArr[length2]);
                    cVar.a();
                    length2++;
                }
                fkVarArr[length2] = new fk();
                cVar.a((j) fkVarArr[length2]);
                this.e = fkVarArr;
            } else if (a == 32) {
                this.f = Boolean.valueOf(cVar.b());
            } else if (a == 40) {
                this.g = Boolean.valueOf(cVar.b());
            } else if (!super.a(cVar, a)) {
                return this;
            }
        }
    }

    public final void a(d dVar) throws IOException {
        if (this.c != null) {
            dVar.a(1, this.c.intValue());
        }
        if (this.d != null && this.d.length > 0) {
            for (fn fnVar : this.d) {
                if (fnVar != null) {
                    dVar.a(2, (j) fnVar);
                }
            }
        }
        if (this.e != null && this.e.length > 0) {
            for (fk fkVar : this.e) {
                if (fkVar != null) {
                    dVar.a(3, (j) fkVar);
                }
            }
        }
        if (this.f != null) {
            dVar.a(4, this.f.booleanValue());
        }
        if (this.g != null) {
            dVar.a(5, this.g.booleanValue());
        }
        super.a(dVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fj)) {
            return false;
        }
        fj fjVar = (fj) obj;
        if (this.c == null) {
            if (fjVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(fjVar.c)) {
            return false;
        }
        if (!i.a((Object[]) this.d, (Object[]) fjVar.d) || !i.a((Object[]) this.e, (Object[]) fjVar.e)) {
            return false;
        }
        if (this.f == null) {
            if (fjVar.f != null) {
                return false;
            }
        } else if (!this.f.equals(fjVar.f)) {
            return false;
        }
        if (this.g == null) {
            if (fjVar.g != null) {
                return false;
            }
        } else if (!this.g.equals(fjVar.g)) {
            return false;
        }
        return (this.a == null || this.a.b()) ? fjVar.a == null || fjVar.a.b() : this.a.equals(fjVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((((((527 + getClass().getName().hashCode()) * 31) + (this.c == null ? 0 : this.c.hashCode())) * 31) + i.a((Object[]) this.d)) * 31) + i.a((Object[]) this.e)) * 31) + (this.f == null ? 0 : this.f.hashCode())) * 31) + (this.g == null ? 0 : this.g.hashCode())) * 31;
        if (this.a != null && !this.a.b()) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }
}
