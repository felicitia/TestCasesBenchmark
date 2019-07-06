package com.etsy.android.ui.convos.convoredesign;

import android.arch.persistence.db.b;
import android.arch.persistence.db.b.C0002b;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.a;
import com.etsy.android.lib.models.ResponseConstants;
import java.util.HashMap;
import java.util.HashSet;

public class ConvoDatabase_Impl extends ConvoDatabase {
    private volatile f _convoDao;
    private volatile l _convoDraftDao;

    /* access modifiers changed from: protected */
    public b createOpenHelper(a aVar) {
        return aVar.a.create(C0002b.a(aVar.b).a(aVar.c).a((b.a) new RoomOpenHelper(aVar, new RoomOpenHelper.a(4) {
            public void b(android.arch.persistence.db.a aVar) {
                aVar.c("CREATE TABLE IF NOT EXISTS `convos` (`conversationId` INTEGER NOT NULL, `userId` INTEGER NOT NULL, `messageCount` INTEGER NOT NULL, `isRead` INTEGER NOT NULL, `hasAttachment` INTEGER NOT NULL, `title` TEXT NOT NULL, `lastMessage` TEXT NOT NULL, `lastUpdated` INTEGER NOT NULL, `otherUserId` INTEGER NOT NULL, `otherUserNameUser` TEXT NOT NULL, `otherUserNameFull` TEXT NOT NULL, `otherUserAvatarUrl` TEXT NOT NULL, `otherUserIsGuest` INTEGER NOT NULL, `isCustomShop` INTEGER NOT NULL, PRIMARY KEY(`conversationId`))");
                aVar.c("CREATE TABLE IF NOT EXISTS `convo_drafts` (`id` INTEGER NOT NULL, `message` TEXT NOT NULL, `subject` TEXT NOT NULL, `userName` TEXT NOT NULL, `selectionStart` INTEGER NOT NULL, `selectionEnd` INTEGER NOT NULL, `imageFilePaths` TEXT NOT NULL, `status` INTEGER NOT NULL, PRIMARY KEY(`id`))");
                aVar.c("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
                aVar.c("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"38d09d5a218db240a54e3884b492e6ca\")");
            }

            public void a(android.arch.persistence.db.a aVar) {
                aVar.c("DROP TABLE IF EXISTS `convos`");
                aVar.c("DROP TABLE IF EXISTS `convo_drafts`");
            }

            /* access modifiers changed from: protected */
            public void d(android.arch.persistence.db.a aVar) {
                if (ConvoDatabase_Impl.this.mCallbacks != null) {
                    int size = ConvoDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.b) ConvoDatabase_Impl.this.mCallbacks.get(i)).a(aVar);
                    }
                }
            }

            public void c(android.arch.persistence.db.a aVar) {
                ConvoDatabase_Impl.this.mDatabase = aVar;
                ConvoDatabase_Impl.this.internalInitInvalidationTracker(aVar);
                if (ConvoDatabase_Impl.this.mCallbacks != null) {
                    int size = ConvoDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.b) ConvoDatabase_Impl.this.mCallbacks.get(i)).b(aVar);
                    }
                }
            }

