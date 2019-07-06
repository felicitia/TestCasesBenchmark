package org.apache.commons.math3.util;

import java.io.PrintStream;

public class FastMath {
    private static final double[] CBRTTWO = {0.6299605249474366d, 0.7937005259840998d, 1.0d, 1.2599210498948732d, 1.5874010519681994d};
    private static final double[] COSINE_TABLE_A = {1.0d, 0.9921976327896118d, 0.9689123630523682d, 0.9305076599121094d, 0.8775825500488281d, 0.8109631538391113d, 0.7316888570785522d, 0.6409968137741089d, 0.5403022766113281d, 0.4311765432357788d, 0.3153223395347595d, 0.19454771280288696d, 0.07073719799518585d, -0.05417713522911072d};
    private static final double[] COSINE_TABLE_B = {0.0d, 3.4439717236742845E-8d, 5.865827662008209E-8d, -3.7999795083850525E-8d, 1.184154459111628E-8d, -3.43338934259355E-8d, 1.1795268640216787E-8d, 4.438921624363781E-8d, 2.925681159240093E-8d, -2.6437112632041807E-8d, 2.2860509143963117E-8d, -4.813899778443457E-9d, 3.6725170580355583E-9d, 2.0217439756338078E-10d};
    public static final double E = 2.718281828459045d;
    private static final double[] EIGHTHS = {0.0d, 0.125d, F_1_4, 0.375d, F_1_2, 0.625d, 0.75d, F_7_8, 1.0d, 1.125d, 1.25d, 1.375d, 1.5d, 1.625d};
    static final int EXP_FRAC_TABLE_LEN = 1025;
    static final int EXP_INT_TABLE_LEN = 1500;
    static final int EXP_INT_TABLE_MAX_INDEX = 750;
    private static final double F_11_12 = 0.9166666666666666d;
    private static final double F_13_14 = 0.9285714285714286d;
    private static final double F_15_16 = 0.9375d;
    private static final double F_1_11 = 0.09090909090909091d;
    private static final double F_1_13 = 0.07692307692307693d;
    private static final double F_1_15 = 0.06666666666666667d;
    private static final double F_1_17 = 0.058823529411764705d;
    private static final double F_1_2 = 0.5d;
    private static final double F_1_3 = 0.3333333333333333d;
    private static final double F_1_4 = 0.25d;
    private static final double F_1_5 = 0.2d;
    private static final double F_1_7 = 0.14285714285714285d;
    private static final double F_1_9 = 0.1111111111111111d;
    private static final double F_3_4 = 0.75d;
    private static final double F_5_6 = 0.8333333333333334d;
    private static final double F_7_8 = 0.875d;
    private static final double F_9_10 = 0.9d;
    private static final long HEX_40000000 = 1073741824;
    private static final double LN_2_A = 0.6931470632553101d;
    private static final double LN_2_B = 1.1730463525082348E-7d;
    private static final double[][] LN_HI_PREC_COEF = {new double[]{1.0d, -6.032174644509064E-23d}, new double[]{-0.25d, -0.25d}, new double[]{0.3333333134651184d, 1.9868161777724352E-8d}, new double[]{-0.2499999701976776d, -2.957007209750105E-8d}, new double[]{0.19999954104423523d, 1.5830993332061267E-10d}, new double[]{-0.16624879837036133d, -2.6033824355191673E-8d}};
    static final int LN_MANT_LEN = 1024;
    private static final double[][] LN_QUICK_COEF = {new double[]{1.0d, 5.669184079525E-24d}, new double[]{-0.25d, -0.25d}, new double[]{0.3333333134651184d, 1.986821492305628E-8d}, new double[]{-0.25d, -6.663542893624021E-14d}, new double[]{0.19999998807907104d, 1.1921056801463227E-8d}, new double[]{-0.1666666567325592d, -7.800414592973399E-9d}, new double[]{0.1428571343421936d, 5.650007086920087E-9d}, new double[]{-0.12502530217170715d, -7.44321345601866E-11d}, new double[]{0.11113807559013367d, 9.219544613762692E-9d}};
    private static final double LOG_MAX_VALUE = StrictMath.log(Double.MAX_VALUE);
    private static final long MASK_30BITS = -1073741824;
    public static final double PI = 3.141592653589793d;
    private static final long[] PI_O_4_BITS = {-3958705157555305932L, -4267615245585081135L};
    private static final long[] RECIP_2PI = {2935890503282001226L, 9154082963658192752L, 3952090531849364496L, 9193070505571053912L, 7910884519577875640L, 113236205062349959L, 4577762542105553359L, -5034868814120038111L, 4208363204685324176L, 5648769086999809661L, 2819561105158720014L, -4035746434778044925L, -302932621132653753L, -2644281811660520851L, -3183605296591799669L, 6722166367014452318L, -3512299194304650054L, -7278142539171889152L};
    private static final boolean RECOMPUTE_TABLES_AT_RUNTIME = false;
    private static final double[] SINE_TABLE_A = {0.0d, 0.1246747374534607d, 0.24740394949913025d, 0.366272509098053d, 0.4794255495071411d, 0.5850973129272461d, 0.6816387176513672d, 0.7675435543060303d, 0.8414709568023682d, 0.902267575263977d, 0.9489846229553223d, 0.9808930158615112d, 0.9974949359893799d, 0.9985313415527344d};
    private static final double[] SINE_TABLE_B = {0.0d, -4.068233003401932E-9d, 9.755392680573412E-9d, 1.9987994582857286E-8d, -1.0902938113007961E-8d, -3.9986783938944604E-8d, 4.23719669792332E-8d, -5.207000323380292E-8d, 2.800552834259E-8d, 1.883511811213715E-8d, -3.5997360512765566E-9d, 4.116164446561962E-8d, 5.0614674548127384E-8d, -1.0129027912496858E-9d};
    private static final int SINE_TABLE_LEN = 14;
    private static final double[] TANGENT_TABLE_A = {0.0d, 0.1256551444530487d, 0.25534194707870483d, 0.3936265707015991d, 0.5463024377822876d, 0.7214844226837158d, 0.9315965175628662d, 1.1974215507507324d, 1.5574076175689697d, 2.092571258544922d, 3.0095696449279785d, 5.041914939880371d, 14.101419448852539d, -18.430862426757812d};
    private static final double[] TANGENT_TABLE_B = {0.0d, -7.877917738262007E-9d, -2.5857668567479893E-8d, 5.2240336371356666E-9d, 5.206150291559893E-8d, 1.8307188599677033E-8d, -5.7618793749770706E-8d, 7.848361555046424E-8d, 1.0708593250394448E-7d, 1.7827257129423813E-8d, 2.893485277253286E-8d, 3.1660099222737955E-7d, 4.983191803254889E-7d, -3.356118100840571E-7d};
    private static final double TWO_POWER_52 = 4.503599627370496E15d;
    private static final double TWO_POWER_53 = 9.007199254740992E15d;

    private static class CodyWaite {
        private final int finalK;
        private final double finalRemA;
        private final double finalRemB;

        CodyWaite(double d) {
            int i = (int) (0.6366197723675814d * d);
            while (true) {
                double d2 = (double) (-i);
                double d3 = 1.570796251296997d * d2;
                double d4 = d + d3;
                double d5 = 7.549789948768648E-8d * d2;
                double d6 = d5 + d4;
                double d7 = (-((d4 - d) - d3)) + (-((d6 - d4) - d5));
                double d8 = d2 * 6.123233995736766E-17d;
                double d9 = d8 + d6;
                double d10 = d7 + (-((d9 - d6) - d8));
                if (d9 > 0.0d) {
                    this.finalK = i;
                    this.finalRemA = d9;
                    this.finalRemB = d10;
                    return;
                }
                i--;
            }
        }

        /* access modifiers changed from: 0000 */
        public int getK() {
            return this.finalK;
        }

        /* access modifiers changed from: 0000 */
        public double getRemA() {
            return this.finalRemA;
        }

        /* access modifiers changed from: 0000 */
        public double getRemB() {
            return this.finalRemB;
        }
    }

    private static class ExpFracTable {
        /* access modifiers changed from: private */
        public static final double[] EXP_FRAC_TABLE_A = FastMathLiteralArrays.loadExpFracA();
        /* access modifiers changed from: private */
        public static final double[] EXP_FRAC_TABLE_B = FastMathLiteralArrays.loadExpFracB();

        private ExpFracTable() {
        }
    }

    private static class ExpIntTable {
        /* access modifiers changed from: private */
        public static final double[] EXP_INT_TABLE_A = FastMathLiteralArrays.loadExpIntA();
        /* access modifiers changed from: private */
        public static final double[] EXP_INT_TABLE_B = FastMathLiteralArrays.loadExpIntB();

        private ExpIntTable() {
        }
    }

    private static class lnMant {
        /* access modifiers changed from: private */
        public static final double[][] LN_MANT = FastMathLiteralArrays.loadLnMant();

        private lnMant() {
        }
    }

    public static double abs(double d) {
        if (d < 0.0d) {
            return -d;
        }
        if (d == 0.0d) {
            return 0.0d;
        }
        return d;
    }

    public static float abs(float f) {
        if (f < 0.0f) {
            return -f;
        }
        if (f == 0.0f) {
            return 0.0f;
        }
        return f;
    }

    public static int abs(int i) {
        return i < 0 ? -i : i;
    }

    public static long abs(long j) {
        return j < 0 ? -j : j;
    }

    public static double floor(double d) {
        if (d != d || d >= TWO_POWER_52 || d <= -4.503599627370496E15d) {
            return d;
        }
        long j = (long) d;
        if (d < 0.0d && ((double) j) != d) {
            j--;
        }
        return j == 0 ? d * ((double) j) : (double) j;
    }

    public static int max(int i, int i2) {
        return i <= i2 ? i2 : i;
    }

    public static long max(long j, long j2) {
        return j <= j2 ? j2 : j;
    }

    public static int min(int i, int i2) {
        return i <= i2 ? i : i2;
    }

