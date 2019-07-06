package org.apache.commons.math3.optimization.direct;

import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.optimization.BaseMultivariateVectorOptimizer;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.InitialGuess;
import org.apache.commons.math3.optimization.OptimizationData;
import org.apache.commons.math3.optimization.PointVectorValuePair;
import org.apache.commons.math3.optimization.SimpleVectorValueChecker;
import org.apache.commons.math3.optimization.Target;
import org.apache.commons.math3.optimization.Weight;
import org.apache.commons.math3.util.Incrementor;

@Deprecated
public abstract class BaseAbstractMultivariateVectorOptimizer<FUNC extends MultivariateVectorFunction> implements BaseMultivariateVectorOptimizer<FUNC> {
    private ConvergenceChecker<PointVectorValuePair> checker;
    protected final Incrementor evaluations;
    private FUNC function;
    private double[] start;
    private double[] target;
    private double[] weight;
    private RealMatrix weightMatrix;

    /* access modifiers changed from: protected */
    public abstract PointVectorValuePair doOptimize();

    @Deprecated
    protected BaseAbstractMultivariateVectorOptimizer() {
        this(new SimpleVectorValueChecker());
    }

    protected BaseAbstractMultivariateVectorOptimizer(ConvergenceChecker<PointVectorValuePair> convergenceChecker) {
        this.evaluations = new Incrementor();
        this.checker = convergenceChecker;
    }

    public int getMaxEvaluations() {
        return this.evaluations.getMaximalCount();
    }

    public int getEvaluations() {
        return this.evaluations.getCount();
    }

    public ConvergenceChecker<PointVectorValuePair> getConvergenceChecker() {
        return this.checker;
    }

    /* access modifiers changed from: protected */
    public double[] computeObjectiveValue(double[] dArr) {
        try {
            this.evaluations.incrementCount();
            return this.function.value(dArr);
        } catch (MaxCountExceededException e) {
            throw new TooManyEvaluationsException(e.getMax());
        }
    }

    @Deprecated
    public PointVectorValuePair optimize(int i, FUNC func, double[] dArr, double[] dArr2, double[] dArr3) {
        return optimizeInternal(i, func, dArr, dArr2, dArr3);
    }

    /* access modifiers changed from: protected */
    public PointVectorValuePair optimize(int i, FUNC func, OptimizationData... optimizationDataArr) throws TooManyEvaluationsException, DimensionMismatchException {
        return optimizeInternal(i, func, optimizationDataArr);
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public PointVectorValuePair optimizeInternal(int i, FUNC func, double[] dArr, double[] dArr2, double[] dArr3) {
        if (func == null) {
            throw new NullArgumentException();
        } else if (dArr == null) {
            throw new NullArgumentException();
        } else if (dArr2 == null) {
            throw new NullArgumentException();
        } else if (dArr3 == null) {
            throw new NullArgumentException();
        } else if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        } else {
            return optimizeInternal(i, func, new Target(dArr), new Weight(dArr2), new InitialGuess(dArr3));
        }
    }

    /* access modifiers changed from: protected */
    public PointVectorValuePair optimizeInternal(int i, FUNC func, OptimizationData... optimizationDataArr) throws TooManyEvaluationsException, DimensionMismatchException {
        this.evaluations.setMaximalCount(i);
        this.evaluations.resetCount();
        this.function = func;
        parseOptimizationData(optimizationDataArr);
        checkParameters();
        setUp();
        return doOptimize();
    }

    public double[] getStartPoint() {
        return (double[]) this.start.clone();
    }

    public RealMatrix getWeight() {
        return this.weightMatrix.copy();
    }

    public double[] getTarget() {
        return (double[]) this.target.clone();
    }

    /* access modifiers changed from: protected */
    public FUNC getObjectiveFunction() {
        return this.function;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public double[] getTargetRef() {
        return this.target;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public double[] getWeightRef() {
        return this.weight;
    }

    /* access modifiers changed from: protected */
    public void setUp() {
        int length = this.target.length;
        this.weight = new double[length];
        for (int i = 0; i < length; i++) {
            this.weight[i] = this.weightMatrix.getEntry(i, i);
        }
    }

    private void parseOptimizationData(OptimizationData... optimizationDataArr) {
        for (Target target2 : optimizationDataArr) {
            if (target2 instanceof Target) {
                this.target = target2.getTarget();
            } else if (target2 instanceof Weight) {
                this.weightMatrix = ((Weight) target2).getWeight();
            } else if (target2 instanceof InitialGuess) {
                this.start = ((InitialGuess) target2).getInitialGuess();
            }
        }
    }

    private void checkParameters() {
        if (this.target.length != this.weightMatrix.getColumnDimension()) {
            throw new DimensionMismatchException(this.target.length, this.weightMatrix.getColumnDimension());
        }
    }
}
