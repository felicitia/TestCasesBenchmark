package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.annotation.GuardedBy;
import android.util.Log;
import com.etsy.android.lib.models.ResponseConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class gf {
    private static final ConcurrentHashMap<Uri, gf> a = new ConcurrentHashMap<>();
    private static final String[] i = {ResponseConstants.KEY, ResponseConstants.VALUE};
    private final ContentResolver b;
    private final Uri c;
    private final ContentObserver d;
    private final Object e = new Object();
    private volatile Map<String, String> f;
    private final Object g = new Object();
    @GuardedBy("listenersLock")
    private final List<gh> h = new ArrayList();

    private gf(ContentResolver contentResolver, Uri uri) {
        this.b = contentResolver;
        this.c = uri;
        this.d = new gg(this, null);
    }

    public static gf a(ContentResolver contentResolver, Uri uri) {
        gf gfVar = (gf) a.get(uri);
        if (gfVar == null) {
            gf gfVar2 = new gf(contentResolver, uri);
            gf gfVar3 = (gf) a.putIfAbsent(uri, gfVar2);
            if (gfVar3 == null) {
                gfVar2.b.registerContentObserver(gfVar2.c, false, gfVar2.d);
                return gfVar2;
            }
            gfVar = gfVar3;
        }
        return gfVar;
    }

    private final Map<String, String> c() {
        Cursor query;
        try {
            HashMap hashMap = new HashMap();
            query = this.b.query(this.c, i, null, null, null);
            if (query == null) {
                return hashMap;
            }
            while (query.moveToNext()) {
                hashMap.put(query.getString(0), query.getString(1));
            }
            query.close();
            return hashMap;
        } catch (SQLiteException | SecurityException unused) {
            Log.e("ConfigurationContentLoader", "PhenotypeFlag unable to load ContentProvider, using default values");
            return null;
        } catch (Throwable th) {
            query.close();
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public final void d() {
        synchronized (this.g) {
            for (gh a2 : this.h) {
                a2.a();
            }
        }
    }

    public final Map<String, String> a() {
        Map<String, String> c2 = gi.a("gms:phenotype:phenotype_flag:debug_disable_caching", false) ? c() : this.f;
        if (c2 == null) {
            synchronized (this.e) {
                Map<String, String> map = this.f;
                if (map == null) {
                    map = c();
                    this.f = map;
                }
            }
        }
        return c2 != null ? c2 : Collections.emptyMap();
    }

    public final void b() {
        synchronized (this.e) {
            this.f = null;
        }
    }
}
