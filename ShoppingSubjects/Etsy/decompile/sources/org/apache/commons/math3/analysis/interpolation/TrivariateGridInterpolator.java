package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.TrivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;

public interface TrivariateGridInterpolator {
    TrivariateFunction interpolate(double[] dArr, double[] dArr2, double[] dArr3, double[][][] dArr4) throws NoDataException, DimensionMismatchException;
}
