package kotlin.sequences;

import java.util.HashSet;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;

/* compiled from: _Sequences.kt */
final class SequencesKt___SequencesKt$minus$4$iterator$1 extends Lambda implements b<T, Boolean> {
    final /* synthetic */ HashSet $other;

    SequencesKt___SequencesKt$minus$4$iterator$1(HashSet hashSet) {
        this.$other = hashSet;
        super(1);
    }

    public final boolean invoke(T t) {
        return this.$other.contains(t);
    }
}
