package org.apache.commons.math3.optimization;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.RealMatrix;

@Deprecated
public class LeastSquaresConverter implements MultivariateFunction {
    private final MultivariateVectorFunction function;
    private final double[] observations;
    private final RealMatrix scale;
    private final double[] weights;

    public LeastSquaresConverter(MultivariateVectorFunction multivariateVectorFunction, double[] dArr) {
        this.function = multivariateVectorFunction;
        this.observations = (double[]) dArr.clone();
        this.weights = null;
        this.scale = null;
    }

    public LeastSquaresConverter(MultivariateVectorFunction multivariateVectorFunction, double[] dArr, double[] dArr2) {
        if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        }
        this.function = multivariateVectorFunction;
        this.observations = (double[]) dArr.clone();
        this.weights = (double[]) dArr2.clone();
        this.scale = null;
    }

    public LeastSquaresConverter(MultivariateVectorFunction multivariateVectorFunction, double[] dArr, RealMatrix realMatrix) {
        if (dArr.length != realMatrix.getColumnDimension()) {
            throw new DimensionMismatchException(dArr.length, realMatrix.getColumnDimension());
        }
        this.function = multivariateVectorFunction;
        this.observations = (double[]) dArr.clone();
        this.weights = null;
        this.scale = realMatrix.copy();
    }

    public double value(double[] dArr) {
        double[] value = this.function.value(dArr);
        if (value.length != this.observations.length) {
            throw new DimensionMismatchException(value.length, this.observations.length);
        }
        int i = 0;
        for (int i2 = 0; i2 < value.length; i2++) {
            value[i2] = value[i2] - this.observations[i2];
        }
        double d = 0.0d;
        if (this.weights != null) {
            while (i < value.length) {
                double d2 = value[i];
                d += this.weights[i] * d2 * d2;
                i++;
            }
        } else if (this.scale != null) {
            double[] operate = this.scale.operate(value);
            int length = operate.length;
            while (i < length) {
                double d3 = operate[i];
                d += d3 * d3;
                i++;
            }
        } else {
            int length2 = value.length;
            while (i < length2) {
                double d4 = value[i];
                d += d4 * d4;
                i++;
            }
        }
        return d;
    }
}
