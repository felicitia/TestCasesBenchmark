package com.crittercism.internal;

public final class z extends v {
    private int d = -1;

    /* access modifiers changed from: protected */
    public final int d() {
        return 16;
    }

    /* access modifiers changed from: protected */
    public final int e() {
        return 256;
    }

    public z(v vVar) {
        super(vVar);
    }

    public final v b() {
        if (this.d == 0) {
            return new ah(this);
        }
        this.b.b = 0;
        return new y(this, this.d);
    }

    public final v c() {
        return aj.d;
    }

    public final boolean a(w wVar) {
        int i;
        int i2 = wVar.b;
        if (i2 > wVar.b) {
            i2 = wVar.b;
        }
        if (i2 >= 0) {
            i = 0;
            while (true) {
                if (i >= i2) {
                    break;
                } else if (wVar.a[i] == ';') {
                    break;
                } else {
                    i++;
                }
            }
        }
        i = -1;
        int i3 = wVar.b;
        if (i > 0) {
            i3 = i;
        }
        try {
            this.d = Integer.parseInt(wVar.a(i3), 16);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public final void f() {
        this.a.b(a());
        this.a.a((v) aj.d);
    }
}
