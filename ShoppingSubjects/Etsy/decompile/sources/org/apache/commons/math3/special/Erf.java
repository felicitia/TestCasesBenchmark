package org.apache.commons.math3.special;

import org.apache.commons.math3.util.FastMath;

public class Erf {
    private static final double X_CRIT = 0.4769362762044697d;

    private Erf() {
    }

    public static double erf(double d) {
        if (FastMath.abs(d) > 40.0d) {
            return d > 0.0d ? 1.0d : -1.0d;
        }
        double regularizedGammaP = Gamma.regularizedGammaP(0.5d, d * d, 1.0E-15d, 10000);
        if (d < 0.0d) {
            regularizedGammaP = -regularizedGammaP;
        }
        return regularizedGammaP;
    }

    public static double erfc(double d) {
        double d2 = 2.0d;
        if (FastMath.abs(d) > 40.0d) {
            if (d > 0.0d) {
                d2 = 0.0d;
            }
            return d2;
        }
        double regularizedGammaQ = Gamma.regularizedGammaQ(0.5d, d * d, 1.0E-15d, 10000);
        if (d < 0.0d) {
            regularizedGammaQ = 2.0d - regularizedGammaQ;
        }
        return regularizedGammaQ;
    }

    public static double erf(double d, double d2) {
        double d3;
        double erf;
        double erf2;
        if (d > d2) {
            return -erf(d2, d);
        }
        if (d < -0.4769362762044697d) {
            if (d2 < 0.0d) {
                erf = erfc(-d2);
                erf2 = erfc(-d);
            }
            erf = erf(d2);
            erf2 = erf(d);
        } else {
            if (d2 > X_CRIT && d > 0.0d) {
                d3 = erfc(d) - erfc(d2);
                return d3;
            }
            erf = erf(d2);
            erf2 = erf(d);
        }
        d3 = erf - erf2;
        return d3;
    }
}
