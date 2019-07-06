package com.squareup.moshi;

import com.etsy.android.lib.convos.Draft;
import java.io.IOException;
import okio.d;
import org.apache.commons.math3.geometry.VectorFormat;

/* compiled from: JsonUtf8Writer */
final class i extends l {
    private static final String[] a = new String[128];
    private final d j;
    private String k = Draft.IMAGE_DELIMITER;
    private String l;

    static {
        for (int i = 0; i <= 31; i++) {
            a[i] = String.format("\\u%04x", new Object[]{Integer.valueOf(i)});
        }
        a[34] = "\\\"";
        a[92] = "\\\\";
        a[9] = "\\t";
        a[8] = "\\b";
        a[10] = "\\n";
        a[13] = "\\r";
        a[12] = "\\f";
    }

    i(d dVar) {
        if (dVar == null) {
            throw new NullPointerException("sink == null");
        }
        this.j = dVar;
        a(6);
    }

    public void a(String str) {
        super.a(str);
        this.k = !str.isEmpty() ? ": " : Draft.IMAGE_DELIMITER;
    }

    public l a() throws IOException {
        if (this.i) {
            StringBuilder sb = new StringBuilder();
            sb.append("Array cannot be used as a map key in JSON at path ");
            sb.append(m());
            throw new IllegalStateException(sb.toString());
        }
        f();
        return a(1, "[");
    }

    public l b() throws IOException {
        return a(1, 2, "]");
    }

    public l c() throws IOException {
        if (this.i) {
            StringBuilder sb = new StringBuilder();
            sb.append("Object cannot be used as a map key in JSON at path ");
            sb.append(m());
            throw new IllegalStateException(sb.toString());
        }
        f();
        return a(3, VectorFormat.DEFAULT_PREFIX);
    }

    public l d() throws IOException {
        this.i = false;
        return a(3, 5, VectorFormat.DEFAULT_SUFFIX);
    }

    private l a(int i, String str) throws IOException {
        p();
        h();
        a(i);
        this.e[this.b - 1] = 0;
        this.j.b(str);
        return this;
    }

    private l a(int i, int i2, String str) throws IOException {
        int g = g();
        if (g != i2 && g != i) {
            throw new IllegalStateException("Nesting problem.");
        } else if (this.l != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Dangling name: ");
            sb.append(this.l);
            throw new IllegalStateException(sb.toString());
        } else {
            this.b--;
            this.d[this.b] = null;
            int[] iArr = this.e;
            int i3 = this.b - 1;
            iArr[i3] = iArr[i3] + 1;
            if (g == i2) {
                n();
            }
            this.j.b(str);
            return this;
        }
    }

    public l b(String str) throws IOException {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (this.b == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        } else {
            int g = g();
            if ((g == 3 || g == 5) && this.l == null) {
                this.l = str;
                this.d[this.b - 1] = str;
                this.i = false;
                return this;
            }
            throw new IllegalStateException("Nesting problem.");
        }
    }

    private void f() throws IOException {
        if (this.l != null) {
            o();
            a(this.j, this.l);
            this.l = null;
        }
    }

    public l c(String str) throws IOException {
        if (str == null) {
            return e();
        }
        if (this.i) {
            return b(str);
        }
        f();
        p();
        a(this.j, str);
        int[] iArr = this.e;
        int i = this.b - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    public l e() throws IOException {
        if (this.i) {
            StringBuilder sb = new StringBuilder();
            sb.append("null cannot be used as a map key in JSON at path ");
            sb.append(m());
            throw new IllegalStateException(sb.toString());
        }
        if (this.l != null) {
            if (this.h) {
                f();
            } else {
                this.l = null;
                return this;
            }
        }
        p();
        this.j.b("null");
        int[] iArr = this.e;
        int i = this.b - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    public l a(boolean z) throws IOException {
        if (this.i) {
            StringBuilder sb = new StringBuilder();
            sb.append("Boolean cannot be used as a map key in JSON at path ");
            sb.append(m());
            throw new IllegalStateException(sb.toString());
        }
        f();
        p();
        this.j.b(z ? "true" : "false");
        int[] iArr = this.e;
        int i = this.b - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    public l a(double d) throws IOException {
        if (!this.g && (Double.isNaN(d) || Double.isInfinite(d))) {
            StringBuilder sb = new StringBuilder();
            sb.append("Numeric values must be finite, but was ");
            sb.append(d);
            throw new IllegalArgumentException(sb.toString());
        } else if (this.i) {
            return b(Double.toString(d));
        } else {
            f();
            p();
            this.j.b(Double.toString(d));
            int[] iArr = this.e;
            int i = this.b - 1;
            iArr[i] = iArr[i] + 1;
            return this;
        }
    }

    public l a(long j2) throws IOException {
        if (this.i) {
            return b(Long.toString(j2));
        }
        f();
        p();
        this.j.b(Long.toString(j2));
        int[] iArr = this.e;
        int i = this.b - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    public l a(Number number) throws IOException {
        if (number == null) {
            return e();
        }
        String obj = number.toString();
        if (!this.g && (obj.equals("-Infinity") || obj.equals("Infinity") || obj.equals("NaN"))) {
            StringBuilder sb = new StringBuilder();
            sb.append("Numeric values must be finite, but was ");
            sb.append(number);
            throw new IllegalArgumentException(sb.toString());
        } else if (this.i) {
            return b(obj);
        } else {
            f();
            p();
            this.j.b(obj);
            int[] iArr = this.e;
            int i = this.b - 1;
            iArr[i] = iArr[i] + 1;
            return this;
        }
    }

    public void flush() throws IOException {
        if (this.b == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.j.flush();
    }

    public void close() throws IOException {
        this.j.close();
        int i = this.b;
        if (i > 1 || (i == 1 && this.c[i - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.b = 0;
    }

    static void a(d dVar, String str) throws IOException {
        String str2;
        String[] strArr = a;
        dVar.k(34);
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt < 128) {
                str2 = strArr[charAt];
                if (str2 == null) {
                }
            } else if (charAt == 8232) {
                str2 = "\\u2028";
            } else if (charAt == 8233) {
                str2 = "\\u2029";
            }
            if (i < i2) {
                dVar.b(str, i, i2);
            }
            dVar.b(str2);
            i = i2 + 1;
        }
        if (i < length) {
            dVar.b(str, i, length);
        }
        dVar.k(34);
    }

    private void n() throws IOException {
        if (this.f != null) {
            this.j.k(10);
            int i = this.b;
            for (int i2 = 1; i2 < i; i2++) {
                this.j.b(this.f);
            }
        }
    }

    private void o() throws IOException {
        int g = g();
        if (g == 5) {
            this.j.k(44);
        } else if (g != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        n();
        b(4);
    }

    private void p() throws IOException {
        switch (g()) {
            case 1:
                b(2);
                n();
                return;
            case 2:
                this.j.k(44);
                n();
                return;
            case 4:
                this.j.b(this.k);
                b(5);
                return;
            case 6:
                break;
            case 7:
                if (!this.g) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
                break;
            default:
                throw new IllegalStateException("Nesting problem.");
        }
        b(7);
    }
}
