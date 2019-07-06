package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.summary.SumOfLogs;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class GeometricMean extends AbstractStorelessUnivariateStatistic implements Serializable {
    private static final long serialVersionUID = -8178734905303459453L;
    private StorelessUnivariateStatistic sumOfLogs;

    public GeometricMean() {
        this.sumOfLogs = new SumOfLogs();
    }

    public GeometricMean(GeometricMean geometricMean) throws NullArgumentException {
        copy(geometricMean, this);
    }

    public GeometricMean(SumOfLogs sumOfLogs2) {
        this.sumOfLogs = sumOfLogs2;
    }

    public GeometricMean copy() {
        GeometricMean geometricMean = new GeometricMean();
        copy(this, geometricMean);
        return geometricMean;
    }

    public void increment(double d) {
        this.sumOfLogs.increment(d);
    }

    public double getResult() {
        if (this.sumOfLogs.getN() > 0) {
            return FastMath.exp(this.sumOfLogs.getResult() / ((double) this.sumOfLogs.getN()));
        }
        return Double.NaN;
    }

    public void clear() {
        this.sumOfLogs.clear();
    }

    public double evaluate(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        return FastMath.exp(this.sumOfLogs.evaluate(dArr, i, i2) / ((double) i2));
    }

    public long getN() {
        return this.sumOfLogs.getN();
    }

    public void setSumLogImpl(StorelessUnivariateStatistic storelessUnivariateStatistic) throws MathIllegalStateException {
        checkEmpty();
        this.sumOfLogs = storelessUnivariateStatistic;
    }

    public StorelessUnivariateStatistic getSumLogImpl() {
        return this.sumOfLogs;
    }

    public static void copy(GeometricMean geometricMean, GeometricMean geometricMean2) throws NullArgumentException {
        MathUtils.checkNotNull(geometricMean);
        MathUtils.checkNotNull(geometricMean2);
        geometricMean2.setData(geometricMean.getDataRef());
        geometricMean2.sumOfLogs = geometricMean.sumOfLogs.copy();
    }

    private void checkEmpty() throws MathIllegalStateException {
        if (getN() > 0) {
            throw new MathIllegalStateException(LocalizedFormats.VALUES_ADDED_BEFORE_CONFIGURING_STATISTIC, Long.valueOf(getN()));
        }
    }
}
