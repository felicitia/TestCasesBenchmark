package org.apache.commons.math3.random;

import java.util.Random;

public class RandomAdaptor extends Random implements RandomGenerator {
    private static final long serialVersionUID = 2306581345647615033L;
    private final RandomGenerator randomGenerator;

    private RandomAdaptor() {
        this.randomGenerator = null;
    }

    public RandomAdaptor(RandomGenerator randomGenerator2) {
        this.randomGenerator = randomGenerator2;
    }

    public static Random createAdaptor(RandomGenerator randomGenerator2) {
        return new RandomAdaptor(randomGenerator2);
    }

    public boolean nextBoolean() {
        return this.randomGenerator.nextBoolean();
    }

    public void nextBytes(byte[] bArr) {
        this.randomGenerator.nextBytes(bArr);
    }

    public double nextDouble() {
        return this.randomGenerator.nextDouble();
    }

    public float nextFloat() {
        return this.randomGenerator.nextFloat();
    }

    public double nextGaussian() {
        return this.randomGenerator.nextGaussian();
    }

    public int nextInt() {
        return this.randomGenerator.nextInt();
    }

    public int nextInt(int i) {
        return this.randomGenerator.nextInt(i);
    }

    public long nextLong() {
        return this.randomGenerator.nextLong();
    }

    public void setSeed(int i) {
        if (this.randomGenerator != null) {
            this.randomGenerator.setSeed(i);
        }
    }

    public void setSeed(int[] iArr) {
        if (this.randomGenerator != null) {
            this.randomGenerator.setSeed(iArr);
        }
    }

    public void setSeed(long j) {
        if (this.randomGenerator != null) {
            this.randomGenerator.setSeed(j);
        }
    }
}
