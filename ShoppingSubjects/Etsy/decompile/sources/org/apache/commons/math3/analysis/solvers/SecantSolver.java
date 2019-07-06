package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

public class SecantSolver extends AbstractUnivariateSolver {
    protected static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;

    public SecantSolver() {
        super(1.0E-6d);
    }

    public SecantSolver(double d) {
        super(d);
    }

    public SecantSolver(double d, double d2) {
        super(d, d2);
    }

    /* access modifiers changed from: protected */
    public final double doSolve() throws TooManyEvaluationsException, NoBracketingException {
        SecantSolver secantSolver = this;
        double min = getMin();
        double max = getMax();
        double computeObjectiveValue = secantSolver.computeObjectiveValue(min);
        double computeObjectiveValue2 = secantSolver.computeObjectiveValue(max);
        double d = 0.0d;
        if (computeObjectiveValue == 0.0d) {
            return min;
        }
        if (computeObjectiveValue2 == 0.0d) {
            return max;
        }
        secantSolver.verifyBracketing(min, max);
        double functionValueAccuracy = getFunctionValueAccuracy();
        double absoluteAccuracy = getAbsoluteAccuracy();
        double relativeAccuracy = getRelativeAccuracy();
        double d2 = computeObjectiveValue;
        double d3 = computeObjectiveValue2;
        double d4 = d2;
        double d5 = min;
        double d6 = max;
        double d7 = d5;
        while (true) {
            double d8 = d6 - (((d6 - d7) * d3) / (d3 - d4));
            double computeObjectiveValue3 = secantSolver.computeObjectiveValue(d8);
            if (computeObjectiveValue3 == d || FastMath.abs(computeObjectiveValue3) <= functionValueAccuracy) {
                return d8;
            }
            double d9 = d6;
            if (FastMath.abs(d8 - d6) < FastMath.max(relativeAccuracy * FastMath.abs(d8), absoluteAccuracy)) {
                return d8;
            }
            d6 = d8;
            d7 = d9;
            secantSolver = this;
            d = 0.0d;
            double d10 = d3;
            d3 = computeObjectiveValue3;
            d4 = d10;
        }
    }
}