    public static long min(long j, long j2) {
        return j <= j2 ? j : j2;
    }

    private static double polyCosine(double d) {
        double d2 = d * d;
        return ((((((2.479773539153719E-5d * d2) - 3231.288930800263d) * d2) + 0.041666666666621166d) * d2) - 8.000000000000002d) * d2;
    }

    private static double polySine(double d) {
        double d2 = d * d;
        return ((((((2.7553817452272217E-6d * d2) - 22521.49865654966d) * d2) + 0.008333333333329196d) * d2) - 26.666666666666668d) * d2 * d;
    }

    public static double pow(double d, int i) {
        double d2;
        int i2 = i;
        double d3 = 1.0d;
        if (i2 == 0) {
            return 1.0d;
        }
        if (i2 < 0) {
            i2 = -i2;
            d2 = 1.0d / d;
        } else {
            d2 = d;
        }
        double d4 = 1.34217729E8d * d2;
        double d5 = d4 - (d4 - d2);
        double d6 = d2 - d5;
        double d7 = 0.0d;
        while (i2 != 0) {
            if ((i2 & 1) != 0) {
                double d8 = d3 * d2;
                double d9 = 1.34217729E8d * d3;
                double d10 = d9 - (d9 - d3);
                double d11 = d3 - d10;
                d7 = (d7 * d2) + ((d11 * d6) - (((d8 - (d10 * d5)) - (d11 * d5)) - (d10 * d6)));
                d3 = d8;
            }
            double d12 = d5 * d2;
            double d13 = 1.34217729E8d * d5;
            double d14 = d13 - (d13 - d5);
            double d15 = d5 - d14;
            double d16 = 1.34217729E8d * d12;
            d5 = d16 - (d16 - d12);
            d6 = (d6 * d2) + ((d15 * d6) - (((d12 - (d14 * d5)) - (d15 * d5)) - (d14 * d6))) + (d12 - d5);
            d2 = d5 + d6;
            i2 >>= 1;
        }
        return d3 + d7;
    }

    public static double signum(double d) {
        if (d < 0.0d) {
            return -1.0d;
        }
        if (d > 0.0d) {
            return 1.0d;
        }
        return d;
    }

    public static float signum(float f) {
        if (f < 0.0f) {
            return -1.0f;
        }
        if (f > 0.0f) {
            return 1.0f;
        }
        return f;
    }

    private FastMath() {
    }

    private static double doubleHighPart(double d) {
        if (d <= (-Precision.SAFE_MIN) || d >= Precision.SAFE_MIN) {
            return Double.longBitsToDouble(Double.doubleToLongBits(d) & MASK_30BITS);
        }
        return d;
    }

    public static double sqrt(double d) {
        return Math.sqrt(d);
    }

    public static double cosh(double d) {
        double d2 = d;
        if (d2 != d2) {
            return d2;
        }
        if (d2 > 20.0d) {
            if (d2 < LOG_MAX_VALUE) {
                return F_1_2 * exp(d);
            }
            double exp = exp(d2 * F_1_2);
            return F_1_2 * exp * exp;
        } else if (d2 >= -20.0d) {
            double[] dArr = new double[2];
            if (d2 < 0.0d) {
                d2 = -d2;
            }
            exp(d2, 0.0d, dArr);
            double d3 = dArr[0] + dArr[1];
            double d4 = -((d3 - dArr[0]) - dArr[1]);
            double d5 = d3 * 1.073741824E9d;
            double d6 = (d3 + d5) - d5;
            double d7 = d3 - d6;
            double d8 = 1.0d / d3;
            double d9 = 1.073741824E9d * d8;
            double d10 = (d8 + d9) - d9;
            double d11 = d8 - d10;
            double d12 = d11 + (((((1.0d - (d6 * d10)) - (d6 * d11)) - (d7 * d10)) - (d7 * d11)) * d8) + ((-d4) * d8 * d8);
            double d13 = d3 + d10;
            double d14 = d13 + d12;
            return (d14 + d4 + (-((d13 - d3) - d10)) + (-((d14 - d13) - d12))) * F_1_2;
        } else if (d2 > (-LOG_MAX_VALUE)) {
            return F_1_2 * exp(-d2);
        } else {
            double exp2 = exp(-0.5d * d2);
            return F_1_2 * exp2 * exp2;
        }
    }

    public static double sinh(double d) {
        boolean z;
        double d2;
        double d3 = d;
        if (d3 != d3) {
            return d3;
        }
        if (d3 > 20.0d) {
            if (d3 < LOG_MAX_VALUE) {
                return F_1_2 * exp(d);
            }
            double exp = exp(d3 * F_1_2);
            return F_1_2 * exp * exp;
        } else if (d3 < -20.0d) {
            if (d3 > (-LOG_MAX_VALUE)) {
                return -0.5d * exp(-d3);
            }
            double exp2 = exp(d3 * -0.5d);
            return -0.5d * exp2 * exp2;
        } else if (d3 == 0.0d) {
            return d3;
        } else {
            if (d3 < 0.0d) {
                d3 = -d3;
                z = true;
            } else {
                z = false;
            }
            if (d3 > F_1_4) {
                double[] dArr = new double[2];
                exp(d3, 0.0d, dArr);
                double d4 = dArr[0] + dArr[1];
                double d5 = -((d4 - dArr[0]) - dArr[1]);
                double d6 = d4 * 1.073741824E9d;
                double d7 = (d4 + d6) - d6;
                double d8 = d4 - d7;
                double d9 = 1.0d / d4;
                double d10 = 1.073741824E9d * d9;
                double d11 = (d9 + d10) - d10;
                double d12 = d9 - d11;
                double d13 = -d11;
                double d14 = -(d12 + (((((1.0d - (d7 * d11)) - (d7 * d12)) - (d8 * d11)) - (d8 * d12)) * d9) + ((-d5) * d9 * d9));
                double d15 = d4 + d13;
                double d16 = d5 + (-((d15 - d4) - d13));
                double d17 = d15 + d14;
                d2 = (d17 + d16 + (-((d17 - d15) - d14))) * F_1_2;
            } else {
                double[] dArr2 = new double[2];
                expm1(d3, dArr2);
                double d18 = dArr2[0] + dArr2[1];
                double d19 = -((d18 - dArr2[0]) - dArr2[1]);
                double d20 = 1.0d + d18;
                double d21 = 1.0d / d20;
                double d22 = d18 * d21;
                double d23 = d22 * 1.073741824E9d;
                double d24 = (d22 + d23) - d23;
                double d25 = d22 - d24;
                double d26 = 1.073741824E9d * d20;
                double d27 = (d20 + d26) - d26;
                double d28 = d20 - d27;
                double d29 = d25 + (((((d18 - (d27 * d24)) - (d27 * d25)) - (d28 * d24)) - (d28 * d25)) * d21) + (d19 * d21) + ((-d18) * ((-((d20 - 1.0d) - d18)) + d19) * d21 * d21);
                double d30 = d18 + d24;
                double d31 = d19 + (-((d30 - d18) - d24));
                double d32 = d30 + d29;
                d2 = (d32 + d31 + (-((d32 - d30) - d29))) * F_1_2;
            }
            if (z) {
                d2 = -d2;
            }
            return d2;
        }
    }

    public static double tanh(double d) {
        boolean z;
        double d2;
        double d3 = d;
        if (d3 != d3) {
            return d3;
        }
        if (d3 > 20.0d) {
            return 1.0d;
        }
        if (d3 < -20.0d) {
            return -1.0d;
        }
        if (d3 == 0.0d) {
            return d3;
        }
        if (d3 < 0.0d) {
            d3 = -d3;
            z = true;
        } else {
            z = false;
        }
        if (d3 >= F_1_2) {
            double[] dArr = new double[2];
            exp(d3 * 2.0d, 0.0d, dArr);
            double d4 = dArr[0] + dArr[1];
            double d5 = -((d4 - dArr[0]) - dArr[1]);
            double d6 = -1.0d + d4;
            double d7 = d6 + d5;
            double d8 = (-((d6 + 1.0d) - d4)) + (-((d7 - d6) - d5));
            double d9 = 1.0d + d4;
            double d10 = d9 + d5;
            double d11 = (-((d9 - 1.0d) - d4)) + (-((d10 - d9) - d5));
            double d12 = d10 * 1.073741824E9d;
            double d13 = (d10 + d12) - d12;
            double d14 = d10 - d13;
            double d15 = d7 / d10;
            double d16 = 1.073741824E9d * d15;
            double d17 = (d15 + d16) - d16;
            double d18 = d15 - d17;
            d2 = d17 + d18 + (((((d7 - (d13 * d17)) - (d13 * d18)) - (d14 * d17)) - (d14 * d18)) / d10) + (d8 / d10) + ((((-d11) * d7) / d10) / d10);
        } else {
            double[] dArr2 = new double[2];
            expm1(d3 * 2.0d, dArr2);
            double d19 = dArr2[0] + dArr2[1];
            double d20 = -((d19 - dArr2[0]) - dArr2[1]);
            double d21 = 2.0d + d19;
            double d22 = d21 + d20;
            double d23 = (-((d21 - 2.0d) - d19)) + (-((d22 - d21) - d20));
            double d24 = d22 * 1.073741824E9d;
            double d25 = (d22 + d24) - d24;
            double d26 = d22 - d25;
            double d27 = d19 / d22;
            double d28 = 1.073741824E9d * d27;
            double d29 = (d27 + d28) - d28;
            double d30 = d27 - d29;
            d2 = d29 + d30 + (((((d19 - (d25 * d29)) - (d25 * d30)) - (d26 * d29)) - (d26 * d30)) / d22) + (d20 / d22) + ((((-d23) * d19) / d22) / d22);
        }
        double d31 = d2;
        if (z) {
            d31 = -d31;
        }
        return d31;
    }

    public static double acosh(double d) {
        return log(d + sqrt((d * d) - 1.0d));
    }

