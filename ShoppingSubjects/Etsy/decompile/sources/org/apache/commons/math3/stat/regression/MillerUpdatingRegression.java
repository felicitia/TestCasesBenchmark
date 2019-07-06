package org.apache.commons.math3.stat.regression;

import java.util.Arrays;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Precision;

public class MillerUpdatingRegression implements UpdatingMultipleLinearRegression {
    private final double[] d;
    private final double epsilon;
    private boolean hasIntercept;
    private final boolean[] lindep;
    private long nobs;
    private final int nvars;
    private final double[] r;
    private final double[] rhs;
    private final double[] rss;
    private boolean rss_set;
    private double sserr;
    private double sumsqy;
    private double sumy;
    private final double[] tol;
    private boolean tol_set;
    private final int[] vorder;
    private final double[] work_sing;
    private final double[] work_tolset;
    private final double[] x_sing;

    private MillerUpdatingRegression() {
        this(-1, false, Double.NaN);
    }

    public MillerUpdatingRegression(int i, boolean z, double d2) throws ModelSpecificationException {
        this.nobs = 0;
        this.sserr = 0.0d;
        this.rss_set = false;
        this.tol_set = false;
        this.sumy = 0.0d;
        this.sumsqy = 0.0d;
        if (i < 1) {
            throw new ModelSpecificationException(LocalizedFormats.NO_REGRESSORS, new Object[0]);
        }
        if (z) {
            this.nvars = i + 1;
        } else {
            this.nvars = i;
        }
        this.hasIntercept = z;
        this.nobs = 0;
        this.d = new double[this.nvars];
        this.rhs = new double[this.nvars];
        this.r = new double[((this.nvars * (this.nvars - 1)) / 2)];
        this.tol = new double[this.nvars];
        this.rss = new double[this.nvars];
        this.vorder = new int[this.nvars];
        this.x_sing = new double[this.nvars];
        this.work_sing = new double[this.nvars];
        this.work_tolset = new double[this.nvars];
        this.lindep = new boolean[this.nvars];
        for (int i2 = 0; i2 < this.nvars; i2++) {
            this.vorder[i2] = i2;
        }
        if (d2 > 0.0d) {
            this.epsilon = d2;
        } else {
            this.epsilon = -d2;
        }
    }

    public MillerUpdatingRegression(int i, boolean z) throws ModelSpecificationException {
        this(i, z, Precision.EPSILON);
    }

    public boolean hasIntercept() {
        return this.hasIntercept;
    }

    public long getN() {
        return this.nobs;
    }

    public void addObservation(double[] dArr, double d2) throws ModelSpecificationException {
        if ((this.hasIntercept || dArr.length == this.nvars) && (!this.hasIntercept || dArr.length + 1 == this.nvars)) {
            if (!this.hasIntercept) {
                include(MathArrays.copyOf(dArr, dArr.length), 1.0d, d2);
            } else {
                double[] dArr2 = new double[(dArr.length + 1)];
                System.arraycopy(dArr, 0, dArr2, 1, dArr.length);
                dArr2[0] = 1.0d;
                include(dArr2, 1.0d, d2);
            }
            this.nobs++;
            return;
        }
        throw new ModelSpecificationException(LocalizedFormats.INVALID_REGRESSION_OBSERVATION, Integer.valueOf(dArr.length), Integer.valueOf(this.nvars));
    }

