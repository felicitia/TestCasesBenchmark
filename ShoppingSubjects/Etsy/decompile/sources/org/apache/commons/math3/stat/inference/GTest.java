package org.apache.commons.math3.stat.inference;

import java.lang.reflect.Array;
import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class GTest {
    public double g(double[] dArr, long[] jArr) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException {
        boolean z;
        double d;
        double d2;
        double[] dArr2 = dArr;
        long[] jArr2 = jArr;
        if (dArr2.length < 2) {
            throw new DimensionMismatchException(dArr2.length, 2);
        } else if (dArr2.length != jArr2.length) {
            throw new DimensionMismatchException(dArr2.length, jArr2.length);
        } else {
            MathArrays.checkPositive(dArr);
            MathArrays.checkNonNegative(jArr);
            double d3 = 0.0d;
            double d4 = 0.0d;
            double d5 = 0.0d;
            for (int i = 0; i < jArr2.length; i++) {
                d4 += dArr2[i];
                d5 += (double) jArr2[i];
            }
            double d6 = 1.0d;
            if (Math.abs(d4 - d5) > 1.0E-5d) {
                d6 = d5 / d4;
                z = true;
            } else {
                z = false;
            }
            for (int i2 = 0; i2 < jArr2.length; i2++) {
                if (z) {
                    d = (double) jArr2[i2];
                    d2 = dArr2[i2] * d6;
                } else {
                    d = (double) jArr2[i2];
                    d2 = dArr2[i2];
                }
                d3 += ((double) jArr2[i2]) * FastMath.log(d / d2);
            }
            return 2.0d * d3;
        }
    }

    public double gTest(double[] dArr, long[] jArr) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, MaxCountExceededException {
        return 1.0d - new ChiSquaredDistribution(((double) dArr.length) - 1.0d).cumulativeProbability(g(dArr, jArr));
    }

    public double gTestIntrinsic(double[] dArr, long[] jArr) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, MaxCountExceededException {
        return 1.0d - new ChiSquaredDistribution(((double) dArr.length) - 2.0d).cumulativeProbability(g(dArr, jArr));
    }

    public boolean gTest(double[] dArr, long[] jArr, double d) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, OutOfRangeException, MaxCountExceededException {
        if (d > 0.0d && d <= 0.5d) {
            return gTest(dArr, jArr) < d;
        }
        throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(d), Integer.valueOf(0), Double.valueOf(0.5d));
    }

    private double entropy(long[][] jArr) {
        double d = 0.0d;
        int i = 0;
        while (i < jArr.length) {
            double d2 = d;
            for (long j : jArr[i]) {
                d2 += (double) j;
            }
            i++;
            d = d2;
        }
        double d3 = 0.0d;
        for (int i2 = 0; i2 < jArr.length; i2++) {
            for (int i3 = 0; i3 < jArr[i2].length; i3++) {
                if (jArr[i2][i3] != 0) {
                    double d4 = ((double) jArr[i2][i3]) / d;
                    d3 += d4 * Math.log(d4);
                }
            }
        }
        return -d3;
    }

    private double entropy(long[] jArr) {
        double d = 0.0d;
        double d2 = 0.0d;
        for (long j : jArr) {
            d2 += (double) j;
        }
        for (int i = 0; i < jArr.length; i++) {
            if (jArr[i] != 0) {
                double d3 = ((double) jArr[i]) / d2;
                d += d3 * Math.log(d3);
            }
        }
        return -d;
    }

    public double gDataSetsComparison(long[] jArr, long[] jArr2) throws DimensionMismatchException, NotPositiveException, ZeroException {
        long[] jArr3 = jArr;
        long[] jArr4 = jArr2;
        if (jArr3.length < 2) {
            throw new DimensionMismatchException(jArr3.length, 2);
        } else if (jArr3.length != jArr4.length) {
            throw new DimensionMismatchException(jArr3.length, jArr4.length);
        } else {
            MathArrays.checkNonNegative(jArr);
            MathArrays.checkNonNegative(jArr2);
            long[] jArr5 = new long[jArr3.length];
            long[][] jArr6 = (long[][]) Array.newInstance(long.class, new int[]{2, jArr3.length});
            int i = 0;
            long j = 0;
            long j2 = 0;
            while (i < jArr3.length) {
                if (jArr3[i] == 0 && jArr4[i] == 0) {
                    throw new ZeroException(LocalizedFormats.OBSERVED_COUNTS_BOTTH_ZERO_FOR_ENTRY, Integer.valueOf(i));
                }
                long j3 = j + jArr3[i];
                long j4 = j2 + jArr4[i];
                jArr5[i] = jArr3[i] + jArr4[i];
                jArr6[0][i] = jArr3[i];
                jArr6[1][i] = jArr4[i];
                i++;
                j2 = j4;
                j = j3;
            }
            if (j == 0 || j2 == 0) {
                throw new ZeroException();
            }
            return 2.0d * (((double) j) + ((double) j2)) * ((entropy(new long[]{j, j2}) + entropy(jArr5)) - entropy(jArr6));
        }
    }

    public double rootLogLikelihoodRatio(long j, long j2, long j3, long j4) {
        double sqrt = FastMath.sqrt(gDataSetsComparison(new long[]{j, j2}, new long[]{j3, j4}));
        return ((double) j) / ((double) (j + j2)) < ((double) j3) / ((double) (j3 + j4)) ? -sqrt : sqrt;
    }

    public double gTestDataSetsComparison(long[] jArr, long[] jArr2) throws DimensionMismatchException, NotPositiveException, ZeroException, MaxCountExceededException {
        return 1.0d - new ChiSquaredDistribution(((double) jArr.length) - 1.0d).cumulativeProbability(gDataSetsComparison(jArr, jArr2));
    }

    public boolean gTestDataSetsComparison(long[] jArr, long[] jArr2, double d) throws DimensionMismatchException, NotPositiveException, ZeroException, OutOfRangeException, MaxCountExceededException {
        if (d > 0.0d && d <= 0.5d) {
            return gTestDataSetsComparison(jArr, jArr2) < d;
        }
        throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(d), Integer.valueOf(0), Double.valueOf(0.5d));
    }
}
