package retrofit2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.ab;
import okhttp3.z;
import retrofit2.b.w;

/* compiled from: BuiltInConverters */
final class a extends retrofit2.e.a {

    /* renamed from: retrofit2.a$a reason: collision with other inner class name */
    /* compiled from: BuiltInConverters */
    static final class C0198a implements e<ab, ab> {
        static final C0198a a = new C0198a();

        C0198a() {
        }

        public ab a(ab abVar) throws IOException {
            try {
                return o.a(abVar);
            } finally {
                abVar.close();
            }
        }
    }

    /* compiled from: BuiltInConverters */
    static final class b implements e<z, z> {
        static final b a = new b();

        public z a(z zVar) {
            return zVar;
        }

        b() {
        }
    }

    /* compiled from: BuiltInConverters */
    static final class c implements e<ab, ab> {
        static final c a = new c();

        public ab a(ab abVar) {
            return abVar;
        }

        c() {
        }
    }

    /* compiled from: BuiltInConverters */
    static final class d implements e<Object, String> {
        static final d a = new d();

        d() {
        }

        /* renamed from: b */
        public String a(Object obj) {
            return obj.toString();
        }
    }

    /* compiled from: BuiltInConverters */
    static final class e implements e<ab, Void> {
        static final e a = new e();

        e() {
        }

        public Void a(ab abVar) {
            abVar.close();
            return null;
        }
    }

    a() {
    }

    public e<ab, ?> a(Type type, Annotation[] annotationArr, m mVar) {
        e<ab, ?> eVar;
        if (type == ab.class) {
            if (o.a(annotationArr, w.class)) {
                eVar = c.a;
            } else {
                eVar = C0198a.a;
            }
            return eVar;
        } else if (type == Void.class) {
            return e.a;
        } else {
            return null;
        }
    }

    public e<?, z> a(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, m mVar) {
        if (z.class.isAssignableFrom(o.a(type))) {
            return b.a;
        }
        return null;
    }
}
