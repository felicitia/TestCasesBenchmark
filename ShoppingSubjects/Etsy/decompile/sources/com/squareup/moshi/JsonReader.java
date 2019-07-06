package com.squareup.moshi;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import okio.ByteString;
import okio.c;
import okio.d;
import okio.e;
import okio.n;

public abstract class JsonReader implements Closeable {
    int a = 0;
    int[] b = new int[32];
    String[] c = new String[32];
    int[] d = new int[32];
    boolean e;
    boolean f;

    public enum Token {
        BEGIN_ARRAY,
        END_ARRAY,
        BEGIN_OBJECT,
        END_OBJECT,
        NAME,
        STRING,
        NUMBER,
        BOOLEAN,
        NULL,
        END_DOCUMENT
    }

    public static final class a {
        final String[] a;
        final n b;

        private a(String[] strArr, n nVar) {
            this.a = strArr;
            this.b = nVar;
        }

        public static a a(String... strArr) {
            try {
                ByteString[] byteStringArr = new ByteString[strArr.length];
                c cVar = new c();
                for (int i = 0; i < strArr.length; i++) {
                    i.a((d) cVar, strArr[i]);
                    cVar.i();
                    byteStringArr[i] = cVar.o();
                }
                return new a((String[]) strArr.clone(), n.a(byteStringArr));
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }
    }

    public abstract int a(a aVar) throws IOException;

    public abstract int b(a aVar) throws IOException;

    public abstract void c() throws IOException;

    public abstract void d() throws IOException;

    public abstract void e() throws IOException;

    public abstract void f() throws IOException;

    public abstract boolean g() throws IOException;

    public abstract Token h() throws IOException;

    public abstract String i() throws IOException;

    public abstract void j() throws IOException;

    public abstract String k() throws IOException;

    public abstract boolean l() throws IOException;

    public abstract <T> T m() throws IOException;

    public abstract double n() throws IOException;

    public abstract long o() throws IOException;

    public abstract int p() throws IOException;

    public abstract void q() throws IOException;

    /* access modifiers changed from: 0000 */
    public abstract void t() throws IOException;

    public static JsonReader a(e eVar) {
        return new h(eVar);
    }

    JsonReader() {
    }

    /* access modifiers changed from: 0000 */
    public final void a(int i) {
        if (this.a == this.b.length) {
            if (this.a == 256) {
                StringBuilder sb = new StringBuilder();
                sb.append("Nesting too deep at ");
                sb.append(s());
                throw new JsonDataException(sb.toString());
            }
            this.b = Arrays.copyOf(this.b, this.b.length * 2);
            this.c = (String[]) Arrays.copyOf(this.c, this.c.length * 2);
            this.d = Arrays.copyOf(this.d, this.d.length * 2);
        }
        int[] iArr = this.b;
        int i2 = this.a;
        this.a = i2 + 1;
        iArr[i2] = i;
    }

    /* access modifiers changed from: 0000 */
    public final JsonEncodingException a(String str) throws JsonEncodingException {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" at path ");
        sb.append(s());
        throw new JsonEncodingException(sb.toString());
    }

    /* access modifiers changed from: 0000 */
    public final JsonDataException a(Object obj, Object obj2) {
        if (obj == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Expected ");
            sb.append(obj2);
            sb.append(" but was null at path ");
            sb.append(s());
            return new JsonDataException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Expected ");
        sb2.append(obj2);
        sb2.append(" but was ");
        sb2.append(obj);
        sb2.append(", a ");
        sb2.append(obj.getClass().getName());
        sb2.append(", at path ");
        sb2.append(s());
        return new JsonDataException(sb2.toString());
    }

    public final void a(boolean z) {
        this.e = z;
    }

    public final boolean a() {
        return this.e;
    }

    public final void b(boolean z) {
        this.f = z;
    }

    public final boolean b() {
        return this.f;
    }

    public final Object r() throws IOException {
        switch (h()) {
            case BEGIN_ARRAY:
                ArrayList arrayList = new ArrayList();
                c();
                while (g()) {
                    arrayList.add(r());
                }
                d();
                return arrayList;
            case BEGIN_OBJECT:
                LinkedHashTreeMap linkedHashTreeMap = new LinkedHashTreeMap();
                e();
                while (g()) {
                    String i = i();
                    Object r = r();
                    Object put = linkedHashTreeMap.put(i, r);
                    if (put != null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Map key '");
                        sb.append(i);
                        sb.append("' has multiple values at path ");
                        sb.append(s());
                        sb.append(": ");
                        sb.append(put);
                        sb.append(" and ");
                        sb.append(r);
                        throw new JsonDataException(sb.toString());
                    }
                }
                f();
                return linkedHashTreeMap;
            case STRING:
                return k();
            case NUMBER:
                return Double.valueOf(n());
            case BOOLEAN:
                return Boolean.valueOf(l());
            case NULL:
                return m();
            default:
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Expected a value but was ");
                sb2.append(h());
                sb2.append(" at path ");
                sb2.append(s());
                throw new IllegalStateException(sb2.toString());
        }
    }

    public final String s() {
        return g.a(this.a, this.b, this.c, this.d);
    }
}
