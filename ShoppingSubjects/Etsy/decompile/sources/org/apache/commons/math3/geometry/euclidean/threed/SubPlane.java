package org.apache.commons.math3.geometry.euclidean.threed;

import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet;
import org.apache.commons.math3.geometry.euclidean.twod.SubLine;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.Side;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane.SplitSubHyperplane;

public class SubPlane extends AbstractSubHyperplane<Euclidean3D, Euclidean2D> {
    public SubPlane(Hyperplane<Euclidean3D> hyperplane, Region<Euclidean2D> region) {
        super(hyperplane, region);
    }

    /* access modifiers changed from: protected */
    public AbstractSubHyperplane<Euclidean3D, Euclidean2D> buildNew(Hyperplane<Euclidean3D> hyperplane, Region<Euclidean2D> region) {
        return new SubPlane(hyperplane, region);
    }

    public Side side(Hyperplane<Euclidean3D> hyperplane) {
        Plane plane = (Plane) hyperplane;
        Plane plane2 = (Plane) getHyperplane();
        Line intersection = plane.intersection(plane2);
        if (intersection == null) {
            double offset = plane.getOffset(plane2);
            Side side = offset < -1.0E-10d ? Side.MINUS : offset > 1.0E-10d ? Side.PLUS : Side.HYPER;
            return side;
        }
        Vector2D subSpace = plane2.toSubSpace((Vector) intersection.toSpace((Vector) Vector1D.ZERO));
        Vector2D subSpace2 = plane2.toSubSpace((Vector) intersection.toSpace((Vector) Vector1D.ONE));
        if (Vector3D.crossProduct(intersection.getDirection(), plane2.getNormal()).dotProduct(plane.getNormal()) < 0.0d) {
            Vector2D vector2D = subSpace2;
            subSpace2 = subSpace;
            subSpace = vector2D;
        }
        return getRemainingRegion().side(new Line(subSpace, subSpace2));
    }

    public SplitSubHyperplane<Euclidean3D> split(Hyperplane<Euclidean3D> hyperplane) {
        Plane plane = (Plane) hyperplane;
        Plane plane2 = (Plane) getHyperplane();
        Line intersection = plane.intersection(plane2);
        if (intersection == null) {
            return plane.getOffset(plane2) < -1.0E-10d ? new SplitSubHyperplane<>(null, this) : new SplitSubHyperplane<>(this, null);
        }
        Vector2D subSpace = plane2.toSubSpace((Vector) intersection.toSpace((Vector) Vector1D.ZERO));
        Vector2D subSpace2 = plane2.toSubSpace((Vector) intersection.toSpace((Vector) Vector1D.ONE));
        if (Vector3D.crossProduct(intersection.getDirection(), plane2.getNormal()).dotProduct(plane.getNormal()) < 0.0d) {
            Vector2D vector2D = subSpace2;
            subSpace2 = subSpace;
            subSpace = vector2D;
        }
        SubLine wholeHyperplane = new Line(subSpace, subSpace2).wholeHyperplane();
        SubLine wholeHyperplane2 = new Line(subSpace2, subSpace).wholeHyperplane();
        BSPTree split = getRemainingRegion().getTree(false).split(wholeHyperplane);
        return new SplitSubHyperplane<>(new SubPlane(plane2.copySelf(), new PolygonsSet(getRemainingRegion().isEmpty(split.getPlus()) ? new BSPTree<>(Boolean.FALSE) : new BSPTree<>(wholeHyperplane2, new BSPTree(Boolean.FALSE), split.getPlus(), null))), new SubPlane(plane2.copySelf(), new PolygonsSet(getRemainingRegion().isEmpty(split.getMinus()) ? new BSPTree<>(Boolean.FALSE) : new BSPTree<>(wholeHyperplane, new BSPTree(Boolean.FALSE), split.getMinus(), null))));
    }
}