    public static double asinh(double d) {
        boolean z;
        double d2;
        double d3 = d;
        if (d3 < 0.0d) {
            z = true;
            d3 = -d3;
        } else {
            z = false;
        }
        if (d3 > 0.167d) {
            d2 = log(sqrt((d3 * d3) + 1.0d) + d3);
        } else {
            double d4 = d3 * d3;
            d2 = d3 > 0.097d ? d3 * (1.0d - ((d4 * (F_1_3 - (((F_1_5 - (((F_1_7 - (((F_1_9 - (((F_1_11 - (((F_1_13 - (((F_1_15 - ((F_1_17 * d4) * F_15_16)) * d4) * F_13_14)) * d4) * F_11_12)) * d4) * F_9_10)) * d4) * F_7_8)) * d4) * F_5_6)) * d4) * 0.75d))) * F_1_2)) : d3 > 0.036d ? d3 * (1.0d - ((d4 * (F_1_3 - (((F_1_5 - (((F_1_7 - (((F_1_9 - (((F_1_11 - ((F_1_13 * d4) * F_11_12)) * d4) * F_9_10)) * d4) * F_7_8)) * d4) * F_5_6)) * d4) * 0.75d))) * F_1_2)) : d3 > 0.0036d ? d3 * (1.0d - ((d4 * (F_1_3 - (((F_1_5 - (((F_1_7 - ((F_1_9 * d4) * F_7_8)) * d4) * F_5_6)) * d4) * 0.75d))) * F_1_2)) : d3 * (1.0d - ((d4 * (F_1_3 - ((F_1_5 * d4) * 0.75d))) * F_1_2));
        }
        return z ? -d2 : d2;
    }

    public static double atanh(double d) {
        boolean z;
        double d2;
        double d3 = d;
        if (d3 < 0.0d) {
            z = true;
            d3 = -d3;
        } else {
            z = false;
        }
        if (d3 > 0.15d) {
            d2 = F_1_2 * log((1.0d + d3) / (1.0d - d3));
        } else {
            double d4 = d3 * d3;
            d2 = d3 > 0.087d ? d3 * (1.0d + (d4 * (F_1_3 + ((F_1_5 + ((F_1_7 + ((F_1_9 + ((F_1_11 + ((F_1_13 + ((F_1_15 + (F_1_17 * d4)) * d4)) * d4)) * d4)) * d4)) * d4)) * d4)))) : d3 > 0.031d ? d3 * (1.0d + (d4 * (F_1_3 + ((F_1_5 + ((F_1_7 + ((F_1_9 + ((F_1_11 + (F_1_13 * d4)) * d4)) * d4)) * d4)) * d4)))) : d3 > 0.003d ? d3 * (1.0d + (d4 * (F_1_3 + ((F_1_5 + ((F_1_7 + (F_1_9 * d4)) * d4)) * d4)))) : d3 * (1.0d + (d4 * (F_1_3 + (F_1_5 * d4))));
        }
        return z ? -d2 : d2;
    }

    public static double nextUp(double d) {
        return nextAfter(d, Double.POSITIVE_INFINITY);
    }

    public static float nextUp(float f) {
        return nextAfter(f, Double.POSITIVE_INFINITY);
    }

    public static double random() {
        return Math.random();
    }

    public static double exp(double d) {
        return exp(d, 0.0d, null);
    }

    private static double exp(double d, double d2, double[] dArr) {
        double d3;
        double d4;
        int i;
        double d5;
        double d6 = d;
        double d7 = d2;
        double[] dArr2 = dArr;
        if (d6 < 0.0d) {
            int i2 = (int) (-d6);
            if (i2 > 746) {
                if (dArr2 != null) {
                    dArr2[0] = 0.0d;
                    dArr2[1] = 0.0d;
                }
                return 0.0d;
            } else if (i2 > 709) {
                double exp = exp(d6 + 40.19140625d, d7, dArr2) / 2.85040095144011776E17d;
                if (dArr2 != null) {
                    dArr2[0] = dArr2[0] / 2.85040095144011776E17d;
                    dArr2[1] = dArr2[1] / 2.85040095144011776E17d;
                }
                return exp;
            } else if (i2 == 709) {
                double exp2 = exp(d6 + 1.494140625d, d7, dArr2) / 4.455505956692757d;
                if (dArr2 != null) {
                    dArr2[0] = dArr2[0] / 4.455505956692757d;
                    dArr2[1] = dArr2[1] / 4.455505956692757d;
                }
                return exp2;
            } else {
                int i3 = i2 + 1;
                int i4 = EXP_INT_TABLE_MAX_INDEX - i3;
                d3 = ExpIntTable.EXP_INT_TABLE_A[i4];
                d4 = ExpIntTable.EXP_INT_TABLE_B[i4];
                i = -i3;
            }
        } else {
            i = (int) d6;
            if (i > 709) {
                if (dArr2 != null) {
                    dArr2[0] = Double.POSITIVE_INFINITY;
                    dArr2[1] = 0.0d;
                }
                return Double.POSITIVE_INFINITY;
            }
            int i5 = EXP_INT_TABLE_MAX_INDEX + i;
            d3 = ExpIntTable.EXP_INT_TABLE_A[i5];
            d4 = ExpIntTable.EXP_INT_TABLE_B[i5];
        }
        double d8 = (double) i;
        int i6 = (int) ((d6 - d8) * 1024.0d);
        double d9 = ExpFracTable.EXP_FRAC_TABLE_A[i6];
        double d10 = ExpFracTable.EXP_FRAC_TABLE_B[i6];
        double d11 = d6 - (d8 + (((double) i6) / 1024.0d));
        double d12 = (((((((0.04168701738764507d * d11) + 0.1666666505023083d) * d11) + 0.5000000000042687d) * d11) + 1.0d) * d11) - 1.1409003175371524E20d;
        double d13 = d3 * d9;
        double d14 = (d3 * d10) + (d9 * d4) + (d4 * d10);
        double d15 = d14 + d13;
        if (d7 != 0.0d) {
            double d16 = d15 * d7;
            d5 = (d16 * d12) + d16 + (d15 * d12) + d14 + d13;
        } else {
            d5 = (d15 * d12) + d14 + d13;
        }
        if (dArr2 != null) {
            dArr2[0] = d13;
            double d17 = d15 * d7;
            dArr2[1] = (d17 * d12) + d17 + (d15 * d12) + d14;
        }
        return d5;
    }

    public static double expm1(double d) {
        return expm1(d, null);
    }

    private static double expm1(double d, double[] dArr) {
        boolean z;
        double d2 = d;
        if (d2 != d2 || d2 == 0.0d) {
            return d2;
        }
        if (d2 <= -1.0d || d2 >= 1.0d) {
            double[] dArr2 = new double[2];
            exp(d2, 0.0d, dArr2);
            if (d2 > 0.0d) {
                return -1.0d + dArr2[0] + dArr2[1];
            }
            double d3 = -1.0d + dArr2[0];
            return d3 + (-((1.0d + d3) - dArr2[0])) + dArr2[1];
        }
        if (d2 < 0.0d) {
            d2 = -d2;
            z = true;
        } else {
            z = false;
        }
        int i = (int) (d2 * 1024.0d);
        double d4 = ExpFracTable.EXP_FRAC_TABLE_A[i] - 1.0d;
        double d5 = ExpFracTable.EXP_FRAC_TABLE_B[i];
        double d6 = d4 + d5;
        double d7 = d6 * 1.073741824E9d;
        double d8 = (d6 + d7) - d7;
        double d9 = (-((d6 - d4) - d5)) + (d6 - d8);
        double d10 = d2 - (((double) i) / 1024.0d);
        double d11 = ((((((0.008336750013465571d * d10) + 0.041666663879186654d) * d10) + 0.16666666666745392d) * d10) + 0.49999999999999994d) * d10 * d10;
        double d12 = d10 + d11;
        double d13 = d12 * 1.073741824E9d;
        double d14 = (d12 + d13) - d13;
        double d15 = (-((d12 - d10) - d11)) + (d12 - d14);
        double d16 = d14 * d8;
        double d17 = d14 * d9;
        double d18 = d16 + d17;
        double d19 = d15 * d8;
        double d20 = d18 + d19;
        double d21 = (-((d18 - d16) - d17)) + (-((d20 - d18) - d19));
        double d22 = d15 * d9;
        double d23 = d20 + d22;
        double d24 = d21 + (-((d23 - d20) - d22));
        double d25 = d23 + d8;
        double d26 = d25 + d14;
        double d27 = d24 + (-((d25 - d8) - d23)) + (-((d26 - d25) - d14));
        double d28 = d26 + d9;
        double d29 = d27 + (-((d28 - d26) - d9));
        double d30 = d28 + d15;
        double d31 = d29 + (-((d30 - d28) - d15));
        if (z) {
            double d32 = 1.0d + d30;
            double d33 = 1.0d / d32;
            double d34 = (-((d32 - 1.0d) - d30)) + d31;
            double d35 = d30 * d33;
            double d36 = d35 * 1.073741824E9d;
            double d37 = (d35 + d36) - d36;
            double d38 = d35 - d37;
            double d39 = 1.073741824E9d * d32;
            double d40 = (d32 + d39) - d39;
            double d41 = d32 - d40;
            d30 = -d37;
            d31 = -(d38 + (((((d30 - (d40 * d37)) - (d40 * d38)) - (d41 * d37)) - (d41 * d38)) * d33) + (d31 * d33) + ((-d30) * d34 * d33 * d33));
        }
        if (dArr != null) {
            dArr[0] = d30;
            dArr[1] = d31;
        }
        return d30 + d31;
    }

    public static double log(double d) {
        return log(d, (double[]) null);
    }

