package org.apache.commons.math3.stat.regression;

import java.io.Serializable;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

public class SimpleRegression implements Serializable, UpdatingMultipleLinearRegression {
    private static final long serialVersionUID = -3004689053607543335L;
    private final boolean hasIntercept;
    private long n;
    private double sumX;
    private double sumXX;
    private double sumXY;
    private double sumY;
    private double sumYY;
    private double xbar;
    private double ybar;

    public SimpleRegression() {
        this(true);
    }

    public SimpleRegression(boolean z) {
        this.sumX = 0.0d;
        this.sumXX = 0.0d;
        this.sumY = 0.0d;
        this.sumYY = 0.0d;
        this.sumXY = 0.0d;
        this.n = 0;
        this.xbar = 0.0d;
        this.ybar = 0.0d;
        this.hasIntercept = z;
    }

    public void addData(double d, double d2) {
        double d3 = d;
        double d4 = d2;
        if (this.n == 0) {
            this.xbar = d3;
            this.ybar = d4;
        } else if (this.hasIntercept) {
            double d5 = ((double) this.n) + 1.0d;
            double d6 = ((double) this.n) / (1.0d + ((double) this.n));
            double d7 = d3 - this.xbar;
            double d8 = d4 - this.ybar;
            this.sumXX += d7 * d7 * d6;
            this.sumYY += d8 * d8 * d6;
            this.sumXY += d7 * d8 * d6;
            this.xbar += d7 / d5;
            this.ybar += d8 / d5;
        }
        if (!this.hasIntercept) {
            this.sumXX += d3 * d3;
            this.sumYY += d4 * d4;
            this.sumXY += d3 * d4;
        }
        this.sumX += d3;
        this.sumY += d4;
        this.n++;
    }

    public void removeData(double d, double d2) {
        if (this.n > 0) {
            if (this.hasIntercept) {
                double d3 = ((double) this.n) - 1.0d;
                double d4 = ((double) this.n) / (((double) this.n) - 1.0d);
                double d5 = d - this.xbar;
                double d6 = d2 - this.ybar;
                this.sumXX -= (d5 * d5) * d4;
                this.sumYY -= (d6 * d6) * d4;
                this.sumXY -= (d5 * d6) * d4;
                this.xbar -= d5 / d3;
                this.ybar -= d6 / d3;
            } else {
                double d7 = ((double) this.n) - 1.0d;
                this.sumXX -= d * d;
                this.sumYY -= d2 * d2;
                this.sumXY -= d * d2;
                this.xbar -= d / d7;
                this.ybar -= d2 / d7;
            }
            this.sumX -= d;
            this.sumY -= d2;
            this.n--;
        }
    }

    public void addData(double[][] dArr) throws ModelSpecificationException {
        for (int i = 0; i < dArr.length; i++) {
            if (dArr[i].length < 2) {
                throw new ModelSpecificationException(LocalizedFormats.INVALID_REGRESSION_OBSERVATION, Integer.valueOf(dArr[i].length), Integer.valueOf(2));
            }
            addData(dArr[i][0], dArr[i][1]);
        }
    }

    public void addObservation(double[] dArr, double d) throws ModelSpecificationException {
        if (dArr == null || dArr.length == 0) {
            LocalizedFormats localizedFormats = LocalizedFormats.INVALID_REGRESSION_OBSERVATION;
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(dArr != null ? dArr.length : 0);
            objArr[1] = Integer.valueOf(1);
            throw new ModelSpecificationException(localizedFormats, objArr);
        }
        addData(dArr[0], d);
    }

