package com.google.zxing.oned;

import org.apache.commons.lang3.ClassUtils;

/* compiled from: CodaBarWriter */
public final class b extends n {
    private static final char[] a = {'A', 'B', 'C', 'D'};
    private static final char[] b = {'T', 'N', '*', 'E'};
    private static final char[] c = {'/', ':', '+', ClassUtils.PACKAGE_SEPARATOR_CHAR};
    private static final char d = a[0];

    public boolean[] a(String str) {
        int i;
        if (str.length() < 2) {
            StringBuilder sb = new StringBuilder();
            sb.append(d);
            sb.append(str);
            sb.append(d);
            str = sb.toString();
        } else {
            char upperCase = Character.toUpperCase(str.charAt(0));
            char upperCase2 = Character.toUpperCase(str.charAt(str.length() - 1));
            boolean a2 = a.a(a, upperCase);
            boolean a3 = a.a(a, upperCase2);
            boolean a4 = a.a(b, upperCase);
            boolean a5 = a.a(b, upperCase2);
            if (a2) {
                if (!a3) {
                    StringBuilder sb2 = new StringBuilder("Invalid start/end guards: ");
                    sb2.append(str);
                    throw new IllegalArgumentException(sb2.toString());
                }
            } else if (a4) {
                if (!a5) {
                    StringBuilder sb3 = new StringBuilder("Invalid start/end guards: ");
                    sb3.append(str);
                    throw new IllegalArgumentException(sb3.toString());
                }
            } else if (a3 || a5) {
                StringBuilder sb4 = new StringBuilder("Invalid start/end guards: ");
                sb4.append(str);
                throw new IllegalArgumentException(sb4.toString());
            } else {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(d);
                sb5.append(str);
                sb5.append(d);
                str = sb5.toString();
            }
        }
        int i2 = 20;
        for (int i3 = 1; i3 < str.length() - 1; i3++) {
            if (Character.isDigit(str.charAt(i3)) || str.charAt(i3) == '-' || str.charAt(i3) == '$') {
                i2 += 9;
            } else if (a.a(c, str.charAt(i3))) {
                i2 += 10;
            } else {
                StringBuilder sb6 = new StringBuilder("Cannot encode : '");
                sb6.append(str.charAt(i3));
                sb6.append('\'');
                throw new IllegalArgumentException(sb6.toString());
            }
        }
        boolean[] zArr = new boolean[(i2 + (str.length() - 1))];
        int i4 = 0;
        for (int i5 = 0; i5 < str.length(); i5++) {
            char upperCase3 = Character.toUpperCase(str.charAt(i5));
            if (i5 == 0 || i5 == str.length() - 1) {
                if (upperCase3 == '*') {
                    upperCase3 = 'C';
                } else if (upperCase3 == 'E') {
                    upperCase3 = 'D';
                } else if (upperCase3 == 'N') {
                    upperCase3 = 'B';
                } else if (upperCase3 == 'T') {
                    upperCase3 = 'A';
                }
            }
            int i6 = 0;
            while (true) {
                if (i6 >= a.a.length) {
                    i = 0;
                    break;
                } else if (upperCase3 == a.a[i6]) {
                    i = a.b[i6];
                    break;
                } else {
                    i6++;
                }
            }
            int i7 = 0;
            boolean z = true;
            int i8 = i4;
            int i9 = 0;
            while (i9 < 7) {
                zArr[i8] = z;
                i8++;
                if (((i >> (6 - i9)) & 1) == 0 || i7 == 1) {
                    z = !z;
                    i9++;
                    i7 = 0;
                } else {
                    i7++;
                }
            }
            if (i5 < str.length() - 1) {
                zArr[i8] = false;
                i8++;
            }
            i4 = i8;
        }
        return zArr;
    }
}
