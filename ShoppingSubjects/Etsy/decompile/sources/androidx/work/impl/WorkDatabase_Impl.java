package androidx.work.impl;

import android.arch.persistence.db.b.C0002b;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.a;
import android.arch.persistence.room.b.b.C0004b;
import android.arch.persistence.room.b.b.d;
import android.os.Build.VERSION;
import androidx.work.impl.b.b;
import androidx.work.impl.b.c;
import androidx.work.impl.b.e;
import androidx.work.impl.b.f;
import androidx.work.impl.b.h;
import androidx.work.impl.b.i;
import androidx.work.impl.b.k;
import androidx.work.impl.b.l;
import androidx.work.impl.b.n;
import androidx.work.impl.b.o;
import com.etsy.android.lib.models.ResponseConstants;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class WorkDatabase_Impl extends WorkDatabase {
    private volatile b _dependencyDao;
    private volatile e _systemIdInfoDao;
    private volatile h _workNameDao;
    private volatile k _workSpecDao;
    private volatile n _workTagDao;

    /* access modifiers changed from: protected */
    public android.arch.persistence.db.b createOpenHelper(a aVar) {
        return aVar.a.create(C0002b.a(aVar.b).a(aVar.c).a((android.arch.persistence.db.b.a) new RoomOpenHelper(aVar, new RoomOpenHelper.a(4) {
            public void b(android.arch.persistence.db.a aVar) {
                aVar.c("CREATE TABLE IF NOT EXISTS `Dependency` (`work_spec_id` TEXT NOT NULL, `prerequisite_id` TEXT NOT NULL, PRIMARY KEY(`work_spec_id`, `prerequisite_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE , FOREIGN KEY(`prerequisite_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                aVar.c("CREATE  INDEX `index_Dependency_work_spec_id` ON `Dependency` (`work_spec_id`)");
                aVar.c("CREATE  INDEX `index_Dependency_prerequisite_id` ON `Dependency` (`prerequisite_id`)");
                aVar.c("CREATE TABLE IF NOT EXISTS `WorkSpec` (`id` TEXT NOT NULL, `state` INTEGER NOT NULL, `worker_class_name` TEXT NOT NULL, `input_merger_class_name` TEXT, `input` BLOB NOT NULL, `output` BLOB NOT NULL, `initial_delay` INTEGER NOT NULL, `interval_duration` INTEGER NOT NULL, `flex_duration` INTEGER NOT NULL, `run_attempt_count` INTEGER NOT NULL, `backoff_policy` INTEGER NOT NULL, `backoff_delay_duration` INTEGER NOT NULL, `period_start_time` INTEGER NOT NULL, `minimum_retention_duration` INTEGER NOT NULL, `schedule_requested_at` INTEGER NOT NULL, `required_network_type` INTEGER, `requires_charging` INTEGER NOT NULL, `requires_device_idle` INTEGER NOT NULL, `requires_battery_not_low` INTEGER NOT NULL, `requires_storage_not_low` INTEGER NOT NULL, `content_uri_triggers` BLOB, PRIMARY KEY(`id`))");
                aVar.c("CREATE  INDEX `index_WorkSpec_schedule_requested_at` ON `WorkSpec` (`schedule_requested_at`)");
                aVar.c("CREATE TABLE IF NOT EXISTS `WorkTag` (`tag` TEXT NOT NULL, `work_spec_id` TEXT NOT NULL, PRIMARY KEY(`tag`, `work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                aVar.c("CREATE  INDEX `index_WorkTag_work_spec_id` ON `WorkTag` (`work_spec_id`)");
                aVar.c("CREATE TABLE IF NOT EXISTS `SystemIdInfo` (`work_spec_id` TEXT NOT NULL, `system_id` INTEGER NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                aVar.c("CREATE TABLE IF NOT EXISTS `WorkName` (`name` TEXT NOT NULL, `work_spec_id` TEXT NOT NULL, PRIMARY KEY(`name`, `work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                aVar.c("CREATE  INDEX `index_WorkName_work_spec_id` ON `WorkName` (`work_spec_id`)");
                aVar.c("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
                aVar.c("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"c45e5fcbdf3824dead9778f19e2fd8af\")");
            }

            public void a(android.arch.persistence.db.a aVar) {
                aVar.c("DROP TABLE IF EXISTS `Dependency`");
                aVar.c("DROP TABLE IF EXISTS `WorkSpec`");
                aVar.c("DROP TABLE IF EXISTS `WorkTag`");
                aVar.c("DROP TABLE IF EXISTS `SystemIdInfo`");
                aVar.c("DROP TABLE IF EXISTS `WorkName`");
            }

            /* access modifiers changed from: protected */
            public void d(android.arch.persistence.db.a aVar) {
                if (WorkDatabase_Impl.this.mCallbacks != null) {
                    int size = WorkDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.b) WorkDatabase_Impl.this.mCallbacks.get(i)).a(aVar);
                    }
                }
            }

            public void c(android.arch.persistence.db.a aVar) {
                WorkDatabase_Impl.this.mDatabase = aVar;
                aVar.c("PRAGMA foreign_keys = ON");
                WorkDatabase_Impl.this.internalInitInvalidationTracker(aVar);
                if (WorkDatabase_Impl.this.mCallbacks != null) {
                    int size = WorkDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.b) WorkDatabase_Impl.this.mCallbacks.get(i)).b(aVar);
                    }
                }
            }

            /* access modifiers changed from: protected */
            public void e(android.arch.persistence.db.a aVar) {
                android.arch.persistence.db.a aVar2 = aVar;
                HashMap hashMap = new HashMap(2);
                hashMap.put("work_spec_id", new android.arch.persistence.room.b.b.a("work_spec_id", "TEXT", true, 1));
                hashMap.put("prerequisite_id", new android.arch.persistence.room.b.b.a("prerequisite_id", "TEXT", true, 2));
                HashSet hashSet = new HashSet(2);
                C0004b bVar = new C0004b("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"id"}));
                hashSet.add(bVar);
                C0004b bVar2 = new C0004b("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"prerequisite_id"}), Arrays.asList(new String[]{"id"}));
                hashSet.add(bVar2);
                HashSet hashSet2 = new HashSet(2);
                hashSet2.add(new d("index_Dependency_work_spec_id", false, Arrays.asList(new String[]{"work_spec_id"})));
                hashSet2.add(new d("index_Dependency_prerequisite_id", false, Arrays.asList(new String[]{"prerequisite_id"})));
                android.arch.persistence.room.b.b bVar3 = new android.arch.persistence.room.b.b("Dependency", hashMap, hashSet, hashSet2);
                android.arch.persistence.room.b.b a = android.arch.persistence.room.b.b.a(aVar2, "Dependency");
                if (!bVar3.equals(a)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Migration didn't properly handle Dependency(androidx.work.impl.model.Dependency).\n Expected:\n");
                    sb.append(bVar3);
                    sb.append("\n");
                    sb.append(" Found:\n");
                    sb.append(a);
                    throw new IllegalStateException(sb.toString());
                }
                HashMap hashMap2 = new HashMap(21);
                hashMap2.put("id", new android.arch.persistence.room.b.b.a("id", "TEXT", true, 1));
                hashMap2.put(ResponseConstants.STATE, new android.arch.persistence.room.b.b.a(ResponseConstants.STATE, "INTEGER", true, 0));
                hashMap2.put("worker_class_name", new android.arch.persistence.room.b.b.a("worker_class_name", "TEXT", true, 0));
                hashMap2.put("input_merger_class_name", new android.arch.persistence.room.b.b.a("input_merger_class_name", "TEXT", false, 0));
                hashMap2.put("input", new android.arch.persistence.room.b.b.a("input", "BLOB", true, 0));
                hashMap2.put("output", new android.arch.persistence.room.b.b.a("output", "BLOB", true, 0));
                hashMap2.put("initial_delay", new android.arch.persistence.room.b.b.a("initial_delay", "INTEGER", true, 0));
                hashMap2.put("interval_duration", new android.arch.persistence.room.b.b.a("interval_duration", "INTEGER", true, 0));
                hashMap2.put("flex_duration", new android.arch.persistence.room.b.b.a("flex_duration", "INTEGER", true, 0));
                hashMap2.put("run_attempt_count", new android.arch.persistence.room.b.b.a("run_attempt_count", "INTEGER", true, 0));
                hashMap2.put("backoff_policy", new android.arch.persistence.room.b.b.a("backoff_policy", "INTEGER", true, 0));
                hashMap2.put("backoff_delay_duration", new android.arch.persistence.room.b.b.a("backoff_delay_duration", "INTEGER", true, 0));
                hashMap2.put("period_start_time", new android.arch.persistence.room.b.b.a("period_start_time", "INTEGER", true, 0));
                hashMap2.put("minimum_retention_duration", new android.arch.persistence.room.b.b.a("minimum_retention_duration", "INTEGER", true, 0));
                hashMap2.put("schedule_requested_at", new android.arch.persistence.room.b.b.a("schedule_requested_at", "INTEGER", true, 0));
                hashMap2.put("required_network_type", new android.arch.persistence.room.b.b.a("required_network_type", "INTEGER", false, 0));
                hashMap2.put("requires_charging", new android.arch.persistence.room.b.b.a("requires_charging", "INTEGER", true, 0));
                hashMap2.put("requires_device_idle", new android.arch.persistence.room.b.b.a("requires_device_idle", "INTEGER", true, 0));
                hashMap2.put("requires_battery_not_low", new android.arch.persistence.room.b.b.a("requires_battery_not_low", "INTEGER", true, 0));
                hashMap2.put("requires_storage_not_low", new android.arch.persistence.room.b.b.a("requires_storage_not_low", "INTEGER", true, 0));
                hashMap2.put("content_uri_triggers", new android.arch.persistence.room.b.b.a("content_uri_triggers", "BLOB", false, 0));
                HashSet hashSet3 = new HashSet(0);
                HashSet hashSet4 = new HashSet(1);
                hashSet4.add(new d("index_WorkSpec_schedule_requested_at", false, Arrays.asList(new String[]{"schedule_requested_at"})));
                android.arch.persistence.room.b.b bVar4 = new android.arch.persistence.room.b.b("WorkSpec", hashMap2, hashSet3, hashSet4);
                android.arch.persistence.room.b.b a2 = android.arch.persistence.room.b.b.a(aVar2, "WorkSpec");
                if (!bVar4.equals(a2)) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Migration didn't properly handle WorkSpec(androidx.work.impl.model.WorkSpec).\n Expected:\n");
                    sb2.append(bVar4);
                    sb2.append("\n");
                    sb2.append(" Found:\n");
                    sb2.append(a2);
                    throw new IllegalStateException(sb2.toString());
                }
                HashMap hashMap3 = new HashMap(2);
                hashMap3.put("tag", new android.arch.persistence.room.b.b.a("tag", "TEXT", true, 1));
                hashMap3.put("work_spec_id", new android.arch.persistence.room.b.b.a("work_spec_id", "TEXT", true, 2));
                HashSet hashSet5 = new HashSet(1);
                C0004b bVar5 = new C0004b("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"id"}));
                hashSet5.add(bVar5);
                HashSet hashSet6 = new HashSet(1);
                hashSet6.add(new d("index_WorkTag_work_spec_id", false, Arrays.asList(new String[]{"work_spec_id"})));
                android.arch.persistence.room.b.b bVar6 = new android.arch.persistence.room.b.b("WorkTag", hashMap3, hashSet5, hashSet6);
                android.arch.persistence.room.b.b a3 = android.arch.persistence.room.b.b.a(aVar2, "WorkTag");
                if (!bVar6.equals(a3)) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Migration didn't properly handle WorkTag(androidx.work.impl.model.WorkTag).\n Expected:\n");
                    sb3.append(bVar6);
                    sb3.append("\n");
                    sb3.append(" Found:\n");
                    sb3.append(a3);
                    throw new IllegalStateException(sb3.toString());
                }
                HashMap hashMap4 = new HashMap(2);
                hashMap4.put("work_spec_id", new android.arch.persistence.room.b.b.a("work_spec_id", "TEXT", true, 1));
                hashMap4.put("system_id", new android.arch.persistence.room.b.b.a("system_id", "INTEGER", true, 0));
                HashSet hashSet7 = new HashSet(1);
                C0004b bVar7 = new C0004b("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"id"}));
                hashSet7.add(bVar7);
                android.arch.persistence.room.b.b bVar8 = new android.arch.persistence.room.b.b("SystemIdInfo", hashMap4, hashSet7, new HashSet(0));
                android.arch.persistence.room.b.b a4 = android.arch.persistence.room.b.b.a(aVar2, "SystemIdInfo");
                if (!bVar8.equals(a4)) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("Migration didn't properly handle SystemIdInfo(androidx.work.impl.model.SystemIdInfo).\n Expected:\n");
                    sb4.append(bVar8);
                    sb4.append("\n");
                    sb4.append(" Found:\n");
                    sb4.append(a4);
                    throw new IllegalStateException(sb4.toString());
                }
                HashMap hashMap5 = new HashMap(2);
                hashMap5.put(ResponseConstants.NAME, new android.arch.persistence.room.b.b.a(ResponseConstants.NAME, "TEXT", true, 1));
                hashMap5.put("work_spec_id", new android.arch.persistence.room.b.b.a("work_spec_id", "TEXT", true, 2));
                HashSet hashSet8 = new HashSet(1);
                C0004b bVar9 = new C0004b("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"id"}));
                hashSet8.add(bVar9);
                HashSet hashSet9 = new HashSet(1);
                hashSet9.add(new d("index_WorkName_work_spec_id", false, Arrays.asList(new String[]{"work_spec_id"})));
                android.arch.persistence.room.b.b bVar10 = new android.arch.persistence.room.b.b("WorkName", hashMap5, hashSet8, hashSet9);
                android.arch.persistence.room.b.b a5 = android.arch.persistence.room.b.b.a(aVar2, "WorkName");
                if (!bVar10.equals(a5)) {
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("Migration didn't properly handle WorkName(androidx.work.impl.model.WorkName).\n Expected:\n");
                    sb5.append(bVar10);
                    sb5.append("\n");
                    sb5.append(" Found:\n");
                    sb5.append(a5);
                    throw new IllegalStateException(sb5.toString());
                }
            }
        }, "c45e5fcbdf3824dead9778f19e2fd8af", "433431a854c108416da77d9b397eaeec")).a());
    }

    /* access modifiers changed from: protected */
    public android.arch.persistence.room.b createInvalidationTracker() {
        return new android.arch.persistence.room.b(this, "Dependency", "WorkSpec", "WorkTag", "SystemIdInfo", "WorkName");
    }

    public void clearAllTables() {
        super.assertNotMainThread();
        android.arch.persistence.db.a a = super.getOpenHelper().a();
        boolean z = VERSION.SDK_INT >= 21;
        if (!z) {
            try {
                a.c("PRAGMA foreign_keys = FALSE");
            } catch (Throwable th) {
                super.endTransaction();
                if (!z) {
                    a.c("PRAGMA foreign_keys = TRUE");
                }
                a.b("PRAGMA wal_checkpoint(FULL)").close();
                if (!a.d()) {
                    a.c("VACUUM");
                }
                throw th;
            }
        }
        super.beginTransaction();
        if (z) {
            a.c("PRAGMA defer_foreign_keys = TRUE");
        }
        a.c("DELETE FROM `Dependency`");
        a.c("DELETE FROM `WorkSpec`");
        a.c("DELETE FROM `WorkTag`");
        a.c("DELETE FROM `SystemIdInfo`");
        a.c("DELETE FROM `WorkName`");
        super.setTransactionSuccessful();
        super.endTransaction();
        if (!z) {
            a.c("PRAGMA foreign_keys = TRUE");
        }
        a.b("PRAGMA wal_checkpoint(FULL)").close();
        if (!a.d()) {
            a.c("VACUUM");
        }
    }

    public k workSpecDao() {
        k kVar;
        if (this._workSpecDao != null) {
            return this._workSpecDao;
        }
        synchronized (this) {
            if (this._workSpecDao == null) {
                this._workSpecDao = new l(this);
            }
            kVar = this._workSpecDao;
        }
        return kVar;
    }

    public b dependencyDao() {
        b bVar;
        if (this._dependencyDao != null) {
            return this._dependencyDao;
        }
        synchronized (this) {
            if (this._dependencyDao == null) {
                this._dependencyDao = new c(this);
            }
            bVar = this._dependencyDao;
        }
        return bVar;
    }

    public n workTagDao() {
        n nVar;
        if (this._workTagDao != null) {
            return this._workTagDao;
        }
        synchronized (this) {
            if (this._workTagDao == null) {
                this._workTagDao = new o(this);
            }
            nVar = this._workTagDao;
        }
        return nVar;
    }

    public e systemIdInfoDao() {
        e eVar;
        if (this._systemIdInfoDao != null) {
            return this._systemIdInfoDao;
        }
        synchronized (this) {
            if (this._systemIdInfoDao == null) {
                this._systemIdInfoDao = new f(this);
            }
            eVar = this._systemIdInfoDao;
        }
        return eVar;
    }

    public h workNameDao() {
        h hVar;
        if (this._workNameDao != null) {
            return this._workNameDao;
        }
        synchronized (this) {
            if (this._workNameDao == null) {
                this._workNameDao = new i(this);
            }
            hVar = this._workNameDao;
        }
        return hVar;
    }
}
