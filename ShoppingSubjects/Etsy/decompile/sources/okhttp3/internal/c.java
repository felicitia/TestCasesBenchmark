package okhttp3.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import com.etsy.android.lib.convos.Draft;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.IDN;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import okhttp3.HttpUrl;
import okhttp3.ab;
import okhttp3.u;
import okhttp3.z;
import okio.ByteString;
import okio.e;
import okio.t;

/* compiled from: Util */
public final class c {
    public static final byte[] a = new byte[0];
    public static final String[] b = new String[0];
    public static final ab c = ab.a(null, a);
    public static final z d = z.a((u) null, a);
    public static final Charset e = Charset.forName("UTF-8");
    public static final Charset f = Charset.forName("ISO-8859-1");
    public static final TimeZone g = TimeZone.getTimeZone("GMT");
    public static final Comparator<String> h = new Comparator<String>() {
        /* renamed from: a */
        public int compare(String str, String str2) {
            return str.compareTo(str2);
        }
    };
    private static final ByteString i = ByteString.decodeHex("efbbbf");
    private static final ByteString j = ByteString.decodeHex("feff");
    private static final ByteString k = ByteString.decodeHex("fffe");
    private static final ByteString l = ByteString.decodeHex("0000ffff");
    private static final ByteString m = ByteString.decodeHex("ffff0000");
    private static final Charset n = Charset.forName("UTF-16BE");
    private static final Charset o = Charset.forName("UTF-16LE");
    private static final Charset p = Charset.forName("UTF-32BE");
    private static final Charset q = Charset.forName("UTF-32LE");
    private static final Pattern r = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");

    public static int a(char c2) {
        if (c2 >= '0' && c2 <= '9') {
            return c2 - '0';
        }
        if (c2 >= 'a' && c2 <= 'f') {
            return (c2 - 'a') + 10;
        }
        if (c2 < 'A' || c2 > 'F') {
            return -1;
        }
        return (c2 - 'A') + 10;
    }

