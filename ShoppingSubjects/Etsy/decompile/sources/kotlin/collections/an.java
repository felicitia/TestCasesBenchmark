package kotlin.collections;

import java.util.Set;
import kotlin.jvm.internal.p;

/* compiled from: Sets.kt */
class an extends am {
    public static final <T> Set<T> a() {
        return EmptySet.INSTANCE;
    }

    public static final <T> Set<T> a(T... tArr) {
        p.b(tArr, "elements");
        return tArr.length > 0 ? f.c(tArr) : al.a();
    }
}
