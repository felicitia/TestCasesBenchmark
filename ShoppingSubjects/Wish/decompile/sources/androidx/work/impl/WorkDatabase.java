package androidx.work.impl;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomDatabase.Builder;
import android.arch.persistence.room.RoomDatabase.Callback;
import android.content.Context;
import androidx.work.impl.model.AlarmInfoDao;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.WorkNameDao;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkTagDao;
import java.util.concurrent.TimeUnit;

public abstract class WorkDatabase extends RoomDatabase {
    private static final long PRUNE_THRESHOLD_MILLIS = TimeUnit.DAYS.toMillis(7);

    public abstract AlarmInfoDao alarmInfoDao();

    public abstract DependencyDao dependencyDao();

    public abstract WorkNameDao workNameDao();

    public abstract WorkSpecDao workSpecDao();

    public abstract WorkTagDao workTagDao();

    public static WorkDatabase create(Context context, boolean z) {
        Builder builder;
        if (z) {
            builder = Room.inMemoryDatabaseBuilder(context, WorkDatabase.class).allowMainThreadQueries();
        } else {
            builder = Room.databaseBuilder(context, WorkDatabase.class, "androidx.work.workdb");
        }
        return (WorkDatabase) builder.addCallback(generateCleanupCallback()).build();
    }

    static Callback generateCleanupCallback() {
        return new Callback() {
            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                super.onOpen(supportSQLiteDatabase);
                supportSQLiteDatabase.beginTransaction();
                try {
                    supportSQLiteDatabase.execSQL("UPDATE workspec SET state=0 WHERE state=1");
                    supportSQLiteDatabase.execSQL(WorkDatabase.getPruneSQL());
                    supportSQLiteDatabase.setTransactionSuccessful();
                } finally {
                    supportSQLiteDatabase.endTransaction();
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public static String getPruneSQL() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM workspec WHERE state IN (2, 3, 5) AND (period_start_time + minimum_retention_duration) < ");
        sb.append(getPruneDate());
        sb.append(" AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))");
        return sb.toString();
    }

    static long getPruneDate() {
        return System.currentTimeMillis() - PRUNE_THRESHOLD_MILLIS;
    }
}
