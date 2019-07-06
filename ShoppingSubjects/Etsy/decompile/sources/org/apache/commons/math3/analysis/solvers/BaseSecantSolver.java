package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.util.FastMath;

public abstract class BaseSecantSolver extends AbstractUnivariateSolver implements BracketedUnivariateSolver<UnivariateFunction> {
    protected static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;
    private AllowedSolution allowed = AllowedSolution.ANY_SIDE;
    private final Method method;

    protected enum Method {
        REGULA_FALSI,
        ILLINOIS,
        PEGASUS
    }

    protected BaseSecantSolver(double d, Method method2) {
        super(d);
        this.method = method2;
    }

    protected BaseSecantSolver(double d, double d2, Method method2) {
        super(d, d2);
        this.method = method2;
    }

    protected BaseSecantSolver(double d, double d2, double d3, Method method2) {
        super(d, d2, d3);
        this.method = method2;
    }

    public double solve(int i, UnivariateFunction univariateFunction, double d, double d2, AllowedSolution allowedSolution) {
        return solve(i, univariateFunction, d, d2, (0.5d * (d2 - d)) + d, allowedSolution);
    }

    public double solve(int i, UnivariateFunction univariateFunction, double d, double d2, double d3, AllowedSolution allowedSolution) {
        this.allowed = allowedSolution;
        return super.solve(i, univariateFunction, d, d2, d3);
    }

    public double solve(int i, UnivariateFunction univariateFunction, double d, double d2, double d3) {
        return solve(i, univariateFunction, d, d2, d3, AllowedSolution.ANY_SIDE);
    }

    /* access modifiers changed from: protected */
    public final double doSolve() throws ConvergenceException, MathInternalError {
        double min = getMin();
        double max = getMax();
        double computeObjectiveValue = computeObjectiveValue(min);
        double computeObjectiveValue2 = computeObjectiveValue(max);
        double d = 0.0d;
        if (computeObjectiveValue == 0.0d) {
            return min;
        }
        if (computeObjectiveValue2 == 0.0d) {
            return max;
        }
        verifyBracketing(min, max);
        double functionValueAccuracy = getFunctionValueAccuracy();
        double absoluteAccuracy = getAbsoluteAccuracy();
        double relativeAccuracy = getRelativeAccuracy();
        boolean z = false;
        while (true) {
            double d2 = min;
            double d3 = max - (((max - min) * computeObjectiveValue2) / (computeObjectiveValue2 - computeObjectiveValue));
            double d4 = absoluteAccuracy;
            double computeObjectiveValue3 = computeObjectiveValue(d3);
            if (computeObjectiveValue3 == d) {
                return d3;
            }
            if (computeObjectiveValue2 * computeObjectiveValue3 < d) {
                d2 = max;
                z = !z;
                computeObjectiveValue = computeObjectiveValue2;
            } else {
                switch (this.method) {
                    case ILLINOIS:
                        computeObjectiveValue *= 0.5d;
                        break;
                    case PEGASUS:
                        computeObjectiveValue *= computeObjectiveValue2 / (computeObjectiveValue2 + computeObjectiveValue3);
                        break;
                    case REGULA_FALSI:
                        if (d3 == max) {
                            throw new ConvergenceException();
                        }
                        break;
                    default:
                        throw new MathInternalError();
                }
            }
            if (FastMath.abs(computeObjectiveValue3) <= functionValueAccuracy) {
                switch (this.allowed) {
                    case ANY_SIDE:
                        return d3;
                    case LEFT_SIDE:
                        if (z) {
                            return d3;
                        }
                        break;
                    case RIGHT_SIDE:
                        if (!z) {
                            return d3;
                        }
                        break;
                    case BELOW_SIDE:
                        if (computeObjectiveValue3 <= 0.0d) {
                            return d3;
                        }
                        break;
                    case ABOVE_SIDE:
                        if (computeObjectiveValue3 >= 0.0d) {
                            return d3;
                        }
                        break;
                    default:
                        throw new MathInternalError();
                }
            }
            double d5 = d4;
            if (FastMath.abs(d3 - d2) < FastMath.max(FastMath.abs(d3) * relativeAccuracy, d5)) {
                switch (this.allowed) {
                    case ANY_SIDE:
                        return d3;
                    case LEFT_SIDE:
                        if (!z) {
                            d3 = d2;
                        }
                        return d3;
                    case RIGHT_SIDE:
                        if (z) {
                            d3 = d2;
                        }
                        return d3;
                    case BELOW_SIDE:
                        if (computeObjectiveValue3 > 0.0d) {
                            d3 = d2;
                        }
                        return d3;
                    case ABOVE_SIDE:
                        if (computeObjectiveValue3 < 0.0d) {
                            d3 = d2;
                        }
                        return d3;
                    default:
                        throw new MathInternalError();
                }
            } else {
                max = d3;
                computeObjectiveValue2 = computeObjectiveValue3;
                min = d2;
                absoluteAccuracy = d5;
                d = 0.0d;
            }
        }
    }
}
