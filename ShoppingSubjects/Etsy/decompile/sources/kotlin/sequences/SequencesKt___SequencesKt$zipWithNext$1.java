package kotlin.sequences;

import kotlin.Pair;
import kotlin.f;
import kotlin.jvm.a.m;
import kotlin.jvm.internal.Lambda;

/* compiled from: _Sequences.kt */
final class SequencesKt___SequencesKt$zipWithNext$1 extends Lambda implements m<T, T, Pair<? extends T, ? extends T>> {
    public static final SequencesKt___SequencesKt$zipWithNext$1 INSTANCE = new SequencesKt___SequencesKt$zipWithNext$1();

    SequencesKt___SequencesKt$zipWithNext$1() {
        super(2);
    }

    public final Pair<T, T> invoke(T t, T t2) {
        return f.a(t, t2);
    }
}
