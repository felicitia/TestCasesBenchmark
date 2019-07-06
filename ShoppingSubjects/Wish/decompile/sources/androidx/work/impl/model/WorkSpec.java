package androidx.work.impl.model;

import android.arch.core.util.Function;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OverwritingInputMerger;
import androidx.work.State;
import java.util.List;

public class WorkSpec {
    public static final Function<List<Object>, List<Object>> WORK_STATUS_MAPPER = new Function<List<Object>, List<Object>>() {
    };
    public long backoffDelayDuration = 30000;
    public BackoffPolicy backoffPolicy = BackoffPolicy.EXPONENTIAL;
    public Constraints constraints = Constraints.NONE;
    public long flexDuration;
    public String id;
    public long initialDelay;
    public Data input = Data.EMPTY;
    public String inputMergerClassName = OverwritingInputMerger.class.getName();
    public long intervalDuration;
    public long minimumRetentionDuration;
    public Data output = Data.EMPTY;
    public long periodStartTime;
    public int runAttemptCount;
    public long scheduleRequestedAt = -1;
    public State state = State.ENQUEUED;
    public String workerClassName;

    public static class IdAndState {
        public String id;
        public State state;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            IdAndState idAndState = (IdAndState) obj;
            if (this.state != idAndState.state) {
                return false;
            }
            return this.id.equals(idAndState.id);
        }

        public int hashCode() {
            return (this.id.hashCode() * 31) + this.state.hashCode();
        }
    }

    public WorkSpec(String str, String str2) {
        this.id = str;
        this.workerClassName = str2;
    }

    public boolean isPeriodic() {
        return this.intervalDuration != 0;
    }

    public boolean isBackedOff() {
        return this.state == State.ENQUEUED && this.runAttemptCount > 0;
    }

    public long calculateNextRunTime() {
        long j;
        if (isBackedOff()) {
            if (this.backoffPolicy == BackoffPolicy.LINEAR) {
                j = this.backoffDelayDuration * ((long) this.runAttemptCount);
            } else {
                j = (long) Math.scalb((float) this.backoffDelayDuration, this.runAttemptCount - 1);
            }
            return this.periodStartTime + Math.min(18000000, j);
        } else if (isPeriodic()) {
            return (this.periodStartTime + this.intervalDuration) - this.flexDuration;
        } else {
            return this.periodStartTime + this.initialDelay;
        }
    }

    public boolean hasConstraints() {
        return !Constraints.NONE.equals(this.constraints);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WorkSpec workSpec = (WorkSpec) obj;
        if (this.initialDelay != workSpec.initialDelay || this.intervalDuration != workSpec.intervalDuration || this.flexDuration != workSpec.flexDuration || this.runAttemptCount != workSpec.runAttemptCount || this.backoffDelayDuration != workSpec.backoffDelayDuration || this.periodStartTime != workSpec.periodStartTime || this.minimumRetentionDuration != workSpec.minimumRetentionDuration || this.scheduleRequestedAt != workSpec.scheduleRequestedAt || !this.id.equals(workSpec.id) || this.state != workSpec.state || !this.workerClassName.equals(workSpec.workerClassName)) {
            return false;
        }
        if (this.inputMergerClassName == null ? workSpec.inputMergerClassName != null : !this.inputMergerClassName.equals(workSpec.inputMergerClassName)) {
            return false;
        }
        if (!this.input.equals(workSpec.input) || !this.output.equals(workSpec.output) || !this.constraints.equals(workSpec.constraints)) {
            return false;
        }
        if (this.backoffPolicy != workSpec.backoffPolicy) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((this.id.hashCode() * 31) + this.state.hashCode()) * 31) + this.workerClassName.hashCode()) * 31) + (this.inputMergerClassName != null ? this.inputMergerClassName.hashCode() : 0)) * 31) + this.input.hashCode()) * 31) + this.output.hashCode()) * 31) + ((int) (this.initialDelay ^ (this.initialDelay >>> 32)))) * 31) + ((int) (this.intervalDuration ^ (this.intervalDuration >>> 32)))) * 31) + ((int) (this.flexDuration ^ (this.flexDuration >>> 32)))) * 31) + this.constraints.hashCode()) * 31) + this.runAttemptCount) * 31) + this.backoffPolicy.hashCode()) * 31) + ((int) (this.backoffDelayDuration ^ (this.backoffDelayDuration >>> 32)))) * 31) + ((int) (this.periodStartTime ^ (this.periodStartTime >>> 32)))) * 31) + ((int) (this.minimumRetentionDuration ^ (this.minimumRetentionDuration >>> 32)))) * 31) + ((int) (this.scheduleRequestedAt ^ (this.scheduleRequestedAt >>> 32)));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{WorkSpec: ");
        sb.append(this.id);
        sb.append("}");
        return sb.toString();
    }
}
