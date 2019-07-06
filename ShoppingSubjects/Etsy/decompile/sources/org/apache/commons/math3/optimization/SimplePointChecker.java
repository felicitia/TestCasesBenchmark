package org.apache.commons.math3.optimization;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Pair;

@Deprecated
public class SimplePointChecker<PAIR extends Pair<double[], ? extends Object>> extends AbstractConvergenceChecker<PAIR> {
    private static final int ITERATION_CHECK_DISABLED = -1;
    private final int maxIterationCount;

    @Deprecated
    public SimplePointChecker() {
        this.maxIterationCount = -1;
    }

    public SimplePointChecker(double d, double d2) {
        super(d, d2);
        this.maxIterationCount = -1;
    }

    public SimplePointChecker(double d, double d2, int i) {
        super(d, d2);
        if (i <= 0) {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        }
        this.maxIterationCount = i;
    }

    public boolean converged(int i, PAIR pair, PAIR pair2) {
        if (this.maxIterationCount != -1 && i >= this.maxIterationCount) {
            return true;
        }
        double[] dArr = (double[]) pair.getKey();
        double[] dArr2 = (double[]) pair2.getKey();
        for (int i2 = 0; i2 < dArr.length; i2++) {
            double d = dArr[i2];
            double d2 = dArr2[i2];
            double abs = FastMath.abs(d - d2);
            if (abs > FastMath.max(FastMath.abs(d), FastMath.abs(d2)) * getRelativeThreshold() && abs > getAbsoluteThreshold()) {
                return false;
            }
        }
        return true;
    }
}
