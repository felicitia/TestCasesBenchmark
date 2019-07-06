package kotlin.collections;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import kotlin.jvm.internal.p;

/* compiled from: MutableCollectionsJVM.kt */
class u extends t {
    public static final <T extends Comparable<? super T>> void c(List<T> list) {
        p.b(list, "$receiver");
        if (list.size() > 1) {
            Collections.sort(list);
        }
    }

    public static final <T> void a(List<T> list, Comparator<? super T> comparator) {
        p.b(list, "$receiver");
        p.b(comparator, "comparator");
        if (list.size() > 1) {
            Collections.sort(list, comparator);
        }
    }
}
