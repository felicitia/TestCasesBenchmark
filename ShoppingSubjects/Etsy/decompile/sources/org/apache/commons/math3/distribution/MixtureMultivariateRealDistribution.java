package org.apache.commons.math3.distribution;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.distribution.MultivariateRealDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.Pair;

public class MixtureMultivariateRealDistribution<T extends MultivariateRealDistribution> extends AbstractMultivariateRealDistribution {
    private final List<T> distribution;
    private final double[] weight;

    public MixtureMultivariateRealDistribution(List<Pair<Double, T>> list) {
        this(new Well19937c(), list);
    }

    public MixtureMultivariateRealDistribution(RandomGenerator randomGenerator, List<Pair<Double, T>> list) {
        super(randomGenerator, ((MultivariateRealDistribution) ((Pair) list.get(0)).getSecond()).getDimension());
        int size = list.size();
        int dimension = getDimension();
        int i = 0;
        double d = 0.0d;
        while (i < size) {
            Pair pair = (Pair) list.get(i);
            if (((MultivariateRealDistribution) pair.getSecond()).getDimension() != dimension) {
                throw new DimensionMismatchException(((MultivariateRealDistribution) pair.getSecond()).getDimension(), dimension);
            } else if (((Double) pair.getFirst()).doubleValue() < 0.0d) {
                throw new NotPositiveException((Number) pair.getFirst());
            } else {
                d += ((Double) pair.getFirst()).doubleValue();
                i++;
            }
        }
        if (Double.isInfinite(d)) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW, new Object[0]);
        }
        this.distribution = new ArrayList();
        this.weight = new double[size];
        for (int i2 = 0; i2 < size; i2++) {
            Pair pair2 = (Pair) list.get(i2);
            this.weight[i2] = ((Double) pair2.getFirst()).doubleValue() / d;
            this.distribution.add(pair2.getSecond());
        }
    }

    public double density(double[] dArr) {
        double d = 0.0d;
        for (int i = 0; i < this.weight.length; i++) {
            d += this.weight[i] * ((MultivariateRealDistribution) this.distribution.get(i)).density(dArr);
        }
        return d;
    }

    public double[] sample() {
        double[] dArr;
        double nextDouble = this.random.nextDouble();
        double d = 0.0d;
        int i = 0;
        while (true) {
            if (i >= this.weight.length) {
                dArr = null;
                break;
            }
            d += this.weight[i];
            if (nextDouble <= d) {
                dArr = ((MultivariateRealDistribution) this.distribution.get(i)).sample();
                break;
            }
            i++;
        }
        return dArr == null ? ((MultivariateRealDistribution) this.distribution.get(this.weight.length - 1)).sample() : dArr;
    }

    public void reseedRandomGenerator(long j) {
        super.reseedRandomGenerator(j);
        int i = 0;
        while (i < this.distribution.size()) {
            i++;
            ((MultivariateRealDistribution) this.distribution.get(i)).reseedRandomGenerator(((long) i) + j);
        }
    }

    public List<Pair<Double, T>> getComponents() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.weight.length; i++) {
            arrayList.add(new Pair(Double.valueOf(this.weight[i]), this.distribution.get(i)));
        }
        return arrayList;
    }
}
