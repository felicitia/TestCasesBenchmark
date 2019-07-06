package androidx.work.impl.model;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;

public class AlarmInfoDao_Impl implements AlarmInfoDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter __insertionAdapterOfAlarmInfo;
    private final SharedSQLiteStatement __preparedStmtOfRemoveAlarmInfo;

    public AlarmInfoDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfAlarmInfo = new EntityInsertionAdapter<AlarmInfo>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR FAIL INTO `alarmInfo`(`work_spec_id`,`alarm_id`) VALUES (?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, AlarmInfo alarmInfo) {
                if (alarmInfo.workSpecId == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, alarmInfo.workSpecId);
                }
                supportSQLiteStatement.bindLong(2, (long) alarmInfo.alarmId);
            }
        };
        this.__preparedStmtOfRemoveAlarmInfo = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM alarmInfo where work_spec_id=?";
            }
        };
    }

    public void insertAlarmInfo(AlarmInfo alarmInfo) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfAlarmInfo.insert(alarmInfo);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void removeAlarmInfo(String str) {
        SupportSQLiteStatement acquire = this.__preparedStmtOfRemoveAlarmInfo.acquire();
        this.__db.beginTransaction();
        if (str == null) {
            try {
                acquire.bindNull(1);
            } catch (Throwable th) {
                this.__db.endTransaction();
                this.__preparedStmtOfRemoveAlarmInfo.release(acquire);
                throw th;
            }
        } else {
            acquire.bindString(1, str);
        }
        acquire.executeUpdateDelete();
        this.__db.setTransactionSuccessful();
        this.__db.endTransaction();
        this.__preparedStmtOfRemoveAlarmInfo.release(acquire);
    }

    public AlarmInfo getAlarmInfo(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM alarmInfo WHERE work_spec_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.__db.query(acquire);
        try {
            return query.moveToFirst() ? new AlarmInfo(query.getString(query.getColumnIndexOrThrow("work_spec_id")), query.getInt(query.getColumnIndexOrThrow("alarm_id"))) : null;
        } finally {
            query.close();
            acquire.release();
        }
    }
}
