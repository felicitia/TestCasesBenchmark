package kotlin.text;

import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;

/* compiled from: Regex.kt */
public final class RegexKt$fromInt$$inlined$apply$lambda$1 extends Lambda implements b<T, Boolean> {
    final /* synthetic */ int $value$inlined;

    public RegexKt$fromInt$$inlined$apply$lambda$1(int i) {
        this.$value$inlined = i;
        super(1);
    }

    public /* synthetic */ Object invoke(Object obj) {
        return Boolean.valueOf(invoke((T) (Enum) obj));
    }

    public final boolean invoke(T t) {
        f fVar = (f) t;
        return (this.$value$inlined & fVar.getMask()) == fVar.getValue();
    }
}
