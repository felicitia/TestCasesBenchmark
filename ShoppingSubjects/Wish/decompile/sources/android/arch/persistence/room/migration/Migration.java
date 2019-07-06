package android.arch.persistence.room.migration;

import android.arch.persistence.db.SupportSQLiteDatabase;

public abstract class Migration {
    public abstract void migrate(SupportSQLiteDatabase supportSQLiteDatabase);
}
