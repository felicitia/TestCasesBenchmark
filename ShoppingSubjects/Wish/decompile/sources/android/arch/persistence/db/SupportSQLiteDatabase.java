package android.arch.persistence.db;

import android.database.Cursor;
import android.database.SQLException;
import android.util.Pair;
import java.io.Closeable;
import java.util.List;

public interface SupportSQLiteDatabase extends Closeable {
    void beginTransaction();

    SupportSQLiteStatement compileStatement(String str);

    void endTransaction();

    void execSQL(String str) throws SQLException;

    List<Pair<String, String>> getAttachedDbs();

    String getPath();

    boolean inTransaction();

    boolean isOpen();

    Cursor query(SupportSQLiteQuery supportSQLiteQuery);

    Cursor query(String str);

    void setTransactionSuccessful();
}
