package org.m4m.domain;

import java.util.Collection;

interface ITopologyTree<T> {
    T current();

    Collection<ITopologyTree<T>> next();
}
