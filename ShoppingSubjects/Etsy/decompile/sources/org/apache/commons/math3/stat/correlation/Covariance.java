package org.apache.commons.math3.stat.correlation;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.Variance;

public class Covariance {
    private final RealMatrix covarianceMatrix;
    private final int n;

    public Covariance() {
        this.covarianceMatrix = null;
        this.n = 0;
    }

    public Covariance(double[][] dArr, boolean z) throws MathIllegalArgumentException {
        this((RealMatrix) new BlockRealMatrix(dArr), z);
    }

    public Covariance(double[][] dArr) throws MathIllegalArgumentException {
        this(dArr, true);
    }

    public Covariance(RealMatrix realMatrix, boolean z) throws MathIllegalArgumentException {
        checkSufficientData(realMatrix);
        this.n = realMatrix.getRowDimension();
        this.covarianceMatrix = computeCovarianceMatrix(realMatrix, z);
    }

    public Covariance(RealMatrix realMatrix) throws MathIllegalArgumentException {
        this(realMatrix, true);
    }

    public RealMatrix getCovarianceMatrix() {
        return this.covarianceMatrix;
    }

    public int getN() {
        return this.n;
    }

    /* access modifiers changed from: protected */
    public RealMatrix computeCovarianceMatrix(RealMatrix realMatrix, boolean z) throws MathIllegalArgumentException {
        int columnDimension = realMatrix.getColumnDimension();
        Variance variance = new Variance(z);
        BlockRealMatrix blockRealMatrix = new BlockRealMatrix(columnDimension, columnDimension);
        for (int i = 0; i < columnDimension; i++) {
            for (int i2 = 0; i2 < i; i2++) {
                double covariance = covariance(realMatrix.getColumn(i), realMatrix.getColumn(i2), z);
                blockRealMatrix.setEntry(i, i2, covariance);
                blockRealMatrix.setEntry(i2, i, covariance);
            }
            blockRealMatrix.setEntry(i, i, variance.evaluate(realMatrix.getColumn(i)));
        }
        return blockRealMatrix;
    }

    /* access modifiers changed from: protected */
    public RealMatrix computeCovarianceMatrix(RealMatrix realMatrix) throws MathIllegalArgumentException {
        return computeCovarianceMatrix(realMatrix, true);
    }

    /* access modifiers changed from: protected */
    public RealMatrix computeCovarianceMatrix(double[][] dArr, boolean z) throws MathIllegalArgumentException {
        return computeCovarianceMatrix((RealMatrix) new BlockRealMatrix(dArr), z);
    }

    /* access modifiers changed from: protected */
    public RealMatrix computeCovarianceMatrix(double[][] dArr) throws MathIllegalArgumentException {
        return computeCovarianceMatrix(dArr, true);
    }

    public double covariance(double[] dArr, double[] dArr2, boolean z) throws MathIllegalArgumentException {
        double[] dArr3 = dArr;
        double[] dArr4 = dArr2;
        Mean mean = new Mean();
        int i = 0;
        int length = dArr3.length;
        if (length != dArr4.length) {
            throw new MathIllegalArgumentException(LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, Integer.valueOf(length), Integer.valueOf(dArr4.length));
        } else if (length < 2) {
            throw new MathIllegalArgumentException(LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE, Integer.valueOf(length), Integer.valueOf(2));
        } else {
            double evaluate = mean.evaluate(dArr3);
            double evaluate2 = mean.evaluate(dArr4);
            double d = 0.0d;
            while (i < length) {
                i++;
                d += (((dArr3[i] - evaluate) * (dArr4[i] - evaluate2)) - d) / ((double) i);
            }
            return z ? d * (((double) length) / ((double) (length - 1))) : d;
        }
    }

    public double covariance(double[] dArr, double[] dArr2) throws MathIllegalArgumentException {
        return covariance(dArr, dArr2, true);
    }

    private void checkSufficientData(RealMatrix realMatrix) throws MathIllegalArgumentException {
        int rowDimension = realMatrix.getRowDimension();
        int columnDimension = realMatrix.getColumnDimension();
        if (rowDimension < 2 || columnDimension < 2) {
            throw new MathIllegalArgumentException(LocalizedFormats.INSUFFICIENT_ROWS_AND_COLUMNS, Integer.valueOf(rowDimension), Integer.valueOf(columnDimension));
        }
    }
}
