package com.google.android.gms.ads.internal.gmsg;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.view.View;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.ack;
import java.util.ArrayList;
import java.util.List;

@VisibleForTesting
public final class f {
    private final Context a;
    private final ack b;
    private final View c;

    public f(Context context, ack ack, View view) {
        this.a = context;
        this.b = ack;
        this.c = view;
    }

    private static Intent a(Intent intent, ResolveInfo resolveInfo) {
        Intent intent2 = new Intent(intent);
        intent2.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        return intent2;
    }

    private static Intent a(Uri uri) {
        if (uri == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(ErrorDialogData.BINDER_CRASH);
        intent.setData(uri);
        intent.setAction("android.intent.action.VIEW");
        return intent;
    }

    @VisibleForTesting
    private final ResolveInfo a(Intent intent) {
        return a(intent, new ArrayList<>());
    }

    @VisibleForTesting
    private final ResolveInfo a(Intent intent, ArrayList<ResolveInfo> arrayList) {
        ResolveInfo resolveInfo = null;
        try {
            PackageManager packageManager = this.a.getPackageManager();
            if (packageManager == null) {
                return null;
            }
            List queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
            ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
            if (queryIntentActivities != null && resolveActivity != null) {
                int i = 0;
                while (true) {
                    if (i >= queryIntentActivities.size()) {
                        break;
                    }
                    ResolveInfo resolveInfo2 = (ResolveInfo) queryIntentActivities.get(i);
                    if (resolveActivity != null && resolveActivity.activityInfo.name.equals(resolveInfo2.activityInfo.name)) {
                        resolveInfo = resolveActivity;
                        break;
                    }
                    i++;
                }
            }
            arrayList.addAll(queryIntentActivities);
            return resolveInfo;
        } catch (Throwable th) {
            ao.i().a(th, "OpenSystemBrowserHandler.getDefaultBrowserResolverForIntent");
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00bd  */
    @com.google.android.gms.common.util.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.content.Intent a(java.util.Map<java.lang.String, java.lang.String> r12) {
        /*
            r11 = this;
            android.content.Context r0 = r11.a
            java.lang.String r1 = "activity"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.app.ActivityManager r0 = (android.app.ActivityManager) r0
            java.lang.String r1 = "u"
            java.lang.Object r1 = r12.get(r1)
            java.lang.String r1 = (java.lang.String) r1
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            r3 = 0
            if (r2 == 0) goto L_0x001a
            return r3
        L_0x001a:
            android.content.Context r2 = r11.a
            com.google.android.gms.internal.ads.ack r4 = r11.b
            android.view.View r5 = r11.c
            java.lang.String r1 = com.google.android.gms.ads.internal.gmsg.e.a(r2, r4, r1, r5, r3)
            android.net.Uri r1 = android.net.Uri.parse(r1)
            java.lang.String r2 = "use_first_package"
            java.lang.Object r2 = r12.get(r2)
            java.lang.String r2 = (java.lang.String) r2
            boolean r2 = java.lang.Boolean.parseBoolean(r2)
            java.lang.String r4 = "use_running_process"
            java.lang.Object r4 = r12.get(r4)
            java.lang.String r4 = (java.lang.String) r4
            boolean r4 = java.lang.Boolean.parseBoolean(r4)
            java.lang.String r5 = "use_custom_tabs"
            java.lang.Object r12 = r12.get(r5)
            java.lang.String r12 = (java.lang.String) r12
            boolean r12 = java.lang.Boolean.parseBoolean(r12)
            r5 = 0
            if (r12 != 0) goto L_0x0064
            com.google.android.gms.internal.ads.akb<java.lang.Boolean> r12 = com.google.android.gms.internal.ads.akl.cM
            com.google.android.gms.internal.ads.akj r6 = com.google.android.gms.internal.ads.ajh.f()
            java.lang.Object r12 = r6.a(r12)
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 == 0) goto L_0x0062
            goto L_0x0064
        L_0x0062:
            r12 = r5
            goto L_0x0065
        L_0x0064:
            r12 = 1
        L_0x0065:
            java.lang.String r6 = "http"
            java.lang.String r7 = r1.getScheme()
            boolean r6 = r6.equalsIgnoreCase(r7)
            if (r6 == 0) goto L_0x0080
            android.net.Uri$Builder r3 = r1.buildUpon()
            java.lang.String r6 = "https"
        L_0x0077:
            android.net.Uri$Builder r3 = r3.scheme(r6)
            android.net.Uri r3 = r3.build()
            goto L_0x0093
        L_0x0080:
            java.lang.String r6 = "https"
            java.lang.String r7 = r1.getScheme()
            boolean r6 = r6.equalsIgnoreCase(r7)
            if (r6 == 0) goto L_0x0093
            android.net.Uri$Builder r3 = r1.buildUpon()
            java.lang.String r6 = "http"
            goto L_0x0077
        L_0x0093:
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            android.content.Intent r1 = a(r1)
            android.content.Intent r3 = a(r3)
            if (r12 == 0) goto L_0x00b2
            com.google.android.gms.ads.internal.ao.e()
            android.content.Context r12 = r11.a
            com.google.android.gms.internal.ads.hd.b(r12, r1)
            com.google.android.gms.ads.internal.ao.e()
            android.content.Context r12 = r11.a
            com.google.android.gms.internal.ads.hd.b(r12, r3)
        L_0x00b2:
            android.content.pm.ResolveInfo r12 = r11.a(r1, r6)
            if (r12 == 0) goto L_0x00bd
            android.content.Intent r12 = a(r1, r12)
            return r12
        L_0x00bd:
            if (r3 == 0) goto L_0x00d0
            android.content.pm.ResolveInfo r12 = r11.a(r3)
            if (r12 == 0) goto L_0x00d0
            android.content.Intent r12 = a(r1, r12)
            android.content.pm.ResolveInfo r3 = r11.a(r12)
            if (r3 == 0) goto L_0x00d0
            return r12
        L_0x00d0:
            int r12 = r6.size()
            if (r12 != 0) goto L_0x00d7
            return r1
        L_0x00d7:
            if (r4 == 0) goto L_0x0114
            if (r0 == 0) goto L_0x0114
            java.util.List r12 = r0.getRunningAppProcesses()
            if (r12 == 0) goto L_0x0114
            r0 = r6
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            int r3 = r0.size()
            r4 = r5
        L_0x00e9:
            if (r4 >= r3) goto L_0x0114
            java.lang.Object r7 = r0.get(r4)
            int r4 = r4 + 1
            android.content.pm.ResolveInfo r7 = (android.content.pm.ResolveInfo) r7
            java.util.Iterator r8 = r12.iterator()
        L_0x00f7:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x00e9
            java.lang.Object r9 = r8.next()
            android.app.ActivityManager$RunningAppProcessInfo r9 = (android.app.ActivityManager.RunningAppProcessInfo) r9
            java.lang.String r9 = r9.processName
            android.content.pm.ActivityInfo r10 = r7.activityInfo
            java.lang.String r10 = r10.packageName
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x00f7
            android.content.Intent r12 = a(r1, r7)
            return r12
        L_0x0114:
            if (r2 == 0) goto L_0x0121
            java.lang.Object r12 = r6.get(r5)
            android.content.pm.ResolveInfo r12 = (android.content.pm.ResolveInfo) r12
            android.content.Intent r12 = a(r1, r12)
            return r12
        L_0x0121:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.gmsg.f.a(java.util.Map):android.content.Intent");
    }
}
