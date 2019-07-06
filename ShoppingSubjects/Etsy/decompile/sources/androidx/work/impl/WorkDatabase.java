package androidx.work.impl;

import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomDatabase.a;
import android.arch.persistence.room.c;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.impl.WorkDatabaseMigrations.WorkMigration;
import androidx.work.impl.b.b;
import androidx.work.impl.b.e;
import androidx.work.impl.b.h;
import androidx.work.impl.b.k;
import androidx.work.impl.b.n;
import java.util.concurrent.TimeUnit;

@RestrictTo({Scope.LIBRARY_GROUP})
public abstract class WorkDatabase extends RoomDatabase {
    private static final String CLEANUP_SQL = "UPDATE workspec SET state=0, schedule_requested_at=-1 WHERE state=1";
    private static final String DB_NAME = "androidx.work.workdb";
    private static final String PRUNE_SQL_FORMAT_PREFIX = "DELETE FROM workspec WHERE state IN (2, 3, 5) AND (period_start_time + minimum_retention_duration) < ";
    private static final String PRUNE_SQL_FORMAT_SUFFIX = " AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))";
    private static final long PRUNE_THRESHOLD_MILLIS = TimeUnit.DAYS.toMillis(7);

    public abstract b dependencyDao();

    public abstract e systemIdInfoDao();

    public abstract h workNameDao();

    public abstract k workSpecDao();

    public abstract n workTagDao();

    public static WorkDatabase create(Context context, boolean z) {
        a aVar;
        if (z) {
            aVar = c.a(context, WorkDatabase.class).a();
        } else {
            aVar = c.a(context, WorkDatabase.class, DB_NAME);
        }
        return (WorkDatabase) aVar.a(generateCleanupCallback()).a(WorkDatabaseMigrations.a).a(new WorkMigration(context, 2, 3)).a(WorkDatabaseMigrations.b).b().c();
    }

    static RoomDatabase.b generateCleanupCallback() {
        return new RoomDatabase.b() {
            public void b(@NonNull android.arch.persistence.db.a aVar) {
                super.b(aVar);
                aVar.a();
                try {
                    aVar.c(WorkDatabase.CLEANUP_SQL);
                    aVar.c(WorkDatabase.getPruneSQL());
                    aVar.c();
                } finally {
                    aVar.b();
                }
            }
        };
    }

    static String getPruneSQL() {
        StringBuilder sb = new StringBuilder();
        sb.append(PRUNE_SQL_FORMAT_PREFIX);
        sb.append(getPruneDate());
        sb.append(PRUNE_SQL_FORMAT_SUFFIX);
        return sb.toString();
    }

    static long getPruneDate() {
        return System.currentTimeMillis() - PRUNE_THRESHOLD_MILLIS;
    }
}
