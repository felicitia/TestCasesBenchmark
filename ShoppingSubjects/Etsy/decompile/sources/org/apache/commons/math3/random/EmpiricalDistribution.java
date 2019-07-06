package org.apache.commons.math3.random;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.StatisticalSummary;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class EmpiricalDistribution extends AbstractRealDistribution {
    public static final int DEFAULT_BIN_COUNT = 1000;
    private static final String FILE_CHARSET = "US-ASCII";
    private static final long serialVersionUID = 5729073523949762654L;
    private final int binCount;
    /* access modifiers changed from: private */
    public final List<SummaryStatistics> binStats;
    private double delta;
    private boolean loaded;
    private double max;
    private double min;
    private final RandomDataGenerator randomData;
    /* access modifiers changed from: private */
    public SummaryStatistics sampleStats;
    private double[] upperBounds;

    private class ArrayDataAdapter extends DataAdapter {
        private double[] inputArray;

        public ArrayDataAdapter(double[] dArr) throws NullArgumentException {
            super();
            MathUtils.checkNotNull(dArr);
            this.inputArray = dArr;
        }

        public void computeStats() throws IOException {
            EmpiricalDistribution.this.sampleStats = new SummaryStatistics();
            for (double addValue : this.inputArray) {
                EmpiricalDistribution.this.sampleStats.addValue(addValue);
            }
        }

        public void computeBinStats() throws IOException {
            for (int i = 0; i < this.inputArray.length; i++) {
                ((SummaryStatistics) EmpiricalDistribution.this.binStats.get(EmpiricalDistribution.this.findBin(this.inputArray[i]))).addValue(this.inputArray[i]);
            }
        }
    }

    private abstract class DataAdapter {
        public abstract void computeBinStats() throws IOException;

        public abstract void computeStats() throws IOException;

        private DataAdapter() {
        }
    }

    private class StreamDataAdapter extends DataAdapter {
        private BufferedReader inputStream;

        public StreamDataAdapter(BufferedReader bufferedReader) {
            super();
            this.inputStream = bufferedReader;
        }

        public void computeBinStats() throws IOException {
            while (true) {
                String readLine = this.inputStream.readLine();
                if (readLine != null) {
                    double parseDouble = Double.parseDouble(readLine);
                    ((SummaryStatistics) EmpiricalDistribution.this.binStats.get(EmpiricalDistribution.this.findBin(parseDouble))).addValue(parseDouble);
                } else {
                    this.inputStream.close();
                    this.inputStream = null;
                    return;
                }
            }
        }

        public void computeStats() throws IOException {
            EmpiricalDistribution.this.sampleStats = new SummaryStatistics();
            while (true) {
                String readLine = this.inputStream.readLine();
                if (readLine != null) {
                    EmpiricalDistribution.this.sampleStats.addValue(Double.valueOf(readLine).doubleValue());
                } else {
                    this.inputStream.close();
                    this.inputStream = null;
                    return;
                }
            }
        }
    }

    public boolean isSupportConnected() {
        return true;
    }

    public boolean isSupportLowerBoundInclusive() {
        return true;
    }

    public boolean isSupportUpperBoundInclusive() {
        return true;
    }

    public double probability(double d) {
        return 0.0d;
    }

    public EmpiricalDistribution() {
        this(1000);
    }

    public EmpiricalDistribution(int i) {
        this(i, new RandomDataGenerator());
    }

    public EmpiricalDistribution(int i, RandomGenerator randomGenerator) {
        this(i, new RandomDataGenerator(randomGenerator));
    }

    public EmpiricalDistribution(RandomGenerator randomGenerator) {
        this(1000, randomGenerator);
    }

    @Deprecated
    public EmpiricalDistribution(int i, RandomDataImpl randomDataImpl) {
        this(i, randomDataImpl.getDelegate());
    }

    @Deprecated
    public EmpiricalDistribution(RandomDataImpl randomDataImpl) {
        this(1000, randomDataImpl);
    }

    private EmpiricalDistribution(int i, RandomDataGenerator randomDataGenerator) {
        super(null);
        this.sampleStats = null;
        this.max = Double.NEGATIVE_INFINITY;
        this.min = Double.POSITIVE_INFINITY;
        this.delta = 0.0d;
        this.loaded = false;
        this.upperBounds = null;
        this.binCount = i;
        this.randomData = randomDataGenerator;
        this.binStats = new ArrayList();
    }

    public void load(double[] dArr) throws NullArgumentException {
        try {
            new ArrayDataAdapter(dArr).computeStats();
            fillBinStats(new ArrayDataAdapter(dArr));
            this.loaded = true;
        } catch (IOException unused) {
            throw new MathInternalError();
        }
    }

    public void load(URL url) throws IOException, NullArgumentException, ZeroException {
        MathUtils.checkNotNull(url);
        Charset forName = Charset.forName("US-ASCII");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), forName));
        try {
            new StreamDataAdapter(bufferedReader).computeStats();
            if (this.sampleStats.getN() == 0) {
                throw new ZeroException(LocalizedFormats.URL_CONTAINS_NO_DATA, url);
            }
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(url.openStream(), forName));
            try {
                fillBinStats(new StreamDataAdapter(bufferedReader2));
                this.loaded = true;
                try {
                    bufferedReader2.close();
                } catch (IOException unused) {
                }
            } catch (Throwable th) {
                th = th;
                bufferedReader = bufferedReader2;
                try {
                    bufferedReader.close();
                } catch (IOException unused2) {
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader.close();
            throw th;
        }
    }

    public void load(File file) throws IOException, NullArgumentException {
        MathUtils.checkNotNull(file);
        Charset forName = Charset.forName("US-ASCII");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), forName));
        try {
            new StreamDataAdapter(bufferedReader).computeStats();
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file), forName));
            try {
                fillBinStats(new StreamDataAdapter(bufferedReader2));
                this.loaded = true;
                try {
                    bufferedReader2.close();
                } catch (IOException unused) {
                }
            } catch (Throwable th) {
                th = th;
                bufferedReader = bufferedReader2;
                try {
                    bufferedReader.close();
                } catch (IOException unused2) {
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader.close();
            throw th;
        }
    }

    private void fillBinStats(DataAdapter dataAdapter) throws IOException {
        this.min = this.sampleStats.getMin();
        this.max = this.sampleStats.getMax();
        this.delta = (this.max - this.min) / Double.valueOf((double) this.binCount).doubleValue();
        if (!this.binStats.isEmpty()) {
            this.binStats.clear();
        }
        for (int i = 0; i < this.binCount; i++) {
            this.binStats.add(i, new SummaryStatistics());
        }
        dataAdapter.computeBinStats();
        this.upperBounds = new double[this.binCount];
        this.upperBounds[0] = ((double) ((SummaryStatistics) this.binStats.get(0)).getN()) / ((double) this.sampleStats.getN());
        for (int i2 = 1; i2 < this.binCount - 1; i2++) {
            this.upperBounds[i2] = this.upperBounds[i2 - 1] + (((double) ((SummaryStatistics) this.binStats.get(i2)).getN()) / ((double) this.sampleStats.getN()));
        }
        this.upperBounds[this.binCount - 1] = 1.0d;
    }

    /* access modifiers changed from: private */
    public int findBin(double d) {
        return FastMath.min(FastMath.max(((int) FastMath.ceil((d - this.min) / this.delta)) - 1, 0), this.binCount - 1);
    }

    public double getNextValue() throws MathIllegalStateException {
        if (!this.loaded) {
            throw new MathIllegalStateException(LocalizedFormats.DISTRIBUTION_NOT_LOADED, new Object[0]);
        }
        double nextUniform = this.randomData.nextUniform(0.0d, 1.0d);
        for (int i = 0; i < this.binCount; i++) {
            if (nextUniform <= this.upperBounds[i]) {
                SummaryStatistics summaryStatistics = (SummaryStatistics) this.binStats.get(i);
                if (summaryStatistics.getN() > 0) {
                    if (summaryStatistics.getStandardDeviation() > 0.0d) {
                        return this.randomData.nextGaussian(summaryStatistics.getMean(), summaryStatistics.getStandardDeviation());
                    }
                    return summaryStatistics.getMean();
                }
            }
        }
        throw new MathIllegalStateException(LocalizedFormats.NO_BIN_SELECTED, new Object[0]);
    }

    public StatisticalSummary getSampleStats() {
        return this.sampleStats;
    }

    public int getBinCount() {
        return this.binCount;
    }

    public List<SummaryStatistics> getBinStats() {
        return this.binStats;
    }

    public double[] getUpperBounds() {
        double[] dArr = new double[this.binCount];
        int i = 0;
        while (i < this.binCount - 1) {
            int i2 = i + 1;
            dArr[i] = this.min + (this.delta * ((double) i2));
            i = i2;
        }
        dArr[this.binCount - 1] = this.max;
        return dArr;
    }

    public double[] getGeneratorUpperBounds() {
        int length = this.upperBounds.length;
        double[] dArr = new double[length];
        System.arraycopy(this.upperBounds, 0, dArr, 0, length);
        return dArr;
    }

    public boolean isLoaded() {
        return this.loaded;
    }

    public void reSeed(long j) {
        this.randomData.reSeed(j);
    }

    public double density(double d) {
        if (d < this.min || d > this.max) {
            return 0.0d;
        }
        int findBin = findBin(d);
        return (getKernel((SummaryStatistics) this.binStats.get(findBin)).density(d) * pB(findBin)) / kB(findBin);
    }

    public double cumulativeProbability(double d) {
        if (d < this.min) {
            return 0.0d;
        }
        if (d >= this.max) {
            return 1.0d;
        }
        int findBin = findBin(d);
        double pBminus = pBminus(findBin);
        double pB = pB(findBin);
        double[] upperBounds2 = getUpperBounds();
        double kB = kB(findBin);
        double d2 = findBin == 0 ? this.min : upperBounds2[findBin - 1];
        RealDistribution k = k(d);
        return pBminus + (pB * ((k.cumulativeProbability(d) - k.cumulativeProbability(d2)) / kB));
    }

    public double inverseCumulativeProbability(double d) throws OutOfRangeException {
        int i = 0;
        if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d), Integer.valueOf(0), Integer.valueOf(1));
        } else if (d == 0.0d) {
            return getSupportLowerBound();
        } else {
            if (d == 1.0d) {
                return getSupportUpperBound();
            }
            while (cumBinP(i) < d) {
                i++;
            }
            RealDistribution kernel = getKernel((SummaryStatistics) this.binStats.get(i));
            double kB = kB(i);
            double d2 = i == 0 ? this.min : getUpperBounds()[i - 1];
            double cumulativeProbability = kernel.cumulativeProbability(d2);
            double pB = pB(i);
            double pBminus = d - pBminus(i);
            if (pBminus <= 0.0d) {
                return d2;
            }
            return kernel.inverseCumulativeProbability(cumulativeProbability + ((pBminus * kB) / pB));
        }
    }

    public double getNumericalMean() {
        return this.sampleStats.getMean();
    }

    public double getNumericalVariance() {
        return this.sampleStats.getVariance();
    }

    public double getSupportLowerBound() {
        return this.min;
    }

    public double getSupportUpperBound() {
        return this.max;
    }

    public double sample() {
        return getNextValue();
    }

    public void reseedRandomGenerator(long j) {
        this.randomData.reSeed(j);
    }

    private double pB(int i) {
        return i == 0 ? this.upperBounds[0] : this.upperBounds[i] - this.upperBounds[i - 1];
    }

    private double pBminus(int i) {
        if (i == 0) {
            return 0.0d;
        }
        return this.upperBounds[i - 1];
    }

    private double kB(int i) {
        double d;
        double d2;
        double[] upperBounds2 = getUpperBounds();
        RealDistribution kernel = getKernel((SummaryStatistics) this.binStats.get(i));
        if (i == 0) {
            d = this.min;
            d2 = upperBounds2[0];
        } else {
            d = upperBounds2[i - 1];
            d2 = upperBounds2[i];
        }
        return kernel.cumulativeProbability(d, d2);
    }

    private RealDistribution k(double d) {
        return getKernel((SummaryStatistics) this.binStats.get(findBin(d)));
    }

    private double cumBinP(int i) {
        return this.upperBounds[i];
    }

    private RealDistribution getKernel(SummaryStatistics summaryStatistics) {
        return new NormalDistribution(summaryStatistics.getMean(), summaryStatistics.getStandardDeviation());
    }
}
