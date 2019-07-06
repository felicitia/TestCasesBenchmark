package com.salesforce.marketingcloud.d.a.b;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import com.etsy.android.lib.models.ResponseConstants;
import com.salesforce.marketingcloud.j;

@RestrictTo({Scope.LIBRARY})
public final class b {
    private static final String a = j.a(b.class);

    private b() {
    }

    public static int a(Cursor cursor) {
        String string = cursor.getString(cursor.getColumnIndex("analytic_types"));
        return string.contains(",") ? string.contains("4") ? 5 : 2 : Integer.valueOf(string.replaceAll("\\[", "").replaceAll("\\]", "").trim()).intValue();
    }

    public static void a(SQLiteDatabase sQLiteDatabase) {
        b(sQLiteDatabase);
        c(sQLiteDatabase);
        d(sQLiteDatabase);
        e(sQLiteDatabase);
        f(sQLiteDatabase);
    }

    private static void b(SQLiteDatabase sQLiteDatabase) {
        String str = "CREATE TABLE analytic_item (id INTEGER PRIMARY KEY AUTOINCREMENT, event_date VARCHAR, analytic_product_type INTEGER, analytic_type INTEGER, value INTEGER, ready_to_send SMALLINT, object_ids VARCHAR, json_payload VARCHAR, request_id VARCHAR)";
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL("CREATE TEMPORARY TABLE analytic_item_temp (id INTEGER PRIMARY KEY AUTOINCREMENT, event_date VARCHAR, analytic_product_type INTEGER, analytic_types VARCHAR, value INTEGER, ready_to_send SMALLINT, object_ids VARCHAR, json_payload VARCHAR)");
            sQLiteDatabase.execSQL("INSERT INTO analytic_item_temp SELECT id,event_date,analytic_product_type,analytic_types,value,ready_to_send,object_ids,json_payload FROM analytic_item");
            sQLiteDatabase.execSQL("DROP TABLE analytic_item");
            sQLiteDatabase.execSQL(str);
            Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM analytic_item_temp", null);
            if (rawQuery != null) {
                if (rawQuery.moveToFirst()) {
                    do {
                        ContentValues contentValues = new ContentValues();
                        try {
                            contentValues.put("id", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("id"))));
                            contentValues.put("event_date", rawQuery.getString(rawQuery.getColumnIndex("event_date")));
                            contentValues.put("analytic_product_type", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("analytic_product_type"))));
                            contentValues.put("analytic_type", Integer.valueOf(a(rawQuery)));
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
            sQLiteDatabase.execSQL("DROP TABLE analytic_item_temp");
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e2) {
            j.c(a, e2, "Failed to upgrade Analytics local storage.  Starting fresh.  Some analytics items may have been lost.", new Object[0]);
            try {
                sQLiteDatabase.execSQL(str);
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

    private static void c(SQLiteDatabase sQLiteDatabase) {
        String str = "CREATE TABLE registration (id INTEGER PRIMARY KEY AUTOINCREMENT, platform VARCHAR, subscriber_key VARCHAR, et_app_id VARCHAR, timezone INTEGER, dst SMALLINT, tags VARCHAR, attributes VARCHAR, platform_version VARCHAR, push_enabled SMALLINT, location_enabled SMALLINT, hwid VARCHAR, system_token VARCHAR, device_id VARCHAR, app_version VARCHAR, sdk_version VARCHAR, locale VARCHAR );";
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL("CREATE TEMPORARY TABLE reg_temp (id INTEGER PRIMARY KEY AUTOINCREMENT, platform VARCHAR, subscriber_key VARCHAR, et_app_id VARCHAR, timezone INTEGER, dst SMALLINT, tags VARCHAR, attributes VARCHAR, platform_version VARCHAR, push_enabled SMALLINT, location_enabled SMALLINT, hwid VARCHAR, system_token VARCHAR, device_id VARCHAR, app_version VARCHAR, sdk_version VARCHAR, locale VARCHAR );");
            String str2 = "id,platform,subscriber_key,et_app_id,timezone,dst,tags,attributes,platform_version,push_enabled,location_enabled,hwid,system_token,device_id,app_version,sdk_version,locale";
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO reg_temp SELECT ");
            sb.append(str2);
            sb.append(" FROM registration");
            sQLiteDatabase.execSQL(sb.toString());
            sQLiteDatabase.execSQL("DROP TABLE registration");
            sQLiteDatabase.execSQL(str);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("INSERT INTO registration SELECT ");
            sb2.append(str2);
            sb2.append(" FROM reg_temp");
            sQLiteDatabase.execSQL(sb2.toString());
            sQLiteDatabase.execSQL("DROP TABLE reg_temp");
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
            j.c(a, e, "Failed to upgrade Registration local storage.  Starting fresh.  Attributes, Tags and ContactKey will be lost.", new Object[0]);
            try {
                sQLiteDatabase.execSQL(str);
                sQLiteDatabase.setTransactionSuccessful();
            } catch (SQLException e2) {
                j.c(a, e2, "Failed to create local storage for Registration.", new Object[0]);
            }
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
        sQLiteDatabase.endTransaction();
    }

    private static void d(SQLiteDatabase sQLiteDatabase) {
        String str = "CREATE TABLE inbox_message_status (id VARCHAR PRIMARY KEY, status INTEGER);";
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL(str);
            Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM cloud_page_messages", null);
            if (rawQuery != null) {
                if (rawQuery.moveToFirst()) {
                    do {
                        boolean z = rawQuery.getInt(rawQuery.getColumnIndex("message_deleted")) == 1;
                        boolean z2 = rawQuery.getInt(rawQuery.getColumnIndex("read")) == 1;
                        String string = rawQuery.getString(rawQuery.getColumnIndex("id"));
                        int i = z ? 2 : z2 ? 1 : 0;
                        ContentValues contentValues = new ContentValues();
                        if (i > 0) {
                            try {
                                contentValues.put("id", string);
                                contentValues.put("status", Integer.valueOf(i));
                                sQLiteDatabase.insert("inbox_message_status", null, contentValues);
                            } catch (Exception e) {
                                j.b(a, e, "Failed to add message %s to inbox_message_status table.", string);
                            }
                        }
                    } while (rawQuery.moveToNext());
                }
                rawQuery.close();
            }
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e2) {
            j.c(a, e2, "Failed to update inbox_message_status table.", new Object[0]);
            try {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS inbox_message_status;");
                sQLiteDatabase.execSQL(str);
                sQLiteDatabase.setTransactionSuccessful();
            } catch (Exception e3) {
                j.c(a, e3, "Failed to create inbox_message_status table.", new Object[0]);
            }
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
        sQLiteDatabase.endTransaction();
    }

    private static void e(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.beginTransaction();
            ContentValues contentValues = new ContentValues(1);
            contentValues.put("message_type", Integer.valueOf(8));
            sQLiteDatabase.update("cloud_page_messages", contentValues, null, null);
            sQLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            j.b(a, e, "Failed to convert CloudPageMessages to an InboxMessages.", new Object[0]);
            try {
                sQLiteDatabase.execSQL("DELETE FROM cloud_page_messages WHERE content_type=2 AND message_type=1;");
                sQLiteDatabase.setTransactionSuccessful();
            } catch (Exception e2) {
                j.c(a, e2, "Failed to remove legacy CloudPage data.", new Object[0]);
            }
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
        sQLiteDatabase.endTransaction();
    }

    private static void f(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL("ALTER TABLE cloud_page_messages ADD COLUMN message_hash VARCHAR;");
            sQLiteDatabase.execSQL("ALTER TABLE cloud_page_messages ADD COLUMN request_id VARCHAR;");
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
            j.c(a, e, "Failed to update cloud_page_messages table.", new Object[0]);
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS cloud_page_messages;");
            sQLiteDatabase.execSQL("CREATE TABLE cloud_page_messages (id VARCHAR PRIMARY KEY, start_date VARCHAR, end_date VARCHAR, message_type INTEGER, content_type INTEGER, url VARCHAR, subject VARCHAR, read SMALLINT, message_deleted SMALLINT, message_hash VARCHAR, request_id VARCHAR)");
            sQLiteDatabase.setTransactionSuccessful();
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
        sQLiteDatabase.endTransaction();
    }
}