    public void addObservations(double[][] dArr, double[] dArr2) throws ModelSpecificationException {
        int i = 0;
        if (dArr == null || dArr2 == null || dArr.length != dArr2.length) {
            LocalizedFormats localizedFormats = LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE;
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(dArr == null ? 0 : dArr.length);
            if (dArr2 != null) {
                i = dArr2.length;
            }
            objArr[1] = Integer.valueOf(i);
            throw new ModelSpecificationException(localizedFormats, objArr);
        } else if (dArr.length == 0) {
            throw new ModelSpecificationException(LocalizedFormats.NO_DATA, new Object[0]);
        } else if (dArr[0].length + 1 > dArr.length) {
            throw new ModelSpecificationException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, Integer.valueOf(dArr.length), Integer.valueOf(dArr[0].length));
        } else {
            while (i < dArr.length) {
                addObservation(dArr[i], dArr2[i]);
                i++;
            }
        }
    }

    private void include(double[] dArr, double d2, double d3) {
        double d4;
        double d5;
        double[] dArr2 = dArr;
        double d6 = d3;
        int i = 0;
        this.rss_set = false;
        this.sumy = smartAdd(d6, this.sumy);
        this.sumsqy = smartAdd(this.sumsqy, d6 * d6);
        double d7 = d6;
        int i2 = 0;
        double d8 = d2;
        while (i < dArr2.length) {
            if (d8 != 0.0d) {
                double d9 = dArr2[i];
                if (d9 == 0.0d) {
                    i2 += (this.nvars - i) - 1;
                } else {
                    double d10 = this.d[i];
                    double d11 = d8 * d9;
                    if (d10 != 0.0d) {
                        double d12 = d11 * d9;
                        d4 = smartAdd(d10, d12);
                        if (FastMath.abs(d12 / d10) > Precision.EPSILON) {
                            d8 = (d8 * d10) / d4;
                        }
                        d5 = d8;
                    } else {
                        d4 = d11 * d9;
                        d5 = 0.0d;
                    }
                    this.d[i] = d4;
                    int i3 = i + 1;
                    while (i3 < this.nvars) {
                        double d13 = d5;
                        double d14 = dArr2[i3];
                        double d15 = d7;
                        dArr2[i3] = smartAdd(d14, (-d9) * this.r[i2]);
                        if (d10 != 0.0d) {
                            this.r[i2] = smartAdd(d10 * this.r[i2], d14 * d11) / d4;
                        } else {
                            this.r[i2] = d14 / d9;
                        }
                        i2++;
                        i3++;
                        d5 = d13;
                        d7 = d15;
                    }
                    double d16 = d5;
                    double d17 = d7;
                    double smartAdd = smartAdd(d17, (-d9) * this.rhs[i]);
                    if (d10 != 0.0d) {
                        this.rhs[i] = smartAdd(d10 * this.rhs[i], d17 * d11) / d4;
                    } else {
                        this.rhs[i] = d17 / d9;
                    }
                    d7 = smartAdd;
                    d8 = d16;
                }
                i++;
            } else {
                return;
            }
        }
        this.sserr = smartAdd(this.sserr, d8 * d7 * d7);
    }

    private double smartAdd(double d2, double d3) {
        double abs = FastMath.abs(d2);
        double abs2 = FastMath.abs(d3);
        return abs > abs2 ? abs2 > abs * Precision.EPSILON ? d2 + d3 : d2 : abs > abs2 * Precision.EPSILON ? d2 + d3 : d3;
    }

    public void clear() {
        Arrays.fill(this.d, 0.0d);
        Arrays.fill(this.rhs, 0.0d);
        Arrays.fill(this.r, 0.0d);
        Arrays.fill(this.tol, 0.0d);
        Arrays.fill(this.rss, 0.0d);
        Arrays.fill(this.work_tolset, 0.0d);
        Arrays.fill(this.work_sing, 0.0d);
        Arrays.fill(this.x_sing, 0.0d);
        Arrays.fill(this.lindep, false);
        for (int i = 0; i < this.nvars; i++) {
            this.vorder[i] = i;
        }
        this.nobs = 0;
        this.sserr = 0.0d;
        this.sumy = 0.0d;
        this.sumsqy = 0.0d;
        this.rss_set = false;
        this.tol_set = false;
    }

    private void tolset() {
        double d2 = this.epsilon;
        for (int i = 0; i < this.nvars; i++) {
            this.work_tolset[i] = Math.sqrt(this.d[i]);
        }
        this.tol[0] = this.work_tolset[0] * d2;
        for (int i2 = 1; i2 < this.nvars; i2++) {
            int i3 = i2 - 1;
            double d3 = this.work_tolset[i2];
            int i4 = i3;
            for (int i5 = 0; i5 < i2; i5++) {
                d3 += Math.abs(this.r[i4]) * this.work_tolset[i5];
                i4 += (this.nvars - i5) - 2;
            }
            this.tol[i2] = d3 * d2;
        }
        this.tol_set = true;
    }

    private double[] regcf(int i) throws ModelSpecificationException {
        if (i < 1) {
            throw new ModelSpecificationException(LocalizedFormats.NO_REGRESSORS, new Object[0]);
        } else if (i > this.nvars) {
            throw new ModelSpecificationException(LocalizedFormats.TOO_MANY_REGRESSORS, Integer.valueOf(i), Integer.valueOf(this.nvars));
        } else {
            if (!this.tol_set) {
                tolset();
            }
            double[] dArr = new double[i];
            boolean z = false;
            for (int i2 = i - 1; i2 > -1; i2--) {
                if (Math.sqrt(this.d[i2]) < this.tol[i2]) {
                    dArr[i2] = 0.0d;
                    this.d[i2] = 0.0d;
                    z = true;
                } else {
                    dArr[i2] = this.rhs[i2];
                    int i3 = ((((this.nvars + this.nvars) - i2) - 1) * i2) / 2;
                    for (int i4 = i2 + 1; i4 < i; i4++) {
                        dArr[i2] = smartAdd(dArr[i2], (-this.r[i3]) * dArr[i4]);
                        i3++;
                    }
                }
            }
            if (z) {
                for (int i5 = 0; i5 < i; i5++) {
                    if (this.lindep[i5]) {
                        dArr[i5] = Double.NaN;
                    }
                }
            }
            return dArr;
        }
    }

    private void singcheck() {
        for (int i = 0; i < this.nvars; i++) {
            this.work_sing[i] = Math.sqrt(this.d[i]);
        }
        for (int i2 = 0; i2 < this.nvars; i2++) {
            double d2 = this.tol[i2];
            int i3 = i2 - 1;
            int i4 = i3;
            for (int i5 = 0; i5 < i3; i5++) {
                if (Math.abs(this.r[i4]) * this.work_sing[i5] < d2) {
                    this.r[i4] = 0.0d;
                }
                i4 += (this.nvars - i5) - 2;
            }
            this.lindep[i2] = false;
            if (this.work_sing[i2] < d2) {
                this.lindep[i2] = true;
                if (i2 < this.nvars - 1) {
                    Arrays.fill(this.x_sing, 0.0d);
                    int i6 = ((((this.nvars + this.nvars) - i2) - 1) * i2) / 2;
                    int i7 = i2 + 1;
                    while (i7 < this.nvars) {
                        this.x_sing[i7] = this.r[i6];
                        this.r[i6] = 0.0d;
                        i7++;
                        i6++;
                    }
                    double d3 = this.rhs[i2];
                    double d4 = this.d[i2];
                    this.d[i2] = 0.0d;
                    this.rhs[i2] = 0.0d;
                    include(this.x_sing, d4, d3);
                } else {
                    this.sserr += this.d[i2] * this.rhs[i2] * this.rhs[i2];
                }
            }
        }
    }

    private void ss() {
        double d2 = this.sserr;
        this.rss[this.nvars - 1] = this.sserr;
        for (int i = this.nvars - 1; i > 0; i--) {
            d2 += this.d[i] * this.rhs[i] * this.rhs[i];
            this.rss[i - 1] = d2;
        }
        this.rss_set = true;
    }

    private double[] cov(int i) {
        double d2;
        double[] dArr;
        double d3;
        double[] dArr2;
        int i2 = i;
        if (this.nobs <= ((long) i2)) {
            return null;
        }
        int i3 = 0;
        double d4 = 0.0d;
        int i4 = 0;
        while (true) {
            d2 = 1.0d;
            if (i4 >= i2) {
                break;
            }
            if (!this.lindep[i4]) {
                d4 += 1.0d;
            }
            i4++;
        }
        int i5 = i2 - 1;
        double d5 = this.rss[i5] / (((double) this.nobs) - d4);
        double[] dArr3 = new double[((i2 * i5) / 2)];
        inverse(dArr3, i2);
        double[] dArr4 = new double[(((i2 + 1) * i2) / 2)];
        Arrays.fill(dArr4, Double.NaN);
        int i6 = 0;
        while (i3 < i2) {
            if (!this.lindep[i3]) {
                int i7 = i3;
                int i8 = i6;
                while (i7 < i2) {
                    if (!this.lindep[i7]) {
                        int i9 = (i6 + i7) - i3;
                        if (i3 == i7) {
                            d3 = d2 / this.d[i7];
                        } else {
                            d3 = dArr3[i9 - 1] / this.d[i7];
                        }
                        int i10 = i7 + 1;
                        int i11 = i9;
                        int i12 = i8;
                        int i13 = i10;
                        while (i13 < i2) {
                            if (!this.lindep[i13]) {
                                dArr2 = dArr3;
                                d3 += (dArr3[i11] * dArr3[i12]) / this.d[i13];
                            } else {
                                dArr2 = dArr3;
                            }
                            i11++;
                            i12++;
                            i13++;
                            dArr3 = dArr2;
                        }
                        dArr = dArr3;
                        dArr4[((i10 * i7) / 2) + i3] = d3 * d5;
                        i8 = i12;
                    } else {
                        dArr = dArr3;
                        i8 += (i2 - i7) - 1;
                    }
                    i7++;
                    dArr3 = dArr;
                    d2 = 1.0d;
                }
            }
            i6 += (i2 - i3) - 1;
            i3++;
            dArr3 = dArr3;
            d2 = 1.0d;
        }
        return dArr4;
    }

    private void inverse(double[] dArr, int i) {
        int i2 = i - 1;
        int i3 = ((i * i2) / 2) - 1;
        Arrays.fill(dArr, Double.NaN);
        while (i2 > 0) {
            if (!this.lindep[i2]) {
                int i4 = ((i2 - 1) * ((this.nvars + this.nvars) - i2)) / 2;
                int i5 = i3;
                for (int i6 = i; i6 > i2; i6--) {
                    int i7 = i4;
                    double d2 = 0.0d;
                    int i8 = i5;
                    for (int i9 = i2; i9 < i6 - 1; i9++) {
                        i8 += (i - i9) - 1;
                        if (!this.lindep[i9]) {
                            d2 += (-this.r[i7]) * dArr[i8];
                        }
                        i7++;
                    }
                    dArr[i5] = d2 - this.r[i7];
                    i5--;
                }
                i3 = i5;
            } else {
                i3 -= i - i2;
            }
            i2--;
        }
    }

    public double[] getPartialCorrelations(int i) {
        int i2 = i;
        double[] dArr = new double[((((this.nvars - i2) + 1) * (this.nvars - i2)) / 2)];
        int i3 = -i2;
        int i4 = i2 + 1;
        int i5 = -i4;
        double[] dArr2 = new double[(this.nvars - i2)];
        double[] dArr3 = new double[((this.nvars - i2) - 1)];
        int i6 = ((this.nvars - i2) * ((this.nvars - i2) - 1)) / 2;
        if (i2 < -1 || i2 >= this.nvars) {
            return null;
        }
        int i7 = (this.nvars - 1) - i2;
        int length = this.r.length - ((i7 * (i7 + 1)) / 2);
        if (this.d[i2] > 0.0d) {
            dArr2[i2 + i3] = 1.0d / Math.sqrt(this.d[i2]);
        }
        while (i4 < this.nvars) {
            int i8 = ((length + i4) - 1) - i2;
            double d2 = this.d[i4];
            int i9 = i8;
            for (int i10 = i2; i10 < i4; i10++) {
                d2 += this.d[i10] * this.r[i9] * this.r[i9];
                i9 += (this.nvars - i10) - 2;
            }
            if (d2 > 0.0d) {
                dArr2[i4 + i3] = 1.0d / Math.sqrt(d2);
            } else {
                dArr2[i4 + i3] = 0.0d;
            }
            i4++;
        }
        double d3 = this.sserr;
        for (int i11 = i2; i11 < this.nvars; i11++) {
            d3 += this.d[i11] * this.rhs[i11] * this.rhs[i11];
        }
        double d4 = 0.0d;
        if (d3 > 0.0d) {
            d3 = 1.0d / Math.sqrt(d3);
        }
        int i12 = i2;
        while (i12 < this.nvars) {
            Arrays.fill(dArr3, d4);
            int i13 = ((length + i12) - i2) - 1;
            double d5 = d4;
            int i14 = i2;
            while (i14 < i12) {
                int i15 = i13 + 1;
                int i16 = i12 + 1;
                while (i16 < this.nvars) {
                    int i17 = i16 + i5;
                    int i18 = length;
                    dArr3[i17] = dArr3[i17] + (this.d[i14] * this.r[i13] * this.r[i15]);
                    i15++;
                    i16++;
                    length = i18;
                }
                d5 += this.d[i14] * this.r[i13] * this.rhs[i14];
                i13 += (this.nvars - i14) - 2;
                i14++;
                length = length;
            }
            int i19 = length;
            int i20 = i13 + 1;
            int i21 = i12 + 1;
            for (int i22 = i21; i22 < this.nvars; i22++) {
                int i23 = i22 + i5;
                dArr3[i23] = dArr3[i23] + (this.d[i12] * this.r[i20]);
                i20++;
                dArr[(((((i22 - 1) - i2) * (i22 - i2)) / 2) + i12) - i2] = dArr3[i23] * dArr2[i12 + i3] * dArr2[i22 + i3];
            }
            int i24 = i12 + i3;
            dArr[i24 + i6] = (d5 + (this.d[i12] * this.rhs[i12])) * dArr2[i24] * d3;
            i12 = i21;
            length = i19;
            d4 = 0.0d;
        }
        return dArr;
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00fb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void vmove(int r26, int r27) {
        /*
            r25 = this;
            r0 = r25
            r1 = r26
            r2 = r27
            if (r1 != r2) goto L_0x0009
            return
        L_0x0009:
            boolean r3 = r0.rss_set
            if (r3 != 0) goto L_0x0010
            r25.ss()
        L_0x0010:
            r3 = 1
            if (r1 >= r2) goto L_0x0016
            int r2 = r2 - r1
            r5 = r3
            goto L_0x001c
        L_0x0016:
            int r4 = r1 + -1
            r5 = -1
            int r2 = r1 - r2
            r1 = r4
        L_0x001c:
            r6 = r1
            r1 = 0
            r7 = 0
        L_0x001f:
            if (r1 >= r2) goto L_0x01b4
            int r8 = r0.nvars
            int r9 = r0.nvars
            int r8 = r8 + r9
            int r8 = r8 - r6
            int r8 = r8 - r3
            int r8 = r8 * r6
            int r8 = r8 / 2
            int r9 = r0.nvars
            int r9 = r9 + r8
            int r9 = r9 - r6
            int r9 = r9 - r3
            int r10 = r6 + 1
            double[] r11 = r0.d
            r12 = r11[r6]
            double[] r11 = r0.d
            r14 = r11[r10]
            r16 = r5
            double r4 = r0.epsilon
            int r11 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r11 > 0) goto L_0x0051
            double r4 = r0.epsilon
            int r11 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r11 <= 0) goto L_0x0049
            goto L_0x0051
        L_0x0049:
            r18 = r1
            r17 = r2
            r24 = r10
            goto L_0x0152
        L_0x0051:
            double[] r4 = r0.r
            r18 = r1
            r17 = r2
            r1 = r4[r8]
            double r4 = java.lang.Math.abs(r1)
            double r19 = java.lang.Math.sqrt(r12)
            double r4 = r4 * r19
            double[] r11 = r0.tol
            r19 = r11[r10]
            int r11 = (r4 > r19 ? 1 : (r4 == r19 ? 0 : -1))
            if (r11 >= 0) goto L_0x006d
            r1 = 0
        L_0x006d:
            double r4 = r0.epsilon
            int r11 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r11 < 0) goto L_0x00b4
            double r4 = java.lang.Math.abs(r1)
            r23 = r9
            r24 = r10
            double r9 = r0.epsilon
            int r11 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r11 >= 0) goto L_0x0082
            goto L_0x00b8
        L_0x0082:
            double r4 = r0.epsilon
            int r9 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r9 >= 0) goto L_0x00f9
            double[] r4 = r0.d
            double r9 = r12 * r1
            double r9 = r9 * r1
            r4[r6] = r9
            double[] r4 = r0.r
            r9 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r9 = r9 / r1
            r4[r8] = r9
            int r4 = r8 + 1
        L_0x0098:
            int r5 = r0.nvars
            int r5 = r5 + r8
            int r5 = r5 - r6
            int r5 = r5 - r3
            if (r4 >= r5) goto L_0x00a9
            double[] r5 = r0.r
            r9 = r5[r4]
            double r9 = r9 / r1
            r5[r4] = r9
            int r4 = r4 + 1
            goto L_0x0098
        L_0x00a9:
            double[] r4 = r0.rhs
            double[] r5 = r0.rhs
            r9 = r5[r6]
            double r9 = r9 / r1
            r4[r6] = r9
            r7 = r3
            goto L_0x00f9
        L_0x00b4:
            r23 = r9
            r24 = r10
        L_0x00b8:
            double[] r1 = r0.d
            r1[r6] = r14
            double[] r1 = r0.d
            r1[r24] = r12
            double[] r1 = r0.r
            r4 = 0
            r1[r8] = r4
            int r1 = r6 + 2
            r9 = r23
        L_0x00ca:
            int r2 = r0.nvars
            if (r1 >= r2) goto L_0x00e5
            int r8 = r8 + 1
            double[] r2 = r0.r
            r4 = r2[r8]
            double[] r2 = r0.r
            double[] r7 = r0.r
            r10 = r7[r9]
            r2[r8] = r10
            double[] r2 = r0.r
            r2[r9] = r4
            int r9 = r9 + 1
            int r1 = r1 + 1
            goto L_0x00ca
        L_0x00e5:
            double[] r1 = r0.rhs
            r4 = r1[r6]
            double[] r1 = r0.rhs
            double[] r2 = r0.rhs
            r10 = r2[r24]
            r1[r6] = r10
            double[] r1 = r0.rhs
            r1[r24] = r4
            r7 = r3
            r1 = r4
            r23 = r9
        L_0x00f9:
            if (r7 != 0) goto L_0x0152
            double r4 = r12 * r1
            double r9 = r4 * r1
            double r9 = r9 + r14
            double r14 = r14 / r9
            double r4 = r4 / r9
            double r12 = r12 * r14
            double[] r11 = r0.d
            r11[r6] = r9
            double[] r9 = r0.d
            r9[r24] = r12
            double[] r9 = r0.r
            r9[r8] = r4
            int r9 = r6 + 2
        L_0x0111:
            int r10 = r0.nvars
            if (r9 >= r10) goto L_0x0139
            int r8 = r8 + r3
            double[] r10 = r0.r
            r11 = r10[r8]
            double[] r10 = r0.r
            double[] r13 = r0.r
            r19 = r13[r23]
            double r19 = r19 * r14
            double r21 = r4 * r11
            double r19 = r19 + r21
            r10[r8] = r19
            double[] r10 = r0.r
            double[] r13 = r0.r
            r19 = r13[r23]
            double r19 = r19 * r1
            double r11 = r11 - r19
            r10[r23] = r11
            int r23 = r23 + 1
            int r9 = r9 + 1
            goto L_0x0111
        L_0x0139:
            double[] r8 = r0.rhs
            r9 = r8[r6]
            double[] r8 = r0.rhs
            double[] r11 = r0.rhs
            r12 = r11[r24]
            double r14 = r14 * r12
            double r4 = r4 * r9
            double r14 = r14 + r4
            r8[r6] = r14
            double[] r4 = r0.rhs
            double[] r5 = r0.rhs
            r11 = r5[r24]
            double r1 = r1 * r11
            double r9 = r9 - r1
            r4[r24] = r9
        L_0x0152:
            if (r6 <= 0) goto L_0x0173
            r2 = r6
            r1 = 0
        L_0x0156:
            if (r1 >= r6) goto L_0x0173
            double[] r4 = r0.r
            r8 = r4[r2]
            double[] r4 = r0.r
            double[] r5 = r0.r
            int r10 = r2 + -1
            r11 = r5[r10]
            r4[r2] = r11
            double[] r4 = r0.r
            r4[r10] = r8
            int r4 = r0.nvars
            int r4 = r4 - r1
            int r4 = r4 + -2
            int r2 = r2 + r4
            int r1 = r1 + 1
            goto L_0x0156
        L_0x0173:
            int[] r1 = r0.vorder
            r1 = r1[r6]
            int[] r2 = r0.vorder
            int[] r4 = r0.vorder
            r4 = r4[r24]
            r2[r6] = r4
            int[] r2 = r0.vorder
            r2[r24] = r1
            double[] r1 = r0.tol
            r4 = r1[r6]
            double[] r1 = r0.tol
            double[] r2 = r0.tol
            r8 = r2[r24]
            r1[r6] = r8
            double[] r1 = r0.tol
            r1[r24] = r4
            double[] r1 = r0.rss
            double[] r2 = r0.rss
            r4 = r2[r24]
            double[] r2 = r0.d
            r8 = r2[r24]
            double[] r2 = r0.rhs
            r10 = r2[r24]
            double r8 = r8 * r10
            double[] r2 = r0.rhs
            r10 = r2[r24]
            double r8 = r8 * r10
            double r4 = r4 + r8
            r1[r6] = r4
            int r6 = r6 + r16
            int r1 = r18 + 1
            r5 = r16
            r2 = r17
            goto L_0x001f
        L_0x01b4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove(int, int):void");
    }

    private int reorderRegressors(int[] iArr, int i) {
        if (iArr.length < 1 || iArr.length > (this.nvars + 1) - i) {
            return -1;
        }
        int i2 = i;
        int i3 = i2;
        while (i2 < this.nvars) {
            int i4 = this.vorder[i2];
            int i5 = 0;
            while (true) {
                if (i5 >= iArr.length) {
                    break;
                } else if (i4 != iArr[i5] || i2 <= i3) {
                    i5++;
                } else {
                    vmove(i2, i3);
                    i3++;
                    if (i3 >= iArr.length + i) {
                        return 0;
                    }
                }
            }
            i2++;
        }
        return 0;
    }

    public double getDiagonalOfHatMatrix(double[] dArr) {
        double[] dArr2 = dArr;
        double[] dArr3 = new double[this.nvars];
        if (dArr2.length > this.nvars) {
            return Double.NaN;
        }
        if (this.hasIntercept) {
            double[] dArr4 = new double[(dArr2.length + 1)];
            dArr4[0] = 1.0d;
            System.arraycopy(dArr2, 0, dArr4, 1, dArr2.length);
            dArr2 = dArr4;
        }
        double d2 = 0.0d;
        for (int i = 0; i < dArr2.length; i++) {
            if (Math.sqrt(this.d[i]) < this.tol[i]) {
                dArr3[i] = 0.0d;
            } else {
                int i2 = i - 1;
                double d3 = dArr2[i];
                int i3 = i2;
                for (int i4 = 0; i4 < i; i4++) {
                    d3 = smartAdd(d3, (-dArr3[i4]) * this.r[i3]);
                    i3 += (this.nvars - i4) - 2;
                }
                dArr3[i] = d3;
                d2 = smartAdd(d2, (d3 * d3) / this.d[i]);
            }
        }
        return d2;
    }

    public int[] getOrderOfRegressors() {
        return MathArrays.copyOf(this.vorder);
    }

    public RegressionResults regress() throws ModelSpecificationException {
        return regress(this.nvars);
    }

    public RegressionResults regress(int i) throws ModelSpecificationException {
        boolean z;
        int i2;
        int i3 = i;
        if (this.nobs <= ((long) i3)) {
            throw new ModelSpecificationException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, Long.valueOf(this.nobs), Integer.valueOf(i));
        } else if (i3 > this.nvars) {
            throw new ModelSpecificationException(LocalizedFormats.TOO_MANY_REGRESSORS, Integer.valueOf(i), Integer.valueOf(this.nvars));
        } else {
            tolset();
            singcheck();
            double[] regcf = regcf(i);
            ss();
            double[] cov = cov(i);
            int i4 = 0;
            for (boolean z2 : this.lindep) {
                if (!z2) {
                    i4++;
                }
            }
            int i5 = 0;
            while (true) {
                if (i5 >= i3) {
                    z = false;
                    break;
                } else if (this.vorder[i5] != i5) {
                    z = true;
                    break;
                } else {
                    i5++;
                }
            }
            if (!z) {
                RegressionResults regressionResults = new RegressionResults(regcf, new double[][]{cov}, true, this.nobs, i4, this.sumy, this.sumsqy, this.sserr, this.hasIntercept, false);
                return regressionResults;
            }
            double[] dArr = new double[regcf.length];
            double[] dArr2 = new double[cov.length];
            int[] iArr = new int[regcf.length];
            for (int i6 = 0; i6 < this.nvars; i6++) {
                for (int i7 = 0; i7 < i3; i7++) {
                    if (this.vorder[i7] == i6) {
                        dArr[i6] = regcf[i7];
                        iArr[i6] = i7;
                    }
                }
            }
            int i8 = 0;
            int i9 = 0;
            while (i8 < regcf.length) {
                int i10 = iArr[i8];
                int i11 = i9;
                int i12 = 0;
                while (i12 <= i8) {
                    int i13 = iArr[i12];
                    if (i10 > i13) {
                        i2 = (((i10 + 1) * i10) / 2) + i13;
                    } else {
                        i2 = ((i13 * (i13 + 1)) / 2) + i10;
                    }
                    dArr2[i11] = cov[i2];
                    i12++;
                    i11++;
                }
                i8++;
                i9 = i11;
            }
            RegressionResults regressionResults2 = new RegressionResults(dArr, new double[][]{dArr2}, true, this.nobs, i4, this.sumy, this.sumsqy, this.sserr, this.hasIntercept, false);
            return regressionResults2;
        }
    }

    public RegressionResults regress(int[] iArr) throws ModelSpecificationException {
        boolean z;
        int i;
        int[] iArr2 = iArr;
        if (iArr2.length > this.nvars) {
            throw new ModelSpecificationException(LocalizedFormats.TOO_MANY_REGRESSORS, Integer.valueOf(iArr2.length), Integer.valueOf(this.nvars));
        } else if (this.nobs <= ((long) this.nvars)) {
            throw new ModelSpecificationException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, Long.valueOf(this.nobs), Integer.valueOf(this.nvars));
        } else {
            Arrays.sort(iArr);
            int i2 = 0;
            for (int i3 = 0; i3 < iArr2.length; i3++) {
                if (i3 >= this.nvars) {
                    throw new ModelSpecificationException(LocalizedFormats.INDEX_LARGER_THAN_MAX, Integer.valueOf(i3), Integer.valueOf(this.nvars));
                }
                if (i3 > 0 && iArr2[i3] == iArr2[i3 - 1]) {
                    iArr2[i3] = -1;
                    i2++;
                }
            }
            if (i2 > 0) {
                int[] iArr3 = new int[(iArr2.length - i2)];
                int i4 = 0;
                for (int i5 = 0; i5 < iArr2.length; i5++) {
                    if (iArr2[i5] > -1) {
                        iArr3[i4] = iArr2[i5];
                        i4++;
                    }
                }
                iArr2 = iArr3;
            }
            reorderRegressors(iArr2, 0);
            tolset();
            singcheck();
            double[] regcf = regcf(iArr2.length);
            ss();
            double[] cov = cov(iArr2.length);
            int i6 = 0;
            for (boolean z2 : this.lindep) {
                if (!z2) {
                    i6++;
                }
            }
            int i7 = 0;
            while (true) {
                if (i7 >= this.nvars) {
                    z = false;
                    break;
                } else if (this.vorder[i7] != iArr2[i7]) {
                    z = true;
                    break;
                } else {
                    i7++;
                }
            }
            if (!z) {
                RegressionResults regressionResults = new RegressionResults(regcf, new double[][]{cov}, true, this.nobs, i6, this.sumy, this.sumsqy, this.sserr, this.hasIntercept, false);
                return regressionResults;
            }
            double[] dArr = new double[regcf.length];
            int[] iArr4 = new int[regcf.length];
            for (int i8 = 0; i8 < iArr2.length; i8++) {
                for (int i9 = 0; i9 < this.vorder.length; i9++) {
                    if (this.vorder[i9] == iArr2[i8]) {
                        dArr[i8] = regcf[i9];
                        iArr4[i8] = i9;
                    }
                }
            }
            double[] dArr2 = new double[cov.length];
            int i10 = 0;
            int i11 = 0;
            while (i10 < regcf.length) {
                int i12 = iArr4[i10];
                int i13 = i11;
                int i14 = 0;
                while (i14 <= i10) {
                    int i15 = iArr4[i14];
                    if (i12 > i15) {
                        i = (((i12 + 1) * i12) / 2) + i15;
                    } else {
                        i = ((i15 * (i15 + 1)) / 2) + i12;
                    }
                    dArr2[i13] = cov[i];
                    i14++;
                    i13++;
                }
                i10++;
                i11 = i13;
            }
            double[][] dArr3 = {dArr2};
            long j = this.nobs;
            double d2 = this.sumy;
            RegressionResults regressionResults2 = new RegressionResults(dArr, dArr3, true, j, i6, d2, this.sumsqy, this.sserr, this.hasIntercept, false);
            return regressionResults2;
        }
    }
}
