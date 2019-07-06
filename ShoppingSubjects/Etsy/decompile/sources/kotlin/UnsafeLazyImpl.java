package kotlin;

import java.io.Serializable;
import kotlin.jvm.a.a;
import kotlin.jvm.internal.p;

/* compiled from: Lazy.kt */
public final class UnsafeLazyImpl<T> implements Serializable, b<T> {
    private Object _value = g.a;
    private a<? extends T> initializer;

    public UnsafeLazyImpl(a<? extends T> aVar) {
        p.b(aVar, "initializer");
        this.initializer = aVar;
    }

    public T getValue() {
        if (this._value == g.a) {
            a<? extends T> aVar = this.initializer;
            if (aVar == null) {
                p.a();
            }
            this._value = aVar.invoke();
            this.initializer = null;
        }
        return this._value;
    }

    public boolean isInitialized() {
        return this._value != g.a;
    }

    public String toString() {
        return isInitialized() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }

    private final Object writeReplace() {
        return new InitializedLazyImpl(getValue());
    }
}
