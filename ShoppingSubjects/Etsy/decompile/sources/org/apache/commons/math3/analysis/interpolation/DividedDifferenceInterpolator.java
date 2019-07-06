package org.apache.commons.math3.analysis.interpolation;

import java.io.Serializable;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;

public class DividedDifferenceInterpolator implements Serializable, UnivariateInterpolator {
    private static final long serialVersionUID = 107049519551235069L;

    public PolynomialFunctionNewtonForm interpolate(double[] dArr, double[] dArr2) throws DimensionMismatchException, NumberIsTooSmallException, NonMonotonicSequenceException {
        PolynomialFunctionLagrangeForm.verifyInterpolationArray(dArr, dArr2, true);
        double[] dArr3 = new double[(dArr.length - 1)];
        System.arraycopy(dArr, 0, dArr3, 0, dArr3.length);
        return new PolynomialFunctionNewtonForm(computeDividedDifference(dArr, dArr2), dArr3);
    }

    protected static double[] computeDividedDifference(double[] dArr, double[] dArr2) throws DimensionMismatchException, NumberIsTooSmallException, NonMonotonicSequenceException {
        PolynomialFunctionLagrangeForm.verifyInterpolationArray(dArr, dArr2, true);
        double[] dArr3 = (double[]) dArr2.clone();
        int length = dArr.length;
        double[] dArr4 = new double[length];
        dArr4[0] = dArr3[0];
        for (int i = 1; i < length; i++) {
            int i2 = 0;
            while (i2 < length - i) {
                int i3 = i2 + 1;
                dArr3[i2] = (dArr3[i3] - dArr3[i2]) / (dArr[i2 + i] - dArr[i2]);
                i2 = i3;
            }
            dArr4[i] = dArr3[0];
        }
        return dArr4;
    }
}
