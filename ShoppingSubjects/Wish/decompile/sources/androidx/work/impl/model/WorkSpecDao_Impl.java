package androidx.work.impl.model;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.arch.persistence.room.util.StringUtil;
import android.database.Cursor;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.State;
import androidx.work.impl.model.WorkSpec.IdAndState;
import java.util.ArrayList;
import java.util.List;

public class WorkSpecDao_Impl implements WorkSpecDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter __insertionAdapterOfWorkSpec;
    private final SharedSQLiteStatement __preparedStmtOfDelete;
    private final SharedSQLiteStatement __preparedStmtOfIncrementWorkSpecRunAttemptCount;
    private final SharedSQLiteStatement __preparedStmtOfMarkWorkSpecScheduled;
    private final SharedSQLiteStatement __preparedStmtOfResetScheduledState;
    private final SharedSQLiteStatement __preparedStmtOfResetWorkSpecRunAttemptCount;
    private final SharedSQLiteStatement __preparedStmtOfSetOutput;
    private final SharedSQLiteStatement __preparedStmtOfSetPeriodStartTime;

    public WorkSpecDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfWorkSpec = new EntityInsertionAdapter<WorkSpec>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR IGNORE INTO `WorkSpec`(`id`,`state`,`worker_class_name`,`input_merger_class_name`,`input`,`output`,`initial_delay`,`interval_duration`,`flex_duration`,`run_attempt_count`,`backoff_policy`,`backoff_delay_duration`,`period_start_time`,`minimum_retention_duration`,`schedule_requested_at`,`required_network_type`,`requires_charging`,`requires_device_idle`,`requires_battery_not_low`,`requires_storage_not_low`,`content_uri_triggers`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, WorkSpec workSpec) {
                if (workSpec.id == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, workSpec.id);
                }
                supportSQLiteStatement.bindLong(2, (long) WorkTypeConverters.stateToInt(workSpec.state));
                if (workSpec.workerClassName == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, workSpec.workerClassName);
                }
                if (workSpec.inputMergerClassName == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, workSpec.inputMergerClassName);
                }
                byte[] byteArray = Data.toByteArray(workSpec.input);
                if (byteArray == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindBlob(5, byteArray);
                }
                byte[] byteArray2 = Data.toByteArray(workSpec.output);
                if (byteArray2 == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindBlob(6, byteArray2);
                }
                supportSQLiteStatement.bindLong(7, workSpec.initialDelay);
                supportSQLiteStatement.bindLong(8, workSpec.intervalDuration);
                supportSQLiteStatement.bindLong(9, workSpec.flexDuration);
                supportSQLiteStatement.bindLong(10, (long) workSpec.runAttemptCount);
                supportSQLiteStatement.bindLong(11, (long) WorkTypeConverters.backoffPolicyToInt(workSpec.backoffPolicy));
                supportSQLiteStatement.bindLong(12, workSpec.backoffDelayDuration);
                supportSQLiteStatement.bindLong(13, workSpec.periodStartTime);
                supportSQLiteStatement.bindLong(14, workSpec.minimumRetentionDuration);
                supportSQLiteStatement.bindLong(15, workSpec.scheduleRequestedAt);
                Constraints constraints = workSpec.constraints;
                if (constraints != null) {
                    supportSQLiteStatement.bindLong(16, (long) WorkTypeConverters.networkTypeToInt(constraints.getRequiredNetworkType()));
                    supportSQLiteStatement.bindLong(17, constraints.requiresCharging() ? 1 : 0);
                    supportSQLiteStatement.bindLong(18, constraints.requiresDeviceIdle() ? 1 : 0);
                    supportSQLiteStatement.bindLong(19, constraints.requiresBatteryNotLow() ? 1 : 0);
                    supportSQLiteStatement.bindLong(20, constraints.requiresStorageNotLow() ? 1 : 0);
                    byte[] contentUriTriggersToByteArray = WorkTypeConverters.contentUriTriggersToByteArray(constraints.getContentUriTriggers());
                    if (contentUriTriggersToByteArray == null) {
                        supportSQLiteStatement.bindNull(21);
                    } else {
                        supportSQLiteStatement.bindBlob(21, contentUriTriggersToByteArray);
                    }
                } else {
                    supportSQLiteStatement.bindNull(16);
                    supportSQLiteStatement.bindNull(17);
                    supportSQLiteStatement.bindNull(18);
                    supportSQLiteStatement.bindNull(19);
                    supportSQLiteStatement.bindNull(20);
                    supportSQLiteStatement.bindNull(21);
                }
            }
        };
        this.__preparedStmtOfDelete = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM workspec WHERE id=?";
            }
        };
        this.__preparedStmtOfSetOutput = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET output=? WHERE id=?";
            }
        };
        this.__preparedStmtOfSetPeriodStartTime = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET period_start_time=? WHERE id=?";
            }
        };
        this.__preparedStmtOfIncrementWorkSpecRunAttemptCount = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET run_attempt_count=run_attempt_count+1 WHERE id=?";
            }
        };
        this.__preparedStmtOfResetWorkSpecRunAttemptCount = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET run_attempt_count=0 WHERE id=?";
            }
        };
        this.__preparedStmtOfMarkWorkSpecScheduled = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET schedule_requested_at=? WHERE id=?";
            }
        };
        this.__preparedStmtOfResetScheduledState = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET schedule_requested_at=-1 WHERE state NOT IN (2, 3, 5)";
            }
        };
    }

    public void insertWorkSpec(WorkSpec workSpec) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfWorkSpec.insert(workSpec);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void delete(String str) {
        SupportSQLiteStatement acquire = this.__preparedStmtOfDelete.acquire();
        this.__db.beginTransaction();
        if (str == null) {
            try {
                acquire.bindNull(1);
            } catch (Throwable th) {
                this.__db.endTransaction();
                this.__preparedStmtOfDelete.release(acquire);
                throw th;
            }
        } else {
            acquire.bindString(1, str);
        }
        acquire.executeUpdateDelete();
        this.__db.setTransactionSuccessful();
        this.__db.endTransaction();
        this.__preparedStmtOfDelete.release(acquire);
    }

    public void setOutput(String str, Data data) {
        SupportSQLiteStatement acquire = this.__preparedStmtOfSetOutput.acquire();
        this.__db.beginTransaction();
        try {
            byte[] byteArray = Data.toByteArray(data);
            if (byteArray == null) {
                acquire.bindNull(1);
            } else {
                acquire.bindBlob(1, byteArray);
            }
            if (str == null) {
                acquire.bindNull(2);
            } else {
                acquire.bindString(2, str);
            }
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfSetOutput.release(acquire);
        }
    }

    public void setPeriodStartTime(String str, long j) {
        SupportSQLiteStatement acquire = this.__preparedStmtOfSetPeriodStartTime.acquire();
        this.__db.beginTransaction();
        try {
            acquire.bindLong(1, j);
            if (str == null) {
                acquire.bindNull(2);
            } else {
                acquire.bindString(2, str);
            }
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfSetPeriodStartTime.release(acquire);
        }
    }

    public int incrementWorkSpecRunAttemptCount(String str) {
        SupportSQLiteStatement acquire = this.__preparedStmtOfIncrementWorkSpecRunAttemptCount.acquire();
        this.__db.beginTransaction();
        if (str == null) {
            try {
                acquire.bindNull(1);
            } catch (Throwable th) {
                this.__db.endTransaction();
                this.__preparedStmtOfIncrementWorkSpecRunAttemptCount.release(acquire);
                throw th;
            }
        } else {
            acquire.bindString(1, str);
        }
        int executeUpdateDelete = acquire.executeUpdateDelete();
        this.__db.setTransactionSuccessful();
        this.__db.endTransaction();
        this.__preparedStmtOfIncrementWorkSpecRunAttemptCount.release(acquire);
        return executeUpdateDelete;
    }

    public int resetWorkSpecRunAttemptCount(String str) {
        SupportSQLiteStatement acquire = this.__preparedStmtOfResetWorkSpecRunAttemptCount.acquire();
        this.__db.beginTransaction();
        if (str == null) {
            try {
                acquire.bindNull(1);
            } catch (Throwable th) {
                this.__db.endTransaction();
                this.__preparedStmtOfResetWorkSpecRunAttemptCount.release(acquire);
                throw th;
            }
        } else {
            acquire.bindString(1, str);
        }
        int executeUpdateDelete = acquire.executeUpdateDelete();
        this.__db.setTransactionSuccessful();
        this.__db.endTransaction();
        this.__preparedStmtOfResetWorkSpecRunAttemptCount.release(acquire);
        return executeUpdateDelete;
    }

    public int markWorkSpecScheduled(String str, long j) {
        SupportSQLiteStatement acquire = this.__preparedStmtOfMarkWorkSpecScheduled.acquire();
        this.__db.beginTransaction();
        try {
            acquire.bindLong(1, j);
            if (str == null) {
                acquire.bindNull(2);
            } else {
                acquire.bindString(2, str);
            }
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            return executeUpdateDelete;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfMarkWorkSpecScheduled.release(acquire);
        }
    }

    public int resetScheduledState() {
        SupportSQLiteStatement acquire = this.__preparedStmtOfResetScheduledState.acquire();
        this.__db.beginTransaction();
        try {
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            return executeUpdateDelete;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfResetScheduledState.release(acquire);
        }
    }

    public WorkSpec getWorkSpec(String str) {
        Throwable th;
        WorkSpec workSpec;
        String str2 = str;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE id=?", 1);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        Cursor query = this.__db.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("state");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("worker_class_name");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("input_merger_class_name");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("input");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("output");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("initial_delay");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("interval_duration");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("flex_duration");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("run_attempt_count");
            int columnIndexOrThrow11 = query.getColumnIndexOrThrow("backoff_policy");
            int columnIndexOrThrow12 = query.getColumnIndexOrThrow("backoff_delay_duration");
            int columnIndexOrThrow13 = query.getColumnIndexOrThrow("period_start_time");
            RoomSQLiteQuery roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow("minimum_retention_duration");
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow("schedule_requested_at");
                int columnIndexOrThrow16 = query.getColumnIndexOrThrow("required_network_type");
                int i = columnIndexOrThrow13;
                int columnIndexOrThrow17 = query.getColumnIndexOrThrow("requires_charging");
                int i2 = columnIndexOrThrow12;
                int columnIndexOrThrow18 = query.getColumnIndexOrThrow("requires_device_idle");
                int i3 = columnIndexOrThrow11;
                int columnIndexOrThrow19 = query.getColumnIndexOrThrow("requires_battery_not_low");
                int i4 = columnIndexOrThrow10;
                int columnIndexOrThrow20 = query.getColumnIndexOrThrow("requires_storage_not_low");
                int i5 = columnIndexOrThrow9;
                int columnIndexOrThrow21 = query.getColumnIndexOrThrow("content_uri_triggers");
                if (query.moveToFirst()) {
                    try {
                        String string = query.getString(columnIndexOrThrow);
                        String string2 = query.getString(columnIndexOrThrow3);
                        int i6 = columnIndexOrThrow8;
                        Constraints constraints = new Constraints();
                        constraints.setRequiredNetworkType(WorkTypeConverters.intToNetworkType(query.getInt(columnIndexOrThrow16)));
                        boolean z = false;
                        constraints.setRequiresCharging(query.getInt(columnIndexOrThrow17) != 0);
                        constraints.setRequiresDeviceIdle(query.getInt(columnIndexOrThrow18) != 0);
                        constraints.setRequiresBatteryNotLow(query.getInt(columnIndexOrThrow19) != 0);
                        if (query.getInt(columnIndexOrThrow20) != 0) {
                            z = true;
                        }
                        constraints.setRequiresStorageNotLow(z);
                        constraints.setContentUriTriggers(WorkTypeConverters.byteArrayToContentUriTriggers(query.getBlob(columnIndexOrThrow21)));
                        workSpec = new WorkSpec(string, string2);
                        workSpec.state = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                        workSpec.inputMergerClassName = query.getString(columnIndexOrThrow4);
                        workSpec.input = Data.fromByteArray(query.getBlob(columnIndexOrThrow5));
                        workSpec.output = Data.fromByteArray(query.getBlob(columnIndexOrThrow6));
                        workSpec.initialDelay = query.getLong(columnIndexOrThrow7);
                        workSpec.intervalDuration = query.getLong(i6);
                        workSpec.flexDuration = query.getLong(i5);
                        workSpec.runAttemptCount = query.getInt(i4);
                        workSpec.backoffPolicy = WorkTypeConverters.intToBackoffPolicy(query.getInt(i3));
                        workSpec.backoffDelayDuration = query.getLong(i2);
                        workSpec.periodStartTime = query.getLong(i);
                        workSpec.minimumRetentionDuration = query.getLong(columnIndexOrThrow14);
                        workSpec.scheduleRequestedAt = query.getLong(columnIndexOrThrow15);
                        workSpec.constraints = constraints;
                    } catch (Throwable th2) {
                        th = th2;
                        acquire = roomSQLiteQuery;
                        query.close();
                        acquire.release();
                        throw th;
                    }
                } else {
                    workSpec = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return workSpec;
            } catch (Throwable th3) {
                th = th3;
                acquire = roomSQLiteQuery;
                th = th;
                query.close();
                acquire.release();
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            th = th;
            query.close();
            acquire.release();
            throw th;
        }
    }

    public List<IdAndState> getWorkSpecIdAndStatesForName(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id, state FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.__db.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("state");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                IdAndState idAndState = new IdAndState();
                idAndState.id = query.getString(columnIndexOrThrow);
                idAndState.state = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                arrayList.add(idAndState);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public State getState(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT state FROM workspec WHERE id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.__db.query(acquire);
        try {
            return query.moveToFirst() ? WorkTypeConverters.intToState(query.getInt(0)) : null;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public List<Data> getInputsFromPrerequisites(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT output FROM workspec WHERE id IN (SELECT prerequisite_id FROM dependency WHERE work_spec_id=?)", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.__db.query(acquire);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(Data.fromByteArray(query.getBlob(0)));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public List<String> getUnfinishedWorkWithTag(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM worktag WHERE tag=?)", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.__db.query(acquire);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(query.getString(0));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public List<String> getUnfinishedWorkWithName(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.__db.query(acquire);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(query.getString(0));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public List<WorkSpec> getEligibleWorkForScheduling() {
        Throwable th;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from workspec WHERE state=0 AND schedule_requested_at=-1 LIMIT (SELECT 100-COUNT(*) FROM workspec WHERE schedule_requested_at<>-1 AND state NOT IN (2, 3, 5))", 0);
        Cursor query = this.__db.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("state");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("worker_class_name");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("input_merger_class_name");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("input");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("output");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("initial_delay");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("interval_duration");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("flex_duration");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("run_attempt_count");
            int columnIndexOrThrow11 = query.getColumnIndexOrThrow("backoff_policy");
            int columnIndexOrThrow12 = query.getColumnIndexOrThrow("backoff_delay_duration");
            int columnIndexOrThrow13 = query.getColumnIndexOrThrow("period_start_time");
            RoomSQLiteQuery roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow("minimum_retention_duration");
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow("schedule_requested_at");
                int columnIndexOrThrow16 = query.getColumnIndexOrThrow("required_network_type");
                int i = columnIndexOrThrow13;
                int columnIndexOrThrow17 = query.getColumnIndexOrThrow("requires_charging");
                int i2 = columnIndexOrThrow12;
                int columnIndexOrThrow18 = query.getColumnIndexOrThrow("requires_device_idle");
                int i3 = columnIndexOrThrow11;
                int columnIndexOrThrow19 = query.getColumnIndexOrThrow("requires_battery_not_low");
                int i4 = columnIndexOrThrow10;
                int columnIndexOrThrow20 = query.getColumnIndexOrThrow("requires_storage_not_low");
                int i5 = columnIndexOrThrow9;
                int columnIndexOrThrow21 = query.getColumnIndexOrThrow("content_uri_triggers");
                int i6 = columnIndexOrThrow8;
                int i7 = columnIndexOrThrow7;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    try {
                        String string = query.getString(columnIndexOrThrow);
                        int i8 = columnIndexOrThrow;
                        String string2 = query.getString(columnIndexOrThrow3);
                        int i9 = columnIndexOrThrow3;
                        Constraints constraints = new Constraints();
                        ArrayList arrayList2 = arrayList;
                        constraints.setRequiredNetworkType(WorkTypeConverters.intToNetworkType(query.getInt(columnIndexOrThrow16)));
                        constraints.setRequiresCharging(query.getInt(columnIndexOrThrow17) != 0);
                        constraints.setRequiresDeviceIdle(query.getInt(columnIndexOrThrow18) != 0);
                        constraints.setRequiresBatteryNotLow(query.getInt(columnIndexOrThrow19) != 0);
                        constraints.setRequiresStorageNotLow(query.getInt(columnIndexOrThrow20) != 0);
                        constraints.setContentUriTriggers(WorkTypeConverters.byteArrayToContentUriTriggers(query.getBlob(columnIndexOrThrow21)));
                        WorkSpec workSpec = new WorkSpec(string, string2);
                        workSpec.state = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                        workSpec.inputMergerClassName = query.getString(columnIndexOrThrow4);
                        workSpec.input = Data.fromByteArray(query.getBlob(columnIndexOrThrow5));
                        workSpec.output = Data.fromByteArray(query.getBlob(columnIndexOrThrow6));
                        int i10 = columnIndexOrThrow16;
                        int i11 = columnIndexOrThrow18;
                        int i12 = i7;
                        workSpec.initialDelay = query.getLong(i12);
                        int i13 = columnIndexOrThrow17;
                        int i14 = i6;
                        workSpec.intervalDuration = query.getLong(i14);
                        int i15 = i12;
                        int i16 = columnIndexOrThrow2;
                        int i17 = i5;
                        workSpec.flexDuration = query.getLong(i17);
                        int i18 = i4;
                        workSpec.runAttemptCount = query.getInt(i18);
                        int i19 = i3;
                        workSpec.backoffPolicy = WorkTypeConverters.intToBackoffPolicy(query.getInt(i19));
                        int i20 = i14;
                        int i21 = i17;
                        int i22 = i2;
                        workSpec.backoffDelayDuration = query.getLong(i22);
                        int i23 = i18;
                        int i24 = i;
                        workSpec.periodStartTime = query.getLong(i24);
                        int i25 = i19;
                        int i26 = i22;
                        int i27 = columnIndexOrThrow14;
                        workSpec.minimumRetentionDuration = query.getLong(i27);
                        int i28 = columnIndexOrThrow15;
                        workSpec.scheduleRequestedAt = query.getLong(i28);
                        workSpec.constraints = constraints;
                        ArrayList arrayList3 = arrayList2;
                        arrayList3.add(workSpec);
                        i = i24;
                        columnIndexOrThrow14 = i27;
                        columnIndexOrThrow15 = i28;
                        arrayList = arrayList3;
                        columnIndexOrThrow = i8;
                        columnIndexOrThrow3 = i9;
                        columnIndexOrThrow16 = i10;
                        columnIndexOrThrow18 = i11;
                        columnIndexOrThrow17 = i13;
                        columnIndexOrThrow2 = i16;
                        i7 = i15;
                        i6 = i20;
                        i5 = i21;
                        i4 = i23;
                        i3 = i25;
                        i2 = i26;
                    } catch (Throwable th2) {
                        th = th2;
                        acquire = roomSQLiteQuery;
                        query.close();
                        acquire.release();
                        throw th;
                    }
                }
                ArrayList arrayList4 = arrayList;
                query.close();
                roomSQLiteQuery.release();
                return arrayList4;
            } catch (Throwable th3) {
                th = th3;
                acquire = roomSQLiteQuery;
                th = th;
                query.close();
                acquire.release();
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            th = th;
            query.close();
            acquire.release();
            throw th;
        }
    }

    public int setState(State state, String... strArr) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("UPDATE workspec SET state=");
        newStringBuilder.append("?");
        newStringBuilder.append(" WHERE id IN (");
        StringUtil.appendPlaceholders(newStringBuilder, strArr.length);
        newStringBuilder.append(")");
        SupportSQLiteStatement compileStatement = this.__db.compileStatement(newStringBuilder.toString());
        compileStatement.bindLong(1, (long) WorkTypeConverters.stateToInt(state));
        int i = 2;
        for (String str : strArr) {
            if (str == null) {
                compileStatement.bindNull(i);
            } else {
                compileStatement.bindString(i, str);
            }
            i++;
        }
        this.__db.beginTransaction();
        try {
            int executeUpdateDelete = compileStatement.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            return executeUpdateDelete;
        } finally {
            this.__db.endTransaction();
        }
    }
}
