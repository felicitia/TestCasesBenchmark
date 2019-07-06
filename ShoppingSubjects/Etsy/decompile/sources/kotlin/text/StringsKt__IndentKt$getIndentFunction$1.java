package kotlin.text;

import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: Indent.kt */
final class StringsKt__IndentKt$getIndentFunction$1 extends Lambda implements b<String, String> {
    public static final StringsKt__IndentKt$getIndentFunction$1 INSTANCE = new StringsKt__IndentKt$getIndentFunction$1();

    StringsKt__IndentKt$getIndentFunction$1() {
        super(1);
    }

    public final String invoke(String str) {
        p.b(str, "line");
        return str;
    }
}
