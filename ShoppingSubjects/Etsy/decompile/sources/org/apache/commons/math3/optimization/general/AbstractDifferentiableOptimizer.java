package org.apache.commons.math3.optimization.general;

import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.analysis.differentiation.GradientFunction;
import org.apache.commons.math3.analysis.differentiation.MultivariateDifferentiableFunction;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.InitialGuess;
import org.apache.commons.math3.optimization.OptimizationData;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateOptimizer;

@Deprecated
public abstract class AbstractDifferentiableOptimizer extends BaseAbstractMultivariateOptimizer<MultivariateDifferentiableFunction> {
    private MultivariateVectorFunction gradient;

    protected AbstractDifferentiableOptimizer(ConvergenceChecker<PointValuePair> convergenceChecker) {
        super(convergenceChecker);
    }

    /* access modifiers changed from: protected */
    public double[] computeObjectiveGradient(double[] dArr) {
        return this.gradient.value(dArr);
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public PointValuePair optimizeInternal(int i, MultivariateDifferentiableFunction multivariateDifferentiableFunction, GoalType goalType, double[] dArr) {
        return optimizeInternal(i, multivariateDifferentiableFunction, goalType, new InitialGuess(dArr));
    }

    /* access modifiers changed from: protected */
    public PointValuePair optimizeInternal(int i, MultivariateDifferentiableFunction multivariateDifferentiableFunction, GoalType goalType, OptimizationData... optimizationDataArr) {
        this.gradient = new GradientFunction(multivariateDifferentiableFunction);
        return super.optimizeInternal(i, multivariateDifferentiableFunction, goalType, optimizationDataArr);
    }
}
