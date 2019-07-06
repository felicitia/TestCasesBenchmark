package com.threatmetrix.TrustDefender.internal;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class NK {

    /* renamed from: byte reason: not valid java name */
    private static final Pattern f459byte = Pattern.compile("^[a-fA-F0-9]{40,64}$");

    /* renamed from: case reason: not valid java name */
    private static SecureRandom f460case = new SecureRandom();

    /* renamed from: char reason: not valid java name */
    private static final Pattern f461char = Pattern.compile("^([0]{1,2}[:])*([0]{1,2})$");

    /* renamed from: do reason: not valid java name */
    private static final MessageDigest f462do;

    /* renamed from: for reason: not valid java name */
    private static final String f463for = TL.m331if(NK.class);

    /* renamed from: if reason: not valid java name */
    private static final MessageDigest f464if;

    /* renamed from: int reason: not valid java name */
    private static final MessageDigest f465int;

    /* renamed from: new reason: not valid java name */
    private static final char[] f466new = "0123456789abcdef".toCharArray();

    /* renamed from: try reason: not valid java name */
    private static final Pattern f467try = Pattern.compile("^([0-9A-Fa-f]{2}[:])*([0-9A-Fa-f]{2})$");

    NK() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x005b  */
    static {
        /*
            java.lang.Class<com.threatmetrix.TrustDefender.internal.NK> r0 = com.threatmetrix.TrustDefender.internal.NK.class
            java.lang.String r0 = com.threatmetrix.TrustDefender.internal.TL.m331if(r0)
            f463for = r0
            java.lang.String r0 = "0123456789abcdef"
            char[] r0 = r0.toCharArray()
            f466new = r0
            java.lang.String r0 = "^[a-fA-F0-9]{40,64}$"
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)
            f459byte = r0
            java.lang.String r0 = "^([0-9A-Fa-f]{2}[:])*([0-9A-Fa-f]{2})$"
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)
            f467try = r0
            java.lang.String r0 = "^([0]{1,2}[:])*([0]{1,2})$"
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)
            f461char = r0
            java.lang.String r0 = f463for
            java.lang.String r1 = "Getting SHA1 digest"
            com.threatmetrix.TrustDefender.internal.TL.m338new(r0, r1)
            r0 = 0
            java.lang.String r1 = "SHA1"
            java.security.MessageDigest r1 = java.security.MessageDigest.getInstance(r1)     // Catch:{ NoSuchAlgorithmException -> 0x0037 }
            goto L_0x0038
        L_0x0037:
            r1 = r0
        L_0x0038:
            f464if = r1
            com.threatmetrix.TrustDefender.internal.PH r1 = com.threatmetrix.TrustDefender.internal.PH.m275do()
            boolean r1 = r1.f494char
            if (r1 != 0) goto L_0x0050
            java.lang.String r1 = f463for
            java.lang.String r2 = "Getting MD5 digest"
            com.threatmetrix.TrustDefender.internal.TL.m338new(r1, r2)
            java.lang.String r1 = "MD5"
            java.security.MessageDigest r1 = java.security.MessageDigest.getInstance(r1)     // Catch:{ NoSuchAlgorithmException -> 0x0050 }
            goto L_0x0051
        L_0x0050:
            r1 = r0
        L_0x0051:
            f465int = r1
            com.threatmetrix.TrustDefender.internal.PH r1 = com.threatmetrix.TrustDefender.internal.PH.m275do()
            boolean r1 = r1.f494char
            if (r1 != 0) goto L_0x0069
            java.lang.String r1 = f463for
            java.lang.String r2 = "Getting SHA256 digest"
            com.threatmetrix.TrustDefender.internal.TL.m338new(r1, r2)
            java.lang.String r1 = "SHA-256"
            java.security.MessageDigest r1 = java.security.MessageDigest.getInstance(r1)     // Catch:{ NoSuchAlgorithmException -> 0x0069 }
            r0 = r1
        L_0x0069:
            f462do = r0
            java.security.SecureRandom r0 = new java.security.SecureRandom
            r0.<init>()
            f460case = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.NK.<clinit>():void");
    }

    /* renamed from: if reason: not valid java name */
    static String m214if(byte[] bArr) {
        String str;
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        if (PH.m275do().f494char) {
            return PH.m275do().m282do(bArr);
        }
        if (f464if == null) {
            return null;
        }
        synchronized (f464if) {
            f464if.update(bArr);
            byte[] digest = f464if.digest();
            f464if.reset();
            str = m210do(digest);
        }
        return str;
    }

    /* renamed from: new reason: not valid java name */
    static String m224new(byte[] bArr) {
        String str;
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        if (PH.m275do().f494char) {
            return PH.m275do().m298int(bArr);
        }
        if (f462do == null) {
            return null;
        }
        synchronized (f462do) {
            f462do.update(bArr);
            byte[] digest = f462do.digest();
            f462do.reset();
            str = m210do(digest);
        }
        return str;
    }

    /* renamed from: do reason: not valid java name */
    public static String m210do(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i] & 255;
            int i2 = i * 2;
            cArr[i2] = f466new[b >>> 4];
            cArr[i2 + 1] = f466new[b & 15];
        }
        return new String(cArr);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002a, code lost:
        return 13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002d, code lost:
        return 12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0030, code lost:
        return 11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0033, code lost:
        return 10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0024, code lost:
        return 15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0027, code lost:
        return 14;
     */
    /* renamed from: new reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte m222new(char r3) throws java.lang.IllegalArgumentException {
        /*
            switch(r3) {
                case 48: goto L_0x0048;
                case 49: goto L_0x0046;
                case 50: goto L_0x0044;
                case 51: goto L_0x0042;
                case 52: goto L_0x0040;
                case 53: goto L_0x003e;
                case 54: goto L_0x003c;
                case 55: goto L_0x003a;
                case 56: goto L_0x0037;
                case 57: goto L_0x0034;
                default: goto L_0x0003;
            }
        L_0x0003:
            switch(r3) {
                case 65: goto L_0x0031;
                case 66: goto L_0x002e;
                case 67: goto L_0x002b;
                case 68: goto L_0x0028;
                case 69: goto L_0x0025;
                case 70: goto L_0x0022;
                default: goto L_0x0006;
            }
        L_0x0006:
            switch(r3) {
                case 97: goto L_0x0031;
                case 98: goto L_0x002e;
                case 99: goto L_0x002b;
                case 100: goto L_0x0028;
                case 101: goto L_0x0025;
                case 102: goto L_0x0022;
                default: goto L_0x0009;
            }
        L_0x0009:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "\""
            r1.<init>(r2)
            r1.append(r3)
            java.lang.String r3 = "\" is not a valid hexidecimal character"
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            r0.<init>(r3)
            throw r0
        L_0x0022:
            r3 = 15
            return r3
        L_0x0025:
            r3 = 14
            return r3
        L_0x0028:
            r3 = 13
            return r3
        L_0x002b:
            r3 = 12
            return r3
        L_0x002e:
            r3 = 11
            return r3
        L_0x0031:
            r3 = 10
            return r3
        L_0x0034:
            r3 = 9
            return r3
        L_0x0037:
            r3 = 8
            return r3
        L_0x003a:
            r3 = 7
            return r3
        L_0x003c:
            r3 = 6
            return r3
        L_0x003e:
            r3 = 5
            return r3
        L_0x0040:
            r3 = 4
            return r3
        L_0x0042:
            r3 = 3
            return r3
        L_0x0044:
            r3 = 2
            return r3
        L_0x0046:
            r3 = 1
            return r3
        L_0x0048:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.NK.m222new(char):byte");
    }

    /* renamed from: for reason: not valid java name */
    public static byte[] m213for(String str) throws IllegalArgumentException {
        int length = str.length() / 2;
        if (length * 2 != str.length()) {
            StringBuilder sb = new StringBuilder("\"");
            sb.append(str);
            sb.append("\" has an odd number of characters");
            throw new IllegalArgumentException(sb.toString());
        }
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (m222new(str.charAt(i2 + 1)) | (m222new(str.charAt(i2)) << 4));
        }
        return bArr;
    }

    /* renamed from: int reason: not valid java name */
    static String m219int(byte[] bArr) {
        char[] cArr = new char[((bArr.length * 3) - 1)];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i] & 255;
            int i2 = i * 3;
            cArr[i2] = f466new[b >>> 4];
            cArr[i2 + 1] = f466new[b & 15];
            if (i < bArr.length - 1) {
                cArr[i2 + 2] = ':';
            }
        }
        return new String(cArr);
    }

    /* renamed from: for reason: not valid java name */
    static String m212for(String str, String str2) {
        if (PH.m275do().f494char) {
            return PH.m275do().m286for(str, str2);
        }
        if (str == null || str.length() > 9999 || str2 == null) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes("UTF-8");
            byte[] bytes2 = str2.getBytes("UTF-8");
            StringBuilder sb = new StringBuilder();
            sb.append(bytes.length);
            sb.append("&");
            byte[] bytes3 = sb.toString().getBytes("UTF-8");
            byte[] bArr = new byte[(bytes.length + bytes3.length)];
            int length = bytes2.length;
            int length2 = bytes3.length;
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (i < length2) {
                int i4 = i2 + 1;
                int i5 = i3 + 1;
                bArr[i2] = (byte) ((bytes2[i3] & 10) ^ bytes3[i]);
                i3 = i5 >= length ? 0 : i5;
                i++;
                i2 = i4;
            }
            int length3 = bytes.length;
            int i6 = 0;
            while (i6 < length3) {
                int i7 = i2 + 1;
                int i8 = i3 + 1;
                bArr[i2] = (byte) (bytes[i6] ^ (bytes2[i3] & 10));
                i3 = i8 >= length ? 0 : i8;
                i6++;
                i2 = i7;
            }
            return m210do(bArr);
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    /* renamed from: do reason: not valid java name */
    static String m206do() {
        return m207do(32);
    }

    /* renamed from: int reason: not valid java name */
    static List<String> m220int(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        while (true) {
            int indexOf = str.indexOf(str2);
            if (indexOf == -1) {
                break;
            }
            if (indexOf > 0) {
                arrayList.add(str.substring(0, indexOf));
            }
            str = str.substring(indexOf + str2.length());
        }
        if (!str.isEmpty()) {
            arrayList.add(str);
        }
        return arrayList;
    }

    /* renamed from: int reason: not valid java name */
    static String m217int(List<String> list, String str) {
        return m218int(list, str, false);
    }

    /* renamed from: int reason: not valid java name */
    static String m218int(List<String> list, String str, boolean z) {
        StringBuilder sb = new StringBuilder();
        for (String str2 : list) {
            if (!str2.isEmpty()) {
                if (sb.length() > 0) {
                    sb.append(str);
                }
                sb.append(str2);
            }
        }
        if (z && sb.length() > 0) {
            sb.append(str);
        }
        return sb.toString();
    }

    /* renamed from: if reason: not valid java name */
    public static boolean m215if(String str) {
        return str == null || str.isEmpty();
    }

    /* renamed from: byte reason: not valid java name */
    static boolean m203byte(String str) {
        return str != null && !str.isEmpty();
    }

    /* renamed from: try reason: not valid java name */
    static String m226try(String str) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (int i2 = 0; i2 < str.length() && i < 128; i2++) {
            char charAt = str.charAt(i2);
            if ((charAt >= '0' && charAt <= '9') || ((charAt >= 'a' && charAt <= 'z') || charAt == '_' || charAt == '/' || charAt == '-')) {
                sb.append(charAt);
                i++;
            } else if (charAt >= 'A' && charAt <= 'Z') {
                sb.append(Character.toLowerCase(charAt));
                i++;
            }
        }
        return sb.toString();
    }

    /* renamed from: char reason: not valid java name */
    public static String m205char(String str) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (int i2 = 0; i2 < str.length() && i < 128; i2++) {
            char charAt = str.charAt(i2);
            if ((charAt >= '0' && charAt <= '9') || ((charAt >= 'a' && charAt <= 'z') || ((charAt >= 'A' && charAt <= 'Z') || charAt == '_' || charAt == '/' || charAt == '-'))) {
                sb.append(charAt);
                i++;
            }
        }
        return sb.toString();
    }

    /* renamed from: do reason: not valid java name */
    public static String m207do(int i) {
        if (PH.m275do().f494char) {
            String str = PH.m275do().m279do(i);
            if (str != null) {
                return str;
            }
        }
        byte[] bArr = new byte[((i + 1) / 2)];
        f460case.nextBytes(bArr);
        String str2 = m210do(bArr);
        if (str2.length() <= i) {
            return str2;
        }
        return str2.substring(0, i);
    }

    /* renamed from: do reason: not valid java name */
    static String m209do(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        if (map.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder("{\"");
        boolean z = true;
        for (Entry entry : map.entrySet()) {
            if (z) {
                z = false;
            } else {
                sb.append("\",\"");
            }
            sb.append((String) entry.getKey());
            sb.append("\":\"");
            sb.append((String) entry.getValue());
        }
        sb.append("\"}");
        return sb.toString();
    }

    /* renamed from: new reason: not valid java name */
    public static String m223new(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        if (PH.m275do().f494char) {
            return PH.m275do().m302new(str);
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    /* renamed from: do reason: not valid java name */
    static String m208do(String str) {
        String str2;
        if (str == null || str.isEmpty()) {
            return null;
        }
        if (PH.m275do().f494char) {
            return PH.m275do().m281do(str);
        }
        if (f465int == null) {
            return null;
        }
        synchronized (f465int) {
            f465int.update(str.getBytes());
            byte[] digest = f465int.digest();
            f465int.reset();
            str2 = m210do(digest);
        }
        return str2;
    }

    /* renamed from: break reason: not valid java name */
    private static String m202break(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        try {
            return m214if(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    /* renamed from: int reason: not valid java name */
    static String m216int(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        try {
            return m224new(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    /* renamed from: case reason: not valid java name */
    static String m204case(String str) {
        if (str != null && !str.isEmpty()) {
            return m202break(str);
        }
        return null;
    }

    /* renamed from: new reason: not valid java name */
    static void m225new(String str, boolean z, String str2, Map<String, String> map) {
        boolean z2 = false;
        if (z) {
            if (str != null && !str.isEmpty()) {
                str = m202break(str);
            } else {
                str = null;
            }
        }
        if (str != null && !str.isEmpty()) {
            z2 = true;
        }
        if (z2) {
            map.put(str2, str);
        }
    }

    /* renamed from: else reason: not valid java name */
    static String m211else(String str) {
        byte[] bytes;
        if (str == null || str.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : str.getBytes(Charset.forName("UTF8"))) {
            if (b < 32) {
                sb.append("\\x");
                sb.append(String.format("%02x", new Object[]{Byte.valueOf(b)}));
            } else {
                sb.append((char) b);
            }
        }
        return sb.toString();
    }

    /* renamed from: long reason: not valid java name */
    static boolean m221long(String str) {
        return (str != null && !str.isEmpty()) && f459byte.matcher(str).find();
    }

    /* renamed from: void reason: not valid java name */
    static boolean m227void(String str) {
        return (str != null && !str.isEmpty()) && f467try.matcher(str).find() && !f461char.matcher(str).find();
    }
}
