package android.arch.persistence.db.framework;

import android.database.sqlite.SQLiteProgram;

/* compiled from: FrameworkSQLiteProgram */
class c implements android.arch.persistence.db.c {
    private final SQLiteProgram a;

    c(SQLiteProgram sQLiteProgram) {
        this.a = sQLiteProgram;
    }

    public void bindNull(int i) {
        this.a.bindNull(i);
    }

    public void bindLong(int i, long j) {
        this.a.bindLong(i, j);
    }

    public void bindDouble(int i, double d) {
        this.a.bindDouble(i, d);
    }

    public void bindString(int i, String str) {
        this.a.bindString(i, str);
    }

    public void bindBlob(int i, byte[] bArr) {
        this.a.bindBlob(i, bArr);
    }

    public void close() {
        this.a.close();
    }
}
