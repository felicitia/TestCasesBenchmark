package com.google.android.gms.internal.measurement;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.HttpUtils;
import java.io.Closeable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class zzbc extends zzar implements Closeable {
    /* access modifiers changed from: private */
    public static final String zzwt = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER);", new Object[]{"hits2", "hit_id", "hit_time", "hit_url", "hit_string", "hit_app_id"});
    private static final String zzwu = String.format("SELECT MAX(%s) FROM %s WHERE 1;", new Object[]{"hit_time", "hits2"});
    private final zzbd zzwv;
    private final zzcz zzww = new zzcz(zzbt());
    /* access modifiers changed from: private */
    public final zzcz zzwx = new zzcz(zzbt());

    zzbc(zzat zzat) {
        super(zzat);
        this.zzwv = new zzbd(this, zzat.getContext(), "google_analytics_v4.db");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0035  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final long zza(java.lang.String r4, java.lang.String[] r5) {
        /*
            r3 = this;
            android.database.sqlite.SQLiteDatabase r5 = r3.getWritableDatabase()
            r0 = 0
            android.database.Cursor r5 = r5.rawQuery(r4, r0)     // Catch:{ SQLiteException -> 0x0029, all -> 0x0026 }
            boolean r0 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x0024 }
            if (r0 == 0) goto L_0x001a
            r0 = 0
            long r0 = r5.getLong(r0)     // Catch:{ SQLiteException -> 0x0024 }
            if (r5 == 0) goto L_0x0019
            r5.close()
        L_0x0019:
            return r0
        L_0x001a:
            android.database.sqlite.SQLiteException r0 = new android.database.sqlite.SQLiteException     // Catch:{ SQLiteException -> 0x0024 }
            java.lang.String r1 = "Database returned empty set"
            r0.<init>(r1)     // Catch:{ SQLiteException -> 0x0024 }
            throw r0     // Catch:{ SQLiteException -> 0x0024 }
        L_0x0022:
            r4 = move-exception
            goto L_0x0033
        L_0x0024:
            r0 = move-exception
            goto L_0x002d
        L_0x0026:
            r4 = move-exception
            r5 = r0
            goto L_0x0033
        L_0x0029:
            r5 = move-exception
            r2 = r0
            r0 = r5
            r5 = r2
        L_0x002d:
            java.lang.String r1 = "Database error"
            r3.zzd(r1, r4, r0)     // Catch:{ all -> 0x0022 }
            throw r0     // Catch:{ all -> 0x0022 }
        L_0x0033:
            if (r5 == 0) goto L_0x0038
            r5.close()
        L_0x0038:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbc.zza(java.lang.String, java.lang.String[]):long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0033  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final long zza(java.lang.String r1, java.lang.String[] r2, long r3) {
        /*
            r0 = this;
            android.database.sqlite.SQLiteDatabase r3 = r0.getWritableDatabase()
            r4 = 0
            android.database.Cursor r2 = r3.rawQuery(r1, r2)     // Catch:{ SQLiteException -> 0x002a }
            boolean r3 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x0025, all -> 0x0022 }
            if (r3 == 0) goto L_0x001a
            r3 = 0
            long r3 = r2.getLong(r3)     // Catch:{ SQLiteException -> 0x0025, all -> 0x0022 }
            if (r2 == 0) goto L_0x0019
            r2.close()
        L_0x0019:
            return r3
        L_0x001a:
            if (r2 == 0) goto L_0x001f
            r2.close()
        L_0x001f:
            r1 = 0
            return r1
        L_0x0022:
            r1 = move-exception
            r4 = r2
            goto L_0x0031
        L_0x0025:
            r3 = move-exception
            r4 = r2
            goto L_0x002b
        L_0x0028:
            r1 = move-exception
            goto L_0x0031
        L_0x002a:
            r3 = move-exception
        L_0x002b:
            java.lang.String r2 = "Database error"
            r0.zzd(r2, r1, r3)     // Catch:{ all -> 0x0028 }
            throw r3     // Catch:{ all -> 0x0028 }
        L_0x0031:
            if (r4 == 0) goto L_0x0036
            r4.close()
        L_0x0036:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbc.zza(java.lang.String, java.lang.String[], long):long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0077  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.List<java.lang.Long> zzc(long r14) {
        /*
            r13 = this;
            com.google.android.gms.analytics.zzk.zzab()
            r13.zzch()
            r0 = 0
            int r2 = (r14 > r0 ? 1 : (r14 == r0 ? 0 : -1))
            if (r2 > 0) goto L_0x0011
            java.util.List r14 = java.util.Collections.emptyList()
            return r14
        L_0x0011:
            android.database.sqlite.SQLiteDatabase r0 = r13.getWritableDatabase()
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            r10 = 0
            java.lang.String r1 = "hits2"
            r2 = 1
            java.lang.String[] r3 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x0069 }
            java.lang.String r4 = "hit_id"
            r11 = 0
            r3[r11] = r4     // Catch:{ SQLiteException -> 0x0069 }
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            java.lang.String r8 = "%s ASC"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ SQLiteException -> 0x0069 }
            java.lang.String r12 = "hit_id"
            r2[r11] = r12     // Catch:{ SQLiteException -> 0x0069 }
            java.lang.String r8 = java.lang.String.format(r8, r2)     // Catch:{ SQLiteException -> 0x0069 }
            java.lang.String r14 = java.lang.Long.toString(r14)     // Catch:{ SQLiteException -> 0x0069 }
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r14
            android.database.Cursor r14 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x0069 }
            boolean r15 = r14.moveToFirst()     // Catch:{ SQLiteException -> 0x0064, all -> 0x0061 }
            if (r15 == 0) goto L_0x005b
        L_0x004a:
            long r0 = r14.getLong(r11)     // Catch:{ SQLiteException -> 0x0064, all -> 0x0061 }
            java.lang.Long r15 = java.lang.Long.valueOf(r0)     // Catch:{ SQLiteException -> 0x0064, all -> 0x0061 }
            r9.add(r15)     // Catch:{ SQLiteException -> 0x0064, all -> 0x0061 }
            boolean r15 = r14.moveToNext()     // Catch:{ SQLiteException -> 0x0064, all -> 0x0061 }
            if (r15 != 0) goto L_0x004a
        L_0x005b:
            if (r14 == 0) goto L_0x0074
            r14.close()
            return r9
        L_0x0061:
            r15 = move-exception
            r10 = r14
            goto L_0x0075
        L_0x0064:
            r15 = move-exception
            r10 = r14
            goto L_0x006a
        L_0x0067:
            r15 = move-exception
            goto L_0x0075
        L_0x0069:
            r15 = move-exception
        L_0x006a:
            java.lang.String r14 = "Error selecting hit ids"
            r13.zzd(r14, r15)     // Catch:{ all -> 0x0067 }
            if (r10 == 0) goto L_0x0074
            r10.close()
        L_0x0074:
            return r9
        L_0x0075:
            if (r10 == 0) goto L_0x007a
            r10.close()
        L_0x007a:
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbc.zzc(long):java.util.List");
    }

    private final long zzcr() {
        zzk.zzab();
        zzch();
        return zza("SELECT COUNT(*) FROM hits2", null);
    }

    /* access modifiers changed from: private */
    public static String zzcz() {
        return "google_analytics_v4.db";
    }

    private final Map<String, String> zzv(String str) {
        if (TextUtils.isEmpty(str)) {
            return new HashMap(0);
        }
        try {
            if (!str.startsWith("?")) {
                String str2 = "?";
                String valueOf = String.valueOf(str);
                str = valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2);
            }
            return HttpUtils.parse(new URI(str), "UTF-8");
        } catch (URISyntaxException e) {
            zze("Error parsing hit parameters", e);
            return new HashMap(0);
        }
    }

    private final Map<String, String> zzw(String str) {
        if (TextUtils.isEmpty(str)) {
            return new HashMap(0);
        }
        String str2 = "?";
        try {
            String valueOf = String.valueOf(str);
            return HttpUtils.parse(new URI(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2)), "UTF-8");
        } catch (URISyntaxException e) {
            zze("Error parsing property parameters", e);
            return new HashMap(0);
        }
    }

    public final void beginTransaction() {
        zzch();
        getWritableDatabase().beginTransaction();
    }

    public final void close() {
        String str;
        try {
            this.zzwv.close();
        } catch (SQLiteException e) {
            e = e;
            str = "Sql error closing database";
            zze(str, e);
        } catch (IllegalStateException e2) {
            e = e2;
            str = "Error closing database";
            zze(str, e);
        }
    }

    public final void endTransaction() {
        zzch();
        getWritableDatabase().endTransaction();
    }

    /* access modifiers changed from: 0000 */
    public final SQLiteDatabase getWritableDatabase() {
        try {
            return this.zzwv.getWritableDatabase();
        } catch (SQLiteException e) {
            zzd("Error opening database", e);
            throw e;
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean isEmpty() {
        return zzcr() == 0;
    }

    public final void setTransactionSuccessful() {
        zzch();
        getWritableDatabase().setTransactionSuccessful();
    }

    public final long zza(long j, String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzch();
        zzk.zzab();
        return zza("SELECT hits_count FROM properties WHERE app_uid=? AND cid=? AND tid=?", new String[]{String.valueOf(j), str, str2}, 0);
    }

    public final void zza(List<Long> list) {
        Preconditions.checkNotNull(list);
        zzk.zzab();
        zzch();
        if (!list.isEmpty()) {
            StringBuilder sb = new StringBuilder("hit_id");
            sb.append(" in (");
            for (int i = 0; i < list.size(); i++) {
                Long l = (Long) list.get(i);
                if (l == null || l.longValue() == 0) {
                    throw new SQLiteException("Invalid hit id");
                }
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(l);
            }
            sb.append(")");
            String sb2 = sb.toString();
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                zza("Deleting dispatched hits. count", Integer.valueOf(list.size()));
                int delete = writableDatabase.delete("hits2", sb2, null);
                if (delete != list.size()) {
                    zzb("Deleted fewer hits then expected", Integer.valueOf(list.size()), Integer.valueOf(delete), sb2);
                }
            } catch (SQLiteException e) {
                zze("Error deleting hits", e);
                throw e;
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zzac() {
    }

    public final void zzc(zzch zzch) {
        Preconditions.checkNotNull(zzch);
        zzk.zzab();
        zzch();
        Preconditions.checkNotNull(zzch);
        Builder builder = new Builder();
        for (Entry entry : zzch.zzcs().entrySet()) {
            String str = (String) entry.getKey();
            if (!"ht".equals(str) && !"qt".equals(str) && !"AppUID".equals(str)) {
                builder.appendQueryParameter(str, (String) entry.getValue());
            }
        }
        String encodedQuery = builder.build().getEncodedQuery();
        if (encodedQuery == null) {
            encodedQuery = "";
        }
        if (encodedQuery.length() > 8192) {
            zzbu().zza(zzch, "Hit length exceeds the maximum allowed size");
            return;
        }
        int intValue = ((Integer) zzcc.zzyo.get()).intValue();
        long zzcr = zzcr();
        if (zzcr > ((long) (intValue - 1))) {
            List zzc = zzc((zzcr - ((long) intValue)) + 1);
            zzd("Store full, deleting hits to make room, count", Integer.valueOf(zzc.size()));
            zza(zzc);
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hit_string", encodedQuery);
        contentValues.put("hit_time", Long.valueOf(zzch.zzen()));
        contentValues.put("hit_app_id", Integer.valueOf(zzch.zzel()));
        contentValues.put("hit_url", zzch.zzep() ? zzbu.zzdz() : zzbu.zzea());
        try {
            long insert = writableDatabase.insert("hits2", null, contentValues);
            if (insert == -1) {
                zzu("Failed to insert a hit (got -1)");
            } else {
                zzb("Hit saved to database. db-id, hit", Long.valueOf(insert), zzch);
            }
        } catch (SQLiteException e) {
            zze("Error storing a hit", e);
        }
    }

    public final int zzcx() {
        zzk.zzab();
        zzch();
        if (!this.zzww.zzj(86400000)) {
            return 0;
        }
        this.zzww.start();
        zzq("Deleting stale hits (if any)");
        int delete = getWritableDatabase().delete("hits2", "hit_time < ?", new String[]{Long.toString(zzbt().currentTimeMillis() - 2592000000L)});
        zza("Deleted stale hits, count", Integer.valueOf(delete));
        return delete;
    }

    public final long zzcy() {
        zzk.zzab();
        zzch();
        return zza(zzwu, (String[]) null, 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00b2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.internal.measurement.zzch> zzd(long r24) {
        /*
            r23 = this;
            r10 = r23
            r1 = 0
            int r5 = (r24 > r1 ? 1 : (r24 == r1 ? 0 : -1))
            r11 = 0
            r12 = 1
            if (r5 < 0) goto L_0x000c
            r1 = 1
            goto L_0x000d
        L_0x000c:
            r1 = 0
        L_0x000d:
            com.google.android.gms.common.internal.Preconditions.checkArgument(r1)
            com.google.android.gms.analytics.zzk.zzab()
            r23.zzch()
            android.database.sqlite.SQLiteDatabase r13 = r23.getWritableDatabase()
            r1 = 0
            java.lang.String r14 = "hits2"
            r2 = 5
            java.lang.String[] r15 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x00a5, all -> 0x00a1 }
            java.lang.String r2 = "hit_id"
            r15[r11] = r2     // Catch:{ SQLiteException -> 0x00a5, all -> 0x00a1 }
            java.lang.String r2 = "hit_time"
            r15[r12] = r2     // Catch:{ SQLiteException -> 0x00a5, all -> 0x00a1 }
            java.lang.String r2 = "hit_string"
            r9 = 2
            r15[r9] = r2     // Catch:{ SQLiteException -> 0x00a5, all -> 0x00a1 }
            java.lang.String r2 = "hit_url"
            r7 = 3
            r15[r7] = r2     // Catch:{ SQLiteException -> 0x00a5, all -> 0x00a1 }
            java.lang.String r2 = "hit_app_id"
            r8 = 4
            r15[r8] = r2     // Catch:{ SQLiteException -> 0x00a5, all -> 0x00a1 }
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            java.lang.String r2 = "%s ASC"
            java.lang.Object[] r5 = new java.lang.Object[r12]     // Catch:{ SQLiteException -> 0x00a5, all -> 0x00a1 }
            java.lang.String r6 = "hit_id"
            r5[r11] = r6     // Catch:{ SQLiteException -> 0x00a5, all -> 0x00a1 }
            java.lang.String r20 = java.lang.String.format(r2, r5)     // Catch:{ SQLiteException -> 0x00a5, all -> 0x00a1 }
            java.lang.String r21 = java.lang.Long.toString(r24)     // Catch:{ SQLiteException -> 0x00a5, all -> 0x00a1 }
            android.database.Cursor r13 = r13.query(r14, r15, r16, r17, r18, r19, r20, r21)     // Catch:{ SQLiteException -> 0x00a5, all -> 0x00a1 }
            java.util.ArrayList r14 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x009f }
            r14.<init>()     // Catch:{ SQLiteException -> 0x009f }
            boolean r1 = r13.moveToFirst()     // Catch:{ SQLiteException -> 0x009f }
            if (r1 == 0) goto L_0x0099
        L_0x005e:
            long r15 = r13.getLong(r11)     // Catch:{ SQLiteException -> 0x009f }
            long r4 = r13.getLong(r12)     // Catch:{ SQLiteException -> 0x009f }
            java.lang.String r1 = r13.getString(r9)     // Catch:{ SQLiteException -> 0x009f }
            java.lang.String r2 = r13.getString(r7)     // Catch:{ SQLiteException -> 0x009f }
            int r17 = r13.getInt(r8)     // Catch:{ SQLiteException -> 0x009f }
            java.util.Map r3 = r10.zzv(r1)     // Catch:{ SQLiteException -> 0x009f }
            boolean r6 = com.google.android.gms.internal.measurement.zzdd.zzah(r2)     // Catch:{ SQLiteException -> 0x009f }
            com.google.android.gms.internal.measurement.zzch r2 = new com.google.android.gms.internal.measurement.zzch     // Catch:{ SQLiteException -> 0x009f }
            r1 = r2
            r11 = r2
            r2 = r10
            r18 = 3
            r19 = 4
            r7 = r15
            r15 = 2
            r9 = r17
            r1.<init>(r2, r3, r4, r6, r7, r9)     // Catch:{ SQLiteException -> 0x009f }
            r14.add(r11)     // Catch:{ SQLiteException -> 0x009f }
            boolean r1 = r13.moveToNext()     // Catch:{ SQLiteException -> 0x009f }
            if (r1 != 0) goto L_0x0094
            goto L_0x0099
        L_0x0094:
            r7 = 3
            r8 = 4
            r9 = 2
            r11 = 0
            goto L_0x005e
        L_0x0099:
            if (r13 == 0) goto L_0x009e
            r13.close()
        L_0x009e:
            return r14
        L_0x009f:
            r0 = move-exception
            goto L_0x00a7
        L_0x00a1:
            r0 = move-exception
            r13 = r1
        L_0x00a3:
            r1 = r0
            goto L_0x00b0
        L_0x00a5:
            r0 = move-exception
            r13 = r1
        L_0x00a7:
            r1 = r0
            java.lang.String r2 = "Error loading hits from the database"
            r10.zze(r2, r1)     // Catch:{ all -> 0x00ae }
            throw r1     // Catch:{ all -> 0x00ae }
        L_0x00ae:
            r0 = move-exception
            goto L_0x00a3
        L_0x00b0:
            if (r13 == 0) goto L_0x00b5
            r13.close()
        L_0x00b5:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbc.zzd(long):java.util.List");
    }

    public final void zze(long j) {
        zzk.zzab();
        zzch();
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Long.valueOf(j));
        zza("Deleting hit, id", Long.valueOf(j));
        zza((List<Long>) arrayList);
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00cf  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.internal.measurement.zzaw> zzf(long r28) {
        /*
            r27 = this;
            r1 = r27
            r27.zzch()
            com.google.android.gms.analytics.zzk.zzab()
            android.database.sqlite.SQLiteDatabase r2 = r27.getWritableDatabase()
            r3 = 5
            java.lang.String[] r4 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x00c2, all -> 0x00be }
            java.lang.String r3 = "cid"
            r12 = 0
            r4[r12] = r3     // Catch:{ SQLiteException -> 0x00c2, all -> 0x00be }
            java.lang.String r3 = "tid"
            r13 = 1
            r4[r13] = r3     // Catch:{ SQLiteException -> 0x00c2, all -> 0x00be }
            java.lang.String r3 = "adid"
            r14 = 2
            r4[r14] = r3     // Catch:{ SQLiteException -> 0x00c2, all -> 0x00be }
            java.lang.String r3 = "hits_count"
            r15 = 3
            r4[r15] = r3     // Catch:{ SQLiteException -> 0x00c2, all -> 0x00be }
            java.lang.String r3 = "params"
            r10 = 4
            r4[r10] = r3     // Catch:{ SQLiteException -> 0x00c2, all -> 0x00be }
            com.google.android.gms.internal.measurement.zzcd<java.lang.Integer> r3 = com.google.android.gms.internal.measurement.zzcc.zzyq     // Catch:{ SQLiteException -> 0x00c2, all -> 0x00be }
            java.lang.Object r3 = r3.get()     // Catch:{ SQLiteException -> 0x00c2, all -> 0x00be }
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch:{ SQLiteException -> 0x00c2, all -> 0x00be }
            int r9 = r3.intValue()     // Catch:{ SQLiteException -> 0x00c2, all -> 0x00be }
            java.lang.String r16 = java.lang.String.valueOf(r9)     // Catch:{ SQLiteException -> 0x00c2, all -> 0x00be }
            java.lang.String r5 = "app_uid=?"
            java.lang.String[] r6 = new java.lang.String[r13]     // Catch:{ SQLiteException -> 0x00c2, all -> 0x00be }
            java.lang.String r3 = "0"
            r6[r12] = r3     // Catch:{ SQLiteException -> 0x00c2, all -> 0x00be }
            java.lang.String r3 = "properties"
            r7 = 0
            r8 = 0
            r17 = 0
            r11 = r9
            r9 = r17
            r10 = r16
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x00c2, all -> 0x00be }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
            r3.<init>()     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
            boolean r4 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
            if (r4 == 0) goto L_0x00a6
        L_0x005a:
            java.lang.String r4 = r2.getString(r12)     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
            java.lang.String r5 = r2.getString(r13)     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
            int r6 = r2.getInt(r14)     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
            if (r6 == 0) goto L_0x006b
            r23 = 1
            goto L_0x006d
        L_0x006b:
            r23 = 0
        L_0x006d:
            int r6 = r2.getInt(r15)     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
            long r6 = (long) r6     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
            r8 = 4
            java.lang.String r9 = r2.getString(r8)     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
            java.util.Map r26 = r1.zzw(r9)     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
            boolean r9 = android.text.TextUtils.isEmpty(r4)     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
            if (r9 != 0) goto L_0x009b
            boolean r9 = android.text.TextUtils.isEmpty(r5)     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
            if (r9 == 0) goto L_0x0088
            goto L_0x009b
        L_0x0088:
            com.google.android.gms.internal.measurement.zzaw r9 = new com.google.android.gms.internal.measurement.zzaw     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
            r19 = 0
            r18 = r9
            r21 = r4
            r22 = r5
            r24 = r6
            r18.<init>(r19, r21, r22, r23, r24, r26)     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
            r3.add(r9)     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
            goto L_0x00a0
        L_0x009b:
            java.lang.String r6 = "Read property with empty client id or tracker id"
            r1.zzc(r6, r4, r5)     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
        L_0x00a0:
            boolean r4 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
            if (r4 != 0) goto L_0x005a
        L_0x00a6:
            int r4 = r3.size()     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
            if (r4 < r11) goto L_0x00b1
            java.lang.String r4 = "Sending hits to too many properties. Campaign report might be incorrect"
            r1.zzt(r4)     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
        L_0x00b1:
            if (r2 == 0) goto L_0x00b6
            r2.close()
        L_0x00b6:
            return r3
        L_0x00b7:
            r0 = move-exception
            r11 = r2
            goto L_0x00cc
        L_0x00ba:
            r0 = move-exception
            r11 = r2
            r2 = r0
            goto L_0x00c5
        L_0x00be:
            r0 = move-exception
            r2 = r0
            r11 = 0
            goto L_0x00cd
        L_0x00c2:
            r0 = move-exception
            r2 = r0
            r11 = 0
        L_0x00c5:
            java.lang.String r3 = "Error loading hits from the database"
            r1.zze(r3, r2)     // Catch:{ all -> 0x00cb }
            throw r2     // Catch:{ all -> 0x00cb }
        L_0x00cb:
            r0 = move-exception
        L_0x00cc:
            r2 = r0
        L_0x00cd:
            if (r11 == 0) goto L_0x00d2
            r11.close()
        L_0x00d2:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbc.zzf(long):java.util.List");
    }
}
