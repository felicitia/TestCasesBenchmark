package org.apache.commons.math3.geometry.euclidean.threed;

import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.partitioning.Embedding;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.util.FastMath;

public class Plane implements Embedding<Euclidean3D, Euclidean2D>, Hyperplane<Euclidean3D> {
    private Vector3D origin;
    private double originOffset;
    private Vector3D u;
    private Vector3D v;
    private Vector3D w;

    public Plane(Vector3D vector3D) throws MathArithmeticException {
        setNormal(vector3D);
        this.originOffset = 0.0d;
        setFrame();
    }

    public Plane(Vector3D vector3D, Vector3D vector3D2) throws MathArithmeticException {
        setNormal(vector3D2);
        this.originOffset = -vector3D.dotProduct(this.w);
        setFrame();
    }

    public Plane(Vector3D vector3D, Vector3D vector3D2, Vector3D vector3D3) throws MathArithmeticException {
        this(vector3D, vector3D2.subtract((Vector) vector3D).crossProduct(vector3D3.subtract((Vector) vector3D)));
    }

    public Plane(Plane plane) {
        this.originOffset = plane.originOffset;
        this.origin = plane.origin;
        this.u = plane.u;
        this.v = plane.v;
        this.w = plane.w;
    }

    public Plane copySelf() {
        return new Plane(this);
    }

    public void reset(Vector3D vector3D, Vector3D vector3D2) throws MathArithmeticException {
        setNormal(vector3D2);
        this.originOffset = -vector3D.dotProduct(this.w);
        setFrame();
    }

    public void reset(Plane plane) {
        this.originOffset = plane.originOffset;
        this.origin = plane.origin;
        this.u = plane.u;
        this.v = plane.v;
        this.w = plane.w;
    }

    private void setNormal(Vector3D vector3D) throws MathArithmeticException {
        double norm = vector3D.getNorm();
        if (norm < 1.0E-10d) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        this.w = new Vector3D(1.0d / norm, vector3D);
    }

    private void setFrame() {
        this.origin = new Vector3D(-this.originOffset, this.w);
        this.u = this.w.orthogonal();
        this.v = Vector3D.crossProduct(this.w, this.u);
    }

    public Vector3D getOrigin() {
        return this.origin;
    }

    public Vector3D getNormal() {
        return this.w;
    }

    public Vector3D getU() {
        return this.u;
    }

    public Vector3D getV() {
        return this.v;
    }

    public void revertSelf() {
        Vector3D vector3D = this.u;
        this.u = this.v;
        this.v = vector3D;
        this.w = this.w.negate();
        this.originOffset = -this.originOffset;
    }

    public Vector2D toSubSpace(Vector<Euclidean3D> vector) {
        return new Vector2D(vector.dotProduct(this.u), vector.dotProduct(this.v));
    }

    public Vector3D toSpace(Vector<Euclidean2D> vector) {
        Vector2D vector2D = (Vector2D) vector;
        Vector3D vector3D = new Vector3D(vector2D.getX(), this.u, vector2D.getY(), this.v, -this.originOffset, this.w);
        return vector3D;
    }

    public Vector3D getPointAt(Vector2D vector2D, double d) {
        Vector3D vector3D = new Vector3D(vector2D.getX(), this.u, vector2D.getY(), this.v, d - this.originOffset, this.w);
        return vector3D;
    }

    public boolean isSimilarTo(Plane plane) {
        double angle = Vector3D.angle(this.w, plane.w);
        return (angle < 1.0E-10d && FastMath.abs(this.originOffset - plane.originOffset) < 1.0E-10d) || (angle > 3.141592653489793d && FastMath.abs(this.originOffset + plane.originOffset) < 1.0E-10d);
    }

    public Plane rotate(Vector3D vector3D, Rotation rotation) {
        Plane plane = new Plane(vector3D.add((Vector) rotation.applyTo(this.origin.subtract((Vector) vector3D))), rotation.applyTo(this.w));
        plane.u = rotation.applyTo(this.u);
        plane.v = rotation.applyTo(this.v);
        return plane;
    }

    public Plane translate(Vector3D vector3D) {
        Plane plane = new Plane(this.origin.add((Vector) vector3D), this.w);
        plane.u = this.u;
        plane.v = this.v;
        return plane;
    }

    public Vector3D intersection(Line line) {
        Vector3D direction = line.getDirection();
        double dotProduct = this.w.dotProduct(direction);
        if (FastMath.abs(dotProduct) < 1.0E-10d) {
            return null;
        }
        Vector3D space = line.toSpace((Vector) Vector1D.ZERO);
        Vector3D vector3D = new Vector3D(1.0d, space, (-(this.originOffset + this.w.dotProduct(space))) / dotProduct, direction);
        return vector3D;
    }

    public Line intersection(Plane plane) {
        Vector3D crossProduct = Vector3D.crossProduct(this.w, plane.w);
        if (crossProduct.getNorm() < 1.0E-10d) {
            return null;
        }
        Vector3D intersection = intersection(this, plane, new Plane(crossProduct));
        return new Line(intersection, intersection.add((Vector) crossProduct));
    }

    public static Vector3D intersection(Plane plane, Plane plane2, Plane plane3) {
        Plane plane4 = plane;
        Plane plane5 = plane2;
        Plane plane6 = plane3;
        double x = plane4.w.getX();
        double y = plane4.w.getY();
        double z = plane4.w.getZ();
        double d = plane4.originOffset;
        double x2 = plane5.w.getX();
        double y2 = plane5.w.getY();
        double z2 = plane5.w.getZ();
        double d2 = plane5.originOffset;
        double x3 = plane6.w.getX();
        double d3 = d;
        double y3 = plane6.w.getY();
        double d4 = z;
        double z3 = plane6.w.getZ();
        double d5 = y;
        double d6 = plane6.originOffset;
        double d7 = (y2 * z3) - (y3 * z2);
        double d8 = z3;
        double d9 = (z2 * x3) - (z3 * x2);
        double d10 = x2;
        double d11 = (x2 * y3) - (x3 * y2);
        double d12 = d11;
        double d13 = (x * d7) + (d5 * d9) + (d4 * d11);
        if (FastMath.abs(d13) < 1.0E-10d) {
            return null;
        }
        double d14 = 1.0d / d13;
        Vector3D vector3D = new Vector3D(((((-d7) * d3) - (((d4 * y3) - (d8 * d5)) * d2)) - (((z2 * d5) - (d4 * y2)) * d6)) * d14, ((((-d9) * d3) - (((d8 * x) - (d4 * x3)) * d2)) - (((d4 * d10) - (z2 * x)) * d6)) * d14, ((((-d12) * d3) - (((x3 * d5) - (y3 * x)) * d2)) - (((y2 * x) - (d5 * d10)) * d6)) * d14);
        return vector3D;
    }

    public SubPlane wholeHyperplane() {
        return new SubPlane(this, new PolygonsSet());
    }

    public PolyhedronsSet wholeSpace() {
        return new PolyhedronsSet();
    }

    public boolean contains(Vector3D vector3D) {
        return FastMath.abs(getOffset((Vector<Euclidean3D>) vector3D)) < 1.0E-10d;
    }

    public double getOffset(Plane plane) {
        return this.originOffset + (sameOrientationAs(plane) ? -plane.originOffset : plane.originOffset);
    }

    public double getOffset(Vector<Euclidean3D> vector) {
        return vector.dotProduct(this.w) + this.originOffset;
    }

    public boolean sameOrientationAs(Hyperplane<Euclidean3D> hyperplane) {
        return ((Plane) hyperplane).w.dotProduct(this.w) > 0.0d;
    }
}
