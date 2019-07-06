package com.etsy.android.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.etsy.android.contentproviders.a;
import com.etsy.android.lib.convos.c;
import com.etsy.android.lib.core.x;
import com.etsy.android.lib.messaging.g;
import com.etsy.android.lib.util.ae;
import com.etsy.android.ui.homescreen.b;

/* compiled from: SessionManager */
public class f implements x {
    static boolean a = true;
    private final com.etsy.android.lib.push.f b;

    f(com.etsy.android.lib.push.f fVar) {
        this.b = fVar;
    }

    public void a(Context context, boolean z) {
        a.d(context);
        c.a(context);
        b(context, z);
        g.a(context);
        b.a().b();
    }

    public void a() {
        this.b.c();
    }

    private void b(Context context, boolean z) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        launchIntentForPackage.putExtra("HOME_RESET", true);
        launchIntentForPackage.putExtra("FORCED_SIGNOUT", z);
        launchIntentForPackage.addFlags(67108864);
        if (a) {
            launchIntentForPackage.setPackage(null);
        }
        context.startActivity(launchIntentForPackage);
    }

    public static boolean a(Intent intent) {
        return "android.intent.action.MAIN".equals(intent.getAction()) && intent.getCategories() != null && intent.getCategories().contains("android.intent.category.LAUNCHER");
    }

    public void a(Intent intent, Activity activity) {
        b(intent);
        a(activity);
    }

    private static void b(Intent intent) {
        if (a(intent)) {
            a = intent.getPackage() == null;
        }
    }

    private void a(Activity activity) {
        ae.a(false);
        com.etsy.android.lib.config.a.a().c(activity.getApplicationContext());
        com.etsy.android.lib.messaging.a.a(activity);
        this.b.b();
    }
}
