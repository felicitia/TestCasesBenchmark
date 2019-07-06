package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fr extends e<fr> {
    private static volatile fr[] e;
    public String c;
    public String d;

    public fr() {
        this.c = null;
        this.d = null;
        this.a = null;
        this.b = -1;
    }

    public static fr[] e() {
        if (e == null) {
            synchronized (i.b) {
                if (e == null) {
                    e = new fr[0];
                }
            }
        }
        return e;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a = super.a();
        if (this.c != null) {
            a += d.b(1, this.c);
        }
        return this.d != null ? a + d.b(2, this.d) : a;
    }

    public final /* synthetic */ j a(c cVar) throws IOException {
        while (true) {
            int a = cVar.a();
            if (a == 0) {
                return this;
            }
            if (a == 10) {
                this.c = cVar.c();
            } else if (a == 18) {
                this.d = cVar.c();
            } else if (!super.a(cVar, a)) {
                return this;
            }
        }
    }

    public final void a(d dVar) throws IOException {
        if (this.c != null) {
            dVar.a(1, this.c);
        }
        if (this.d != null) {
            dVar.a(2, this.d);
        }
        super.a(dVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fr)) {
            return false;
        }
        fr frVar = (fr) obj;
        if (this.c == null) {
            if (frVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(frVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (frVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(frVar.d)) {
            return false;
        }
        return (this.a == null || this.a.b()) ? frVar.a == null || frVar.a.b() : this.a.equals(frVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((527 + getClass().getName().hashCode()) * 31) + (this.c == null ? 0 : this.c.hashCode())) * 31) + (this.d == null ? 0 : this.d.hashCode())) * 31;
        if (this.a != null && !this.a.b()) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }
}
