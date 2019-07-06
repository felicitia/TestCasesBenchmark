package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Space;

public interface SubHyperplane<S extends Space> {

    public static class SplitSubHyperplane<U extends Space> {
        private final SubHyperplane<U> minus;
        private final SubHyperplane<U> plus;

        public SplitSubHyperplane(SubHyperplane<U> subHyperplane, SubHyperplane<U> subHyperplane2) {
            this.plus = subHyperplane;
            this.minus = subHyperplane2;
        }

        public SubHyperplane<U> getPlus() {
            return this.plus;
        }

        public SubHyperplane<U> getMinus() {
            return this.minus;
        }
    }

    SubHyperplane<S> copySelf();

    Hyperplane<S> getHyperplane();

    double getSize();

    boolean isEmpty();

    SubHyperplane<S> reunite(SubHyperplane<S> subHyperplane);

    Side side(Hyperplane<S> hyperplane);

    SplitSubHyperplane<S> split(Hyperplane<S> hyperplane);
}
