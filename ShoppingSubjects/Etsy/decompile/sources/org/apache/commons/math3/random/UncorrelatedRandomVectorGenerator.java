package org.apache.commons.math3.random;

import java.util.Arrays;
import org.apache.commons.math3.exception.DimensionMismatchException;

public class UncorrelatedRandomVectorGenerator implements RandomVectorGenerator {
    private final NormalizedRandomGenerator generator;
    private final double[] mean;
    private final double[] standardDeviation;

    public UncorrelatedRandomVectorGenerator(double[] dArr, double[] dArr2, NormalizedRandomGenerator normalizedRandomGenerator) {
        if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        }
        this.mean = (double[]) dArr.clone();
        this.standardDeviation = (double[]) dArr2.clone();
        this.generator = normalizedRandomGenerator;
    }

    public UncorrelatedRandomVectorGenerator(int i, NormalizedRandomGenerator normalizedRandomGenerator) {
        this.mean = new double[i];
        this.standardDeviation = new double[i];
        Arrays.fill(this.standardDeviation, 1.0d);
        this.generator = normalizedRandomGenerator;
    }

    public double[] nextVector() {
        double[] dArr = new double[this.mean.length];
        for (int i = 0; i < dArr.length; i++) {
            dArr[i] = this.mean[i] + (this.standardDeviation[i] * this.generator.nextNormalizedDouble());
        }
        return dArr;
    }
}
