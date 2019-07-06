package com.salesforce.marketingcloud.d.a.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.ShopHomeSortOption;
import com.etsy.android.lib.models.editable.EditableListing;
import com.salesforce.marketingcloud.e.g;
import com.salesforce.marketingcloud.j;
import java.util.Map;
import java.util.Set;

@RestrictTo({Scope.LIBRARY})
@Deprecated
public class a extends SQLiteOpenHelper {
    public static final String a = "etdb.db";
    public static final String[] b = {"registration", ResponseConstants.MESSAGES, "analytic_item", "beacon_request", "geofence_request", "region_message", "regions"};
    private static final String c = j.a(a.class);
    private static final int d = 10;
    private static final String e = " ADD COLUMN ";
    private static final String f = " BIGINT;";
    private static final String g = " VARCHAR;";
    private static final String h = " SMALLINT;";
    private static final String i = " INTEGER;";
    private static final String j = " TEXT;";
    private static final String k = "DROP TABLE IF EXISTS ";
    private static final String l = "ALTER TABLE ";
    private static final String m = " = ? ";
    private static final String n = " = ?";
    private final com.salesforce.marketingcloud.e.a o;

    /* renamed from: com.salesforce.marketingcloud.d.a.a.a$a reason: collision with other inner class name */
    public static class C0164a {
        public static final String[] a = {"id", "device_id", "et_app_id", "event_date", "analytic_types", "object_ids", ResponseConstants.VALUE, "ready_to_send", "pi_app_key", "last_sent", "json_payload"};
    }

    public static class b {
        public static final String[] a = {"id", "device_id", ResponseConstants.LATITUDE, ResponseConstants.LONGITUDE};
    }

    public static class c {
        public static final String[] a = {"id", "device_id", ResponseConstants.LATITUDE, ResponseConstants.LONGITUDE};
    }

    public static class d {
        public static final String[] a = {"id", "alert", "sound", "badge", "open_direct", "category", "start_date", "end_date", "message_type", "content_type", "page_id", "url", ResponseConstants.SUBJECT, "site_id", "read", ShopHomeSortOption.SORT_CUSTOM, "keys", "period_show_count", "last_shown_date", "next_allowed_show", "show_count", "message_limit", "rolling_period", "period_type", "number_of_periods", "messages_per_period", "message_deleted", "min_tripped", "proximity", "ephemeral_message", "has_entered", "notify_id", "loiter_seconds", "entry_time"};
    }

    public static class e {
        public static final String[] a = {"id", ResponseConstants.LATITUDE, ResponseConstants.LONGITUDE, ResponseConstants.RADIUS, "active", "beacon_guid", "beacon_major", "beacon_minor", "entry_count", "exit_count", "description", ResponseConstants.NAME, "location_type", "has_entered"};
    }

    public static class f {
        public static final String[] a = {"id", ResponseConstants.PLATFORM, "device_id", "subscriber_key", "et_app_id", "email", "badge", "timezone", "dst", EditableListing.FIELD_TAGS, ResponseConstants.ATTRIBUTES, ResponseConstants.PLATFORM_VERSION, "push_enabled", "location_enabled", "last_sent", "hwid", "gcm_sender_id", "locale"};
    }

    public a(Context context, com.salesforce.marketingcloud.e.a aVar) {
        super(context, a, null, 10);
        this.o = aVar;
    }

    private void a(SQLiteDatabase sQLiteDatabase, @NonNull com.salesforce.marketingcloud.e.a aVar) {
        Cursor query = sQLiteDatabase.query("analytic_item", C0164a.a, null, null, null, null, null);
        if (query != null) {
            query.moveToFirst();
            while (!query.isAfterLast()) {
                int i2 = query.getInt(query.getColumnIndex("id"));
                String string = query.getString(query.getColumnIndex("device_id"));
                String string2 = query.getString(query.getColumnIndex("et_app_id"));
                String string3 = query.getString(query.getColumnIndex("pi_app_key"));
                ContentValues contentValues = new ContentValues();
                contentValues.put("device_id", aVar.a(string));
                contentValues.put("et_app_id", aVar.a(string2));
                contentValues.put("pi_app_key", aVar.a(string3));
                String[] strArr = {String.valueOf(i2)};
                sQLiteDatabase.update("analytic_item", contentValues, "id = ?", strArr);
                query.moveToNext();
            }
            query.close();
        }
    }

