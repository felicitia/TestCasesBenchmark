package com.salesforce.marketingcloud.notifications;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.NotificationCompat.Builder;
import android.webkit.URLUtil;
import com.salesforce.marketingcloud.e.g;
import com.salesforce.marketingcloud.j;
import com.salesforce.marketingcloud.notifications.c.C0173c;
import com.salesforce.marketingcloud.notifications.c.a;
import com.salesforce.marketingcloud.notifications.c.b;
import java.net.HttpURLConnection;
import java.net.URL;

class e {
    private static final String a = j.a(e.class);
    private final Class<? extends Activity> b;
    private final Class<? extends Activity> c;
    private final Class<? extends Activity> d;
    private final C0173c e;
    private final a f;
    private final b g;
    private final int h;
    private final String i;
    private final String j;

    e(Context context, @Nullable Class<? extends Activity> cls, @Nullable Class<? extends Activity> cls2, @Nullable Class<? extends Activity> cls3, @DrawableRes int i2, @Nullable String str, @Nullable String str2, @Nullable C0173c cVar, @Nullable a aVar, @Nullable b bVar) {
        this.b = cls2;
        this.c = cls;
        this.d = cls3;
        this.e = cVar;
        this.f = aVar;
        this.g = bVar;
        this.j = str2;
        if (i2 <= 0) {
            i2 = context.getApplicationInfo().icon;
        }
        this.h = i2;
        this.i = str;
    }

