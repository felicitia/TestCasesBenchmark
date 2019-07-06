package com.facebook;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.facebook.AccessToken.AccessTokenRefreshCallback;
import com.facebook.GraphRequest.Callback;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

final class AccessTokenManager {
    private static volatile AccessTokenManager instance;
    private final AccessTokenCache accessTokenCache;
    private AccessToken currentAccessToken;
    private Date lastAttemptedTokenExtendDate = new Date(0);
    private final LocalBroadcastManager localBroadcastManager;
    /* access modifiers changed from: private */
    public AtomicBoolean tokenRefreshInProgress = new AtomicBoolean(false);

    private static class RefreshResult {
        public String accessToken;
        public int expiresAt;

        private RefreshResult() {
        }
    }

    AccessTokenManager(LocalBroadcastManager localBroadcastManager2, AccessTokenCache accessTokenCache2) {
        Validate.notNull(localBroadcastManager2, "localBroadcastManager");
        Validate.notNull(accessTokenCache2, "accessTokenCache");
        this.localBroadcastManager = localBroadcastManager2;
        this.accessTokenCache = accessTokenCache2;
    }

    static AccessTokenManager getInstance() {
        if (instance == null) {
            synchronized (AccessTokenManager.class) {
                if (instance == null) {
                    instance = new AccessTokenManager(LocalBroadcastManager.getInstance(FacebookSdk.getApplicationContext()), new AccessTokenCache());
                }
            }
        }
        return instance;
    }

    /* access modifiers changed from: 0000 */
    public AccessToken getCurrentAccessToken() {
        return this.currentAccessToken;
    }

