package com.etsy.android.ui.search.v2.impressions;

import android.arch.persistence.db.b;
import android.arch.persistence.db.b.C0002b;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.a;
import java.util.HashMap;
import java.util.HashSet;

public class SearchImpressionsDatabase_Impl extends SearchImpressionsDatabase {
    private volatile a _searchImpressionDao;

    /* access modifiers changed from: protected */
    public b createOpenHelper(a aVar) {
        return aVar.a.create(C0002b.a(aVar.b).a(aVar.c).a((b.a) new RoomOpenHelper(aVar, new RoomOpenHelper.a(1) {
            public void b(android.arch.persistence.db.a aVar) {
                aVar.c("CREATE TABLE IF NOT EXISTS `searchImpressions` (`displayLocation` TEXT NOT NULL, `loggingKey` TEXT NOT NULL, `data` TEXT NOT NULL, PRIMARY KEY(`displayLocation`, `loggingKey`, `data`))");
                aVar.c("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
                aVar.c("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"c78824230b168d99da44ed132890d304\")");
            }

            public void a(android.arch.persistence.db.a aVar) {
                aVar.c("DROP TABLE IF EXISTS `searchImpressions`");
            }

            /* access modifiers changed from: protected */
            public void d(android.arch.persistence.db.a aVar) {
                if (SearchImpressionsDatabase_Impl.this.mCallbacks != null) {
                    int size = SearchImpressionsDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.b) SearchImpressionsDatabase_Impl.this.mCallbacks.get(i)).a(aVar);
                    }
                }
            }

            public void c(android.arch.persistence.db.a aVar) {
                SearchImpressionsDatabase_Impl.this.mDatabase = aVar;
                SearchImpressionsDatabase_Impl.this.internalInitInvalidationTracker(aVar);
                if (SearchImpressionsDatabase_Impl.this.mCallbacks != null) {
                    int size = SearchImpressionsDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.b) SearchImpressionsDatabase_Impl.this.mCallbacks.get(i)).b(aVar);
                    }
                }
            }

            /* access modifiers changed from: protected */
            public void e(android.arch.persistence.db.a aVar) {
                HashMap hashMap = new HashMap(3);
                hashMap.put("displayLocation", new android.arch.persistence.room.b.b.a("displayLocation", "TEXT", true, 1));
                hashMap.put("loggingKey", new android.arch.persistence.room.b.b.a("loggingKey", "TEXT", true, 2));
                hashMap.put("data", new android.arch.persistence.room.b.b.a("data", "TEXT", true, 3));
                android.arch.persistence.room.b.b bVar = new android.arch.persistence.room.b.b("searchImpressions", hashMap, new HashSet(0), new HashSet(0));
                android.arch.persistence.room.b.b a = android.arch.persistence.room.b.b.a(aVar, "searchImpressions");
                if (!bVar.equals(a)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Migration didn't properly handle searchImpressions(com.etsy.android.ui.search.v2.impressions.SearchImpressionDbModel).\n Expected:\n");
                    sb.append(bVar);
                    sb.append("\n Found:\n");
                    sb.append(a);
                    throw new IllegalStateException(sb.toString());
                }
            }
        }, "c78824230b168d99da44ed132890d304", "5e0fcb72fb07d68be996df3a00624800")).a());
    }

    /* access modifiers changed from: protected */
    public android.arch.persistence.room.b createInvalidationTracker() {
        return new android.arch.persistence.room.b(this, "searchImpressions");
    }

    public void clearAllTables() {
        super.assertNotMainThread();
        android.arch.persistence.db.a a = super.getOpenHelper().a();
        try {
            super.beginTransaction();
            a.c("DELETE FROM `searchImpressions`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            a.b("PRAGMA wal_checkpoint(FULL)").close();
            if (!a.d()) {
                a.c("VACUUM");
            }
        }
    }

    public a searchImpressionDao() {
        a aVar;
        if (this._searchImpressionDao != null) {
            return this._searchImpressionDao;
        }
        synchronized (this) {
            if (this._searchImpressionDao == null) {
                this._searchImpressionDao = new b(this);
            }
            aVar = this._searchImpressionDao;
        }
        return aVar;
    }
}
