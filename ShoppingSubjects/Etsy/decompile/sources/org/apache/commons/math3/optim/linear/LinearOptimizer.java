package org.apache.commons.math3.optim.linear;

import java.util.Collection;
import java.util.Collections;
import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer;

public abstract class LinearOptimizer extends MultivariateOptimizer {
    private LinearObjectiveFunction function;
    private Collection<LinearConstraint> linearConstraints;
    private boolean nonNegative;

    protected LinearOptimizer() {
        super(null);
    }

    /* access modifiers changed from: protected */
    public boolean isRestrictedToNonNegative() {
        return this.nonNegative;
    }

    /* access modifiers changed from: protected */
    public LinearObjectiveFunction getFunction() {
        return this.function;
    }

    /* access modifiers changed from: protected */
    public Collection<LinearConstraint> getConstraints() {
        return Collections.unmodifiableCollection(this.linearConstraints);
    }

    public PointValuePair optimize(OptimizationData... optimizationDataArr) throws TooManyIterationsException {
        parseOptimizationData(optimizationDataArr);
        return super.optimize(optimizationDataArr);
    }

    private void parseOptimizationData(OptimizationData... optimizationDataArr) {
        for (LinearObjectiveFunction linearObjectiveFunction : optimizationDataArr) {
            if (linearObjectiveFunction instanceof LinearObjectiveFunction) {
                this.function = linearObjectiveFunction;
            } else if (linearObjectiveFunction instanceof LinearConstraintSet) {
                this.linearConstraints = ((LinearConstraintSet) linearObjectiveFunction).getConstraints();
            } else if (linearObjectiveFunction instanceof NonNegativeConstraint) {
                this.nonNegative = ((NonNegativeConstraint) linearObjectiveFunction).isRestrictedToNonNegative();
            }
        }
    }
}
