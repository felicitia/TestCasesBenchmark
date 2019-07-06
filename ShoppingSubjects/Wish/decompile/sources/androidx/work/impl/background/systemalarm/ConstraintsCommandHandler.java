package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.model.WorkSpec;
import java.util.ArrayList;
import java.util.List;

class ConstraintsCommandHandler {
    private final Context mContext;
    private final SystemAlarmDispatcher mDispatcher;
    private final List<WorkSpec> mEligibleWorkSpecs = new ArrayList();
    private final int mStartId;
    private final WorkConstraintsTracker mWorkConstraintsTracker = new WorkConstraintsTracker(this.mContext, null);

    ConstraintsCommandHandler(Context context, int i, SystemAlarmDispatcher systemAlarmDispatcher) {
        this.mContext = context;
        this.mStartId = i;
        this.mDispatcher = systemAlarmDispatcher;
    }

    /* access modifiers changed from: 0000 */
    public void handleConstraintsChanged() {
        List<WorkSpec> eligibleWorkForScheduling = this.mDispatcher.getWorkManager().getWorkDatabase().workSpecDao().getEligibleWorkForScheduling();
        ArrayList<WorkSpec> arrayList = new ArrayList<>(eligibleWorkForScheduling.size());
        for (WorkSpec workSpec : eligibleWorkForScheduling) {
            if (workSpec.scheduleRequestedAt != -1) {
                arrayList.add(workSpec);
            }
        }
        ConstraintProxy.updateAll(this.mContext, arrayList);
        this.mWorkConstraintsTracker.replace(arrayList);
        for (WorkSpec workSpec2 : arrayList) {
            String str = workSpec2.id;
            if (!workSpec2.hasConstraints() || this.mWorkConstraintsTracker.areAllConstraintsMet(str)) {
                this.mEligibleWorkSpecs.add(workSpec2);
            }
        }
        for (WorkSpec workSpec3 : this.mEligibleWorkSpecs) {
            String str2 = workSpec3.id;
            Intent createDelayMetIntent = CommandHandler.createDelayMetIntent(this.mContext, str2);
            Log.d("ConstraintsCmdHandler", String.format("Creating a delay_met command for workSpec with id (%s)", new Object[]{str2}));
            this.mDispatcher.postOnMainThread(new AddRunnable(this.mDispatcher, createDelayMetIntent, this.mStartId));
        }
        this.mWorkConstraintsTracker.reset();
    }
}
