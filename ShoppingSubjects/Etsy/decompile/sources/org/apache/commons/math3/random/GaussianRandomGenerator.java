package org.apache.commons.math3.random;

public class GaussianRandomGenerator implements NormalizedRandomGenerator {
    private final RandomGenerator generator;

    public GaussianRandomGenerator(RandomGenerator randomGenerator) {
        this.generator = randomGenerator;
    }

    public double nextNormalizedDouble() {
        return this.generator.nextGaussian();
    }
}
