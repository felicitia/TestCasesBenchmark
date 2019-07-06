package org.apache.commons.math3.optim;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.util.FastMath;

public class SimpleVectorValueChecker extends AbstractConvergenceChecker<PointVectorValuePair> {
    private static final int ITERATION_CHECK_DISABLED = -1;
    private final int maxIterationCount;

    public SimpleVectorValueChecker(double d, double d2) {
        super(d, d2);
        this.maxIterationCount = -1;
    }

    public SimpleVectorValueChecker(double d, double d2, int i) {
        super(d, d2);
        if (i <= 0) {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        }
        this.maxIterationCount = i;
    }

    public boolean converged(int i, PointVectorValuePair pointVectorValuePair, PointVectorValuePair pointVectorValuePair2) {
        if (this.maxIterationCount != -1 && i >= this.maxIterationCount) {
            return true;
        }
        double[] valueRef = pointVectorValuePair.getValueRef();
        double[] valueRef2 = pointVectorValuePair2.getValueRef();
        for (int i2 = 0; i2 < valueRef.length; i2++) {
            double d = valueRef[i2];
            double d2 = valueRef2[i2];
            double abs = FastMath.abs(d - d2);
            if (abs > FastMath.max(FastMath.abs(d), FastMath.abs(d2)) * getRelativeThreshold() && abs > getAbsoluteThreshold()) {
                return false;
            }
        }
        return true;
    }
}
