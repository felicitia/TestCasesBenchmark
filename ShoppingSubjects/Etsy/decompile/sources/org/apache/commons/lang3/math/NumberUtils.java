package org.apache.commons.lang3.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.apache.commons.lang3.StringUtils;

public class NumberUtils {
    public static final Byte BYTE_MINUS_ONE = Byte.valueOf(-1);
    public static final Byte BYTE_ONE = Byte.valueOf(1);
    public static final Byte BYTE_ZERO = Byte.valueOf(0);
    public static final Double DOUBLE_MINUS_ONE = Double.valueOf(-1.0d);
    public static final Double DOUBLE_ONE = Double.valueOf(1.0d);
    public static final Double DOUBLE_ZERO = Double.valueOf(0.0d);
    public static final Float FLOAT_MINUS_ONE = Float.valueOf(-1.0f);
    public static final Float FLOAT_ONE = Float.valueOf(1.0f);
    public static final Float FLOAT_ZERO = Float.valueOf(0.0f);
    public static final Integer INTEGER_MINUS_ONE = Integer.valueOf(-1);
    public static final Integer INTEGER_ONE = Integer.valueOf(1);
    public static final Integer INTEGER_ZERO = Integer.valueOf(0);
    public static final Long LONG_MINUS_ONE = Long.valueOf(-1);
    public static final Long LONG_ONE = Long.valueOf(1);
    public static final Long LONG_ZERO = Long.valueOf(0);
    public static final Short SHORT_MINUS_ONE = Short.valueOf(-1);
    public static final Short SHORT_ONE = Short.valueOf(1);
    public static final Short SHORT_ZERO = Short.valueOf(0);

    public static byte max(byte b, byte b2, byte b3) {
        if (b2 > b) {
            b = b2;
        }
        return b3 > b ? b3 : b;
    }

    public static int max(int i, int i2, int i3) {
        if (i2 > i) {
            i = i2;
        }
        return i3 > i ? i3 : i;
    }

    public static long max(long j, long j2, long j3) {
        if (j2 > j) {
            j = j2;
        }
        return j3 > j ? j3 : j;
    }

    public static short max(short s, short s2, short s3) {
        if (s2 > s) {
            s = s2;
        }
        return s3 > s ? s3 : s;
    }

    public static byte min(byte b, byte b2, byte b3) {
        if (b2 < b) {
            b = b2;
        }
        return b3 < b ? b3 : b;
    }

    public static int min(int i, int i2, int i3) {
        if (i2 < i) {
            i = i2;
        }
        return i3 < i ? i3 : i;
    }

    public static long min(long j, long j2, long j3) {
        if (j2 < j) {
            j = j2;
        }
        return j3 < j ? j3 : j;
    }

    public static short min(short s, short s2, short s3) {
        if (s2 < s) {
            s = s2;
        }
        return s3 < s ? s3 : s;
    }

    public static int toInt(String str) {
        return toInt(str, 0);
    }

