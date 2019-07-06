package org.apache.commons.math3.geometry.euclidean.twod;

import java.awt.geom.AffineTransform;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
import org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.partitioning.Embedding;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.geometry.partitioning.Transform;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class Line implements Embedding<Euclidean2D, Euclidean1D>, Hyperplane<Euclidean2D> {
    private double angle;
    /* access modifiers changed from: private */
    public double cos;
    /* access modifiers changed from: private */
    public double originOffset;
    /* access modifiers changed from: private */
    public double sin;

    private static class LineTransform implements Transform<Euclidean2D, Euclidean1D> {
        private double c11 = ((this.cXX * this.cYY) - (this.cYX * this.cXY));
        private double c1X = ((this.cXX * this.cY1) - (this.cYX * this.cX1));
        private double c1Y = ((this.cXY * this.cY1) - (this.cYY * this.cX1));
        private double cX1;
        private double cXX;
        private double cXY;
        private double cY1;
        private double cYX;
        private double cYY;

        public LineTransform(AffineTransform affineTransform) throws MathIllegalArgumentException {
            double[] dArr = new double[6];
            affineTransform.getMatrix(dArr);
            this.cXX = dArr[0];
            this.cXY = dArr[2];
            this.cX1 = dArr[4];
            this.cYX = dArr[1];
            this.cYY = dArr[3];
            this.cY1 = dArr[5];
            if (FastMath.abs(this.c11) < 1.0E-20d) {
                throw new MathIllegalArgumentException(LocalizedFormats.NON_INVERTIBLE_TRANSFORM, new Object[0]);
            }
        }

        public Vector2D apply(Vector<Euclidean2D> vector) {
            Vector2D vector2D = (Vector2D) vector;
            double x = vector2D.getX();
            double y = vector2D.getY();
            return new Vector2D((this.cXX * x) + (this.cXY * y) + this.cX1, (this.cYX * x) + (this.cYY * y) + this.cY1);
        }

        public Line apply(Hyperplane<Euclidean2D> hyperplane) {
            Line line = (Line) hyperplane;
            double access$000 = (this.cXX * line.cos) + (this.cXY * line.sin);
            double access$0002 = (this.cYX * line.cos) + (this.cYY * line.sin);
            double sqrt = 1.0d / FastMath.sqrt((access$0002 * access$0002) + (access$000 * access$000));
            Line line2 = new Line(3.141592653589793d + FastMath.atan2(-access$0002, -access$000), sqrt * access$000, sqrt * access$0002, sqrt * ((this.c1X * line.cos) + (this.c1Y * line.sin) + (this.c11 * line.originOffset)));
            return line2;
        }

        public SubHyperplane<Euclidean1D> apply(SubHyperplane<Euclidean1D> subHyperplane, Hyperplane<Euclidean2D> hyperplane, Hyperplane<Euclidean2D> hyperplane2) {
            OrientedPoint orientedPoint = (OrientedPoint) subHyperplane.getHyperplane();
            return new OrientedPoint(((Line) hyperplane2).toSubSpace((Vector) apply((Vector) ((Line) hyperplane).toSpace((Vector) orientedPoint.getLocation()))), orientedPoint.isDirect()).wholeHyperplane();
        }
    }

    public Line(Vector2D vector2D, Vector2D vector2D2) {
        reset(vector2D, vector2D2);
    }

    public Line(Vector2D vector2D, double d) {
        reset(vector2D, d);
    }

    private Line(double d, double d2, double d3, double d4) {
        this.angle = d;
        this.cos = d2;
        this.sin = d3;
        this.originOffset = d4;
    }

    public Line(Line line) {
        this.angle = MathUtils.normalizeAngle(line.angle, 3.141592653589793d);
        this.cos = FastMath.cos(this.angle);
        this.sin = FastMath.sin(this.angle);
        this.originOffset = line.originOffset;
    }

    public Line copySelf() {
        return new Line(this);
    }

    public void reset(Vector2D vector2D, Vector2D vector2D2) {
        double x = vector2D2.getX() - vector2D.getX();
        double y = vector2D2.getY() - vector2D.getY();
        double hypot = FastMath.hypot(x, y);
        if (hypot == 0.0d) {
            this.angle = 0.0d;
            this.cos = 1.0d;
            this.sin = 0.0d;
            this.originOffset = vector2D.getY();
            return;
        }
        this.angle = 3.141592653589793d + FastMath.atan2(-y, -x);
        this.cos = FastMath.cos(this.angle);
        this.sin = FastMath.sin(this.angle);
        this.originOffset = ((vector2D2.getX() * vector2D.getY()) - (vector2D.getX() * vector2D2.getY())) / hypot;
    }

    public void reset(Vector2D vector2D, double d) {
        this.angle = MathUtils.normalizeAngle(d, 3.141592653589793d);
        this.cos = FastMath.cos(this.angle);
        this.sin = FastMath.sin(this.angle);
        this.originOffset = (this.cos * vector2D.getY()) - (this.sin * vector2D.getX());
    }

    public void revertSelf() {
        if (this.angle < 3.141592653589793d) {
            this.angle += 3.141592653589793d;
        } else {
            this.angle -= 3.141592653589793d;
        }
        this.cos = -this.cos;
        this.sin = -this.sin;
        this.originOffset = -this.originOffset;
    }

    public Line getReverse() {
        Line line = new Line(this.angle < 3.141592653589793d ? this.angle + 3.141592653589793d : this.angle - 3.141592653589793d, -this.cos, -this.sin, -this.originOffset);
        return line;
    }

    public Vector1D toSubSpace(Vector<Euclidean2D> vector) {
        Vector2D vector2D = (Vector2D) vector;
        return new Vector1D((this.cos * vector2D.getX()) + (this.sin * vector2D.getY()));
    }

    public Vector2D toSpace(Vector<Euclidean1D> vector) {
        double x = ((Vector1D) vector).getX();
        return new Vector2D((this.cos * x) - (this.originOffset * this.sin), (x * this.sin) + (this.originOffset * this.cos));
    }

    public Vector2D intersection(Line line) {
        double d = (this.sin * line.cos) - (line.sin * this.cos);
        if (FastMath.abs(d) < 1.0E-10d) {
            return null;
        }
        return new Vector2D(((this.cos * line.originOffset) - (line.cos * this.originOffset)) / d, ((this.sin * line.originOffset) - (line.sin * this.originOffset)) / d);
    }

    public SubLine wholeHyperplane() {
        return new SubLine((Hyperplane<Euclidean2D>) this, (Region<Euclidean1D>) new IntervalsSet<Euclidean1D>());
    }

    public PolygonsSet wholeSpace() {
        return new PolygonsSet();
    }

    public double getOffset(Line line) {
        return this.originOffset + ((this.cos * line.cos) + (this.sin * line.sin) > 0.0d ? -line.originOffset : line.originOffset);
    }

    public double getOffset(Vector<Euclidean2D> vector) {
        Vector2D vector2D = (Vector2D) vector;
        return ((this.sin * vector2D.getX()) - (this.cos * vector2D.getY())) + this.originOffset;
    }

    public boolean sameOrientationAs(Hyperplane<Euclidean2D> hyperplane) {
        Line line = (Line) hyperplane;
        return (this.sin * line.sin) + (this.cos * line.cos) >= 0.0d;
    }

    public Vector2D getPointAt(Vector1D vector1D, double d) {
        double x = vector1D.getX();
        double d2 = d - this.originOffset;
        return new Vector2D((this.cos * x) + (this.sin * d2), (x * this.sin) - (d2 * this.cos));
    }

    public boolean contains(Vector2D vector2D) {
        return FastMath.abs(getOffset((Vector<Euclidean2D>) vector2D)) < 1.0E-10d;
    }

    public double distance(Vector2D vector2D) {
        return FastMath.abs(getOffset((Vector<Euclidean2D>) vector2D));
    }

    public boolean isParallelTo(Line line) {
        return FastMath.abs((this.sin * line.cos) - (this.cos * line.sin)) < 1.0E-10d;
    }

    public void translateToPoint(Vector2D vector2D) {
        this.originOffset = (this.cos * vector2D.getY()) - (this.sin * vector2D.getX());
    }

    public double getAngle() {
        return MathUtils.normalizeAngle(this.angle, 3.141592653589793d);
    }

    public void setAngle(double d) {
        this.angle = MathUtils.normalizeAngle(d, 3.141592653589793d);
        this.cos = FastMath.cos(this.angle);
        this.sin = FastMath.sin(this.angle);
    }

    public double getOriginOffset() {
        return this.originOffset;
    }

    public void setOriginOffset(double d) {
        this.originOffset = d;
    }

    public static Transform<Euclidean2D, Euclidean1D> getTransform(AffineTransform affineTransform) throws MathIllegalArgumentException {
        return new LineTransform(affineTransform);
    }
}
