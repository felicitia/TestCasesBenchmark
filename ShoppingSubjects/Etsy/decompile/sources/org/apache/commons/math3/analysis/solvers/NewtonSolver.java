package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

@Deprecated
public class NewtonSolver extends AbstractDifferentiableUnivariateSolver {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;

    public NewtonSolver() {
        this(1.0E-6d);
    }

    public NewtonSolver(double d) {
        super(d);
    }

    public double solve(int i, DifferentiableUnivariateFunction differentiableUnivariateFunction, double d, double d2) throws TooManyEvaluationsException {
        return super.solve(i, differentiableUnivariateFunction, UnivariateSolverUtils.midpoint(d, d2));
    }

    /* access modifiers changed from: protected */
    public double doSolve() throws TooManyEvaluationsException {
        double startValue = getStartValue();
        while (true) {
            double computeObjectiveValue = startValue - (computeObjectiveValue(startValue) / computeDerivativeObjectiveValue(startValue));
            if (FastMath.abs(computeObjectiveValue - startValue) <= getAbsoluteAccuracy()) {
                return computeObjectiveValue;
            }
            startValue = computeObjectiveValue;
        }
    }
}
