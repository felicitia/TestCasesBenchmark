package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.exception.DimensionMismatchException;

public class VectorialMean implements Serializable {
    private static final long serialVersionUID = 8223009086481006892L;
    private final Mean[] means;

    public VectorialMean(int i) {
        this.means = new Mean[i];
        for (int i2 = 0; i2 < i; i2++) {
            this.means[i2] = new Mean();
        }
    }

    public void increment(double[] dArr) throws DimensionMismatchException {
        if (dArr.length != this.means.length) {
            throw new DimensionMismatchException(dArr.length, this.means.length);
        }
        for (int i = 0; i < dArr.length; i++) {
            this.means[i].increment(dArr[i]);
        }
    }

    public double[] getResult() {
        double[] dArr = new double[this.means.length];
        for (int i = 0; i < dArr.length; i++) {
            dArr[i] = this.means[i].getResult();
        }
        return dArr;
    }

    public long getN() {
        if (this.means.length == 0) {
            return 0;
        }
        return this.means[0].getN();
    }

    public int hashCode() {
        return 31 + Arrays.hashCode(this.means);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VectorialMean)) {
            return false;
        }
        return Arrays.equals(this.means, ((VectorialMean) obj).means);
    }
}
