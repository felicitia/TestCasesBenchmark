package org.apache.commons.math3.special;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.ContinuedFraction;
import org.apache.commons.math3.util.FastMath;

public class Gamma {
    private static final double C_LIMIT = 49.0d;
    private static final double DEFAULT_EPSILON = 1.0E-14d;
    public static final double GAMMA = 0.5772156649015329d;
    private static final double HALF_LOG_2_PI = (0.5d * FastMath.log(6.283185307179586d));
    private static final double INV_GAMMA1P_M1_A0 = 6.116095104481416E-9d;
    private static final double INV_GAMMA1P_M1_A1 = 6.247308301164655E-9d;
    private static final double INV_GAMMA1P_M1_B1 = 0.203610414066807d;
    private static final double INV_GAMMA1P_M1_B2 = 0.026620534842894922d;
    private static final double INV_GAMMA1P_M1_B3 = 4.939449793824468E-4d;
    private static final double INV_GAMMA1P_M1_B4 = -8.514194324403149E-6d;
    private static final double INV_GAMMA1P_M1_B5 = -6.4304548177935305E-6d;
    private static final double INV_GAMMA1P_M1_B6 = 9.926418406727737E-7d;
    private static final double INV_GAMMA1P_M1_B7 = -6.077618957228252E-8d;
    private static final double INV_GAMMA1P_M1_B8 = 1.9575583661463974E-10d;
    private static final double INV_GAMMA1P_M1_C = -0.42278433509846713d;
    private static final double INV_GAMMA1P_M1_C0 = 0.5772156649015329d;
    private static final double INV_GAMMA1P_M1_C1 = -0.6558780715202539d;
    private static final double INV_GAMMA1P_M1_C10 = -2.013485478078824E-5d;
    private static final double INV_GAMMA1P_M1_C11 = -1.2504934821426706E-6d;
    private static final double INV_GAMMA1P_M1_C12 = 1.133027231981696E-6d;
    private static final double INV_GAMMA1P_M1_C13 = -2.056338416977607E-7d;
    private static final double INV_GAMMA1P_M1_C2 = -0.04200263503409524d;
    private static final double INV_GAMMA1P_M1_C3 = 0.16653861138229148d;
    private static final double INV_GAMMA1P_M1_C4 = -0.04219773455554433d;
    private static final double INV_GAMMA1P_M1_C5 = -0.009621971527876973d;
    private static final double INV_GAMMA1P_M1_C6 = 0.0072189432466631d;
    private static final double INV_GAMMA1P_M1_C7 = -0.0011651675918590652d;
    private static final double INV_GAMMA1P_M1_C8 = -2.1524167411495098E-4d;
    private static final double INV_GAMMA1P_M1_C9 = 1.280502823881162E-4d;
    private static final double INV_GAMMA1P_M1_P0 = 6.116095104481416E-9d;
    private static final double INV_GAMMA1P_M1_P1 = 6.8716741130671986E-9d;
    private static final double INV_GAMMA1P_M1_P2 = 6.820161668496171E-10d;
    private static final double INV_GAMMA1P_M1_P3 = 4.686843322948848E-11d;
    private static final double INV_GAMMA1P_M1_P4 = 1.5728330277104463E-12d;
    private static final double INV_GAMMA1P_M1_P5 = -1.2494415722763663E-13d;
    private static final double INV_GAMMA1P_M1_P6 = 4.343529937408594E-15d;
    private static final double INV_GAMMA1P_M1_Q1 = 0.3056961078365221d;
    private static final double INV_GAMMA1P_M1_Q2 = 0.054642130860422966d;
    private static final double INV_GAMMA1P_M1_Q3 = 0.004956830093825887d;
    private static final double INV_GAMMA1P_M1_Q4 = 2.6923694661863613E-4d;
    private static final double[] LANCZOS = {0.9999999999999971d, 57.15623566586292d, -59.59796035547549d, 14.136097974741746d, -0.4919138160976202d, 3.399464998481189E-5d, 4.652362892704858E-5d, -9.837447530487956E-5d, 1.580887032249125E-4d, -2.1026444172410488E-4d, 2.1743961811521265E-4d, -1.643181065367639E-4d, 8.441822398385275E-5d, -2.6190838401581408E-5d, 3.6899182659531625E-6d};
    public static final double LANCZOS_G = 4.7421875d;
    private static final double SQRT_TWO_PI = 2.5066282746310007d;
    private static final double S_LIMIT = 1.0E-5d;

    private Gamma() {
    }

