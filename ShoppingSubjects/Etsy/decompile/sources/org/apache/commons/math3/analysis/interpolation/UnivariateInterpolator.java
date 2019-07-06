package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.UnivariateFunction;

public interface UnivariateInterpolator {
    UnivariateFunction interpolate(double[] dArr, double[] dArr2);
}
