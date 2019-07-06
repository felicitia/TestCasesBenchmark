package kotlin;

import java.io.Serializable;

/* compiled from: Lazy.kt */
public final class InitializedLazyImpl<T> implements Serializable, b<T> {
    private final T value;

    public boolean isInitialized() {
        return true;
    }

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
