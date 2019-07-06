package org.apache.commons.math3.optimization.general;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BrentSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.SimpleValueChecker;
import org.apache.commons.math3.util.FastMath;

@Deprecated
public class NonLinearConjugateGradientOptimizer extends AbstractScalarDifferentiableOptimizer {
    private double initialStep;
    /* access modifiers changed from: private */
    public double[] point;
    private final Preconditioner preconditioner;
    private final UnivariateSolver solver;
    private final ConjugateGradientFormula updateFormula;

    public static class IdentityPreconditioner implements Preconditioner {
        public double[] precondition(double[] dArr, double[] dArr2) {
            return (double[]) dArr2.clone();
        }
    }

    private class LineSearchFunction implements UnivariateFunction {
        private final double[] searchDirection;

        public LineSearchFunction(double[] dArr) {
            this.searchDirection = dArr;
        }

        public double value(double d) {
            double[] dArr = (double[]) NonLinearConjugateGradientOptimizer.this.point.clone();
            for (int i = 0; i < dArr.length; i++) {
                dArr[i] = dArr[i] + (this.searchDirection[i] * d);
            }
            double[] computeObjectiveGradient = NonLinearConjugateGradientOptimizer.this.computeObjectiveGradient(dArr);
            double d2 = 0.0d;
            for (int i2 = 0; i2 < computeObjectiveGradient.length; i2++) {
                d2 += computeObjectiveGradient[i2] * this.searchDirection[i2];
            }
            return d2;
        }
    }

    @Deprecated
    public NonLinearConjugateGradientOptimizer(ConjugateGradientFormula conjugateGradientFormula) {
        this(conjugateGradientFormula, new SimpleValueChecker());
    }

    public NonLinearConjugateGradientOptimizer(ConjugateGradientFormula conjugateGradientFormula, ConvergenceChecker<PointValuePair> convergenceChecker) {
        this(conjugateGradientFormula, convergenceChecker, new BrentSolver(), new IdentityPreconditioner());
    }

    public NonLinearConjugateGradientOptimizer(ConjugateGradientFormula conjugateGradientFormula, ConvergenceChecker<PointValuePair> convergenceChecker, UnivariateSolver univariateSolver) {
        this(conjugateGradientFormula, convergenceChecker, univariateSolver, new IdentityPreconditioner());
    }

    public NonLinearConjugateGradientOptimizer(ConjugateGradientFormula conjugateGradientFormula, ConvergenceChecker<PointValuePair> convergenceChecker, UnivariateSolver univariateSolver, Preconditioner preconditioner2) {
        super(convergenceChecker);
        this.updateFormula = conjugateGradientFormula;
        this.solver = univariateSolver;
        this.preconditioner = preconditioner2;
        this.initialStep = 1.0d;
    }

    public void setInitialStep(double d) {
        if (d <= 0.0d) {
            this.initialStep = 1.0d;
        } else {
            this.initialStep = d;
        }
    }

    /* access modifiers changed from: protected */
    public PointValuePair doOptimize() {
        double d;
        ConvergenceChecker convergenceChecker = getConvergenceChecker();
        this.point = getStartPoint();
        GoalType goalType = getGoalType();
        int length = this.point.length;
        double[] computeObjectiveGradient = computeObjectiveGradient(this.point);
        if (goalType == GoalType.MINIMIZE) {
            for (int i = 0; i < length; i++) {
                computeObjectiveGradient[i] = -computeObjectiveGradient[i];
            }
        }
        double[] precondition = this.preconditioner.precondition(this.point, computeObjectiveGradient);
        double[] dArr = (double[]) precondition.clone();
        double d2 = 0.0d;
        for (int i2 = 0; i2 < length; i2++) {
            d2 += computeObjectiveGradient[i2] * dArr[i2];
        }
        double[] dArr2 = precondition;
        double[] dArr3 = dArr;
        int maxEvaluations = getMaxEvaluations();
        double d3 = d2;
        PointValuePair pointValuePair = null;
        int i3 = 0;
        while (true) {
            int i4 = i3 + 1;
            PointValuePair pointValuePair2 = new PointValuePair(this.point, computeObjectiveValue(this.point));
            if (pointValuePair != null && convergenceChecker.converged(i4, pointValuePair, pointValuePair2)) {
                return pointValuePair2;
            }
            LineSearchFunction lineSearchFunction = new LineSearchFunction(dArr3);
            LineSearchFunction lineSearchFunction2 = lineSearchFunction;
            double findUpperBound = findUpperBound(lineSearchFunction, 0.0d, this.initialStep);
            PointValuePair pointValuePair3 = pointValuePair2;
            i3 = i4;
            double solve = this.solver.solve(maxEvaluations, lineSearchFunction2, 0.0d, findUpperBound, 1.0E-15d);
            maxEvaluations -= this.solver.getEvaluations();
            for (int i5 = 0; i5 < this.point.length; i5++) {
                double[] dArr4 = this.point;
                dArr4[i5] = dArr4[i5] + (dArr3[i5] * solve);
            }
            double[] computeObjectiveGradient2 = computeObjectiveGradient(this.point);
            if (goalType == GoalType.MINIMIZE) {
                for (int i6 = 0; i6 < length; i6++) {
                    computeObjectiveGradient2[i6] = -computeObjectiveGradient2[i6];
                }
            }
            double[] precondition2 = this.preconditioner.precondition(this.point, computeObjectiveGradient2);
            double d4 = 0.0d;
            for (int i7 = 0; i7 < length; i7++) {
                d4 += computeObjectiveGradient2[i7] * precondition2[i7];
            }
            if (this.updateFormula == ConjugateGradientFormula.FLETCHER_REEVES) {
                d = d4 / d3;
            } else {
                double d5 = 0.0d;
                for (int i8 = 0; i8 < computeObjectiveGradient2.length; i8++) {
                    d5 += computeObjectiveGradient2[i8] * dArr2[i8];
                }
                d = (d4 - d5) / d3;
            }
            if (i3 % length == 0 || d < 0.0d) {
                dArr3 = (double[]) precondition2.clone();
            } else {
                for (int i9 = 0; i9 < length; i9++) {
                    dArr3[i9] = precondition2[i9] + (dArr3[i9] * d);
                }
            }
            dArr2 = precondition2;
            pointValuePair = pointValuePair3;
            d3 = d4;
        }
    }

    private double findUpperBound(UnivariateFunction univariateFunction, double d, double d2) {
        double value = univariateFunction.value(d);
        double d3 = d2;
        while (d3 < Double.MAX_VALUE) {
            double d4 = d + d3;
            double value2 = univariateFunction.value(d4);
            if (value * value2 <= 0.0d) {
                return d4;
            }
            d3 *= FastMath.max(2.0d, value / value2);
        }
        throw new MathIllegalStateException(LocalizedFormats.UNABLE_TO_BRACKET_OPTIMUM_IN_LINE_SEARCH, new Object[0]);
    }
}
