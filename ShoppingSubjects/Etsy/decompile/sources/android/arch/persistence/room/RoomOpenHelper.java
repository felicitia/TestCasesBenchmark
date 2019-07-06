package android.arch.persistence.room;

import android.arch.persistence.db.SimpleSQLiteQuery;
import android.arch.persistence.db.d;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP})
public class RoomOpenHelper extends android.arch.persistence.db.b.a {
    @Nullable
    private a mConfiguration;
    @NonNull
    private final a mDelegate;
    @NonNull
    private final String mIdentityHash;
    @NonNull
    private final String mLegacyHash;

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static abstract class a {
        public final int a;

        /* access modifiers changed from: protected */
        public abstract void a(android.arch.persistence.db.a aVar);

        /* access modifiers changed from: protected */
        public abstract void b(android.arch.persistence.db.a aVar);

        /* access modifiers changed from: protected */
        public abstract void c(android.arch.persistence.db.a aVar);

        /* access modifiers changed from: protected */
        public abstract void d(android.arch.persistence.db.a aVar);

        /* access modifiers changed from: protected */
        public abstract void e(android.arch.persistence.db.a aVar);

        public a(int i) {
            this.a = i;
        }
    }

    public RoomOpenHelper(@NonNull a aVar, @NonNull a aVar2, @NonNull String str, @NonNull String str2) {
        super(aVar2.a);
        this.mConfiguration = aVar;
        this.mDelegate = aVar2;
        this.mIdentityHash = str;
        this.mLegacyHash = str2;
    }

    public RoomOpenHelper(@NonNull a aVar, @NonNull a aVar2, @NonNull String str) {
        this(aVar, aVar2, "", str);
    }

    public void onConfigure(android.arch.persistence.db.a aVar) {
        super.onConfigure(aVar);
    }

    public void onCreate(android.arch.persistence.db.a aVar) {
        updateIdentity(aVar);
        this.mDelegate.b(aVar);
        this.mDelegate.d(aVar);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:19:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onUpgrade(android.arch.persistence.db.a r3, int r4, int r5) {
        /*
            r2 = this;
            android.arch.persistence.room.a r0 = r2.mConfiguration
            if (r0 == 0) goto L_0x002c
            android.arch.persistence.room.a r0 = r2.mConfiguration
            android.arch.persistence.room.RoomDatabase$c r0 = r0.d
            java.util.List r0 = r0.a(r4, r5)
            if (r0 == 0) goto L_0x002c
            java.util.Iterator r0 = r0.iterator()
        L_0x0012:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0022
            java.lang.Object r1 = r0.next()
            android.arch.persistence.room.a.a r1 = (android.arch.persistence.room.a.a) r1
            r1.migrate(r3)
            goto L_0x0012
        L_0x0022:
            android.arch.persistence.room.RoomOpenHelper$a r0 = r2.mDelegate
            r0.e(r3)
            r2.updateIdentity(r3)
            r0 = 1
            goto L_0x002d
        L_0x002c:
            r0 = 0
        L_0x002d:
            if (r0 != 0) goto L_0x007e
            android.arch.persistence.room.a r0 = r2.mConfiguration
            if (r0 == 0) goto L_0x0046
            android.arch.persistence.room.a r0 = r2.mConfiguration
            boolean r0 = r0.a(r4)
            if (r0 != 0) goto L_0x0046
            android.arch.persistence.room.RoomOpenHelper$a r4 = r2.mDelegate
            r4.a(r3)
            android.arch.persistence.room.RoomOpenHelper$a r4 = r2.mDelegate
            r4.b(r3)
            goto L_0x007e
        L_0x0046:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "A migration from "
            r0.append(r1)
            r0.append(r4)
            java.lang.String r4 = " to "
            r0.append(r4)
            r0.append(r5)
            java.lang.String r4 = " was required but not found. Please provide the "
            r0.append(r4)
            java.lang.String r4 = "necessary Migration path via "
            r0.append(r4)
            java.lang.String r4 = "RoomDatabase.Builder.addMigration(Migration ...) or allow for "
            r0.append(r4)
            java.lang.String r4 = "destructive migrations via one of the "
            r0.append(r4)
            java.lang.String r4 = "RoomDatabase.Builder.fallbackToDestructiveMigration* methods."
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            r3.<init>(r4)
            throw r3
        L_0x007e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.arch.persistence.room.RoomOpenHelper.onUpgrade(android.arch.persistence.db.a, int, int):void");
    }

    public void onDowngrade(android.arch.persistence.db.a aVar, int i, int i2) {
        onUpgrade(aVar, i, i2);
    }

    public void onOpen(android.arch.persistence.db.a aVar) {
        super.onOpen(aVar);
        checkIdentity(aVar);
        this.mDelegate.c(aVar);
        this.mConfiguration = null;
    }

    private void checkIdentity(android.arch.persistence.db.a aVar) {
        Object obj = null;
        if (hasRoomMasterTable(aVar)) {
            Cursor a2 = aVar.a((d) new SimpleSQLiteQuery("SELECT identity_hash FROM room_master_table WHERE id = 42 LIMIT 1"));
            try {
                if (a2.moveToFirst()) {
                    obj = a2.getString(0);
                }
            } finally {
                a2.close();
            }
        }
        if (!this.mIdentityHash.equals(obj) && !this.mLegacyHash.equals(obj)) {
            throw new IllegalStateException("Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number.");
        }
    }

    private void updateIdentity(android.arch.persistence.db.a aVar) {
        createMasterTableIfNotExists(aVar);
        aVar.c(d.a(this.mIdentityHash));
    }

    private void createMasterTableIfNotExists(android.arch.persistence.db.a aVar) {
        aVar.c("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
    }

    private static boolean hasRoomMasterTable(android.arch.persistence.db.a aVar) {
        Cursor b = aVar.b("SELECT 1 FROM sqlite_master WHERE type = 'table' AND name='room_master_table'");
        try {
            boolean z = false;
            if (b.moveToFirst() && b.getInt(0) != 0) {
                z = true;
            }
            return z;
        } finally {
            b.close();
        }
    }
}
