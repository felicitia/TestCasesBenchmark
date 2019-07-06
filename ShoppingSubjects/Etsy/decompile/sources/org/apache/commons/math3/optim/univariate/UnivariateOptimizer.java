package org.apache.commons.math3.optim.univariate;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.optim.BaseOptimizer;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

public abstract class UnivariateOptimizer extends BaseOptimizer<UnivariatePointValuePair> {
    private UnivariateFunction function;
    private GoalType goal;
    private double max;
    private double min;
    private double start;

    protected UnivariateOptimizer(ConvergenceChecker<UnivariatePointValuePair> convergenceChecker) {
        super(convergenceChecker);
    }

    public UnivariatePointValuePair optimize(OptimizationData... optimizationDataArr) throws TooManyEvaluationsException {
        parseOptimizationData(optimizationDataArr);
        return (UnivariatePointValuePair) super.optimize(optimizationDataArr);
    }

    public GoalType getGoalType() {
        return this.goal;
    }

    private void parseOptimizationData(OptimizationData... optimizationDataArr) {
        for (SearchInterval searchInterval : optimizationDataArr) {
            if (searchInterval instanceof SearchInterval) {
                SearchInterval searchInterval2 = searchInterval;
                this.min = searchInterval2.getMin();
                this.max = searchInterval2.getMax();
                this.start = searchInterval2.getStartValue();
            } else if (searchInterval instanceof UnivariateObjectiveFunction) {
                this.function = ((UnivariateObjectiveFunction) searchInterval).getObjectiveFunction();
            } else if (searchInterval instanceof GoalType) {
                this.goal = (GoalType) searchInterval;
            }
        }
    }

    public double getStartValue() {
        return this.start;
    }

    public double getMin() {
        return this.min;
    }

    public double getMax() {
        return this.max;
    }

    /* access modifiers changed from: protected */
    public double computeObjectiveValue(double d) {
        super.incrementEvaluationCount();
        return this.function.value(d);
    }
}
