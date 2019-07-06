package org.apache.commons.math3.optim.nonlinear.vector;

import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.optim.BaseMultivariateOptimizer;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointVectorValuePair;

public abstract class MultivariateVectorOptimizer extends BaseMultivariateOptimizer<PointVectorValuePair> {
    private MultivariateVectorFunction model;
    private double[] target;
    private RealMatrix weightMatrix;

    protected MultivariateVectorOptimizer(ConvergenceChecker<PointVectorValuePair> convergenceChecker) {
        super(convergenceChecker);
    }

    /* access modifiers changed from: protected */
    public double[] computeObjectiveValue(double[] dArr) {
        super.incrementEvaluationCount();
        return this.model.value(dArr);
    }

    public PointVectorValuePair optimize(OptimizationData... optimizationDataArr) throws TooManyEvaluationsException, DimensionMismatchException {
        parseOptimizationData(optimizationDataArr);
        checkParameters();
        return (PointVectorValuePair) super.optimize(optimizationDataArr);
    }

    public RealMatrix getWeight() {
        return this.weightMatrix.copy();
    }

    public double[] getTarget() {
        return (double[]) this.target.clone();
    }

    public int getTargetSize() {
        return this.target.length;
    }

    private void parseOptimizationData(OptimizationData... optimizationDataArr) {
        for (ModelFunction modelFunction : optimizationDataArr) {
            if (modelFunction instanceof ModelFunction) {
                this.model = modelFunction.getModelFunction();
            } else if (modelFunction instanceof Target) {
                this.target = ((Target) modelFunction).getTarget();
            } else if (modelFunction instanceof Weight) {
                this.weightMatrix = ((Weight) modelFunction).getWeight();
            }
        }
    }

    private void checkParameters() {
        if (this.target.length != this.weightMatrix.getColumnDimension()) {
            throw new DimensionMismatchException(this.target.length, this.weightMatrix.getColumnDimension());
        }
    }
}