    private static double log(double d, double[] dArr) {
        double d2;
        double d3;
        if (d == 0.0d) {
            return Double.NEGATIVE_INFINITY;
        }
        long doubleToLongBits = Double.doubleToLongBits(d);
        if (((doubleToLongBits & Long.MIN_VALUE) != 0 || d != d) && d != 0.0d) {
            if (dArr != null) {
                dArr[0] = Double.NaN;
            }
            return Double.NaN;
        } else if (d == Double.POSITIVE_INFINITY) {
            if (dArr != null) {
                dArr[0] = Double.POSITIVE_INFINITY;
            }
            return Double.POSITIVE_INFINITY;
        } else {
            int i = ((int) (doubleToLongBits >> 52)) - 1023;
            if ((doubleToLongBits & 9218868437227405312L) == 0) {
                if (d == 0.0d) {
                    if (dArr != null) {
                        dArr[0] = Double.NEGATIVE_INFINITY;
                    }
                    return Double.NEGATIVE_INFINITY;
                }
                doubleToLongBits <<= 1;
                while ((doubleToLongBits & 4503599627370496L) == 0) {
                    i--;
                    doubleToLongBits <<= 1;
                }
            }
            if ((i == -1 || i == 0) && d < 1.01d && d > 0.99d && dArr == null) {
                double d4 = d - 1.0d;
                double d5 = d4 * 1.073741824E9d;
                double d6 = (d4 + d5) - d5;
                double d7 = d4 - d6;
                double[] dArr2 = LN_QUICK_COEF[LN_QUICK_COEF.length - 1];
                double d8 = dArr2[0];
                double d9 = dArr2[1];
                for (int length = LN_QUICK_COEF.length - 2; length >= 0; length--) {
                    double d10 = d8 * d6;
                    double d11 = (d8 * d7) + (d9 * d6) + (d9 * d7);
                    double d12 = d10 * 1.073741824E9d;
                    double d13 = (d10 + d12) - d12;
                    double d14 = (d10 - d13) + d11;
                    double[] dArr3 = LN_QUICK_COEF[length];
                    double d15 = d13 + dArr3[0];
                    double d16 = d15 * 1.073741824E9d;
                    d8 = (d15 + d16) - d16;
                    d9 = (d15 - d8) + d14 + dArr3[1];
                }
                double d17 = d8 * d6;
                double d18 = 1.073741824E9d * d17;
                double d19 = (d17 + d18) - d18;
                return d19 + (d17 - d19) + (d8 * d7) + (d6 * d9) + (d9 * d7);
            }
            long j = doubleToLongBits & 4499201580859392L;
            double[] dArr4 = lnMant.LN_MANT[(int) (j >> 42)];
            double d20 = (double) (doubleToLongBits & 4398046511103L);
            double d21 = TWO_POWER_52 + ((double) j);
            double d22 = d20 / d21;
            if (dArr != null) {
                double d23 = d22 * 1.073741824E9d;
                double d24 = (d22 + d23) - d23;
                double d25 = d22 - d24;
                double d26 = d25 + (((d20 - (d24 * d21)) - (d25 * d21)) / d21);
                double[] dArr5 = LN_HI_PREC_COEF[LN_HI_PREC_COEF.length - 1];
                double d27 = dArr5[0];
                double d28 = dArr5[1];
                for (int length2 = LN_HI_PREC_COEF.length - 2; length2 >= 0; length2--) {
                    double d29 = d27 * d24;
                    double d30 = (d27 * d26) + (d28 * d24) + (d28 * d26);
                    double d31 = d29 * 1.073741824E9d;
                    double d32 = (d29 + d31) - d31;
                    double d33 = (d29 - d32) + d30;
                    double[] dArr6 = LN_HI_PREC_COEF[length2];
                    double d34 = d32 + dArr6[0];
                    double d35 = d34 * 1.073741824E9d;
                    d27 = (d34 + d35) - d35;
                    d28 = (d34 - d27) + d33 + dArr6[1];
                }
                double d36 = d27 * d24;
                double d37 = (d27 * d26) + (d24 * d28) + (d28 * d26);
                d3 = d36 + d37;
                d2 = -((d3 - d36) - d37);
            } else {
                d3 = d22 * ((((((((((-0.16624882440418567d * d22) + 0.19999954120254515d) * d22) - 16.00000002972804d) * d22) + 0.3333333333332802d) * d22) - 8.0d) * d22) + 1.0d);
                d2 = 0.0d;
            }
            double d38 = (double) i;
            double d39 = LN_2_A * d38;
            double d40 = dArr4[0] + d39;
            double d41 = d40 + d3;
            double d42 = 0.0d + (-((d40 - d39) - dArr4[0])) + (-((d41 - d40) - d3));
            double d43 = LN_2_B * d38;
            double d44 = d41 + d43;
            double d45 = d42 + (-((d44 - d41) - d43));
            double d46 = dArr4[1] + d44;
            double d47 = d45 + (-((d46 - d44) - dArr4[1]));
            double d48 = d46 + d2;
            double d49 = d47 + (-((d48 - d46) - d2));
            if (dArr != null) {
                dArr[0] = d48;
                dArr[1] = d49;
            }
            return d48 + d49;
        }
    }

    public static double log1p(double d) {
        if (d == -1.0d) {
            return Double.NEGATIVE_INFINITY;
        }
        if (d == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        }
        if (d <= 1.0E-6d && d >= -1.0E-6d) {
            return ((((F_1_3 * d) - F_1_2) * d) + 1.0d) * d;
        }
        double d2 = 1.0d + d;
        double d3 = -((d2 - 1.0d) - d);
        double[] dArr = new double[2];
        double log = log(d2, dArr);
        if (Double.isInfinite(log)) {
            return log;
        }
        double d4 = d3 / d2;
        return (((F_1_2 * d4) + 1.0d) * d4) + dArr[1] + dArr[0];
    }

    public static double log10(double d) {
        double[] dArr = new double[2];
        double log = log(d, dArr);
        if (Double.isInfinite(log)) {
            return log;
        }
        double d2 = dArr[0] * 1.073741824E9d;
        double d3 = (dArr[0] + d2) - d2;
        double d4 = (dArr[0] - d3) + dArr[1];
        return (1.9699272335463627E-8d * d4) + (1.9699272335463627E-8d * d3) + (d4 * 0.4342944622039795d) + (0.4342944622039795d * d3);
    }

    public static double log(double d, double d2) {
        return log(d2) / log(d);
    }

    public static double pow(double d, double d2) {
        double d3;
        double d4;
        double d5;
        double d6 = d;
        double d7 = d2;
        double[] dArr = new double[2];
        if (d7 == 0.0d) {
            return 1.0d;
        }
        if (d6 != d6) {
            return d6;
        }
        if (d6 == 0.0d) {
            if ((Double.doubleToLongBits(d) & Long.MIN_VALUE) != 0) {
                long j = (long) d7;
                if (d7 < 0.0d && d7 == ((double) j) && (j & 1) == 1) {
                    return Double.NEGATIVE_INFINITY;
                }
                if (d7 > 0.0d && d7 == ((double) j) && (j & 1) == 1) {
                    return -0.0d;
                }
            }
            if (d7 < 0.0d) {
                return Double.POSITIVE_INFINITY;
            }
            return d7 > 0.0d ? 0.0d : Double.NaN;
        } else if (d6 == Double.POSITIVE_INFINITY) {
            if (d7 != d7) {
                return d7;
            }
            return d7 < 0.0d ? 0.0d : Double.POSITIVE_INFINITY;
        } else if (d7 == Double.POSITIVE_INFINITY) {
            double d8 = d6 * d6;
            if (d8 == 1.0d) {
                return Double.NaN;
            }
            return d8 > 1.0d ? Double.POSITIVE_INFINITY : 0.0d;
        } else {
            if (d6 == Double.NEGATIVE_INFINITY) {
                if (d7 != d7) {
                    return d7;
                }
                if (d7 < 0.0d) {
                    long j2 = (long) d7;
                    return (d7 == ((double) j2) && (j2 & 1) == 1) ? -0.0d : 0.0d;
                } else if (d7 > 0.0d) {
                    long j3 = (long) d7;
                    return (d7 == ((double) j3) && (j3 & 1) == 1) ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
                }
            }
            if (d7 == Double.NEGATIVE_INFINITY) {
                double d9 = d6 * d6;
                if (d9 == 1.0d) {
                    return Double.NaN;
                }
                return d9 < 1.0d ? Double.POSITIVE_INFINITY : 0.0d;
            } else if (d6 >= 0.0d) {
                if (d7 >= 8.0E298d || d7 <= -8.0E298d) {
                    double d10 = d7 * 9.313225746154785E-10d;
                    d3 = (((9.313225746154785E-10d * d10) + d10) - d10) * 1.073741824E9d * 1.073741824E9d;
                    d4 = d7 - d3;
                } else {
                    double d11 = d7 * 1.073741824E9d;
                    d3 = (d7 + d11) - d11;
                    d4 = d7 - d3;
                }
                double log = log(d6, dArr);
                if (Double.isInfinite(log)) {
                    return log;
                }
                double d12 = dArr[0];
                double d13 = 1.073741824E9d * d12;
                double d14 = (d12 + d13) - d13;
                double d15 = dArr[1] + (d12 - d14);
                double d16 = d14 * d3;
                double d17 = (d14 * d4) + (d3 * d15) + (d15 * d4);
                double d18 = d16 + d17;
                double d19 = -((d18 - d16) - d17);
                return exp(d18, ((((((((0.008333333333333333d * d19) + 0.041666666666666664d) * d19) + 0.16666666666666666d) * d19) + F_1_2) * d19) + 1.0d) * d19, null);
            } else if (d7 >= TWO_POWER_53 || d7 <= -9.007199254740992E15d) {
                return pow(-d6, d7);
            } else {
                long j4 = (long) d7;
                if (d7 != ((double) j4)) {
                    return Double.NaN;
                }
                if ((j4 & 1) == 0) {
                    d5 = pow(-d6, d7);
                } else {
                    d5 = -pow(-d6, d7);
                }
                return d5;
            }
        }
    }

