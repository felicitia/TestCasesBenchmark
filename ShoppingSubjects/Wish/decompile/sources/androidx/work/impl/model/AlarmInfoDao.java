package androidx.work.impl.model;

public interface AlarmInfoDao {
    AlarmInfo getAlarmInfo(String str);

    void insertAlarmInfo(AlarmInfo alarmInfo);

    void removeAlarmInfo(String str);
}
