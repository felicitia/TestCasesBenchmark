package org.apache.commons.math3.special;

import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.ContinuedFraction;
import org.apache.commons.math3.util.FastMath;

public class Beta {
    private static final double DEFAULT_EPSILON = 1.0E-14d;
    private static final double[] DELTA = {0.08333333333333333d, -2.777777777777778E-5d, 7.936507936507937E-8d, -5.952380952380953E-10d, 8.417508417508329E-12d, -1.917526917518546E-13d, 6.410256405103255E-15d, -2.955065141253382E-16d, 1.7964371635940225E-17d, -1.3922896466162779E-18d, 1.338028550140209E-19d, -1.542460098679661E-20d, 1.9770199298095743E-21d, -2.3406566479399704E-22d, 1.713480149663986E-23d};
    private static final double HALF_LOG_TWO_PI = 0.9189385332046727d;

    private Beta() {
    }

    public static double regularizedBeta(double d, double d2, double d3) {
        return regularizedBeta(d, d2, d3, DEFAULT_EPSILON, Integer.MAX_VALUE);
    }

    public static double regularizedBeta(double d, double d2, double d3, double d4) {
        return regularizedBeta(d, d2, d3, d4, Integer.MAX_VALUE);
    }

    public static double regularizedBeta(double d, double d2, double d3, int i) {
        return regularizedBeta(d, d2, d3, DEFAULT_EPSILON, i);
    }

    public static double regularizedBeta(double d, double d2, double d3, double d4, int i) {
        final double d5 = d2;
        final double d6 = d3;
        if (Double.isNaN(d) || Double.isNaN(d2) || Double.isNaN(d3) || d < 0.0d || d > 1.0d || d5 <= 0.0d || d6 <= 0.0d) {
            return Double.NaN;
        }
        if (d > (d5 + 1.0d) / ((d5 + d6) + 2.0d)) {
            return 1.0d - regularizedBeta(1.0d - d, d6, d5, d4, i);
        }
        return (FastMath.exp((((FastMath.log(d) * d5) + (FastMath.log(1.0d - d) * d6)) - FastMath.log(d2)) - logBeta(d2, d3)) * 1.0d) / new ContinuedFraction() {
            /* access modifiers changed from: protected */
            public double getA(int i, double d) {
                return 1.0d;
            }

            /* access modifiers changed from: protected */
            public double getB(int i, double d) {
                int i2 = i;
                if (i2 % 2 == 0) {
                    double d2 = ((double) i2) / 2.0d;
                    double d3 = 2.0d * d2;
                    return (((d6 - d2) * d2) * d) / (((d5 + d3) - 1.0d) * (d5 + d3));
                }
                double d4 = (((double) i2) - 1.0d) / 2.0d;
                double d5 = 2.0d * d4;
                return (-(((d5 + d4) * ((d5 + d6) + d4)) * d)) / ((d5 + d5) * ((d5 + d5) + 1.0d));
            }
        }.evaluate(d, d4, i);
    }

    @Deprecated
    public static double logBeta(double d, double d2, double d3, int i) {
        return logBeta(d, d2);
    }

    private static double logGammaSum(double d, double d2) throws OutOfRangeException {
        if (d < 1.0d || d > 2.0d) {
            throw new OutOfRangeException(Double.valueOf(d), Double.valueOf(1.0d), Double.valueOf(2.0d));
        } else if (d2 < 1.0d || d2 > 2.0d) {
            throw new OutOfRangeException(Double.valueOf(d2), Double.valueOf(1.0d), Double.valueOf(2.0d));
        } else {
            double d3 = (d - 1.0d) + (d2 - 1.0d);
            if (d3 <= 0.5d) {
                return Gamma.logGamma1p(1.0d + d3);
            }
            if (d3 <= 1.5d) {
                return Gamma.logGamma1p(d3) + FastMath.log1p(d3);
            }
            return Gamma.logGamma1p(d3 - 1.0d) + FastMath.log(d3 * (1.0d + d3));
        }
    }

    private static double logGammaMinusLogGammaSum(double d, double d2) throws NumberIsTooSmallException {
        double d3;
        double d4;
        if (d < 0.0d) {
            throw new NumberIsTooSmallException(Double.valueOf(d), Double.valueOf(0.0d), true);
        } else if (d2 < 10.0d) {
            throw new NumberIsTooSmallException(Double.valueOf(d2), Double.valueOf(10.0d), true);
        } else {
            if (d <= d2) {
                d4 = (d - 0.5d) + d2;
                d3 = deltaMinusDeltaSum(d, d2);
            } else {
                d4 = (d2 - 0.5d) + d;
                d3 = deltaMinusDeltaSum(d2, d);
            }
            double log1p = d4 * FastMath.log1p(d / d2);
            double log = d * (FastMath.log(d2) - 1.0d);
            return log1p <= log ? (d3 - log1p) - log : (d3 - log) - log1p;
        }
    }

