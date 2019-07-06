package com.squareup.moshi;

import com.squareup.moshi.JsonReader.Token;
import com.squareup.moshi.JsonReader.a;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: JsonValueReader */
final class j extends JsonReader {
    private static final Object g = new Object();
    private Object[] h = new Object[32];

    j(Object obj) {
        this.b[this.a] = 7;
        Object[] objArr = this.h;
        int i = this.a;
        this.a = i + 1;
        objArr[i] = obj;
    }

    public void c() throws IOException {
        ListIterator listIterator = ((List) a(List.class, Token.BEGIN_ARRAY)).listIterator();
        this.h[this.a - 1] = listIterator;
        this.b[this.a - 1] = 1;
        this.d[this.a - 1] = 0;
        if (listIterator.hasNext()) {
            a(listIterator.next());
        }
    }

    public void d() throws IOException {
        ListIterator listIterator = (ListIterator) a(ListIterator.class, Token.END_ARRAY);
        if (listIterator.hasNext()) {
            throw a(listIterator, Token.END_ARRAY);
        }
        u();
    }

    public void e() throws IOException {
        Iterator it = ((Map) a(Map.class, Token.BEGIN_OBJECT)).entrySet().iterator();
        this.h[this.a - 1] = it;
        this.b[this.a - 1] = 3;
        if (it.hasNext()) {
            a(it.next());
        }
    }

    public void f() throws IOException {
        Iterator it = (Iterator) a(Iterator.class, Token.END_OBJECT);
        if ((it instanceof ListIterator) || it.hasNext()) {
            throw a(it, Token.END_OBJECT);
        }
        this.c[this.a - 1] = null;
        u();
    }

    public boolean g() throws IOException {
        boolean z = true;
        if (this.a == 0) {
            return true;
        }
        Object obj = this.h[this.a - 1];
        if ((obj instanceof Iterator) && !((Iterator) obj).hasNext()) {
            z = false;
        }
        return z;
    }

    public Token h() throws IOException {
        if (this.a == 0) {
            return Token.END_DOCUMENT;
        }
        Object obj = this.h[this.a - 1];
        if (obj instanceof ListIterator) {
            return Token.END_ARRAY;
        }
        if (obj instanceof Iterator) {
            return Token.END_OBJECT;
        }
        if (obj instanceof List) {
            return Token.BEGIN_ARRAY;
        }
        if (obj instanceof Map) {
            return Token.BEGIN_OBJECT;
        }
        if (obj instanceof Entry) {
            return Token.NAME;
        }
        if (obj instanceof String) {
            return Token.STRING;
        }
        if (obj instanceof Boolean) {
            return Token.BOOLEAN;
        }
        if (obj instanceof Number) {
            return Token.NUMBER;
        }
        if (obj == null) {
            return Token.NULL;
        }
        if (obj == g) {
            throw new IllegalStateException("JsonReader is closed");
        }
        throw a(obj, "a JSON value");
    }

    public String i() throws IOException {
        Entry entry = (Entry) a(Entry.class, Token.NAME);
        String a = a(entry);
        this.h[this.a - 1] = entry.getValue();
        this.c[this.a - 2] = a;
        return a;
    }

    public int a(a aVar) throws IOException {
        Entry entry = (Entry) a(Entry.class, Token.NAME);
        String a = a(entry);
        int length = aVar.a.length;
        for (int i = 0; i < length; i++) {
            if (aVar.a[i].equals(a)) {
                this.h[this.a - 1] = entry.getValue();
                this.c[this.a - 2] = a;
                return i;
            }
        }
        return -1;
    }

    public void j() throws IOException {
        if (this.f) {
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot skip unexpected ");
            sb.append(h());
            sb.append(" at ");
            sb.append(s());
            throw new JsonDataException(sb.toString());
        }
        this.h[this.a - 1] = ((Entry) a(Entry.class, Token.NAME)).getValue();
        this.c[this.a - 2] = "null";
    }

