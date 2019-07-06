package kotlin.text;

import kotlin.jvm.a.b;
import kotlin.jvm.internal.p;

/* compiled from: StringBuilder.kt */
class r extends q {
    public static final <T> void a(Appendable appendable, T t, b<? super T, ? extends CharSequence> bVar) {
        p.b(appendable, "$receiver");
        if (bVar != null) {
            appendable.append((CharSequence) bVar.invoke(t));
            return;
        }
        if (t != null ? t instanceof CharSequence : true) {
            appendable.append((CharSequence) t);
        } else if (t instanceof Character) {
            appendable.append(((Character) t).charValue());
        } else {
            appendable.append(String.valueOf(t));
        }
    }
}