    public static int toInt(String str, int i) {
        if (str == null) {
            return i;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static long toLong(String str) {
        return toLong(str, 0);
    }

    public static long toLong(String str, long j) {
        if (str == null) {
            return j;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return j;
        }
    }

    public static float toFloat(String str) {
        return toFloat(str, 0.0f);
    }

    public static float toFloat(String str, float f) {
        if (str == null) {
            return f;
        }
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException unused) {
            return f;
        }
    }

    public static double toDouble(String str) {
        return toDouble(str, 0.0d);
    }

    public static double toDouble(String str, double d) {
        if (str == null) {
            return d;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException unused) {
            return d;
        }
    }

    public static byte toByte(String str) {
        return toByte(str, 0);
    }

    public static byte toByte(String str, byte b) {
        if (str == null) {
            return b;
        }
        try {
            return Byte.parseByte(str);
        } catch (NumberFormatException unused) {
            return b;
        }
    }

    public static short toShort(String str) {
        return toShort(str, 0);
    }

    public static short toShort(String str, short s) {
        if (str == null) {
            return s;
        }
        try {
            return Short.parseShort(str);
        } catch (NumberFormatException unused) {
            return s;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:115|(1:119)|120|121|(1:127)|128|129|(1:135)|136|138) */
    /* JADX WARNING: Can't wrap try/catch for region: R(11:(1:43)|44|(1:49)(1:48)|50|(5:52|(3:54|(2:56|(2:58|(1:60)))|(2:76|77)(3:70|71|72))|78|79|(1:85))|86|87|(1:93)|94|95|96) */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x01ab, code lost:
        return createLong(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x01b0, code lost:
        return createBigInteger(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0105, code lost:
        if (r1 != 'l') goto L_0x0175;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0175, code lost:
        r1 = new java.lang.StringBuilder();
        r1.append(r13);
        r1.append(" is not a valid number.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x018b, code lost:
        throw new java.lang.NumberFormatException(r1.toString());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:109:0x01a7 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:128:0x01d4 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:86:0x015a */
    /* JADX WARNING: Missing exception handler attribute for start block: B:94:0x0170 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Number createNumber(java.lang.String r13) throws java.lang.NumberFormatException {
        /*
            r0 = 0
            if (r13 != 0) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = org.apache.commons.lang3.StringUtils.isBlank(r13)
            if (r1 == 0) goto L_0x0012
            java.lang.NumberFormatException r13 = new java.lang.NumberFormatException
            java.lang.String r0 = "A blank string is not a valid number"
            r13.<init>(r0)
            throw r13
        L_0x0012:
            java.lang.String r1 = "--"
            boolean r1 = r13.startsWith(r1)
            if (r1 == 0) goto L_0x001b
            return r0
        L_0x001b:
            java.lang.String r1 = "0x"
            boolean r1 = r13.startsWith(r1)
            if (r1 != 0) goto L_0x01ee
            java.lang.String r1 = "-0x"
            boolean r1 = r13.startsWith(r1)
            if (r1 != 0) goto L_0x01ee
            java.lang.String r1 = "0X"
            boolean r1 = r13.startsWith(r1)
            if (r1 != 0) goto L_0x01ee
            java.lang.String r1 = "-0X"
            boolean r1 = r13.startsWith(r1)
            if (r1 == 0) goto L_0x003d
            goto L_0x01ee
        L_0x003d:
            int r1 = r13.length()
            r2 = 1
            int r1 = r1 - r2
            char r1 = r13.charAt(r1)
            r3 = 46
            int r4 = r13.indexOf(r3)
            r5 = 101(0x65, float:1.42E-43)
            int r5 = r13.indexOf(r5)
            r6 = 69
            int r6 = r13.indexOf(r6)
            int r5 = r5 + r6
            int r5 = r5 + r2
            r6 = -1
            r7 = 0
            if (r4 <= r6) goto L_0x0093
            if (r5 <= r6) goto L_0x0088
            if (r5 < r4) goto L_0x0071
            int r8 = r13.length()
            if (r5 <= r8) goto L_0x006a
            goto L_0x0071
        L_0x006a:
            int r8 = r4 + 1
            java.lang.String r8 = r13.substring(r8, r5)
            goto L_0x008e
        L_0x0071:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r13)
            java.lang.String r13 = " is not a valid number."
            r1.append(r13)
            java.lang.String r13 = r1.toString()
            r0.<init>(r13)
            throw r0
        L_0x0088:
            int r8 = r4 + 1
            java.lang.String r8 = r13.substring(r8)
        L_0x008e:
            java.lang.String r4 = r13.substring(r7, r4)
            goto L_0x00b9
        L_0x0093:
            if (r5 <= r6) goto L_0x00b7
            int r4 = r13.length()
            if (r5 <= r4) goto L_0x00b2
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r13)
            java.lang.String r13 = " is not a valid number."
            r1.append(r13)
            java.lang.String r13 = r1.toString()
            r0.<init>(r13)
            throw r0
        L_0x00b2:
            java.lang.String r4 = r13.substring(r7, r5)
            goto L_0x00b8
        L_0x00b7:
            r4 = r13
        L_0x00b8:
            r8 = r0
        L_0x00b9:
            boolean r9 = java.lang.Character.isDigit(r1)
            r10 = 0
            r12 = 0
            if (r9 != 0) goto L_0x018c
            if (r1 == r3) goto L_0x018c
            if (r5 <= r6) goto L_0x00d7
            int r3 = r13.length()
            int r3 = r3 - r2
            if (r5 >= r3) goto L_0x00d7
            int r5 = r5 + r2
            int r0 = r13.length()
            int r0 = r0 - r2
            java.lang.String r0 = r13.substring(r5, r0)
        L_0x00d7:
            int r3 = r13.length()
            int r3 = r3 - r2
            java.lang.String r3 = r13.substring(r7, r3)
            boolean r4 = isAllZeros(r4)
            if (r4 == 0) goto L_0x00ee
            boolean r4 = isAllZeros(r0)
            if (r4 == 0) goto L_0x00ee
            r4 = r2
            goto L_0x00ef
        L_0x00ee:
            r4 = r7
        L_0x00ef:
            r5 = 68
            if (r1 == r5) goto L_0x015a
            r5 = 70
            if (r1 == r5) goto L_0x0145
            r5 = 76
            if (r1 == r5) goto L_0x0108
            r5 = 100
            if (r1 == r5) goto L_0x015a
            r5 = 102(0x66, float:1.43E-43)
            if (r1 == r5) goto L_0x0145
            r4 = 108(0x6c, float:1.51E-43)
            if (r1 == r4) goto L_0x0108
            goto L_0x0175
        L_0x0108:
            if (r8 != 0) goto L_0x012e
            if (r0 != 0) goto L_0x012e
            char r0 = r3.charAt(r7)
            r1 = 45
            if (r0 != r1) goto L_0x011e
            java.lang.String r0 = r3.substring(r2)
            boolean r0 = isDigits(r0)
            if (r0 != 0) goto L_0x0124
        L_0x011e:
            boolean r0 = isDigits(r3)
            if (r0 == 0) goto L_0x012e
        L_0x0124:
            java.lang.Long r13 = createLong(r3)     // Catch:{ NumberFormatException -> 0x0129 }
            return r13
        L_0x0129:
            java.math.BigInteger r13 = createBigInteger(r3)
            return r13
        L_0x012e:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r13)
            java.lang.String r13 = " is not a valid number."
            r1.append(r13)
            java.lang.String r13 = r1.toString()
            r0.<init>(r13)
            throw r0
        L_0x0145:
            java.lang.Float r0 = createFloat(r3)     // Catch:{ NumberFormatException -> 0x015a }
            boolean r1 = r0.isInfinite()     // Catch:{ NumberFormatException -> 0x015a }
            if (r1 != 0) goto L_0x015a
            float r1 = r0.floatValue()     // Catch:{ NumberFormatException -> 0x015a }
            int r1 = (r1 > r12 ? 1 : (r1 == r12 ? 0 : -1))
            if (r1 != 0) goto L_0x0159
            if (r4 == 0) goto L_0x015a
        L_0x0159:
            return r0
        L_0x015a:
            java.lang.Double r0 = createDouble(r3)     // Catch:{ NumberFormatException -> 0x0170 }
            boolean r1 = r0.isInfinite()     // Catch:{ NumberFormatException -> 0x0170 }
            if (r1 != 0) goto L_0x0170
            float r1 = r0.floatValue()     // Catch:{ NumberFormatException -> 0x0170 }
            double r1 = (double) r1
            int r5 = (r1 > r10 ? 1 : (r1 == r10 ? 0 : -1))
            if (r5 != 0) goto L_0x016f
            if (r4 == 0) goto L_0x0170
        L_0x016f:
            return r0
        L_0x0170:
            java.math.BigDecimal r0 = createBigDecimal(r3)     // Catch:{ NumberFormatException -> 0x0175 }
            return r0
        L_0x0175:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r13)
            java.lang.String r13 = " is not a valid number."
            r1.append(r13)
            java.lang.String r13 = r1.toString()
            r0.<init>(r13)
            throw r0
        L_0x018c:
            if (r5 <= r6) goto L_0x019e
            int r1 = r13.length()
            int r1 = r1 - r2
            if (r5 >= r1) goto L_0x019e
            int r5 = r5 + r2
            int r0 = r13.length()
            java.lang.String r0 = r13.substring(r5, r0)
        L_0x019e:
            if (r8 != 0) goto L_0x01b1
            if (r0 != 0) goto L_0x01b1
            java.lang.Integer r0 = createInteger(r13)     // Catch:{ NumberFormatException -> 0x01a7 }
            return r0
        L_0x01a7:
            java.lang.Long r0 = createLong(r13)     // Catch:{ NumberFormatException -> 0x01ac }
            return r0
        L_0x01ac:
            java.math.BigInteger r13 = createBigInteger(r13)
            return r13
        L_0x01b1:
            boolean r1 = isAllZeros(r4)
            if (r1 == 0) goto L_0x01be
            boolean r0 = isAllZeros(r0)
            if (r0 == 0) goto L_0x01be
            goto L_0x01bf
        L_0x01be:
            r2 = r7
        L_0x01bf:
            java.lang.Float r0 = createFloat(r13)     // Catch:{ NumberFormatException -> 0x01d4 }
            boolean r1 = r0.isInfinite()     // Catch:{ NumberFormatException -> 0x01d4 }
            if (r1 != 0) goto L_0x01d4
            float r1 = r0.floatValue()     // Catch:{ NumberFormatException -> 0x01d4 }
            int r1 = (r1 > r12 ? 1 : (r1 == r12 ? 0 : -1))
            if (r1 != 0) goto L_0x01d3
            if (r2 == 0) goto L_0x01d4
        L_0x01d3:
            return r0
        L_0x01d4:
            java.lang.Double r0 = createDouble(r13)     // Catch:{ NumberFormatException -> 0x01e9 }
            boolean r1 = r0.isInfinite()     // Catch:{ NumberFormatException -> 0x01e9 }
            if (r1 != 0) goto L_0x01e9
            double r3 = r0.doubleValue()     // Catch:{ NumberFormatException -> 0x01e9 }
            int r1 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r1 != 0) goto L_0x01e8
            if (r2 == 0) goto L_0x01e9
        L_0x01e8:
            return r0
        L_0x01e9:
            java.math.BigDecimal r13 = createBigDecimal(r13)
            return r13
        L_0x01ee:
            java.lang.Integer r13 = createInteger(r13)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.math.NumberUtils.createNumber(java.lang.String):java.lang.Number");
    }

    private static boolean isAllZeros(String str) {
        boolean z = true;
        if (str == null) {
            return true;
        }
        for (int length = str.length() - 1; length >= 0; length--) {
            if (str.charAt(length) != '0') {
                return false;
            }
        }
        if (str.length() <= 0) {
            z = false;
        }
        return z;
    }

    public static Float createFloat(String str) {
        if (str == null) {
            return null;
        }
        return Float.valueOf(str);
    }

    public static Double createDouble(String str) {
        if (str == null) {
            return null;
        }
        return Double.valueOf(str);
    }

    public static Integer createInteger(String str) {
        if (str == null) {
            return null;
        }
        return Integer.decode(str);
    }

    public static Long createLong(String str) {
        if (str == null) {
            return null;
        }
        return Long.decode(str);
    }

    public static BigInteger createBigInteger(String str) {
        if (str == null) {
            return null;
        }
        return new BigInteger(str);
    }

    public static BigDecimal createBigDecimal(String str) {
        if (str == null) {
            return null;
        }
        if (!StringUtils.isBlank(str)) {
            return new BigDecimal(str);
        }
        throw new NumberFormatException("A blank string is not a valid number");
    }

    public static long min(long[] jArr) {
        if (jArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (jArr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        } else {
            long j = jArr[0];
            for (int i = 1; i < jArr.length; i++) {
                if (jArr[i] < j) {
                    j = jArr[i];
                }
            }
            return j;
        }
    }

    public static int min(int[] iArr) {
        if (iArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (iArr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        } else {
            int i = iArr[0];
            for (int i2 = 1; i2 < iArr.length; i2++) {
                if (iArr[i2] < i) {
                    i = iArr[i2];
                }
            }
            return i;
        }
    }

    public static short min(short[] sArr) {
        if (sArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (sArr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        } else {
            short s = sArr[0];
            for (int i = 1; i < sArr.length; i++) {
                if (sArr[i] < s) {
                    s = sArr[i];
                }
            }
            return s;
        }
    }

    public static byte min(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (bArr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        } else {
            byte b = bArr[0];
            for (int i = 1; i < bArr.length; i++) {
                if (bArr[i] < b) {
                    b = bArr[i];
                }
            }
            return b;
        }
    }

    public static double min(double[] dArr) {
        if (dArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (dArr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        } else {
            double d = dArr[0];
            for (int i = 1; i < dArr.length; i++) {
                if (Double.isNaN(dArr[i])) {
                    return Double.NaN;
                }
                if (dArr[i] < d) {
                    d = dArr[i];
                }
            }
            return d;
        }
    }

    public static float min(float[] fArr) {
        if (fArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (fArr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        } else {
            float f = fArr[0];
            for (int i = 1; i < fArr.length; i++) {
                if (Float.isNaN(fArr[i])) {
                    return Float.NaN;
                }
                if (fArr[i] < f) {
                    f = fArr[i];
                }
            }
            return f;
        }
    }

    public static long max(long[] jArr) {
        if (jArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (jArr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        } else {
            long j = jArr[0];
            for (int i = 1; i < jArr.length; i++) {
                if (jArr[i] > j) {
                    j = jArr[i];
                }
            }
            return j;
        }
    }

    public static int max(int[] iArr) {
        if (iArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (iArr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        } else {
            int i = iArr[0];
            for (int i2 = 1; i2 < iArr.length; i2++) {
                if (iArr[i2] > i) {
                    i = iArr[i2];
                }
            }
            return i;
        }
    }

    public static short max(short[] sArr) {
        if (sArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (sArr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        } else {
            short s = sArr[0];
            for (int i = 1; i < sArr.length; i++) {
                if (sArr[i] > s) {
                    s = sArr[i];
                }
            }
            return s;
        }
    }

    public static byte max(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (bArr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        } else {
            byte b = bArr[0];
            for (int i = 1; i < bArr.length; i++) {
                if (bArr[i] > b) {
                    b = bArr[i];
                }
            }
            return b;
        }
    }

    public static double max(double[] dArr) {
        if (dArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (dArr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        } else {
            double d = dArr[0];
            for (int i = 1; i < dArr.length; i++) {
                if (Double.isNaN(dArr[i])) {
                    return Double.NaN;
                }
                if (dArr[i] > d) {
                    d = dArr[i];
                }
            }
            return d;
        }
    }

    public static float max(float[] fArr) {
        if (fArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (fArr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        } else {
            float f = fArr[0];
            for (int i = 1; i < fArr.length; i++) {
                if (Float.isNaN(fArr[i])) {
                    return Float.NaN;
                }
                if (fArr[i] > f) {
                    f = fArr[i];
                }
            }
            return f;
        }
    }

    public static double min(double d, double d2, double d3) {
        return Math.min(Math.min(d, d2), d3);
    }

    public static float min(float f, float f2, float f3) {
        return Math.min(Math.min(f, f2), f3);
    }

    public static double max(double d, double d2, double d3) {
        return Math.max(Math.max(d, d2), d3);
    }

    public static float max(float f, float f2, float f3) {
        return Math.max(Math.max(f, f2), f3);
    }

    public static boolean isDigits(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNumber(String str) {
        char c;
        boolean z = false;
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        char[] charArray = str.toCharArray();
        boolean z2 = true;
        int length = charArray.length;
        int i = charArray[0] == '-' ? 1 : 0;
        int i2 = i + 1;
        if (length > i2 && charArray[i] == '0' && charArray[i2] == 'x') {
            int i3 = i + 2;
            if (i3 == length) {
                return false;
            }
            while (i3 < charArray.length) {
                if ((charArray[i3] < '0' || charArray[i3] > '9') && ((charArray[i3] < 'a' || charArray[i3] > 'f') && (charArray[i3] < 'A' || charArray[i3] > 'F'))) {
                    return false;
                }
                i3++;
            }
            return true;
        }
        int i4 = length - 1;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        while (true) {
            if (i >= i4) {
                if (i >= i4 + 1 || !z3 || z4) {
                }
            }
            if (charArray[i] >= '0' && charArray[i] <= '9') {
                z3 = false;
                c = '-';
                z4 = true;
            } else if (charArray[i] == '.') {
                if (z5 || z6) {
                    return false;
                }
                c = '-';
                z5 = true;
            } else if (charArray[i] == 'e' || charArray[i] == 'E') {
                c = '-';
                if (z6 || !z4) {
                    return false;
                }
                z3 = true;
                z6 = true;
            } else {
                if (charArray[i] != '+') {
                    c = '-';
                    if (charArray[i] != '-') {
                        return false;
                    }
                } else {
                    c = '-';
                }
                if (!z3) {
                    return false;
                }
                z3 = false;
                z4 = false;
            }
            i++;
            char c2 = c;
            z2 = true;
        }
        if (i >= charArray.length) {
            if (!z3 && z4) {
                z = z2;
            }
            return z;
        } else if (charArray[i] >= '0' && charArray[i] <= '9') {
            return z2;
        } else {
            if (charArray[i] == 'e' || charArray[i] == 'E') {
                return false;
            }
            if (charArray[i] == '.') {
                if (z5 || z6) {
                    return false;
                }
                return z4;
            } else if (!z3 && (charArray[i] == 'd' || charArray[i] == 'D' || charArray[i] == 'f' || charArray[i] == 'F')) {
                return z4;
            } else {
                if (charArray[i] != 'l' && charArray[i] != 'L') {
                    return false;
                }
                if (z4 && !z6 && !z5) {
                    z = z2;
                }
                return z;
            }
        }
    }
}
