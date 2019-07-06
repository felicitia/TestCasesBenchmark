package org.apache.commons.math3.analysis.integration.gauss;

import com.etsy.android.lib.models.editable.EditableListing;
import java.math.BigDecimal;
import java.math.MathContext;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.Pair;

public class LegendreHighPrecisionRuleFactory extends BaseRuleFactory<BigDecimal> {
    private final MathContext mContext;
    private final BigDecimal minusOne;
    private final BigDecimal oneHalf;
    private final BigDecimal two;

    public LegendreHighPrecisionRuleFactory() {
        this(MathContext.DECIMAL128);
    }

    public LegendreHighPrecisionRuleFactory(MathContext mathContext) {
        this.mContext = mathContext;
        this.two = new BigDecimal("2", mathContext);
        this.minusOne = new BigDecimal(EditableListing.LISTING_ID_DEVICE_DRAFT, mathContext);
        this.oneHalf = new BigDecimal("0.5", mathContext);
    }

    /* access modifiers changed from: protected */
    public Pair<BigDecimal[], BigDecimal[]> computeRule(int i) {
        BigDecimal bigDecimal;
        int i2 = i;
        if (i2 <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_POINTS, Integer.valueOf(i));
        }
        int i3 = 1;
        if (i2 == 1) {
            return new Pair<>(new BigDecimal[]{BigDecimal.ZERO}, new BigDecimal[]{this.two});
        }
        BigDecimal[] bigDecimalArr = (BigDecimal[]) getRuleInternal(i2 - 1).getFirst();
        BigDecimal[] bigDecimalArr2 = new BigDecimal[i2];
        BigDecimal[] bigDecimalArr3 = new BigDecimal[i2];
        int i4 = i2 / 2;
        int i5 = 0;
        while (true) {
            int i6 = 2;
            if (i5 >= i4) {
                break;
            }
            BigDecimal bigDecimal2 = i5 == 0 ? this.minusOne : bigDecimalArr[i5 - 1];
            BigDecimal bigDecimal3 = i4 == i3 ? BigDecimal.ONE : bigDecimalArr[i5];
            BigDecimal bigDecimal4 = bigDecimal3;
            BigDecimal bigDecimal5 = BigDecimal.ONE;
            BigDecimal bigDecimal6 = BigDecimal.ONE;
            int i7 = i3;
            BigDecimal bigDecimal7 = bigDecimal2;
            while (i7 < i2) {
                BigDecimal[] bigDecimalArr4 = bigDecimalArr;
                BigDecimal bigDecimal8 = new BigDecimal((i6 * i7) + 1, this.mContext);
                BigDecimal bigDecimal9 = new BigDecimal(i7, this.mContext);
                int i8 = i7 + 1;
                int i9 = i4;
                BigDecimal bigDecimal10 = new BigDecimal(i8, this.mContext);
                int i10 = i8;
                BigDecimal divide = bigDecimal7.multiply(bigDecimal2.multiply(bigDecimal8, this.mContext), this.mContext).subtract(bigDecimal5.multiply(bigDecimal9, this.mContext), this.mContext).divide(bigDecimal10, this.mContext);
                BigDecimal divide2 = bigDecimal4.multiply(bigDecimal3.multiply(bigDecimal8, this.mContext), this.mContext).subtract(bigDecimal6.multiply(bigDecimal9, this.mContext), this.mContext).divide(bigDecimal10, this.mContext);
                bigDecimal5 = bigDecimal7;
                bigDecimalArr = bigDecimalArr4;
                i7 = i10;
                i6 = 2;
                bigDecimal7 = divide;
                i4 = i9;
                BigDecimal bigDecimal11 = bigDecimal4;
                bigDecimal4 = divide2;
                bigDecimal6 = bigDecimal11;
            }
            BigDecimal[] bigDecimalArr5 = bigDecimalArr;
            int i11 = i4;
            BigDecimal multiply = bigDecimal2.add(bigDecimal3, this.mContext).multiply(this.oneHalf, this.mContext);
            BigDecimal bigDecimal12 = BigDecimal.ONE;
            BigDecimal bigDecimal13 = multiply;
            boolean z = false;
            while (!z) {
                z = bigDecimal3.subtract(bigDecimal2, this.mContext).compareTo(bigDecimal13.ulp().multiply(BigDecimal.TEN, this.mContext)) <= 0;
                multiply = bigDecimal13;
                BigDecimal bigDecimal14 = BigDecimal.ONE;
                int i12 = 1;
                while (i12 < i2) {
                    BigDecimal bigDecimal15 = new BigDecimal((2 * i12) + 1, this.mContext);
                    BigDecimal bigDecimal16 = new BigDecimal(i12, this.mContext);
                    i12++;
                    bigDecimal2 = bigDecimal2;
                    BigDecimal divide3 = multiply.multiply(bigDecimal13.multiply(bigDecimal15, this.mContext), this.mContext).subtract(bigDecimal14.multiply(bigDecimal16, this.mContext), this.mContext).divide(new BigDecimal(i12, this.mContext), this.mContext);
                    bigDecimal14 = multiply;
                    multiply = divide3;
                }
                BigDecimal bigDecimal17 = bigDecimal2;
                if (!z) {
                    if (bigDecimal7.signum() * multiply.signum() <= 0) {
                        bigDecimal3 = bigDecimal13;
                        bigDecimal13 = bigDecimal17;
                    } else {
                        bigDecimal7 = multiply;
                    }
                    bigDecimal = bigDecimal13;
                    bigDecimal13 = bigDecimal13.add(bigDecimal3, this.mContext).multiply(this.oneHalf, this.mContext);
                } else {
                    bigDecimal = bigDecimal17;
                }
                bigDecimal12 = bigDecimal14;
            }
            BigDecimal divide4 = BigDecimal.ONE.subtract(bigDecimal13.pow(2, this.mContext), this.mContext).multiply(this.two, this.mContext).divide(bigDecimal12.subtract(bigDecimal13.multiply(multiply, this.mContext), this.mContext).multiply(new BigDecimal(i2, this.mContext)).pow(2, this.mContext), this.mContext);
            bigDecimalArr2[i5] = bigDecimal13;
            bigDecimalArr3[i5] = divide4;
            int i13 = (i2 - i5) - 1;
            bigDecimalArr2[i13] = bigDecimal13.negate(this.mContext);
            bigDecimalArr3[i13] = divide4;
            i5++;
            i3 = 1;
            bigDecimalArr = bigDecimalArr5;
            i4 = i11;
        }
        int i14 = i4;
        if (i2 % 2 != 0) {
            BigDecimal bigDecimal18 = BigDecimal.ONE;
            for (int i15 = i3; i15 < i2; i15 += 2) {
                bigDecimal18 = bigDecimal18.multiply(new BigDecimal(i15, this.mContext), this.mContext).divide(new BigDecimal(i15 + 1, this.mContext), this.mContext).negate(this.mContext);
            }
            BigDecimal divide5 = this.two.divide(bigDecimal18.multiply(new BigDecimal(i2, this.mContext), this.mContext).pow(2, this.mContext), this.mContext);
            bigDecimalArr2[i14] = BigDecimal.ZERO;
            bigDecimalArr3[i14] = divide5;
        }
        return new Pair<>(bigDecimalArr2, bigDecimalArr3);
    }
}