    public static double logGamma(double d) {
        double d2;
        if (Double.isNaN(d) || d <= 0.0d) {
            d2 = Double.NaN;
        } else if (d < 0.5d) {
            return logGamma1p(d) - FastMath.log(d);
        } else {
            if (d <= 2.5d) {
                return logGamma1p((d - 0.5d) - 0.5d);
            }
            if (d <= 8.0d) {
                int floor = (int) FastMath.floor(d - 1.5d);
                double d3 = 1.0d;
                for (int i = 1; i <= floor; i++) {
                    d3 *= d - ((double) i);
                }
                return logGamma1p(d - ((double) (floor + 1))) + FastMath.log(d3);
            }
            double d4 = 4.7421875d + d + 0.5d;
            d2 = (((0.5d + d) * FastMath.log(d4)) - d4) + HALF_LOG_2_PI + FastMath.log(lanczos(d) / d);
        }
        return d2;
    }

    public static double regularizedGammaP(double d, double d2) {
        return regularizedGammaP(d, d2, DEFAULT_EPSILON, Integer.MAX_VALUE);
    }

    public static double regularizedGammaP(double d, double d2, double d3, int i) {
        double d4 = d2;
        int i2 = i;
        if (Double.isNaN(d) || Double.isNaN(d2) || d <= 0.0d || d4 < 0.0d) {
            return Double.NaN;
        }
        if (d4 == 0.0d) {
            return 0.0d;
        }
        if (d4 >= d + 1.0d) {
            return 1.0d - regularizedGammaQ(d, d2, d3, i);
        }
        double d5 = 1.0d / d;
        double d6 = 0.0d;
        double d7 = d5;
        while (FastMath.abs(d5 / d7) > d3 && d6 < ((double) i2) && d7 < Double.POSITIVE_INFINITY) {
            d6 += 1.0d;
            d5 *= d4 / (d + d6);
            d7 += d5;
        }
        if (d6 >= ((double) i2)) {
            throw new MaxCountExceededException(Integer.valueOf(i));
        } else if (Double.isInfinite(d7)) {
            return 1.0d;
        } else {
            return FastMath.exp(((-d4) + (FastMath.log(d2) * d)) - logGamma(d)) * d7;
        }
    }

    public static double regularizedGammaQ(double d, double d2) {
        return regularizedGammaQ(d, d2, DEFAULT_EPSILON, Integer.MAX_VALUE);
    }

    public static double regularizedGammaQ(final double d, double d2, double d3, int i) {
        if (Double.isNaN(d) || Double.isNaN(d2) || d <= 0.0d || d2 < 0.0d) {
            return Double.NaN;
        }
        if (d2 == 0.0d) {
            return 1.0d;
        }
        if (d2 < d + 1.0d) {
            return 1.0d - regularizedGammaP(d, d2, d3, i);
        }
        return (1.0d / new ContinuedFraction() {
            /* access modifiers changed from: protected */
            public double getA(int i, double d) {
                return (((2.0d * ((double) i)) + 1.0d) - d) + d;
            }

            /* access modifiers changed from: protected */
            public double getB(int i, double d) {
                double d2 = (double) i;
                return d2 * (d - d2);
            }
        }.evaluate(d2, d3, i)) * FastMath.exp(((-d2) + (FastMath.log(d2) * d)) - logGamma(d));
    }

    public static double digamma(double d) {
        if (d > 0.0d && d <= 1.0E-5d) {
            return -0.5772156649015329d - (1.0d / d);
        }
        if (d < C_LIMIT) {
            return digamma(d + 1.0d) - (1.0d / d);
        }
        double d2 = 1.0d / (d * d);
        return (FastMath.log(d) - (0.5d / d)) - (d2 * (0.08333333333333333d + ((0.008333333333333333d - (d2 / 252.0d)) * d2)));
    }

    public static double trigamma(double d) {
        if (d > 0.0d && d <= 1.0E-5d) {
            return 1.0d / (d * d);
        }
        if (d < C_LIMIT) {
            return trigamma(d + 1.0d) + (1.0d / (d * d));
        }
        double d2 = 1.0d / (d * d);
        return (1.0d / d) + (d2 / 2.0d) + ((d2 / d) * (0.16666666666666666d - (d2 * (0.03333333333333333d + (d2 / 42.0d)))));
    }

    public static double lanczos(double d) {
        double d2 = 0.0d;
        for (int length = LANCZOS.length - 1; length > 0; length--) {
            d2 += LANCZOS[length] / (((double) length) + d);
        }
        return d2 + LANCZOS[0];
    }

