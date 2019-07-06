package dagger.internal;

/* compiled from: InstanceFactory */
public final class d<T> implements c<T> {
    private static final d<Object> a = new d<>(null);
    private final T b;

    public static <T> c<T> a(T t) {
        return new d(f.a(t, "instance cannot be null"));
    }

    private d(T t) {
        this.b = t;
    }

    public T b() {
        return this.b;
    }
}
