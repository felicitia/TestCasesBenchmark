package kotlin.sequences;

import kotlin.jvm.a.a;
import kotlin.jvm.internal.Lambda;

/* compiled from: Sequences.kt */
final class SequencesKt__SequencesKt$generateSequence$2 extends Lambda implements a<T> {
    final /* synthetic */ Object $seed;

    SequencesKt__SequencesKt$generateSequence$2(Object obj) {
        this.$seed = obj;
        super(0);
    }

    public final T invoke() {
        return this.$seed;
    }
}
