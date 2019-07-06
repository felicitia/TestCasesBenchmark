package kotlin.coroutines.experimental;

import com.etsy.android.lib.models.ResponseConstants;
import kotlin.coroutines.experimental.d.b;
import kotlin.coroutines.experimental.d.c;
import kotlin.jvm.a.m;
import kotlin.jvm.internal.p;

/* compiled from: CoroutineContextImpl.kt */
public final class e implements d {
    public static final e a = new e();

    public <R> R a(R r, m<? super R, ? super b, ? extends R> mVar) {
        p.b(mVar, "operation");
        return r;
    }

    public <E extends b> E a(c<E> cVar) {
        p.b(cVar, ResponseConstants.KEY);
        return null;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return "EmptyCoroutineContext";
    }

    private e() {
    }

    public d b(c<?> cVar) {
        p.b(cVar, ResponseConstants.KEY);
        return this;
    }
}
