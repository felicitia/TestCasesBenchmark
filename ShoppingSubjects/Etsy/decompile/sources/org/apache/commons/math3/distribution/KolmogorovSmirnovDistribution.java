package org.apache.commons.math3.distribution;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.FieldMatrix;

public class KolmogorovSmirnovDistribution implements Serializable {
    private static final long serialVersionUID = -4670676796862967187L;
    private int n;

    public KolmogorovSmirnovDistribution(int i) throws NotStrictlyPositiveException {
        if (i <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NOT_POSITIVE_NUMBER_OF_SAMPLES, Integer.valueOf(i));
        }
        this.n = i;
    }

    public double cdf(double d) throws MathArithmeticException {
        return cdf(d, false);
    }

    public double cdfExact(double d) throws MathArithmeticException {
        return cdf(d, true);
    }

    public double cdf(double d, boolean z) throws MathArithmeticException {
        double d2 = 1.0d;
        double d3 = 1.0d / ((double) this.n);
        double d4 = 0.5d * d3;
        if (d <= d4) {
            return 0.0d;
        }
        if (d4 < d && d <= d3) {
            double d5 = (2.0d * d) - d3;
            for (int i = 1; i <= this.n; i++) {
                d2 *= ((double) i) * d5;
            }
            return d2;
        } else if (1.0d - d3 <= d && d < 1.0d) {
            return 1.0d - (2.0d * Math.pow(1.0d - d, (double) this.n));
        } else {
            if (1.0d <= d) {
                return 1.0d;
            }
            return z ? exactK(d) : roundedK(d);
        }
    }

    private double exactK(double d) throws MathArithmeticException {
        int ceil = (int) Math.ceil(((double) this.n) * d);
        int i = ceil - 1;
        BigFraction bigFraction = (BigFraction) createH(d).power(this.n).getEntry(i, i);
        for (int i2 = 1; i2 <= this.n; i2++) {
            bigFraction = bigFraction.multiply(i2).divide(this.n);
        }
        return bigFraction.bigDecimalValue(20, 4).doubleValue();
    }

    private double roundedK(double d) throws MathArithmeticException {
        int ceil = (int) Math.ceil(((double) this.n) * d);
        FieldMatrix createH = createH(d);
        int rowDimension = createH.getRowDimension();
        Array2DRowRealMatrix array2DRowRealMatrix = new Array2DRowRealMatrix(rowDimension, rowDimension);
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < rowDimension; i2++) {
                array2DRowRealMatrix.setEntry(i, i2, ((BigFraction) createH.getEntry(i, i2)).doubleValue());
            }
        }
        int i3 = ceil - 1;
        double entry = array2DRowRealMatrix.power(this.n).getEntry(i3, i3);
        for (int i4 = 1; i4 <= this.n; i4++) {
            entry *= ((double) i4) / ((double) this.n);
        }
        return entry;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(20:4|5|6|7|10|(4:12|(3:14|(2:16|46)(2:17|45)|18)|44|19)|43|20|(1:22)|47|23|(1:25)|48|26|(1:28)|29|(3:31|(2:32|(3:34|(3:36|(1:38)|52)(1:51)|39)(1:50))|40)|49|41|42) */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x004c, code lost:
        r11 = new org.apache.commons.math3.fraction.BigFraction(r1, 1.0E-5d, 10000);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x003d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.apache.commons.math3.linear.FieldMatrix<org.apache.commons.math3.fraction.BigFraction> createH(double r18) throws org.apache.commons.math3.exception.NumberIsTooLargeException, org.apache.commons.math3.fraction.FractionConversionException {
        /*
            r17 = this;
            r0 = r17
            int r3 = r0.n
            double r3 = (double) r3
            double r3 = r3 * r18
            double r3 = java.lang.Math.ceil(r3)
            int r3 = (int) r3
            r4 = 2
            int r5 = r4 * r3
            r6 = 1
            int r5 = r5 - r6
            double r7 = (double) r3
            int r3 = r0.n
            double r9 = (double) r3
            double r9 = r9 * r18
            double r1 = r7 - r9
            r7 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r3 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            r9 = 0
            if (r3 < 0) goto L_0x002e
            org.apache.commons.math3.exception.NumberIsTooLargeException r3 = new org.apache.commons.math3.exception.NumberIsTooLargeException
            java.lang.Double r1 = java.lang.Double.valueOf(r1)
            java.lang.Double r2 = java.lang.Double.valueOf(r7)
            r3.<init>(r1, r2, r9)
            throw r3
        L_0x002e:
            org.apache.commons.math3.fraction.BigFraction r3 = new org.apache.commons.math3.fraction.BigFraction     // Catch:{ FractionConversionException -> 0x003d }
            r14 = 4307583784117748259(0x3bc79ca10c924223, double:1.0E-20)
            r16 = 10000(0x2710, float:1.4013E-41)
            r11 = r3
            r12 = r1
            r11.<init>(r12, r14, r16)     // Catch:{ FractionConversionException -> 0x003d }
            goto L_0x005a
        L_0x003d:
            org.apache.commons.math3.fraction.BigFraction r3 = new org.apache.commons.math3.fraction.BigFraction     // Catch:{ FractionConversionException -> 0x004c }
            r14 = 4457293557087583675(0x3ddb7cdfd9d7bdbb, double:1.0E-10)
            r16 = 10000(0x2710, float:1.4013E-41)
            r11 = r3
            r12 = r1
            r11.<init>(r12, r14, r16)     // Catch:{ FractionConversionException -> 0x004c }
            goto L_0x005a
        L_0x004c:
            org.apache.commons.math3.fraction.BigFraction r3 = new org.apache.commons.math3.fraction.BigFraction
            r14 = 4532020583610935537(0x3ee4f8b588e368f1, double:1.0E-5)
            r16 = 10000(0x2710, float:1.4013E-41)
            r11 = r3
            r12 = r1
            r11.<init>(r12, r14, r16)
        L_0x005a:
            int[] r1 = new int[]{r5, r5}
            java.lang.Class<org.apache.commons.math3.fraction.BigFraction> r2 = org.apache.commons.math3.fraction.BigFraction.class
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r2, r1)
            org.apache.commons.math3.fraction.BigFraction[][] r1 = (org.apache.commons.math3.fraction.BigFraction[][]) r1
            r2 = r9
        L_0x0067:
            if (r2 >= r5) goto L_0x0084
            r7 = r9
        L_0x006a:
            if (r7 >= r5) goto L_0x0081
            int r8 = r2 - r7
            int r8 = r8 + r6
            if (r8 >= 0) goto L_0x0078
            r8 = r1[r2]
            org.apache.commons.math3.fraction.BigFraction r10 = org.apache.commons.math3.fraction.BigFraction.ZERO
            r8[r7] = r10
            goto L_0x007e
        L_0x0078:
            r8 = r1[r2]
            org.apache.commons.math3.fraction.BigFraction r10 = org.apache.commons.math3.fraction.BigFraction.ONE
            r8[r7] = r10
        L_0x007e:
            int r7 = r7 + 1
            goto L_0x006a
        L_0x0081:
            int r2 = r2 + 1
            goto L_0x0067
        L_0x0084:
            org.apache.commons.math3.fraction.BigFraction[] r2 = new org.apache.commons.math3.fraction.BigFraction[r5]
            r2[r9] = r3
            r7 = r6
        L_0x0089:
            if (r7 >= r5) goto L_0x0098
            int r8 = r7 + -1
            r8 = r2[r8]
            org.apache.commons.math3.fraction.BigFraction r8 = r3.multiply(r8)
            r2[r7] = r8
            int r7 = r7 + 1
            goto L_0x0089
        L_0x0098:
            r7 = r9
        L_0x0099:
            if (r7 >= r5) goto L_0x00bf
            r8 = r1[r7]
            r10 = r1[r7]
            r10 = r10[r9]
            r11 = r2[r7]
            org.apache.commons.math3.fraction.BigFraction r10 = r10.subtract(r11)
            r8[r9] = r10
            int r8 = r5 + -1
            r10 = r1[r8]
            r8 = r1[r8]
            r8 = r8[r7]
            int r11 = r5 - r7
            int r11 = r11 - r6
            r11 = r2[r11]
            org.apache.commons.math3.fraction.BigFraction r8 = r8.subtract(r11)
            r10[r7] = r8
            int r7 = r7 + 1
            goto L_0x0099
        L_0x00bf:
            org.apache.commons.math3.fraction.BigFraction r2 = org.apache.commons.math3.fraction.BigFraction.ONE_HALF
            int r2 = r3.compareTo(r2)
            if (r2 != r6) goto L_0x00e1
            int r2 = r5 + -1
            r7 = r1[r2]
            r2 = r1[r2]
            r2 = r2[r9]
            org.apache.commons.math3.fraction.BigFraction r3 = r3.multiply(r4)
            org.apache.commons.math3.fraction.BigFraction r3 = r3.subtract(r6)
            org.apache.commons.math3.fraction.BigFraction r3 = r3.pow(r5)
            org.apache.commons.math3.fraction.BigFraction r2 = r2.add(r3)
            r7[r9] = r2
        L_0x00e1:
            r2 = r9
        L_0x00e2:
            if (r2 >= r5) goto L_0x0105
            r3 = r9
        L_0x00e5:
            int r7 = r2 + 1
            if (r3 >= r7) goto L_0x0103
            int r7 = r2 - r3
            int r7 = r7 + r6
            if (r7 <= 0) goto L_0x0100
            r8 = r4
        L_0x00ef:
            if (r8 > r7) goto L_0x0100
            r10 = r1[r2]
            r11 = r1[r2]
            r11 = r11[r3]
            org.apache.commons.math3.fraction.BigFraction r11 = r11.divide(r8)
            r10[r3] = r11
            int r8 = r8 + 1
            goto L_0x00ef
        L_0x0100:
            int r3 = r3 + 1
            goto L_0x00e5
        L_0x0103:
            r2 = r7
            goto L_0x00e2
        L_0x0105:
            org.apache.commons.math3.linear.Array2DRowFieldMatrix r2 = new org.apache.commons.math3.linear.Array2DRowFieldMatrix
            org.apache.commons.math3.fraction.BigFractionField r3 = org.apache.commons.math3.fraction.BigFractionField.getInstance()
            r2.<init>(r3, (T[][]) r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH(double):org.apache.commons.math3.linear.FieldMatrix");
    }
}
