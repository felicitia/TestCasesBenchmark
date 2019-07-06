package org.apache.commons.math3.optim.nonlinear.vector;

import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.optim.OptimizationData;

public class ModelFunction implements OptimizationData {
    private final MultivariateVectorFunction model;

    public ModelFunction(MultivariateVectorFunction multivariateVectorFunction) {
        this.model = multivariateVectorFunction;
    }

    public MultivariateVectorFunction getModelFunction() {
        return this.model;
    }
}
