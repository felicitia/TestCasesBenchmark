package org.apache.commons.math3.optim.univariate;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.optim.OptimizationData;

public class UnivariateObjectiveFunction implements OptimizationData {
    private final UnivariateFunction function;

    public UnivariateObjectiveFunction(UnivariateFunction univariateFunction) {
        this.function = univariateFunction;
    }

    public UnivariateFunction getObjectiveFunction() {
        return this.function;
    }
}
