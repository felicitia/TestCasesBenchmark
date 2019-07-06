package org.apache.commons.math3.linear;

import org.apache.commons.math3.FieldElement;

public class DefaultFieldMatrixChangingVisitor<T extends FieldElement<T>> implements FieldMatrixChangingVisitor<T> {
    private final T zero;

    public void start(int i, int i2, int i3, int i4, int i5, int i6) {
    }

    public T visit(int i, int i2, T t) {
        return t;
    }

    public DefaultFieldMatrixChangingVisitor(T t) {
        this.zero = t;
    }

    public T end() {
        return this.zero;
    }
}
