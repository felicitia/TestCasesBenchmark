package org.apache.commons.math3.analysis.function;

import java.util.Arrays;
import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

public class Gaussian implements DifferentiableUnivariateFunction, UnivariateDifferentiableFunction {
    private final double i2s2;
    private final double is;
    private final double mean;
    private final double norm;

    public static class Parametric implements ParametricUnivariateFunction {
        public double value(double d, double... dArr) throws NullArgumentException, DimensionMismatchException, NotStrictlyPositiveException {
            validateParameters(dArr);
            return Gaussian.value(d - dArr[1], dArr[0], 1.0d / ((2.0d * dArr[2]) * dArr[2]));
        }

        public double[] gradient(double d, double... dArr) throws NullArgumentException, DimensionMismatchException, NotStrictlyPositiveException {
            double[] dArr2 = dArr;
            validateParameters(dArr2);
            double d2 = dArr2[0];
            double d3 = d - dArr2[1];
            double d4 = dArr2[2];
            double d5 = 1.0d / ((2.0d * d4) * d4);
            double access$000 = Gaussian.value(d3, 1.0d, d5);
            double d6 = d2 * access$000 * 2.0d * d5 * d3;
            return new double[]{access$000, d6, (d3 * d6) / d4};
        }

        private void validateParameters(double[] dArr) throws NullArgumentException, DimensionMismatchException, NotStrictlyPositiveException {
            if (dArr == null) {
                throw new NullArgumentException();
            } else if (dArr.length != 3) {
                throw new DimensionMismatchException(dArr.length, 3);
            } else if (dArr[2] <= 0.0d) {
                throw new NotStrictlyPositiveException(Double.valueOf(dArr[2]));
            }
        }
    }

    public Gaussian(double d, double d2, double d3) throws NotStrictlyPositiveException {
        if (d3 <= 0.0d) {
            throw new NotStrictlyPositiveException(Double.valueOf(d3));
        }
        this.norm = d;
        this.mean = d2;
        this.is = 1.0d / d3;
        this.i2s2 = 0.5d * this.is * this.is;
    }

    public Gaussian(double d, double d2) throws NotStrictlyPositiveException {
        this(1.0d / (FastMath.sqrt(6.283185307179586d) * d2), d, d2);
    }

    public Gaussian() {
        this(0.0d, 1.0d);
    }

    public double value(double d) {
        return value(d - this.mean, this.norm, this.i2s2);
    }

    @Deprecated
    public UnivariateFunction derivative() {
        return FunctionUtils.toDifferentiableUnivariateFunction(this).derivative();
    }

    /* access modifiers changed from: private */
    public static double value(double d, double d2, double d3) {
        return d2 * FastMath.exp((-d) * d * d3);
    }

    public DerivativeStructure value(DerivativeStructure derivativeStructure) {
        double d;
        boolean z;
        double value = this.is * (derivativeStructure.getValue() - this.mean);
        double[] dArr = new double[(derivativeStructure.getOrder() + 1)];
        boolean z2 = false;
        double[] dArr2 = new double[dArr.length];
        dArr2[0] = 1.0d;
        double d2 = value * value;
        double exp = this.norm * FastMath.exp(-0.5d * d2);
        if (exp <= Precision.SAFE_MIN) {
            Arrays.fill(dArr, 0.0d);
        } else {
            dArr[0] = exp;
            int i = 1;
            while (i < dArr.length) {
                dArr2[i] = -dArr2[i - 1];
                int i2 = i;
                double d3 = 0.0d;
                while (i2 >= 0) {
                    d3 = (d3 * d2) + dArr2[i2];
                    if (i2 > 2) {
                        int i3 = i2 - 1;
                        d = d2;
                        dArr2[i2 - 2] = (((double) i3) * dArr2[i3]) - dArr2[i2 - 3];
                    } else {
                        d = d2;
                        if (i2 == 2) {
                            z = false;
                            dArr2[0] = dArr2[1];
                            i2 -= 2;
                            z2 = z;
                            d2 = d;
                        }
                    }
                    z = false;
                    i2 -= 2;
                    z2 = z;
                    d2 = d;
                }
                double d4 = d2;
                boolean z3 = z2;
                if ((i & 1) == 1) {
                    d3 *= value;
                }
                exp *= this.is;
                dArr[i] = d3 * exp;
                i++;
                z2 = z3;
                d2 = d4;
            }
        }
        return derivativeStructure.compose(dArr);
    }
}
