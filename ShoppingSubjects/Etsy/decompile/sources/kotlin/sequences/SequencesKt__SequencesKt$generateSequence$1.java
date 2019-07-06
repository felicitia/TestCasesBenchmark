package kotlin.sequences;

import kotlin.jvm.a.a;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: Sequences.kt */
final class SequencesKt__SequencesKt$generateSequence$1 extends Lambda implements b<T, T> {
    final /* synthetic */ a $nextFunction;

    SequencesKt__SequencesKt$generateSequence$1(a aVar) {
        this.$nextFunction = aVar;
        super(1);
    }

    public final T invoke(T t) {
        p.b(t, "it");
        return this.$nextFunction.invoke();
    }
}
