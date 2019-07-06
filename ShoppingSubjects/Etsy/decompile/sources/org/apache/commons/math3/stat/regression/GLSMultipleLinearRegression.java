package org.apache.commons.math3.stat.regression;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public class GLSMultipleLinearRegression extends AbstractMultipleLinearRegression {
    private RealMatrix Omega;
    private RealMatrix OmegaInverse;

    public void newSampleData(double[] dArr, double[][] dArr2, double[][] dArr3) {
        validateSampleData(dArr2, dArr);
        newYSampleData(dArr);
        newXSampleData(dArr2);
        validateCovarianceData(dArr2, dArr3);
        newCovarianceData(dArr3);
    }

    /* access modifiers changed from: protected */
    public void newCovarianceData(double[][] dArr) {
        this.Omega = new Array2DRowRealMatrix(dArr);
        this.OmegaInverse = null;
    }

    /* access modifiers changed from: protected */
    public RealMatrix getOmegaInverse() {
        if (this.OmegaInverse == null) {
            this.OmegaInverse = new LUDecomposition(this.Omega).getSolver().getInverse();
        }
        return this.OmegaInverse;
    }

    /* access modifiers changed from: protected */
    public RealVector calculateBeta() {
        RealMatrix omegaInverse = getOmegaInverse();
        RealMatrix transpose = getX().transpose();
        return new LUDecomposition(transpose.multiply(omegaInverse).multiply(getX())).getSolver().getInverse().multiply(transpose).multiply(omegaInverse).operate(getY());
    }

    /* access modifiers changed from: protected */
    public RealMatrix calculateBetaVariance() {
        return new LUDecomposition(getX().transpose().multiply(getOmegaInverse()).multiply(getX())).getSolver().getInverse();
    }

    /* access modifiers changed from: protected */
    public double calculateErrorVariance() {
        RealVector calculateResiduals = calculateResiduals();
        return calculateResiduals.dotProduct(getOmegaInverse().operate(calculateResiduals)) / ((double) (getX().getRowDimension() - getX().getColumnDimension()));
    }
}
