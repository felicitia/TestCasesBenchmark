package okhttp3;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import okhttp3.internal.c;

public final class HttpUrl {
    private static final char[] d = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    final String a;
    final String b;
    final int c;
    private final String e;
    private final String f;
    private final List<String> g;
    private final List<String> h;
    private final String i;
    private final String j;

    public static final class Builder {
        String a;
        String b = "";
        String c = "";
        String d;
        int e = -1;
        final List<String> f = new ArrayList();
        List<String> g;
        String h;

        enum ParseResult {
            SUCCESS,
            MISSING_SCHEME,
            UNSUPPORTED_SCHEME,
            INVALID_PORT,
            INVALID_HOST
        }

        public Builder() {
            this.f.add("");
        }

        public Builder a(String str) {
            if (str == null) {
                throw new NullPointerException("scheme == null");
            }
            if (str.equalsIgnoreCase("http")) {
                this.a = "http";
            } else if (str.equalsIgnoreCase("https")) {
                this.a = "https";
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("unexpected scheme: ");
                sb.append(str);
                throw new IllegalArgumentException(sb.toString());
            }
            return this;
        }

        public Builder b(String str) {
            if (str == null) {
                throw new NullPointerException("username == null");
            }
            this.b = HttpUrl.a(str, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }

        public Builder c(String str) {
            if (str == null) {
                throw new NullPointerException("password == null");
            }
            this.c = HttpUrl.a(str, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }

        public Builder d(String str) {
            if (str == null) {
                throw new NullPointerException("host == null");
            }
            String e2 = e(str, 0, str.length());
            if (e2 == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("unexpected host: ");
                sb.append(str);
                throw new IllegalArgumentException(sb.toString());
            }
            this.d = e2;
            return this;
        }

        public Builder a(int i) {
            if (i <= 0 || i > 65535) {
                StringBuilder sb = new StringBuilder();
                sb.append("unexpected port: ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
            }
            this.e = i;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public int a() {
            return this.e != -1 ? this.e : HttpUrl.a(this.a);
        }

        public Builder e(String str) {
            List<String> list;
            if (str != null) {
                list = HttpUrl.b(HttpUrl.a(str, " \"'<>#", true, false, true, true));
            } else {
                list = null;
            }
            this.g = list;
            return this;
        }

        public Builder a(String str, String str2) {
            String str3;
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            if (this.g == null) {
                this.g = new ArrayList();
            }
            this.g.add(HttpUrl.a(str, " !\"#$&'(),/:;<=>?@[]\\^`{|}~", false, false, true, true));
            List<String> list = this.g;
            if (str2 != null) {
                str3 = HttpUrl.a(str2, " !\"#$&'(),/:;<=>?@[]\\^`{|}~", false, false, true, true);
            } else {
                str3 = null;
            }
            list.add(str3);
            return this;
        }

        public Builder b(String str, String str2) {
            String str3;
            if (str == null) {
                throw new NullPointerException("encodedName == null");
            }
            if (this.g == null) {
                this.g = new ArrayList();
            }
            this.g.add(HttpUrl.a(str, " \"'<>#&=", true, false, true, true));
            List<String> list = this.g;
            if (str2 != null) {
                str3 = HttpUrl.a(str2, " \"'<>#&=", true, false, true, true);
            } else {
                str3 = null;
            }
            list.add(str3);
            return this;
        }

        /* access modifiers changed from: 0000 */
        public Builder b() {
            int size = this.f.size();
            for (int i = 0; i < size; i++) {
                this.f.set(i, HttpUrl.a((String) this.f.get(i), "[]", true, true, false, true));
            }
            if (this.g != null) {
                int size2 = this.g.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    String str = (String) this.g.get(i2);
                    if (str != null) {
                        this.g.set(i2, HttpUrl.a(str, "\\^`{|}", true, true, true, true));
                    }
                }
            }
            if (this.h != null) {
                this.h = HttpUrl.a(this.h, " \"#<>\\^`{|}", true, true, false, false);
            }
            return this;
        }

        public HttpUrl c() {
            if (this.a == null) {
                throw new IllegalStateException("scheme == null");
            } else if (this.d != null) {
                return new HttpUrl(this);
            } else {
                throw new IllegalStateException("host == null");
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.a);
            sb.append("://");
            if (!this.b.isEmpty() || !this.c.isEmpty()) {
                sb.append(this.b);
                if (!this.c.isEmpty()) {
                    sb.append(':');
                    sb.append(this.c);
                }
                sb.append('@');
            }
            if (this.d.indexOf(58) != -1) {
                sb.append('[');
                sb.append(this.d);
                sb.append(']');
            } else {
                sb.append(this.d);
            }
            int a2 = a();
            if (a2 != HttpUrl.a(this.a)) {
                sb.append(':');
                sb.append(a2);
            }
            HttpUrl.a(sb, this.f);
            if (this.g != null) {
                sb.append('?');
                HttpUrl.b(sb, this.g);
            }
            if (this.h != null) {
                sb.append('#');
                sb.append(this.h);
            }
            return sb.toString();
        }

        /* access modifiers changed from: 0000 */
        public ParseResult a(HttpUrl httpUrl, String str) {
            int i;
            int i2;
            int a2;
            int i3;
            HttpUrl httpUrl2 = httpUrl;
            String str2 = str;
            int a3 = c.a(str2, 0, str.length());
            int b2 = c.b(str2, a3, str.length());
            if (b(str2, a3, b2) != -1) {
                if (str2.regionMatches(true, a3, "https:", 0, 6)) {
                    this.a = "https";
                    a3 += "https:".length();
                } else {
                    if (!str2.regionMatches(true, a3, "http:", 0, 5)) {
                        return ParseResult.UNSUPPORTED_SCHEME;
                    }
                    this.a = "http";
                    a3 += "http:".length();
                }
            } else if (httpUrl2 == null) {
                return ParseResult.MISSING_SCHEME;
            } else {
                this.a = httpUrl2.a;
            }
            int c2 = c(str2, a3, b2);
            char c3 = '#';
            if (c2 >= 2 || httpUrl2 == null || !httpUrl2.a.equals(this.a)) {
                boolean z = false;
                boolean z2 = false;
                int i4 = a3 + c2;
                while (true) {
                    a2 = c.a(str2, i4, b2, "@/\\?#");
                    char charAt = a2 != b2 ? str2.charAt(a2) : 65535;
                    if (!(charAt == 65535 || charAt == c3 || charAt == '/' || charAt == '\\')) {
                        switch (charAt) {
                            case '?':
                                break;
                            case '@':
                                if (!z) {
                                    int a4 = c.a(str2, i4, a2, ':');
                                    int i5 = a4;
                                    i3 = a2;
                                    String a5 = HttpUrl.a(str2, i4, a4, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                                    if (z2) {
                                        StringBuilder sb = new StringBuilder();
                                        sb.append(this.b);
                                        sb.append("%40");
                                        sb.append(a5);
                                        a5 = sb.toString();
                                    }
                                    this.b = a5;
                                    if (i5 != i3) {
                                        this.c = HttpUrl.a(str2, i5 + 1, i3, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                                        z = true;
                                    }
                                    z2 = true;
                                } else {
                                    i3 = a2;
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append(this.c);
                                    sb2.append("%40");
                                    sb2.append(HttpUrl.a(str2, i4, i3, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null));
                                    this.c = sb2.toString();
                                }
                                i4 = i3 + 1;
                                continue;
                        }
                    }
                    c3 = '#';
                }
                i = a2;
                int d2 = d(str2, i4, i);
                int i6 = d2 + 1;
                if (i6 < i) {
                    this.d = e(str2, i4, d2);
                    this.e = f(str2, i6, i);
                    if (this.e == -1) {
                        return ParseResult.INVALID_PORT;
                    }
                } else {
                    this.d = e(str2, i4, d2);
                    this.e = HttpUrl.a(this.a);
                }
                if (this.d == null) {
                    return ParseResult.INVALID_HOST;
                }
            } else {
                this.b = httpUrl.d();
                this.c = httpUrl.e();
                this.d = httpUrl2.b;
                this.e = httpUrl2.c;
                this.f.clear();
                this.f.addAll(httpUrl.i());
                if (a3 == b2 || str2.charAt(a3) == '#') {
                    e(httpUrl.k());
                }
                i = a3;
            }
            int a6 = c.a(str2, i, b2, "?#");
            a(str2, i, a6);
            if (a6 >= b2 || str2.charAt(a6) != '?') {
                i2 = a6;
            } else {
                i2 = c.a(str2, a6, b2, '#');
                this.g = HttpUrl.b(HttpUrl.a(str2, a6 + 1, i2, " \"'<>#", true, false, true, true, null));
            }
            if (i2 < b2 && str2.charAt(i2) == '#') {
                this.h = HttpUrl.a(str2, i2 + 1, b2, "", true, false, false, false, null);
            }
            return ParseResult.SUCCESS;
        }

        private void a(String str, int i, int i2) {
            if (i != i2) {
                char charAt = str.charAt(i);
                if (charAt == '/' || charAt == '\\') {
                    this.f.clear();
                    this.f.add("");
                    i++;
                } else {
                    this.f.set(this.f.size() - 1, "");
                }
                while (true) {
                    int i3 = r11;
                    if (i3 < i2) {
                        r11 = c.a(str, i3, i2, "/\\");
                        boolean z = r11 < i2;
                        a(str, i3, r11, z, true);
                        if (z) {
                            r11++;
                        }
                    } else {
                        return;
                    }
                }
            }
        }

        private void a(String str, int i, int i2, boolean z, boolean z2) {
            String a2 = HttpUrl.a(str, i, i2, " \"<>^`{}|/\\?#", z2, false, false, true, null);
            if (!f(a2)) {
                if (g(a2)) {
                    d();
                    return;
                }
                if (((String) this.f.get(this.f.size() - 1)).isEmpty()) {
                    this.f.set(this.f.size() - 1, a2);
                } else {
                    this.f.add(a2);
                }
                if (z) {
                    this.f.add("");
                }
            }
        }

        private boolean f(String str) {
            return str.equals(".") || str.equalsIgnoreCase("%2e");
        }

        private boolean g(String str) {
            return str.equals("..") || str.equalsIgnoreCase("%2e.") || str.equalsIgnoreCase(".%2e") || str.equalsIgnoreCase("%2e%2e");
        }

        private void d() {
            if (!((String) this.f.remove(this.f.size() - 1)).isEmpty() || this.f.isEmpty()) {
                this.f.add("");
            } else {
                this.f.set(this.f.size() - 1, "");
            }
        }

        private static int b(String str, int i, int i2) {
            if (i2 - i < 2) {
                return -1;
            }
            char charAt = str.charAt(i);
            if ((charAt < 'a' || charAt > 'z') && (charAt < 'A' || charAt > 'Z')) {
                return -1;
            }
            while (true) {
                i++;
                if (i >= i2) {
                    return -1;
                }
                char charAt2 = str.charAt(i);
                if ((charAt2 < 'a' || charAt2 > 'z') && ((charAt2 < 'A' || charAt2 > 'Z') && !((charAt2 >= '0' && charAt2 <= '9') || charAt2 == '+' || charAt2 == '-' || charAt2 == '.'))) {
                    if (charAt2 == ':') {
                        return i;
                    }
                    return -1;
                }
            }
        }

        private static int c(String str, int i, int i2) {
            int i3 = 0;
            while (i < i2) {
                char charAt = str.charAt(i);
                if (charAt != '\\' && charAt != '/') {
                    break;
                }
                i3++;
                i++;
            }
            return i3;
        }

        private static int d(String str, int i, int i2) {
            while (i < i2) {
                char charAt = str.charAt(i);
                if (charAt == ':') {
                    return i;
                }
                if (charAt == '[') {
                    do {
                        i++;
                        if (i >= i2) {
                            break;
                        }
                    } while (str.charAt(i) != ']');
                }
                i++;
            }
            return i2;
        }

        private static String e(String str, int i, int i2) {
            return c.a(HttpUrl.a(str, i, i2, false));
        }

        private static int f(String str, int i, int i2) {
            try {
                int parseInt = Integer.parseInt(HttpUrl.a(str, i, i2, "", false, false, false, true, null));
                if (parseInt <= 0 || parseInt > 65535) {
                    return -1;
                }
                return parseInt;
            } catch (NumberFormatException unused) {
                return -1;
            }
        }
    }

    HttpUrl(Builder builder) {
        this.a = builder.a;
        this.e = a(builder.b, false);
        this.f = a(builder.c, false);
        this.b = builder.d;
        this.c = builder.a();
        this.g = a(builder.f, false);
        String str = null;
        this.h = builder.g != null ? a(builder.g, true) : null;
        if (builder.h != null) {
            str = a(builder.h, false);
        }
        this.i = str;
        this.j = builder.toString();
    }

    public URI a() {
        String builder = o().b().toString();
        try {
            return new URI(builder);
        } catch (URISyntaxException e2) {
            try {
                return URI.create(builder.replaceAll("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]", ""));
            } catch (Exception unused) {
                throw new RuntimeException(e2);
            }
        }
    }

    public String b() {
        return this.a;
    }

    public boolean c() {
        return this.a.equals("https");
    }

    public String d() {
        if (this.e.isEmpty()) {
            return "";
        }
        int length = this.a.length() + 3;
        return this.j.substring(length, c.a(this.j, length, this.j.length(), ":@"));
    }

    public String e() {
        if (this.f.isEmpty()) {
            return "";
        }
        return this.j.substring(this.j.indexOf(58, this.a.length() + 3) + 1, this.j.indexOf(64));
    }

    public String f() {
        return this.b;
    }

    public int g() {
        return this.c;
    }

    public static int a(String str) {
        if (str.equals("http")) {
            return 80;
        }
        return str.equals("https") ? 443 : -1;
    }

    public String h() {
        int indexOf = this.j.indexOf(47, this.a.length() + 3);
        return this.j.substring(indexOf, c.a(this.j, indexOf, this.j.length(), "?#"));
    }

    static void a(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            sb.append('/');
            sb.append((String) list.get(i2));
        }
    }

    public List<String> i() {
        int indexOf = this.j.indexOf(47, this.a.length() + 3);
        int a2 = c.a(this.j, indexOf, this.j.length(), "?#");
        ArrayList arrayList = new ArrayList();
        while (indexOf < a2) {
            int i2 = indexOf + 1;
            int a3 = c.a(this.j, i2, a2, '/');
            arrayList.add(this.j.substring(i2, a3));
            indexOf = a3;
        }
        return arrayList;
    }

    public List<String> j() {
        return this.g;
    }

    public String k() {
        if (this.h == null) {
            return null;
        }
        int indexOf = this.j.indexOf(63) + 1;
        return this.j.substring(indexOf, c.a(this.j, indexOf, this.j.length(), '#'));
    }

    static void b(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2 += 2) {
            String str = (String) list.get(i2);
            String str2 = (String) list.get(i2 + 1);
            if (i2 > 0) {
                sb.append('&');
            }
            sb.append(str);
            if (str2 != null) {
                sb.append('=');
                sb.append(str2);
            }
        }
    }

    static List<String> b(String str) {
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 <= str.length()) {
            int indexOf = str.indexOf(38, i2);
            if (indexOf == -1) {
                indexOf = str.length();
            }
            int indexOf2 = str.indexOf(61, i2);
            if (indexOf2 == -1 || indexOf2 > indexOf) {
                arrayList.add(str.substring(i2, indexOf));
                arrayList.add(null);
            } else {
                arrayList.add(str.substring(i2, indexOf2));
                arrayList.add(str.substring(indexOf2 + 1, indexOf));
            }
            i2 = indexOf + 1;
        }
        return arrayList;
    }

    public String l() {
        if (this.h == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        b(sb, this.h);
        return sb.toString();
    }

    public String m() {
        if (this.i == null) {
            return null;
        }
        return this.j.substring(this.j.indexOf(35) + 1);
    }

    public String n() {
        return d("/...").b("").c("").c().toString();
    }

    public HttpUrl c(String str) {
        Builder d2 = d(str);
        if (d2 != null) {
            return d2.c();
        }
        return null;
    }

    public Builder o() {
        Builder builder = new Builder();
        builder.a = this.a;
        builder.b = d();
        builder.c = e();
        builder.d = this.b;
        builder.e = this.c != a(this.a) ? this.c : -1;
        builder.f.clear();
        builder.f.addAll(i());
        builder.e(k());
        builder.h = m();
        return builder;
    }

    public Builder d(String str) {
        Builder builder = new Builder();
        if (builder.a(this, str) == ParseResult.SUCCESS) {
            return builder;
        }
        return null;
    }

    public static HttpUrl e(String str) {
        Builder builder = new Builder();
        if (builder.a((HttpUrl) null, str) == ParseResult.SUCCESS) {
            return builder.c();
        }
        return null;
    }

    public boolean equals(Object obj) {
        return (obj instanceof HttpUrl) && ((HttpUrl) obj).j.equals(this.j);
    }

    public int hashCode() {
        return this.j.hashCode();
    }

    public String toString() {
        return this.j;
    }

    static String a(String str, boolean z) {
        return a(str, 0, str.length(), z);
    }

    private List<String> a(List<String> list, boolean z) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i2 = 0; i2 < size; i2++) {
            String str = (String) list.get(i2);
            arrayList.add(str != null ? a(str, z) : null);
        }
        return Collections.unmodifiableList(arrayList);
    }

