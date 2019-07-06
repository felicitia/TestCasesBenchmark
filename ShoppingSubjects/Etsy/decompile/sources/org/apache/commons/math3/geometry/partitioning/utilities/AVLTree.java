package org.apache.commons.math3.geometry.partitioning.utilities;

import java.lang.Comparable;

public class AVLTree<T extends Comparable<T>> {
    /* access modifiers changed from: private */
    public Node top = null;

    public class Node {
        /* access modifiers changed from: private */
        public T element;
        /* access modifiers changed from: private */
        public Node left = null;
        private Node parent;
        /* access modifiers changed from: private */
        public Node right = null;
        private Skew skew;

        Node(T t, Node node) {
            this.element = t;
            this.parent = node;
            this.skew = Skew.BALANCED;
        }

        public T getElement() {
            return this.element;
        }

        /* access modifiers changed from: 0000 */
        public int size() {
            int i = 0;
            int size = 1 + (this.left == null ? 0 : this.left.size());
            if (this.right != null) {
                i = this.right.size();
            }
            return size + i;
        }

        /* access modifiers changed from: 0000 */
        public Node getSmallest() {
            Node node = this;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        /* access modifiers changed from: 0000 */
        public Node getLargest() {
            Node node = this;
            while (node.right != null) {
                node = node.right;
            }
            return node;
        }

        public Node getPrevious() {
            if (this.left != null) {
                Node largest = this.left.getLargest();
                if (largest != null) {
                    return largest;
                }
            }
            for (Node node = this; node.parent != null; node = node.parent) {
                if (node != node.parent.left) {
                    return node.parent;
                }
            }
            return null;
        }

        public Node getNext() {
            if (this.right != null) {
                Node smallest = this.right.getSmallest();
                if (smallest != null) {
                    return smallest;
                }
            }
            for (Node node = this; node.parent != null; node = node.parent) {
                if (node != node.parent.right) {
                    return node.parent;
                }
            }
            return null;
        }

        /* access modifiers changed from: 0000 */
        public boolean insert(T t) {
            boolean z = false;
            if (t.compareTo(this.element) < 0) {
                if (this.left == null) {
                    this.left = new Node<>(t, this);
                    return rebalanceLeftGrown();
                }
                if (this.left.insert(t)) {
                    z = rebalanceLeftGrown();
                }
                return z;
            } else if (this.right == null) {
                this.right = new Node<>(t, this);
                return rebalanceRightGrown();
            } else {
                if (this.right.insert(t)) {
                    z = rebalanceRightGrown();
                }
                return z;
            }
        }

        public void delete() {
            Node node;
            boolean z;
            Node node2;
            if (this.parent == null && this.left == null && this.right == null) {
                this.element = null;
                AVLTree.this.top = null;
            } else {
                if (this.left == null && this.right == null) {
                    this.element = null;
                    node = null;
                    z = this == this.parent.left;
                    node2 = this;
                } else {
                    node2 = this.left != null ? this.left.getLargest() : this.right.getSmallest();
                    this.element = node2.element;
                    z = node2 == node2.parent.left;
                    node = node2.left != null ? node2.left : node2.right;
                }
                Node node3 = node2.parent;
                if (z) {
                    node3.left = node;
                } else {
                    node3.right = node;
                }
                if (node != null) {
                    node.parent = node3;
                }
                while (true) {
                    if (!z) {
                        if (!node3.rebalanceRightShrunk()) {
                            break;
                        }
                    } else if (!node3.rebalanceLeftShrunk()) {
                        break;
                    }
                    if (node3.parent != null) {
                        z = node3 == node3.parent.left;
                        node3 = node3.parent;
                    } else {
                        return;
                    }
                }
            }
        }

        private boolean rebalanceLeftGrown() {
            switch (this.skew) {
                case LEFT_HIGH:
                    if (this.left.skew == Skew.LEFT_HIGH) {
                        rotateCW();
                        this.skew = Skew.BALANCED;
                        this.right.skew = Skew.BALANCED;
                    } else {
                        Skew skew2 = this.left.right.skew;
                        this.left.rotateCCW();
                        rotateCW();
                        switch (skew2) {
                            case LEFT_HIGH:
                                this.left.skew = Skew.BALANCED;
                                this.right.skew = Skew.RIGHT_HIGH;
                                break;
                            case RIGHT_HIGH:
                                this.left.skew = Skew.LEFT_HIGH;
                                this.right.skew = Skew.BALANCED;
                                break;
                            default:
                                this.left.skew = Skew.BALANCED;
                                this.right.skew = Skew.BALANCED;
                                break;
                        }
                        this.skew = Skew.BALANCED;
                    }
                    return false;
                case RIGHT_HIGH:
                    this.skew = Skew.BALANCED;
                    return false;
                default:
                    this.skew = Skew.LEFT_HIGH;
                    return true;
            }
        }

        private boolean rebalanceRightGrown() {
            switch (this.skew) {
                case LEFT_HIGH:
                    this.skew = Skew.BALANCED;
                    return false;
                case RIGHT_HIGH:
                    if (this.right.skew == Skew.RIGHT_HIGH) {
                        rotateCCW();
                        this.skew = Skew.BALANCED;
                        this.left.skew = Skew.BALANCED;
                    } else {
                        Skew skew2 = this.right.left.skew;
                        this.right.rotateCW();
                        rotateCCW();
                        switch (skew2) {
                            case LEFT_HIGH:
                                this.left.skew = Skew.BALANCED;
                                this.right.skew = Skew.RIGHT_HIGH;
                                break;
                            case RIGHT_HIGH:
                                this.left.skew = Skew.LEFT_HIGH;
                                this.right.skew = Skew.BALANCED;
                                break;
                            default:
                                this.left.skew = Skew.BALANCED;
                                this.right.skew = Skew.BALANCED;
                                break;
                        }
                        this.skew = Skew.BALANCED;
                    }
                    return false;
                default:
                    this.skew = Skew.RIGHT_HIGH;
                    return true;
            }
        }

        private boolean rebalanceLeftShrunk() {
            switch (this.skew) {
                case LEFT_HIGH:
                    this.skew = Skew.BALANCED;
                    return true;
                case RIGHT_HIGH:
                    if (this.right.skew == Skew.RIGHT_HIGH) {
                        rotateCCW();
                        this.skew = Skew.BALANCED;
                        this.left.skew = Skew.BALANCED;
                        return true;
                    } else if (this.right.skew == Skew.BALANCED) {
                        rotateCCW();
                        this.skew = Skew.LEFT_HIGH;
                        this.left.skew = Skew.RIGHT_HIGH;
                        return false;
                    } else {
                        Skew skew2 = this.right.left.skew;
                        this.right.rotateCW();
                        rotateCCW();
                        switch (skew2) {
                            case LEFT_HIGH:
                                this.left.skew = Skew.BALANCED;
                                this.right.skew = Skew.RIGHT_HIGH;
                                break;
                            case RIGHT_HIGH:
                                this.left.skew = Skew.LEFT_HIGH;
                                this.right.skew = Skew.BALANCED;
                                break;
                            default:
                                this.left.skew = Skew.BALANCED;
                                this.right.skew = Skew.BALANCED;
                                break;
                        }
                        this.skew = Skew.BALANCED;
                        return true;
                    }
                default:
                    this.skew = Skew.RIGHT_HIGH;
                    return false;
            }
        }

        private boolean rebalanceRightShrunk() {
            switch (this.skew) {
                case LEFT_HIGH:
                    if (this.left.skew == Skew.LEFT_HIGH) {
                        rotateCW();
                        this.skew = Skew.BALANCED;
                        this.right.skew = Skew.BALANCED;
                        return true;
                    } else if (this.left.skew == Skew.BALANCED) {
                        rotateCW();
                        this.skew = Skew.RIGHT_HIGH;
                        this.right.skew = Skew.LEFT_HIGH;
                        return false;
                    } else {
                        Skew skew2 = this.left.right.skew;
                        this.left.rotateCCW();
                        rotateCW();
                        switch (skew2) {
                            case LEFT_HIGH:
                                this.left.skew = Skew.BALANCED;
                                this.right.skew = Skew.RIGHT_HIGH;
                                break;
                            case RIGHT_HIGH:
                                this.left.skew = Skew.LEFT_HIGH;
                                this.right.skew = Skew.BALANCED;
                                break;
                            default:
                                this.left.skew = Skew.BALANCED;
                                this.right.skew = Skew.BALANCED;
                                break;
                        }
                        this.skew = Skew.BALANCED;
                        return true;
                    }
                case RIGHT_HIGH:
                    this.skew = Skew.BALANCED;
                    return true;
                default:
                    this.skew = Skew.LEFT_HIGH;
                    return false;
            }
        }

        private void rotateCW() {
            T t = this.element;
            this.element = this.left.element;
            this.left.element = t;
            Node node = this.left;
            this.left = node.left;
            node.left = node.right;
            node.right = this.right;
            this.right = node;
            if (this.left != null) {
                this.left.parent = this;
            }
            if (this.right.right != null) {
                this.right.right.parent = this.right;
            }
        }

        private void rotateCCW() {
            T t = this.element;
            this.element = this.right.element;
            this.right.element = t;
            Node node = this.right;
            this.right = node.right;
            node.right = node.left;
            node.left = this.left;
            this.left = node;
            if (this.right != null) {
                this.right.parent = this;
            }
            if (this.left.left != null) {
                this.left.left.parent = this.left;
            }
        }
    }

