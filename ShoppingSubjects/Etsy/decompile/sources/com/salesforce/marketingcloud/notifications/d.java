package com.salesforce.marketingcloud.notifications;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.util.ArraySet;
import android.text.TextUtils;
import com.salesforce.marketingcloud.analytics.m;
import com.salesforce.marketingcloud.b.c;
import com.salesforce.marketingcloud.e.f;
import com.salesforce.marketingcloud.e.g;
import com.salesforce.marketingcloud.h;
import com.salesforce.marketingcloud.j;
import com.salesforce.marketingcloud.notifications.c.C0173c;
import java.util.Set;

@RestrictTo({Scope.LIBRARY})
public class d extends c implements h {
    /* access modifiers changed from: private */
    public static final String b = j.a(d.class);
    /* access modifiers changed from: private */
    public final e c;
    /* access modifiers changed from: private */
    public final Context d;
    private final com.salesforce.marketingcloud.d.h e;
    private final Set<com.salesforce.marketingcloud.notifications.c.d> f;
    /* access modifiers changed from: private */
    public final m g;
    private BroadcastReceiver h;
    private boolean i = true;

    public interface a {
        void a(int i);
    }

    private class b extends BroadcastReceiver {
        private b() {
        }

        public void onReceive(Context context, Intent intent) {
            String d;
            String str;
            if (intent == null) {
                d = d.b;
                str = "Received null intent";
            } else {
                String action = intent.getAction();
                if (action == null) {
                    d = d.b;
                    str = "Received null action";
                } else {
                    char c = 65535;
                    if (action.hashCode() == 441866220 && action.equals("com.salesforce.marketingcloud.notifications.OPENED")) {
                        c = 0;
                    }
                    if (c != 0) {
                        j.b(d.b, "Received unknown action: %s", action);
                        return;
                    }
                    d.this.a(context, (NotificationMessage) intent.getParcelableExtra("com.salesforce.marketingcloud.notifications.EXTRA_MESSAGE"), (PendingIntent) intent.getParcelableExtra("com.salesforce.marketingcloud.notifications.EXTRA_OPEN_INTENT"), intent.getBooleanExtra("com.salesforce.marketingcloud.notifications.EXTRA_AUTO_CANCEL", true));
                    return;
                }
            }
            j.b(d, str, new Object[0]);
        }
    }

    @VisibleForTesting
    d(Context context, com.salesforce.marketingcloud.d.h hVar, e eVar, m mVar) {
        this.d = context;
        this.e = hVar;
        this.c = eVar;
        this.g = (m) f.a(mVar, "MessageAnalyticEventListener is null.");
        this.f = new ArraySet();
    }

    @RestrictTo({Scope.LIBRARY})
    public static d a(@NonNull Context context, @NonNull com.salesforce.marketingcloud.d.h hVar, @Nullable Class<? extends Activity> cls, @Nullable Class<? extends Activity> cls2, @Nullable Class<? extends Activity> cls3, int i2, String str, String str2, @Nullable C0173c cVar, @Nullable com.salesforce.marketingcloud.notifications.c.a aVar, @Nullable com.salesforce.marketingcloud.notifications.c.b bVar, @NonNull m mVar) {
        Context context2 = context;
        String str3 = str2;
        com.salesforce.marketingcloud.notifications.c.b bVar2 = bVar;
        a(context2, str3, bVar2);
        e eVar = new e(context2, cls, cls2, cls3, i2, str, str3, cVar, aVar, bVar2);
        return new d(context2, hVar, eVar, mVar);
    }

