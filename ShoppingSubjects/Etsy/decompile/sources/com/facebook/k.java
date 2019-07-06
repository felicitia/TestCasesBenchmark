package com.facebook;

import android.os.Handler;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ProgressNoopOutputStream */
class k extends OutputStream implements m {
    private final Map<GraphRequest, n> a = new HashMap();
    private final Handler b;
    private GraphRequest c;
    private n d;
    private int e;

    k(Handler handler) {
        this.b = handler;
    }

    public void a(GraphRequest graphRequest) {
        this.c = graphRequest;
        this.d = graphRequest != null ? (n) this.a.get(graphRequest) : null;
    }

    /* access modifiers changed from: 0000 */
    public int a() {
        return this.e;
    }

    /* access modifiers changed from: 0000 */
    public Map<GraphRequest, n> b() {
        return this.a;
    }

    /* access modifiers changed from: 0000 */
    public void a(long j) {
        if (this.d == null) {
            this.d = new n(this.b, this.c);
            this.a.put(this.c, this.d);
        }
        this.d.b(j);
        this.e = (int) (((long) this.e) + j);
    }

    public void write(byte[] bArr) {
        a((long) bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) {
        a((long) i2);
    }

    public void write(int i) {
        a(1);
    }
}
