package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.MultivariateFunction;

public interface MultivariateInterpolator {
    MultivariateFunction interpolate(double[][] dArr, double[] dArr2);
}
