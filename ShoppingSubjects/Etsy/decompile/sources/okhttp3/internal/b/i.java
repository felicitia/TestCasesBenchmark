package okhttp3.internal.b;

import java.net.Proxy.Type;
import okhttp3.HttpUrl;
import okhttp3.y;

/* compiled from: RequestLine */
public final class i {
    public static String a(y yVar, Type type) {
        StringBuilder sb = new StringBuilder();
        sb.append(yVar.b());
        sb.append(' ');
        if (b(yVar, type)) {
            sb.append(yVar.a());
        } else {
            sb.append(a(yVar.a()));
        }
        sb.append(" HTTP/1.1");
        return sb.toString();
    }

    private static boolean b(y yVar, Type type) {
        return !yVar.g() && type == Type.HTTP;
    }

    public static String a(HttpUrl httpUrl) {
        String h = httpUrl.h();
        String k = httpUrl.k();
        if (k == null) {
            return h;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(h);
        sb.append('?');
        sb.append(k);
        return sb.toString();
    }
}
