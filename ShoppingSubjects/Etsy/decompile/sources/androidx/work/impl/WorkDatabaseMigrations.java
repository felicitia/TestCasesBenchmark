package androidx.work.impl;

import android.arch.persistence.room.a.a;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.impl.utils.e;

@RestrictTo({Scope.LIBRARY_GROUP})
public class WorkDatabaseMigrations {
    public static a a = new a(1, 2) {
        public void migrate(@NonNull android.arch.persistence.db.a aVar) {
            aVar.c("CREATE TABLE IF NOT EXISTS `SystemIdInfo` (`work_spec_id` TEXT NOT NULL, `system_id` INTEGER NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
            aVar.c("INSERT INTO SystemIdInfo(work_spec_id, system_id) SELECT work_spec_id, alarm_id AS system_id FROM alarmInfo");
            aVar.c("DROP TABLE IF EXISTS alarmInfo");
            aVar.c("INSERT OR IGNORE INTO worktag(tag, work_spec_id) SELECT worker_class_name AS tag, id AS work_spec_id FROM workspec");
        }
    };
    public static a b = new a(3, 4) {
        public void migrate(@NonNull android.arch.persistence.db.a aVar) {
            if (VERSION.SDK_INT >= 23) {
                aVar.c("UPDATE workspec SET schedule_requested_at=0 WHERE state NOT IN (2, 3, 5) AND schedule_requested_at=-1 AND interval_duration<>0");
            }
        }
    };

    public static class WorkMigration extends a {
        final Context mContext;

        public WorkMigration(@NonNull Context context, int i, int i2) {
            super(i, i2);
            this.mContext = context;
        }

        public void migrate(@NonNull android.arch.persistence.db.a aVar) {
            new e(this.mContext).a(true);
        }
    }
}
