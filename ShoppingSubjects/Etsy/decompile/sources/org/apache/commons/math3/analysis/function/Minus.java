package org.apache.commons.math3.analysis.function;

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;

public class Minus implements DifferentiableUnivariateFunction, UnivariateDifferentiableFunction {
    public double value(double d) {
        return -d;
    }

    @Deprecated
    public DifferentiableUnivariateFunction derivative() {
        return new Constant(-1.0d);
    }

    public DerivativeStructure value(DerivativeStructure derivativeStructure) {
        return derivativeStructure.negate();
    }
}