    private void a(@NonNull Context context) {
        if (this.e != null) {
            NotificationManagerCompat from = NotificationManagerCompat.from(context);
            int i2 = this.e.e().getInt("notification_id_key", -1);
            int i3 = 0;
            while (i2 >= 0 && i3 < 100) {
                from.cancel("com.marketingcloud.salesforce.notifications.TAG", i2);
                i2--;
                i3++;
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(Context context, NotificationMessage notificationMessage, PendingIntent pendingIntent, boolean z) {
        this.g.b(notificationMessage);
        if (pendingIntent != null) {
            try {
                pendingIntent.send();
            } catch (CanceledException e2) {
                j.c(b, e2, "Failed to send notification's open action PendingIntent.", new Object[0]);
            }
        }
        if (z) {
            c.b(context, notificationMessage);
        }
        context.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.salesforce.marketingcloud.notifications.EXTRA_MESSAGE", notificationMessage);
        c.a(context, com.salesforce.marketingcloud.b.a.BEHAVIOR_SDK_NOTIFICATION_OPENED, bundle);
    }

    private static void a(Context context, String str, com.salesforce.marketingcloud.notifications.c.b bVar) {
        if (TextUtils.isEmpty(str) && bVar == null && g.a(context)) {
            throw new IllegalStateException("A channel name must be provided when target SDK version is at least Android O.");
        }
    }

    /* access modifiers changed from: private */
    public void a(NotificationMessage notificationMessage) {
        synchronized (this.f) {
            if (!this.f.isEmpty()) {
                for (com.salesforce.marketingcloud.notifications.c.d dVar : this.f) {
                    if (dVar != null) {
                        try {
                            dVar.a(notificationMessage);
                        } catch (Exception e2) {
                            j.c(b, e2, "%s threw an exception while processing notification message (%s)", dVar.getClass().getName(), notificationMessage.id());
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final e a() {
        return this.c;
    }

    public void a(int i2) {
    }

    public final void a(com.salesforce.marketingcloud.InitializationStatus.a aVar, int i2) {
        this.i = this.e.e().getBoolean("com.marketingcloud.salesforce.notifications.ENABLED", true);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.salesforce.marketingcloud.notifications.OPENED");
        this.h = new b();
        LocalBroadcastManager.getInstance(this.d).registerReceiver(this.h, intentFilter);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0048, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001f, code lost:
        return;
     */
    @android.support.annotation.RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(@android.support.annotation.NonNull com.salesforce.marketingcloud.notifications.NotificationMessage r7, @android.support.annotation.Nullable final com.salesforce.marketingcloud.notifications.d.a r8) {
        /*
            r6 = this;
            monitor-enter(r6)
            boolean r0 = r6.c()     // Catch:{ all -> 0x007e }
            r1 = 1
            r2 = -1
            r3 = 0
            if (r0 != 0) goto L_0x0020
            java.lang.String r0 = b     // Catch:{ all -> 0x007e }
            java.lang.String r4 = "Notifications are not enabled.  Message %s will not be displayed"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x007e }
            java.lang.String r7 = r7.id()     // Catch:{ all -> 0x007e }
            r1[r3] = r7     // Catch:{ all -> 0x007e }
            com.salesforce.marketingcloud.j.b(r0, r4, r1)     // Catch:{ all -> 0x007e }
            if (r8 == 0) goto L_0x001e
            r8.a(r2)     // Catch:{ all -> 0x007e }
        L_0x001e:
            monitor-exit(r6)
            return
        L_0x0020:
            java.lang.String r0 = r7.alert()     // Catch:{ all -> 0x007e }
            java.lang.String r0 = r0.trim()     // Catch:{ all -> 0x007e }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x007e }
            if (r0 == 0) goto L_0x003c
            r8.a(r2)     // Catch:{ all -> 0x007e }
            java.lang.String r7 = b     // Catch:{ all -> 0x007e }
            java.lang.String r8 = "Notifications with not alert message are not shown."
            java.lang.Object[] r0 = new java.lang.Object[r3]     // Catch:{ all -> 0x007e }
            com.salesforce.marketingcloud.j.b(r7, r8, r0)     // Catch:{ all -> 0x007e }
            monitor-exit(r6)
            return
        L_0x003c:
            int r0 = r7.notificationId()     // Catch:{ all -> 0x007e }
            if (r0 < 0) goto L_0x0049
            if (r8 == 0) goto L_0x0047
            r8.a(r2)     // Catch:{ all -> 0x007e }
        L_0x0047:
            monitor-exit(r6)
            return
        L_0x0049:
            com.salesforce.marketingcloud.d.h r0 = r6.e     // Catch:{ all -> 0x007e }
            android.content.SharedPreferences r0 = r0.e()     // Catch:{ all -> 0x007e }
            java.lang.String r2 = "notification_id_key"
            int r2 = r0.getInt(r2, r3)     // Catch:{ all -> 0x007e }
            com.salesforce.marketingcloud.notifications.NotificationMessage r7 = r7.a(r2)     // Catch:{ all -> 0x007e }
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ all -> 0x007e }
            java.lang.String r2 = "notification_id_key"
            int r4 = r7.notificationId()     // Catch:{ all -> 0x007e }
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 >= r5) goto L_0x006d
            int r3 = r7.notificationId()     // Catch:{ all -> 0x007e }
            int r3 = r3 + r1
        L_0x006d:
            android.content.SharedPreferences$Editor r0 = r0.putInt(r2, r3)     // Catch:{ all -> 0x007e }
            r0.apply()     // Catch:{ all -> 0x007e }
            com.salesforce.marketingcloud.notifications.d$1 r0 = new com.salesforce.marketingcloud.notifications.d$1     // Catch:{ all -> 0x007e }
            r0.<init>(r7, r8)     // Catch:{ all -> 0x007e }
            r0.start()     // Catch:{ all -> 0x007e }
            monitor-exit(r6)
            return
        L_0x007e:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.notifications.d.a(com.salesforce.marketingcloud.notifications.NotificationMessage, com.salesforce.marketingcloud.notifications.d$a):void");
    }

    public final void a(boolean z) {
        if (z) {
            a(this.d);
        }
        if (this.d != null) {
            LocalBroadcastManager.getInstance(this.d).unregisterReceiver(this.h);
        }
    }

    @NonNull
    public final String b() {
        return "NotificationManager";
    }

    public final synchronized boolean c() {
        return this.i;
    }
}
