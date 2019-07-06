package org.apache.commons.math3.random;

import org.apache.commons.math3.util.FastMath;

public class UnitSphereRandomVectorGenerator implements RandomVectorGenerator {
    private final int dimension;
    private final RandomGenerator rand;

    public UnitSphereRandomVectorGenerator(int i, RandomGenerator randomGenerator) {
        this.dimension = i;
        this.rand = randomGenerator;
    }

    public UnitSphereRandomVectorGenerator(int i) {
        this(i, new MersenneTwister());
    }

    public double[] nextVector() {
        int i;
        double d;
        double[] dArr = new double[this.dimension];
        do {
            d = 0.0d;
            for (int i2 = 0; i2 < this.dimension; i2++) {
                double nextDouble = (2.0d * this.rand.nextDouble()) - 1.0d;
                dArr[i2] = nextDouble;
                d += nextDouble * nextDouble;
            }
        } while (d > 1.0d);
        double sqrt = 1.0d / FastMath.sqrt(d);
        for (i = 0; i < this.dimension; i++) {
            dArr[i] = dArr[i] * sqrt;
        }
        return dArr;
    }
}
