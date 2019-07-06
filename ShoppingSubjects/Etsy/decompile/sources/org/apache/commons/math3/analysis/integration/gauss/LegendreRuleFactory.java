package org.apache.commons.math3.analysis.integration.gauss;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.Pair;

public class LegendreRuleFactory extends BaseRuleFactory<Double> {
    /* access modifiers changed from: protected */
    public Pair<Double[], Double[]> computeRule(int i) throws NotStrictlyPositiveException {
        double d;
        double d2;
        int i2 = i;
        if (i2 <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_POINTS, Integer.valueOf(i));
        }
        int i3 = 1;
        if (i2 == 1) {
            return new Pair<>(new Double[]{Double.valueOf(0.0d)}, new Double[]{Double.valueOf(2.0d)});
        }
        Double[] dArr = (Double[]) getRuleInternal(i2 - 1).getFirst();
        Double[] dArr2 = new Double[i2];
        Double[] dArr3 = new Double[i2];
        int i4 = i2 / 2;
        int i5 = 0;
        while (i5 < i4) {
            if (i5 == 0) {
                d = -1.0d;
            } else {
                d = dArr[i5 - 1].doubleValue();
            }
            if (i4 == i3) {
                d2 = 1.0d;
            } else {
                d2 = dArr[i5].doubleValue();
            }
            int i6 = i3;
            double d3 = d;
            double d4 = 1.0d;
            while (i6 < i2) {
                int i7 = i6 + 1;
                i6 = i7;
                d4 = d3;
                d3 = (((((double) ((2 * i6) + 1)) * d) * d3) - (((double) i6) * d4)) / ((double) i7);
            }
            double d5 = (d + d2) * 0.5d;
            double d6 = d5;
            double d7 = d3;
            int i8 = 0;
            double d8 = 1.0d;
            while (i8 == 0) {
                i8 = d2 - d <= Math.ulp(d5) ? i3 : 0;
                int i9 = i3;
                d6 = d5;
                d8 = 1.0d;
                while (i9 < i2) {
                    i9++;
                    double d9 = (((((double) ((2 * i9) + i3)) * d5) * d6) - (((double) i9) * d8)) / ((double) i9);
                    d8 = d6;
                    dArr = dArr;
                    i3 = 1;
                    d6 = d9;
                }
                Double[] dArr4 = dArr;
                if (i8 == 0) {
                    if (d7 * d6 <= 0.0d) {
                        d2 = d5;
                    } else {
                        d = d5;
                        d7 = d6;
                    }
                    d5 = 0.5d * (d + d2);
                    dArr = dArr4;
                } else {
                    dArr = dArr4;
                }
                i3 = 1;
            }
            Double[] dArr5 = dArr;
            double d10 = ((double) i2) * (d8 - (d6 * d5));
            double d11 = ((1.0d - (d5 * d5)) * 2.0d) / (d10 * d10);
            dArr2[i5] = Double.valueOf(d5);
            dArr3[i5] = Double.valueOf(d11);
            int i10 = (i2 - i5) - 1;
            dArr2[i10] = Double.valueOf(-d5);
            dArr3[i10] = Double.valueOf(d11);
            i5++;
            i3 = 1;
        }
        double d12 = 1.0d;
        if (i2 % 2 != 0) {
            for (int i11 = i3; i11 < i2; i11 += 2) {
                d12 = (((double) (-i11)) * d12) / ((double) (i11 + 1));
            }
            double d13 = ((double) i2) * d12;
            double d14 = 2.0d / (d13 * d13);
            dArr2[i4] = Double.valueOf(0.0d);
            dArr3[i4] = Double.valueOf(d14);
        }
        return new Pair<>(dArr2, dArr3);
    }
}