    private enum Skew {
        LEFT_HIGH,
        RIGHT_HIGH,
        BALANCED
    }

    public void insert(T t) {
        if (t == null) {
            return;
        }
        if (this.top == null) {
            this.top = new Node<>(t, null);
        } else {
            this.top.insert(t);
        }
    }

    public boolean delete(T t) {
        if (t != null) {
            Node notSmaller = getNotSmaller(t);
            while (notSmaller != null) {
                if (notSmaller.element == t) {
                    notSmaller.delete();
                    return true;
                } else if (notSmaller.element.compareTo(t) > 0) {
                    return false;
                } else {
                    notSmaller = notSmaller.getNext();
                }
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return this.top == null;
    }

    public int size() {
        if (this.top == null) {
            return 0;
        }
        return this.top.size();
    }

    public Node getSmallest() {
        if (this.top == null) {
            return null;
        }
        return this.top.getSmallest();
    }

    public Node getLargest() {
        if (this.top == null) {
            return null;
        }
        return this.top.getLargest();
    }

    public Node getNotSmaller(T t) {
        Node node = this.top;
        Node node2 = null;
        while (node != null) {
            if (node.element.compareTo(t) < 0) {
                if (node.right == null) {
                    return node2;
                }
                node = node.right;
            } else if (node.left == null) {
                return node;
            } else {
                node2 = node;
                node = node.left;
            }
        }
        return null;
    }

    public Node getNotLarger(T t) {
        Node node = this.top;
        Node node2 = null;
        while (node != null) {
            if (node.element.compareTo(t) > 0) {
                if (node.left == null) {
                    return node2;
                }
                node = node.left;
            } else if (node.right == null) {
                return node;
            } else {
                node2 = node;
                node = node.right;
            }
        }
        return null;
    }
}
