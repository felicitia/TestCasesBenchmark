package kotlin.text;

import kotlin.b.d;
import kotlin.jvm.internal.p;

/* compiled from: _Strings.kt */
class x extends w {
    public static final String a(String str, int i) {
        p.b(str, "$receiver");
        if (!(i >= 0)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Requested character count ");
            sb.append(i);
            sb.append(" is less than zero.");
            throw new IllegalArgumentException(sb.toString().toString());
        }
        String substring = str.substring(d.d(i, str.length()));
        p.a((Object) substring, "(this as java.lang.String).substring(startIndex)");
        return substring;
    }
}
