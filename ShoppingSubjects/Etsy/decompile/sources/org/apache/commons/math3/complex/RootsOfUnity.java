package org.apache.commons.math3.complex;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

public class RootsOfUnity implements Serializable {
    private static final long serialVersionUID = 20120201;
    private boolean isCounterClockWise = true;
    private int omegaCount = 0;
    private double[] omegaImaginaryClockwise = null;
    private double[] omegaImaginaryCounterClockwise = null;
    private double[] omegaReal = null;

    public synchronized boolean isCounterClockWise() throws MathIllegalStateException {
        if (this.omegaCount == 0) {
            throw new MathIllegalStateException(LocalizedFormats.ROOTS_OF_UNITY_NOT_COMPUTED_YET, new Object[0]);
        }
        return this.isCounterClockWise;
    }

    public synchronized void computeRoots(int i) throws ZeroException {
        if (i == 0) {
            try {
                throw new ZeroException(LocalizedFormats.CANNOT_COMPUTE_0TH_ROOT_OF_UNITY, new Object[0]);
            } catch (Throwable th) {
                throw th;
            }
        } else {
            this.isCounterClockWise = i > 0;
            int abs = FastMath.abs(i);
            if (abs != this.omegaCount) {
                double d = 6.283185307179586d / ((double) abs);
                double cos = FastMath.cos(d);
                double sin = FastMath.sin(d);
                this.omegaReal = new double[abs];
                this.omegaImaginaryCounterClockwise = new double[abs];
                this.omegaImaginaryClockwise = new double[abs];
                this.omegaReal[0] = 1.0d;
                this.omegaImaginaryCounterClockwise[0] = 0.0d;
                this.omegaImaginaryClockwise[0] = 0.0d;
                for (int i2 = 1; i2 < abs; i2++) {
                    int i3 = i2 - 1;
                    this.omegaReal[i2] = (this.omegaReal[i3] * cos) - (this.omegaImaginaryCounterClockwise[i3] * sin);
                    this.omegaImaginaryCounterClockwise[i2] = (this.omegaReal[i3] * sin) + (this.omegaImaginaryCounterClockwise[i3] * cos);
                    this.omegaImaginaryClockwise[i2] = -this.omegaImaginaryCounterClockwise[i2];
                }
                this.omegaCount = abs;
            }
        }
    }

    public synchronized double getReal(int i) throws MathIllegalStateException, MathIllegalArgumentException {
        if (this.omegaCount == 0) {
            throw new MathIllegalStateException(LocalizedFormats.ROOTS_OF_UNITY_NOT_COMPUTED_YET, new Object[0]);
        }
        if (i >= 0) {
            if (i < this.omegaCount) {
            }
        }
        throw new OutOfRangeException(LocalizedFormats.OUT_OF_RANGE_ROOT_OF_UNITY_INDEX, Integer.valueOf(i), Integer.valueOf(0), Integer.valueOf(this.omegaCount - 1));
        return this.omegaReal[i];
    }

    public synchronized double getImaginary(int i) throws MathIllegalStateException, OutOfRangeException {
        if (this.omegaCount == 0) {
            throw new MathIllegalStateException(LocalizedFormats.ROOTS_OF_UNITY_NOT_COMPUTED_YET, new Object[0]);
        }
        if (i >= 0) {
            if (i >= this.omegaCount) {
            }
        }
        throw new OutOfRangeException(LocalizedFormats.OUT_OF_RANGE_ROOT_OF_UNITY_INDEX, Integer.valueOf(i), Integer.valueOf(0), Integer.valueOf(this.omegaCount - 1));
        return this.isCounterClockWise ? this.omegaImaginaryCounterClockwise[i] : this.omegaImaginaryClockwise[i];
    }

    public synchronized int getNumberOfRoots() {
        return this.omegaCount;
    }
}
