package kotlin.sequences;

import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: _Sequences.kt */
final class SequencesKt___SequencesKt$elementAt$1 extends Lambda implements b {
    final /* synthetic */ int $index;

    SequencesKt___SequencesKt$elementAt$1(int i) {
        this.$index = i;
        super(1);
    }

    public /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }

    public final Void invoke(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("Sequence doesn't contain element at index ");
        sb.append(this.$index);
        sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
        throw new IndexOutOfBoundsException(sb.toString());
    }
}
