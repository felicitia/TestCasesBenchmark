package com.google.android.gms.internal.measurement;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcelable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.messaging.CartRefreshDelegate;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class z extends ex {
    /* access modifiers changed from: private */
    public static final String[] b = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] c = {CartRefreshDelegate.ARG_ORIGIN, "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    /* access modifiers changed from: private */
    public static final String[] d = {"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", ResponseConstants.DAY, "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] e = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] f = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] g = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    private final ac h = new ac(this, n(), "google_app_measurement.db");
    /* access modifiers changed from: private */
    public final et i = new et(m());

    z(ey eyVar) {
        super(eyVar);
    }

    private final boolean K() {
        return n().getDatabasePath("google_app_measurement.db").exists();
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0039  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final long a(java.lang.String r3, java.lang.String[] r4, long r5) {
        /*
            r2 = this;
            android.database.sqlite.SQLiteDatabase r0 = r2.i()
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
            com.google.android.gms.internal.measurement.aq r4 = r2.r()     // Catch:{ all -> 0x0026 }
            com.google.android.gms.internal.measurement.as r4 = r4.h_()     // Catch:{ all -> 0x0026 }
            java.lang.String r6 = "Database error"
            r4.a(r6, r3, r5)     // Catch:{ all -> 0x0026 }
            throw r5     // Catch:{ all -> 0x0026 }
        L_0x0037:
            if (r1 == 0) goto L_0x003c
            r1.close()
        L_0x003c:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.z.a(java.lang.String, java.lang.String[], long):long");
    }

    @WorkerThread
    @VisibleForTesting
    private final Object a(Cursor cursor, int i2) {
        int type = cursor.getType(i2);
        switch (type) {
            case 0:
                r().h_().a("Loaded invalid null value from database");
                return null;
            case 1:
                return Long.valueOf(cursor.getLong(i2));
            case 2:
                return Double.valueOf(cursor.getDouble(i2));
            case 3:
                return cursor.getString(i2);
            case 4:
                r().h_().a("Loaded invalid blob type value, ignoring it");
                return null;
            default:
                r().h_().a("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
                return null;
        }
    }

    @WorkerThread
    private static void a(ContentValues contentValues, String str, Object obj) {
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

    @WorkerThread
    private final boolean a(String str, int i2, fk fkVar) {
        I();
        d();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(fkVar);
        if (TextUtils.isEmpty(fkVar.d)) {
            r().i().a("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", aq.a(str), Integer.valueOf(i2), String.valueOf(fkVar.c));
            return false;
        }
        try {
            byte[] bArr = new byte[fkVar.d()];
            d a = d.a(bArr, 0, bArr.length);
            fkVar.a(a);
            a.a();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i2));
            contentValues.put("filter_id", fkVar.c);
            contentValues.put(ResponseConstants.EVENT_NAME, fkVar.d);
            contentValues.put("data", bArr);
            try {
                if (i().insertWithOnConflict("event_filters", null, contentValues, 5) == -1) {
                    r().h_().a("Failed to insert event filter (got -1). appId", aq.a(str));
                }
                return true;
            } catch (SQLiteException e2) {
                r().h_().a("Error storing event filter. appId", aq.a(str), e2);
                return false;
            }
        } catch (IOException e3) {
            r().h_().a("Configuration loss. Failed to serialize event filter. appId", aq.a(str), e3);
            return false;
        }
    }

    @WorkerThread
    private final boolean a(String str, int i2, fn fnVar) {
        I();
        d();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(fnVar);
        if (TextUtils.isEmpty(fnVar.d)) {
            r().i().a("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", aq.a(str), Integer.valueOf(i2), String.valueOf(fnVar.c));
            return false;
        }
        try {
            byte[] bArr = new byte[fnVar.d()];
            d a = d.a(bArr, 0, bArr.length);
            fnVar.a(a);
            a.a();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i2));
            contentValues.put("filter_id", fnVar.c);
            contentValues.put(ResponseConstants.PROPERTY_NAME, fnVar.d);
            contentValues.put("data", bArr);
            try {
                if (i().insertWithOnConflict("property_filters", null, contentValues, 5) != -1) {
                    return true;
                }
                r().h_().a("Failed to insert property filter (got -1). appId", aq.a(str));
                return false;
            } catch (SQLiteException e2) {
                r().h_().a("Error storing property filter. appId", aq.a(str), e2);
                return false;
            }
        } catch (IOException e3) {
            r().h_().a("Configuration loss. Failed to serialize property filter. appId", aq.a(str), e3);
            return false;
        }
    }

    private final boolean a(String str, List<Integer> list) {
        Preconditions.checkNotEmpty(str);
        I();
        d();
        SQLiteDatabase i2 = i();
        try {
            long b2 = b("select count(1) from audience_filter_values where app_id=?", new String[]{str});
            int max = Math.max(0, Math.min(2000, t().b(str, ak.N)));
            if (b2 <= ((long) max)) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            for (int i3 = 0; i3 < list.size(); i3++) {
                Integer num = (Integer) list.get(i3);
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
            StringBuilder sb3 = new StringBuilder(140 + String.valueOf(sb2).length());
            sb3.append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ");
            sb3.append(sb2);
            sb3.append(" order by rowid desc limit -1 offset ?)");
            return i2.delete("audience_filter_values", sb3.toString(), new String[]{str, Integer.toString(max)}) > 0;
        } catch (SQLiteException e2) {
            r().h_().a("Database error querying filters. appId", aq.a(str), e2);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x003b  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final long b(java.lang.String r4, java.lang.String[] r5) {
        /*
            r3 = this;
            android.database.sqlite.SQLiteDatabase r0 = r3.i()
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
            com.google.android.gms.internal.measurement.aq r5 = r3.r()     // Catch:{ all -> 0x0027 }
            com.google.android.gms.internal.measurement.as r5 = r5.h_()     // Catch:{ all -> 0x0027 }
            java.lang.String r2 = "Database error"
            r5.a(r2, r4, r0)     // Catch:{ all -> 0x0027 }
            throw r0     // Catch:{ all -> 0x0027 }
        L_0x0039:
            if (r5 == 0) goto L_0x003e
            r5.close()
        L_0x003e:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.z.b(java.lang.String, java.lang.String[]):long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0044  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long A() {
        /*
            r7 = this;
            r0 = -1
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r7.i()     // Catch:{ SQLiteException -> 0x002e }
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
            com.google.android.gms.internal.measurement.aq r4 = r7.r()     // Catch:{ all -> 0x002c }
            com.google.android.gms.internal.measurement.as r4 = r4.h_()     // Catch:{ all -> 0x002c }
            java.lang.String r5 = "Error querying raw events"
            r4.a(r5, r3)     // Catch:{ all -> 0x002c }
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.z.A():long");
    }

    public final long a(fx fxVar) throws IOException {
        long j;
        d();
        I();
        Preconditions.checkNotNull(fxVar);
        Preconditions.checkNotEmpty(fxVar.q);
        try {
            byte[] bArr = new byte[fxVar.d()];
            d a = d.a(bArr, 0, bArr.length);
            fxVar.a(a);
            a.a();
            fe f_ = f_();
            Preconditions.checkNotNull(bArr);
            f_.p().d();
            MessageDigest i2 = fg.i();
            if (i2 == null) {
                f_.r().h_().a("Failed to get MD5");
                j = 0;
            } else {
                j = fg.a(i2.digest(bArr));
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", fxVar.q);
            contentValues.put("metadata_fingerprint", Long.valueOf(j));
            contentValues.put(ResponseConstants.METADATA, bArr);
            try {
                i().insertWithOnConflict("raw_events_metadata", null, contentValues, 4);
                return j;
            } catch (SQLiteException e2) {
                r().h_().a("Error storing raw event metadata. appId", aq.a(fxVar.q), e2);
                throw e2;
            }
        } catch (IOException e3) {
            r().h_().a("Data loss. Failed to serialize event metadata. appId", aq.a(fxVar.q), e3);
            throw e3;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x008f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.util.Pair<com.google.android.gms.internal.measurement.fu, java.lang.Long> a(java.lang.String r8, java.lang.Long r9) {
        /*
            r7 = this;
            r7.d()
            r7.I()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r7.i()     // Catch:{ SQLiteException -> 0x0077, all -> 0x0074 }
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
            com.google.android.gms.internal.measurement.aq r8 = r7.r()     // Catch:{ SQLiteException -> 0x0072 }
            com.google.android.gms.internal.measurement.as r8 = r8.w()     // Catch:{ SQLiteException -> 0x0072 }
            java.lang.String r9 = "Main event not found"
            r8.a(r9)     // Catch:{ SQLiteException -> 0x0072 }
            if (r1 == 0) goto L_0x0036
            r1.close()
        L_0x0036:
            return r0
        L_0x0037:
            byte[] r2 = r1.getBlob(r4)     // Catch:{ SQLiteException -> 0x0072 }
            long r5 = r1.getLong(r6)     // Catch:{ SQLiteException -> 0x0072 }
            java.lang.Long r3 = java.lang.Long.valueOf(r5)     // Catch:{ SQLiteException -> 0x0072 }
            int r5 = r2.length     // Catch:{ SQLiteException -> 0x0072 }
            com.google.android.gms.internal.measurement.c r2 = com.google.android.gms.internal.measurement.c.a(r2, r4, r5)     // Catch:{ SQLiteException -> 0x0072 }
            com.google.android.gms.internal.measurement.fu r4 = new com.google.android.gms.internal.measurement.fu     // Catch:{ SQLiteException -> 0x0072 }
            r4.<init>()     // Catch:{ SQLiteException -> 0x0072 }
            r4.a(r2)     // Catch:{ IOException -> 0x005a }
            android.util.Pair r8 = android.util.Pair.create(r4, r3)     // Catch:{ SQLiteException -> 0x0072 }
            if (r1 == 0) goto L_0x0059
            r1.close()
        L_0x0059:
            return r8
        L_0x005a:
            r2 = move-exception
            com.google.android.gms.internal.measurement.aq r3 = r7.r()     // Catch:{ SQLiteException -> 0x0072 }
            com.google.android.gms.internal.measurement.as r3 = r3.h_()     // Catch:{ SQLiteException -> 0x0072 }
            java.lang.String r4 = "Failed to merge main event. appId, eventId"
            java.lang.Object r8 = com.google.android.gms.internal.measurement.aq.a(r8)     // Catch:{ SQLiteException -> 0x0072 }
            r3.a(r4, r8, r9, r2)     // Catch:{ SQLiteException -> 0x0072 }
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
            com.google.android.gms.internal.measurement.aq r9 = r7.r()     // Catch:{ all -> 0x008c }
            com.google.android.gms.internal.measurement.as r9 = r9.h_()     // Catch:{ all -> 0x008c }
            java.lang.String r2 = "Error selecting main event"
            r9.a(r2, r8)     // Catch:{ all -> 0x008c }
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.z.a(java.lang.String, java.lang.Long):android.util.Pair");
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x013b  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0144  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.aa a(long r22, java.lang.String r24, boolean r25, boolean r26, boolean r27, boolean r28, boolean r29) {
        /*
            r21 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24)
            r21.d()
            r21.I()
            r2 = 1
            java.lang.String[] r3 = new java.lang.String[r2]
            r4 = 0
            r3[r4] = r24
            com.google.android.gms.internal.measurement.aa r5 = new com.google.android.gms.internal.measurement.aa
            r5.<init>()
            android.database.sqlite.SQLiteDatabase r15 = r21.i()     // Catch:{ SQLiteException -> 0x0125, all -> 0x0121 }
            java.lang.String r8 = "apps"
            r7 = 6
            java.lang.String[] r9 = new java.lang.String[r7]     // Catch:{ SQLiteException -> 0x0125, all -> 0x0121 }
            java.lang.String r7 = "day"
            r9[r4] = r7     // Catch:{ SQLiteException -> 0x0125, all -> 0x0121 }
            java.lang.String r7 = "daily_events_count"
            r9[r2] = r7     // Catch:{ SQLiteException -> 0x0125, all -> 0x0121 }
            java.lang.String r7 = "daily_public_events_count"
            r14 = 2
            r9[r14] = r7     // Catch:{ SQLiteException -> 0x0125, all -> 0x0121 }
            java.lang.String r7 = "daily_conversions_count"
            r13 = 3
            r9[r13] = r7     // Catch:{ SQLiteException -> 0x0125, all -> 0x0121 }
            java.lang.String r7 = "daily_error_events_count"
            r12 = 4
            r9[r12] = r7     // Catch:{ SQLiteException -> 0x0125, all -> 0x0121 }
            java.lang.String r7 = "daily_realtime_events_count"
            r11 = 5
            r9[r11] = r7     // Catch:{ SQLiteException -> 0x0125, all -> 0x0121 }
            java.lang.String r10 = "app_id=?"
            java.lang.String[] r7 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x0125, all -> 0x0121 }
            r7[r4] = r24     // Catch:{ SQLiteException -> 0x0125, all -> 0x0121 }
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = r7
            r7 = r15
            r6 = r11
            r11 = r19
            r6 = r12
            r12 = r16
            r6 = r13
            r13 = r17
            r6 = r14
            r14 = r18
            android.database.Cursor r7 = r7.query(r8, r9, r10, r11, r12, r13, r14)     // Catch:{ SQLiteException -> 0x0125, all -> 0x0121 }
            boolean r8 = r7.moveToFirst()     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            if (r8 != 0) goto L_0x0075
            com.google.android.gms.internal.measurement.aq r2 = r21.r()     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            com.google.android.gms.internal.measurement.as r2 = r2.i()     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            java.lang.String r3 = "Not updating daily counts, app is not known. appId"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.aq.a(r24)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r2.a(r3, r4)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            if (r7 == 0) goto L_0x0074
            r7.close()
        L_0x0074:
            return r5
        L_0x0075:
            long r8 = r7.getLong(r4)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            int r4 = (r8 > r22 ? 1 : (r8 == r22 ? 0 : -1))
            if (r4 != 0) goto L_0x009e
            long r8 = r7.getLong(r2)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r5.b = r8     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            long r8 = r7.getLong(r6)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r5.a = r8     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r2 = 3
            long r8 = r7.getLong(r2)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r5.c = r8     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r2 = 4
            long r8 = r7.getLong(r2)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r5.d = r8     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r2 = 5
            long r8 = r7.getLong(r2)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r5.e = r8     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
        L_0x009e:
            r8 = 1
            if (r25 == 0) goto L_0x00a8
            long r12 = r5.b     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            long r1 = r12 + r8
            r5.b = r1     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
        L_0x00a8:
            if (r26 == 0) goto L_0x00b0
            long r1 = r5.a     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            long r12 = r1 + r8
            r5.a = r12     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
        L_0x00b0:
            if (r27 == 0) goto L_0x00b8
            long r1 = r5.c     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            long r12 = r1 + r8
            r5.c = r12     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
        L_0x00b8:
            if (r28 == 0) goto L_0x00c0
            long r1 = r5.d     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            long r12 = r1 + r8
            r5.d = r12     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
        L_0x00c0:
            if (r29 == 0) goto L_0x00c8
            long r1 = r5.e     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            long r12 = r1 + r8
            r5.e = r12     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
        L_0x00c8:
            android.content.ContentValues r1 = new android.content.ContentValues     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r1.<init>()     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            java.lang.String r2 = "day"
            java.lang.Long r4 = java.lang.Long.valueOf(r22)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r1.put(r2, r4)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            java.lang.String r2 = "daily_public_events_count"
            long r8 = r5.a     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            java.lang.Long r4 = java.lang.Long.valueOf(r8)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r1.put(r2, r4)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            java.lang.String r2 = "daily_events_count"
            long r8 = r5.b     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            java.lang.Long r4 = java.lang.Long.valueOf(r8)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r1.put(r2, r4)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            java.lang.String r2 = "daily_conversions_count"
            long r8 = r5.c     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            java.lang.Long r4 = java.lang.Long.valueOf(r8)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r1.put(r2, r4)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            java.lang.String r2 = "daily_error_events_count"
            long r8 = r5.d     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            java.lang.Long r4 = java.lang.Long.valueOf(r8)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r1.put(r2, r4)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            java.lang.String r2 = "daily_realtime_events_count"
            long r8 = r5.e     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            java.lang.Long r4 = java.lang.Long.valueOf(r8)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r1.put(r2, r4)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            java.lang.String r2 = "apps"
            java.lang.String r4 = "app_id=?"
            r15.update(r2, r1, r4, r3)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            if (r7 == 0) goto L_0x0119
            r7.close()
        L_0x0119:
            return r5
        L_0x011a:
            r0 = move-exception
            r1 = r0
            goto L_0x0142
        L_0x011d:
            r0 = move-exception
            r1 = r0
            r6 = r7
            goto L_0x0128
        L_0x0121:
            r0 = move-exception
            r1 = r0
            r7 = 0
            goto L_0x0142
        L_0x0125:
            r0 = move-exception
            r1 = r0
            r6 = 0
        L_0x0128:
            com.google.android.gms.internal.measurement.aq r2 = r21.r()     // Catch:{ all -> 0x013f }
            com.google.android.gms.internal.measurement.as r2 = r2.h_()     // Catch:{ all -> 0x013f }
            java.lang.String r3 = "Error updating daily counts. appId"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.aq.a(r24)     // Catch:{ all -> 0x013f }
            r2.a(r3, r4, r1)     // Catch:{ all -> 0x013f }
            if (r6 == 0) goto L_0x013e
            r6.close()
        L_0x013e:
            return r5
        L_0x013f:
            r0 = move-exception
            r1 = r0
            r7 = r6
        L_0x0142:
            if (r7 == 0) goto L_0x0147
            r7.close()
        L_0x0147:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.z.a(long, java.lang.String, boolean, boolean, boolean, boolean, boolean):com.google.android.gms.internal.measurement.aa");
    }

    /* JADX WARNING: Removed duplicated region for block: B:60:0x011d  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0125  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.ai a(java.lang.String r23, java.lang.String r24) {
        /*
            r22 = this;
            r15 = r24
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r23)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24)
            r22.d()
            r22.I()
            r16 = 0
            android.database.sqlite.SQLiteDatabase r1 = r22.i()     // Catch:{ SQLiteException -> 0x00fc, all -> 0x00f7 }
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
            r9 = r10
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
            com.google.android.gms.internal.measurement.ai r18 = new com.google.android.gms.internal.measurement.ai     // Catch:{ SQLiteException -> 0x00f3, all -> 0x00f0 }
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
            com.google.android.gms.internal.measurement.aq r1 = r22.r()     // Catch:{ SQLiteException -> 0x00ee }
            com.google.android.gms.internal.measurement.as r1 = r1.h_()     // Catch:{ SQLiteException -> 0x00ee }
            java.lang.String r2 = "Got multiple records for event aggregates, expected one. appId"
            java.lang.Object r3 = com.google.android.gms.internal.measurement.aq.a(r23)     // Catch:{ SQLiteException -> 0x00ee }
            r1.a(r2, r3)     // Catch:{ SQLiteException -> 0x00ee }
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
            com.google.android.gms.internal.measurement.aq r2 = r22.r()     // Catch:{ all -> 0x0121 }
            com.google.android.gms.internal.measurement.as r2 = r2.h_()     // Catch:{ all -> 0x0121 }
            java.lang.String r3 = "Error querying events. appId"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.aq.a(r23)     // Catch:{ all -> 0x0121 }
            com.google.android.gms.internal.measurement.ao r5 = r22.o()     // Catch:{ all -> 0x0121 }
            r6 = r24
            java.lang.String r5 = r5.a(r6)     // Catch:{ all -> 0x0121 }
            r2.a(r3, r4, r5, r1)     // Catch:{ all -> 0x0121 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.z.a(java.lang.String, java.lang.String):com.google.android.gms.internal.measurement.ai");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String a(long r5) {
        /*
            r4 = this;
            r4.d()
            r4.I()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r4.i()     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            java.lang.String r2 = "select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            r6 = 0
            r3[r6] = r5     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            android.database.Cursor r5 = r1.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            boolean r1 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x003e }
            if (r1 != 0) goto L_0x0034
            com.google.android.gms.internal.measurement.aq r6 = r4.r()     // Catch:{ SQLiteException -> 0x003e }
            com.google.android.gms.internal.measurement.as r6 = r6.w()     // Catch:{ SQLiteException -> 0x003e }
            java.lang.String r1 = "No expired configs for apps with pending events"
            r6.a(r1)     // Catch:{ SQLiteException -> 0x003e }
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
            com.google.android.gms.internal.measurement.aq r1 = r4.r()     // Catch:{ all -> 0x0058 }
            com.google.android.gms.internal.measurement.as r1 = r1.h_()     // Catch:{ all -> 0x0058 }
            java.lang.String r2 = "Error selecting expired configs"
            r1.a(r2, r6)     // Catch:{ all -> 0x0058 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.z.a(long):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00bd  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.internal.measurement.ff> a(java.lang.String r25) {
        /*
            r24 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r25)
            r24.d()
            r24.I()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r24.i()     // Catch:{ SQLiteException -> 0x009d, all -> 0x0097 }
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
            java.lang.Object r22 = r4.a(r3, r15)     // Catch:{ SQLiteException -> 0x008c }
            if (r22 != 0) goto L_0x0074
            com.google.android.gms.internal.measurement.aq r5 = r24.r()     // Catch:{ SQLiteException -> 0x008c }
            com.google.android.gms.internal.measurement.as r5 = r5.h_()     // Catch:{ SQLiteException -> 0x008c }
            java.lang.String r6 = "Read invalid user property value, ignoring it. appId"
            java.lang.Object r7 = com.google.android.gms.internal.measurement.aq.a(r25)     // Catch:{ SQLiteException -> 0x008c }
            r5.a(r6, r7)     // Catch:{ SQLiteException -> 0x008c }
            goto L_0x0080
        L_0x0074:
            com.google.android.gms.internal.measurement.ff r5 = new com.google.android.gms.internal.measurement.ff     // Catch:{ SQLiteException -> 0x008c }
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
            com.google.android.gms.internal.measurement.aq r5 = r24.r()     // Catch:{ all -> 0x00b9 }
            com.google.android.gms.internal.measurement.as r5 = r5.h_()     // Catch:{ all -> 0x00b9 }
            java.lang.String r6 = "Error querying user properties. appId"
            java.lang.Object r7 = com.google.android.gms.internal.measurement.aq.a(r25)     // Catch:{ all -> 0x00b9 }
            r5.a(r6, r7, r1)     // Catch:{ all -> 0x00b9 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.z.a(java.lang.String):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:57:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00ff  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<android.util.Pair<com.google.android.gms.internal.measurement.fx, java.lang.Long>> a(java.lang.String r18, int r19, int r20) {
        /*
            r17 = this;
            r1 = r20
            r17.d()
            r17.I()
            r2 = 1
            r3 = 0
            if (r19 <= 0) goto L_0x000e
            r5 = r2
            goto L_0x000f
        L_0x000e:
            r5 = r3
        L_0x000f:
            com.google.android.gms.common.internal.Preconditions.checkArgument(r5)
            if (r1 <= 0) goto L_0x0016
            r5 = r2
            goto L_0x0017
        L_0x0016:
            r5 = r3
        L_0x0017:
            com.google.android.gms.common.internal.Preconditions.checkArgument(r5)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r18)
            r5 = 0
            android.database.sqlite.SQLiteDatabase r6 = r17.i()     // Catch:{ SQLiteException -> 0x00e0 }
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
            r6 = r3
        L_0x005c:
            long r7 = r4.getLong(r3)     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            byte[] r9 = r4.getBlob(r2)     // Catch:{ IOException -> 0x00b4 }
            com.google.android.gms.internal.measurement.fe r10 = r17.f_()     // Catch:{ IOException -> 0x00b4 }
            byte[] r9 = r10.a(r9)     // Catch:{ IOException -> 0x00b4 }
            boolean r10 = r5.isEmpty()     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            if (r10 != 0) goto L_0x0076
            int r10 = r9.length     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            int r10 = r10 + r6
            if (r10 > r1) goto L_0x00cf
        L_0x0076:
            int r10 = r9.length     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            com.google.android.gms.internal.measurement.c r10 = com.google.android.gms.internal.measurement.c.a(r9, r3, r10)     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            com.google.android.gms.internal.measurement.fx r11 = new com.google.android.gms.internal.measurement.fx     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            r11.<init>()     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            r11.a(r10)     // Catch:{ IOException -> 0x00a1 }
            boolean r10 = r4.isNull(r15)     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            if (r10 != 0) goto L_0x0093
            int r10 = r4.getInt(r15)     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            r11.J = r10     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
        L_0x0093:
            int r9 = r9.length     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            int r6 = r6 + r9
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            android.util.Pair r7 = android.util.Pair.create(r11, r7)     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            r5.add(r7)     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            goto L_0x00c7
        L_0x00a1:
            r0 = move-exception
            com.google.android.gms.internal.measurement.aq r7 = r17.r()     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            com.google.android.gms.internal.measurement.as r7 = r7.h_()     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            java.lang.String r8 = "Failed to merge queued bundle. appId"
            java.lang.Object r9 = com.google.android.gms.internal.measurement.aq.a(r18)     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            r7.a(r8, r9, r0)     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            goto L_0x00c7
        L_0x00b4:
            r0 = move-exception
            r7 = r0
            com.google.android.gms.internal.measurement.aq r8 = r17.r()     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            com.google.android.gms.internal.measurement.as r8 = r8.h_()     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            java.lang.String r9 = "Failed to unzip queued bundle. appId"
            java.lang.Object r10 = com.google.android.gms.internal.measurement.aq.a(r18)     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
            r8.a(r9, r10, r7)     // Catch:{ SQLiteException -> 0x00d8, all -> 0x00d5 }
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
            com.google.android.gms.internal.measurement.aq r2 = r17.r()     // Catch:{ all -> 0x00dc }
            com.google.android.gms.internal.measurement.as r2 = r2.h_()     // Catch:{ all -> 0x00dc }
            java.lang.String r3 = "Error querying bundles. appId"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.aq.a(r18)     // Catch:{ all -> 0x00dc }
            r2.a(r3, r4, r1)     // Catch:{ all -> 0x00dc }
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.z.a(java.lang.String, int, int):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0032, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0033, code lost:
        r15 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0129, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x012a, code lost:
        r15 = r23;
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x012e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x012f, code lost:
        r15 = r23;
        r12 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0153, code lost:
        r2.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0129 A[ExcHandler: all (r0v5 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:1:0x000f] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x014a  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0153  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.internal.measurement.ff> a(java.lang.String r24, java.lang.String r25, java.lang.String r26) {
        /*
            r23 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24)
            r23.d()
            r23.I()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x012e, all -> 0x0129 }
            r4 = 3
            r3.<init>(r4)     // Catch:{ SQLiteException -> 0x012e, all -> 0x0129 }
            r12 = r24
            r3.add(r12)     // Catch:{ SQLiteException -> 0x0125, all -> 0x0129 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0125, all -> 0x0129 }
            java.lang.String r6 = "app_id=?"
            r5.<init>(r6)     // Catch:{ SQLiteException -> 0x0125, all -> 0x0129 }
            boolean r6 = android.text.TextUtils.isEmpty(r25)     // Catch:{ SQLiteException -> 0x0125, all -> 0x0129 }
            if (r6 != 0) goto L_0x0037
            r6 = r25
            r3.add(r6)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0129 }
            java.lang.String r7 = " and origin=?"
            r5.append(r7)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0129 }
            goto L_0x0039
        L_0x0032:
            r0 = move-exception
            r15 = r23
            goto L_0x0135
        L_0x0037:
            r6 = r25
        L_0x0039:
            boolean r7 = android.text.TextUtils.isEmpty(r26)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0129 }
            if (r7 != 0) goto L_0x0051
            java.lang.String r7 = java.lang.String.valueOf(r26)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0129 }
            java.lang.String r8 = "*"
            java.lang.String r7 = r7.concat(r8)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0129 }
            r3.add(r7)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0129 }
            java.lang.String r7 = " and name glob ?"
            r5.append(r7)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0129 }
        L_0x0051:
            int r7 = r3.size()     // Catch:{ SQLiteException -> 0x0032, all -> 0x0129 }
            java.lang.String[] r7 = new java.lang.String[r7]     // Catch:{ SQLiteException -> 0x0032, all -> 0x0129 }
            java.lang.Object[] r3 = r3.toArray(r7)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0129 }
            r17 = r3
            java.lang.String[] r17 = (java.lang.String[]) r17     // Catch:{ SQLiteException -> 0x0032, all -> 0x0129 }
            android.database.sqlite.SQLiteDatabase r13 = r23.i()     // Catch:{ SQLiteException -> 0x0032, all -> 0x0129 }
            java.lang.String r14 = "user_attributes"
            r3 = 4
            java.lang.String[] r15 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x0032, all -> 0x0129 }
            java.lang.String r3 = "name"
            r11 = 0
            r15[r11] = r3     // Catch:{ SQLiteException -> 0x0032, all -> 0x0129 }
            java.lang.String r3 = "set_timestamp"
            r9 = 1
            r15[r9] = r3     // Catch:{ SQLiteException -> 0x0032, all -> 0x0129 }
            java.lang.String r3 = "value"
            r10 = 2
            r15[r10] = r3     // Catch:{ SQLiteException -> 0x0032, all -> 0x0129 }
            java.lang.String r3 = "origin"
            r15[r4] = r3     // Catch:{ SQLiteException -> 0x0032, all -> 0x0129 }
            java.lang.String r16 = r5.toString()     // Catch:{ SQLiteException -> 0x0032, all -> 0x0129 }
            r18 = 0
            r19 = 0
            java.lang.String r20 = "rowid"
            java.lang.String r21 = "1001"
            android.database.Cursor r3 = r13.query(r14, r15, r16, r17, r18, r19, r20, r21)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0129 }
            boolean r5 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x0120, all -> 0x011c }
            if (r5 != 0) goto L_0x0097
            if (r3 == 0) goto L_0x0096
            r3.close()
        L_0x0096:
            return r1
        L_0x0097:
            int r5 = r1.size()     // Catch:{ SQLiteException -> 0x0120, all -> 0x011c }
            r7 = 1000(0x3e8, float:1.401E-42)
            if (r5 < r7) goto L_0x00b3
            com.google.android.gms.internal.measurement.aq r4 = r23.r()     // Catch:{ SQLiteException -> 0x0120, all -> 0x011c }
            com.google.android.gms.internal.measurement.as r4 = r4.h_()     // Catch:{ SQLiteException -> 0x0120, all -> 0x011c }
            java.lang.String r5 = "Read more than the max allowed user properties, ignoring excess"
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ SQLiteException -> 0x0120, all -> 0x011c }
            r4.a(r5, r7)     // Catch:{ SQLiteException -> 0x0120, all -> 0x011c }
            r15 = r23
            goto L_0x0102
        L_0x00b3:
            java.lang.String r8 = r3.getString(r11)     // Catch:{ SQLiteException -> 0x0120, all -> 0x011c }
            long r13 = r3.getLong(r9)     // Catch:{ SQLiteException -> 0x0120, all -> 0x011c }
            r15 = r23
            java.lang.Object r16 = r15.a(r3, r10)     // Catch:{ SQLiteException -> 0x011a }
            java.lang.String r7 = r3.getString(r4)     // Catch:{ SQLiteException -> 0x011a }
            if (r16 != 0) goto L_0x00e7
            com.google.android.gms.internal.measurement.aq r5 = r23.r()     // Catch:{ SQLiteException -> 0x00e2 }
            com.google.android.gms.internal.measurement.as r5 = r5.h_()     // Catch:{ SQLiteException -> 0x00e2 }
            java.lang.String r6 = "(2)Read invalid user property value, ignoring it"
            java.lang.Object r8 = com.google.android.gms.internal.measurement.aq.a(r24)     // Catch:{ SQLiteException -> 0x00e2 }
            r13 = r26
            r5.a(r6, r8, r7, r13)     // Catch:{ SQLiteException -> 0x00e2 }
            r17 = r7
            r18 = r9
            r19 = r10
            r13 = r11
            goto L_0x00fc
        L_0x00e2:
            r0 = move-exception
            r1 = r0
            r6 = r7
            goto L_0x0137
        L_0x00e7:
            com.google.android.gms.internal.measurement.ff r6 = new com.google.android.gms.internal.measurement.ff     // Catch:{ SQLiteException -> 0x0113 }
            r5 = r6
            r4 = r6
            r6 = r12
            r17 = r7
            r18 = r9
            r19 = r10
            r9 = r13
            r13 = r11
            r11 = r16
            r5.<init>(r6, r7, r8, r9, r11)     // Catch:{ SQLiteException -> 0x0111 }
            r1.add(r4)     // Catch:{ SQLiteException -> 0x0111 }
        L_0x00fc:
            boolean r4 = r3.moveToNext()     // Catch:{ SQLiteException -> 0x0111 }
            if (r4 != 0) goto L_0x0108
        L_0x0102:
            if (r3 == 0) goto L_0x0107
            r3.close()
        L_0x0107:
            return r1
        L_0x0108:
            r11 = r13
            r6 = r17
            r9 = r18
            r10 = r19
            r4 = 3
            goto L_0x0097
        L_0x0111:
            r0 = move-exception
            goto L_0x0116
        L_0x0113:
            r0 = move-exception
            r17 = r7
        L_0x0116:
            r1 = r0
            r6 = r17
            goto L_0x0137
        L_0x011a:
            r0 = move-exception
            goto L_0x0123
        L_0x011c:
            r0 = move-exception
            r15 = r23
            goto L_0x014f
        L_0x0120:
            r0 = move-exception
            r15 = r23
        L_0x0123:
            r1 = r0
            goto L_0x0137
        L_0x0125:
            r0 = move-exception
            r15 = r23
            goto L_0x0133
        L_0x0129:
            r0 = move-exception
            r15 = r23
            r1 = r0
            goto L_0x0151
        L_0x012e:
            r0 = move-exception
            r15 = r23
            r12 = r24
        L_0x0133:
            r6 = r25
        L_0x0135:
            r1 = r0
            r3 = r2
        L_0x0137:
            com.google.android.gms.internal.measurement.aq r4 = r23.r()     // Catch:{ all -> 0x014e }
            com.google.android.gms.internal.measurement.as r4 = r4.h_()     // Catch:{ all -> 0x014e }
            java.lang.String r5 = "(2)Error querying user properties"
            java.lang.Object r7 = com.google.android.gms.internal.measurement.aq.a(r24)     // Catch:{ all -> 0x014e }
            r4.a(r5, r7, r6, r1)     // Catch:{ all -> 0x014e }
            if (r3 == 0) goto L_0x014d
            r3.close()
        L_0x014d:
            return r2
        L_0x014e:
            r0 = move-exception
        L_0x014f:
            r1 = r0
            r2 = r3
        L_0x0151:
            if (r2 == 0) goto L_0x0156
            r2.close()
        L_0x0156:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.z.a(java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x016d  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0176  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.internal.measurement.zzef> a(java.lang.String r39, java.lang.String[] r40) {
        /*
            r38 = this;
            r38.d()
            r38.I()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            android.database.sqlite.SQLiteDatabase r3 = r38.i()     // Catch:{ SQLiteException -> 0x0157, all -> 0x0153 }
            java.lang.String r4 = "conditional_properties"
            r5 = 13
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x0157, all -> 0x0153 }
            java.lang.String r6 = "app_id"
            r12 = 0
            r5[r12] = r6     // Catch:{ SQLiteException -> 0x0157, all -> 0x0153 }
            java.lang.String r6 = "origin"
            r13 = 1
            r5[r13] = r6     // Catch:{ SQLiteException -> 0x0157, all -> 0x0153 }
            java.lang.String r6 = "name"
            r14 = 2
            r5[r14] = r6     // Catch:{ SQLiteException -> 0x0157, all -> 0x0153 }
            java.lang.String r6 = "value"
            r15 = 3
            r5[r15] = r6     // Catch:{ SQLiteException -> 0x0157, all -> 0x0153 }
            java.lang.String r6 = "active"
            r11 = 4
            r5[r11] = r6     // Catch:{ SQLiteException -> 0x0157, all -> 0x0153 }
            java.lang.String r6 = "trigger_event_name"
            r10 = 5
            r5[r10] = r6     // Catch:{ SQLiteException -> 0x0157, all -> 0x0153 }
            java.lang.String r6 = "trigger_timeout"
            r9 = 6
            r5[r9] = r6     // Catch:{ SQLiteException -> 0x0157, all -> 0x0153 }
            java.lang.String r6 = "timed_out_event"
            r8 = 7
            r5[r8] = r6     // Catch:{ SQLiteException -> 0x0157, all -> 0x0153 }
            java.lang.String r6 = "creation_timestamp"
            r7 = 8
            r5[r7] = r6     // Catch:{ SQLiteException -> 0x0157, all -> 0x0153 }
            java.lang.String r6 = "triggered_event"
            r2 = 9
            r5[r2] = r6     // Catch:{ SQLiteException -> 0x0157, all -> 0x0153 }
            java.lang.String r6 = "triggered_timestamp"
            r2 = 10
            r5[r2] = r6     // Catch:{ SQLiteException -> 0x0157, all -> 0x0153 }
            java.lang.String r6 = "time_to_live"
            r2 = 11
            r5[r2] = r6     // Catch:{ SQLiteException -> 0x0157, all -> 0x0153 }
            java.lang.String r6 = "expired_event"
            r2 = 12
            r5[r2] = r6     // Catch:{ SQLiteException -> 0x0157, all -> 0x0153 }
            r20 = 0
            r21 = 0
            java.lang.String r22 = "rowid"
            java.lang.String r23 = "1001"
            r6 = r39
            r2 = r7
            r7 = r40
            r2 = r8
            r8 = r20
            r2 = r9
            r9 = r21
            r2 = r10
            r10 = r22
            r2 = r11
            r11 = r23
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0153 }
            boolean r4 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            if (r4 != 0) goto L_0x0084
            if (r3 == 0) goto L_0x0083
            r3.close()
        L_0x0083:
            return r1
        L_0x0084:
            int r4 = r1.size()     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            r5 = 1000(0x3e8, float:1.401E-42)
            if (r4 < r5) goto L_0x009f
            com.google.android.gms.internal.measurement.aq r2 = r38.r()     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            com.google.android.gms.internal.measurement.as r2 = r2.h_()     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            java.lang.String r4 = "Read more than the max allowed conditional properties, ignoring extra"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            r2.a(r4, r5)     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            goto L_0x0142
        L_0x009f:
            java.lang.String r4 = r3.getString(r12)     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            java.lang.String r11 = r3.getString(r13)     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            java.lang.String r6 = r3.getString(r14)     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            r10 = r38
            java.lang.Object r9 = r10.a(r3, r15)     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            int r5 = r3.getInt(r2)     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            if (r5 == 0) goto L_0x00bb
            r22 = r13
        L_0x00b9:
            r7 = 5
            goto L_0x00be
        L_0x00bb:
            r22 = r12
            goto L_0x00b9
        L_0x00be:
            java.lang.String r23 = r3.getString(r7)     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            r8 = 6
            long r27 = r3.getLong(r8)     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            com.google.android.gms.internal.measurement.fe r5 = r38.f_()     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            r2 = 7
            byte[] r7 = r3.getBlob(r2)     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzex> r2 = com.google.android.gms.internal.measurement.zzex.CREATOR     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            android.os.Parcelable r2 = r5.a(r7, r2)     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            com.google.android.gms.internal.measurement.zzex r2 = (com.google.android.gms.internal.measurement.zzex) r2     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            r7 = 8
            long r20 = r3.getLong(r7)     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            com.google.android.gms.internal.measurement.fe r5 = r38.f_()     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            r12 = 9
            byte[] r7 = r3.getBlob(r12)     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzex> r8 = com.google.android.gms.internal.measurement.zzex.CREATOR     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            android.os.Parcelable r5 = r5.a(r7, r8)     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            r29 = r5
            com.google.android.gms.internal.measurement.zzex r29 = (com.google.android.gms.internal.measurement.zzex) r29     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            r7 = 10
            long r16 = r3.getLong(r7)     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            r8 = 11
            long r34 = r3.getLong(r8)     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            com.google.android.gms.internal.measurement.fe r5 = r38.f_()     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            r12 = 12
            byte[] r7 = r3.getBlob(r12)     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzex> r8 = com.google.android.gms.internal.measurement.zzex.CREATOR     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            android.os.Parcelable r5 = r5.a(r7, r8)     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            r30 = r5
            com.google.android.gms.internal.measurement.zzex r30 = (com.google.android.gms.internal.measurement.zzex) r30     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            com.google.android.gms.internal.measurement.zzka r19 = new com.google.android.gms.internal.measurement.zzka     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            r5 = r19
            r31 = 5
            r32 = 6
            r33 = 8
            r36 = 10
            r37 = 11
            r7 = r16
            r10 = r11
            r5.<init>(r6, r7, r9, r10)     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            com.google.android.gms.internal.measurement.zzef r5 = new com.google.android.gms.internal.measurement.zzef     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            r16 = r5
            r17 = r4
            r18 = r11
            r24 = r2
            r25 = r27
            r27 = r29
            r28 = r34
            r16.<init>(r17, r18, r19, r20, r22, r23, r24, r25, r27, r28, r30)     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            r1.add(r5)     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            boolean r2 = r3.moveToNext()     // Catch:{ SQLiteException -> 0x014f, all -> 0x014c }
            if (r2 != 0) goto L_0x0148
        L_0x0142:
            if (r3 == 0) goto L_0x0147
            r3.close()
        L_0x0147:
            return r1
        L_0x0148:
            r2 = 4
            r12 = 0
            goto L_0x0084
        L_0x014c:
            r0 = move-exception
            r1 = r0
            goto L_0x0174
        L_0x014f:
            r0 = move-exception
            r1 = r0
            r2 = r3
            goto L_0x015a
        L_0x0153:
            r0 = move-exception
            r1 = r0
            r3 = 0
            goto L_0x0174
        L_0x0157:
            r0 = move-exception
            r1 = r0
            r2 = 0
        L_0x015a:
            com.google.android.gms.internal.measurement.aq r3 = r38.r()     // Catch:{ all -> 0x0171 }
            com.google.android.gms.internal.measurement.as r3 = r3.h_()     // Catch:{ all -> 0x0171 }
            java.lang.String r4 = "Error querying conditional user property value"
            r3.a(r4, r1)     // Catch:{ all -> 0x0171 }
            java.util.List r1 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0171 }
            if (r2 == 0) goto L_0x0170
            r2.close()
        L_0x0170:
            return r1
        L_0x0171:
            r0 = move-exception
            r1 = r0
            r3 = r2
        L_0x0174:
            if (r3 == 0) goto L_0x0179
            r3.close()
        L_0x0179:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.z.a(java.lang.String, java.lang.String[]):java.util.List");
    }

    @WorkerThread
    public final void a(ai aiVar) {
        Preconditions.checkNotNull(aiVar);
        d();
        I();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", aiVar.a);
        contentValues.put(ResponseConstants.NAME, aiVar.b);
        contentValues.put("lifetime_count", Long.valueOf(aiVar.c));
        contentValues.put("current_bundle_count", Long.valueOf(aiVar.d));
        contentValues.put("last_fire_timestamp", Long.valueOf(aiVar.e));
        contentValues.put("last_bundled_timestamp", Long.valueOf(aiVar.f));
        contentValues.put("last_sampled_complex_event_id", aiVar.g);
        contentValues.put("last_sampling_rate", aiVar.h);
        contentValues.put("last_exempt_from_sampling", (aiVar.i == null || !aiVar.i.booleanValue()) ? null : Long.valueOf(1));
        try {
            if (i().insertWithOnConflict("events", null, contentValues, 5) == -1) {
                r().h_().a("Failed to insert/update event aggregates (got -1). appId", aq.a(aiVar.a));
            }
        } catch (SQLiteException e2) {
            r().h_().a("Error storing event aggregates. appId", aq.a(aiVar.a), e2);
        }
    }

    @WorkerThread
    public final void a(t tVar) {
        Preconditions.checkNotNull(tVar);
        d();
        I();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", tVar.b());
        contentValues.put("app_instance_id", tVar.c());
        contentValues.put("gmp_app_id", tVar.d());
        contentValues.put("resettable_device_id_hash", tVar.e());
        contentValues.put("last_bundle_index", Long.valueOf(tVar.o()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(tVar.g()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(tVar.h()));
        contentValues.put("app_version", tVar.i());
        contentValues.put("app_store", tVar.k());
        contentValues.put("gmp_version", Long.valueOf(tVar.l()));
        contentValues.put("dev_cert_hash", Long.valueOf(tVar.m()));
        contentValues.put("measurement_enabled", Boolean.valueOf(tVar.n()));
        contentValues.put(ResponseConstants.DAY, Long.valueOf(tVar.s()));
        contentValues.put("daily_public_events_count", Long.valueOf(tVar.t()));
        contentValues.put("daily_events_count", Long.valueOf(tVar.u()));
        contentValues.put("daily_conversions_count", Long.valueOf(tVar.v()));
        contentValues.put("config_fetched_time", Long.valueOf(tVar.p()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(tVar.q()));
        contentValues.put("app_version_int", Long.valueOf(tVar.j()));
        contentValues.put("firebase_instance_id", tVar.f());
        contentValues.put("daily_error_events_count", Long.valueOf(tVar.x()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(tVar.w()));
        contentValues.put("health_monitor_sample", tVar.y());
        contentValues.put("android_id", Long.valueOf(tVar.A()));
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(tVar.B()));
        contentValues.put("ssaid_reporting_enabled", Boolean.valueOf(tVar.C()));
        try {
            SQLiteDatabase i2 = i();
            if (((long) i2.update("apps", contentValues, "app_id = ?", new String[]{tVar.b()})) == 0 && i2.insertWithOnConflict("apps", null, contentValues, 5) == -1) {
                r().h_().a("Failed to insert/update app (got -1). appId", aq.a(tVar.b()));
            }
        } catch (SQLiteException e2) {
            r().h_().a("Error storing app. appId", aq.a(tVar.b()), e2);
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void a(String str, fj[] fjVarArr) {
        boolean z;
        as i2;
        String str2;
        Object a;
        Integer num;
        I();
        d();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(fjVarArr);
        SQLiteDatabase i3 = i();
        i3.beginTransaction();
        try {
            I();
            d();
            Preconditions.checkNotEmpty(str);
            SQLiteDatabase i4 = i();
            i4.delete("property_filters", "app_id=?", new String[]{str});
            i4.delete("event_filters", "app_id=?", new String[]{str});
            for (fj fjVar : fjVarArr) {
                I();
                d();
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotNull(fjVar);
                Preconditions.checkNotNull(fjVar.e);
                Preconditions.checkNotNull(fjVar.d);
                if (fjVar.c != null) {
                    int intValue = fjVar.c.intValue();
                    fk[] fkVarArr = fjVar.e;
                    int length = fkVarArr.length;
                    int i5 = 0;
                    while (true) {
                        if (i5 >= length) {
                            fn[] fnVarArr = fjVar.d;
                            int length2 = fnVarArr.length;
                            int i6 = 0;
                            while (i6 < length2) {
                                if (fnVarArr[i6].c == null) {
                                    i2 = r().i();
                                    str2 = "Property filter with no ID. Audience definition ignored. appId, audienceId";
                                    a = aq.a(str);
                                    num = fjVar.c;
                                } else {
                                    i6++;
                                }
                            }
                            fk[] fkVarArr2 = fjVar.e;
                            int length3 = fkVarArr2.length;
                            int i7 = 0;
                            while (true) {
                                if (i7 >= length3) {
                                    z = true;
                                    break;
                                } else if (!a(str, intValue, fkVarArr2[i7])) {
                                    z = false;
                                    break;
                                } else {
                                    i7++;
                                }
                            }
                            if (z) {
                                fn[] fnVarArr2 = fjVar.d;
                                int length4 = fnVarArr2.length;
                                int i8 = 0;
                                while (true) {
                                    if (i8 >= length4) {
                                        break;
                                    } else if (!a(str, intValue, fnVarArr2[i8])) {
                                        z = false;
                                        break;
                                    } else {
                                        i8++;
                                    }
                                }
                            }
                            if (!z) {
                                I();
                                d();
                                Preconditions.checkNotEmpty(str);
                                SQLiteDatabase i9 = i();
                                i9.delete("property_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(intValue)});
                                i9.delete("event_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(intValue)});
                            }
                        } else if (fkVarArr[i5].c == null) {
                            i2 = r().i();
                            str2 = "Event filter with no ID. Audience definition ignored. appId, audienceId";
                            a = aq.a(str);
                            num = fjVar.c;
                            break;
                        } else {
                            i5++;
                        }
                    }
                    i2.a(str2, a, num);
                    break;
                } else {
                    r().i().a("Audience with no ID. appId", aq.a(str));
                }
            }
            ArrayList arrayList = new ArrayList();
            for (fj fjVar2 : fjVarArr) {
                arrayList.add(fjVar2.c);
            }
            a(str, (List<Integer>) arrayList);
            i3.setTransactionSuccessful();
        } finally {
            i3.endTransaction();
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    @VisibleForTesting
    public final void a(List<Long> list) {
        d();
        I();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (K()) {
            String join = TextUtils.join(",", list);
            StringBuilder sb = new StringBuilder(2 + String.valueOf(join).length());
            sb.append("(");
            sb.append(join);
            sb.append(")");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder(80 + String.valueOf(sb2).length());
            sb3.append("SELECT COUNT(1) FROM queue WHERE rowid IN ");
            sb3.append(sb2);
            sb3.append(" AND retry_count =  2147483647 LIMIT 1");
            if (b(sb3.toString(), (String[]) null) > 0) {
                r().i().a("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                SQLiteDatabase i2 = i();
                StringBuilder sb4 = new StringBuilder(127 + String.valueOf(sb2).length());
                sb4.append("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN ");
                sb4.append(sb2);
                sb4.append(" AND (retry_count IS NULL OR retry_count < 2147483647)");
                i2.execSQL(sb4.toString());
            } catch (SQLiteException e2) {
                r().h_().a("Error incrementing retry count. error", e2);
            }
        }
    }

    public final boolean a(ah ahVar, long j, boolean z) {
        as h_;
        String str;
        d();
        I();
        Preconditions.checkNotNull(ahVar);
        Preconditions.checkNotEmpty(ahVar.a);
        fu fuVar = new fu();
        fuVar.f = Long.valueOf(ahVar.d);
        fuVar.c = new fv[ahVar.e.size()];
        Iterator it = ahVar.e.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            String str2 = (String) it.next();
            fv fvVar = new fv();
            int i3 = i2 + 1;
            fuVar.c[i2] = fvVar;
            fvVar.c = str2;
            f_().a(fvVar, ahVar.e.get(str2));
            i2 = i3;
        }
        try {
            byte[] bArr = new byte[fuVar.d()];
            d a = d.a(bArr, 0, bArr.length);
            fuVar.a(a);
            a.a();
            r().w().a("Saving event, name, data size", o().a(ahVar.b), Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", ahVar.a);
            contentValues.put(ResponseConstants.NAME, ahVar.b);
            contentValues.put("timestamp", Long.valueOf(ahVar.c));
            contentValues.put("metadata_fingerprint", Long.valueOf(j));
            contentValues.put("data", bArr);
            contentValues.put("realtime", Integer.valueOf(z ? 1 : 0));
            try {
                if (i().insert("raw_events", null, contentValues) != -1) {
                    return true;
                }
                r().h_().a("Failed to insert raw event (got -1). appId", aq.a(ahVar.a));
                return false;
            } catch (SQLiteException e2) {
                e = e2;
                h_ = r().h_();
                str = "Error storing raw event. appId";
                h_.a(str, aq.a(ahVar.a), e);
                return false;
            }
        } catch (IOException e3) {
            e = e3;
            h_ = r().h_();
            str = "Data loss. Failed to serialize event params/data. appId";
            h_.a(str, aq.a(ahVar.a), e);
            return false;
        }
    }

    @WorkerThread
    public final boolean a(ff ffVar) {
        Preconditions.checkNotNull(ffVar);
        d();
        I();
        if (c(ffVar.a, ffVar.c) == null) {
            if (fg.a(ffVar.c)) {
                if (b("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{ffVar.a}) >= 25) {
                    return false;
                }
            } else {
                if (b("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{ffVar.a, ffVar.b}) >= 25) {
                    return false;
                }
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", ffVar.a);
        contentValues.put(CartRefreshDelegate.ARG_ORIGIN, ffVar.b);
        contentValues.put(ResponseConstants.NAME, ffVar.c);
        contentValues.put("set_timestamp", Long.valueOf(ffVar.d));
        a(contentValues, ResponseConstants.VALUE, ffVar.e);
        try {
            if (i().insertWithOnConflict("user_attributes", null, contentValues, 5) == -1) {
                r().h_().a("Failed to insert/update user property (got -1). appId", aq.a(ffVar.a));
                return true;
            }
        } catch (SQLiteException e2) {
            r().h_().a("Error storing user property. appId", aq.a(ffVar.a), e2);
        }
        return true;
    }

    @WorkerThread
    public final boolean a(fx fxVar, boolean z) {
        as h_;
        String str;
        d();
        I();
        Preconditions.checkNotNull(fxVar);
        Preconditions.checkNotEmpty(fxVar.q);
        Preconditions.checkNotNull(fxVar.h);
        v();
        long currentTimeMillis = m().currentTimeMillis();
        if (fxVar.h.longValue() < currentTimeMillis - w.j() || fxVar.h.longValue() > currentTimeMillis + w.j()) {
            r().i().a("Storing bundle outside of the max uploading time span. appId, now, timestamp", aq.a(fxVar.q), Long.valueOf(currentTimeMillis), fxVar.h);
        }
        try {
            byte[] bArr = new byte[fxVar.d()];
            d a = d.a(bArr, 0, bArr.length);
            fxVar.a(a);
            a.a();
            byte[] b2 = f_().b(bArr);
            r().w().a("Saving bundle, size", Integer.valueOf(b2.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", fxVar.q);
            contentValues.put("bundle_end_timestamp", fxVar.h);
            contentValues.put("data", b2);
            contentValues.put("has_realtime", Integer.valueOf(z ? 1 : 0));
            if (fxVar.J != null) {
                contentValues.put("retry_count", fxVar.J);
            }
            try {
                if (i().insert("queue", null, contentValues) != -1) {
                    return true;
                }
                r().h_().a("Failed to insert bundle (got -1). appId", aq.a(fxVar.q));
                return false;
            } catch (SQLiteException e2) {
                e = e2;
                h_ = r().h_();
                str = "Error storing bundle. appId";
                h_.a(str, aq.a(fxVar.q), e);
                return false;
            }
        } catch (IOException e3) {
            e = e3;
            h_ = r().h_();
            str = "Data loss. Failed to serialize bundle. appId";
            h_.a(str, aq.a(fxVar.q), e);
            return false;
        }
    }

    @WorkerThread
    public final boolean a(zzef zzef) {
        Preconditions.checkNotNull(zzef);
        d();
        I();
        if (c(zzef.packageName, zzef.zzage.name) == null) {
            if (b("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{zzef.packageName}) >= 1000) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzef.packageName);
        contentValues.put(CartRefreshDelegate.ARG_ORIGIN, zzef.origin);
        contentValues.put(ResponseConstants.NAME, zzef.zzage.name);
        a(contentValues, ResponseConstants.VALUE, zzef.zzage.getValue());
        contentValues.put("active", Boolean.valueOf(zzef.active));
        contentValues.put("trigger_event_name", zzef.triggerEventName);
        contentValues.put("trigger_timeout", Long.valueOf(zzef.triggerTimeout));
        p();
        contentValues.put("timed_out_event", fg.a((Parcelable) zzef.zzagf));
        contentValues.put("creation_timestamp", Long.valueOf(zzef.creationTimestamp));
        p();
        contentValues.put("triggered_event", fg.a((Parcelable) zzef.zzagg));
        contentValues.put("triggered_timestamp", Long.valueOf(zzef.zzage.zzast));
        contentValues.put("time_to_live", Long.valueOf(zzef.timeToLive));
        p();
        contentValues.put("expired_event", fg.a((Parcelable) zzef.zzagh));
        try {
            if (i().insertWithOnConflict("conditional_properties", null, contentValues, 5) == -1) {
                r().h_().a("Failed to insert/update conditional user property (got -1)", aq.a(zzef.packageName));
                return true;
            }
        } catch (SQLiteException e2) {
            r().h_().a("Error storing conditional user property", aq.a(zzef.packageName), e2);
        }
        return true;
    }

    public final boolean a(String str, Long l, long j, fu fuVar) {
        d();
        I();
        Preconditions.checkNotNull(fuVar);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l);
        try {
            byte[] bArr = new byte[fuVar.d()];
            d a = d.a(bArr, 0, bArr.length);
            fuVar.a(a);
            a.a();
            r().w().a("Saving complex main event, appId, data size", o().a(str), Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("event_id", l);
            contentValues.put("children_to_process", Long.valueOf(j));
            contentValues.put("main_event", bArr);
            try {
                if (i().insertWithOnConflict("main_event_params", null, contentValues, 5) != -1) {
                    return true;
                }
                r().h_().a("Failed to insert complex main event (got -1). appId", aq.a(str));
                return false;
            } catch (SQLiteException e2) {
                r().h_().a("Error storing complex main event. appId", aq.a(str), e2);
                return false;
            }
        } catch (IOException e3) {
            r().h_().a("Data loss. Failed to serialize event params/data. appId, eventId", aq.a(str), l, e3);
            return false;
        }
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
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.t b(java.lang.String r21) {
        /*
            r20 = this;
            r1 = r21
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r21)
            r20.d()
            r20.I()
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r20.i()     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
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
            r15 = r8
            r8 = r16
            r15 = r9
            r9 = r17
            r15 = r10
            r10 = r18
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x0217, all -> 0x0211 }
            boolean r4 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x020b, all -> 0x0206 }
            if (r4 != 0) goto L_0x00cb
            if (r3 == 0) goto L_0x00ca
            r3.close()
        L_0x00ca:
            return r2
        L_0x00cb:
            com.google.android.gms.internal.measurement.t r4 = new com.google.android.gms.internal.measurement.t     // Catch:{ SQLiteException -> 0x020b, all -> 0x0206 }
            r5 = r20
            com.google.android.gms.internal.measurement.ey r6 = r5.a     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            com.google.android.gms.internal.measurement.bu r6 = r6.o()     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.<init>(r6, r1)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            java.lang.String r6 = r3.getString(r11)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.a(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            java.lang.String r6 = r3.getString(r12)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.b(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            java.lang.String r6 = r3.getString(r13)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.c(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            long r6 = r3.getLong(r14)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.f(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 4
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.a(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            long r6 = r3.getLong(r15)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.b(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 6
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.e(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 7
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.f(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 8
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.d(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 9
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.e(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 10
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            if (r7 != 0) goto L_0x0136
            int r6 = r3.getInt(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            if (r6 == 0) goto L_0x0134
            goto L_0x0136
        L_0x0134:
            r6 = r11
            goto L_0x0137
        L_0x0136:
            r6 = r12
        L_0x0137:
            r4.a(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 11
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.i(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 12
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.j(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 13
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.k(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 14
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.l(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 15
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.g(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 16
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.h(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 17
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            if (r7 == 0) goto L_0x017c
            r6 = -2147483648(0xffffffff80000000, double:NaN)
            goto L_0x0181
        L_0x017c:
            int r6 = r3.getInt(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            long r6 = (long) r6     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
        L_0x0181:
            r4.c(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 18
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.d(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 19
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.n(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 20
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.m(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 21
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.g(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 22
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            if (r7 == 0) goto L_0x01b3
            r6 = 0
            goto L_0x01b7
        L_0x01b3:
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
        L_0x01b7:
            r4.o(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 23
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            if (r7 != 0) goto L_0x01cb
            int r6 = r3.getInt(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            if (r6 == 0) goto L_0x01c9
            goto L_0x01cb
        L_0x01c9:
            r6 = r11
            goto L_0x01cc
        L_0x01cb:
            r6 = r12
        L_0x01cc:
            r4.b(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6 = 24
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            if (r7 != 0) goto L_0x01df
            int r6 = r3.getInt(r6)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            if (r6 == 0) goto L_0x01de
            goto L_0x01df
        L_0x01de:
            r12 = r11
        L_0x01df:
            r4.c(r12)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r4.a()     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            boolean r6 = r3.moveToNext()     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            if (r6 == 0) goto L_0x01fc
            com.google.android.gms.internal.measurement.aq r6 = r20.r()     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            com.google.android.gms.internal.measurement.as r6 = r6.h_()     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            java.lang.String r7 = "Got multiple records for app, expected one. appId"
            java.lang.Object r8 = com.google.android.gms.internal.measurement.aq.a(r21)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
            r6.a(r7, r8)     // Catch:{ SQLiteException -> 0x0204, all -> 0x0202 }
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
            com.google.android.gms.internal.measurement.aq r6 = r20.r()     // Catch:{ all -> 0x0233 }
            com.google.android.gms.internal.measurement.as r6 = r6.h_()     // Catch:{ all -> 0x0233 }
            java.lang.String r7 = "Error querying app. appId"
            java.lang.Object r1 = com.google.android.gms.internal.measurement.aq.a(r21)     // Catch:{ all -> 0x0233 }
            r6.a(r7, r1, r3)     // Catch:{ all -> 0x0233 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.z.b(java.lang.String):com.google.android.gms.internal.measurement.t");
    }

    @WorkerThread
    public final List<zzef> b(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        d();
        I();
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
        return a(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    @WorkerThread
    public final void b(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        d();
        I();
        try {
            r().w().a("Deleted user attribute rows", Integer.valueOf(i().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2})));
        } catch (SQLiteException e2) {
            r().h_().a("Error deleting user attribute. appId", aq.a(str), o().c(str2), e2);
        }
    }

    public final long c(String str) {
        Preconditions.checkNotEmpty(str);
        d();
        I();
        try {
            return (long) i().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str, String.valueOf(Math.max(0, Math.min(1000000, t().b(str, ak.x))))});
        } catch (SQLiteException e2) {
            r().h_().a("Error deleting over the limit events. appId", aq.a(str), e2);
            return 0;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b1  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.ff c(java.lang.String r20, java.lang.String r21) {
        /*
            r19 = this;
            r8 = r21
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r20)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r21)
            r19.d()
            r19.I()
            r9 = 0
            android.database.sqlite.SQLiteDatabase r10 = r19.i()     // Catch:{ SQLiteException -> 0x0089, all -> 0x0083 }
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
            java.lang.Object r7 = r11.a(r10, r3)     // Catch:{ SQLiteException -> 0x0078 }
            java.lang.String r3 = r10.getString(r4)     // Catch:{ SQLiteException -> 0x0078 }
            com.google.android.gms.internal.measurement.ff r12 = new com.google.android.gms.internal.measurement.ff     // Catch:{ SQLiteException -> 0x0078 }
            r1 = r12
            r2 = r20
            r4 = r8
            r1.<init>(r2, r3, r4, r5, r7)     // Catch:{ SQLiteException -> 0x0078 }
            boolean r1 = r10.moveToNext()     // Catch:{ SQLiteException -> 0x0078 }
            if (r1 == 0) goto L_0x0072
            com.google.android.gms.internal.measurement.aq r1 = r19.r()     // Catch:{ SQLiteException -> 0x0078 }
            com.google.android.gms.internal.measurement.as r1 = r1.h_()     // Catch:{ SQLiteException -> 0x0078 }
            java.lang.String r2 = "Got multiple records for user property, expected one. appId"
            java.lang.Object r3 = com.google.android.gms.internal.measurement.aq.a(r20)     // Catch:{ SQLiteException -> 0x0078 }
            r1.a(r2, r3)     // Catch:{ SQLiteException -> 0x0078 }
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
            com.google.android.gms.internal.measurement.aq r2 = r19.r()     // Catch:{ all -> 0x00ad }
            com.google.android.gms.internal.measurement.as r2 = r2.h_()     // Catch:{ all -> 0x00ad }
            java.lang.String r3 = "Error querying user property. appId"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.aq.a(r20)     // Catch:{ all -> 0x00ad }
            com.google.android.gms.internal.measurement.ao r5 = r19.o()     // Catch:{ all -> 0x00ad }
            java.lang.String r5 = r5.c(r8)     // Catch:{ all -> 0x00ad }
            r2.a(r3, r4, r5, r1)     // Catch:{ all -> 0x00ad }
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.z.c(java.lang.String, java.lang.String):com.google.android.gms.internal.measurement.ff");
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x014d  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0155  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzef d(java.lang.String r34, java.lang.String r35) {
        /*
            r33 = this;
            r7 = r35
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r34)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r35)
            r33.d()
            r33.I()
            r8 = 0
            android.database.sqlite.SQLiteDatabase r9 = r33.i()     // Catch:{ SQLiteException -> 0x012d, all -> 0x0127 }
            java.lang.String r10 = "conditional_properties"
            r1 = 11
            java.lang.String[] r11 = new java.lang.String[r1]     // Catch:{ SQLiteException -> 0x012d, all -> 0x0127 }
            java.lang.String r1 = "origin"
            r2 = 0
            r11[r2] = r1     // Catch:{ SQLiteException -> 0x012d, all -> 0x0127 }
            java.lang.String r1 = "value"
            r3 = 1
            r11[r3] = r1     // Catch:{ SQLiteException -> 0x012d, all -> 0x0127 }
            java.lang.String r1 = "active"
            r4 = 2
            r11[r4] = r1     // Catch:{ SQLiteException -> 0x012d, all -> 0x0127 }
            java.lang.String r1 = "trigger_event_name"
            r5 = 3
            r11[r5] = r1     // Catch:{ SQLiteException -> 0x012d, all -> 0x0127 }
            java.lang.String r1 = "trigger_timeout"
            r6 = 4
            r11[r6] = r1     // Catch:{ SQLiteException -> 0x012d, all -> 0x0127 }
            java.lang.String r1 = "timed_out_event"
            r15 = 5
            r11[r15] = r1     // Catch:{ SQLiteException -> 0x012d, all -> 0x0127 }
            java.lang.String r1 = "creation_timestamp"
            r14 = 6
            r11[r14] = r1     // Catch:{ SQLiteException -> 0x012d, all -> 0x0127 }
            java.lang.String r1 = "triggered_event"
            r13 = 7
            r11[r13] = r1     // Catch:{ SQLiteException -> 0x012d, all -> 0x0127 }
            java.lang.String r1 = "triggered_timestamp"
            r12 = 8
            r11[r12] = r1     // Catch:{ SQLiteException -> 0x012d, all -> 0x0127 }
            java.lang.String r1 = "time_to_live"
            r6 = 9
            r11[r6] = r1     // Catch:{ SQLiteException -> 0x012d, all -> 0x0127 }
            java.lang.String r1 = "expired_event"
            r6 = 10
            r11[r6] = r1     // Catch:{ SQLiteException -> 0x012d, all -> 0x0127 }
            java.lang.String r1 = "app_id=? and name=?"
            java.lang.String[] r13 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x012d, all -> 0x0127 }
            r13[r2] = r34     // Catch:{ SQLiteException -> 0x012d, all -> 0x0127 }
            r13[r3] = r7     // Catch:{ SQLiteException -> 0x012d, all -> 0x0127 }
            r16 = 0
            r17 = 0
            r18 = 0
            r6 = r12
            r12 = r1
            r1 = 7
            r6 = r14
            r14 = r16
            r1 = r15
            r15 = r17
            r16 = r18
            android.database.Cursor r9 = r9.query(r10, r11, r12, r13, r14, r15, r16)     // Catch:{ SQLiteException -> 0x012d, all -> 0x0127 }
            boolean r10 = r9.moveToFirst()     // Catch:{ SQLiteException -> 0x0122, all -> 0x011e }
            if (r10 != 0) goto L_0x007c
            if (r9 == 0) goto L_0x007b
            r9.close()
        L_0x007b:
            return r8
        L_0x007c:
            java.lang.String r19 = r9.getString(r2)     // Catch:{ SQLiteException -> 0x0122, all -> 0x011e }
            r10 = r33
            java.lang.Object r11 = r10.a(r9, r3)     // Catch:{ SQLiteException -> 0x011c }
            int r4 = r9.getInt(r4)     // Catch:{ SQLiteException -> 0x011c }
            if (r4 == 0) goto L_0x008f
            r23 = r3
            goto L_0x0091
        L_0x008f:
            r23 = r2
        L_0x0091:
            java.lang.String r24 = r9.getString(r5)     // Catch:{ SQLiteException -> 0x011c }
            r2 = 4
            long r26 = r9.getLong(r2)     // Catch:{ SQLiteException -> 0x011c }
            com.google.android.gms.internal.measurement.fe r2 = r33.f_()     // Catch:{ SQLiteException -> 0x011c }
            byte[] r1 = r9.getBlob(r1)     // Catch:{ SQLiteException -> 0x011c }
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzex> r3 = com.google.android.gms.internal.measurement.zzex.CREATOR     // Catch:{ SQLiteException -> 0x011c }
            android.os.Parcelable r1 = r2.a(r1, r3)     // Catch:{ SQLiteException -> 0x011c }
            r25 = r1
            com.google.android.gms.internal.measurement.zzex r25 = (com.google.android.gms.internal.measurement.zzex) r25     // Catch:{ SQLiteException -> 0x011c }
            long r21 = r9.getLong(r6)     // Catch:{ SQLiteException -> 0x011c }
            com.google.android.gms.internal.measurement.fe r1 = r33.f_()     // Catch:{ SQLiteException -> 0x011c }
            r2 = 7
            byte[] r2 = r9.getBlob(r2)     // Catch:{ SQLiteException -> 0x011c }
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzex> r3 = com.google.android.gms.internal.measurement.zzex.CREATOR     // Catch:{ SQLiteException -> 0x011c }
            android.os.Parcelable r1 = r1.a(r2, r3)     // Catch:{ SQLiteException -> 0x011c }
            r28 = r1
            com.google.android.gms.internal.measurement.zzex r28 = (com.google.android.gms.internal.measurement.zzex) r28     // Catch:{ SQLiteException -> 0x011c }
            r1 = 8
            long r3 = r9.getLong(r1)     // Catch:{ SQLiteException -> 0x011c }
            r1 = 9
            long r29 = r9.getLong(r1)     // Catch:{ SQLiteException -> 0x011c }
            com.google.android.gms.internal.measurement.fe r1 = r33.f_()     // Catch:{ SQLiteException -> 0x011c }
            r2 = 10
            byte[] r2 = r9.getBlob(r2)     // Catch:{ SQLiteException -> 0x011c }
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzex> r5 = com.google.android.gms.internal.measurement.zzex.CREATOR     // Catch:{ SQLiteException -> 0x011c }
            android.os.Parcelable r1 = r1.a(r2, r5)     // Catch:{ SQLiteException -> 0x011c }
            r31 = r1
            com.google.android.gms.internal.measurement.zzex r31 = (com.google.android.gms.internal.measurement.zzex) r31     // Catch:{ SQLiteException -> 0x011c }
            com.google.android.gms.internal.measurement.zzka r20 = new com.google.android.gms.internal.measurement.zzka     // Catch:{ SQLiteException -> 0x011c }
            r1 = r20
            r2 = r7
            r5 = r11
            r6 = r19
            r1.<init>(r2, r3, r5, r6)     // Catch:{ SQLiteException -> 0x011c }
            com.google.android.gms.internal.measurement.zzef r1 = new com.google.android.gms.internal.measurement.zzef     // Catch:{ SQLiteException -> 0x011c }
            r17 = r1
            r18 = r34
            r17.<init>(r18, r19, r20, r21, r23, r24, r25, r26, r28, r29, r31)     // Catch:{ SQLiteException -> 0x011c }
            boolean r2 = r9.moveToNext()     // Catch:{ SQLiteException -> 0x011c }
            if (r2 == 0) goto L_0x0116
            com.google.android.gms.internal.measurement.aq r2 = r33.r()     // Catch:{ SQLiteException -> 0x011c }
            com.google.android.gms.internal.measurement.as r2 = r2.h_()     // Catch:{ SQLiteException -> 0x011c }
            java.lang.String r3 = "Got multiple records for conditional property, expected one"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.aq.a(r34)     // Catch:{ SQLiteException -> 0x011c }
            com.google.android.gms.internal.measurement.ao r5 = r33.o()     // Catch:{ SQLiteException -> 0x011c }
            java.lang.String r5 = r5.c(r7)     // Catch:{ SQLiteException -> 0x011c }
            r2.a(r3, r4, r5)     // Catch:{ SQLiteException -> 0x011c }
        L_0x0116:
            if (r9 == 0) goto L_0x011b
            r9.close()
        L_0x011b:
            return r1
        L_0x011c:
            r0 = move-exception
            goto L_0x0125
        L_0x011e:
            r0 = move-exception
            r10 = r33
            goto L_0x0152
        L_0x0122:
            r0 = move-exception
            r10 = r33
        L_0x0125:
            r1 = r0
            goto L_0x0132
        L_0x0127:
            r0 = move-exception
            r10 = r33
            r1 = r0
            r9 = r8
            goto L_0x0153
        L_0x012d:
            r0 = move-exception
            r10 = r33
            r1 = r0
            r9 = r8
        L_0x0132:
            com.google.android.gms.internal.measurement.aq r2 = r33.r()     // Catch:{ all -> 0x0151 }
            com.google.android.gms.internal.measurement.as r2 = r2.h_()     // Catch:{ all -> 0x0151 }
            java.lang.String r3 = "Error querying conditional property"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.aq.a(r34)     // Catch:{ all -> 0x0151 }
            com.google.android.gms.internal.measurement.ao r5 = r33.o()     // Catch:{ all -> 0x0151 }
            java.lang.String r5 = r5.c(r7)     // Catch:{ all -> 0x0151 }
            r2.a(r3, r4, r5, r1)     // Catch:{ all -> 0x0151 }
            if (r9 == 0) goto L_0x0150
            r9.close()
        L_0x0150:
            return r8
        L_0x0151:
            r0 = move-exception
        L_0x0152:
            r1 = r0
        L_0x0153:
            if (r9 == 0) goto L_0x0158
            r9.close()
        L_0x0158:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.z.d(java.lang.String, java.lang.String):com.google.android.gms.internal.measurement.zzef");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0079  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] d(java.lang.String r12) {
        /*
            r11 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)
            r11.d()
            r11.I()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r11.i()     // Catch:{ SQLiteException -> 0x005d, all -> 0x005a }
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
            com.google.android.gms.internal.measurement.aq r3 = r11.r()     // Catch:{ SQLiteException -> 0x0058 }
            com.google.android.gms.internal.measurement.as r3 = r3.h_()     // Catch:{ SQLiteException -> 0x0058 }
            java.lang.String r4 = "Got multiple records for app config, expected one. appId"
            java.lang.Object r5 = com.google.android.gms.internal.measurement.aq.a(r12)     // Catch:{ SQLiteException -> 0x0058 }
            r3.a(r4, r5)     // Catch:{ SQLiteException -> 0x0058 }
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
            com.google.android.gms.internal.measurement.aq r3 = r11.r()     // Catch:{ all -> 0x0076 }
            com.google.android.gms.internal.measurement.as r3 = r3.h_()     // Catch:{ all -> 0x0076 }
            java.lang.String r4 = "Error querying remote config. appId"
            java.lang.Object r12 = com.google.android.gms.internal.measurement.aq.a(r12)     // Catch:{ all -> 0x0076 }
            r3.a(r4, r12, r2)     // Catch:{ all -> 0x0076 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.z.d(java.lang.String):byte[]");
    }

    @WorkerThread
    public final int e(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        d();
        I();
        try {
            return i().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e2) {
            r().h_().a("Error deleting conditional property", aq.a(str), o().c(str2), e2);
            return 0;
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x009b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, com.google.android.gms.internal.measurement.fy> e(java.lang.String r12) {
        /*
            r11 = this;
            r11.I()
            r11.d()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)
            android.database.sqlite.SQLiteDatabase r0 = r11.i()
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
            com.google.android.gms.internal.measurement.c r3 = com.google.android.gms.internal.measurement.c.a(r3, r9, r4)     // Catch:{ SQLiteException -> 0x007a }
            com.google.android.gms.internal.measurement.fy r4 = new com.google.android.gms.internal.measurement.fy     // Catch:{ SQLiteException -> 0x007a }
            r4.<init>()     // Catch:{ SQLiteException -> 0x007a }
            r4.a(r3)     // Catch:{ IOException -> 0x0058 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ SQLiteException -> 0x007a }
            r1.put(r2, r4)     // Catch:{ SQLiteException -> 0x007a }
            goto L_0x006e
        L_0x0058:
            r3 = move-exception
            com.google.android.gms.internal.measurement.aq r4 = r11.r()     // Catch:{ SQLiteException -> 0x007a }
            com.google.android.gms.internal.measurement.as r4 = r4.h_()     // Catch:{ SQLiteException -> 0x007a }
            java.lang.String r5 = "Failed to merge filter results. appId, audienceId, error"
            java.lang.Object r6 = com.google.android.gms.internal.measurement.aq.a(r12)     // Catch:{ SQLiteException -> 0x007a }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ SQLiteException -> 0x007a }
            r4.a(r5, r6, r2, r3)     // Catch:{ SQLiteException -> 0x007a }
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
            com.google.android.gms.internal.measurement.aq r2 = r11.r()     // Catch:{ all -> 0x0098 }
            com.google.android.gms.internal.measurement.as r2 = r2.h_()     // Catch:{ all -> 0x0098 }
            java.lang.String r3 = "Database error querying filter results. appId"
            java.lang.Object r12 = com.google.android.gms.internal.measurement.aq.a(r12)     // Catch:{ all -> 0x0098 }
            r2.a(r3, r12, r1)     // Catch:{ all -> 0x0098 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.z.e(java.lang.String):java.util.Map");
    }

    /* access modifiers changed from: protected */
    public final boolean e() {
        return false;
    }

    public final long f(String str) {
        Preconditions.checkNotEmpty(str);
        return a("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0);
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.fk>> f(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            r12.I()
            r12.d()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r13)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            android.support.v4.util.ArrayMap r0 = new android.support.v4.util.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r12.i()
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
            com.google.android.gms.internal.measurement.c r1 = com.google.android.gms.internal.measurement.c.a(r1, r10, r2)     // Catch:{ SQLiteException -> 0x0097 }
            com.google.android.gms.internal.measurement.fk r2 = new com.google.android.gms.internal.measurement.fk     // Catch:{ SQLiteException -> 0x0097 }
            r2.<init>()     // Catch:{ SQLiteException -> 0x0097 }
            r2.a(r1)     // Catch:{ IOException -> 0x0079 }
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
            com.google.android.gms.internal.measurement.aq r2 = r12.r()     // Catch:{ SQLiteException -> 0x0097 }
            com.google.android.gms.internal.measurement.as r2 = r2.h_()     // Catch:{ SQLiteException -> 0x0097 }
            java.lang.String r3 = "Failed to merge filter. appId"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.aq.a(r13)     // Catch:{ SQLiteException -> 0x0097 }
            r2.a(r3, r4, r1)     // Catch:{ SQLiteException -> 0x0097 }
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
            com.google.android.gms.internal.measurement.aq r1 = r12.r()     // Catch:{ all -> 0x00b5 }
            com.google.android.gms.internal.measurement.as r1 = r1.h_()     // Catch:{ all -> 0x00b5 }
            java.lang.String r2 = "Database error querying filters. appId"
            java.lang.Object r13 = com.google.android.gms.internal.measurement.aq.a(r13)     // Catch:{ all -> 0x00b5 }
            r1.a(r2, r13, r0)     // Catch:{ all -> 0x00b5 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.z.f(java.lang.String, java.lang.String):java.util.Map");
    }

    @WorkerThread
    public final void f() {
        I();
        i().beginTransaction();
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.fn>> g(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            r12.I()
            r12.d()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r13)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            android.support.v4.util.ArrayMap r0 = new android.support.v4.util.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r12.i()
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
            com.google.android.gms.internal.measurement.c r1 = com.google.android.gms.internal.measurement.c.a(r1, r10, r2)     // Catch:{ SQLiteException -> 0x0097 }
            com.google.android.gms.internal.measurement.fn r2 = new com.google.android.gms.internal.measurement.fn     // Catch:{ SQLiteException -> 0x0097 }
            r2.<init>()     // Catch:{ SQLiteException -> 0x0097 }
            r2.a(r1)     // Catch:{ IOException -> 0x0079 }
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
            com.google.android.gms.internal.measurement.aq r2 = r12.r()     // Catch:{ SQLiteException -> 0x0097 }
            com.google.android.gms.internal.measurement.as r2 = r2.h_()     // Catch:{ SQLiteException -> 0x0097 }
            java.lang.String r3 = "Failed to merge filter"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.aq.a(r13)     // Catch:{ SQLiteException -> 0x0097 }
            r2.a(r3, r4, r1)     // Catch:{ SQLiteException -> 0x0097 }
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
            com.google.android.gms.internal.measurement.aq r1 = r12.r()     // Catch:{ all -> 0x00b5 }
            com.google.android.gms.internal.measurement.as r1 = r1.h_()     // Catch:{ all -> 0x00b5 }
            java.lang.String r2 = "Database error querying filters. appId"
            java.lang.Object r13 = com.google.android.gms.internal.measurement.aq.a(r13)     // Catch:{ all -> 0x00b5 }
            r1.a(r2, r13, r0)     // Catch:{ all -> 0x00b5 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.z.g(java.lang.String, java.lang.String):java.util.Map");
    }

    @WorkerThread
    public final void g() {
        I();
        i().setTransactionSuccessful();
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    @VisibleForTesting
    public final long h(String str, String str2) {
        long j;
        Object obj;
        String str3 = str;
        String str4 = str2;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        d();
        I();
        SQLiteDatabase i2 = i();
        i2.beginTransaction();
        try {
            StringBuilder sb = new StringBuilder(32 + String.valueOf(str2).length());
            sb.append("select ");
            sb.append(str4);
            sb.append(" from app2 where app_id=?");
            try {
                j = a(sb.toString(), new String[]{str3}, -1);
                if (j == -1) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("app_id", str3);
                    contentValues.put("first_open_count", Integer.valueOf(0));
                    contentValues.put("previous_install_count", Integer.valueOf(0));
                    if (i2.insertWithOnConflict("app2", null, contentValues, 5) == -1) {
                        r().h_().a("Failed to insert column (got -1). appId", aq.a(str), str4);
                        i2.endTransaction();
                        return -1;
                    }
                    j = 0;
                }
                try {
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("app_id", str3);
                    contentValues2.put(str4, Long.valueOf(j + 1));
                    if (((long) i2.update("app2", contentValues2, "app_id = ?", new String[]{str3})) == 0) {
                        r().h_().a("Failed to update column (got 0). appId", aq.a(str), str4);
                        i2.endTransaction();
                        return -1;
                    }
                    i2.setTransactionSuccessful();
                    i2.endTransaction();
                    return j;
                } catch (SQLiteException e2) {
                    obj = e2;
                }
            } catch (SQLiteException e3) {
                e = e3;
                obj = e;
                j = 0;
                try {
                    r().h_().a("Error inserting column. appId", aq.a(str), str4, obj);
                    i2.endTransaction();
                    return j;
                } catch (Throwable th) {
                    th = th;
                    Throwable th2 = th;
                    i2.endTransaction();
                    throw th2;
                }
            }
        } catch (SQLiteException e4) {
            e = e4;
            obj = e;
            j = 0;
            r().h_().a("Error inserting column. appId", aq.a(str), str4, obj);
            i2.endTransaction();
            return j;
        } catch (Throwable th3) {
            th = th3;
            Throwable th22 = th;
            i2.endTransaction();
            throw th22;
        }
    }

    @WorkerThread
    public final void h() {
        I();
        i().endTransaction();
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    @VisibleForTesting
    public final SQLiteDatabase i() {
        d();
        try {
            return this.h.getWritableDatabase();
        } catch (SQLiteException e2) {
            r().i().a("Error opening database", e2);
            throw e2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0041  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String j() {
        /*
            r6 = this;
            android.database.sqlite.SQLiteDatabase r0 = r6.i()
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
            com.google.android.gms.internal.measurement.aq r3 = r6.r()     // Catch:{ all -> 0x003e }
            com.google.android.gms.internal.measurement.as r3 = r3.h_()     // Catch:{ all -> 0x003e }
            java.lang.String r4 = "Database error getting next bundle app id"
            r3.a(r4, r2)     // Catch:{ all -> 0x003e }
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.z.j():java.lang.String");
    }

    public final boolean k() {
        return b("select count(1) > 0 from queue where has_realtime = 1", (String[]) null) != 0;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void v() {
        d();
        I();
        if (K()) {
            long a = s().f.a();
            long elapsedRealtime = m().elapsedRealtime();
            if (Math.abs(elapsedRealtime - a) > ((Long) ak.G.b()).longValue()) {
                s().f.a(elapsedRealtime);
                d();
                I();
                if (K()) {
                    int delete = i().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(m().currentTimeMillis()), String.valueOf(w.j())});
                    if (delete > 0) {
                        r().w().a("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
                    }
                }
            }
        }
    }

    @WorkerThread
    public final long w() {
        return a("select max(bundle_end_timestamp) from queue", (String[]) null, 0);
    }

    @WorkerThread
    public final long x() {
        return a("select max(timestamp) from raw_events", (String[]) null, 0);
    }

    public final boolean y() {
        return b("select count(1) > 0 from raw_events", (String[]) null) != 0;
    }

    public final boolean z() {
        return b("select count(1) > 0 from raw_events where realtime = 1", (String[]) null) != 0;
    }
}
