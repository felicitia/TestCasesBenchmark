package com.google.android.gms.gcm;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import java.util.MissingFormatArgumentException;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;

final class d {
    static d a;
    private final Context b;
    private String c;
    private final AtomicInteger d = new AtomicInteger((int) SystemClock.elapsedRealtime());

    private d(Context context) {
        this.b = context.getApplicationContext();
    }

    private final Bundle a() {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = this.b.getPackageManager().getApplicationInfo(this.b.getPackageName(), 128);
        } catch (NameNotFoundException unused) {
            applicationInfo = null;
        }
        return (applicationInfo == null || applicationInfo.metaData == null) ? Bundle.EMPTY : applicationInfo.metaData;
    }

    static synchronized d a(Context context) {
        d dVar;
        synchronized (d.class) {
            if (a == null) {
                a = new d(context);
            }
            dVar = a;
        }
        return dVar;
    }

    static String a(Bundle bundle, String str) {
        String string = bundle.getString(str);
        return string == null ? bundle.getString(str.replace("gcm.n.", "gcm.notification.")) : string;
    }

    private final String b(Bundle bundle, String str) {
        String a2 = a(bundle, str);
        if (!TextUtils.isEmpty(a2)) {
            return a2;
        }
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf("_loc_key");
        String a3 = a(bundle, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        if (TextUtils.isEmpty(a3)) {
            return null;
        }
        Resources resources = this.b.getResources();
        int identifier = resources.getIdentifier(a3, "string", this.b.getPackageName());
        if (identifier == 0) {
            String str2 = "GcmNotification";
            String valueOf3 = String.valueOf(str);
            String valueOf4 = String.valueOf("_loc_key");
            String substring = (valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3)).substring(6);
            StringBuilder sb = new StringBuilder(49 + String.valueOf(substring).length() + String.valueOf(a3).length());
            sb.append(substring);
            sb.append(" resource not found: ");
            sb.append(a3);
            sb.append(" Default value will be used.");
            Log.w(str2, sb.toString());
            return null;
        }
        String valueOf5 = String.valueOf(str);
        String valueOf6 = String.valueOf("_loc_args");
        String a4 = a(bundle, valueOf6.length() != 0 ? valueOf5.concat(valueOf6) : new String(valueOf5));
        if (TextUtils.isEmpty(a4)) {
            return resources.getString(identifier);
        }
        try {
            JSONArray jSONArray = new JSONArray(a4);
            Object[] objArr = new String[jSONArray.length()];
            for (int i = 0; i < objArr.length; i++) {
                objArr[i] = jSONArray.opt(i);
            }
            return resources.getString(identifier, objArr);
        } catch (JSONException unused) {
            String str3 = "GcmNotification";
            String valueOf7 = String.valueOf(str);
            String valueOf8 = String.valueOf("_loc_args");
            String substring2 = (valueOf8.length() != 0 ? valueOf7.concat(valueOf8) : new String(valueOf7)).substring(6);
            StringBuilder sb2 = new StringBuilder(41 + String.valueOf(substring2).length() + String.valueOf(a4).length());
            sb2.append("Malformed ");
            sb2.append(substring2);
            sb2.append(": ");
            sb2.append(a4);
            sb2.append("  Default value will be used.");
            Log.w(str3, sb2.toString());
            return null;
        } catch (MissingFormatArgumentException e) {
            StringBuilder sb3 = new StringBuilder(58 + String.valueOf(a3).length() + String.valueOf(a4).length());
            sb3.append("Missing format argument for ");
            sb3.append(a3);
            sb3.append(": ");
            sb3.append(a4);
            sb3.append(" Default value will be used.");
            Log.w("GcmNotification", sb3.toString(), e);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0148  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0178  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0283  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x028c  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0295  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x029e  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x02a3  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x02b8  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x02cf  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(android.os.Bundle r14) {
        /*
            r13 = this;
            java.lang.String r0 = "gcm.n.title"
            java.lang.String r0 = r13.b(r14, r0)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L_0x001c
            android.content.Context r0 = r13.b
            android.content.pm.ApplicationInfo r0 = r0.getApplicationInfo()
            android.content.Context r1 = r13.b
            android.content.pm.PackageManager r1 = r1.getPackageManager()
            java.lang.CharSequence r0 = r0.loadLabel(r1)
        L_0x001c:
            java.lang.String r1 = "gcm.n.body"
            java.lang.String r1 = r13.b(r14, r1)
            java.lang.String r2 = "gcm.n.icon"
            java.lang.String r2 = a(r14, r2)
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L_0x0078
            android.content.Context r3 = r13.b
            android.content.res.Resources r3 = r3.getResources()
            java.lang.String r4 = "drawable"
            android.content.Context r5 = r13.b
            java.lang.String r5 = r5.getPackageName()
            int r4 = r3.getIdentifier(r2, r4, r5)
            if (r4 == 0) goto L_0x0043
            goto L_0x0086
        L_0x0043:
            java.lang.String r4 = "mipmap"
            android.content.Context r5 = r13.b
            java.lang.String r5 = r5.getPackageName()
            int r4 = r3.getIdentifier(r2, r4, r5)
            if (r4 == 0) goto L_0x0052
            goto L_0x0086
        L_0x0052:
            java.lang.String r3 = "GcmNotification"
            r4 = 57
            java.lang.String r5 = java.lang.String.valueOf(r2)
            int r5 = r5.length()
            int r4 = r4 + r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r4)
            java.lang.String r4 = "Icon resource "
            r5.append(r4)
            r5.append(r2)
            java.lang.String r2 = " not found. Notification will use app icon."
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            android.util.Log.w(r3, r2)
        L_0x0078:
            android.content.Context r2 = r13.b
            android.content.pm.ApplicationInfo r2 = r2.getApplicationInfo()
            int r2 = r2.icon
            if (r2 != 0) goto L_0x0085
            r2 = 17301651(0x1080093, float:2.4979667E-38)
        L_0x0085:
            r4 = r2
        L_0x0086:
            java.lang.String r2 = "gcm.n.color"
            java.lang.String r2 = a(r14, r2)
            java.lang.String r3 = "gcm.n.sound2"
            java.lang.String r3 = a(r14, r3)
            boolean r5 = android.text.TextUtils.isEmpty(r3)
            r6 = 0
            if (r5 == 0) goto L_0x009b
            r3 = r6
            goto L_0x00f4
        L_0x009b:
            java.lang.String r5 = "default"
            boolean r5 = r5.equals(r3)
            if (r5 != 0) goto L_0x00ef
            android.content.Context r5 = r13.b
            android.content.res.Resources r5 = r5.getResources()
            java.lang.String r7 = "raw"
            android.content.Context r8 = r13.b
            java.lang.String r8 = r8.getPackageName()
            int r5 = r5.getIdentifier(r3, r7, r8)
            if (r5 == 0) goto L_0x00ef
            android.content.Context r5 = r13.b
            java.lang.String r5 = r5.getPackageName()
            r7 = 24
            java.lang.String r8 = java.lang.String.valueOf(r5)
            int r8 = r8.length()
            int r7 = r7 + r8
            java.lang.String r8 = java.lang.String.valueOf(r3)
            int r8 = r8.length()
            int r7 = r7 + r8
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>(r7)
            java.lang.String r7 = "android.resource://"
            r8.append(r7)
            r8.append(r5)
            java.lang.String r5 = "/raw/"
            r8.append(r5)
            r8.append(r3)
            java.lang.String r3 = r8.toString()
            android.net.Uri r3 = android.net.Uri.parse(r3)
            goto L_0x00f4
        L_0x00ef:
            r3 = 2
            android.net.Uri r3 = android.media.RingtoneManager.getDefaultUri(r3)
        L_0x00f4:
            java.lang.String r5 = "gcm.n.click_action"
            java.lang.String r5 = a(r14, r5)
            boolean r7 = android.text.TextUtils.isEmpty(r5)
            if (r7 != 0) goto L_0x0114
            android.content.Intent r7 = new android.content.Intent
            r7.<init>(r5)
            android.content.Context r5 = r13.b
            java.lang.String r5 = r5.getPackageName()
            r7.setPackage(r5)
            r5 = 268435456(0x10000000, float:2.5243549E-29)
            r7.setFlags(r5)
            goto L_0x012f
        L_0x0114:
            android.content.Context r5 = r13.b
            android.content.pm.PackageManager r5 = r5.getPackageManager()
            android.content.Context r7 = r13.b
            java.lang.String r7 = r7.getPackageName()
            android.content.Intent r7 = r5.getLaunchIntentForPackage(r7)
            if (r7 != 0) goto L_0x012f
            java.lang.String r5 = "GcmNotification"
            java.lang.String r7 = "No activity found to launch app"
            android.util.Log.w(r5, r7)
            r5 = r6
            goto L_0x0170
        L_0x012f:
            android.os.Bundle r5 = new android.os.Bundle
            r5.<init>(r14)
            com.google.android.gms.gcm.GcmListenerService.zzd(r5)
            r7.putExtras(r5)
            java.util.Set r5 = r5.keySet()
            java.util.Iterator r5 = r5.iterator()
        L_0x0142:
            boolean r8 = r5.hasNext()
            if (r8 == 0) goto L_0x0162
            java.lang.Object r8 = r5.next()
            java.lang.String r8 = (java.lang.String) r8
            java.lang.String r9 = "gcm.n."
            boolean r9 = r8.startsWith(r9)
            if (r9 != 0) goto L_0x015e
            java.lang.String r9 = "gcm.notification."
            boolean r9 = r8.startsWith(r9)
            if (r9 == 0) goto L_0x0142
        L_0x015e:
            r7.removeExtra(r8)
            goto L_0x0142
        L_0x0162:
            android.content.Context r5 = r13.b
            java.util.concurrent.atomic.AtomicInteger r8 = r13.d
            int r8 = r8.getAndIncrement()
            r9 = 1073741824(0x40000000, float:2.0)
            android.app.PendingIntent r5 = android.app.PendingIntent.getActivity(r5, r8, r7, r9)
        L_0x0170:
            boolean r7 = com.google.android.gms.common.util.PlatformVersion.isAtLeastO()
            r8 = 3
            r9 = 1
            if (r7 == 0) goto L_0x026e
            android.content.Context r7 = r13.b
            android.content.pm.ApplicationInfo r7 = r7.getApplicationInfo()
            int r7 = r7.targetSdkVersion
            r10 = 26
            if (r7 < r10) goto L_0x026e
            java.lang.String r7 = "gcm.n.android_channel_id"
            java.lang.String r7 = a(r14, r7)
            boolean r10 = com.google.android.gms.common.util.PlatformVersion.isAtLeastO()
            if (r10 != 0) goto L_0x0192
            goto L_0x0220
        L_0x0192:
            android.content.Context r6 = r13.b
            java.lang.Class<android.app.NotificationManager> r10 = android.app.NotificationManager.class
            java.lang.Object r6 = r6.getSystemService(r10)
            android.app.NotificationManager r6 = (android.app.NotificationManager) r6
            boolean r10 = android.text.TextUtils.isEmpty(r7)
            if (r10 != 0) goto L_0x01d1
            android.app.NotificationChannel r10 = r6.getNotificationChannel(r7)
            if (r10 == 0) goto L_0x01ab
            r6 = r7
            goto L_0x0220
        L_0x01ab:
            java.lang.String r10 = "GcmNotification"
            r11 = 122(0x7a, float:1.71E-43)
            java.lang.String r12 = java.lang.String.valueOf(r7)
            int r12 = r12.length()
            int r11 = r11 + r12
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>(r11)
            java.lang.String r11 = "Notification Channel requested ("
            r12.append(r11)
            r12.append(r7)
            java.lang.String r7 = ") has not been created by the app. Manifest configuration, or default, value will be used."
            r12.append(r7)
            java.lang.String r7 = r12.toString()
            android.util.Log.w(r10, r7)
        L_0x01d1:
            java.lang.String r7 = r13.c
            if (r7 == 0) goto L_0x01d8
        L_0x01d5:
            java.lang.String r6 = r13.c
            goto L_0x0220
        L_0x01d8:
            android.os.Bundle r7 = r13.a()
            java.lang.String r10 = "com.google.android.gms.gcm.default_notification_channel_id"
            java.lang.String r7 = r7.getString(r10)
            r13.c = r7
            java.lang.String r7 = r13.c
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            if (r7 != 0) goto L_0x01fa
            java.lang.String r7 = r13.c
            android.app.NotificationChannel r7 = r6.getNotificationChannel(r7)
            if (r7 == 0) goto L_0x01f5
            goto L_0x01d5
        L_0x01f5:
            java.lang.String r7 = "GcmNotification"
            java.lang.String r10 = "Notification Channel set in AndroidManifest.xml has not been created by the app. Default value will be used."
            goto L_0x01fe
        L_0x01fa:
            java.lang.String r7 = "GcmNotification"
            java.lang.String r10 = "Missing Default Notification Channel metadata in AndroidManifest. Default value will be used."
        L_0x01fe:
            android.util.Log.w(r7, r10)
            java.lang.String r7 = "fcm_fallback_notification_channel"
            android.app.NotificationChannel r7 = r6.getNotificationChannel(r7)
            if (r7 != 0) goto L_0x021b
            android.app.NotificationChannel r7 = new android.app.NotificationChannel
            java.lang.String r10 = "fcm_fallback_notification_channel"
            android.content.Context r11 = r13.b
            int r12 = com.google.android.gms.gcm.b.a.gcm_fallback_notification_channel_label
            java.lang.String r11 = r11.getString(r12)
            r7.<init>(r10, r11, r8)
            r6.createNotificationChannel(r7)
        L_0x021b:
            java.lang.String r6 = "fcm_fallback_notification_channel"
            r13.c = r6
            goto L_0x01d5
        L_0x0220:
            android.app.Notification$Builder r7 = new android.app.Notification$Builder
            android.content.Context r10 = r13.b
            r7.<init>(r10)
            android.app.Notification$Builder r7 = r7.setAutoCancel(r9)
            android.app.Notification$Builder r4 = r7.setSmallIcon(r4)
            boolean r7 = android.text.TextUtils.isEmpty(r0)
            if (r7 != 0) goto L_0x0238
            r4.setContentTitle(r0)
        L_0x0238:
            boolean r0 = android.text.TextUtils.isEmpty(r1)
            if (r0 != 0) goto L_0x024d
            r4.setContentText(r1)
            android.app.Notification$BigTextStyle r0 = new android.app.Notification$BigTextStyle
            r0.<init>()
            android.app.Notification$BigTextStyle r0 = r0.bigText(r1)
            r4.setStyle(r0)
        L_0x024d:
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 != 0) goto L_0x025a
            int r0 = android.graphics.Color.parseColor(r2)
            r4.setColor(r0)
        L_0x025a:
            if (r3 == 0) goto L_0x025f
            r4.setSound(r3)
        L_0x025f:
            if (r5 == 0) goto L_0x0264
            r4.setContentIntent(r5)
        L_0x0264:
            if (r6 == 0) goto L_0x0269
            r4.setChannelId(r6)
        L_0x0269:
            android.app.Notification r0 = r4.build()
            goto L_0x02aa
        L_0x026e:
            android.support.v4.app.NotificationCompat$Builder r6 = new android.support.v4.app.NotificationCompat$Builder
            android.content.Context r7 = r13.b
            r6.<init>(r7)
            android.support.v4.app.NotificationCompat$Builder r6 = r6.setAutoCancel(r9)
            android.support.v4.app.NotificationCompat$Builder r4 = r6.setSmallIcon(r4)
            boolean r6 = android.text.TextUtils.isEmpty(r0)
            if (r6 != 0) goto L_0x0286
            r4.setContentTitle(r0)
        L_0x0286:
            boolean r0 = android.text.TextUtils.isEmpty(r1)
            if (r0 != 0) goto L_0x028f
            r4.setContentText(r1)
        L_0x028f:
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 != 0) goto L_0x029c
            int r0 = android.graphics.Color.parseColor(r2)
            r4.setColor(r0)
        L_0x029c:
            if (r3 == 0) goto L_0x02a1
            r4.setSound(r3)
        L_0x02a1:
            if (r5 == 0) goto L_0x02a6
            r4.setContentIntent(r5)
        L_0x02a6:
            android.app.Notification r0 = r4.build()
        L_0x02aa:
            java.lang.String r1 = "gcm.n.tag"
            java.lang.String r14 = a(r14, r1)
            java.lang.String r1 = "GcmNotification"
            boolean r1 = android.util.Log.isLoggable(r1, r8)
            if (r1 == 0) goto L_0x02bf
            java.lang.String r1 = "GcmNotification"
            java.lang.String r2 = "Showing notification"
            android.util.Log.d(r1, r2)
        L_0x02bf:
            android.content.Context r1 = r13.b
            java.lang.String r2 = "notification"
            java.lang.Object r1 = r1.getSystemService(r2)
            android.app.NotificationManager r1 = (android.app.NotificationManager) r1
            boolean r2 = android.text.TextUtils.isEmpty(r14)
            if (r2 == 0) goto L_0x02e6
            long r2 = android.os.SystemClock.uptimeMillis()
            r14 = 37
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r14)
            java.lang.String r14 = "GCM-Notification:"
            r4.append(r14)
            r4.append(r2)
            java.lang.String r14 = r4.toString()
        L_0x02e6:
            r2 = 0
            r1.notify(r14, r2, r0)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.gcm.d.a(android.os.Bundle):boolean");
    }
}
