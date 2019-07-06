package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fu extends e<fu> {
    private static volatile fu[] h;
    public fv[] c;
    public String d;
    public Long e;
    public Long f;
    public Integer g;

    public fu() {
        this.c = fv.e();
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.a = null;
        this.b = -1;
    }

    public static fu[] e() {
        if (h == null) {
            synchronized (i.b) {
                if (h == null) {
                    h = new fu[0];
                }
            }
        }
        return h;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a = super.a();
        if (this.c != null && this.c.length > 0) {
            for (fv fvVar : this.c) {
                if (fvVar != null) {
                    a += d.b(1, (j) fvVar);
                }
            }
        }
        if (this.d != null) {
            a += d.b(2, this.d);
        }
        if (this.e != null) {
            a += d.c(3, this.e.longValue());
        }
        if (this.f != null) {
            a += d.c(4, this.f.longValue());
        }
        return this.g != null ? a + d.b(5, this.g.intValue()) : a;
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
                fv[] fvVarArr = new fv[(a2 + length)];
                if (length != 0) {
                    System.arraycopy(this.c, 0, fvVarArr, 0, length);
                }
                while (length < fvVarArr.length - 1) {
                    fvVarArr[length] = new fv();
                    cVar.a((j) fvVarArr[length]);
                    cVar.a();
                    length++;
                }
                fvVarArr[length] = new fv();
                cVar.a((j) fvVarArr[length]);
                this.c = fvVarArr;
            } else if (a == 18) {
                this.d = cVar.c();
            } else if (a == 24) {
                this.e = Long.valueOf(cVar.e());
            } else if (a == 32) {
                this.f = Long.valueOf(cVar.e());
            } else if (a == 40) {
                this.g = Integer.valueOf(cVar.d());
            } else if (!super.a(cVar, a)) {
                return this;
            }
        }
    }

    public final void a(d dVar) throws IOException {
        if (this.c != null && this.c.length > 0) {
            for (fv fvVar : this.c) {
                if (fvVar != null) {
                    dVar.a(1, (j) fvVar);
                }
            }
        }
        if (this.d != null) {
            dVar.a(2, this.d);
        }
        if (this.e != null) {
            dVar.b(3, this.e.longValue());
        }
        if (this.f != null) {
            dVar.b(4, this.f.longValue());
        }
        if (this.g != null) {
            dVar.a(5, this.g.intValue());
        }
        super.a(dVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fu)) {
            return false;
        }
        fu fuVar = (fu) obj;
        if (!i.a((Object[]) this.c, (Object[]) fuVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (fuVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(fuVar.d)) {
            return false;
        }
        if (this.e == null) {
            if (fuVar.e != null) {
                return false;
            }
        } else if (!this.e.equals(fuVar.e)) {
            return false;
        }
        if (this.f == null) {
            if (fuVar.f != null) {
                return false;
            }
        } else if (!this.f.equals(fuVar.f)) {
            return false;
        }
        if (this.g == null) {
            if (fuVar.g != null) {
                return false;
            }
        } else if (!this.g.equals(fuVar.g)) {
            return false;
        }
        return (this.a == null || this.a.b()) ? fuVar.a == null || fuVar.a.b() : this.a.equals(fuVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((((((527 + getClass().getName().hashCode()) * 31) + i.a((Object[]) this.c)) * 31) + (this.d == null ? 0 : this.d.hashCode())) * 31) + (this.e == null ? 0 : this.e.hashCode())) * 31) + (this.f == null ? 0 : this.f.hashCode())) * 31) + (this.g == null ? 0 : this.g.hashCode())) * 31;
        if (this.a != null && !this.a.b()) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }
}
