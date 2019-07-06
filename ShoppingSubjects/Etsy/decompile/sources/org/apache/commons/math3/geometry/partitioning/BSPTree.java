package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane.SplitSubHyperplane;
import org.apache.commons.math3.util.FastMath;

public class BSPTree<S extends Space> {
    private Object attribute;
    private SubHyperplane<S> cut;
    private BSPTree<S> minus;
    private BSPTree<S> parent;
    private BSPTree<S> plus;

    public interface LeafMerger<S extends Space> {
        BSPTree<S> merge(BSPTree<S> bSPTree, BSPTree<S> bSPTree2, BSPTree<S> bSPTree3, boolean z, boolean z2);
    }

    public BSPTree() {
        this.cut = null;
        this.plus = null;
        this.minus = null;
        this.parent = null;
        this.attribute = null;
    }

    public BSPTree(Object obj) {
        this.cut = null;
        this.plus = null;
        this.minus = null;
        this.parent = null;
        this.attribute = obj;
    }

    public BSPTree(SubHyperplane<S> subHyperplane, BSPTree<S> bSPTree, BSPTree<S> bSPTree2, Object obj) {
        this.cut = subHyperplane;
        this.plus = bSPTree;
        this.minus = bSPTree2;
        this.parent = null;
        this.attribute = obj;
        bSPTree.parent = this;
        bSPTree2.parent = this;
    }

    public boolean insertCut(Hyperplane<S> hyperplane) {
        if (this.cut != null) {
            this.plus.parent = null;
            this.minus.parent = null;
        }
        SubHyperplane<S> fitToCell = fitToCell(hyperplane.wholeHyperplane());
        if (fitToCell == null || fitToCell.isEmpty()) {
            this.cut = null;
            this.plus = null;
            this.minus = null;
            return false;
        }
        this.cut = fitToCell;
        this.plus = new BSPTree<>();
        this.plus.parent = this;
        this.minus = new BSPTree<>();
        this.minus.parent = this;
        return true;
    }

    public BSPTree<S> copySelf() {
        if (this.cut == null) {
            return new BSPTree<>(this.attribute);
        }
        return new BSPTree<>(this.cut.copySelf(), this.plus.copySelf(), this.minus.copySelf(), this.attribute);
    }

    public SubHyperplane<S> getCut() {
        return this.cut;
    }

    public BSPTree<S> getPlus() {
        return this.plus;
    }

    public BSPTree<S> getMinus() {
        return this.minus;
    }

    public BSPTree<S> getParent() {
        return this.parent;
    }

    public void setAttribute(Object obj) {
        this.attribute = obj;
    }

    public Object getAttribute() {
        return this.attribute;
    }

    public void visit(BSPTreeVisitor<S> bSPTreeVisitor) {
        if (this.cut == null) {
            bSPTreeVisitor.visitLeafNode(this);
            return;
        }
        switch (bSPTreeVisitor.visitOrder(this)) {
            case PLUS_MINUS_SUB:
                this.plus.visit(bSPTreeVisitor);
                this.minus.visit(bSPTreeVisitor);
                bSPTreeVisitor.visitInternalNode(this);
                return;
            case PLUS_SUB_MINUS:
                this.plus.visit(bSPTreeVisitor);
                bSPTreeVisitor.visitInternalNode(this);
                this.minus.visit(bSPTreeVisitor);
                return;
            case MINUS_PLUS_SUB:
                this.minus.visit(bSPTreeVisitor);
                this.plus.visit(bSPTreeVisitor);
                bSPTreeVisitor.visitInternalNode(this);
                return;
            case MINUS_SUB_PLUS:
                this.minus.visit(bSPTreeVisitor);
                bSPTreeVisitor.visitInternalNode(this);
                this.plus.visit(bSPTreeVisitor);
                return;
            case SUB_PLUS_MINUS:
                bSPTreeVisitor.visitInternalNode(this);
                this.plus.visit(bSPTreeVisitor);
                this.minus.visit(bSPTreeVisitor);
                return;
            case SUB_MINUS_PLUS:
                bSPTreeVisitor.visitInternalNode(this);
                this.minus.visit(bSPTreeVisitor);
                this.plus.visit(bSPTreeVisitor);
                return;
            default:
                throw new MathInternalError();
        }
    }

    private SubHyperplane<S> fitToCell(SubHyperplane<S> subHyperplane) {
        SubHyperplane<S> subHyperplane2 = subHyperplane;
        for (BSPTree bSPTree = this; bSPTree.parent != null; bSPTree = bSPTree.parent) {
            if (bSPTree == bSPTree.parent.plus) {
                subHyperplane2 = subHyperplane2.split(bSPTree.parent.cut.getHyperplane()).getPlus();
            } else {
                subHyperplane2 = subHyperplane2.split(bSPTree.parent.cut.getHyperplane()).getMinus();
            }
        }
        return subHyperplane2;
    }

    public BSPTree<S> getCell(Vector<S> vector) {
        if (this.cut == null) {
            return this;
        }
        double offset = this.cut.getHyperplane().getOffset(vector);
        if (FastMath.abs(offset) < 1.0E-10d) {
            return this;
        }
        if (offset <= 0.0d) {
            return this.minus.getCell(vector);
        }
        return this.plus.getCell(vector);
    }

    private void condense() {
        if (this.cut == null || this.plus.cut != null || this.minus.cut != null) {
            return;
        }
        if ((this.plus.attribute == null && this.minus.attribute == null) || (this.plus.attribute != null && this.plus.attribute.equals(this.minus.attribute))) {
            this.attribute = (this.plus.attribute == null ? this.minus : this.plus).attribute;
            this.cut = null;
            this.plus = null;
            this.minus = null;
        }
    }

