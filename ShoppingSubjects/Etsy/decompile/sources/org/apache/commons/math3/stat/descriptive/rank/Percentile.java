package org.apache.commons.math3.stat.descriptive.rank;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class Percentile extends AbstractUnivariateStatistic implements Serializable {
    private static final int MAX_CACHED_LEVELS = 10;
    private static final int MIN_SELECT_SIZE = 15;
    private static final long serialVersionUID = -8091216485095130416L;
    private int[] cachedPivots;
    private double quantile;

    public Percentile() {
        this(50.0d);
    }

    public Percentile(double d) throws MathIllegalArgumentException {
        this.quantile = 0.0d;
        setQuantile(d);
        this.cachedPivots = null;
    }

    public Percentile(Percentile percentile) throws NullArgumentException {
        this.quantile = 0.0d;
        copy(percentile, this);
    }

    public void setData(double[] dArr) {
        if (dArr == null) {
            this.cachedPivots = null;
        } else {
            this.cachedPivots = new int[1023];
            Arrays.fill(this.cachedPivots, -1);
        }
        super.setData(dArr);
    }

    public void setData(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        if (dArr == null) {
            this.cachedPivots = null;
        } else {
            this.cachedPivots = new int[1023];
            Arrays.fill(this.cachedPivots, -1);
        }
        super.setData(dArr, i, i2);
    }

    public double evaluate(double d) throws MathIllegalArgumentException {
        return evaluate(getDataRef(), d);
    }

    public double evaluate(double[] dArr, double d) throws MathIllegalArgumentException {
        test(dArr, 0, 0);
        return evaluate(dArr, 0, dArr.length, d);
    }

    public double evaluate(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        return evaluate(dArr, i, i2, this.quantile);
    }

    public double evaluate(double[] dArr, int i, int i2, double d) throws MathIllegalArgumentException {
        int[] iArr;
        double[] dArr2;
        test(dArr, i, i2);
        if (d > 100.0d || d <= 0.0d) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUNDS_QUANTILE_VALUE, Double.valueOf(d), Integer.valueOf(0), Integer.valueOf(100));
        } else if (i2 == 0) {
            return Double.NaN;
        } else {
            if (i2 == 1) {
                return dArr[i];
            }
            double d2 = (double) i2;
            double d3 = (d * (d2 + 1.0d)) / 100.0d;
            double floor = FastMath.floor(d3);
            int i3 = (int) floor;
            double d4 = d3 - floor;
            if (dArr == getDataRef()) {
                double[] dataRef = getDataRef();
                iArr = this.cachedPivots;
                dArr2 = dataRef;
            } else {
                dArr2 = new double[i2];
                System.arraycopy(dArr, i, dArr2, 0, i2);
                iArr = new int[1023];
                Arrays.fill(iArr, -1);
            }
            if (d3 < 1.0d) {
                return select(dArr2, iArr, 0);
            }
            if (d3 >= d2) {
                return select(dArr2, iArr, i2 - 1);
            }
            double select = select(dArr2, iArr, i3 - 1);
            return select + (d4 * (select(dArr2, iArr, i3) - select));
        }
    }

    private double select(double[] dArr, int[] iArr, int i) {
        int i2;
        int i3 = 0;
        int length = dArr.length;
        int i4 = 0;
        while (length - i3 > 15) {
            if (i4 >= iArr.length || iArr[i4] < 0) {
                i2 = partition(dArr, i3, length, medianOf3(dArr, i3, length));
                if (i4 < iArr.length) {
                    iArr[i4] = i2;
                }
            } else {
                i2 = iArr[i4];
            }
            if (i == i2) {
                return dArr[i];
            }
            if (i < i2) {
                i4 = FastMath.min((2 * i4) + 1, iArr.length);
                length = i2;
            } else {
                int i5 = i2 + 1;
                i4 = FastMath.min((i4 * 2) + 2, iArr.length);
                i3 = i5;
            }
        }
        insertionSort(dArr, i3, length);
        return dArr[i];
    }

    /* access modifiers changed from: 0000 */
    public int medianOf3(double[] dArr, int i, int i2) {
        int i3 = i2 - 1;
        int i4 = ((i3 - i) / 2) + i;
        double d = dArr[i];
        double d2 = dArr[i4];
        double d3 = dArr[i3];
        if (d < d2) {
            if (d2 < d3) {
                return i4;
            }
            if (d < d3) {
                i = i3;
            }
            return i;
        } else if (d < d3) {
            return i;
        } else {
            if (d2 >= d3) {
                i3 = i4;
            }
            return i3;
        }
    }

    private int partition(double[] dArr, int i, int i2, int i3) {
        double d = dArr[i3];
        dArr[i3] = dArr[i];
        int i4 = i + 1;
        int i5 = i2 - 1;
        while (i4 < i5) {
            while (i4 < i5 && dArr[i5] > d) {
                i5--;
            }
            while (i4 < i5 && dArr[i4] < d) {
                i4++;
            }
            if (i4 < i5) {
                double d2 = dArr[i4];
                int i6 = i4 + 1;
                dArr[i4] = dArr[i5];
                int i7 = i5 - 1;
                dArr[i5] = d2;
                i5 = i7;
                i4 = i6;
            }
        }
        if (i4 >= i2 || dArr[i4] > d) {
            i4--;
        }
        dArr[i] = dArr[i4];
        dArr[i4] = d;
        return i4;
    }

    private void insertionSort(double[] dArr, int i, int i2) {
        for (int i3 = i + 1; i3 < i2; i3++) {
            double d = dArr[i3];
            int i4 = i3 - 1;
            while (i4 >= i && d < dArr[i4]) {
                dArr[i4 + 1] = dArr[i4];
                i4--;
            }
            dArr[i4 + 1] = d;
        }
    }

    public double getQuantile() {
        return this.quantile;
    }

    public void setQuantile(double d) throws MathIllegalArgumentException {
        if (d <= 0.0d || d > 100.0d) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUNDS_QUANTILE_VALUE, Double.valueOf(d), Integer.valueOf(0), Integer.valueOf(100));
        }
        this.quantile = d;
    }

    public Percentile copy() {
        Percentile percentile = new Percentile();
        copy(this, percentile);
        return percentile;
    }

    public static void copy(Percentile percentile, Percentile percentile2) throws NullArgumentException {
        MathUtils.checkNotNull(percentile);
        MathUtils.checkNotNull(percentile2);
        percentile2.setData(percentile.getDataRef());
        if (percentile.cachedPivots != null) {
            System.arraycopy(percentile.cachedPivots, 0, percentile2.cachedPivots, 0, percentile.cachedPivots.length);
        }
        percentile2.quantile = percentile.quantile;
    }
}
