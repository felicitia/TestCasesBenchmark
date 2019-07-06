package org.apache.commons.math3.analysis.function;

import org.apache.commons.math3.analysis.BivariateFunction;
import org.apache.commons.math3.util.FastMath;

public class Max implements BivariateFunction {
    public double value(double d, double d2) {
        return FastMath.max(d, d2);
    }
}
