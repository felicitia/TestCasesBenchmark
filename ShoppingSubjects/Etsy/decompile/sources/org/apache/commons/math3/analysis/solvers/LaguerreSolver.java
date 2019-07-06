package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexUtils;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

public class LaguerreSolver extends AbstractPolynomialSolver {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;
    private final ComplexSolver complexSolver;

    private class ComplexSolver {
        private ComplexSolver() {
        }

        public boolean isRoot(double d, double d2, Complex complex) {
            boolean z = false;
            if (!LaguerreSolver.this.isSequence(d, complex.getReal(), d2)) {
                return false;
            }
            if (FastMath.abs(complex.getImaginary()) <= FastMath.max(LaguerreSolver.this.getRelativeAccuracy() * complex.abs(), LaguerreSolver.this.getAbsoluteAccuracy()) || complex.abs() <= LaguerreSolver.this.getFunctionValueAccuracy()) {
                z = true;
            }
            return z;
        }

        public Complex[] solveAll(Complex[] complexArr, Complex complex) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
            if (complexArr == null) {
                throw new NullArgumentException();
            }
            int length = complexArr.length - 1;
            if (length == 0) {
                throw new NoDataException(LocalizedFormats.POLYNOMIAL);
            }
            Complex[] complexArr2 = new Complex[(length + 1)];
            for (int i = 0; i <= length; i++) {
                complexArr2[i] = complexArr[i];
            }
            Complex[] complexArr3 = new Complex[length];
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = length - i2;
                Complex[] complexArr4 = new Complex[(i3 + 1)];
                System.arraycopy(complexArr2, 0, complexArr4, 0, complexArr4.length);
                complexArr3[i2] = solve(complexArr4, complex);
                Complex complex2 = complexArr2[i3];
                for (int i4 = i3 - 1; i4 >= 0; i4--) {
                    Complex complex3 = complexArr2[i4];
                    complexArr2[i4] = complex2;
                    complex2 = complex3.add(complex2.multiply(complexArr3[i2]));
                }
            }
            return complexArr3;
        }

        public Complex solve(Complex[] complexArr, Complex complex) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
            Complex[] complexArr2 = complexArr;
            if (complexArr2 == null) {
                throw new NullArgumentException();
            }
            int length = complexArr2.length - 1;
            if (length == 0) {
                throw new NoDataException(LocalizedFormats.POLYNOMIAL);
            }
            double absoluteAccuracy = LaguerreSolver.this.getAbsoluteAccuracy();
            double relativeAccuracy = LaguerreSolver.this.getRelativeAccuracy();
            double functionValueAccuracy = LaguerreSolver.this.getFunctionValueAccuracy();
            Complex complex2 = new Complex((double) length, 0.0d);
            int i = length - 1;
            Complex complex3 = new Complex((double) i, 0.0d);
            Complex complex4 = new Complex(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
            Complex complex5 = complex;
            while (true) {
                Complex complex6 = complexArr2[length];
                int i2 = length;
                Complex complex7 = complex6;
                Complex complex8 = Complex.ZERO;
                Complex complex9 = Complex.ZERO;
                int i3 = i;
                while (i3 >= 0) {
                    complex9 = complex8.add(complex5.multiply(complex9));
                    complex8 = complex7.add(complex5.multiply(complex8));
                    int i4 = i;
                    complex7 = complexArr2[i3].add(complex5.multiply(complex7));
                    i3--;
                    i = i4;
                }
                int i5 = i;
                Complex complex10 = complex2;
                Complex complex11 = complex3;
                Complex multiply = complex9.multiply(new Complex(2.0d, 0.0d));
                if (complex5.subtract(complex4).abs() <= FastMath.max(complex5.abs() * relativeAccuracy, absoluteAccuracy) || complex7.abs() <= functionValueAccuracy) {
                    return complex5;
                }
                Complex divide = complex8.divide(complex7);
                Complex multiply2 = divide.multiply(divide);
                Complex complex12 = complex10;
                Complex subtract = complex12.multiply(multiply2.subtract(multiply.divide(complex7))).subtract(multiply2);
                Complex complex13 = complex11;
                Complex sqrt = complex13.multiply(subtract).sqrt();
                Complex add = divide.add(sqrt);
                Complex subtract2 = divide.subtract(sqrt);
                if (add.abs() > subtract2.abs()) {
                    subtract2 = add;
                }
                if (subtract2.equals(new Complex(0.0d, 0.0d))) {
                    complex5 = complex5.add(new Complex(absoluteAccuracy, absoluteAccuracy));
                    complex4 = new Complex(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
                } else {
                    complex4 = complex5;
                    complex5 = complex5.subtract(complex12.divide(subtract2));
                }
                LaguerreSolver.this.incrementEvaluationCount();
                complex3 = complex13;
                i = i5;
                complexArr2 = complexArr;
                complex2 = complex12;
                length = i2;
            }
        }
    }

    public LaguerreSolver() {
        this(1.0E-6d);
    }

    public LaguerreSolver(double d) {
        super(d);
        this.complexSolver = new ComplexSolver();
    }

    public LaguerreSolver(double d, double d2) {
        super(d, d2);
        this.complexSolver = new ComplexSolver();
    }

    public LaguerreSolver(double d, double d2, double d3) {
        super(d, d2, d3);
        this.complexSolver = new ComplexSolver();
    }

    public double doSolve() throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        double min = getMin();
        double max = getMax();
        double startValue = getStartValue();
        double functionValueAccuracy = getFunctionValueAccuracy();
        verifySequence(min, startValue, max);
        double computeObjectiveValue = computeObjectiveValue(startValue);
        if (FastMath.abs(computeObjectiveValue) <= functionValueAccuracy) {
            return startValue;
        }
        double computeObjectiveValue2 = computeObjectiveValue(min);
        if (FastMath.abs(computeObjectiveValue2) <= functionValueAccuracy) {
            return min;
        }
        if (computeObjectiveValue * computeObjectiveValue2 < 0.0d) {
            return laguerre(min, startValue, computeObjectiveValue2, computeObjectiveValue);
        }
        double d = computeObjectiveValue2;
        double d2 = computeObjectiveValue;
        double computeObjectiveValue3 = computeObjectiveValue(max);
        if (FastMath.abs(computeObjectiveValue3) <= functionValueAccuracy) {
            return max;
        }
        if (d2 * computeObjectiveValue3 < 0.0d) {
            return laguerre(startValue, max, d2, computeObjectiveValue3);
        }
        NoBracketingException noBracketingException = new NoBracketingException(min, max, d, computeObjectiveValue3);
        throw noBracketingException;
    }

    @Deprecated
    public double laguerre(double d, double d2, double d3, double d4) {
        Complex[] convertToComplex = ComplexUtils.convertToComplex(getCoefficients());
        Complex complex = new Complex(0.5d * (d + d2), 0.0d);
        Complex solve = this.complexSolver.solve(convertToComplex, complex);
        if (this.complexSolver.isRoot(d, d2, solve)) {
            return solve.getReal();
        }
        double d5 = Double.NaN;
        Complex[] solveAll = this.complexSolver.solveAll(convertToComplex, complex);
        int i = 0;
        while (true) {
            if (i >= solveAll.length) {
                break;
            }
            if (this.complexSolver.isRoot(d, d2, solveAll[i])) {
                d5 = solveAll[i].getReal();
                break;
            }
            i++;
        }
        return d5;
    }

    public Complex[] solveAllComplex(double[] dArr, double d) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
        setup(Integer.MAX_VALUE, new PolynomialFunction(dArr), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, d);
        return this.complexSolver.solveAll(ComplexUtils.convertToComplex(dArr), new Complex(d, 0.0d));
    }

    public Complex solveComplex(double[] dArr, double d) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
        setup(Integer.MAX_VALUE, new PolynomialFunction(dArr), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, d);
        return this.complexSolver.solve(ComplexUtils.convertToComplex(dArr), new Complex(d, 0.0d));
    }
}
