package org.apache.commons.math3.geometry.euclidean.oned;

import java.text.NumberFormat;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class Vector1D implements Vector<Euclidean1D> {
    public static final Vector1D NEGATIVE_INFINITY = new Vector1D(Double.NEGATIVE_INFINITY);
    public static final Vector1D NaN = new Vector1D(Double.NaN);
    public static final Vector1D ONE = new Vector1D(1.0d);
    public static final Vector1D POSITIVE_INFINITY = new Vector1D(Double.POSITIVE_INFINITY);
    public static final Vector1D ZERO = new Vector1D(0.0d);
    private static final long serialVersionUID = 7556674948671647925L;
    private final double x;

    public Vector1D(double d) {
        this.x = d;
    }

    public Vector1D(double d, Vector1D vector1D) {
        this.x = d * vector1D.x;
    }

    public Vector1D(double d, Vector1D vector1D, double d2, Vector1D vector1D2) {
        this.x = (d * vector1D.x) + (d2 * vector1D2.x);
    }

    public Vector1D(double d, Vector1D vector1D, double d2, Vector1D vector1D2, double d3, Vector1D vector1D3) {
        this.x = (d * vector1D.x) + (d2 * vector1D2.x) + (d3 * vector1D3.x);
    }

    public Vector1D(double d, Vector1D vector1D, double d2, Vector1D vector1D2, double d3, Vector1D vector1D3, double d4, Vector1D vector1D4) {
        this.x = (d * vector1D.x) + (d2 * vector1D2.x) + (d3 * vector1D3.x) + (d4 * vector1D4.x);
    }

    public double getX() {
        return this.x;
    }

    public Space getSpace() {
        return Euclidean1D.getInstance();
    }

    public Vector1D getZero() {
        return ZERO;
    }

    public double getNorm1() {
        return FastMath.abs(this.x);
    }

    public double getNorm() {
        return FastMath.abs(this.x);
    }

    public double getNormSq() {
        return this.x * this.x;
    }

    public double getNormInf() {
        return FastMath.abs(this.x);
    }

    public Vector1D add(Vector<Euclidean1D> vector) {
        return new Vector1D(this.x + ((Vector1D) vector).getX());
    }

    public Vector1D add(double d, Vector<Euclidean1D> vector) {
        return new Vector1D(this.x + (d * ((Vector1D) vector).getX()));
    }

    public Vector1D subtract(Vector<Euclidean1D> vector) {
        return new Vector1D(this.x - ((Vector1D) vector).x);
    }

    public Vector1D subtract(double d, Vector<Euclidean1D> vector) {
        return new Vector1D(this.x - (d * ((Vector1D) vector).getX()));
    }

    public Vector1D normalize() throws MathArithmeticException {
        double norm = getNorm();
        if (norm != 0.0d) {
            return scalarMultiply(1.0d / norm);
        }
        throw new MathArithmeticException(LocalizedFormats.CANNOT_NORMALIZE_A_ZERO_NORM_VECTOR, new Object[0]);
    }

    public Vector1D negate() {
        return new Vector1D(-this.x);
    }

    public Vector1D scalarMultiply(double d) {
        return new Vector1D(d * this.x);
    }

    public boolean isNaN() {
        return Double.isNaN(this.x);
    }

    public boolean isInfinite() {
        return !isNaN() && Double.isInfinite(this.x);
    }

    public double distance1(Vector<Euclidean1D> vector) {
        return FastMath.abs(((Vector1D) vector).x - this.x);
    }

    public double distance(Vector<Euclidean1D> vector) {
        return FastMath.abs(((Vector1D) vector).x - this.x);
    }

    public double distanceInf(Vector<Euclidean1D> vector) {
        return FastMath.abs(((Vector1D) vector).x - this.x);
    }

    public double distanceSq(Vector<Euclidean1D> vector) {
        double d = ((Vector1D) vector).x - this.x;
        return d * d;
    }

    public double dotProduct(Vector<Euclidean1D> vector) {
        return this.x * ((Vector1D) vector).x;
    }

    public static double distance(Vector1D vector1D, Vector1D vector1D2) {
        return vector1D.distance(vector1D2);
    }

    public static double distanceInf(Vector1D vector1D, Vector1D vector1D2) {
        return vector1D.distanceInf(vector1D2);
    }

    public static double distanceSq(Vector1D vector1D, Vector1D vector1D2) {
        return vector1D.distanceSq(vector1D2);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Vector1D)) {
            return false;
        }
        Vector1D vector1D = (Vector1D) obj;
        if (vector1D.isNaN()) {
            return isNaN();
        }
        if (this.x != vector1D.x) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (isNaN()) {
            return 7785;
        }
        return 997 * MathUtils.hash(this.x);
    }

    public String toString() {
        return Vector1DFormat.getInstance().format(this);
    }

    public String toString(NumberFormat numberFormat) {
        return new Vector1DFormat(numberFormat).format(this);
    }
}
