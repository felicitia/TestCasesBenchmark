package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

public class MullerSolver extends AbstractUnivariateSolver {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;

    public MullerSolver() {
        this(1.0E-6d);
    }

    public MullerSolver(double d) {
        super(d);
    }

    public MullerSolver(double d, double d2) {
        super(d, d2);
    }

    /* access modifiers changed from: protected */
    public double doSolve() throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        double min = getMin();
        double max = getMax();
        double startValue = getStartValue();
        double functionValueAccuracy = getFunctionValueAccuracy();
        verifySequence(min, startValue, max);
        double computeObjectiveValue = computeObjectiveValue(min);
        if (FastMath.abs(computeObjectiveValue) < functionValueAccuracy) {
            return min;
        }
        double computeObjectiveValue2 = computeObjectiveValue(max);
        if (FastMath.abs(computeObjectiveValue2) < functionValueAccuracy) {
            return max;
        }
        double computeObjectiveValue3 = computeObjectiveValue(startValue);
        if (FastMath.abs(computeObjectiveValue3) < functionValueAccuracy) {
            return startValue;
        }
        verifyBracketing(min, max);
        if (isBracketing(min, startValue)) {
            return solve(min, startValue, computeObjectiveValue, computeObjectiveValue3);
        }
        return solve(startValue, max, computeObjectiveValue3, computeObjectiveValue2);
    }

    private double solve(double d, double d2, double d3, double d4) throws TooManyEvaluationsException {
        double sqrt;
        long j;
        double relativeAccuracy = getRelativeAccuracy();
        double absoluteAccuracy = getAbsoluteAccuracy();
        double functionValueAccuracy = getFunctionValueAccuracy();
        double d5 = (d + d2) * 0.5d;
        double computeObjectiveValue = computeObjectiveValue(d5);
        double d6 = d;
        double d7 = d2;
        double d8 = d4;
        double d9 = d5;
        double d10 = Double.POSITIVE_INFINITY;
        double d11 = d3;
        while (true) {
            double d12 = d9 - d6;
            double d13 = (computeObjectiveValue - d11) / d12;
            double d14 = d7 - d9;
            double d15 = d7 - d6;
            double d16 = (((d8 - computeObjectiveValue) / d14) - d13) / d15;
            double d17 = d13 + (d12 * d16);
            double d18 = (d17 * d17) - ((4.0d * computeObjectiveValue) * d16);
            double d19 = -2.0d * computeObjectiveValue;
            double sqrt2 = d9 + (d19 / (d17 + FastMath.sqrt(d18)));
            double d20 = d11;
            sqrt = isSequence(d6, sqrt2, d7) ? sqrt2 : d9 + (d19 / (d17 - FastMath.sqrt(d18)));
            double computeObjectiveValue2 = computeObjectiveValue(sqrt);
            if (FastMath.abs(sqrt - d10) <= FastMath.max(FastMath.abs(sqrt) * relativeAccuracy, absoluteAccuracy) || FastMath.abs(computeObjectiveValue2) <= functionValueAccuracy) {
                return sqrt;
            }
            if (!((sqrt < d9 && d12 > 0.95d * d15) || (sqrt > d9 && d14 > 0.95d * d15) || sqrt == d9)) {
                if (sqrt >= d9) {
                    d6 = d9;
                }
                if (sqrt >= d9) {
                    d20 = computeObjectiveValue;
                }
                if (sqrt <= d9) {
                    d7 = d9;
                }
                if (sqrt <= d9) {
                    d8 = computeObjectiveValue;
                }
                d9 = sqrt;
                d10 = d9;
                computeObjectiveValue = computeObjectiveValue2;
                d11 = d20;
                j = 4602678819172646912L;
            } else {
                j = 4602678819172646912L;
                double d21 = (d6 + d7) * 0.5d;
                double computeObjectiveValue3 = computeObjectiveValue(d21);
                double d22 = d20;
                if (FastMath.signum(d22) + FastMath.signum(computeObjectiveValue3) == 0.0d) {
                    d7 = d21;
                } else {
                    d6 = d21;
                    d22 = computeObjectiveValue3;
                    computeObjectiveValue3 = d8;
                }
                double d23 = (d6 + d7) * 0.5d;
                computeObjectiveValue = computeObjectiveValue(d23);
                d9 = d23;
                d8 = computeObjectiveValue3;
                d11 = d22;
                d10 = Double.POSITIVE_INFINITY;
            }
            long j2 = j;
        }
        return sqrt;
    }
}
