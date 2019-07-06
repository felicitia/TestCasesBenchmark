package org.apache.commons.math3.analysis.differentiation;

import java.lang.reflect.Array;
import org.apache.commons.math3.analysis.MultivariateMatrixFunction;

public class JacobianFunction implements MultivariateMatrixFunction {
    private final MultivariateDifferentiableVectorFunction f;

    public JacobianFunction(MultivariateDifferentiableVectorFunction multivariateDifferentiableVectorFunction) {
        this.f = multivariateDifferentiableVectorFunction;
    }

    public double[][] value(double[] dArr) throws IllegalArgumentException {
        DerivativeStructure[] derivativeStructureArr = new DerivativeStructure[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            DerivativeStructure derivativeStructure = new DerivativeStructure(dArr.length, 1, i, dArr[i]);
            derivativeStructureArr[i] = derivativeStructure;
        }
        DerivativeStructure[] value = this.f.value(derivativeStructureArr);
        double[][] dArr2 = (double[][]) Array.newInstance(double.class, new int[]{value.length, dArr.length});
        int[] iArr = new int[dArr.length];
        for (int i2 = 0; i2 < value.length; i2++) {
            for (int i3 = 0; i3 < dArr.length; i3++) {
                iArr[i3] = 1;
                dArr2[i2][i3] = value[i2].getPartialDerivative(iArr);
                iArr[i3] = 0;
            }
        }
        return dArr2;
    }
}
