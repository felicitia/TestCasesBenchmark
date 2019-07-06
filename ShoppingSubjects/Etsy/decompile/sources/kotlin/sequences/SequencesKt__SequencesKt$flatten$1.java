package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: Sequences.kt */
final class SequencesKt__SequencesKt$flatten$1 extends Lambda implements b<c<? extends T>, Iterator<? extends T>> {
    public static final SequencesKt__SequencesKt$flatten$1 INSTANCE = new SequencesKt__SequencesKt$flatten$1();

    SequencesKt__SequencesKt$flatten$1() {
        super(1);
    }

    public final Iterator<T> invoke(c<? extends T> cVar) {
        p.b(cVar, "it");
        return cVar.a();
    }
}
