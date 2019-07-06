package kotlin.collections;

import java.util.Iterator;
import java.util.List;
import kotlin.coroutines.experimental.h;
import kotlin.jvm.internal.p;

/* compiled from: SlidingWindow.kt */
public final class aq {
    public static final void a(int i, int i2) {
        String str;
        if (!(i > 0 && i2 > 0)) {
            if (i != i2) {
                StringBuilder sb = new StringBuilder();
                sb.append("Both size ");
                sb.append(i);
                sb.append(" and step ");
                sb.append(i2);
                sb.append(" must be greater than zero.");
                str = sb.toString();
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("size ");
                sb2.append(i);
                sb2.append(" must be greater than zero.");
                str = sb2.toString();
            }
            throw new IllegalArgumentException(str.toString());
        }
    }

    public static final <T> Iterator<List<T>> a(Iterator<? extends T> it, int i, int i2, boolean z, boolean z2) {
        p.b(it, "iterator");
        if (!it.hasNext()) {
            return aa.a;
        }
        SlidingWindowKt$windowedIterator$1 slidingWindowKt$windowedIterator$1 = new SlidingWindowKt$windowedIterator$1(i2, i, it, z2, z, null);
        return h.a(slidingWindowKt$windowedIterator$1);
    }
}
