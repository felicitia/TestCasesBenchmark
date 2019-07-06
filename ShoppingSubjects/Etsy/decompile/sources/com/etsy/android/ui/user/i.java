package com.etsy.android.ui.user;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: UserBadgeCountManager */
public class i {
    public static final AtomicInteger a = new AtomicInteger();
    public static final AtomicInteger b = new AtomicInteger();

    public static void a(int i) {
        a.set(i);
    }

    public static int a() {
        return a.get();
    }

    public static void a(Context context) {
        int max = Math.max(0, a.get() - 1);
        SharedPreferencesUtility.b(context, max);
        a.set(max);
        c(context);
    }

    public static void b(int i) {
        b.set(i);
    }

    public static int b() {
        return b.get();
    }

    public static void b(Context context) {
        int max = Math.max(0, b.get() - 1);
        SharedPreferencesUtility.c(context, max);
        b.set(max);
        c(context);
    }

    public static void c(Context context) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.etsy.android.badge.count.UPDATE"));
    }
}
