package androidx.work.impl.b;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;

/* compiled from: WorkNameDao_Impl */
public class i implements h {
    private final RoomDatabase a;
    private final EntityInsertionAdapter b;

    public i(RoomDatabase roomDatabase) {
        this.a = roomDatabase;
        this.b = new EntityInsertionAdapter<g>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR IGNORE INTO `WorkName`(`name`,`work_spec_id`) VALUES (?,?)";
            }

            /* renamed from: a */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, g gVar) {
                if (gVar.a == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, gVar.a);
                }
                if (gVar.b == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, gVar.b);
                }
            }
        };
    }

    public void a(g gVar) {
        this.a.beginTransaction();
        try {
            this.b.insert(gVar);
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
        }
    }
}
