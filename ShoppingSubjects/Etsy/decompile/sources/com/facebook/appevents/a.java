package com.facebook.appevents;

import android.preference.PreferenceManager;
import android.util.Log;
import com.facebook.f;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* compiled from: AnalyticsUserIDStore */
class a {
    private static final String a = "a";
    private static ReentrantReadWriteLock b = new ReentrantReadWriteLock();
    private static String c = null;
    private static volatile boolean d = false;

    a() {
    }

    public static void a() {
        if (!d) {
            AppEventsLogger.g().execute(new Runnable() {
                public void run() {
                    a.d();
                }
            });
        }
    }

    public static String b() {
        if (!d) {
            Log.w(a, "initStore should have been called before calling setUserID");
            d();
        }
        b.readLock().lock();
        try {
            return c;
        } finally {
            b.readLock().unlock();
        }
    }

    /* access modifiers changed from: private */
    public static void d() {
        if (!d) {
            b.writeLock().lock();
            try {
                if (!d) {
                    c = PreferenceManager.getDefaultSharedPreferences(f.f()).getString("com.facebook.appevents.AnalyticsUserIDStore.userID", null);
                    d = true;
                    b.writeLock().unlock();
                }
            } finally {
                b.writeLock().unlock();
            }
        }
    }
}
