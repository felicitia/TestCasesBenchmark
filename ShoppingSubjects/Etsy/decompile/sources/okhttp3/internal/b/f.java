package okhttp3.internal.b;

import com.etsy.android.lib.core.http.request.BaseHttpRequest;

/* compiled from: HttpMethod */
public final class f {
    public static boolean a(String str) {
        return str.equals(BaseHttpRequest.POST) || str.equals("PATCH") || str.equals(BaseHttpRequest.PUT) || str.equals(BaseHttpRequest.DELETE) || str.equals("MOVE");
    }

    public static boolean b(String str) {
        return str.equals(BaseHttpRequest.POST) || str.equals(BaseHttpRequest.PUT) || str.equals("PATCH") || str.equals("PROPPATCH") || str.equals("REPORT");
    }

    public static boolean c(String str) {
        return !str.equals(BaseHttpRequest.GET) && !str.equals(BaseHttpRequest.HEAD);
    }

    public static boolean d(String str) {
        return str.equals("PROPFIND");
    }

    public static boolean e(String str) {
        return !str.equals("PROPFIND");
    }
}
