package org.apache.commons.math3.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class MathArrays {
    private static final int SPLIT_FACTOR = 134217729;

    public interface Function {
        double evaluate(double[] dArr);

        double evaluate(double[] dArr, int i, int i2);
    }

    public enum OrderDirection {
        INCREASING,
        DECREASING
    }

    private MathArrays() {
    }

    public static double[] ebeAdd(double[] dArr, double[] dArr2) {
        if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        }
        double[] dArr3 = (double[]) dArr.clone();
        for (int i = 0; i < dArr.length; i++) {
            dArr3[i] = dArr3[i] + dArr2[i];
        }
        return dArr3;
    }

    public static double[] ebeSubtract(double[] dArr, double[] dArr2) {
        if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        }
        double[] dArr3 = (double[]) dArr.clone();
        for (int i = 0; i < dArr.length; i++) {
            dArr3[i] = dArr3[i] - dArr2[i];
        }
        return dArr3;
    }

    public static double[] ebeMultiply(double[] dArr, double[] dArr2) {
        if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        }
        double[] dArr3 = (double[]) dArr.clone();
        for (int i = 0; i < dArr.length; i++) {
            dArr3[i] = dArr3[i] * dArr2[i];
        }
        return dArr3;
    }

    public static double[] ebeDivide(double[] dArr, double[] dArr2) {
        if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        }
        double[] dArr3 = (double[]) dArr.clone();
        for (int i = 0; i < dArr.length; i++) {
            dArr3[i] = dArr3[i] / dArr2[i];
        }
        return dArr3;
    }

    public static double distance1(double[] dArr, double[] dArr2) {
        double d = 0.0d;
        for (int i = 0; i < dArr.length; i++) {
            d += FastMath.abs(dArr[i] - dArr2[i]);
        }
        return d;
    }

    public static int distance1(int[] iArr, int[] iArr2) {
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            i += FastMath.abs(iArr[i2] - iArr2[i2]);
        }
        return i;
    }

    public static double distance(double[] dArr, double[] dArr2) {
        double d = 0.0d;
        for (int i = 0; i < dArr.length; i++) {
            double d2 = dArr[i] - dArr2[i];
            d += d2 * d2;
        }
        return FastMath.sqrt(d);
    }

    public static double distance(int[] iArr, int[] iArr2) {
        double d = 0.0d;
        for (int i = 0; i < iArr.length; i++) {
            double d2 = (double) (iArr[i] - iArr2[i]);
            d += d2 * d2;
        }
        return FastMath.sqrt(d);
    }

    public static double distanceInf(double[] dArr, double[] dArr2) {
        double d = 0.0d;
        for (int i = 0; i < dArr.length; i++) {
            d = FastMath.max(d, FastMath.abs(dArr[i] - dArr2[i]));
        }
        return d;
    }

    public static int distanceInf(int[] iArr, int[] iArr2) {
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            i = FastMath.max(i, FastMath.abs(iArr[i2] - iArr2[i2]));
        }
        return i;
    }

    public static <T extends Comparable<? super T>> boolean isMonotonic(T[] tArr, OrderDirection orderDirection, boolean z) {
        T t = tArr[0];
        int length = tArr.length;
        T t2 = t;
        for (int i = 1; i < length; i++) {
            switch (orderDirection) {
                case INCREASING:
                    int compareTo = t2.compareTo(tArr[i]);
                    if (!z) {
                        if (compareTo <= 0) {
                            break;
                        } else {
                            return false;
                        }
                    } else if (compareTo < 0) {
                        break;
                    } else {
                        return false;
                    }
                case DECREASING:
                    int compareTo2 = tArr[i].compareTo(t2);
                    if (!z) {
                        if (compareTo2 <= 0) {
                            break;
                        } else {
                            return false;
                        }
                    } else if (compareTo2 < 0) {
                        break;
                    } else {
                        return false;
                    }
                default:
                    throw new MathInternalError();
            }
            t2 = tArr[i];
        }
        return true;
    }

    public static boolean isMonotonic(double[] dArr, OrderDirection orderDirection, boolean z) {
        return checkOrder(dArr, orderDirection, z, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0039, code lost:
        r1 = r11[r8];
        r8 = r8 + 1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0040 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0041  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean checkOrder(double[] r11, org.apache.commons.math3.util.MathArrays.OrderDirection r12, boolean r13, boolean r14) throws org.apache.commons.math3.exception.NonMonotonicSequenceException {
        /*
            r0 = 0
            r1 = r11[r0]
            r3 = 1
            int r4 = r11.length
            r8 = r3
        L_0x0006:
            if (r8 >= r4) goto L_0x003e
            int[] r5 = org.apache.commons.math3.util.MathArrays.AnonymousClass2.$SwitchMap$org$apache$commons$math3$util$MathArrays$OrderDirection
            int r6 = r12.ordinal()
            r5 = r5[r6]
            switch(r5) {
                case 1: goto L_0x0029;
                case 2: goto L_0x0019;
                default: goto L_0x0013;
            }
        L_0x0013:
            org.apache.commons.math3.exception.MathInternalError r11 = new org.apache.commons.math3.exception.MathInternalError
            r11.<init>()
            throw r11
        L_0x0019:
            if (r13 == 0) goto L_0x0022
            r5 = r11[r8]
            int r7 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r7 < 0) goto L_0x0039
            goto L_0x003e
        L_0x0022:
            r5 = r11[r8]
            int r7 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r7 <= 0) goto L_0x0039
            goto L_0x003e
        L_0x0029:
            if (r13 == 0) goto L_0x0032
            r5 = r11[r8]
            int r7 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r7 > 0) goto L_0x0039
            goto L_0x003e
        L_0x0032:
            r5 = r11[r8]
            int r7 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r7 >= 0) goto L_0x0039
            goto L_0x003e
        L_0x0039:
            r1 = r11[r8]
            int r8 = r8 + 1
            goto L_0x0006
        L_0x003e:
            if (r8 != r4) goto L_0x0041
            return r3
        L_0x0041:
            if (r14 == 0) goto L_0x0056
            org.apache.commons.math3.exception.NonMonotonicSequenceException r14 = new org.apache.commons.math3.exception.NonMonotonicSequenceException
            r3 = r11[r8]
            java.lang.Double r6 = java.lang.Double.valueOf(r3)
            java.lang.Double r7 = java.lang.Double.valueOf(r1)
            r5 = r14
            r9 = r12
            r10 = r13
            r5.<init>(r6, r7, r8, r9, r10)
            throw r14
        L_0x0056:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.util.MathArrays.checkOrder(double[], org.apache.commons.math3.util.MathArrays$OrderDirection, boolean, boolean):boolean");
    }

    public static void checkOrder(double[] dArr, OrderDirection orderDirection, boolean z) throws NonMonotonicSequenceException {
        checkOrder(dArr, orderDirection, z, true);
    }

    public static void checkOrder(double[] dArr) throws NonMonotonicSequenceException {
        checkOrder(dArr, OrderDirection.INCREASING, true);
    }

    public static void checkRectangular(long[][] jArr) throws NullArgumentException, DimensionMismatchException {
        MathUtils.checkNotNull(jArr);
        for (int i = 1; i < jArr.length; i++) {
            if (jArr[i].length != jArr[0].length) {
                throw new DimensionMismatchException(LocalizedFormats.DIFFERENT_ROWS_LENGTHS, jArr[i].length, jArr[0].length);
            }
        }
    }

    public static void checkPositive(double[] dArr) throws NotStrictlyPositiveException {
        for (int i = 0; i < dArr.length; i++) {
            if (dArr[i] <= 0.0d) {
                throw new NotStrictlyPositiveException(Double.valueOf(dArr[i]));
            }
        }
    }

    public static void checkNonNegative(long[] jArr) throws NotPositiveException {
        for (int i = 0; i < jArr.length; i++) {
            if (jArr[i] < 0) {
                throw new NotPositiveException(Long.valueOf(jArr[i]));
            }
        }
    }

    public static void checkNonNegative(long[][] jArr) throws NotPositiveException {
        for (int i = 0; i < jArr.length; i++) {
            for (int i2 = 0; i2 < jArr[i].length; i2++) {
                if (jArr[i][i2] < 0) {
                    throw new NotPositiveException(Long.valueOf(jArr[i][i2]));
                }
            }
        }
    }

    public static double safeNorm(double[] dArr) {
        double[] dArr2 = dArr;
        double length = 1.304E19d / ((double) dArr2.length);
        double d = 0.0d;
        double d2 = 0.0d;
        double d3 = 0.0d;
        double d4 = 0.0d;
        double d5 = 0.0d;
        for (double abs : dArr2) {
            double abs2 = Math.abs(abs);
            if (abs2 >= 3.834E-20d && abs2 <= length) {
                d3 += abs2 * abs2;
            } else if (abs2 > 3.834E-20d) {
                if (abs2 > d2) {
                    double d6 = d2 / abs2;
                    d2 = abs2;
                    d = 1.0d + (d * d6 * d6);
                } else {
                    double d7 = abs2 / d2;
                    d += d7 * d7;
                }
            } else if (abs2 > d4) {
                double d8 = d4 / abs2;
                d4 = abs2;
                d5 = 1.0d + (d5 * d8 * d8);
            } else {
                if (abs2 != 0.0d) {
                    double d9 = abs2 / d4;
                    d5 += d9 * d9;
                }
            }
        }
        if (d != 0.0d) {
            return d2 * Math.sqrt(d + ((d3 / d2) / d2));
        }
        if (d3 == 0.0d) {
            return d4 * Math.sqrt(d5);
        }
        if (d3 >= d4) {
            return Math.sqrt(d3 * (1.0d + ((d4 / d3) * d4 * d5)));
        }
        return Math.sqrt(d4 * ((d3 / d4) + (d5 * d4)));
    }

    public static void sortInPlace(double[] dArr, double[]... dArr2) throws DimensionMismatchException, NullArgumentException {
        sortInPlace(dArr, OrderDirection.INCREASING, dArr2);
    }

    public static void sortInPlace(double[] dArr, final OrderDirection orderDirection, double[]... dArr2) throws NullArgumentException, DimensionMismatchException {
        if (dArr == null) {
            throw new NullArgumentException();
        }
        int length = dArr.length;
        ArrayList arrayList = new ArrayList(length);
        int length2 = dArr2.length;
        for (int i = 0; i < length; i++) {
            double[] dArr3 = new double[length2];
            int i2 = 0;
            while (i2 < length2) {
                double[] dArr4 = dArr2[i2];
                if (dArr4 == null) {
                    throw new NullArgumentException();
                } else if (dArr4.length != length) {
                    throw new DimensionMismatchException(dArr4.length, length);
                } else {
                    dArr3[i2] = dArr4[i];
                    i2++;
                }
            }
            arrayList.add(new Pair(Double.valueOf(dArr[i]), dArr3));
        }
        Collections.sort(arrayList, new Comparator<Pair<Double, double[]>>() {
            public int compare(Pair<Double, double[]> pair, Pair<Double, double[]> pair2) {
                switch (AnonymousClass2.$SwitchMap$org$apache$commons$math3$util$MathArrays$OrderDirection[orderDirection.ordinal()]) {
                    case 1:
                        return ((Double) pair.getKey()).compareTo((Double) pair2.getKey());
                    case 2:
                        return ((Double) pair2.getKey()).compareTo((Double) pair.getKey());
                    default:
                        throw new MathInternalError();
                }
            }
        });
        for (int i3 = 0; i3 < length; i3++) {
            Pair pair = (Pair) arrayList.get(i3);
            dArr[i3] = ((Double) pair.getKey()).doubleValue();
            double[] dArr5 = (double[]) pair.getValue();
            for (int i4 = 0; i4 < length2; i4++) {
                dArr2[i4][i3] = dArr5[i4];
            }
        }
    }

    public static int[] copyOf(int[] iArr) {
        return copyOf(iArr, iArr.length);
    }

    public static double[] copyOf(double[] dArr) {
        return copyOf(dArr, dArr.length);
    }

    public static int[] copyOf(int[] iArr, int i) {
        int[] iArr2 = new int[i];
        System.arraycopy(iArr, 0, iArr2, 0, FastMath.min(i, iArr.length));
        return iArr2;
    }

    public static double[] copyOf(double[] dArr, int i) {
        double[] dArr2 = new double[i];
        System.arraycopy(dArr, 0, dArr2, 0, FastMath.min(i, dArr.length));
        return dArr2;
    }

    public static double linearCombination(double[] dArr, double[] dArr2) throws DimensionMismatchException {
        double[] dArr3 = dArr;
        double[] dArr4 = dArr2;
        int length = dArr3.length;
        if (length != dArr4.length) {
            throw new DimensionMismatchException(length, dArr4.length);
        }
        double[] dArr5 = new double[length];
        double d = 0.0d;
        for (int i = 0; i < length; i++) {
            double d2 = dArr3[i];
            double d3 = 1.34217729E8d * d2;
            double d4 = d3 - (d3 - d2);
            double d5 = d2 - d4;
            double d6 = dArr4[i];
            double d7 = 1.34217729E8d * d6;
            double d8 = d7 - (d7 - d6);
            double d9 = d6 - d8;
            dArr5[i] = d2 * d6;
            d += (d5 * d9) - (((dArr5[i] - (d4 * d8)) - (d5 * d8)) - (d4 * d9));
        }
        double d10 = dArr5[0];
        int i2 = 1;
        double d11 = dArr5[1];
        double d12 = d10 + d11;
        double d13 = d12 - d11;
        double d14 = (d11 - (d12 - d13)) + (d10 - d13);
        int i3 = length - 1;
        while (i2 < i3) {
            i2++;
            double d15 = dArr5[i2];
            double d16 = d12 + d15;
            double d17 = d16 - d15;
            d14 += (d15 - (d16 - d17)) + (d12 - d17);
            d12 = d16;
        }
        double d18 = d12 + d + d14;
        if (Double.isNaN(d18)) {
            d18 = 0.0d;
            for (int i4 = 0; i4 < length; i4++) {
                d18 += dArr3[i4] * dArr4[i4];
            }
        }
        return d18;
    }

    public static double linearCombination(double d, double d2, double d3, double d4) {
        double d5 = 1.34217729E8d * d;
        double d6 = d5 - (d5 - d);
        double d7 = d - d6;
        double d8 = 1.34217729E8d * d2;
        double d9 = d8 - (d8 - d2);
        double d10 = d2 - d9;
        double d11 = d * d2;
        double d12 = 1.34217729E8d * d3;
        double d13 = d12 - (d12 - d3);
        double d14 = d3 - d13;
        double d15 = 1.34217729E8d * d4;
        double d16 = d15 - (d15 - d4);
        double d17 = d4 - d16;
        double d18 = d3 * d4;
        double d19 = d11 + d18;
        double d20 = d19 - d18;
        double d21 = d19 + ((d7 * d10) - (((d11 - (d6 * d9)) - (d7 * d9)) - (d6 * d10))) + ((d14 * d17) - (((d18 - (d13 * d16)) - (d14 * d16)) - (d13 * d17))) + (d18 - (d19 - d20)) + (d11 - d20);
        return Double.isNaN(d21) ? d19 : d21;
    }

    public static double linearCombination(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = 1.34217729E8d * d;
        double d8 = d7 - (d7 - d);
        double d9 = d - d8;
        double d10 = 1.34217729E8d * d2;
        double d11 = d10 - (d10 - d2);
        double d12 = d2 - d11;
        double d13 = d * d2;
        double d14 = 1.34217729E8d * d3;
        double d15 = d14 - (d14 - d3);
        double d16 = d3 - d15;
        double d17 = 1.34217729E8d * d4;
        double d18 = d17 - (d17 - d4);
        double d19 = d4 - d18;
        double d20 = d3 * d4;
        double d21 = 1.34217729E8d * d5;
        double d22 = d21 - (d21 - d5);
        double d23 = d5 - d22;
        double d24 = 1.34217729E8d * d6;
        double d25 = d24 - (d24 - d6);
        double d26 = d6 - d25;
        double d27 = d5 * d6;
        double d28 = d13 + d20;
        double d29 = d28 - d20;
        double d30 = (d20 - (d28 - d29)) + (d13 - d29);
        double d31 = d28 + d27;
        double d32 = d31 - d27;
        double d33 = ((d9 * d12) - (((d13 - (d8 * d11)) - (d9 * d11)) - (d8 * d12))) + ((d16 * d19) - (((d20 - (d15 * d18)) - (d16 * d18)) - (d15 * d19))) + ((d23 * d26) - (((d27 - (d22 * d25)) - (d23 * d25)) - (d22 * d26))) + d30 + (d27 - (d31 - d32)) + (d28 - d32) + d31;
        return Double.isNaN(d33) ? d31 : d33;
    }

    public static double linearCombination(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8) {
        double d9 = 1.34217729E8d * d;
        double d10 = d9 - (d9 - d);
        double d11 = d - d10;
        double d12 = 1.34217729E8d * d2;
        double d13 = d12 - (d12 - d2);
        double d14 = d2 - d13;
        double d15 = d * d2;
        double d16 = (d11 * d14) - (((d15 - (d10 * d13)) - (d11 * d13)) - (d10 * d14));
        double d17 = 1.34217729E8d * d3;
        double d18 = d17 - (d17 - d3);
        double d19 = d3 - d18;
        double d20 = 1.34217729E8d * d4;
        double d21 = d20 - (d20 - d4);
        double d22 = d4 - d21;
        double d23 = d3 * d4;
        double d24 = (d19 * d22) - (((d23 - (d18 * d21)) - (d19 * d21)) - (d18 * d22));
        double d25 = 1.34217729E8d * d5;
        double d26 = d25 - (d25 - d5);
        double d27 = d5 - d26;
        double d28 = 1.34217729E8d * d6;
        double d29 = d28 - (d28 - d6);
        double d30 = d6 - d29;
        double d31 = d5 * d6;
        double d32 = 1.34217729E8d * d7;
        double d33 = d32 - (d32 - d7);
        double d34 = d7 - d33;
        double d35 = 1.34217729E8d * d8;
        double d36 = d35 - (d35 - d8);
        double d37 = d8 - d36;
        double d38 = d7 * d8;
        double d39 = d15 + d23;
        double d40 = d39 - d23;
        double d41 = (d23 - (d39 - d40)) + (d15 - d40);
        double d42 = d39 + d31;
        double d43 = d42 - d31;
        double d44 = (d31 - (d42 - d43)) + (d39 - d43);
        double d45 = d42 + d38;
        double d46 = d45 - d38;
        double d47 = d45 + d16 + d24 + ((d27 * d30) - (((d31 - (d26 * d29)) - (d27 * d29)) - (d26 * d30))) + ((d34 * d37) - (((d38 - (d33 * d36)) - (d34 * d36)) - (d33 * d37))) + d41 + d44 + (d38 - (d45 - d46)) + (d42 - d46);
        return Double.isNaN(d47) ? d45 : d47;
    }

    public static boolean equals(float[] fArr, float[] fArr2) {
        boolean z = true;
        if (fArr == null || fArr2 == null) {
            if ((fArr == null) ^ (fArr2 == null)) {
                z = false;
            }
            return z;
        } else if (fArr.length != fArr2.length) {
            return false;
        } else {
            for (int i = 0; i < fArr.length; i++) {
                if (!Precision.equals(fArr[i], fArr2[i])) {
                    return false;
                }
            }
            return true;
        }
    }

    public static boolean equalsIncludingNaN(float[] fArr, float[] fArr2) {
        boolean z = true;
        if (fArr == null || fArr2 == null) {
            if ((fArr == null) ^ (fArr2 == null)) {
                z = false;
            }
            return z;
        } else if (fArr.length != fArr2.length) {
            return false;
        } else {
            for (int i = 0; i < fArr.length; i++) {
                if (!Precision.equalsIncludingNaN(fArr[i], fArr2[i])) {
                    return false;
                }
            }
            return true;
        }
    }

    public static boolean equals(double[] dArr, double[] dArr2) {
        boolean z = true;
        if (dArr == null || dArr2 == null) {
            if ((dArr == null) ^ (dArr2 == null)) {
                z = false;
            }
            return z;
        } else if (dArr.length != dArr2.length) {
            return false;
        } else {
            for (int i = 0; i < dArr.length; i++) {
                if (!Precision.equals(dArr[i], dArr2[i])) {
                    return false;
                }
            }
            return true;
        }
    }

    public static boolean equalsIncludingNaN(double[] dArr, double[] dArr2) {
        boolean z = true;
        if (dArr == null || dArr2 == null) {
            if ((dArr == null) ^ (dArr2 == null)) {
                z = false;
            }
            return z;
        } else if (dArr.length != dArr2.length) {
            return false;
        } else {
            for (int i = 0; i < dArr.length; i++) {
                if (!Precision.equalsIncludingNaN(dArr[i], dArr2[i])) {
                    return false;
                }
            }
            return true;
        }
    }

    public static double[] normalizeArray(double[] dArr, double d) throws MathIllegalArgumentException, MathArithmeticException {
        if (Double.isInfinite(d)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NORMALIZE_INFINITE, new Object[0]);
        } else if (Double.isNaN(d)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NORMALIZE_NAN, new Object[0]);
        } else {
            int length = dArr.length;
            double[] dArr2 = new double[length];
            double d2 = 0.0d;
            for (int i = 0; i < length; i++) {
                if (Double.isInfinite(dArr[i])) {
                    throw new MathIllegalArgumentException(LocalizedFormats.INFINITE_ARRAY_ELEMENT, Double.valueOf(dArr[i]), Integer.valueOf(i));
                }
                if (!Double.isNaN(dArr[i])) {
                    d2 += dArr[i];
                }
            }
            if (d2 == 0.0d) {
                throw new MathArithmeticException(LocalizedFormats.ARRAY_SUMS_TO_ZERO, new Object[0]);
            }
            for (int i2 = 0; i2 < length; i2++) {
                if (Double.isNaN(dArr[i2])) {
                    dArr2[i2] = Double.NaN;
                } else {
                    dArr2[i2] = (dArr[i2] * d) / d2;
                }
            }
            return dArr2;
        }
    }
}
