package org.m4m.domain;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

class TopologySolver {
    private final LinkedList<LeftNode> pureSources = new LinkedList<>();
    private final LinkedList<IsConnectable> rules = new LinkedList<>();
    private final LinkedList<RightNode> sinks = new LinkedList<>();
    private boolean solved;
    private final LinkedList<LeftNode> sources = new LinkedList<>();
    private LinkedList<ITopologyTree> trees;

    class LeftNode extends ConnectedNode<IOutputRaw, IInputRaw> {
        LeftNode(IOutputRaw iOutputRaw) {
            super(iOutputRaw);
        }
    }

    class RightNode extends ConnectedNode<IInputRaw, IOutputRaw> {
        RightNode(IInputRaw iInputRaw) {
            super(iInputRaw);
        }
    }

    TopologySolver() {
    }

    public void addConnectionRule(IsConnectable isConnectable) {
        assertIsNotSolved();
        this.rules.add(isConnectable);
    }

    public void add(ITransform iTransform) {
        assertIsNotSolved();
        this.sinks.add(new RightNode(iTransform));
        this.sources.add(new LeftNode(iTransform));
    }

    public void add(IOutputRaw iOutputRaw) {
        assertIsNotSolved();
        LeftNode leftNode = new LeftNode(iOutputRaw);
        this.pureSources.add(leftNode);
        this.sources.add(leftNode);
    }

    public void add(IInputRaw iInputRaw) {
        assertIsNotSolved();
        this.sinks.add(new RightNode(iInputRaw));
    }

    public Collection<IOutputRaw> getSources() {
        LinkedList linkedList = new LinkedList();
        Iterator it = this.pureSources.iterator();
        while (it.hasNext()) {
            linkedList.add(((LeftNode) it.next()).value());
        }
        return linkedList;
    }

    public Collection<IInputRaw> getSinks() {
        LinkedList linkedList = new LinkedList();
        Iterator it = this.sinks.iterator();
        while (it.hasNext()) {
            linkedList.add(((RightNode) it.next()).value());
        }
        return linkedList;
    }

    public Collection<Pair<IOutputRaw, IInputRaw>> getConnectionsQueue() {
        resolve();
        LinkedList linkedList = new LinkedList();
        Iterator it = this.trees.iterator();
        while (it.hasNext()) {
            buildConnectionQueue((ITopologyTree) it.next(), linkedList, true);
        }
        return linkedList;
    }

    private void buildConnectionQueue(ITopologyTree iTopologyTree, LinkedList<Pair<IOutputRaw, IInputRaw>> linkedList, boolean z) {
        if (iTopologyTree != null && (iTopologyTree.current() instanceof IOutputRaw)) {
            IOutputRaw iOutputRaw = (IOutputRaw) iTopologyTree.current();
            for (ITopologyTree iTopologyTree2 : iTopologyTree.next()) {
                IInputRaw iInputRaw = (IInputRaw) iTopologyTree2.current();
                if (!iInputRaw.canConnectFirst(iOutputRaw) || (z && !iOutputRaw.canConnectFirst(iInputRaw))) {
                    buildConnectionQueue(iTopologyTree2, linkedList, false);
                    linkedList.add(new Pair(iOutputRaw, iInputRaw));
                } else {
                    linkedList.add(new Pair(iOutputRaw, iInputRaw));
                    buildConnectionQueue(iTopologyTree2, linkedList, false);
                }
            }
        }
    }

    public Collection<ITopologyTree> resolve() throws RuntimeException {
        if (!this.solved) {
            if (!continueResolve()) {
                throw new IllegalStateException("Cannot resolve");
            }
            this.trees = new LinkedList<>();
            Iterator it = this.pureSources.iterator();
            while (it.hasNext()) {
                this.trees.add(buildTree((LeftNode) it.next()));
            }
            this.solved = true;
        }
        return this.trees;
    }

    private void assertIsNotSolved() {
        if (this.solved) {
            throw new IllegalStateException("cannot modify topology after solving");
        }
    }

    private ITopologyTree buildTree(IInputRaw iInputRaw) {
        LeftNode findOutputForTransform = findOutputForTransform(iInputRaw);
        if (findOutputForTransform == null) {
            return new TopologyNet(iInputRaw);
        }
        return buildTree(findOutputForTransform);
    }

    private ITopologyTree buildTree(LeftNode leftNode) {
        TopologyNet topologyNet = new TopologyNet(leftNode.value());
        for (IInputRaw iInputRaw : leftNode.getConnector()) {
            if (topologyNet.next() == null) {
                topologyNet.setNext(new LinkedList());
            }
            topologyNet.next().add(buildTree(iInputRaw));
        }
        return topologyNet;
    }

    private LeftNode findOutputForTransform(IInputRaw iInputRaw) {
        if (!(iInputRaw instanceof ITransform)) {
            return null;
        }
        Iterator it = this.sources.iterator();
        while (it.hasNext()) {
            LeftNode leftNode = (LeftNode) it.next();
            if ((leftNode.value() instanceof ITransform) && iInputRaw == leftNode.value()) {
                return leftNode;
            }
        }
        return null;
    }

    private boolean continueResolve() {
        Iterator it = this.sources.iterator();
        boolean z = true;
        while (it.hasNext()) {
            LeftNode leftNode = (LeftNode) it.next();
            if (!leftNode.isConnected()) {
                z = false;
            }
            Iterator it2 = this.sinks.iterator();
            while (true) {
                if (it2.hasNext()) {
                    RightNode rightNode = (RightNode) it2.next();
                    if (!leftNode.isConnectedTo(rightNode.value())) {
                        leftNode.connect(rightNode.value());
                        rightNode.connect(leftNode.value());
                        if (matchConnectionRules(leftNode, rightNode) && continueResolve()) {
                            return true;
                        }
                        rightNode.disconnect(leftNode.value());
                        leftNode.disconnect(rightNode.value());
                    }
                }
            }
        }
        return z;
    }

    private boolean matchConnectionRules(LeftNode leftNode, RightNode rightNode) {
        Iterator it = this.rules.iterator();
        while (it.hasNext()) {
            IsConnectable isConnectable = (IsConnectable) it.next();
            if (isConnectable.isConnectable((IOutputRaw) leftNode.value(), leftNode.getConnector()) && isConnectable.isConnectable(rightNode.getConnector(), (IInputRaw) rightNode.value())) {
                return true;
            }
        }
        return false;
    }
}
