package org.apache.commons.math3.analysis.interpolation;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NotFiniteNumberException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

public class LoessInterpolator implements Serializable, UnivariateInterpolator {
    public static final double DEFAULT_ACCURACY = 1.0E-12d;
    public static final double DEFAULT_BANDWIDTH = 0.3d;
    public static final int DEFAULT_ROBUSTNESS_ITERS = 2;
    private static final long serialVersionUID = 5204927143605193821L;
    private final double accuracy;
    private final double bandwidth;
    private final int robustnessIters;

    public LoessInterpolator() {
        this.bandwidth = 0.3d;
        this.robustnessIters = 2;
        this.accuracy = 1.0E-12d;
    }

    public LoessInterpolator(double d, int i) {
        this(d, i, 1.0E-12d);
    }

    public LoessInterpolator(double d, int i, double d2) throws OutOfRangeException, NotPositiveException {
        if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(LocalizedFormats.BANDWIDTH, Double.valueOf(d), Integer.valueOf(0), Integer.valueOf(1));
        }
        this.bandwidth = d;
        if (i < 0) {
            throw new NotPositiveException(LocalizedFormats.ROBUSTNESS_ITERATIONS, Integer.valueOf(i));
        }
        this.robustnessIters = i;
        this.accuracy = d2;
    }

    public final PolynomialSplineFunction interpolate(double[] dArr, double[] dArr2) throws NonMonotonicSequenceException, DimensionMismatchException, NoDataException, NotFiniteNumberException, NumberIsTooSmallException {
        return new SplineInterpolator().interpolate(dArr, smooth(dArr, dArr2));
    }

    public final double[] smooth(double[] dArr, double[] dArr2, double[] dArr3) throws NonMonotonicSequenceException, DimensionMismatchException, NoDataException, NotFiniteNumberException, NumberIsTooSmallException {
        double[] dArr4 = dArr;
        double[] dArr5 = dArr2;
        double[] dArr6 = dArr3;
        if (dArr4.length != dArr5.length) {
            throw new DimensionMismatchException(dArr4.length, dArr5.length);
        }
        int length = dArr4.length;
        if (length == 0) {
            throw new NoDataException();
        }
        checkAllFiniteReal(dArr);
        checkAllFiniteReal(dArr2);
        checkAllFiniteReal(dArr3);
        MathArrays.checkOrder(dArr);
        int i = 0;
        char c = 1;
        if (length == 1) {
            return new double[]{dArr5[0]};
        }
        int i2 = 2;
        if (length == 2) {
            return new double[]{dArr5[0], dArr5[1]};
        }
        int i3 = (int) (this.bandwidth * ((double) length));
        if (i3 < 2) {
            throw new NumberIsTooSmallException(LocalizedFormats.BANDWIDTH, Integer.valueOf(i3), Integer.valueOf(2), true);
        }
        double[] dArr7 = new double[length];
        double[] dArr8 = new double[length];
        double[] dArr9 = new double[length];
        double[] dArr10 = new double[length];
        Arrays.fill(dArr10, 1.0d);
        int i4 = 0;
        while (i4 <= this.robustnessIters) {
            int[] iArr = new int[i2];
            iArr[i] = i;
            iArr[c] = i3 - 1;
            int i5 = i;
            while (true) {
                double d = 0.0d;
                if (i5 >= length) {
                    break;
                }
                double d2 = dArr4[i5];
                if (i5 > 0) {
                    updateBandwidthInterval(dArr4, dArr6, i5, iArr);
                }
                int i6 = iArr[i];
                int i7 = iArr[c];
                double abs = FastMath.abs(1.0d / (dArr4[dArr4[i5] - dArr4[i6] > dArr4[i7] - dArr4[i5] ? i6 : i7] - d2));
                int i8 = i3;
                double d3 = 0.0d;
                double d4 = 0.0d;
                double d5 = 0.0d;
                double d6 = 0.0d;
                double d7 = 0.0d;
                int i9 = i6;
                while (i9 <= i7) {
                    double d8 = dArr4[i9];
                    double d9 = dArr5[i9];
                    double[] dArr11 = dArr8;
                    double tricube = tricube((i9 < i5 ? d2 - d8 : d8 - d2) * abs) * dArr10[i9] * dArr6[i9];
                    double d10 = d8 * tricube;
                    d4 += tricube;
                    d3 += d10;
                    d7 += d8 * d10;
                    d5 += tricube * d9;
                    d6 += d9 * d10;
                    i9++;
                    dArr8 = dArr11;
                    dArr9 = dArr9;
                }
                double[] dArr12 = dArr8;
                double[] dArr13 = dArr9;
                double d11 = d3 / d4;
                double d12 = d5 / d4;
                double d13 = d6 / d4;
                double d14 = (d7 / d4) - (d11 * d11);
                if (FastMath.sqrt(FastMath.abs(d14)) >= this.accuracy) {
                    d = (d13 - (d11 * d12)) / d14;
                }
                dArr7[i5] = (d * d2) + (d12 - (d11 * d));
                dArr12[i5] = FastMath.abs(dArr5[i5] - dArr7[i5]);
                i5++;
                i3 = i8;
                dArr8 = dArr12;
                dArr9 = dArr13;
                i = 0;
                c = 1;
            }
            int i10 = i3;
            double[] dArr14 = dArr8;
            double[] dArr15 = dArr9;
            if (i4 == this.robustnessIters) {
                break;
            }
            double[] dArr16 = dArr14;
            double[] dArr17 = dArr15;
            System.arraycopy(dArr16, 0, dArr17, 0, length);
            Arrays.sort(dArr17);
            double d15 = dArr17[length / 2];
            if (FastMath.abs(d15) < this.accuracy) {
                break;
            }
            for (int i11 = 0; i11 < length; i11++) {
                double d16 = dArr16[i11] / (6.0d * d15);
                if (d16 >= 1.0d) {
                    dArr10[i11] = 0.0d;
                } else {
                    double d17 = 1.0d - (d16 * d16);
                    dArr10[i11] = d17 * d17;
                }
            }
            i4++;
            dArr8 = dArr16;
            dArr9 = dArr17;
            i3 = i10;
            i = 0;
            c = 1;
            i2 = 2;
        }
        return dArr7;
    }

    public final double[] smooth(double[] dArr, double[] dArr2) throws NonMonotonicSequenceException, DimensionMismatchException, NoDataException, NotFiniteNumberException, NumberIsTooSmallException {
        if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        }
        double[] dArr3 = new double[dArr.length];
        Arrays.fill(dArr3, 1.0d);
        return smooth(dArr, dArr2, dArr3);
    }

    private static void updateBandwidthInterval(double[] dArr, double[] dArr2, int i, int[] iArr) {
        int i2 = iArr[0];
        int nextNonzero = nextNonzero(dArr2, iArr[1]);
        if (nextNonzero < dArr.length && dArr[nextNonzero] - dArr[i] < dArr[i] - dArr[i2]) {
            iArr[0] = nextNonzero(dArr2, iArr[0]);
            iArr[1] = nextNonzero;
        }
    }

    private static int nextNonzero(double[] dArr, int i) {
        while (true) {
            i++;
            if (i >= dArr.length || dArr[i] != 0.0d) {
                return i;
            }
        }
        return i;
    }

    private static double tricube(double d) {
        double abs = FastMath.abs(d);
        if (abs >= 1.0d) {
            return 0.0d;
        }
        double d2 = 1.0d - ((abs * abs) * abs);
        return d2 * d2 * d2;
    }

    private static void checkAllFiniteReal(double[] dArr) {
        for (double checkFinite : dArr) {
            MathUtils.checkFinite(checkFinite);
        }
    }
}
