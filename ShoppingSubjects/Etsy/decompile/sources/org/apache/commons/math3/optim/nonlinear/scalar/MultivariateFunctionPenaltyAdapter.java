package org.apache.commons.math3.optim.nonlinear.scalar;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class MultivariateFunctionPenaltyAdapter implements MultivariateFunction {
    private final MultivariateFunction bounded;
    private final double[] lower;
    private final double offset;
    private final double[] scale;
    private final double[] upper;

    public MultivariateFunctionPenaltyAdapter(MultivariateFunction multivariateFunction, double[] dArr, double[] dArr2, double d, double[] dArr3) {
        MathUtils.checkNotNull(dArr);
        MathUtils.checkNotNull(dArr2);
        MathUtils.checkNotNull(dArr3);
        if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        } else if (dArr.length != dArr3.length) {
            throw new DimensionMismatchException(dArr.length, dArr3.length);
        } else {
            for (int i = 0; i < dArr.length; i++) {
                if (dArr2[i] < dArr[i]) {
                    throw new NumberIsTooSmallException(Double.valueOf(dArr2[i]), Double.valueOf(dArr[i]), true);
                }
            }
            this.bounded = multivariateFunction;
            this.lower = (double[]) dArr.clone();
            this.upper = (double[]) dArr2.clone();
            this.offset = d;
            this.scale = (double[]) dArr3.clone();
        }
    }

    public double value(double[] dArr) {
        int i = 0;
        while (i < this.scale.length) {
            if (dArr[i] < this.lower[i] || dArr[i] > this.upper[i]) {
                double d = 0.0d;
                while (i < this.scale.length) {
                    double d2 = dArr[i] < this.lower[i] ? this.scale[i] * (this.lower[i] - dArr[i]) : dArr[i] > this.upper[i] ? this.scale[i] * (dArr[i] - this.upper[i]) : 0.0d;
                    d += FastMath.sqrt(d2);
                    i++;
                }
                return this.offset + d;
            }
            i++;
        }
        return this.bounded.value(dArr);
    }
}
