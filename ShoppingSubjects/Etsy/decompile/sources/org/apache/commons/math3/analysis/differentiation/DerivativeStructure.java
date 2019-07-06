package org.apache.commons.math3.analysis.differentiation;

import java.io.Serializable;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.util.FastMath;

public class DerivativeStructure implements Serializable, FieldElement<DerivativeStructure> {
    private static final long serialVersionUID = 20120730;
    /* access modifiers changed from: private */
    public transient DSCompiler compiler;
    private final double[] data;

    private static class DataTransferObject implements Serializable {
        private static final long serialVersionUID = 20120730;
        private final double[] data;
        private final int order;
        private final int variables;

        public DataTransferObject(int i, int i2, double[] dArr) {
            this.variables = i;
            this.order = i2;
            this.data = dArr;
        }

        private Object readResolve() {
            return new DerivativeStructure(this.variables, this.order, this.data);
        }
    }

    private DerivativeStructure(DSCompiler dSCompiler) {
        this.compiler = dSCompiler;
        this.data = new double[dSCompiler.getSize()];
    }

    public DerivativeStructure(int i, int i2) {
        this(DSCompiler.getCompiler(i, i2));
    }

    public DerivativeStructure(int i, int i2, double d) {
        this(i, i2);
        this.data[0] = d;
    }

    public DerivativeStructure(int i, int i2, int i3, double d) throws NumberIsTooLargeException {
        this(i, i2, d);
        if (i3 >= i) {
            throw new NumberIsTooLargeException(Integer.valueOf(i3), Integer.valueOf(i), false);
        } else if (i2 > 0) {
            this.data[DSCompiler.getCompiler(i3, i2).getSize()] = 1.0d;
        }
    }

    public DerivativeStructure(double d, DerivativeStructure derivativeStructure, double d2, DerivativeStructure derivativeStructure2) throws DimensionMismatchException {
        DerivativeStructure derivativeStructure3 = derivativeStructure;
        DerivativeStructure derivativeStructure4 = derivativeStructure2;
        this(derivativeStructure3.compiler);
        this.compiler.checkCompatibility(derivativeStructure4.compiler);
        this.compiler.linearCombination(d, derivativeStructure3.data, 0, d2, derivativeStructure4.data, 0, this.data, 0);
    }

    public DerivativeStructure(double d, DerivativeStructure derivativeStructure, double d2, DerivativeStructure derivativeStructure2, double d3, DerivativeStructure derivativeStructure3) throws DimensionMismatchException {
        DerivativeStructure derivativeStructure4 = derivativeStructure;
        DerivativeStructure derivativeStructure5 = derivativeStructure2;
        DerivativeStructure derivativeStructure6 = derivativeStructure3;
        this(derivativeStructure4.compiler);
        this.compiler.checkCompatibility(derivativeStructure5.compiler);
        this.compiler.checkCompatibility(derivativeStructure6.compiler);
        this.compiler.linearCombination(d, derivativeStructure4.data, 0, d2, derivativeStructure5.data, 0, d3, derivativeStructure6.data, 0, this.data, 0);
    }

    public DerivativeStructure(double d, DerivativeStructure derivativeStructure, double d2, DerivativeStructure derivativeStructure2, double d3, DerivativeStructure derivativeStructure3, double d4, DerivativeStructure derivativeStructure4) throws DimensionMismatchException {
        DerivativeStructure derivativeStructure5 = derivativeStructure;
        DerivativeStructure derivativeStructure6 = derivativeStructure2;
        DerivativeStructure derivativeStructure7 = derivativeStructure3;
        DerivativeStructure derivativeStructure8 = derivativeStructure4;
        this(derivativeStructure5.compiler);
        this.compiler.checkCompatibility(derivativeStructure6.compiler);
        this.compiler.checkCompatibility(derivativeStructure7.compiler);
        this.compiler.checkCompatibility(derivativeStructure8.compiler);
        this.compiler.linearCombination(d, derivativeStructure5.data, 0, d2, derivativeStructure6.data, 0, d3, derivativeStructure7.data, 0, d4, derivativeStructure8.data, 0, this.data, 0);
    }

    public DerivativeStructure(int i, int i2, double... dArr) throws DimensionMismatchException {
        this(i, i2);
        if (dArr.length != this.data.length) {
            throw new DimensionMismatchException(dArr.length, this.data.length);
        }
        System.arraycopy(dArr, 0, this.data, 0, this.data.length);
    }

    private DerivativeStructure(DerivativeStructure derivativeStructure) {
        this.compiler = derivativeStructure.compiler;
        this.data = (double[]) derivativeStructure.data.clone();
    }

    public int getFreeParameters() {
        return this.compiler.getFreeParameters();
    }

    public int getOrder() {
        return this.compiler.getOrder();
    }

    public double getValue() {
        return this.data[0];
    }

    public double getPartialDerivative(int... iArr) throws DimensionMismatchException, NumberIsTooLargeException {
        return this.data[this.compiler.getPartialDerivativeIndex(iArr)];
    }

