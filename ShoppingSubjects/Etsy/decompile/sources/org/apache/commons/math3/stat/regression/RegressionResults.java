package org.apache.commons.math3.stat.regression;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class RegressionResults implements Serializable {
    private static final int ADJRSQ_IDX = 4;
    private static final int MSE_IDX = 3;
    private static final int RSQ_IDX = 2;
    private static final int SSE_IDX = 0;
    private static final int SST_IDX = 1;
    private static final long serialVersionUID = 1;
    private final boolean containsConstant;
    private final double[] globalFitInfo;
    private final boolean isSymmetricVCD;
    private final long nobs;
    private final double[] parameters;
    private final int rank;
    private final double[][] varCovData;

    private RegressionResults() {
        this.parameters = null;
        this.varCovData = null;
        this.rank = -1;
        this.nobs = -1;
        this.containsConstant = false;
        this.isSymmetricVCD = false;
        this.globalFitInfo = null;
    }

    public RegressionResults(double[] dArr, double[][] dArr2, boolean z, long j, int i, double d, double d2, double d3, boolean z2, boolean z3) {
        double[][] dArr3 = dArr2;
        long j2 = j;
        int i2 = i;
        boolean z4 = z2;
        if (z3) {
            this.parameters = MathArrays.copyOf(dArr);
            this.varCovData = new double[dArr3.length][];
            for (int i3 = 0; i3 < dArr3.length; i3++) {
                this.varCovData[i3] = MathArrays.copyOf(dArr3[i3]);
            }
        } else {
            this.parameters = dArr;
            this.varCovData = dArr3;
        }
        this.isSymmetricVCD = z;
        this.nobs = j2;
        this.rank = i2;
        this.containsConstant = z4;
        this.globalFitInfo = new double[5];
        Arrays.fill(this.globalFitInfo, Double.NaN);
        if (i2 > 0) {
            this.globalFitInfo[1] = z4 ? d2 - ((d * d) / ((double) j2)) : d2;
        }
        this.globalFitInfo[0] = d3;
        double d4 = (double) (j2 - ((long) i2));
        this.globalFitInfo[3] = this.globalFitInfo[0] / d4;
        this.globalFitInfo[2] = 1.0d - (this.globalFitInfo[0] / this.globalFitInfo[1]);
        if (!z4) {
            this.globalFitInfo[4] = 1.0d - ((1.0d - this.globalFitInfo[2]) * (((double) j2) / d4));
        } else {
            this.globalFitInfo[4] = 1.0d - (((((double) j2) - 1.0d) * d3) / (this.globalFitInfo[1] * d4));
        }
    }

    public double getParameterEstimate(int i) throws OutOfRangeException {
        if (this.parameters == null) {
            return Double.NaN;
        }
        if (i >= 0 && i < this.parameters.length) {
            return this.parameters[i];
        }
        throw new OutOfRangeException(Integer.valueOf(i), Integer.valueOf(0), Integer.valueOf(this.parameters.length - 1));
    }

    public double[] getParameterEstimates() {
        if (this.parameters == null) {
            return null;
        }
        return MathArrays.copyOf(this.parameters);
    }

    public double getStdErrorOfEstimate(int i) throws OutOfRangeException {
        if (this.parameters == null) {
            return Double.NaN;
        }
        if (i < 0 || i >= this.parameters.length) {
            throw new OutOfRangeException(Integer.valueOf(i), Integer.valueOf(0), Integer.valueOf(this.parameters.length - 1));
        }
        double vcvElement = getVcvElement(i, i);
        if (Double.isNaN(vcvElement) || vcvElement <= Double.MIN_VALUE) {
            return Double.NaN;
        }
        return FastMath.sqrt(vcvElement);
    }

    public double[] getStdErrorOfEstimates() {
        if (this.parameters == null) {
            return null;
        }
        double[] dArr = new double[this.parameters.length];
        for (int i = 0; i < this.parameters.length; i++) {
            double vcvElement = getVcvElement(i, i);
            if (Double.isNaN(vcvElement) || vcvElement <= Double.MIN_VALUE) {
                dArr[i] = Double.NaN;
            } else {
                dArr[i] = FastMath.sqrt(vcvElement);
            }
        }
        return dArr;
    }

    public double getCovarianceOfParameters(int i, int i2) throws OutOfRangeException {
        if (this.parameters == null) {
            return Double.NaN;
        }
        if (i < 0 || i >= this.parameters.length) {
            throw new OutOfRangeException(Integer.valueOf(i), Integer.valueOf(0), Integer.valueOf(this.parameters.length - 1));
        } else if (i2 >= 0 && i2 < this.parameters.length) {
            return getVcvElement(i, i2);
        } else {
            throw new OutOfRangeException(Integer.valueOf(i2), Integer.valueOf(0), Integer.valueOf(this.parameters.length - 1));
        }
    }

    public int getNumberOfParameters() {
        if (this.parameters == null) {
            return -1;
        }
        return this.parameters.length;
    }

    public long getN() {
        return this.nobs;
    }

    public double getTotalSumSquares() {
        return this.globalFitInfo[1];
    }

    public double getRegressionSumSquares() {
        return this.globalFitInfo[1] - this.globalFitInfo[0];
    }

    public double getErrorSumSquares() {
        return this.globalFitInfo[0];
    }

    public double getMeanSquareError() {
        return this.globalFitInfo[3];
    }

    public double getRSquared() {
        return this.globalFitInfo[2];
    }

    public double getAdjustedRSquared() {
        return this.globalFitInfo[4];
    }

    public boolean hasIntercept() {
        return this.containsConstant;
    }

    private double getVcvElement(int i, int i2) {
        if (!this.isSymmetricVCD) {
            return this.varCovData[i][i2];
        }
        if (this.varCovData.length > 1) {
            if (i == i2) {
                return this.varCovData[i][i];
            }
            if (i >= this.varCovData[i2].length) {
                return this.varCovData[i][i2];
            }
            return this.varCovData[i2][i];
        } else if (i > i2) {
            return this.varCovData[0][(((i + 1) * i) / 2) + i2];
        } else {
            return this.varCovData[0][(((i2 + 1) * i2) / 2) + i];
        }
    }
}