    private static double deltaMinusDeltaSum(double d, double d2) throws OutOfRangeException, NumberIsTooSmallException {
        if (d < 0.0d || d > d2) {
            throw new OutOfRangeException(Double.valueOf(d), Integer.valueOf(0), Double.valueOf(d2));
        } else if (d2 < 10.0d) {
            throw new NumberIsTooSmallException(Double.valueOf(d2), Integer.valueOf(10), true);
        } else {
            double d3 = d / d2;
            double d4 = 1.0d + d3;
            double d5 = d3 / d4;
            double d6 = 1.0d / d4;
            double d7 = d6 * d6;
            double[] dArr = new double[DELTA.length];
            dArr[0] = 1.0d;
            for (int i = 1; i < dArr.length; i++) {
                dArr[i] = (dArr[i - 1] * d7) + d6 + 1.0d;
            }
            double d8 = 10.0d / d2;
            double d9 = d8 * d8;
            double d10 = DELTA[DELTA.length - 1] * dArr[dArr.length - 1];
            for (int length = DELTA.length - 2; length >= 0; length--) {
                d10 = (d10 * d9) + (DELTA[length] * dArr[length]);
            }
            return (d10 * d5) / d2;
        }
    }

    private static double sumDeltaMinusDeltaSum(double d, double d2) {
        if (d < 10.0d) {
            throw new NumberIsTooSmallException(Double.valueOf(d), Double.valueOf(10.0d), true);
        } else if (d2 < 10.0d) {
            throw new NumberIsTooSmallException(Double.valueOf(d2), Double.valueOf(10.0d), true);
        } else {
            double min = FastMath.min(d, d2);
            double max = FastMath.max(d, d2);
            double d3 = 10.0d / min;
            double d4 = d3 * d3;
            double d5 = DELTA[DELTA.length - 1];
            for (int length = DELTA.length - 2; length >= 0; length--) {
                d5 = (d5 * d4) + DELTA[length];
            }
            return (d5 / min) + deltaMinusDeltaSum(min, max);
        }
    }

    public static double logBeta(double d, double d2) {
        if (Double.isNaN(d) || Double.isNaN(d2) || d <= 0.0d || d2 <= 0.0d) {
            return Double.NaN;
        }
        double min = FastMath.min(d, d2);
        double max = FastMath.max(d, d2);
        if (min >= 10.0d) {
            double sumDeltaMinusDeltaSum = sumDeltaMinusDeltaSum(min, max);
            double d3 = min / max;
            double log = (-(min - 0.5d)) * FastMath.log(d3 / (1.0d + d3));
            double log1p = FastMath.log1p(d3) * max;
            if (log <= log1p) {
                return ((((-0.5d * FastMath.log(max)) + HALF_LOG_TWO_PI) + sumDeltaMinusDeltaSum) - log) - log1p;
            }
            return ((((-0.5d * FastMath.log(max)) + HALF_LOG_TWO_PI) + sumDeltaMinusDeltaSum) - log1p) - log;
        } else if (min > 2.0d) {
            if (max > 1000.0d) {
                int floor = (int) FastMath.floor(min - 1.0d);
                double d4 = 1.0d;
                for (int i = 0; i < floor; i++) {
                    min -= 1.0d;
                    d4 *= min / ((min / max) + 1.0d);
                }
                return (FastMath.log(d4) - (((double) floor) * FastMath.log(max))) + Gamma.logGamma(min) + logGammaMinusLogGammaSum(min, max);
            }
            double d5 = 1.0d;
            while (min > 2.0d) {
                min -= 1.0d;
                double d6 = min / max;
                d5 *= d6 / (1.0d + d6);
            }
            if (max >= 10.0d) {
                return FastMath.log(d5) + Gamma.logGamma(min) + logGammaMinusLogGammaSum(min, max);
            }
            double d7 = 1.0d;
            while (max > 2.0d) {
                max -= 1.0d;
                d7 *= max / (min + max);
            }
            return FastMath.log(d5) + FastMath.log(d7) + Gamma.logGamma(min) + (Gamma.logGamma(max) - logGammaSum(min, max));
        } else if (min >= 1.0d) {
            if (max <= 2.0d) {
                return (Gamma.logGamma(min) + Gamma.logGamma(max)) - logGammaSum(min, max);
            }
            if (max >= 10.0d) {
                return Gamma.logGamma(min) + logGammaMinusLogGammaSum(min, max);
            }
            double d8 = 1.0d;
            while (max > 2.0d) {
                max -= 1.0d;
                d8 *= max / (min + max);
            }
            return FastMath.log(d8) + Gamma.logGamma(min) + (Gamma.logGamma(max) - logGammaSum(min, max));
        } else if (max >= 10.0d) {
            return Gamma.logGamma(min) + logGammaMinusLogGammaSum(min, max);
        } else {
            return FastMath.log((Gamma.gamma(min) * Gamma.gamma(max)) / Gamma.gamma(min + max));
        }
    }
}
