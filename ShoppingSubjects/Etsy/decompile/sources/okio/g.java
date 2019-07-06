package okio;

import java.io.IOException;

/* compiled from: ForwardingSink */
public abstract class g implements s {
    private final s a;

    public g(s sVar) {
        if (sVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.a = sVar;
    }

    public void a_(c cVar, long j) throws IOException {
        this.a.a_(cVar, j);
    }

    public void flush() throws IOException {
        this.a.flush();
    }

    public u a() {
        return this.a.a();
    }

    public void close() throws IOException {
        this.a.close();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("(");
        sb.append(this.a.toString());
        sb.append(")");
        return sb.toString();
    }
}
