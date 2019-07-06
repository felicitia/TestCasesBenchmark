package kotlin.reflect;

/* compiled from: KProperty.kt */
public interface m<D, E, R> extends kotlin.jvm.a.m<D, E, R>, j<R> {

    /* compiled from: KProperty.kt */
    public interface a<D, E, R> extends kotlin.jvm.a.m<D, E, R>, kotlin.reflect.j.a<R> {
    }

    R get(D d, E e);

    Object getDelegate(D d, E e);

    a<D, E, R> getGetter();
}
