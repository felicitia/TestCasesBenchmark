package com.google.android.gms.internal.measurement;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class zzek extends zzjs {
    /* access modifiers changed from: private */
    public static final String[] zzagl = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzagm = {"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    /* access modifiers changed from: private */
    public static final String[] zzagn = {"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzago = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzagp = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzagq = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    private final zzen zzagr = new zzen(this, getContext(), "google_app_measurement.db");
    /* access modifiers changed from: private */
    public final zzjo zzags = new zzjo(zzbt());

    zzek(zzjt zzjt) {
        super(zzjt);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x003b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final long zza(java.lang.String r4, java.lang.String[] r5) {
        /*
            r3 = this;
            android.database.sqlite.SQLiteDatabase r0 = r3.getWritableDatabase()
            r1 = 0
            android.database.Cursor r5 = r0.rawQuery(r4, r5)     // Catch:{ SQLiteException -> 0x002a }
            boolean r0 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x0024, all -> 0x0022 }
            if (r0 == 0) goto L_0x001a
            r0 = 0
            long r0 = r5.getLong(r0)     // Catch:{ SQLiteException -> 0x0024, all -> 0x0022 }
            if (r5 == 0) goto L_0x0019
            r5.close()
        L_0x0019:
            return r0
        L_0x001a:
            android.database.sqlite.SQLiteException r0 = new android.database.sqlite.SQLiteException     // Catch:{ SQLiteException -> 0x0024, all -> 0x0022 }
            java.lang.String r1 = "Database returned empty set"
            r0.<init>(r1)     // Catch:{ SQLiteException -> 0x0024, all -> 0x0022 }
            throw r0     // Catch:{ SQLiteException -> 0x0024, all -> 0x0022 }
        L_0x0022:
            r4 = move-exception
            goto L_0x0039
        L_0x0024:
            r0 = move-exception
            r1 = r5
            goto L_0x002b
        L_0x0027:
            r4 = move-exception
            r5 = r1
            goto L_0x0039
        L_0x002a:
            r0 = move-exception
        L_0x002b:
            com.google.android.gms.internal.measurement.zzfi r5 = r3.zzgi()     // Catch:{ all -> 0x0027 }
            com.google.android.gms.internal.measurement.zzfk r5 = r5.zziv()     // Catch:{ all -> 0x0027 }
            java.lang.String r2 = "Database error"
            r5.zze(r2, r4, r0)     // Catch:{ all -> 0x0027 }
            throw r0     // Catch:{ all -> 0x0027 }
        L_0x0039:
            if (r5 == 0) goto L_0x003e
            r5.close()
        L_0x003e:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zza(java.lang.String, java.lang.String[]):long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0039  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final long zza(java.lang.String r3, java.lang.String[] r4, long r5) {
        /*
            r2 = this;
            android.database.sqlite.SQLiteDatabase r0 = r2.getWritableDatabase()
            r1 = 0
            android.database.Cursor r4 = r0.rawQuery(r3, r4)     // Catch:{ SQLiteException -> 0x0028 }
            boolean r0 = r4.moveToFirst()     // Catch:{ SQLiteException -> 0x0023, all -> 0x0020 }
            if (r0 == 0) goto L_0x001a
            r5 = 0
            long r5 = r4.getLong(r5)     // Catch:{ SQLiteException -> 0x0023, all -> 0x0020 }
            if (r4 == 0) goto L_0x0019
            r4.close()
        L_0x0019:
            return r5
        L_0x001a:
            if (r4 == 0) goto L_0x001f
            r4.close()
        L_0x001f:
            return r5
        L_0x0020:
            r3 = move-exception
            r1 = r4
            goto L_0x0037
        L_0x0023:
            r5 = move-exception
            r1 = r4
            goto L_0x0029
        L_0x0026:
            r3 = move-exception
            goto L_0x0037
        L_0x0028:
            r5 = move-exception
        L_0x0029:
            com.google.android.gms.internal.measurement.zzfi r4 = r2.zzgi()     // Catch:{ all -> 0x0026 }
            com.google.android.gms.internal.measurement.zzfk r4 = r4.zziv()     // Catch:{ all -> 0x0026 }
            java.lang.String r6 = "Database error"
            r4.zze(r6, r3, r5)     // Catch:{ all -> 0x0026 }
            throw r5     // Catch:{ all -> 0x0026 }
        L_0x0037:
            if (r1 == 0) goto L_0x003c
            r1.close()
        L_0x003c:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zza(java.lang.String, java.lang.String[], long):long");
    }

    private final Object zza(Cursor cursor, int i) {
        int type = cursor.getType(i);
        switch (type) {
            case 0:
                zzgi().zziv().log("Loaded invalid null value from database");
                return null;
            case 1:
                return Long.valueOf(cursor.getLong(i));
            case 2:
                return Double.valueOf(cursor.getDouble(i));
            case 3:
                return cursor.getString(i);
            case 4:
                zzgi().zziv().log("Loaded invalid blob type value, ignoring it");
                return null;
            default:
                zzgi().zziv().zzg("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
                return null;
        }
    }

    private static void zza(ContentValues contentValues, String str, Object obj) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
        } else if (obj instanceof Double) {
            contentValues.put(str, (Double) obj);
        } else {
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    private final boolean zza(String str, int i, zzkh zzkh) {
        zzch();
        zzab();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzkh);
        if (TextUtils.isEmpty(zzkh.zzatl)) {
            zzgi().zziy().zzd("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", zzfi.zzbp(str), Integer.valueOf(i), String.valueOf(zzkh.zzatk));
            return false;
        }
        try {
            byte[] bArr = new byte[zzkh.zzwb()];
            zzacb zzb = zzacb.zzb(bArr, 0, bArr.length);
            zzkh.zza(zzb);
            zzb.zzvt();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", zzkh.zzatk);
            contentValues.put("event_name", zzkh.zzatl);
            contentValues.put("data", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("event_filters", null, contentValues, 5) == -1) {
                    zzgi().zziv().zzg("Failed to insert event filter (got -1). appId", zzfi.zzbp(str));
                }
                return true;
            } catch (SQLiteException e) {
                zzgi().zziv().zze("Error storing event filter. appId", zzfi.zzbp(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzgi().zziv().zze("Configuration loss. Failed to serialize event filter. appId", zzfi.zzbp(str), e2);
            return false;
        }
    }

    private final boolean zza(String str, int i, zzkk zzkk) {
        zzch();
        zzab();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzkk);
        if (TextUtils.isEmpty(zzkk.zzauc)) {
            zzgi().zziy().zzd("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", zzfi.zzbp(str), Integer.valueOf(i), String.valueOf(zzkk.zzatk));
            return false;
        }
        try {
            byte[] bArr = new byte[zzkk.zzwb()];
            zzacb zzb = zzacb.zzb(bArr, 0, bArr.length);
            zzkk.zza(zzb);
            zzb.zzvt();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", zzkk.zzatk);
            contentValues.put("property_name", zzkk.zzauc);
            contentValues.put("data", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("property_filters", null, contentValues, 5) != -1) {
                    return true;
                }
                zzgi().zziv().zzg("Failed to insert property filter (got -1). appId", zzfi.zzbp(str));
                return false;
            } catch (SQLiteException e) {
                zzgi().zziv().zze("Error storing property filter. appId", zzfi.zzbp(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzgi().zziv().zze("Configuration loss. Failed to serialize property filter. appId", zzfi.zzbp(str), e2);
            return false;
        }
    }

    private final boolean zza(String str, List<Integer> list) {
        Preconditions.checkNotEmpty(str);
        zzch();
        zzab();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        try {
            long zza = zza("select count(1) from audience_filter_values where app_id=?", new String[]{str});
            int max = Math.max(0, Math.min(2000, zzgk().zzb(str, zzez.zzajj)));
            if (zza <= ((long) max)) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                Integer num = (Integer) list.get(i);
                if (num == null || !(num instanceof Integer)) {
                    return false;
                }
                arrayList.add(Integer.toString(num.intValue()));
            }
            String join = TextUtils.join(",", arrayList);
            StringBuilder sb = new StringBuilder(String.valueOf(join).length() + 2);
            sb.append("(");
            sb.append(join);
            sb.append(")");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder(String.valueOf(sb2).length() + 140);
            sb3.append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ");
            sb3.append(sb2);
            sb3.append(" order by rowid desc limit -1 offset ?)");
            return writableDatabase.delete("audience_filter_values", sb3.toString(), new String[]{str, Integer.toString(max)}) > 0;
        } catch (SQLiteException e) {
            zzgi().zziv().zze("Database error querying filters. appId", zzfi.zzbp(str), e);
            return false;
        }
    }

    private final boolean zzid() {
        return getContext().getDatabasePath("google_app_measurement.db").exists();
    }

    public final void beginTransaction() {
        zzch();
        getWritableDatabase().beginTransaction();
    }

    public final void endTransaction() {
        zzch();
        getWritableDatabase().endTransaction();
    }

    /* access modifiers changed from: 0000 */
    public final SQLiteDatabase getWritableDatabase() {
        zzab();
        try {
            return this.zzagr.getWritableDatabase();
        } catch (SQLiteException e) {
            zzgi().zziy().zzg("Error opening database", e);
            throw e;
        }
    }

    public final void setTransactionSuccessful() {
        zzch();
        getWritableDatabase().setTransactionSuccessful();
    }

    public final long zza(zzku zzku) throws IOException {
        long j;
        zzab();
        zzch();
        Preconditions.checkNotNull(zzku);
        Preconditions.checkNotEmpty(zzku.zzth);
        try {
            byte[] bArr = new byte[zzku.zzwb()];
            zzacb zzb = zzacb.zzb(bArr, 0, bArr.length);
            zzku.zza(zzb);
            zzb.zzvt();
            zzjz zzjf = zzjf();
            Preconditions.checkNotNull(bArr);
            zzjf.zzgg().zzab();
            MessageDigest messageDigest = zzkd.getMessageDigest();
            if (messageDigest == null) {
                zzjf.zzgi().zziv().log("Failed to get MD5");
                j = 0;
            } else {
                j = zzkd.zzc(messageDigest.digest(bArr));
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzku.zzth);
            contentValues.put("metadata_fingerprint", Long.valueOf(j));
            contentValues.put("metadata", bArr);
            try {
                getWritableDatabase().insertWithOnConflict("raw_events_metadata", null, contentValues, 4);
                return j;
            } catch (SQLiteException e) {
                zzgi().zziv().zze("Error storing raw event metadata. appId", zzfi.zzbp(zzku.zzth), e);
                throw e;
            }
        } catch (IOException e2) {
            zzgi().zziv().zze("Data loss. Failed to serialize event metadata. appId", zzfi.zzbp(zzku.zzth), e2);
            throw e2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x008f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.util.Pair<com.google.android.gms.internal.measurement.zzkr, java.lang.Long> zza(java.lang.String r8, java.lang.Long r9) {
        /*
            r7 = this;
            r7.zzab()
            r7.zzch()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r7.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0077, all -> 0x0074 }
            java.lang.String r2 = "select main_event, children_to_process from main_event_params where app_id=? and event_id=?"
            r3 = 2
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x0077, all -> 0x0074 }
            r4 = 0
            r3[r4] = r8     // Catch:{ SQLiteException -> 0x0077, all -> 0x0074 }
            java.lang.String r5 = java.lang.String.valueOf(r9)     // Catch:{ SQLiteException -> 0x0077, all -> 0x0074 }
            r6 = 1
            r3[r6] = r5     // Catch:{ SQLiteException -> 0x0077, all -> 0x0074 }
            android.database.Cursor r1 = r1.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x0077, all -> 0x0074 }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0072 }
            if (r2 != 0) goto L_0x0037
            com.google.android.gms.internal.measurement.zzfi r8 = r7.zzgi()     // Catch:{ SQLiteException -> 0x0072 }
            com.google.android.gms.internal.measurement.zzfk r8 = r8.zzjc()     // Catch:{ SQLiteException -> 0x0072 }
            java.lang.String r9 = "Main event not found"
            r8.log(r9)     // Catch:{ SQLiteException -> 0x0072 }
            if (r1 == 0) goto L_0x0036
            r1.close()
        L_0x0036:
            return r0
        L_0x0037:
            byte[] r2 = r1.getBlob(r4)     // Catch:{ SQLiteException -> 0x0072 }
            long r5 = r1.getLong(r6)     // Catch:{ SQLiteException -> 0x0072 }
            java.lang.Long r3 = java.lang.Long.valueOf(r5)     // Catch:{ SQLiteException -> 0x0072 }
            int r5 = r2.length     // Catch:{ SQLiteException -> 0x0072 }
            com.google.android.gms.internal.measurement.zzaca r2 = com.google.android.gms.internal.measurement.zzaca.zza(r2, r4, r5)     // Catch:{ SQLiteException -> 0x0072 }
            com.google.android.gms.internal.measurement.zzkr r4 = new com.google.android.gms.internal.measurement.zzkr     // Catch:{ SQLiteException -> 0x0072 }
            r4.<init>()     // Catch:{ SQLiteException -> 0x0072 }
            r4.zzb(r2)     // Catch:{ IOException -> 0x005a }
            android.util.Pair r8 = android.util.Pair.create(r4, r3)     // Catch:{ SQLiteException -> 0x0072 }
            if (r1 == 0) goto L_0x0059
            r1.close()
        L_0x0059:
            return r8
        L_0x005a:
            r2 = move-exception
            com.google.android.gms.internal.measurement.zzfi r3 = r7.zzgi()     // Catch:{ SQLiteException -> 0x0072 }
            com.google.android.gms.internal.measurement.zzfk r3 = r3.zziv()     // Catch:{ SQLiteException -> 0x0072 }
            java.lang.String r4 = "Failed to merge main event. appId, eventId"
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzfi.zzbp(r8)     // Catch:{ SQLiteException -> 0x0072 }
            r3.zzd(r4, r8, r9, r2)     // Catch:{ SQLiteException -> 0x0072 }
            if (r1 == 0) goto L_0x0071
            r1.close()
        L_0x0071:
            return r0
        L_0x0072:
            r8 = move-exception
            goto L_0x0079
        L_0x0074:
            r8 = move-exception
            r1 = r0
            goto L_0x008d
        L_0x0077:
            r8 = move-exception
            r1 = r0
        L_0x0079:
            com.google.android.gms.internal.measurement.zzfi r9 = r7.zzgi()     // Catch:{ all -> 0x008c }
            com.google.android.gms.internal.measurement.zzfk r9 = r9.zziv()     // Catch:{ all -> 0x008c }
            java.lang.String r2 = "Error selecting main event"
            r9.zzg(r2, r8)     // Catch:{ all -> 0x008c }
            if (r1 == 0) goto L_0x008b
            r1.close()
        L_0x008b:
            return r0
        L_0x008c:
            r8 = move-exception
        L_0x008d:
            if (r1 == 0) goto L_0x0092
            r1.close()
        L_0x0092:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zza(java.lang.String, java.lang.Long):android.util.Pair");
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x0140  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0149  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzel zza(long r22, java.lang.String r24, boolean r25, boolean r26, boolean r27, boolean r28, boolean r29) {
        /*
            r21 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24)
            r21.zzab()
            r21.zzch()
            r2 = 1
            java.lang.String[] r3 = new java.lang.String[r2]
            r4 = 0
            r3[r4] = r24
            com.google.android.gms.internal.measurement.zzel r5 = new com.google.android.gms.internal.measurement.zzel
            r5.<init>()
            android.database.sqlite.SQLiteDatabase r15 = r21.getWritableDatabase()     // Catch:{ SQLiteException -> 0x012a, all -> 0x0126 }
            java.lang.String r8 = "apps"
            r7 = 6
            java.lang.String[] r9 = new java.lang.String[r7]     // Catch:{ SQLiteException -> 0x012a, all -> 0x0126 }
            java.lang.String r7 = "day"
            r9[r4] = r7     // Catch:{ SQLiteException -> 0x012a, all -> 0x0126 }
            java.lang.String r7 = "daily_events_count"
            r9[r2] = r7     // Catch:{ SQLiteException -> 0x012a, all -> 0x0126 }
            java.lang.String r7 = "daily_public_events_count"
            r14 = 2
            r9[r14] = r7     // Catch:{ SQLiteException -> 0x012a, all -> 0x0126 }
            java.lang.String r7 = "daily_conversions_count"
            r13 = 3
            r9[r13] = r7     // Catch:{ SQLiteException -> 0x012a, all -> 0x0126 }
            java.lang.String r7 = "daily_error_events_count"
            r12 = 4
            r9[r12] = r7     // Catch:{ SQLiteException -> 0x012a, all -> 0x0126 }
            java.lang.String r7 = "daily_realtime_events_count"
            r11 = 5
            r9[r11] = r7     // Catch:{ SQLiteException -> 0x012a, all -> 0x0126 }
            java.lang.String r10 = "app_id=?"
            java.lang.String[] r7 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x012a, all -> 0x0126 }
            r7[r4] = r24     // Catch:{ SQLiteException -> 0x012a, all -> 0x0126 }
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = r7
            r7 = r15
            r6 = 5
            r11 = r19
            r6 = 4
            r12 = r16
            r6 = 3
            r13 = r17
            r6 = 2
            r14 = r18
            android.database.Cursor r7 = r7.query(r8, r9, r10, r11, r12, r13, r14)     // Catch:{ SQLiteException -> 0x012a, all -> 0x0126 }
            boolean r8 = r7.moveToFirst()     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            if (r8 != 0) goto L_0x0075
            com.google.android.gms.internal.measurement.zzfi r2 = r21.zzgi()     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zziy()     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            java.lang.String r3 = "Not updating daily counts, app is not known. appId"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfi.zzbp(r24)     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            r2.zzg(r3, r4)     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            if (r7 == 0) goto L_0x0074
            r7.close()
        L_0x0074:
            return r5
        L_0x0075:
            long r8 = r7.getLong(r4)     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            int r4 = (r8 > r22 ? 1 : (r8 == r22 ? 0 : -1))
            if (r4 != 0) goto L_0x009e
            long r8 = r7.getLong(r2)     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            r5.zzagu = r8     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            long r8 = r7.getLong(r6)     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            r5.zzagt = r8     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            r2 = 3
            long r8 = r7.getLong(r2)     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            r5.zzagv = r8     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            r2 = 4
            long r8 = r7.getLong(r2)     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            r5.zzagw = r8     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            r2 = 5
            long r8 = r7.getLong(r2)     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            r5.zzagx = r8     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
        L_0x009e:
            r8 = 1
            if (r25 == 0) goto L_0x00a9
            long r12 = r5.zzagu     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            r2 = 0
            long r1 = r12 + r8
            r5.zzagu = r1     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
        L_0x00a9:
            if (r26 == 0) goto L_0x00b2
            long r1 = r5.zzagt     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            r4 = 0
            long r12 = r1 + r8
            r5.zzagt = r12     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
        L_0x00b2:
            if (r27 == 0) goto L_0x00bb
            long r1 = r5.zzagv     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            r4 = 0
            long r12 = r1 + r8
            r5.zzagv = r12     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
        L_0x00bb:
            if (r28 == 0) goto L_0x00c4
            long r1 = r5.zzagw     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            r4 = 0
            long r12 = r1 + r8
            r5.zzagw = r12     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
        L_0x00c4:
            if (r29 == 0) goto L_0x00cd
            long r1 = r5.zzagx     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            r4 = 0
            long r12 = r1 + r8
            r5.zzagx = r12     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
        L_0x00cd:
            android.content.ContentValues r1 = new android.content.ContentValues     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            r1.<init>()     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            java.lang.String r2 = "day"
            java.lang.Long r4 = java.lang.Long.valueOf(r22)     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            r1.put(r2, r4)     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            java.lang.String r2 = "daily_public_events_count"
            long r8 = r5.zzagt     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            java.lang.Long r4 = java.lang.Long.valueOf(r8)     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            r1.put(r2, r4)     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            java.lang.String r2 = "daily_events_count"
            long r8 = r5.zzagu     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            java.lang.Long r4 = java.lang.Long.valueOf(r8)     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            r1.put(r2, r4)     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            java.lang.String r2 = "daily_conversions_count"
            long r8 = r5.zzagv     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            java.lang.Long r4 = java.lang.Long.valueOf(r8)     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            r1.put(r2, r4)     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            java.lang.String r2 = "daily_error_events_count"
            long r8 = r5.zzagw     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            java.lang.Long r4 = java.lang.Long.valueOf(r8)     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            r1.put(r2, r4)     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            java.lang.String r2 = "daily_realtime_events_count"
            long r8 = r5.zzagx     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            java.lang.Long r4 = java.lang.Long.valueOf(r8)     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            r1.put(r2, r4)     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            java.lang.String r2 = "apps"
            java.lang.String r4 = "app_id=?"
            r15.update(r2, r1, r4, r3)     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            if (r7 == 0) goto L_0x011e
            r7.close()
        L_0x011e:
            return r5
        L_0x011f:
            r0 = move-exception
            r1 = r0
            goto L_0x0147
        L_0x0122:
            r0 = move-exception
            r1 = r0
            r6 = r7
            goto L_0x012d
        L_0x0126:
            r0 = move-exception
            r1 = r0
            r7 = 0
            goto L_0x0147
        L_0x012a:
            r0 = move-exception
            r1 = r0
            r6 = 0
        L_0x012d:
            com.google.android.gms.internal.measurement.zzfi r2 = r21.zzgi()     // Catch:{ all -> 0x0144 }
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zziv()     // Catch:{ all -> 0x0144 }
            java.lang.String r3 = "Error updating daily counts. appId"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfi.zzbp(r24)     // Catch:{ all -> 0x0144 }
            r2.zze(r3, r4, r1)     // Catch:{ all -> 0x0144 }
            if (r6 == 0) goto L_0x0143
            r6.close()
        L_0x0143:
            return r5
        L_0x0144:
            r0 = move-exception
            r1 = r0
            r7 = r6
        L_0x0147:
            if (r7 == 0) goto L_0x014c
            r7.close()
        L_0x014c:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zza(long, java.lang.String, boolean, boolean, boolean, boolean, boolean):com.google.android.gms.internal.measurement.zzel");
    }

    public final void zza(zzea zzea) {
        Preconditions.checkNotNull(zzea);
        zzab();
        zzch();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzea.zzah());
        contentValues.put("app_instance_id", zzea.getAppInstanceId());
        contentValues.put("gmp_app_id", zzea.getGmpAppId());
        contentValues.put("resettable_device_id_hash", zzea.zzgq());
        contentValues.put("last_bundle_index", Long.valueOf(zzea.zzgy()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzea.zzgs()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzea.zzgt()));
        contentValues.put("app_version", zzea.zzag());
        contentValues.put("app_store", zzea.zzgv());
        contentValues.put("gmp_version", Long.valueOf(zzea.zzgw()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzea.zzgx()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzea.isMeasurementEnabled()));
        contentValues.put("day", Long.valueOf(zzea.zzhc()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzea.zzhd()));
        contentValues.put("daily_events_count", Long.valueOf(zzea.zzhe()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzea.zzhf()));
        contentValues.put("config_fetched_time", Long.valueOf(zzea.zzgz()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzea.zzha()));
        contentValues.put("app_version_int", Long.valueOf(zzea.zzgu()));
        contentValues.put("firebase_instance_id", zzea.zzgr());
        contentValues.put("daily_error_events_count", Long.valueOf(zzea.zzhh()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzea.zzhg()));
        contentValues.put("health_monitor_sample", zzea.zzhi());
        contentValues.put("android_id", Long.valueOf(zzea.zzhk()));
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(zzea.zzhl()));
        contentValues.put("ssaid_reporting_enabled", Boolean.valueOf(zzea.zzhm()));
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (((long) writableDatabase.update("apps", contentValues, "app_id = ?", new String[]{zzea.zzah()})) == 0 && writableDatabase.insertWithOnConflict("apps", null, contentValues, 5) == -1) {
                zzgi().zziv().zzg("Failed to insert/update app (got -1). appId", zzfi.zzbp(zzea.zzah()));
            }
        } catch (SQLiteException e) {
            zzgi().zziv().zze("Error storing app. appId", zzfi.zzbp(zzea.zzah()), e);
        }
    }

    public final void zza(zzet zzet) {
        Preconditions.checkNotNull(zzet);
        zzab();
        zzch();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzet.zzth);
        contentValues.put("name", zzet.name);
        contentValues.put("lifetime_count", Long.valueOf(zzet.zzahh));
        contentValues.put("current_bundle_count", Long.valueOf(zzet.zzahi));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzet.zzahj));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzet.zzahk));
        contentValues.put("last_sampled_complex_event_id", zzet.zzahl);
        contentValues.put("last_sampling_rate", zzet.zzahm);
        contentValues.put("last_exempt_from_sampling", (zzet.zzahn == null || !zzet.zzahn.booleanValue()) ? null : Long.valueOf(1));
        try {
            if (getWritableDatabase().insertWithOnConflict("events", null, contentValues, 5) == -1) {
                zzgi().zziv().zzg("Failed to insert/update event aggregates (got -1). appId", zzfi.zzbp(zzet.zzth));
            }
        } catch (SQLiteException e) {
            zzgi().zziv().zze("Error storing event aggregates. appId", zzfi.zzbp(zzet.zzth), e);
        }
    }

    public final boolean zza(zzef zzef) {
        Preconditions.checkNotNull(zzef);
        zzab();
        zzch();
        if (zzh(zzef.packageName, zzef.zzage.name) == null) {
            if (zza("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{zzef.packageName}) >= 1000) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzef.packageName);
        contentValues.put("origin", zzef.origin);
        contentValues.put("name", zzef.zzage.name);
        zza(contentValues, "value", zzef.zzage.getValue());
        contentValues.put("active", Boolean.valueOf(zzef.active));
        contentValues.put("trigger_event_name", zzef.triggerEventName);
        contentValues.put("trigger_timeout", Long.valueOf(zzef.triggerTimeout));
        zzgg();
        contentValues.put("timed_out_event", zzkd.zza((Parcelable) zzef.zzagf));
        contentValues.put("creation_timestamp", Long.valueOf(zzef.creationTimestamp));
        zzgg();
        contentValues.put("triggered_event", zzkd.zza((Parcelable) zzef.zzagg));
        contentValues.put("triggered_timestamp", Long.valueOf(zzef.zzage.zzast));
        contentValues.put("time_to_live", Long.valueOf(zzef.timeToLive));
        zzgg();
        contentValues.put("expired_event", zzkd.zza((Parcelable) zzef.zzagh));
        try {
            if (getWritableDatabase().insertWithOnConflict("conditional_properties", null, contentValues, 5) == -1) {
                zzgi().zziv().zzg("Failed to insert/update conditional user property (got -1)", zzfi.zzbp(zzef.packageName));
                return true;
            }
        } catch (SQLiteException e) {
            zzgi().zziv().zze("Error storing conditional user property", zzfi.zzbp(zzef.packageName), e);
        }
        return true;
    }

    public final boolean zza(zzes zzes, long j, boolean z) {
        zzfk zziv;
        String str;
        zzab();
        zzch();
        Preconditions.checkNotNull(zzes);
        Preconditions.checkNotEmpty(zzes.zzth);
        zzkr zzkr = new zzkr();
        zzkr.zzavc = Long.valueOf(zzes.zzahf);
        zzkr.zzava = new zzks[zzes.zzahg.size()];
        Iterator it = zzes.zzahg.iterator();
        int i = 0;
        while (it.hasNext()) {
            String str2 = (String) it.next();
            zzks zzks = new zzks();
            int i2 = i + 1;
            zzkr.zzava[i] = zzks;
            zzks.name = str2;
            zzjf().zza(zzks, zzes.zzahg.get(str2));
            i = i2;
        }
        try {
            byte[] bArr = new byte[zzkr.zzwb()];
            zzacb zzb = zzacb.zzb(bArr, 0, bArr.length);
            zzkr.zza(zzb);
            zzb.zzvt();
            zzgi().zzjc().zze("Saving event, name, data size", zzgf().zzbm(zzes.name), Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzes.zzth);
            contentValues.put("name", zzes.name);
            contentValues.put("timestamp", Long.valueOf(zzes.timestamp));
            contentValues.put("metadata_fingerprint", Long.valueOf(j));
            contentValues.put("data", bArr);
            contentValues.put("realtime", Integer.valueOf(z ? 1 : 0));
            try {
                if (getWritableDatabase().insert("raw_events", null, contentValues) != -1) {
                    return true;
                }
                zzgi().zziv().zzg("Failed to insert raw event (got -1). appId", zzfi.zzbp(zzes.zzth));
                return false;
            } catch (SQLiteException e) {
                e = e;
                zziv = zzgi().zziv();
                str = "Error storing raw event. appId";
                zziv.zze(str, zzfi.zzbp(zzes.zzth), e);
                return false;
            }
        } catch (IOException e2) {
            e = e2;
            zziv = zzgi().zziv();
            str = "Data loss. Failed to serialize event params/data. appId";
            zziv.zze(str, zzfi.zzbp(zzes.zzth), e);
            return false;
        }
    }

    public final boolean zza(zzkc zzkc) {
        Preconditions.checkNotNull(zzkc);
        zzab();
        zzch();
        if (zzh(zzkc.zzth, zzkc.name) == null) {
            if (zzkd.zzcg(zzkc.name)) {
                if (zza("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzkc.zzth}) >= 25) {
                    return false;
                }
            } else {
                if (zza("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzkc.zzth, zzkc.origin}) >= 25) {
                    return false;
                }
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzkc.zzth);
        contentValues.put("origin", zzkc.origin);
        contentValues.put("name", zzkc.name);
        contentValues.put("set_timestamp", Long.valueOf(zzkc.zzast));
        zza(contentValues, "value", zzkc.value);
        try {
            if (getWritableDatabase().insertWithOnConflict("user_attributes", null, contentValues, 5) == -1) {
                zzgi().zziv().zzg("Failed to insert/update user property (got -1). appId", zzfi.zzbp(zzkc.zzth));
                return true;
            }
        } catch (SQLiteException e) {
            zzgi().zziv().zze("Error storing user property. appId", zzfi.zzbp(zzkc.zzth), e);
        }
        return true;
    }

    public final boolean zza(zzku zzku, boolean z) {
        zzfk zziv;
        String str;
        zzab();
        zzch();
        Preconditions.checkNotNull(zzku);
        Preconditions.checkNotEmpty(zzku.zzth);
        Preconditions.checkNotNull(zzku.zzavm);
        zzhx();
        long currentTimeMillis = zzbt().currentTimeMillis();
        if (zzku.zzavm.longValue() < currentTimeMillis - zzeh.zzhq() || zzku.zzavm.longValue() > currentTimeMillis + zzeh.zzhq()) {
            zzgi().zziy().zzd("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzfi.zzbp(zzku.zzth), Long.valueOf(currentTimeMillis), zzku.zzavm);
        }
        try {
            byte[] bArr = new byte[zzku.zzwb()];
            zzacb zzb = zzacb.zzb(bArr, 0, bArr.length);
            zzku.zza(zzb);
            zzb.zzvt();
            byte[] zzb2 = zzjf().zzb(bArr);
            zzgi().zzjc().zzg("Saving bundle, size", Integer.valueOf(zzb2.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzku.zzth);
            contentValues.put("bundle_end_timestamp", zzku.zzavm);
            contentValues.put("data", zzb2);
            contentValues.put("has_realtime", Integer.valueOf(z ? 1 : 0));
            if (zzku.zzawj != null) {
                contentValues.put("retry_count", zzku.zzawj);
            }
            try {
                if (getWritableDatabase().insert("queue", null, contentValues) != -1) {
                    return true;
                }
                zzgi().zziv().zzg("Failed to insert bundle (got -1). appId", zzfi.zzbp(zzku.zzth));
                return false;
            } catch (SQLiteException e) {
                e = e;
                zziv = zzgi().zziv();
                str = "Error storing bundle. appId";
                zziv.zze(str, zzfi.zzbp(zzku.zzth), e);
                return false;
            }
        } catch (IOException e2) {
            e = e2;
            zziv = zzgi().zziv();
            str = "Data loss. Failed to serialize bundle. appId";
            zziv.zze(str, zzfi.zzbp(zzku.zzth), e);
            return false;
        }
    }

    public final boolean zza(String str, Long l, long j, zzkr zzkr) {
        zzab();
        zzch();
        Preconditions.checkNotNull(zzkr);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l);
        try {
            byte[] bArr = new byte[zzkr.zzwb()];
            zzacb zzb = zzacb.zzb(bArr, 0, bArr.length);
            zzkr.zza(zzb);
            zzb.zzvt();
            zzgi().zzjc().zze("Saving complex main event, appId, data size", zzgf().zzbm(str), Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("event_id", l);
            contentValues.put("children_to_process", Long.valueOf(j));
            contentValues.put("main_event", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("main_event_params", null, contentValues, 5) != -1) {
                    return true;
                }
                zzgi().zziv().zzg("Failed to insert complex main event (got -1). appId", zzfi.zzbp(str));
                return false;
            } catch (SQLiteException e) {
                zzgi().zziv().zze("Error storing complex main event. appId", zzfi.zzbp(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzgi().zziv().zzd("Data loss. Failed to serialize event params/data. appId, eventId", zzfi.zzbp(str), l, e2);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzag(long r5) {
        /*
            r4 = this;
            r4.zzab()
            r4.zzch()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r4.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            java.lang.String r2 = "select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            r6 = 0
            r3[r6] = r5     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            android.database.Cursor r5 = r1.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            boolean r1 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x003e }
            if (r1 != 0) goto L_0x0034
            com.google.android.gms.internal.measurement.zzfi r6 = r4.zzgi()     // Catch:{ SQLiteException -> 0x003e }
            com.google.android.gms.internal.measurement.zzfk r6 = r6.zzjc()     // Catch:{ SQLiteException -> 0x003e }
            java.lang.String r1 = "No expired configs for apps with pending events"
            r6.log(r1)     // Catch:{ SQLiteException -> 0x003e }
            if (r5 == 0) goto L_0x0033
            r5.close()
        L_0x0033:
            return r0
        L_0x0034:
            java.lang.String r6 = r5.getString(r6)     // Catch:{ SQLiteException -> 0x003e }
            if (r5 == 0) goto L_0x003d
            r5.close()
        L_0x003d:
            return r6
        L_0x003e:
            r6 = move-exception
            goto L_0x0045
        L_0x0040:
            r6 = move-exception
            r5 = r0
            goto L_0x0059
        L_0x0043:
            r6 = move-exception
            r5 = r0
        L_0x0045:
            com.google.android.gms.internal.measurement.zzfi r1 = r4.zzgi()     // Catch:{ all -> 0x0058 }
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zziv()     // Catch:{ all -> 0x0058 }
            java.lang.String r2 = "Error selecting expired configs"
            r1.zzg(r2, r6)     // Catch:{ all -> 0x0058 }
            if (r5 == 0) goto L_0x0057
            r5.close()
        L_0x0057:
            return r0
        L_0x0058:
            r6 = move-exception
        L_0x0059:
            if (r5 == 0) goto L_0x005e
            r5.close()
        L_0x005e:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzag(long):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:57:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00ff  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<android.util.Pair<com.google.android.gms.internal.measurement.zzku, java.lang.Long>> zzb(java.lang.String r18, int r19, int r20) {
        /*
            r17 = this;
            r1 = r20
            r17.zzab()
            r17.zzch()
            r2 = 1
            r3 = 0
            if (r19 <= 0) goto L_0x000e
            r5 = 1
            goto L_0x000f
        L_0x000e:
            r5 = 0
        L_0x000f:
            com.google.android.gms.common.internal.Preconditions.checkArgument(r5)
            if (r1 <= 0) goto L_0x0016
            r5 = 1
            goto L_0x0017
        L_0x0016:
            r5 = 0
        L_0x0017:
            com.google.android.gms.common.internal.Preconditions.checkArgument(r5)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r18)
            r5 = 0
            android.database.sqlite.SQLiteDatabase r6 = r17.getWritableDatabase()     // Catch:{ SQLiteException -> 0x00e0 }
            java.lang.String r7 = "queue"
            r8 = 3
            java.lang.String[] r8 = new java.lang.String[r8]     // Catch:{ SQLiteException -> 0x00e0 }
            java.lang.String r9 = "rowid"
            r8[r3] = r9     // Catch:{ SQLiteException -> 0x00e0 }
            java.lang.String r9 = "data"
            r8[r2] = r9     // Catch:{ SQLiteException -> 0x00e0 }
            java.lang.String r9 = "retry_count"
            r15 = 2
            r8[r15] = r9     // Catch:{ SQLiteException -> 0x00e0 }
            java.lang.String r9 = "app_id=?"
            java.lang.String[] r10 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x00e0 }
            r10[r3] = r18     // Catch:{ SQLiteException -> 0x00e0 }
            r11 = 0
            r12 = 0
            java.lang.String r13 = "rowid"
            java.lang.String r14 = java.lang.String.valueOf(r19)     // Catch:{ SQLiteException -> 0x00e0 }
            android.database.Cursor r4 = r6.query(r7, r8, r9, r10, r11, r12, r13, r14)     // Catch:{ SQLiteException -> 0x00e0 }
            boolean r5 = r4.moveToFirst()     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            if (r5 != 0) goto L_0x0056
            java.util.List r1 = java.util.Collections.emptyList()     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            if (r4 == 0) goto L_0x0055
            r4.close()
        L_0x0055:
            return r1
        L_0x0056:
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            r5.<init>()     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            r6 = 0
        L_0x005c:
            long r7 = r4.getLong(r3)     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            byte[] r9 = r4.getBlob(r2)     // Catch:{ IOException -> 0x00b4 }
            com.google.android.gms.internal.measurement.zzjz r10 = r17.zzjf()     // Catch:{ IOException -> 0x00b4 }
            byte[] r9 = r10.zza(r9)     // Catch:{ IOException -> 0x00b4 }
            boolean r10 = r5.isEmpty()     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            if (r10 != 0) goto L_0x0076
            int r10 = r9.length     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            int r10 = r10 + r6
            if (r10 > r1) goto L_0x00cf
        L_0x0076:
            int r10 = r9.length     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            com.google.android.gms.internal.measurement.zzaca r10 = com.google.android.gms.internal.measurement.zzaca.zza(r9, r3, r10)     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            com.google.android.gms.internal.measurement.zzku r11 = new com.google.android.gms.internal.measurement.zzku     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            r11.<init>()     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            r11.zzb(r10)     // Catch:{ IOException -> 0x00a1 }
            boolean r10 = r4.isNull(r15)     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            if (r10 != 0) goto L_0x0093
            int r10 = r4.getInt(r15)     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            r11.zzawj = r10     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
        L_0x0093:
            int r9 = r9.length     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            int r6 = r6 + r9
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            android.util.Pair r7 = android.util.Pair.create(r11, r7)     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            r5.add(r7)     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            goto L_0x00c7
        L_0x00a1:
            r0 = move-exception
            com.google.android.gms.internal.measurement.zzfi r7 = r17.zzgi()     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            com.google.android.gms.internal.measurement.zzfk r7 = r7.zziv()     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            java.lang.String r8 = "Failed to merge queued bundle. appId"
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzfi.zzbp(r18)     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            r7.zze(r8, r9, r0)     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            goto L_0x00c7
        L_0x00b4:
            r0 = move-exception
            r7 = r0
            com.google.android.gms.internal.measurement.zzfi r8 = r17.zzgi()     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            com.google.android.gms.internal.measurement.zzfk r8 = r8.zziv()     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            java.lang.String r9 = "Failed to unzip queued bundle. appId"
            java.lang.Object r10 = com.google.android.gms.internal.measurement.zzfi.zzbp(r18)     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            r8.zze(r9, r10, r7)     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
        L_0x00c7:
            boolean r7 = r4.moveToNext()     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            if (r7 == 0) goto L_0x00cf
            if (r6 <= r1) goto L_0x005c
        L_0x00cf:
            if (r4 == 0) goto L_0x00d4
            r4.close()
        L_0x00d4:
            return r5
        L_0x00d5:
            r0 = move-exception
            r1 = r0
            goto L_0x00fd
        L_0x00d8:
            r0 = move-exception
            r1 = r0
            r5 = r4
            goto L_0x00e2
        L_0x00dc:
            r0 = move-exception
            r1 = r0
            r4 = r5
            goto L_0x00fd
        L_0x00e0:
            r0 = move-exception
            r1 = r0
        L_0x00e2:
            com.google.android.gms.internal.measurement.zzfi r2 = r17.zzgi()     // Catch:{ all -> 0x00dc }
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zziv()     // Catch:{ all -> 0x00dc }
            java.lang.String r3 = "Error querying bundles. appId"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfi.zzbp(r18)     // Catch:{ all -> 0x00dc }
            r2.zze(r3, r4, r1)     // Catch:{ all -> 0x00dc }
            java.util.List r1 = java.util.Collections.emptyList()     // Catch:{ all -> 0x00dc }
            if (r5 == 0) goto L_0x00fc
            r5.close()
        L_0x00fc:
            return r1
        L_0x00fd:
            if (r4 == 0) goto L_0x0102
            r4.close()
        L_0x0102:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzb(java.lang.String, int, int):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0032, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0033, code lost:
        r15 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0127, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0128, code lost:
        r15 = r23;
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x012c, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x012d, code lost:
        r15 = r23;
        r12 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0148, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0151, code lost:
        r2.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0127 A[ExcHandler: all (r0v5 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:1:0x000f] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0148  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0151  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.internal.measurement.zzkc> zzb(java.lang.String r24, java.lang.String r25, java.lang.String r26) {
        /*
            r23 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24)
            r23.zzab()
            r23.zzch()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x012c, all -> 0x0127 }
            r4 = 3
            r3.<init>(r4)     // Catch:{ SQLiteException -> 0x012c, all -> 0x0127 }
            r12 = r24
            r3.add(r12)     // Catch:{ SQLiteException -> 0x0123, all -> 0x0127 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0123, all -> 0x0127 }
            java.lang.String r6 = "app_id=?"
            r5.<init>(r6)     // Catch:{ SQLiteException -> 0x0123, all -> 0x0127 }
            boolean r6 = android.text.TextUtils.isEmpty(r25)     // Catch:{ SQLiteException -> 0x0123, all -> 0x0127 }
            if (r6 != 0) goto L_0x0037
            r6 = r25
            r3.add(r6)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0127 }
            java.lang.String r7 = " and origin=?"
            r5.append(r7)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0127 }
            goto L_0x0039
        L_0x0032:
            r0 = move-exception
            r15 = r23
            goto L_0x0133
        L_0x0037:
            r6 = r25
        L_0x0039:
            boolean r7 = android.text.TextUtils.isEmpty(r26)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0127 }
            if (r7 != 0) goto L_0x0051
            java.lang.String r7 = java.lang.String.valueOf(r26)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0127 }
            java.lang.String r8 = "*"
            java.lang.String r7 = r7.concat(r8)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0127 }
            r3.add(r7)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0127 }
            java.lang.String r7 = " and name glob ?"
            r5.append(r7)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0127 }
        L_0x0051:
            int r7 = r3.size()     // Catch:{ SQLiteException -> 0x0032, all -> 0x0127 }
            java.lang.String[] r7 = new java.lang.String[r7]     // Catch:{ SQLiteException -> 0x0032, all -> 0x0127 }
            java.lang.Object[] r3 = r3.toArray(r7)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0127 }
            r17 = r3
            java.lang.String[] r17 = (java.lang.String[]) r17     // Catch:{ SQLiteException -> 0x0032, all -> 0x0127 }
            android.database.sqlite.SQLiteDatabase r13 = r23.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0032, all -> 0x0127 }
            java.lang.String r14 = "user_attributes"
            r3 = 4
            java.lang.String[] r15 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x0032, all -> 0x0127 }
            java.lang.String r3 = "name"
            r11 = 0
            r15[r11] = r3     // Catch:{ SQLiteException -> 0x0032, all -> 0x0127 }
            java.lang.String r3 = "set_timestamp"
            r9 = 1
            r15[r9] = r3     // Catch:{ SQLiteException -> 0x0032, all -> 0x0127 }
            java.lang.String r3 = "value"
            r10 = 2
            r15[r10] = r3     // Catch:{ SQLiteException -> 0x0032, all -> 0x0127 }
            java.lang.String r3 = "origin"
            r15[r4] = r3     // Catch:{ SQLiteException -> 0x0032, all -> 0x0127 }
            java.lang.String r16 = r5.toString()     // Catch:{ SQLiteException -> 0x0032, all -> 0x0127 }
            r18 = 0
            r19 = 0
            java.lang.String r20 = "rowid"
            java.lang.String r21 = "1001"
            android.database.Cursor r3 = r13.query(r14, r15, r16, r17, r18, r19, r20, r21)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0127 }
            boolean r5 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x011e, all -> 0x011a }
            if (r5 != 0) goto L_0x0097
            if (r3 == 0) goto L_0x0096
            r3.close()
        L_0x0096:
            return r1
        L_0x0097:
            int r5 = r1.size()     // Catch:{ SQLiteException -> 0x011e, all -> 0x011a }
            r7 = 1000(0x3e8, float:1.401E-42)
            if (r5 < r7) goto L_0x00b3
            com.google.android.gms.internal.measurement.zzfi r4 = r23.zzgi()     // Catch:{ SQLiteException -> 0x011e, all -> 0x011a }
            com.google.android.gms.internal.measurement.zzfk r4 = r4.zziv()     // Catch:{ SQLiteException -> 0x011e, all -> 0x011a }
            java.lang.String r5 = "Read more than the max allowed user properties, ignoring excess"
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ SQLiteException -> 0x011e, all -> 0x011a }
            r4.zzg(r5, r7)     // Catch:{ SQLiteException -> 0x011e, all -> 0x011a }
            r15 = r23
            goto L_0x0102
        L_0x00b3:
            java.lang.String r8 = r3.getString(r11)     // Catch:{ SQLiteException -> 0x011e, all -> 0x011a }
            long r13 = r3.getLong(r9)     // Catch:{ SQLiteException -> 0x011e, all -> 0x011a }
            r15 = r23
            java.lang.Object r16 = r15.zza(r3, r10)     // Catch:{ SQLiteException -> 0x0118 }
            java.lang.String r7 = r3.getString(r4)     // Catch:{ SQLiteException -> 0x0118 }
            if (r16 != 0) goto L_0x00e7
            com.google.android.gms.internal.measurement.zzfi r5 = r23.zzgi()     // Catch:{ SQLiteException -> 0x00e2 }
            com.google.android.gms.internal.measurement.zzfk r5 = r5.zziv()     // Catch:{ SQLiteException -> 0x00e2 }
            java.lang.String r6 = "(2)Read invalid user property value, ignoring it"
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzfi.zzbp(r24)     // Catch:{ SQLiteException -> 0x00e2 }
            r13 = r26
            r5.zzd(r6, r8, r7, r13)     // Catch:{ SQLiteException -> 0x00e2 }
            r17 = r7
            r13 = 0
            r18 = 1
            r19 = 2
            goto L_0x00fc
        L_0x00e2:
            r0 = move-exception
            r1 = r0
            r6 = r7
            goto L_0x0135
        L_0x00e7:
            com.google.android.gms.internal.measurement.zzkc r6 = new com.google.android.gms.internal.measurement.zzkc     // Catch:{ SQLiteException -> 0x0111 }
            r5 = r6
            r4 = r6
            r6 = r12
            r17 = r7
            r18 = 1
            r19 = 2
            r9 = r13
            r13 = 0
            r11 = r16
            r5.<init>(r6, r7, r8, r9, r11)     // Catch:{ SQLiteException -> 0x010f }
            r1.add(r4)     // Catch:{ SQLiteException -> 0x010f }
        L_0x00fc:
            boolean r4 = r3.moveToNext()     // Catch:{ SQLiteException -> 0x010f }
            if (r4 != 0) goto L_0x0108
        L_0x0102:
            if (r3 == 0) goto L_0x0107
            r3.close()
        L_0x0107:
            return r1
        L_0x0108:
            r6 = r17
            r4 = 3
            r9 = 1
            r10 = 2
            r11 = 0
            goto L_0x0097
        L_0x010f:
            r0 = move-exception
            goto L_0x0114
        L_0x0111:
            r0 = move-exception
            r17 = r7
        L_0x0114:
            r1 = r0
            r6 = r17
            goto L_0x0135
        L_0x0118:
            r0 = move-exception
            goto L_0x0121
        L_0x011a:
            r0 = move-exception
            r15 = r23
            goto L_0x014d
        L_0x011e:
            r0 = move-exception
            r15 = r23
        L_0x0121:
            r1 = r0
            goto L_0x0135
        L_0x0123:
            r0 = move-exception
            r15 = r23
            goto L_0x0131
        L_0x0127:
            r0 = move-exception
            r15 = r23
            r1 = r0
            goto L_0x014f
        L_0x012c:
            r0 = move-exception
            r15 = r23
            r12 = r24
        L_0x0131:
            r6 = r25
        L_0x0133:
            r1 = r0
            r3 = r2
        L_0x0135:
            com.google.android.gms.internal.measurement.zzfi r4 = r23.zzgi()     // Catch:{ all -> 0x014c }
            com.google.android.gms.internal.measurement.zzfk r4 = r4.zziv()     // Catch:{ all -> 0x014c }
            java.lang.String r5 = "(2)Error querying user properties"
            java.lang.Object r7 = com.google.android.gms.internal.measurement.zzfi.zzbp(r24)     // Catch:{ all -> 0x014c }
            r4.zzd(r5, r7, r6, r1)     // Catch:{ all -> 0x014c }
            if (r3 == 0) goto L_0x014b
            r3.close()
        L_0x014b:
            return r2
        L_0x014c:
            r0 = move-exception
        L_0x014d:
            r1 = r0
            r2 = r3
        L_0x014f:
            if (r2 == 0) goto L_0x0154
            r2.close()
        L_0x0154:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzb(java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x016e  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0177  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.internal.measurement.zzef> zzb(java.lang.String r39, java.lang.String[] r40) {
        /*
            r38 = this;
            r38.zzab()
            r38.zzch()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            android.database.sqlite.SQLiteDatabase r3 = r38.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0158, all -> 0x0154 }
            java.lang.String r4 = "conditional_properties"
            r5 = 13
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x0158, all -> 0x0154 }
            java.lang.String r6 = "app_id"
            r12 = 0
            r5[r12] = r6     // Catch:{ SQLiteException -> 0x0158, all -> 0x0154 }
            java.lang.String r6 = "origin"
            r13 = 1
            r5[r13] = r6     // Catch:{ SQLiteException -> 0x0158, all -> 0x0154 }
            java.lang.String r6 = "name"
            r14 = 2
            r5[r14] = r6     // Catch:{ SQLiteException -> 0x0158, all -> 0x0154 }
            java.lang.String r6 = "value"
            r15 = 3
            r5[r15] = r6     // Catch:{ SQLiteException -> 0x0158, all -> 0x0154 }
            java.lang.String r6 = "active"
            r11 = 4
            r5[r11] = r6     // Catch:{ SQLiteException -> 0x0158, all -> 0x0154 }
            java.lang.String r6 = "trigger_event_name"
            r10 = 5
            r5[r10] = r6     // Catch:{ SQLiteException -> 0x0158, all -> 0x0154 }
            java.lang.String r6 = "trigger_timeout"
            r9 = 6
            r5[r9] = r6     // Catch:{ SQLiteException -> 0x0158, all -> 0x0154 }
            java.lang.String r6 = "timed_out_event"
            r8 = 7
            r5[r8] = r6     // Catch:{ SQLiteException -> 0x0158, all -> 0x0154 }
            java.lang.String r6 = "creation_timestamp"
            r7 = 8
            r5[r7] = r6     // Catch:{ SQLiteException -> 0x0158, all -> 0x0154 }
            java.lang.String r6 = "triggered_event"
            r2 = 9
            r5[r2] = r6     // Catch:{ SQLiteException -> 0x0158, all -> 0x0154 }
            java.lang.String r6 = "triggered_timestamp"
            r2 = 10
            r5[r2] = r6     // Catch:{ SQLiteException -> 0x0158, all -> 0x0154 }
            java.lang.String r6 = "time_to_live"
            r2 = 11
            r5[r2] = r6     // Catch:{ SQLiteException -> 0x0158, all -> 0x0154 }
            java.lang.String r6 = "expired_event"
            r2 = 12
            r5[r2] = r6     // Catch:{ SQLiteException -> 0x0158, all -> 0x0154 }
            r20 = 0
            r21 = 0
            java.lang.String r22 = "rowid"
            java.lang.String r23 = "1001"
            r6 = r39
            r2 = 8
            r7 = r40
            r2 = 7
            r8 = r20
            r2 = 6
            r9 = r21
            r2 = 5
            r10 = r22
            r2 = 4
            r11 = r23
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ SQLiteException -> 0x0158, all -> 0x0154 }
            boolean r4 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            if (r4 != 0) goto L_0x0085
            if (r3 == 0) goto L_0x0084
            r3.close()
        L_0x0084:
            return r1
        L_0x0085:
            int r4 = r1.size()     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            r5 = 1000(0x3e8, float:1.401E-42)
            if (r4 < r5) goto L_0x00a0
            com.google.android.gms.internal.measurement.zzfi r2 = r38.zzgi()     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zziv()     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            java.lang.String r4 = "Read more than the max allowed conditional properties, ignoring extra"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            r2.zzg(r4, r5)     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            goto L_0x0143
        L_0x00a0:
            java.lang.String r4 = r3.getString(r12)     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            java.lang.String r11 = r3.getString(r13)     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            java.lang.String r6 = r3.getString(r14)     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            r10 = r38
            java.lang.Object r9 = r10.zza(r3, r15)     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            int r5 = r3.getInt(r2)     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            if (r5 == 0) goto L_0x00bc
            r7 = 5
            r22 = 1
            goto L_0x00bf
        L_0x00bc:
            r7 = 5
            r22 = 0
        L_0x00bf:
            java.lang.String r23 = r3.getString(r7)     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            r8 = 6
            long r27 = r3.getLong(r8)     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            com.google.android.gms.internal.measurement.zzjz r5 = r38.zzjf()     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            r2 = 7
            byte[] r7 = r3.getBlob(r2)     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzex> r2 = com.google.android.gms.internal.measurement.zzex.CREATOR     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            android.os.Parcelable r2 = r5.zza(r7, r2)     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            com.google.android.gms.internal.measurement.zzex r2 = (com.google.android.gms.internal.measurement.zzex) r2     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            r7 = 8
            long r20 = r3.getLong(r7)     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            com.google.android.gms.internal.measurement.zzjz r5 = r38.zzjf()     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            r12 = 9
            byte[] r7 = r3.getBlob(r12)     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzex> r8 = com.google.android.gms.internal.measurement.zzex.CREATOR     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            android.os.Parcelable r5 = r5.zza(r7, r8)     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            r29 = r5
            com.google.android.gms.internal.measurement.zzex r29 = (com.google.android.gms.internal.measurement.zzex) r29     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            r7 = 10
            long r16 = r3.getLong(r7)     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            r8 = 11
            long r34 = r3.getLong(r8)     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            com.google.android.gms.internal.measurement.zzjz r5 = r38.zzjf()     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            r12 = 12
            byte[] r7 = r3.getBlob(r12)     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzex> r8 = com.google.android.gms.internal.measurement.zzex.CREATOR     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            android.os.Parcelable r5 = r5.zza(r7, r8)     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            r30 = r5
            com.google.android.gms.internal.measurement.zzex r30 = (com.google.android.gms.internal.measurement.zzex) r30     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            com.google.android.gms.internal.measurement.zzka r19 = new com.google.android.gms.internal.measurement.zzka     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            r5 = r19
            r31 = 5
            r32 = 6
            r33 = 8
            r36 = 10
            r37 = 11
            r7 = r16
            r10 = r11
            r5.<init>(r6, r7, r9, r10)     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            com.google.android.gms.internal.measurement.zzef r5 = new com.google.android.gms.internal.measurement.zzef     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            r16 = r5
            r17 = r4
            r18 = r11
            r24 = r2
            r25 = r27
            r27 = r29
            r28 = r34
            r16.<init>(r17, r18, r19, r20, r22, r23, r24, r25, r27, r28, r30)     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            r1.add(r5)     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            boolean r2 = r3.moveToNext()     // Catch:{ SQLiteException -> 0x0150, all -> 0x014d }
            if (r2 != 0) goto L_0x0149
        L_0x0143:
            if (r3 == 0) goto L_0x0148
            r3.close()
        L_0x0148:
            return r1
        L_0x0149:
            r2 = 4
            r12 = 0
            goto L_0x0085
        L_0x014d:
            r0 = move-exception
            r1 = r0
            goto L_0x0175
        L_0x0150:
            r0 = move-exception
            r1 = r0
            r2 = r3
            goto L_0x015b
        L_0x0154:
            r0 = move-exception
            r1 = r0
            r3 = 0
            goto L_0x0175
        L_0x0158:
            r0 = move-exception
            r1 = r0
            r2 = 0
        L_0x015b:
            com.google.android.gms.internal.measurement.zzfi r3 = r38.zzgi()     // Catch:{ all -> 0x0172 }
            com.google.android.gms.internal.measurement.zzfk r3 = r3.zziv()     // Catch:{ all -> 0x0172 }
            java.lang.String r4 = "Error querying conditional user property value"
            r3.zzg(r4, r1)     // Catch:{ all -> 0x0172 }
            java.util.List r1 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0172 }
            if (r2 == 0) goto L_0x0171
            r2.close()
        L_0x0171:
            return r1
        L_0x0172:
            r0 = move-exception
            r1 = r0
            r3 = r2
        L_0x0175:
            if (r3 == 0) goto L_0x017a
            r3.close()
        L_0x017a:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzb(java.lang.String, java.lang.String[]):java.util.List");
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(String str, zzkg[] zzkgArr) {
        boolean z;
        zzfk zziy;
        String str2;
        Object zzbp;
        Integer num;
        zzch();
        zzab();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzkgArr);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            zzch();
            zzab();
            Preconditions.checkNotEmpty(str);
            SQLiteDatabase writableDatabase2 = getWritableDatabase();
            writableDatabase2.delete("property_filters", "app_id=?", new String[]{str});
            writableDatabase2.delete("event_filters", "app_id=?", new String[]{str});
            for (zzkg zzkg : zzkgArr) {
                zzch();
                zzab();
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotNull(zzkg);
                Preconditions.checkNotNull(zzkg.zzatg);
                Preconditions.checkNotNull(zzkg.zzatf);
                if (zzkg.zzate != null) {
                    int intValue = zzkg.zzate.intValue();
                    zzkh[] zzkhArr = zzkg.zzatg;
                    int length = zzkhArr.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            zzkk[] zzkkArr = zzkg.zzatf;
                            int length2 = zzkkArr.length;
                            int i2 = 0;
                            while (i2 < length2) {
                                if (zzkkArr[i2].zzatk == null) {
                                    zziy = zzgi().zziy();
                                    str2 = "Property filter with no ID. Audience definition ignored. appId, audienceId";
                                    zzbp = zzfi.zzbp(str);
                                    num = zzkg.zzate;
                                } else {
                                    i2++;
                                }
                            }
                            zzkh[] zzkhArr2 = zzkg.zzatg;
                            int length3 = zzkhArr2.length;
                            int i3 = 0;
                            while (true) {
                                if (i3 >= length3) {
                                    z = true;
                                    break;
                                } else if (!zza(str, intValue, zzkhArr2[i3])) {
                                    z = false;
                                    break;
                                } else {
                                    i3++;
                                }
                            }
                            if (z) {
                                zzkk[] zzkkArr2 = zzkg.zzatf;
                                int length4 = zzkkArr2.length;
                                int i4 = 0;
                                while (true) {
                                    if (i4 >= length4) {
                                        break;
                                    } else if (!zza(str, intValue, zzkkArr2[i4])) {
                                        z = false;
                                        break;
                                    } else {
                                        i4++;
                                    }
                                }
                            }
                            if (!z) {
                                zzch();
                                zzab();
                                Preconditions.checkNotEmpty(str);
                                SQLiteDatabase writableDatabase3 = getWritableDatabase();
                                writableDatabase3.delete("property_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(intValue)});
                                writableDatabase3.delete("event_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(intValue)});
                            }
                        } else if (zzkhArr[i].zzatk == null) {
                            zziy = zzgi().zziy();
                            str2 = "Event filter with no ID. Audience definition ignored. appId, audienceId";
                            zzbp = zzfi.zzbp(str);
                            num = zzkg.zzate;
                            break;
                        } else {
                            i++;
                        }
                    }
                    zziy.zze(str2, zzbp, num);
                    break;
                } else {
                    zzgi().zziy().zzg("Audience with no ID. appId", zzfi.zzbp(str));
                }
            }
            ArrayList arrayList = new ArrayList();
            for (zzkg zzkg2 : zzkgArr) {
                arrayList.add(zzkg2.zzate);
            }
            zza(str, (List<Integer>) arrayList);
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00bd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.internal.measurement.zzkc> zzbe(java.lang.String r25) {
        /*
            r24 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r25)
            r24.zzab()
            r24.zzch()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r24.getWritableDatabase()     // Catch:{ SQLiteException -> 0x009d, all -> 0x0097 }
            java.lang.String r4 = "user_attributes"
            r5 = 4
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x009d, all -> 0x0097 }
            java.lang.String r6 = "name"
            r12 = 0
            r5[r12] = r6     // Catch:{ SQLiteException -> 0x009d, all -> 0x0097 }
            java.lang.String r6 = "origin"
            r13 = 1
            r5[r13] = r6     // Catch:{ SQLiteException -> 0x009d, all -> 0x0097 }
            java.lang.String r6 = "set_timestamp"
            r14 = 2
            r5[r14] = r6     // Catch:{ SQLiteException -> 0x009d, all -> 0x0097 }
            java.lang.String r6 = "value"
            r15 = 3
            r5[r15] = r6     // Catch:{ SQLiteException -> 0x009d, all -> 0x0097 }
            java.lang.String r6 = "app_id=?"
            java.lang.String[] r7 = new java.lang.String[r13]     // Catch:{ SQLiteException -> 0x009d, all -> 0x0097 }
            r7[r12] = r25     // Catch:{ SQLiteException -> 0x009d, all -> 0x0097 }
            r8 = 0
            r9 = 0
            java.lang.String r10 = "rowid"
            java.lang.String r11 = "1000"
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ SQLiteException -> 0x009d, all -> 0x0097 }
            boolean r4 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x0092, all -> 0x008e }
            if (r4 != 0) goto L_0x0048
            if (r3 == 0) goto L_0x0047
            r3.close()
        L_0x0047:
            return r1
        L_0x0048:
            java.lang.String r19 = r3.getString(r12)     // Catch:{ SQLiteException -> 0x0092, all -> 0x008e }
            java.lang.String r4 = r3.getString(r13)     // Catch:{ SQLiteException -> 0x0092, all -> 0x008e }
            if (r4 != 0) goto L_0x0054
            java.lang.String r4 = ""
        L_0x0054:
            r18 = r4
            long r20 = r3.getLong(r14)     // Catch:{ SQLiteException -> 0x0092, all -> 0x008e }
            r4 = r24
            java.lang.Object r22 = r4.zza(r3, r15)     // Catch:{ SQLiteException -> 0x008c }
            if (r22 != 0) goto L_0x0074
            com.google.android.gms.internal.measurement.zzfi r5 = r24.zzgi()     // Catch:{ SQLiteException -> 0x008c }
            com.google.android.gms.internal.measurement.zzfk r5 = r5.zziv()     // Catch:{ SQLiteException -> 0x008c }
            java.lang.String r6 = "Read invalid user property value, ignoring it. appId"
            java.lang.Object r7 = com.google.android.gms.internal.measurement.zzfi.zzbp(r25)     // Catch:{ SQLiteException -> 0x008c }
            r5.zzg(r6, r7)     // Catch:{ SQLiteException -> 0x008c }
            goto L_0x0080
        L_0x0074:
            com.google.android.gms.internal.measurement.zzkc r5 = new com.google.android.gms.internal.measurement.zzkc     // Catch:{ SQLiteException -> 0x008c }
            r16 = r5
            r17 = r25
            r16.<init>(r17, r18, r19, r20, r22)     // Catch:{ SQLiteException -> 0x008c }
            r1.add(r5)     // Catch:{ SQLiteException -> 0x008c }
        L_0x0080:
            boolean r5 = r3.moveToNext()     // Catch:{ SQLiteException -> 0x008c }
            if (r5 != 0) goto L_0x0048
            if (r3 == 0) goto L_0x008b
            r3.close()
        L_0x008b:
            return r1
        L_0x008c:
            r0 = move-exception
            goto L_0x0095
        L_0x008e:
            r0 = move-exception
            r4 = r24
            goto L_0x00ba
        L_0x0092:
            r0 = move-exception
            r4 = r24
        L_0x0095:
            r1 = r0
            goto L_0x00a2
        L_0x0097:
            r0 = move-exception
            r4 = r24
            r1 = r0
            r3 = r2
            goto L_0x00bb
        L_0x009d:
            r0 = move-exception
            r4 = r24
            r1 = r0
            r3 = r2
        L_0x00a2:
            com.google.android.gms.internal.measurement.zzfi r5 = r24.zzgi()     // Catch:{ all -> 0x00b9 }
            com.google.android.gms.internal.measurement.zzfk r5 = r5.zziv()     // Catch:{ all -> 0x00b9 }
            java.lang.String r6 = "Error querying user properties. appId"
            java.lang.Object r7 = com.google.android.gms.internal.measurement.zzfi.zzbp(r25)     // Catch:{ all -> 0x00b9 }
            r5.zze(r6, r7, r1)     // Catch:{ all -> 0x00b9 }
            if (r3 == 0) goto L_0x00b8
            r3.close()
        L_0x00b8:
            return r2
        L_0x00b9:
            r0 = move-exception
        L_0x00ba:
            r1 = r0
        L_0x00bb:
            if (r3 == 0) goto L_0x00c0
            r3.close()
        L_0x00c0:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzbe(java.lang.String):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0178 A[Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x017c A[Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x01b0 A[Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x01b3 A[Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x01c2 A[Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x01d7 A[Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x01eb A[Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x01fe  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x022f  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0238  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzea zzbf(java.lang.String r21) {
        /*
            r20 = this;
            r1 = r21
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r21)
            r20.zzab()
            r20.zzch()
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r20.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            java.lang.String r4 = "apps"
            r5 = 25
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            java.lang.String r6 = "app_instance_id"
            r11 = 0
            r5[r11] = r6     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            java.lang.String r6 = "gmp_app_id"
            r12 = 1
            r5[r12] = r6     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            java.lang.String r6 = "resettable_device_id_hash"
            r13 = 2
            r5[r13] = r6     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            java.lang.String r6 = "last_bundle_index"
            r14 = 3
            r5[r14] = r6     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            java.lang.String r6 = "last_bundle_start_timestamp"
            r15 = 4
            r5[r15] = r6     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            java.lang.String r6 = "last_bundle_end_timestamp"
            r10 = 5
            r5[r10] = r6     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            java.lang.String r6 = "app_version"
            r9 = 6
            r5[r9] = r6     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            java.lang.String r6 = "app_store"
            r8 = 7
            r5[r8] = r6     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            java.lang.String r6 = "gmp_version"
            r7 = 8
            r5[r7] = r6     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            r6 = 9
            java.lang.String r16 = "dev_cert_hash"
            r5[r6] = r16     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            java.lang.String r6 = "measurement_enabled"
            r15 = 10
            r5[r15] = r6     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            r6 = 11
            java.lang.String r16 = "day"
            r5[r6] = r16     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            r6 = 12
            java.lang.String r16 = "daily_public_events_count"
            r5[r6] = r16     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            r6 = 13
            java.lang.String r16 = "daily_events_count"
            r5[r6] = r16     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            r6 = 14
            java.lang.String r16 = "daily_conversions_count"
            r5[r6] = r16     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            r6 = 15
            java.lang.String r16 = "config_fetched_time"
            r5[r6] = r16     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            r6 = 16
            java.lang.String r16 = "failed_config_fetch_time"
            r5[r6] = r16     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            java.lang.String r6 = "app_version_int"
            r15 = 17
            r5[r15] = r6     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            r6 = 18
            java.lang.String r16 = "firebase_instance_id"
            r5[r6] = r16     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            r6 = 19
            java.lang.String r16 = "daily_error_events_count"
            r5[r6] = r16     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            r6 = 20
            java.lang.String r16 = "daily_realtime_events_count"
            r5[r6] = r16     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            r6 = 21
            java.lang.String r16 = "health_monitor_sample"
            r5[r6] = r16     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            java.lang.String r6 = "android_id"
            r15 = 22
            r5[r15] = r6     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            java.lang.String r6 = "adid_reporting_enabled"
            r15 = 23
            r5[r15] = r6     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            java.lang.String r6 = "ssaid_reporting_enabled"
            r15 = 24
            r5[r15] = r6     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            java.lang.String r6 = "app_id=?"
            java.lang.String[] r7 = new java.lang.String[r12]     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            r7[r11] = r1     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            r16 = 0
            r17 = 0
            r18 = 0
            r15 = 8
            r15 = 7
            r8 = r16
            r15 = 6
            r9 = r17
            r15 = 5
            r10 = r18
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            boolean r4 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x020b, all -> 0x0206 }
            if (r4 != 0) goto L_0x00cb
            if (r3 == 0) goto L_0x00ca
            r3.close()
        L_0x00ca:
            return r2
        L_0x00cb:
            com.google.android.gms.internal.measurement.zzea r4 = new com.google.android.gms.internal.measurement.zzea     // Catch:{ SQLiteException -> 0x020b, all -> 0x0206 }
            r5 = r20
            com.google.android.gms.internal.measurement.zzjt r6 = r5.zzalo     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            com.google.android.gms.internal.measurement.zzgn r6 = r6.zzlm()     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.<init>(r6, r1)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            java.lang.String r6 = r3.getString(r11)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.zzam(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            java.lang.String r6 = r3.getString(r12)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.zzan(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            java.lang.String r6 = r3.getString(r13)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.zzao(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            long r6 = r3.getLong(r14)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.zzw(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 4
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.zzr(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            long r6 = r3.getLong(r15)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.zzs(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 6
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.setAppVersion(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 7
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.zzaq(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 8
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.zzu(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 9
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.zzv(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 10
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            if (r7 != 0) goto L_0x0136
            int r6 = r3.getInt(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            if (r6 == 0) goto L_0x0134
            goto L_0x0136
        L_0x0134:
            r6 = 0
            goto L_0x0137
        L_0x0136:
            r6 = 1
        L_0x0137:
            r4.setMeasurementEnabled(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 11
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.zzz(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 12
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.zzaa(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 13
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.zzab(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 14
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.zzac(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 15
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.zzx(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 16
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.zzy(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 17
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            if (r7 == 0) goto L_0x017c
            r6 = -2147483648(0xffffffff80000000, double:NaN)
            goto L_0x0181
        L_0x017c:
            int r6 = r3.getInt(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            long r6 = (long) r6     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
        L_0x0181:
            r4.zzt(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 18
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.zzap(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 19
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.zzae(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 20
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.zzad(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 21
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.zzar(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 22
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            if (r7 == 0) goto L_0x01b3
            r6 = 0
            goto L_0x01b7
        L_0x01b3:
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
        L_0x01b7:
            r4.zzaf(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 23
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            if (r7 != 0) goto L_0x01cb
            int r6 = r3.getInt(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            if (r6 == 0) goto L_0x01c9
            goto L_0x01cb
        L_0x01c9:
            r6 = 0
            goto L_0x01cc
        L_0x01cb:
            r6 = 1
        L_0x01cc:
            r4.zzd(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 24
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            if (r7 != 0) goto L_0x01df
            int r6 = r3.getInt(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            if (r6 == 0) goto L_0x01de
            goto L_0x01df
        L_0x01de:
            r12 = 0
        L_0x01df:
            r4.zze(r12)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.zzgp()     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            boolean r6 = r3.moveToNext()     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            if (r6 == 0) goto L_0x01fc
            com.google.android.gms.internal.measurement.zzfi r6 = r20.zzgi()     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            com.google.android.gms.internal.measurement.zzfk r6 = r6.zziv()     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            java.lang.String r7 = "Got multiple records for app, expected one. appId"
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzfi.zzbp(r21)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6.zzg(r7, r8)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
        L_0x01fc:
            if (r3 == 0) goto L_0x0201
            r3.close()
        L_0x0201:
            return r4
        L_0x0202:
            r0 = move-exception
            goto L_0x0209
        L_0x0204:
            r0 = move-exception
            goto L_0x020e
        L_0x0206:
            r0 = move-exception
            r5 = r20
        L_0x0209:
            r1 = r0
            goto L_0x0236
        L_0x020b:
            r0 = move-exception
            r5 = r20
        L_0x020e:
            r4 = r3
            r3 = r0
            goto L_0x021c
        L_0x0211:
            r0 = move-exception
            r5 = r20
            r1 = r0
            r3 = r2
            goto L_0x0236
        L_0x0217:
            r0 = move-exception
            r5 = r20
            r3 = r0
            r4 = r2
        L_0x021c:
            com.google.android.gms.internal.measurement.zzfi r6 = r20.zzgi()     // Catch:{ all -> 0x0233 }
            com.google.android.gms.internal.measurement.zzfk r6 = r6.zziv()     // Catch:{ all -> 0x0233 }
            java.lang.String r7 = "Error querying app. appId"
            java.lang.Object r1 = com.google.android.gms.internal.measurement.zzfi.zzbp(r21)     // Catch:{ all -> 0x0233 }
            r6.zze(r7, r1, r3)     // Catch:{ all -> 0x0233 }
            if (r4 == 0) goto L_0x0232
            r4.close()
        L_0x0232:
            return r2
        L_0x0233:
            r0 = move-exception
            r1 = r0
            r3 = r4
        L_0x0236:
            if (r3 == 0) goto L_0x023b
            r3.close()
        L_0x023b:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzbf(java.lang.String):com.google.android.gms.internal.measurement.zzea");
    }

    public final long zzbg(String str) {
        Preconditions.checkNotEmpty(str);
        zzab();
        zzch();
        try {
            return (long) getWritableDatabase().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str, String.valueOf(Math.max(0, Math.min(1000000, zzgk().zzb(str, zzez.zzait))))});
        } catch (SQLiteException e) {
            zzgi().zziv().zze("Error deleting over the limit events. appId", zzfi.zzbp(str), e);
            return 0;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0079  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] zzbh(java.lang.String r12) {
        /*
            r11 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)
            r11.zzab()
            r11.zzch()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r11.getWritableDatabase()     // Catch:{ SQLiteException -> 0x005d, all -> 0x005a }
            java.lang.String r2 = "apps"
            r3 = 1
            java.lang.String[] r4 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x005d, all -> 0x005a }
            java.lang.String r5 = "remote_config"
            r9 = 0
            r4[r9] = r5     // Catch:{ SQLiteException -> 0x005d, all -> 0x005a }
            java.lang.String r5 = "app_id=?"
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x005d, all -> 0x005a }
            r6[r9] = r12     // Catch:{ SQLiteException -> 0x005d, all -> 0x005a }
            r7 = 0
            r8 = 0
            r10 = 0
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r10
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x005d, all -> 0x005a }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0058 }
            if (r2 != 0) goto L_0x0037
            if (r1 == 0) goto L_0x0036
            r1.close()
        L_0x0036:
            return r0
        L_0x0037:
            byte[] r2 = r1.getBlob(r9)     // Catch:{ SQLiteException -> 0x0058 }
            boolean r3 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x0058 }
            if (r3 == 0) goto L_0x0052
            com.google.android.gms.internal.measurement.zzfi r3 = r11.zzgi()     // Catch:{ SQLiteException -> 0x0058 }
            com.google.android.gms.internal.measurement.zzfk r3 = r3.zziv()     // Catch:{ SQLiteException -> 0x0058 }
            java.lang.String r4 = "Got multiple records for app config, expected one. appId"
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzfi.zzbp(r12)     // Catch:{ SQLiteException -> 0x0058 }
            r3.zzg(r4, r5)     // Catch:{ SQLiteException -> 0x0058 }
        L_0x0052:
            if (r1 == 0) goto L_0x0057
            r1.close()
        L_0x0057:
            return r2
        L_0x0058:
            r2 = move-exception
            goto L_0x005f
        L_0x005a:
            r12 = move-exception
            r1 = r0
            goto L_0x0077
        L_0x005d:
            r2 = move-exception
            r1 = r0
        L_0x005f:
            com.google.android.gms.internal.measurement.zzfi r3 = r11.zzgi()     // Catch:{ all -> 0x0076 }
            com.google.android.gms.internal.measurement.zzfk r3 = r3.zziv()     // Catch:{ all -> 0x0076 }
            java.lang.String r4 = "Error querying remote config. appId"
            java.lang.Object r12 = com.google.android.gms.internal.measurement.zzfi.zzbp(r12)     // Catch:{ all -> 0x0076 }
            r3.zze(r4, r12, r2)     // Catch:{ all -> 0x0076 }
            if (r1 == 0) goto L_0x0075
            r1.close()
        L_0x0075:
            return r0
        L_0x0076:
            r12 = move-exception
        L_0x0077:
            if (r1 == 0) goto L_0x007c
            r1.close()
        L_0x007c:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzbh(java.lang.String):byte[]");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x009b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, com.google.android.gms.internal.measurement.zzkv> zzbi(java.lang.String r12) {
        /*
            r11 = this;
            r11.zzch()
            r11.zzab()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)
            android.database.sqlite.SQLiteDatabase r0 = r11.getWritableDatabase()
            r8 = 0
            java.lang.String r1 = "audience_filter_values"
            r2 = 2
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x007f, all -> 0x007c }
            java.lang.String r3 = "audience_id"
            r9 = 0
            r2[r9] = r3     // Catch:{ SQLiteException -> 0x007f, all -> 0x007c }
            java.lang.String r3 = "current_results"
            r10 = 1
            r2[r10] = r3     // Catch:{ SQLiteException -> 0x007f, all -> 0x007c }
            java.lang.String r3 = "app_id=?"
            java.lang.String[] r4 = new java.lang.String[r10]     // Catch:{ SQLiteException -> 0x007f, all -> 0x007c }
            r4[r9] = r12     // Catch:{ SQLiteException -> 0x007f, all -> 0x007c }
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x007f, all -> 0x007c }
            boolean r1 = r0.moveToFirst()     // Catch:{ SQLiteException -> 0x007a }
            if (r1 != 0) goto L_0x0036
            if (r0 == 0) goto L_0x0035
            r0.close()
        L_0x0035:
            return r8
        L_0x0036:
            android.support.v4.util.ArrayMap r1 = new android.support.v4.util.ArrayMap     // Catch:{ SQLiteException -> 0x007a }
            r1.<init>()     // Catch:{ SQLiteException -> 0x007a }
        L_0x003b:
            int r2 = r0.getInt(r9)     // Catch:{ SQLiteException -> 0x007a }
            byte[] r3 = r0.getBlob(r10)     // Catch:{ SQLiteException -> 0x007a }
            int r4 = r3.length     // Catch:{ SQLiteException -> 0x007a }
            com.google.android.gms.internal.measurement.zzaca r3 = com.google.android.gms.internal.measurement.zzaca.zza(r3, r9, r4)     // Catch:{ SQLiteException -> 0x007a }
            com.google.android.gms.internal.measurement.zzkv r4 = new com.google.android.gms.internal.measurement.zzkv     // Catch:{ SQLiteException -> 0x007a }
            r4.<init>()     // Catch:{ SQLiteException -> 0x007a }
            r4.zzb(r3)     // Catch:{ IOException -> 0x0058 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ SQLiteException -> 0x007a }
            r1.put(r2, r4)     // Catch:{ SQLiteException -> 0x007a }
            goto L_0x006e
        L_0x0058:
            r3 = move-exception
            com.google.android.gms.internal.measurement.zzfi r4 = r11.zzgi()     // Catch:{ SQLiteException -> 0x007a }
            com.google.android.gms.internal.measurement.zzfk r4 = r4.zziv()     // Catch:{ SQLiteException -> 0x007a }
            java.lang.String r5 = "Failed to merge filter results. appId, audienceId, error"
            java.lang.Object r6 = com.google.android.gms.internal.measurement.zzfi.zzbp(r12)     // Catch:{ SQLiteException -> 0x007a }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ SQLiteException -> 0x007a }
            r4.zzd(r5, r6, r2, r3)     // Catch:{ SQLiteException -> 0x007a }
        L_0x006e:
            boolean r2 = r0.moveToNext()     // Catch:{ SQLiteException -> 0x007a }
            if (r2 != 0) goto L_0x003b
            if (r0 == 0) goto L_0x0079
            r0.close()
        L_0x0079:
            return r1
        L_0x007a:
            r1 = move-exception
            goto L_0x0081
        L_0x007c:
            r12 = move-exception
            r0 = r8
            goto L_0x0099
        L_0x007f:
            r1 = move-exception
            r0 = r8
        L_0x0081:
            com.google.android.gms.internal.measurement.zzfi r2 = r11.zzgi()     // Catch:{ all -> 0x0098 }
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zziv()     // Catch:{ all -> 0x0098 }
            java.lang.String r3 = "Database error querying filter results. appId"
            java.lang.Object r12 = com.google.android.gms.internal.measurement.zzfi.zzbp(r12)     // Catch:{ all -> 0x0098 }
            r2.zze(r3, r12, r1)     // Catch:{ all -> 0x0098 }
            if (r0 == 0) goto L_0x0097
            r0.close()
        L_0x0097:
            return r8
        L_0x0098:
            r12 = move-exception
        L_0x0099:
            if (r0 == 0) goto L_0x009e
            r0.close()
        L_0x009e:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzbi(java.lang.String):java.util.Map");
    }

    public final long zzbj(String str) {
        Preconditions.checkNotEmpty(str);
        return zza("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0);
    }

    public final List<zzef> zzc(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzab();
        zzch();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat("*"));
            sb.append(" and name glob ?");
        }
        return zzb(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    /* access modifiers changed from: 0000 */
    public final void zzc(List<Long> list) {
        zzab();
        zzch();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (zzid()) {
            String join = TextUtils.join(",", list);
            StringBuilder sb = new StringBuilder(String.valueOf(join).length() + 2);
            sb.append("(");
            sb.append(join);
            sb.append(")");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder(String.valueOf(sb2).length() + 80);
            sb3.append("SELECT COUNT(1) FROM queue WHERE rowid IN ");
            sb3.append(sb2);
            sb3.append(" AND retry_count =  2147483647 LIMIT 1");
            if (zza(sb3.toString(), (String[]) null) > 0) {
                zzgi().zziy().log("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                StringBuilder sb4 = new StringBuilder(String.valueOf(sb2).length() + 127);
                sb4.append("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN ");
                sb4.append(sb2);
                sb4.append(" AND (retry_count IS NULL OR retry_count < 2147483647)");
                writableDatabase.execSQL(sb4.toString());
            } catch (SQLiteException e) {
                zzgi().zziv().zzg("Error incrementing retry count. error", e);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:60:0x011d  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0125  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzet zzf(java.lang.String r23, java.lang.String r24) {
        /*
            r22 = this;
            r15 = r24
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r23)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24)
            r22.zzab()
            r22.zzch()
            r16 = 0
            android.database.sqlite.SQLiteDatabase r1 = r22.getWritableDatabase()     // Catch:{ SQLiteException -> 0x00fc, all -> 0x00f7 }
            java.lang.String r2 = "events"
            r3 = 7
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x00fc, all -> 0x00f7 }
            java.lang.String r4 = "lifetime_count"
            r9 = 0
            r3[r9] = r4     // Catch:{ SQLiteException -> 0x00fc, all -> 0x00f7 }
            java.lang.String r4 = "current_bundle_count"
            r10 = 1
            r3[r10] = r4     // Catch:{ SQLiteException -> 0x00fc, all -> 0x00f7 }
            java.lang.String r4 = "last_fire_timestamp"
            r11 = 2
            r3[r11] = r4     // Catch:{ SQLiteException -> 0x00fc, all -> 0x00f7 }
            java.lang.String r4 = "last_bundled_timestamp"
            r12 = 3
            r3[r12] = r4     // Catch:{ SQLiteException -> 0x00fc, all -> 0x00f7 }
            java.lang.String r4 = "last_sampled_complex_event_id"
            r13 = 4
            r3[r13] = r4     // Catch:{ SQLiteException -> 0x00fc, all -> 0x00f7 }
            java.lang.String r4 = "last_sampling_rate"
            r14 = 5
            r3[r14] = r4     // Catch:{ SQLiteException -> 0x00fc, all -> 0x00f7 }
            java.lang.String r4 = "last_exempt_from_sampling"
            r8 = 6
            r3[r8] = r4     // Catch:{ SQLiteException -> 0x00fc, all -> 0x00f7 }
            java.lang.String r4 = "app_id=? and name=?"
            java.lang.String[] r5 = new java.lang.String[r11]     // Catch:{ SQLiteException -> 0x00fc, all -> 0x00f7 }
            r5[r9] = r23     // Catch:{ SQLiteException -> 0x00fc, all -> 0x00f7 }
            r5[r10] = r15     // Catch:{ SQLiteException -> 0x00fc, all -> 0x00f7 }
            r6 = 0
            r7 = 0
            r17 = 0
            r8 = r17
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x00fc, all -> 0x00f7 }
            boolean r1 = r8.moveToFirst()     // Catch:{ SQLiteException -> 0x00f3, all -> 0x00f0 }
            if (r1 != 0) goto L_0x005a
            if (r8 == 0) goto L_0x0059
            r8.close()
        L_0x0059:
            return r16
        L_0x005a:
            long r4 = r8.getLong(r9)     // Catch:{ SQLiteException -> 0x00f3, all -> 0x00f0 }
            long r6 = r8.getLong(r10)     // Catch:{ SQLiteException -> 0x00f3, all -> 0x00f0 }
            long r19 = r8.getLong(r11)     // Catch:{ SQLiteException -> 0x00f3, all -> 0x00f0 }
            boolean r1 = r8.isNull(r12)     // Catch:{ SQLiteException -> 0x00f3, all -> 0x00f0 }
            if (r1 == 0) goto L_0x0070
            r1 = 0
        L_0x006e:
            r11 = r1
            goto L_0x0075
        L_0x0070:
            long r1 = r8.getLong(r12)     // Catch:{ SQLiteException -> 0x00f3, all -> 0x00f0 }
            goto L_0x006e
        L_0x0075:
            boolean r1 = r8.isNull(r13)     // Catch:{ SQLiteException -> 0x00f3, all -> 0x00f0 }
            if (r1 == 0) goto L_0x007e
            r13 = r16
            goto L_0x0087
        L_0x007e:
            long r1 = r8.getLong(r13)     // Catch:{ SQLiteException -> 0x00f3, all -> 0x00f0 }
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ SQLiteException -> 0x00f3, all -> 0x00f0 }
            r13 = r1
        L_0x0087:
            boolean r1 = r8.isNull(r14)     // Catch:{ SQLiteException -> 0x00f3, all -> 0x00f0 }
            if (r1 == 0) goto L_0x0091
            r14 = r16
        L_0x008f:
            r1 = 6
            goto L_0x009b
        L_0x0091:
            long r1 = r8.getLong(r14)     // Catch:{ SQLiteException -> 0x00f3, all -> 0x00f0 }
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ SQLiteException -> 0x00f3, all -> 0x00f0 }
            r14 = r1
            goto L_0x008f
        L_0x009b:
            boolean r2 = r8.isNull(r1)     // Catch:{ SQLiteException -> 0x00f3, all -> 0x00f0 }
            if (r2 != 0) goto L_0x00bc
            long r1 = r8.getLong(r1)     // Catch:{ SQLiteException -> 0x00b8, all -> 0x00b3 }
            r17 = 1
            int r3 = (r1 > r17 ? 1 : (r1 == r17 ? 0 : -1))
            if (r3 != 0) goto L_0x00ac
            r9 = 1
        L_0x00ac:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r9)     // Catch:{ SQLiteException -> 0x00b8, all -> 0x00b3 }
            r17 = r1
            goto L_0x00be
        L_0x00b3:
            r0 = move-exception
            r1 = r0
            r15 = r8
            goto L_0x0123
        L_0x00b8:
            r0 = move-exception
            r1 = r0
            r15 = r8
            goto L_0x0100
        L_0x00bc:
            r17 = r16
        L_0x00be:
            com.google.android.gms.internal.measurement.zzet r18 = new com.google.android.gms.internal.measurement.zzet     // Catch:{ SQLiteException -> 0x00f3, all -> 0x00f0 }
            r1 = r18
            r2 = r23
            r3 = r15
            r10 = r8
            r8 = r19
            r15 = r10
            r10 = r11
            r12 = r13
            r13 = r14
            r14 = r17
            r1.<init>(r2, r3, r4, r6, r8, r10, r12, r13, r14)     // Catch:{ SQLiteException -> 0x00ee }
            boolean r1 = r15.moveToNext()     // Catch:{ SQLiteException -> 0x00ee }
            if (r1 == 0) goto L_0x00e8
            com.google.android.gms.internal.measurement.zzfi r1 = r22.zzgi()     // Catch:{ SQLiteException -> 0x00ee }
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zziv()     // Catch:{ SQLiteException -> 0x00ee }
            java.lang.String r2 = "Got multiple records for event aggregates, expected one. appId"
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzfi.zzbp(r23)     // Catch:{ SQLiteException -> 0x00ee }
            r1.zzg(r2, r3)     // Catch:{ SQLiteException -> 0x00ee }
        L_0x00e8:
            if (r15 == 0) goto L_0x00ed
            r15.close()
        L_0x00ed:
            return r18
        L_0x00ee:
            r0 = move-exception
            goto L_0x00f5
        L_0x00f0:
            r0 = move-exception
            r15 = r8
            goto L_0x0122
        L_0x00f3:
            r0 = move-exception
            r15 = r8
        L_0x00f5:
            r1 = r0
            goto L_0x0100
        L_0x00f7:
            r0 = move-exception
            r1 = r0
            r15 = r16
            goto L_0x0123
        L_0x00fc:
            r0 = move-exception
            r1 = r0
            r15 = r16
        L_0x0100:
            com.google.android.gms.internal.measurement.zzfi r2 = r22.zzgi()     // Catch:{ all -> 0x0121 }
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zziv()     // Catch:{ all -> 0x0121 }
            java.lang.String r3 = "Error querying events. appId"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfi.zzbp(r23)     // Catch:{ all -> 0x0121 }
            com.google.android.gms.internal.measurement.zzfg r5 = r22.zzgf()     // Catch:{ all -> 0x0121 }
            r6 = r24
            java.lang.String r5 = r5.zzbm(r6)     // Catch:{ all -> 0x0121 }
            r2.zzd(r3, r4, r5, r1)     // Catch:{ all -> 0x0121 }
            if (r15 == 0) goto L_0x0120
            r15.close()
        L_0x0120:
            return r16
        L_0x0121:
            r0 = move-exception
        L_0x0122:
            r1 = r0
        L_0x0123:
            if (r15 == 0) goto L_0x0128
            r15.close()
        L_0x0128:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzf(java.lang.String, java.lang.String):com.google.android.gms.internal.measurement.zzet");
    }

    public final void zzg(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzab();
        zzch();
        try {
            zzgi().zzjc().zzg("Deleted user attribute rows", Integer.valueOf(getWritableDatabase().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2})));
        } catch (SQLiteException e) {
            zzgi().zziv().zzd("Error deleting user attribute. appId", zzfi.zzbp(str), zzgf().zzbo(str2), e);
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zzgn() {
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzkc zzh(java.lang.String r20, java.lang.String r21) {
        /*
            r19 = this;
            r8 = r21
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r20)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r21)
            r19.zzab()
            r19.zzch()
            r9 = 0
            android.database.sqlite.SQLiteDatabase r10 = r19.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0089, all -> 0x0083 }
            java.lang.String r11 = "user_attributes"
            r1 = 3
            java.lang.String[] r12 = new java.lang.String[r1]     // Catch:{ SQLiteException -> 0x0089, all -> 0x0083 }
            java.lang.String r1 = "set_timestamp"
            r2 = 0
            r12[r2] = r1     // Catch:{ SQLiteException -> 0x0089, all -> 0x0083 }
            java.lang.String r1 = "value"
            r3 = 1
            r12[r3] = r1     // Catch:{ SQLiteException -> 0x0089, all -> 0x0083 }
            java.lang.String r1 = "origin"
            r4 = 2
            r12[r4] = r1     // Catch:{ SQLiteException -> 0x0089, all -> 0x0083 }
            java.lang.String r13 = "app_id=? and name=?"
            java.lang.String[] r14 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x0089, all -> 0x0083 }
            r14[r2] = r20     // Catch:{ SQLiteException -> 0x0089, all -> 0x0083 }
            r14[r3] = r8     // Catch:{ SQLiteException -> 0x0089, all -> 0x0083 }
            r15 = 0
            r16 = 0
            r17 = 0
            android.database.Cursor r10 = r10.query(r11, r12, r13, r14, r15, r16, r17)     // Catch:{ SQLiteException -> 0x0089, all -> 0x0083 }
            boolean r1 = r10.moveToFirst()     // Catch:{ SQLiteException -> 0x007e, all -> 0x007a }
            if (r1 != 0) goto L_0x0044
            if (r10 == 0) goto L_0x0043
            r10.close()
        L_0x0043:
            return r9
        L_0x0044:
            long r5 = r10.getLong(r2)     // Catch:{ SQLiteException -> 0x007e, all -> 0x007a }
            r11 = r19
            java.lang.Object r7 = r11.zza(r10, r3)     // Catch:{ SQLiteException -> 0x0078 }
            java.lang.String r3 = r10.getString(r4)     // Catch:{ SQLiteException -> 0x0078 }
            com.google.android.gms.internal.measurement.zzkc r12 = new com.google.android.gms.internal.measurement.zzkc     // Catch:{ SQLiteException -> 0x0078 }
            r1 = r12
            r2 = r20
            r4 = r8
            r1.<init>(r2, r3, r4, r5, r7)     // Catch:{ SQLiteException -> 0x0078 }
            boolean r1 = r10.moveToNext()     // Catch:{ SQLiteException -> 0x0078 }
            if (r1 == 0) goto L_0x0072
            com.google.android.gms.internal.measurement.zzfi r1 = r19.zzgi()     // Catch:{ SQLiteException -> 0x0078 }
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zziv()     // Catch:{ SQLiteException -> 0x0078 }
            java.lang.String r2 = "Got multiple records for user property, expected one. appId"
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzfi.zzbp(r20)     // Catch:{ SQLiteException -> 0x0078 }
            r1.zzg(r2, r3)     // Catch:{ SQLiteException -> 0x0078 }
        L_0x0072:
            if (r10 == 0) goto L_0x0077
            r10.close()
        L_0x0077:
            return r12
        L_0x0078:
            r0 = move-exception
            goto L_0x0081
        L_0x007a:
            r0 = move-exception
            r11 = r19
            goto L_0x00ae
        L_0x007e:
            r0 = move-exception
            r11 = r19
        L_0x0081:
            r1 = r0
            goto L_0x008e
        L_0x0083:
            r0 = move-exception
            r11 = r19
            r1 = r0
            r10 = r9
            goto L_0x00af
        L_0x0089:
            r0 = move-exception
            r11 = r19
            r1 = r0
            r10 = r9
        L_0x008e:
            com.google.android.gms.internal.measurement.zzfi r2 = r19.zzgi()     // Catch:{ all -> 0x00ad }
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zziv()     // Catch:{ all -> 0x00ad }
            java.lang.String r3 = "Error querying user property. appId"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfi.zzbp(r20)     // Catch:{ all -> 0x00ad }
            com.google.android.gms.internal.measurement.zzfg r5 = r19.zzgf()     // Catch:{ all -> 0x00ad }
            java.lang.String r5 = r5.zzbo(r8)     // Catch:{ all -> 0x00ad }
            r2.zzd(r3, r4, r5, r1)     // Catch:{ all -> 0x00ad }
            if (r10 == 0) goto L_0x00ac
            r10.close()
        L_0x00ac:
            return r9
        L_0x00ad:
            r0 = move-exception
        L_0x00ae:
            r1 = r0
        L_0x00af:
            if (r10 == 0) goto L_0x00b4
            r10.close()
        L_0x00b4:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzh(java.lang.String, java.lang.String):com.google.android.gms.internal.measurement.zzkc");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0041  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzhv() {
        /*
            r6 = this;
            android.database.sqlite.SQLiteDatabase r0 = r6.getWritableDatabase()
            r1 = 0
            java.lang.String r2 = "select app_id from queue order by has_realtime desc, rowid asc limit 1;"
            android.database.Cursor r0 = r0.rawQuery(r2, r1)     // Catch:{ SQLiteException -> 0x0029, all -> 0x0024 }
            boolean r2 = r0.moveToFirst()     // Catch:{ SQLiteException -> 0x0022 }
            if (r2 == 0) goto L_0x001c
            r2 = 0
            java.lang.String r2 = r0.getString(r2)     // Catch:{ SQLiteException -> 0x0022 }
            if (r0 == 0) goto L_0x001b
            r0.close()
        L_0x001b:
            return r2
        L_0x001c:
            if (r0 == 0) goto L_0x0021
            r0.close()
        L_0x0021:
            return r1
        L_0x0022:
            r2 = move-exception
            goto L_0x002b
        L_0x0024:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x003f
        L_0x0029:
            r2 = move-exception
            r0 = r1
        L_0x002b:
            com.google.android.gms.internal.measurement.zzfi r3 = r6.zzgi()     // Catch:{ all -> 0x003e }
            com.google.android.gms.internal.measurement.zzfk r3 = r3.zziv()     // Catch:{ all -> 0x003e }
            java.lang.String r4 = "Database error getting next bundle app id"
            r3.zzg(r4, r2)     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x003d
            r0.close()
        L_0x003d:
            return r1
        L_0x003e:
            r1 = move-exception
        L_0x003f:
            if (r0 == 0) goto L_0x0044
            r0.close()
        L_0x0044:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzhv():java.lang.String");
    }

    public final boolean zzhw() {
        return zza("select count(1) > 0 from queue where has_realtime = 1", (String[]) null) != 0;
    }

    /* access modifiers changed from: 0000 */
    public final void zzhx() {
        zzab();
        zzch();
        if (zzid()) {
            long j = zzgj().zzalw.get();
            long elapsedRealtime = zzbt().elapsedRealtime();
            if (Math.abs(elapsedRealtime - j) > ((Long) zzez.zzajc.get()).longValue()) {
                zzgj().zzalw.set(elapsedRealtime);
                zzab();
                zzch();
                if (zzid()) {
                    int delete = getWritableDatabase().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(zzbt().currentTimeMillis()), String.valueOf(zzeh.zzhq())});
                    if (delete > 0) {
                        zzgi().zzjc().zzg("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
                    }
                }
            }
        }
    }

    public final long zzhy() {
        return zza("select max(bundle_end_timestamp) from queue", (String[]) null, 0);
    }

    public final long zzhz() {
        return zza("select max(timestamp) from raw_events", (String[]) null, 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x014e  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0156  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzef zzi(java.lang.String r34, java.lang.String r35) {
        /*
            r33 = this;
            r7 = r35
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r34)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r35)
            r33.zzab()
            r33.zzch()
            r8 = 0
            android.database.sqlite.SQLiteDatabase r9 = r33.getWritableDatabase()     // Catch:{ SQLiteException -> 0x012e, all -> 0x0128 }
            java.lang.String r10 = "conditional_properties"
            r1 = 11
            java.lang.String[] r11 = new java.lang.String[r1]     // Catch:{ SQLiteException -> 0x012e, all -> 0x0128 }
            java.lang.String r1 = "origin"
            r2 = 0
            r11[r2] = r1     // Catch:{ SQLiteException -> 0x012e, all -> 0x0128 }
            java.lang.String r1 = "value"
            r3 = 1
            r11[r3] = r1     // Catch:{ SQLiteException -> 0x012e, all -> 0x0128 }
            java.lang.String r1 = "active"
            r4 = 2
            r11[r4] = r1     // Catch:{ SQLiteException -> 0x012e, all -> 0x0128 }
            java.lang.String r1 = "trigger_event_name"
            r5 = 3
            r11[r5] = r1     // Catch:{ SQLiteException -> 0x012e, all -> 0x0128 }
            java.lang.String r1 = "trigger_timeout"
            r6 = 4
            r11[r6] = r1     // Catch:{ SQLiteException -> 0x012e, all -> 0x0128 }
            java.lang.String r1 = "timed_out_event"
            r15 = 5
            r11[r15] = r1     // Catch:{ SQLiteException -> 0x012e, all -> 0x0128 }
            java.lang.String r1 = "creation_timestamp"
            r14 = 6
            r11[r14] = r1     // Catch:{ SQLiteException -> 0x012e, all -> 0x0128 }
            java.lang.String r1 = "triggered_event"
            r13 = 7
            r11[r13] = r1     // Catch:{ SQLiteException -> 0x012e, all -> 0x0128 }
            java.lang.String r1 = "triggered_timestamp"
            r12 = 8
            r11[r12] = r1     // Catch:{ SQLiteException -> 0x012e, all -> 0x0128 }
            java.lang.String r1 = "time_to_live"
            r6 = 9
            r11[r6] = r1     // Catch:{ SQLiteException -> 0x012e, all -> 0x0128 }
            java.lang.String r1 = "expired_event"
            r6 = 10
            r11[r6] = r1     // Catch:{ SQLiteException -> 0x012e, all -> 0x0128 }
            java.lang.String r1 = "app_id=? and name=?"
            java.lang.String[] r13 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x012e, all -> 0x0128 }
            r13[r2] = r34     // Catch:{ SQLiteException -> 0x012e, all -> 0x0128 }
            r13[r3] = r7     // Catch:{ SQLiteException -> 0x012e, all -> 0x0128 }
            r16 = 0
            r17 = 0
            r18 = 0
            r6 = 8
            r12 = r1
            r1 = 7
            r6 = 6
            r14 = r16
            r1 = 5
            r15 = r17
            r16 = r18
            android.database.Cursor r9 = r9.query(r10, r11, r12, r13, r14, r15, r16)     // Catch:{ SQLiteException -> 0x012e, all -> 0x0128 }
            boolean r10 = r9.moveToFirst()     // Catch:{ SQLiteException -> 0x0123, all -> 0x011f }
            if (r10 != 0) goto L_0x007d
            if (r9 == 0) goto L_0x007c
            r9.close()
        L_0x007c:
            return r8
        L_0x007d:
            java.lang.String r19 = r9.getString(r2)     // Catch:{ SQLiteException -> 0x0123, all -> 0x011f }
            r10 = r33
            java.lang.Object r11 = r10.zza(r9, r3)     // Catch:{ SQLiteException -> 0x011d }
            int r4 = r9.getInt(r4)     // Catch:{ SQLiteException -> 0x011d }
            if (r4 == 0) goto L_0x0090
            r23 = 1
            goto L_0x0092
        L_0x0090:
            r23 = 0
        L_0x0092:
            java.lang.String r24 = r9.getString(r5)     // Catch:{ SQLiteException -> 0x011d }
            r2 = 4
            long r26 = r9.getLong(r2)     // Catch:{ SQLiteException -> 0x011d }
            com.google.android.gms.internal.measurement.zzjz r2 = r33.zzjf()     // Catch:{ SQLiteException -> 0x011d }
            byte[] r1 = r9.getBlob(r1)     // Catch:{ SQLiteException -> 0x011d }
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzex> r3 = com.google.android.gms.internal.measurement.zzex.CREATOR     // Catch:{ SQLiteException -> 0x011d }
            android.os.Parcelable r1 = r2.zza(r1, r3)     // Catch:{ SQLiteException -> 0x011d }
            r25 = r1
            com.google.android.gms.internal.measurement.zzex r25 = (com.google.android.gms.internal.measurement.zzex) r25     // Catch:{ SQLiteException -> 0x011d }
            long r21 = r9.getLong(r6)     // Catch:{ SQLiteException -> 0x011d }
            com.google.android.gms.internal.measurement.zzjz r1 = r33.zzjf()     // Catch:{ SQLiteException -> 0x011d }
            r2 = 7
            byte[] r2 = r9.getBlob(r2)     // Catch:{ SQLiteException -> 0x011d }
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzex> r3 = com.google.android.gms.internal.measurement.zzex.CREATOR     // Catch:{ SQLiteException -> 0x011d }
            android.os.Parcelable r1 = r1.zza(r2, r3)     // Catch:{ SQLiteException -> 0x011d }
            r28 = r1
            com.google.android.gms.internal.measurement.zzex r28 = (com.google.android.gms.internal.measurement.zzex) r28     // Catch:{ SQLiteException -> 0x011d }
            r1 = 8
            long r3 = r9.getLong(r1)     // Catch:{ SQLiteException -> 0x011d }
            r1 = 9
            long r29 = r9.getLong(r1)     // Catch:{ SQLiteException -> 0x011d }
            com.google.android.gms.internal.measurement.zzjz r1 = r33.zzjf()     // Catch:{ SQLiteException -> 0x011d }
            r2 = 10
            byte[] r2 = r9.getBlob(r2)     // Catch:{ SQLiteException -> 0x011d }
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzex> r5 = com.google.android.gms.internal.measurement.zzex.CREATOR     // Catch:{ SQLiteException -> 0x011d }
            android.os.Parcelable r1 = r1.zza(r2, r5)     // Catch:{ SQLiteException -> 0x011d }
            r31 = r1
            com.google.android.gms.internal.measurement.zzex r31 = (com.google.android.gms.internal.measurement.zzex) r31     // Catch:{ SQLiteException -> 0x011d }
            com.google.android.gms.internal.measurement.zzka r20 = new com.google.android.gms.internal.measurement.zzka     // Catch:{ SQLiteException -> 0x011d }
            r1 = r20
            r2 = r7
            r5 = r11
            r6 = r19
            r1.<init>(r2, r3, r5, r6)     // Catch:{ SQLiteException -> 0x011d }
            com.google.android.gms.internal.measurement.zzef r1 = new com.google.android.gms.internal.measurement.zzef     // Catch:{ SQLiteException -> 0x011d }
            r17 = r1
            r18 = r34
            r17.<init>(r18, r19, r20, r21, r23, r24, r25, r26, r28, r29, r31)     // Catch:{ SQLiteException -> 0x011d }
            boolean r2 = r9.moveToNext()     // Catch:{ SQLiteException -> 0x011d }
            if (r2 == 0) goto L_0x0117
            com.google.android.gms.internal.measurement.zzfi r2 = r33.zzgi()     // Catch:{ SQLiteException -> 0x011d }
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zziv()     // Catch:{ SQLiteException -> 0x011d }
            java.lang.String r3 = "Got multiple records for conditional property, expected one"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfi.zzbp(r34)     // Catch:{ SQLiteException -> 0x011d }
            com.google.android.gms.internal.measurement.zzfg r5 = r33.zzgf()     // Catch:{ SQLiteException -> 0x011d }
            java.lang.String r5 = r5.zzbo(r7)     // Catch:{ SQLiteException -> 0x011d }
            r2.zze(r3, r4, r5)     // Catch:{ SQLiteException -> 0x011d }
        L_0x0117:
            if (r9 == 0) goto L_0x011c
            r9.close()
        L_0x011c:
            return r1
        L_0x011d:
            r0 = move-exception
            goto L_0x0126
        L_0x011f:
            r0 = move-exception
            r10 = r33
            goto L_0x0153
        L_0x0123:
            r0 = move-exception
            r10 = r33
        L_0x0126:
            r1 = r0
            goto L_0x0133
        L_0x0128:
            r0 = move-exception
            r10 = r33
            r1 = r0
            r9 = r8
            goto L_0x0154
        L_0x012e:
            r0 = move-exception
            r10 = r33
            r1 = r0
            r9 = r8
        L_0x0133:
            com.google.android.gms.internal.measurement.zzfi r2 = r33.zzgi()     // Catch:{ all -> 0x0152 }
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zziv()     // Catch:{ all -> 0x0152 }
            java.lang.String r3 = "Error querying conditional property"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfi.zzbp(r34)     // Catch:{ all -> 0x0152 }
            com.google.android.gms.internal.measurement.zzfg r5 = r33.zzgf()     // Catch:{ all -> 0x0152 }
            java.lang.String r5 = r5.zzbo(r7)     // Catch:{ all -> 0x0152 }
            r2.zzd(r3, r4, r5, r1)     // Catch:{ all -> 0x0152 }
            if (r9 == 0) goto L_0x0151
            r9.close()
        L_0x0151:
            return r8
        L_0x0152:
            r0 = move-exception
        L_0x0153:
            r1 = r0
        L_0x0154:
            if (r9 == 0) goto L_0x0159
            r9.close()
        L_0x0159:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzi(java.lang.String, java.lang.String):com.google.android.gms.internal.measurement.zzef");
    }

    public final boolean zzia() {
        return zza("select count(1) > 0 from raw_events", (String[]) null) != 0;
    }

    public final boolean zzib() {
        return zza("select count(1) > 0 from raw_events where realtime = 1", (String[]) null) != 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0044  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long zzic() {
        /*
            r7 = this;
            r0 = -1
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r7.getWritableDatabase()     // Catch:{ SQLiteException -> 0x002e }
            java.lang.String r4 = "select rowid from raw_events order by rowid desc limit 1;"
            android.database.Cursor r3 = r3.rawQuery(r4, r2)     // Catch:{ SQLiteException -> 0x002e }
            boolean r2 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x0027, all -> 0x0024 }
            if (r2 != 0) goto L_0x0019
            if (r3 == 0) goto L_0x0018
            r3.close()
        L_0x0018:
            return r0
        L_0x0019:
            r2 = 0
            long r4 = r3.getLong(r2)     // Catch:{ SQLiteException -> 0x0027, all -> 0x0024 }
            if (r3 == 0) goto L_0x0023
            r3.close()
        L_0x0023:
            return r4
        L_0x0024:
            r0 = move-exception
            r2 = r3
            goto L_0x0042
        L_0x0027:
            r2 = move-exception
            r6 = r3
            r3 = r2
            r2 = r6
            goto L_0x002f
        L_0x002c:
            r0 = move-exception
            goto L_0x0042
        L_0x002e:
            r3 = move-exception
        L_0x002f:
            com.google.android.gms.internal.measurement.zzfi r4 = r7.zzgi()     // Catch:{ all -> 0x002c }
            com.google.android.gms.internal.measurement.zzfk r4 = r4.zziv()     // Catch:{ all -> 0x002c }
            java.lang.String r5 = "Error querying raw events"
            r4.zzg(r5, r3)     // Catch:{ all -> 0x002c }
            if (r2 == 0) goto L_0x0041
            r2.close()
        L_0x0041:
            return r0
        L_0x0042:
            if (r2 == 0) goto L_0x0047
            r2.close()
        L_0x0047:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzic():long");
    }

    public final int zzj(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzab();
        zzch();
        try {
            return getWritableDatabase().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzgi().zziv().zzd("Error deleting conditional property", zzfi.zzbp(str), zzgf().zzbo(str2), e);
            return 0;
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.zzkh>> zzk(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            r12.zzch()
            r12.zzab()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r13)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            android.support.v4.util.ArrayMap r0 = new android.support.v4.util.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r12.getWritableDatabase()
            r9 = 0
            java.lang.String r2 = "event_filters"
            r3 = 2
            java.lang.String[] r4 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x009c, all -> 0x0099 }
            java.lang.String r5 = "audience_id"
            r10 = 0
            r4[r10] = r5     // Catch:{ SQLiteException -> 0x009c, all -> 0x0099 }
            java.lang.String r5 = "data"
            r11 = 1
            r4[r11] = r5     // Catch:{ SQLiteException -> 0x009c, all -> 0x0099 }
            java.lang.String r5 = "app_id=? AND event_name=?"
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x009c, all -> 0x0099 }
            r6[r10] = r13     // Catch:{ SQLiteException -> 0x009c, all -> 0x0099 }
            r6[r11] = r14     // Catch:{ SQLiteException -> 0x009c, all -> 0x0099 }
            r14 = 0
            r7 = 0
            r8 = 0
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r14
            android.database.Cursor r14 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x009c, all -> 0x0099 }
            boolean r1 = r14.moveToFirst()     // Catch:{ SQLiteException -> 0x0097 }
            if (r1 != 0) goto L_0x0048
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x0097 }
            if (r14 == 0) goto L_0x0047
            r14.close()
        L_0x0047:
            return r0
        L_0x0048:
            byte[] r1 = r14.getBlob(r11)     // Catch:{ SQLiteException -> 0x0097 }
            int r2 = r1.length     // Catch:{ SQLiteException -> 0x0097 }
            com.google.android.gms.internal.measurement.zzaca r1 = com.google.android.gms.internal.measurement.zzaca.zza(r1, r10, r2)     // Catch:{ SQLiteException -> 0x0097 }
            com.google.android.gms.internal.measurement.zzkh r2 = new com.google.android.gms.internal.measurement.zzkh     // Catch:{ SQLiteException -> 0x0097 }
            r2.<init>()     // Catch:{ SQLiteException -> 0x0097 }
            r2.zzb(r1)     // Catch:{ IOException -> 0x0079 }
            int r1 = r14.getInt(r10)     // Catch:{ SQLiteException -> 0x0097 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch:{ SQLiteException -> 0x0097 }
            java.lang.Object r3 = r0.get(r3)     // Catch:{ SQLiteException -> 0x0097 }
            java.util.List r3 = (java.util.List) r3     // Catch:{ SQLiteException -> 0x0097 }
            if (r3 != 0) goto L_0x0075
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0097 }
            r3.<init>()     // Catch:{ SQLiteException -> 0x0097 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ SQLiteException -> 0x0097 }
            r0.put(r1, r3)     // Catch:{ SQLiteException -> 0x0097 }
        L_0x0075:
            r3.add(r2)     // Catch:{ SQLiteException -> 0x0097 }
            goto L_0x008b
        L_0x0079:
            r1 = move-exception
            com.google.android.gms.internal.measurement.zzfi r2 = r12.zzgi()     // Catch:{ SQLiteException -> 0x0097 }
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zziv()     // Catch:{ SQLiteException -> 0x0097 }
            java.lang.String r3 = "Failed to merge filter. appId"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfi.zzbp(r13)     // Catch:{ SQLiteException -> 0x0097 }
            r2.zze(r3, r4, r1)     // Catch:{ SQLiteException -> 0x0097 }
        L_0x008b:
            boolean r1 = r14.moveToNext()     // Catch:{ SQLiteException -> 0x0097 }
            if (r1 != 0) goto L_0x0048
            if (r14 == 0) goto L_0x0096
            r14.close()
        L_0x0096:
            return r0
        L_0x0097:
            r0 = move-exception
            goto L_0x009e
        L_0x0099:
            r13 = move-exception
            r14 = r9
            goto L_0x00b6
        L_0x009c:
            r0 = move-exception
            r14 = r9
        L_0x009e:
            com.google.android.gms.internal.measurement.zzfi r1 = r12.zzgi()     // Catch:{ all -> 0x00b5 }
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zziv()     // Catch:{ all -> 0x00b5 }
            java.lang.String r2 = "Database error querying filters. appId"
            java.lang.Object r13 = com.google.android.gms.internal.measurement.zzfi.zzbp(r13)     // Catch:{ all -> 0x00b5 }
            r1.zze(r2, r13, r0)     // Catch:{ all -> 0x00b5 }
            if (r14 == 0) goto L_0x00b4
            r14.close()
        L_0x00b4:
            return r9
        L_0x00b5:
            r13 = move-exception
        L_0x00b6:
            if (r14 == 0) goto L_0x00bb
            r14.close()
        L_0x00bb:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzk(java.lang.String, java.lang.String):java.util.Map");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.zzkk>> zzl(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            r12.zzch()
            r12.zzab()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r13)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            android.support.v4.util.ArrayMap r0 = new android.support.v4.util.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r12.getWritableDatabase()
            r9 = 0
            java.lang.String r2 = "property_filters"
            r3 = 2
            java.lang.String[] r4 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x009c, all -> 0x0099 }
            java.lang.String r5 = "audience_id"
            r10 = 0
            r4[r10] = r5     // Catch:{ SQLiteException -> 0x009c, all -> 0x0099 }
            java.lang.String r5 = "data"
            r11 = 1
            r4[r11] = r5     // Catch:{ SQLiteException -> 0x009c, all -> 0x0099 }
            java.lang.String r5 = "app_id=? AND property_name=?"
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x009c, all -> 0x0099 }
            r6[r10] = r13     // Catch:{ SQLiteException -> 0x009c, all -> 0x0099 }
            r6[r11] = r14     // Catch:{ SQLiteException -> 0x009c, all -> 0x0099 }
            r14 = 0
            r7 = 0
            r8 = 0
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r14
            android.database.Cursor r14 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x009c, all -> 0x0099 }
            boolean r1 = r14.moveToFirst()     // Catch:{ SQLiteException -> 0x0097 }
            if (r1 != 0) goto L_0x0048
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x0097 }
            if (r14 == 0) goto L_0x0047
            r14.close()
        L_0x0047:
            return r0
        L_0x0048:
            byte[] r1 = r14.getBlob(r11)     // Catch:{ SQLiteException -> 0x0097 }
            int r2 = r1.length     // Catch:{ SQLiteException -> 0x0097 }
            com.google.android.gms.internal.measurement.zzaca r1 = com.google.android.gms.internal.measurement.zzaca.zza(r1, r10, r2)     // Catch:{ SQLiteException -> 0x0097 }
            com.google.android.gms.internal.measurement.zzkk r2 = new com.google.android.gms.internal.measurement.zzkk     // Catch:{ SQLiteException -> 0x0097 }
            r2.<init>()     // Catch:{ SQLiteException -> 0x0097 }
            r2.zzb(r1)     // Catch:{ IOException -> 0x0079 }
            int r1 = r14.getInt(r10)     // Catch:{ SQLiteException -> 0x0097 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch:{ SQLiteException -> 0x0097 }
            java.lang.Object r3 = r0.get(r3)     // Catch:{ SQLiteException -> 0x0097 }
            java.util.List r3 = (java.util.List) r3     // Catch:{ SQLiteException -> 0x0097 }
            if (r3 != 0) goto L_0x0075
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0097 }
            r3.<init>()     // Catch:{ SQLiteException -> 0x0097 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ SQLiteException -> 0x0097 }
            r0.put(r1, r3)     // Catch:{ SQLiteException -> 0x0097 }
        L_0x0075:
            r3.add(r2)     // Catch:{ SQLiteException -> 0x0097 }
            goto L_0x008b
        L_0x0079:
            r1 = move-exception
            com.google.android.gms.internal.measurement.zzfi r2 = r12.zzgi()     // Catch:{ SQLiteException -> 0x0097 }
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zziv()     // Catch:{ SQLiteException -> 0x0097 }
            java.lang.String r3 = "Failed to merge filter"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfi.zzbp(r13)     // Catch:{ SQLiteException -> 0x0097 }
            r2.zze(r3, r4, r1)     // Catch:{ SQLiteException -> 0x0097 }
        L_0x008b:
            boolean r1 = r14.moveToNext()     // Catch:{ SQLiteException -> 0x0097 }
            if (r1 != 0) goto L_0x0048
            if (r14 == 0) goto L_0x0096
            r14.close()
        L_0x0096:
            return r0
        L_0x0097:
            r0 = move-exception
            goto L_0x009e
        L_0x0099:
            r13 = move-exception
            r14 = r9
            goto L_0x00b6
        L_0x009c:
            r0 = move-exception
            r14 = r9
        L_0x009e:
            com.google.android.gms.internal.measurement.zzfi r1 = r12.zzgi()     // Catch:{ all -> 0x00b5 }
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zziv()     // Catch:{ all -> 0x00b5 }
            java.lang.String r2 = "Database error querying filters. appId"
            java.lang.Object r13 = com.google.android.gms.internal.measurement.zzfi.zzbp(r13)     // Catch:{ all -> 0x00b5 }
            r1.zze(r2, r13, r0)     // Catch:{ all -> 0x00b5 }
            if (r14 == 0) goto L_0x00b4
            r14.close()
        L_0x00b4:
            return r9
        L_0x00b5:
            r13 = move-exception
        L_0x00b6:
            if (r14 == 0) goto L_0x00bb
            r14.close()
        L_0x00bb:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzl(java.lang.String, java.lang.String):java.util.Map");
    }

    /* access modifiers changed from: protected */
    public final long zzm(String str, String str2) {
        long j;
        String str3 = str;
        String str4 = str2;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzab();
        zzch();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 32);
            sb.append("select ");
            sb.append(str4);
            sb.append(" from app2 where app_id=?");
            try {
                j = zza(sb.toString(), new String[]{str3}, -1);
                if (j == -1) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("app_id", str3);
                    contentValues.put("first_open_count", Integer.valueOf(0));
                    contentValues.put("previous_install_count", Integer.valueOf(0));
                    if (writableDatabase.insertWithOnConflict("app2", null, contentValues, 5) == -1) {
                        zzgi().zziv().zze("Failed to insert column (got -1). appId", zzfi.zzbp(str), str4);
                        writableDatabase.endTransaction();
                        return -1;
                    }
                    j = 0;
                }
                try {
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("app_id", str3);
                    contentValues2.put(str4, Long.valueOf(j + 1));
                    if (((long) writableDatabase.update("app2", contentValues2, "app_id = ?", new String[]{str3})) == 0) {
                        zzgi().zziv().zze("Failed to update column (got 0). appId", zzfi.zzbp(str), str4);
                        writableDatabase.endTransaction();
                        return -1;
                    }
                    writableDatabase.setTransactionSuccessful();
                    writableDatabase.endTransaction();
                    return j;
                } catch (SQLiteException e) {
                    e = e;
                }
            } catch (SQLiteException e2) {
                e = e2;
                j = 0;
                try {
                    zzgi().zziv().zzd("Error inserting column. appId", zzfi.zzbp(str), str4, e);
                    writableDatabase.endTransaction();
                    return j;
                } catch (Throwable th) {
                    th = th;
                    Throwable th2 = th;
                    writableDatabase.endTransaction();
                    throw th2;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            j = 0;
            zzgi().zziv().zzd("Error inserting column. appId", zzfi.zzbp(str), str4, e);
            writableDatabase.endTransaction();
            return j;
        } catch (Throwable th3) {
            th = th3;
            Throwable th22 = th;
            writableDatabase.endTransaction();
            throw th22;
        }
    }
}
