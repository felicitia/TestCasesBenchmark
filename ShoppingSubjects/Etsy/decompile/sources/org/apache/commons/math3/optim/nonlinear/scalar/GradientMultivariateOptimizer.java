package org.apache.commons.math3.optim.nonlinear.scalar;

import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;

public abstract class GradientMultivariateOptimizer extends MultivariateOptimizer {
    private MultivariateVectorFunction gradient;

    protected GradientMultivariateOptimizer(ConvergenceChecker<PointValuePair> convergenceChecker) {
        super(convergenceChecker);
    }

    /* access modifiers changed from: protected */
    public double[] computeObjectiveGradient(double[] dArr) {
        return this.gradient.value(dArr);
    }

    public PointValuePair optimize(OptimizationData... optimizationDataArr) throws TooManyEvaluationsException {
        parseOptimizationData(optimizationDataArr);
        return super.optimize(optimizationDataArr);
    }

    private void parseOptimizationData(OptimizationData... optimizationDataArr) {
        for (ObjectiveFunctionGradient objectiveFunctionGradient : optimizationDataArr) {
            if (objectiveFunctionGradient instanceof ObjectiveFunctionGradient) {
                this.gradient = objectiveFunctionGradient.getObjectiveFunctionGradient();
                return;
            }
        }
    }
}
