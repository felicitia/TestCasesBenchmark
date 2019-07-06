package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import java.util.List;

@bu
public final class ala implements abg {
    @Nullable
    private CustomTabsSession a;
    @Nullable
    private CustomTabsClient b;
    @Nullable
    private CustomTabsServiceConnection c;
    @Nullable
    private alb d;

    public static boolean a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 0);
        List queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        if (!(queryIntentActivities == null || resolveActivity == null)) {
            for (int i = 0; i < queryIntentActivities.size(); i++) {
                if (resolveActivity.activityInfo.name.equals(((ResolveInfo) queryIntentActivities.get(i)).activityInfo.name)) {
                    return resolveActivity.activityInfo.packageName.equals(abf.a(context));
                }
            }
        }
        return false;
    }

    public final void a() {
        this.b = null;
        this.a = null;
        if (this.d != null) {
            this.d.b();
        }
    }

    public final void a(Activity activity) {
        if (this.c != null) {
            activity.unbindService(this.c);
            this.b = null;
            this.a = null;
            this.c = null;
        }
    }

    public final void a(CustomTabsClient customTabsClient) {
        this.b = customTabsClient;
        this.b.warmup(0);
        if (this.d != null) {
            this.d.a();
        }
    }

    public final void a(alb alb) {
        this.d = alb;
    }

    public final boolean a(Uri uri, Bundle bundle, List<Bundle> list) {
        if (this.b == null) {
            return false;
        }
        if (this.b == null) {
            this.a = null;
        } else if (this.a == null) {
            this.a = this.b.newSession(null);
        }
        CustomTabsSession customTabsSession = this.a;
        if (customTabsSession == null) {
            return false;
        }
        return customTabsSession.mayLaunchUrl(uri, null, null);
    }

    public final void b(Activity activity) {
        if (this.b == null) {
            String a2 = abf.a(activity);
            if (a2 != null) {
                this.c = new zzbfx(this);
                CustomTabsClient.bindCustomTabsService(activity, a2, this.c);
            }
        }
    }
}
