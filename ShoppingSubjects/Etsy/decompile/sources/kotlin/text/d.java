package kotlin.text;

import java.nio.charset.Charset;
import kotlin.jvm.internal.p;

/* compiled from: Charsets.kt */
public final class d {
    public static final Charset a;
    public static final Charset b;
    public static final Charset c;
    public static final Charset d;
    public static final Charset e;
    public static final Charset f;
    public static final d g = new d();

    static {
        Charset forName = Charset.forName("UTF-8");
        p.a((Object) forName, "Charset.forName(\"UTF-8\")");
        a = forName;
        Charset forName2 = Charset.forName("UTF-16");
        p.a((Object) forName2, "Charset.forName(\"UTF-16\")");
        b = forName2;
        Charset forName3 = Charset.forName("UTF-16BE");
        p.a((Object) forName3, "Charset.forName(\"UTF-16BE\")");
        c = forName3;
        Charset forName4 = Charset.forName("UTF-16LE");
        p.a((Object) forName4, "Charset.forName(\"UTF-16LE\")");
        d = forName4;
        Charset forName5 = Charset.forName("US-ASCII");
        p.a((Object) forName5, "Charset.forName(\"US-ASCII\")");
        e = forName5;
        Charset forName6 = Charset.forName("ISO-8859-1");
        p.a((Object) forName6, "Charset.forName(\"ISO-8859-1\")");
        f = forName6;
    }

    private d() {
    }
}
