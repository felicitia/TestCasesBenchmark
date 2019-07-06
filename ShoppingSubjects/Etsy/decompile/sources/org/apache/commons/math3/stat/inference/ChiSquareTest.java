package org.apache.commons.math3.stat.inference;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class ChiSquareTest {
    public double chiSquare(double[] dArr, long[] jArr) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException {
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
            if (FastMath.abs(d4 - d5) > 1.0E-5d) {
                d6 = d5 / d4;
                z = true;
            } else {
                z = false;
            }
            for (int i2 = 0; i2 < jArr2.length; i2++) {
                if (z) {
                    double d7 = ((double) jArr2[i2]) - (dArr2[i2] * d6);
                    d2 = d7 * d7;
                    d = dArr2[i2] * d6;
                } else {
                    double d8 = ((double) jArr2[i2]) - dArr2[i2];
                    d2 = d8 * d8;
                    d = dArr2[i2];
                }
                d3 += d2 / d;
            }
            return d3;
        }
    }

    public double chiSquareTest(double[] dArr, long[] jArr) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, MaxCountExceededException {
        return 1.0d - new ChiSquaredDistribution(((double) dArr.length) - 1.0d).cumulativeProbability(chiSquare(dArr, jArr));
    }

    public boolean chiSquareTest(double[] dArr, long[] jArr, double d) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, OutOfRangeException, MaxCountExceededException {
        if (d > 0.0d && d <= 0.5d) {
            return chiSquareTest(dArr, jArr) < d;
        }
        throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(d), Integer.valueOf(0), Double.valueOf(0.5d));
    }

    public double chiSquare(long[][] jArr) throws NullArgumentException, NotPositiveException, DimensionMismatchException {
        long[][] jArr2 = jArr;
        checkArray(jArr);
        int i = 0;
        int length = jArr2.length;
        int length2 = jArr2[0].length;
        double[] dArr = new double[length];
        double[] dArr2 = new double[length2];
        int i2 = 0;
        double d = 0.0d;
        while (i2 < length) {
            double d2 = d;
            for (int i3 = 0; i3 < length2; i3++) {
                dArr[i2] = dArr[i2] + ((double) jArr2[i2][i3]);
                dArr2[i3] = dArr2[i3] + ((double) jArr2[i2][i3]);
                d2 += (double) jArr2[i2][i3];
            }
            i2++;
            d = d2;
        }
        int i4 = 0;
        double d3 = 0.0d;
        while (i4 < length) {
            int i5 = i;
            while (i5 < length2) {
                double d4 = (dArr[i4] * dArr2[i5]) / d;
                d3 += ((((double) jArr2[i4][i5]) - d4) * (((double) jArr2[i4][i5]) - d4)) / d4;
                i5++;
                length = length;
            }
            int i6 = length;
            i4++;
            i = 0;
        }
        return d3;
    }

    public double chiSquareTest(long[][] jArr) throws NullArgumentException, DimensionMismatchException, NotPositiveException, MaxCountExceededException {
        checkArray(jArr);
        return 1.0d - new ChiSquaredDistribution((((double) jArr.length) - 1.0d) * (((double) jArr[0].length) - 1.0d)).cumulativeProbability(chiSquare(jArr));
    }

    public boolean chiSquareTest(long[][] jArr, double d) throws NullArgumentException, DimensionMismatchException, NotPositiveException, OutOfRangeException, MaxCountExceededException {
        if (d > 0.0d && d <= 0.5d) {
            return chiSquareTest(jArr) < d;
        }
        throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(d), Integer.valueOf(0), Double.valueOf(0.5d));
    }

    public double chiSquareDataSetsComparison(long[] jArr, long[] jArr2) throws DimensionMismatchException, NotPositiveException, ZeroException {
        long[] jArr3 = jArr;
        long[] jArr4 = jArr2;
        if (jArr3.length < 2) {
            throw new DimensionMismatchException(jArr3.length, 2);
        } else if (jArr3.length != jArr4.length) {
            throw new DimensionMismatchException(jArr3.length, jArr4.length);
        } else {
            MathArrays.checkNonNegative(jArr);
            MathArrays.checkNonNegative(jArr2);
            char c = 0;
            long j = 0;
            int i = 0;
            long j2 = 0;
            long j3 = 0;
            while (i < jArr3.length) {
                long j4 = j2 + jArr3[i];
                i++;
                j3 += jArr4[i];
                j2 = j4;
            }
            if (j2 == 0 || j3 == 0) {
                throw new ZeroException();
            }
            boolean z = j2 != j3;
            double d = 0.0d;
            double sqrt = z ? FastMath.sqrt(((double) j2) / ((double) j3)) : 0.0d;
            int i2 = 0;
            while (i2 < jArr3.length) {
                if (jArr3[i2] == j && jArr4[i2] == j) {
                    LocalizedFormats localizedFormats = LocalizedFormats.OBSERVED_COUNTS_BOTTH_ZERO_FOR_ENTRY;
                    Object[] objArr = new Object[1];
                    objArr[c] = Integer.valueOf(i2);
                    throw new ZeroException(localizedFormats, objArr);
                }
                double d2 = (double) jArr3[i2];
                double d3 = (double) jArr4[i2];
                double d4 = z ? (d2 / sqrt) - (d3 * sqrt) : d2 - d3;
                d += (d4 * d4) / (d2 + d3);
                i2++;
                c = 0;
                j = 0;
            }
            return d;
        }
    }

    public double chiSquareTestDataSetsComparison(long[] jArr, long[] jArr2) throws DimensionMismatchException, NotPositiveException, ZeroException, MaxCountExceededException {
        return 1.0d - new ChiSquaredDistribution(((double) jArr.length) - 1.0d).cumulativeProbability(chiSquareDataSetsComparison(jArr, jArr2));
    }

    public boolean chiSquareTestDataSetsComparison(long[] jArr, long[] jArr2, double d) throws DimensionMismatchException, NotPositiveException, ZeroException, OutOfRangeException, MaxCountExceededException {
        if (d > 0.0d && d <= 0.5d) {
            return chiSquareTestDataSetsComparison(jArr, jArr2) < d;
        }
        throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(d), Integer.valueOf(0), Double.valueOf(0.5d));
    }

    private void checkArray(long[][] jArr) throws NullArgumentException, DimensionMismatchException, NotPositiveException {
        if (jArr.length < 2) {
            throw new DimensionMismatchException(jArr.length, 2);
        } else if (jArr[0].length < 2) {
            throw new DimensionMismatchException(jArr[0].length, 2);
        } else {
            MathArrays.checkRectangular(jArr);
            MathArrays.checkNonNegative(jArr);
        }
    }
}
