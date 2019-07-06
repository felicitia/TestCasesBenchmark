package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import retrofit2.c.a;

/* compiled from: DefaultCallAdapterFactory */
final class f extends a {
    static final a a = new f();

    f() {
    }

    public c<?, ?> a(Type type, Annotation[] annotationArr, m mVar) {
        if (a(type) != b.class) {
            return null;
        }
        final Type e = o.e(type);
        return new c<Object, b<?>>() {
            /* renamed from: b */
            public b<Object> a(b<Object> bVar) {
                return bVar;
            }

            public Type a() {
                return e;
            }
        };
    }
}
