package org.apache.commons.math3.stat.descriptive;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.MathUtils;

public class SynchronizedDescriptiveStatistics extends DescriptiveStatistics {
    private static final long serialVersionUID = 1;

    public SynchronizedDescriptiveStatistics() {
        this(-1);
    }

    public SynchronizedDescriptiveStatistics(int i) throws MathIllegalArgumentException {
        super(i);
    }

    public SynchronizedDescriptiveStatistics(SynchronizedDescriptiveStatistics synchronizedDescriptiveStatistics) throws NullArgumentException {
        copy(synchronizedDescriptiveStatistics, this);
    }

    public synchronized void addValue(double d) {
        super.addValue(d);
    }

    public synchronized double apply(UnivariateStatistic univariateStatistic) {
        return super.apply(univariateStatistic);
    }

    public synchronized void clear() {
        super.clear();
    }

    public synchronized double getElement(int i) {
        return super.getElement(i);
    }

    public synchronized long getN() {
        return super.getN();
    }

    public synchronized double getStandardDeviation() {
        return super.getStandardDeviation();
    }

    public synchronized double[] getValues() {
        return super.getValues();
    }

    public synchronized int getWindowSize() {
        return super.getWindowSize();
    }

    public synchronized void setWindowSize(int i) throws MathIllegalArgumentException {
        super.setWindowSize(i);
    }

    public synchronized String toString() {
        return super.toString();
    }

    public synchronized SynchronizedDescriptiveStatistics copy() {
        SynchronizedDescriptiveStatistics synchronizedDescriptiveStatistics;
        synchronizedDescriptiveStatistics = new SynchronizedDescriptiveStatistics();
        copy(this, synchronizedDescriptiveStatistics);
        return synchronizedDescriptiveStatistics;
    }

    public static void copy(SynchronizedDescriptiveStatistics synchronizedDescriptiveStatistics, SynchronizedDescriptiveStatistics synchronizedDescriptiveStatistics2) throws NullArgumentException {
        MathUtils.checkNotNull(synchronizedDescriptiveStatistics);
        MathUtils.checkNotNull(synchronizedDescriptiveStatistics2);
        synchronized (synchronizedDescriptiveStatistics) {
            synchronized (synchronizedDescriptiveStatistics2) {
                DescriptiveStatistics.copy(synchronizedDescriptiveStatistics, synchronizedDescriptiveStatistics2);
            }
        }
    }
}
