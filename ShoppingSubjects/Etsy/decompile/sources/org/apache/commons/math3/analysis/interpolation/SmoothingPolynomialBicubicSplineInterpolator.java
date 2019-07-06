package org.apache.commons.math3.analysis.interpolation;

import java.lang.reflect.Array;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.fitting.PolynomialFitter;
import org.apache.commons.math3.optim.SimpleVectorValueChecker;
import org.apache.commons.math3.optim.nonlinear.vector.jacobian.GaussNewtonOptimizer;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Precision;

public class SmoothingPolynomialBicubicSplineInterpolator extends BicubicSplineInterpolator {
    private final int xDegree;
    private final PolynomialFitter xFitter;
    private final int yDegree;
    private final PolynomialFitter yFitter;

    public SmoothingPolynomialBicubicSplineInterpolator() {
        this(3);
    }

    public SmoothingPolynomialBicubicSplineInterpolator(int i) {
        this(i, i);
    }

    public SmoothingPolynomialBicubicSplineInterpolator(int i, int i2) {
        if (i < 0) {
            throw new NotPositiveException(Integer.valueOf(i));
        } else if (i2 < 0) {
            throw new NotPositiveException(Integer.valueOf(i2));
        } else {
            this.xDegree = i;
            this.yDegree = i2;
            SimpleVectorValueChecker simpleVectorValueChecker = new SimpleVectorValueChecker(Precision.EPSILON * 100.0d, 100.0d * Precision.SAFE_MIN);
            this.xFitter = new PolynomialFitter(new GaussNewtonOptimizer(false, simpleVectorValueChecker));
            this.yFitter = new PolynomialFitter(new GaussNewtonOptimizer(false, simpleVectorValueChecker));
        }
    }

    public BicubicSplineInterpolatingFunction interpolate(double[] dArr, double[] dArr2, double[][] dArr3) throws NoDataException, DimensionMismatchException {
        double[] dArr4 = dArr;
        double[] dArr5 = dArr2;
        double[][] dArr6 = dArr3;
        if (dArr4.length == 0 || dArr5.length == 0 || dArr6.length == 0) {
            throw new NoDataException();
        } else if (dArr4.length != dArr6.length) {
            throw new DimensionMismatchException(dArr4.length, dArr6.length);
        } else {
            int length = dArr4.length;
            int length2 = dArr5.length;
            for (int i = 0; i < length; i++) {
                if (dArr6[i].length != length2) {
                    throw new DimensionMismatchException(dArr6[i].length, length2);
                }
            }
            MathArrays.checkOrder(dArr);
            MathArrays.checkOrder(dArr2);
            PolynomialFunction[] polynomialFunctionArr = new PolynomialFunction[length2];
            for (int i2 = 0; i2 < length2; i2++) {
                this.xFitter.clearObservations();
                for (int i3 = 0; i3 < length; i3++) {
                    this.xFitter.addObservedPoint(1.0d, dArr4[i3], dArr6[i3][i2]);
                }
                polynomialFunctionArr[i2] = new PolynomialFunction(this.xFitter.fit(new double[(this.xDegree + 1)]));
            }
            double[][] dArr7 = (double[][]) Array.newInstance(double.class, new int[]{length, length2});
            for (int i4 = 0; i4 < length2; i4++) {
                PolynomialFunction polynomialFunction = polynomialFunctionArr[i4];
                for (int i5 = 0; i5 < length; i5++) {
                    dArr7[i5][i4] = polynomialFunction.value(dArr4[i5]);
                }
            }
            PolynomialFunction[] polynomialFunctionArr2 = new PolynomialFunction[length];
            for (int i6 = 0; i6 < length; i6++) {
                this.yFitter.clearObservations();
                for (int i7 = 0; i7 < length2; i7++) {
                    this.yFitter.addObservedPoint(1.0d, dArr5[i7], dArr7[i6][i7]);
                }
                polynomialFunctionArr2[i6] = new PolynomialFunction(this.yFitter.fit(new double[(this.yDegree + 1)]));
            }
            double[][] dArr8 = (double[][]) Array.newInstance(double.class, new int[]{length, length2});
            for (int i8 = 0; i8 < length; i8++) {
                PolynomialFunction polynomialFunction2 = polynomialFunctionArr2[i8];
                for (int i9 = 0; i9 < length2; i9++) {
                    dArr8[i8][i9] = polynomialFunction2.value(dArr5[i9]);
                }
            }
            return super.interpolate(dArr4, dArr5, dArr8);
        }
    }
}
