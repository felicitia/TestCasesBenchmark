package okhttp3.internal.b;

import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import java.util.List;
import java.util.regex.Pattern;
import okhttp3.HttpUrl;
import okhttp3.aa;
import okhttp3.l;
import okhttp3.m;
import okhttp3.s;

/* compiled from: HttpHeaders */
public final class e {
    private static final Pattern a = Pattern.compile(" +([^ \"=]*)=(:?\"([^\"]*)\"|([^ \"=]*)) *(:?,|$)");

    public static long a(aa aaVar) {
        return a(aaVar.f());
    }

    public static long a(s sVar) {
        return a(sVar.a("Content-Length"));
    }

    private static long a(String str) {
        if (str == null) {
            return -1;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public static void a(m mVar, HttpUrl httpUrl, s sVar) {
        if (mVar != m.a) {
            List a2 = l.a(httpUrl, sVar);
            if (!a2.isEmpty()) {
                mVar.a(httpUrl, a2);
            }
        }
    }

    public static boolean b(aa aaVar) {
        if (aaVar.a().b().equals(BaseHttpRequest.HEAD)) {
            return false;
        }
        int b = aaVar.b();
        if (((b >= 100 && b < 200) || b == 204 || b == 304) && a(aaVar) == -1 && !"chunked".equalsIgnoreCase(aaVar.a("Transfer-Encoding"))) {
            return false;
        }
        return true;
    }

    public static int a(String str, int i, String str2) {
        while (i < str.length() && str2.indexOf(str.charAt(i)) == -1) {
            i++;
        }
        return i;
    }

    public static int a(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt != ' ' && charAt != 9) {
                break;
            }
            i++;
        }
        return i;
    }

    public static int b(String str, int i) {
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            if (parseLong < 0) {
                return 0;
            }
            return (int) parseLong;
        } catch (NumberFormatException unused) {
            return i;
        }
    }
}
