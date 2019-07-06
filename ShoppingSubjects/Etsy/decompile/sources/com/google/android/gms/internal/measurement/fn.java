package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fn extends e<fn> {
    private static volatile fn[] f;
    public Integer c;
    public String d;
    public fl e;

    public fn() {
        this.c = null;
        this.d = null;
        this.e = null;
        this.a = null;
        this.b = -1;
    }

    public static fn[] e() {
        if (f == null) {
            synchronized (i.b) {
                if (f == null) {
                    f = new fn[0];
                }
            }
        }
        return f;
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
        return this.e != null ? a + d.b(3, (j) this.e) : a;
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
                if (this.e == null) {
                    this.e = new fl();
                }
                cVar.a((j) this.e);
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
        if (this.e != null) {
            dVar.a(3, (j) this.e);
        }
        super.a(dVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fn)) {
            return false;
        }
        fn fnVar = (fn) obj;
        if (this.c == null) {
            if (fnVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(fnVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (fnVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(fnVar.d)) {
            return false;
        }
        if (this.e == null) {
            if (fnVar.e != null) {
                return false;
            }
        } else if (!this.e.equals(fnVar.e)) {
            return false;
        }
        return (this.a == null || this.a.b()) ? fnVar.a == null || fnVar.a.b() : this.a.equals(fnVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((((527 + getClass().getName().hashCode()) * 31) + (this.c == null ? 0 : this.c.hashCode())) * 31) + (this.d == null ? 0 : this.d.hashCode());
        fl flVar = this.e;
        int hashCode2 = ((hashCode * 31) + (flVar == null ? 0 : flVar.hashCode())) * 31;
        if (this.a != null && !this.a.b()) {
            i = this.a.hashCode();
        }
        return hashCode2 + i;
    }
}
