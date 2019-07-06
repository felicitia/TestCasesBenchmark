package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.partitioning.BSPTree.LeafMerger;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order;

public class RegionFactory<S extends Space> {
    private final NodesCleaner nodeCleaner = new NodesCleaner<>();

    private class DifferenceMerger implements LeafMerger<S> {
        private DifferenceMerger() {
        }

        public BSPTree<S> merge(BSPTree<S> bSPTree, BSPTree<S> bSPTree2, BSPTree<S> bSPTree3, boolean z, boolean z2) {
            if (((Boolean) bSPTree.getAttribute()).booleanValue()) {
                RegionFactory regionFactory = RegionFactory.this;
                if (z2) {
                    bSPTree = bSPTree2;
                }
                BSPTree<S> access$500 = regionFactory.recurseComplement(bSPTree);
                access$500.insertInTree(bSPTree3, z);
                return access$500;
            }
            if (!z2) {
                bSPTree = bSPTree2;
            }
            bSPTree.insertInTree(bSPTree3, z);
            return bSPTree;
        }
    }

    private class IntersectionMerger implements LeafMerger<S> {
        private IntersectionMerger() {
        }

        public BSPTree<S> merge(BSPTree<S> bSPTree, BSPTree<S> bSPTree2, BSPTree<S> bSPTree3, boolean z, boolean z2) {
            if (((Boolean) bSPTree.getAttribute()).booleanValue()) {
                bSPTree2.insertInTree(bSPTree3, z);
                return bSPTree2;
            }
            bSPTree.insertInTree(bSPTree3, z);
            return bSPTree;
        }
    }

    private class NodesCleaner implements BSPTreeVisitor<S> {
        public void visitLeafNode(BSPTree<S> bSPTree) {
        }

        private NodesCleaner() {
        }

        public Order visitOrder(BSPTree<S> bSPTree) {
            return Order.PLUS_SUB_MINUS;
        }

        public void visitInternalNode(BSPTree<S> bSPTree) {
            bSPTree.setAttribute(null);
        }
    }

    private class UnionMerger implements LeafMerger<S> {
        private UnionMerger() {
        }

        public BSPTree<S> merge(BSPTree<S> bSPTree, BSPTree<S> bSPTree2, BSPTree<S> bSPTree3, boolean z, boolean z2) {
            if (((Boolean) bSPTree.getAttribute()).booleanValue()) {
                bSPTree.insertInTree(bSPTree3, z);
                return bSPTree;
            }
            bSPTree2.insertInTree(bSPTree3, z);
            return bSPTree2;
        }
    }

    private class XorMerger implements LeafMerger<S> {
        private XorMerger() {
        }

        public BSPTree<S> merge(BSPTree<S> bSPTree, BSPTree<S> bSPTree2, BSPTree<S> bSPTree3, boolean z, boolean z2) {
            if (((Boolean) bSPTree.getAttribute()).booleanValue()) {
                bSPTree2 = RegionFactory.this.recurseComplement(bSPTree2);
            }
            bSPTree2.insertInTree(bSPTree3, z);
            return bSPTree2;
        }
    }

    public Region<S> buildConvex(Hyperplane<S>... hyperplaneArr) {
        if (hyperplaneArr == null || hyperplaneArr.length == 0) {
            return null;
        }
        Region<S> wholeSpace = hyperplaneArr[0].wholeSpace();
        BSPTree tree = wholeSpace.getTree(false);
        tree.setAttribute(Boolean.TRUE);
        for (Hyperplane<S> insertCut : hyperplaneArr) {
            if (tree.insertCut(insertCut)) {
                tree.setAttribute(null);
                tree.getPlus().setAttribute(Boolean.FALSE);
                tree = tree.getMinus();
                tree.setAttribute(Boolean.TRUE);
            }
        }
        return wholeSpace;
    }

    public Region<S> union(Region<S> region, Region<S> region2) {
        BSPTree merge = region.getTree(false).merge(region2.getTree(false), new UnionMerger());
        merge.visit(this.nodeCleaner);
        return region.buildNew(merge);
    }

    public Region<S> intersection(Region<S> region, Region<S> region2) {
        BSPTree merge = region.getTree(false).merge(region2.getTree(false), new IntersectionMerger());
        merge.visit(this.nodeCleaner);
        return region.buildNew(merge);
    }

    public Region<S> xor(Region<S> region, Region<S> region2) {
        BSPTree merge = region.getTree(false).merge(region2.getTree(false), new XorMerger());
        merge.visit(this.nodeCleaner);
        return region.buildNew(merge);
    }

    public Region<S> difference(Region<S> region, Region<S> region2) {
        BSPTree merge = region.getTree(false).merge(region2.getTree(false), new DifferenceMerger());
        merge.visit(this.nodeCleaner);
        return region.buildNew(merge);
    }

    public Region<S> getComplement(Region<S> region) {
        return region.buildNew(recurseComplement(region.getTree(false)));
    }

    /* access modifiers changed from: private */
    public BSPTree<S> recurseComplement(BSPTree<S> bSPTree) {
        if (bSPTree.getCut() == null) {
            return new BSPTree<>(((Boolean) bSPTree.getAttribute()).booleanValue() ? Boolean.FALSE : Boolean.TRUE);
        }
        BoundaryAttribute boundaryAttribute = (BoundaryAttribute) bSPTree.getAttribute();
        if (boundaryAttribute != null) {
            SubHyperplane subHyperplane = null;
            SubHyperplane copySelf = boundaryAttribute.getPlusInside() == null ? null : boundaryAttribute.getPlusInside().copySelf();
            if (boundaryAttribute.getPlusOutside() != null) {
                subHyperplane = boundaryAttribute.getPlusOutside().copySelf();
            }
            boundaryAttribute = new BoundaryAttribute(copySelf, subHyperplane);
        }
        return new BSPTree<>(bSPTree.getCut().copySelf(), recurseComplement(bSPTree.getPlus()), recurseComplement(bSPTree.getMinus()), boundaryAttribute);
    }
}
