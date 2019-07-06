package com.etsy.android.ui.search.v2.impressions;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.e;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

/* compiled from: SearchImpressionDao_Impl */
public class b implements a {
    private final RoomDatabase a;
    private final EntityInsertionAdapter b;
    private final EntityDeletionOrUpdateAdapter c;
    private final e d;

    public b(RoomDatabase roomDatabase) {
        this.a = roomDatabase;
        this.b = new EntityInsertionAdapter<c>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `searchImpressions`(`displayLocation`,`loggingKey`,`data`) VALUES (?,?,?)";
            }

            /* renamed from: a */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, c cVar) {
                if (cVar.a() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, cVar.a());
                }
                if (cVar.b() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, cVar.b());
                }
                if (cVar.c() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, cVar.c());
                }
            }
        };
        this.c = new EntityDeletionOrUpdateAdapter<c>(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM `searchImpressions` WHERE `displayLocation` = ? AND `loggingKey` = ? AND `data` = ?";
            }

            /* renamed from: a */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, c cVar) {
                if (cVar.a() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, cVar.a());
                }
                if (cVar.b() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, cVar.b());
                }
                if (cVar.c() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, cVar.c());
                }
            }
        };
        this.d = new e(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM searchImpressions";
            }
        };
    }

    public long a(c cVar) {
        this.a.beginTransaction();
        try {
            long insertAndReturnId = this.b.insertAndReturnId(cVar);
            this.a.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.a.endTransaction();
        }
    }

    public int a(List<c> list) {
        this.a.beginTransaction();
        try {
            int handleMultiple = 0 + this.c.handleMultiple((Iterable<T>) list);
            this.a.setTransactionSuccessful();
            return handleMultiple;
        } finally {
            this.a.endTransaction();
        }
    }

    public List<c> a(int i) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM searchImpressions LIMIT ?", 1);
        acquire.bindLong(1, (long) i);
        Cursor query = this.a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("displayLocation");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("loggingKey");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("data");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(new c(query.getString(columnIndexOrThrow), query.getString(columnIndexOrThrow2), query.getString(columnIndexOrThrow3)));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }
}
