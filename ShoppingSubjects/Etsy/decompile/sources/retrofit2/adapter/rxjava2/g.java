package retrofit2.adapter.rxjava2;

import io.reactivex.k;
import io.reactivex.q;
import io.reactivex.u;
import io.reactivex.v;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import retrofit2.c;
import retrofit2.c.a;
import retrofit2.l;
import retrofit2.m;

/* compiled from: RxJava2CallAdapterFactory */
public final class g extends a {
    private final u a;
    private final boolean b;

    public static g a() {
        return new g(null, false);
    }

    private g(u uVar, boolean z) {
        this.a = uVar;
        this.b = z;
    }

    public c<?, ?> a(Type type, Annotation[] annotationArr, m mVar) {
        boolean z;
        boolean z2;
        Type type2;
        Class<q> a2 = a(type);
        if (a2 == io.reactivex.a.class) {
            f fVar = new f(Void.class, this.a, this.b, false, true, false, false, false, true);
            return fVar;
        }
        boolean z3 = a2 == io.reactivex.g.class;
        boolean z4 = a2 == v.class;
        boolean z5 = a2 == k.class;
        if (a2 != q.class && !z3 && !z4 && !z5) {
            return null;
        }
        if (!(type instanceof ParameterizedType)) {
            String str = z3 ? "Flowable" : z4 ? "Single" : z5 ? "Maybe" : "Observable";
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" return type must be parameterized as ");
            sb.append(str);
            sb.append("<Foo> or ");
            sb.append(str);
            sb.append("<? extends Foo>");
            throw new IllegalStateException(sb.toString());
        }
        Type a3 = a(0, (ParameterizedType) type);
        Class<d> a4 = a(a3);
        if (a4 == l.class) {
            if (!(a3 instanceof ParameterizedType)) {
                throw new IllegalStateException("Response must be parameterized as Response<Foo> or Response<? extends Foo>");
            }
            type2 = a(0, (ParameterizedType) a3);
            z2 = false;
            z = false;
        } else if (a4 != d.class) {
            type2 = a3;
            z = true;
            z2 = false;
        } else if (!(a3 instanceof ParameterizedType)) {
            throw new IllegalStateException("Result must be parameterized as Result<Foo> or Result<? extends Foo>");
        } else {
            type2 = a(0, (ParameterizedType) a3);
            z2 = true;
            z = false;
        }
        f fVar2 = new f(type2, this.a, this.b, z2, z, z3, z4, z5, false);
        return fVar2;
    }
}
