package androidx.work.impl.constraints.controllers;

import android.content.Context;
import androidx.work.impl.constraints.controllers.ConstraintController.OnConstraintUpdatedCallback;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkSpec;

public class StorageNotLowController extends ConstraintController<Boolean> {
    public StorageNotLowController(Context context, OnConstraintUpdatedCallback onConstraintUpdatedCallback) {
        super(Trackers.getInstance(context).getStorageNotLowTracker(), onConstraintUpdatedCallback);
    }

    /* access modifiers changed from: 0000 */
    public boolean hasConstraint(WorkSpec workSpec) {
        return workSpec.constraints.requiresStorageNotLow();
    }

    /* access modifiers changed from: 0000 */
    public boolean isConstrained(Boolean bool) {
        return !bool.booleanValue();
    }
}
