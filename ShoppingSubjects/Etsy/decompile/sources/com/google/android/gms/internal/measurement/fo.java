package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fo extends e<fo> {
    public Integer c;
    public String d;
    public Boolean e;
    public String[] f;

    public fo() {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = m.c;
        this.a = null;
        this.b = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public final fo a(c cVar) throws IOException {
        int d2;
        while (true) {
            int a = cVar.a();
            if (a == 0) {
                return this;
            }
            if (a == 8) {
                try {
                    d2 = cVar.d();
                    if (d2 < 0 || d2 > 6) {
                        StringBuilder sb = new StringBuilder(41);
                        sb.append(d2);
                        sb.append(" is not a valid enum MatchType");
                    } else {
                        this.c = Integer.valueOf(d2);
                    }
                } catch (IllegalArgumentException unused) {
                    cVar.e(cVar.i());
                    a(cVar, a);
                }
            } else if (a == 18) {
                this.d = cVar.c();
            } else if (a == 24) {
                this.e = Boolean.valueOf(cVar.b());
            } else if (a == 34) {
                int a2 = m.a(cVar, 34);
                int length = this.f == null ? 0 : this.f.length;
                String[] strArr = new String[(a2 + length)];
                if (length != 0) {
                    System.arraycopy(this.f, 0, strArr, 0, length);
                }
                while (length < strArr.length - 1) {
                    strArr[length] = cVar.c();
                    cVar.a();
                    length++;
                }
                strArr[length] = cVar.c();
                this.f = strArr;
            } else if (!super.a(cVar, a)) {
                return this;
            }
        }
        StringBuilder sb2 = new StringBuilder(41);
        sb2.append(d2);
        sb2.append(" is not a valid enum MatchType");
        throw new IllegalArgumentException(sb2.toString());
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
        if (this.e != null) {
            this.e.booleanValue();
            a += d.b(3) + 1;
        }
        if (this.f == null || this.f.length <= 0) {
            return a;
        }
        int i = 0;
        int i2 = 0;
        for (String str : this.f) {
            if (str != null) {
                i2++;
                i += d.a(str);
            }
        }
        return a + i + (1 * i2);
    }

    public final void a(d dVar) throws IOException {
        if (this.c != null) {
            dVar.a(1, this.c.intValue());
        }
        if (this.d != null) {
            dVar.a(2, this.d);
        }
        if (this.e != null) {
            dVar.a(3, this.e.booleanValue());
        }
        if (this.f != null && this.f.length > 0) {
            for (String str : this.f) {
                if (str != null) {
                    dVar.a(4, str);
                }
            }
        }
        super.a(dVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fo)) {
            return false;
        }
        fo foVar = (fo) obj;
        if (this.c == null) {
            if (foVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(foVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (foVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(foVar.d)) {
            return false;
        }
        if (this.e == null) {
            if (foVar.e != null) {
                return false;
            }
        } else if (!this.e.equals(foVar.e)) {
            return false;
        }
        if (!i.a((Object[]) this.f, (Object[]) foVar.f)) {
            return false;
        }
        return (this.a == null || this.a.b()) ? foVar.a == null || foVar.a.b() : this.a.equals(foVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((((527 + getClass().getName().hashCode()) * 31) + (this.c == null ? 0 : this.c.intValue())) * 31) + (this.d == null ? 0 : this.d.hashCode())) * 31) + (this.e == null ? 0 : this.e.hashCode())) * 31) + i.a((Object[]) this.f)) * 31;
        if (this.a != null && !this.a.b()) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }
}
