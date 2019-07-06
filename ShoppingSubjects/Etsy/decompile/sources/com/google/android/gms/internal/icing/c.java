package com.google.android.gms.internal.icing;

import java.io.IOException;

public final class c extends dh<c> {
    public d[] a;

    public c() {
        this.a = d.b();
        this.d = null;
        this.e = -1;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        if (this.a != null && this.a.length > 0) {
            for (d dVar : this.a) {
                if (dVar != null) {
                    a2 += dg.b(1, (dm) dVar);
                }
            }
        }
        return a2;
    }

    public final void a(dg dgVar) throws IOException {
        if (this.a != null && this.a.length > 0) {
            for (d dVar : this.a) {
                if (dVar != null) {
                    dgVar.a(1, (dm) dVar);
                }
            }
        }
        super.a(dgVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        if (!dl.a((Object[]) this.a, (Object[]) cVar.a)) {
            return false;
        }
        return (this.d == null || this.d.b()) ? cVar.d == null || cVar.d.b() : this.d.equals(cVar.d);
    }

    public final int hashCode() {
        return ((((527 + getClass().getName().hashCode()) * 31) + dl.a(this.a)) * 31) + ((this.d == null || this.d.b()) ? 0 : this.d.hashCode());
    }
}
