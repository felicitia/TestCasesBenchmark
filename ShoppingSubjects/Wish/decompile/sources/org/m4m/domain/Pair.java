package org.m4m.domain;

public class Pair<T, U> {
    public T left;
    public U right;

    public Pair(T t, U u) {
        this.left = t;
        this.right = u;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(this.left == null ? "NULL" : this.left.toString());
        sb.append(", ");
        sb.append(this.right == null ? "NULL" : this.right.toString());
        sb.append(")");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        if ((this.left != null || pair.left == null) && ((this.left == null || pair.left != null) && (this.left == null || this.left.equals(pair.left)))) {
            return (this.right != null || pair.right == null) && (this.right == null || pair.right != null) && (this.right == null || this.right.equals(pair.right));
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        if (this.left != null) {
            i = 0 + this.left.hashCode();
        }
        return this.right != null ? (i * 31) + this.right.hashCode() : i;
    }
}
