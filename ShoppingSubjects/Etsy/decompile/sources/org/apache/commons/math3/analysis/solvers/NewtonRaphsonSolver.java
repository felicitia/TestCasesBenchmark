package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

public class NewtonRaphsonSolver extends AbstractUnivariateDifferentiableSolver {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;

    public NewtonRaphsonSolver() {
        this(1.0E-6d);
    }

    public NewtonRaphsonSolver(double d) {
        super(d);
    }

    public double solve(int i, UnivariateDifferentiableFunction univariateDifferentiableFunction, double d, double d2) throws TooManyEvaluationsException {
        return super.solve(i, univariateDifferentiableFunction, UnivariateSolverUtils.midpoint(d, d2));
    }

    /* access modifiers changed from: protected */
    public double doSolve() throws TooManyEvaluationsException {
        double startValue = getStartValue();
        double absoluteAccuracy = getAbsoluteAccuracy();
        while (true) {
            DerivativeStructure computeObjectiveValueAndDerivative = computeObjectiveValueAndDerivative(startValue);
            double value = startValue - (computeObjectiveValueAndDerivative.getValue() / computeObjectiveValueAndDerivative.getPartialDerivative(1));
            if (FastMath.abs(value - startValue) <= absoluteAccuracy) {
                return value;
            }
            startValue = value;
        }
    }
}
