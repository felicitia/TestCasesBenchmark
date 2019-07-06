package org.apache.commons.math3.util;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public final class ArithmeticUtils {
    static final long[] FACTORIALS = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600, 6227020800L, 87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L, 2432902008176640000L};
    static final AtomicReference<long[][]> STIRLING_S2 = new AtomicReference<>(null);

    public static boolean isPowerOfTwo(long j) {
        return j > 0 && (j & (j - 1)) == 0;
    }

    private ArithmeticUtils() {
    }

    public static int addAndCheck(int i, int i2) throws MathArithmeticException {
        long j = ((long) i) + ((long) i2);
        if (j >= -2147483648L && j <= 2147483647L) {
            return (int) j;
        }
        throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_ADDITION, Integer.valueOf(i), Integer.valueOf(i2));
    }

    public static long addAndCheck(long j, long j2) throws MathArithmeticException {
        return addAndCheck(j, j2, LocalizedFormats.OVERFLOW_IN_ADDITION);
    }

    public static long binomialCoefficient(int i, int i2) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        checkBinomial(i, i2);
        long j = 1;
        if (i == i2 || i2 == 0) {
            return 1;
        }
        if (i2 == 1 || i2 == i - 1) {
            return (long) i;
        }
        if (i2 > i / 2) {
            return binomialCoefficient(i, i - i2);
        }
        if (i <= 61) {
            int i3 = (i - i2) + 1;
            for (int i4 = 1; i4 <= i2; i4++) {
                j = (j * ((long) i3)) / ((long) i4);
                i3++;
            }
        } else if (i <= 66) {
            long j2 = 1;
            int i5 = (i - i2) + 1;
            for (int i6 = 1; i6 <= i2; i6++) {
                long gcd = (long) gcd(i5, i6);
                j2 = (j2 / (((long) i6) / gcd)) * (((long) i5) / gcd);
                i5++;
            }
            j = j2;
        } else {
            int i7 = (i - i2) + 1;
            for (int i8 = 1; i8 <= i2; i8++) {
                long gcd2 = (long) gcd(i7, i8);
                j = mulAndCheck(j / (((long) i8) / gcd2), ((long) i7) / gcd2);
                i7++;
            }
        }
        return j;
    }

    public static double binomialCoefficientDouble(int i, int i2) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        checkBinomial(i, i2);
        double d = 1.0d;
        if (i == i2 || i2 == 0) {
            return 1.0d;
        }
        if (i2 == 1 || i2 == i - 1) {
            return (double) i;
        }
        if (i2 > i / 2) {
            return binomialCoefficientDouble(i, i - i2);
        }
        if (i < 67) {
            return (double) binomialCoefficient(i, i2);
        }
        for (int i3 = 1; i3 <= i2; i3++) {
            d *= ((double) ((i - i2) + i3)) / ((double) i3);
        }
        return FastMath.floor(d + 0.5d);
    }

    public static double binomialCoefficientLog(int i, int i2) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        checkBinomial(i, i2);
        double d = 0.0d;
        if (i == i2 || i2 == 0) {
            return 0.0d;
        }
        if (i2 == 1 || i2 == i - 1) {
            return FastMath.log((double) i);
        }
        if (i < 67) {
            return FastMath.log((double) binomialCoefficient(i, i2));
        }
        if (i < 1030) {
            return FastMath.log(binomialCoefficientDouble(i, i2));
        }
        if (i2 > i / 2) {
            return binomialCoefficientLog(i, i - i2);
        }
        for (int i3 = (i - i2) + 1; i3 <= i; i3++) {
            d += FastMath.log((double) i3);
        }
        for (int i4 = 2; i4 <= i2; i4++) {
            d -= FastMath.log((double) i4);
        }
        return d;
    }

    public static long factorial(int i) throws NotPositiveException, MathArithmeticException {
        if (i < 0) {
            throw new NotPositiveException(LocalizedFormats.FACTORIAL_NEGATIVE_PARAMETER, Integer.valueOf(i));
        } else if (i <= 20) {
            return FACTORIALS[i];
        } else {
            throw new MathArithmeticException();
        }
    }

    public static double factorialDouble(int i) throws NotPositiveException {
        if (i < 0) {
            throw new NotPositiveException(LocalizedFormats.FACTORIAL_NEGATIVE_PARAMETER, Integer.valueOf(i));
        } else if (i < 21) {
            return (double) FACTORIALS[i];
        } else {
            return FastMath.floor(FastMath.exp(factorialLog(i)) + 0.5d);
        }
    }

    public static double factorialLog(int i) throws NotPositiveException {
        if (i < 0) {
            throw new NotPositiveException(LocalizedFormats.FACTORIAL_NEGATIVE_PARAMETER, Integer.valueOf(i));
        } else if (i < 21) {
            return FastMath.log((double) FACTORIALS[i]);
        } else {
            double d = 0.0d;
            for (int i2 = 2; i2 <= i; i2++) {
                d += FastMath.log((double) i2);
            }
            return d;
        }
    }

    public static int gcd(int i, int i2) throws MathArithmeticException {
        int i3;
        boolean z;
        int i4;
        if (i != 0 && i2 != 0) {
            long j = (long) i;
            long j2 = (long) i2;
            if (i < 0) {
                if (Integer.MIN_VALUE == i) {
                    i3 = i;
                    z = true;
                } else {
                    i3 = -i;
                    z = false;
                }
                j = -j;
            } else {
                i3 = i;
                z = false;
            }
            if (i2 < 0) {
                if (Integer.MIN_VALUE == i2) {
                    i4 = i2;
                    z = true;
                } else {
                    i4 = -i2;
                }
                j2 = -j2;
            } else {
                i4 = i2;
            }
            if (z) {
                if (j == j2) {
                    throw new MathArithmeticException(LocalizedFormats.GCD_OVERFLOW_32_BITS, Integer.valueOf(i), Integer.valueOf(i2));
                }
                long j3 = j2 % j;
                if (j3 != 0) {
                    i4 = (int) j3;
                    i3 = (int) (j % j3);
                } else if (j <= 2147483647L) {
                    return (int) j;
                } else {
                    throw new MathArithmeticException(LocalizedFormats.GCD_OVERFLOW_32_BITS, Integer.valueOf(i), Integer.valueOf(i2));
                }
            }
            return gcdPositive(i3, i4);
        } else if (i != Integer.MIN_VALUE && i2 != Integer.MIN_VALUE) {
            return FastMath.abs(i + i2);
        } else {
            throw new MathArithmeticException(LocalizedFormats.GCD_OVERFLOW_32_BITS, Integer.valueOf(i), Integer.valueOf(i2));
        }
    }

    private static int gcdPositive(int i, int i2) {
        if (i == 0) {
            return i2;
        }
        if (i2 == 0) {
            return i;
        }
        int numberOfTrailingZeros = Integer.numberOfTrailingZeros(i);
        int i3 = i >> numberOfTrailingZeros;
        int numberOfTrailingZeros2 = Integer.numberOfTrailingZeros(i2);
        int i4 = i2 >> numberOfTrailingZeros2;
        int min = Math.min(numberOfTrailingZeros, numberOfTrailingZeros2);
        while (i3 != i4) {
            int i5 = i3 - i4;
            i4 = Math.min(i3, i4);
            int abs = Math.abs(i5);
            i3 = abs >> Integer.numberOfTrailingZeros(abs);
        }
        return i3 << min;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0055  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long gcd(long r23, long r25) throws org.apache.commons.math3.exception.MathArithmeticException {
        /*
            r0 = r23
            r2 = r25
            r4 = 0
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            r7 = 2
            r9 = 0
            if (r6 == 0) goto L_0x007c
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 != 0) goto L_0x0012
            goto L_0x007c
        L_0x0012:
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x0018
            long r10 = -r0
            goto L_0x0019
        L_0x0018:
            r10 = r0
        L_0x0019:
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x001f
            long r12 = -r2
            goto L_0x0020
        L_0x001f:
            r12 = r2
        L_0x0020:
            r6 = r9
        L_0x0021:
            r14 = 1
            long r16 = r10 & r14
            int r18 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1))
            r8 = 63
            r19 = 2
            if (r18 != 0) goto L_0x003c
            long r21 = r12 & r14
            int r18 = (r21 > r4 ? 1 : (r21 == r4 ? 0 : -1))
            if (r18 != 0) goto L_0x003c
            if (r6 >= r8) goto L_0x003c
            long r10 = r10 / r19
            long r12 = r12 / r19
            int r6 = r6 + 1
            goto L_0x0021
        L_0x003c:
            if (r6 != r8) goto L_0x0055
            org.apache.commons.math3.exception.MathArithmeticException r4 = new org.apache.commons.math3.exception.MathArithmeticException
            org.apache.commons.math3.exception.util.LocalizedFormats r5 = org.apache.commons.math3.exception.util.LocalizedFormats.GCD_OVERFLOW_64_BITS
            java.lang.Object[] r6 = new java.lang.Object[r7]
            java.lang.Long r0 = java.lang.Long.valueOf(r23)
            r6[r9] = r0
            java.lang.Long r0 = java.lang.Long.valueOf(r25)
            r1 = 1
            r6[r1] = r0
            r4.<init>(r5, r6)
            throw r4
        L_0x0055:
            int r0 = (r16 > r14 ? 1 : (r16 == r14 ? 0 : -1))
            if (r0 != 0) goto L_0x005b
            r0 = r12
            goto L_0x005e
        L_0x005b:
            long r0 = r10 / r19
            long r0 = -r0
        L_0x005e:
            long r2 = r0 & r14
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 != 0) goto L_0x0067
            long r0 = r0 / r19
            goto L_0x005e
        L_0x0067:
            int r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r2 <= 0) goto L_0x006e
            long r0 = -r0
            r10 = r0
            goto L_0x006f
        L_0x006e:
            r12 = r0
        L_0x006f:
            long r0 = r12 - r10
            long r0 = r0 / r19
            int r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r2 != 0) goto L_0x005e
            long r0 = -r10
            long r2 = r14 << r6
            long r0 = r0 * r2
            return r0
        L_0x007c:
            r4 = -9223372036854775808
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 == 0) goto L_0x0092
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 != 0) goto L_0x0087
            goto L_0x0092
        L_0x0087:
            long r0 = org.apache.commons.math3.util.FastMath.abs(r23)
            long r2 = org.apache.commons.math3.util.FastMath.abs(r25)
            long r4 = r0 + r2
            return r4
        L_0x0092:
            org.apache.commons.math3.exception.MathArithmeticException r4 = new org.apache.commons.math3.exception.MathArithmeticException
            org.apache.commons.math3.exception.util.LocalizedFormats r5 = org.apache.commons.math3.exception.util.LocalizedFormats.GCD_OVERFLOW_64_BITS
            java.lang.Object[] r6 = new java.lang.Object[r7]
            java.lang.Long r0 = java.lang.Long.valueOf(r23)
            r6[r9] = r0
            java.lang.Long r0 = java.lang.Long.valueOf(r25)
            r1 = 1
            r6[r1] = r0
            r4.<init>(r5, r6)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.util.ArithmeticUtils.gcd(long, long):long");
    }

    public static int lcm(int i, int i2) throws MathArithmeticException {
        if (i == 0 || i2 == 0) {
            return 0;
        }
        int abs = FastMath.abs(mulAndCheck(i / gcd(i, i2), i2));
        if (abs != Integer.MIN_VALUE) {
            return abs;
        }
        throw new MathArithmeticException(LocalizedFormats.LCM_OVERFLOW_32_BITS, Integer.valueOf(i), Integer.valueOf(i2));
    }

    public static long lcm(long j, long j2) throws MathArithmeticException {
        if (j == 0 || j2 == 0) {
            return 0;
        }
        long abs = FastMath.abs(mulAndCheck(j / gcd(j, j2), j2));
        if (abs != Long.MIN_VALUE) {
            return abs;
        }
        throw new MathArithmeticException(LocalizedFormats.LCM_OVERFLOW_64_BITS, Long.valueOf(j), Long.valueOf(j2));
    }

    public static int mulAndCheck(int i, int i2) throws MathArithmeticException {
        long j = ((long) i) * ((long) i2);
        if (j >= -2147483648L && j <= 2147483647L) {
            return (int) j;
        }
        throw new MathArithmeticException();
    }

    public static long mulAndCheck(long j, long j2) throws MathArithmeticException {
        if (j > j2) {
            return mulAndCheck(j2, j);
        }
        if (j < 0) {
            if (j2 < 0) {
                if (j >= Long.MAX_VALUE / j2) {
                    return j * j2;
                }
                throw new MathArithmeticException();
            } else if (j2 <= 0) {
                return 0;
            } else {
                if (Long.MIN_VALUE / j2 <= j) {
                    return j * j2;
                }
                throw new MathArithmeticException();
            }
        } else if (j <= 0) {
            return 0;
        } else {
            if (j <= Long.MAX_VALUE / j2) {
                return j * j2;
            }
            throw new MathArithmeticException();
        }
    }

    public static int subAndCheck(int i, int i2) throws MathArithmeticException {
        long j = ((long) i) - ((long) i2);
        if (j >= -2147483648L && j <= 2147483647L) {
            return (int) j;
        }
        throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_SUBTRACTION, Integer.valueOf(i), Integer.valueOf(i2));
    }

    public static long subAndCheck(long j, long j2) throws MathArithmeticException {
        if (j2 != Long.MIN_VALUE) {
            return addAndCheck(j, -j2, LocalizedFormats.OVERFLOW_IN_ADDITION);
        }
        if (j < 0) {
            return j - j2;
        }
        throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_ADDITION, Long.valueOf(j), Long.valueOf(-j2));
    }

    public static int pow(int i, int i2) throws NotPositiveException {
        if (i2 < 0) {
            throw new NotPositiveException(LocalizedFormats.EXPONENT, Integer.valueOf(i2));
        }
        int i3 = 1;
        while (i2 != 0) {
            if ((i2 & 1) != 0) {
                i3 *= i;
            }
            i *= i;
            i2 >>= 1;
        }
        return i3;
    }

    public static int pow(int i, long j) throws NotPositiveException {
        if (j < 0) {
            throw new NotPositiveException(LocalizedFormats.EXPONENT, Long.valueOf(j));
        }
        int i2 = i;
        int i3 = 1;
        while (j != 0) {
            if ((j & 1) != 0) {
                i3 *= i2;
            }
            i2 *= i2;
            j >>= 1;
        }
        return i3;
    }

    public static long pow(long j, int i) throws NotPositiveException {
        if (i < 0) {
            throw new NotPositiveException(LocalizedFormats.EXPONENT, Integer.valueOf(i));
        }
        long j2 = 1;
        while (i != 0) {
            if ((i & 1) != 0) {
                j2 *= j;
            }
            j *= j;
            i >>= 1;
        }
        return j2;
    }

    public static long pow(long j, long j2) throws NotPositiveException {
        if (j2 < 0) {
            throw new NotPositiveException(LocalizedFormats.EXPONENT, Long.valueOf(j2));
        }
        long j3 = j;
        long j4 = 1;
        while (j2 != 0) {
            if ((j2 & 1) != 0) {
                j4 *= j3;
            }
            j3 *= j3;
            j2 >>= 1;
        }
        return j4;
    }

    public static BigInteger pow(BigInteger bigInteger, int i) throws NotPositiveException {
        if (i >= 0) {
            return bigInteger.pow(i);
        }
        throw new NotPositiveException(LocalizedFormats.EXPONENT, Integer.valueOf(i));
    }

    public static BigInteger pow(BigInteger bigInteger, long j) throws NotPositiveException {
        if (j < 0) {
            throw new NotPositiveException(LocalizedFormats.EXPONENT, Long.valueOf(j));
        }
        BigInteger bigInteger2 = BigInteger.ONE;
        while (j != 0) {
            if ((j & 1) != 0) {
                bigInteger2 = bigInteger2.multiply(bigInteger);
            }
            bigInteger = bigInteger.multiply(bigInteger);
            j >>= 1;
        }
        return bigInteger2;
    }

    public static BigInteger pow(BigInteger bigInteger, BigInteger bigInteger2) throws NotPositiveException {
        if (bigInteger2.compareTo(BigInteger.ZERO) < 0) {
            throw new NotPositiveException(LocalizedFormats.EXPONENT, bigInteger2);
        }
        BigInteger bigInteger3 = BigInteger.ONE;
        while (!BigInteger.ZERO.equals(bigInteger2)) {
            if (bigInteger2.testBit(0)) {
                bigInteger3 = bigInteger3.multiply(bigInteger);
            }
            bigInteger = bigInteger.multiply(bigInteger);
            bigInteger2 = bigInteger2.shiftRight(1);
        }
        return bigInteger3;
    }

    public static long stirlingS2(int i, int i2) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        int i3 = i;
        int i4 = i2;
        if (i4 < 0) {
            throw new NotPositiveException(Integer.valueOf(i2));
        } else if (i4 > i3) {
            throw new NumberIsTooLargeException(Integer.valueOf(i2), Integer.valueOf(i), true);
        } else {
            long[][] jArr = (long[][]) STIRLING_S2.get();
            long j = 1;
            if (jArr == null) {
                jArr = new long[26][];
                jArr[0] = new long[]{1};
                int i5 = 1;
                while (i5 < jArr.length) {
                    int i6 = i5 + 1;
                    jArr[i5] = new long[i6];
                    jArr[i5][0] = 0;
                    jArr[i5][1] = 1;
                    jArr[i5][i5] = 1;
                    for (int i7 = 2; i7 < i5; i7++) {
                        int i8 = i5 - 1;
                        jArr[i5][i7] = (((long) i7) * jArr[i8][i7]) + jArr[i8][i7 - 1];
                    }
                    i5 = i6;
                }
                STIRLING_S2.compareAndSet(null, jArr);
            }
            if (i3 < jArr.length) {
                return jArr[i3][i4];
            }
            if (i4 == 0) {
                return 0;
            }
            if (i4 == 1 || i4 == i3) {
                return 1;
            }
            if (i4 == 2) {
                return (1 << (i3 - 1)) - 1;
            }
            if (i4 == i3 - 1) {
                return binomialCoefficient(i3, 2);
            }
            if ((i4 & 1) != 0) {
                j = -1;
            }
            long j2 = 0;
            long j3 = j;
            int i9 = 1;
            while (i9 <= i4) {
                j3 = -j3;
                long[][] jArr2 = jArr;
                long binomialCoefficient = j2 + (binomialCoefficient(i4, i9) * j3 * ((long) pow(i9, i3)));
                if (binomialCoefficient < 0) {
                    throw new MathArithmeticException(LocalizedFormats.ARGUMENT_OUTSIDE_DOMAIN, Integer.valueOf(i), Integer.valueOf(0), Integer.valueOf(jArr2.length - 1));
                }
                i9++;
                j2 = binomialCoefficient;
                jArr = jArr2;
            }
            return j2 / factorial(i2);
        }
    }

    private static long addAndCheck(long j, long j2, Localizable localizable) throws MathArithmeticException {
        if (j > j2) {
            return addAndCheck(j2, j, localizable);
        }
        if (j < 0) {
            if (j2 >= 0) {
                return j + j2;
            }
            if (Long.MIN_VALUE - j2 <= j) {
                return j + j2;
            }
            throw new MathArithmeticException(localizable, Long.valueOf(j), Long.valueOf(j2));
        } else if (j <= Long.MAX_VALUE - j2) {
            return j + j2;
        } else {
            throw new MathArithmeticException(localizable, Long.valueOf(j), Long.valueOf(j2));
        }
    }

    private static void checkBinomial(int i, int i2) throws NumberIsTooLargeException, NotPositiveException {
        if (i < i2) {
            throw new NumberIsTooLargeException(LocalizedFormats.BINOMIAL_INVALID_PARAMETERS_ORDER, Integer.valueOf(i2), Integer.valueOf(i), true);
        } else if (i < 0) {
            throw new NotPositiveException(LocalizedFormats.BINOMIAL_NEGATIVE_PARAMETER, Integer.valueOf(i));
        }
    }
}
