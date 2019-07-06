package com.facebook.marketing.internal;

import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.facebook.f;
import com.facebook.f.a;

public final class MarketingInitProvider extends ContentProvider {
    private static final String TAG = "MarketingInitProvider";

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    public boolean onCreate() {
        try {
            if (!f.a()) {
                f.a(getContext(), (a) new a() {
                    public void a() {
                        MarketingInitProvider.this.setupCodeless();
                    }
                });
            } else {
                setupCodeless();
            }
        } catch (Exception e) {
            Log.i(TAG, "Failed to auto initialize the Marketing SDK", e);
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void setupCodeless() {
        com.facebook.marketing.a.a((Application) f.f(), f.j());
        new a((Application) f.f(), f.j()).a();
    }
}