    public static double invGamma1pm1(double d) {
        if (d < -0.5d) {
            throw new NumberIsTooSmallException(Double.valueOf(d), Double.valueOf(-0.5d), true);
        } else if (d > 1.5d) {
            throw new NumberIsTooLargeException(Double.valueOf(d), Double.valueOf(1.5d), true);
        } else {
            double d2 = d <= 0.5d ? d : (d - 0.5d) - 0.5d;
            if (d2 < 0.0d) {
                double d3 = INV_GAMMA1P_M1_C + ((INV_GAMMA1P_M1_C1 + ((INV_GAMMA1P_M1_C2 + ((INV_GAMMA1P_M1_C3 + ((INV_GAMMA1P_M1_C4 + ((INV_GAMMA1P_M1_C5 + ((INV_GAMMA1P_M1_C6 + ((INV_GAMMA1P_M1_C7 + ((INV_GAMMA1P_M1_C8 + ((INV_GAMMA1P_M1_C9 + ((INV_GAMMA1P_M1_C10 + ((INV_GAMMA1P_M1_C11 + ((INV_GAMMA1P_M1_C12 + ((INV_GAMMA1P_M1_C13 + (((6.116095104481416E-9d + (INV_GAMMA1P_M1_A1 * d2)) / (1.0d + ((INV_GAMMA1P_M1_B1 + ((INV_GAMMA1P_M1_B2 + ((INV_GAMMA1P_M1_B3 + ((INV_GAMMA1P_M1_B4 + ((INV_GAMMA1P_M1_B5 + ((INV_GAMMA1P_M1_B6 + ((INV_GAMMA1P_M1_B7 + (INV_GAMMA1P_M1_B8 * d2)) * d2)) * d2)) * d2)) * d2)) * d2)) * d2)) * d2))) * d2)) * d2)) * d2)) * d2)) * d2)) * d2)) * d2)) * d2)) * d2)) * d2)) * d2)) * d2)) * d2)) * d2);
                return d > 0.5d ? (d2 * d3) / d : d * (d3 + 0.5d + 0.5d);
            }
            double d4 = 0.5772156649015329d + ((INV_GAMMA1P_M1_C1 + ((INV_GAMMA1P_M1_C2 + ((INV_GAMMA1P_M1_C3 + ((INV_GAMMA1P_M1_C4 + ((INV_GAMMA1P_M1_C5 + ((INV_GAMMA1P_M1_C6 + ((INV_GAMMA1P_M1_C7 + ((INV_GAMMA1P_M1_C8 + ((INV_GAMMA1P_M1_C9 + ((INV_GAMMA1P_M1_C10 + ((INV_GAMMA1P_M1_C11 + ((INV_GAMMA1P_M1_C12 + ((INV_GAMMA1P_M1_C13 + (((6.116095104481416E-9d + ((INV_GAMMA1P_M1_P1 + ((INV_GAMMA1P_M1_P2 + ((INV_GAMMA1P_M1_P3 + ((INV_GAMMA1P_M1_P4 + ((INV_GAMMA1P_M1_P5 + (INV_GAMMA1P_M1_P6 * d2)) * d2)) * d2)) * d2)) * d2)) * d2)) / (1.0d + ((INV_GAMMA1P_M1_Q1 + ((INV_GAMMA1P_M1_Q2 + ((INV_GAMMA1P_M1_Q3 + (INV_GAMMA1P_M1_Q4 * d2)) * d2)) * d2)) * d2))) * d2)) * d2)) * d2)) * d2)) * d2)) * d2)) * d2)) * d2)) * d2)) * d2)) * d2)) * d2)) * d2)) * d2);
            return d > 0.5d ? (d2 / d) * ((d4 - 0.5d) - 0.5d) : d * d4;
        }
    }

    public static double logGamma1p(double d) throws NumberIsTooSmallException, NumberIsTooLargeException {
        if (d < -0.5d) {
            throw new NumberIsTooSmallException(Double.valueOf(d), Double.valueOf(-0.5d), true);
        } else if (d <= 1.5d) {
            return -FastMath.log1p(invGamma1pm1(d));
        } else {
            throw new NumberIsTooLargeException(Double.valueOf(d), Double.valueOf(1.5d), true);
        }
    }

    public static double gamma(double d) {
        double d2;
        if (d == FastMath.rint(d) && d <= 0.0d) {
            return Double.NaN;
        }
        double abs = FastMath.abs(d);
        if (abs > 20.0d) {
            double d3 = 4.7421875d + abs + 0.5d;
            d2 = lanczos(abs) * (SQRT_TWO_PI / d) * FastMath.pow(d3, 0.5d + abs) * FastMath.exp(-d3);
            if (d <= 0.0d) {
                d2 = -3.141592653589793d / ((d * FastMath.sin(3.141592653589793d * d)) * d2);
            }
        } else if (d >= 1.0d) {
            double d4 = 1.0d;
            while (d > 2.5d) {
                d -= 1.0d;
                d4 *= d;
            }
            d2 = d4 / (1.0d + invGamma1pm1(d - 1.0d));
        } else {
            double d5 = d;
            while (d < -0.5d) {
                d += 1.0d;
                d5 *= d;
            }
            d2 = 1.0d / (d5 * (invGamma1pm1(d) + 1.0d));
        }
        return d2;
    }
}
