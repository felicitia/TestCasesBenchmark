package kotlin.jvm.internal;

import java.util.Iterator;

/* compiled from: ArrayIterator.kt */
public final class h {
    public static final <T> Iterator<T> a(T[] tArr) {
        p.b(tArr, "array");
        return new g<>(tArr);
    }
}
