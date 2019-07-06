package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.BivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;

public interface BivariateGridInterpolator {
    BivariateFunction interpolate(double[] dArr, double[] dArr2, double[][] dArr3) throws NoDataException, DimensionMismatchException;
}
