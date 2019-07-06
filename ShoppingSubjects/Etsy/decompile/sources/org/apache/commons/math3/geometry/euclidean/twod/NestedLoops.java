package org.apache.commons.math3.geometry.euclidean.twod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.RegionFactory;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

class NestedLoops {
    private Vector2D[] loop;
    private boolean originalIsClockwise;
    private Region<Euclidean2D> polygon;
    private ArrayList<NestedLoops> surrounded;

    public NestedLoops() {
        this.surrounded = new ArrayList<>();
    }

    private NestedLoops(Vector2D[] vector2DArr) throws MathIllegalArgumentException {
        if (vector2DArr[0] == null) {
            throw new MathIllegalArgumentException(LocalizedFormats.OUTLINE_BOUNDARY_LOOP_OPEN, new Object[0]);
        }
        this.loop = vector2DArr;
        this.surrounded = new ArrayList<>();
        ArrayList arrayList = new ArrayList();
        Vector2D vector2D = vector2DArr[vector2DArr.length - 1];
        int i = 0;
        while (i < vector2DArr.length) {
            Vector2D vector2D2 = vector2DArr[i];
            Line line = new Line(vector2D, vector2D2);
            arrayList.add(new SubLine((Hyperplane<Euclidean2D>) line, (Region<Euclidean1D>) new IntervalsSet<Euclidean1D>(line.toSubSpace((Vector) vector2D).getX(), line.toSubSpace((Vector) vector2D2).getX())));
            i++;
            vector2D = vector2D2;
        }
        this.polygon = new PolygonsSet((Collection<SubHyperplane<Euclidean2D>>) arrayList);
        if (Double.isInfinite(this.polygon.getSize())) {
            this.polygon = new RegionFactory().getComplement(this.polygon);
            this.originalIsClockwise = false;
            return;
        }
        this.originalIsClockwise = true;
    }

    public void add(Vector2D[] vector2DArr) throws MathIllegalArgumentException {
        add(new NestedLoops(vector2DArr));
    }

    private void add(NestedLoops nestedLoops) throws MathIllegalArgumentException {
        Iterator it = this.surrounded.iterator();
        while (it.hasNext()) {
            NestedLoops nestedLoops2 = (NestedLoops) it.next();
            if (nestedLoops2.polygon.contains(nestedLoops.polygon)) {
                nestedLoops2.add(nestedLoops);
                return;
            }
        }
        Iterator it2 = this.surrounded.iterator();
        while (it2.hasNext()) {
            NestedLoops nestedLoops3 = (NestedLoops) it2.next();
            if (nestedLoops.polygon.contains(nestedLoops3.polygon)) {
                nestedLoops.surrounded.add(nestedLoops3);
                it2.remove();
            }
        }
        RegionFactory regionFactory = new RegionFactory();
        Iterator it3 = this.surrounded.iterator();
        while (it3.hasNext()) {
            if (!regionFactory.intersection(nestedLoops.polygon, ((NestedLoops) it3.next()).polygon).isEmpty()) {
                throw new MathIllegalArgumentException(LocalizedFormats.CROSSING_BOUNDARY_LOOPS, new Object[0]);
            }
        }
        this.surrounded.add(nestedLoops);
    }

    public void correctOrientation() {
        Iterator it = this.surrounded.iterator();
        while (it.hasNext()) {
            ((NestedLoops) it.next()).setClockWise(true);
        }
    }

    private void setClockWise(boolean z) {
        if (this.originalIsClockwise ^ z) {
            int length = this.loop.length;
            int i = -1;
            while (true) {
                i++;
                length--;
                if (i >= length) {
                    break;
                }
                Vector2D vector2D = this.loop[i];
                this.loop[i] = this.loop[length];
                this.loop[length] = vector2D;
            }
        }
        Iterator it = this.surrounded.iterator();
        while (it.hasNext()) {
            ((NestedLoops) it.next()).setClockWise(!z);
        }
    }
}
