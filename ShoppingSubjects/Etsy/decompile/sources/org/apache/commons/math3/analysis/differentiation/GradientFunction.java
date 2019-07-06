package org.apache.commons.math3.analysis.differentiation;

import org.apache.commons.math3.analysis.MultivariateVectorFunction;

public class GradientFunction implements MultivariateVectorFunction {
    private final MultivariateDifferentiableFunction f;

    public GradientFunction(MultivariateDifferentiableFunction multivariateDifferentiableFunction) {
        this.f = multivariateDifferentiableFunction;
    }

    public double[] value(double[] dArr) throws IllegalArgumentException {
        DerivativeStructure[] derivativeStructureArr = new DerivativeStructure[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            DerivativeStructure derivativeStructure = new DerivativeStructure(dArr.length, 1, i, dArr[i]);
            derivativeStructureArr[i] = derivativeStructure;
        }
        DerivativeStructure value = this.f.value(derivativeStructureArr);
        double[] dArr2 = new double[dArr.length];
        int[] iArr = new int[dArr.length];
        for (int i2 = 0; i2 < dArr.length; i2++) {
            iArr[i2] = 1;
            dArr2[i2] = value.getPartialDerivative(iArr);
            iArr[i2] = 0;
        }
        return dArr2;
    }
}
