package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* compiled from: Timeout */
public class u {
    public static final u c = new u() {
        public u a(long j) {
            return this;
        }

        public u a(long j, TimeUnit timeUnit) {
            return this;
        }

        public void g() throws IOException {
        }
    };
    private boolean a;
    private long b;
    private long d;

    public u a(long j, TimeUnit timeUnit) {
        if (j < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("timeout < 0: ");
            sb.append(j);
            throw new IllegalArgumentException(sb.toString());
        } else if (timeUnit == null) {
            throw new IllegalArgumentException("unit == null");
        } else {
            this.d = timeUnit.toNanos(j);
            return this;
        }
    }

    public long j_() {
        return this.d;
    }

    public boolean k_() {
        return this.a;
    }

    public long d() {
        if (this.a) {
            return this.b;
        }
        throw new IllegalStateException("No deadline");
    }

    public u a(long j) {
        this.a = true;
        this.b = j;
        return this;
    }

    public u l_() {
        this.d = 0;
        return this;
    }

    public u f() {
        this.a = false;
        return this;
    }

    public void g() throws IOException {
        if (Thread.interrupted()) {
            throw new InterruptedIOException("thread interrupted");
        } else if (this.a && this.b - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }
}
