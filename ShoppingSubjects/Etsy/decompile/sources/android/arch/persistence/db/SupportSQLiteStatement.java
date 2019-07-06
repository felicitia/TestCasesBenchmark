package android.arch.persistence.db;

public interface SupportSQLiteStatement extends c {
    void execute();

    long executeInsert();

    int executeUpdateDelete();

    long simpleQueryForLong();

    String simpleQueryForString();
}
