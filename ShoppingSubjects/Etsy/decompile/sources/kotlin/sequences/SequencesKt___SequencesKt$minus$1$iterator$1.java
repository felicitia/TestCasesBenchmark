package kotlin.sequences;

import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref.BooleanRef;
import kotlin.jvm.internal.p;
import kotlin.sequences.h.a;

/* compiled from: _Sequences.kt */
final class SequencesKt___SequencesKt$minus$1$iterator$1 extends Lambda implements b<T, Boolean> {
    final /* synthetic */ BooleanRef $removed;
    final /* synthetic */ a this$0;

    SequencesKt___SequencesKt$minus$1$iterator$1(a aVar, BooleanRef booleanRef) {
        this.this$0 = aVar;
        this.$removed = booleanRef;
        super(1);
    }

    public final boolean invoke(T t) {
        if (this.$removed.element || !p.a((Object) t, this.this$0.b)) {
            return true;
        }
        this.$removed.element = true;
        return false;
    }
}
