package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class ahv extends aam<ahv> {
    public String a;
    public aii b;
    private aih c;
    private Integer d;
    private Integer e;
    private Integer f;
    private Integer g;
    private Integer h;

    public ahv() {
        this.a = null;
        this.c = null;
        this.d = null;
        this.b = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.Y = null;
        this.Z = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public final ahv a(aaj aaj) throws IOException {
        aar aar;
        int i;
        while (true) {
            int a2 = aaj.a();
            if (a2 == 0) {
                return this;
            }
            if (a2 != 10) {
                if (a2 == 18) {
                    if (this.c == null) {
                        this.c = new aih();
                    }
                    aar = this.c;
                } else if (a2 == 24) {
                    this.d = Integer.valueOf(aaj.g());
                } else if (a2 == 34) {
                    if (this.b == null) {
                        this.b = new aii();
                    }
                    aar = this.b;
                } else if (a2 == 40) {
                    this.e = Integer.valueOf(aaj.g());
                } else if (a2 == 48) {
                    i = aaj.j();
                    this.f = Integer.valueOf(ahp.a(aaj.g()));
                } else if (a2 == 56) {
                    i = aaj.j();
                    this.g = Integer.valueOf(ahp.a(aaj.g()));
                } else if (a2 == 64) {
                    i = aaj.j();
                    try {
                        this.h = Integer.valueOf(ahp.a(aaj.g()));
                    } catch (IllegalArgumentException unused) {
                        aaj.e(i);
                        a(aaj, a2);
                    }
                } else if (!super.a(aaj, a2)) {
                    return this;
                }
                aaj.a(aar);
            } else {
                this.a = aaj.e();
            }
        }
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        if (this.a != null) {
            a2 += aal.b(1, this.a);
        }
        if (this.c != null) {
            a2 += aal.b(2, (aar) this.c);
        }
        if (this.d != null) {
            a2 += aal.b(3, this.d.intValue());
        }
        if (this.b != null) {
            a2 += aal.b(4, (aar) this.b);
        }
        if (this.e != null) {
            a2 += aal.b(5, this.e.intValue());
        }
        if (this.f != null) {
            a2 += aal.b(6, this.f.intValue());
        }
        if (this.g != null) {
            a2 += aal.b(7, this.g.intValue());
        }
        return this.h != null ? a2 + aal.b(8, this.h.intValue()) : a2;
    }

    public final void a(aal aal) throws IOException {
        if (this.a != null) {
            aal.a(1, this.a);
        }
        if (this.c != null) {
            aal.a(2, (aar) this.c);
        }
        if (this.d != null) {
            aal.a(3, this.d.intValue());
        }
        if (this.b != null) {
            aal.a(4, (aar) this.b);
        }
        if (this.e != null) {
            aal.a(5, this.e.intValue());
        }
        if (this.f != null) {
            aal.a(6, this.f.intValue());
        }
        if (this.g != null) {
            aal.a(7, this.g.intValue());
        }
        if (this.h != null) {
            aal.a(8, this.h.intValue());
        }
        super.a(aal);
    }
}
