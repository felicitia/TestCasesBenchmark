package android.arch.persistence.db.framework;

import android.arch.persistence.db.SimpleSQLiteQuery;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.db.d;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteQuery;
import android.util.Pair;
import java.io.IOException;
import java.util.List;

/* compiled from: FrameworkSQLiteDatabase */
class a implements android.arch.persistence.db.a {
    private static final String[] a = {"", " OR ROLLBACK ", " OR ABORT ", " OR FAIL ", " OR IGNORE ", " OR REPLACE "};
    private static final String[] b = new String[0];
    private final SQLiteDatabase c;

    a(SQLiteDatabase sQLiteDatabase) {
        this.c = sQLiteDatabase;
    }

    public SupportSQLiteStatement a(String str) {
        return new d(this.c.compileStatement(str));
    }

    public void a() {
        this.c.beginTransaction();
    }

    public void b() {
        this.c.endTransaction();
    }

    public void c() {
        this.c.setTransactionSuccessful();
    }

    public boolean d() {
        return this.c.inTransaction();
    }

    public Cursor b(String str) {
        return a((d) new SimpleSQLiteQuery(str));
    }

    public Cursor a(final d dVar) {
        return this.c.rawQueryWithFactory(new CursorFactory() {
            public Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
                dVar.bindTo(new c(sQLiteQuery));
                return new SQLiteCursor(sQLiteCursorDriver, str, sQLiteQuery);
            }
        }, dVar.getSql(), b, null);
    }

    public void c(String str) throws SQLException {
        this.c.execSQL(str);
    }

    public boolean e() {
        return this.c.isOpen();
    }

    public String f() {
        return this.c.getPath();
    }

    public List<Pair<String, String>> g() {
        return this.c.getAttachedDbs();
    }

    public void close() throws IOException {
        this.c.close();
    }
}
