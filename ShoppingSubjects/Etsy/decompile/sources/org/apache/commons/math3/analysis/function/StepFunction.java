package org.apache.commons.math3.analysis.function;

import java.util.Arrays;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.MathArrays;

public class StepFunction implements UnivariateFunction {
    private final double[] abscissa;
    private final double[] ordinate;

    public StepFunction(double[] dArr, double[] dArr2) throws NullArgumentException, NoDataException, DimensionMismatchException {
        if (dArr == null || dArr2 == null) {
            throw new NullArgumentException();
        } else if (dArr.length == 0 || dArr2.length == 0) {
            throw new NoDataException();
        } else if (dArr2.length != dArr.length) {
            throw new DimensionMismatchException(dArr2.length, dArr.length);
        } else {
            MathArrays.checkOrder(dArr);
            this.abscissa = MathArrays.copyOf(dArr);
            this.ordinate = MathArrays.copyOf(dArr2);
        }
    }

    public double value(double d) {
        int binarySearch = Arrays.binarySearch(this.abscissa, d);
        if (binarySearch < -1) {
            return this.ordinate[(-binarySearch) - 2];
        }
        if (binarySearch >= 0) {
            return this.ordinate[binarySearch];
        }
        return this.ordinate[0];
    }
}