            /* access modifiers changed from: protected */
            public void e(android.arch.persistence.db.a aVar) {
                HashMap hashMap = new HashMap(14);
                hashMap.put("conversationId", new android.arch.persistence.room.b.b.a("conversationId", "INTEGER", true, 1));
                hashMap.put("userId", new android.arch.persistence.room.b.b.a("userId", "INTEGER", true, 0));
                hashMap.put("messageCount", new android.arch.persistence.room.b.b.a("messageCount", "INTEGER", true, 0));
                hashMap.put("isRead", new android.arch.persistence.room.b.b.a("isRead", "INTEGER", true, 0));
                hashMap.put("hasAttachment", new android.arch.persistence.room.b.b.a("hasAttachment", "INTEGER", true, 0));
                hashMap.put("title", new android.arch.persistence.room.b.b.a("title", "TEXT", true, 0));
                hashMap.put("lastMessage", new android.arch.persistence.room.b.b.a("lastMessage", "TEXT", true, 0));
                hashMap.put("lastUpdated", new android.arch.persistence.room.b.b.a("lastUpdated", "INTEGER", true, 0));
                hashMap.put("otherUserId", new android.arch.persistence.room.b.b.a("otherUserId", "INTEGER", true, 0));
                hashMap.put("otherUserNameUser", new android.arch.persistence.room.b.b.a("otherUserNameUser", "TEXT", true, 0));
                hashMap.put("otherUserNameFull", new android.arch.persistence.room.b.b.a("otherUserNameFull", "TEXT", true, 0));
                hashMap.put("otherUserAvatarUrl", new android.arch.persistence.room.b.b.a("otherUserAvatarUrl", "TEXT", true, 0));
                hashMap.put("otherUserIsGuest", new android.arch.persistence.room.b.b.a("otherUserIsGuest", "INTEGER", true, 0));
                hashMap.put("isCustomShop", new android.arch.persistence.room.b.b.a("isCustomShop", "INTEGER", true, 0));
                android.arch.persistence.room.b.b bVar = new android.arch.persistence.room.b.b("convos", hashMap, new HashSet(0), new HashSet(0));
                android.arch.persistence.room.b.b a = android.arch.persistence.room.b.b.a(aVar, "convos");
                if (!bVar.equals(a)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Migration didn't properly handle convos(com.etsy.android.ui.convos.convoredesign.ConvoDbModel).\n Expected:\n");
                    sb.append(bVar);
                    sb.append("\n Found:\n");
                    sb.append(a);
                    throw new IllegalStateException(sb.toString());
                }
                HashMap hashMap2 = new HashMap(8);
                hashMap2.put("id", new android.arch.persistence.room.b.b.a("id", "INTEGER", true, 1));
                hashMap2.put("message", new android.arch.persistence.room.b.b.a("message", "TEXT", true, 0));
                hashMap2.put(ResponseConstants.SUBJECT, new android.arch.persistence.room.b.b.a(ResponseConstants.SUBJECT, "TEXT", true, 0));
                hashMap2.put("userName", new android.arch.persistence.room.b.b.a("userName", "TEXT", true, 0));
                hashMap2.put("selectionStart", new android.arch.persistence.room.b.b.a("selectionStart", "INTEGER", true, 0));
                hashMap2.put("selectionEnd", new android.arch.persistence.room.b.b.a("selectionEnd", "INTEGER", true, 0));
                hashMap2.put("imageFilePaths", new android.arch.persistence.room.b.b.a("imageFilePaths", "TEXT", true, 0));
                hashMap2.put("status", new android.arch.persistence.room.b.b.a("status", "INTEGER", true, 0));
                android.arch.persistence.room.b.b bVar2 = new android.arch.persistence.room.b.b("convo_drafts", hashMap2, new HashSet(0), new HashSet(0));
                android.arch.persistence.room.b.b a2 = android.arch.persistence.room.b.b.a(aVar, "convo_drafts");
                if (!bVar2.equals(a2)) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Migration didn't properly handle convo_drafts(com.etsy.android.ui.convos.convoredesign.ConvoDraftDbModel).\n Expected:\n");
                    sb2.append(bVar2);
                    sb2.append("\n Found:\n");
                    sb2.append(a2);
                    throw new IllegalStateException(sb2.toString());
                }
            }
        }, "38d09d5a218db240a54e3884b492e6ca", "12b9a28c5225472e077461665390ad2c")).a());
    }

    /* access modifiers changed from: protected */
    public android.arch.persistence.room.b createInvalidationTracker() {
        return new android.arch.persistence.room.b(this, "convos", "convo_drafts");
    }

    public void clearAllTables() {
        super.assertNotMainThread();
        android.arch.persistence.db.a a = super.getOpenHelper().a();
        try {
            super.beginTransaction();
            a.c("DELETE FROM `convos`");
            a.c("DELETE FROM `convo_drafts`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            a.b("PRAGMA wal_checkpoint(FULL)").close();
            if (!a.d()) {
                a.c("VACUUM");
            }
        }
    }

    public f convoDao() {
        f fVar;
        if (this._convoDao != null) {
            return this._convoDao;
        }
        synchronized (this) {
            if (this._convoDao == null) {
                this._convoDao = new g(this);
            }
            fVar = this._convoDao;
        }
        return fVar;
    }

    public l convoDraftDao() {
        l lVar;
        if (this._convoDraftDao != null) {
            return this._convoDraftDao;
        }
        synchronized (this) {
            if (this._convoDraftDao == null) {
                this._convoDraftDao = new m(this);
            }
            lVar = this._convoDraftDao;
        }
        return lVar;
    }
}
