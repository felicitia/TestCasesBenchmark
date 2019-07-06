package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

final class zzbd extends SQLiteOpenHelper {
    private final /* synthetic */ zzbc zzwy;

    zzbd(zzbc zzbc, Context context, String str) {
        this.zzwy = zzbc;
        super(context, str, null, 1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zza(android.database.sqlite.SQLiteDatabase r12, java.lang.String r13) {
        /*
            r11 = this;
            r0 = 0
            r1 = 0
            java.lang.String r3 = "SQLITE_MASTER"
            r2 = 1
            java.lang.String[] r4 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x002d }
            java.lang.String r5 = "name"
            r4[r0] = r5     // Catch:{ SQLiteException -> 0x002d }
            java.lang.String r5 = "name=?"
            java.lang.String[] r6 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x002d }
            r6[r0] = r13     // Catch:{ SQLiteException -> 0x002d }
            r7 = 0
            r8 = 0
            r9 = 0
            r2 = r12
            android.database.Cursor r12 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ SQLiteException -> 0x002d }
            boolean r1 = r12.moveToFirst()     // Catch:{ SQLiteException -> 0x0026, all -> 0x0023 }
            if (r12 == 0) goto L_0x0022
            r12.close()
        L_0x0022:
            return r1
        L_0x0023:
            r13 = move-exception
            r1 = r12
            goto L_0x003b
        L_0x0026:
            r1 = move-exception
            r10 = r1
            r1 = r12
            r12 = r10
            goto L_0x002e
        L_0x002b:
            r13 = move-exception
            goto L_0x003b
        L_0x002d:
            r12 = move-exception
        L_0x002e:
            com.google.android.gms.internal.measurement.zzbc r2 = r11.zzwy     // Catch:{ all -> 0x002b }
            java.lang.String r3 = "Error querying for table"
            r2.zzc(r3, r13, r12)     // Catch:{ all -> 0x002b }
            if (r1 == 0) goto L_0x003a
            r1.close()
        L_0x003a:
            return r0
        L_0x003b:
            if (r1 == 0) goto L_0x0040
            r1.close()
        L_0x0040:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbd.zza(android.database.sqlite.SQLiteDatabase, java.lang.String):boolean");
    }

    private static Set<String> zzb(SQLiteDatabase sQLiteDatabase, String str) {
        HashSet hashSet = new HashSet();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 22);
        sb.append("SELECT * FROM ");
        sb.append(str);
        sb.append(" LIMIT 0");
        Cursor rawQuery = sQLiteDatabase.rawQuery(sb.toString(), null);
        try {
            String[] columnNames = rawQuery.getColumnNames();
            for (String add : columnNames) {
                hashSet.add(add);
            }
            return hashSet;
        } finally {
            rawQuery.close();
        }
    }

    public final SQLiteDatabase getWritableDatabase() {
        if (!this.zzwy.zzwx.zzj(3600000)) {
            throw new SQLiteException("Database open failed");
        }
        try {
            return super.getWritableDatabase();
        } catch (SQLiteException unused) {
            this.zzwy.zzwx.start();
            this.zzwy.zzu("Opening the database failed, dropping the table and recreating it");
            this.zzwy.getContext().getDatabasePath(zzbc.zzcz()).delete();
            try {
                SQLiteDatabase writableDatabase = super.getWritableDatabase();
                this.zzwy.zzwx.clear();
                return writableDatabase;
            } catch (SQLiteException e) {
                this.zzwy.zze("Failed to open freshly created database", e);
                throw e;
            }
        }
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        String path = sQLiteDatabase.getPath();
        if (zzcb.version() >= 9) {
            File file = new File(path);
            file.setReadable(false, false);
            file.setWritable(false, false);
            file.setReadable(true, true);
            file.setWritable(true, true);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0099  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onOpen(android.database.sqlite.SQLiteDatabase r11) {
        /*
            r10 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 15
            if (r0 >= r1) goto L_0x0019
            java.lang.String r0 = "PRAGMA journal_mode=memory"
            r1 = 0
            android.database.Cursor r0 = r11.rawQuery(r0, r1)
            r0.moveToFirst()     // Catch:{ all -> 0x0014 }
            r0.close()
            goto L_0x0019
        L_0x0014:
            r11 = move-exception
            r0.close()
            throw r11
        L_0x0019:
            java.lang.String r0 = "hits2"
            boolean r0 = r10.zza(r11, r0)
            r1 = 3
            r2 = 2
            r3 = 4
            r4 = 0
            r5 = 1
            if (r0 != 0) goto L_0x002e
            java.lang.String r0 = com.google.android.gms.internal.measurement.zzbc.zzwt
        L_0x002a:
            r11.execSQL(r0)
            goto L_0x008b
        L_0x002e:
            java.lang.String r0 = "hits2"
            java.util.Set r0 = zzb(r11, r0)
            java.lang.String[] r6 = new java.lang.String[r3]
            java.lang.String r7 = "hit_id"
            r6[r4] = r7
            java.lang.String r7 = "hit_string"
            r6[r5] = r7
            java.lang.String r7 = "hit_time"
            r6[r2] = r7
            java.lang.String r7 = "hit_url"
            r6[r1] = r7
            r7 = 0
        L_0x0047:
            if (r7 >= r3) goto L_0x0071
            r8 = r6[r7]
            boolean r9 = r0.remove(r8)
            if (r9 != 0) goto L_0x006e
            android.database.sqlite.SQLiteException r11 = new android.database.sqlite.SQLiteException
            java.lang.String r0 = "Database hits2 is missing required column: "
            java.lang.String r1 = java.lang.String.valueOf(r8)
            int r2 = r1.length()
            if (r2 == 0) goto L_0x0064
            java.lang.String r0 = r0.concat(r1)
            goto L_0x006a
        L_0x0064:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r0)
            r0 = r1
        L_0x006a:
            r11.<init>(r0)
            throw r11
        L_0x006e:
            int r7 = r7 + 1
            goto L_0x0047
        L_0x0071:
            java.lang.String r6 = "hit_app_id"
            boolean r6 = r0.remove(r6)
            r6 = r6 ^ r5
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x0086
            android.database.sqlite.SQLiteException r11 = new android.database.sqlite.SQLiteException
            java.lang.String r0 = "Database hits2 has extra columns"
            r11.<init>(r0)
            throw r11
        L_0x0086:
            if (r6 == 0) goto L_0x008b
            java.lang.String r0 = "ALTER TABLE hits2 ADD COLUMN hit_app_id INTEGER"
            goto L_0x002a
        L_0x008b:
            java.lang.String r0 = "properties"
            boolean r0 = r10.zza(r11, r0)
            if (r0 != 0) goto L_0x0099
            java.lang.String r0 = "CREATE TABLE IF NOT EXISTS properties ( app_uid INTEGER NOT NULL, cid TEXT NOT NULL, tid TEXT NOT NULL, params TEXT NOT NULL, adid INTEGER NOT NULL, hits_count INTEGER NOT NULL, PRIMARY KEY (app_uid, cid, tid)) ;"
            r11.execSQL(r0)
            return
        L_0x0099:
            java.lang.String r0 = "properties"
            java.util.Set r11 = zzb(r11, r0)
            r0 = 6
            java.lang.String[] r6 = new java.lang.String[r0]
            java.lang.String r7 = "app_uid"
            r6[r4] = r7
            java.lang.String r7 = "cid"
            r6[r5] = r7
            java.lang.String r5 = "tid"
            r6[r2] = r5
            java.lang.String r2 = "params"
            r6[r1] = r2
            java.lang.String r1 = "adid"
            r6[r3] = r1
            r1 = 5
            java.lang.String r2 = "hits_count"
            r6[r1] = r2
        L_0x00bb:
            if (r4 >= r0) goto L_0x00e5
            r1 = r6[r4]
            boolean r2 = r11.remove(r1)
            if (r2 != 0) goto L_0x00e2
            android.database.sqlite.SQLiteException r11 = new android.database.sqlite.SQLiteException
            java.lang.String r0 = "Database properties is missing required column: "
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r2 = r1.length()
            if (r2 == 0) goto L_0x00d8
            java.lang.String r0 = r0.concat(r1)
            goto L_0x00de
        L_0x00d8:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r0)
            r0 = r1
        L_0x00de:
            r11.<init>(r0)
            throw r11
        L_0x00e2:
            int r4 = r4 + 1
            goto L_0x00bb
        L_0x00e5:
            boolean r11 = r11.isEmpty()
            if (r11 != 0) goto L_0x00f3
            android.database.sqlite.SQLiteException r11 = new android.database.sqlite.SQLiteException
            java.lang.String r0 = "Database properties table has extra columns"
            r11.<init>(r0)
            throw r11
        L_0x00f3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbd.onOpen(android.database.sqlite.SQLiteDatabase):void");
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
