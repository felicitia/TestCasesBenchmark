package com.facebook;

import android.os.Handler;
import com.facebook.g.a;
import com.facebook.g.b;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/* compiled from: ProgressOutputStream */
class l extends FilterOutputStream implements m {
    private final Map<GraphRequest, n> a;
    /* access modifiers changed from: private */
    public final g b;
    private final long c = f.i();
    /* access modifiers changed from: private */
    public long d;
    private long e;
    /* access modifiers changed from: private */
    public long f;
    private n g;

    l(OutputStream outputStream, g gVar, Map<GraphRequest, n> map, long j) {
        super(outputStream);
        this.b = gVar;
        this.a = map;
        this.f = j;
    }

    private void a(long j) {
        if (this.g != null) {
            this.g.a(j);
        }
        this.d += j;
        if (this.d >= this.e + this.c || this.d >= this.f) {
            a();
        }
    }

    private void a() {
        if (this.d > this.e) {
            for (a aVar : this.b.e()) {
                if (aVar instanceof b) {
                    Handler c2 = this.b.c();
                    final b bVar = (b) aVar;
                    if (c2 == null) {
                        bVar.a(this.b, this.d, this.f);
                    } else {
                        c2.post(new Runnable() {
                            public void run() {
                                bVar.a(l.this.b, l.this.d, l.this.f);
                            }
                        });
                    }
                }
            }
            this.e = this.d;
        }
    }

    public void a(GraphRequest graphRequest) {
        this.g = graphRequest != null ? (n) this.a.get(graphRequest) : null;
    }

    public void write(byte[] bArr) throws IOException {
        this.out.write(bArr);
        a((long) bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
        a((long) i2);
    }

    public void write(int i) throws IOException {
        this.out.write(i);
        a(1);
    }

    public void close() throws IOException {
        super.close();
        for (n a2 : this.a.values()) {
            a2.a();
        }
        a();
    }
}
