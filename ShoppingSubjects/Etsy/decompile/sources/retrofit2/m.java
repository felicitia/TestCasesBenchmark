package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import okhttp3.HttpUrl;
import okhttp3.ab;
import okhttp3.w;
import okhttp3.z;

/* compiled from: Retrofit */
public final class m {
    final okhttp3.e.a a;
    final HttpUrl b;
    final List<retrofit2.e.a> c;
    final List<retrofit2.c.a> d;
    final Executor e;
    final boolean f;
    private final Map<Method, n<?, ?>> g = new ConcurrentHashMap();

    /* compiled from: Retrofit */
    public static final class a {
        private final j a;
        private okhttp3.e.a b;
        private HttpUrl c;
        private final List<retrofit2.e.a> d;
        private final List<retrofit2.c.a> e;
        private Executor f;
        private boolean g;

        a(j jVar) {
            this.d = new ArrayList();
            this.e = new ArrayList();
            this.a = jVar;
        }

        public a() {
            this(j.a());
        }

        public a a(w wVar) {
            return a((okhttp3.e.a) o.a(wVar, "client == null"));
        }

        public a a(okhttp3.e.a aVar) {
            this.b = (okhttp3.e.a) o.a(aVar, "factory == null");
            return this;
        }

        public a a(String str) {
            o.a(str, "baseUrl == null");
            HttpUrl e2 = HttpUrl.e(str);
            if (e2 != null) {
                return a(e2);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Illegal URL: ");
            sb.append(str);
            throw new IllegalArgumentException(sb.toString());
        }

        public a a(HttpUrl httpUrl) {
            o.a(httpUrl, "baseUrl == null");
            List j = httpUrl.j();
            if (!"".equals(j.get(j.size() - 1))) {
                StringBuilder sb = new StringBuilder();
                sb.append("baseUrl must end in /: ");
                sb.append(httpUrl);
                throw new IllegalArgumentException(sb.toString());
            }
            this.c = httpUrl;
            return this;
        }

        public a a(retrofit2.e.a aVar) {
            this.d.add(o.a(aVar, "factory == null"));
            return this;
        }

        public a a(retrofit2.c.a aVar) {
            this.e.add(o.a(aVar, "factory == null"));
            return this;
        }

        public m a() {
            if (this.c == null) {
                throw new IllegalStateException("Base URL required.");
            }
            okhttp3.e.a aVar = this.b;
            if (aVar == null) {
                aVar = new w();
            }
            okhttp3.e.a aVar2 = aVar;
            Executor executor = this.f;
            if (executor == null) {
                executor = this.a.b();
            }
            Executor executor2 = executor;
            ArrayList arrayList = new ArrayList(this.e);
            arrayList.add(this.a.a(executor2));
            ArrayList arrayList2 = new ArrayList(1 + this.d.size());
            arrayList2.add(new a());
            arrayList2.addAll(this.d);
            m mVar = new m(aVar2, this.c, Collections.unmodifiableList(arrayList2), Collections.unmodifiableList(arrayList), executor2, this.g);
            return mVar;
        }
    }

    m(okhttp3.e.a aVar, HttpUrl httpUrl, List<retrofit2.e.a> list, List<retrofit2.c.a> list2, Executor executor, boolean z) {
        this.a = aVar;
        this.b = httpUrl;
        this.c = list;
        this.d = list2;
        this.e = executor;
        this.f = z;
    }

