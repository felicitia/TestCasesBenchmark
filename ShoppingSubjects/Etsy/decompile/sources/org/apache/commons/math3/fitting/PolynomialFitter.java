package org.apache.commons.math3.fitting;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction.Parametric;
import org.apache.commons.math3.optim.nonlinear.vector.MultivariateVectorOptimizer;

public class PolynomialFitter extends CurveFitter<Parametric> {
    public PolynomialFitter(MultivariateVectorOptimizer multivariateVectorOptimizer) {
        super(multivariateVectorOptimizer);
    }

    public double[] fit(int i, double[] dArr) {
        return fit(i, new Parametric(), dArr);
    }

    public double[] fit(double[] dArr) {
        return fit(new Parametric(), dArr);
    }
}
