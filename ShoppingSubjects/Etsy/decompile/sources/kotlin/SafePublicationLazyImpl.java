package kotlin;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.internal.p;

/* compiled from: LazyJVM.kt */
final class SafePublicationLazyImpl<T> implements Serializable, b<T> {
    public static final a Companion = new a(null);
    private static final AtomicReferenceFieldUpdater<SafePublicationLazyImpl<?>, Object> a = AtomicReferenceFieldUpdater.newUpdater(SafePublicationLazyImpl.class, Object.class, "_value");
    private volatile Object _value = g.a;

    /* renamed from: final reason: not valid java name */
    private final Object f1final = g.a;
    private volatile kotlin.jvm.a.a<? extends T> initializer;

    /* compiled from: LazyJVM.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(o oVar) {
            this();
        }
    }

    public SafePublicationLazyImpl(kotlin.jvm.a.a<? extends T> aVar) {
        p.b(aVar, "initializer");
        this.initializer = aVar;
    }

    public T getValue() {
        T t = this._value;
        if (t != g.a) {
            return t;
        }
        kotlin.jvm.a.a<? extends T> aVar = this.initializer;
        if (aVar != null) {
            T invoke = aVar.invoke();
            if (a.compareAndSet(this, g.a, invoke)) {
                this.initializer = null;
                return invoke;
            }
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