    private static double sinQ(double d, double d2) {
        double d3;
        int i = (int) ((8.0d * d) + F_1_2);
        double d4 = d - EIGHTHS[i];
        double d5 = SINE_TABLE_A[i];
        double d6 = SINE_TABLE_B[i];
        double d7 = COSINE_TABLE_A[i];
        double d8 = COSINE_TABLE_B[i];
        double polySine = polySine(d4);
        double polyCosine = polyCosine(d4);
        double d9 = 1.073741824E9d * d4;
        double d10 = (d4 + d9) - d9;
        double d11 = polySine + (d4 - d10);
        double d12 = 0.0d + d5;
        double d13 = d7 * d10;
        double d14 = d12 + d13;
        double d15 = (-((d12 - 0.0d) - d5)) + 0.0d + (-((d14 - d12) - d13)) + (d5 * polyCosine) + (d7 * d11) + d6 + (d8 * d10) + (d6 * polyCosine) + (d8 * d11);
        if (d2 != 0.0d) {
            double d16 = (((d7 + d8) * (1.0d + polyCosine)) - ((d5 + d6) * (d10 + d11))) * d2;
            d3 = d14 + d16;
            d15 += -((d3 - d14) - d16);
        } else {
            d3 = d14;
        }
        return d3 + d15;
    }

    private static double cosQ(double d, double d2) {
        double d3 = 1.5707963267948966d - d;
        return sinQ(d3, (-((d3 - 1.5707963267948966d) + d)) + (6.123233995736766E-17d - d2));
    }

    private static double tanQ(double d, double d2, boolean z) {
        int i = (int) ((8.0d * d) + F_1_2);
        double d3 = d - EIGHTHS[i];
        double d4 = SINE_TABLE_A[i];
        double d5 = SINE_TABLE_B[i];
        double d6 = COSINE_TABLE_A[i];
        double d7 = COSINE_TABLE_B[i];
        double polySine = polySine(d3);
        double polyCosine = polyCosine(d3);
        double d8 = d3 * 1.073741824E9d;
        double d9 = (d3 + d8) - d8;
        double d10 = polySine + (d3 - d9);
        double d11 = 0.0d + d4;
        double d12 = d6 * d9;
        double d13 = d11 + d12;
        double d14 = (-((d11 - 0.0d) - d4)) + 0.0d + (-((d13 - d11) - d12)) + (d4 * polyCosine) + (d6 * d10) + d5 + (d7 * d9) + (d5 * polyCosine) + (d7 * d10);
        double d15 = d13 + d14;
        double d16 = d6 * 1.0d;
        double d17 = 0.0d + d16;
        double d18 = d15;
        double d19 = -((d15 - d13) - d14);
        double d20 = (-d4) * d9;
        double d21 = d17 + d20;
        double d22 = (((((0.0d + (-((d17 - 0.0d) - d16))) + (-((d21 - d17) - d20))) + (1.0d * d7)) + (d6 * polyCosine)) + (d7 * polyCosine)) - (((d9 * d5) + (d4 * d10)) + (d5 * d10));
        double d23 = d21 + d22;
        double d24 = -((d23 - d21) - d22);
        if (!z) {
            double d25 = d24;
            d24 = d19;
            d19 = d25;
            double d26 = d23;
            d23 = d18;
            d18 = d26;
        }
        double d27 = d23 / d18;
        double d28 = d27 * 1.073741824E9d;
        double d29 = (d27 + d28) - d28;
        double d30 = d27 - d29;
        double d31 = 1.073741824E9d * d18;
        double d32 = (d18 + d31) - d31;
        double d33 = d18 - d32;
        double d34 = (((((d23 - (d29 * d32)) - (d29 * d33)) - (d32 * d30)) - (d30 * d33)) / d18) + (d24 / d18) + ((((-d23) * d19) / d18) / d18);
        if (d2 != 0.0d) {
            double d35 = d2 + (d27 * d27 * d2);
            if (z) {
                d35 = -d35;
            }
            d34 += d35;
        }
        return d27 + d34;
    }

    private static void reducePayneHanek(double d, double[] dArr) {
        long j;
        long j2;
        long j3;
        long doubleToLongBits = Double.doubleToLongBits(d);
        int i = (((int) ((doubleToLongBits >> 52) & 2047)) - 1023) + 1;
        long j4 = ((doubleToLongBits & 4503599627370495L) | 4503599627370496L) << 11;
        int i2 = i >> 6;
        int i3 = i - (i2 << 6);
        if (i3 != 0) {
            int i4 = 64 - i3;
            j2 = (i2 == 0 ? 0 : RECIP_2PI[i2 - 1] << i3) | (RECIP_2PI[i2] >>> i4);
            int i5 = i2 + 1;
            j = (RECIP_2PI[i2] << i3) | (RECIP_2PI[i5] >>> i4);
            j3 = (RECIP_2PI[i5] << i3) | (RECIP_2PI[i2 + 2] >>> i4);
        } else {
            if (i2 == 0) {
                j2 = 0;
            } else {
                j2 = RECIP_2PI[i2 - 1];
            }
            j = RECIP_2PI[i2];
            j3 = RECIP_2PI[i2 + 1];
        }
        long j5 = j4 >>> 32;
        long j6 = j4 & 4294967295L;
        long j7 = j >>> 32;
        long j8 = j & 4294967295L;
        long j9 = j5 * j7;
        long j10 = j6 * j8;
        long j11 = j7 * j6;
        long j12 = j8 * j5;
        long j13 = j10 + (j12 << 32);
        long j14 = j9 + (j12 >>> 32);
        boolean z = (j10 & Long.MIN_VALUE) != 0;
        boolean z2 = (j12 & 2147483648L) != 0;
        long j15 = j13 & Long.MIN_VALUE;
        boolean z3 = j15 != 0;
        if ((z && z2) || ((z || z2) && !z3)) {
            j14++;
        }
        boolean z4 = j15 != 0;
        boolean z5 = (j11 & 2147483648L) != 0;
        long j16 = j13 + (j11 << 32);
        long j17 = j14 + (j11 >>> 32);
        long j18 = j16 & Long.MIN_VALUE;
        long j19 = ((!z4 || !z5) && ((!z4 && !z5) || ((j18 > 0 ? 1 : (j18 == 0 ? 0 : -1)) != 0))) ? j17 : j17 + 1;
        long j20 = j3 >>> 32;
        long j21 = (j5 * j20) + (((j20 * j6) + ((j3 & 4294967295L) * j5)) >>> 32);
        boolean z6 = j18 != 0;
        boolean z7 = (j21 & Long.MIN_VALUE) != 0;
        long j22 = j16 + j21;
        long j23 = j2 & 4294967295L;
        long j24 = (((!z6 || !z7) && ((!z6 && !z7) || (((j22 & Long.MIN_VALUE) > 0 ? 1 : ((j22 & Long.MIN_VALUE) == 0 ? 0 : -1)) != 0))) ? j19 : j19 + 1) + (j6 * j23) + (((j6 * (j2 >>> 32)) + (j5 * j23)) << 32);
        int i6 = (int) (j24 >>> 62);
        long j25 = (j24 << 2) | (j22 >>> 62);
        long j26 = j22 << 2;
        long j27 = j25 >>> 32;
        long j28 = j25 & 4294967295L;
        long j29 = PI_O_4_BITS[0] >>> 32;
        long j30 = PI_O_4_BITS[0] & 4294967295L;
        long j31 = j27 * j29;
        long j32 = j28 * j30;
        long j33 = j29 * j28;
        long j34 = j30 * j27;
        long j35 = j32 + (j34 << 32);
        long j36 = j31 + (j34 >>> 32);
        boolean z8 = (j32 & Long.MIN_VALUE) != 0;
        boolean z9 = (j34 & 2147483648L) != 0;
        long j37 = j35 & Long.MIN_VALUE;
        boolean z10 = j37 != 0;
        if ((z8 && z9) || ((z8 || z9) && !z10)) {
            j36++;
        }
        boolean z11 = j37 != 0;
        boolean z12 = (j33 & 2147483648L) != 0;
        long j38 = j35 + (j33 << 32);
        long j39 = j36 + (j33 >>> 32);
        long j40 = j38 & Long.MIN_VALUE;
        boolean z13 = j40 != 0;
        if ((z11 && z12) || ((z11 || z12) && !z13)) {
            j39++;
        }
        long j41 = PI_O_4_BITS[1] >>> 32;
        long j42 = (j27 * j41) + (((j28 * j41) + (j27 * (PI_O_4_BITS[1] & 4294967295L))) >>> 32);
        boolean z14 = j40 != 0;
        boolean z15 = (j42 & Long.MIN_VALUE) != 0;
        long j43 = j38 + j42;
        long j44 = j43 & Long.MIN_VALUE;
        long j45 = ((!z14 || !z15) && ((!z14 && !z15) || ((j44 > 0 ? 1 : (j44 == 0 ? 0 : -1)) != 0))) ? j39 : j39 + 1;
        long j46 = j26 >>> 32;
        long j47 = j26 & 4294967295L;
        long j48 = PI_O_4_BITS[0] >>> 32;
        long j49 = (j46 * j48) + (((j47 * j48) + (j46 * (PI_O_4_BITS[0] & 4294967295L))) >>> 32);
        boolean z16 = j44 != 0;
        boolean z17 = (j49 & Long.MIN_VALUE) != 0;
        long j50 = j43 + j49;
        long j51 = ((!z16 || !z17) && ((!z16 && !z17) || (((j50 & Long.MIN_VALUE) > 0 ? 1 : ((j50 & Long.MIN_VALUE) == 0 ? 0 : -1)) != 0))) ? j45 : j45 + 1;
        double d2 = ((double) (j51 >>> 12)) / TWO_POWER_52;
        double d3 = (((double) (((j51 & 4095) << 40) + (j50 >>> 24))) / TWO_POWER_52) / TWO_POWER_52;
        double d4 = d2 + d3;
        double d5 = -((d4 - d2) - d3);
        dArr[0] = (double) i6;
        dArr[1] = d4 * 2.0d;
        dArr[2] = d5 * 2.0d;
    }

