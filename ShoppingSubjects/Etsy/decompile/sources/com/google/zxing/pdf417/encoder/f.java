package com.google.zxing.pdf417.encoder;

import com.google.zxing.WriterException;
import com.google.zxing.common.CharacterSetECI;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;

/* compiled from: PDF417HighLevelEncoder */
final class f {
    private static final byte[] a = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 38, 13, 9, 44, 58, 35, 45, 46, 36, 47, 43, 37, 42, 61, 94, 0, 32, 0, 0, 0};
    private static final byte[] b = {59, 60, 62, 64, 91, 92, 93, 95, 96, 126, 33, 13, 9, 44, 58, 10, 45, 46, 36, 47, 34, 124, 42, 40, 41, 63, 123, 125, 39, 0};
    private static final byte[] c = new byte[128];
    private static final byte[] d = new byte[128];
    private static final Charset e = Charset.forName("ISO-8859-1");

    private static boolean a(char c2) {
        return c2 >= '0' && c2 <= '9';
    }

    private static boolean b(char c2) {
        return c2 == ' ' || (c2 >= 'A' && c2 <= 'Z');
    }

    private static boolean c(char c2) {
        return c2 == ' ' || (c2 >= 'a' && c2 <= 'z');
    }

    private static boolean f(char c2) {
        return c2 == 9 || c2 == 10 || c2 == 13 || (c2 >= ' ' && c2 <= '~');
    }

    static {
        Arrays.fill(c, -1);
        for (int i = 0; i < a.length; i++) {
            byte b2 = a[i];
            if (b2 > 0) {
                c[b2] = (byte) i;
            }
        }
        Arrays.fill(d, -1);
        for (int i2 = 0; i2 < b.length; i2++) {
            byte b3 = b[i2];
            if (b3 > 0) {
                d[b3] = (byte) i2;
            }
        }
    }

    static String a(String str, Compaction compaction, Charset charset) throws WriterException {
        StringBuilder sb = new StringBuilder(str.length());
        if (charset == null) {
            charset = e;
        } else if (!e.equals(charset)) {
            CharacterSetECI characterSetECIByName = CharacterSetECI.getCharacterSetECIByName(charset.name());
            if (characterSetECIByName != null) {
                a(characterSetECIByName.getValue(), sb);
            }
        }
        int length = str.length();
        if (compaction == Compaction.TEXT) {
            a((CharSequence) str, 0, length, sb, 0);
        } else if (compaction == Compaction.BYTE) {
            byte[] bytes = str.getBytes(charset);
            a(bytes, 0, bytes.length, 1, sb);
        } else if (compaction == Compaction.NUMERIC) {
            sb.append(902);
            a(str, 0, length, sb);
        } else {
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (i < length) {
                int a2 = a((CharSequence) str, i);
                if (a2 >= 13) {
                    sb.append(902);
                    i2 = 2;
                    a(str, i, a2, sb);
                    i += a2;
                    i3 = 0;
                } else {
                    int b2 = b(str, i);
                    if (b2 >= 5 || a2 == length) {
                        if (i2 != 0) {
                            sb.append(900);
                            i2 = 0;
                            i3 = 0;
                        }
                        i3 = a((CharSequence) str, i, b2, sb, i3);
                        i += b2;
                    } else {
                        int a3 = a(str, i, charset);
                        if (a3 == 0) {
                            a3 = 1;
                        }
                        int i4 = a3 + i;
                        byte[] bytes2 = str.substring(i, i4).getBytes(charset);
                        if (bytes2.length == 1 && i2 == 0) {
                            a(bytes2, 0, 1, 0, sb);
                        } else {
                            a(bytes2, 0, bytes2.length, i2, sb);
                            i3 = 0;
                            i2 = 1;
                        }
                        i = i4;
                    }
                }
            }
        }
        return sb.toString();
    }

    /* JADX WARNING: type inference failed for: r7v4 */
    /* JADX WARNING: type inference failed for: r7v5, types: [char] */
    /* JADX WARNING: type inference failed for: r7v8, types: [char] */
    /* JADX WARNING: type inference failed for: r7v9 */
    /* JADX WARNING: type inference failed for: r7v10 */
    /* JADX WARNING: type inference failed for: r7v11 */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00d0, code lost:
        r9 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00dc, code lost:
        r9 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00ea, code lost:
        r8 = r8 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ec, code lost:
        if (r8 < r1) goto L_0x0010;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00ee, code lost:
        r0 = r3.length();
        r1 = 0;
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00f4, code lost:
        if (r1 >= r0) goto L_0x0112;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00f8, code lost:
        if ((r1 % 2) == 0) goto L_0x00fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00fa, code lost:
        r8 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00fc, code lost:
        r8 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00fd, code lost:
        if (r8 == false) goto L_0x010b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00ff, code lost:
        r7 = (char) ((r7 * 30) + r3.charAt(r1));
        r2.append(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x010f, code lost:
        r1 = r1 + 1;
        r7 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0113, code lost:
        if ((r0 % 2) == 0) goto L_0x011c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0115, code lost:
        r2.append((char) ((r7 * 30) + 29));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x011c, code lost:
        return r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0120, code lost:
        r9 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x010f, code lost:
        r7 = r3.charAt(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x010f, code lost:
        r7 = r7;
     */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r7v4
      assigns: []
      uses: []
      mth insns count: 110
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/183835416.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$33/170174037.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at jadx.core.ProcessClass$$Lambda$38/2083670723.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1919834117.run(Unknown Source)
     */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int a(java.lang.CharSequence r17, int r18, int r19, java.lang.StringBuilder r20, int r21) {
        /*
            r0 = r17
            r1 = r19
            r2 = r20
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r1)
            r4 = 2
            r6 = 0
            r9 = r21
            r8 = r6
        L_0x0010:
            int r10 = r18 + r8
            char r11 = r0.charAt(r10)
            r12 = 26
            r13 = 32
            r14 = 27
            r15 = 28
            r5 = 29
            switch(r9) {
                case 0: goto L_0x00b4;
                case 1: goto L_0x007b;
                case 2: goto L_0x0033;
                default: goto L_0x0023;
            }
        L_0x0023:
            boolean r10 = e(r11)
            if (r10 == 0) goto L_0x011d
            byte[] r10 = d
            byte r10 = r10[r11]
            char r10 = (char) r10
            r3.append(r10)
            goto L_0x00ea
        L_0x0033:
            boolean r12 = d(r11)
            if (r12 == 0) goto L_0x0043
            byte[] r10 = c
            byte r10 = r10[r11]
            char r10 = (char) r10
            r3.append(r10)
            goto L_0x00ea
        L_0x0043:
            boolean r12 = b(r11)
            if (r12 == 0) goto L_0x004e
            r3.append(r15)
            goto L_0x0120
        L_0x004e:
            boolean r12 = c(r11)
            if (r12 == 0) goto L_0x0059
            r3.append(r14)
            goto L_0x00d0
        L_0x0059:
            int r10 = r10 + 1
            if (r10 >= r1) goto L_0x006e
            char r10 = r0.charAt(r10)
            boolean r10 = e(r10)
            if (r10 == 0) goto L_0x006e
            r9 = 3
            r5 = 25
            r3.append(r5)
            goto L_0x0010
        L_0x006e:
            r3.append(r5)
            byte[] r10 = d
            byte r10 = r10[r11]
            char r10 = (char) r10
            r3.append(r10)
            goto L_0x00ea
        L_0x007b:
            boolean r10 = c(r11)
            if (r10 == 0) goto L_0x008e
            if (r11 != r13) goto L_0x0087
            r3.append(r12)
            goto L_0x00ea
        L_0x0087:
            int r11 = r11 + -97
            char r10 = (char) r11
            r3.append(r10)
            goto L_0x00ea
        L_0x008e:
            boolean r10 = b(r11)
            if (r10 == 0) goto L_0x009e
            r3.append(r14)
            int r11 = r11 + -65
            char r10 = (char) r11
            r3.append(r10)
            goto L_0x00ea
        L_0x009e:
            boolean r10 = d(r11)
            if (r10 == 0) goto L_0x00a8
            r3.append(r15)
            goto L_0x00dc
        L_0x00a8:
            r3.append(r5)
            byte[] r10 = d
            byte r10 = r10[r11]
            char r10 = (char) r10
            r3.append(r10)
            goto L_0x00ea
        L_0x00b4:
            boolean r10 = b(r11)
            if (r10 == 0) goto L_0x00c7
            if (r11 != r13) goto L_0x00c0
            r3.append(r12)
            goto L_0x00ea
        L_0x00c0:
            int r11 = r11 + -65
            char r10 = (char) r11
            r3.append(r10)
            goto L_0x00ea
        L_0x00c7:
            boolean r10 = c(r11)
            if (r10 == 0) goto L_0x00d3
            r3.append(r14)
        L_0x00d0:
            r9 = 1
            goto L_0x0010
        L_0x00d3:
            boolean r10 = d(r11)
            if (r10 == 0) goto L_0x00df
            r3.append(r15)
        L_0x00dc:
            r9 = r4
            goto L_0x0010
        L_0x00df:
            r3.append(r5)
            byte[] r10 = d
            byte r10 = r10[r11]
            char r10 = (char) r10
            r3.append(r10)
        L_0x00ea:
            int r8 = r8 + 1
            if (r8 < r1) goto L_0x0010
            int r0 = r3.length()
            r1 = r6
            r7 = r1
        L_0x00f4:
            if (r1 >= r0) goto L_0x0112
            int r8 = r1 % 2
            if (r8 == 0) goto L_0x00fc
            r8 = 1
            goto L_0x00fd
        L_0x00fc:
            r8 = r6
        L_0x00fd:
            if (r8 == 0) goto L_0x010b
            int r7 = r7 * 30
            char r8 = r3.charAt(r1)
            int r7 = r7 + r8
            char r7 = (char) r7
            r2.append(r7)
            goto L_0x010f
        L_0x010b:
            char r7 = r3.charAt(r1)
        L_0x010f:
            int r1 = r1 + 1
            goto L_0x00f4
        L_0x0112:
            int r0 = r0 % r4
            if (r0 == 0) goto L_0x011c
            int r7 = r7 * 30
            int r7 = r7 + r5
            char r0 = (char) r7
            r2.append(r0)
        L_0x011c:
            return r9
        L_0x011d:
            r3.append(r5)
        L_0x0120:
            r9 = r6
            goto L_0x0010
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.encoder.f.a(java.lang.CharSequence, int, int, java.lang.StringBuilder, int):int");
    }

    private static void a(byte[] bArr, int i, int i2, int i3, StringBuilder sb) {
        int i4;
        int i5 = i2;
        StringBuilder sb2 = sb;
        if (i5 == 1 && i3 == 0) {
            sb2.append(913);
        } else if (i5 % 6 == 0) {
            sb2.append(924);
        } else {
            sb2.append(901);
        }
        if (i5 >= 6) {
            char[] cArr = new char[5];
            i4 = i;
            while ((i + i5) - i4 >= 6) {
                long j = 0;
                int i6 = 0;
                while (i6 < 6) {
                    i6++;
                    j = (j << 8) + ((long) (bArr[i4 + i6] & 255));
                }
                for (int i7 = 0; i7 < 5; i7++) {
                    cArr[i7] = (char) ((int) (j % 900));
                    j /= 900;
                }
                for (int i8 = 4; i8 >= 0; i8--) {
                    sb2.append(cArr[i8]);
                }
                i4 += 6;
            }
        } else {
            i4 = i;
        }
        while (i4 < i + i5) {
            sb2.append((char) (bArr[i4] & 255));
            i4++;
        }
    }

    private static void a(String str, int i, int i2, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder((i2 / 3) + 1);
        BigInteger valueOf = BigInteger.valueOf(900);
        BigInteger valueOf2 = BigInteger.valueOf(0);
        int i3 = 0;
        while (i3 < i2) {
            sb2.setLength(0);
            int min = Math.min(44, i2 - i3);
            StringBuilder sb3 = new StringBuilder("1");
            int i4 = i + i3;
            sb3.append(str.substring(i4, i4 + min));
            BigInteger bigInteger = new BigInteger(sb3.toString());
            do {
                sb2.append((char) bigInteger.mod(valueOf).intValue());
                bigInteger = bigInteger.divide(valueOf);
            } while (!bigInteger.equals(valueOf2));
            for (int length = sb2.length() - 1; length >= 0; length--) {
                sb.append(sb2.charAt(length));
            }
            i3 += min;
        }
    }

    private static boolean d(char c2) {
        return c[c2] != -1;
    }

    private static boolean e(char c2) {
        return d[c2] != -1;
    }

    private static int a(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        if (i < length) {
            char charAt = charSequence.charAt(i);
            while (a(charAt) && i < length) {
                i2++;
                i++;
                if (i < length) {
                    charAt = charSequence.charAt(i);
                }
            }
        }
        return i2;
    }

    private static int b(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = i;
        while (i2 < length) {
            char charAt = charSequence.charAt(i2);
            int i3 = 0;
            while (i3 < 13 && a(charAt) && i2 < length) {
                i3++;
                i2++;
                if (i2 < length) {
                    charAt = charSequence.charAt(i2);
                }
            }
            if (i3 < 13) {
                if (i3 <= 0) {
                    if (!f(charSequence.charAt(i2))) {
                        break;
                    }
                    i2++;
                }
            } else {
                return (i2 - i) - i3;
            }
        }
        return i2 - i;
    }

    private static int a(String str, int i, Charset charset) throws WriterException {
        CharsetEncoder newEncoder = charset.newEncoder();
        int length = str.length();
        int i2 = i;
        while (i2 < length) {
            char charAt = str.charAt(i2);
            int i3 = 0;
            while (i3 < 13 && a(charAt)) {
                i3++;
                int i4 = i2 + i3;
                if (i4 >= length) {
                    break;
                }
                charAt = str.charAt(i4);
            }
            if (i3 >= 13) {
                return i2 - i;
            }
            char charAt2 = str.charAt(i2);
            if (!newEncoder.canEncode(charAt2)) {
                StringBuilder sb = new StringBuilder("Non-encodable character detected: ");
                sb.append(charAt2);
                sb.append(" (Unicode: ");
                sb.append(charAt2);
                sb.append(')');
                throw new WriterException(sb.toString());
            }
            i2++;
        }
        return i2 - i;
    }

    private static void a(int i, StringBuilder sb) throws WriterException {
        if (i >= 0 && i < 900) {
            sb.append(927);
            sb.append((char) i);
        } else if (i < 810900) {
            sb.append(926);
            sb.append((char) ((i / 900) - 1));
            sb.append((char) (i % 900));
        } else if (i < 811800) {
            sb.append(925);
            sb.append((char) (810900 - i));
        } else {
            StringBuilder sb2 = new StringBuilder("ECI number not in valid range from 0..811799, but was ");
            sb2.append(i);
            throw new WriterException(sb2.toString());
        }
    }
}
