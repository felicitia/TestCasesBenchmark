package com.crittercism.internal;

import java.io.OutputStream;

public final class g extends OutputStream implements ac {
    OutputStream a;
    private u b;
    private b c;
    private v d;

    public final void a(int i) {
    }

    public g(u uVar, OutputStream outputStream) {
        if (uVar == null) {
            throw new NullPointerException("socket was null");
        } else if (outputStream == null) {
            throw new NullPointerException("output stream was null");
        } else {
            this.b = uVar;
            this.a = outputStream;
            this.d = b();
            if (this.d == null) {
                throw new NullPointerException("parser was null");
            }
        }
    }

    public final void flush() {
        this.a.flush();
    }

    public final void close() {
        this.a.close();
    }

    public final void write(int i) {
        this.a.write(i);
        try {
            this.d.a(i);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            cm.b(th);
            this.d = aj.d;
        }
    }

    public final void write(byte[] bArr) {
        this.a.write(bArr);
        if (bArr != null) {
            a(bArr, 0, bArr.length);
        }
    }

    public final void write(byte[] bArr, int i, int i2) {
        this.a.write(bArr, i, i2);
        if (bArr != null) {
            a(bArr, i, i2);
        }
    }

    public final void a(String str, String str2) {
        b d2 = d();
        d2.c();
        d2.j = str;
        d2.n = null;
        f fVar = d2.m;
        if (str2 != null) {
            fVar.c = str2;
        }
        this.b.a(d2);
    }

    public final void a(v vVar) {
        this.d = vVar;
    }

    public final v a() {
        return this.d;
    }

    public final void b(int i) {
        b bVar = this.c;
        this.c = null;
        if (bVar != null) {
            bVar.b((long) i);
        }
    }

    private b d() {
        if (this.c == null) {
            this.c = this.b.a();
        }
        return this.c;
    }

    public final v b() {
        return new ae(this);
    }

    public final String c() {
        b d2 = d();
        if (d2 != null) {
            return d2.j;
        }
        return null;
    }

    public final void a(String str) {
        b d2 = d();
        if (d2 != null) {
            d2.b(str);
        }
    }

    private void a(byte[] bArr, int i, int i2) {
        try {
            this.d.a(bArr, i, i2);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            cm.b(th);
            this.d = aj.d;
        }
    }
}
