package android.arch.persistence.room;

import android.arch.persistence.db.SimpleSQLiteQuery;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteQuery;
import android.database.Cursor;

public class RoomOpenHelper extends Callback {
    private DatabaseConfiguration mConfiguration;
    private final Delegate mDelegate;
    private final String mIdentityHash;

    public static abstract class Delegate {
        public final int version;

        /* access modifiers changed from: protected */
        public abstract void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase);

        /* access modifiers changed from: protected */
        public abstract void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase);

        /* access modifiers changed from: protected */
        public abstract void onCreate(SupportSQLiteDatabase supportSQLiteDatabase);

        /* access modifiers changed from: protected */
        public abstract void onOpen(SupportSQLiteDatabase supportSQLiteDatabase);

        /* access modifiers changed from: protected */
        public abstract void validateMigration(SupportSQLiteDatabase supportSQLiteDatabase);

        public Delegate(int i) {
            this.version = i;
        }
    }

    public RoomOpenHelper(DatabaseConfiguration databaseConfiguration, Delegate delegate, String str) {
        super(delegate.version);
        this.mConfiguration = databaseConfiguration;
        this.mDelegate = delegate;
        this.mIdentityHash = str;
    }

    public void onConfigure(SupportSQLiteDatabase supportSQLiteDatabase) {
        super.onConfigure(supportSQLiteDatabase);
    }

    public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
        updateIdentity(supportSQLiteDatabase);
        this.mDelegate.createAllTables(supportSQLiteDatabase);
        this.mDelegate.onCreate(supportSQLiteDatabase);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:19:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onUpgrade(android.arch.persistence.db.SupportSQLiteDatabase r3, int r4, int r5) {
        /*
            r2 = this;
            android.arch.persistence.room.DatabaseConfiguration r0 = r2.mConfiguration
            if (r0 == 0) goto L_0x002c
            android.arch.persistence.room.DatabaseConfiguration r0 = r2.mConfiguration
            android.arch.persistence.room.RoomDatabase$MigrationContainer r0 = r0.migrationContainer
            java.util.List r0 = r0.findMigrationPath(r4, r5)
            if (r0 == 0) goto L_0x002c
            java.util.Iterator r0 = r0.iterator()
        L_0x0012:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0022
            java.lang.Object r1 = r0.next()
            android.arch.persistence.room.migration.Migration r1 = (android.arch.persistence.room.migration.Migration) r1
            r1.migrate(r3)
            goto L_0x0012
        L_0x0022:
            android.arch.persistence.room.RoomOpenHelper$Delegate r0 = r2.mDelegate
            r0.validateMigration(r3)
            r2.updateIdentity(r3)
            r0 = 1
            goto L_0x002d
        L_0x002c:
            r0 = 0
        L_0x002d:
            if (r0 != 0) goto L_0x0073
            android.arch.persistence.room.DatabaseConfiguration r0 = r2.mConfiguration
            if (r0 == 0) goto L_0x0045
            android.arch.persistence.room.DatabaseConfiguration r0 = r2.mConfiguration
            boolean r0 = r0.requireMigration
            if (r0 == 0) goto L_0x003a
            goto L_0x0045
        L_0x003a:
            android.arch.persistence.room.RoomOpenHelper$Delegate r4 = r2.mDelegate
            r4.dropAllTables(r3)
            android.arch.persistence.room.RoomOpenHelper$Delegate r4 = r2.mDelegate
            r4.createAllTables(r3)
            goto L_0x0073
        L_0x0045:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "A migration from "
            r0.append(r1)
            r0.append(r4)
            java.lang.String r4 = " to "
            r0.append(r4)
            r0.append(r5)
            java.lang.String r4 = " is necessary. Please provide a Migration in the builder or call"
            r0.append(r4)
            java.lang.String r4 = " fallbackToDestructiveMigration in the builder in which case Room will"
            r0.append(r4)
            java.lang.String r4 = " re-create all of the tables."
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            r3.<init>(r4)
            throw r3
        L_0x0073:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.arch.persistence.room.RoomOpenHelper.onUpgrade(android.arch.persistence.db.SupportSQLiteDatabase, int, int):void");
    }

    public void onDowngrade(SupportSQLiteDatabase supportSQLiteDatabase, int i, int i2) {
        onUpgrade(supportSQLiteDatabase, i, i2);
    }

    public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
        super.onOpen(supportSQLiteDatabase);
        checkIdentity(supportSQLiteDatabase);
        this.mDelegate.onOpen(supportSQLiteDatabase);
        this.mConfiguration = null;
    }

    /* JADX INFO: finally extract failed */
    private void checkIdentity(SupportSQLiteDatabase supportSQLiteDatabase) {
        createMasterTableIfNotExists(supportSQLiteDatabase);
        String str = "";
        Cursor query = supportSQLiteDatabase.query((SupportSQLiteQuery) new SimpleSQLiteQuery("SELECT identity_hash FROM room_master_table WHERE id = 42 LIMIT 1"));
        try {
            if (query.moveToFirst()) {
                str = query.getString(0);
            }
            query.close();
            if (!this.mIdentityHash.equals(str)) {
                throw new IllegalStateException("Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number.");
            }
        } catch (Throwable th) {
            query.close();
            throw th;
        }
    }

    private void updateIdentity(SupportSQLiteDatabase supportSQLiteDatabase) {
        createMasterTableIfNotExists(supportSQLiteDatabase);
        supportSQLiteDatabase.execSQL(RoomMasterTable.createInsertQuery(this.mIdentityHash));
    }

    private void createMasterTableIfNotExists(SupportSQLiteDatabase supportSQLiteDatabase) {
        supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
    }
}
