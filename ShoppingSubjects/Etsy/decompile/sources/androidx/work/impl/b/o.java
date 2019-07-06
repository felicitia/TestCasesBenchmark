package androidx.work.impl.b;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

/* compiled from: WorkTagDao_Impl */
public class o implements n {
    private final RoomDatabase a;
    private final EntityInsertionAdapter b;

    public o(RoomDatabase roomDatabase) {
        this.a = roomDatabase;
        this.b = new EntityInsertionAdapter<m>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR IGNORE INTO `WorkTag`(`tag`,`work_spec_id`) VALUES (?,?)";
            }

            /* renamed from: a */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, m mVar) {
                if (mVar.a == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, mVar.a);
                }
                if (mVar.b == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, mVar.b);
                }
            }
        };
    }

    public void a(m mVar) {
        this.a.beginTransaction();
        try {
            this.b.insert(mVar);
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
        }
    }

    public List<String> a(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT DISTINCT tag FROM worktag WHERE work_spec_id=?", 1);
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
}
