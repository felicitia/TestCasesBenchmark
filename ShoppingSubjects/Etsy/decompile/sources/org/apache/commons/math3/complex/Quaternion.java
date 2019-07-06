package org.apache.commons.math3.complex;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.Serializable;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

public final class Quaternion implements Serializable {
    public static final Quaternion I;
    public static final Quaternion IDENTITY;
    public static final Quaternion J;
    public static final Quaternion K;
    public static final Quaternion ZERO;
    private static final long serialVersionUID = 20092012;
    private final double q0;
    private final double q1;
    private final double q2;
    private final double q3;

    static {
        Quaternion quaternion = new Quaternion(1.0d, 0.0d, 0.0d, 0.0d);
        IDENTITY = quaternion;
        Quaternion quaternion2 = new Quaternion(0.0d, 0.0d, 0.0d, 0.0d);
        ZERO = quaternion2;
        Quaternion quaternion3 = new Quaternion(0.0d, 1.0d, 0.0d, 0.0d);
        I = quaternion3;
        Quaternion quaternion4 = new Quaternion(0.0d, 0.0d, 1.0d, 0.0d);
        J = quaternion4;
        Quaternion quaternion5 = new Quaternion(0.0d, 0.0d, 0.0d, 1.0d);
        K = quaternion5;
    }

    public Quaternion(double d, double d2, double d3, double d4) {
        this.q0 = d;
        this.q1 = d2;
        this.q2 = d3;
        this.q3 = d4;
    }

    public Quaternion(double d, double[] dArr) throws DimensionMismatchException {
        if (dArr.length != 3) {
            throw new DimensionMismatchException(dArr.length, 3);
        }
        this.q0 = d;
        this.q1 = dArr[0];
        this.q2 = dArr[1];
        this.q3 = dArr[2];
    }

    public Quaternion(double[] dArr) {
        this(0.0d, dArr);
    }

    public Quaternion getConjugate() {
        Quaternion quaternion = new Quaternion(this.q0, -this.q1, -this.q2, -this.q3);
        return quaternion;
    }

    public static Quaternion multiply(Quaternion quaternion, Quaternion quaternion2) {
        double q02 = quaternion.getQ0();
        double q12 = quaternion.getQ1();
        double q22 = quaternion.getQ2();
        double q32 = quaternion.getQ3();
        double q03 = quaternion2.getQ0();
        double q13 = quaternion2.getQ1();
        double q23 = quaternion2.getQ2();
        double q33 = quaternion2.getQ3();
        Quaternion quaternion3 = new Quaternion((((q02 * q03) - (q12 * q13)) - (q22 * q23)) - (q32 * q33), (((q02 * q13) + (q12 * q03)) + (q22 * q33)) - (q32 * q23), ((q02 * q23) - (q12 * q33)) + (q22 * q03) + (q32 * q13), (((q02 * q33) + (q12 * q23)) - (q22 * q13)) + (q32 * q03));
        return quaternion3;
    }

    public Quaternion multiply(Quaternion quaternion) {
        return multiply(this, quaternion);
    }

    public static Quaternion add(Quaternion quaternion, Quaternion quaternion2) {
        Quaternion quaternion3 = new Quaternion(quaternion.getQ0() + quaternion2.getQ0(), quaternion.getQ1() + quaternion2.getQ1(), quaternion.getQ2() + quaternion2.getQ2(), quaternion.getQ3() + quaternion2.getQ3());
        return quaternion3;
    }

    public Quaternion add(Quaternion quaternion) {
        return add(this, quaternion);
    }

    public static Quaternion subtract(Quaternion quaternion, Quaternion quaternion2) {
        Quaternion quaternion3 = new Quaternion(quaternion.getQ0() - quaternion2.getQ0(), quaternion.getQ1() - quaternion2.getQ1(), quaternion.getQ2() - quaternion2.getQ2(), quaternion.getQ3() - quaternion2.getQ3());
        return quaternion3;
    }

    public Quaternion subtract(Quaternion quaternion) {
        return subtract(this, quaternion);
    }

    public static double dotProduct(Quaternion quaternion, Quaternion quaternion2) {
        return (quaternion.getQ0() * quaternion2.getQ0()) + (quaternion.getQ1() * quaternion2.getQ1()) + (quaternion.getQ2() * quaternion2.getQ2()) + (quaternion.getQ3() * quaternion2.getQ3());
    }

    public double dotProduct(Quaternion quaternion) {
        return dotProduct(this, quaternion);
    }

    public double getNorm() {
        return FastMath.sqrt((this.q0 * this.q0) + (this.q1 * this.q1) + (this.q2 * this.q2) + (this.q3 * this.q3));
    }

    public Quaternion normalize() {
        double norm = getNorm();
        if (norm < Precision.SAFE_MIN) {
            throw new ZeroException(LocalizedFormats.NORM, Double.valueOf(norm));
        }
        Quaternion quaternion = new Quaternion(this.q0 / norm, this.q1 / norm, this.q2 / norm, this.q3 / norm);
        return quaternion;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Quaternion)) {
            return false;
        }
        Quaternion quaternion = (Quaternion) obj;
        if (!(this.q0 == quaternion.getQ0() && this.q1 == quaternion.getQ1() && this.q2 == quaternion.getQ2() && this.q3 == quaternion.getQ3())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 17;
        for (double hash : new double[]{this.q0, this.q1, this.q2, this.q3}) {
            i = (31 * i) + MathUtils.hash(hash);
        }
        return i;
    }

    public boolean equals(Quaternion quaternion, double d) {
        return Precision.equals(this.q0, quaternion.getQ0(), d) && Precision.equals(this.q1, quaternion.getQ1(), d) && Precision.equals(this.q2, quaternion.getQ2(), d) && Precision.equals(this.q3, quaternion.getQ3(), d);
    }

    public boolean isUnitQuaternion(double d) {
        return Precision.equals(getNorm(), 1.0d, d);
    }

    public boolean isPureQuaternion(double d) {
        return FastMath.abs(getQ0()) <= d;
    }

    public Quaternion getPositivePolarForm() {
        if (getQ0() >= 0.0d) {
            return normalize();
        }
        Quaternion normalize = normalize();
        Quaternion quaternion = new Quaternion(-normalize.getQ0(), -normalize.getQ1(), -normalize.getQ2(), -normalize.getQ3());
        return quaternion;
    }

    public Quaternion getInverse() {
        double d = (this.q0 * this.q0) + (this.q1 * this.q1) + (this.q2 * this.q2) + (this.q3 * this.q3);
        if (d < Precision.SAFE_MIN) {
            throw new ZeroException(LocalizedFormats.NORM, Double.valueOf(d));
        }
        Quaternion quaternion = new Quaternion(this.q0 / d, (-this.q1) / d, (-this.q2) / d, (-this.q3) / d);
        return quaternion;
    }

    public double getQ0() {
        return this.q0;
    }

    public double getQ1() {
        return this.q1;
    }

    public double getQ2() {
        return this.q2;
    }

    public double getQ3() {
        return this.q3;
    }

    public double getScalarPart() {
        return getQ0();
    }

    public double[] getVectorPart() {
        return new double[]{getQ1(), getQ2(), getQ3()};
    }

    public Quaternion multiply(double d) {
        Quaternion quaternion = new Quaternion(d * this.q0, this.q1 * d, this.q2 * d, this.q3 * d);
        return quaternion;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(this.q0);
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(this.q1);
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(this.q2);
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(this.q3);
        sb.append("]");
        return sb.toString();
    }
}
