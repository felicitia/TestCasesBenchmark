package retrofit2;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Map;
import java.util.Map.Entry;
import okhttp3.s;
import okhttp3.z;
import org.apache.http.entity.mime.MIME;

/* compiled from: ParameterHandler */
abstract class i<T> {

    /* compiled from: ParameterHandler */
    static final class a<T> extends i<T> {
        private final e<T, z> a;

        a(e<T, z> eVar) {
            this.a = eVar;
        }

        /* access modifiers changed from: 0000 */
        public void a(k kVar, T t) {
            if (t == null) {
                throw new IllegalArgumentException("Body parameter value must not be null.");
            }
            try {
                kVar.a((z) this.a.a(t));
            } catch (IOException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to convert ");
                sb.append(t);
                sb.append(" to RequestBody");
                throw new RuntimeException(sb.toString(), e);
            }
        }
    }

    /* compiled from: ParameterHandler */
    static final class b<T> extends i<T> {
        private final String a;
        private final e<T, String> b;
        private final boolean c;

        b(String str, e<T, String> eVar, boolean z) {
            this.a = (String) o.a(str, "name == null");
            this.b = eVar;
            this.c = z;
        }

        /* access modifiers changed from: 0000 */
        public void a(k kVar, T t) throws IOException {
            if (t != null) {
                String str = (String) this.b.a(t);
                if (str != null) {
                    kVar.c(this.a, str, this.c);
                }
            }
        }
    }

    /* compiled from: ParameterHandler */
    static final class c<T> extends i<Map<String, T>> {
        private final e<T, String> a;
        private final boolean b;

        c(e<T, String> eVar, boolean z) {
            this.a = eVar;
            this.b = z;
        }

