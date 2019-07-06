package com.google.gson.internal.a;

import com.google.gson.h;
import com.google.gson.l;
import com.google.gson.m;
import com.google.gson.n;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.a;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map.Entry;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: JsonTreeReader */
public final class e extends a {
    private static final Reader b = new Reader() {
        public int read(char[] cArr, int i, int i2) throws IOException {
            throw new AssertionError();
        }

        public void close() throws IOException {
            throw new AssertionError();
        }
    };
    private static final Object c = new Object();
    private Object[] d;
    private int e;
    private String[] f;
    private int[] g;

    public void a() throws IOException {
        a(JsonToken.BEGIN_ARRAY);
        a((Object) ((h) t()).iterator());
        this.g[this.e - 1] = 0;
    }

    public void b() throws IOException {
        a(JsonToken.END_ARRAY);
        u();
        u();
        if (this.e > 0) {
            int[] iArr = this.g;
            int i = this.e - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    public void c() throws IOException {
        a(JsonToken.BEGIN_OBJECT);
        a((Object) ((m) t()).o().iterator());
    }

    public void d() throws IOException {
        a(JsonToken.END_OBJECT);
        u();
        u();
        if (this.e > 0) {
            int[] iArr = this.g;
            int i = this.e - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    public boolean e() throws IOException {
        JsonToken f2 = f();
        return (f2 == JsonToken.END_OBJECT || f2 == JsonToken.END_ARRAY) ? false : true;
    }

    public JsonToken f() throws IOException {
        if (this.e == 0) {
            return JsonToken.END_DOCUMENT;
        }
        Object t = t();
        if (t instanceof Iterator) {
            boolean z = this.d[this.e - 2] instanceof m;
            Iterator it = (Iterator) t;
            if (!it.hasNext()) {
                return z ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
            } else if (z) {
                return JsonToken.NAME;
            } else {
                a(it.next());
                return f();
            }
        } else if (t instanceof m) {
            return JsonToken.BEGIN_OBJECT;
        } else {
            if (t instanceof h) {
                return JsonToken.BEGIN_ARRAY;
            }
            if (t instanceof n) {
                n nVar = (n) t;
                if (nVar.q()) {
                    return JsonToken.STRING;
                }
                if (nVar.o()) {
                    return JsonToken.BOOLEAN;
                }
                if (nVar.p()) {
                    return JsonToken.NUMBER;
                }
                throw new AssertionError();
            } else if (t instanceof l) {
                return JsonToken.NULL;
            } else {
                if (t == c) {
                    throw new IllegalStateException("JsonReader is closed");
                }
                throw new AssertionError();
            }
        }
    }

    private Object t() {
        return this.d[this.e - 1];
    }

    private Object u() {
        Object[] objArr = this.d;
        int i = this.e - 1;
        this.e = i;
        Object obj = objArr[i];
        this.d[this.e] = null;
        return obj;
    }

    private void a(JsonToken jsonToken) throws IOException {
        if (f() != jsonToken) {
            StringBuilder sb = new StringBuilder();
            sb.append("Expected ");
            sb.append(jsonToken);
            sb.append(" but was ");
            sb.append(f());
            sb.append(v());
            throw new IllegalStateException(sb.toString());
        }
    }

    public String g() throws IOException {
        a(JsonToken.NAME);
        Entry entry = (Entry) ((Iterator) t()).next();
        String str = (String) entry.getKey();
        this.f[this.e - 1] = str;
        a(entry.getValue());
        return str;
    }

    public String h() throws IOException {
        JsonToken f2 = f();
        if (f2 == JsonToken.STRING || f2 == JsonToken.NUMBER) {
            String b2 = ((n) u()).b();
            if (this.e > 0) {
                int[] iArr = this.g;
                int i = this.e - 1;
                iArr[i] = iArr[i] + 1;
            }
            return b2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Expected ");
        sb.append(JsonToken.STRING);
        sb.append(" but was ");
        sb.append(f2);
        sb.append(v());
        throw new IllegalStateException(sb.toString());
    }

    public boolean i() throws IOException {
        a(JsonToken.BOOLEAN);
        boolean f2 = ((n) u()).f();
        if (this.e > 0) {
            int[] iArr = this.g;
            int i = this.e - 1;
            iArr[i] = iArr[i] + 1;
        }
        return f2;
    }

    public void j() throws IOException {
        a(JsonToken.NULL);
        u();
        if (this.e > 0) {
            int[] iArr = this.g;
            int i = this.e - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    public double k() throws IOException {
        JsonToken f2 = f();
        if (f2 == JsonToken.NUMBER || f2 == JsonToken.STRING) {
            double c2 = ((n) t()).c();
            if (q() || (!Double.isNaN(c2) && !Double.isInfinite(c2))) {
                u();
                if (this.e > 0) {
                    int[] iArr = this.g;
                    int i = this.e - 1;
                    iArr[i] = iArr[i] + 1;
                }
                return c2;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("JSON forbids NaN and infinities: ");
            sb.append(c2);
            throw new NumberFormatException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Expected ");
        sb2.append(JsonToken.NUMBER);
        sb2.append(" but was ");
        sb2.append(f2);
        sb2.append(v());
        throw new IllegalStateException(sb2.toString());
    }

    public long l() throws IOException {
        JsonToken f2 = f();
        if (f2 == JsonToken.NUMBER || f2 == JsonToken.STRING) {
            long d2 = ((n) t()).d();
            u();
            if (this.e > 0) {
                int[] iArr = this.g;
                int i = this.e - 1;
                iArr[i] = iArr[i] + 1;
            }
            return d2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Expected ");
        sb.append(JsonToken.NUMBER);
        sb.append(" but was ");
        sb.append(f2);
        sb.append(v());
        throw new IllegalStateException(sb.toString());
    }

    public int m() throws IOException {
        JsonToken f2 = f();
        if (f2 == JsonToken.NUMBER || f2 == JsonToken.STRING) {
            int e2 = ((n) t()).e();
            u();
            if (this.e > 0) {
                int[] iArr = this.g;
                int i = this.e - 1;
                iArr[i] = iArr[i] + 1;
            }
            return e2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Expected ");
        sb.append(JsonToken.NUMBER);
        sb.append(" but was ");
        sb.append(f2);
        sb.append(v());
        throw new IllegalStateException(sb.toString());
    }

    public void close() throws IOException {
        this.d = new Object[]{c};
        this.e = 1;
    }

    public void n() throws IOException {
        if (f() == JsonToken.NAME) {
            g();
            this.f[this.e - 2] = "null";
        } else {
            u();
            if (this.e > 0) {
                this.f[this.e - 1] = "null";
            }
        }
        if (this.e > 0) {
            int[] iArr = this.g;
            int i = this.e - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    public void o() throws IOException {
        a(JsonToken.NAME);
        Entry entry = (Entry) ((Iterator) t()).next();
        a(entry.getValue());
        a((Object) new n((String) entry.getKey()));
    }

    private void a(Object obj) {
        if (this.e == this.d.length) {
            Object[] objArr = new Object[(this.e * 2)];
            int[] iArr = new int[(this.e * 2)];
            String[] strArr = new String[(this.e * 2)];
            System.arraycopy(this.d, 0, objArr, 0, this.e);
            System.arraycopy(this.g, 0, iArr, 0, this.e);
            System.arraycopy(this.f, 0, strArr, 0, this.e);
            this.d = objArr;
            this.g = iArr;
            this.f = strArr;
        }
        Object[] objArr2 = this.d;
        int i = this.e;
        this.e = i + 1;
        objArr2[i] = obj;
    }

    public String p() {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int i = 0;
        while (i < this.e) {
            if (this.d[i] instanceof h) {
                i++;
                if (this.d[i] instanceof Iterator) {
                    sb.append('[');
                    sb.append(this.g[i]);
                    sb.append(']');
                }
            } else if (this.d[i] instanceof m) {
                i++;
                if (this.d[i] instanceof Iterator) {
                    sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
                    if (this.f[i] != null) {
                        sb.append(this.f[i]);
                    }
                }
            }
            i++;
        }
        return sb.toString();
    }

    private String v() {
        StringBuilder sb = new StringBuilder();
        sb.append(" at path ");
        sb.append(p());
        return sb.toString();
    }
}