    public BSPTree<S> merge(BSPTree<S> bSPTree, LeafMerger<S> leafMerger) {
        return merge(bSPTree, leafMerger, null, false);
    }

    private BSPTree<S> merge(BSPTree<S> bSPTree, LeafMerger<S> leafMerger, BSPTree<S> bSPTree2, boolean z) {
        if (this.cut == null) {
            return leafMerger.merge(this, bSPTree, bSPTree2, z, true);
        }
        if (bSPTree.cut == null) {
            return leafMerger.merge(bSPTree, this, bSPTree2, z, false);
        }
        BSPTree<S> split = bSPTree.split(this.cut);
        if (bSPTree2 != null) {
            split.parent = bSPTree2;
            if (z) {
                bSPTree2.plus = split;
            } else {
                bSPTree2.minus = split;
            }
        }
        this.plus.merge(split.plus, leafMerger, split, true);
        this.minus.merge(split.minus, leafMerger, split, false);
        split.condense();
        if (split.cut != null) {
            split.cut = split.fitToCell(split.cut.getHyperplane().wholeHyperplane());
        }
        return split;
    }

    public BSPTree<S> split(SubHyperplane<S> subHyperplane) {
        BSPTree<S> bSPTree;
        if (this.cut == null) {
            return new BSPTree<>(subHyperplane, copySelf(), new BSPTree(this.attribute), null);
        }
        Hyperplane hyperplane = this.cut.getHyperplane();
        Hyperplane hyperplane2 = subHyperplane.getHyperplane();
        switch (subHyperplane.side(hyperplane)) {
            case PLUS:
                BSPTree<S> split = this.plus.split(subHyperplane);
                if (this.cut.side(hyperplane2) == Side.PLUS) {
                    split.plus = new BSPTree<>(this.cut.copySelf(), split.plus, this.minus.copySelf(), this.attribute);
                    split.plus.condense();
                    split.plus.parent = split;
                } else {
                    split.minus = new BSPTree<>(this.cut.copySelf(), split.minus, this.minus.copySelf(), this.attribute);
                    split.minus.condense();
                    split.minus.parent = split;
                }
                return split;
            case MINUS:
                BSPTree<S> split2 = this.minus.split(subHyperplane);
                if (this.cut.side(hyperplane2) == Side.PLUS) {
                    split2.plus = new BSPTree<>(this.cut.copySelf(), this.plus.copySelf(), split2.plus, this.attribute);
                    split2.plus.condense();
                    split2.plus.parent = split2;
                } else {
                    split2.minus = new BSPTree<>(this.cut.copySelf(), this.plus.copySelf(), split2.minus, this.attribute);
                    split2.minus.condense();
                    split2.minus.parent = split2;
                }
                return split2;
            case BOTH:
                SplitSubHyperplane split3 = this.cut.split(hyperplane2);
                SplitSubHyperplane split4 = subHyperplane.split(hyperplane);
                BSPTree<S> bSPTree2 = new BSPTree<>(subHyperplane, this.plus.split(split4.getPlus()), this.minus.split(split4.getMinus()), null);
                bSPTree2.plus.cut = split3.getPlus();
                bSPTree2.minus.cut = split3.getMinus();
                BSPTree<S> bSPTree3 = bSPTree2.plus.minus;
                bSPTree2.plus.minus = bSPTree2.minus.plus;
                bSPTree2.plus.minus.parent = bSPTree2.plus;
                bSPTree2.minus.plus = bSPTree3;
                bSPTree2.minus.plus.parent = bSPTree2.minus;
                bSPTree2.plus.condense();
                bSPTree2.minus.condense();
                return bSPTree2;
            default:
                if (hyperplane.sameOrientationAs(hyperplane2)) {
                    bSPTree = new BSPTree<>(subHyperplane, this.plus.copySelf(), this.minus.copySelf(), this.attribute);
                } else {
                    bSPTree = new BSPTree<>(subHyperplane, this.minus.copySelf(), this.plus.copySelf(), this.attribute);
                }
                return bSPTree;
        }
    }

    public void insertInTree(BSPTree<S> bSPTree, boolean z) {
        this.parent = bSPTree;
        if (bSPTree != null) {
            if (z) {
                bSPTree.plus = this;
            } else {
                bSPTree.minus = this;
            }
        }
        if (this.cut != null) {
            for (BSPTree bSPTree2 = this; bSPTree2.parent != null; bSPTree2 = bSPTree2.parent) {
                Hyperplane hyperplane = bSPTree2.parent.cut.getHyperplane();
                if (bSPTree2 == bSPTree2.parent.plus) {
                    this.cut = this.cut.split(hyperplane).getPlus();
                    this.plus.chopOffMinus(hyperplane);
                    this.minus.chopOffMinus(hyperplane);
                } else {
                    this.cut = this.cut.split(hyperplane).getMinus();
                    this.plus.chopOffPlus(hyperplane);
                    this.minus.chopOffPlus(hyperplane);
                }
            }
            condense();
        }
    }

    private void chopOffMinus(Hyperplane<S> hyperplane) {
        if (this.cut != null) {
            this.cut = this.cut.split(hyperplane).getPlus();
            this.plus.chopOffMinus(hyperplane);
            this.minus.chopOffMinus(hyperplane);
        }
    }

    private void chopOffPlus(Hyperplane<S> hyperplane) {
        if (this.cut != null) {
            this.cut = this.cut.split(hyperplane).getMinus();
            this.plus.chopOffPlus(hyperplane);
            this.minus.chopOffPlus(hyperplane);
        }
    }
}
