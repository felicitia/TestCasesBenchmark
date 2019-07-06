package org.apache.commons.math3.analysis.function;

import java.util.Arrays;
import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.FastMath;

public class Sigmoid implements DifferentiableUnivariateFunction, UnivariateDifferentiableFunction {
    private final double hi;
    private final double lo;

    public static class Parametric implements ParametricUnivariateFunction {
        public double value(double d, double... dArr) throws NullArgumentException, DimensionMismatchException {
            validateParameters(dArr);
            return Sigmoid.value(d, dArr[0], dArr[1]);
        }

        public double[] gradient(double d, double... dArr) throws NullArgumentException, DimensionMismatchException {
            validateParameters(dArr);
            double exp = 1.0d / (FastMath.exp(-d) + 1.0d);
            return new double[]{1.0d - exp, exp};
        }

        private void validateParameters(double[] dArr) throws NullArgumentException, DimensionMismatchException {
            if (dArr == null) {
                throw new NullArgumentException();
            } else if (dArr.length != 2) {
                throw new DimensionMismatchException(dArr.length, 2);
            }
        }
    }

    public Sigmoid() {
        this(0.0d, 1.0d);
    }

    public Sigmoid(double d, double d2) {
        this.lo = d;
        this.hi = d2;
    }

    @Deprecated
    public UnivariateFunction derivative() {
        return FunctionUtils.toDifferentiableUnivariateFunction(this).derivative();
    }

    public double value(double d) {
        return value(d, this.lo, this.hi);
    }

    /* access modifiers changed from: private */
    public static double value(double d, double d2, double d3) {
        return d2 + ((d3 - d2) / (1.0d + FastMath.exp(-d)));
    }

    public DerivativeStructure value(DerivativeStructure derivativeStructure) {
        char c;
        int i = 1;
        double[] dArr = new double[(derivativeStructure.getOrder() + 1)];
        double exp = FastMath.exp(-derivativeStructure.getValue());
        char c2 = 0;
        if (Double.isInfinite(exp)) {
            dArr[0] = this.lo;
            Arrays.fill(dArr, 1, dArr.length, 0.0d);
        } else {
            double d = 1.0d;
            double[] dArr2 = new double[dArr.length];
            double d2 = 1.0d / (1.0d + exp);
            double d3 = this.hi - this.lo;
            int i2 = 0;
            while (i2 < dArr.length) {
                dArr2[i2] = d;
                int i3 = i2;
                double d4 = 0.0d;
                while (i3 >= 0) {
                    d4 = (d4 * exp) + dArr2[i3];
                    if (i3 > i) {
                        int i4 = i3 - 1;
                        dArr2[i4] = (((double) ((i2 - i3) + 2)) * dArr2[i3 - 2]) - (((double) i4) * dArr2[i4]);
                        c = 0;
                    } else {
                        c = c2;
                        dArr2[c] = 0.0d;
                    }
                    i3--;
                    c2 = c;
                    i = 1;
                }
                char c3 = c2;
                d3 *= d2;
                dArr[i2] = d4 * d3;
                i2++;
                c2 = c3;
                i = 1;
                d = 1.0d;
            }
            char c4 = c2;
            dArr[c4] = dArr[c4] + this.lo;
        }
        return derivativeStructure.compose(dArr);
    }
}
