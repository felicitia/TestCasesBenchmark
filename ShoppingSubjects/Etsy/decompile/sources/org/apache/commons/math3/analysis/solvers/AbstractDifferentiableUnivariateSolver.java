package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.TooManyEvaluationsException;

@Deprecated
public abstract class AbstractDifferentiableUnivariateSolver extends BaseAbstractUnivariateSolver<DifferentiableUnivariateFunction> implements DifferentiableUnivariateSolver {
    private UnivariateFunction functionDerivative;

    protected AbstractDifferentiableUnivariateSolver(double d) {
        super(d);
    }

    protected AbstractDifferentiableUnivariateSolver(double d, double d2, double d3) {
        super(d, d2, d3);
    }

    /* access modifiers changed from: protected */
    public double computeDerivativeObjectiveValue(double d) throws TooManyEvaluationsException {
        incrementEvaluationCount();
        return this.functionDerivative.value(d);
    }

    /* access modifiers changed from: protected */
    public void setup(int i, DifferentiableUnivariateFunction differentiableUnivariateFunction, double d, double d2, double d3) {
        super.setup(i, differentiableUnivariateFunction, d, d2, d3);
        this.functionDerivative = differentiableUnivariateFunction.derivative();
    }
}
