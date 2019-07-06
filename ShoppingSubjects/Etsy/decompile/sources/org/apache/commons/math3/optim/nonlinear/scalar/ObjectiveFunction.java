package org.apache.commons.math3.optim.nonlinear.scalar;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.optim.OptimizationData;

public class ObjectiveFunction implements OptimizationData {
    private final MultivariateFunction function;

    public ObjectiveFunction(MultivariateFunction multivariateFunction) {
        this.function = multivariateFunction;
    }

    public MultivariateFunction getObjectiveFunction() {
        return this.function;
    }
}
