package okhttp3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.b.d;
import okhttp3.internal.c;

/* compiled from: Cookie */
public final class l {
    private static final Pattern a = Pattern.compile("(\\d{2,4})[^\\d]*");
    private static final Pattern b = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");
    private static final Pattern c = Pattern.compile("(\\d{1,2})[^\\d]*");
    private static final Pattern d = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");
    private final String e;
    private final String f;
    private final long g;
    private final String h;
    private final String i;
    private final boolean j;
    private final boolean k;
    private final boolean l;
    private final boolean m;

    private l(String str, String str2, long j2, String str3, String str4, boolean z, boolean z2, boolean z3, boolean z4) {
        this.e = str;
        this.f = str2;
        this.g = j2;
        this.h = str3;
        this.i = str4;
        this.j = z;
        this.k = z2;
        this.m = z3;
        this.l = z4;
    }

    public String a() {
        return this.e;
    }

    public String b() {
        return this.f;
    }

    private static boolean a(String str, String str2) {
        if (str.equals(str2)) {
            return true;
        }
        if (!str.endsWith(str2) || str.charAt((str.length() - str2.length()) - 1) != '.' || c.c(str)) {
            return false;
        }
        return true;
    }

    public static l a(HttpUrl httpUrl, String str) {
        return a(System.currentTimeMillis(), httpUrl, str);
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0135  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x013a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static okhttp3.l a(long r26, okhttp3.HttpUrl r28, java.lang.String r29) {
        /*
            r2 = r29
            int r3 = r29.length()
            r4 = 59
            r5 = 0
            int r6 = okhttp3.internal.c.a(r2, r5, r3, r4)
            r7 = 61
            int r8 = okhttp3.internal.c.a(r2, r5, r6, r7)
            r9 = 0
            if (r8 != r6) goto L_0x0017
            return r9
        L_0x0017:
            java.lang.String r11 = okhttp3.internal.c.c(r2, r5, r8)
            boolean r10 = r11.isEmpty()
            if (r10 != 0) goto L_0x014e
            int r10 = okhttp3.internal.c.b(r11)
            r12 = -1
            if (r10 == r12) goto L_0x002a
            goto L_0x014e
        L_0x002a:
            r10 = 1
            int r8 = r8 + r10
            java.lang.String r8 = okhttp3.internal.c.c(r2, r8, r6)
            int r13 = okhttp3.internal.c.b(r8)
            if (r13 == r12) goto L_0x0037
            return r9
        L_0x0037:
            int r6 = r6 + r10
            r12 = -1
            r14 = 253402300799999(0xe677d21fdbff, double:1.251973714024093E-309)
            r18 = r5
            r19 = r18
            r24 = r19
            r21 = r9
            r20 = r10
            r16 = r12
            r22 = r14
            r10 = r21
        L_0x004f:
            if (r6 >= r3) goto L_0x00c4
            int r9 = okhttp3.internal.c.a(r2, r6, r3, r4)
            int r4 = okhttp3.internal.c.a(r2, r6, r9, r7)
            java.lang.String r6 = okhttp3.internal.c.c(r2, r6, r4)
            if (r4 >= r9) goto L_0x0066
            int r4 = r4 + 1
            java.lang.String r4 = okhttp3.internal.c.c(r2, r4, r9)
            goto L_0x0068
        L_0x0066:
            java.lang.String r4 = ""
        L_0x0068:
            java.lang.String r7 = "expires"
            boolean r7 = r6.equalsIgnoreCase(r7)
            if (r7 == 0) goto L_0x007d
            int r6 = r4.length()     // Catch:{ IllegalArgumentException -> 0x00bc }
            long r6 = a(r4, r5, r6)     // Catch:{ IllegalArgumentException -> 0x00bc }
            r22 = r6
        L_0x007a:
            r24 = 1
            goto L_0x00bc
        L_0x007d:
            java.lang.String r7 = "max-age"
            boolean r7 = r6.equalsIgnoreCase(r7)
            if (r7 == 0) goto L_0x008c
            long r6 = a(r4)     // Catch:{  }
            r16 = r6
            goto L_0x007a
        L_0x008c:
            java.lang.String r7 = "domain"
            boolean r7 = r6.equalsIgnoreCase(r7)
            if (r7 == 0) goto L_0x009c
            java.lang.String r4 = b(r4)     // Catch:{ IllegalArgumentException -> 0x00bc }
            r10 = r4
            r20 = r5
            goto L_0x00bc
        L_0x009c:
            java.lang.String r7 = "path"
            boolean r7 = r6.equalsIgnoreCase(r7)
            if (r7 == 0) goto L_0x00a7
            r21 = r4
            goto L_0x00bc
        L_0x00a7:
            java.lang.String r4 = "secure"
            boolean r4 = r6.equalsIgnoreCase(r4)
            if (r4 == 0) goto L_0x00b2
            r18 = 1
            goto L_0x00bc
        L_0x00b2:
            java.lang.String r4 = "httponly"
            boolean r4 = r6.equalsIgnoreCase(r4)
            if (r4 == 0) goto L_0x00bc
            r19 = 1
        L_0x00bc:
            int r6 = r9 + 1
            r4 = 59
            r7 = 61
            r9 = 0
            goto L_0x004f
        L_0x00c4:
            r2 = -9223372036854775808
            int r4 = (r16 > r2 ? 1 : (r16 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x00cc
        L_0x00ca:
            r13 = r2
            goto L_0x00f1
        L_0x00cc:
            int r2 = (r16 > r12 ? 1 : (r16 == r12 ? 0 : -1))
            if (r2 == 0) goto L_0x00ef
            r2 = 9223372036854775(0x20c49ba5e353f7, double:4.663754807431093E-308)
            int r4 = (r16 > r2 ? 1 : (r16 == r2 ? 0 : -1))
            if (r4 > 0) goto L_0x00de
            r2 = 1000(0x3e8, double:4.94E-321)
            long r16 = r16 * r2
            goto L_0x00e3
        L_0x00de:
            r16 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
        L_0x00e3:
            long r2 = r26 + r16
            int r4 = (r2 > r26 ? 1 : (r2 == r26 ? 0 : -1))
            if (r4 < 0) goto L_0x00ed
            int r0 = (r2 > r14 ? 1 : (r2 == r14 ? 0 : -1))
            if (r0 <= 0) goto L_0x00ca
        L_0x00ed:
            r13 = r14
            goto L_0x00f1
        L_0x00ef:
            r13 = r22
        L_0x00f1:
            java.lang.String r0 = r28.f()
            if (r10 != 0) goto L_0x00fa
            r15 = r0
            r1 = 0
            goto L_0x0104
        L_0x00fa:
            boolean r1 = a(r0, r10)
            if (r1 != 0) goto L_0x0102
            r1 = 0
            return r1
        L_0x0102:
            r1 = 0
            r15 = r10
        L_0x0104:
            int r0 = r0.length()
            int r2 = r15.length()
            if (r0 == r2) goto L_0x0119
            okhttp3.internal.f.a r0 = okhttp3.internal.f.a.a()
            java.lang.String r0 = r0.a(r15)
            if (r0 != 0) goto L_0x0119
            return r1
        L_0x0119:
            r9 = r21
            if (r9 == 0) goto L_0x0129
            java.lang.String r0 = "/"
            boolean r0 = r9.startsWith(r0)
            if (r0 != 0) goto L_0x0126
            goto L_0x0129
        L_0x0126:
            r16 = r9
            goto L_0x013e
        L_0x0129:
            java.lang.String r0 = r28.h()
            r1 = 47
            int r1 = r0.lastIndexOf(r1)
            if (r1 == 0) goto L_0x013a
            java.lang.String r0 = r0.substring(r5, r1)
            goto L_0x013c
        L_0x013a:
            java.lang.String r0 = "/"
        L_0x013c:
            r16 = r0
        L_0x013e:
            okhttp3.l r0 = new okhttp3.l
            r10 = r0
            r12 = r8
            r17 = r18
            r18 = r19
            r19 = r20
            r20 = r24
            r10.<init>(r11, r12, r13, r15, r16, r17, r18, r19, r20)
            return r0
        L_0x014e:
            r0 = r9
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.l.a(long, okhttp3.HttpUrl, java.lang.String):okhttp3.l");
    }

    private static long a(String str, int i2, int i3) {
        int a2 = a(str, i2, i3, false);
        Matcher matcher = d.matcher(str);
        int i4 = -1;
        int i5 = -1;
        int i6 = -1;
        int i7 = -1;
        int i8 = -1;
        int i9 = -1;
        while (a2 < i3) {
            int a3 = a(str, a2 + 1, i3, true);
            matcher.region(a2, a3);
            if (i4 == -1 && matcher.usePattern(d).matches()) {
                int parseInt = Integer.parseInt(matcher.group(1));
                int parseInt2 = Integer.parseInt(matcher.group(2));
                i9 = Integer.parseInt(matcher.group(3));
                i8 = parseInt2;
                i4 = parseInt;
            } else if (i6 == -1 && matcher.usePattern(c).matches()) {
                i6 = Integer.parseInt(matcher.group(1));
            } else if (i7 == -1 && matcher.usePattern(b).matches()) {
                i7 = b.pattern().indexOf(matcher.group(1).toLowerCase(Locale.US)) / 4;
            } else if (i5 == -1 && matcher.usePattern(a).matches()) {
                i5 = Integer.parseInt(matcher.group(1));
            }
            a2 = a(str, a3 + 1, i3, false);
        }
        if (i5 >= 70 && i5 <= 99) {
            i5 += 1900;
        }
        if (i5 >= 0 && i5 <= 69) {
            i5 += 2000;
        }
        if (i5 < 1601) {
            throw new IllegalArgumentException();
        } else if (i7 == -1) {
            throw new IllegalArgumentException();
        } else if (i6 < 1 || i6 > 31) {
            throw new IllegalArgumentException();
        } else if (i4 < 0 || i4 > 23) {
            throw new IllegalArgumentException();
        } else if (i8 < 0 || i8 > 59) {
            throw new IllegalArgumentException();
        } else if (i9 < 0 || i9 > 59) {
            throw new IllegalArgumentException();
        } else {
            GregorianCalendar gregorianCalendar = new GregorianCalendar(c.g);
            gregorianCalendar.setLenient(false);
            gregorianCalendar.set(1, i5);
            gregorianCalendar.set(2, i7 - 1);
            gregorianCalendar.set(5, i6);
            gregorianCalendar.set(11, i4);
            gregorianCalendar.set(12, i8);
            gregorianCalendar.set(13, i9);
            gregorianCalendar.set(14, 0);
            return gregorianCalendar.getTimeInMillis();
        }
    }

    private static int a(String str, int i2, int i3, boolean z) {
        while (i2 < i3) {
            char charAt = str.charAt(i2);
            if (((charAt < ' ' && charAt != 9) || charAt >= 127 || (charAt >= '0' && charAt <= '9') || ((charAt >= 'a' && charAt <= 'z') || ((charAt >= 'A' && charAt <= 'Z') || charAt == ':'))) == (!z)) {
                return i2;
            }
            i2++;
        }
        return i3;
    }

    private static long a(String str) {
        long j2 = Long.MIN_VALUE;
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong > 0) {
                j2 = parseLong;
            }
            return j2;
        } catch (NumberFormatException e2) {
            if (str.matches("-?\\d+")) {
                if (!str.startsWith("-")) {
                    j2 = Long.MAX_VALUE;
                }
                return j2;
            }
            throw e2;
        }
    }

    private static String b(String str) {
        if (str.endsWith(".")) {
            throw new IllegalArgumentException();
        }
        if (str.startsWith(".")) {
            str = str.substring(1);
        }
        String a2 = c.a(str);
        if (a2 != null) {
            return a2;
        }
        throw new IllegalArgumentException();
    }

    public static List<l> a(HttpUrl httpUrl, s sVar) {
        List b2 = sVar.b("Set-Cookie");
        int size = b2.size();
        ArrayList arrayList = null;
        for (int i2 = 0; i2 < size; i2++) {
            l a2 = a(httpUrl, (String) b2.get(i2));
            if (a2 != null) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(a2);
            }
        }
        if (arrayList != null) {
            return Collections.unmodifiableList(arrayList);
        }
        return Collections.emptyList();
    }

    public String toString() {
        return a(false);
    }

    /* access modifiers changed from: 0000 */
    public String a(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.e);
        sb.append('=');
        sb.append(this.f);
        if (this.l) {
            if (this.g == Long.MIN_VALUE) {
                sb.append("; max-age=0");
            } else {
                sb.append("; expires=");
                sb.append(d.a(new Date(this.g)));
            }
        }
        if (!this.m) {
            sb.append("; domain=");
            if (z) {
                sb.append(".");
            }
            sb.append(this.h);
        }
        sb.append("; path=");
        sb.append(this.i);
        if (this.j) {
            sb.append("; secure");
        }
        if (this.k) {
            sb.append("; httponly");
        }
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof l)) {
            return false;
        }
        l lVar = (l) obj;
        if (lVar.e.equals(this.e) && lVar.f.equals(this.f) && lVar.h.equals(this.h) && lVar.i.equals(this.i) && lVar.g == this.g && lVar.j == this.j && lVar.k == this.k && lVar.l == this.l && lVar.m == this.m) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return (31 * (((((((((((((((527 + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + this.h.hashCode()) * 31) + this.i.hashCode()) * 31) + ((int) (this.g ^ (this.g >>> 32)))) * 31) + (this.j ^ true ? 1 : 0)) * 31) + (this.k ^ true ? 1 : 0)) * 31) + (this.l ^ true ? 1 : 0))) + (this.m ^ true ? 1 : 0);
    }
}
