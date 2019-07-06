package androidx.work.impl.b;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.e;
import android.database.Cursor;

/* compiled from: SystemIdInfoDao_Impl */
public class f implements e {
    private final RoomDatabase a;
    private final EntityInsertionAdapter b;
    private final e c;

    public f(RoomDatabase roomDatabase) {
        this.a = roomDatabase;
        this.b = new EntityInsertionAdapter<d>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `SystemIdInfo`(`work_spec_id`,`system_id`) VALUES (?,?)";
            }

            /* renamed from: a */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, d dVar) {
                if (dVar.a == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, dVar.a);
                }
                supportSQLiteStatement.bindLong(2, (long) dVar.b);
            }
        };
        this.c = new e(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM SystemIdInfo where work_spec_id=?";
            }
        };
    }

    public void a(d dVar) {
        this.a.beginTransaction();
        try {
            this.b.insert(dVar);
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
        }
    }

    public void b(String str) {
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

    public d a(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM SystemIdInfo WHERE work_spec_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.a.query(acquire);
        try {
            return query.moveToFirst() ? new d(query.getString(query.getColumnIndexOrThrow("work_spec_id")), query.getInt(query.getColumnIndexOrThrow("system_id"))) : null;
        } finally {
            query.close();
            acquire.release();
        }
    }
}
