package kotlin.collections;

import java.util.Collections;
import java.util.Map;
import kotlin.Pair;
import kotlin.jvm.internal.p;

/* compiled from: MapsJVM.kt */
class ah extends ag {
    public static final <K, V> Map<K, V> a(Pair<? extends K, ? extends V> pair) {
        p.b(pair, "pair");
        Map<K, V> singletonMap = Collections.singletonMap(pair.getFirst(), pair.getSecond());
        p.a((Object) singletonMap, "java.util.Collections.si…(pair.first, pair.second)");
        return singletonMap;
    }
}