    public static double sin(double d) {
        double d2;
        boolean z;
        double d3 = 0.0d;
        int i = 0;
        if (d < 0.0d) {
            d2 = -d;
            z = true;
        } else {
            d2 = d;
            z = false;
        }
        if (d2 == 0.0d) {
            if (Double.doubleToLongBits(d) < 0) {
                return -0.0d;
            }
            return 0.0d;
        } else if (d2 != d2 || d2 == Double.POSITIVE_INFINITY) {
            return Double.NaN;
        } else {
            if (d2 > 3294198.0d) {
                double[] dArr = new double[3];
                reducePayneHanek(d2, dArr);
                i = ((int) dArr[0]) & 3;
                d2 = dArr[1];
                d3 = dArr[2];
            } else if (d2 > 1.5707963267948966d) {
                CodyWaite codyWaite = new CodyWaite(d2);
                i = codyWaite.getK() & 3;
                d2 = codyWaite.getRemA();
                d3 = codyWaite.getRemB();
            }
            if (z) {
                i ^= 2;
            }
            switch (i) {
                case 0:
                    return sinQ(d2, d3);
                case 1:
                    return cosQ(d2, d3);
                case 2:
                    return -sinQ(d2, d3);
                case 3:
                    return -cosQ(d2, d3);
                default:
                    return Double.NaN;
            }
        }
    }

    public static double cos(double d) {
        double d2 = 0.0d;
        if (d < 0.0d) {
            d = -d;
        }
        if (d != d || d == Double.POSITIVE_INFINITY) {
            return Double.NaN;
        }
        int i = 0;
        if (d > 3294198.0d) {
            double[] dArr = new double[3];
            reducePayneHanek(d, dArr);
            i = ((int) dArr[0]) & 3;
            d = dArr[1];
            d2 = dArr[2];
        } else if (d > 1.5707963267948966d) {
            CodyWaite codyWaite = new CodyWaite(d);
            i = codyWaite.getK() & 3;
            d = codyWaite.getRemA();
            d2 = codyWaite.getRemB();
        }
        switch (i) {
            case 0:
                return cosQ(d, d2);
            case 1:
                return -sinQ(d, d2);
            case 2:
                return -cosQ(d, d2);
            case 3:
                return sinQ(d, d2);
            default:
                return Double.NaN;
        }
    }

    public static double tan(double d) {
        double d2;
        boolean z;
        double d3;
        int i;
        double d4;
        if (d < 0.0d) {
            d2 = -d;
            z = true;
        } else {
            d2 = d;
            z = false;
        }
        if (d2 == 0.0d) {
            if (Double.doubleToLongBits(d) < 0) {
                return -0.0d;
            }
            return 0.0d;
        } else if (d2 != d2 || d2 == Double.POSITIVE_INFINITY) {
            return Double.NaN;
        } else {
            if (d2 > 3294198.0d) {
                double[] dArr = new double[3];
                reducePayneHanek(d2, dArr);
                i = ((int) dArr[0]) & 3;
                d2 = dArr[1];
                d3 = dArr[2];
            } else if (d2 > 1.5707963267948966d) {
                CodyWaite codyWaite = new CodyWaite(d2);
                i = codyWaite.getK() & 3;
                d2 = codyWaite.getRemA();
                d3 = codyWaite.getRemB();
            } else {
                d3 = 0.0d;
                i = 0;
            }
            if (d2 > 1.5d) {
                double d5 = 1.5707963267948966d - d2;
                double d6 = (-((d5 - 1.5707963267948966d) + d2)) + (6.123233995736766E-17d - d3);
                d2 = d5 + d6;
                d3 = -((d2 - d5) - d6);
                i ^= 1;
                z = !z;
            }
            if ((i & 1) == 0) {
                d4 = tanQ(d2, d3, false);
            } else {
                d4 = -tanQ(d2, d3, true);
            }
            if (z) {
                d4 = -d4;
            }
            return d4;
        }
    }

    public static double atan(double d) {
        return atan(d, 0.0d, false);
    }

    private static double atan(double d, double d2, boolean z) {
        boolean z2;
        double d3;
        int i;
        double d4;
        double d5;
        double d6 = d;
        if (d6 == 0.0d) {
            if (z) {
                d6 = copySign(3.141592653589793d, d6);
            }
            return d6;
        }
        if (d6 < 0.0d) {
            d6 = -d6;
            d3 = -d2;
            z2 = true;
        } else {
            d3 = d2;
            z2 = false;
        }
        if (d6 > 1.633123935319537E16d) {
            return z2 ^ z ? -1.5707963267948966d : 1.5707963267948966d;
        }
        if (d6 < 1.0d) {
            i = (int) ((((-1.7168146928204135d * d6 * d6) + 8.0d) * d6) + F_1_2);
        } else {
            double d7 = 1.0d / d6;
            i = (int) ((-(((-1.7168146928204135d * d7 * d7) + 8.0d) * d7)) + 13.07d);
        }
        double d8 = d6 - TANGENT_TABLE_A[i];
        double d9 = (-((d8 - d6) + TANGENT_TABLE_A[i])) + (d3 - TANGENT_TABLE_B[i]);
        double d10 = d8 + d9;
        double d11 = -((d10 - d8) - d9);
        double d12 = d6 * 1.073741824E9d;
        double d13 = (d6 + d12) - d12;
        double d14 = d3 + ((d6 + d3) - d13);
        if (i == 0) {
            double d15 = 1.0d / (1.0d + ((d13 + d14) * (TANGENT_TABLE_A[i] + TANGENT_TABLE_B[i])));
            d4 = d11 * d15;
            d5 = d10 * d15;
        } else {
            double d16 = TANGENT_TABLE_A[i] * d13;
            double d17 = 1.0d + d16;
            double d18 = (TANGENT_TABLE_A[i] * d14) + (d13 * TANGENT_TABLE_B[i]);
            double d19 = d17 + d18;
            double d20 = (-((d17 - 1.0d) - d16)) + (-((d19 - d17) - d18)) + (d14 * TANGENT_TABLE_B[i]);
            d5 = d10 / d19;
            double d21 = d5 * 1.073741824E9d;
            double d22 = (d5 + d21) - d21;
            double d23 = d5 - d22;
            double d24 = 1.073741824E9d * d19;
            double d25 = (d19 + d24) - d24;
            double d26 = d19 - d25;
            d4 = (((((d10 - (d22 * d25)) - (d22 * d26)) - (d25 * d23)) - (d23 * d26)) / d19) + ((((-d10) * d20) / d19) / d19) + (d11 / d19);
        }
        double d27 = d5 * d5;
        double d28 = ((((((((((0.07490822288864472d * d27) - 49.467131565131815d) * d27) + 0.11111095942313305d) * d27) - 29.714285776906472d) * d27) + 0.19999999999923582d) * d27) - 13.333333333333348d) * d27 * d5;
        double d29 = d5 + d28;
        double d30 = (-((d29 - d5) - d28)) + (d4 / (d27 + 1.0d));
        double d31 = EIGHTHS[i] + d29;
        double d32 = d31 + d30;
        double d33 = (-((d31 - EIGHTHS[i]) - d29)) + (-((d32 - d31) - d30));
        double d34 = d32 + d33;
        double d35 = -((d34 - d32) - d33);
        if (z) {
            double d36 = 3.141592653589793d - d34;
            d34 = (-((d36 - 3.141592653589793d) + d34)) + (1.2246467991473532E-16d - d35) + d36;
        }
        if (z2 ^ z) {
            d34 = -d34;
        }
        return d34;
    }

    public static double atan2(double d, double d2) {
        double d3 = d;
        if (d2 != d2 || d3 != d3) {
            return Double.NaN;
        }
        if (d3 == 0.0d) {
            double d4 = d2 * d3;
            double d5 = 1.0d / d2;
            double d6 = 1.0d / d3;
            if (d5 == 0.0d) {
                return d2 > 0.0d ? d3 : copySign(3.141592653589793d, d3);
            }
            if (d2 < 0.0d || d5 < 0.0d) {
                return (d3 < 0.0d || d6 < 0.0d) ? -3.141592653589793d : 3.141592653589793d;
            }
            return d4;
        } else if (d3 == Double.POSITIVE_INFINITY) {
            if (d2 == Double.POSITIVE_INFINITY) {
                return 0.7853981633974483d;
            }
            return d2 == Double.NEGATIVE_INFINITY ? 2.356194490192345d : 1.5707963267948966d;
        } else if (d3 != Double.NEGATIVE_INFINITY) {
            if (d2 == Double.POSITIVE_INFINITY) {
                if (d3 <= 0.0d) {
                    double d7 = 1.0d / d3;
                    if (d7 <= 0.0d) {
                        if (d3 < 0.0d || d7 < 0.0d) {
                            return -0.0d;
                        }
                    }
                }
                return 0.0d;
            }
            if (d2 == Double.NEGATIVE_INFINITY) {
                if (d3 <= 0.0d) {
                    double d8 = 1.0d / d3;
                    if (d8 <= 0.0d) {
                        if (d3 < 0.0d || d8 < 0.0d) {
                            return -3.141592653589793d;
                        }
                    }
                }
                return 3.141592653589793d;
            }
            if (d2 == 0.0d) {
                if (d3 <= 0.0d) {
                    double d9 = 1.0d / d3;
                    if (d9 <= 0.0d) {
                        if (d3 < 0.0d || d9 < 0.0d) {
                            return -1.5707963267948966d;
                        }
                    }
                }
                return 1.5707963267948966d;
            }
            double d10 = d3 / d2;
            boolean z = true;
            if (Double.isInfinite(d10)) {
                if (d2 >= 0.0d) {
                    z = false;
                }
                return atan(d10, 0.0d, z);
            }
            double doubleHighPart = doubleHighPart(d10);
            double d11 = d10 - doubleHighPart;
            double doubleHighPart2 = doubleHighPart(d2);
            double d12 = d2 - doubleHighPart2;
            double d13 = d11 + (((((d3 - (doubleHighPart * doubleHighPart2)) - (doubleHighPart * d12)) - (doubleHighPart2 * d11)) - (d12 * d11)) / d2);
            double d14 = doubleHighPart + d13;
            double d15 = -((d14 - doubleHighPart) - d13);
            if (d14 == 0.0d) {
                d14 = copySign(0.0d, d3);
            }
            if (d2 >= 0.0d) {
                z = false;
            }
            return atan(d14, d15, z);
        } else if (d2 == Double.POSITIVE_INFINITY) {
            return -0.7853981633974483d;
        } else {
            return d2 == Double.NEGATIVE_INFINITY ? -2.356194490192345d : -1.5707963267948966d;
        }
    }

