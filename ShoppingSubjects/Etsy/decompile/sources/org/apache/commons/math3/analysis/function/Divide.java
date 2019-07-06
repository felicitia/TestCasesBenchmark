package org.apache.commons.math3.analysis.function;

import org.apache.commons.math3.analysis.BivariateFunction;

public class Divide implements BivariateFunction {
    public double value(double d, double d2) {
        return d / d2;
    }
}
