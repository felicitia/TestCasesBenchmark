package kotlin.sequences;

import kotlin.collections.ac;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: _Sequences.kt */
final class SequencesKt___SequencesKt$filterIndexed$2 extends Lambda implements b<ac<? extends T>, T> {
    public static final SequencesKt___SequencesKt$filterIndexed$2 INSTANCE = new SequencesKt___SequencesKt$filterIndexed$2();

    SequencesKt___SequencesKt$filterIndexed$2() {
        super(1);
    }

    public final T invoke(ac<? extends T> acVar) {
        p.b(acVar, "it");
        return acVar.b();
    }
}
