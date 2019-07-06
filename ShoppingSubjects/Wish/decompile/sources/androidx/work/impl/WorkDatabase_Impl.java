package androidx.work.impl;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase.Callback;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import androidx.work.impl.model.AlarmInfoDao;
import androidx.work.impl.model.AlarmInfoDao_Impl;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.DependencyDao_Impl;
import androidx.work.impl.model.WorkNameDao;
import androidx.work.impl.model.WorkNameDao_Impl;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkSpecDao_Impl;
import androidx.work.impl.model.WorkTagDao;
import androidx.work.impl.model.WorkTagDao_Impl;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class WorkDatabase_Impl extends WorkDatabase {
    private volatile AlarmInfoDao _alarmInfoDao;
    private volatile DependencyDao _dependencyDao;
    private volatile WorkNameDao _workNameDao;
    private volatile WorkSpecDao _workSpecDao;
    private volatile WorkTagDao _workTagDao;

    /* access modifiers changed from: protected */
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new Delegate(1) {
            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `Dependency` (`work_spec_id` TEXT NOT NULL, `prerequisite_id` TEXT NOT NULL, PRIMARY KEY(`work_spec_id`, `prerequisite_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE , FOREIGN KEY(`prerequisite_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                supportSQLiteDatabase.execSQL("CREATE  INDEX `index_Dependency_work_spec_id` ON `Dependency` (`work_spec_id`)");
                supportSQLiteDatabase.execSQL("CREATE  INDEX `index_Dependency_prerequisite_id` ON `Dependency` (`prerequisite_id`)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `WorkSpec` (`id` TEXT NOT NULL, `state` INTEGER NOT NULL, `worker_class_name` TEXT NOT NULL, `input_merger_class_name` TEXT, `input` BLOB NOT NULL, `output` BLOB NOT NULL, `initial_delay` INTEGER NOT NULL, `interval_duration` INTEGER NOT NULL, `flex_duration` INTEGER NOT NULL, `run_attempt_count` INTEGER NOT NULL, `backoff_policy` INTEGER NOT NULL, `backoff_delay_duration` INTEGER NOT NULL, `period_start_time` INTEGER NOT NULL, `minimum_retention_duration` INTEGER NOT NULL, `schedule_requested_at` INTEGER NOT NULL, `required_network_type` INTEGER, `requires_charging` INTEGER NOT NULL, `requires_device_idle` INTEGER NOT NULL, `requires_battery_not_low` INTEGER NOT NULL, `requires_storage_not_low` INTEGER NOT NULL, `content_uri_triggers` BLOB, PRIMARY KEY(`id`))");
                supportSQLiteDatabase.execSQL("CREATE  INDEX `index_WorkSpec_schedule_requested_at` ON `WorkSpec` (`schedule_requested_at`)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `WorkTag` (`tag` TEXT NOT NULL, `work_spec_id` TEXT NOT NULL, PRIMARY KEY(`tag`, `work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                supportSQLiteDatabase.execSQL("CREATE  INDEX `index_WorkTag_work_spec_id` ON `WorkTag` (`work_spec_id`)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `alarmInfo` (`work_spec_id` TEXT NOT NULL, `alarm_id` INTEGER NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `WorkName` (`name` TEXT NOT NULL, `work_spec_id` TEXT NOT NULL, PRIMARY KEY(`name`, `work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                supportSQLiteDatabase.execSQL("CREATE  INDEX `index_WorkName_work_spec_id` ON `WorkName` (`work_spec_id`)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"f890a1c3a43db87aba55eaaf535b9e03\")");
            }

            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `Dependency`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `WorkSpec`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `WorkTag`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `alarmInfo`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `WorkName`");
            }

            /* access modifiers changed from: protected */
            public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (WorkDatabase_Impl.this.mCallbacks != null) {
                    int size = WorkDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((Callback) WorkDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                WorkDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
                supportSQLiteDatabase.execSQL("PRAGMA foreign_keys = ON");
                WorkDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (WorkDatabase_Impl.this.mCallbacks != null) {
                    int size = WorkDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((Callback) WorkDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
                    }
                }
            }

            /* access modifiers changed from: protected */
            public void validateMigration(SupportSQLiteDatabase supportSQLiteDatabase) {
                SupportSQLiteDatabase supportSQLiteDatabase2 = supportSQLiteDatabase;
                HashMap hashMap = new HashMap(2);
                hashMap.put("work_spec_id", new Column("work_spec_id", "TEXT", true, 1));
                hashMap.put("prerequisite_id", new Column("prerequisite_id", "TEXT", true, 2));
                HashSet hashSet = new HashSet(2);
                ForeignKey foreignKey = new ForeignKey("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"id"}));
                hashSet.add(foreignKey);
                ForeignKey foreignKey2 = new ForeignKey("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"prerequisite_id"}), Arrays.asList(new String[]{"id"}));
                hashSet.add(foreignKey2);
                HashSet hashSet2 = new HashSet(2);
                hashSet2.add(new Index("index_Dependency_work_spec_id", false, Arrays.asList(new String[]{"work_spec_id"})));
                hashSet2.add(new Index("index_Dependency_prerequisite_id", false, Arrays.asList(new String[]{"prerequisite_id"})));
                TableInfo tableInfo = new TableInfo("Dependency", hashMap, hashSet, hashSet2);
                TableInfo read = TableInfo.read(supportSQLiteDatabase2, "Dependency");
                if (!tableInfo.equals(read)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Migration didn't properly handle Dependency(androidx.work.impl.model.Dependency).\n Expected:\n");
                    sb.append(tableInfo);
                    sb.append("\n");
                    sb.append(" Found:\n");
                    sb.append(read);
                    throw new IllegalStateException(sb.toString());
                }
                HashMap hashMap2 = new HashMap(21);
                hashMap2.put("id", new Column("id", "TEXT", true, 1));
                hashMap2.put("state", new Column("state", "INTEGER", true, 0));
                hashMap2.put("worker_class_name", new Column("worker_class_name", "TEXT", true, 0));
                hashMap2.put("input_merger_class_name", new Column("input_merger_class_name", "TEXT", false, 0));
                hashMap2.put("input", new Column("input", "BLOB", true, 0));
                hashMap2.put("output", new Column("output", "BLOB", true, 0));
                hashMap2.put("initial_delay", new Column("initial_delay", "INTEGER", true, 0));
                hashMap2.put("interval_duration", new Column("interval_duration", "INTEGER", true, 0));
                hashMap2.put("flex_duration", new Column("flex_duration", "INTEGER", true, 0));
                hashMap2.put("run_attempt_count", new Column("run_attempt_count", "INTEGER", true, 0));
                hashMap2.put("backoff_policy", new Column("backoff_policy", "INTEGER", true, 0));
                hashMap2.put("backoff_delay_duration", new Column("backoff_delay_duration", "INTEGER", true, 0));
                hashMap2.put("period_start_time", new Column("period_start_time", "INTEGER", true, 0));
                hashMap2.put("minimum_retention_duration", new Column("minimum_retention_duration", "INTEGER", true, 0));
                hashMap2.put("schedule_requested_at", new Column("schedule_requested_at", "INTEGER", true, 0));
                hashMap2.put("required_network_type", new Column("required_network_type", "INTEGER", false, 0));
                hashMap2.put("requires_charging", new Column("requires_charging", "INTEGER", true, 0));
                hashMap2.put("requires_device_idle", new Column("requires_device_idle", "INTEGER", true, 0));
                hashMap2.put("requires_battery_not_low", new Column("requires_battery_not_low", "INTEGER", true, 0));
                hashMap2.put("requires_storage_not_low", new Column("requires_storage_not_low", "INTEGER", true, 0));
                hashMap2.put("content_uri_triggers", new Column("content_uri_triggers", "BLOB", false, 0));
                HashSet hashSet3 = new HashSet(0);
                HashSet hashSet4 = new HashSet(1);
                hashSet4.add(new Index("index_WorkSpec_schedule_requested_at", false, Arrays.asList(new String[]{"schedule_requested_at"})));
                TableInfo tableInfo2 = new TableInfo("WorkSpec", hashMap2, hashSet3, hashSet4);
                TableInfo read2 = TableInfo.read(supportSQLiteDatabase2, "WorkSpec");
                if (!tableInfo2.equals(read2)) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Migration didn't properly handle WorkSpec(androidx.work.impl.model.WorkSpec).\n Expected:\n");
                    sb2.append(tableInfo2);
                    sb2.append("\n");
                    sb2.append(" Found:\n");
                    sb2.append(read2);
                    throw new IllegalStateException(sb2.toString());
                }
                HashMap hashMap3 = new HashMap(2);
                hashMap3.put("tag", new Column("tag", "TEXT", true, 1));
                hashMap3.put("work_spec_id", new Column("work_spec_id", "TEXT", true, 2));
                HashSet hashSet5 = new HashSet(1);
                ForeignKey foreignKey3 = new ForeignKey("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"id"}));
                hashSet5.add(foreignKey3);
                HashSet hashSet6 = new HashSet(1);
                hashSet6.add(new Index("index_WorkTag_work_spec_id", false, Arrays.asList(new String[]{"work_spec_id"})));
                TableInfo tableInfo3 = new TableInfo("WorkTag", hashMap3, hashSet5, hashSet6);
                TableInfo read3 = TableInfo.read(supportSQLiteDatabase2, "WorkTag");
                if (!tableInfo3.equals(read3)) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Migration didn't properly handle WorkTag(androidx.work.impl.model.WorkTag).\n Expected:\n");
                    sb3.append(tableInfo3);
                    sb3.append("\n");
                    sb3.append(" Found:\n");
                    sb3.append(read3);
                    throw new IllegalStateException(sb3.toString());
                }
                HashMap hashMap4 = new HashMap(2);
                hashMap4.put("work_spec_id", new Column("work_spec_id", "TEXT", true, 1));
                hashMap4.put("alarm_id", new Column("alarm_id", "INTEGER", true, 0));
                HashSet hashSet7 = new HashSet(1);
                ForeignKey foreignKey4 = new ForeignKey("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"id"}));
                hashSet7.add(foreignKey4);
                TableInfo tableInfo4 = new TableInfo("alarmInfo", hashMap4, hashSet7, new HashSet(0));
                TableInfo read4 = TableInfo.read(supportSQLiteDatabase2, "alarmInfo");
                if (!tableInfo4.equals(read4)) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("Migration didn't properly handle alarmInfo(androidx.work.impl.model.AlarmInfo).\n Expected:\n");
                    sb4.append(tableInfo4);
                    sb4.append("\n");
                    sb4.append(" Found:\n");
                    sb4.append(read4);
                    throw new IllegalStateException(sb4.toString());
                }
                HashMap hashMap5 = new HashMap(2);
                hashMap5.put("name", new Column("name", "TEXT", true, 1));
                hashMap5.put("work_spec_id", new Column("work_spec_id", "TEXT", true, 2));
                HashSet hashSet8 = new HashSet(1);
                ForeignKey foreignKey5 = new ForeignKey("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"id"}));
                hashSet8.add(foreignKey5);
                HashSet hashSet9 = new HashSet(1);
                hashSet9.add(new Index("index_WorkName_work_spec_id", false, Arrays.asList(new String[]{"work_spec_id"})));
                TableInfo tableInfo5 = new TableInfo("WorkName", hashMap5, hashSet8, hashSet9);
                TableInfo read5 = TableInfo.read(supportSQLiteDatabase2, "WorkName");
                if (!tableInfo5.equals(read5)) {
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("Migration didn't properly handle WorkName(androidx.work.impl.model.WorkName).\n Expected:\n");
                    sb5.append(tableInfo5);
                    sb5.append("\n");
                    sb5.append(" Found:\n");
                    sb5.append(read5);
                    throw new IllegalStateException(sb5.toString());
                }
            }
        }, "f890a1c3a43db87aba55eaaf535b9e03")).build());
    }

    /* access modifiers changed from: protected */
    public InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, "Dependency", "WorkSpec", "WorkTag", "alarmInfo", "WorkName");
    }

    public WorkSpecDao workSpecDao() {
        WorkSpecDao workSpecDao;
        if (this._workSpecDao != null) {
            return this._workSpecDao;
        }
        synchronized (this) {
            if (this._workSpecDao == null) {
                this._workSpecDao = new WorkSpecDao_Impl(this);
            }
            workSpecDao = this._workSpecDao;
        }
        return workSpecDao;
    }

    public DependencyDao dependencyDao() {
        DependencyDao dependencyDao;
        if (this._dependencyDao != null) {
            return this._dependencyDao;
        }
        synchronized (this) {
            if (this._dependencyDao == null) {
                this._dependencyDao = new DependencyDao_Impl(this);
            }
            dependencyDao = this._dependencyDao;
        }
        return dependencyDao;
    }

    public WorkTagDao workTagDao() {
        WorkTagDao workTagDao;
        if (this._workTagDao != null) {
            return this._workTagDao;
        }
        synchronized (this) {
            if (this._workTagDao == null) {
                this._workTagDao = new WorkTagDao_Impl(this);
            }
            workTagDao = this._workTagDao;
        }
        return workTagDao;
    }

    public AlarmInfoDao alarmInfoDao() {
        AlarmInfoDao alarmInfoDao;
        if (this._alarmInfoDao != null) {
            return this._alarmInfoDao;
        }
        synchronized (this) {
            if (this._alarmInfoDao == null) {
                this._alarmInfoDao = new AlarmInfoDao_Impl(this);
            }
            alarmInfoDao = this._alarmInfoDao;
        }
        return alarmInfoDao;
    }

    public WorkNameDao workNameDao() {
        WorkNameDao workNameDao;
        if (this._workNameDao != null) {
            return this._workNameDao;
        }
        synchronized (this) {
            if (this._workNameDao == null) {
                this._workNameDao = new WorkNameDao_Impl(this);
            }
            workNameDao = this._workNameDao;
        }
        return workNameDao;
    }
}
