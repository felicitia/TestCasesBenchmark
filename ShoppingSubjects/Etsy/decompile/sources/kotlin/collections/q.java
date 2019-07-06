package kotlin.collections;

import java.util.Collection;
import java.util.List;
import kotlin.b.c;
import kotlin.jvm.internal.p;

/* compiled from: Collections.kt */
class q extends p {
    public static final <T> List<T> a() {
        return EmptyList.INSTANCE;
    }

    public static final c a(Collection<?> collection) {
        p.b(collection, "$receiver");
        return new c(0, collection.size() - 1);
    }

    public static final <T> int a(List<? extends T> list) {
        p.b(list, "$receiver");
        return list.size() - 1;
    }

    public static final <T> List<T> b(List<? extends T> list) {
        p.b(list, "$receiver");
        switch (list.size()) {
            case 0:
                return o.a();
            case 1:
                return o.a(list.get(0));
            default:
                return list;
        }
    }
}
