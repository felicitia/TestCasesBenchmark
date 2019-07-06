package kotlin.text;

import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: _Strings.kt */
final class StringsKt___StringsKt$windowed$1 extends Lambda implements b<CharSequence, String> {
    public static final StringsKt___StringsKt$windowed$1 INSTANCE = new StringsKt___StringsKt$windowed$1();

    StringsKt___StringsKt$windowed$1() {
        super(1);
    }

    public final String invoke(CharSequence charSequence) {
        p.b(charSequence, "it");
        return charSequence.toString();
    }
}
