package kotlin.sequences;

import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;

/* compiled from: _Sequences.kt */
final class SequencesKt___SequencesKt$onEach$1 extends Lambda implements b<T, T> {
    final /* synthetic */ b $action;

    SequencesKt___SequencesKt$onEach$1(b bVar) {
        this.$action = bVar;
        super(1);
    }

    public final T invoke(T t) {
        this.$action.invoke(t);
        return t;
    }
}
