package org.apache.commons.math3.random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class ValueServer {
    public static final int CONSTANT_MODE = 5;
    public static final int DIGEST_MODE = 0;
    public static final int EXPONENTIAL_MODE = 3;
    public static final int GAUSSIAN_MODE = 4;
    public static final int REPLAY_MODE = 1;
    public static final int UNIFORM_MODE = 2;
    private EmpiricalDistribution empiricalDistribution;
    private BufferedReader filePointer;
    private int mode;
    private double mu;
    private final RandomDataImpl randomData;
    private double sigma;
    private URL valuesFileURL;

    public ValueServer() {
        this.mode = 5;
        this.valuesFileURL = null;
        this.mu = 0.0d;
        this.sigma = 0.0d;
        this.empiricalDistribution = null;
        this.filePointer = null;
        this.randomData = new RandomDataImpl();
    }

    public ValueServer(RandomDataImpl randomDataImpl) {
        this.mode = 5;
        this.valuesFileURL = null;
        this.mu = 0.0d;
        this.sigma = 0.0d;
        this.empiricalDistribution = null;
        this.filePointer = null;
        this.randomData = randomDataImpl;
    }

    public ValueServer(RandomGenerator randomGenerator) {
        this.mode = 5;
        this.valuesFileURL = null;
        this.mu = 0.0d;
        this.sigma = 0.0d;
        this.empiricalDistribution = null;
        this.filePointer = null;
        this.randomData = new RandomDataImpl(randomGenerator);
    }

    public double getNext() throws IOException, MathIllegalStateException, MathIllegalArgumentException {
        switch (this.mode) {
            case 0:
                return getNextDigest();
            case 1:
                return getNextReplay();
            case 2:
                return getNextUniform();
            case 3:
                return getNextExponential();
            case 4:
                return getNextGaussian();
            case 5:
                return this.mu;
            default:
                throw new MathIllegalStateException(LocalizedFormats.UNKNOWN_MODE, Integer.valueOf(this.mode), "DIGEST_MODE", Integer.valueOf(0), "REPLAY_MODE", Integer.valueOf(1), "UNIFORM_MODE", Integer.valueOf(2), "EXPONENTIAL_MODE", Integer.valueOf(3), "GAUSSIAN_MODE", Integer.valueOf(4), "CONSTANT_MODE", Integer.valueOf(5));
        }
    }

    public void fill(double[] dArr) throws IOException, MathIllegalStateException, MathIllegalArgumentException {
        for (int i = 0; i < dArr.length; i++) {
            dArr[i] = getNext();
        }
    }

    public double[] fill(int i) throws IOException, MathIllegalStateException, MathIllegalArgumentException {
        double[] dArr = new double[i];
        for (int i2 = 0; i2 < i; i2++) {
            dArr[i2] = getNext();
        }
        return dArr;
    }

    public void computeDistribution() throws IOException, ZeroException, NullArgumentException {
        computeDistribution(1000);
    }

    public void computeDistribution(int i) throws NullArgumentException, IOException, ZeroException {
        this.empiricalDistribution = new EmpiricalDistribution(i, this.randomData);
        this.empiricalDistribution.load(this.valuesFileURL);
        this.mu = this.empiricalDistribution.getSampleStats().getMean();
        this.sigma = this.empiricalDistribution.getSampleStats().getStandardDeviation();
    }

    public int getMode() {
        return this.mode;
    }

    public void setMode(int i) {
        this.mode = i;
    }

    public URL getValuesFileURL() {
        return this.valuesFileURL;
    }

    public void setValuesFileURL(String str) throws MalformedURLException {
        this.valuesFileURL = new URL(str);
    }

    public void setValuesFileURL(URL url) {
        this.valuesFileURL = url;
    }

    public EmpiricalDistribution getEmpiricalDistribution() {
        return this.empiricalDistribution;
    }

    public void resetReplayFile() throws IOException {
        if (this.filePointer != null) {
            try {
                this.filePointer.close();
                this.filePointer = null;
            } catch (IOException unused) {
            }
        }
        this.filePointer = new BufferedReader(new InputStreamReader(this.valuesFileURL.openStream(), "UTF-8"));
    }

    public void closeReplayFile() throws IOException {
        if (this.filePointer != null) {
            this.filePointer.close();
            this.filePointer = null;
        }
    }

    public double getMu() {
        return this.mu;
    }

    public void setMu(double d) {
        this.mu = d;
    }

    public double getSigma() {
        return this.sigma;
    }

    public void setSigma(double d) {
        this.sigma = d;
    }

    public void reSeed(long j) {
        this.randomData.reSeed(j);
    }

    private double getNextDigest() throws MathIllegalStateException {
        if (this.empiricalDistribution != null && this.empiricalDistribution.getBinStats().size() != 0) {
            return this.empiricalDistribution.getNextValue();
        }
        throw new MathIllegalStateException(LocalizedFormats.DIGEST_NOT_INITIALIZED, new Object[0]);
    }

    private double getNextReplay() throws IOException, MathIllegalStateException {
        if (this.filePointer == null) {
            resetReplayFile();
        }
        String readLine = this.filePointer.readLine();
        if (readLine == null) {
            closeReplayFile();
            resetReplayFile();
            readLine = this.filePointer.readLine();
            if (readLine == null) {
                throw new MathIllegalStateException(LocalizedFormats.URL_CONTAINS_NO_DATA, this.valuesFileURL);
            }
        }
        return Double.valueOf(readLine).doubleValue();
    }

    private double getNextUniform() throws MathIllegalArgumentException {
        return this.randomData.nextUniform(0.0d, 2.0d * this.mu);
    }

    private double getNextExponential() throws MathIllegalArgumentException {
        return this.randomData.nextExponential(this.mu);
    }

    private double getNextGaussian() throws MathIllegalArgumentException {
        return this.randomData.nextGaussian(this.mu, this.sigma);
    }
}