    public static void a(long j2, long j3, long j4) {
        if ((j3 | j4) < 0 || j3 > j2 || j2 - j3 < j4) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Exception unused) {
            }
        }
    }

    public static void a(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (AssertionError e2) {
                if (!a(e2)) {
                    throw e2;
                }
            } catch (RuntimeException e3) {
                throw e3;
            } catch (Exception unused) {
            }
        }
    }

    public static boolean a(t tVar, int i2, TimeUnit timeUnit) {
        try {
            return b(tVar, i2, timeUnit);
        } catch (IOException unused) {
            return false;
        }
    }

    public static boolean b(t tVar, int i2, TimeUnit timeUnit) throws IOException {
        long nanoTime = System.nanoTime();
        long d2 = tVar.a().k_() ? tVar.a().d() - nanoTime : Long.MAX_VALUE;
        tVar.a().a(nanoTime + Math.min(d2, timeUnit.toNanos((long) i2)));
        try {
            okio.c cVar = new okio.c();
            while (tVar.a(cVar, PlaybackStateCompat.ACTION_PLAY_FROM_URI) != -1) {
                cVar.t();
            }
            if (d2 == Long.MAX_VALUE) {
                tVar.a().f();
            } else {
                tVar.a().a(nanoTime + d2);
            }
            return true;
        } catch (InterruptedIOException unused) {
            if (d2 == Long.MAX_VALUE) {
                tVar.a().f();
            } else {
                tVar.a().a(nanoTime + d2);
            }
            return false;
        } catch (Throwable th) {
            if (d2 == Long.MAX_VALUE) {
                tVar.a().f();
            } else {
                tVar.a().a(nanoTime + d2);
            }
            throw th;
        }
    }

    public static <T> List<T> a(List<T> list) {
        return Collections.unmodifiableList(new ArrayList(list));
    }

    public static <T> List<T> a(T... tArr) {
        return Collections.unmodifiableList(Arrays.asList((Object[]) tArr.clone()));
    }

    public static ThreadFactory a(final String str, final boolean z) {
        return new ThreadFactory() {
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, str);
                thread.setDaemon(z);
                return thread;
            }
        };
    }

    public static String[] a(Comparator<? super String> comparator, String[] strArr, String[] strArr2) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            int length = strArr2.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                } else if (comparator.compare(str, strArr2[i2]) == 0) {
                    arrayList.add(str);
                    break;
                } else {
                    i2++;
                }
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static boolean b(Comparator<String> comparator, String[] strArr, String[] strArr2) {
        if (strArr == null || strArr2 == null || strArr.length == 0 || strArr2.length == 0) {
            return false;
        }
        for (String str : strArr) {
            for (String compare : strArr2) {
                if (comparator.compare(str, compare) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String a(HttpUrl httpUrl, boolean z) {
        String str;
        if (httpUrl.f().contains(Draft.IMAGE_DELIMITER)) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append(httpUrl.f());
            sb.append("]");
            str = sb.toString();
        } else {
            str = httpUrl.f();
        }
        if (!z && httpUrl.g() == HttpUrl.a(httpUrl.b())) {
            return str;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(Draft.IMAGE_DELIMITER);
        sb2.append(httpUrl.g());
        return sb2.toString();
    }

    public static boolean a(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    public static int a(Comparator<String> comparator, String[] strArr, String str) {
        int length = strArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (comparator.compare(strArr[i2], str) == 0) {
                return i2;
            }
        }
        return -1;
    }

    public static String[] a(String[] strArr, String str) {
        String[] strArr2 = new String[(strArr.length + 1)];
        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        strArr2[strArr2.length - 1] = str;
        return strArr2;
    }

    public static int a(String str, int i2, int i3) {
        while (i2 < i3) {
            switch (str.charAt(i2)) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    i2++;
                default:
                    return i2;
            }
        }
        return i3;
    }

    public static int b(String str, int i2, int i3) {
        int i4 = i3 - 1;
        while (i4 >= i2) {
            switch (str.charAt(i4)) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    i4--;
                default:
                    return i4 + 1;
            }
        }
        return i2;
    }

    public static String c(String str, int i2, int i3) {
        int a2 = a(str, i2, i3);
        return str.substring(a2, b(str, a2, i3));
    }

    public static int a(String str, int i2, int i3, String str2) {
        while (i2 < i3) {
            if (str2.indexOf(str.charAt(i2)) != -1) {
                return i2;
            }
            i2++;
        }
        return i3;
    }

    public static int a(String str, int i2, int i3, char c2) {
        while (i2 < i3) {
            if (str.charAt(i2) == c2) {
                return i2;
            }
            i2++;
        }
        return i3;
    }

    public static String a(String str) {
        InetAddress inetAddress;
        if (str.contains(Draft.IMAGE_DELIMITER)) {
            if (!str.startsWith("[") || !str.endsWith("]")) {
                inetAddress = d(str, 0, str.length());
            } else {
                inetAddress = d(str, 1, str.length() - 1);
            }
            if (inetAddress == null) {
                return null;
            }
            byte[] address = inetAddress.getAddress();
            if (address.length == 16) {
                return a(address);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid IPv6 address: '");
            sb.append(str);
            sb.append("'");
            throw new AssertionError(sb.toString());
        }
        try {
            String lowerCase = IDN.toASCII(str).toLowerCase(Locale.US);
            if (!lowerCase.isEmpty() && !d(lowerCase)) {
                return lowerCase;
            }
            return null;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    private static boolean d(String str) {
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (charAt <= 31 || charAt >= 127 || " #%/:?@[\\]".indexOf(charAt) != -1) {
                return true;
            }
        }
        return false;
    }

    public static int b(String str) {
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt <= 31 || charAt >= 127) {
                return i2;
            }
        }
        return -1;
    }

    public static boolean c(String str) {
        return r.matcher(str).matches();
    }

    public static String a(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    public static Charset a(e eVar, Charset charset) throws IOException {
        if (eVar.a(0, i)) {
            eVar.i((long) i.size());
            return e;
        } else if (eVar.a(0, j)) {
            eVar.i((long) j.size());
            return n;
        } else if (eVar.a(0, k)) {
            eVar.i((long) k.size());
            return o;
        } else if (eVar.a(0, l)) {
            eVar.i((long) l.size());
            return p;
        } else if (!eVar.a(0, m)) {
            return charset;
        } else {
            eVar.i((long) m.size());
            return q;
        }
    }

    public static AssertionError a(String str, Exception exc) {
        AssertionError assertionError = new AssertionError(str);
        try {
            assertionError.initCause(exc);
        } catch (IllegalStateException unused) {
        }
        return assertionError;
    }

    private static InetAddress d(String str, int i2, int i3) {
        byte[] bArr = new byte[16];
        int i4 = -1;
        int i5 = -1;
        int i6 = 0;
        while (true) {
            if (i2 >= i3) {
                break;
            } else if (i6 == bArr.length) {
                return null;
            } else {
                int i7 = i2 + 2;
                if (i7 > i3 || !str.regionMatches(i2, "::", 0, 2)) {
                    if (i6 != 0) {
                        if (str.regionMatches(i2, Draft.IMAGE_DELIMITER, 0, 1)) {
                            i2++;
                        } else if (!str.regionMatches(i2, ".", 0, 1) || !a(str, i5, i3, bArr, i6 - 2)) {
                            return null;
                        } else {
                            i6 += 2;
                        }
                    }
                    i5 = i2;
                } else if (i4 != -1) {
                    return null;
                } else {
                    i6 += 2;
                    if (i7 == i3) {
                        i4 = i6;
                        break;
                    }
                    i4 = i6;
                    i5 = i7;
                }
                int i8 = 0;
                i2 = i5;
                while (i2 < i3) {
                    int a2 = a(str.charAt(i2));
                    if (a2 == -1) {
                        break;
                    }
                    i8 = (i8 << 4) + a2;
                    i2++;
                }
                int i9 = i2 - i5;
                if (i9 == 0 || i9 > 4) {
                    return null;
                }
                int i10 = i6 + 1;
                bArr[i6] = (byte) ((i8 >>> 8) & 255);
                i6 = i10 + 1;
                bArr[i10] = (byte) (i8 & 255);
            }
        }
        if (i6 != bArr.length) {
            if (i4 == -1) {
                return null;
            }
            int i11 = i6 - i4;
            System.arraycopy(bArr, i4, bArr, bArr.length - i11, i11);
            Arrays.fill(bArr, i4, (bArr.length - i6) + i4, 0);
        }
        try {
            return InetAddress.getByAddress(bArr);
        } catch (UnknownHostException unused) {
            throw new AssertionError();
        }
    }

    private static boolean a(String str, int i2, int i3, byte[] bArr, int i4) {
        int i5 = i4;
        while (i2 < i3) {
            if (i5 == bArr.length) {
                return false;
            }
            if (i5 != i4) {
                if (str.charAt(i2) != '.') {
                    return false;
                }
                i2++;
            }
            int i6 = i2;
            int i7 = 0;
            while (i6 < i3) {
                char charAt = str.charAt(i6);
                if (charAt < '0' || charAt > '9') {
                    break;
                } else if (i7 == 0 && i2 != i6) {
                    return false;
                } else {
                    i7 = ((i7 * 10) + charAt) - 48;
                    if (i7 > 255) {
                        return false;
                    }
                    i6++;
                }
            }
            if (i6 - i2 == 0) {
                return false;
            }
            int i8 = i5 + 1;
            bArr[i5] = (byte) i7;
            i5 = i8;
            i2 = i6;
        }
        if (i5 != i4 + 4) {
            return false;
        }
        return true;
    }

    private static String a(byte[] bArr) {
        int i2 = 0;
        int i3 = 0;
        int i4 = -1;
        int i5 = 0;
        while (i5 < bArr.length) {
            int i6 = i5;
            while (i6 < 16 && bArr[i6] == 0 && bArr[i6 + 1] == 0) {
                i6 += 2;
            }
            int i7 = i6 - i5;
            if (i7 > i3 && i7 >= 4) {
                i4 = i5;
                i3 = i7;
            }
            i5 = i6 + 2;
        }
        okio.c cVar = new okio.c();
        while (i2 < bArr.length) {
            if (i2 == i4) {
                cVar.k(58);
                i2 += i3;
                if (i2 == 16) {
                    cVar.k(58);
                }
            } else {
                if (i2 > 0) {
                    cVar.k(58);
                }
                cVar.l((long) (((bArr[i2] & 255) << 8) | (bArr[i2 + 1] & 255)));
                i2 += 2;
            }
        }
        return cVar.p();
    }
}
