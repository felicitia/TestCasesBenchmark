package org.apache.commons.math3.optim.linear;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.math3.optim.OptimizationData;

public class LinearConstraintSet implements OptimizationData {
    private final Set<LinearConstraint> linearConstraints = new HashSet();

    public LinearConstraintSet(LinearConstraint... linearConstraintArr) {
        for (LinearConstraint add : linearConstraintArr) {
            this.linearConstraints.add(add);
        }
    }

    public LinearConstraintSet(Collection<LinearConstraint> collection) {
        this.linearConstraints.addAll(collection);
    }

    public Collection<LinearConstraint> getConstraints() {
        return Collections.unmodifiableSet(this.linearConstraints);
    }
}
