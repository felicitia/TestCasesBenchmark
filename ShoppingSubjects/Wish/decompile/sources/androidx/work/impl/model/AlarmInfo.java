package androidx.work.impl.model;

public class AlarmInfo {
    public final int alarmId;
    public final String workSpecId;

    public AlarmInfo(String str, int i) {
        this.workSpecId = str;
        this.alarmId = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AlarmInfo alarmInfo = (AlarmInfo) obj;
        if (this.alarmId != alarmInfo.alarmId) {
            return false;
        }
        return this.workSpecId.equals(alarmInfo.workSpecId);
    }

    public int hashCode() {
        return (this.workSpecId.hashCode() * 31) + this.alarmId;
    }
}
