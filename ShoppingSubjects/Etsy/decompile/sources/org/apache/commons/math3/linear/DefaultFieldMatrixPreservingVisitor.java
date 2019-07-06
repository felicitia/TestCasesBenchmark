package org.apache.commons.math3.linear;

import org.apache.commons.math3.FieldElement;

public class DefaultFieldMatrixPreservingVisitor<T extends FieldElement<T>> implements FieldMatrixPreservingVisitor<T> {
    private final T zero;

    public void start(int i, int i2, int i3, int i4, int i5, int i6) {
    }

    public void visit(int i, int i2, T t) {
    }

    public DefaultFieldMatrixPreservingVisitor(T t) {
        this.zero = t;
    }

    public T end() {
        return this.zero;
    }
}
