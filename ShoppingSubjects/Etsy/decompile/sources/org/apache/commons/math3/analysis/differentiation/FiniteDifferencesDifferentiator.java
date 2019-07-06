package org.apache.commons.math3.analysis.differentiation;

import java.io.Serializable;
import java.lang.reflect.Array;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.UnivariateMatrixFunction;
import org.apache.commons.math3.analysis.UnivariateVectorFunction;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.FastMath;

public class FiniteDifferencesDifferentiator implements Serializable, UnivariateFunctionDifferentiator, UnivariateMatrixFunctionDifferentiator, UnivariateVectorFunctionDifferentiator {
    private static final long serialVersionUID = 20120917;
    /* access modifiers changed from: private */
    public final double halfSampleSpan;
    /* access modifiers changed from: private */
    public final int nbPoints;
    /* access modifiers changed from: private */
    public final double stepSize;
    /* access modifiers changed from: private */
    public final double tMax;
    /* access modifiers changed from: private */
    public final double tMin;

    public FiniteDifferencesDifferentiator(int i, double d) throws NotPositiveException, NumberIsTooSmallException {
        this(i, d, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    public FiniteDifferencesDifferentiator(int i, double d, double d2, double d3) throws NotPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        if (i <= 1) {
            throw new NumberIsTooSmallException(Double.valueOf(d), Integer.valueOf(1), false);
        }
        this.nbPoints = i;
        if (d <= 0.0d) {
            throw new NotPositiveException(Double.valueOf(d));
        }
        this.stepSize = d;
        this.halfSampleSpan = 0.5d * d * ((double) (i - 1));
        double d4 = d3 - d2;
        if (this.halfSampleSpan * 2.0d >= d4) {
            throw new NumberIsTooLargeException(Double.valueOf(2.0d * this.halfSampleSpan), Double.valueOf(d4), false);
        }
        double ulp = FastMath.ulp(this.halfSampleSpan);
        this.tMin = d2 + this.halfSampleSpan + ulp;
        this.tMax = (d3 - this.halfSampleSpan) - ulp;
    }

    public int getNbPoints() {
        return this.nbPoints;
    }

    public double getStepSize() {
        return this.stepSize;
    }

    /* access modifiers changed from: private */
    public DerivativeStructure evaluate(DerivativeStructure derivativeStructure, double d, double[] dArr) throws NumberIsTooLargeException {
        double[] dArr2 = new double[this.nbPoints];
        double[] dArr3 = new double[this.nbPoints];
        for (int i = 0; i < this.nbPoints; i++) {
            dArr3[i] = dArr[i];
            for (int i2 = 1; i2 <= i; i2++) {
                int i3 = i - i2;
                dArr3[i3] = (dArr3[i3 + 1] - dArr3[i3]) / (((double) i2) * this.stepSize);
            }
            dArr2[i] = dArr3[0];
        }
        int order = derivativeStructure.getOrder();
        int freeParameters = derivativeStructure.getFreeParameters();
        double[] allDerivatives = derivativeStructure.getAllDerivatives();
        double value = derivativeStructure.getValue() - d;
        DerivativeStructure derivativeStructure2 = null;
        DerivativeStructure derivativeStructure3 = new DerivativeStructure(freeParameters, order, 0.0d);
        for (int i4 = 0; i4 < this.nbPoints; i4++) {
            if (i4 == 0) {
                derivativeStructure2 = new DerivativeStructure(freeParameters, order, 1.0d);
            } else {
                allDerivatives[0] = value - (((double) (i4 - 1)) * this.stepSize);
                derivativeStructure2 = derivativeStructure2.multiply(new DerivativeStructure(freeParameters, order, allDerivatives));
            }
            derivativeStructure3 = derivativeStructure3.add(derivativeStructure2.multiply(dArr2[i4]));
        }
        return derivativeStructure3;
    }

    public UnivariateDifferentiableFunction differentiate(final UnivariateFunction univariateFunction) {
        return new UnivariateDifferentiableFunction() {
            public double value(double d) throws MathIllegalArgumentException {
                return univariateFunction.value(d);
            }

            public DerivativeStructure value(DerivativeStructure derivativeStructure) throws MathIllegalArgumentException {
                if (derivativeStructure.getOrder() >= FiniteDifferencesDifferentiator.this.nbPoints) {
                    throw new NumberIsTooLargeException(Integer.valueOf(derivativeStructure.getOrder()), Integer.valueOf(FiniteDifferencesDifferentiator.this.nbPoints), false);
                }
                double max = FastMath.max(FastMath.min(derivativeStructure.getValue(), FiniteDifferencesDifferentiator.this.tMax), FiniteDifferencesDifferentiator.this.tMin) - FiniteDifferencesDifferentiator.this.halfSampleSpan;
                double[] dArr = new double[FiniteDifferencesDifferentiator.this.nbPoints];
                for (int i = 0; i < FiniteDifferencesDifferentiator.this.nbPoints; i++) {
                    dArr[i] = univariateFunction.value((((double) i) * FiniteDifferencesDifferentiator.this.stepSize) + max);
                }
                return FiniteDifferencesDifferentiator.this.evaluate(derivativeStructure, max, dArr);
            }
        };
    }

    public UnivariateDifferentiableVectorFunction differentiate(final UnivariateVectorFunction univariateVectorFunction) {
        return new UnivariateDifferentiableVectorFunction() {
            public double[] value(double d) throws MathIllegalArgumentException {
                return univariateVectorFunction.value(d);
            }

            public DerivativeStructure[] value(DerivativeStructure derivativeStructure) throws MathIllegalArgumentException {
                if (derivativeStructure.getOrder() >= FiniteDifferencesDifferentiator.this.nbPoints) {
                    throw new NumberIsTooLargeException(Integer.valueOf(derivativeStructure.getOrder()), Integer.valueOf(FiniteDifferencesDifferentiator.this.nbPoints), false);
                }
                double max = FastMath.max(FastMath.min(derivativeStructure.getValue(), FiniteDifferencesDifferentiator.this.tMax), FiniteDifferencesDifferentiator.this.tMin) - FiniteDifferencesDifferentiator.this.halfSampleSpan;
                double[][] dArr = null;
                for (int i = 0; i < FiniteDifferencesDifferentiator.this.nbPoints; i++) {
                    double[] value = univariateVectorFunction.value((((double) i) * FiniteDifferencesDifferentiator.this.stepSize) + max);
                    if (i == 0) {
                        dArr = (double[][]) Array.newInstance(double.class, new int[]{value.length, FiniteDifferencesDifferentiator.this.nbPoints});
                    }
                    for (int i2 = 0; i2 < value.length; i2++) {
                        dArr[i2][i] = value[i2];
                    }
                }
                DerivativeStructure[] derivativeStructureArr = new DerivativeStructure[dArr.length];
                for (int i3 = 0; i3 < derivativeStructureArr.length; i3++) {
                    derivativeStructureArr[i3] = FiniteDifferencesDifferentiator.this.evaluate(derivativeStructure, max, dArr[i3]);
                }
                return derivativeStructureArr;
            }
        };
    }

    public UnivariateDifferentiableMatrixFunction differentiate(final UnivariateMatrixFunction univariateMatrixFunction) {
        return new UnivariateDifferentiableMatrixFunction() {
            public double[][] value(double d) throws MathIllegalArgumentException {
                return univariateMatrixFunction.value(d);
            }

            public DerivativeStructure[][] value(DerivativeStructure derivativeStructure) throws MathIllegalArgumentException {
                if (derivativeStructure.getOrder() >= FiniteDifferencesDifferentiator.this.nbPoints) {
                    throw new NumberIsTooLargeException(Integer.valueOf(derivativeStructure.getOrder()), Integer.valueOf(FiniteDifferencesDifferentiator.this.nbPoints), false);
                }
                double max = FastMath.max(FastMath.min(derivativeStructure.getValue(), FiniteDifferencesDifferentiator.this.tMax), FiniteDifferencesDifferentiator.this.tMin) - FiniteDifferencesDifferentiator.this.halfSampleSpan;
                double[][][] dArr = null;
                for (int i = 0; i < FiniteDifferencesDifferentiator.this.nbPoints; i++) {
                    double[][] value = univariateMatrixFunction.value((((double) i) * FiniteDifferencesDifferentiator.this.stepSize) + max);
                    if (i == 0) {
                        dArr = (double[][][]) Array.newInstance(double.class, new int[]{value.length, value[0].length, FiniteDifferencesDifferentiator.this.nbPoints});
                    }
                    for (int i2 = 0; i2 < value.length; i2++) {
                        for (int i3 = 0; i3 < value[i2].length; i3++) {
                            dArr[i2][i3][i] = value[i2][i3];
                        }
                    }
                }
                DerivativeStructure[][] derivativeStructureArr = (DerivativeStructure[][]) Array.newInstance(DerivativeStructure.class, new int[]{dArr.length, dArr[0].length});
                for (int i4 = 0; i4 < derivativeStructureArr.length; i4++) {
                    for (int i5 = 0; i5 < dArr[i4].length; i5++) {
                        derivativeStructureArr[i4][i5] = FiniteDifferencesDifferentiator.this.evaluate(derivativeStructure, max, dArr[i4][i5]);
                    }
                }
                return derivativeStructureArr;
            }
        };
    }
}