    private static Uri a(Context context, String str, Uri uri) {
        int identifier = context.getResources().getIdentifier(a(str), "raw", context.getPackageName());
        if (identifier <= 0) {
            return uri;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("android.resource://");
        sb.append(context.getPackageName());
        sb.append("/");
        sb.append(identifier);
        return Uri.parse(sb.toString());
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0085  */
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static android.support.v4.app.NotificationCompat.Builder a(android.content.Context r7, com.salesforce.marketingcloud.notifications.NotificationMessage r8) {
        /*
            android.support.v4.app.NotificationCompat$Builder r0 = new android.support.v4.app.NotificationCompat$Builder
            r0.<init>(r7)
            android.content.pm.ApplicationInfo r1 = r7.getApplicationInfo()
            int r1 = r1.icon
            if (r1 <= 0) goto L_0x0018
            android.content.res.Resources r2 = r7.getResources()
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeResource(r2, r1)
            r0.setLargeIcon(r1)
        L_0x0018:
            int r1 = r8.smallIconResId()
            r0.setSmallIcon(r1)
            java.lang.String r1 = r8.title()
            r0.setContentTitle(r1)
            java.lang.String r1 = r8.alert()
            r2 = 0
            if (r1 == 0) goto L_0x0099
            java.lang.String r1 = r8.mediaUrl()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x0067
            r1 = 1
            java.lang.String r3 = r8.mediaUrl()     // Catch:{ Exception -> 0x0057 }
            android.graphics.Bitmap r3 = c(r3)     // Catch:{ Exception -> 0x0057 }
            if (r3 == 0) goto L_0x0067
            android.support.v4.app.NotificationCompat$BigPictureStyle r4 = new android.support.v4.app.NotificationCompat$BigPictureStyle     // Catch:{ Exception -> 0x0057 }
            r4.<init>()     // Catch:{ Exception -> 0x0057 }
            android.support.v4.app.NotificationCompat$BigPictureStyle r3 = r4.bigPicture(r3)     // Catch:{ Exception -> 0x0057 }
            java.lang.String r4 = r8.alert()     // Catch:{ Exception -> 0x0057 }
            android.support.v4.app.NotificationCompat$BigPictureStyle r3 = r3.setSummaryText(r4)     // Catch:{ Exception -> 0x0057 }
            r0.setStyle(r3)     // Catch:{ Exception -> 0x0057 }
            goto L_0x0068
        L_0x0057:
            r3 = move-exception
            java.lang.String r4 = a
            java.lang.String r5 = "Unable to load notification image %s"
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r6 = r8.mediaUrl()
            r1[r2] = r6
            com.salesforce.marketingcloud.j.c(r4, r3, r5, r1)
        L_0x0067:
            r1 = r2
        L_0x0068:
            if (r1 != 0) goto L_0x0079
            java.lang.String r3 = r8.mediaAltText()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0079
            java.lang.String r3 = r8.mediaAltText()
            goto L_0x007d
        L_0x0079:
            java.lang.String r3 = r8.alert()
        L_0x007d:
            r0.setContentText(r3)
            r0.setTicker(r3)
            if (r1 != 0) goto L_0x0099
            android.support.v4.app.NotificationCompat$BigTextStyle r1 = new android.support.v4.app.NotificationCompat$BigTextStyle
            r1.<init>()
            android.support.v4.app.NotificationCompat$BigTextStyle r1 = r1.bigText(r3)
            java.lang.String r3 = r8.title()
            android.support.v4.app.NotificationCompat$BigTextStyle r1 = r1.setBigContentTitle(r3)
            r0.setStyle(r1)
        L_0x0099:
            int[] r1 = com.salesforce.marketingcloud.notifications.e.AnonymousClass1.a
            com.salesforce.marketingcloud.notifications.NotificationMessage$Sound r3 = r8.sound()
            int r3 = r3.ordinal()
            r1 = r1[r3]
            switch(r1) {
                case 1: goto L_0x00b6;
                case 2: goto L_0x00b3;
                case 3: goto L_0x00a9;
                default: goto L_0x00a8;
            }
        L_0x00a8:
            return r0
        L_0x00a9:
            java.lang.String r7 = a
            java.lang.String r8 = "No sound was set for notification."
            java.lang.Object[] r1 = new java.lang.Object[r2]
            com.salesforce.marketingcloud.j.b(r7, r8, r1)
            return r0
        L_0x00b3:
            android.net.Uri r7 = android.provider.Settings.System.DEFAULT_NOTIFICATION_URI
            goto L_0x00c0
        L_0x00b6:
            java.lang.String r8 = r8.soundName()
            android.net.Uri r1 = android.provider.Settings.System.DEFAULT_NOTIFICATION_URI
            android.net.Uri r7 = a(r7, r8, r1)
        L_0x00c0:
            r0.setSound(r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.notifications.e.a(android.content.Context, com.salesforce.marketingcloud.notifications.NotificationMessage):android.support.v4.app.NotificationCompat$Builder");
    }

    public static String a(String str) {
        return (str == null || str.lastIndexOf(".") <= 0) ? str : str.substring(0, str.lastIndexOf("."));
    }

    @RequiresApi(api = 26)
    private void a(NotificationManager notificationManager) {
        if (this.j != null) {
            NotificationChannel notificationChannel = new NotificationChannel("com.salesforce.marketingcloud.DEFAULT_CHANNEL", this.j, 3);
            notificationChannel.enableLights(false);
            notificationChannel.enableVibration(false);
            notificationChannel.setShowBadge(true);
            notificationChannel.setLockscreenVisibility(0);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private static boolean b(String str) {
        return str != null && URLUtil.isValidUrl(str) && (URLUtil.isHttpUrl(str) || URLUtil.isHttpsUrl(str));
    }

    private static Bitmap c(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setReadTimeout(30000);
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.connect();
            return BitmapFactory.decodeStream(httpURLConnection.getInputStream());
        } catch (Exception e2) {
            j.c(a, e2, "Unable to download image %s", str);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public Class<? extends Activity> a() {
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public Class<? extends Activity> a(NotificationMessage notificationMessage) {
        if (this.b != null) {
            return this.b;
        }
        if (b(notificationMessage.url())) {
            return DefaultUrlPresenter.class;
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    @SuppressLint({"NewApi"})
    public void a(Builder builder, Context context, NotificationMessage notificationMessage) {
        if (g.a()) {
            if (this.g != null) {
                try {
                    builder.setChannelId(this.g.a(context, notificationMessage));
                } catch (Exception e2) {
                    j.c(a, e2, "Exception thrown while app determined channel id for notification message.", new Object[0]);
                }
            } else {
                NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                if (notificationManager.getNotificationChannel("com.salesforce.marketingcloud.DEFAULT_CHANNEL") == null) {
                    a(notificationManager);
                }
                builder.setChannelId("com.salesforce.marketingcloud.DEFAULT_CHANNEL");
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @DrawableRes
    public int b() {
        return this.h;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0075 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.app.PendingIntent b(android.content.Context r7, com.salesforce.marketingcloud.notifications.NotificationMessage r8) {
        /*
            r6 = this;
            com.salesforce.marketingcloud.notifications.c$c r0 = r6.e
            if (r0 == 0) goto L_0x000b
            com.salesforce.marketingcloud.notifications.c$c r0 = r6.e
            android.app.PendingIntent r7 = r0.a(r7, r8)
            return r7
        L_0x000b:
            com.salesforce.marketingcloud.notifications.NotificationMessage$Type r0 = r8.type()
            com.salesforce.marketingcloud.notifications.NotificationMessage$Type r1 = com.salesforce.marketingcloud.notifications.NotificationMessage.Type.CLOUD_PAGE
            if (r0 != r1) goto L_0x0018
            java.lang.Class r0 = r6.b(r8)
            goto L_0x0029
        L_0x0018:
            com.salesforce.marketingcloud.notifications.NotificationMessage$Type r0 = r8.type()
            com.salesforce.marketingcloud.notifications.NotificationMessage$Type r1 = com.salesforce.marketingcloud.notifications.NotificationMessage.Type.OPEN_DIRECT
            if (r0 != r1) goto L_0x0025
            java.lang.Class r0 = r6.a(r8)
            goto L_0x0029
        L_0x0025:
            java.lang.Class r0 = r6.a()
        L_0x0029:
            r1 = 0
            if (r0 == 0) goto L_0x0050
            android.content.Intent r2 = new android.content.Intent
            r2.<init>(r7, r0)
            android.content.pm.PackageManager r3 = r7.getPackageManager()
            r4 = 0
            java.util.List r3 = r3.queryIntentActivities(r2, r4)
            int r3 = r3.size()
            if (r3 != 0) goto L_0x0051
            java.lang.String r2 = a
            java.lang.String r3 = "Activity %s is not declared in the app's manifest."
            r5 = 1
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.String r0 = r0.getName()
            r5[r4] = r0
            com.salesforce.marketingcloud.j.b(r2, r3, r5)
        L_0x0050:
            r2 = r1
        L_0x0051:
            if (r2 != 0) goto L_0x005f
            android.content.pm.PackageManager r0 = r7.getPackageManager()
            java.lang.String r2 = r7.getPackageName()
            android.content.Intent r2 = r0.getLaunchIntentForPackage(r2)
        L_0x005f:
            if (r2 == 0) goto L_0x0075
            java.lang.String r0 = "com.salesforce.marketingcloud.notifications.EXTRA_MESSAGE"
            android.content.Intent r0 = r2.putExtra(r0, r8)
            r1 = 134217728(0x8000000, float:3.85186E-34)
            r0.addFlags(r1)
            int r8 = r8.notificationId()
            android.app.PendingIntent r7 = android.app.PendingIntent.getActivity(r7, r8, r2, r1)
            return r7
        L_0x0075:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.notifications.e.b(android.content.Context, com.salesforce.marketingcloud.notifications.NotificationMessage):android.app.PendingIntent");
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public Class<? extends Activity> b(NotificationMessage notificationMessage) {
        if (this.c != null) {
            return this.c;
        }
        if (b(notificationMessage.url())) {
            return DefaultUrlPresenter.class;
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public Builder c(Context context, NotificationMessage notificationMessage) {
        return this.f != null ? this.f.a(context, notificationMessage) : a(context, notificationMessage);
    }

    /* access modifiers changed from: 0000 */
    public String c() {
        return this.i;
    }
}