    public double[] getAllDerivatives() {
        return (double[]) this.data.clone();
    }

    public DerivativeStructure add(double d) {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this);
        double[] dArr = derivativeStructure.data;
        dArr[0] = dArr[0] + d;
        return derivativeStructure;
    }

    public DerivativeStructure add(DerivativeStructure derivativeStructure) throws DimensionMismatchException {
        this.compiler.checkCompatibility(derivativeStructure.compiler);
        DerivativeStructure derivativeStructure2 = new DerivativeStructure(this);
        this.compiler.add(this.data, 0, derivativeStructure.data, 0, derivativeStructure2.data, 0);
        return derivativeStructure2;
    }

    public DerivativeStructure subtract(double d) {
        return add(-d);
    }

    public DerivativeStructure subtract(DerivativeStructure derivativeStructure) throws DimensionMismatchException {
        this.compiler.checkCompatibility(derivativeStructure.compiler);
        DerivativeStructure derivativeStructure2 = new DerivativeStructure(this);
        this.compiler.subtract(this.data, 0, derivativeStructure.data, 0, derivativeStructure2.data, 0);
        return derivativeStructure2;
    }

    public DerivativeStructure multiply(int i) {
        return multiply((double) i);
    }

    public DerivativeStructure multiply(double d) {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this);
        for (int i = 0; i < derivativeStructure.data.length; i++) {
            double[] dArr = derivativeStructure.data;
            dArr[i] = dArr[i] * d;
        }
        return derivativeStructure;
    }

    public DerivativeStructure multiply(DerivativeStructure derivativeStructure) throws DimensionMismatchException {
        this.compiler.checkCompatibility(derivativeStructure.compiler);
        DerivativeStructure derivativeStructure2 = new DerivativeStructure(this.compiler);
        this.compiler.multiply(this.data, 0, derivativeStructure.data, 0, derivativeStructure2.data, 0);
        return derivativeStructure2;
    }

    public DerivativeStructure divide(double d) {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this);
        for (int i = 0; i < derivativeStructure.data.length; i++) {
            double[] dArr = derivativeStructure.data;
            dArr[i] = dArr[i] / d;
        }
        return derivativeStructure;
    }

    public DerivativeStructure divide(DerivativeStructure derivativeStructure) throws DimensionMismatchException {
        this.compiler.checkCompatibility(derivativeStructure.compiler);
        DerivativeStructure derivativeStructure2 = new DerivativeStructure(this.compiler);
        this.compiler.divide(this.data, 0, derivativeStructure.data, 0, derivativeStructure2.data, 0);
        return derivativeStructure2;
    }

    public DerivativeStructure remainder(double d) {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this);
        derivativeStructure.data[0] = derivativeStructure.data[0] % d;
        return derivativeStructure;
    }

    public DerivativeStructure remainder(DerivativeStructure derivativeStructure) throws DimensionMismatchException {
        this.compiler.checkCompatibility(derivativeStructure.compiler);
        DerivativeStructure derivativeStructure2 = new DerivativeStructure(this.compiler);
        this.compiler.remainder(this.data, 0, derivativeStructure.data, 0, derivativeStructure2.data, 0);
        return derivativeStructure2;
    }

    public DerivativeStructure negate() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        for (int i = 0; i < derivativeStructure.data.length; i++) {
            derivativeStructure.data[i] = -this.data[i];
        }
        return derivativeStructure;
    }

    public DerivativeStructure abs() {
        return Double.doubleToLongBits(this.data[0]) < 0 ? negate() : this;
    }

    public DerivativeStructure ceil() {
        return new DerivativeStructure(this.compiler.getFreeParameters(), this.compiler.getOrder(), FastMath.ceil(this.data[0]));
    }

    public DerivativeStructure floor() {
        return new DerivativeStructure(this.compiler.getFreeParameters(), this.compiler.getOrder(), FastMath.floor(this.data[0]));
    }

    public DerivativeStructure rint() {
        return new DerivativeStructure(this.compiler.getFreeParameters(), this.compiler.getOrder(), FastMath.rint(this.data[0]));
    }

    public long round() {
        return FastMath.round(this.data[0]);
    }

    public DerivativeStructure signum() {
        return new DerivativeStructure(this.compiler.getFreeParameters(), this.compiler.getOrder(), FastMath.signum(this.data[0]));
    }

    public DerivativeStructure copySign(double d) {
        long doubleToLongBits = Double.doubleToLongBits(this.data[0]);
        long doubleToLongBits2 = Double.doubleToLongBits(d);
        if ((doubleToLongBits < 0 || doubleToLongBits2 < 0) && (doubleToLongBits >= 0 || doubleToLongBits2 >= 0)) {
            return negate();
        }
        return this;
    }

    public int getExponent() {
        return FastMath.getExponent(this.data[0]);
    }

    public DerivativeStructure scalb(int i) {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        for (int i2 = 0; i2 < derivativeStructure.data.length; i2++) {
            derivativeStructure.data[i2] = FastMath.scalb(this.data[i2], i);
        }
        return derivativeStructure;
    }

    public static DerivativeStructure hypot(DerivativeStructure derivativeStructure, DerivativeStructure derivativeStructure2) throws DimensionMismatchException {
        derivativeStructure.compiler.checkCompatibility(derivativeStructure2.compiler);
        if (Double.isInfinite(derivativeStructure.data[0]) || Double.isInfinite(derivativeStructure2.data[0])) {
            return new DerivativeStructure(derivativeStructure.compiler.getFreeParameters(), derivativeStructure.compiler.getFreeParameters(), Double.POSITIVE_INFINITY);
        }
        if (Double.isNaN(derivativeStructure.data[0]) || Double.isNaN(derivativeStructure2.data[0])) {
            return new DerivativeStructure(derivativeStructure.compiler.getFreeParameters(), derivativeStructure.compiler.getFreeParameters(), Double.NaN);
        }
        int exponent = derivativeStructure.getExponent();
        int exponent2 = derivativeStructure2.getExponent();
        if (exponent > exponent2 + 27) {
            return derivativeStructure.abs();
        }
        if (exponent2 > exponent + 27) {
            return derivativeStructure2.abs();
        }
        int i = (exponent + exponent2) / 2;
        int i2 = -i;
        DerivativeStructure scalb = derivativeStructure.scalb(i2);
        DerivativeStructure scalb2 = derivativeStructure2.scalb(i2);
        return scalb.multiply(scalb).add(scalb2.multiply(scalb2)).sqrt().scalb(i);
    }

    public DerivativeStructure compose(double... dArr) {
        if (dArr.length != getOrder() + 1) {
            throw new DimensionMismatchException(dArr.length, getOrder() + 1);
        }
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.compose(this.data, 0, dArr, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure reciprocal() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.pow(this.data, 0, -1, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure sqrt() {
        return rootN(2);
    }

    public DerivativeStructure cbrt() {
        return rootN(3);
    }

    public DerivativeStructure rootN(int i) {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.rootN(this.data, 0, i, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public Field<DerivativeStructure> getField() {
        return new Field<DerivativeStructure>() {
            public DerivativeStructure getZero() {
                return new DerivativeStructure(DerivativeStructure.this.compiler.getFreeParameters(), DerivativeStructure.this.compiler.getOrder(), 0.0d);
            }

            public DerivativeStructure getOne() {
                return new DerivativeStructure(DerivativeStructure.this.compiler.getFreeParameters(), DerivativeStructure.this.compiler.getOrder(), 1.0d);
            }

            public Class<? extends FieldElement<DerivativeStructure>> getRuntimeClass() {
                return DerivativeStructure.class;
            }
        };
    }

    public DerivativeStructure pow(double d) {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.pow(this.data, 0, d, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure pow(int i) {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.pow(this.data, 0, i, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure pow(DerivativeStructure derivativeStructure) throws DimensionMismatchException {
        this.compiler.checkCompatibility(derivativeStructure.compiler);
        DerivativeStructure derivativeStructure2 = new DerivativeStructure(this.compiler);
        this.compiler.pow(this.data, 0, derivativeStructure.data, 0, derivativeStructure2.data, 0);
        return derivativeStructure2;
    }

    public DerivativeStructure exp() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.exp(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure expm1() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.expm1(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure log() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.log(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure log1p() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.log1p(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure log10() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.log10(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure cos() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.cos(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure sin() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.sin(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure tan() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.tan(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure acos() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.acos(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure asin() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.asin(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure atan() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.atan(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public static DerivativeStructure atan2(DerivativeStructure derivativeStructure, DerivativeStructure derivativeStructure2) throws DimensionMismatchException {
        derivativeStructure.compiler.checkCompatibility(derivativeStructure2.compiler);
        DerivativeStructure derivativeStructure3 = new DerivativeStructure(derivativeStructure.compiler);
        derivativeStructure.compiler.atan2(derivativeStructure.data, 0, derivativeStructure2.data, 0, derivativeStructure3.data, 0);
        return derivativeStructure3;
    }

    public DerivativeStructure cosh() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.cosh(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure sinh() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.sinh(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure tanh() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.tanh(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure acosh() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.acosh(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure asinh() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.asinh(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure atanh() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.atanh(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure toDegrees() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        for (int i = 0; i < derivativeStructure.data.length; i++) {
            derivativeStructure.data[i] = FastMath.toDegrees(this.data[i]);
        }
        return derivativeStructure;
    }

    public DerivativeStructure toRadians() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        for (int i = 0; i < derivativeStructure.data.length; i++) {
            derivativeStructure.data[i] = FastMath.toRadians(this.data[i]);
        }
        return derivativeStructure;
    }

    public double taylor(double... dArr) {
        return this.compiler.taylor(this.data, 0, dArr);
    }

    private Object writeReplace() {
        return new DataTransferObject(this.compiler.getFreeParameters(), this.compiler.getOrder(), this.data);
    }
}
