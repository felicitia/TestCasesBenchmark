package kotlin.collections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import kotlin.jvm.internal.p;

/* compiled from: _ArraysJvm.kt */
class i extends h {
    public static final <T> List<T> a(T[] tArr) {
        p.b(tArr, "$receiver");
        List<T> a = k.a(tArr);
        p.a((Object) a, "ArraysUtilJVM.asList(this)");
        return a;
    }

    public static final <T> void a(T[] tArr, Comparator<? super T> comparator) {
        p.b(tArr, "$receiver");
        p.b(comparator, "comparator");
        if (tArr.length > 1) {
            Arrays.sort(tArr, comparator);
        }
    }
}
