package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

public class MullerSolver2 extends AbstractUnivariateSolver {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;

    public MullerSolver2() {
        this(1.0E-6d);
    }

    public MullerSolver2(double d) {
        super(d);
    }

    public MullerSolver2(double d, double d2) {
        super(d, d2);
    }

    /* access modifiers changed from: protected */
    public double doSolve() throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        double d;
        double d2;
        double d3;
        MullerSolver2 mullerSolver2 = this;
        double min = getMin();
        double max = getMax();
        mullerSolver2.verifyInterval(min, max);
        double relativeAccuracy = getRelativeAccuracy();
        double absoluteAccuracy = getAbsoluteAccuracy();
        double functionValueAccuracy = getFunctionValueAccuracy();
        double computeObjectiveValue = mullerSolver2.computeObjectiveValue(min);
        if (FastMath.abs(computeObjectiveValue) < functionValueAccuracy) {
            return min;
        }
        double computeObjectiveValue2 = mullerSolver2.computeObjectiveValue(max);
        if (FastMath.abs(computeObjectiveValue2) < functionValueAccuracy) {
            return max;
        }
        if (computeObjectiveValue * computeObjectiveValue2 > 0.0d) {
            NoBracketingException noBracketingException = new NoBracketingException(min, max, computeObjectiveValue, computeObjectiveValue2);
            throw noBracketingException;
        }
        double d4 = computeObjectiveValue;
        double d5 = 0.5d * (min + max);
        double d6 = computeObjectiveValue2;
        double d7 = Double.POSITIVE_INFINITY;
        double d8 = d4;
        double d9 = max;
        double computeObjectiveValue3 = mullerSolver2.computeObjectiveValue(d5);
        double d10 = min;
        while (true) {
            double d11 = d5 - d9;
            double d12 = d11 / (d9 - d10);
            double d13 = 1.0d + d12;
            double d14 = ((computeObjectiveValue3 - (d13 * d6)) + (d12 * d8)) * d12;
            double d15 = ((((2.0d * d12) + 1.0d) * computeObjectiveValue3) - ((d13 * d13) * d6)) + (d12 * d12 * d8);
            double d16 = d13 * computeObjectiveValue3;
            double d17 = d15 * d15;
            double d18 = functionValueAccuracy;
            double d19 = d17 - ((4.0d * d14) * d16);
            if (d19 >= 0.0d) {
                d = relativeAccuracy;
                d2 = d15 + FastMath.sqrt(d19);
                double sqrt = d15 - FastMath.sqrt(d19);
                if (FastMath.abs(d2) <= FastMath.abs(sqrt)) {
                    d2 = sqrt;
                }
            } else {
                d = relativeAccuracy;
                d2 = FastMath.sqrt(d17 - d19);
            }
            if (d2 != 0.0d) {
                d3 = d5 - (((2.0d * d16) * d11) / d2);
                while (true) {
                    if (d3 != d9 && d3 != d5) {
                        break;
                    }
                    d3 += absoluteAccuracy;
                }
            } else {
                d3 = (FastMath.random() * (max - min)) + min;
                d7 = Double.POSITIVE_INFINITY;
            }
            double computeObjectiveValue4 = mullerSolver2.computeObjectiveValue(d3);
            double d20 = min;
            if (FastMath.abs(d3 - d7) <= FastMath.max(d * FastMath.abs(d3), absoluteAccuracy) || FastMath.abs(computeObjectiveValue4) <= d18) {
                return d3;
            }
            d7 = d3;
            d10 = d9;
            d8 = d6;
            min = d20;
            mullerSolver2 = this;
            d9 = d5;
            d6 = computeObjectiveValue3;
            relativeAccuracy = d;
            d5 = d7;
            computeObjectiveValue3 = computeObjectiveValue4;
            functionValueAccuracy = d18;
        }
        return d3;
    }
}
