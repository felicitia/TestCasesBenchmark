package com.salesforce.marketingcloud.messages.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.util.ArraySet;
import android.text.TextUtils;
import com.salesforce.marketingcloud.a.a.C0160a;
import com.salesforce.marketingcloud.b.c;
import com.salesforce.marketingcloud.e.f;
import com.salesforce.marketingcloud.h;
import com.salesforce.marketingcloud.j;
import com.salesforce.marketingcloud.k;
import com.salesforce.marketingcloud.messages.push.a.C0172a;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.notifications.d;
import java.util.Set;

@RestrictTo({Scope.LIBRARY})
public class b extends a implements com.salesforce.marketingcloud.a.b.a, h {
    /* access modifiers changed from: private */
    public static final String c = j.a(b.class);
    private final Context d;
    private final d e;
    private final com.salesforce.marketingcloud.a.b f;
    private final Set<com.salesforce.marketingcloud.messages.push.a.b> g;
    private final com.salesforce.marketingcloud.d.h h;
    private final String i;
    private final Set<C0172a> j;
    private int k;
    private BroadcastReceiver l;
    private boolean m = false;

    private class a extends BroadcastReceiver {
        private a() {
        }

        public void onReceive(Context context, Intent intent) {
            String g;
            String str;
            if (intent == null) {
                g = b.c;
                str = "Received null intent";
            } else {
                String action = intent.getAction();
                if (action == null) {
                    g = b.c;
                    str = "Received null action";
                } else {
                    char c = 65535;
                    int hashCode = action.hashCode();
                    if (hashCode != -1017383756) {
                        if (hashCode == -558520539 && action.equals("com.salesforce.marketingcloud.messages.push.TOKEN_REFRESHED")) {
                            c = 1;
                        }
                    } else if (action.equals("com.salesforce.marketingcloud.messages.GCM_RECEIVED")) {
                        c = 0;
                    }
                    switch (c) {
                        case 0:
                            b.this.e(intent.getBundleExtra("com.salesforce.marketingcloud.messages.GCM_DATA"));
                            return;
                        case 1:
                            b.this.c(intent.getExtras());
                            return;
                        default:
                            j.b(b.c, "Received unknown action: %s", action);
                            return;
                    }
                }
            }
            j.a(g, str, new Object[0]);
        }
    }

    public b(@NonNull Context context, @NonNull com.salesforce.marketingcloud.d.h hVar, @NonNull d dVar, @NonNull com.salesforce.marketingcloud.a.b bVar, @Nullable String str) {
        this.d = (Context) f.a(context, "Content is null");
        this.h = (com.salesforce.marketingcloud.d.h) f.a(hVar, "Storage is null");
        this.e = (d) f.a(dVar, "NotificationManager is null");
        this.f = (com.salesforce.marketingcloud.a.b) f.a(bVar, "AlarmScheduler is null");
        this.i = str;
        this.g = new ArraySet();
        this.j = new ArraySet();
    }

