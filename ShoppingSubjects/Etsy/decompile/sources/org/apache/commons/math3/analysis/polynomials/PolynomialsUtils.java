package org.apache.commons.math3.analysis.polynomials;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.FastMath;

public class PolynomialsUtils {
    private static final List<BigFraction> CHEBYSHEV_COEFFICIENTS = new ArrayList();
    private static final List<BigFraction> HERMITE_COEFFICIENTS = new ArrayList();
    private static final Map<JacobiKey, List<BigFraction>> JACOBI_COEFFICIENTS = new HashMap();
    private static final List<BigFraction> LAGUERRE_COEFFICIENTS = new ArrayList();
    private static final List<BigFraction> LEGENDRE_COEFFICIENTS = new ArrayList();

    private static class JacobiKey {
        private final int v;
        private final int w;

        public JacobiKey(int i, int i2) {
            this.v = i;
            this.w = i2;
        }

        public int hashCode() {
            return (this.v << 16) ^ this.w;
        }

        public boolean equals(Object obj) {
            boolean z = false;
            if (obj == null || !(obj instanceof JacobiKey)) {
                return false;
            }
            JacobiKey jacobiKey = (JacobiKey) obj;
            if (this.v == jacobiKey.v && this.w == jacobiKey.w) {
                z = true;
            }
            return z;
        }
    }

    private interface RecurrenceCoefficientsGenerator {
        BigFraction[] generate(int i);
    }

    static {
        CHEBYSHEV_COEFFICIENTS.add(BigFraction.ONE);
        CHEBYSHEV_COEFFICIENTS.add(BigFraction.ZERO);
        CHEBYSHEV_COEFFICIENTS.add(BigFraction.ONE);
        HERMITE_COEFFICIENTS.add(BigFraction.ONE);
        HERMITE_COEFFICIENTS.add(BigFraction.ZERO);
        HERMITE_COEFFICIENTS.add(BigFraction.TWO);
        LAGUERRE_COEFFICIENTS.add(BigFraction.ONE);
        LAGUERRE_COEFFICIENTS.add(BigFraction.ONE);
        LAGUERRE_COEFFICIENTS.add(BigFraction.MINUS_ONE);
        LEGENDRE_COEFFICIENTS.add(BigFraction.ONE);
        LEGENDRE_COEFFICIENTS.add(BigFraction.ZERO);
        LEGENDRE_COEFFICIENTS.add(BigFraction.ONE);
    }

    private PolynomialsUtils() {
    }

    public static PolynomialFunction createChebyshevPolynomial(int i) {
        return buildPolynomial(i, CHEBYSHEV_COEFFICIENTS, new RecurrenceCoefficientsGenerator() {
            private final BigFraction[] coeffs = {BigFraction.ZERO, BigFraction.TWO, BigFraction.ONE};

            public BigFraction[] generate(int i) {
                return this.coeffs;
            }
        });
    }

    public static PolynomialFunction createHermitePolynomial(int i) {
        return buildPolynomial(i, HERMITE_COEFFICIENTS, new RecurrenceCoefficientsGenerator() {
            public BigFraction[] generate(int i) {
                return new BigFraction[]{BigFraction.ZERO, BigFraction.TWO, new BigFraction(i * 2)};
            }
        });
    }

    public static PolynomialFunction createLaguerrePolynomial(int i) {
        return buildPolynomial(i, LAGUERRE_COEFFICIENTS, new RecurrenceCoefficientsGenerator() {
            public BigFraction[] generate(int i) {
                int i2 = i + 1;
                return new BigFraction[]{new BigFraction((2 * i) + 1, i2), new BigFraction(-1, i2), new BigFraction(i, i2)};
            }
        });
    }

    public static PolynomialFunction createLegendrePolynomial(int i) {
        return buildPolynomial(i, LEGENDRE_COEFFICIENTS, new RecurrenceCoefficientsGenerator() {
            public BigFraction[] generate(int i) {
                int i2 = i + 1;
                return new BigFraction[]{BigFraction.ZERO, new BigFraction(i + i2, i2), new BigFraction(i, i2)};
            }
        });
    }

