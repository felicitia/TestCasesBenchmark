package androidx.work.impl.constraints.controllers;

import androidx.work.impl.constraints.ConstraintListener;
import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.model.WorkSpec;
import java.util.ArrayList;
import java.util.List;

public abstract class ConstraintController<T> implements ConstraintListener<T> {
    private OnConstraintUpdatedCallback mCallback;
    private T mCurrentValue;
    private final List<String> mMatchingWorkSpecIds = new ArrayList();
    private ConstraintTracker<T> mTracker;

    public interface OnConstraintUpdatedCallback {
        void onConstraintMet(List<String> list);

        void onConstraintNotMet(List<String> list);
    }

    /* access modifiers changed from: 0000 */
    public abstract boolean hasConstraint(WorkSpec workSpec);

    /* access modifiers changed from: 0000 */
    public abstract boolean isConstrained(T t);

    ConstraintController(ConstraintTracker<T> constraintTracker, OnConstraintUpdatedCallback onConstraintUpdatedCallback) {
        this.mTracker = constraintTracker;
        this.mCallback = onConstraintUpdatedCallback;
    }

    public void replace(List<WorkSpec> list) {
        this.mMatchingWorkSpecIds.clear();
        for (WorkSpec workSpec : list) {
            if (hasConstraint(workSpec)) {
                this.mMatchingWorkSpecIds.add(workSpec.id);
            }
        }
        if (this.mMatchingWorkSpecIds.isEmpty()) {
            this.mTracker.removeListener(this);
        } else {
            this.mTracker.addListener(this);
        }
        updateCallback();
    }

    public void reset() {
        if (!this.mMatchingWorkSpecIds.isEmpty()) {
            this.mMatchingWorkSpecIds.clear();
            this.mTracker.removeListener(this);
        }
    }

    public boolean isWorkSpecConstrained(String str) {
        return this.mCurrentValue != null && isConstrained(this.mCurrentValue) && this.mMatchingWorkSpecIds.contains(str);
    }

    private void updateCallback() {
        if (!this.mMatchingWorkSpecIds.isEmpty()) {
            if (this.mCurrentValue == null || isConstrained(this.mCurrentValue)) {
                this.mCallback.onConstraintNotMet(this.mMatchingWorkSpecIds);
            } else {
                this.mCallback.onConstraintMet(this.mMatchingWorkSpecIds);
            }
        }
    }

    public void onConstraintChanged(T t) {
        this.mCurrentValue = t;
        updateCallback();
    }
}
