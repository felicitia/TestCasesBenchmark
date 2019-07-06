package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.Vector;

public interface Embedding<S extends Space, T extends Space> {
    Vector<S> toSpace(Vector<T> vector);

    Vector<T> toSubSpace(Vector<S> vector);
}
