package com.google.gson.internal.a;

import com.google.gson.h;
import com.google.gson.k;
import com.google.gson.l;
import com.google.gson.m;
import com.google.gson.n;
import com.google.gson.stream.b;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/* compiled from: JsonTreeWriter */
public final class f extends b {
    private static final Writer a = new Writer() {
        public void write(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }

        public void flush() throws IOException {
            throw new AssertionError();
        }

        public void close() throws IOException {
            throw new AssertionError();
        }
    };
    private static final n b = new n("closed");
    private final List<k> c = new ArrayList();
    private String d;
    private k e = l.a;

    public void flush() throws IOException {
    }

    public f() {
        super(a);
    }

    public k a() {
        if (this.c.isEmpty()) {
            return this.e;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Expected one JSON element but was ");
        sb.append(this.c);
        throw new IllegalStateException(sb.toString());
    }

    private k i() {
        return (k) this.c.get(this.c.size() - 1);
    }

    private void a(k kVar) {
        if (this.d != null) {
            if (!kVar.j() || h()) {
                ((m) i()).a(this.d, kVar);
            }
            this.d = null;
        } else if (this.c.isEmpty()) {
            this.e = kVar;
        } else {
            k i = i();
            if (i instanceof h) {
                ((h) i).a(kVar);
                return;
            }
            throw new IllegalStateException();
        }
    }

    public b b() throws IOException {
        h hVar = new h();
        a((k) hVar);
        this.c.add(hVar);
        return this;
    }

    public b c() throws IOException {
        if (this.c.isEmpty() || this.d != null) {
            throw new IllegalStateException();
        } else if (i() instanceof h) {
            this.c.remove(this.c.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public b d() throws IOException {
        m mVar = new m();
        a((k) mVar);
        this.c.add(mVar);
        return this;
    }

    public b e() throws IOException {
        if (this.c.isEmpty() || this.d != null) {
            throw new IllegalStateException();
        } else if (i() instanceof m) {
            this.c.remove(this.c.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public b a(String str) throws IOException {
        if (this.c.isEmpty() || this.d != null) {
            throw new IllegalStateException();
        } else if (i() instanceof m) {
            this.d = str;
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public b b(String str) throws IOException {
        if (str == null) {
            return f();
        }
        a((k) new n(str));
        return this;
    }

    public b f() throws IOException {
        a((k) l.a);
        return this;
    }

    public b a(boolean z) throws IOException {
        a((k) new n(Boolean.valueOf(z)));
        return this;
    }

    public b a(Boolean bool) throws IOException {
        if (bool == null) {
            return f();
        }
        a((k) new n(bool));
        return this;
    }

    public b a(long j) throws IOException {
        a((k) new n((Number) Long.valueOf(j)));
        return this;
    }

    public b a(Number number) throws IOException {
        if (number == null) {
            return f();
        }
        if (!g()) {
            double doubleValue = number.doubleValue();
            if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
                StringBuilder sb = new StringBuilder();
                sb.append("JSON forbids NaN and infinities: ");
                sb.append(number);
                throw new IllegalArgumentException(sb.toString());
            }
        }
        a((k) new n(number));
        return this;
    }

    public void close() throws IOException {
        if (!this.c.isEmpty()) {
            throw new IOException("Incomplete document");
        }
        this.c.add(b);
    }
}
