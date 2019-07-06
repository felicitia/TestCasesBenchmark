package androidx.work.impl.constraints.controllers;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import androidx.work.NetworkType;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.constraints.controllers.ConstraintController.OnConstraintUpdatedCallback;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkSpec;

public class NetworkNotRoamingController extends ConstraintController<NetworkState> {
    public NetworkNotRoamingController(Context context, OnConstraintUpdatedCallback onConstraintUpdatedCallback) {
        super(Trackers.getInstance(context).getNetworkStateTracker(), onConstraintUpdatedCallback);
    }

    /* access modifiers changed from: 0000 */
    public boolean hasConstraint(WorkSpec workSpec) {
        return workSpec.constraints.getRequiredNetworkType() == NetworkType.NOT_ROAMING;
    }

    /* access modifiers changed from: 0000 */
    public boolean isConstrained(NetworkState networkState) {
        boolean z = true;
        if (VERSION.SDK_INT < 24) {
            Log.d("NetworkNotRoamingCtrlr", "Not-roaming network constraint is not supported before API 24, only checking for connected state.");
            return !networkState.isConnected();
        }
        if (networkState.isConnected() && networkState.isNotRoaming()) {
            z = false;
        }
        return z;
    }
}