    static String a(String str, int i2, int i3, boolean z) {
        for (int i4 = i2; i4 < i3; i4++) {
            char charAt = str.charAt(i4);
            if (charAt == '%' || (charAt == '+' && z)) {
                okio.c cVar = new okio.c();
                cVar.b(str, i2, i4);
                a(cVar, str, i4, i3, z);
                return cVar.p();
            }
        }
        return str.substring(i2, i3);
    }

    static void a(okio.c cVar, String str, int i2, int i3, boolean z) {
        while (i2 < i3) {
            int codePointAt = str.codePointAt(i2);
            if (codePointAt == 37) {
                int i4 = i2 + 2;
                if (i4 < i3) {
                    int a2 = c.a(str.charAt(i2 + 1));
                    int a3 = c.a(str.charAt(i4));
                    if (!(a2 == -1 || a3 == -1)) {
                        cVar.k((a2 << 4) + a3);
                        i2 = i4;
                        i2 += Character.charCount(codePointAt);
                    }
                    cVar.a(codePointAt);
                    i2 += Character.charCount(codePointAt);
                }
            }
            if (codePointAt == 43 && z) {
                cVar.k(32);
                i2 += Character.charCount(codePointAt);
            }
            cVar.a(codePointAt);
            i2 += Character.charCount(codePointAt);
        }
    }

