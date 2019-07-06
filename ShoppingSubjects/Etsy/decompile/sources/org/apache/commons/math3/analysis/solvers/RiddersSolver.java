package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

public class RiddersSolver extends AbstractUnivariateSolver {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;

    public RiddersSolver() {
        this(1.0E-6d);
    }

    public RiddersSolver(double d) {
        super(d);
    }

    public RiddersSolver(double d, double d2) {
        super(d, d2);
    }

    /* access modifiers changed from: protected */
    public double doSolve() throws TooManyEvaluationsException, NoBracketingException {
        double min = getMin();
        double max = getMax();
        double computeObjectiveValue = computeObjectiveValue(min);
        double computeObjectiveValue2 = computeObjectiveValue(max);
        if (computeObjectiveValue == 0.0d) {
            return min;
        }
        if (computeObjectiveValue2 == 0.0d) {
            return max;
        }
        verifyBracketing(min, max);
        double absoluteAccuracy = getAbsoluteAccuracy();
        double functionValueAccuracy = getFunctionValueAccuracy();
        double relativeAccuracy = getRelativeAccuracy();
        double d = Double.POSITIVE_INFINITY;
        while (true) {
            double d2 = 0.5d * (min + max);
            double d3 = max;
            double computeObjectiveValue3 = computeObjectiveValue(d2);
            if (FastMath.abs(computeObjectiveValue3) <= functionValueAccuracy) {
                return d2;
            }
            double d4 = computeObjectiveValue;
            double signum = ((FastMath.signum(computeObjectiveValue2) * FastMath.signum(computeObjectiveValue3)) * (d2 - min)) / FastMath.sqrt(1.0d - ((computeObjectiveValue * computeObjectiveValue2) / (computeObjectiveValue3 * computeObjectiveValue3)));
            double d5 = d2 - signum;
            double d6 = min;
            double computeObjectiveValue4 = computeObjectiveValue(d5);
            double d7 = computeObjectiveValue3;
            double d8 = d2;
            if (FastMath.abs(d5 - d) <= FastMath.max(relativeAccuracy * FastMath.abs(d5), absoluteAccuracy) || FastMath.abs(computeObjectiveValue4) <= functionValueAccuracy) {
                return d5;
            }
            if (signum > 0.0d) {
                double d9 = d4;
                if (FastMath.signum(d9) + FastMath.signum(computeObjectiveValue4) == 0.0d) {
                    computeObjectiveValue2 = computeObjectiveValue4;
                    d8 = d5;
                    d7 = d9;
                    min = d6;
                } else {
                    computeObjectiveValue2 = d7;
                    d7 = computeObjectiveValue4;
                    min = d5;
                }
            } else if (FastMath.signum(computeObjectiveValue2) + FastMath.signum(computeObjectiveValue4) == 0.0d) {
                d7 = computeObjectiveValue4;
                min = d5;
                d8 = d3;
            } else {
                computeObjectiveValue2 = computeObjectiveValue4;
                min = d8;
                d8 = d5;
            }
            d = d5;
            computeObjectiveValue = d7;
            max = d8;
        }
    }
}
