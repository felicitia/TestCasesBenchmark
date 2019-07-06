package kotlin.reflect;

import kotlin.jvm.a.b;

/* compiled from: KProperty.kt */
public interface l<T, R> extends b<T, R>, j<R> {

    /* compiled from: KProperty.kt */
    public interface a<T, R> extends b<T, R>, kotlin.reflect.j.a<R> {
    }

    R get(T t);

    Object getDelegate(T t);

    a<T, R> getGetter();
}