    public static void a(@NonNull Context context, @NonNull Bundle bundle) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.salesforce.marketingcloud.messages.GCM_RECEIVED").putExtra("com.salesforce.marketingcloud.messages.GCM_DATA", bundle));
    }

    public static void a(@NonNull Context context, boolean z, String str, String str2) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.salesforce.marketingcloud.messages.push.TOKEN_REFRESHED").putExtra("com.salesforce.marketingcloud.push.TOKEN_REFRESH_SUCCESSFUL", z).putExtra("com.salesforce.marketingcloud.push.TOKEN_SENDER_ID", str).putExtra(b, str2));
    }

    private void a(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(b, str);
        c.a(this.d, com.salesforce.marketingcloud.b.a.BEHAVIOR_SDK_TOKEN_REFRESHED, bundle);
    }

    private void b(String str) {
        synchronized (this.j) {
            for (C0172a aVar : this.j) {
                if (aVar != null) {
                    try {
                        aVar.a(str);
                    } catch (Exception e2) {
                        j.c(c, e2, "%s threw an exception while processing the token refresh", aVar.getClass().getName());
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void c(Bundle bundle) {
        com.salesforce.marketingcloud.d.c d2 = this.h.d();
        if (bundle.getBoolean("com.salesforce.marketingcloud.push.TOKEN_REFRESH_SUCCESSFUL", false)) {
            String string = bundle.getString(b, "");
            d2.a("gcm_reg_id_key", string);
            d2.a("gcm_sender_id", bundle.getString("com.salesforce.marketingcloud.push.TOKEN_SENDER_ID", ""));
            a(string);
            this.f.c(C0160a.GCM_REGISTRATION);
            b(string);
            return;
        }
        d2.a("gcm_sender_id");
        this.f.b(C0160a.GCM_REGISTRATION);
    }

    private boolean d(Bundle bundle) {
        String str;
        String str2;
        if (com.salesforce.marketingcloud.d.b(this.k, 4)) {
            str = c;
            str2 = "Blocking push message.  Received a push message when the push feature is blocked.";
        } else if (!com.salesforce.marketingcloud.d.b(this.k, 128) || !com.salesforce.marketingcloud.messages.c.c.a(bundle)) {
            return false;
        } else {
            str = c;
            str2 = "Blocking push message.  Received an inbox message when the inbox feature is blocked.";
        }
        j.b(str, str2, new Object[0]);
        return true;
    }

    /* access modifiers changed from: private */
    public void e(Bundle bundle) {
        if (bundle != null && !d(bundle)) {
            c.a(this.d, com.salesforce.marketingcloud.b.a.BEHAVIOR_SDK_PUSH_RECEIVED, new Bundle(bundle));
            if (bundle.containsKey("_nodes")) {
                j.a(c, "Control channel push received.", new Object[0]);
            } else if (c()) {
                if (bundle.containsKey("content-available")) {
                    f(bundle);
                } else if (bundle.containsKey("_c")) {
                    g(bundle);
                } else {
                    try {
                        NotificationMessage a2 = NotificationMessage.a(this.e, bundle);
                        if (TextUtils.isEmpty(a2.alert().trim())) {
                            j.b(c, "Message (%s) was received but does not have an alert message.", a2.id());
                            return;
                        }
                        this.e.a(a2, (com.salesforce.marketingcloud.notifications.d.a) null);
                    } catch (Exception e2) {
                        j.c(c, e2, "Unable to show GCM notification", new Object[0]);
                    }
                }
            }
        }
    }

    private void f(Bundle bundle) {
        Object obj = bundle.get("content-available");
        int i2 = 0;
        if (obj instanceof String) {
            try {
                i2 = Integer.valueOf((String) obj).intValue();
            } catch (Exception e2) {
                j.c(c, e2, "Unable to parse content avaiable flag: %s", obj);
            }
        } else if (obj instanceof Integer) {
            i2 = ((Integer) obj).intValue();
        }
        if (i2 == 1) {
            h(bundle);
        }
    }

    private void g(Bundle bundle) {
        bundle.remove("_c");
        bundle.remove("_p");
        h(bundle);
    }

    private void h() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(a, this.m);
        c.a(this.d, com.salesforce.marketingcloud.b.a.BEHAVIOR_CUSTOMER_PUSH_MESSAGING_TOGGLED, bundle);
    }

    private void h(Bundle bundle) {
        synchronized (this.g) {
            for (com.salesforce.marketingcloud.messages.push.a.b bVar : this.g) {
                if (bVar != null) {
                    try {
                        bVar.a(bundle);
                    } catch (Exception e2) {
                        j.c(c, e2, "%s threw an exception while processing the silent push message", bVar.getClass().getName());
                    }
                }
            }
        }
    }

    private void i() {
        if (this.h != null) {
            this.h.e().edit().putBoolean("et_push_enabled", this.m).apply();
        }
    }

    @Nullable
    public String a() {
        return this.h.d().b("gcm_reg_id_key", null);
    }

    public void a(int i2) {
        if (com.salesforce.marketingcloud.d.b(i2, 4)) {
            f();
            if (this.l != null) {
                LocalBroadcastManager.getInstance(this.d).unregisterReceiver(this.l);
            }
            this.f.a(C0160a.GCM_REGISTRATION);
            this.f.c(C0160a.GCM_REGISTRATION);
            if (com.salesforce.marketingcloud.d.c(i2, 4)) {
                com.salesforce.marketingcloud.d.c d2 = this.h.d();
                d2.a("gcm_sender_id");
                d2.a("gcm_reg_id_key");
            }
            this.k = i2;
            return;
        }
        if (com.salesforce.marketingcloud.d.b(this.k, 4)) {
            this.k = i2;
            d();
            this.f.a((com.salesforce.marketingcloud.a.b.a) this, C0160a.GCM_REGISTRATION);
            e();
            if (this.i != null) {
                k.b(this.d, this.i);
            }
        }
    }

    public void a(com.salesforce.marketingcloud.InitializationStatus.a aVar, int i2) {
        this.k = i2;
        if (com.salesforce.marketingcloud.d.a(i2, 4)) {
            this.m = this.h.e().getBoolean("et_push_enabled", true);
            d();
            this.f.a((com.salesforce.marketingcloud.a.b.a) this, C0160a.GCM_REGISTRATION);
            if (this.i == null) {
                j.b(c, "No sender id was provided during initialization.  You will not receive GCM Push until a token is manually set.", new Object[0]);
                this.f.c(C0160a.GCM_REGISTRATION);
                this.h.d().a("gcm_sender_id");
            } else if (!this.i.equals(this.h.d().b("gcm_sender_id", null))) {
                j.a(c, "Sender Id has changed.  Refresh system token.", new Object[0]);
                k.b(this.d, this.i);
            }
        }
    }

    public void a(@NonNull C0160a aVar) {
        if (aVar == C0160a.GCM_REGISTRATION && this.i != null) {
            k.b(this.d, this.i);
        }
    }

    public void a(boolean z) {
        if (this.l != null) {
            LocalBroadcastManager.getInstance(this.d).unregisterReceiver(this.l);
        }
    }

    @NonNull
    public String b() {
        return "PushMessageManager";
    }

    public boolean b(@NonNull Bundle bundle) {
        if (!a(bundle)) {
            j.b(c, "handlePushMessage was called with push data that was not sent from the Marketing Cloud.  Ignoring call.", new Object[0]);
            return false;
        }
        e(bundle);
        return true;
    }

    public synchronized boolean c() {
        return this.m;
    }

    /* access modifiers changed from: 0000 */
    public void d() {
        this.l = new a();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.salesforce.marketingcloud.messages.GCM_RECEIVED");
        intentFilter.addAction("com.salesforce.marketingcloud.messages.push.TOKEN_REFRESHED");
        LocalBroadcastManager.getInstance(this.d).registerReceiver(this.l, intentFilter);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void e() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.m     // Catch:{ all -> 0x001c }
            if (r0 != 0) goto L_0x001a
            int r0 = r2.k     // Catch:{ all -> 0x001c }
            r1 = 4
            boolean r0 = com.salesforce.marketingcloud.d.b(r0, r1)     // Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x000f
            goto L_0x001a
        L_0x000f:
            r0 = 1
            r2.m = r0     // Catch:{ all -> 0x001c }
            r2.h()     // Catch:{ all -> 0x001c }
            r2.i()     // Catch:{ all -> 0x001c }
            monitor-exit(r2)
            return
        L_0x001a:
            monitor-exit(r2)
            return
        L_0x001c:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.push.b.e():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void f() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.m     // Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x001a
            int r0 = r2.k     // Catch:{ all -> 0x001c }
            r1 = 4
            boolean r0 = com.salesforce.marketingcloud.d.b(r0, r1)     // Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x000f
            goto L_0x001a
        L_0x000f:
            r0 = 0
            r2.m = r0     // Catch:{ all -> 0x001c }
            r2.h()     // Catch:{ all -> 0x001c }
            r2.i()     // Catch:{ all -> 0x001c }
            monitor-exit(r2)
            return
        L_0x001a:
            monitor-exit(r2)
            return
        L_0x001c:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.push.b.f():void");
    }
}
