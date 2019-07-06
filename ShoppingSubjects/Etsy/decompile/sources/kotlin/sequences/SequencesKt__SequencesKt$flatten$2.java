package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: Sequences.kt */
final class SequencesKt__SequencesKt$flatten$2 extends Lambda implements b<Iterable<? extends T>, Iterator<? extends T>> {
    public static final SequencesKt__SequencesKt$flatten$2 INSTANCE = new SequencesKt__SequencesKt$flatten$2();

    SequencesKt__SequencesKt$flatten$2() {
        super(1);
    }

    public final Iterator<T> invoke(Iterable<? extends T> iterable) {
        p.b(iterable, "it");
        return iterable.iterator();
    }
}
