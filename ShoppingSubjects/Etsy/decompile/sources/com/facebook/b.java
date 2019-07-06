package com.facebook;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.facebook.internal.aa;
import com.facebook.internal.z;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: AccessTokenManager */
public final class b {
    private static volatile b a;
    private final LocalBroadcastManager b;
    private final a c;
    private AccessToken d;
    /* access modifiers changed from: private */
    public AtomicBoolean e = new AtomicBoolean(false);
    private Date f = new Date(0);

    /* compiled from: AccessTokenManager */
    private static class a {
        public String a;
        public int b;

        private a() {
        }
    }

    b(LocalBroadcastManager localBroadcastManager, a aVar) {
        aa.a((Object) localBroadcastManager, "localBroadcastManager");
        aa.a((Object) aVar, "accessTokenCache");
        this.b = localBroadcastManager;
        this.c = aVar;
    }

    static b a() {
        if (a == null) {
            synchronized (b.class) {
                if (a == null) {
                    a = new b(LocalBroadcastManager.getInstance(f.f()), new a());
                }
            }
        }
        return a;
    }

    /* access modifiers changed from: 0000 */
    public AccessToken b() {
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public boolean c() {
        AccessToken a2 = this.c.a();
        if (a2 == null) {
            return false;
        }
        a(a2, false);
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void a(AccessToken accessToken) {
        a(accessToken, true);
    }

    private void a(AccessToken accessToken, boolean z) {
        AccessToken accessToken2 = this.d;
        this.d = accessToken;
        this.e.set(false);
        this.f = new Date(0);
        if (z) {
            if (accessToken != null) {
                this.c.a(accessToken);
            } else {
                this.c.b();
                z.b(f.f());
            }
        }
        if (!z.a(accessToken2, accessToken)) {
            a(accessToken2, accessToken);
            f();
        }
    }

    /* access modifiers changed from: 0000 */
    public void d() {
        a(this.d, this.d);
    }

    private void a(AccessToken accessToken, AccessToken accessToken2) {
        Intent intent = new Intent(f.f(), CurrentAccessTokenExpirationBroadcastReceiver.class);
        intent.setAction("com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED");
        intent.putExtra("com.facebook.sdk.EXTRA_OLD_ACCESS_TOKEN", accessToken);
        intent.putExtra("com.facebook.sdk.EXTRA_NEW_ACCESS_TOKEN", accessToken2);
        this.b.sendBroadcast(intent);
    }

    private void f() {
        Context f2 = f.f();
        AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
        AlarmManager alarmManager = (AlarmManager) f2.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (AccessToken.isCurrentAccessTokenActive() && currentAccessToken.getExpires() != null && alarmManager != null) {
            Intent intent = new Intent(f2, CurrentAccessTokenExpirationBroadcastReceiver.class);
            intent.setAction("com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED");
            alarmManager.set(1, currentAccessToken.getExpires().getTime(), PendingIntent.getBroadcast(f2, 0, intent, 0));
        }
    }

    /* access modifiers changed from: 0000 */
    public void e() {
        if (g()) {
            a((com.facebook.AccessToken.b) null);
        }
    }

    private boolean g() {
        boolean z = false;
        if (this.d == null) {
            return false;
        }
        Long valueOf = Long.valueOf(new Date().getTime());
        if (this.d.getSource().canExtendToken() && valueOf.longValue() - this.f.getTime() > DateUtils.MILLIS_PER_HOUR && valueOf.longValue() - this.d.getLastRefresh().getTime() > DateUtils.MILLIS_PER_DAY) {
            z = true;
        }
        return z;
    }

    private static GraphRequest a(AccessToken accessToken, com.facebook.GraphRequest.b bVar) {
        AccessToken accessToken2 = accessToken;
        GraphRequest graphRequest = new GraphRequest(accessToken2, "me/permissions", new Bundle(), HttpMethod.GET, bVar);
        return graphRequest;
    }

    private static GraphRequest b(AccessToken accessToken, com.facebook.GraphRequest.b bVar) {
        Bundle bundle = new Bundle();
        bundle.putString("grant_type", "fb_extend_sso_token");
        GraphRequest graphRequest = new GraphRequest(accessToken, "oauth/access_token", bundle, HttpMethod.GET, bVar);
        return graphRequest;
    }

    /* access modifiers changed from: 0000 */
    public void a(final com.facebook.AccessToken.b bVar) {
        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            b(bVar);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    b.this.b(bVar);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void b(com.facebook.AccessToken.b bVar) {
        final AccessToken accessToken = this.d;
        if (accessToken == null) {
            if (bVar != null) {
                bVar.a(new FacebookException("No current access token to refresh"));
            }
        } else if (!this.e.compareAndSet(false, true)) {
            if (bVar != null) {
                bVar.a(new FacebookException("Refresh already in progress"));
            }
        } else {
            this.f = new Date();
            final HashSet hashSet = new HashSet();
            final HashSet hashSet2 = new HashSet();
            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            final a aVar = new a();
            g gVar = new g(a(accessToken, (com.facebook.GraphRequest.b) new com.facebook.GraphRequest.b() {
                public void a(GraphResponse graphResponse) {
                    JSONObject b2 = graphResponse.b();
                    if (b2 != null) {
                        JSONArray optJSONArray = b2.optJSONArray("data");
                        if (optJSONArray != null) {
                            atomicBoolean.set(true);
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                                if (optJSONObject != null) {
                                    String optString = optJSONObject.optString("permission");
                                    String optString2 = optJSONObject.optString("status");
                                    if (!z.a(optString) && !z.a(optString2)) {
                                        String lowerCase = optString2.toLowerCase(Locale.US);
                                        if (lowerCase.equals("granted")) {
                                            hashSet.add(optString);
                                        } else if (lowerCase.equals("declined")) {
                                            hashSet2.add(optString);
                                        } else {
                                            StringBuilder sb = new StringBuilder();
                                            sb.append("Unexpected status: ");
                                            sb.append(lowerCase);
                                            Log.w("AccessTokenManager", sb.toString());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }), b(accessToken, new com.facebook.GraphRequest.b() {
                public void a(GraphResponse graphResponse) {
                    JSONObject b2 = graphResponse.b();
                    if (b2 != null) {
                        aVar.a = b2.optString(AccessToken.ACCESS_TOKEN_KEY);
                        aVar.b = b2.optInt("expires_at");
                    }
                }
            }));
            final com.facebook.AccessToken.b bVar2 = bVar;
            AnonymousClass4 r0 = new com.facebook.g.a() {
                /* JADX WARNING: Unknown top exception splitter block from list: {B:18:0x0052=Splitter:B:18:0x0052, B:47:0x00e3=Splitter:B:47:0x00e3} */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void a(com.facebook.g r15) {
                    /*
                        r14 = this;
                        r15 = 0
                        r0 = 0
                        com.facebook.b r1 = com.facebook.b.a()     // Catch:{ all -> 0x00ff }
                        com.facebook.AccessToken r1 = r1.b()     // Catch:{ all -> 0x00ff }
                        if (r1 == 0) goto L_0x00e3
                        com.facebook.b r1 = com.facebook.b.a()     // Catch:{ all -> 0x00ff }
                        com.facebook.AccessToken r1 = r1.b()     // Catch:{ all -> 0x00ff }
                        java.lang.String r1 = r1.getUserId()     // Catch:{ all -> 0x00ff }
                        com.facebook.AccessToken r2 = r2     // Catch:{ all -> 0x00ff }
                        java.lang.String r2 = r2.getUserId()     // Catch:{ all -> 0x00ff }
                        if (r1 == r2) goto L_0x0022
                        goto L_0x00e3
                    L_0x0022:
                        java.util.concurrent.atomic.AtomicBoolean r1 = r4     // Catch:{ all -> 0x00ff }
                        boolean r1 = r1.get()     // Catch:{ all -> 0x00ff }
                        if (r1 != 0) goto L_0x0052
                        com.facebook.b$a r1 = r5     // Catch:{ all -> 0x00ff }
                        java.lang.String r1 = r1.a     // Catch:{ all -> 0x00ff }
                        if (r1 != 0) goto L_0x0052
                        com.facebook.b$a r1 = r5     // Catch:{ all -> 0x00ff }
                        int r1 = r1.b     // Catch:{ all -> 0x00ff }
                        if (r1 != 0) goto L_0x0052
                        com.facebook.AccessToken$b r1 = r3     // Catch:{ all -> 0x00ff }
                        if (r1 == 0) goto L_0x0046
                        com.facebook.AccessToken$b r1 = r3     // Catch:{ all -> 0x00ff }
                        com.facebook.FacebookException r2 = new com.facebook.FacebookException     // Catch:{ all -> 0x00ff }
                        java.lang.String r3 = "Failed to refresh access token"
                        r2.<init>(r3)     // Catch:{ all -> 0x00ff }
                        r1.a(r2)     // Catch:{ all -> 0x00ff }
                    L_0x0046:
                        com.facebook.b r0 = com.facebook.b.this
                        java.util.concurrent.atomic.AtomicBoolean r0 = r0.e
                        r0.set(r15)
                        com.facebook.AccessToken$b r15 = r3
                        return
                    L_0x0052:
                        com.facebook.AccessToken r1 = new com.facebook.AccessToken     // Catch:{ all -> 0x00ff }
                        com.facebook.b$a r2 = r5     // Catch:{ all -> 0x00ff }
                        java.lang.String r2 = r2.a     // Catch:{ all -> 0x00ff }
                        if (r2 == 0) goto L_0x005f
                        com.facebook.b$a r2 = r5     // Catch:{ all -> 0x00ff }
                        java.lang.String r2 = r2.a     // Catch:{ all -> 0x00ff }
                        goto L_0x0065
                    L_0x005f:
                        com.facebook.AccessToken r2 = r2     // Catch:{ all -> 0x00ff }
                        java.lang.String r2 = r2.getToken()     // Catch:{ all -> 0x00ff }
                    L_0x0065:
                        r3 = r2
                        com.facebook.AccessToken r2 = r2     // Catch:{ all -> 0x00ff }
                        java.lang.String r4 = r2.getApplicationId()     // Catch:{ all -> 0x00ff }
                        com.facebook.AccessToken r2 = r2     // Catch:{ all -> 0x00ff }
                        java.lang.String r5 = r2.getUserId()     // Catch:{ all -> 0x00ff }
                        java.util.concurrent.atomic.AtomicBoolean r2 = r4     // Catch:{ all -> 0x00ff }
                        boolean r2 = r2.get()     // Catch:{ all -> 0x00ff }
                        if (r2 == 0) goto L_0x007e
                        java.util.Set r2 = r6     // Catch:{ all -> 0x00ff }
                    L_0x007c:
                        r6 = r2
                        goto L_0x0085
                    L_0x007e:
                        com.facebook.AccessToken r2 = r2     // Catch:{ all -> 0x00ff }
                        java.util.Set r2 = r2.getPermissions()     // Catch:{ all -> 0x00ff }
                        goto L_0x007c
                    L_0x0085:
                        java.util.concurrent.atomic.AtomicBoolean r2 = r4     // Catch:{ all -> 0x00ff }
                        boolean r2 = r2.get()     // Catch:{ all -> 0x00ff }
                        if (r2 == 0) goto L_0x0091
                        java.util.Set r2 = r7     // Catch:{ all -> 0x00ff }
                    L_0x008f:
                        r7 = r2
                        goto L_0x0098
                    L_0x0091:
                        com.facebook.AccessToken r2 = r2     // Catch:{ all -> 0x00ff }
                        java.util.Set r2 = r2.getDeclinedPermissions()     // Catch:{ all -> 0x00ff }
                        goto L_0x008f
                    L_0x0098:
                        com.facebook.AccessToken r2 = r2     // Catch:{ all -> 0x00ff }
                        com.facebook.AccessTokenSource r8 = r2.getSource()     // Catch:{ all -> 0x00ff }
                        com.facebook.b$a r2 = r5     // Catch:{ all -> 0x00ff }
                        int r2 = r2.b     // Catch:{ all -> 0x00ff }
                        if (r2 == 0) goto L_0x00b2
                        java.util.Date r2 = new java.util.Date     // Catch:{ all -> 0x00ff }
                        com.facebook.b$a r9 = r5     // Catch:{ all -> 0x00ff }
                        int r9 = r9.b     // Catch:{ all -> 0x00ff }
                        long r9 = (long) r9     // Catch:{ all -> 0x00ff }
                        r11 = 1000(0x3e8, double:4.94E-321)
                        long r9 = r9 * r11
                        r2.<init>(r9)     // Catch:{ all -> 0x00ff }
                        goto L_0x00b8
                    L_0x00b2:
                        com.facebook.AccessToken r2 = r2     // Catch:{ all -> 0x00ff }
                        java.util.Date r2 = r2.getExpires()     // Catch:{ all -> 0x00ff }
                    L_0x00b8:
                        r9 = r2
                        java.util.Date r10 = new java.util.Date     // Catch:{ all -> 0x00ff }
                        r10.<init>()     // Catch:{ all -> 0x00ff }
                        r2 = r1
                        r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x00ff }
                        com.facebook.b r0 = com.facebook.b.a()     // Catch:{ all -> 0x00de }
                        r0.a(r1)     // Catch:{ all -> 0x00de }
                        com.facebook.b r0 = com.facebook.b.this
                        java.util.concurrent.atomic.AtomicBoolean r0 = r0.e
                        r0.set(r15)
                        com.facebook.AccessToken$b r15 = r3
                        if (r15 == 0) goto L_0x00dd
                        if (r1 == 0) goto L_0x00dd
                        com.facebook.AccessToken$b r15 = r3
                        r15.a(r1)
                    L_0x00dd:
                        return
                    L_0x00de:
                        r0 = move-exception
                        r13 = r1
                        r1 = r0
                        r0 = r13
                        goto L_0x0100
                    L_0x00e3:
                        com.facebook.AccessToken$b r1 = r3     // Catch:{ all -> 0x00ff }
                        if (r1 == 0) goto L_0x00f3
                        com.facebook.AccessToken$b r1 = r3     // Catch:{ all -> 0x00ff }
                        com.facebook.FacebookException r2 = new com.facebook.FacebookException     // Catch:{ all -> 0x00ff }
                        java.lang.String r3 = "No current access token to refresh"
                        r2.<init>(r3)     // Catch:{ all -> 0x00ff }
                        r1.a(r2)     // Catch:{ all -> 0x00ff }
                    L_0x00f3:
                        com.facebook.b r0 = com.facebook.b.this
                        java.util.concurrent.atomic.AtomicBoolean r0 = r0.e
                        r0.set(r15)
                        com.facebook.AccessToken$b r15 = r3
                        return
                    L_0x00ff:
                        r1 = move-exception
                    L_0x0100:
                        com.facebook.b r2 = com.facebook.b.this
                        java.util.concurrent.atomic.AtomicBoolean r2 = r2.e
                        r2.set(r15)
                        com.facebook.AccessToken$b r15 = r3
                        if (r15 == 0) goto L_0x0114
                        if (r0 == 0) goto L_0x0114
                        com.facebook.AccessToken$b r15 = r3
                        r15.a(r0)
                    L_0x0114:
                        throw r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.facebook.b.AnonymousClass4.a(com.facebook.g):void");
                }
            };
            gVar.a((com.facebook.g.a) r0);
            gVar.h();
        }
    }
}
