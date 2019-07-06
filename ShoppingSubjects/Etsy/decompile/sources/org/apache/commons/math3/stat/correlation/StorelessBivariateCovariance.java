package org.apache.commons.math3.stat.correlation;

import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

class StorelessBivariateCovariance {
    private boolean biasCorrected;
    private double covarianceNumerator;
    private double meanX;
    private double meanY;
    private double n;

    public StorelessBivariateCovariance() {
        this(true);
    }

    public StorelessBivariateCovariance(boolean z) {
        this.meanY = 0.0d;
        this.meanX = 0.0d;
        this.n = 0.0d;
        this.covarianceNumerator = 0.0d;
        this.biasCorrected = z;
    }

    public void increment(double d, double d2) {
        this.n += 1.0d;
        double d3 = d - this.meanX;
        double d4 = d2 - this.meanY;
        this.meanX += d3 / this.n;
        this.meanY += d4 / this.n;
        this.covarianceNumerator += ((this.n - 1.0d) / this.n) * d3 * d4;
    }

    public double getN() {
        return this.n;
    }

    public double getResult() throws NumberIsTooSmallException {
        if (this.n < 2.0d) {
            throw new NumberIsTooSmallException(LocalizedFormats.INSUFFICIENT_DIMENSION, Double.valueOf(this.n), Integer.valueOf(2), true);
        } else if (this.biasCorrected) {
            return this.covarianceNumerator / (this.n - 1.0d);
        } else {
            return this.covarianceNumerator / this.n;
        }
    }
}
