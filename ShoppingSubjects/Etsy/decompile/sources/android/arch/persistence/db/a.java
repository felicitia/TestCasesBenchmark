package android.arch.persistence.db;

import android.database.Cursor;
import android.database.SQLException;
import android.util.Pair;
import java.io.Closeable;
import java.util.List;

/* compiled from: SupportSQLiteDatabase */
public interface a extends Closeable {
    SupportSQLiteStatement a(String str);

    Cursor a(d dVar);

    void a();

    Cursor b(String str);

    void b();

    void c();

    void c(String str) throws SQLException;

    boolean d();

    boolean e();

    String f();

    List<Pair<String, String>> g();
}
