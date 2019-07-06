package com.facebook.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;
import com.etsy.android.lib.models.ResponseConstants;
import com.facebook.FacebookSdkNotInitializedException;
import com.facebook.f;
import java.util.Collection;
import java.util.List;

/* compiled from: Validate */
public final class aa {
    private static final String a = "com.facebook.internal.aa";

    public static void a(Object obj, String str) {
        if (obj == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Argument '");
            sb.append(str);
            sb.append("' cannot be null");
            throw new NullPointerException(sb.toString());
        }
    }

    public static <T> void a(Collection<T> collection, String str) {
        if (collection.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Container '");
            sb.append(str);
            sb.append("' cannot be empty");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public static <T> void b(Collection<T> collection, String str) {
        a((Object) collection, str);
        for (T t : collection) {
            if (t == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Container '");
                sb.append(str);
                sb.append("' cannot contain null values");
                throw new NullPointerException(sb.toString());
            }
        }
    }

    public static <T> void c(Collection<T> collection, String str) {
        b(collection, str);
        a(collection, str);
    }

    public static void a(String str, String str2) {
        if (z.a(str)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Argument '");
            sb.append(str2);
            sb.append("' cannot be null or empty");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public static void a() {
        if (!f.a()) {
            throw new FacebookSdkNotInitializedException("The SDK has not been initialized, make sure to call FacebookSdk.sdkInitialize() first.");
        }
    }

    public static String b() {
        String j = f.j();
        if (j != null) {
            return j;
        }
        throw new IllegalStateException("No App ID found, please set the App ID.");
    }

    public static String c() {
        String l = f.l();
        if (l != null) {
            return l;
        }
        throw new IllegalStateException("No Client Token found, please set the Client Token.");
    }

    public static void a(Context context) {
        a(context, true);
    }

    public static void a(Context context, boolean z) {
        a((Object) context, ResponseConstants.CONTEXT);
        if (context.checkCallingOrSelfPermission("android.permission.INTERNET") != -1) {
            return;
        }
        if (z) {
            throw new IllegalStateException("No internet permissions granted for the app, please add <uses-permission android:name=\"android.permission.INTERNET\" /> to your AndroidManifest.xml.");
        }
        Log.w(a, "No internet permissions granted for the app, please add <uses-permission android:name=\"android.permission.INTERNET\" /> to your AndroidManifest.xml.");
    }

    public static boolean b(Context context) {
        return a(context, "android.permission.ACCESS_COARSE_LOCATION") || a(context, "android.permission.ACCESS_FINE_LOCATION");
    }

    public static boolean a(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    public static void c(Context context) {
        b(context, true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void b(android.content.Context r3, boolean r4) {
        /*
            java.lang.String r0 = "context"
            a(r3, r0)
            android.content.pm.PackageManager r0 = r3.getPackageManager()
            if (r0 == 0) goto L_0x0018
            android.content.ComponentName r1 = new android.content.ComponentName
            java.lang.String r2 = "com.facebook.FacebookActivity"
            r1.<init>(r3, r2)
            r3 = 1
            android.content.pm.ActivityInfo r3 = r0.getActivityInfo(r1, r3)     // Catch:{ NameNotFoundException -> 0x0018 }
            goto L_0x0019
        L_0x0018:
            r3 = 0
        L_0x0019:
            if (r3 != 0) goto L_0x002c
            if (r4 == 0) goto L_0x0025
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r4 = "FacebookActivity is not declared in the AndroidManifest.xml, please add com.facebook.FacebookActivity to your AndroidManifest.xml file. See https://developers.facebook.com/docs/android/getting-started for more info."
            r3.<init>(r4)
            throw r3
        L_0x0025:
            java.lang.String r3 = a
            java.lang.String r4 = "FacebookActivity is not declared in the AndroidManifest.xml, please add com.facebook.FacebookActivity to your AndroidManifest.xml file. See https://developers.facebook.com/docs/android/getting-started for more info."
            android.util.Log.w(r3, r4)
        L_0x002c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.aa.b(android.content.Context, boolean):void");
    }

    public static boolean d(Context context) {
        List<ResolveInfo> list;
        a((Object) context, ResponseConstants.CONTEXT);
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addCategory("android.intent.category.BROWSABLE");
            StringBuilder sb = new StringBuilder();
            sb.append("fb");
            sb.append(f.j());
            sb.append("://authorize");
            intent.setData(Uri.parse(sb.toString()));
            list = packageManager.queryIntentActivities(intent, 64);
        } else {
            list = null;
        }
        boolean z = false;
        if (list != null) {
            boolean z2 = false;
            for (ResolveInfo resolveInfo : list) {
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                if (!activityInfo.name.equals("com.facebook.CustomTabActivity") || !activityInfo.packageName.equals(context.getPackageName())) {
                    return false;
                }
                z2 = true;
            }
            z = z2;
        }
        return z;
    }

    public static void e(Context context) {
        a((Object) context, ResponseConstants.CONTEXT);
        String b = b();
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("com.facebook.app.FacebookContentProvider");
            sb.append(b);
            String sb2 = sb.toString();
            if (packageManager.resolveContentProvider(sb2, 0) == null) {
                throw new IllegalStateException(String.format("A ContentProvider for this app was not set up in the AndroidManifest.xml, please add %s as a provider to your AndroidManifest.xml file. See https://developers.facebook.com/docs/sharing/android for more info.", new Object[]{sb2}));
            }
        }
    }
}