    public static PolynomialFunction createJacobiPolynomial(int i, final int i2, final int i3) {
        JacobiKey jacobiKey = new JacobiKey(i2, i3);
        if (!JACOBI_COEFFICIENTS.containsKey(jacobiKey)) {
            ArrayList arrayList = new ArrayList();
            JACOBI_COEFFICIENTS.put(jacobiKey, arrayList);
            arrayList.add(BigFraction.ONE);
            arrayList.add(new BigFraction(i2 - i3, 2));
            arrayList.add(new BigFraction(2 + i2 + i3, 2));
        }
        return buildPolynomial(i, (List) JACOBI_COEFFICIENTS.get(jacobiKey), new RecurrenceCoefficientsGenerator() {
            public BigFraction[] generate(int i) {
                int i2 = i + 1;
                int i3 = i2 + i2 + i3;
                int i4 = i3 + i2;
                int i5 = i4 - 1;
                int i6 = i4 - 2;
                int i7 = 2 * i2 * i3 * i6;
                return new BigFraction[]{new BigFraction(((i2 * i2) - (i3 * i3)) * i5, i7), new BigFraction(i5 * i4 * i6, i7), new BigFraction(((i2 + i2) - 1) * 2 * ((i2 + i3) - 1) * i4, i7)};
            }
        });
    }

    public static double[] shift(double[] dArr, double d) {
        int length = dArr.length;
        double[] dArr2 = new double[length];
        int[][] iArr = (int[][]) Array.newInstance(int.class, new int[]{length, length});
        for (int i = 0; i < length; i++) {
            for (int i2 = 0; i2 <= i; i2++) {
                iArr[i][i2] = (int) ArithmeticUtils.binomialCoefficient(i, i2);
            }
        }
        for (int i3 = 0; i3 < length; i3++) {
            dArr2[0] = dArr2[0] + (dArr[i3] * FastMath.pow(d, i3));
        }
        int i4 = length - 1;
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i5;
            while (i6 < i4) {
                int i7 = i5 + 1;
                int i8 = i6 + 1;
                int i9 = i6 - i5;
                dArr2[i7] = dArr2[i7] + (((double) iArr[i8][i9]) * dArr[i8] * FastMath.pow(d, i9));
                i6 = i8;
            }
        }
        return dArr2;
    }

    private static PolynomialFunction buildPolynomial(int i, List<BigFraction> list, RecurrenceCoefficientsGenerator recurrenceCoefficientsGenerator) {
        int floor = ((int) FastMath.floor(FastMath.sqrt((double) (list.size() * 2)))) - 1;
        synchronized (PolynomialsUtils.class) {
            if (i > floor) {
                try {
                    computeUpToDegree(i, floor, recurrenceCoefficientsGenerator, list);
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
        }
        int i2 = i + 1;
        int i3 = (i * i2) / 2;
        double[] dArr = new double[i2];
        for (int i4 = 0; i4 <= i; i4++) {
            dArr[i4] = ((BigFraction) list.get(i3 + i4)).doubleValue();
        }
        return new PolynomialFunction(dArr);
    }

    private static void computeUpToDegree(int i, int i2, RecurrenceCoefficientsGenerator recurrenceCoefficientsGenerator, List<BigFraction> list) {
        int i3 = ((i2 - 1) * i2) / 2;
        while (i2 < i) {
            int i4 = i3 + i2;
            BigFraction[] generate = recurrenceCoefficientsGenerator.generate(i2);
            BigFraction bigFraction = (BigFraction) list.get(i4);
            list.add(bigFraction.multiply(generate[0]).subtract(((BigFraction) list.get(i3)).multiply(generate[2])));
            BigFraction bigFraction2 = bigFraction;
            int i5 = 1;
            while (i5 < i2) {
                BigFraction bigFraction3 = (BigFraction) list.get(i4 + i5);
                list.add(bigFraction3.multiply(generate[0]).add(bigFraction2.multiply(generate[1])).subtract(((BigFraction) list.get(i3 + i5)).multiply(generate[2])));
                i5++;
                bigFraction2 = bigFraction3;
            }
            BigFraction bigFraction4 = (BigFraction) list.get(i4 + i2);
            list.add(bigFraction4.multiply(generate[0]).add(bigFraction2.multiply(generate[1])));
            list.add(bigFraction4.multiply(generate[1]));
            i2++;
            i3 = i4;
        }
    }
}
