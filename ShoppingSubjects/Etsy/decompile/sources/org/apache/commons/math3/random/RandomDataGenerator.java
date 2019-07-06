package org.apache.commons.math3.random;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Collection;
import org.apache.commons.math3.distribution.BetaDistribution;
import org.apache.commons.math3.distribution.BinomialDistribution;
import org.apache.commons.math3.distribution.CauchyDistribution;
import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.GammaDistribution;
import org.apache.commons.math3.distribution.HypergeometricDistribution;
import org.apache.commons.math3.distribution.PascalDistribution;
import org.apache.commons.math3.distribution.PoissonDistribution;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.distribution.WeibullDistribution;
import org.apache.commons.math3.distribution.ZipfDistribution;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotANumberException;
import org.apache.commons.math3.exception.NotFiniteNumberException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

public class RandomDataGenerator implements Serializable, RandomData {
    private static final long serialVersionUID = -626730818244969716L;
    private RandomGenerator rand = null;
    private SecureRandom secRand = null;

    public RandomDataGenerator() {
    }

    public RandomDataGenerator(RandomGenerator randomGenerator) {
        this.rand = randomGenerator;
    }

    public String nextHexString(int i) throws NotStrictlyPositiveException {
        if (i <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.LENGTH, Integer.valueOf(i));
        }
        RandomGenerator ran = getRan();
        StringBuilder sb = new StringBuilder();
        byte[] bArr = new byte[((i / 2) + 1)];
        ran.nextBytes(bArr);
        for (byte valueOf : bArr) {
            String hexString = Integer.toHexString(Integer.valueOf(valueOf).intValue() + 128);
            if (hexString.length() == 1) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("0");
                sb2.append(hexString);
                hexString = sb2.toString();
            }
            sb.append(hexString);
        }
        return sb.toString().substring(0, i);
    }

    public int nextInt(int i, int i2) throws NumberIsTooLargeException {
        if (i >= i2) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Integer.valueOf(i), Integer.valueOf(i2), false);
        }
        double nextDouble = getRan().nextDouble();
        return (int) FastMath.floor((((double) i2) * nextDouble) + ((1.0d - nextDouble) * ((double) i)) + nextDouble);
    }

    public long nextLong(long j, long j2) throws NumberIsTooLargeException {
        if (j >= j2) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Long.valueOf(j), Long.valueOf(j2), false);
        }
        double nextDouble = getRan().nextDouble();
        return (long) FastMath.floor((((double) j2) * nextDouble) + ((1.0d - nextDouble) * ((double) j)) + nextDouble);
    }

    public String nextSecureHexString(int i) throws NotStrictlyPositiveException {
        if (i <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.LENGTH, Integer.valueOf(i));
        }
        SecureRandom secRan = getSecRan();
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.reset();
            int i2 = (i / 40) + 1;
            StringBuilder sb = new StringBuilder();
            int i3 = 1;
            while (true) {
                if (i3 >= i2 + 1) {
                    return sb.toString().substring(0, i);
                }
                byte[] bArr = new byte[40];
                secRan.nextBytes(bArr);
                instance.update(bArr);
                byte[] digest = instance.digest();
                for (byte valueOf : digest) {
                    String hexString = Integer.toHexString(Integer.valueOf(valueOf).intValue() + 128);
                    if (hexString.length() == 1) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("0");
                        sb2.append(hexString);
                        hexString = sb2.toString();
                    }
                    sb.append(hexString);
                }
                i3++;
            }
        } catch (NoSuchAlgorithmException e) {
            throw new MathInternalError(e);
        }
    }

    public int nextSecureInt(int i, int i2) throws NumberIsTooLargeException {
        if (i >= i2) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Integer.valueOf(i), Integer.valueOf(i2), false);
        }
        double nextDouble = getSecRan().nextDouble();
        return (int) FastMath.floor((((double) i2) * nextDouble) + ((1.0d - nextDouble) * ((double) i)) + nextDouble);
    }

    public long nextSecureLong(long j, long j2) throws NumberIsTooLargeException {
        if (j >= j2) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Long.valueOf(j), Long.valueOf(j2), false);
        }
        double nextDouble = getSecRan().nextDouble();
        return (long) FastMath.floor((((double) j2) * nextDouble) + ((1.0d - nextDouble) * ((double) j)) + nextDouble);
    }

    public long nextPoisson(double d) throws NotStrictlyPositiveException {
        PoissonDistribution poissonDistribution = new PoissonDistribution(getRan(), d, 1.0E-12d, PoissonDistribution.DEFAULT_MAX_ITERATIONS);
        return (long) poissonDistribution.sample();
    }

    public double nextGaussian(double d, double d2) throws NotStrictlyPositiveException {
        if (d2 > 0.0d) {
            return (d2 * getRan().nextGaussian()) + d;
        }
        throw new NotStrictlyPositiveException(LocalizedFormats.STANDARD_DEVIATION, Double.valueOf(d2));
    }

    public double nextExponential(double d) throws NotStrictlyPositiveException {
        ExponentialDistribution exponentialDistribution = new ExponentialDistribution(getRan(), d, 1.0E-9d);
        return exponentialDistribution.sample();
    }

    public double nextGamma(double d, double d2) throws NotStrictlyPositiveException {
        GammaDistribution gammaDistribution = new GammaDistribution(getRan(), d, d2, 1.0E-9d);
        return gammaDistribution.sample();
    }

    public int nextHypergeometric(int i, int i2, int i3) throws NotPositiveException, NotStrictlyPositiveException, NumberIsTooLargeException {
        return new HypergeometricDistribution(getRan(), i, i2, i3).sample();
    }

    public int nextPascal(int i, double d) throws NotStrictlyPositiveException, OutOfRangeException {
        return new PascalDistribution(getRan(), i, d).sample();
    }

    public double nextT(double d) throws NotStrictlyPositiveException {
        TDistribution tDistribution = new TDistribution(getRan(), d, 1.0E-9d);
        return tDistribution.sample();
    }

    public double nextWeibull(double d, double d2) throws NotStrictlyPositiveException {
        WeibullDistribution weibullDistribution = new WeibullDistribution(getRan(), d, d2, 1.0E-9d);
        return weibullDistribution.sample();
    }

    public int nextZipf(int i, double d) throws NotStrictlyPositiveException {
        return new ZipfDistribution(getRan(), i, d).sample();
    }

    public double nextBeta(double d, double d2) {
        BetaDistribution betaDistribution = new BetaDistribution(getRan(), d, d2, 1.0E-9d);
        return betaDistribution.sample();
    }

    public int nextBinomial(int i, double d) {
        return new BinomialDistribution(getRan(), i, d).sample();
    }

    public double nextCauchy(double d, double d2) {
        CauchyDistribution cauchyDistribution = new CauchyDistribution(getRan(), d, d2, 1.0E-9d);
        return cauchyDistribution.sample();
    }

    public double nextChiSquare(double d) {
        ChiSquaredDistribution chiSquaredDistribution = new ChiSquaredDistribution(getRan(), d, 1.0E-9d);
        return chiSquaredDistribution.sample();
    }

    public double nextF(double d, double d2) throws NotStrictlyPositiveException {
        FDistribution fDistribution = new FDistribution(getRan(), d, d2, 1.0E-9d);
        return fDistribution.sample();
    }

    public double nextUniform(double d, double d2) throws NumberIsTooLargeException, NotFiniteNumberException, NotANumberException {
        return nextUniform(d, d2, false);
    }

    public double nextUniform(double d, double d2, boolean z) throws NumberIsTooLargeException, NotFiniteNumberException, NotANumberException {
        if (d >= d2) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Double.valueOf(d), Double.valueOf(d2), false);
        } else if (Double.isInfinite(d)) {
            throw new NotFiniteNumberException(LocalizedFormats.INFINITE_BOUND, Double.valueOf(d), new Object[0]);
        } else if (Double.isInfinite(d2)) {
            throw new NotFiniteNumberException(LocalizedFormats.INFINITE_BOUND, Double.valueOf(d2), new Object[0]);
        } else if (Double.isNaN(d) || Double.isNaN(d2)) {
            throw new NotANumberException();
        } else {
            RandomGenerator ran = getRan();
            double nextDouble = ran.nextDouble();
            while (!z && nextDouble <= 0.0d) {
                nextDouble = ran.nextDouble();
            }
            return (d2 * nextDouble) + ((1.0d - nextDouble) * d);
        }
    }

    public int[] nextPermutation(int i, int i2) throws NumberIsTooLargeException, NotStrictlyPositiveException {
        if (i2 > i) {
            throw new NumberIsTooLargeException(LocalizedFormats.PERMUTATION_EXCEEDS_N, Integer.valueOf(i2), Integer.valueOf(i), true);
        } else if (i2 <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.PERMUTATION_SIZE, Integer.valueOf(i2));
        } else {
            int[] natural = getNatural(i);
            shuffle(natural, i - i2);
            int[] iArr = new int[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                iArr[i3] = natural[(i - i3) - 1];
            }
            return iArr;
        }
    }

    public Object[] nextSample(Collection<?> collection, int i) throws NumberIsTooLargeException, NotStrictlyPositiveException {
        int size = collection.size();
        if (i > size) {
            throw new NumberIsTooLargeException(LocalizedFormats.SAMPLE_SIZE_EXCEEDS_COLLECTION_SIZE, Integer.valueOf(i), Integer.valueOf(size), true);
        } else if (i <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_SAMPLES, Integer.valueOf(i));
        } else {
            Object[] array = collection.toArray();
            int[] nextPermutation = nextPermutation(size, i);
            Object[] objArr = new Object[i];
            for (int i2 = 0; i2 < i; i2++) {
                objArr[i2] = array[nextPermutation[i2]];
            }
            return objArr;
        }
    }

    public void reSeed(long j) {
        getRan().setSeed(j);
    }

    public void reSeedSecure() {
        getSecRan().setSeed(System.currentTimeMillis());
    }

    public void reSeedSecure(long j) {
        getSecRan().setSeed(j);
    }

    public void reSeed() {
        getRan().setSeed(System.currentTimeMillis() + ((long) System.identityHashCode(this)));
    }

    public void setSecureAlgorithm(String str, String str2) throws NoSuchAlgorithmException, NoSuchProviderException {
        this.secRand = SecureRandom.getInstance(str, str2);
    }

    private RandomGenerator getRan() {
        if (this.rand == null) {
            initRan();
        }
        return this.rand;
    }

    private void initRan() {
        this.rand = new Well19937c(System.currentTimeMillis() + ((long) System.identityHashCode(this)));
    }

    private SecureRandom getSecRan() {
        if (this.secRand == null) {
            this.secRand = new SecureRandom();
            this.secRand.setSeed(System.currentTimeMillis() + ((long) System.identityHashCode(this)));
        }
        return this.secRand;
    }

    private void shuffle(int[] iArr, int i) {
        for (int length = iArr.length - 1; length >= i; length--) {
            int i2 = 0;
            if (length != 0) {
                i2 = nextInt(0, length);
            }
            int i3 = iArr[i2];
            iArr[i2] = iArr[length];
            iArr[length] = i3;
        }
    }

    private int[] getNatural(int i) {
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = i2;
        }
        return iArr;
    }
}
