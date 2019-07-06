package com.etsy.android.ui.convos.convoredesign;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.e;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ConvoDao_Impl */
public class g implements f {
    private final RoomDatabase a;
    private final EntityInsertionAdapter b;
    private final e c;
    private final e d;
    private final e e;

    public g(RoomDatabase roomDatabase) {
        this.a = roomDatabase;
        this.b = new EntityInsertionAdapter<h>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `convos`(`conversationId`,`userId`,`messageCount`,`isRead`,`hasAttachment`,`title`,`lastMessage`,`lastUpdated`,`otherUserId`,`otherUserNameUser`,`otherUserNameFull`,`otherUserAvatarUrl`,`otherUserIsGuest`,`isCustomShop`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            /* renamed from: a */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, h hVar) {
                supportSQLiteStatement.bindLong(1, hVar.b());
                supportSQLiteStatement.bindLong(2, hVar.c());
                supportSQLiteStatement.bindLong(3, (long) hVar.d());
                supportSQLiteStatement.bindLong(4, hVar.e() ? 1 : 0);
                supportSQLiteStatement.bindLong(5, hVar.f() ? 1 : 0);
                if (hVar.g() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, hVar.g());
                }
                if (hVar.h() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, hVar.h());
                }
                supportSQLiteStatement.bindLong(8, hVar.i());
                supportSQLiteStatement.bindLong(9, hVar.j());
                if (hVar.k() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, hVar.k());
                }
                if (hVar.l() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, hVar.l());
                }
                if (hVar.m() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, hVar.m());
                }
                supportSQLiteStatement.bindLong(13, hVar.n() ? 1 : 0);
                supportSQLiteStatement.bindLong(14, hVar.o() ? 1 : 0);
            }
        };
        this.c = new e(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM convos";
            }
        };
        this.d = new e(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM convos WHERE conversationId = ?";
            }
        };
        this.e = new e(roomDatabase) {
            public String createQuery() {
                return "UPDATE convos set isRead = ? WHERE conversationId = ?";
            }
        };
    }

    public void a(List<h> list) {
        this.a.beginTransaction();
        try {
            this.b.insert((Iterable<T>) list);
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
        }
    }

    public int a(long j, boolean z) {
        SupportSQLiteStatement acquire = this.e.acquire();
        this.a.beginTransaction();
        try {
            acquire.bindLong(1, z ? 1 : 0);
            acquire.bindLong(2, j);
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.a.setTransactionSuccessful();
            return executeUpdateDelete;
        } finally {
            this.a.endTransaction();
            this.e.release(acquire);
        }
    }

    public List<h> a(long j) {
        RoomSQLiteQuery roomSQLiteQuery;
        Throwable th;
        int i;
        boolean z;
        int i2;
        int i3;
        boolean z2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM convos WHERE userId = ?", 1);
        acquire.bindLong(1, j);
        Cursor query = this.a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("conversationId");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("userId");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("messageCount");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("isRead");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hasAttachment");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("title");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("lastMessage");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("lastUpdated");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("otherUserId");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("otherUserNameUser");
            int columnIndexOrThrow11 = query.getColumnIndexOrThrow("otherUserNameFull");
            int columnIndexOrThrow12 = query.getColumnIndexOrThrow("otherUserAvatarUrl");
            int columnIndexOrThrow13 = query.getColumnIndexOrThrow("otherUserIsGuest");
            RoomSQLiteQuery roomSQLiteQuery2 = acquire;
            try {
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow("isCustomShop");
                int i4 = columnIndexOrThrow13;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    try {
                        long j2 = query.getLong(columnIndexOrThrow);
                        long j3 = query.getLong(columnIndexOrThrow2);
                        int i5 = query.getInt(columnIndexOrThrow3);
                        boolean z3 = query.getInt(columnIndexOrThrow4) != 0;
                        boolean z4 = query.getInt(columnIndexOrThrow5) != 0;
                        String string = query.getString(columnIndexOrThrow6);
                        String string2 = query.getString(columnIndexOrThrow7);
                        long j4 = query.getLong(columnIndexOrThrow8);
                        long j5 = query.getLong(columnIndexOrThrow9);
                        String string3 = query.getString(columnIndexOrThrow10);
                        String string4 = query.getString(columnIndexOrThrow11);
                        String string5 = query.getString(columnIndexOrThrow12);
                        int i6 = i4;
                        if (query.getInt(i6) != 0) {
                            i = columnIndexOrThrow12;
                            i2 = columnIndexOrThrow14;
                            z = true;
                        } else {
                            i = columnIndexOrThrow12;
                            i2 = columnIndexOrThrow14;
                            z = false;
                        }
                        if (query.getInt(i2) != 0) {
                            i3 = i2;
                            z2 = true;
                        } else {
                            i3 = i2;
                            z2 = false;
                        }
                        h hVar = new h(j2, j3, i5, z3, z4, string, string2, j4, j5, string3, string4, string5, z, z2);
                        arrayList.add(hVar);
                        i4 = i6;
                        columnIndexOrThrow12 = i;
                        columnIndexOrThrow14 = i3;
                    } catch (Throwable th2) {
                        th = th2;
                        roomSQLiteQuery = roomSQLiteQuery2;
                        query.close();
                        roomSQLiteQuery.release();
                        throw th;
                    }
                }
                query.close();
                roomSQLiteQuery2.release();
                return arrayList;
            } catch (Throwable th3) {
                th = th3;
                roomSQLiteQuery = roomSQLiteQuery2;
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            roomSQLiteQuery = acquire;
            th = th;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }
}
