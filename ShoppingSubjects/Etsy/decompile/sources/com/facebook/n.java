package com.facebook;

import android.os.Handler;
import com.facebook.GraphRequest.b;
import com.facebook.GraphRequest.e;

/* compiled from: RequestProgress */
class n {
    private final GraphRequest a;
    private final Handler b;
    private final long c = f.i();
    private long d;
    private long e;
    private long f;

    n(Handler handler, GraphRequest graphRequest) {
        this.a = graphRequest;
        this.b = handler;
    }

    /* access modifiers changed from: 0000 */
    public void a(long j) {
        this.d += j;
        if (this.d >= this.e + this.c || this.d >= this.f) {
            a();
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(long j) {
        this.f += j;
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        if (this.d > this.e) {
            b g = this.a.g();
            if (this.f > 0 && (g instanceof e)) {
                final long j = this.d;
                final long j2 = this.f;
                final e eVar = (e) g;
                if (this.b == null) {
                    eVar.a(j, j2);
                } else {
                    Handler handler = this.b;
                    AnonymousClass1 r2 = new Runnable() {
                        public void run() {
                            eVar.a(j, j2);
                        }
                    };
                    handler.post(r2);
                }
                this.e = this.d;
            }
        }
    }
}
