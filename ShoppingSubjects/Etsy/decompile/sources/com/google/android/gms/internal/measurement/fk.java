package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fk extends e<fk> {
    private static volatile fk[] g;
    public Integer c;
    public String d;
    public fl[] e;
    public fm f;
    private Boolean h;

    public fk() {
        this.c = null;
        this.d = null;
        this.e = fl.e();
        this.h = null;
        this.f = null;
        this.a = null;
        this.b = -1;
    }

    public static fk[] e() {
        if (g == null) {
            synchronized (i.b) {
                if (g == null) {
                    g = new fk[0];
                }
            }
        }
        return g;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a = super.a();
        if (this.c != null) {
            a += d.b(1, this.c.intValue());
        }
        if (this.d != null) {
            a += d.b(2, this.d);
        }
        if (this.e != null && this.e.length > 0) {
            for (fl flVar : this.e) {
                if (flVar != null) {
                    a += d.b(3, (j) flVar);
                }
            }
        }
        if (this.h != null) {
            this.h.booleanValue();
            a += d.b(4) + 1;
        }
        return this.f != null ? a + d.b(5, (j) this.f) : a;
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
                this.d = cVar.c();
            } else if (a == 26) {
                int a2 = m.a(cVar, 26);
                int length = this.e == null ? 0 : this.e.length;
                fl[] flVarArr = new fl[(a2 + length)];
                if (length != 0) {
                    System.arraycopy(this.e, 0, flVarArr, 0, length);
                }
                while (length < flVarArr.length - 1) {
                    flVarArr[length] = new fl();
                    cVar.a((j) flVarArr[length]);
                    cVar.a();
                    length++;
                }
                flVarArr[length] = new fl();
                cVar.a((j) flVarArr[length]);
                this.e = flVarArr;
            } else if (a == 32) {
                this.h = Boolean.valueOf(cVar.b());
            } else if (a == 42) {
                if (this.f == null) {
                    this.f = new fm();
                }
                cVar.a((j) this.f);
            } else if (!super.a(cVar, a)) {
                return this;
            }
        }
    }

    public final void a(d dVar) throws IOException {
        if (this.c != null) {
            dVar.a(1, this.c.intValue());
        }
        if (this.d != null) {
            dVar.a(2, this.d);
        }
        if (this.e != null && this.e.length > 0) {
            for (fl flVar : this.e) {
                if (flVar != null) {
                    dVar.a(3, (j) flVar);
                }
            }
        }
        if (this.h != null) {
            dVar.a(4, this.h.booleanValue());
        }
        if (this.f != null) {
            dVar.a(5, (j) this.f);
        }
        super.a(dVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fk)) {
            return false;
        }
        fk fkVar = (fk) obj;
        if (this.c == null) {
            if (fkVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(fkVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (fkVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(fkVar.d)) {
            return false;
        }
        if (!i.a((Object[]) this.e, (Object[]) fkVar.e)) {
            return false;
        }
        if (this.h == null) {
            if (fkVar.h != null) {
                return false;
            }
        } else if (!this.h.equals(fkVar.h)) {
            return false;
        }
        if (this.f == null) {
            if (fkVar.f != null) {
                return false;
            }
        } else if (!this.f.equals(fkVar.f)) {
            return false;
        }
        return (this.a == null || this.a.b()) ? fkVar.a == null || fkVar.a.b() : this.a.equals(fkVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((((((((527 + getClass().getName().hashCode()) * 31) + (this.c == null ? 0 : this.c.hashCode())) * 31) + (this.d == null ? 0 : this.d.hashCode())) * 31) + i.a((Object[]) this.e)) * 31) + (this.h == null ? 0 : this.h.hashCode());
        fm fmVar = this.f;
        int hashCode2 = ((hashCode * 31) + (fmVar == null ? 0 : fmVar.hashCode())) * 31;
        if (this.a != null && !this.a.b()) {
            i = this.a.hashCode();
        }
        return hashCode2 + i;
    }
}
