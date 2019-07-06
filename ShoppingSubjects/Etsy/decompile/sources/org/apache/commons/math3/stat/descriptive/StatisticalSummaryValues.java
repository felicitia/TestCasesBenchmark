package org.apache.commons.math3.stat.descriptive;

import java.io.Serializable;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

public class StatisticalSummaryValues implements Serializable, StatisticalSummary {
    private static final long serialVersionUID = -5108854841843722536L;
    private final double max;
    private final double mean;
    private final double min;
    private final long n;
    private final double sum;
    private final double variance;

    public StatisticalSummaryValues(double d, double d2, long j, double d3, double d4, double d5) {
        this.mean = d;
        this.variance = d2;
        this.n = j;
        this.max = d3;
        this.min = d4;
        this.sum = d5;
    }

    public double getMax() {
        return this.max;
    }

    public double getMean() {
        return this.mean;
    }

    public double getMin() {
        return this.min;
    }

    public long getN() {
        return this.n;
    }

    public double getSum() {
        return this.sum;
    }

    public double getStandardDeviation() {
        return FastMath.sqrt(this.variance);
    }

    public double getVariance() {
        return this.variance;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StatisticalSummaryValues)) {
            return false;
        }
        StatisticalSummaryValues statisticalSummaryValues = (StatisticalSummaryValues) obj;
        if (!Precision.equalsIncludingNaN(statisticalSummaryValues.getMax(), getMax()) || !Precision.equalsIncludingNaN(statisticalSummaryValues.getMean(), getMean()) || !Precision.equalsIncludingNaN(statisticalSummaryValues.getMin(), getMin()) || !Precision.equalsIncludingNaN((float) statisticalSummaryValues.getN(), (float) getN()) || !Precision.equalsIncludingNaN(statisticalSummaryValues.getSum(), getSum()) || !Precision.equalsIncludingNaN(statisticalSummaryValues.getVariance(), getVariance())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((((((((((MathUtils.hash(getMax()) + 31) * 31) + MathUtils.hash(getMean())) * 31) + MathUtils.hash(getMin())) * 31) + MathUtils.hash((double) getN())) * 31) + MathUtils.hash(getSum())) * 31) + MathUtils.hash(getVariance());
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String str = "\n";
        stringBuffer.append("StatisticalSummaryValues:");
        stringBuffer.append(str);
        stringBuffer.append("n: ");
        stringBuffer.append(getN());
        stringBuffer.append(str);
        stringBuffer.append("min: ");
        stringBuffer.append(getMin());
        stringBuffer.append(str);
        stringBuffer.append("max: ");
        stringBuffer.append(getMax());
        stringBuffer.append(str);
        stringBuffer.append("mean: ");
        stringBuffer.append(getMean());
        stringBuffer.append(str);
        stringBuffer.append("std dev: ");
        stringBuffer.append(getStandardDeviation());
        stringBuffer.append(str);
        stringBuffer.append("variance: ");
        stringBuffer.append(getVariance());
        stringBuffer.append(str);
        stringBuffer.append("sum: ");
        stringBuffer.append(getSum());
        stringBuffer.append(str);
        return stringBuffer.toString();
    }
}
