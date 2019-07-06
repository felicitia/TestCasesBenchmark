package org.apache.commons.math3.optimization.general;

import org.apache.commons.math3.analysis.DifferentiableMultivariateFunction;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.analysis.differentiation.MultivariateDifferentiableFunction;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.DifferentiableMultivariateOptimizer;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateOptimizer;

@Deprecated
public abstract class AbstractScalarDifferentiableOptimizer extends BaseAbstractMultivariateOptimizer<DifferentiableMultivariateFunction> implements DifferentiableMultivariateOptimizer {
    private MultivariateVectorFunction gradient;

    @Deprecated
    protected AbstractScalarDifferentiableOptimizer() {
    }

    protected AbstractScalarDifferentiableOptimizer(ConvergenceChecker<PointValuePair> convergenceChecker) {
        super(convergenceChecker);
    }

    /* access modifiers changed from: protected */
    public double[] computeObjectiveGradient(double[] dArr) {
        return this.gradient.value(dArr);
    }

    /* access modifiers changed from: protected */
    public PointValuePair optimizeInternal(int i, DifferentiableMultivariateFunction differentiableMultivariateFunction, GoalType goalType, double[] dArr) {
        this.gradient = differentiableMultivariateFunction.gradient();
        return super.optimizeInternal(i, differentiableMultivariateFunction, goalType, dArr);
    }

    public PointValuePair optimize(int i, MultivariateDifferentiableFunction multivariateDifferentiableFunction, GoalType goalType, double[] dArr) {
        return optimizeInternal(i, FunctionUtils.toDifferentiableMultivariateFunction(multivariateDifferentiableFunction), goalType, dArr);
    }
}
