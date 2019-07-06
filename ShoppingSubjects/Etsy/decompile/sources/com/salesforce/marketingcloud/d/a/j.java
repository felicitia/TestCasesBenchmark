package com.salesforce.marketingcloud.d.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.VisibleForTesting;
import com.etsy.android.lib.models.ResponseConstants;
import com.salesforce.marketingcloud.d.a.b.b;
import com.salesforce.marketingcloud.d.a.b.c;
import com.salesforce.marketingcloud.d.a.b.d;
import com.salesforce.marketingcloud.d.a.b.e;
import com.salesforce.marketingcloud.d.a.b.f;
import com.salesforce.marketingcloud.e.a;
import java.util.Locale;

public final class j extends SQLiteOpenHelper {
    @VisibleForTesting
    public static final int a = 7;
    private static final String b = "mcsdk_%s.db";
    private static final String c = com.salesforce.marketingcloud.j.a(j.class);
    private final Context d;
    private final a e;
    private boolean f;
    private SQLiteDatabase g;

    public j(Context context, a aVar, String str) {
        this(context, aVar, a(str), 7);
    }

    j(Context context, a aVar, String str, int i) {
        super(context, str, null, i);
        this.f = false;
        this.d = context;
        this.e = aVar;
    }

    public static String a(String str) {
        return String.format(Locale.ENGLISH, b, new Object[]{str});
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL(String.format(Locale.ENGLISH, "DROP TABLE IF EXISTS  %s", new Object[]{"cloud_page_messages"}));
            sQLiteDatabase.execSQL(String.format(Locale.ENGLISH, "DROP TABLE IF EXISTS  %s", new Object[]{"region_message"}));
            sQLiteDatabase.execSQL(String.format(Locale.ENGLISH, "DROP TABLE IF EXISTS  %s", new Object[]{"analytic_item"}));
            sQLiteDatabase.execSQL(String.format(Locale.ENGLISH, "DROP TABLE IF EXISTS %s", new Object[]{"regions"}));
            sQLiteDatabase.execSQL(String.format(Locale.ENGLISH, "DROP TABLE IF EXISTS %s", new Object[]{ResponseConstants.MESSAGES}));
            sQLiteDatabase.execSQL(String.format(Locale.ENGLISH, "DROP TABLE IF EXISTS %s", new Object[]{"registration"}));
            sQLiteDatabase.execSQL(String.format(Locale.ENGLISH, "DROP TABLE IF EXISTS %s", new Object[]{"location_table"}));
            sQLiteDatabase.execSQL(String.format(Locale.ENGLISH, "DROP TABLE IF EXISTS %s", new Object[]{"inbox_message_status"}));
        } catch (Exception e2) {
            throw e2;
        }
    }

    public boolean a() {
        return this.f;
    }

    public synchronized SQLiteDatabase b() {
        if (this.g == null) {
            this.g = getWritableDatabase();
        }
        return this.g;
    }

    public void c() {
        SQLiteDatabase b2 = b();
        a(b2);
        b2.execSQL("VACUUM");
        onCreate(b2);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE cloud_page_messages (id VARCHAR PRIMARY KEY, start_date VARCHAR, end_date VARCHAR, message_type INTEGER, content_type INTEGER, url VARCHAR, subject VARCHAR, read SMALLINT, message_deleted SMALLINT, custom VARCHAR, keys VARCHAR, title VARCHAR, alert VARCHAR, sound VARCHAR, mediaUrl VARCHAR, mediaAlt VARCHAR, message_hash VARCHAR, request_id VARCHAR);");
        sQLiteDatabase.execSQL("CREATE TABLE region_message (id INTEGER PRIMARY KEY AUTOINCREMENT, region_id VARCHAR, message_id VARCHAR );");
        sQLiteDatabase.execSQL("CREATE TABLE regions (id VARCHAR PRIMARY KEY, latitude VARCHAR, longitude VARCHAR, radius INTEGER, beacon_guid VARCHAR, beacon_major INTEGER, beacon_minor INTEGER, description VARCHAR, name VARCHAR, location_type INTEGER, is_inside SMALLINT );");
        sQLiteDatabase.execSQL("CREATE TABLE messages (id VARCHAR PRIMARY KEY, title VARCHAR, alert VARCHAR, sound VARCHAR, mediaUrl VARCHAR, mediaAlt VARCHAR, open_direct VARCHAR, start_date VARCHAR, end_date VARCHAR, message_type INTEGER, content_type INTEGER, url VARCHAR, custom VARCHAR, keys VARCHAR, period_show_count INTEGER, last_shown_date VARCHAR, next_allowed_show VARCHAR, show_count INTEGER, message_limit INTEGER, rolling_period SMALLINT, period_type INTEGER, number_of_periods INTEGER, messages_per_period INTEGER, proximity INTEGER, notify_id INTEGER );");
        sQLiteDatabase.execSQL("CREATE TABLE registration (id INTEGER PRIMARY KEY AUTOINCREMENT, platform VARCHAR, subscriber_key VARCHAR, et_app_id VARCHAR, timezone INTEGER, dst SMALLINT, tags VARCHAR, attributes VARCHAR, platform_version VARCHAR, push_enabled SMALLINT, location_enabled SMALLINT, proximity_enabled SMALLINT, hwid VARCHAR, system_token VARCHAR, device_id VARCHAR, app_version VARCHAR, sdk_version VARCHAR, signed_string VARCHAR, locale VARCHAR );");
        sQLiteDatabase.execSQL("CREATE TABLE analytic_item (id INTEGER PRIMARY KEY AUTOINCREMENT, event_date VARCHAR, analytic_product_type INTEGER, analytic_type INTEGER, value INTEGER, ready_to_send SMALLINT, object_ids VARCHAR, json_payload VARCHAR, request_id VARCHAR);");
        sQLiteDatabase.execSQL("CREATE TABLE location_table (id INTEGER PRIMARY KEY CHECK (id = 0), latitude VARCHAR, longitude VARCHAR );");
        sQLiteDatabase.execSQL("CREATE TABLE inbox_message_status (id VARCHAR PRIMARY KEY, status INTEGER);");
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        com.salesforce.marketingcloud.j.d(c, "SQLite database being downgraded from %d to %d", Integer.valueOf(i2), Integer.valueOf(i));
        this.f = true;
        a(sQLiteDatabase);
        onCreate(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 2) {
            com.salesforce.marketingcloud.d.a.b.a.a(sQLiteDatabase, this.d, this.e);
        }
        if (i < 3) {
            b.a(sQLiteDatabase);
        }
        if (i < 4) {
            c.a(sQLiteDatabase);
        }
        if (i < 5) {
            d.a(sQLiteDatabase);
        }
        if (i < 6) {
            e.a(sQLiteDatabase);
        }
        if (i < 7) {
            f.a(sQLiteDatabase);
        }
    }
}
