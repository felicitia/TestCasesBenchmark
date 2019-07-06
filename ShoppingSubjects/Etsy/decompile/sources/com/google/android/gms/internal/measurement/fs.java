package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fs extends e<fs> {
    private static volatile fs[] g;
    public Integer c;
    public fy d;
    public fy e;
    public Boolean f;

    public fs() {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.a = null;
        this.b = -1;
    }

    public static fs[] e() {
        if (g == null) {
            synchronized (i.b) {
                if (g == null) {
                    g = new fs[0];
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
            a += d.b(2, (j) this.d);
        }
        if (this.e != null) {
            a += d.b(3, (j) this.e);
        }
        if (this.f == null) {
            return a;
        }
        this.f.booleanValue();
        return a + d.b(4) + 1;
    }

    public final /* synthetic */ j a(c cVar) throws IOException {
        fy fyVar;
        while (true) {
            int a = cVar.a();
            if (a == 0) {
                return this;
            }
            if (a != 8) {
                if (a == 18) {
                    if (this.d == null) {
                        this.d = new fy();
                    }
                    fyVar = this.d;
                } else if (a == 26) {
                    if (this.e == null) {
                        this.e = new fy();
                    }
                    fyVar = this.e;
                } else if (a == 32) {
                    this.f = Boolean.valueOf(cVar.b());
                } else if (!super.a(cVar, a)) {
                    return this;
                }
                cVar.a((j) fyVar);
            } else {
                this.c = Integer.valueOf(cVar.d());
            }
        }
    }

    public final void a(d dVar) throws IOException {
        if (this.c != null) {
            dVar.a(1, this.c.intValue());
        }
        if (this.d != null) {
            dVar.a(2, (j) this.d);
        }
        if (this.e != null) {
            dVar.a(3, (j) this.e);
        }
        if (this.f != null) {
            dVar.a(4, this.f.booleanValue());
        }
        super.a(dVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fs)) {
            return false;
        }
        fs fsVar = (fs) obj;
        if (this.c == null) {
            if (fsVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(fsVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (fsVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(fsVar.d)) {
            return false;
        }
        if (this.e == null) {
            if (fsVar.e != null) {
                return false;
            }
        } else if (!this.e.equals(fsVar.e)) {
            return false;
        }
        if (this.f == null) {
            if (fsVar.f != null) {
                return false;
            }
        } else if (!this.f.equals(fsVar.f)) {
            return false;
        }
        return (this.a == null || this.a.b()) ? fsVar.a == null || fsVar.a.b() : this.a.equals(fsVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((527 + getClass().getName().hashCode()) * 31) + (this.c == null ? 0 : this.c.hashCode());
        fy fyVar = this.d;
        int hashCode2 = (hashCode * 31) + (fyVar == null ? 0 : fyVar.hashCode());
        fy fyVar2 = this.e;
        int hashCode3 = ((((hashCode2 * 31) + (fyVar2 == null ? 0 : fyVar2.hashCode())) * 31) + (this.f == null ? 0 : this.f.hashCode())) * 31;
        if (this.a != null && !this.a.b()) {
            i = this.a.hashCode();
        }
        return hashCode3 + i;
    }
}
