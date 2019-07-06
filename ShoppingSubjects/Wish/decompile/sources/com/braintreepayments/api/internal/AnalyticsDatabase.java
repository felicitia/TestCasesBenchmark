package com.braintreepayments.api.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class AnalyticsDatabase extends SQLiteOpenHelper {
    public static AnalyticsDatabase getInstance(Context context) {
        return new AnalyticsDatabase(context, "braintree-analytics.db", null, 1);
    }

    public AnalyticsDatabase(Context context, String str, CursorFactory cursorFactory, int i) {
        super(context, "braintree-analytics.db", cursorFactory, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table analytics(_id integer primary key autoincrement, event text not null, timestamp long not null, meta_json text not null);");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("drop table if exists analytics");
        onCreate(sQLiteDatabase);
    }

    public void addEvent(AnalyticsEvent analyticsEvent) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("event", analyticsEvent.event);
        contentValues.put("timestamp", Long.valueOf(analyticsEvent.timestamp));
        contentValues.put("meta_json", analyticsEvent.metadata.toString());
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.insert("analytics", null, contentValues);
        writableDatabase.close();
    }

    public void removeEvents(List<AnalyticsEvent> list) {
        StringBuilder sb = new StringBuilder("_id");
        sb.append(" in (");
        String[] strArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strArr[i] = Integer.toString(((AnalyticsEvent) list.get(i)).id);
            sb.append("?");
            if (i < list.size() - 1) {
                sb.append(",");
            } else {
                sb.append(")");
            }
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete("analytics", sb.toString(), strArr);
        writableDatabase.close();
    }

    public List<List<AnalyticsEvent>> getPendingRequests() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        SQLiteDatabase sQLiteDatabase = readableDatabase;
        Cursor query = sQLiteDatabase.query(false, "analytics", new String[]{"group_concat(_id)", "group_concat(event)", "group_concat(timestamp)", "meta_json"}, null, null, "meta_json", null, "_id asc", null);
        ArrayList arrayList = new ArrayList();
        while (query.moveToNext()) {
            ArrayList arrayList2 = new ArrayList();
            String[] split = query.getString(0).split(",");
            String[] split2 = query.getString(1).split(",");
            String[] split3 = query.getString(2).split(",");
            for (int i = 0; i < split2.length; i++) {
                try {
                    AnalyticsEvent analyticsEvent = new AnalyticsEvent();
                    analyticsEvent.id = Integer.valueOf(split[i]).intValue();
                    analyticsEvent.event = split2[i];
                    analyticsEvent.timestamp = Long.valueOf(split3[i]).longValue();
                    analyticsEvent.metadata = new JSONObject(query.getString(query.getColumnIndex("meta_json")));
                    arrayList2.add(analyticsEvent);
                } catch (JSONException unused) {
                }
            }
            arrayList.add(arrayList2);
        }
        query.close();
        readableDatabase.close();
        return arrayList;
    }
}