        /* access modifiers changed from: 0000 */
        public void a(k kVar, Map<String, T> map) throws IOException {
            if (map == null) {
                throw new IllegalArgumentException("Field map was null.");
            }
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null) {
                    throw new IllegalArgumentException("Field map contained null key.");
                }
                Object value = entry.getValue();
                if (value == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Field map contained null value for key '");
                    sb.append(str);
                    sb.append("'.");
                    throw new IllegalArgumentException(sb.toString());
                }
                String str2 = (String) this.a.a(value);
                if (str2 == null) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Field map value '");
                    sb2.append(value);
                    sb2.append("' converted to null by ");
                    sb2.append(this.a.getClass().getName());
                    sb2.append(" for key '");
                    sb2.append(str);
                    sb2.append("'.");
                    throw new IllegalArgumentException(sb2.toString());
                }
                kVar.c(str, str2, this.b);
            }
        }
    }

    /* compiled from: ParameterHandler */
    static final class d<T> extends i<T> {
        private final String a;
        private final e<T, String> b;

        d(String str, e<T, String> eVar) {
            this.a = (String) o.a(str, "name == null");
            this.b = eVar;
        }

        /* access modifiers changed from: 0000 */
        public void a(k kVar, T t) throws IOException {
            if (t != null) {
                String str = (String) this.b.a(t);
                if (str != null) {
                    kVar.a(this.a, str);
                }
            }
        }
    }

    /* compiled from: ParameterHandler */
    static final class e<T> extends i<Map<String, T>> {
        private final e<T, String> a;

        e(e<T, String> eVar) {
            this.a = eVar;
        }

        /* access modifiers changed from: 0000 */
        public void a(k kVar, Map<String, T> map) throws IOException {
            if (map == null) {
                throw new IllegalArgumentException("Header map was null.");
            }
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null) {
                    throw new IllegalArgumentException("Header map contained null key.");
                }
                Object value = entry.getValue();
                if (value == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Header map contained null value for key '");
                    sb.append(str);
                    sb.append("'.");
                    throw new IllegalArgumentException(sb.toString());
                }
                kVar.a(str, (String) this.a.a(value));
            }
        }
    }

    /* compiled from: ParameterHandler */
    static final class f<T> extends i<T> {
        private final s a;
        private final e<T, z> b;

        f(s sVar, e<T, z> eVar) {
            this.a = sVar;
            this.b = eVar;
        }

        /* access modifiers changed from: 0000 */
        public void a(k kVar, T t) {
            if (t != null) {
                try {
                    kVar.a(this.a, (z) this.b.a(t));
                } catch (IOException e) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unable to convert ");
                    sb.append(t);
                    sb.append(" to RequestBody");
                    throw new RuntimeException(sb.toString(), e);
                }
            }
        }
    }

    /* compiled from: ParameterHandler */
    static final class g<T> extends i<Map<String, T>> {
        private final e<T, z> a;
        private final String b;

        g(e<T, z> eVar, String str) {
            this.a = eVar;
            this.b = str;
        }

        /* access modifiers changed from: 0000 */
        public void a(k kVar, Map<String, T> map) throws IOException {
            if (map == null) {
                throw new IllegalArgumentException("Part map was null.");
            }
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null) {
                    throw new IllegalArgumentException("Part map contained null key.");
                }
                Object value = entry.getValue();
                if (value == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Part map contained null value for key '");
                    sb.append(str);
                    sb.append("'.");
                    throw new IllegalArgumentException(sb.toString());
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append("form-data; name=\"");
                sb2.append(str);
                sb2.append("\"");
                kVar.a(s.a(MIME.CONTENT_DISPOSITION, sb2.toString(), MIME.CONTENT_TRANSFER_ENC, this.b), (z) this.a.a(value));
            }
        }
    }

    /* compiled from: ParameterHandler */
    static final class h<T> extends i<T> {
        private final String a;
        private final e<T, String> b;
        private final boolean c;

        h(String str, e<T, String> eVar, boolean z) {
            this.a = (String) o.a(str, "name == null");
            this.b = eVar;
            this.c = z;
        }

        /* access modifiers changed from: 0000 */
        public void a(k kVar, T t) throws IOException {
            if (t == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Path parameter \"");
                sb.append(this.a);
                sb.append("\" value must not be null.");
                throw new IllegalArgumentException(sb.toString());
            }
            kVar.a(this.a, (String) this.b.a(t), this.c);
        }
    }

    /* renamed from: retrofit2.i$i reason: collision with other inner class name */
    /* compiled from: ParameterHandler */
    static final class C0200i<T> extends i<T> {
        private final String a;
        private final e<T, String> b;
        private final boolean c;

        C0200i(String str, e<T, String> eVar, boolean z) {
            this.a = (String) o.a(str, "name == null");
            this.b = eVar;
            this.c = z;
        }

        /* access modifiers changed from: 0000 */
        public void a(k kVar, T t) throws IOException {
            if (t != null) {
                String str = (String) this.b.a(t);
                if (str != null) {
                    kVar.b(this.a, str, this.c);
                }
            }
        }
    }

    /* compiled from: ParameterHandler */
    static final class j<T> extends i<Map<String, T>> {
        private final e<T, String> a;
        private final boolean b;

        j(e<T, String> eVar, boolean z) {
            this.a = eVar;
            this.b = z;
        }

        /* access modifiers changed from: 0000 */
        public void a(k kVar, Map<String, T> map) throws IOException {
            if (map == null) {
                throw new IllegalArgumentException("Query map was null.");
            }
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null) {
                    throw new IllegalArgumentException("Query map contained null key.");
                }
                Object value = entry.getValue();
                if (value == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Query map contained null value for key '");
                    sb.append(str);
                    sb.append("'.");
                    throw new IllegalArgumentException(sb.toString());
                }
                String str2 = (String) this.a.a(value);
                if (str2 == null) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Query map value '");
                    sb2.append(value);
                    sb2.append("' converted to null by ");
                    sb2.append(this.a.getClass().getName());
                    sb2.append(" for key '");
                    sb2.append(str);
                    sb2.append("'.");
                    throw new IllegalArgumentException(sb2.toString());
                }
                kVar.b(str, str2, this.b);
            }
        }
    }

    /* compiled from: ParameterHandler */
    static final class k<T> extends i<T> {
        private final e<T, String> a;
        private final boolean b;

        k(e<T, String> eVar, boolean z) {
            this.a = eVar;
            this.b = z;
        }

        /* access modifiers changed from: 0000 */
        public void a(k kVar, T t) throws IOException {
            if (t != null) {
                kVar.b((String) this.a.a(t), null, this.b);
            }
        }
    }

    /* compiled from: ParameterHandler */
    static final class l extends i<okhttp3.v.b> {
        static final l a = new l();

        private l() {
        }

        /* access modifiers changed from: 0000 */
        public void a(k kVar, okhttp3.v.b bVar) {
            if (bVar != null) {
                kVar.a(bVar);
            }
        }
    }

    /* compiled from: ParameterHandler */
    static final class m extends i<Object> {
        m() {
        }

        /* access modifiers changed from: 0000 */
        public void a(k kVar, Object obj) {
            o.a(obj, "@Url parameter is null.");
            kVar.a(obj);
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract void a(k kVar, T t) throws IOException;

    i() {
    }

    /* access modifiers changed from: 0000 */
    public final i<Iterable<T>> a() {
        return new i<Iterable<T>>() {
            /* access modifiers changed from: 0000 */
            public void a(k kVar, Iterable<T> iterable) throws IOException {
                if (iterable != null) {
                    for (T a2 : iterable) {
                        i.this.a(kVar, a2);
                    }
                }
            }
        };
    }

    /* access modifiers changed from: 0000 */
    public final i<Object> b() {
        return new i<Object>() {
            /* access modifiers changed from: 0000 */
            public void a(k kVar, Object obj) throws IOException {
                if (obj != null) {
                    int length = Array.getLength(obj);
                    for (int i = 0; i < length; i++) {
                        i.this.a(kVar, Array.get(obj, i));
                    }
                }
            }
        };
    }
}
