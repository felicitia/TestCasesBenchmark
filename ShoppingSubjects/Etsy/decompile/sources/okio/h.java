package okio;

import java.io.IOException;

/* compiled from: ForwardingSource */
public abstract class h implements t {
    private final t a;

    public h(t tVar) {
        if (tVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.a = tVar;
    }

    public final t b() {
        return this.a;
    }

    public long a(c cVar, long j) throws IOException {
        return this.a.a(cVar, j);
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