    private void b(SQLiteDatabase sQLiteDatabase, @NonNull com.salesforce.marketingcloud.e.a aVar) {
        Cursor query = sQLiteDatabase.query("beacon_request", b.a, null, null, null, null, null);
        if (query != null) {
            query.moveToFirst();
            while (!query.isAfterLast()) {
                int i2 = query.getInt(query.getColumnIndex("id"));
                String string = query.getString(query.getColumnIndex("device_id"));
                Double valueOf = Double.valueOf(query.getDouble(query.getColumnIndex(ResponseConstants.LATITUDE)));
                Double valueOf2 = Double.valueOf(query.getDouble(query.getColumnIndex(ResponseConstants.LONGITUDE)));
                ContentValues contentValues = new ContentValues();
                contentValues.put("device_id", aVar.a(string));
                contentValues.put(ResponseConstants.LATITUDE, aVar.a(String.valueOf(valueOf)));
                contentValues.put(ResponseConstants.LONGITUDE, aVar.a(String.valueOf(valueOf2)));
                String[] strArr = {String.valueOf(i2)};
                sQLiteDatabase.update("beacon_request", contentValues, "id = ?", strArr);
                query.moveToNext();
            }
            query.close();
        }
    }

    private void c(SQLiteDatabase sQLiteDatabase, @NonNull com.salesforce.marketingcloud.e.a aVar) {
        Cursor query = sQLiteDatabase.query("geofence_request", c.a, null, null, null, null, null);
        if (query != null) {
            query.moveToFirst();
            while (!query.isAfterLast()) {
                int i2 = query.getInt(query.getColumnIndex("id"));
                String string = query.getString(query.getColumnIndex("device_id"));
                Double valueOf = Double.valueOf(query.getDouble(query.getColumnIndex(ResponseConstants.LATITUDE)));
                Double valueOf2 = Double.valueOf(query.getDouble(query.getColumnIndex(ResponseConstants.LONGITUDE)));
                ContentValues contentValues = new ContentValues();
                contentValues.put("device_id", aVar.a(string));
                contentValues.put(ResponseConstants.LATITUDE, aVar.a(String.valueOf(valueOf)));
                contentValues.put(ResponseConstants.LONGITUDE, aVar.a(String.valueOf(valueOf2)));
                String[] strArr = {String.valueOf(i2)};
                sQLiteDatabase.update("geofence_request", contentValues, "id = ?", strArr);
                query.moveToNext();
            }
            query.close();
        }
    }

    private void d(SQLiteDatabase sQLiteDatabase, @NonNull com.salesforce.marketingcloud.e.a aVar) {
        Cursor query = sQLiteDatabase.query(ResponseConstants.MESSAGES, d.a, null, null, null, null, null);
        if (query != null) {
            query.moveToFirst();
            while (!query.isAfterLast()) {
                String string = query.getString(query.getColumnIndex("id"));
                String string2 = query.getString(query.getColumnIndex("alert"));
                String string3 = query.getString(query.getColumnIndex("open_direct"));
                String string4 = query.getString(query.getColumnIndex("category"));
                String string5 = query.getString(query.getColumnIndex("url"));
                String string6 = query.getString(query.getColumnIndex(ResponseConstants.SUBJECT));
                Map c2 = g.c(new String(query.getBlob(query.getColumnIndex("keys"))));
                ContentValues contentValues = new ContentValues();
                contentValues.put("alert", aVar.a(string2));
                contentValues.put("open_direct", aVar.a(string3));
                contentValues.put("category", aVar.a(string4));
                contentValues.put("url", aVar.a(string5));
                contentValues.put(ResponseConstants.SUBJECT, aVar.a(string6));
                contentValues.put("keys", aVar.a(g.a(c2)));
                String[] strArr = {string};
                sQLiteDatabase.update(ResponseConstants.MESSAGES, contentValues, "id = ?", strArr);
                query.moveToNext();
            }
            query.close();
        }
    }

