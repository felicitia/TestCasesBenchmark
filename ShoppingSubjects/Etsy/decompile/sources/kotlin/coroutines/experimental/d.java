package kotlin.coroutines.experimental;

import kotlin.jvm.a.m;

/* compiled from: CoroutineContext.kt */
public interface d {

    /* compiled from: CoroutineContext.kt */
    public static final class a {
    }

    /* compiled from: CoroutineContext.kt */
    public interface b extends d {
        <E extends b> E a(c<E> cVar);

        c<?> a();
    }

    /* compiled from: CoroutineContext.kt */
    public interface c<E extends b> {
    }

    <R> R a(R r, m<? super R, ? super b, ? extends R> mVar);

    <E extends b> E a(c<E> cVar);

    d b(c<?> cVar);
}
