package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.Vector;

public interface Region<S extends Space> {

    public enum Location {
        INSIDE,
        OUTSIDE,
        BOUNDARY
    }

    Region<S> buildNew(BSPTree<S> bSPTree);

    Location checkPoint(Vector<S> vector);

    boolean contains(Region<S> region);

    Region<S> copySelf();

    Vector<S> getBarycenter();

    double getBoundarySize();

    double getSize();

    BSPTree<S> getTree(boolean z);

    SubHyperplane<S> intersection(SubHyperplane<S> subHyperplane);

    boolean isEmpty();

    boolean isEmpty(BSPTree<S> bSPTree);

    Side side(Hyperplane<S> hyperplane);
}
