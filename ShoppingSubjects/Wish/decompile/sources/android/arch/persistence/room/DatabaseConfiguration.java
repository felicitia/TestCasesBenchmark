package android.arch.persistence.room;

import android.arch.persistence.db.SupportSQLiteOpenHelper.Factory;
import android.arch.persistence.room.RoomDatabase.Callback;
import android.arch.persistence.room.RoomDatabase.MigrationContainer;
import android.content.Context;
import java.util.List;

public class DatabaseConfiguration {
    public final boolean allowMainThreadQueries;
    public final List<Callback> callbacks;
    public final Context context;
    public final MigrationContainer migrationContainer;
    public final String name;
    public final boolean requireMigration;
    public final Factory sqliteOpenHelperFactory;

    public DatabaseConfiguration(Context context2, String str, Factory factory, MigrationContainer migrationContainer2, List<Callback> list, boolean z, boolean z2) {
        this.sqliteOpenHelperFactory = factory;
        this.context = context2;
        this.name = str;
        this.migrationContainer = migrationContainer2;
        this.callbacks = list;
        this.allowMainThreadQueries = z;
        this.requireMigration = z2;
    }
}
