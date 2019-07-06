package org.m4m.domain;

import java.util.Collection;
import java.util.LinkedList;

class ConnectedNode<T, T1> {
    LinkedList<T1> connectedTo = new LinkedList<>();
    T node;

    ConnectedNode(T t) {
        this.node = t;
    }

    public T value() {
        return this.node;
    }

    public boolean isConnected() {
        return !this.connectedTo.isEmpty();
    }

    public Collection<T1> getConnector() {
        return this.connectedTo;
    }

    public void connect(T1 t1) {
        this.connectedTo.add(t1);
    }

    public void disconnect(T1 t1) {
        this.connectedTo.remove(t1);
    }

    public boolean isConnectedTo(T1 t1) {
        return getConnector().contains(t1);
    }
}
