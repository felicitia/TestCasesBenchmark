package org.apache.commons.math3.optim.nonlinear.scalar.noderiv;

import java.lang.reflect.Array;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.MaxEval;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer;
import org.apache.commons.math3.optim.univariate.BracketFinder;
import org.apache.commons.math3.optim.univariate.BrentOptimizer;
import org.apache.commons.math3.optim.univariate.SearchInterval;
import org.apache.commons.math3.optim.univariate.SimpleUnivariateValueChecker;
import org.apache.commons.math3.optim.univariate.UnivariateObjectiveFunction;
import org.apache.commons.math3.optim.univariate.UnivariatePointValuePair;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class PowellOptimizer extends MultivariateOptimizer {
    private static final double MIN_RELATIVE_TOLERANCE = (2.0d * FastMath.ulp(1.0d));
    private final double absoluteThreshold;
    private final LineSearch line;
    private final double relativeThreshold;

    private class LineSearch extends BrentOptimizer {
        private static final double ABS_TOL_UNUSED = Double.MIN_VALUE;
        private static final double REL_TOL_UNUSED = 1.0E-15d;
        private final BracketFinder bracket = new BracketFinder();

        LineSearch(double d, double d2) {
            super(1.0E-15d, ABS_TOL_UNUSED, new SimpleUnivariateValueChecker(d, d2));
        }

        public UnivariatePointValuePair search(double[] dArr, double[] dArr2) {
            final double[] dArr3 = dArr;
            final int length = dArr3.length;
            final double[] dArr4 = dArr2;
            AnonymousClass1 r14 = new UnivariateFunction() {
                public double value(double d) {
                    double[] dArr = new double[length];
                    for (int i = 0; i < length; i++) {
                        dArr[i] = dArr3[i] + (dArr4[i] * d);
                    }
                    return PowellOptimizer.this.computeObjectiveValue(dArr);
                }
            };
            GoalType goalType = PowellOptimizer.this.getGoalType();
            this.bracket.search(r14, goalType, 0.0d, 1.0d);
            SearchInterval searchInterval = new SearchInterval(this.bracket.getLo(), this.bracket.getHi(), this.bracket.getMid());
            return optimize(new OptimizationData[]{new MaxEval(Integer.MAX_VALUE), new UnivariateObjectiveFunction(r14), goalType, searchInterval});
        }
    }

    public PowellOptimizer(double d, double d2, ConvergenceChecker<PointValuePair> convergenceChecker) {
        this(d, d2, FastMath.sqrt(d), FastMath.sqrt(d2), convergenceChecker);
    }

    public PowellOptimizer(double d, double d2, double d3, double d4, ConvergenceChecker<PointValuePair> convergenceChecker) {
        super(convergenceChecker);
        if (d < MIN_RELATIVE_TOLERANCE) {
            throw new NumberIsTooSmallException(Double.valueOf(d), Double.valueOf(MIN_RELATIVE_TOLERANCE), true);
        } else if (d2 <= 0.0d) {
            throw new NotStrictlyPositiveException(Double.valueOf(d2));
        } else {
            this.relativeThreshold = d;
            this.absoluteThreshold = d2;
            LineSearch lineSearch = new LineSearch(d3, d4);
            this.line = lineSearch;
        }
    }

    public PowellOptimizer(double d, double d2) {
        this(d, d2, null);
    }

    public PowellOptimizer(double d, double d2, double d3, double d4) {
        this(d, d2, d3, d4, null);
    }

    /* access modifiers changed from: protected */
    public PointValuePair doOptimize() {
        double d;
        PointValuePair pointValuePair;
        PointValuePair pointValuePair2;
        int i;
        GoalType goalType = getGoalType();
        double[] startPoint = getStartPoint();
        int i2 = 0;
        int length = startPoint.length;
        double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{length, length});
        for (int i3 = 0; i3 < length; i3++) {
            dArr[i3][i3] = 1.0d;
        }
        ConvergenceChecker convergenceChecker = getConvergenceChecker();
        double computeObjectiveValue = computeObjectiveValue(startPoint);
        double[] dArr2 = (double[]) startPoint.clone();
        double[] dArr3 = startPoint;
        int i4 = 0;
        while (true) {
            i4++;
            int i5 = i2;
            d = computeObjectiveValue;
            double[] dArr4 = dArr3;
            double d2 = 0.0d;
            int i6 = i5;
            while (i6 < length) {
                double[] copyOf = MathArrays.copyOf(dArr[i6]);
                UnivariatePointValuePair search = this.line.search(dArr4, copyOf);
                double value = search.getValue();
                int i7 = length;
                double[][] dArr5 = dArr;
                dArr4 = newPointAndDirection(dArr4, copyOf, search.getPoint())[i2];
                double d3 = d - value;
                if (d3 > d2) {
                    i5 = i6;
                    d2 = d3;
                }
                i6++;
                d = value;
                length = i7;
                dArr = dArr5;
            }
            int i8 = length;
            double[][] dArr6 = dArr;
            double d4 = computeObjectiveValue - d;
            double d5 = d4;
            boolean z = 2.0d * d4 <= (this.relativeThreshold * (FastMath.abs(computeObjectiveValue) + FastMath.abs(d))) + this.absoluteThreshold;
            pointValuePair = new PointValuePair(dArr2, computeObjectiveValue);
            pointValuePair2 = new PointValuePair(dArr4, d);
            if (!z && convergenceChecker != null) {
                z = convergenceChecker.converged(i4, pointValuePair, pointValuePair2);
            }
            if (z) {
                break;
            }
            int i9 = i8;
            double[] dArr7 = new double[i9];
            double[] dArr8 = new double[i9];
            for (int i10 = 0; i10 < i9; i10++) {
                dArr7[i10] = dArr4[i10] - dArr2[i10];
                dArr8[i10] = (dArr4[i10] * 2.0d) - dArr2[i10];
            }
            dArr2 = (double[]) dArr4.clone();
            double computeObjectiveValue2 = computeObjectiveValue(dArr8);
            if (computeObjectiveValue > computeObjectiveValue2) {
                double d6 = d5 - d2;
                double d7 = computeObjectiveValue - computeObjectiveValue2;
                if (((((computeObjectiveValue + computeObjectiveValue2) - (2.0d * d)) * 2.0d) * (d6 * d6)) - ((d2 * d7) * d7) < 0.0d) {
                    UnivariatePointValuePair search2 = this.line.search(dArr4, dArr7);
                    computeObjectiveValue = search2.getValue();
                    double[][] newPointAndDirection = newPointAndDirection(dArr4, dArr7, search2.getPoint());
                    i = 0;
                    dArr3 = newPointAndDirection[0];
                    int i11 = i9 - 1;
                    dArr6[i5] = dArr6[i11];
                    dArr6[i11] = newPointAndDirection[1];
                    length = i9;
                    i2 = i;
                    dArr = dArr6;
                }
            }
            i = 0;
            dArr3 = dArr4;
            computeObjectiveValue = d;
            length = i9;
            i2 = i;
            dArr = dArr6;
        }
        if (goalType == GoalType.MINIMIZE) {
            if (d < computeObjectiveValue) {
                pointValuePair = pointValuePair2;
            }
            return pointValuePair;
        }
        if (d > computeObjectiveValue) {
            pointValuePair = pointValuePair2;
        }
        return pointValuePair;
    }

    private double[][] newPointAndDirection(double[] dArr, double[] dArr2, double d) {
        int length = dArr.length;
        double[] dArr3 = new double[length];
        double[] dArr4 = new double[length];
        for (int i = 0; i < length; i++) {
            dArr4[i] = dArr2[i] * d;
            dArr3[i] = dArr[i] + dArr4[i];
        }
        return new double[][]{dArr3, dArr4};
    }
}
