package com.etsy.android.ui.convos.convoredesign;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.e;

/* compiled from: ConvoDraftDao_Impl */
public class m implements l {
    private final RoomDatabase a;
    private final EntityInsertionAdapter b;
    private final e c;
    private final e d;

    public m(RoomDatabase roomDatabase) {
        this.a = roomDatabase;
        this.b = new EntityInsertionAdapter<n>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `convo_drafts`(`id`,`message`,`subject`,`userName`,`selectionStart`,`selectionEnd`,`imageFilePaths`,`status`) VALUES (?,?,?,?,?,?,?,?)";
            }

            /* renamed from: a */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, n nVar) {
                supportSQLiteStatement.bindLong(1, nVar.a());
                if (nVar.b() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, nVar.b());
                }
                if (nVar.c() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, nVar.c());
                }
                if (nVar.d() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, nVar.d());
                }
                supportSQLiteStatement.bindLong(5, (long) nVar.e());
                supportSQLiteStatement.bindLong(6, (long) nVar.f());
                if (nVar.g() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, nVar.g());
                }
                supportSQLiteStatement.bindLong(8, (long) nVar.h());
            }
        };
        this.c = new e(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM convo_drafts";
            }
        };
        this.d = new e(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM convo_drafts WHERE id = ?";
            }
        };
    }
}
