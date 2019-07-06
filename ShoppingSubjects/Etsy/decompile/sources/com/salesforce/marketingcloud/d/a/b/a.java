package com.salesforce.marketingcloud.d.a.b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.util.ArraySet;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.salesforce.marketingcloud.e.c;
import com.salesforce.marketingcloud.e.d;
import com.salesforce.marketingcloud.e.h;
import com.salesforce.marketingcloud.j;
import java.util.ArrayList;
import java.util.Collection;

@RestrictTo({Scope.LIBRARY})
public final class a {
    private static final String a = j.a(a.class);

    /* renamed from: com.salesforce.marketingcloud.d.a.b.a$a reason: collision with other inner class name */
    private static class C0165a {
        final String a;
        final String b;

        C0165a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof C0165a)) {
                return false;
            }
            C0165a aVar = (C0165a) obj;
            if (this.a == null && aVar.a == null) {
                return true;
            }
            return this.a.equalsIgnoreCase(aVar.a);
        }

        public int hashCode() {
            return this.a.toLowerCase().hashCode();
        }
    }

    private a() {
    }

    private static synchronized String a(Collection<C0165a> collection) {
        synchronized (a.class) {
            if (collection == null) {
                return null;
            }
            ArrayList<C0165a> arrayList = new ArrayList<>(collection.size());
            for (C0165a add : collection) {
                arrayList.add(add);
            }
            StringBuilder sb = new StringBuilder();
            for (C0165a aVar : arrayList) {
                if (aVar != null) {
                    sb.append(aVar.a);
                    sb.append("^|^");
                    sb.append(aVar.b);
                    sb.append("^|^");
                } else {
                    j.d(a, "A null attribute was encountered.", new Object[0]);
                }
            }
            String sb2 = sb.toString();
            return sb2;
        }
    }

    private static ArrayList<C0165a> a(String str) {
        ArrayList<C0165a> arrayList = new ArrayList<>();
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split("\\^\\|\\^");
            int i = 0;
            while (i < split.length) {
                while (true) {
                    if (split[i] != null && !split[i].isEmpty()) {
                        break;
                    }
                    i++;
                }
                int i2 = i + 1;
                if (i2 >= split.length) {
                    arrayList.add(new C0165a(split[i], ""));
                } else {
                    arrayList.add(new C0165a(split[i], split[i2]));
                }
                i += 2;
            }
        }
        return arrayList;
    }

    private static void a(SQLiteDatabase sQLiteDatabase) {
        String str = "CREATE TABLE cloud_page_messages (id VARCHAR PRIMARY KEY, start_date VARCHAR, end_date VARCHAR, message_type INTEGER, content_type INTEGER, url VARCHAR, subject VARCHAR, read SMALLINT, message_deleted SMALLINT)";
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL(str);
            sQLiteDatabase.execSQL("INSERT INTO cloud_page_messages SELECT id,start_date,end_date,message_type,content_type,url,subject,read,message_deleted FROM messages WHERE message_type=1 AND content_type=2");
            sQLiteDatabase.execSQL("DELETE FROM messages WHERE message_type=1 AND content_type=2");
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
            j.c(a, e, "Failed to move Messages to CloudPage Messages table.", new Object[0]);
            try {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS cloud_page_messages");
                sQLiteDatabase.execSQL(str);
                sQLiteDatabase.setTransactionSuccessful();
            } catch (Exception e2) {
                j.c(a, e2, "Could not create cloud_page_messages table.", new Object[0]);
            }
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
        sQLiteDatabase.endTransaction();
    }

    public static void a(SQLiteDatabase sQLiteDatabase, Context context, com.salesforce.marketingcloud.e.a aVar) {
        a(sQLiteDatabase);
        b(sQLiteDatabase);
        c(sQLiteDatabase);
        d(sQLiteDatabase);
        b(sQLiteDatabase, context, aVar);
        e(sQLiteDatabase);
    }

    private static void b(SQLiteDatabase sQLiteDatabase) {
        String str = "CREATE TABLE messages (id VARCHAR PRIMARY KEY, alert VARCHAR, sound VARCHAR, open_direct VARCHAR, start_date VARCHAR, end_date VARCHAR, message_type INTEGER, content_type INTEGER, url VARCHAR, custom VARCHAR, keys VARCHAR, period_show_count INTEGER, last_shown_date VARCHAR, next_allowed_show VARCHAR, show_count INTEGER, message_limit INTEGER, rolling_period SMALLINT, period_type INTEGER, number_of_periods INTEGER, messages_per_period INTEGER, proximity INTEGER, has_entered SMALLINT, notify_id INTEGER );";
        try {
            sQLiteDatabase.execSQL("DELETE FROM messages WHERE message_type NOT IN ( 5, 3, 4 )");
        } catch (Exception e) {
            j.c(a, e, "Unable to clean unused messages from db.", new Object[0]);
        }
        try {
            sQLiteDatabase.beginTransaction();
            String str2 = "old_messages";
            StringBuilder sb = new StringBuilder();
            sb.append("ALTER TABLE messages RENAME TO ");
            sb.append(str2);
            sQLiteDatabase.execSQL(sb.toString());
            sQLiteDatabase.execSQL(str);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("INSERT INTO messages SELECT id, alert, sound, open_direct, start_date, end_date, message_type, content_type, url, custom, keys, period_show_count, last_shown_date, next_allowed_show, show_count, message_limit, rolling_period, period_type, number_of_periods, messages_per_period, proximity, has_entered, notify_id FROM ");
            sb2.append(str2);
            sQLiteDatabase.execSQL(sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            sb3.append("DROP TABLE ");
            sb3.append(str2);
            sQLiteDatabase.execSQL(sb3.toString());
            sQLiteDatabase.setTransactionSuccessful();
        } catch (Exception e2) {
            try {
                sQLiteDatabase.beginTransaction();
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS messages");
                sQLiteDatabase.execSQL(str);
                sQLiteDatabase.setTransactionSuccessful();
            } catch (Exception e3) {
                j.c(a, e3, "Unable a create message table.", new Object[0]);
            }
            j.c(a, e2, "Unable to update message table", new Object[0]);
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private static void b(SQLiteDatabase sQLiteDatabase, Context context, com.salesforce.marketingcloud.e.a aVar) {
        String str = "CREATE TABLE registration (id INTEGER PRIMARY KEY AUTOINCREMENT, platform VARCHAR, subscriber_key VARCHAR, et_app_id VARCHAR, badge INTEGER, timezone INTEGER, dst SMALLINT, tags VARCHAR, attributes VARCHAR, platform_version VARCHAR, push_enabled SMALLINT, location_enabled SMALLINT, hwid VARCHAR, gcm_sender_id VARCHAR, system_token VARCHAR, device_id VARCHAR, app_version VARCHAR, sdk_version VARCHAR, locale VARCHAR )";
        String str2 = "id,platform,subscriber_key,et_app_id,badge,timezone,dst,tags,attributes,platform_version,push_enabled,location_enabled,hwid,gcm_sender_id,locale";
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL("ALTER TABLE registration RENAME TO old_registration");
            sQLiteDatabase.execSQL(str);
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO registration (");
            sb.append(str2);
            sb.append(") SELECT ");
            sb.append(str2);
            sb.append(" FROM old_registration");
            sQLiteDatabase.execSQL(sb.toString());
            if (DatabaseUtils.queryNumEntries(sQLiteDatabase, "registration") > 0) {
                ContentValues contentValues = new ContentValues(3);
                contentValues.put("device_id", c.a(context));
                contentValues.put("app_version", d.a(context));
                contentValues.put("sdk_version", h.a());
                sQLiteDatabase.update("registration", contentValues, null, null);
            }
            sQLiteDatabase.execSQL("DROP TABLE old_registration");
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
            j.c(a, e, "Unable to update registration table", new Object[0]);
            try {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS registration");
                sQLiteDatabase.execSQL(str);
                sQLiteDatabase.setTransactionSuccessful();
            } catch (Exception e2) {
                j.c(a, e2, "Unable to create registration table", new Object[0]);
            }
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
        sQLiteDatabase.endTransaction();
        try {
            sQLiteDatabase.beginTransaction();
            Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT id, attributes FROM registration", null);
            if (rawQuery != null) {
                if (rawQuery.moveToFirst()) {
                    do {
                        ArrayList a2 = a(aVar.b(rawQuery.getString(rawQuery.getColumnIndex(ResponseConstants.ATTRIBUTES))));
                        ArraySet arraySet = new ArraySet();
                        if (!a2.isEmpty()) {
                            for (int size = a2.size() - 1; size >= 0; size--) {
                                arraySet.add(a2.get(size));
                            }
                            ContentValues contentValues2 = new ContentValues();
                            contentValues2.put(ResponseConstants.ATTRIBUTES, aVar.a(a((Collection<C0165a>) arraySet)));
                            sQLiteDatabase.update("registration", contentValues2, "id=?", new String[]{rawQuery.getString(rawQuery.getColumnIndex("id"))});
                        }
                    } while (rawQuery.moveToNext());
                }
                rawQuery.close();
            }
        } catch (Exception e3) {
            j.e(a, "Unable to remove duplicate attributes from row", e3);
        } catch (Throwable th2) {
            sQLiteDatabase.endTransaction();
            throw th2;
        }
        sQLiteDatabase.setTransactionSuccessful();
        sQLiteDatabase.endTransaction();
    }

    private static void c(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL("CREATE TABLE location_table (id INTEGER PRIMARY KEY CHECK (id = 0), latitude VARCHAR, longitude VARCHAR );");
            sQLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            j.c(a, e, "Unable to create location table", new Object[0]);
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
        sQLiteDatabase.endTransaction();
        try {
            sQLiteDatabase.execSQL("DROP TABLE beacon_request");
            sQLiteDatabase.execSQL("DROP TABLE geofence_request");
        } catch (Exception e2) {
            j.c(a, e2, "Unable to drop unused request tables", new Object[0]);
        }
    }

    private static void d(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS regions");
            sQLiteDatabase.execSQL("CREATE TABLE regions (id VARCHAR PRIMARY KEY, latitude VARCHAR, longitude VARCHAR, radius INTEGER, beacon_guid VARCHAR, beacon_major INTEGER, beacon_minor INTEGER, description VARCHAR, name VARCHAR, location_type INTEGER );");
            sQLiteDatabase.execSQL("DELETE FROM region_message");
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLException unused) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS regions");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS region_message");
            sQLiteDatabase.execSQL("CREATE TABLE regions (id VARCHAR PRIMARY KEY, latitude VARCHAR, longitude VARCHAR, radius INTEGER, beacon_guid VARCHAR, beacon_major INTEGER, beacon_minor INTEGER, description VARCHAR, name VARCHAR, location_type INTEGER );");
            sQLiteDatabase.execSQL("CREATE TABLE region_message (id INTEGER PRIMARY KEY AUTOINCREMENT, region_id VARCHAR, message_id VARCHAR );");
            sQLiteDatabase.setTransactionSuccessful();
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
        sQLiteDatabase.endTransaction();
    }

    private static void e(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.beginTransaction();
            String str = "old_analytic_item";
            StringBuilder sb = new StringBuilder();
            sb.append("ALTER TABLE analytic_item RENAME TO ");
            sb.append(str);
            sQLiteDatabase.execSQL(sb.toString());
            sQLiteDatabase.execSQL("CREATE TABLE analytic_item (id INTEGER PRIMARY KEY AUTOINCREMENT, event_date VARCHAR, analytic_product_type INTEGER, analytic_types VARCHAR, value INTEGER, ready_to_send SMALLINT, object_ids VARCHAR, json_payload VARCHAR)");
            StringBuilder sb2 = new StringBuilder();
            sb2.append("SELECT * FROM ");
            sb2.append(str);
            Cursor rawQuery = sQLiteDatabase.rawQuery(sb2.toString(), null);
            if (rawQuery != null) {
                if (rawQuery.moveToFirst()) {
                    do {
                        ContentValues contentValues = new ContentValues();
                        try {
                            contentValues.put("id", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("id"))));
                            contentValues.put("event_date", rawQuery.getString(rawQuery.getColumnIndex("event_date")));
                            contentValues.put("analytic_product_type", Integer.valueOf(TextUtils.isEmpty(rawQuery.getString(rawQuery.getColumnIndex("pi_app_key"))) ^ true ? 1 : 0));
                            contentValues.put("analytic_types", rawQuery.getString(rawQuery.getColumnIndex("analytic_types")));
                            contentValues.put(ResponseConstants.VALUE, Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex(ResponseConstants.VALUE))));
                            contentValues.put("ready_to_send", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("ready_to_send"))));
                            contentValues.put("object_ids", rawQuery.getString(rawQuery.getColumnIndex("object_ids")));
                            contentValues.put("json_payload", rawQuery.getString(rawQuery.getColumnIndex("json_payload")));
                            sQLiteDatabase.insert("analytic_item", null, contentValues);
                        } catch (Exception e) {
                            j.c(a, e, "Failed to update item in Analytics local storage during upgrade.", new Object[0]);
                        }
                    } while (rawQuery.moveToNext());
                }
                rawQuery.close();
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append("DROP TABLE ");
            sb3.append(str);
            sQLiteDatabase.execSQL(sb3.toString());
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e2) {
            j.c(a, e2, "Failed to upgrade Analytics local storage.  Starting fresh.  Some analytics items may have been lost.", new Object[0]);
            try {
                sQLiteDatabase.execSQL("CREATE TABLE analytic_item (id INTEGER PRIMARY KEY AUTOINCREMENT, event_date VARCHAR, analytic_product_type INTEGER, analytic_type INTEGER, value INTEGER, ready_to_send SMALLINT, object_ids VARCHAR, json_payload VARCHAR, request_id VARCHAR);");
                sQLiteDatabase.setTransactionSuccessful();
            } catch (SQLException e3) {
                j.c(a, e3, "Failed to create local storage for Analytics.", new Object[0]);
            }
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
        sQLiteDatabase.endTransaction();
    }
}