    public static double asin(double d) {
        double d2 = d;
        if (d2 != d2 || d2 > 1.0d || d2 < -1.0d) {
            return Double.NaN;
        }
        if (d2 == 1.0d) {
            return 1.5707963267948966d;
        }
        if (d2 == -1.0d) {
            return -1.5707963267948966d;
        }
        if (d2 == 0.0d) {
            return d2;
        }
        double d3 = d2 * 1.073741824E9d;
        double d4 = (d2 + d3) - d3;
        double d5 = d2 - d4;
        double d6 = d4 * d4;
        double d7 = (d4 * d5 * 2.0d) + (d5 * d5);
        double d8 = -d6;
        double d9 = -d7;
        double d10 = 1.0d + d8;
        double d11 = d10 + d9;
        double d12 = (-((d10 - 1.0d) - d8)) + (-((d11 - d10) - d9));
        double sqrt = sqrt(d11);
        double d13 = sqrt * 1.073741824E9d;
        double d14 = (sqrt + d13) - d13;
        double d15 = sqrt - d14;
        double d16 = 2.0d * sqrt;
        double d17 = d15 + ((((d11 - (d14 * d14)) - ((2.0d * d14) * d15)) - (d15 * d15)) / d16);
        double d18 = d2 / sqrt;
        double d19 = 1.073741824E9d * d18;
        double d20 = (d18 + d19) - d19;
        double d21 = d18 - d20;
        double d22 = d21 + (((((d2 - (d20 * d14)) - (d20 * d17)) - (d14 * d21)) - (d17 * d21)) / sqrt) + ((((-d2) * (d12 / d16)) / sqrt) / sqrt);
        double d23 = d20 + d22;
        return atan(d23, -((d23 - d20) - d22), false);
    }

    public static double acos(double d) {
        if (d != d || d > 1.0d || d < -1.0d) {
            return Double.NaN;
        }
        if (d == -1.0d) {
            return 3.141592653589793d;
        }
        if (d == 1.0d) {
            return 0.0d;
        }
        if (d == 0.0d) {
            return 1.5707963267948966d;
        }
        double d2 = d * 1.073741824E9d;
        double d3 = (d + d2) - d2;
        double d4 = d - d3;
        double d5 = -(d3 * d3);
        double d6 = -((d3 * d4 * 2.0d) + (d4 * d4));
        double d7 = 1.0d + d5;
        double d8 = d7 + d6;
        double d9 = (-((d7 - 1.0d) - d5)) + (-((d8 - d7) - d6));
        double sqrt = sqrt(d8);
        double d10 = 1.073741824E9d * sqrt;
        double d11 = (sqrt + d10) - d10;
        double d12 = sqrt - d11;
        double d13 = 2.0d * sqrt;
        double d14 = d12 + ((((d8 - (d11 * d11)) - ((2.0d * d11) * d12)) - (d12 * d12)) / d13) + (d9 / d13);
        double d15 = d11 + d14;
        double d16 = -((d15 - d11) - d14);
        double d17 = d15 / d;
        if (Double.isInfinite(d17)) {
            return 1.5707963267948966d;
        }
        double doubleHighPart = doubleHighPart(d17);
        double d18 = d17 - doubleHighPart;
        double d19 = d18 + (((((d15 - (doubleHighPart * d3)) - (doubleHighPart * d4)) - (d3 * d18)) - (d4 * d18)) / d) + (d16 / d);
        double d20 = doubleHighPart + d19;
        return atan(d20, -((d20 - doubleHighPart) - d19), d < 0.0d);
    }

    public static double cbrt(double d) {
        long j;
        int i;
        boolean z;
        double d2;
        long doubleToLongBits = Double.doubleToLongBits(d);
        int i2 = ((int) ((doubleToLongBits >> 52) & 2047)) - 1023;
        if (i2 != -1023) {
            d2 = d;
            long j2 = doubleToLongBits;
            i = i2;
            z = false;
            j = j2;
        } else if (d == 0.0d) {
            return d;
        } else {
            z = true;
            d2 = d * 1.8014398509481984E16d;
            j = Double.doubleToLongBits(d2);
            i = ((int) ((j >> 52) & 2047)) - 1023;
        }
        if (i == 1024) {
            return d2;
        }
        double longBitsToDouble = Double.longBitsToDouble((j & Long.MIN_VALUE) | (((long) (((i / 3) + 1023) & 2047)) << 52));
        double longBitsToDouble2 = Double.longBitsToDouble((j & 4503599627370495L) | 4607182418800017408L);
        double d3 = ((((((((-0.010714690733195933d * longBitsToDouble2) + 0.0875862700108075d) * longBitsToDouble2) - 14.214349574856733d) * longBitsToDouble2) + 0.7249995199969751d) * longBitsToDouble2) + 0.5039018405998233d) * CBRTTWO[(i % 3) + 2];
        double d4 = d2 / ((longBitsToDouble * longBitsToDouble) * longBitsToDouble);
        double d5 = d3 + ((d4 - ((d3 * d3) * d3)) / ((3.0d * d3) * d3));
        double d6 = d5 + ((d4 - ((d5 * d5) * d5)) / ((3.0d * d5) * d5));
        double d7 = d6 * 1.073741824E9d;
        double d8 = (d6 + d7) - d7;
        double d9 = d6 - d8;
        double d10 = d8 * d8;
        double d11 = 1.073741824E9d * d10;
        double d12 = (d10 + d11) - d11;
        double d13 = (d8 * d9 * 2.0d) + (d9 * d9) + (d10 - d12);
        double d14 = d12 * d8;
        double d15 = d4 - d14;
        double d16 = (d6 + ((d15 + ((-((d15 - d4) + d14)) - (((d12 * d9) + (d8 * d13)) + (d13 * d9)))) / ((3.0d * d6) * d6))) * longBitsToDouble;
        if (z) {
            d16 *= 3.814697265625E-6d;
        }
        return d16;
    }

    public static double toRadians(double d) {
        if (Double.isInfinite(d) || d == 0.0d) {
            return d;
        }
        double doubleHighPart = doubleHighPart(d);
        double d2 = d - doubleHighPart;
        double d3 = (d2 * 1.997844754509471E-9d) + (d2 * 0.01745329052209854d) + (1.997844754509471E-9d * doubleHighPart) + (doubleHighPart * 0.01745329052209854d);
        if (d3 == 0.0d) {
            d3 *= d;
        }
        return d3;
    }

    public static double toDegrees(double d) {
        if (Double.isInfinite(d) || d == 0.0d) {
            return d;
        }
        double doubleHighPart = doubleHighPart(d);
        double d2 = d - doubleHighPart;
        return (d2 * 3.145894820876798E-6d) + (d2 * 57.2957763671875d) + (3.145894820876798E-6d * doubleHighPart) + (doubleHighPart * 57.2957763671875d);
    }

    public static double ulp(double d) {
        if (Double.isInfinite(d)) {
            return Double.POSITIVE_INFINITY;
        }
        return abs(d - Double.longBitsToDouble(Double.doubleToLongBits(d) ^ 1));
    }

    public static float ulp(float f) {
        if (Float.isInfinite(f)) {
            return Float.POSITIVE_INFINITY;
        }
        return abs(f - Float.intBitsToFloat(Float.floatToIntBits(f) ^ 1));
    }

    public static double scalb(double d, int i) {
        int i2 = i;
        if (i2 > -1023 && i2 < 1024) {
            return d * Double.longBitsToDouble(((long) (i2 + 1023)) << 52);
        }
        if (!Double.isNaN(d) && !Double.isInfinite(d)) {
            double d2 = 0.0d;
            if (d != 0.0d) {
                if (i2 < -2098) {
                    if (d <= 0.0d) {
                        d2 = -0.0d;
                    }
                    return d2;
                }
                double d3 = Double.POSITIVE_INFINITY;
                if (i2 > 2097) {
                    if (d <= 0.0d) {
                        d3 = Double.NEGATIVE_INFINITY;
                    }
                    return d3;
                }
                long doubleToLongBits = Double.doubleToLongBits(d);
                long j = doubleToLongBits & Long.MIN_VALUE;
                int i3 = ((int) (doubleToLongBits >>> 52)) & 2047;
                long j2 = doubleToLongBits & 4503599627370495L;
                int i4 = i3 + i2;
                if (i2 < 0) {
                    if (i4 > 0) {
                        return Double.longBitsToDouble(j | (((long) i4) << 52) | j2);
                    }
                    if (i4 > -53) {
                        long j3 = j2 | 4503599627370496L;
                        long j4 = j3 >>> (1 - i4);
                        if ((j3 & (1 << (-i4))) != 0) {
                            j4++;
                        }
                        return Double.longBitsToDouble(j | j4);
                    }
                    if (j != 0) {
                        d2 = -0.0d;
                    }
                    return d2;
                } else if (i3 == 0) {
                    while ((j2 >>> 52) != 1) {
                        j2 <<= 1;
                        i4--;
                    }
                    int i5 = i4 + 1;
                    long j5 = j2 & 4503599627370495L;
                    if (i5 < 2047) {
                        return Double.longBitsToDouble(j | (((long) i5) << 52) | j5);
                    }
                    if (j != 0) {
                        d3 = Double.NEGATIVE_INFINITY;
                    }
                    return d3;
                } else if (i4 < 2047) {
                    return Double.longBitsToDouble(j | (((long) i4) << 52) | j2);
                } else {
                    if (j != 0) {
                        d3 = Double.NEGATIVE_INFINITY;
                    }
                    return d3;
                }
            }
        }
        return d;
    }

