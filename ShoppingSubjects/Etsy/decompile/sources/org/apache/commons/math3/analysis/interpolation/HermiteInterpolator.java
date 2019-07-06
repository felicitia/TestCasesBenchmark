package org.apache.commons.math3.analysis.interpolation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableVectorFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.ArithmeticUtils;

public class HermiteInterpolator implements UnivariateDifferentiableVectorFunction {
    private final List<Double> abscissae = new ArrayList();
    private final List<double[]> bottomDiagonal = new ArrayList();
    private final List<double[]> topDiagonal = new ArrayList();

    public void addSamplePoint(double d, double[]... dArr) throws ZeroException, MathArithmeticException {
        double[][] dArr2 = dArr;
        int i = 0;
        int i2 = 0;
        while (i2 < dArr2.length) {
            double[] dArr3 = (double[]) dArr2[i2].clone();
            double d2 = 1.0d;
            if (i2 > 1) {
                double factorial = 1.0d / ((double) ArithmeticUtils.factorial(i2));
                for (int i3 = i; i3 < dArr3.length; i3++) {
                    dArr3[i3] = dArr3[i3] * factorial;
                }
            }
            int size = this.abscissae.size();
            this.bottomDiagonal.add(size - i2, dArr3);
            int i4 = i2;
            double[] dArr4 = dArr3;
            while (i4 < size) {
                i4++;
                int i5 = size - i4;
                double[] dArr5 = (double[]) this.bottomDiagonal.get(i5);
                double doubleValue = d2 / (d - ((Double) this.abscissae.get(i5)).doubleValue());
                if (Double.isInfinite(doubleValue)) {
                    LocalizedFormats localizedFormats = LocalizedFormats.DUPLICATED_ABSCISSA_DIVISION_BY_ZERO;
                    Object[] objArr = new Object[1];
                    objArr[i] = Double.valueOf(d);
                    throw new ZeroException(localizedFormats, objArr);
                }
                while (i < dArr3.length) {
                    dArr5[i] = (dArr4[i] - dArr5[i]) * doubleValue;
                    i++;
                }
                dArr4 = dArr5;
                i = 0;
                d2 = 1.0d;
            }
            this.topDiagonal.add(dArr4.clone());
            this.abscissae.add(Double.valueOf(d));
            i2++;
            i = 0;
        }
    }

    public PolynomialFunction[] getPolynomials() throws NoDataException {
        checkInterpolation();
        PolynomialFunction polynomial = polynomial(0.0d);
        PolynomialFunction[] polynomialFunctionArr = new PolynomialFunction[((double[]) this.topDiagonal.get(0)).length];
        for (int i = 0; i < polynomialFunctionArr.length; i++) {
            polynomialFunctionArr[i] = polynomial;
        }
        PolynomialFunction polynomial2 = polynomial(1.0d);
        for (int i2 = 0; i2 < this.topDiagonal.size(); i2++) {
            double[] dArr = (double[]) this.topDiagonal.get(i2);
            for (int i3 = 0; i3 < polynomialFunctionArr.length; i3++) {
                polynomialFunctionArr[i3] = polynomialFunctionArr[i3].add(polynomial2.multiply(polynomial(dArr[i3])));
            }
            polynomial2 = polynomial2.multiply(polynomial(-((Double) this.abscissae.get(i2)).doubleValue(), 1.0d));
        }
        return polynomialFunctionArr;
    }

    public double[] value(double d) throws NoDataException {
        checkInterpolation();
        double[] dArr = new double[((double[]) this.topDiagonal.get(0)).length];
        double d2 = 1.0d;
        for (int i = 0; i < this.topDiagonal.size(); i++) {
            double[] dArr2 = (double[]) this.topDiagonal.get(i);
            for (int i2 = 0; i2 < dArr.length; i2++) {
                dArr[i2] = dArr[i2] + (dArr2[i2] * d2);
            }
            d2 *= d - ((Double) this.abscissae.get(i)).doubleValue();
        }
        return dArr;
    }

    public DerivativeStructure[] value(DerivativeStructure derivativeStructure) throws NoDataException {
        checkInterpolation();
        DerivativeStructure[] derivativeStructureArr = new DerivativeStructure[((double[]) this.topDiagonal.get(0)).length];
        Arrays.fill(derivativeStructureArr, derivativeStructure.getField().getZero());
        DerivativeStructure derivativeStructure2 = (DerivativeStructure) derivativeStructure.getField().getOne();
        for (int i = 0; i < this.topDiagonal.size(); i++) {
            double[] dArr = (double[]) this.topDiagonal.get(i);
            for (int i2 = 0; i2 < derivativeStructureArr.length; i2++) {
                derivativeStructureArr[i2] = derivativeStructureArr[i2].add(derivativeStructure2.multiply(dArr[i2]));
            }
            derivativeStructure2 = derivativeStructure2.multiply(derivativeStructure.subtract(((Double) this.abscissae.get(i)).doubleValue()));
        }
        return derivativeStructureArr;
    }

    private void checkInterpolation() throws NoDataException {
        if (this.abscissae.isEmpty()) {
            throw new NoDataException(LocalizedFormats.EMPTY_INTERPOLATION_SAMPLE);
        }
    }

    private PolynomialFunction polynomial(double... dArr) {
        return new PolynomialFunction(dArr);
    }
}
