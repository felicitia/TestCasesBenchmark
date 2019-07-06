package org.apache.commons.math3.random;

public class SynchronizedRandomGenerator implements RandomGenerator {
    private final RandomGenerator wrapped;

    public SynchronizedRandomGenerator(RandomGenerator randomGenerator) {
        this.wrapped = randomGenerator;
    }

    public synchronized void setSeed(int i) {
        this.wrapped.setSeed(i);
    }

    public synchronized void setSeed(int[] iArr) {
        this.wrapped.setSeed(iArr);
    }

    public synchronized void setSeed(long j) {
        this.wrapped.setSeed(j);
    }

    public synchronized void nextBytes(byte[] bArr) {
        this.wrapped.nextBytes(bArr);
    }

    public synchronized int nextInt() {
        return this.wrapped.nextInt();
    }

    public synchronized int nextInt(int i) {
        return this.wrapped.nextInt(i);
    }

    public synchronized long nextLong() {
        return this.wrapped.nextLong();
    }

    public synchronized boolean nextBoolean() {
        return this.wrapped.nextBoolean();
    }

    public synchronized float nextFloat() {
        return this.wrapped.nextFloat();
    }

    public synchronized double nextDouble() {
        return this.wrapped.nextDouble();
    }

    public synchronized double nextGaussian() {
        return this.wrapped.nextGaussian();
    }
}
