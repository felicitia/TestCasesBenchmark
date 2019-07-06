package android.arch.persistence.db.framework;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.RequiresApi;

/* compiled from: FrameworkSQLiteOpenHelper */
class b implements android.arch.persistence.db.b {
    private final a a;

    /* compiled from: FrameworkSQLiteOpenHelper */
    static class a extends SQLiteOpenHelper {
        final a[] a;
        final android.arch.persistence.db.b.a b;
        private boolean c;

        a(Context context, String str, final a[] aVarArr, final android.arch.persistence.db.b.a aVar) {
            super(context, str, null, aVar.version, new DatabaseErrorHandler() {
                public void onCorruption(SQLiteDatabase sQLiteDatabase) {
                    a aVar = aVarArr[0];
                    if (aVar != null) {
                        aVar.onCorruption(aVar);
                    }
                }
            });
            this.b = aVar;
            this.a = aVarArr;
        }

        /* access modifiers changed from: 0000 */
        public synchronized android.arch.persistence.db.a a() {
            this.c = false;
            SQLiteDatabase writableDatabase = super.getWritableDatabase();
            if (this.c) {
                close();
                return a();
            }
            return a(writableDatabase);
        }

        /* access modifiers changed from: 0000 */
        public a a(SQLiteDatabase sQLiteDatabase) {
            if (this.a[0] == null) {
                this.a[0] = new a(sQLiteDatabase);
            }
            return this.a[0];
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            this.b.onCreate(a(sQLiteDatabase));
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.c = true;
            this.b.onUpgrade(a(sQLiteDatabase), i, i2);
        }

        public void onConfigure(SQLiteDatabase sQLiteDatabase) {
            this.b.onConfigure(a(sQLiteDatabase));
        }

        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.c = true;
            this.b.onDowngrade(a(sQLiteDatabase), i, i2);
        }

        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            if (!this.c) {
                this.b.onOpen(a(sQLiteDatabase));
            }
        }

        public synchronized void close() {
            super.close();
            this.a[0] = null;
        }
    }

    b(Context context, String str, android.arch.persistence.db.b.a aVar) {
        this.a = a(context, str, aVar);
    }

    private a a(Context context, String str, android.arch.persistence.db.b.a aVar) {
        return new a(context, str, new a[1], aVar);
    }

    @RequiresApi(api = 16)
    public void a(boolean z) {
        this.a.setWriteAheadLoggingEnabled(z);
    }

    public android.arch.persistence.db.a a() {
        return this.a.a();
    }

    public void b() {
        this.a.close();
    }
}
