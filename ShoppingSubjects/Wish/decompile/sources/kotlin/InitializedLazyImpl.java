package kotlin;

import java.io.Serializable;

/* compiled from: Lazy.kt */
final class InitializedLazyImpl<T> implements Serializable, Lazy<T> {
    private final T value;

    public InitializedLazyImpl(T t) {
        this.value = t;
    }

    public T getValue() {
        return this.value;
    }

    public String toString() {
        return String.valueOf(getValue());
    }
}
