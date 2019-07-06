package org.apache.commons.math3.optimization.univariate;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.util.Incrementor;

@Deprecated
public abstract class BaseAbstractUnivariateOptimizer implements UnivariateOptimizer {
    private final ConvergenceChecker<UnivariatePointValuePair> checker;
    private final Incrementor evaluations = new Incrementor();
    private UnivariateFunction function;
    private GoalType goal;
    private double searchMax;
    private double searchMin;
    private double searchStart;

    /* access modifiers changed from: protected */
    public abstract UnivariatePointValuePair doOptimize();

    protected BaseAbstractUnivariateOptimizer(ConvergenceChecker<UnivariatePointValuePair> convergenceChecker) {
        this.checker = convergenceChecker;
    }

    public int getMaxEvaluations() {
        return this.evaluations.getMaximalCount();
    }

    public int getEvaluations() {
        return this.evaluations.getCount();
    }

    public GoalType getGoalType() {
        return this.goal;
    }

    public double getMin() {
        return this.searchMin;
    }

    public double getMax() {
        return this.searchMax;
    }

    public double getStartValue() {
        return this.searchStart;
    }

    /* access modifiers changed from: protected */
    public double computeObjectiveValue(double d) {
        try {
            this.evaluations.incrementCount();
            return this.function.value(d);
        } catch (MaxCountExceededException e) {
            throw new TooManyEvaluationsException(e.getMax());
        }
    }

    public UnivariatePointValuePair optimize(int i, UnivariateFunction univariateFunction, GoalType goalType, double d, double d2, double d3) {
        if (univariateFunction == null) {
            throw new NullArgumentException();
        } else if (goalType == null) {
            throw new NullArgumentException();
        } else {
            this.searchMin = d;
            this.searchMax = d2;
            this.searchStart = d3;
            this.goal = goalType;
            this.function = univariateFunction;
            this.evaluations.setMaximalCount(i);
            this.evaluations.resetCount();
            return doOptimize();
        }
    }

    public UnivariatePointValuePair optimize(int i, UnivariateFunction univariateFunction, GoalType goalType, double d, double d2) {
        return optimize(i, univariateFunction, goalType, d, d2, d + (0.5d * (d2 - d)));
    }

    public ConvergenceChecker<UnivariatePointValuePair> getConvergenceChecker() {
        return this.checker;
    }
}
