package org.apache.commons.math3.optim.nonlinear.scalar.gradient;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BrentSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.nonlinear.scalar.GradientMultivariateOptimizer;
import org.apache.commons.math3.util.FastMath;

public class NonLinearConjugateGradientOptimizer extends GradientMultivariateOptimizer {
    private double initialStep;
    private final Preconditioner preconditioner;
    private final UnivariateSolver solver;
    private final Formula updateFormula;

    public static class BracketingStep implements OptimizationData {
        private final double initialStep;

        public BracketingStep(double d) {
            this.initialStep = d;
        }

        public double getBracketingStep() {
            return this.initialStep;
        }
    }

    public enum Formula {
        FLETCHER_REEVES,
        POLAK_RIBIERE
    }

    public static class IdentityPreconditioner implements Preconditioner {
        public double[] precondition(double[] dArr, double[] dArr2) {
            return (double[]) dArr2.clone();
        }
    }

    private class LineSearchFunction implements UnivariateFunction {
        private final double[] currentPoint;
        private final double[] searchDirection;

        public LineSearchFunction(double[] dArr, double[] dArr2) {
            this.currentPoint = (double[]) dArr.clone();
            this.searchDirection = (double[]) dArr2.clone();
        }

        public double value(double d) {
            double[] dArr = (double[]) this.currentPoint.clone();
            for (int i = 0; i < dArr.length; i++) {
                dArr[i] = dArr[i] + (this.searchDirection[i] * d);
            }
            double[] access$000 = NonLinearConjugateGradientOptimizer.this.computeObjectiveGradient(dArr);
            double d2 = 0.0d;
            for (int i2 = 0; i2 < access$000.length; i2++) {
                d2 += access$000[i2] * this.searchDirection[i2];
            }
            return d2;
        }
    }

    public NonLinearConjugateGradientOptimizer(Formula formula, ConvergenceChecker<PointValuePair> convergenceChecker) {
        this(formula, convergenceChecker, new BrentSolver(), new IdentityPreconditioner());
    }

    public NonLinearConjugateGradientOptimizer(Formula formula, ConvergenceChecker<PointValuePair> convergenceChecker, UnivariateSolver univariateSolver) {
        this(formula, convergenceChecker, univariateSolver, new IdentityPreconditioner());
    }

    public NonLinearConjugateGradientOptimizer(Formula formula, ConvergenceChecker<PointValuePair> convergenceChecker, UnivariateSolver univariateSolver, Preconditioner preconditioner2) {
        super(convergenceChecker);
        this.initialStep = 1.0d;
        this.updateFormula = formula;
        this.solver = univariateSolver;
        this.preconditioner = preconditioner2;
        this.initialStep = 1.0d;
    }

    public PointValuePair optimize(OptimizationData... optimizationDataArr) throws TooManyEvaluationsException {
        parseOptimizationData(optimizationDataArr);
        return super.optimize(optimizationDataArr);
    }

    /* access modifiers changed from: protected */
    public PointValuePair doOptimize() {
        double d;
        ConvergenceChecker convergenceChecker = getConvergenceChecker();
        double[] startPoint = getStartPoint();
        GoalType goalType = getGoalType();
        int length = startPoint.length;
        double[] computeObjectiveGradient = computeObjectiveGradient(startPoint);
        if (goalType == GoalType.MINIMIZE) {
            for (int i = 0; i < length; i++) {
                computeObjectiveGradient[i] = -computeObjectiveGradient[i];
            }
        }
        double[] precondition = this.preconditioner.precondition(startPoint, computeObjectiveGradient);
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
            PointValuePair pointValuePair2 = new PointValuePair(startPoint, computeObjectiveValue(startPoint));
            if (pointValuePair != null && convergenceChecker.converged(i4, pointValuePair, pointValuePair2)) {
                return pointValuePair2;
            }
            LineSearchFunction lineSearchFunction = new LineSearchFunction(startPoint, dArr3);
            PointValuePair pointValuePair3 = pointValuePair2;
            int i5 = i4;
            double solve = this.solver.solve(maxEvaluations, lineSearchFunction, 0.0d, findUpperBound(lineSearchFunction, 0.0d, this.initialStep), 1.0E-15d);
            maxEvaluations -= this.solver.getEvaluations();
            for (int i6 = 0; i6 < startPoint.length; i6++) {
                startPoint[i6] = startPoint[i6] + (dArr3[i6] * solve);
            }
            double[] computeObjectiveGradient2 = computeObjectiveGradient(startPoint);
            if (goalType == GoalType.MINIMIZE) {
                for (int i7 = 0; i7 < length; i7++) {
                    computeObjectiveGradient2[i7] = -computeObjectiveGradient2[i7];
                }
            }
            double[] precondition2 = this.preconditioner.precondition(startPoint, computeObjectiveGradient2);
            double d4 = 0.0d;
            for (int i8 = 0; i8 < length; i8++) {
                d4 += computeObjectiveGradient2[i8] * precondition2[i8];
            }
            switch (this.updateFormula) {
                case FLETCHER_REEVES:
                    d = d4 / d3;
                    break;
                case POLAK_RIBIERE:
                    double d5 = 0.0d;
                    for (int i9 = 0; i9 < computeObjectiveGradient2.length; i9++) {
                        d5 += computeObjectiveGradient2[i9] * dArr2[i9];
                    }
                    d = (d4 - d5) / d3;
                    break;
                default:
                    throw new MathInternalError();
            }
            if (i5 % length == 0 || d < 0.0d) {
                dArr3 = (double[]) precondition2.clone();
            } else {
                for (int i10 = 0; i10 < length; i10++) {
                    dArr3[i10] = precondition2[i10] + (dArr3[i10] * d);
                }
            }
            dArr2 = precondition2;
            i3 = i5;
            d3 = d4;
            pointValuePair = pointValuePair3;
        }
    }

    private void parseOptimizationData(OptimizationData... optimizationDataArr) {
        for (BracketingStep bracketingStep : optimizationDataArr) {
            if (bracketingStep instanceof BracketingStep) {
                this.initialStep = bracketingStep.getBracketingStep();
                return;
            }
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
