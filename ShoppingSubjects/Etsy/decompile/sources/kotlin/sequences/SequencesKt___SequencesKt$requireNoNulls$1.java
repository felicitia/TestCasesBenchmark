package kotlin.sequences;

import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: _Sequences.kt */
final class SequencesKt___SequencesKt$requireNoNulls$1 extends Lambda implements b<T, T> {
    final /* synthetic */ c receiver$0;

    SequencesKt___SequencesKt$requireNoNulls$1(c cVar) {
        this.receiver$0 = cVar;
        super(1);
    }

    public final T invoke(T t) {
        if (t != null) {
            return t;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("null element found in ");
        sb.append(this.receiver$0);
        sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
        throw new IllegalArgumentException(sb.toString());
    }
}