    private void e(SQLiteDatabase sQLiteDatabase, @NonNull com.salesforce.marketingcloud.e.a aVar) {
        Cursor query = sQLiteDatabase.query("regions", e.a, null, null, null, null, null);
        if (query != null) {
            query.moveToFirst();
            while (!query.isAfterLast()) {
                String string = query.getString(query.getColumnIndex("id"));
                Double valueOf = Double.valueOf(query.getDouble(query.getColumnIndex(ResponseConstants.LATITUDE)));
                Double valueOf2 = Double.valueOf(query.getDouble(query.getColumnIndex(ResponseConstants.LONGITUDE)));
                String string2 = query.getString(query.getColumnIndex("beacon_guid"));
                String string3 = query.getString(query.getColumnIndex("description"));
                String string4 = query.getString(query.getColumnIndex(ResponseConstants.NAME));
                ContentValues contentValues = new ContentValues();
                contentValues.put(ResponseConstants.LATITUDE, aVar.a(String.valueOf(valueOf)));
                contentValues.put(ResponseConstants.LONGITUDE, aVar.a(String.valueOf(valueOf2)));
                contentValues.put("beacon_guid", aVar.a(string2));
                contentValues.put("description", aVar.a(string3));
                contentValues.put(ResponseConstants.NAME, aVar.a(string4));
                String[] strArr = {string};
                sQLiteDatabase.update("regions", contentValues, "id = ?", strArr);
                query.moveToNext();
            }
            query.close();
        }
    }

