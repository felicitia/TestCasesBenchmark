package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.Vector;

public interface Hyperplane<S extends Space> {
    Hyperplane<S> copySelf();

    double getOffset(Vector<S> vector);

    boolean sameOrientationAs(Hyperplane<S> hyperplane);

    SubHyperplane<S> wholeHyperplane();

    Region<S> wholeSpace();
}
