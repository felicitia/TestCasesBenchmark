package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.UnivariateFunction;

public interface BaseUnivariateSolver<FUNC extends UnivariateFunction> {
    double getAbsoluteAccuracy();

    int getEvaluations();

    double getFunctionValueAccuracy();

    int getMaxEvaluations();

    double getRelativeAccuracy();

    double solve(int i, FUNC func, double d);

    double solve(int i, FUNC func, double d, double d2);

    double solve(int i, FUNC func, double d, double d2, double d3);
}
