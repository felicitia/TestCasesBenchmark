package com.squareup.moshi;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.util.Arrays;
import okio.d;

/* compiled from: JsonWriter */
public abstract class l implements Closeable, Flushable {
    int b = 0;
    int[] c = new int[32];
    String[] d = new String[32];
    int[] e = new int[32];
    String f;
    boolean g;
    boolean h;
    boolean i;

    public abstract l a() throws IOException;

    public abstract l a(double d2) throws IOException;

    public abstract l a(long j) throws IOException;

    public abstract l a(Number number) throws IOException;

    public abstract l a(boolean z) throws IOException;

    public abstract l b() throws IOException;

    public abstract l b(String str) throws IOException;

    public abstract l c() throws IOException;

    public abstract l c(String str) throws IOException;

    public abstract l d() throws IOException;

    public abstract l e() throws IOException;

    public static l a(d dVar) {
        return new i(dVar);
    }

    l() {
    }

    /* access modifiers changed from: 0000 */
    public final int g() {
        if (this.b != 0) {
            return this.c[this.b - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    /* access modifiers changed from: 0000 */
    public final boolean h() {
        if (this.b != this.c.length) {
            return false;
        }
        if (this.b == 256) {
            StringBuilder sb = new StringBuilder();
            sb.append("Nesting too deep at ");
            sb.append(m());
            sb.append(": circular reference?");
            throw new JsonDataException(sb.toString());
        }
        this.c = Arrays.copyOf(this.c, this.c.length * 2);
        this.d = (String[]) Arrays.copyOf(this.d, this.d.length * 2);
        this.e = Arrays.copyOf(this.e, this.e.length * 2);
        if (this instanceof k) {
            k kVar = (k) this;
            kVar.a = Arrays.copyOf(kVar.a, kVar.a.length * 2);
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public final void a(int i2) {
        int[] iArr = this.c;
        int i3 = this.b;
        this.b = i3 + 1;
        iArr[i3] = i2;
    }

    /* access modifiers changed from: 0000 */
    public final void b(int i2) {
        this.c[this.b - 1] = i2;
    }

    public void a(String str) {
        if (str.isEmpty()) {
            str = null;
        }
        this.f = str;
    }

    public final String i() {
        return this.f != null ? this.f : "";
    }

    public final void b(boolean z) {
        this.g = z;
    }

    public final boolean j() {
        return this.g;
    }

    public final void c(boolean z) {
        this.h = z;
    }

    public final boolean k() {
        return this.h;
    }

    /* access modifiers changed from: 0000 */
    public final void l() throws IOException {
        int g2 = g();
        if (g2 == 5 || g2 == 3) {
            this.i = true;
            return;
        }
        throw new IllegalStateException("Nesting problem.");
    }

    public final String m() {
        return g.a(this.b, this.c, this.d, this.e);
    }
}
