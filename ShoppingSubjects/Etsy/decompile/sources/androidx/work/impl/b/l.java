package androidx.work.impl.b;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.e;
import android.database.Cursor;
import androidx.work.Data;
import androidx.work.State;
import androidx.work.b;
import androidx.work.impl.b.j.a;
import com.etsy.android.lib.models.ResponseConstants;
import java.util.ArrayList;
import java.util.List;

/* compiled from: WorkSpecDao_Impl */
public class l implements k {
    private final RoomDatabase a;
    private final EntityInsertionAdapter b;
    private final e c;
    private final e d;
    private final e e;
    private final e f;
    private final e g;
    private final e h;
    private final e i;
    private final e j;

    public l(RoomDatabase roomDatabase) {
        this.a = roomDatabase;
        this.b = new EntityInsertionAdapter<j>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR IGNORE INTO `WorkSpec`(`id`,`state`,`worker_class_name`,`input_merger_class_name`,`input`,`output`,`initial_delay`,`interval_duration`,`flex_duration`,`run_attempt_count`,`backoff_policy`,`backoff_delay_duration`,`period_start_time`,`minimum_retention_duration`,`schedule_requested_at`,`required_network_type`,`requires_charging`,`requires_device_idle`,`requires_battery_not_low`,`requires_storage_not_low`,`content_uri_triggers`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            /* renamed from: a */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, j jVar) {
                if (jVar.a == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, jVar.a);
                }
                supportSQLiteStatement.bindLong(2, (long) p.a(jVar.b));
                if (jVar.c == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, jVar.c);
                }
                if (jVar.d == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, jVar.d);
                }
                byte[] a2 = Data.a(jVar.e);
                if (a2 == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindBlob(5, a2);
                }
                byte[] a3 = Data.a(jVar.f);
                if (a3 == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindBlob(6, a3);
                }
                supportSQLiteStatement.bindLong(7, jVar.g);
                supportSQLiteStatement.bindLong(8, jVar.h);
                supportSQLiteStatement.bindLong(9, jVar.i);
                supportSQLiteStatement.bindLong(10, (long) jVar.k);
                supportSQLiteStatement.bindLong(11, (long) p.a(jVar.l));
                supportSQLiteStatement.bindLong(12, jVar.m);
                supportSQLiteStatement.bindLong(13, jVar.n);
                supportSQLiteStatement.bindLong(14, jVar.o);
                supportSQLiteStatement.bindLong(15, jVar.p);
                b bVar = jVar.j;
                if (bVar != null) {
                    supportSQLiteStatement.bindLong(16, (long) p.a(bVar.a()));
                    supportSQLiteStatement.bindLong(17, bVar.b() ? 1 : 0);
                    supportSQLiteStatement.bindLong(18, bVar.c() ? 1 : 0);
                    supportSQLiteStatement.bindLong(19, bVar.d() ? 1 : 0);
                    supportSQLiteStatement.bindLong(20, bVar.e() ? 1 : 0);
                    byte[] a4 = p.a(bVar.f());
                    if (a4 == null) {
                        supportSQLiteStatement.bindNull(21);
                    } else {
                        supportSQLiteStatement.bindBlob(21, a4);
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
        this.c = new e(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM workspec WHERE id=?";
            }
        };
        this.d = new e(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET output=? WHERE id=?";
            }
        };
        this.e = new e(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET period_start_time=? WHERE id=?";
            }
        };
        this.f = new e(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET run_attempt_count=run_attempt_count+1 WHERE id=?";
            }
        };
        this.g = new e(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET run_attempt_count=0 WHERE id=?";
            }
        };
        this.h = new e(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET schedule_requested_at=? WHERE id=?";
            }
        };
        this.i = new e(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET schedule_requested_at=-1 WHERE state NOT IN (2, 3, 5)";
            }
        };
        this.j = new e(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM workspec WHERE state IN (2, 3, 5) AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))";
            }
        };
    }

    public void a(j jVar) {
        this.a.beginTransaction();
        try {
            this.b.insert(jVar);
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
        }
    }

    public void a(String str) {
        SupportSQLiteStatement acquire = this.c.acquire();
        this.a.beginTransaction();
        if (str == null) {
            try {
                acquire.bindNull(1);
            } catch (Throwable th) {
                this.a.endTransaction();
                this.c.release(acquire);
                throw th;
            }
        } else {
            acquire.bindString(1, str);
        }
        acquire.executeUpdateDelete();
        this.a.setTransactionSuccessful();
        this.a.endTransaction();
        this.c.release(acquire);
    }

    public void a(String str, Data data) {
        SupportSQLiteStatement acquire = this.d.acquire();
        this.a.beginTransaction();
        try {
            byte[] a2 = Data.a(data);
            if (a2 == null) {
                acquire.bindNull(1);
            } else {
                acquire.bindBlob(1, a2);
            }
            if (str == null) {
                acquire.bindNull(2);
            } else {
                acquire.bindString(2, str);
            }
            acquire.executeUpdateDelete();
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
            this.d.release(acquire);
        }
    }

    public void a(String str, long j2) {
        SupportSQLiteStatement acquire = this.e.acquire();
        this.a.beginTransaction();
        try {
            acquire.bindLong(1, j2);
            if (str == null) {
                acquire.bindNull(2);
            } else {
                acquire.bindString(2, str);
            }
            acquire.executeUpdateDelete();
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
            this.e.release(acquire);
        }
    }

    public int d(String str) {
        SupportSQLiteStatement acquire = this.f.acquire();
        this.a.beginTransaction();
        if (str == null) {
            try {
                acquire.bindNull(1);
            } catch (Throwable th) {
                this.a.endTransaction();
                this.f.release(acquire);
                throw th;
            }
        } else {
            acquire.bindString(1, str);
        }
        int executeUpdateDelete = acquire.executeUpdateDelete();
        this.a.setTransactionSuccessful();
        this.a.endTransaction();
        this.f.release(acquire);
        return executeUpdateDelete;
    }

    public int e(String str) {
        SupportSQLiteStatement acquire = this.g.acquire();
        this.a.beginTransaction();
        if (str == null) {
            try {
                acquire.bindNull(1);
            } catch (Throwable th) {
                this.a.endTransaction();
                this.g.release(acquire);
                throw th;
            }
        } else {
            acquire.bindString(1, str);
        }
        int executeUpdateDelete = acquire.executeUpdateDelete();
        this.a.setTransactionSuccessful();
        this.a.endTransaction();
        this.g.release(acquire);
        return executeUpdateDelete;
    }

    public int b(String str, long j2) {
        SupportSQLiteStatement acquire = this.h.acquire();
        this.a.beginTransaction();
        try {
            acquire.bindLong(1, j2);
            if (str == null) {
                acquire.bindNull(2);
            } else {
                acquire.bindString(2, str);
            }
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.a.setTransactionSuccessful();
            return executeUpdateDelete;
        } finally {
            this.a.endTransaction();
            this.h.release(acquire);
        }
    }

    public int b() {
        SupportSQLiteStatement acquire = this.i.acquire();
        this.a.beginTransaction();
        try {
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.a.setTransactionSuccessful();
            return executeUpdateDelete;
        } finally {
            this.a.endTransaction();
            this.i.release(acquire);
        }
    }

    public j b(String str) {
        Throwable th;
        j jVar;
        String str2 = str;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE id=?", 1);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        Cursor query = this.a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow(ResponseConstants.STATE);
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
                int i2 = columnIndexOrThrow13;
                int columnIndexOrThrow17 = query.getColumnIndexOrThrow("requires_charging");
                int i3 = columnIndexOrThrow12;
                int columnIndexOrThrow18 = query.getColumnIndexOrThrow("requires_device_idle");
                int i4 = columnIndexOrThrow11;
                int columnIndexOrThrow19 = query.getColumnIndexOrThrow("requires_battery_not_low");
                int i5 = columnIndexOrThrow10;
                int columnIndexOrThrow20 = query.getColumnIndexOrThrow("requires_storage_not_low");
                int i6 = columnIndexOrThrow9;
                int columnIndexOrThrow21 = query.getColumnIndexOrThrow("content_uri_triggers");
                if (query.moveToFirst()) {
                    try {
                        String string = query.getString(columnIndexOrThrow);
                        String string2 = query.getString(columnIndexOrThrow3);
                        int i7 = columnIndexOrThrow8;
                        b bVar = new b();
                        bVar.a(p.c(query.getInt(columnIndexOrThrow16)));
                        boolean z = false;
                        bVar.a(query.getInt(columnIndexOrThrow17) != 0);
                        bVar.b(query.getInt(columnIndexOrThrow18) != 0);
                        bVar.c(query.getInt(columnIndexOrThrow19) != 0);
                        if (query.getInt(columnIndexOrThrow20) != 0) {
                            z = true;
                        }
                        bVar.d(z);
                        bVar.a(p.a(query.getBlob(columnIndexOrThrow21)));
                        jVar = new j(string, string2);
                        jVar.b = p.a(query.getInt(columnIndexOrThrow2));
                        jVar.d = query.getString(columnIndexOrThrow4);
                        jVar.e = Data.a(query.getBlob(columnIndexOrThrow5));
                        jVar.f = Data.a(query.getBlob(columnIndexOrThrow6));
                        jVar.g = query.getLong(columnIndexOrThrow7);
                        jVar.h = query.getLong(i7);
                        jVar.i = query.getLong(i6);
                        jVar.k = query.getInt(i5);
                        jVar.l = p.b(query.getInt(i4));
                        jVar.m = query.getLong(i3);
                        jVar.n = query.getLong(i2);
                        jVar.o = query.getLong(columnIndexOrThrow14);
                        jVar.p = query.getLong(columnIndexOrThrow15);
                        jVar.j = bVar;
                    } catch (Throwable th2) {
                        th = th2;
                        acquire = roomSQLiteQuery;
                        query.close();
                        acquire.release();
                        throw th;
                    }
                } else {
                    jVar = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return jVar;
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

    public List<a> c(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id, state FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow(ResponseConstants.STATE);
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                a aVar = new a();
                aVar.a = query.getString(columnIndexOrThrow);
                aVar.b = p.a(query.getInt(columnIndexOrThrow2));
                arrayList.add(aVar);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public State f(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT state FROM workspec WHERE id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.a.query(acquire);
        try {
            return query.moveToFirst() ? p.a(query.getInt(0)) : null;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public List<Data> g(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT output FROM workspec WHERE id IN (SELECT prerequisite_id FROM dependency WHERE work_spec_id=?)", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.a.query(acquire);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(Data.a(query.getBlob(0)));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public List<String> h(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM worktag WHERE tag=?)", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.a.query(acquire);
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

    public List<String> i(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.a.query(acquire);
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

    public List<String> a() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5)", 0);
        Cursor query = this.a.query(acquire);
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

    public List<j> a(int i2) {
        Throwable th;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from workspec WHERE state=0 AND schedule_requested_at=-1 LIMIT (SELECT ?-COUNT(*) FROM workspec WHERE schedule_requested_at<>-1 AND state NOT IN (2, 3, 5))", 1);
        acquire.bindLong(1, (long) i2);
        Cursor query = this.a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow(ResponseConstants.STATE);
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
                int i3 = columnIndexOrThrow13;
                int columnIndexOrThrow17 = query.getColumnIndexOrThrow("requires_charging");
                int i4 = columnIndexOrThrow12;
                int columnIndexOrThrow18 = query.getColumnIndexOrThrow("requires_device_idle");
                int i5 = columnIndexOrThrow11;
                int columnIndexOrThrow19 = query.getColumnIndexOrThrow("requires_battery_not_low");
                int i6 = columnIndexOrThrow10;
                int columnIndexOrThrow20 = query.getColumnIndexOrThrow("requires_storage_not_low");
                int i7 = columnIndexOrThrow9;
                int columnIndexOrThrow21 = query.getColumnIndexOrThrow("content_uri_triggers");
                int i8 = columnIndexOrThrow8;
                int i9 = columnIndexOrThrow7;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    try {
                        String string = query.getString(columnIndexOrThrow);
                        int i10 = columnIndexOrThrow;
                        String string2 = query.getString(columnIndexOrThrow3);
                        int i11 = columnIndexOrThrow3;
                        b bVar = new b();
                        ArrayList arrayList2 = arrayList;
                        bVar.a(p.c(query.getInt(columnIndexOrThrow16)));
                        bVar.a(query.getInt(columnIndexOrThrow17) != 0);
                        bVar.b(query.getInt(columnIndexOrThrow18) != 0);
                        bVar.c(query.getInt(columnIndexOrThrow19) != 0);
                        bVar.d(query.getInt(columnIndexOrThrow20) != 0);
                        bVar.a(p.a(query.getBlob(columnIndexOrThrow21)));
                        j jVar = new j(string, string2);
                        jVar.b = p.a(query.getInt(columnIndexOrThrow2));
                        jVar.d = query.getString(columnIndexOrThrow4);
                        jVar.e = Data.a(query.getBlob(columnIndexOrThrow5));
                        jVar.f = Data.a(query.getBlob(columnIndexOrThrow6));
                        int i12 = columnIndexOrThrow16;
                        int i13 = columnIndexOrThrow18;
                        int i14 = i9;
                        jVar.g = query.getLong(i14);
                        int i15 = columnIndexOrThrow17;
                        int i16 = i8;
                        jVar.h = query.getLong(i16);
                        int i17 = i14;
                        int i18 = columnIndexOrThrow2;
                        int i19 = i7;
                        jVar.i = query.getLong(i19);
                        int i20 = i6;
                        jVar.k = query.getInt(i20);
                        int i21 = i5;
                        jVar.l = p.b(query.getInt(i21));
                        int i22 = i16;
                        int i23 = i19;
                        int i24 = i4;
                        jVar.m = query.getLong(i24);
                        int i25 = i20;
                        int i26 = i3;
                        jVar.n = query.getLong(i26);
                        int i27 = i21;
                        int i28 = i24;
                        int i29 = columnIndexOrThrow14;
                        jVar.o = query.getLong(i29);
                        int i30 = columnIndexOrThrow15;
                        jVar.p = query.getLong(i30);
                        jVar.j = bVar;
                        ArrayList arrayList3 = arrayList2;
                        arrayList3.add(jVar);
                        i3 = i26;
                        columnIndexOrThrow14 = i29;
                        columnIndexOrThrow15 = i30;
                        arrayList = arrayList3;
                        columnIndexOrThrow = i10;
                        columnIndexOrThrow3 = i11;
                        columnIndexOrThrow16 = i12;
                        columnIndexOrThrow18 = i13;
                        columnIndexOrThrow17 = i15;
                        columnIndexOrThrow2 = i18;
                        i9 = i17;
                        i8 = i22;
                        i7 = i23;
                        i6 = i25;
                        i5 = i27;
                        i4 = i28;
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

    public int a(State state, String... strArr) {
        StringBuilder a2 = android.arch.persistence.room.b.a.a();
        a2.append("UPDATE workspec SET state=");
        a2.append("?");
        a2.append(" WHERE id IN (");
        int i2 = 2;
        android.arch.persistence.room.b.a.a(a2, strArr.length);
        a2.append(")");
        SupportSQLiteStatement compileStatement = this.a.compileStatement(a2.toString());
        compileStatement.bindLong(1, (long) p.a(state));
        for (String str : strArr) {
            if (str == null) {
                compileStatement.bindNull(i2);
            } else {
                compileStatement.bindString(i2, str);
            }
            i2++;
        }
        this.a.beginTransaction();
        try {
            int executeUpdateDelete = compileStatement.executeUpdateDelete();
            this.a.setTransactionSuccessful();
            return executeUpdateDelete;
        } finally {
            this.a.endTransaction();
        }
    }
}
