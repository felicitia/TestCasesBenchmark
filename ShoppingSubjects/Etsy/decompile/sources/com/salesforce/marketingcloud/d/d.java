package com.salesforce.marketingcloud.d;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.util.ArrayMap;
import com.salesforce.marketingcloud.e.a;
import com.salesforce.marketingcloud.e.e;
import com.salesforce.marketingcloud.e.f;
import com.salesforce.marketingcloud.e.g;
import com.salesforce.marketingcloud.j;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;

abstract class d {
    private static final String a = j.a(d.class);
    private static AtomicBoolean b = new AtomicBoolean(false);
    private final String c;
    private final String d;

    d(@NonNull String str, @NonNull String str2) {
        this.c = (String) f.a((CharSequence) f.a(str, "Application ID is null."), "Application ID is empty.");
        this.d = (String) f.a((CharSequence) f.a(str2, "Access Token is null."), "Access Token is empty.");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:6|(4:7|8|9|10)|13|14|15) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0037 */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.Object a(@android.support.annotation.NonNull com.salesforce.marketingcloud.e.a r7, java.lang.String r8, java.lang.reflect.Type r9) {
        /*
            r6 = this;
            android.content.Context r0 = r6.b()
            java.lang.String r1 = "ETPush"
            r2 = 0
            android.content.SharedPreferences r0 = r0.getSharedPreferences(r1, r2)
            r1 = 0
            java.lang.String r3 = "%s_enc"
            r4 = 1
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ ClassCastException -> 0x0045 }
            r5[r2] = r8     // Catch:{ ClassCastException -> 0x0045 }
            java.lang.String r3 = java.lang.String.format(r3, r5)     // Catch:{ ClassCastException -> 0x0045 }
            java.lang.String r3 = r0.getString(r3, r1)     // Catch:{ ClassCastException -> 0x0045 }
            if (r3 != 0) goto L_0x0021
            java.lang.String r3 = r0.getString(r8, r1)     // Catch:{ ClassCastException -> 0x0045 }
        L_0x0021:
            if (r3 == 0) goto L_0x0044
            r0 = r3
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ UnsupportedEncodingException | GeneralSecurityException -> 0x0036 }
            java.lang.String r7 = r7.b(r0)     // Catch:{ UnsupportedEncodingException | GeneralSecurityException -> 0x0036 }
            java.lang.String r0 = a     // Catch:{ UnsupportedEncodingException | GeneralSecurityException -> 0x0037 }
            java.lang.String r3 = "Found encrypted value for %s"
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ UnsupportedEncodingException | GeneralSecurityException -> 0x0037 }
            r5[r2] = r8     // Catch:{ UnsupportedEncodingException | GeneralSecurityException -> 0x0037 }
            com.salesforce.marketingcloud.j.c(r0, r3, r5)     // Catch:{ UnsupportedEncodingException | GeneralSecurityException -> 0x0037 }
            goto L_0x0042
        L_0x0036:
            r7 = r3
        L_0x0037:
            java.lang.String r0 = a     // Catch:{ ClassCastException -> 0x0045 }
            java.lang.String r3 = "Found unencrypted value for %s"
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ ClassCastException -> 0x0045 }
            r4[r2] = r8     // Catch:{ ClassCastException -> 0x0045 }
            com.salesforce.marketingcloud.j.c(r0, r3, r4)     // Catch:{ ClassCastException -> 0x0045 }
        L_0x0042:
            r1 = r7
            goto L_0x0045
        L_0x0044:
            r1 = r3
        L_0x0045:
            if (r1 == 0) goto L_0x0048
            return r1
        L_0x0048:
            java.lang.Object r1 = r6.a(r8, r9)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.d.d.a(com.salesforce.marketingcloud.e.a, java.lang.String, java.lang.reflect.Type):java.lang.Object");
    }

    @Nullable
    private Object a(String str, Type type) {
        String[] strArr = {"ETPush", "et_registration_cache", "~!Registration", "~!ETPush", "~!ETLocationManager", "etpushSDK@ETPush", "etpushsdk@ETLocationManager", "DEFAULT_SHARED_PREFERENCES"};
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            String str2 = strArr[i];
            SharedPreferences defaultSharedPreferences = "DEFAULT_SHARED_PREFERENCES".equals(str2) ? PreferenceManager.getDefaultSharedPreferences(b()) : b().getSharedPreferences(str2, 0);
            if (defaultSharedPreferences.contains(str)) {
                if (type == Integer.class) {
                    try {
                        return Integer.valueOf(defaultSharedPreferences.getInt(str, 0));
                    } catch (ClassCastException unused) {
                    }
                } else if (type == Boolean.class) {
                    return Boolean.valueOf(defaultSharedPreferences.getBoolean(str, false));
                } else {
                    if (type == Long.class) {
                        return Long.valueOf(defaultSharedPreferences.getLong(str, 0));
                    }
                    if (type == String.class) {
                        return defaultSharedPreferences.getString(str, null);
                    }
                }
            }
        }
        return null;
    }

    private void a(Context context, String str, String str2) {
        boolean z;
        try {
            boolean andSet = b.getAndSet(true);
            boolean z2 = e().getBoolean("et_207_preference_migration_complete", false);
            if (!andSet) {
                if (!z2) {
                    e eVar = new e(context, str, str2, f());
                    try {
                        eVar.b(context.getSharedPreferences("ETPush", 0).getString("et_device_id_cache_enc", null));
                        z = true;
                    } catch (Exception unused) {
                        z = false;
                    }
                    if (!z) {
                        j.b(a, "Unable to verify old encryption.  Moving on without migrating data.", new Object[0]);
                    } else if (a(context, (a) eVar)) {
                        a((a) eVar);
                        j.c(a, "Old data migrations completed without exception.", new Object[0]);
                    }
                    e().edit().putBoolean("et_207_preference_migration_complete", true).apply();
                    b.set(false);
                    return;
                }
            }
            e().edit().putBoolean("et_207_preference_migration_complete", true).apply();
            b.set(false);
        } catch (Exception e) {
            j.c(a, e, "Data migration failed", new Object[0]);
        } catch (Throwable th) {
            e().edit().putBoolean("et_207_preference_migration_complete", true).apply();
            b.set(false);
            throw th;
        }
    }

    private void a(String str) {
        j.c(a, "Unknown Type for %s. Preference will not be migrated.", str);
    }

    private void b(@NonNull a aVar) {
        String[] strArr;
        for (String str : new String[]{"et_tags_cache", "et_attributes_cache", "et_subscriber_cache", "gcm_reg_id_key", "et_user_id_cache", "et_session_id_cache", "et_last_location_latitude", "et_last_location_longitude"}) {
            try {
                j.c(a, "Migrating %s ...", str);
                Object a2 = a(aVar, str, (Type) String.class);
                if ("et_attributes_cache".equals(str)) {
                    Map c2 = g.c((String) a2);
                    if (c2 != null) {
                        c2.remove("_ETSDKVersion");
                        a2 = g.a(c2);
                    }
                }
                if (a2 == null || "null".equals(a2)) {
                    j.c(a, "Value was \"null\" and will not be migrated.", new Object[0]);
                } else {
                    j.c(a, "Writing %s to encrypted preferences ...", str);
                    d().a(str, String.valueOf(a2));
                }
                j.c(a, "Done with %s.", str);
            } catch (Exception e) {
                j.c(a, e, "Unable to migrate %s", str);
            }
        }
    }

    private void c(@NonNull a aVar) {
        long longValue;
        int intValue;
        boolean booleanValue;
        Editor edit = e().edit();
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put("et_device_id_cache", String.class);
        arrayMap.put("et_manufacturer_cache", String.class);
        arrayMap.put("et_model_cache", String.class);
        arrayMap.put("et_platform_cache", String.class);
        arrayMap.put("et_platform_version_cache", String.class);
        arrayMap.put("previousRegistrationHash", String.class);
        arrayMap.put("et_android_version", String.class);
        arrayMap.put("et_customer_manifest_requires_verification", Boolean.class);
        arrayMap.put("et_geo_enabled_key", Boolean.class);
        arrayMap.put("et_proximity_enabled_key", Boolean.class);
        arrayMap.put("et_proximity_invalidated_key", Boolean.class);
        arrayMap.put("et_push_enabled", Boolean.class);
        arrayMap.put("time_went_in_background", Long.class);
        arrayMap.put("pause_time_key", Long.class);
        arrayMap.put("et_background_time_cache", Long.class);
        arrayMap.put("et_registration_alarm_created_date", Long.class);
        arrayMap.put("et_registration_next_alarm_interval", Long.class);
        arrayMap.put("et_register_for_remote_notifications_alarm_created_date", Long.class);
        arrayMap.put("et_register_for_remote_notifications_next_alarm_interval", Long.class);
        arrayMap.put("et_wama_alarm_created_date", Long.class);
        arrayMap.put("et_wama_next_alarm_interval", Long.class);
        arrayMap.put("et_etanalytic_alarm_created_date", Long.class);
        arrayMap.put("et_etanalytic_next_alarm_interval", Long.class);
        arrayMap.put("et_fetch_beacon_messages_alarm_created_date", Long.class);
        arrayMap.put("et_fetch_beacon_messages_next_alarm_interval", Long.class);
        arrayMap.put("et_fetch_background_beacon_messages_alarm_created_date", Long.class);
        arrayMap.put("et_fetch_background_beacon_messages_next_alarm_interval", Long.class);
        arrayMap.put("et_fetch_fence_messages_alarm_created_date", Long.class);
        arrayMap.put("et_fetch_fence_messages_next_alarm_interval", Long.class);
        arrayMap.put("et_fetch_background_fence_messages_alarm_created_date", Long.class);
        arrayMap.put("et_fetch_background_fence_messages_next_alarm_interval", Long.class);
        arrayMap.put("et_fetch_cloud_messages_alarm_created_date", Long.class);
        arrayMap.put("et_fetch_cloud_messages_next_alarm_interval", Long.class);
        arrayMap.put("et_force_registration_alarm_created_date", Long.class);
        arrayMap.put("et_force_registration_next_alarm_interval", Long.class);
        arrayMap.put("et_cp_route_retry_after_time_in_millis", Long.class);
        arrayMap.put("et_geofence_route_retry_after_time_in_millis", Long.class);
        arrayMap.put("et_proximity_route_retry_after_time_in_millis", Long.class);
        arrayMap.put("gcm_app_version_key", Integer.class);
        arrayMap.put("et_notification_id_key", Integer.class);
        arrayMap.put("et_notification_request_code_key", Integer.class);
        for (Entry entry : arrayMap.entrySet()) {
            String str = (String) entry.getKey();
            Type type = (Type) entry.getValue();
            j.c(a, "Migrating %s ...", str);
            try {
                Object a2 = a(aVar, str, type);
                if (a2 != null) {
                    if (type == Boolean.class) {
                        if (a2 instanceof Boolean) {
                            booleanValue = ((Boolean) a2).booleanValue();
                        } else if (a2 instanceof String) {
                            booleanValue = Boolean.valueOf((String) a2).booleanValue();
                        }
                        edit.putBoolean(str, booleanValue);
                    } else if (type == Integer.class) {
                        if (a2 instanceof Integer) {
                            intValue = ((Integer) a2).intValue();
                        } else if (a2 instanceof String) {
                            intValue = Integer.valueOf((String) a2).intValue();
                        }
                        edit.putInt(str, intValue);
                    } else if (type == Long.class) {
                        if (a2 instanceof Long) {
                            longValue = ((Long) a2).longValue();
                        } else if (a2 instanceof String) {
                            longValue = Long.valueOf((String) a2).longValue();
                        }
                        edit.putLong(str, longValue);
                    } else if (type != String.class) {
                        j.c(a, "Key '%s' with value of '%s' was not written to preferences.", str, a2);
                    } else if (a2 instanceof String) {
                        if (!"null".equals(a2)) {
                            edit.putString(str, (String) a2);
                        } else {
                            j.c(a, "Value was \"null\" and will not be migrated.", new Object[0]);
                        }
                    }
                    a(str);
                }
                j.c(a, "Done with %s.", str);
            } catch (Exception e) {
                j.c(a, e, "Unable to migrate %s", str);
            }
        }
        edit.apply();
    }

    @NonNull
    private String f() {
        StringBuilder sb = new StringBuilder();
        sb.append(Secure.getString(b().getContentResolver(), "android_id"));
        sb.append("-");
        sb.append(b().getPackageName());
        return g.b(sb.toString());
    }

    @RestrictTo({Scope.LIBRARY})
    public abstract a a();

    /* access modifiers changed from: protected */
    public final void a(Context context, int i, int i2) {
        if (i == -1) {
            String[] databaseList = context.databaseList();
            if (databaseList != null) {
                for (String equals : databaseList) {
                    if (com.salesforce.marketingcloud.d.a.a.a.a.equals(equals)) {
                        a(context, this.c, this.d);
                        return;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(@NonNull a aVar) {
        j.c(a, "Migrating SharedPreferences ...", new Object[0]);
        b(aVar);
        c(aVar);
        j.c(a, "Finished migrating SharedPreferences.", new Object[0]);
    }

    /* access modifiers changed from: 0000 */
    public boolean a(Context context, a aVar) {
        SQLiteDatabase readableDatabase = new com.salesforce.marketingcloud.d.a.a.a(context, aVar).getReadableDatabase();
        SQLiteDatabase writableDatabase = c().getWritableDatabase();
        writableDatabase.beginTransaction();
        boolean z = false;
        try {
            new com.salesforce.marketingcloud.d.a.a(writableDatabase).a(aVar, a(), readableDatabase);
            new com.salesforce.marketingcloud.d.a.f(writableDatabase).a(aVar, a(), readableDatabase);
            writableDatabase.setTransactionSuccessful();
            z = true;
        } catch (Exception e) {
            j.c(a, e, "Failed to migrate old database", new Object[0]);
        } catch (Throwable th) {
            writableDatabase.endTransaction();
            throw th;
        }
        writableDatabase.endTransaction();
        readableDatabase.close();
        return z;
    }

    /* access modifiers changed from: protected */
    public abstract Context b();

    /* access modifiers changed from: protected */
    public abstract void b(Context context, int i, int i2);

    /* access modifiers changed from: protected */
    public abstract SQLiteOpenHelper c();

    public abstract c d();

    public abstract SharedPreferences e();
}
