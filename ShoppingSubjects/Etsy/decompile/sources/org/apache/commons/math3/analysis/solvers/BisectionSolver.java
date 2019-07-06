package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

public class BisectionSolver extends AbstractUnivariateSolver {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;

    public BisectionSolver() {
        this(1.0E-6d);
    }

    public BisectionSolver(double d) {
        super(d);
    }

    public BisectionSolver(double d, double d2) {
        super(d, d2);
    }

    /* access modifiers changed from: protected */
    public double doSolve() throws TooManyEvaluationsException {
        double min = getMin();
        double max = getMax();
        verifyInterval(min, max);
        double absoluteAccuracy = getAbsoluteAccuracy();
        do {
            double midpoint = UnivariateSolverUtils.midpoint(min, max);
            if (computeObjectiveValue(midpoint) * computeObjectiveValue(min) > 0.0d) {
                min = midpoint;
            } else {
                max = midpoint;
            }
        } while (FastMath.abs(max - min) > absoluteAccuracy);
        return UnivariateSolverUtils.midpoint(min, max);
    }
}
