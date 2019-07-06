package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class sb extends aam<sb> {
    public String a;
    public Long b;
    private String c;
    private String d;
    private String e;
    private Long f;
    private Long g;
    private String h;
    private Long i;
    private String j;

    public sb() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.Z = -1;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        if (this.a != null) {
            a2 += aal.b(1, this.a);
        }
        if (this.b != null) {
            a2 += aal.d(2, this.b.longValue());
        }
        if (this.c != null) {
            a2 += aal.b(3, this.c);
        }
        if (this.d != null) {
            a2 += aal.b(4, this.d);
        }
        if (this.e != null) {
            a2 += aal.b(5, this.e);
        }
        if (this.f != null) {
            a2 += aal.d(6, this.f.longValue());
        }
        if (this.g != null) {
            a2 += aal.d(7, this.g.longValue());
        }
        if (this.h != null) {
            a2 += aal.b(8, this.h);
        }
        if (this.i != null) {
            a2 += aal.d(9, this.i.longValue());
        }
        return this.j != null ? a2 + aal.b(10, this.j) : a2;
    }

    public final /* synthetic */ aar a(aaj aaj) throws IOException {
        while (true) {
            int a2 = aaj.a();
            switch (a2) {
                case 0:
                    return this;
                case 10:
                    this.a = aaj.e();
                    break;
                case 16:
                    this.b = Long.valueOf(aaj.h());
                    break;
                case 26:
                    this.c = aaj.e();
                    break;
                case 34:
                    this.d = aaj.e();
                    break;
                case 42:
                    this.e = aaj.e();
                    break;
                case 48:
                    this.f = Long.valueOf(aaj.h());
                    break;
                case 56:
                    this.g = Long.valueOf(aaj.h());
                    break;
                case 66:
                    this.h = aaj.e();
                    break;
                case 72:
                    this.i = Long.valueOf(aaj.h());
                    break;
                case 82:
                    this.j = aaj.e();
                    break;
                default:
                    if (super.a(aaj, a2)) {
                        break;
                    } else {
                        return this;
                    }
            }
        }
    }

    public final void a(aal aal) throws IOException {
        if (this.a != null) {
            aal.a(1, this.a);
        }
        if (this.b != null) {
            aal.b(2, this.b.longValue());
        }
        if (this.c != null) {
            aal.a(3, this.c);
        }
        if (this.d != null) {
            aal.a(4, this.d);
        }
        if (this.e != null) {
            aal.a(5, this.e);
        }
        if (this.f != null) {
            aal.b(6, this.f.longValue());
        }
        if (this.g != null) {
            aal.b(7, this.g.longValue());
        }
        if (this.h != null) {
            aal.a(8, this.h);
        }
        if (this.i != null) {
            aal.b(9, this.i.longValue());
        }
        if (this.j != null) {
            aal.a(10, this.j);
        }
        super.a(aal);
    }
}
