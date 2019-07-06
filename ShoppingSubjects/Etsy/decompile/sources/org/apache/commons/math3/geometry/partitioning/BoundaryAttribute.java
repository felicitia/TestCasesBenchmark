package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Space;

public class BoundaryAttribute<S extends Space> {
    private final SubHyperplane<S> plusInside;
    private final SubHyperplane<S> plusOutside;

    public BoundaryAttribute(SubHyperplane<S> subHyperplane, SubHyperplane<S> subHyperplane2) {
        this.plusOutside = subHyperplane;
        this.plusInside = subHyperplane2;
    }

    public SubHyperplane<S> getPlusOutside() {
        return this.plusOutside;
    }

    public SubHyperplane<S> getPlusInside() {
        return this.plusInside;
    }
}
