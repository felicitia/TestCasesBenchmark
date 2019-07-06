package org.apache.commons.math3.optimization.linear;

import java.util.Collection;
import java.util.Collections;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.PointValuePair;

@Deprecated
public abstract class AbstractLinearOptimizer implements LinearOptimizer {
    public static final int DEFAULT_MAX_ITERATIONS = 100;
    private LinearObjectiveFunction function;
    private GoalType goal;
    private int iterations;
    private Collection<LinearConstraint> linearConstraints;
    private int maxIterations;
    private boolean nonNegative;

    /* access modifiers changed from: protected */
    public abstract PointValuePair doOptimize() throws MathIllegalStateException;

    protected AbstractLinearOptimizer() {
        setMaxIterations(100);
    }

    /* access modifiers changed from: protected */
    public boolean restrictToNonNegative() {
        return this.nonNegative;
    }

    /* access modifiers changed from: protected */
    public GoalType getGoalType() {
        return this.goal;
    }

    /* access modifiers changed from: protected */
    public LinearObjectiveFunction getFunction() {
        return this.function;
    }

    /* access modifiers changed from: protected */
    public Collection<LinearConstraint> getConstraints() {
        return Collections.unmodifiableCollection(this.linearConstraints);
    }

    public void setMaxIterations(int i) {
        this.maxIterations = i;
    }

    public int getMaxIterations() {
        return this.maxIterations;
    }

    public int getIterations() {
        return this.iterations;
    }

    /* access modifiers changed from: protected */
    public void incrementIterationsCounter() throws MaxCountExceededException {
        int i = this.iterations + 1;
        this.iterations = i;
        if (i > this.maxIterations) {
            throw new MaxCountExceededException(Integer.valueOf(this.maxIterations));
        }
    }

    public PointValuePair optimize(LinearObjectiveFunction linearObjectiveFunction, Collection<LinearConstraint> collection, GoalType goalType, boolean z) throws MathIllegalStateException {
        this.function = linearObjectiveFunction;
        this.linearConstraints = collection;
        this.goal = goalType;
        this.nonNegative = z;
        this.iterations = 0;
        return doOptimize();
    }
}
