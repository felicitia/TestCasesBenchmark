package com.google.gson.stream;

import com.etsy.android.lib.convos.Draft;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import org.apache.commons.math3.geometry.VectorFormat;

/* compiled from: JsonWriter */
public class b implements Closeable, Flushable {
    private static final String[] a = new String[128];
    private static final String[] b = ((String[]) a.clone());
    private final Writer c;
    private int[] d = new int[32];
    private int e = 0;
    private String f;
    private String g;
    private boolean h;
    private boolean i;
    private String j;
    private boolean k;

    static {
        for (int i2 = 0; i2 <= 31; i2++) {
            a[i2] = String.format("\\u%04x", new Object[]{Integer.valueOf(i2)});
        }
        a[34] = "\\\"";
        a[92] = "\\\\";
        a[9] = "\\t";
        a[8] = "\\b";
        a[10] = "\\n";
        a[13] = "\\r";
        a[12] = "\\f";
        b[60] = "\\u003c";
        b[62] = "\\u003e";
        b[38] = "\\u0026";
        b[61] = "\\u003d";
        b[39] = "\\u0027";
    }

    public b(Writer writer) {
        a(6);
        this.g = Draft.IMAGE_DELIMITER;
        this.k = true;
        if (writer == null) {
            throw new NullPointerException("out == null");
        }
        this.c = writer;
    }

    public final void c(String str) {
        if (str.length() == 0) {
            this.f = null;
            this.g = Draft.IMAGE_DELIMITER;
            return;
        }
        this.f = str;
        this.g = ": ";
    }

    public final void b(boolean z) {
        this.h = z;
    }

    public boolean g() {
        return this.h;
    }

    public final void c(boolean z) {
        this.k = z;
    }

    public final boolean h() {
        return this.k;
    }

    public b b() throws IOException {
        i();
        return a(1, "[");
    }

    public b c() throws IOException {
        return a(1, 2, "]");
    }

    public b d() throws IOException {
        i();
        return a(3, VectorFormat.DEFAULT_PREFIX);
    }

    public b e() throws IOException {
        return a(3, 5, VectorFormat.DEFAULT_SUFFIX);
    }

    private b a(int i2, String str) throws IOException {
        l();
        a(i2);
        this.c.write(str);
        return this;
    }

    private b a(int i2, int i3, String str) throws IOException {
        int a2 = a();
        if (a2 != i3 && a2 != i2) {
            throw new IllegalStateException("Nesting problem.");
        } else if (this.j != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Dangling name: ");
            sb.append(this.j);
            throw new IllegalStateException(sb.toString());
        } else {
            this.e--;
            if (a2 == i3) {
                j();
            }
            this.c.write(str);
            return this;
        }
    }

    private void a(int i2) {
        if (this.e == this.d.length) {
            int[] iArr = new int[(this.e * 2)];
            System.arraycopy(this.d, 0, iArr, 0, this.e);
            this.d = iArr;
        }
        int[] iArr2 = this.d;
        int i3 = this.e;
        this.e = i3 + 1;
        iArr2[i3] = i2;
    }

    private int a() {
        if (this.e != 0) {
            return this.d[this.e - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    private void b(int i2) {
        this.d[this.e - 1] = i2;
    }

    public b a(String str) throws IOException {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (this.j != null) {
            throw new IllegalStateException();
        } else if (this.e == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        } else {
            this.j = str;
            return this;
        }
    }

    private void i() throws IOException {
        if (this.j != null) {
            k();
            d(this.j);
            this.j = null;
        }
    }

    public b b(String str) throws IOException {
        if (str == null) {
            return f();
        }
        i();
        l();
        d(str);
        return this;
    }

    public b f() throws IOException {
        if (this.j != null) {
            if (this.k) {
                i();
            } else {
                this.j = null;
                return this;
            }
        }
        l();
        this.c.write("null");
        return this;
    }

    public b a(boolean z) throws IOException {
        i();
        l();
        this.c.write(z ? "true" : "false");
        return this;
    }

    public b a(Boolean bool) throws IOException {
        if (bool == null) {
            return f();
        }
        i();
        l();
        this.c.write(bool.booleanValue() ? "true" : "false");
        return this;
    }

    public b a(long j2) throws IOException {
        i();
        l();
        this.c.write(Long.toString(j2));
        return this;
    }

    public b a(Number number) throws IOException {
        if (number == null) {
            return f();
        }
        i();
        String obj = number.toString();
        if (this.h || (!obj.equals("-Infinity") && !obj.equals("Infinity") && !obj.equals("NaN"))) {
            l();
            this.c.append(obj);
            return this;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Numeric values must be finite, but was ");
        sb.append(number);
        throw new IllegalArgumentException(sb.toString());
    }

    public void flush() throws IOException {
        if (this.e == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.c.flush();
    }

    public void close() throws IOException {
        this.c.close();
        int i2 = this.e;
        if (i2 > 1 || (i2 == 1 && this.d[i2 - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.e = 0;
    }

    private void d(String str) throws IOException {
        String str2;
        String[] strArr = this.i ? b : a;
        this.c.write("\"");
        int length = str.length();
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = str.charAt(i3);
            if (charAt < 128) {
                str2 = strArr[charAt];
                if (str2 == null) {
                }
            } else if (charAt == 8232) {
                str2 = "\\u2028";
            } else if (charAt == 8233) {
                str2 = "\\u2029";
            }
            if (i2 < i3) {
                this.c.write(str, i2, i3 - i2);
            }
            this.c.write(str2);
            i2 = i3 + 1;
        }
        if (i2 < length) {
            this.c.write(str, i2, length - i2);
        }
        this.c.write("\"");
    }

    private void j() throws IOException {
        if (this.f != null) {
            this.c.write("\n");
            int i2 = this.e;
            for (int i3 = 1; i3 < i2; i3++) {
                this.c.write(this.f);
            }
        }
    }

    private void k() throws IOException {
        int a2 = a();
        if (a2 == 5) {
            this.c.write(44);
        } else if (a2 != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        j();
        b(4);
    }

    private void l() throws IOException {
        switch (a()) {
            case 1:
                b(2);
                j();
                return;
            case 2:
                this.c.append(',');
                j();
                return;
            case 4:
                this.c.append(this.g);
                b(5);
                return;
            case 6:
                break;
            case 7:
                if (!this.h) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
                break;
            default:
                throw new IllegalStateException("Nesting problem.");
        }
        b(7);
    }
}
