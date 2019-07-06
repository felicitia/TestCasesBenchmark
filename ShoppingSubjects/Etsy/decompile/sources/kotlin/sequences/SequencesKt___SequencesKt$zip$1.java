package kotlin.sequences;

import kotlin.Pair;
import kotlin.f;
import kotlin.jvm.a.m;
import kotlin.jvm.internal.Lambda;

/* compiled from: _Sequences.kt */
final class SequencesKt___SequencesKt$zip$1 extends Lambda implements m<T, R, Pair<? extends T, ? extends R>> {
    public static final SequencesKt___SequencesKt$zip$1 INSTANCE = new SequencesKt___SequencesKt$zip$1();

    SequencesKt___SequencesKt$zip$1() {
        super(2);
    }

    public final Pair<T, R> invoke(T t, R r) {
        return f.a(t, r);
    }
}