    public String k() throws IOException {
        String str = this.a != 0 ? this.h[this.a - 1] : null;
        if (str instanceof String) {
            u();
            return str;
        } else if (str instanceof Number) {
            u();
            return str.toString();
        } else if (str == g) {
            throw new IllegalStateException("JsonReader is closed");
        } else {
            throw a(str, Token.STRING);
        }
    }

    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: type inference failed for: r0v7 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int b(com.squareup.moshi.JsonReader.a r6) throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.a
            if (r0 == 0) goto L_0x000d
            java.lang.Object[] r0 = r5.h
            int r1 = r5.a
            int r1 = r1 + -1
            r0 = r0[r1]
            goto L_0x000e
        L_0x000d:
            r0 = 0
        L_0x000e:
            boolean r1 = r0 instanceof java.lang.String
            r2 = -1
            if (r1 != 0) goto L_0x0020
            java.lang.Object r6 = g
            if (r0 != r6) goto L_0x001f
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "JsonReader is closed"
            r6.<init>(r0)
            throw r6
        L_0x001f:
            return r2
        L_0x0020:
            java.lang.String r0 = (java.lang.String) r0
            r1 = 0
            java.lang.String[] r3 = r6.a
            int r3 = r3.length
        L_0x0026:
            if (r1 >= r3) goto L_0x0039
            java.lang.String[] r4 = r6.a
            r4 = r4[r1]
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0036
            r5.u()
            return r1
        L_0x0036:
            int r1 = r1 + 1
            goto L_0x0026
        L_0x0039:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.j.b(com.squareup.moshi.JsonReader$a):int");
    }

    public boolean l() throws IOException {
        Boolean bool = (Boolean) a(Boolean.class, Token.BOOLEAN);
        u();
        return bool.booleanValue();
    }

    public <T> T m() throws IOException {
        a(Void.class, Token.NULL);
        u();
        return null;
    }

    public double n() throws IOException {
        double d;
        Object a = a(Object.class, Token.NUMBER);
        if (a instanceof Number) {
            d = ((Number) a).doubleValue();
        } else if (a instanceof String) {
            try {
                d = Double.parseDouble((String) a);
            } catch (NumberFormatException unused) {
                throw a(a, Token.NUMBER);
            }
        } else {
            throw a(a, Token.NUMBER);
        }
        if (this.e || (!Double.isNaN(d) && !Double.isInfinite(d))) {
            u();
            return d;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("JSON forbids NaN and infinities: ");
        sb.append(d);
        sb.append(" at path ");
        sb.append(s());
        throw new JsonEncodingException(sb.toString());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0036, code lost:
        throw a(r0, com.squareup.moshi.JsonReader.Token.NUMBER);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        r1 = new java.math.BigDecimal((java.lang.String) r0).longValueExact();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0020 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long o() throws java.io.IOException {
        /*
            r3 = this;
            java.lang.Class<java.lang.Object> r0 = java.lang.Object.class
            com.squareup.moshi.JsonReader$Token r1 = com.squareup.moshi.JsonReader.Token.NUMBER
            java.lang.Object r0 = r3.a(r0, r1)
            boolean r1 = r0 instanceof java.lang.Number
            if (r1 == 0) goto L_0x0014
            java.lang.Number r0 = (java.lang.Number) r0
            long r0 = r0.longValue()
            r1 = r0
            goto L_0x002c
        L_0x0014:
            boolean r1 = r0 instanceof java.lang.String
            if (r1 == 0) goto L_0x0037
            r1 = r0
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ NumberFormatException -> 0x0020 }
            long r1 = java.lang.Long.parseLong(r1)     // Catch:{ NumberFormatException -> 0x0020 }
            goto L_0x002c
        L_0x0020:
            java.math.BigDecimal r1 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x0030 }
            r2 = r0
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ NumberFormatException -> 0x0030 }
            r1.<init>(r2)     // Catch:{ NumberFormatException -> 0x0030 }
            long r1 = r1.longValueExact()     // Catch:{ NumberFormatException -> 0x0030 }
        L_0x002c:
            r3.u()
            return r1
        L_0x0030:
            com.squareup.moshi.JsonReader$Token r1 = com.squareup.moshi.JsonReader.Token.NUMBER
            com.squareup.moshi.JsonDataException r0 = r3.a(r0, r1)
            throw r0
        L_0x0037:
            com.squareup.moshi.JsonReader$Token r1 = com.squareup.moshi.JsonReader.Token.NUMBER
            com.squareup.moshi.JsonDataException r0 = r3.a(r0, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.j.o():long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0036, code lost:
        throw a(r0, com.squareup.moshi.JsonReader.Token.NUMBER);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        r1 = new java.math.BigDecimal((java.lang.String) r0).intValueExact();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0020 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int p() throws java.io.IOException {
        /*
            r3 = this;
            java.lang.Class<java.lang.Object> r0 = java.lang.Object.class
            com.squareup.moshi.JsonReader$Token r1 = com.squareup.moshi.JsonReader.Token.NUMBER
            java.lang.Object r0 = r3.a(r0, r1)
            boolean r1 = r0 instanceof java.lang.Number
            if (r1 == 0) goto L_0x0014
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            r1 = r0
            goto L_0x002c
        L_0x0014:
            boolean r1 = r0 instanceof java.lang.String
            if (r1 == 0) goto L_0x0037
            r1 = r0
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ NumberFormatException -> 0x0020 }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ NumberFormatException -> 0x0020 }
            goto L_0x002c
        L_0x0020:
            java.math.BigDecimal r1 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x0030 }
            r2 = r0
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ NumberFormatException -> 0x0030 }
            r1.<init>(r2)     // Catch:{ NumberFormatException -> 0x0030 }
            int r1 = r1.intValueExact()     // Catch:{ NumberFormatException -> 0x0030 }
        L_0x002c:
            r3.u()
            return r1
        L_0x0030:
            com.squareup.moshi.JsonReader$Token r1 = com.squareup.moshi.JsonReader.Token.NUMBER
            com.squareup.moshi.JsonDataException r0 = r3.a(r0, r1)
            throw r0
        L_0x0037:
            com.squareup.moshi.JsonReader$Token r1 = com.squareup.moshi.JsonReader.Token.NUMBER
            com.squareup.moshi.JsonDataException r0 = r3.a(r0, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.j.p():int");
    }

    public void q() throws IOException {
        if (this.f) {
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot skip unexpected ");
            sb.append(h());
            sb.append(" at ");
            sb.append(s());
            throw new JsonDataException(sb.toString());
        }
        if (this.a > 1) {
            this.c[this.a - 2] = "null";
        }
        if ((this.a != 0 ? this.h[this.a - 1] : null) instanceof Entry) {
            this.h[this.a - 1] = ((Entry) this.h[this.a - 1]).getValue();
        } else if (this.a > 0) {
            u();
        }
    }

    /* access modifiers changed from: 0000 */
    public void t() throws IOException {
        if (g()) {
            a((Object) i());
        }
    }

    public void close() throws IOException {
        Arrays.fill(this.h, 0, this.a, null);
        this.h[0] = g;
        this.b[0] = 8;
        this.a = 1;
    }

    private void a(Object obj) {
        if (this.a == this.h.length) {
            if (this.a == 256) {
                StringBuilder sb = new StringBuilder();
                sb.append("Nesting too deep at ");
                sb.append(s());
                throw new JsonDataException(sb.toString());
            }
            this.b = Arrays.copyOf(this.b, this.b.length * 2);
            this.c = (String[]) Arrays.copyOf(this.c, this.c.length * 2);
            this.d = Arrays.copyOf(this.d, this.d.length * 2);
            this.h = Arrays.copyOf(this.h, this.h.length * 2);
        }
        Object[] objArr = this.h;
        int i = this.a;
        this.a = i + 1;
        objArr[i] = obj;
    }

    private <T> T a(Class<T> cls, Token token) throws IOException {
        Object obj = this.a != 0 ? this.h[this.a - 1] : null;
        if (cls.isInstance(obj)) {
            return cls.cast(obj);
        }
        if (obj == null && token == Token.NULL) {
            return null;
        }
        if (obj == g) {
            throw new IllegalStateException("JsonReader is closed");
        }
        throw a(obj, token);
    }

    private String a(Entry<?, ?> entry) {
        Object key = entry.getKey();
        if (key instanceof String) {
            return (String) key;
        }
        throw a(key, Token.NAME);
    }

    private void u() {
        this.a--;
        this.h[this.a] = null;
        this.b[this.a] = 0;
        if (this.a > 0) {
            int[] iArr = this.d;
            int i = this.a - 1;
            iArr[i] = iArr[i] + 1;
            Object obj = this.h[this.a - 1];
            if (obj instanceof Iterator) {
                Iterator it = (Iterator) obj;
                if (it.hasNext()) {
                    a(it.next());
                }
            }
        }
    }
}
