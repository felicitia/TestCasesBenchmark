package android.arch.persistence.db;

import java.io.Closeable;

/* compiled from: SupportSQLiteProgram */
public interface c extends Closeable {
    void bindBlob(int i, byte[] bArr);

    void bindDouble(int i, double d);

    void bindLong(int i, long j);

    void bindNull(int i);

    void bindString(int i, String str);
}
