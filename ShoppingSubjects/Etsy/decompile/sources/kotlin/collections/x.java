package kotlin.collections;

import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.p;

/* compiled from: _CollectionsJvm.kt */
class x extends w {
    public static final <T> void d(List<T> list) {
        p.b(list, "$receiver");
        Collections.reverse(list);
    }
}
