package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane.SplitSubHyperplane;

public abstract class AbstractSubHyperplane<S extends Space, T extends Space> implements SubHyperplane<S> {
    private final Hyperplane<S> hyperplane;
    private final Region<T> remainingRegion;

    /* access modifiers changed from: protected */
    public abstract AbstractSubHyperplane<S, T> buildNew(Hyperplane<S> hyperplane2, Region<T> region);

    public abstract Side side(Hyperplane<S> hyperplane2);

    public abstract SplitSubHyperplane<S> split(Hyperplane<S> hyperplane2);

    protected AbstractSubHyperplane(Hyperplane<S> hyperplane2, Region<T> region) {
        this.hyperplane = hyperplane2;
        this.remainingRegion = region;
    }

    public AbstractSubHyperplane<S, T> copySelf() {
        return buildNew(this.hyperplane, this.remainingRegion);
    }

    public Hyperplane<S> getHyperplane() {
        return this.hyperplane;
    }

    public Region<T> getRemainingRegion() {
        return this.remainingRegion;
    }

    public double getSize() {
        return this.remainingRegion.getSize();
    }

    public AbstractSubHyperplane<S, T> reunite(SubHyperplane<S> subHyperplane) {
        return buildNew(this.hyperplane, new RegionFactory().union(this.remainingRegion, ((AbstractSubHyperplane) subHyperplane).remainingRegion));
    }

    public AbstractSubHyperplane<S, T> applyTransform(Transform<S, T> transform) {
        Hyperplane apply = transform.apply(this.hyperplane);
        return buildNew(apply, this.remainingRegion.buildNew(recurseTransform(this.remainingRegion.getTree(false), apply, transform)));
    }

    private BSPTree<T> recurseTransform(BSPTree<T> bSPTree, Hyperplane<S> hyperplane2, Transform<S, T> transform) {
        if (bSPTree.getCut() == null) {
            return new BSPTree<>(bSPTree.getAttribute());
        }
        BoundaryAttribute boundaryAttribute = (BoundaryAttribute) bSPTree.getAttribute();
        if (boundaryAttribute != null) {
            SubHyperplane subHyperplane = null;
            SubHyperplane apply = boundaryAttribute.getPlusOutside() == null ? null : transform.apply(boundaryAttribute.getPlusOutside(), this.hyperplane, hyperplane2);
            if (boundaryAttribute.getPlusInside() != null) {
                subHyperplane = transform.apply(boundaryAttribute.getPlusInside(), this.hyperplane, hyperplane2);
            }
            boundaryAttribute = new BoundaryAttribute(apply, subHyperplane);
        }
        return new BSPTree<>(transform.apply(bSPTree.getCut(), this.hyperplane, hyperplane2), recurseTransform(bSPTree.getPlus(), hyperplane2, transform), recurseTransform(bSPTree.getMinus(), hyperplane2, transform), boundaryAttribute);
    }

    public boolean isEmpty() {
        return this.remainingRegion.isEmpty();
    }
}
