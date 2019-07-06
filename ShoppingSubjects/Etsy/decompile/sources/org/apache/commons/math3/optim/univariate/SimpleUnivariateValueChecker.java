package org.apache.commons.math3.optim.univariate;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.optim.AbstractConvergenceChecker;
import org.apache.commons.math3.util.FastMath;

public class SimpleUnivariateValueChecker extends AbstractConvergenceChecker<UnivariatePointValuePair> {
    private static final int ITERATION_CHECK_DISABLED = -1;
    private final int maxIterationCount;

    public SimpleUnivariateValueChecker(double d, double d2) {
        super(d, d2);
        this.maxIterationCount = -1;
    }

    public SimpleUnivariateValueChecker(double d, double d2, int i) {
        super(d, d2);
        if (i <= 0) {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        }
        this.maxIterationCount = i;
    }

    public boolean converged(int i, UnivariatePointValuePair univariatePointValuePair, UnivariatePointValuePair univariatePointValuePair2) {
        boolean z = true;
        if (this.maxIterationCount != -1 && i >= this.maxIterationCount) {
            return true;
        }
        double value = univariatePointValuePair.getValue();
        double value2 = univariatePointValuePair2.getValue();
        double abs = FastMath.abs(value - value2);
        if (abs > FastMath.max(FastMath.abs(value), FastMath.abs(value2)) * getRelativeThreshold() && abs > getAbsoluteThreshold()) {
            z = false;
        }
        return z;
    }
}
