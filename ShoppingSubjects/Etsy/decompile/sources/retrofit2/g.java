package retrofit2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.Executor;

/* compiled from: ExecutorCallAdapterFactory */
final class g extends retrofit2.c.a {
    final Executor a;

    /* compiled from: ExecutorCallAdapterFactory */
    static final class a<T> implements b<T> {
        final Executor a;
        final b<T> b;

        a(Executor executor, b<T> bVar) {
            this.a = executor;
            this.b = bVar;
        }

        public void a(final d<T> dVar) {
            o.a(dVar, "callback == null");
            this.b.a(new d<T>() {
                public void a(b<T> bVar, final l<T> lVar) {
                    a.this.a.execute(new Runnable() {
                        public void run() {
                            if (a.this.b.c()) {
                                dVar.a((b<T>) a.this, (Throwable) new IOException("Canceled"));
                            } else {
                                dVar.a((b<T>) a.this, lVar);
                            }
                        }
                    });
                }

                public void a(b<T> bVar, final Throwable th) {
                    a.this.a.execute(new Runnable() {
                        public void run() {
                            dVar.a((b<T>) a.this, th);
                        }
                    });
                }
            });
        }

        public l<T> a() throws IOException {
            return this.b.a();
        }

        public void b() {
            this.b.b();
        }

        public boolean c() {
            return this.b.c();
        }

        /* renamed from: d */
        public b<T> clone() {
            return new a(this.a, this.b.d());
        }
    }

    g(Executor executor) {
        this.a = executor;
    }

    public c<?, ?> a(Type type, Annotation[] annotationArr, m mVar) {
        if (a(type) != b.class) {
            return null;
        }
        final Type e = o.e(type);
        return new c<Object, b<?>>() {
            public Type a() {
                return e;
            }

            /* renamed from: b */
            public b<Object> a(b<Object> bVar) {
                return new a(g.this.a, bVar);
            }
        };
    }
}