    public static float scalb(float f, int i) {
        if (i > -127 && i < 128) {
            return f * Float.intBitsToFloat((i + 127) << 23);
        }
        if (!Float.isNaN(f) && !Float.isInfinite(f)) {
            float f2 = 0.0f;
            if (f != 0.0f) {
                if (i < -277) {
                    if (f <= 0.0f) {
                        f2 = -0.0f;
                    }
                    return f2;
                }
                float f3 = Float.NEGATIVE_INFINITY;
                if (i > 276) {
                    if (f > 0.0f) {
                        f3 = Float.POSITIVE_INFINITY;
                    }
                    return f3;
                }
                int floatToIntBits = Float.floatToIntBits(f);
                int i2 = Integer.MIN_VALUE & floatToIntBits;
                int i3 = (floatToIntBits >>> 23) & 255;
                int i4 = floatToIntBits & 8388607;
                int i5 = i3 + i;
                if (i < 0) {
                    if (i5 > 0) {
                        return Float.intBitsToFloat(i4 | (i5 << 23) | i2);
                    }
                    if (i5 > -24) {
                        int i6 = i4 | 8388608;
                        int i7 = (1 << (-i5)) & i6;
                        int i8 = i6 >>> (1 - i5);
                        if (i7 != 0) {
                            i8++;
                        }
                        return Float.intBitsToFloat(i8 | i2);
                    }
                    if (i2 != 0) {
                        f2 = -0.0f;
                    }
                    return f2;
                } else if (i3 == 0) {
                    while ((i4 >>> 23) != 1) {
                        i4 <<= 1;
                        i5--;
                    }
                    int i9 = i5 + 1;
                    int i10 = i4 & 8388607;
                    if (i9 < 255) {
                        return Float.intBitsToFloat(i10 | (i9 << 23) | i2);
                    }
                    if (i2 == 0) {
                        f3 = Float.POSITIVE_INFINITY;
                    }
                    return f3;
                } else if (i5 < 255) {
                    return Float.intBitsToFloat(i4 | (i5 << 23) | i2);
                } else {
                    if (i2 == 0) {
                        f3 = Float.POSITIVE_INFINITY;
                    }
                    return f3;
                }
            }
        }
        return f;
    }

    public static double nextAfter(double d, double d2) {
        if (Double.isNaN(d) || Double.isNaN(d2)) {
            return Double.NaN;
        }
        if (d == d2) {
            return d2;
        }
        if (Double.isInfinite(d)) {
            return d < 0.0d ? -1.7976931348623157E308d : Double.MAX_VALUE;
        } else if (d == 0.0d) {
            return d2 < 0.0d ? -4.9E-324d : Double.MIN_VALUE;
        } else {
            long doubleToLongBits = Double.doubleToLongBits(d);
            long j = doubleToLongBits & Long.MIN_VALUE;
            int i = (d2 > d ? 1 : (d2 == d ? 0 : -1));
            boolean z = false;
            boolean z2 = i < 0;
            if (j == 0) {
                z = true;
            }
            if (z ^ z2) {
                return Double.longBitsToDouble(j | ((doubleToLongBits & Long.MAX_VALUE) + 1));
            }
            return Double.longBitsToDouble(j | ((doubleToLongBits & Long.MAX_VALUE) - 1));
        }
    }

    public static float nextAfter(float f, double d) {
        double d2 = (double) f;
        if (Double.isNaN(d2) || Double.isNaN(d)) {
            return Float.NaN;
        }
        if (d2 == d) {
            return (float) d;
        }
        if (Float.isInfinite(f)) {
            return f < 0.0f ? -3.4028235E38f : Float.MAX_VALUE;
        } else if (f == 0.0f) {
            return d < 0.0d ? -1.4E-45f : Float.MIN_VALUE;
        } else {
            int floatToIntBits = Float.floatToIntBits(f);
            int i = Integer.MIN_VALUE & floatToIntBits;
            int i2 = (d > d2 ? 1 : (d == d2 ? 0 : -1));
            boolean z = false;
            boolean z2 = i2 < 0;
            if (i == 0) {
                z = true;
            }
            if (z ^ z2) {
                return Float.intBitsToFloat(((floatToIntBits & Integer.MAX_VALUE) + 1) | i);
            }
            return Float.intBitsToFloat(((floatToIntBits & Integer.MAX_VALUE) - 1) | i);
        }
    }

    public static double ceil(double d) {
        if (d != d) {
            return d;
        }
        double floor = floor(d);
        if (floor == d) {
            return floor;
        }
        double d2 = floor + 1.0d;
        return d2 == 0.0d ? d * d2 : d2;
    }

    public static double rint(double d) {
        double floor = floor(d);
        double d2 = d - floor;
        if (d2 > F_1_2) {
            if (floor == -1.0d) {
                return -0.0d;
            }
            return floor + 1.0d;
        } else if (d2 < F_1_2) {
            return floor;
        } else {
            if ((((long) floor) & 1) != 0) {
                floor += 1.0d;
            }
            return floor;
        }
    }

    public static long round(double d) {
        return (long) floor(d + F_1_2);
    }

    public static int round(float f) {
        return (int) floor((double) (f + 0.5f));
    }

    public static float min(float f, float f2) {
        if (f > f2) {
            return f2;
        }
        if (f < f2) {
            return f;
        }
        if (f != f2) {
            return Float.NaN;
        }
        return Float.floatToRawIntBits(f) == Integer.MIN_VALUE ? f : f2;
    }

    public static double min(double d, double d2) {
        if (d > d2) {
            return d2;
        }
        if (d < d2) {
            return d;
        }
        if (d != d2) {
            return Double.NaN;
        }
        return Double.doubleToRawLongBits(d) == Long.MIN_VALUE ? d : d2;
    }

    public static float max(float f, float f2) {
        if (f > f2) {
            return f;
        }
        if (f < f2) {
            return f2;
        }
        if (f != f2) {
            return Float.NaN;
        }
        return Float.floatToRawIntBits(f) == Integer.MIN_VALUE ? f2 : f;
    }

    public static double max(double d, double d2) {
        if (d > d2) {
            return d;
        }
        if (d < d2) {
            return d2;
        }
        if (d != d2) {
            return Double.NaN;
        }
        return Double.doubleToRawLongBits(d) == Long.MIN_VALUE ? d2 : d;
    }

    public static double hypot(double d, double d2) {
        if (Double.isInfinite(d) || Double.isInfinite(d2)) {
            return Double.POSITIVE_INFINITY;
        }
        if (Double.isNaN(d) || Double.isNaN(d2)) {
            return Double.NaN;
        }
        int exponent = getExponent(d);
        int exponent2 = getExponent(d2);
        if (exponent > exponent2 + 27) {
            return abs(d);
        }
        if (exponent2 > exponent + 27) {
            return abs(d2);
        }
        int i = (exponent + exponent2) / 2;
        int i2 = -i;
        double scalb = scalb(d, i2);
        double scalb2 = scalb(d2, i2);
        return scalb(sqrt((scalb * scalb) + (scalb2 * scalb2)), i);
    }

    public static double IEEEremainder(double d, double d2) {
        return StrictMath.IEEEremainder(d, d2);
    }

    public static double copySign(double d, double d2) {
        long doubleToLongBits = Double.doubleToLongBits(d);
        long doubleToLongBits2 = Double.doubleToLongBits(d2);
        return ((doubleToLongBits < 0 || doubleToLongBits2 < 0) && (doubleToLongBits >= 0 || doubleToLongBits2 >= 0)) ? -d : d;
    }

    public static float copySign(float f, float f2) {
        int floatToIntBits = Float.floatToIntBits(f);
        int floatToIntBits2 = Float.floatToIntBits(f2);
        return ((floatToIntBits < 0 || floatToIntBits2 < 0) && (floatToIntBits >= 0 || floatToIntBits2 >= 0)) ? -f : f;
    }

    public static int getExponent(double d) {
        return ((int) ((Double.doubleToLongBits(d) >>> 52) & 2047)) - 1023;
    }

    public static int getExponent(float f) {
        return ((Float.floatToIntBits(f) >>> 23) & 255) - 127;
    }

    public static void main(String[] strArr) {
        PrintStream printStream = System.out;
        FastMathCalc.printarray(printStream, "EXP_INT_TABLE_A", 1500, ExpIntTable.EXP_INT_TABLE_A);
        FastMathCalc.printarray(printStream, "EXP_INT_TABLE_B", 1500, ExpIntTable.EXP_INT_TABLE_B);
        FastMathCalc.printarray(printStream, "EXP_FRAC_TABLE_A", 1025, ExpFracTable.EXP_FRAC_TABLE_A);
        FastMathCalc.printarray(printStream, "EXP_FRAC_TABLE_B", 1025, ExpFracTable.EXP_FRAC_TABLE_B);
        FastMathCalc.printarray(printStream, "LN_MANT", 1024, lnMant.LN_MANT);
        FastMathCalc.printarray(printStream, "SINE_TABLE_A", 14, SINE_TABLE_A);
        FastMathCalc.printarray(printStream, "SINE_TABLE_B", 14, SINE_TABLE_B);
        FastMathCalc.printarray(printStream, "COSINE_TABLE_A", 14, COSINE_TABLE_A);
        FastMathCalc.printarray(printStream, "COSINE_TABLE_B", 14, COSINE_TABLE_B);
        FastMathCalc.printarray(printStream, "TANGENT_TABLE_A", 14, TANGENT_TABLE_A);
        FastMathCalc.printarray(printStream, "TANGENT_TABLE_B", 14, TANGENT_TABLE_B);
    }
}
