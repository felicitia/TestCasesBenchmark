package okio;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/* compiled from: ForwardingTimeout */
public class i extends u {
    private u a;

    public i(u uVar) {
        if (uVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.a = uVar;
    }

    public final u a() {
        return this.a;
    }

    public final i a(u uVar) {
        if (uVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.a = uVar;
        return this;
    }

    public u a(long j, TimeUnit timeUnit) {
        return this.a.a(j, timeUnit);
    }

    public long j_() {
        return this.a.j_();
    }

    public boolean k_() {
        return this.a.k_();
    }

    public long d() {
        return this.a.d();
    }

    public u a(long j) {
        return this.a.a(j);
    }

    public u l_() {
        return this.a.l_();
    }

    public u f() {
        return this.a.f();
    }

    public void g() throws IOException {
        this.a.g();
    }
}
