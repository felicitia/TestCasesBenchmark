package retrofit2.adapter.rxjava2;

import io.reactivex.BackpressureStrategy;
import io.reactivex.q;
import io.reactivex.u;
import java.lang.reflect.Type;
import retrofit2.b;
import retrofit2.c;

/* compiled from: RxJava2CallAdapter */
final class f<R> implements c<R, Object> {
    private final Type a;
    private final u b;
    private final boolean c;
    private final boolean d;
    private final boolean e;
    private final boolean f;
    private final boolean g;
    private final boolean h;
    private final boolean i;

    f(Type type, u uVar, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        this.a = type;
        this.b = uVar;
        this.c = z;
        this.d = z2;
        this.e = z3;
        this.f = z4;
        this.g = z5;
        this.h = z6;
        this.i = z7;
    }

    public Type a() {
        return this.a;
    }

    public Object a(b<R> bVar) {
        q qVar;
        if (this.c) {
            qVar = new b(bVar);
        } else {
            qVar = new c(bVar);
        }
        q qVar2 = this.d ? new e(qVar) : this.e ? new a(qVar) : qVar;
        if (this.b != null) {
            qVar2 = qVar2.b(this.b);
        }
        if (this.f) {
            return qVar2.a(BackpressureStrategy.LATEST);
        }
        if (this.g) {
            return qVar2.j();
        }
        if (this.h) {
            return qVar2.i();
        }
        return this.i ? qVar2.g() : qVar2;
    }
}
