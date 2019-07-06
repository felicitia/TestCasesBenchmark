package kotlin.collections;

import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: _Collections.kt */
final class CollectionsKt___CollectionsKt$elementAt$1 extends Lambda implements b {
    final /* synthetic */ int $index;

    CollectionsKt___CollectionsKt$elementAt$1(int i) {
        this.$index = i;
        super(1);
    }

    public /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }

    public final Void invoke(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("Collection doesn't contain element at index ");
        sb.append(this.$index);
        sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
        throw new IndexOutOfBoundsException(sb.toString());
    }
}