    private void f(SQLiteDatabase sQLiteDatabase, @NonNull com.salesforce.marketingcloud.e.a aVar) {
        Cursor query = sQLiteDatabase.query("registration", f.a, null, null, null, null, null);
        if (query != null) {
            query.moveToFirst();
            while (!query.isAfterLast()) {
                int i2 = query.getInt(query.getColumnIndex("id"));
                String string = query.getString(query.getColumnIndex("device_id"));
                String string2 = query.getString(query.getColumnIndex("subscriber_key"));
                String string3 = query.getString(query.getColumnIndex("et_app_id"));
                String string4 = query.getString(query.getColumnIndex("email"));
                String string5 = query.getString(query.getColumnIndex("gcm_sender_id"));
                Set d2 = g.d(new String(query.getBlob(query.getColumnIndex(EditableListing.FIELD_TAGS))));
                Map c2 = g.c(new String(query.getBlob(query.getColumnIndex(ResponseConstants.ATTRIBUTES))));
                ContentValues contentValues = new ContentValues();
                contentValues.put("device_id", aVar.a(string));
                if (query.getColumnIndex("device_token") >= 0) {
                    contentValues.put("device_token", "");
                }
                contentValues.put("subscriber_key", aVar.a(string2));
                contentValues.put("et_app_id", aVar.a(string3));
                contentValues.put("email", aVar.a(string4));
                contentValues.put("gcm_sender_id", aVar.a(string5));
                contentValues.put(EditableListing.FIELD_TAGS, aVar.a(g.a(d2)));
                contentValues.put(ResponseConstants.ATTRIBUTES, aVar.a(g.a(c2)));
                String[] strArr = {String.valueOf(i2)};
                sQLiteDatabase.update("registration", contentValues, "id = ?", strArr);
                query.moveToNext();
            }
            query.close();
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            j.b(c, "onCreate", new Object[0]);
            sQLiteDatabase.execSQL("CREATE TABLE registration (id INTEGER PRIMARY KEY AUTOINCREMENT, platform VARCHAR, device_id VARCHAR, subscriber_key VARCHAR, et_app_id VARCHAR, email VARCHAR, badge INTEGER, timezone INTEGER, dst SMALLINT, tags VARCHAR, attributes VARCHAR, platform_version VARCHAR, push_enabled SMALLINT, location_enabled SMALLINT, last_sent BIGINT, hwid VARCHAR, gcm_sender_id VARCHAR, locale VARCHAR );");
            sQLiteDatabase.execSQL("CREATE TABLE beacon_request (id INTEGER PRIMARY KEY AUTOINCREMENT, device_id VARCHAR, latitude DOUBLE PRECISION, longitude DOUBLE PRECISION );");
            sQLiteDatabase.execSQL("CREATE TABLE geofence_request (id INTEGER PRIMARY KEY AUTOINCREMENT, device_id VARCHAR, latitude DOUBLE PRECISION, longitude DOUBLE PRECISION );");
            sQLiteDatabase.execSQL("CREATE TABLE messages (id VARCHAR PRIMARY KEY, alert VARCHAR, sound VARCHAR, badge VARCHAR, open_direct VARCHAR, category VARCHAR, start_date VARCHAR, end_date VARCHAR, message_type INTEGER, content_type INTEGER, page_id VARCHAR, url VARCHAR, subject VARCHAR, site_id VARCHAR, read SMALLINT, custom VARCHAR, keys VARCHAR, period_show_count INTEGER, last_shown_date VARCHAR, next_allowed_show VARCHAR, show_count INTEGER, message_limit INTEGER, rolling_period SMALLINT, period_type INTEGER, number_of_periods INTEGER, messages_per_period INTEGER, message_deleted SMALLINT, min_tripped INTEGER, proximity INTEGER, ephemeral_message SMALLINT, has_entered SMALLINT, notify_id INTEGER, loiter_seconds INTEGER, entry_time BIGINT );");
            sQLiteDatabase.execSQL("CREATE TABLE regions (id VARCHAR PRIMARY KEY, latitude DOUBLE PRECISION, longitude DOUBLE PRECISION, radius INTEGER, active SMALLINT, beacon_guid VARCHAR, beacon_major INTEGER, beacon_minor INTEGER, entry_count INTEGER, exit_count INTEGER, description VARCHAR, name VARCHAR, location_type INTEGER, has_entered SMALLINT );");
            sQLiteDatabase.execSQL("CREATE TABLE region_message (id INTEGER PRIMARY KEY AUTOINCREMENT, region_id VARCHAR, message_id VARCHAR );");
            sQLiteDatabase.execSQL("CREATE TABLE analytic_item (id INTEGER PRIMARY KEY AUTOINCREMENT, device_id VARCHAR, et_app_id VARCHAR, event_date VARCHAR, analytic_types VARCHAR, object_ids VARCHAR, value INTEGER, ready_to_send SMALLINT, pi_app_key VARCHAR, last_sent BIGINT, json_payload VARCHAR);");
        } catch (Exception e2) {
            j.c(c, e2, "Can't create database", new Object[0]);
            throw new RuntimeException(e2);
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        if (i2 < 2) {
            try {
                j.b(c, "Updating DB from %d to 2", Integer.valueOf(i2));
                sQLiteDatabase.execSQL("ALTER TABLE registration ADD COLUMN last_sent BIGINT;");
                sQLiteDatabase.execSQL("ALTER TABLE registration ADD COLUMN hwid VARCHAR;");
                sQLiteDatabase.execSQL("ALTER TABLE registration ADD COLUMN gcm_sender_id VARCHAR;");
                sQLiteDatabase.execSQL("ALTER TABLE messages ADD COLUMN message_deleted SMALLINT;");
            } catch (Exception e2) {
                j.c(c, e2, "Can't update database. blow it away and re-create", new Object[0]);
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS registration");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS beacon_request");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS geofence_request");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS messages");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS regions");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS region_message");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS analytic_item");
                onCreate(sQLiteDatabase);
            }
        }
        if (i2 < 3) {
            sQLiteDatabase.execSQL("ALTER TABLE regions ADD COLUMN beacon_guid VARCHAR;");
            sQLiteDatabase.execSQL("ALTER TABLE regions ADD COLUMN beacon_major INTEGER;");
            sQLiteDatabase.execSQL("ALTER TABLE regions ADD COLUMN beacon_minor INTEGER;");
            sQLiteDatabase.execSQL("ALTER TABLE regions ADD COLUMN entry_count INTEGER;");
            sQLiteDatabase.execSQL("ALTER TABLE regions ADD COLUMN exit_count INTEGER;");
            sQLiteDatabase.execSQL("ALTER TABLE regions ADD COLUMN description VARCHAR;");
            sQLiteDatabase.execSQL("ALTER TABLE regions ADD COLUMN location_type INTEGER;");
            sQLiteDatabase.execSQL("ALTER TABLE regions ADD COLUMN name VARCHAR;");
            sQLiteDatabase.execSQL("ALTER TABLE regions ADD COLUMN has_entered SMALLINT;");
            sQLiteDatabase.execSQL("ALTER TABLE messages ADD COLUMN min_tripped INTEGER;");
            sQLiteDatabase.execSQL("ALTER TABLE messages ADD COLUMN proximity INTEGER;");
            sQLiteDatabase.execSQL("ALTER TABLE messages ADD COLUMN ephemeral_message SMALLINT;");
            sQLiteDatabase.execSQL("ALTER TABLE messages ADD COLUMN has_entered SMALLINT;");
            sQLiteDatabase.execSQL("ALTER TABLE messages ADD COLUMN notify_id INTEGER;");
            sQLiteDatabase.execSQL("ALTER TABLE messages ADD COLUMN loiter_seconds INTEGER;");
            sQLiteDatabase.execSQL("ALTER TABLE messages ADD COLUMN entry_time BIGINT;");
            sQLiteDatabase.execSQL("CREATE TABLE analytic_item (id INTEGER PRIMARY KEY AUTOINCREMENT, device_id VARCHAR, et_app_id VARCHAR, event_date VARCHAR, analytic_types VARCHAR, object_ids VARCHAR, value INTEGER, ready_to_send SMALLINT, pi_app_key VARCHAR, last_sent BIGINT, json_payload VARCHAR);");
            sQLiteDatabase.execSQL("CREATE TABLE beacon_request (id INTEGER PRIMARY KEY AUTOINCREMENT, device_id VARCHAR, latitude DOUBLE PRECISION, longitude DOUBLE PRECISION );");
        }
        if (i2 < 4) {
            sQLiteDatabase.execSQL("ALTER TABLE registration ADD COLUMN locale TEXT;");
        }
        if (i2 < 5) {
            sQLiteDatabase.execSQL("ALTER TABLE messages ADD COLUMN category VARCHAR;");
        }
        if (i2 < 6) {
            sQLiteDatabase.execSQL("ALTER TABLE analytic_item ADD COLUMN pi_app_key VARCHAR;");
        }
        if (i2 < 7) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("pi_app_key", null);
            j.b(c, "PI App Rows updated to null: %d", Integer.valueOf(sQLiteDatabase.update("analytic_item", contentValues, "pi_app_key = ? ", new String[]{""})));
            a(sQLiteDatabase, this.o);
            b(sQLiteDatabase, this.o);
            c(sQLiteDatabase, this.o);
            d(sQLiteDatabase, this.o);
            e(sQLiteDatabase, this.o);
            f(sQLiteDatabase, this.o);
        }
        if (i2 < 8) {
            sQLiteDatabase.execSQL("ALTER TABLE analytic_item ADD COLUMN json_payload VARCHAR;");
        }
        if (i2 < 9) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS location_update");
        }
        if (i2 < 10) {
            j.b(c, "Deleting magic fence to force messages fetch.", new Object[0]);
            if (sQLiteDatabase.delete("regions", "id = ?", new String[]{"~~m@g1c_f3nc3~~"}) > 0) {
                j.b(c, "Found and deleted magic fence.", new Object[0]);
            }
        }
    }
}
