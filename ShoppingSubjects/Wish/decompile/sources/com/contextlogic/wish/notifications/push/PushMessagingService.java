package com.contextlogic.wish.notifications.push;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import com.contextlogic.wish.activity.link.DeepLinkActivity;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpManager;
import com.contextlogic.wish.http.ImageHttpManager.ImageRequest;
import com.contextlogic.wish.http.ImageHttpManager.ImageTarget;
import com.contextlogic.wish.http.ImageHttpManager.LoadingSource;
import com.contextlogic.wish.util.ApplicationUtil;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Random;

public class PushMessagingService extends FirebaseMessagingService {
    public static final String ACTION_PUSH_NOTIFICATION = "com.contextlogic.wish.notifications.push.PushMessagingService";
    private static final String TAG = "com.contextlogic.wish.notifications.push.PushMessagingService";

    public void onMessageReceived(RemoteMessage remoteMessage) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("From: ");
        sb.append(remoteMessage.getFrom());
        Log.d(str, sb.toString());
        String str2 = TAG;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Message data payload: ");
        sb2.append(remoteMessage.getData());
        Log.d(str2, sb2.toString());
        if (remoteMessage.getData().size() > 0) {
            if (remoteMessage.getNotification() != null) {
                String str3 = TAG;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Message Notification Body: ");
                sb3.append(remoteMessage.getNotification().getBody());
                Log.d(str3, sb3.toString());
            }
            if (!ApplicationUtil.isPackageInForeground(WishApplication.getInstance().getPackageName())) {
                String str4 = (String) remoteMessage.getData().get("i");
                if (str4 != null) {
                    downloadImage(remoteMessage, str4);
                } else {
                    createNotification(remoteMessage, null);
                }
            }
        }
    }

    private void downloadImage(final RemoteMessage remoteMessage, String str) {
        ImageRequest imageRequest = new ImageRequest(str);
        imageRequest.setImageTarget(new ImageTarget() {
            public void onSuccess(Bitmap bitmap, LoadingSource loadingSource) {
                PushMessagingService.this.createNotification(remoteMessage, bitmap);
            }

            public void onError() {
                PushMessagingService.this.createNotification(remoteMessage, null);
            }
        });
        ImageHttpManager.getInstance().request(imageRequest);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0143  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0182  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x01a2  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x01d4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void createNotification(com.google.firebase.messaging.RemoteMessage r20, android.graphics.Bitmap r21) {
        /*
            r19 = this;
            r0 = r19
            r1 = r21
            java.util.Map r2 = r20.getData()
            java.lang.String r3 = "alert"
            java.lang.Object r2 = r2.get(r3)
            java.lang.String r2 = (java.lang.String) r2
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 == 0) goto L_0x0017
            return
        L_0x0017:
            java.util.Map r3 = r20.getData()
            java.lang.String r4 = "url"
            java.lang.Object r3 = r3.get(r4)
            java.lang.String r3 = (java.lang.String) r3
            java.util.Map r4 = r20.getData()
            java.lang.String r5 = "gk"
            java.lang.Object r4 = r4.get(r5)
            java.lang.String r4 = (java.lang.String) r4
            java.util.Map r5 = r20.getData()
            java.lang.String r6 = "at1"
            java.lang.Object r5 = r5.get(r6)
            java.lang.String r5 = (java.lang.String) r5
            java.util.Map r6 = r20.getData()
            java.lang.String r7 = "av1"
            java.lang.Object r6 = r6.get(r7)
            java.lang.String r6 = (java.lang.String) r6
            java.util.Map r7 = r20.getData()
            java.lang.String r8 = "at2"
            java.lang.Object r7 = r7.get(r8)
            java.lang.String r7 = (java.lang.String) r7
            java.util.Map r8 = r20.getData()
            java.lang.String r9 = "av2"
            java.lang.Object r8 = r8.get(r9)
            java.lang.String r8 = (java.lang.String) r8
            java.util.Map r9 = r20.getData()
            java.lang.String r10 = "at3"
            java.lang.Object r9 = r9.get(r10)
            java.lang.String r9 = (java.lang.String) r9
            java.util.Map r10 = r20.getData()
            java.lang.String r11 = "av3"
            java.lang.Object r10 = r10.get(r11)
            java.lang.String r10 = (java.lang.String) r10
            java.util.Map r11 = r20.getData()
            java.lang.String r12 = "bucket_id"
            java.lang.Object r11 = r11.get(r12)
            java.lang.String r11 = (java.lang.String) r11
            java.util.Map r12 = r20.getData()
            java.lang.String r13 = "notification_id"
            java.lang.Object r12 = r12.get(r13)
            java.lang.String r12 = (java.lang.String) r12
            r13 = -1
            if (r11 == 0) goto L_0x009e
            if (r12 == 0) goto L_0x009e
            int r11 = java.lang.Integer.parseInt(r11)     // Catch:{ Exception -> 0x009e }
            int r12 = java.lang.Integer.parseInt(r12)     // Catch:{ Exception -> 0x009f }
            r13 = r12
            goto L_0x009f
        L_0x009e:
            r11 = -1
        L_0x009f:
            java.util.Random r12 = new java.util.Random
            r12.<init>()
            r14 = 1000000(0xf4240, float:1.401298E-39)
            int r12 = r12.nextInt(r14)
            if (r4 == 0) goto L_0x00b2
            int r4 = r4.hashCode()
            goto L_0x00b3
        L_0x00b2:
            r4 = r12
        L_0x00b3:
            android.support.v4.app.NotificationCompat$Builder r14 = new android.support.v4.app.NotificationCompat$Builder
            android.content.Context r15 = r19.getApplicationContext()
            android.content.Context r1 = r19.getApplicationContext()
            r16 = r10
            r10 = 2131755419(0x7f10019b, float:1.9141717E38)
            java.lang.String r1 = r1.getString(r10)
            r14.<init>(r15, r1)
            r1 = 2131166012(0x7f07033c, float:1.7946257E38)
            r14.setSmallIcon(r1)
            com.contextlogic.wish.application.WishApplication r1 = com.contextlogic.wish.application.WishApplication.getInstance()
            r10 = 2131755090(0x7f100052, float:1.914105E38)
            java.lang.String r1 = r1.getString(r10)
            r14.setContentTitle(r1)
            r14.setContentText(r2)
            r14.setTicker(r2)
            r17 = r11
            long r10 = java.lang.System.currentTimeMillis()
            r14.setWhen(r10)
            r1 = 1
            r14.setAutoCancel(r1)
            r1 = 3
            r14.setDefaults(r1)
            com.contextlogic.wish.application.WishApplication r1 = com.contextlogic.wish.application.WishApplication.getInstance()
            android.content.res.Resources r1 = r1.getResources()
            r10 = 2131034353(0x7f0500f1, float:1.7679221E38)
            int r1 = r1.getColor(r10)
            r14.setColor(r1)
            r10 = 400(0x190, float:5.6E-43)
            r11 = 2000(0x7d0, float:2.803E-42)
            r14.setLights(r1, r10, r11)
            android.content.Intent r1 = new android.content.Intent
            android.content.Context r10 = r19.getBaseContext()
            java.lang.Class<com.contextlogic.wish.activity.link.DeepLinkActivity> r11 = com.contextlogic.wish.activity.link.DeepLinkActivity.class
            r1.<init>(r10, r11)
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = ACTION_PUSH_NOTIFICATION
            r10.append(r11)
            r10.append(r12)
            java.lang.String r10 = r10.toString()
            r1.setAction(r10)
            java.lang.String r10 = com.contextlogic.wish.activity.link.DeepLinkActivity.EXTRA_CANCEL_NOTIFICATION_ID
            r1.putExtra(r10, r4)
            if (r17 < 0) goto L_0x0141
            if (r13 < 0) goto L_0x0141
            java.lang.String r10 = com.contextlogic.wish.activity.link.DeepLinkActivity.EXTRA_PUSH_NOTIFICATION_NUMBER
            r1.putExtra(r10, r13)
            java.lang.String r10 = com.contextlogic.wish.activity.link.DeepLinkActivity.EXTRA_PUSH_NOTIFICATION_BUCKET_NUMBER
            r11 = r17
            r1.putExtra(r10, r11)
        L_0x0141:
            if (r3 == 0) goto L_0x014a
            android.net.Uri r3 = android.net.Uri.parse(r3)
            r1.setData(r3)
        L_0x014a:
            r3 = 268435456(0x10000000, float:2.5243549E-29)
            r1.addFlags(r3)
            android.content.Context r3 = r19.getBaseContext()
            r10 = 0
            android.app.PendingIntent r3 = android.app.PendingIntent.getActivity(r3, r12, r1, r10)
            r14.setContentIntent(r3)
            if (r5 == 0) goto L_0x0166
            if (r6 == 0) goto L_0x0166
            android.app.PendingIntent r3 = r0.createSecondaryActionIntent(r6, r1)
            r14.addAction(r10, r5, r3)
        L_0x0166:
            if (r7 == 0) goto L_0x0171
            if (r8 == 0) goto L_0x0171
            android.app.PendingIntent r3 = r0.createSecondaryActionIntent(r8, r1)
            r14.addAction(r10, r7, r3)
        L_0x0171:
            if (r9 == 0) goto L_0x017e
            if (r16 == 0) goto L_0x017e
            r3 = r16
            android.app.PendingIntent r1 = r0.createSecondaryActionIntent(r3, r1)
            r14.addAction(r10, r9, r1)
        L_0x017e:
            r1 = r21
            if (r1 == 0) goto L_0x01a2
            android.support.v4.app.NotificationCompat$BigPictureStyle r3 = new android.support.v4.app.NotificationCompat$BigPictureStyle
            r3.<init>()
            android.support.v4.app.NotificationCompat$BigPictureStyle r1 = r3.bigPicture(r1)
            android.support.v4.app.NotificationCompat$BigPictureStyle r1 = r1.setSummaryText(r2)
            android.content.Context r2 = r19.getBaseContext()
            r3 = 2131755090(0x7f100052, float:1.914105E38)
            java.lang.String r2 = r2.getString(r3)
            android.support.v4.app.NotificationCompat$BigPictureStyle r1 = r1.setBigContentTitle(r2)
            r14.setStyle(r1)
            goto L_0x01bd
        L_0x01a2:
            r3 = 2131755090(0x7f100052, float:1.914105E38)
            android.support.v4.app.NotificationCompat$BigTextStyle r1 = new android.support.v4.app.NotificationCompat$BigTextStyle
            r1.<init>()
            android.support.v4.app.NotificationCompat$BigTextStyle r1 = r1.bigText(r2)
            android.content.Context r2 = r19.getBaseContext()
            java.lang.String r2 = r2.getString(r3)
            android.support.v4.app.NotificationCompat$BigTextStyle r1 = r1.setBigContentTitle(r2)
            r14.setStyle(r1)
        L_0x01bd:
            android.app.Notification r1 = r14.build()
            com.contextlogic.wish.util.NotificationUtil.sendNotification(r1, r4)
            java.lang.String r1 = "push-notification-ids"
            java.util.ArrayList r1 = com.contextlogic.wish.util.PreferenceUtil.getIntegerArray(r1)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)
            boolean r2 = r1.contains(r2)
            if (r2 != 0) goto L_0x01db
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)
            r1.add(r2)
        L_0x01db:
            java.lang.String r2 = "push-notification-ids"
            com.contextlogic.wish.util.PreferenceUtil.setIntegerArray(r2, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.notifications.push.PushMessagingService.createNotification(com.google.firebase.messaging.RemoteMessage, android.graphics.Bitmap):void");
    }

    private PendingIntent createSecondaryActionIntent(String str, Intent intent) {
        Intent intent2 = new Intent(getBaseContext(), DeepLinkActivity.class);
        intent2.setData(Uri.parse(str));
        intent2.addFlags(268435456);
        if (intent.hasExtra(DeepLinkActivity.EXTRA_PUSH_NOTIFICATION_BUCKET_NUMBER)) {
            intent2.putExtra(DeepLinkActivity.EXTRA_PUSH_NOTIFICATION_BUCKET_NUMBER, intent.getIntExtra(DeepLinkActivity.EXTRA_PUSH_NOTIFICATION_BUCKET_NUMBER, -1));
        }
        if (intent.hasExtra(DeepLinkActivity.EXTRA_PUSH_NOTIFICATION_NUMBER)) {
            intent2.putExtra(DeepLinkActivity.EXTRA_PUSH_NOTIFICATION_NUMBER, intent.getIntExtra(DeepLinkActivity.EXTRA_PUSH_NOTIFICATION_NUMBER, -1));
        }
        if (intent.hasExtra(DeepLinkActivity.EXTRA_CANCEL_NOTIFICATION_ID)) {
            intent2.putExtra(DeepLinkActivity.EXTRA_CANCEL_NOTIFICATION_ID, intent.getIntExtra(DeepLinkActivity.EXTRA_CANCEL_NOTIFICATION_ID, -1));
        }
        int nextInt = new Random().nextInt(1000000);
        StringBuilder sb = new StringBuilder();
        sb.append(ACTION_PUSH_NOTIFICATION);
        sb.append(nextInt);
        intent2.setAction(sb.toString());
        return PendingIntent.getActivity(getBaseContext(), nextInt, intent2, 0);
    }
}
