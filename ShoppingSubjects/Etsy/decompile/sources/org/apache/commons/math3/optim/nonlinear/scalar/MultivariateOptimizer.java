package org.apache.commons.math3.optim.nonlinear.scalar;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.optim.BaseMultivariateOptimizer;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;

public abstract class MultivariateOptimizer extends BaseMultivariateOptimizer<PointValuePair> {
    private MultivariateFunction function;
    private GoalType goal;

    protected MultivariateOptimizer(ConvergenceChecker<PointValuePair> convergenceChecker) {
        super(convergenceChecker);
    }

    public PointValuePair optimize(OptimizationData... optimizationDataArr) throws TooManyEvaluationsException {
        parseOptimizationData(optimizationDataArr);
        return (PointValuePair) super.optimize(optimizationDataArr);
    }

    private void parseOptimizationData(OptimizationData... optimizationDataArr) {
        for (GoalType goalType : optimizationDataArr) {
            if (goalType instanceof GoalType) {
                this.goal = goalType;
            } else if (goalType instanceof ObjectiveFunction) {
                this.function = ((ObjectiveFunction) goalType).getObjectiveFunction();
            }
        }
    }

    public GoalType getGoalType() {
        return this.goal;
    }

    /* access modifiers changed from: protected */
    public double computeObjectiveValue(double[] dArr) {
        super.incrementEvaluationCount();
        return this.function.value(dArr);
    }
}
