package kotlin.sequences;

import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;

/* compiled from: _SequencesJvm.kt */
final class SequencesKt___SequencesJvmKt$filterIsInstance$1 extends Lambda implements b<Object, Boolean> {
    final /* synthetic */ Class $klass;

    SequencesKt___SequencesJvmKt$filterIsInstance$1(Class cls) {
        this.$klass = cls;
        super(1);
    }

    public final boolean invoke(Object obj) {
        return this.$klass.isInstance(obj);
    }
}
