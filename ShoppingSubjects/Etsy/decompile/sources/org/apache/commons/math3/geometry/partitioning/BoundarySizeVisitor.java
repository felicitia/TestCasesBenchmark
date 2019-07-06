package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order;

class BoundarySizeVisitor<S extends Space> implements BSPTreeVisitor<S> {
    private double boundarySize = 0.0d;

    public void visitLeafNode(BSPTree<S> bSPTree) {
    }

    public Order visitOrder(BSPTree<S> bSPTree) {
        return Order.MINUS_SUB_PLUS;
    }

    public void visitInternalNode(BSPTree<S> bSPTree) {
        BoundaryAttribute boundaryAttribute = (BoundaryAttribute) bSPTree.getAttribute();
        if (boundaryAttribute.getPlusOutside() != null) {
            this.boundarySize += boundaryAttribute.getPlusOutside().getSize();
        }
        if (boundaryAttribute.getPlusInside() != null) {
            this.boundarySize += boundaryAttribute.getPlusInside().getSize();
        }
    }

    public double getSize() {
        return this.boundarySize;
    }
}
