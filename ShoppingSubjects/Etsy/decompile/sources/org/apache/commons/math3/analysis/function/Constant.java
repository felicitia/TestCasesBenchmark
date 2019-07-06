package org.apache.commons.math3.analysis.function;

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;

public class Constant implements DifferentiableUnivariateFunction, UnivariateDifferentiableFunction {
    private final double c;

    public Constant(double d) {
        this.c = d;
    }

    public double value(double d) {
        return this.c;
    }

    @Deprecated
    public DifferentiableUnivariateFunction derivative() {
        return new Constant(0.0d);
    }

    public DerivativeStructure value(DerivativeStructure derivativeStructure) {
        return new DerivativeStructure(derivativeStructure.getFreeParameters(), derivativeStructure.getOrder(), this.c);
    }
}