    public <T> T a(final Class<T> cls) {
        o.a(cls);
        if (this.f) {
            b(cls);
        }
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() {
            private final j c = j.a();

            public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                if (method.getDeclaringClass() == Object.class) {
                    return method.invoke(this, objArr);
                }
                if (this.c.a(method)) {
                    return this.c.a(method, cls, obj, objArr);
                }
                n a2 = m.this.a(method);
                return a2.a((b<R>) new h<R>(a2, objArr));
            }
        });
    }

    private void b(Class<?> cls) {
        Method[] declaredMethods;
        j a2 = j.a();
        for (Method method : cls.getDeclaredMethods()) {
            if (!a2.a(method)) {
                a(method);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public n<?, ?> a(Method method) {
        n<?, ?> nVar;
        n<?, ?> nVar2 = (n) this.g.get(method);
        if (nVar2 != null) {
            return nVar2;
        }
        synchronized (this.g) {
            nVar = (n) this.g.get(method);
            if (nVar == null) {
                nVar = new a(this, method).a();
                this.g.put(method, nVar);
            }
        }
        return nVar;
    }

    public okhttp3.e.a a() {
        return this.a;
    }

    public HttpUrl b() {
        return this.b;
    }

    public c<?, ?> a(Type type, Annotation[] annotationArr) {
        return a((retrofit2.c.a) null, type, annotationArr);
    }

    public c<?, ?> a(retrofit2.c.a aVar, Type type, Annotation[] annotationArr) {
        o.a(type, "returnType == null");
        o.a(annotationArr, "annotations == null");
        int indexOf = this.d.indexOf(aVar) + 1;
        int size = this.d.size();
        for (int i = indexOf; i < size; i++) {
            c<?, ?> a2 = ((retrofit2.c.a) this.d.get(i)).a(type, annotationArr, this);
            if (a2 != null) {
                return a2;
            }
        }
        StringBuilder sb = new StringBuilder("Could not locate call adapter for ");
        sb.append(type);
        sb.append(".\n");
        if (aVar != null) {
            sb.append("  Skipped:");
            for (int i2 = 0; i2 < indexOf; i2++) {
                sb.append("\n   * ");
                sb.append(((retrofit2.c.a) this.d.get(i2)).getClass().getName());
            }
            sb.append(10);
        }
        sb.append("  Tried:");
        int size2 = this.d.size();
        while (indexOf < size2) {
            sb.append("\n   * ");
            sb.append(((retrofit2.c.a) this.d.get(indexOf)).getClass().getName());
            indexOf++;
        }
        throw new IllegalArgumentException(sb.toString());
    }

    public <T> e<T, z> a(Type type, Annotation[] annotationArr, Annotation[] annotationArr2) {
        return a(null, type, annotationArr, annotationArr2);
    }

    public <T> e<T, z> a(retrofit2.e.a aVar, Type type, Annotation[] annotationArr, Annotation[] annotationArr2) {
        o.a(type, "type == null");
        o.a(annotationArr, "parameterAnnotations == null");
        o.a(annotationArr2, "methodAnnotations == null");
        int indexOf = this.c.indexOf(aVar) + 1;
        int size = this.c.size();
        for (int i = indexOf; i < size; i++) {
            e<T, z> a2 = ((retrofit2.e.a) this.c.get(i)).a(type, annotationArr, annotationArr2, this);
            if (a2 != null) {
                return a2;
            }
        }
        StringBuilder sb = new StringBuilder("Could not locate RequestBody converter for ");
        sb.append(type);
        sb.append(".\n");
        if (aVar != null) {
            sb.append("  Skipped:");
            for (int i2 = 0; i2 < indexOf; i2++) {
                sb.append("\n   * ");
                sb.append(((retrofit2.e.a) this.c.get(i2)).getClass().getName());
            }
            sb.append(10);
        }
        sb.append("  Tried:");
        int size2 = this.c.size();
        while (indexOf < size2) {
            sb.append("\n   * ");
            sb.append(((retrofit2.e.a) this.c.get(indexOf)).getClass().getName());
            indexOf++;
        }
        throw new IllegalArgumentException(sb.toString());
    }

    public <T> e<ab, T> b(Type type, Annotation[] annotationArr) {
        return a((retrofit2.e.a) null, type, annotationArr);
    }

    public <T> e<ab, T> a(retrofit2.e.a aVar, Type type, Annotation[] annotationArr) {
        o.a(type, "type == null");
        o.a(annotationArr, "annotations == null");
        int indexOf = this.c.indexOf(aVar) + 1;
        int size = this.c.size();
        for (int i = indexOf; i < size; i++) {
            e<ab, T> a2 = ((retrofit2.e.a) this.c.get(i)).a(type, annotationArr, this);
            if (a2 != null) {
                return a2;
            }
        }
        StringBuilder sb = new StringBuilder("Could not locate ResponseBody converter for ");
        sb.append(type);
        sb.append(".\n");
        if (aVar != null) {
            sb.append("  Skipped:");
            for (int i2 = 0; i2 < indexOf; i2++) {
                sb.append("\n   * ");
                sb.append(((retrofit2.e.a) this.c.get(i2)).getClass().getName());
            }
            sb.append(10);
        }
        sb.append("  Tried:");
        int size2 = this.c.size();
        while (indexOf < size2) {
            sb.append("\n   * ");
            sb.append(((retrofit2.e.a) this.c.get(indexOf)).getClass().getName());
            indexOf++;
        }
        throw new IllegalArgumentException(sb.toString());
    }

    public <T> e<T, String> c(Type type, Annotation[] annotationArr) {
        o.a(type, "type == null");
        o.a(annotationArr, "annotations == null");
        int size = this.c.size();
        for (int i = 0; i < size; i++) {
            e<T, String> b2 = ((retrofit2.e.a) this.c.get(i)).b(type, annotationArr, this);
            if (b2 != null) {
                return b2;
            }
        }
        return d.a;
    }
}
