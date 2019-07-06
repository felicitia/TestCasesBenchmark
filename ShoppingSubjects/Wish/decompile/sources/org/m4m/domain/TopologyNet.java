package org.m4m.domain;

import java.util.Collection;

class TopologyNet implements ITopologyTree {
    private Collection<ITopologyTree> mRight;
    Object value;

    TopologyNet(Object obj) {
        this.value = obj;
    }

    public Object current() {
        return this.value;
    }

    public Collection<ITopologyTree> next() {
        return this.mRight;
    }

    public void setNext(Collection<ITopologyTree> collection) {
        this.mRight = collection;
    }
}
