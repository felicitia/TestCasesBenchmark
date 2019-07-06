package com.squareup.moshi;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: JsonValueWriter */
final class k extends l {
    Object[] a = new Object[32];
    private String j;

    k() {
        a(6);
    }

    public Object f() {
        int i = this.b;
        if (i <= 1 && (i != 1 || this.c[i - 1] == 7)) {
            return this.a[0];
        }
        throw new IllegalStateException("Incomplete document");
    }

    public l a() throws IOException {
        if (this.i) {
            StringBuilder sb = new StringBuilder();
            sb.append("Array cannot be used as a map key in JSON at path ");
            sb.append(m());
            throw new IllegalStateException(sb.toString());
        }
        h();
        ArrayList arrayList = new ArrayList();
        a((Object) arrayList);
        this.a[this.b] = arrayList;
        this.e[this.b] = 0;
        a(1);
        return this;
    }

    public l b() throws IOException {
        if (g() != 1) {
            throw new IllegalStateException("Nesting problem.");
        }
        this.b--;
        this.a[this.b] = null;
        int[] iArr = this.e;
        int i = this.b - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    public l c() throws IOException {
        if (this.i) {
            StringBuilder sb = new StringBuilder();
            sb.append("Object cannot be used as a map key in JSON at path ");
            sb.append(m());
            throw new IllegalStateException(sb.toString());
        }
        h();
        LinkedHashTreeMap linkedHashTreeMap = new LinkedHashTreeMap();
        a((Object) linkedHashTreeMap);
        this.a[this.b] = linkedHashTreeMap;
        a(3);
        return this;
    }

    public l d() throws IOException {
        if (g() != 3) {
            throw new IllegalStateException("Nesting problem.");
        } else if (this.j != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Dangling name: ");
            sb.append(this.j);
            throw new IllegalStateException(sb.toString());
        } else {
            this.i = false;
            this.b--;
            this.a[this.b] = null;
            this.d[this.b] = null;
            int[] iArr = this.e;
            int i = this.b - 1;
            iArr[i] = iArr[i] + 1;
            return this;
        }
    }

    public l b(String str) throws IOException {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (this.b == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        } else if (g() == 3 && this.j == null) {
            this.j = str;
            this.d[this.b - 1] = str;
            this.i = false;
            return this;
        } else {
            throw new IllegalStateException("Nesting problem.");
        }
    }

    public l c(String str) throws IOException {
        if (this.i) {
            return b(str);
        }
        a((Object) str);
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
        a((Object) null);
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
        a((Object) Boolean.valueOf(z));
        int[] iArr = this.e;
        int i = this.b - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    public l a(double d) throws IOException {
        if (!this.g && (Double.isNaN(d) || d == Double.NEGATIVE_INFINITY || d == Double.POSITIVE_INFINITY)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Numeric values must be finite, but was ");
            sb.append(d);
            throw new IllegalArgumentException(sb.toString());
        } else if (this.i) {
            return b(Double.toString(d));
        } else {
            a((Object) Double.valueOf(d));
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
        a((Object) Long.valueOf(j2));
        int[] iArr = this.e;
        int i = this.b - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    public l a(Number number) throws IOException {
        BigDecimal bigDecimal;
        if ((number instanceof Byte) || (number instanceof Short) || (number instanceof Integer) || (number instanceof Long)) {
            return a(number.longValue());
        }
        if ((number instanceof Float) || (number instanceof Double)) {
            return a(number.doubleValue());
        }
        if (number == null) {
            return e();
        }
        if (number instanceof BigDecimal) {
            bigDecimal = (BigDecimal) number;
        } else {
            bigDecimal = new BigDecimal(number.toString());
        }
        if (this.i) {
            return b(bigDecimal.toString());
        }
        a((Object) bigDecimal);
        int[] iArr = this.e;
        int i = this.b - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    public void close() throws IOException {
        int i = this.b;
        if (i > 1 || (i == 1 && this.c[i - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.b = 0;
    }

    public void flush() throws IOException {
        if (this.b == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
    }

    private k a(Object obj) {
        int g = g();
        if (this.b == 1) {
            if (g != 6) {
                throw new IllegalStateException("JSON must have only one top-level value.");
            }
            this.c[this.b - 1] = 7;
            this.a[this.b - 1] = obj;
        } else if (g == 3 && this.j != null) {
            if (obj != null || this.h) {
                Object put = ((Map) this.a[this.b - 1]).put(this.j, obj);
                if (put != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Map key '");
                    sb.append(this.j);
                    sb.append("' has multiple values at path ");
                    sb.append(m());
                    sb.append(": ");
                    sb.append(put);
                    sb.append(" and ");
                    sb.append(obj);
                    throw new IllegalArgumentException(sb.toString());
                }
            }
            this.j = null;
        } else if (g == 1) {
            ((List) this.a[this.b - 1]).add(obj);
        } else {
            throw new IllegalStateException("Nesting problem.");
        }
        return this;
    }
}
