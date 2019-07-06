package androidx.work.impl.constraints.controllers;

import android.content.Context;
import androidx.work.NetworkType;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.constraints.controllers.ConstraintController.OnConstraintUpdatedCallback;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkSpec;

public class NetworkUnmeteredController extends ConstraintController<NetworkState> {
    public NetworkUnmeteredController(Context context, OnConstraintUpdatedCallback onConstraintUpdatedCallback) {
        super(Trackers.getInstance(context).getNetworkStateTracker(), onConstraintUpdatedCallback);
    }

    /* access modifiers changed from: 0000 */
    public boolean hasConstraint(WorkSpec workSpec) {
        return workSpec.constraints.getRequiredNetworkType() == NetworkType.UNMETERED;
    }

    /* access modifiers changed from: 0000 */
    public boolean isConstrained(NetworkState networkState) {
        return !networkState.isConnected() || networkState.isMetered();
    }
}
