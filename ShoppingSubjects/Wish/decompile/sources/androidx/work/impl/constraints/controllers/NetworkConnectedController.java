package androidx.work.impl.constraints.controllers;

import android.content.Context;
import android.os.Build.VERSION;
import androidx.work.NetworkType;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.constraints.controllers.ConstraintController.OnConstraintUpdatedCallback;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkSpec;

public class NetworkConnectedController extends ConstraintController<NetworkState> {
    public NetworkConnectedController(Context context, OnConstraintUpdatedCallback onConstraintUpdatedCallback) {
        super(Trackers.getInstance(context).getNetworkStateTracker(), onConstraintUpdatedCallback);
    }

    /* access modifiers changed from: 0000 */
    public boolean hasConstraint(WorkSpec workSpec) {
        return workSpec.constraints.getRequiredNetworkType() == NetworkType.CONNECTED;
    }

    /* access modifiers changed from: 0000 */
    public boolean isConstrained(NetworkState networkState) {
        boolean z = true;
        if (VERSION.SDK_INT < 26) {
            return !networkState.isConnected();
        }
        if (networkState.isConnected() && networkState.isValidated()) {
            z = false;
        }
        return z;
    }
}