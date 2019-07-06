package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import kotlin.jvm.internal.p;

/* compiled from: Iterables.kt */
class r extends q {
    public static final <T> int a(Iterable<? extends T> iterable, int i) {
        p.b(iterable, "$receiver");
        return iterable instanceof Collection ? ((Collection) iterable).size() : i;
    }

    private static final <T> boolean b(Collection<? extends T> collection) {
        return collection.size() > 2 && (collection instanceof ArrayList);
    }

    public static final <T> Collection<T> a(Iterable<? extends T> iterable) {
        p.b(iterable, "$receiver");
        if (iterable instanceof Set) {
            return (Collection) iterable;
        }
        if (!(iterable instanceof Collection)) {
            return o.e(iterable);
        }
        Collection collection = (Collection) iterable;
        return b(collection) ? o.e(iterable) : collection;
    }
}
