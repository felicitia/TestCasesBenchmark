package org.apache.commons.math3.stat.descriptive;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public abstract class AbstractUnivariateStatistic implements UnivariateStatistic {
    private double[] storedData;

    public abstract UnivariateStatistic copy();

    public abstract double evaluate(double[] dArr, int i, int i2) throws MathIllegalArgumentException;

    public void setData(double[] dArr) {
        this.storedData = dArr == null ? null : (double[]) dArr.clone();
    }

    public double[] getData() {
        if (this.storedData == null) {
            return null;
        }
        return (double[]) this.storedData.clone();
    }

    /* access modifiers changed from: protected */
    public double[] getDataRef() {
        return this.storedData;
    }

    public void setData(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        if (dArr == null) {
            throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY, new Object[0]);
        } else if (i < 0) {
            throw new NotPositiveException(LocalizedFormats.START_POSITION, Integer.valueOf(i));
        } else if (i2 < 0) {
            throw new NotPositiveException(LocalizedFormats.LENGTH, Integer.valueOf(i2));
        } else {
            int i3 = i + i2;
            if (i3 > dArr.length) {
                throw new NumberIsTooLargeException(LocalizedFormats.SUBARRAY_ENDS_AFTER_ARRAY_END, Integer.valueOf(i3), Integer.valueOf(dArr.length), true);
            }
            this.storedData = new double[i2];
            System.arraycopy(dArr, i, this.storedData, 0, i2);
        }
    }

    public double evaluate() throws MathIllegalArgumentException {
        return evaluate(this.storedData);
    }

    public double evaluate(double[] dArr) throws MathIllegalArgumentException {
        test(dArr, 0, 0);
        return evaluate(dArr, 0, dArr.length);
    }

    /* access modifiers changed from: protected */
    public boolean test(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        return test(dArr, i, i2, false);
    }

    /* access modifiers changed from: protected */
    public boolean test(double[] dArr, int i, int i2, boolean z) throws MathIllegalArgumentException {
        if (dArr == null) {
            throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY, new Object[0]);
        } else if (i < 0) {
            throw new NotPositiveException(LocalizedFormats.START_POSITION, Integer.valueOf(i));
        } else if (i2 < 0) {
            throw new NotPositiveException(LocalizedFormats.LENGTH, Integer.valueOf(i2));
        } else {
            int i3 = i + i2;
            if (i3 > dArr.length) {
                throw new NumberIsTooLargeException(LocalizedFormats.SUBARRAY_ENDS_AFTER_ARRAY_END, Integer.valueOf(i3), Integer.valueOf(dArr.length), true);
            } else if (i2 != 0 || z) {
                return true;
            } else {
                return false;
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean test(double[] dArr, double[] dArr2, int i, int i2) throws MathIllegalArgumentException {
        return test(dArr, dArr2, i, i2, false);
    }

    /* access modifiers changed from: protected */
    public boolean test(double[] dArr, double[] dArr2, int i, int i2, boolean z) throws MathIllegalArgumentException {
        if (dArr2 == null || dArr == null) {
            throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY, new Object[0]);
        } else if (dArr2.length != dArr.length) {
            throw new DimensionMismatchException(dArr2.length, dArr.length);
        } else {
            int i3 = i;
            boolean z2 = false;
            while (i3 < i + i2) {
                if (Double.isNaN(dArr2[i3])) {
                    throw new MathIllegalArgumentException(LocalizedFormats.NAN_ELEMENT_AT_INDEX, Integer.valueOf(i3));
                } else if (Double.isInfinite(dArr2[i3])) {
                    throw new MathIllegalArgumentException(LocalizedFormats.INFINITE_ARRAY_ELEMENT, Double.valueOf(dArr2[i3]), Integer.valueOf(i3));
                } else if (dArr2[i3] < 0.0d) {
                    throw new MathIllegalArgumentException(LocalizedFormats.NEGATIVE_ELEMENT_AT_INDEX, Integer.valueOf(i3), Double.valueOf(dArr2[i3]));
                } else {
                    if (!z2 && dArr2[i3] > 0.0d) {
                        z2 = true;
                    }
                    i3++;
                }
            }
            if (z2) {
                return test(dArr, i, i2, z);
            }
            throw new MathIllegalArgumentException(LocalizedFormats.WEIGHT_AT_LEAST_ONE_NON_ZERO, new Object[0]);
        }
    }
}
