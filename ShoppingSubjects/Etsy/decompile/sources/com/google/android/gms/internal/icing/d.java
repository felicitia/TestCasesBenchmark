package com.google.android.gms.internal.icing;

import java.io.IOException;

public final class d extends dh<d> {
    private static volatile d[] f;
    public String a;
    public String b;
    public int c;

    public d() {
        this.a = "";
        this.b = "";
        this.c = 0;
        this.d = null;
        this.e = -1;
    }

    public static d[] b() {
        if (f == null) {
            synchronized (dl.a) {
                if (f == null) {
                    f = new d[0];
                }
            }
        }
        return f;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        if (this.a != null && !this.a.equals("")) {
            a2 += dg.b(1, this.a);
        }
        if (this.b != null && !this.b.equals("")) {
            a2 += dg.b(2, this.b);
        }
        if (this.c == 0) {
            return a2;
        }
        int i = this.c;
        return a2 + dg.a(3) + (i >= 0 ? dg.c(i) : 10);
    }

    public final void a(dg dgVar) throws IOException {
        if (this.a != null && !this.a.equals("")) {
            dgVar.a(1, this.a);
        }
        if (this.b != null && !this.b.equals("")) {
            dgVar.a(2, this.b);
        }
        if (this.c != 0) {
            int i = this.c;
            dgVar.a(3, 0);
            if (i >= 0) {
                dgVar.b(i);
            } else {
                dgVar.a((long) i);
            }
        }
        super.a(dgVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        if (this.a == null) {
            if (dVar.a != null) {
                return false;
            }
        } else if (!this.a.equals(dVar.a)) {
            return false;
        }
        if (this.b == null) {
            if (dVar.b != null) {
                return false;
            }
        } else if (!this.b.equals(dVar.b)) {
            return false;
        }
        if (this.c != dVar.c) {
            return false;
        }
        return (this.d == null || this.d.b()) ? dVar.d == null || dVar.d.b() : this.d.equals(dVar.d);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((527 + getClass().getName().hashCode()) * 31) + (this.a == null ? 0 : this.a.hashCode())) * 31) + (this.b == null ? 0 : this.b.hashCode())) * 31) + this.c) * 31;
        if (this.d != null && !this.d.b()) {
            i = this.d.hashCode();
        }
        return hashCode + i;
    }
}
