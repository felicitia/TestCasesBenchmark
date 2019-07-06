package org.apache.commons.math3.complex;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class Complex implements Serializable, FieldElement<Complex> {
    public static final Complex I = new Complex(0.0d, 1.0d);
    public static final Complex INF = new Complex(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    public static final Complex NaN = new Complex(Double.NaN, Double.NaN);
    public static final Complex ONE = new Complex(1.0d, 0.0d);
    public static final Complex ZERO = new Complex(0.0d, 0.0d);
    private static final long serialVersionUID = -6195664516687396620L;
    private final double imaginary;
    private final transient boolean isInfinite;
    private final transient boolean isNaN;
    private final double real;

    public Complex(double d) {
        this(d, 0.0d);
    }

    public Complex(double d, double d2) {
        this.real = d;
        this.imaginary = d2;
        boolean z = true;
        this.isNaN = Double.isNaN(d) || Double.isNaN(d2);
        if (this.isNaN || (!Double.isInfinite(d) && !Double.isInfinite(d2))) {
            z = false;
        }
        this.isInfinite = z;
    }

    public double abs() {
        if (this.isNaN) {
            return Double.NaN;
        }
        if (isInfinite()) {
            return Double.POSITIVE_INFINITY;
        }
        if (FastMath.abs(this.real) < FastMath.abs(this.imaginary)) {
            if (this.imaginary == 0.0d) {
                return FastMath.abs(this.real);
            }
            double d = this.real / this.imaginary;
            return FastMath.abs(this.imaginary) * FastMath.sqrt(1.0d + (d * d));
        } else if (this.real == 0.0d) {
            return FastMath.abs(this.imaginary);
        } else {
            double d2 = this.imaginary / this.real;
            return FastMath.abs(this.real) * FastMath.sqrt(1.0d + (d2 * d2));
        }
    }

    public Complex add(Complex complex) throws NullArgumentException {
        MathUtils.checkNotNull(complex);
        if (this.isNaN || complex.isNaN) {
            return NaN;
        }
        return createComplex(this.real + complex.getReal(), this.imaginary + complex.getImaginary());
    }

    public Complex add(double d) {
        if (this.isNaN || Double.isNaN(d)) {
            return NaN;
        }
        return createComplex(this.real + d, this.imaginary);
    }

    public Complex conjugate() {
        if (this.isNaN) {
            return NaN;
        }
        return createComplex(this.real, -this.imaginary);
    }

    public Complex divide(Complex complex) throws NullArgumentException {
        MathUtils.checkNotNull(complex);
        if (this.isNaN || complex.isNaN) {
            return NaN;
        }
        double real2 = complex.getReal();
        double imaginary2 = complex.getImaginary();
        if (real2 == 0.0d && imaginary2 == 0.0d) {
            return NaN;
        }
        if (complex.isInfinite() && !isInfinite()) {
            return ZERO;
        }
        if (FastMath.abs(real2) < FastMath.abs(imaginary2)) {
            double d = real2 / imaginary2;
            double d2 = (real2 * d) + imaginary2;
            return createComplex(((this.real * d) + this.imaginary) / d2, ((this.imaginary * d) - this.real) / d2);
        }
        double d3 = imaginary2 / real2;
        double d4 = (imaginary2 * d3) + real2;
        return createComplex(((this.imaginary * d3) + this.real) / d4, (this.imaginary - (this.real * d3)) / d4);
    }

    public Complex divide(double d) {
        if (this.isNaN || Double.isNaN(d)) {
            return NaN;
        }
        if (d == 0.0d) {
            return NaN;
        }
        if (!Double.isInfinite(d)) {
            return createComplex(this.real / d, this.imaginary / d);
        }
        return !isInfinite() ? ZERO : NaN;
    }

    public Complex reciprocal() {
        if (this.isNaN) {
            return NaN;
        }
        if (this.real == 0.0d && this.imaginary == 0.0d) {
            return NaN;
        }
        if (this.isInfinite) {
            return ZERO;
        }
        if (FastMath.abs(this.real) < FastMath.abs(this.imaginary)) {
            double d = this.real / this.imaginary;
            double d2 = 1.0d / ((this.real * d) + this.imaginary);
            return createComplex(d * d2, -d2);
        }
        double d3 = this.imaginary / this.real;
        double d4 = 1.0d / ((this.imaginary * d3) + this.real);
        return createComplex(d4, (-d4) * d3);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Complex)) {
            return false;
        }
        Complex complex = (Complex) obj;
        if (complex.isNaN) {
            return this.isNaN;
        }
        if (!(this.real == complex.real && this.imaginary == complex.imaginary)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.isNaN) {
            return 7;
        }
        return 37 * ((17 * MathUtils.hash(this.imaginary)) + MathUtils.hash(this.real));
    }

    public double getImaginary() {
        return this.imaginary;
    }

    public double getReal() {
        return this.real;
    }

    public boolean isNaN() {
        return this.isNaN;
    }

    public boolean isInfinite() {
        return this.isInfinite;
    }

    public Complex multiply(Complex complex) throws NullArgumentException {
        MathUtils.checkNotNull(complex);
        if (this.isNaN || complex.isNaN) {
            return NaN;
        }
        if (Double.isInfinite(this.real) || Double.isInfinite(this.imaginary) || Double.isInfinite(complex.real) || Double.isInfinite(complex.imaginary)) {
            return INF;
        }
        return createComplex((this.real * complex.real) - (this.imaginary * complex.imaginary), (this.real * complex.imaginary) + (this.imaginary * complex.real));
    }

    public Complex multiply(int i) {
        if (this.isNaN) {
            return NaN;
        }
        if (Double.isInfinite(this.real) || Double.isInfinite(this.imaginary)) {
            return INF;
        }
        double d = (double) i;
        return createComplex(this.real * d, this.imaginary * d);
    }

    public Complex multiply(double d) {
        if (this.isNaN || Double.isNaN(d)) {
            return NaN;
        }
        if (Double.isInfinite(this.real) || Double.isInfinite(this.imaginary) || Double.isInfinite(d)) {
            return INF;
        }
        return createComplex(this.real * d, this.imaginary * d);
    }

    public Complex negate() {
        if (this.isNaN) {
            return NaN;
        }
        return createComplex(-this.real, -this.imaginary);
    }

    public Complex subtract(Complex complex) throws NullArgumentException {
        MathUtils.checkNotNull(complex);
        if (this.isNaN || complex.isNaN) {
            return NaN;
        }
        return createComplex(this.real - complex.getReal(), this.imaginary - complex.getImaginary());
    }

    public Complex subtract(double d) {
        if (this.isNaN || Double.isNaN(d)) {
            return NaN;
        }
        return createComplex(this.real - d, this.imaginary);
    }

    public Complex acos() {
        if (this.isNaN) {
            return NaN;
        }
        return add(sqrt1z().multiply(I)).log().multiply(I.negate());
    }

    public Complex asin() {
        if (this.isNaN) {
            return NaN;
        }
        return sqrt1z().add(multiply(I)).log().multiply(I.negate());
    }

    public Complex atan() {
        if (this.isNaN) {
            return NaN;
        }
        return add(I).divide(I.subtract(this)).log().multiply(I.divide(createComplex(2.0d, 0.0d)));
    }

    public Complex cos() {
        if (this.isNaN) {
            return NaN;
        }
        return createComplex(FastMath.cos(this.real) * FastMath.cosh(this.imaginary), (-FastMath.sin(this.real)) * FastMath.sinh(this.imaginary));
    }

    public Complex cosh() {
        if (this.isNaN) {
            return NaN;
        }
        return createComplex(FastMath.cosh(this.real) * FastMath.cos(this.imaginary), FastMath.sinh(this.real) * FastMath.sin(this.imaginary));
    }

    public Complex exp() {
        if (this.isNaN) {
            return NaN;
        }
        double exp = FastMath.exp(this.real);
        return createComplex(FastMath.cos(this.imaginary) * exp, exp * FastMath.sin(this.imaginary));
    }

    public Complex log() {
        if (this.isNaN) {
            return NaN;
        }
        return createComplex(FastMath.log(abs()), FastMath.atan2(this.imaginary, this.real));
    }

    public Complex pow(Complex complex) throws NullArgumentException {
        MathUtils.checkNotNull(complex);
        return log().multiply(complex).exp();
    }

    public Complex pow(double d) {
        return log().multiply(d).exp();
    }

    public Complex sin() {
        if (this.isNaN) {
            return NaN;
        }
        return createComplex(FastMath.sin(this.real) * FastMath.cosh(this.imaginary), FastMath.cos(this.real) * FastMath.sinh(this.imaginary));
    }

    public Complex sinh() {
        if (this.isNaN) {
            return NaN;
        }
        return createComplex(FastMath.sinh(this.real) * FastMath.cos(this.imaginary), FastMath.cosh(this.real) * FastMath.sin(this.imaginary));
    }

    public Complex sqrt() {
        if (this.isNaN) {
            return NaN;
        }
        if (this.real == 0.0d && this.imaginary == 0.0d) {
            return createComplex(0.0d, 0.0d);
        }
        double sqrt = FastMath.sqrt((FastMath.abs(this.real) + abs()) / 2.0d);
        if (this.real >= 0.0d) {
            return createComplex(sqrt, this.imaginary / (2.0d * sqrt));
        }
        return createComplex(FastMath.abs(this.imaginary) / (2.0d * sqrt), FastMath.copySign(1.0d, this.imaginary) * sqrt);
    }

    public Complex sqrt1z() {
        return createComplex(1.0d, 0.0d).subtract(multiply(this)).sqrt();
    }

    public Complex tan() {
        if (this.isNaN || Double.isInfinite(this.real)) {
            return NaN;
        }
        if (this.imaginary > 20.0d) {
            return createComplex(0.0d, 1.0d);
        }
        if (this.imaginary < -20.0d) {
            return createComplex(0.0d, -1.0d);
        }
        double d = this.real * 2.0d;
        double d2 = 2.0d * this.imaginary;
        double cos = FastMath.cos(d) + FastMath.cosh(d2);
        return createComplex(FastMath.sin(d) / cos, FastMath.sinh(d2) / cos);
    }

    public Complex tanh() {
        if (this.isNaN || Double.isInfinite(this.imaginary)) {
            return NaN;
        }
        if (this.real > 20.0d) {
            return createComplex(1.0d, 0.0d);
        }
        if (this.real < -20.0d) {
            return createComplex(-1.0d, 0.0d);
        }
        double d = this.real * 2.0d;
        double d2 = 2.0d * this.imaginary;
        double cosh = FastMath.cosh(d) + FastMath.cos(d2);
        return createComplex(FastMath.sinh(d) / cosh, FastMath.sin(d2) / cosh);
    }

    public double getArgument() {
        return FastMath.atan2(getImaginary(), getReal());
    }

    public List<Complex> nthRoot(int i) throws NotPositiveException {
        if (i <= 0) {
            throw new NotPositiveException(LocalizedFormats.CANNOT_COMPUTE_NTH_ROOT_FOR_NEGATIVE_N, Integer.valueOf(i));
        }
        ArrayList arrayList = new ArrayList();
        if (this.isNaN) {
            arrayList.add(NaN);
            return arrayList;
        } else if (isInfinite()) {
            arrayList.add(INF);
            return arrayList;
        } else {
            double d = (double) i;
            double pow = FastMath.pow(abs(), 1.0d / d);
            double argument = getArgument() / d;
            double d2 = 6.283185307179586d / d;
            for (int i2 = 0; i2 < i; i2++) {
                arrayList.add(createComplex(FastMath.cos(argument) * pow, FastMath.sin(argument) * pow));
                argument += d2;
            }
            return arrayList;
        }
    }

    /* access modifiers changed from: protected */
    public Complex createComplex(double d, double d2) {
        return new Complex(d, d2);
    }

    public static Complex valueOf(double d, double d2) {
        if (Double.isNaN(d) || Double.isNaN(d2)) {
            return NaN;
        }
        return new Complex(d, d2);
    }

    public static Complex valueOf(double d) {
        if (Double.isNaN(d)) {
            return NaN;
        }
        return new Complex(d);
    }

    /* access modifiers changed from: protected */
    public final Object readResolve() {
        return createComplex(this.real, this.imaginary);
    }

    public ComplexField getField() {
        return ComplexField.getInstance();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(this.real);
        sb.append(", ");
        sb.append(this.imaginary);
        sb.append(")");
        return sb.toString();
    }
}
