package org.apache.commons.math3.optimization;

import org.apache.commons.math3.util.Precision;

@Deprecated
public abstract class AbstractConvergenceChecker<PAIR> implements ConvergenceChecker<PAIR> {
    @Deprecated
    private static final double DEFAULT_ABSOLUTE_THRESHOLD = (100.0d * Precision.SAFE_MIN);
    @Deprecated
    private static final double DEFAULT_RELATIVE_THRESHOLD = (Precision.EPSILON * 100.0d);
    private final double absoluteThreshold;
    private final double relativeThreshold;

    public abstract boolean converged(int i, PAIR pair, PAIR pair2);

    @Deprecated
    public AbstractConvergenceChecker() {
        this.relativeThreshold = DEFAULT_RELATIVE_THRESHOLD;
        this.absoluteThreshold = DEFAULT_ABSOLUTE_THRESHOLD;
    }

    public AbstractConvergenceChecker(double d, double d2) {
        this.relativeThreshold = d;
        this.absoluteThreshold = d2;
    }

    public double getRelativeThreshold() {
        return this.relativeThreshold;
    }

    public double getAbsoluteThreshold() {
        return this.absoluteThreshold;
    }
}
