package org.apache.commons.math3.linear;

public class DefaultRealMatrixChangingVisitor implements RealMatrixChangingVisitor {
    public double end() {
        return 0.0d;
    }

    public void start(int i, int i2, int i3, int i4, int i5, int i6) {
    }

    public double visit(int i, int i2, double d) {
        return d;
    }
}