    static boolean a(String str, int i2, int i3) {
        int i4 = i2 + 2;
        if (i4 >= i3 || str.charAt(i2) != '%' || c.a(str.charAt(i2 + 1)) == -1 || c.a(str.charAt(i4)) == -1) {
            return false;
        }
        return true;
    }

    static String a(String str, int i2, int i3, String str2, boolean z, boolean z2, boolean z3, boolean z4, Charset charset) {
        String str3;
        String str4 = str;
        int i4 = i3;
        int i5 = i2;
        while (i5 < i4) {
            int codePointAt = str4.codePointAt(i5);
            if (codePointAt < 32 || codePointAt == 127 || (codePointAt >= 128 && z4)) {
                str3 = str2;
            } else {
                str3 = str2;
                if (str3.indexOf(codePointAt) == -1 && ((codePointAt != 37 || (z && (!z2 || a(str4, i5, i4)))) && (codePointAt != 43 || !z3))) {
                    i5 += Character.charCount(codePointAt);
                }
            }
            okio.c cVar = new okio.c();
            cVar.b(str4, i2, i5);
            a(cVar, str4, i5, i4, str3, z, z2, z3, z4, charset);
            return cVar.p();
        }
        return str4.substring(i2, i4);
    }

    static void a(okio.c cVar, String str, int i2, int i3, String str2, boolean z, boolean z2, boolean z3, boolean z4, Charset charset) {
        okio.c cVar2 = null;
        while (i2 < i3) {
            int codePointAt = str.codePointAt(i2);
            if (!z || !(codePointAt == 9 || codePointAt == 10 || codePointAt == 12 || codePointAt == 13)) {
                if (codePointAt == 43 && z3) {
                    cVar.b(z ? "+" : "%2B");
                } else if (codePointAt < 32 || codePointAt == 127 || ((codePointAt >= 128 && z4) || str2.indexOf(codePointAt) != -1 || (codePointAt == 37 && (!z || (z2 && !a(str, i2, i3)))))) {
                    if (cVar2 == null) {
                        cVar2 = new okio.c();
                    }
                    if (charset == null || charset.equals(c.e)) {
                        cVar2.a(codePointAt);
                    } else {
                        cVar2.a(str, i2, Character.charCount(codePointAt) + i2, charset);
                    }
                    while (!cVar2.f()) {
                        byte i4 = cVar2.i() & 255;
                        cVar.k(37);
                        cVar.k((int) d[(i4 >> 4) & 15]);
                        cVar.k((int) d[i4 & 15]);
                    }
                } else {
                    cVar.a(codePointAt);
                }
            }
            i2 += Character.charCount(codePointAt);
        }
    }

    static String a(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, Charset charset) {
        return a(str, 0, str.length(), str2, z, z2, z3, z4, charset);
    }

    static String a(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        return a(str, 0, str.length(), str2, z, z2, z3, z4, null);
    }
}
