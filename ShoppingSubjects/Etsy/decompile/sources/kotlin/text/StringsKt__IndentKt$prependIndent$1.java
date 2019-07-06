package kotlin.text;

import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: Indent.kt */
final class StringsKt__IndentKt$prependIndent$1 extends Lambda implements b<String, String> {
    final /* synthetic */ String $indent;

    StringsKt__IndentKt$prependIndent$1(String str) {
        this.$indent = str;
        super(1);
    }

    public final String invoke(String str) {
        p.b(str, "it");
        if (!m.a(str)) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.$indent);
            sb.append(str);
            return sb.toString();
        } else if (str.length() < this.$indent.length()) {
            return this.$indent;
        } else {
            return str;
        }
    }
}
