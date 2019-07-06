package org.apache.commons.math3.analysis;

import java.lang.reflect.Array;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.MultivariateDifferentiableFunction;
import org.apache.commons.math3.analysis.differentiation.MultivariateDifferentiableVectorFunction;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.analysis.function.Identity;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class FunctionUtils {
    private FunctionUtils() {
    }

    public static UnivariateFunction compose(final UnivariateFunction... univariateFunctionArr) {
        return new UnivariateFunction() {
            public double value(double d) {
                for (int length = univariateFunctionArr.length - 1; length >= 0; length--) {
                    d = univariateFunctionArr[length].value(d);
                }
                return d;
            }
        };
    }

    public static UnivariateDifferentiableFunction compose(final UnivariateDifferentiableFunction... univariateDifferentiableFunctionArr) {
        return new UnivariateDifferentiableFunction() {
            public double value(double d) {
                for (int length = univariateDifferentiableFunctionArr.length - 1; length >= 0; length--) {
                    d = univariateDifferentiableFunctionArr[length].value(d);
                }
                return d;
            }

            public DerivativeStructure value(DerivativeStructure derivativeStructure) {
                for (int length = univariateDifferentiableFunctionArr.length - 1; length >= 0; length--) {
                    derivativeStructure = univariateDifferentiableFunctionArr[length].value(derivativeStructure);
                }
                return derivativeStructure;
            }
        };
    }

    public static DifferentiableUnivariateFunction compose(final DifferentiableUnivariateFunction... differentiableUnivariateFunctionArr) {
        return new DifferentiableUnivariateFunction() {
            public double value(double d) {
                for (int length = differentiableUnivariateFunctionArr.length - 1; length >= 0; length--) {
                    d = differentiableUnivariateFunctionArr[length].value(d);
                }
                return d;
            }

            public UnivariateFunction derivative() {
                return new UnivariateFunction() {
                    public double value(double d) {
                        double d2 = 1.0d;
                        for (int length = differentiableUnivariateFunctionArr.length - 1; length >= 0; length--) {
                            d2 *= differentiableUnivariateFunctionArr[length].derivative().value(d);
                            d = differentiableUnivariateFunctionArr[length].value(d);
                        }
                        return d2;
                    }
                };
            }
        };
    }

    public static UnivariateFunction add(final UnivariateFunction... univariateFunctionArr) {
        return new UnivariateFunction() {
            public double value(double d) {
                double value = univariateFunctionArr[0].value(d);
                for (int i = 1; i < univariateFunctionArr.length; i++) {
                    value += univariateFunctionArr[i].value(d);
                }
                return value;
            }
        };
    }

    public static UnivariateDifferentiableFunction add(final UnivariateDifferentiableFunction... univariateDifferentiableFunctionArr) {
        return new UnivariateDifferentiableFunction() {
            public double value(double d) {
                double value = univariateDifferentiableFunctionArr[0].value(d);
                for (int i = 1; i < univariateDifferentiableFunctionArr.length; i++) {
                    value += univariateDifferentiableFunctionArr[i].value(d);
                }
                return value;
            }

            public DerivativeStructure value(DerivativeStructure derivativeStructure) {
                DerivativeStructure value = univariateDifferentiableFunctionArr[0].value(derivativeStructure);
                for (int i = 1; i < univariateDifferentiableFunctionArr.length; i++) {
                    value = value.add(univariateDifferentiableFunctionArr[i].value(derivativeStructure));
                }
                return value;
            }
        };
    }

    @Deprecated
    public static DifferentiableUnivariateFunction add(final DifferentiableUnivariateFunction... differentiableUnivariateFunctionArr) {
        return new DifferentiableUnivariateFunction() {
            public double value(double d) {
                double value = differentiableUnivariateFunctionArr[0].value(d);
                for (int i = 1; i < differentiableUnivariateFunctionArr.length; i++) {
                    value += differentiableUnivariateFunctionArr[i].value(d);
                }
                return value;
            }

            public UnivariateFunction derivative() {
                return new UnivariateFunction() {
                    public double value(double d) {
                        double value = differentiableUnivariateFunctionArr[0].derivative().value(d);
                        for (int i = 1; i < differentiableUnivariateFunctionArr.length; i++) {
                            value += differentiableUnivariateFunctionArr[i].derivative().value(d);
                        }
                        return value;
                    }
                };
            }
        };
    }

    public static UnivariateFunction multiply(final UnivariateFunction... univariateFunctionArr) {
        return new UnivariateFunction() {
            public double value(double d) {
                double value = univariateFunctionArr[0].value(d);
                for (int i = 1; i < univariateFunctionArr.length; i++) {
                    value *= univariateFunctionArr[i].value(d);
                }
                return value;
            }
        };
    }

    public static UnivariateDifferentiableFunction multiply(final UnivariateDifferentiableFunction... univariateDifferentiableFunctionArr) {
        return new UnivariateDifferentiableFunction() {
            public double value(double d) {
                double value = univariateDifferentiableFunctionArr[0].value(d);
                for (int i = 1; i < univariateDifferentiableFunctionArr.length; i++) {
                    value *= univariateDifferentiableFunctionArr[i].value(d);
                }
                return value;
            }

            public DerivativeStructure value(DerivativeStructure derivativeStructure) {
                DerivativeStructure value = univariateDifferentiableFunctionArr[0].value(derivativeStructure);
                for (int i = 1; i < univariateDifferentiableFunctionArr.length; i++) {
                    value = value.multiply(univariateDifferentiableFunctionArr[i].value(derivativeStructure));
                }
                return value;
            }
        };
    }

    public static DifferentiableUnivariateFunction multiply(final DifferentiableUnivariateFunction... differentiableUnivariateFunctionArr) {
        return new DifferentiableUnivariateFunction() {
            public double value(double d) {
                double value = differentiableUnivariateFunctionArr[0].value(d);
                for (int i = 1; i < differentiableUnivariateFunctionArr.length; i++) {
                    value *= differentiableUnivariateFunctionArr[i].value(d);
                }
                return value;
            }

            public UnivariateFunction derivative() {
                return new UnivariateFunction() {
                    public double value(double d) {
                        double d2 = 0.0d;
                        for (int i = 0; i < differentiableUnivariateFunctionArr.length; i++) {
                            double value = differentiableUnivariateFunctionArr[i].derivative().value(d);
                            for (int i2 = 0; i2 < differentiableUnivariateFunctionArr.length; i2++) {
                                if (i != i2) {
                                    value *= differentiableUnivariateFunctionArr[i2].value(d);
                                }
                            }
                            d2 += value;
                        }
                        return d2;
                    }
                };
            }
        };
    }

    public static UnivariateFunction combine(final BivariateFunction bivariateFunction, final UnivariateFunction univariateFunction, final UnivariateFunction univariateFunction2) {
        return new UnivariateFunction() {
            public double value(double d) {
                return bivariateFunction.value(univariateFunction.value(d), univariateFunction2.value(d));
            }
        };
    }

    public static MultivariateFunction collector(final BivariateFunction bivariateFunction, final UnivariateFunction univariateFunction, final double d) {
        return new MultivariateFunction() {
            public double value(double[] dArr) {
                double value = bivariateFunction.value(d, univariateFunction.value(dArr[0]));
                for (int i = 1; i < dArr.length; i++) {
                    value = bivariateFunction.value(value, univariateFunction.value(dArr[i]));
                }
                return value;
            }
        };
    }

    public static MultivariateFunction collector(BivariateFunction bivariateFunction, double d) {
        return collector(bivariateFunction, new Identity(), d);
    }

    public static UnivariateFunction fix1stArgument(final BivariateFunction bivariateFunction, final double d) {
        return new UnivariateFunction() {
            public double value(double d) {
                return bivariateFunction.value(d, d);
            }
        };
    }

    public static UnivariateFunction fix2ndArgument(final BivariateFunction bivariateFunction, final double d) {
        return new UnivariateFunction() {
            public double value(double d) {
                return bivariateFunction.value(d, d);
            }
        };
    }

    public static double[] sample(UnivariateFunction univariateFunction, double d, double d2, int i) {
        if (i <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NOT_POSITIVE_NUMBER_OF_SAMPLES, Integer.valueOf(i));
        }
        if (d >= d2) {
            throw new NumberIsTooLargeException(Double.valueOf(d), Double.valueOf(d2), false);
        }
        double[] dArr = new double[i];
        double d3 = (d2 - d) / ((double) i);
        for (int i2 = 0; i2 < i; i2++) {
            dArr[i2] = univariateFunction.value((((double) i2) * d3) + d);
        }
        return dArr;
    }

    @Deprecated
    public static DifferentiableUnivariateFunction toDifferentiableUnivariateFunction(final UnivariateDifferentiableFunction univariateDifferentiableFunction) {
        return new DifferentiableUnivariateFunction() {
            public double value(double d) {
                return univariateDifferentiableFunction.value(d);
            }

            public UnivariateFunction derivative() {
                return new UnivariateFunction() {
                    public double value(double d) {
                        UnivariateDifferentiableFunction univariateDifferentiableFunction = univariateDifferentiableFunction;
                        DerivativeStructure derivativeStructure = new DerivativeStructure(1, 1, 0, d);
                        return univariateDifferentiableFunction.value(derivativeStructure).getPartialDerivative(1);
                    }
                };
            }
        };
    }

    @Deprecated
    public static UnivariateDifferentiableFunction toUnivariateDifferential(final DifferentiableUnivariateFunction differentiableUnivariateFunction) {
        return new UnivariateDifferentiableFunction() {
            public double value(double d) {
                return differentiableUnivariateFunction.value(d);
            }

            public DerivativeStructure value(DerivativeStructure derivativeStructure) throws NumberIsTooLargeException {
                switch (derivativeStructure.getOrder()) {
                    case 0:
                        return new DerivativeStructure(derivativeStructure.getFreeParameters(), 0, differentiableUnivariateFunction.value(derivativeStructure.getValue()));
                    case 1:
                        int freeParameters = derivativeStructure.getFreeParameters();
                        double[] dArr = new double[(freeParameters + 1)];
                        dArr[0] = differentiableUnivariateFunction.value(derivativeStructure.getValue());
                        double value = differentiableUnivariateFunction.derivative().value(derivativeStructure.getValue());
                        int[] iArr = new int[freeParameters];
                        int i = 0;
                        while (i < freeParameters) {
                            iArr[i] = 1;
                            int i2 = i + 1;
                            dArr[i2] = derivativeStructure.getPartialDerivative(iArr) * value;
                            iArr[i] = 0;
                            i = i2;
                        }
                        return new DerivativeStructure(freeParameters, 1, dArr);
                    default:
                        throw new NumberIsTooLargeException(Integer.valueOf(derivativeStructure.getOrder()), Integer.valueOf(1), true);
                }
            }
        };
    }

    @Deprecated
    public static DifferentiableMultivariateFunction toDifferentiableMultivariateFunction(final MultivariateDifferentiableFunction multivariateDifferentiableFunction) {
        return new DifferentiableMultivariateFunction() {
            public double value(double[] dArr) {
                return multivariateDifferentiableFunction.value(dArr);
            }

            public MultivariateFunction partialDerivative(final int i) {
                return new MultivariateFunction() {
                    public double value(double[] dArr) {
                        int length = dArr.length;
                        DerivativeStructure[] derivativeStructureArr = new DerivativeStructure[length];
                        for (int i = 0; i < length; i++) {
                            if (i == i) {
                                DerivativeStructure derivativeStructure = new DerivativeStructure(1, 1, 0, dArr[i]);
                                derivativeStructureArr[i] = derivativeStructure;
                            } else {
                                derivativeStructureArr[i] = new DerivativeStructure(1, 1, dArr[i]);
                            }
                        }
                        return multivariateDifferentiableFunction.value(derivativeStructureArr).getPartialDerivative(1);
                    }
                };
            }

            public MultivariateVectorFunction gradient() {
                return new MultivariateVectorFunction() {
                    public double[] value(double[] dArr) {
                        int length = dArr.length;
                        DerivativeStructure[] derivativeStructureArr = new DerivativeStructure[length];
                        for (int i = 0; i < length; i++) {
                            DerivativeStructure derivativeStructure = new DerivativeStructure(length, 1, i, dArr[i]);
                            derivativeStructureArr[i] = derivativeStructure;
                        }
                        DerivativeStructure value = multivariateDifferentiableFunction.value(derivativeStructureArr);
                        double[] dArr2 = new double[length];
                        int[] iArr = new int[length];
                        for (int i2 = 0; i2 < length; i2++) {
                            iArr[i2] = 1;
                            dArr2[i2] = value.getPartialDerivative(iArr);
                            iArr[i2] = 0;
                        }
                        return dArr2;
                    }
                };
            }
        };
    }

    @Deprecated
    public static MultivariateDifferentiableFunction toMultivariateDifferentiableFunction(final DifferentiableMultivariateFunction differentiableMultivariateFunction) {
        return new MultivariateDifferentiableFunction() {
            public double value(double[] dArr) {
                return differentiableMultivariateFunction.value(dArr);
            }

            public DerivativeStructure value(DerivativeStructure[] derivativeStructureArr) throws DimensionMismatchException, NumberIsTooLargeException {
                DerivativeStructure[] derivativeStructureArr2 = derivativeStructureArr;
                int freeParameters = derivativeStructureArr2[0].getFreeParameters();
                int order = derivativeStructureArr2[0].getOrder();
                int i = 1;
                int length = derivativeStructureArr2.length;
                if (order > 1) {
                    throw new NumberIsTooLargeException(Integer.valueOf(order), Integer.valueOf(1), true);
                }
                int i2 = 0;
                while (i2 < length) {
                    if (derivativeStructureArr2[i2].getFreeParameters() != freeParameters) {
                        throw new DimensionMismatchException(derivativeStructureArr2[i2].getFreeParameters(), freeParameters);
                    } else if (derivativeStructureArr2[i2].getOrder() != order) {
                        throw new DimensionMismatchException(derivativeStructureArr2[i2].getOrder(), order);
                    } else {
                        i2++;
                    }
                }
                double[] dArr = new double[length];
                for (int i3 = 0; i3 < length; i3++) {
                    dArr[i3] = derivativeStructureArr2[i3].getValue();
                }
                double value = differentiableMultivariateFunction.value(dArr);
                double[] value2 = differentiableMultivariateFunction.gradient().value(dArr);
                double[] dArr2 = new double[(freeParameters + 1)];
                dArr2[0] = value;
                int[] iArr = new int[freeParameters];
                int i4 = 0;
                while (i4 < freeParameters) {
                    iArr[i4] = i;
                    for (int i5 = 0; i5 < length; i5++) {
                        int i6 = i4 + 1;
                        dArr2[i6] = dArr2[i6] + (value2[i5] * derivativeStructureArr2[i5].getPartialDerivative(iArr));
                    }
                    iArr[i4] = 0;
                    i4++;
                    i = 1;
                }
                return new DerivativeStructure(freeParameters, order, dArr2);
            }
        };
    }

    @Deprecated
    public static DifferentiableMultivariateVectorFunction toDifferentiableMultivariateVectorFunction(final MultivariateDifferentiableVectorFunction multivariateDifferentiableVectorFunction) {
        return new DifferentiableMultivariateVectorFunction() {
            public double[] value(double[] dArr) {
                return multivariateDifferentiableVectorFunction.value(dArr);
            }

            public MultivariateMatrixFunction jacobian() {
                return new MultivariateMatrixFunction() {
                    public double[][] value(double[] dArr) {
                        int length = dArr.length;
                        DerivativeStructure[] derivativeStructureArr = new DerivativeStructure[length];
                        for (int i = 0; i < length; i++) {
                            DerivativeStructure derivativeStructure = new DerivativeStructure(length, 1, i, dArr[i]);
                            derivativeStructureArr[i] = derivativeStructure;
                        }
                        DerivativeStructure[] value = multivariateDifferentiableVectorFunction.value(derivativeStructureArr);
                        double[][] dArr2 = (double[][]) Array.newInstance(double.class, new int[]{value.length, length});
                        int[] iArr = new int[length];
                        for (int i2 = 0; i2 < value.length; i2++) {
                            for (int i3 = 0; i3 < length; i3++) {
                                iArr[i3] = 1;
                                dArr2[i2][i3] = value[i2].getPartialDerivative(iArr);
                                iArr[i3] = 0;
                            }
                        }
                        return dArr2;
                    }
                };
            }
        };
    }

    @Deprecated
    public static MultivariateDifferentiableVectorFunction toMultivariateDifferentiableVectorFunction(final DifferentiableMultivariateVectorFunction differentiableMultivariateVectorFunction) {
        return new MultivariateDifferentiableVectorFunction() {
            public double[] value(double[] dArr) {
                return differentiableMultivariateVectorFunction.value(dArr);
            }

            public DerivativeStructure[] value(DerivativeStructure[] derivativeStructureArr) throws DimensionMismatchException, NumberIsTooLargeException {
                DerivativeStructure[] derivativeStructureArr2 = derivativeStructureArr;
                int freeParameters = derivativeStructureArr2[0].getFreeParameters();
                int order = derivativeStructureArr2[0].getOrder();
                int i = 1;
                int length = derivativeStructureArr2.length;
                if (order > 1) {
                    throw new NumberIsTooLargeException(Integer.valueOf(order), Integer.valueOf(1), true);
                }
                int i2 = 0;
                while (i2 < length) {
                    if (derivativeStructureArr2[i2].getFreeParameters() != freeParameters) {
                        throw new DimensionMismatchException(derivativeStructureArr2[i2].getFreeParameters(), freeParameters);
                    } else if (derivativeStructureArr2[i2].getOrder() != order) {
                        throw new DimensionMismatchException(derivativeStructureArr2[i2].getOrder(), order);
                    } else {
                        i2++;
                    }
                }
                double[] dArr = new double[length];
                for (int i3 = 0; i3 < length; i3++) {
                    dArr[i3] = derivativeStructureArr2[i3].getValue();
                }
                double[] value = differentiableMultivariateVectorFunction.value(dArr);
                double[][] value2 = differentiableMultivariateVectorFunction.jacobian().value(dArr);
                DerivativeStructure[] derivativeStructureArr3 = new DerivativeStructure[value.length];
                int i4 = 0;
                while (i4 < derivativeStructureArr3.length) {
                    double[] dArr2 = new double[(freeParameters + 1)];
                    dArr2[0] = value[i4];
                    int[] iArr = new int[freeParameters];
                    int i5 = 0;
                    while (i5 < freeParameters) {
                        iArr[i5] = i;
                        for (int i6 = 0; i6 < length; i6++) {
                            int i7 = i5 + 1;
                            dArr2[i7] = dArr2[i7] + (value2[i4][i6] * derivativeStructureArr2[i6].getPartialDerivative(iArr));
                        }
                        iArr[i5] = 0;
                        i5++;
                        i = 1;
                    }
                    derivativeStructureArr3[i4] = new DerivativeStructure(freeParameters, order, dArr2);
                    i4++;
                    i = 1;
                }
                return derivativeStructureArr3;
            }
        };
    }
}
