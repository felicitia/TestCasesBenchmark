package org.apache.commons.math3.geometry.euclidean.threed;

import org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order;
import org.apache.commons.math3.geometry.partitioning.BoundaryAttribute;
import org.apache.commons.math3.util.FastMath;

public class OutlineExtractor {
    /* access modifiers changed from: private */
    public Vector3D u;
    /* access modifiers changed from: private */
    public Vector3D v;
    /* access modifiers changed from: private */
    public Vector3D w;

    private class BoundaryProjector implements BSPTreeVisitor<Euclidean3D> {
        private PolygonsSet projected = new PolygonsSet(new BSPTree<>(Boolean.FALSE));

        public void visitLeafNode(BSPTree<Euclidean3D> bSPTree) {
        }

        public BoundaryProjector() {
        }

        public Order visitOrder(BSPTree<Euclidean3D> bSPTree) {
            return Order.MINUS_SUB_PLUS;
        }

        public void visitInternalNode(BSPTree<Euclidean3D> bSPTree) {
            BoundaryAttribute boundaryAttribute = (BoundaryAttribute) bSPTree.getAttribute();
            if (boundaryAttribute.getPlusOutside() != null) {
                addContribution(boundaryAttribute.getPlusOutside(), false);
            }
            if (boundaryAttribute.getPlusInside() != null) {
                addContribution(boundaryAttribute.getPlusInside(), true);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:43:0x010a, code lost:
            if (r10 != (r8.length - 1)) goto L_0x0111;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void addContribution(org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> r23, boolean r24) {
            /*
                r22 = this;
                r0 = r22
                r2 = r23
                org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane r2 = (org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane) r2
                org.apache.commons.math3.geometry.partitioning.Hyperplane r1 = r23.getHyperplane()
                org.apache.commons.math3.geometry.euclidean.threed.Plane r1 = (org.apache.commons.math3.geometry.euclidean.threed.Plane) r1
                org.apache.commons.math3.geometry.euclidean.threed.Vector3D r3 = r1.getNormal()
                org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor r4 = org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.this
                org.apache.commons.math3.geometry.euclidean.threed.Vector3D r4 = r4.w
                double r3 = r3.dotProduct(r4)
                double r5 = org.apache.commons.math3.util.FastMath.abs(r3)
                r7 = 4562254508917369340(0x3f50624dd2f1a9fc, double:0.001)
                int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
                if (r9 <= 0) goto L_0x015e
                org.apache.commons.math3.geometry.partitioning.Region r2 = r2.getRemainingRegion()
                org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet r2 = (org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet) r2
                org.apache.commons.math3.geometry.euclidean.twod.Vector2D[][] r2 = r2.getVertices()
                r5 = 0
                int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                r3 = 0
                r4 = 1
                if (r7 >= 0) goto L_0x003b
                r6 = r4
                goto L_0x003c
            L_0x003b:
                r6 = r3
            L_0x003c:
                r5 = r6 ^ r24
                if (r5 == 0) goto L_0x0074
                int r5 = r2.length
                org.apache.commons.math3.geometry.euclidean.twod.Vector2D[][] r5 = new org.apache.commons.math3.geometry.euclidean.twod.Vector2D[r5][]
                r6 = r3
            L_0x0044:
                int r7 = r2.length
                if (r6 >= r7) goto L_0x0073
                r7 = r2[r6]
                int r8 = r7.length
                org.apache.commons.math3.geometry.euclidean.twod.Vector2D[] r8 = new org.apache.commons.math3.geometry.euclidean.twod.Vector2D[r8]
                r9 = r7[r3]
                if (r9 != 0) goto L_0x0060
                r9 = 0
                r8[r3] = r9
                r9 = r4
            L_0x0054:
                int r10 = r7.length
                if (r9 >= r10) goto L_0x006e
                int r10 = r7.length
                int r10 = r10 - r9
                r10 = r7[r10]
                r8[r9] = r10
                int r9 = r9 + 1
                goto L_0x0054
            L_0x0060:
                r9 = r3
            L_0x0061:
                int r10 = r7.length
                if (r9 >= r10) goto L_0x006e
                int r10 = r7.length
                int r11 = r9 + 1
                int r10 = r10 - r11
                r10 = r7[r10]
                r8[r9] = r10
                r9 = r11
                goto L_0x0061
            L_0x006e:
                r5[r6] = r8
                int r6 = r6 + 1
                goto L_0x0044
            L_0x0073:
                r2 = r5
            L_0x0074:
                java.util.ArrayList r5 = new java.util.ArrayList
                r5.<init>()
                int r6 = r2.length
                r7 = r3
            L_0x007b:
                if (r7 >= r6) goto L_0x0149
                r8 = r2[r7]
                r9 = r8[r3]
                if (r9 == 0) goto L_0x0085
                r9 = r4
                goto L_0x0086
            L_0x0085:
                r9 = r3
            L_0x0086:
                if (r9 == 0) goto L_0x008b
                int r10 = r8.length
                int r10 = r10 - r4
                goto L_0x008c
            L_0x008b:
                r10 = r4
            L_0x008c:
                r11 = r8[r10]
                org.apache.commons.math3.geometry.euclidean.threed.Vector3D r11 = r1.toSpace(r11)
                int r12 = r10 + 1
                int r13 = r8.length
                int r12 = r12 % r13
                org.apache.commons.math3.geometry.euclidean.twod.Vector2D r13 = new org.apache.commons.math3.geometry.euclidean.twod.Vector2D
                org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor r14 = org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.this
                org.apache.commons.math3.geometry.euclidean.threed.Vector3D r14 = r14.u
                double r14 = r11.dotProduct(r14)
                org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor r3 = org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.this
                org.apache.commons.math3.geometry.euclidean.threed.Vector3D r3 = r3.v
                r16 = r5
                double r4 = r11.dotProduct(r3)
                r13.<init>(r14, r4)
                r3 = r10
                r10 = r12
            L_0x00b3:
                int r4 = r8.length
                if (r10 >= r4) goto L_0x0137
                r4 = r8[r10]
                org.apache.commons.math3.geometry.euclidean.threed.Vector3D r4 = r1.toSpace(r4)
                org.apache.commons.math3.geometry.euclidean.twod.Vector2D r5 = new org.apache.commons.math3.geometry.euclidean.twod.Vector2D
                org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor r11 = org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.this
                org.apache.commons.math3.geometry.euclidean.threed.Vector3D r11 = r11.u
                double r11 = r4.dotProduct(r11)
                org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor r14 = org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.this
                org.apache.commons.math3.geometry.euclidean.threed.Vector3D r14 = r14.v
                double r14 = r4.dotProduct(r14)
                r5.<init>(r11, r14)
                org.apache.commons.math3.geometry.euclidean.twod.Line r4 = new org.apache.commons.math3.geometry.euclidean.twod.Line
                r4.<init>(r13, r5)
                org.apache.commons.math3.geometry.euclidean.twod.SubLine r11 = r4.wholeHyperplane()
                r14 = 4609753056924675352(0x3ff921fb54442d18, double:1.5707963267948966)
                if (r9 != 0) goto L_0x00ee
                r12 = 1
                if (r3 == r12) goto L_0x00e9
                goto L_0x00ee
            L_0x00e9:
                r19 = r1
                r20 = r2
                goto L_0x0105
            L_0x00ee:
                double r17 = r4.getAngle()
                r19 = r1
                r20 = r2
                double r1 = r17 + r14
                org.apache.commons.math3.geometry.euclidean.twod.Line r3 = new org.apache.commons.math3.geometry.euclidean.twod.Line
                r3.<init>(r13, r1)
                org.apache.commons.math3.geometry.partitioning.SubHyperplane$SplitSubHyperplane r1 = r11.split(r3)
                org.apache.commons.math3.geometry.partitioning.SubHyperplane r11 = r1.getPlus()
            L_0x0105:
                if (r9 != 0) goto L_0x0110
                int r1 = r8.length
                r2 = 1
                int r1 = r1 - r2
                if (r10 == r1) goto L_0x010d
                goto L_0x0111
            L_0x010d:
                r1 = r16
                goto L_0x0124
            L_0x0110:
                r2 = 1
            L_0x0111:
                double r3 = r4.getAngle()
                double r3 = r3 + r14
                org.apache.commons.math3.geometry.euclidean.twod.Line r1 = new org.apache.commons.math3.geometry.euclidean.twod.Line
                r1.<init>(r5, r3)
                org.apache.commons.math3.geometry.partitioning.SubHyperplane$SplitSubHyperplane r1 = r11.split(r1)
                org.apache.commons.math3.geometry.partitioning.SubHyperplane r11 = r1.getMinus()
                goto L_0x010d
            L_0x0124:
                r1.add(r11)
                int r3 = r10 + 1
                r16 = r1
                r13 = r5
                r1 = r19
                r2 = r20
                r21 = r10
                r10 = r3
                r3 = r21
                goto L_0x00b3
            L_0x0137:
                r19 = r1
                r20 = r2
                r1 = r16
                r2 = 1
                int r7 = r7 + 1
                r5 = r1
                r4 = r2
                r1 = r19
                r2 = r20
                r3 = 0
                goto L_0x007b
            L_0x0149:
                r1 = r5
                org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet r2 = new org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet
                r2.<init>(r1)
                org.apache.commons.math3.geometry.partitioning.RegionFactory r1 = new org.apache.commons.math3.geometry.partitioning.RegionFactory
                r1.<init>()
                org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet r3 = r0.projected
                org.apache.commons.math3.geometry.partitioning.Region r1 = r1.union(r3, r2)
                org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet r1 = (org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet) r1
                r0.projected = r1
            L_0x015e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.BoundaryProjector.addContribution(org.apache.commons.math3.geometry.partitioning.SubHyperplane, boolean):void");
        }

        public PolygonsSet getProjected() {
            return this.projected;
        }
    }

    public OutlineExtractor(Vector3D vector3D, Vector3D vector3D2) {
        this.u = vector3D;
        this.v = vector3D2;
        this.w = Vector3D.crossProduct(vector3D, vector3D2);
    }

    public Vector2D[][] getOutline(PolyhedronsSet polyhedronsSet) {
        BoundaryProjector boundaryProjector = new BoundaryProjector();
        polyhedronsSet.getTree(true).visit(boundaryProjector);
        Vector2D[][] vertices = boundaryProjector.getProjected().getVertices();
        for (int i = 0; i < vertices.length; i++) {
            Vector2D[] vector2DArr = vertices[i];
            int length = vector2DArr.length;
            int i2 = 0;
            while (i2 < length) {
                if (pointIsBetween(vector2DArr, length, i2)) {
                    int i3 = i2;
                    while (i3 < length - 1) {
                        int i4 = i3 + 1;
                        vector2DArr[i3] = vector2DArr[i4];
                        i3 = i4;
                    }
                    length--;
                } else {
                    i2++;
                }
            }
            if (length != vector2DArr.length) {
                vertices[i] = new Vector2D[length];
                System.arraycopy(vector2DArr, 0, vertices[i], 0, length);
            }
        }
        return vertices;
    }

    private boolean pointIsBetween(Vector2D[] vector2DArr, int i, int i2) {
        Vector2D vector2D = vector2DArr[((i2 + i) - 1) % i];
        Vector2D vector2D2 = vector2DArr[i2];
        Vector2D vector2D3 = vector2DArr[(i2 + 1) % i];
        double x = vector2D2.getX() - vector2D.getX();
        double y = vector2D2.getY() - vector2D.getY();
        double x2 = vector2D3.getX() - vector2D2.getX();
        double y2 = vector2D3.getY() - vector2D2.getY();
        double d = (x * x2) + (y * y2);
        if (FastMath.abs((x * y2) - (x2 * y)) > 1.0E-6d * FastMath.sqrt(((x * x) + (y * y)) * ((x2 * x2) + (y2 * y2))) || d < 0.0d) {
            return false;
        }
        return true;
    }
}
