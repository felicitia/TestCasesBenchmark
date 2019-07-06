package kotlin;

import java.io.Serializable;
import kotlin.jvm.a.a;
import kotlin.jvm.internal.p;

/* compiled from: LazyJVM.kt */
final class SynchronizedLazyImpl<T> implements Serializable, b<T> {
    private volatile Object _value;
    private a<? extends T> initializer;
    private final Object lock;

    public SynchronizedLazyImpl(a<? extends T> aVar, Object obj) {
        p.b(aVar, "initializer");
        this.initializer = aVar;
        this._value = g.a;
        if (obj == 0) {
            obj = this;
        }
        this.lock = obj;
    }

    public /* synthetic */ SynchronizedLazyImpl(a aVar, Object obj, int i, o oVar) {
        if ((i & 2) != 0) {
            obj = null;
        }
        this(aVar, obj);
    }

    public T getValue() {
        T t;
        T t2 = this._value;
        if (t2 != g.a) {
            return t2;
        }
        synchronized (this.lock) {
            t = this._value;
            if (t == g.a) {
                a<? extends T> aVar = this.initializer;
                if (aVar == null) {
                    p.a();
                }
                t = aVar.invoke();
                this._value = t;
                this.initializer = null;
            }
        }
        return t;
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
