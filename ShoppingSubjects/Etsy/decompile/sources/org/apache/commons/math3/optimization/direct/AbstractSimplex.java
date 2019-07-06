package org.apache.commons.math3.optimization.direct;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optimization.OptimizationData;
import org.apache.commons.math3.optimization.PointValuePair;

@Deprecated
public abstract class AbstractSimplex implements OptimizationData {
    private final int dimension;
    private PointValuePair[] simplex;
    private double[][] startConfiguration;

    public abstract void iterate(MultivariateFunction multivariateFunction, Comparator<PointValuePair> comparator);

    protected AbstractSimplex(int i) {
        this(i, 1.0d);
    }

    protected AbstractSimplex(int i, double d) {
        this(createHypercubeSteps(i, d));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0050, code lost:
        r1 = r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected AbstractSimplex(double[] r10) {
        /*
            r9 = this;
            r9.<init>()
            if (r10 != 0) goto L_0x000b
            org.apache.commons.math3.exception.NullArgumentException r10 = new org.apache.commons.math3.exception.NullArgumentException
            r10.<init>()
            throw r10
        L_0x000b:
            int r0 = r10.length
            if (r0 != 0) goto L_0x0014
            org.apache.commons.math3.exception.ZeroException r10 = new org.apache.commons.math3.exception.ZeroException
            r10.<init>()
            throw r10
        L_0x0014:
            r0 = 0
            int r1 = r10.length
            r9.dimension = r1
            int r1 = r9.dimension
            int r2 = r9.dimension
            int[] r1 = new int[]{r1, r2}
            java.lang.Class<double> r2 = double.class
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r2, r1)
            double[][] r1 = (double[][]) r1
            r9.startConfiguration = r1
            r1 = r0
        L_0x002b:
            int r2 = r9.dimension
            if (r1 >= r2) goto L_0x0052
            double[][] r2 = r9.startConfiguration
            r2 = r2[r1]
            r3 = r0
        L_0x0034:
            int r4 = r1 + 1
            if (r3 >= r4) goto L_0x0050
            r4 = r10[r3]
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x004a
            org.apache.commons.math3.exception.ZeroException r10 = new org.apache.commons.math3.exception.ZeroException
            org.apache.commons.math3.exception.util.LocalizedFormats r1 = org.apache.commons.math3.exception.util.LocalizedFormats.EQUAL_VERTICES_IN_SIMPLEX
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r10.<init>(r1, r0)
            throw r10
        L_0x004a:
            int r3 = r3 + 1
            java.lang.System.arraycopy(r10, r0, r2, r0, r3)
            goto L_0x0034
        L_0x0050:
            r1 = r4
            goto L_0x002b
        L_0x0052:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optimization.direct.AbstractSimplex.<init>(double[]):void");
    }

    protected AbstractSimplex(double[][] dArr) {
        boolean z;
        if (dArr.length <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.SIMPLEX_NEED_ONE_POINT, Integer.valueOf(dArr.length));
        }
        this.dimension = dArr.length - 1;
        this.startConfiguration = (double[][]) Array.newInstance(double.class, new int[]{this.dimension, this.dimension});
        double[] dArr2 = dArr[0];
        for (int i = 0; i < dArr.length; i++) {
            double[] dArr3 = dArr[i];
            if (dArr3.length != this.dimension) {
                throw new DimensionMismatchException(dArr3.length, this.dimension);
            }
            for (int i2 = 0; i2 < i; i2++) {
                double[] dArr4 = dArr[i2];
                int i3 = 0;
                while (true) {
                    if (i3 >= this.dimension) {
                        z = true;
                        break;
                    } else if (dArr3[i3] != dArr4[i3]) {
                        z = false;
                        break;
                    } else {
                        i3++;
                    }
                }
                if (z) {
                    throw new MathIllegalArgumentException(LocalizedFormats.EQUAL_VERTICES_IN_SIMPLEX, Integer.valueOf(i), Integer.valueOf(i2));
                }
            }
            if (i > 0) {
                double[] dArr5 = this.startConfiguration[i - 1];
                for (int i4 = 0; i4 < this.dimension; i4++) {
                    dArr5[i4] = dArr3[i4] - dArr2[i4];
                }
            }
        }
    }

    public int getDimension() {
        return this.dimension;
    }

    public int getSize() {
        return this.simplex.length;
    }

    public void build(double[] dArr) {
        if (this.dimension != dArr.length) {
            throw new DimensionMismatchException(this.dimension, dArr.length);
        }
        this.simplex = new PointValuePair[(this.dimension + 1)];
        this.simplex[0] = new PointValuePair(dArr, Double.NaN);
        int i = 0;
        while (i < this.dimension) {
            double[] dArr2 = this.startConfiguration[i];
            double[] dArr3 = new double[this.dimension];
            for (int i2 = 0; i2 < this.dimension; i2++) {
                dArr3[i2] = dArr[i2] + dArr2[i2];
            }
            i++;
            this.simplex[i] = new PointValuePair(dArr3, Double.NaN);
        }
    }

    public void evaluate(MultivariateFunction multivariateFunction, Comparator<PointValuePair> comparator) {
        for (int i = 0; i < this.simplex.length; i++) {
            PointValuePair pointValuePair = this.simplex[i];
            double[] pointRef = pointValuePair.getPointRef();
            if (Double.isNaN(((Double) pointValuePair.getValue()).doubleValue())) {
                this.simplex[i] = new PointValuePair(pointRef, multivariateFunction.value(pointRef), false);
            }
        }
        Arrays.sort(this.simplex, comparator);
    }

    /* access modifiers changed from: protected */
    public void replaceWorstPoint(PointValuePair pointValuePair, Comparator<PointValuePair> comparator) {
        for (int i = 0; i < this.dimension; i++) {
            if (comparator.compare(this.simplex[i], pointValuePair) > 0) {
                PointValuePair pointValuePair2 = this.simplex[i];
                this.simplex[i] = pointValuePair;
                pointValuePair = pointValuePair2;
            }
        }
        this.simplex[this.dimension] = pointValuePair;
    }

    public PointValuePair[] getPoints() {
        PointValuePair[] pointValuePairArr = new PointValuePair[this.simplex.length];
        System.arraycopy(this.simplex, 0, pointValuePairArr, 0, this.simplex.length);
        return pointValuePairArr;
    }

    public PointValuePair getPoint(int i) {
        if (i >= 0 && i < this.simplex.length) {
            return this.simplex[i];
        }
        throw new OutOfRangeException(Integer.valueOf(i), Integer.valueOf(0), Integer.valueOf(this.simplex.length - 1));
    }

    /* access modifiers changed from: protected */
    public void setPoint(int i, PointValuePair pointValuePair) {
        if (i < 0 || i >= this.simplex.length) {
            throw new OutOfRangeException(Integer.valueOf(i), Integer.valueOf(0), Integer.valueOf(this.simplex.length - 1));
        }
        this.simplex[i] = pointValuePair;
    }

    /* access modifiers changed from: protected */
    public void setPoints(PointValuePair[] pointValuePairArr) {
        if (pointValuePairArr.length != this.simplex.length) {
            throw new DimensionMismatchException(pointValuePairArr.length, this.simplex.length);
        }
        this.simplex = pointValuePairArr;
    }

    private static double[] createHypercubeSteps(int i, double d) {
        double[] dArr = new double[i];
        for (int i2 = 0; i2 < i; i2++) {
            dArr[i2] = d;
        }
        return dArr;
    }
}
