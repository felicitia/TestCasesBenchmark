package com.crittercism.internal;

public abstract class v {
    ac a;
    w b;
    protected int c;
    private int d;

    public abstract boolean a(w wVar);

    public abstract v b();

    public abstract v c();

    /* access modifiers changed from: protected */
    public abstract int d();

    /* access modifiers changed from: protected */
    public abstract int e();

    public v(ac acVar) {
        a(acVar, 0);
    }

    public v(v vVar) {
        a(vVar.a, vVar.c);
    }

    private void a(ac acVar, int i) {
        this.a = acVar;
        this.d = e();
        this.b = new w(d());
        this.c = i;
    }

    public boolean a(int i) {
        v vVar;
        if (i == -1) {
            g();
            return true;
        }
        this.c++;
        char c2 = (char) i;
        if (c2 == 10) {
            vVar = a(this.b) ? b() : aj.d;
        } else if (this.b.b < this.d) {
            w wVar = this.b;
            int i2 = wVar.b + 1;
            if (i2 > wVar.a.length) {
                char[] cArr = new char[Math.max(wVar.a.length << 1, i2)];
                System.arraycopy(wVar.a, 0, cArr, 0, wVar.b);
                wVar.a = cArr;
            }
            wVar.a[wVar.b] = c2;
            wVar.b = i2;
            vVar = this;
        } else {
            vVar = c();
        }
        if (vVar != this) {
            this.a.a(vVar);
        }
        if (vVar != this) {
            return true;
        }
        return false;
    }

    public final void a(byte[] bArr, int i, int i2) {
        int b2 = b(bArr, i, i2);
        while (b2 > 0 && b2 < i2) {
            int b3 = this.a.a().b(bArr, i + b2, i2 - b2);
            if (b3 > 0) {
                b2 += b3;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public int b(byte[] bArr, int i, int i2) {
        if (i2 == -1) {
            g();
            return -1;
        } else if (bArr == null || i2 == 0) {
            return -1;
        } else {
            boolean z = false;
            int i3 = 0;
            while (!z && i3 < i2) {
                z = a((int) (char) bArr[i + i3]);
                i3++;
            }
            return i3;
        }
    }

    public final int a() {
        return this.c;
    }

    public final void b(int i) {
        this.c = i;
    }

    public String toString() {
        return this.b.toString();
    }

    public void f() {
        if (this.a != null) {
            this.a.a((v) aj.d);
        }
    }

    private void g() {
        this.a.a((v) aj.d);
    }
}
