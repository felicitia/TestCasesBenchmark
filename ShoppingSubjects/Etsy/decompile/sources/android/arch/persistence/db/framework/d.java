package android.arch.persistence.db.framework;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.database.sqlite.SQLiteStatement;

/* compiled from: FrameworkSQLiteStatement */
class d extends c implements SupportSQLiteStatement {
    private final SQLiteStatement a;

    d(SQLiteStatement sQLiteStatement) {
        super(sQLiteStatement);
        this.a = sQLiteStatement;
    }

    public void execute() {
        this.a.execute();
    }

    public int executeUpdateDelete() {
        return this.a.executeUpdateDelete();
    }

    public long executeInsert() {
        return this.a.executeInsert();
    }

    public long simpleQueryForLong() {
        return this.a.simpleQueryForLong();
    }

    public String simpleQueryForString() {
        return this.a.simpleQueryForString();
    }
}
