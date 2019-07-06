package org.apache.commons.math3.geometry.euclidean.twod;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.Interval;
import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
import org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint;
import org.apache.commons.math3.geometry.euclidean.oned.SubOrientedPoint;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.Region.Location;
import org.apache.commons.math3.geometry.partitioning.Side;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane.SplitSubHyperplane;
import org.apache.commons.math3.util.FastMath;

public class SubLine extends AbstractSubHyperplane<Euclidean2D, Euclidean1D> {
    public SubLine(Hyperplane<Euclidean2D> hyperplane, Region<Euclidean1D> region) {
        super(hyperplane, region);
    }

    public SubLine(Vector2D vector2D, Vector2D vector2D2) {
        super(new Line(vector2D, vector2D2), buildIntervalSet(vector2D, vector2D2));
    }

    public SubLine(Segment segment) {
        super(segment.getLine(), buildIntervalSet(segment.getStart(), segment.getEnd()));
    }

    public List<Segment> getSegments() {
        Line line = (Line) getHyperplane();
        List<Interval> asList = ((IntervalsSet) getRemainingRegion()).asList();
        ArrayList arrayList = new ArrayList();
        for (Interval interval : asList) {
            arrayList.add(new Segment(line.toSpace((Vector) new Vector1D(interval.getInf())), line.toSpace((Vector) new Vector1D(interval.getSup())), line));
        }
        return arrayList;
    }

    public Vector2D intersection(SubLine subLine, boolean z) {
        Line line = (Line) getHyperplane();
        Line line2 = (Line) subLine.getHyperplane();
        Vector2D intersection = line.intersection(line2);
        Location checkPoint = getRemainingRegion().checkPoint(line.toSubSpace((Vector) intersection));
        Location checkPoint2 = subLine.getRemainingRegion().checkPoint(line2.toSubSpace((Vector) intersection));
        Vector2D vector2D = null;
        if (z) {
            if (!(checkPoint == Location.OUTSIDE || checkPoint2 == Location.OUTSIDE)) {
                vector2D = intersection;
            }
            return vector2D;
        }
        if (checkPoint == Location.INSIDE && checkPoint2 == Location.INSIDE) {
            vector2D = intersection;
        }
        return vector2D;
    }

    private static IntervalsSet buildIntervalSet(Vector2D vector2D, Vector2D vector2D2) {
        Line line = new Line(vector2D, vector2D2);
        return new IntervalsSet(line.toSubSpace((Vector) vector2D).getX(), line.toSubSpace((Vector) vector2D2).getX());
    }

    /* access modifiers changed from: protected */
    public AbstractSubHyperplane<Euclidean2D, Euclidean1D> buildNew(Hyperplane<Euclidean2D> hyperplane, Region<Euclidean1D> region) {
        return new SubLine(hyperplane, region);
    }

    public Side side(Hyperplane<Euclidean2D> hyperplane) {
        Line line = (Line) getHyperplane();
        Line line2 = (Line) hyperplane;
        Vector2D intersection = line.intersection(line2);
        if (intersection == null) {
            double offset = line2.getOffset(line);
            Side side = offset < -1.0E-10d ? Side.MINUS : offset > 1.0E-10d ? Side.PLUS : Side.HYPER;
            return side;
        }
        return getRemainingRegion().side(new OrientedPoint(line.toSubSpace((Vector) intersection), FastMath.sin(line.getAngle() - line2.getAngle()) < 0.0d));
    }

    public SplitSubHyperplane<Euclidean2D> split(Hyperplane<Euclidean2D> hyperplane) {
        Line line = (Line) getHyperplane();
        Line line2 = (Line) hyperplane;
        Vector2D intersection = line.intersection(line2);
        if (intersection == null) {
            return line2.getOffset(line) < -1.0E-10d ? new SplitSubHyperplane<>(null, this) : new SplitSubHyperplane<>(this, null);
        }
        boolean z = FastMath.sin(line.getAngle() - line2.getAngle()) < 0.0d;
        Vector1D subSpace = line.toSubSpace((Vector) intersection);
        SubOrientedPoint wholeHyperplane = new OrientedPoint(subSpace, !z).wholeHyperplane();
        SubOrientedPoint wholeHyperplane2 = new OrientedPoint(subSpace, z).wholeHyperplane();
        BSPTree split = getRemainingRegion().getTree(false).split(wholeHyperplane2);
        return new SplitSubHyperplane<>(new SubLine((Hyperplane<Euclidean2D>) line.copySelf(), (Region<Euclidean1D>) new IntervalsSet<Euclidean1D>(getRemainingRegion().isEmpty(split.getPlus()) ? new BSPTree<>(Boolean.FALSE) : new BSPTree<>(wholeHyperplane, new BSPTree(Boolean.FALSE), split.getPlus(), null))), new SubLine((Hyperplane<Euclidean2D>) line.copySelf(), (Region<Euclidean1D>) new IntervalsSet<Euclidean1D>(getRemainingRegion().isEmpty(split.getMinus()) ? new BSPTree<>(Boolean.FALSE) : new BSPTree<>(wholeHyperplane2, new BSPTree(Boolean.FALSE), split.getMinus(), null))));
    }
}
