package org.apache.commons.math3.optim.nonlinear.vector.jacobian;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointVectorValuePair;
import org.apache.commons.math3.optim.nonlinear.vector.JacobianMultivariateVectorOptimizer;
import org.apache.commons.math3.optim.nonlinear.vector.Weight;
import org.apache.commons.math3.util.FastMath;

public abstract class AbstractLeastSquaresOptimizer extends JacobianMultivariateVectorOptimizer {
    private double cost;
    private RealMatrix weightMatrixSqrt;

    protected AbstractLeastSquaresOptimizer(ConvergenceChecker<PointVectorValuePair> convergenceChecker) {
        super(convergenceChecker);
    }

    /* access modifiers changed from: protected */
    public RealMatrix computeWeightedJacobian(double[] dArr) {
        return this.weightMatrixSqrt.multiply(MatrixUtils.createRealMatrix(computeJacobian(dArr)));
    }

    /* access modifiers changed from: protected */
    public double computeCost(double[] dArr) {
        ArrayRealVector arrayRealVector = new ArrayRealVector(dArr);
        return FastMath.sqrt(arrayRealVector.dotProduct(getWeight().operate((RealVector) arrayRealVector)));
    }

    public double getRMS() {
        return FastMath.sqrt(getChiSquare() / ((double) getTargetSize()));
    }

    public double getChiSquare() {
        return this.cost * this.cost;
    }

    public RealMatrix getWeightSquareRoot() {
        return this.weightMatrixSqrt.copy();
    }

    /* access modifiers changed from: protected */
    public void setCost(double d) {
        this.cost = d;
    }

    public double[][] computeCovariances(double[] dArr, double d) {
        RealMatrix computeWeightedJacobian = computeWeightedJacobian(dArr);
        return new QRDecomposition(computeWeightedJacobian.transpose().multiply(computeWeightedJacobian), d).getSolver().getInverse().getData();
    }

    public double[] computeSigma(double[] dArr, double d) {
        int length = dArr.length;
        double[] dArr2 = new double[length];
        double[][] computeCovariances = computeCovariances(dArr, d);
        for (int i = 0; i < length; i++) {
            dArr2[i] = FastMath.sqrt(computeCovariances[i][i]);
        }
        return dArr2;
    }

    public PointVectorValuePair optimize(OptimizationData... optimizationDataArr) throws TooManyEvaluationsException {
        parseOptimizationData(optimizationDataArr);
        return super.optimize(optimizationDataArr);
    }

    /* access modifiers changed from: protected */
    public double[] computeResiduals(double[] dArr) {
        double[] target = getTarget();
        if (dArr.length != target.length) {
            throw new DimensionMismatchException(target.length, dArr.length);
        }
        double[] dArr2 = new double[target.length];
        for (int i = 0; i < target.length; i++) {
            dArr2[i] = target[i] - dArr[i];
        }
        return dArr2;
    }

    private void parseOptimizationData(OptimizationData... optimizationDataArr) {
        for (Weight weight : optimizationDataArr) {
            if (weight instanceof Weight) {
                this.weightMatrixSqrt = squareRoot(weight.getWeight());
                return;
            }
        }
    }

    private RealMatrix squareRoot(RealMatrix realMatrix) {
        return new EigenDecomposition(realMatrix).getSquareRoot();
    }
}
