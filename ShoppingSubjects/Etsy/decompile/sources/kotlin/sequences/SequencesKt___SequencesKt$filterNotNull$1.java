package kotlin.sequences;

import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;

/* compiled from: _Sequences.kt */
final class SequencesKt___SequencesKt$filterNotNull$1 extends Lambda implements b<T, Boolean> {
    public static final SequencesKt___SequencesKt$filterNotNull$1 INSTANCE = new SequencesKt___SequencesKt$filterNotNull$1();

    SequencesKt___SequencesKt$filterNotNull$1() {
        super(1);
    }

    public final boolean invoke(T t) {
        return t == null;
    }
}
