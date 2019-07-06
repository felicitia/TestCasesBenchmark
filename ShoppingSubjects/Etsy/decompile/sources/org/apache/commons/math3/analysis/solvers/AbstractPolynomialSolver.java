package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;

public abstract class AbstractPolynomialSolver extends BaseAbstractUnivariateSolver<PolynomialFunction> implements PolynomialSolver {
    private PolynomialFunction polynomialFunction;

    protected AbstractPolynomialSolver(double d) {
        super(d);
    }

    protected AbstractPolynomialSolver(double d, double d2) {
        super(d, d2);
    }

    protected AbstractPolynomialSolver(double d, double d2, double d3) {
        super(d, d2, d3);
    }

    /* access modifiers changed from: protected */
    public void setup(int i, PolynomialFunction polynomialFunction2, double d, double d2, double d3) {
        super.setup(i, polynomialFunction2, d, d2, d3);
        this.polynomialFunction = polynomialFunction2;
    }

    /* access modifiers changed from: protected */
    public double[] getCoefficients() {
        return this.polynomialFunction.getCoefficients();
    }
}
