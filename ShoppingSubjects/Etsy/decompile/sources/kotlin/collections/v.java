package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.p;

/* compiled from: MutableCollections.kt */
class v extends u {
    public static final <T> boolean a(Collection<? super T> collection, Iterable<? extends T> iterable) {
        p.b(collection, "$receiver");
        p.b(iterable, "elements");
        if (iterable instanceof Collection) {
            return collection.addAll((Collection) iterable);
        }
        boolean z = false;
        for (Object add : iterable) {
            if (collection.add(add)) {
                z = true;
            }
        }
        return z;
    }

    public static final <T> boolean a(Iterable<? extends T> iterable, b<? super T, Boolean> bVar) {
        p.b(iterable, "$receiver");
        p.b(bVar, "predicate");
        return a(iterable, bVar, false);
    }

    private static final <T> boolean a(Iterable<? extends T> iterable, b<? super T, Boolean> bVar, boolean z) {
        Iterator it = iterable.iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            if (((Boolean) bVar.invoke(it.next())).booleanValue() == z) {
                it.remove();
                z2 = true;
            }
        }
        return z2;
    }
}
