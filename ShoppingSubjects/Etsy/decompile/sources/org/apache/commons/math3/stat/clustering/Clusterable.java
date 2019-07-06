package org.apache.commons.math3.stat.clustering;

import java.util.Collection;

public interface Clusterable<T> {
    T centroidOf(Collection<T> collection);

    double distanceFrom(T t);
}
