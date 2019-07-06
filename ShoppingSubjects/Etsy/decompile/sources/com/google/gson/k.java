package com.google.gson;

import com.google.gson.internal.g;
import com.google.gson.stream.b;
import java.io.IOException;
import java.io.StringWriter;

/* compiled from: JsonElement */
public abstract class k {
    public boolean g() {
        return this instanceof h;
    }

    public boolean h() {
        return this instanceof m;
    }

    public boolean i() {
        return this instanceof n;
    }

    public boolean j() {
        return this instanceof l;
    }

    public m k() {
        if (h()) {
            return (m) this;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Not a JSON Object: ");
        sb.append(this);
        throw new IllegalStateException(sb.toString());
    }

    public h l() {
        if (g()) {
            return (h) this;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Not a JSON Array: ");
        sb.append(this);
        throw new IllegalStateException(sb.toString());
    }

    public n m() {
        if (i()) {
            return (n) this;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Not a JSON Primitive: ");
        sb.append(this);
        throw new IllegalStateException(sb.toString());
    }

    public boolean f() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* access modifiers changed from: 0000 */
    public Boolean n() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Number a() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String b() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public double c() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public long d() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public int e() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            b bVar = new b(stringWriter);
            bVar.b(true);
            g.a(this, bVar);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