    public void addObservations(double[][] dArr, double[] dArr2) throws ModelSpecificationException {
        int i = 0;
        if (dArr == null || dArr2 == null || dArr.length != dArr2.length) {
            LocalizedFormats localizedFormats = LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE;
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(dArr == null ? 0 : dArr.length);
            if (dArr2 != null) {
                i = dArr2.length;
            }
            objArr[1] = Integer.valueOf(i);
            throw new ModelSpecificationException(localizedFormats, objArr);
        }
        boolean z = true;
        for (int i2 = 0; i2 < dArr.length; i2++) {
            if (dArr[i2] == null || dArr[i2].length == 0) {
                z = false;
            }
        }
        if (!z) {
            throw new ModelSpecificationException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, Integer.valueOf(0), Integer.valueOf(1));
        }
        for (int i3 = 0; i3 < dArr.length; i3++) {
            addData(dArr[i3][0], dArr2[i3]);
        }
    }

    public void removeData(double[][] dArr) {
        for (int i = 0; i < dArr.length && this.n > 0; i++) {
            removeData(dArr[i][0], dArr[i][1]);
        }
    }

    public void clear() {
        this.sumX = 0.0d;
        this.sumXX = 0.0d;
        this.sumY = 0.0d;
        this.sumYY = 0.0d;
        this.sumXY = 0.0d;
        this.n = 0;
    }

    public long getN() {
        return this.n;
    }

    public double predict(double d) {
        double slope = getSlope();
        return this.hasIntercept ? getIntercept(slope) + (slope * d) : slope * d;
    }

    public double getIntercept() {
        if (this.hasIntercept) {
            return getIntercept(getSlope());
        }
        return 0.0d;
    }

    public boolean hasIntercept() {
        return this.hasIntercept;
    }

    public double getSlope() {
        if (this.n >= 2 && FastMath.abs(this.sumXX) >= 4.9E-323d) {
            return this.sumXY / this.sumXX;
        }
        return Double.NaN;
    }

    public double getSumSquaredErrors() {
        return FastMath.max(0.0d, this.sumYY - ((this.sumXY * this.sumXY) / this.sumXX));
    }

    public double getTotalSumSquares() {
        if (this.n < 2) {
            return Double.NaN;
        }
        return this.sumYY;
    }

    public double getXSumSquares() {
        if (this.n < 2) {
            return Double.NaN;
        }
        return this.sumXX;
    }

    public double getSumOfCrossProducts() {
        return this.sumXY;
    }

    public double getRegressionSumSquares() {
        return getRegressionSumSquares(getSlope());
    }

    public double getMeanSquareError() {
        double sumSquaredErrors;
        long j;
        long j2;
        if (this.n < 3) {
            return Double.NaN;
        }
        if (this.hasIntercept) {
            sumSquaredErrors = getSumSquaredErrors();
            j = this.n;
            j2 = 2;
        } else {
            sumSquaredErrors = getSumSquaredErrors();
            j = this.n;
            j2 = 1;
        }
        return sumSquaredErrors / ((double) (j - j2));
    }

    public double getR() {
        double slope = getSlope();
        double sqrt = FastMath.sqrt(getRSquare());
        return slope < 0.0d ? -sqrt : sqrt;
    }

    public double getRSquare() {
        double totalSumSquares = getTotalSumSquares();
        return (totalSumSquares - getSumSquaredErrors()) / totalSumSquares;
    }

    public double getInterceptStdErr() {
        if (!this.hasIntercept) {
            return Double.NaN;
        }
        return FastMath.sqrt(getMeanSquareError() * ((1.0d / ((double) this.n)) + ((this.xbar * this.xbar) / this.sumXX)));
    }

    public double getSlopeStdErr() {
        return FastMath.sqrt(getMeanSquareError() / this.sumXX);
    }

    public double getSlopeConfidenceInterval() throws OutOfRangeException {
        return getSlopeConfidenceInterval(0.05d);
    }

    public double getSlopeConfidenceInterval(double d) throws OutOfRangeException {
        if (this.n < 3) {
            return Double.NaN;
        }
        if (d >= 1.0d || d <= 0.0d) {
            throw new OutOfRangeException(LocalizedFormats.SIGNIFICANCE_LEVEL, Double.valueOf(d), Integer.valueOf(0), Integer.valueOf(1));
        }
        return getSlopeStdErr() * new TDistribution((double) (this.n - 2)).inverseCumulativeProbability(1.0d - (d / 2.0d));
    }

    public double getSignificance() {
        if (this.n < 3) {
            return Double.NaN;
        }
        return 2.0d * (1.0d - new TDistribution((double) (this.n - 2)).cumulativeProbability(FastMath.abs(getSlope()) / getSlopeStdErr()));
    }

    private double getIntercept(double d) {
        if (this.hasIntercept) {
            return (this.sumY - (d * this.sumX)) / ((double) this.n);
        }
        return 0.0d;
    }

    private double getRegressionSumSquares(double d) {
        return d * d * this.sumXX;
    }

    public RegressionResults regress() throws ModelSpecificationException, NoDataException {
        if (this.hasIntercept) {
            if (this.n < 3) {
                throw new NoDataException(LocalizedFormats.NOT_ENOUGH_DATA_REGRESSION);
            } else if (FastMath.abs(this.sumXX) > Precision.SAFE_MIN) {
                double[] dArr = {getIntercept(), getSlope()};
                double meanSquareError = getMeanSquareError();
                double d = this.sumYY + ((this.sumY * this.sumY) / ((double) this.n));
                RegressionResults regressionResults = new RegressionResults(dArr, new double[][]{new double[]{(((this.xbar * this.xbar) / this.sumXX) + (1.0d / ((double) this.n))) * meanSquareError, ((-this.xbar) * meanSquareError) / this.sumXX, meanSquareError / this.sumXX}}, true, this.n, 2, this.sumY, d, getSumSquaredErrors(), true, false);
                return regressionResults;
            } else {
                double[] dArr2 = {this.sumY / ((double) this.n), Double.NaN};
                double[][] dArr3 = {new double[]{this.ybar / (((double) this.n) - 1.0d), Double.NaN, Double.NaN}};
                long j = this.n;
                double d2 = this.sumY;
                RegressionResults regressionResults2 = new RegressionResults(dArr2, dArr3, true, j, 1, d2, this.sumYY, getSumSquaredErrors(), true, false);
                return regressionResults2;
            }
        } else if (this.n < 2) {
            throw new NoDataException(LocalizedFormats.NOT_ENOUGH_DATA_REGRESSION);
        } else if (!Double.isNaN(this.sumXX)) {
            RegressionResults regressionResults3 = new RegressionResults(new double[]{this.sumXY / this.sumXX}, new double[][]{new double[]{getMeanSquareError() / this.sumXX}}, true, this.n, 1, this.sumY, this.sumYY, getSumSquaredErrors(), false, false);
            return regressionResults3;
        } else {
            RegressionResults regressionResults4 = new RegressionResults(new double[]{Double.NaN}, new double[][]{new double[]{Double.NaN}}, true, this.n, 1, Double.NaN, Double.NaN, Double.NaN, false, false);
            return regressionResults4;
        }
    }

    public RegressionResults regress(int[] iArr) throws MathIllegalArgumentException {
        int[] iArr2 = iArr;
        if (iArr2 == null || iArr2.length == 0) {
            throw new MathIllegalArgumentException(LocalizedFormats.ARRAY_ZERO_LENGTH_OR_NULL_NOT_ALLOWED, new Object[0]);
        }
        int i = 2;
        if (iArr2.length > 2 || (iArr2.length > 1 && !this.hasIntercept)) {
            LocalizedFormats localizedFormats = LocalizedFormats.ARRAY_SIZE_EXCEEDS_MAX_VARIABLES;
            Object[] objArr = new Object[1];
            if (iArr2.length > 1 && !this.hasIntercept) {
                i = 1;
            }
            objArr[0] = Integer.valueOf(i);
            throw new ModelSpecificationException(localizedFormats, objArr);
        } else if (this.hasIntercept) {
            if (iArr2.length == 2) {
                if (iArr2[0] == 1) {
                    throw new ModelSpecificationException(LocalizedFormats.NOT_INCREASING_SEQUENCE, new Object[0]);
                } else if (iArr2[0] != 0) {
                    throw new OutOfRangeException(Integer.valueOf(iArr2[0]), Integer.valueOf(0), Integer.valueOf(1));
                } else if (iArr2[1] == 1) {
                    return regress();
                } else {
                    throw new OutOfRangeException(Integer.valueOf(iArr2[0]), Integer.valueOf(0), Integer.valueOf(1));
                }
            } else if (iArr2[0] == 1 || iArr2[0] == 0) {
                double d = (this.sumY * this.sumY) / ((double) this.n);
                double d2 = this.sumYY + d;
                if (iArr2[0] == 0) {
                    double[] dArr = {this.ybar};
                    double[][] dArr2 = {new double[]{this.sumYY / ((double) ((this.n - 1) * this.n))}};
                    long j = this.n;
                    RegressionResults regressionResults = new RegressionResults(dArr, dArr2, true, j, 1, this.sumY, d2 + d, this.sumYY, true, false);
                    return regressionResults;
                } else if (iArr2[0] != 1) {
                    return null;
                } else {
                    double d3 = this.sumXX + ((this.sumX * this.sumX) / ((double) this.n));
                    double d4 = this.sumXY + ((this.sumX * this.sumY) / ((double) this.n));
                    double max = FastMath.max(0.0d, d2 - ((d4 * d4) / d3));
                    double d5 = max / ((double) (this.n - 1));
                    if (!Double.isNaN(d3)) {
                        double[] dArr3 = {d4 / d3};
                        RegressionResults regressionResults2 = new RegressionResults(dArr3, new double[][]{new double[]{d5 / d3}}, true, this.n, 1, this.sumY, d2, max, false, false);
                        return regressionResults2;
                    }
                    RegressionResults regressionResults3 = new RegressionResults(new double[]{Double.NaN}, new double[][]{new double[]{Double.NaN}}, true, this.n, 1, Double.NaN, Double.NaN, Double.NaN, false, false);
                    return regressionResults3;
                }
            } else {
                throw new OutOfRangeException(Integer.valueOf(iArr2[0]), Integer.valueOf(0), Integer.valueOf(1));
            }
        } else if (iArr2[0] == 0) {
            return regress();
        } else {
            throw new OutOfRangeException(Integer.valueOf(iArr2[0]), Integer.valueOf(0), Integer.valueOf(0));
        }
    }
}
