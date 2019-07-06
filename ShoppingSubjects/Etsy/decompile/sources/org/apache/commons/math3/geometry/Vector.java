package org.apache.commons.math3.geometry;

import java.io.Serializable;
import java.text.NumberFormat;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.geometry.Space;

public interface Vector<S extends Space> extends Serializable {
    Vector<S> add(double d, Vector<S> vector);

    Vector<S> add(Vector<S> vector);

    double distance(Vector<S> vector);

    double distance1(Vector<S> vector);

    double distanceInf(Vector<S> vector);

    double distanceSq(Vector<S> vector);

    double dotProduct(Vector<S> vector);

    double getNorm();

    double getNorm1();

    double getNormInf();

    double getNormSq();

    Space getSpace();

    Vector<S> getZero();

    boolean isInfinite();

    boolean isNaN();

    Vector<S> negate();

    Vector<S> normalize() throws MathArithmeticException;

    Vector<S> scalarMultiply(double d);

    Vector<S> subtract(double d, Vector<S> vector);

    Vector<S> subtract(Vector<S> vector);

    String toString(NumberFormat numberFormat);
}
