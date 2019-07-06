package org.apache.commons.math3.analysis.function;

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.FastMath;

public class HarmonicOscillator implements DifferentiableUnivariateFunction, UnivariateDifferentiableFunction {
    private final double amplitude;
    private final double omega;
    private final double phase;

    public static class Parametric implements ParametricUnivariateFunction {
        public double value(double d, double... dArr) throws NullArgumentException, DimensionMismatchException {
            validateParameters(dArr);
            return HarmonicOscillator.value((d * dArr[1]) + dArr[2], dArr[0]);
        }

        public double[] gradient(double d, double... dArr) throws NullArgumentException, DimensionMismatchException {
            validateParameters(dArr);
            double d2 = dArr[0];
            double d3 = (dArr[1] * d) + dArr[2];
            double sin = (-d2) * FastMath.sin(d3);
            return new double[]{HarmonicOscillator.value(d3, 1.0d), d * sin, sin};
        }

        private void validateParameters(double[] dArr) throws NullArgumentException, DimensionMismatchException {
            if (dArr == null) {
                throw new NullArgumentException();
            } else if (dArr.length != 3) {
                throw new DimensionMismatchException(dArr.length, 3);
            }
        }
    }

    public HarmonicOscillator(double d, double d2, double d3) {
        this.amplitude = d;
        this.omega = d2;
        this.phase = d3;
    }

    public double value(double d) {
        return value((this.omega * d) + this.phase, this.amplitude);
    }

    @Deprecated
    public UnivariateFunction derivative() {
        return FunctionUtils.toDifferentiableUnivariateFunction(this).derivative();
    }

    /* access modifiers changed from: private */
    public static double value(double d, double d2) {
        return d2 * FastMath.cos(d);
    }

    public DerivativeStructure value(DerivativeStructure derivativeStructure) {
        double[] dArr = new double[(derivativeStructure.getOrder() + 1)];
        double value = (this.omega * derivativeStructure.getValue()) + this.phase;
        dArr[0] = this.amplitude * FastMath.cos(value);
        if (dArr.length > 1) {
            dArr[1] = (-this.amplitude) * this.omega * FastMath.sin(value);
            double d = (-this.omega) * this.omega;
            for (int i = 2; i < dArr.length; i++) {
                dArr[i] = dArr[i - 2] * d;
            }
        }
        return derivativeStructure.compose(dArr);
    }
}
