package okio;

/* compiled from: Segment */
final class q {
    final byte[] a;
    int b;
    int c;
    boolean d;
    boolean e;
    q f;
    q g;

    q() {
        this.a = new byte[8192];
        this.e = true;
        this.d = false;
    }

    q(byte[] bArr, int i, int i2, boolean z, boolean z2) {
        this.a = bArr;
        this.b = i;
        this.c = i2;
        this.d = z;
        this.e = z2;
    }

    /* access modifiers changed from: 0000 */
    public q a() {
        this.d = true;
        q qVar = new q(this.a, this.b, this.c, true, false);
        return qVar;
    }

    public q b() {
        q qVar = this.f != this ? this.f : null;
        this.g.f = this.f;
        this.f.g = this.g;
        this.f = null;
        this.g = null;
        return qVar;
    }

    public q a(q qVar) {
        qVar.g = this;
        qVar.f = this.f;
        this.f.g = qVar;
        this.f = qVar;
        return qVar;
    }

    public q a(int i) {
        q qVar;
        if (i <= 0 || i > this.c - this.b) {
            throw new IllegalArgumentException();
        }
        if (i >= 1024) {
            qVar = a();
        } else {
            qVar = r.a();
            System.arraycopy(this.a, this.b, qVar.a, 0, i);
        }
        qVar.c = qVar.b + i;
        this.b += i;
        this.g.a(qVar);
        return qVar;
    }

    public void c() {
        if (this.g == this) {
            throw new IllegalStateException();
        } else if (this.g.e) {
            int i = this.c - this.b;
            if (i <= (8192 - this.g.c) + (this.g.d ? 0 : this.g.b)) {
                a(this.g, i);
                b();
                r.a(this);
            }
        }
    }

    public void a(q qVar, int i) {
        if (!qVar.e) {
            throw new IllegalArgumentException();
        }
        if (qVar.c + i > 8192) {
            if (qVar.d) {
                throw new IllegalArgumentException();
            } else if ((qVar.c + i) - qVar.b > 8192) {
                throw new IllegalArgumentException();
            } else {
                System.arraycopy(qVar.a, qVar.b, qVar.a, 0, qVar.c - qVar.b);
                qVar.c -= qVar.b;
                qVar.b = 0;
            }
        }
        System.arraycopy(this.a, this.b, qVar.a, qVar.c, i);
        qVar.c += i;
        this.b += i;
    }
}
