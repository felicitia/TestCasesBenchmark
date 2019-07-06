package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fl extends e<fl> {
    private static volatile fl[] i;
    public fo c;
    public fm d;
    public Boolean e;
    public String f;
    public Boolean g;
    public Boolean h;

    public fl() {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.a = null;
        this.b = -1;
    }

    public static fl[] e() {
        if (i == null) {
            synchronized (i.b) {
                if (i == null) {
                    i = new fl[0];
                }
            }
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a = super.a();
        if (this.c != null) {
            a += d.b(1, (j) this.c);
        }
        if (this.d != null) {
            a += d.b(2, (j) this.d);
        }
        if (this.e != null) {
            this.e.booleanValue();
            a += d.b(3) + 1;
        }
        if (this.f != null) {
            a += d.b(4, this.f);
        }
        if (this.g != null) {
            this.g.booleanValue();
            a += d.b(5) + 1;
        }
        if (this.h == null) {
            return a;
        }
        this.h.booleanValue();
        return a + d.b(6) + 1;
    }

    public final /* synthetic */ j a(c cVar) throws IOException {
        j jVar;
        while (true) {
            int a = cVar.a();
            if (a == 0) {
                return this;
            }
            if (a == 10) {
                if (this.c == null) {
                    this.c = new fo();
                }
                jVar = this.c;
            } else if (a == 18) {
                if (this.d == null) {
                    this.d = new fm();
                }
                jVar = this.d;
            } else if (a == 24) {
                this.e = Boolean.valueOf(cVar.b());
            } else if (a == 34) {
                this.f = cVar.c();
            } else if (a == 40) {
                this.g = Boolean.valueOf(cVar.b());
            } else if (a == 48) {
                this.h = Boolean.valueOf(cVar.b());
            } else if (!super.a(cVar, a)) {
                return this;
            }
            cVar.a(jVar);
        }
    }

    public final void a(d dVar) throws IOException {
        if (this.c != null) {
            dVar.a(1, (j) this.c);
        }
        if (this.d != null) {
            dVar.a(2, (j) this.d);
        }
        if (this.e != null) {
            dVar.a(3, this.e.booleanValue());
        }
        if (this.f != null) {
            dVar.a(4, this.f);
        }
        if (this.g != null) {
            dVar.a(5, this.g.booleanValue());
        }
        if (this.h != null) {
            dVar.a(6, this.h.booleanValue());
        }
        super.a(dVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fl)) {
            return false;
        }
        fl flVar = (fl) obj;
        if (this.c == null) {
            if (flVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(flVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (flVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(flVar.d)) {
            return false;
        }
        if (this.e == null) {
            if (flVar.e != null) {
                return false;
            }
        } else if (!this.e.equals(flVar.e)) {
            return false;
        }
        if (this.f == null) {
            if (flVar.f != null) {
                return false;
            }
        } else if (!this.f.equals(flVar.f)) {
            return false;
        }
        if (this.g == null) {
            if (flVar.g != null) {
                return false;
            }
        } else if (!this.g.equals(flVar.g)) {
            return false;
        }
        if (this.h == null) {
            if (flVar.h != null) {
                return false;
            }
        } else if (!this.h.equals(flVar.h)) {
            return false;
        }
        return (this.a == null || this.a.b()) ? flVar.a == null || flVar.a.b() : this.a.equals(flVar.a);
    }

    public final int hashCode() {
        int hashCode = 527 + getClass().getName().hashCode();
        fo foVar = this.c;
        int i2 = 0;
        int hashCode2 = (hashCode * 31) + (foVar == null ? 0 : foVar.hashCode());
        fm fmVar = this.d;
        int hashCode3 = ((((((((((hashCode2 * 31) + (fmVar == null ? 0 : fmVar.hashCode())) * 31) + (this.e == null ? 0 : this.e.hashCode())) * 31) + (this.f == null ? 0 : this.f.hashCode())) * 31) + (this.g == null ? 0 : this.g.hashCode())) * 31) + (this.h == null ? 0 : this.h.hashCode())) * 31;
        if (this.a != null && !this.a.b()) {
            i2 = this.a.hashCode();
        }
        return hashCode3 + i2;
    }
}
