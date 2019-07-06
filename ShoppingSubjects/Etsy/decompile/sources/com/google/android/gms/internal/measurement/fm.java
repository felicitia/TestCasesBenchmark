package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fm extends e<fm> {
    public Integer c;
    public Boolean d;
    public String e;
    public String f;
    public String g;

    public fm() {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.a = null;
        this.b = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public final fm a(c cVar) throws IOException {
        int d2;
        while (true) {
            int a = cVar.a();
            if (a == 0) {
                return this;
            }
            if (a == 8) {
                try {
                    d2 = cVar.d();
                    if (d2 < 0 || d2 > 4) {
                        StringBuilder sb = new StringBuilder(46);
                        sb.append(d2);
                        sb.append(" is not a valid enum ComparisonType");
                    } else {
                        this.c = Integer.valueOf(d2);
                    }
                } catch (IllegalArgumentException unused) {
                    cVar.e(cVar.i());
                    a(cVar, a);
                }
            } else if (a == 16) {
                this.d = Boolean.valueOf(cVar.b());
            } else if (a == 26) {
                this.e = cVar.c();
            } else if (a == 34) {
                this.f = cVar.c();
            } else if (a == 42) {
                this.g = cVar.c();
            } else if (!super.a(cVar, a)) {
                return this;
            }
        }
        StringBuilder sb2 = new StringBuilder(46);
        sb2.append(d2);
        sb2.append(" is not a valid enum ComparisonType");
        throw new IllegalArgumentException(sb2.toString());
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a = super.a();
        if (this.c != null) {
            a += d.b(1, this.c.intValue());
        }
        if (this.d != null) {
            this.d.booleanValue();
            a += d.b(2) + 1;
        }
        if (this.e != null) {
            a += d.b(3, this.e);
        }
        if (this.f != null) {
            a += d.b(4, this.f);
        }
        return this.g != null ? a + d.b(5, this.g) : a;
    }

    public final void a(d dVar) throws IOException {
        if (this.c != null) {
            dVar.a(1, this.c.intValue());
        }
        if (this.d != null) {
            dVar.a(2, this.d.booleanValue());
        }
        if (this.e != null) {
            dVar.a(3, this.e);
        }
        if (this.f != null) {
            dVar.a(4, this.f);
        }
        if (this.g != null) {
            dVar.a(5, this.g);
        }
        super.a(dVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fm)) {
            return false;
        }
        fm fmVar = (fm) obj;
        if (this.c == null) {
            if (fmVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(fmVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (fmVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(fmVar.d)) {
            return false;
        }
        if (this.e == null) {
            if (fmVar.e != null) {
                return false;
            }
        } else if (!this.e.equals(fmVar.e)) {
            return false;
        }
        if (this.f == null) {
            if (fmVar.f != null) {
                return false;
            }
        } else if (!this.f.equals(fmVar.f)) {
            return false;
        }
        if (this.g == null) {
            if (fmVar.g != null) {
                return false;
            }
        } else if (!this.g.equals(fmVar.g)) {
            return false;
        }
        return (this.a == null || this.a.b()) ? fmVar.a == null || fmVar.a.b() : this.a.equals(fmVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((((((527 + getClass().getName().hashCode()) * 31) + (this.c == null ? 0 : this.c.intValue())) * 31) + (this.d == null ? 0 : this.d.hashCode())) * 31) + (this.e == null ? 0 : this.e.hashCode())) * 31) + (this.f == null ? 0 : this.f.hashCode())) * 31) + (this.g == null ? 0 : this.g.hashCode())) * 31;
        if (this.a != null && !this.a.b()) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }
}
