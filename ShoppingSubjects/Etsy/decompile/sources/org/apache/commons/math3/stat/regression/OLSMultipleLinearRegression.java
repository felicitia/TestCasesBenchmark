package org.apache.commons.math3.stat.regression;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.moment.SecondMoment;

public class OLSMultipleLinearRegression extends AbstractMultipleLinearRegression {
    private QRDecomposition qr = null;

    public void newSampleData(double[] dArr, double[][] dArr2) throws MathIllegalArgumentException {
        validateSampleData(dArr2, dArr);
        newYSampleData(dArr);
        newXSampleData(dArr2);
    }

    public void newSampleData(double[] dArr, int i, int i2) {
        super.newSampleData(dArr, i, i2);
        this.qr = new QRDecomposition(getX());
    }

    public RealMatrix calculateHat() {
        RealMatrix q = this.qr.getQ();
        int columnDimension = this.qr.getR().getColumnDimension();
        int columnDimension2 = q.getColumnDimension();
        Array2DRowRealMatrix array2DRowRealMatrix = new Array2DRowRealMatrix(columnDimension2, columnDimension2);
        double[][] dataRef = array2DRowRealMatrix.getDataRef();
        for (int i = 0; i < columnDimension2; i++) {
            for (int i2 = 0; i2 < columnDimension2; i2++) {
                if (i != i2 || i >= columnDimension) {
                    dataRef[i][i2] = 0.0d;
                } else {
                    dataRef[i][i2] = 1.0d;
                }
            }
        }
        return q.multiply(array2DRowRealMatrix).multiply(q.transpose());
    }

    public double calculateTotalSumOfSquares() throws MathIllegalArgumentException {
        if (isNoIntercept()) {
            return StatUtils.sumSq(getY().toArray());
        }
        return new SecondMoment().evaluate(getY().toArray());
    }

    public double calculateResidualSumOfSquares() {
        RealVector calculateResiduals = calculateResiduals();
        return calculateResiduals.dotProduct(calculateResiduals);
    }

    public double calculateRSquared() throws MathIllegalArgumentException {
        return 1.0d - (calculateResidualSumOfSquares() / calculateTotalSumOfSquares());
    }

    public double calculateAdjustedRSquared() throws MathIllegalArgumentException {
        double rowDimension = (double) getX().getRowDimension();
        if (isNoIntercept()) {
            return 1.0d - ((1.0d - calculateRSquared()) * (rowDimension / (rowDimension - ((double) getX().getColumnDimension()))));
        }
        return 1.0d - ((calculateResidualSumOfSquares() * (rowDimension - 1.0d)) / (calculateTotalSumOfSquares() * (rowDimension - ((double) getX().getColumnDimension()))));
    }

    /* access modifiers changed from: protected */
    public void newXSampleData(double[][] dArr) {
        super.newXSampleData(dArr);
        this.qr = new QRDecomposition(getX());
    }

    /* access modifiers changed from: protected */
    public RealVector calculateBeta() {
        return this.qr.getSolver().solve(getY());
    }

    /* access modifiers changed from: protected */
    public RealMatrix calculateBetaVariance() {
        int columnDimension = getX().getColumnDimension() - 1;
        RealMatrix inverse = new LUDecomposition(this.qr.getR().getSubMatrix(0, columnDimension, 0, columnDimension)).getSolver().getInverse();
        return inverse.multiply(inverse.transpose());
    }
}
