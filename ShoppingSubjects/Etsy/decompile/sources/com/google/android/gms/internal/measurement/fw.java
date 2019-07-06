package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fw extends e<fw> {
    public fx[] c;

    public fw() {
        this.c = fx.e();
        this.a = null;
        this.b = -1;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a = super.a();
        if (this.c != null && this.c.length > 0) {
            for (fx fxVar : this.c) {
                if (fxVar != null) {
                    a += d.b(1, (j) fxVar);
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
            if (a == 10) {
                int a2 = m.a(cVar, 10);
                int length = this.c == null ? 0 : this.c.length;
                fx[] fxVarArr = new fx[(a2 + length)];
                if (length != 0) {
                    System.arraycopy(this.c, 0, fxVarArr, 0, length);
                }
                while (length < fxVarArr.length - 1) {
                    fxVarArr[length] = new fx();
                    cVar.a((j) fxVarArr[length]);
                    cVar.a();
                    length++;
                }
                fxVarArr[length] = new fx();
                cVar.a((j) fxVarArr[length]);
                this.c = fxVarArr;
            } else if (!super.a(cVar, a)) {
                return this;
            }
        }
    }

    public final void a(d dVar) throws IOException {
        if (this.c != null && this.c.length > 0) {
            for (fx fxVar : this.c) {
                if (fxVar != null) {
                    dVar.a(1, (j) fxVar);
                }
            }
        }
        super.a(dVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fw)) {
            return false;
        }
        fw fwVar = (fw) obj;
        if (!i.a((Object[]) this.c, (Object[]) fwVar.c)) {
            return false;
        }
        return (this.a == null || this.a.b()) ? fwVar.a == null || fwVar.a.b() : this.a.equals(fwVar.a);
    }

    public final int hashCode() {
        return ((((527 + getClass().getName().hashCode()) * 31) + i.a((Object[]) this.c)) * 31) + ((this.a == null || this.a.b()) ? 0 : this.a.hashCode());
    }
}
