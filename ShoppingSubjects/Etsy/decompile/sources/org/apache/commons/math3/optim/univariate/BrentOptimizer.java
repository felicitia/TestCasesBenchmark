package org.apache.commons.math3.optim.univariate;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

public class BrentOptimizer extends UnivariateOptimizer {
    private static final double GOLDEN_SECTION = (0.5d * (3.0d - FastMath.sqrt(5.0d)));
    private static final double MIN_RELATIVE_TOLERANCE = (2.0d * FastMath.ulp(1.0d));
    private final double absoluteThreshold;
    private final double relativeThreshold;

    public BrentOptimizer(double d, double d2, ConvergenceChecker<UnivariatePointValuePair> convergenceChecker) {
        super(convergenceChecker);
        if (d < MIN_RELATIVE_TOLERANCE) {
            throw new NumberIsTooSmallException(Double.valueOf(d), Double.valueOf(MIN_RELATIVE_TOLERANCE), true);
        } else if (d2 <= 0.0d) {
            throw new NotStrictlyPositiveException(Double.valueOf(d2));
        } else {
            this.relativeThreshold = d;
            this.absoluteThreshold = d2;
        }
    }

    public BrentOptimizer(double d, double d2) {
        this(d, d2, null);
    }

    /* access modifiers changed from: protected */
    public UnivariatePointValuePair doOptimize() {
        double d;
        double d2;
        double d3;
        double d4;
        int i;
        ConvergenceChecker convergenceChecker;
        double d5;
        double d6;
        double d7;
        double d8;
        boolean z = getGoalType() == GoalType.MINIMIZE;
        double min = getMin();
        double startValue = getStartValue();
        double max = getMax();
        ConvergenceChecker convergenceChecker2 = getConvergenceChecker();
        if (min >= max) {
            double d9 = min;
            min = max;
            max = d9;
        }
        double computeObjectiveValue = computeObjectiveValue(startValue);
        if (!z) {
            computeObjectiveValue = -computeObjectiveValue;
        }
        double d10 = startValue;
        double d11 = computeObjectiveValue;
        double d12 = d11;
        double d13 = d12;
        UnivariatePointValuePair univariatePointValuePair = null;
        UnivariatePointValuePair univariatePointValuePair2 = new UnivariatePointValuePair(startValue, z ? computeObjectiveValue : -computeObjectiveValue);
        UnivariatePointValuePair univariatePointValuePair3 = univariatePointValuePair2;
        double d14 = 0.0d;
        double d15 = 0.0d;
        int i2 = 0;
        double d16 = d10;
        while (true) {
            double d17 = (min + max) * 0.5d;
            boolean z2 = z;
            ConvergenceChecker convergenceChecker3 = convergenceChecker2;
            double d18 = d16;
            double abs = (this.relativeThreshold * FastMath.abs(startValue)) + this.absoluteThreshold;
            double d19 = 2.0d * abs;
            if (!(FastMath.abs(startValue - d17) <= d19 - ((max - min) * 0.5d))) {
                if (FastMath.abs(d14) > abs) {
                    double d20 = startValue - d10;
                    double d21 = (d11 - d12) * d20;
                    double d22 = startValue - d18;
                    double d23 = (d11 - d13) * d22;
                    double d24 = (d22 * d23) - (d20 * d21);
                    d = d10;
                    double d25 = 2.0d * (d23 - d21);
                    if (d25 > 0.0d) {
                        d24 = -d24;
                    } else {
                        d25 = -d25;
                    }
                    double d26 = min - startValue;
                    if (d24 <= d25 * d26 || d24 >= (max - startValue) * d25 || FastMath.abs(d24) >= FastMath.abs(d14 * 0.5d * d25)) {
                        if (startValue < d17) {
                            d26 = max - startValue;
                        }
                        d2 = GOLDEN_SECTION * d26;
                        d14 = d26;
                    } else {
                        double d27 = d24 / d25;
                        double d28 = startValue + d27;
                        d2 = (d28 - min < d19 || max - d28 < d19) ? startValue <= d17 ? abs : -abs : d27;
                        d14 = d15;
                    }
                } else {
                    d = d10;
                    double d29 = startValue < d17 ? max - startValue : min - startValue;
                    double d30 = d29;
                    d2 = GOLDEN_SECTION * d29;
                    d14 = d30;
                }
                double d31 = FastMath.abs(d2) < abs ? d2 >= 0.0d ? abs + startValue : startValue - abs : startValue + d2;
                double computeObjectiveValue2 = computeObjectiveValue(d31);
                if (!z2) {
                    computeObjectiveValue2 = -computeObjectiveValue2;
                }
                double d32 = d2;
                if (z2) {
                    d3 = min;
                    d4 = computeObjectiveValue2;
                } else {
                    d3 = min;
                    d4 = -computeObjectiveValue2;
                }
                UnivariatePointValuePair univariatePointValuePair4 = new UnivariatePointValuePair(d31, d4);
                boolean z3 = z2;
                univariatePointValuePair3 = best(univariatePointValuePair3, best(univariatePointValuePair2, univariatePointValuePair4, z3), z3);
                if (convergenceChecker3 != null) {
                    i = i2;
                    convergenceChecker = convergenceChecker3;
                    if (convergenceChecker.converged(i, univariatePointValuePair2, univariatePointValuePair4)) {
                        return univariatePointValuePair3;
                    }
                } else {
                    i = i2;
                    convergenceChecker = convergenceChecker3;
                }
                if (computeObjectiveValue2 <= d11) {
                    if (d31 < startValue) {
                        max = startValue;
                    } else {
                        d3 = startValue;
                    }
                    d12 = d13;
                    d13 = d11;
                    d11 = computeObjectiveValue2;
                    d16 = d;
                    double d33 = d31;
                    d5 = startValue;
                    startValue = d33;
                } else {
                    if (d31 < startValue) {
                        d3 = d31;
                    } else {
                        max = d31;
                    }
                    if (computeObjectiveValue2 > d13) {
                        d7 = d31;
                        d5 = d;
                        if (Precision.equals(d5, startValue)) {
                            d6 = max;
                        } else {
                            if (computeObjectiveValue2 > d12) {
                                d8 = max;
                                double d34 = d18;
                                if (!Precision.equals(d34, startValue) && !Precision.equals(d34, d5)) {
                                    d16 = d34;
                                    max = d8;
                                }
                            } else {
                                d8 = max;
                            }
                            d12 = computeObjectiveValue2;
                            d16 = d7;
                            max = d8;
                        }
                    } else {
                        d7 = d31;
                        d6 = max;
                        d5 = d;
                    }
                    d12 = d13;
                    max = d6;
                    d13 = computeObjectiveValue2;
                    d16 = d5;
                    d5 = d7;
                }
                i2 = i + 1;
                univariatePointValuePair = univariatePointValuePair2;
                d15 = d32;
                univariatePointValuePair2 = univariatePointValuePair4;
                double d35 = d5;
                z = z3;
                d10 = d35;
                convergenceChecker2 = convergenceChecker;
                min = d3;
            } else {
                boolean z4 = z2;
                return best(univariatePointValuePair3, best(univariatePointValuePair, univariatePointValuePair2, z4), z4);
            }
        }
    }

    private UnivariatePointValuePair best(UnivariatePointValuePair univariatePointValuePair, UnivariatePointValuePair univariatePointValuePair2, boolean z) {
        if (univariatePointValuePair == null) {
            return univariatePointValuePair2;
        }
        if (univariatePointValuePair2 == null) {
            return univariatePointValuePair;
        }
        if (z) {
            if (univariatePointValuePair.getValue() > univariatePointValuePair2.getValue()) {
                univariatePointValuePair = univariatePointValuePair2;
            }
            return univariatePointValuePair;
        }
        if (univariatePointValuePair.getValue() < univariatePointValuePair2.getValue()) {
            univariatePointValuePair = univariatePointValuePair2;
        }
        return univariatePointValuePair;
    }
}
