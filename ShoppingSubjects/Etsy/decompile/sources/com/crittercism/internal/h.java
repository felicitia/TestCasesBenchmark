package com.crittercism.internal;

import com.crittercism.internal.b.c;
import java.io.IOException;
import java.io.InputStream;

public final class h extends InputStream implements ac {
    b a;
    InputStream b;
    v c;
    private u d;
    private d e;

    public final void a(String str) {
    }

    public final void a(String str, String str2) {
    }

    public h(u uVar, InputStream inputStream, d dVar) {
        if (uVar == null) {
            throw new NullPointerException("socket was null");
        } else if (inputStream == null) {
            throw new NullPointerException("delegate was null");
        } else if (dVar == null) {
            throw new NullPointerException("dispatch was null");
        } else {
            this.d = uVar;
            this.b = inputStream;
            this.e = dVar;
            this.c = b();
            if (this.c == null) {
                throw new NullPointerException("parser was null");
            }
        }
    }

    public final int available() {
        return this.b.available();
    }

    public final void close() {
        try {
            this.c.f();
        } catch (ThreadDeath e2) {
            throw e2;
        } catch (Throwable th) {
            cm.b(th);
        }
        this.b.close();
    }

    public final void mark(int i) {
        this.b.mark(i);
    }

    public final boolean markSupported() {
        return this.b.markSupported();
    }

    public final int read() {
        try {
            int read = this.b.read();
            try {
                this.c.a(read);
            } catch (ThreadDeath e2) {
                throw e2;
            } catch (IllegalStateException unused) {
                this.c = aj.d;
            } catch (Throwable th) {
                this.c = aj.d;
                cm.b(th);
            }
            return read;
        } catch (IOException e3) {
            a((Exception) e3);
            throw e3;
        }
    }

    public final int read(byte[] bArr) {
        try {
            int read = this.b.read(bArr);
            a(bArr, 0, read);
            return read;
        } catch (IOException e2) {
            a((Exception) e2);
            throw e2;
        }
    }

    public final int read(byte[] bArr, int i, int i2) {
        try {
            int read = this.b.read(bArr, i, i2);
            a(bArr, i, read);
            return read;
        } catch (IOException e2) {
            a((Exception) e2);
            throw e2;
        }
    }

    private void a(byte[] bArr, int i, int i2) {
        try {
            this.c.a(bArr, i, i2);
        } catch (ThreadDeath e2) {
            throw e2;
        } catch (IllegalStateException unused) {
            this.c = aj.d;
        } catch (Throwable th) {
            this.c = aj.d;
            cm.b(th);
        }
    }

    public final synchronized void reset() {
        this.b.reset();
    }

    public final long skip(long j) {
        return this.b.skip(j);
    }

    public final void a(int i) {
        b d2 = d();
        d2.d();
        d2.i = i;
    }

    public final void a(v vVar) {
        this.c = vVar;
    }

    public final v a() {
        return this.c;
    }

    public final void b(int i) {
        b bVar = null;
        if (this.a != null) {
            int i2 = this.a.i;
            if (i2 >= 100 && i2 < 200) {
                b bVar2 = new b(this.a.a());
                bVar2.c(this.a.a);
                bVar2.b(this.a.h);
                bVar2.j = this.a.j;
                bVar = bVar2;
            }
            this.a.a((long) i);
            this.e.a(this.a, c.INPUT_STREAM_FINISHED);
        }
        this.a = bVar;
    }

    private b d() {
        if (this.a == null) {
            this.a = this.d.b();
        }
        if (this.a != null) {
            return this.a;
        }
        throw new IllegalStateException("No statistics were queued up.");
    }

    public final v b() {
        return new ag(this);
    }

    public final String c() {
        return d().j;
    }

    private void a(Exception exc) {
        try {
            b d2 = d();
            d2.a((Throwable) exc);
            this.e.a(d2, c.PARSING_INPUT_STREAM_LOG_ERROR);
        } catch (ThreadDeath e2) {
            throw e2;
        } catch (IllegalStateException unused) {
        } catch (Throwable th) {
            cm.b(th);
        }
    }
}
