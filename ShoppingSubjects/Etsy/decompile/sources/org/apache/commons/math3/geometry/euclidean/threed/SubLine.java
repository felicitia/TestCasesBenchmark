package org.apache.commons.math3.geometry.euclidean.threed;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.oned.Interval;
import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.partitioning.Region.Location;

public class SubLine {
    private final Line line;
    private final IntervalsSet remainingRegion;

    public SubLine(Line line2, IntervalsSet intervalsSet) {
        this.line = line2;
        this.remainingRegion = intervalsSet;
    }

    public SubLine(Vector3D vector3D, Vector3D vector3D2) throws MathIllegalArgumentException {
        this(new Line(vector3D, vector3D2), buildIntervalSet(vector3D, vector3D2));
    }

    public SubLine(Segment segment) throws MathIllegalArgumentException {
        this(segment.getLine(), buildIntervalSet(segment.getStart(), segment.getEnd()));
    }

    public List<Segment> getSegments() {
        List<Interval> asList = this.remainingRegion.asList();
        ArrayList arrayList = new ArrayList();
        for (Interval interval : asList) {
            arrayList.add(new Segment(this.line.toSpace((Vector) new Vector1D(interval.getInf())), this.line.toSpace((Vector) new Vector1D(interval.getSup())), this.line));
        }
        return arrayList;
    }

    public Vector3D intersection(SubLine subLine, boolean z) {
        Vector3D intersection = this.line.intersection(subLine.line);
        Location checkPoint = this.remainingRegion.checkPoint(this.line.toSubSpace((Vector) intersection));
        Location checkPoint2 = subLine.remainingRegion.checkPoint(subLine.line.toSubSpace((Vector) intersection));
        if (z) {
            if (checkPoint == Location.OUTSIDE || checkPoint2 == Location.OUTSIDE) {
                intersection = null;
            }
            return intersection;
        }
        if (!(checkPoint == Location.INSIDE && checkPoint2 == Location.INSIDE)) {
            intersection = null;
        }
        return intersection;
    }

    private static IntervalsSet buildIntervalSet(Vector3D vector3D, Vector3D vector3D2) throws MathIllegalArgumentException {
        Line line2 = new Line(vector3D, vector3D2);
        return new IntervalsSet(line2.toSubSpace((Vector) vector3D).getX(), line2.toSubSpace((Vector) vector3D2).getX());
    }
}
