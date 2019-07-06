package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class VectorialCovariance implements Serializable {
    private static final long serialVersionUID = 4118372414238930270L;
    private final boolean isBiasCorrected;
    private long n = 0;
    private final double[] productsSums;
    private final double[] sums;

    public VectorialCovariance(int i, boolean z) {
        this.sums = new double[i];
        this.productsSums = new double[((i * (i + 1)) / 2)];
        this.isBiasCorrected = z;
    }

    public void increment(double[] dArr) throws DimensionMismatchException {
        if (dArr.length != this.sums.length) {
            throw new DimensionMismatchException(dArr.length, this.sums.length);
        }
        int i = 0;
        int i2 = 0;
        while (i < dArr.length) {
            double[] dArr2 = this.sums;
            dArr2[i] = dArr2[i] + dArr[i];
            int i3 = i2;
            int i4 = 0;
            while (i4 <= i) {
                double[] dArr3 = this.productsSums;
                int i5 = i3 + 1;
                dArr3[i3] = dArr3[i3] + (dArr[i] * dArr[i4]);
                i4++;
                i3 = i5;
            }
            i++;
            i2 = i3;
        }
        this.n++;
    }

    public RealMatrix getResult() {
        int length = this.sums.length;
        RealMatrix createRealMatrix = MatrixUtils.createRealMatrix(length, length);
        if (this.n > 1) {
            double d = 1.0d / ((double) (this.n * (this.isBiasCorrected ? this.n - 1 : this.n)));
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int i3 = i2;
                int i4 = 0;
                while (i4 <= i) {
                    int i5 = i3 + 1;
                    double d2 = ((((double) this.n) * this.productsSums[i3]) - (this.sums[i] * this.sums[i4])) * d;
                    createRealMatrix.setEntry(i, i4, d2);
                    createRealMatrix.setEntry(i4, i, d2);
                    i4++;
                    i3 = i5;
                }
                i++;
                i2 = i3;
            }
        }
        return createRealMatrix;
    }

    public long getN() {
        return this.n;
    }

    public void clear() {
        this.n = 0;
        Arrays.fill(this.sums, 0.0d);
        Arrays.fill(this.productsSums, 0.0d);
    }

    public int hashCode() {
        return (31 * ((((((this.isBiasCorrected ? 1231 : 1237) + 31) * 31) + ((int) (this.n ^ (this.n >>> 32)))) * 31) + Arrays.hashCode(this.productsSums))) + Arrays.hashCode(this.sums);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VectorialCovariance)) {
            return false;
        }
        VectorialCovariance vectorialCovariance = (VectorialCovariance) obj;
        return this.isBiasCorrected == vectorialCovariance.isBiasCorrected && this.n == vectorialCovariance.n && Arrays.equals(this.productsSums, vectorialCovariance.productsSums) && Arrays.equals(this.sums, vectorialCovariance.sums);
    }
}