    /* access modifiers changed from: 0000 */
    public boolean loadCurrentAccessToken() {
        AccessToken load = this.accessTokenCache.load();
        if (load == null) {
            return false;
        }
        setCurrentAccessToken(load, false);
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void setCurrentAccessToken(AccessToken accessToken) {
        setCurrentAccessToken(accessToken, true);
    }

    private void setCurrentAccessToken(AccessToken accessToken, boolean z) {
        AccessToken accessToken2 = this.currentAccessToken;
        this.currentAccessToken = accessToken;
        this.tokenRefreshInProgress.set(false);
        this.lastAttemptedTokenExtendDate = new Date(0);
        if (z) {
            if (accessToken != null) {
                this.accessTokenCache.save(accessToken);
            } else {
                this.accessTokenCache.clear();
                Utility.clearFacebookCookies(FacebookSdk.getApplicationContext());
            }
        }
        if (!Utility.areObjectsEqual(accessToken2, accessToken)) {
            sendCurrentAccessTokenChangedBroadcast(accessToken2, accessToken);
        }
    }

    private void sendCurrentAccessTokenChangedBroadcast(AccessToken accessToken, AccessToken accessToken2) {
        Intent intent = new Intent("com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED");
        intent.putExtra("com.facebook.sdk.EXTRA_OLD_ACCESS_TOKEN", accessToken);
        intent.putExtra("com.facebook.sdk.EXTRA_NEW_ACCESS_TOKEN", accessToken2);
        this.localBroadcastManager.sendBroadcast(intent);
    }

    /* access modifiers changed from: 0000 */
    public void extendAccessTokenIfNeeded() {
        if (shouldExtendAccessToken()) {
            refreshCurrentAccessToken(null);
        }
    }

    private boolean shouldExtendAccessToken() {
        boolean z = false;
        if (this.currentAccessToken == null) {
            return false;
        }
        Long valueOf = Long.valueOf(new Date().getTime());
        if (this.currentAccessToken.getSource().canExtendToken() && valueOf.longValue() - this.lastAttemptedTokenExtendDate.getTime() > 3600000 && valueOf.longValue() - this.currentAccessToken.getLastRefresh().getTime() > 86400000) {
            z = true;
        }
        return z;
    }

    private static GraphRequest createGrantedPermissionsRequest(AccessToken accessToken, Callback callback) {
        AccessToken accessToken2 = accessToken;
        GraphRequest graphRequest = new GraphRequest(accessToken2, "me/permissions", new Bundle(), HttpMethod.GET, callback);
        return graphRequest;
    }

    private static GraphRequest createExtendAccessTokenRequest(AccessToken accessToken, Callback callback) {
        Bundle bundle = new Bundle();
        bundle.putString("grant_type", "fb_extend_sso_token");
        GraphRequest graphRequest = new GraphRequest(accessToken, "oauth/access_token", bundle, HttpMethod.GET, callback);
        return graphRequest;
    }

    /* access modifiers changed from: 0000 */
    public void refreshCurrentAccessToken(final AccessTokenRefreshCallback accessTokenRefreshCallback) {
        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            refreshCurrentAccessTokenImpl(accessTokenRefreshCallback);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    AccessTokenManager.this.refreshCurrentAccessTokenImpl(accessTokenRefreshCallback);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void refreshCurrentAccessTokenImpl(AccessTokenRefreshCallback accessTokenRefreshCallback) {
        final AccessToken accessToken = this.currentAccessToken;
        if (accessToken == null) {
            if (accessTokenRefreshCallback != null) {
                accessTokenRefreshCallback.OnTokenRefreshFailed(new FacebookException("No current access token to refresh"));
            }
        } else if (!this.tokenRefreshInProgress.compareAndSet(false, true)) {
            if (accessTokenRefreshCallback != null) {
                accessTokenRefreshCallback.OnTokenRefreshFailed(new FacebookException("Refresh already in progress"));
            }
        } else {
            this.lastAttemptedTokenExtendDate = new Date();
            final HashSet hashSet = new HashSet();
            final HashSet hashSet2 = new HashSet();
            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            final RefreshResult refreshResult = new RefreshResult();
            GraphRequestBatch graphRequestBatch = new GraphRequestBatch(createGrantedPermissionsRequest(accessToken, new Callback() {
                public void onCompleted(GraphResponse graphResponse) {
                    JSONObject jSONObject = graphResponse.getJSONObject();
                    if (jSONObject != null) {
                        JSONArray optJSONArray = jSONObject.optJSONArray("data");
                        if (optJSONArray != null) {
                            atomicBoolean.set(true);
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                                if (optJSONObject != null) {
                                    String optString = optJSONObject.optString("permission");
                                    String optString2 = optJSONObject.optString("status");
                                    if (!Utility.isNullOrEmpty(optString) && !Utility.isNullOrEmpty(optString2)) {
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
            }), createExtendAccessTokenRequest(accessToken, new Callback() {
                public void onCompleted(GraphResponse graphResponse) {
                    JSONObject jSONObject = graphResponse.getJSONObject();
                    if (jSONObject != null) {
                        refreshResult.accessToken = jSONObject.optString("access_token");
                        refreshResult.expiresAt = jSONObject.optInt("expires_at");
                    }
                }
            }));
            final AccessTokenRefreshCallback accessTokenRefreshCallback2 = accessTokenRefreshCallback;
            AnonymousClass4 r0 = new GraphRequestBatch.Callback() {
                /* JADX WARNING: Unknown top exception splitter block from list: {B:18:0x0052=Splitter:B:18:0x0052, B:47:0x00e4=Splitter:B:47:0x00e4} */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onBatchCompleted(com.facebook.GraphRequestBatch r15) {
                    /*
                        r14 = this;
                        r15 = 0
                        r0 = 0
                        com.facebook.AccessTokenManager r1 = com.facebook.AccessTokenManager.getInstance()     // Catch:{ all -> 0x0100 }
                        com.facebook.AccessToken r1 = r1.getCurrentAccessToken()     // Catch:{ all -> 0x0100 }
                        if (r1 == 0) goto L_0x00e4
                        com.facebook.AccessTokenManager r1 = com.facebook.AccessTokenManager.getInstance()     // Catch:{ all -> 0x0100 }
                        com.facebook.AccessToken r1 = r1.getCurrentAccessToken()     // Catch:{ all -> 0x0100 }
                        java.lang.String r1 = r1.getUserId()     // Catch:{ all -> 0x0100 }
                        com.facebook.AccessToken r2 = r2     // Catch:{ all -> 0x0100 }
                        java.lang.String r2 = r2.getUserId()     // Catch:{ all -> 0x0100 }
                        if (r1 == r2) goto L_0x0022
                        goto L_0x00e4
                    L_0x0022:
                        java.util.concurrent.atomic.AtomicBoolean r1 = r4     // Catch:{ all -> 0x0100 }
                        boolean r1 = r1.get()     // Catch:{ all -> 0x0100 }
                        if (r1 != 0) goto L_0x0052
                        com.facebook.AccessTokenManager$RefreshResult r1 = r5     // Catch:{ all -> 0x0100 }
                        java.lang.String r1 = r1.accessToken     // Catch:{ all -> 0x0100 }
                        if (r1 != 0) goto L_0x0052
                        com.facebook.AccessTokenManager$RefreshResult r1 = r5     // Catch:{ all -> 0x0100 }
                        int r1 = r1.expiresAt     // Catch:{ all -> 0x0100 }
                        if (r1 != 0) goto L_0x0052
                        com.facebook.AccessToken$AccessTokenRefreshCallback r1 = r3     // Catch:{ all -> 0x0100 }
                        if (r1 == 0) goto L_0x0046
                        com.facebook.AccessToken$AccessTokenRefreshCallback r1 = r3     // Catch:{ all -> 0x0100 }
                        com.facebook.FacebookException r2 = new com.facebook.FacebookException     // Catch:{ all -> 0x0100 }
                        java.lang.String r3 = "Failed to refresh access token"
                        r2.<init>(r3)     // Catch:{ all -> 0x0100 }
                        r1.OnTokenRefreshFailed(r2)     // Catch:{ all -> 0x0100 }
                    L_0x0046:
                        com.facebook.AccessTokenManager r0 = com.facebook.AccessTokenManager.this
                        java.util.concurrent.atomic.AtomicBoolean r0 = r0.tokenRefreshInProgress
                        r0.set(r15)
                        com.facebook.AccessToken$AccessTokenRefreshCallback r15 = r3
                        return
                    L_0x0052:
                        com.facebook.AccessToken r1 = new com.facebook.AccessToken     // Catch:{ all -> 0x0100 }
                        com.facebook.AccessTokenManager$RefreshResult r2 = r5     // Catch:{ all -> 0x0100 }
                        java.lang.String r2 = r2.accessToken     // Catch:{ all -> 0x0100 }
                        if (r2 == 0) goto L_0x005f
                        com.facebook.AccessTokenManager$RefreshResult r2 = r5     // Catch:{ all -> 0x0100 }
                        java.lang.String r2 = r2.accessToken     // Catch:{ all -> 0x0100 }
                        goto L_0x0065
                    L_0x005f:
                        com.facebook.AccessToken r2 = r2     // Catch:{ all -> 0x0100 }
                        java.lang.String r2 = r2.getToken()     // Catch:{ all -> 0x0100 }
                    L_0x0065:
                        r3 = r2
                        com.facebook.AccessToken r2 = r2     // Catch:{ all -> 0x0100 }
                        java.lang.String r4 = r2.getApplicationId()     // Catch:{ all -> 0x0100 }
                        com.facebook.AccessToken r2 = r2     // Catch:{ all -> 0x0100 }
                        java.lang.String r5 = r2.getUserId()     // Catch:{ all -> 0x0100 }
                        java.util.concurrent.atomic.AtomicBoolean r2 = r4     // Catch:{ all -> 0x0100 }
                        boolean r2 = r2.get()     // Catch:{ all -> 0x0100 }
                        if (r2 == 0) goto L_0x007e
                        java.util.Set r2 = r6     // Catch:{ all -> 0x0100 }
                    L_0x007c:
                        r6 = r2
                        goto L_0x0085
                    L_0x007e:
                        com.facebook.AccessToken r2 = r2     // Catch:{ all -> 0x0100 }
                        java.util.Set r2 = r2.getPermissions()     // Catch:{ all -> 0x0100 }
                        goto L_0x007c
                    L_0x0085:
                        java.util.concurrent.atomic.AtomicBoolean r2 = r4     // Catch:{ all -> 0x0100 }
                        boolean r2 = r2.get()     // Catch:{ all -> 0x0100 }
                        if (r2 == 0) goto L_0x0091
                        java.util.Set r2 = r7     // Catch:{ all -> 0x0100 }
                    L_0x008f:
                        r7 = r2
                        goto L_0x0098
                    L_0x0091:
                        com.facebook.AccessToken r2 = r2     // Catch:{ all -> 0x0100 }
                        java.util.Set r2 = r2.getDeclinedPermissions()     // Catch:{ all -> 0x0100 }
                        goto L_0x008f
                    L_0x0098:
                        com.facebook.AccessToken r2 = r2     // Catch:{ all -> 0x0100 }
                        com.facebook.AccessTokenSource r8 = r2.getSource()     // Catch:{ all -> 0x0100 }
                        com.facebook.AccessTokenManager$RefreshResult r2 = r5     // Catch:{ all -> 0x0100 }
                        int r2 = r2.expiresAt     // Catch:{ all -> 0x0100 }
                        if (r2 == 0) goto L_0x00b3
                        java.util.Date r2 = new java.util.Date     // Catch:{ all -> 0x0100 }
                        com.facebook.AccessTokenManager$RefreshResult r9 = r5     // Catch:{ all -> 0x0100 }
                        int r9 = r9.expiresAt     // Catch:{ all -> 0x0100 }
                        long r9 = (long) r9     // Catch:{ all -> 0x0100 }
                        r11 = 1000(0x3e8, double:4.94E-321)
                        long r9 = r9 * r11
                        r2.<init>(r9)     // Catch:{ all -> 0x0100 }
                        goto L_0x00b9
                    L_0x00b3:
                        com.facebook.AccessToken r2 = r2     // Catch:{ all -> 0x0100 }
                        java.util.Date r2 = r2.getExpires()     // Catch:{ all -> 0x0100 }
                    L_0x00b9:
                        r9 = r2
                        java.util.Date r10 = new java.util.Date     // Catch:{ all -> 0x0100 }
                        r10.<init>()     // Catch:{ all -> 0x0100 }
                        r2 = r1
                        r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x0100 }
                        com.facebook.AccessTokenManager r0 = com.facebook.AccessTokenManager.getInstance()     // Catch:{ all -> 0x00df }
                        r0.setCurrentAccessToken(r1)     // Catch:{ all -> 0x00df }
                        com.facebook.AccessTokenManager r0 = com.facebook.AccessTokenManager.this
                        java.util.concurrent.atomic.AtomicBoolean r0 = r0.tokenRefreshInProgress
                        r0.set(r15)
                        com.facebook.AccessToken$AccessTokenRefreshCallback r15 = r3
                        if (r15 == 0) goto L_0x00de
                        if (r1 == 0) goto L_0x00de
                        com.facebook.AccessToken$AccessTokenRefreshCallback r15 = r3
                        r15.OnTokenRefreshed(r1)
                    L_0x00de:
                        return
                    L_0x00df:
                        r0 = move-exception
                        r13 = r1
                        r1 = r0
                        r0 = r13
                        goto L_0x0101
                    L_0x00e4:
                        com.facebook.AccessToken$AccessTokenRefreshCallback r1 = r3     // Catch:{ all -> 0x0100 }
                        if (r1 == 0) goto L_0x00f4
                        com.facebook.AccessToken$AccessTokenRefreshCallback r1 = r3     // Catch:{ all -> 0x0100 }
                        com.facebook.FacebookException r2 = new com.facebook.FacebookException     // Catch:{ all -> 0x0100 }
                        java.lang.String r3 = "No current access token to refresh"
                        r2.<init>(r3)     // Catch:{ all -> 0x0100 }
                        r1.OnTokenRefreshFailed(r2)     // Catch:{ all -> 0x0100 }
                    L_0x00f4:
                        com.facebook.AccessTokenManager r0 = com.facebook.AccessTokenManager.this
                        java.util.concurrent.atomic.AtomicBoolean r0 = r0.tokenRefreshInProgress
                        r0.set(r15)
                        com.facebook.AccessToken$AccessTokenRefreshCallback r15 = r3
                        return
                    L_0x0100:
                        r1 = move-exception
                    L_0x0101:
                        com.facebook.AccessTokenManager r2 = com.facebook.AccessTokenManager.this
                        java.util.concurrent.atomic.AtomicBoolean r2 = r2.tokenRefreshInProgress
                        r2.set(r15)
                        com.facebook.AccessToken$AccessTokenRefreshCallback r15 = r3
                        if (r15 == 0) goto L_0x0115
                        if (r0 == 0) goto L_0x0115
                        com.facebook.AccessToken$AccessTokenRefreshCallback r15 = r3
                        r15.OnTokenRefreshed(r0)
                    L_0x0115:
                        throw r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.facebook.AccessTokenManager.AnonymousClass4.onBatchCompleted(com.facebook.GraphRequestBatch):void");
                }
            };
            graphRequestBatch.addCallback(r0);
            graphRequestBatch.executeAsync();
        }
    }
}
