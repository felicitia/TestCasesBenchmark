package androidx.work.impl.constraints;

import android.content.Context;
import android.util.Log;
import androidx.work.impl.constraints.controllers.BatteryChargingController;
import androidx.work.impl.constraints.controllers.BatteryNotLowController;
import androidx.work.impl.constraints.controllers.ConstraintController;
import androidx.work.impl.constraints.controllers.ConstraintController.OnConstraintUpdatedCallback;
import androidx.work.impl.constraints.controllers.NetworkConnectedController;
import androidx.work.impl.constraints.controllers.NetworkMeteredController;
import androidx.work.impl.constraints.controllers.NetworkNotRoamingController;
import androidx.work.impl.constraints.controllers.NetworkUnmeteredController;
import androidx.work.impl.constraints.controllers.StorageNotLowController;
import androidx.work.impl.model.WorkSpec;
import java.util.ArrayList;
import java.util.List;

public class WorkConstraintsTracker implements OnConstraintUpdatedCallback {
    private final WorkConstraintsCallback mCallback;
    private final ConstraintController[] mConstraintControllers;

    public WorkConstraintsTracker(Context context, WorkConstraintsCallback workConstraintsCallback) {
        Context applicationContext = context.getApplicationContext();
        this.mCallback = workConstraintsCallback;
        this.mConstraintControllers = new ConstraintController[]{new BatteryChargingController(applicationContext, this), new BatteryNotLowController(applicationContext, this), new StorageNotLowController(applicationContext, this), new NetworkConnectedController(applicationContext, this), new NetworkUnmeteredController(applicationContext, this), new NetworkNotRoamingController(applicationContext, this), new NetworkMeteredController(applicationContext, this)};
    }

    public void replace(List<WorkSpec> list) {
        for (ConstraintController replace : this.mConstraintControllers) {
            replace.replace(list);
        }
    }

    public void reset() {
        for (ConstraintController reset : this.mConstraintControllers) {
            reset.reset();
        }
    }

    public boolean areAllConstraintsMet(String str) {
        ConstraintController[] constraintControllerArr;
        for (ConstraintController constraintController : this.mConstraintControllers) {
            if (constraintController.isWorkSpecConstrained(str)) {
                Log.d("WorkConstraintsTracker", String.format("Work %s constrained by %s", new Object[]{str, constraintController.getClass().getSimpleName()}));
                return false;
            }
        }
        return true;
    }

    public void onConstraintMet(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (areAllConstraintsMet(str)) {
                Log.d("WorkConstraintsTracker", String.format("Constraints met for %s", new Object[]{str}));
                arrayList.add(str);
            }
        }
        if (this.mCallback != null) {
            this.mCallback.onAllConstraintsMet(arrayList);
        }
    }

    public void onConstraintNotMet(List<String> list) {
        if (this.mCallback != null) {
            this.mCallback.onAllConstraintsNotMet(list);
        }
    }
}
