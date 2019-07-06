package com.contextlogic.wish.api.service;

import android.os.Handler;
import android.os.Looper;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger;
import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.WishTypeAdapterFactory;
import com.contextlogic.wish.api.datacenter.AuthenticationDataCenter;
import com.contextlogic.wish.application.ApiGuardManager;
import com.contextlogic.wish.application.ApplicationEventManager;
import com.contextlogic.wish.application.ApplicationEventManager.EventType;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.WishHttpClient;
import com.contextlogic.wish.util.NetworkUtil;
import com.facebook.network.connectionclass.DeviceBandwidthSampler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.text.ParseException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import org.json.JSONException;

public abstract class ApiService {
    /* access modifiers changed from: protected */
    public static final Gson gson = new GsonBuilder().registerTypeAdapterFactory(WishTypeAdapterFactory.create()).create();
    /* access modifiers changed from: protected */
    public Handler mHandler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public int mRequestCount;

    protected interface ApiCallback {
        String getCallIdentifier();

        void handleFailure(ApiResponse apiResponse, String str);

        void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException;
    }

    public abstract class CancellableApiCallback implements ApiCallback {
        private boolean mCancelled;

        public CancellableApiCallback() {
        }

        public void cancel() {
            this.mCancelled = true;
        }

        public boolean isCancelled() {
            return this.mCancelled;
        }
    }

    public interface DefaultCodeFailureCallback {
        void onFailure(String str, int i);
    }

    public interface DefaultFailureCallback {
        void onFailure(String str);
    }

    public interface DefaultSuccessCallback {
        void onSuccess();
    }

    /* access modifiers changed from: protected */
    public int[] getSuccessLikeErrorCodes() {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean shouldSampleConnection() {
        return false;
    }

    public void postRunnable(Runnable runnable) {
        this.mHandler.post(runnable);
    }

    /* access modifiers changed from: private */
    public Call queueRequest(final ApiRequest apiRequest, final ApiCallback apiCallback) {
        this.mRequestCount++;
        GoogleAnalyticsLogger.getInstance().logLastApiRequest(apiRequest);
        if (shouldSampleConnection() && !DeviceBandwidthSampler.getInstance().isSampling()) {
            DeviceBandwidthSampler.getInstance().startSampling();
        }
        final boolean canUseApiGuard = ApiGuardManager.getInstance().canUseApiGuard();
        Request buildRequest = apiRequest.buildRequest();
        if (canUseApiGuard) {
            buildRequest = ApiGuardManager.getInstance().getApiGuard().transformRequest(buildRequest);
        }
        return WishHttpClient.getInstance().startRequest(buildRequest, new Callback() {
            public void onFailure(Call call, IOException iOException) {
                if (ApiService.this.shouldSampleConnection() && DeviceBandwidthSampler.getInstance().isSampling()) {
                    DeviceBandwidthSampler.getInstance().stopSampling();
                }
                if (!NetworkUtil.isNetworkAvailable()) {
                    apiCallback.handleFailure(null, WishApplication.getInstance().getString(R.string.device_lost_network));
                } else {
                    apiCallback.handleFailure(null, null);
                }
            }

            /* JADX WARNING: Removed duplicated region for block: B:52:0x0124 A[SYNTHETIC, Splitter:B:52:0x0124] */
            /* JADX WARNING: Removed duplicated region for block: B:56:0x0129 A[SYNTHETIC, Splitter:B:56:0x0129] */
            /* JADX WARNING: Removed duplicated region for block: B:70:0x0140 A[SYNTHETIC, Splitter:B:70:0x0140] */
            /* JADX WARNING: Removed duplicated region for block: B:74:0x0145 A[SYNTHETIC, Splitter:B:74:0x0145] */
            /* JADX WARNING: Removed duplicated region for block: B:87:0x015d  */
            /* JADX WARNING: Removed duplicated region for block: B:91:0x0177 A[SYNTHETIC, Splitter:B:91:0x0177] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onResponse(okhttp3.Call r9, okhttp3.Response r10) throws java.io.IOException {
                /*
                    r8 = this;
                    com.contextlogic.wish.api.service.ApiService r9 = com.contextlogic.wish.api.service.ApiService.this
                    boolean r9 = r9.shouldSampleConnection()
                    if (r9 == 0) goto L_0x0019
                    com.facebook.network.connectionclass.DeviceBandwidthSampler r9 = com.facebook.network.connectionclass.DeviceBandwidthSampler.getInstance()
                    boolean r9 = r9.isSampling()
                    if (r9 == 0) goto L_0x0019
                    com.facebook.network.connectionclass.DeviceBandwidthSampler r9 = com.facebook.network.connectionclass.DeviceBandwidthSampler.getInstance()
                    r9.stopSampling()
                L_0x0019:
                    if (r10 == 0) goto L_0x002b
                    boolean r9 = r0
                    if (r9 == 0) goto L_0x002b
                    com.contextlogic.wish.application.ApiGuardManager r9 = com.contextlogic.wish.application.ApiGuardManager.getInstance()
                    com.apiguard.APIGuard r9 = r9.getApiGuard()
                    okhttp3.Response r10 = r9.transformResponse(r10)
                L_0x002b:
                    if (r10 == 0) goto L_0x00db
                    int r9 = r10.code()
                    r0 = 503(0x1f7, float:7.05E-43)
                    if (r9 == r0) goto L_0x003d
                    int r9 = r10.code()
                    r0 = 504(0x1f8, float:7.06E-43)
                    if (r9 != r0) goto L_0x00db
                L_0x003d:
                    java.lang.Exception r9 = new java.lang.Exception
                    java.util.Locale r0 = java.util.Locale.US
                    java.lang.String r1 = "%d response code for endpoint %s"
                    r2 = 2
                    java.lang.Object[] r3 = new java.lang.Object[r2]
                    int r4 = r10.code()
                    java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
                    r5 = 0
                    r3[r5] = r4
                    com.contextlogic.wish.api.ApiRequest r4 = r5
                    java.lang.String r4 = r4.getApiEndpointPath()
                    r6 = 1
                    r3[r6] = r4
                    java.lang.String r0 = java.lang.String.format(r0, r1, r3)
                    r9.<init>(r0)
                    com.crashlytics.android.Crashlytics.logException(r9)
                    com.contextlogic.wish.api.datacenter.ConfigDataCenter r9 = com.contextlogic.wish.api.datacenter.ConfigDataCenter.getInstance()
                    java.util.ArrayList r9 = r9.getApiRetryDelays()
                    com.contextlogic.wish.api.datacenter.ConfigDataCenter r0 = com.contextlogic.wish.api.datacenter.ConfigDataCenter.getInstance()
                    java.util.HashSet r0 = r0.getApiRetryPaths()
                    com.contextlogic.wish.api.service.ApiService r1 = com.contextlogic.wish.api.service.ApiService.this
                    int r1 = r1.mRequestCount
                    int r3 = r9.size()
                    if (r1 > r3) goto L_0x00aa
                    com.contextlogic.wish.api.ApiRequest r1 = r5
                    java.lang.String r1 = r1.getApiEndpointPath()
                    boolean r0 = r0.contains(r1)
                    if (r0 == 0) goto L_0x00aa
                    com.contextlogic.wish.api.service.ApiService r10 = com.contextlogic.wish.api.service.ApiService.this
                    android.os.Handler r10 = r10.mHandler
                    com.contextlogic.wish.api.service.ApiService$1$1 r0 = new com.contextlogic.wish.api.service.ApiService$1$1
                    r0.<init>()
                    com.contextlogic.wish.api.service.ApiService r1 = com.contextlogic.wish.api.service.ApiService.this
                    int r1 = r1.mRequestCount
                    int r1 = r1 - r6
                    java.lang.Object r9 = r9.get(r1)
                    java.lang.Long r9 = (java.lang.Long) r9
                    long r1 = r9.longValue()
                    r10.postDelayed(r0, r1)
                    return
                L_0x00aa:
                    com.contextlogic.wish.api.service.ApiService r0 = com.contextlogic.wish.api.service.ApiService.this
                    int r0 = r0.mRequestCount
                    int r1 = r9.size()
                    int r1 = r1 + r6
                    if (r0 != r1) goto L_0x00db
                    java.lang.Exception r0 = new java.lang.Exception
                    java.util.Locale r1 = java.util.Locale.US
                    java.lang.String r3 = "Giving up after %d retries for %s"
                    java.lang.Object[] r2 = new java.lang.Object[r2]
                    int r9 = r9.size()
                    java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
                    r2[r5] = r9
                    com.contextlogic.wish.api.ApiRequest r9 = r5
                    java.lang.String r9 = r9.getApiEndpointPath()
                    r2[r6] = r9
                    java.lang.String r9 = java.lang.String.format(r1, r3, r2)
                    r0.<init>(r9)
                    com.crashlytics.android.Crashlytics.logException(r0)
                L_0x00db:
                    r9 = 0
                    java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x013c, all -> 0x0120 }
                    okhttp3.ResponseBody r1 = r10.body()     // Catch:{ Throwable -> 0x013c, all -> 0x0120 }
                    java.io.InputStream r1 = r1.byteStream()     // Catch:{ Throwable -> 0x013c, all -> 0x0120 }
                    java.lang.String r2 = "UTF-8"
                    r0.<init>(r1, r2)     // Catch:{ Throwable -> 0x013c, all -> 0x0120 }
                    com.google.gson.stream.JsonReader r1 = new com.google.gson.stream.JsonReader     // Catch:{ Throwable -> 0x011e, all -> 0x0119 }
                    r1.<init>(r0)     // Catch:{ Throwable -> 0x011e, all -> 0x0119 }
                    com.contextlogic.wish.http.JsonObjectParser r2 = new com.contextlogic.wish.http.JsonObjectParser     // Catch:{ Throwable -> 0x013e, all -> 0x0113 }
                    r2.<init>()     // Catch:{ Throwable -> 0x013e, all -> 0x0113 }
                    org.json.JSONObject r2 = r2.parseJsonObject(r1)     // Catch:{ Throwable -> 0x013e, all -> 0x0113 }
                    if (r1 == 0) goto L_0x00fe
                    r1.close()     // Catch:{ Throwable -> 0x00fe }
                L_0x00fe:
                    if (r0 == 0) goto L_0x0103
                    r0.close()     // Catch:{ Throwable -> 0x0103 }
                L_0x0103:
                    if (r10 == 0) goto L_0x0158
                    okhttp3.ResponseBody r0 = r10.body()
                    if (r0 == 0) goto L_0x0158
                    okhttp3.ResponseBody r0 = r10.body()     // Catch:{ Throwable -> 0x0158 }
                    r0.close()     // Catch:{ Throwable -> 0x0158 }
                    goto L_0x0158
                L_0x0113:
                    r9 = move-exception
                    r7 = r0
                    r0 = r9
                    r9 = r1
                    r1 = r7
                    goto L_0x0122
                L_0x0119:
                    r1 = move-exception
                    r7 = r1
                    r1 = r0
                    r0 = r7
                    goto L_0x0122
                L_0x011e:
                    r1 = r9
                    goto L_0x013e
                L_0x0120:
                    r0 = move-exception
                    r1 = r9
                L_0x0122:
                    if (r9 == 0) goto L_0x0127
                    r9.close()     // Catch:{ Throwable -> 0x0127 }
                L_0x0127:
                    if (r1 == 0) goto L_0x012c
                    r1.close()     // Catch:{ Throwable -> 0x012c }
                L_0x012c:
                    if (r10 == 0) goto L_0x013b
                    okhttp3.ResponseBody r9 = r10.body()
                    if (r9 == 0) goto L_0x013b
                    okhttp3.ResponseBody r9 = r10.body()     // Catch:{ Throwable -> 0x013b }
                    r9.close()     // Catch:{ Throwable -> 0x013b }
                L_0x013b:
                    throw r0
                L_0x013c:
                    r0 = r9
                    r1 = r0
                L_0x013e:
                    if (r1 == 0) goto L_0x0143
                    r1.close()     // Catch:{ Throwable -> 0x0143 }
                L_0x0143:
                    if (r0 == 0) goto L_0x0148
                    r0.close()     // Catch:{ Throwable -> 0x0148 }
                L_0x0148:
                    if (r10 == 0) goto L_0x0157
                    okhttp3.ResponseBody r0 = r10.body()
                    if (r0 == 0) goto L_0x0157
                    okhttp3.ResponseBody r0 = r10.body()     // Catch:{ Throwable -> 0x0157 }
                    r0.close()     // Catch:{ Throwable -> 0x0157 }
                L_0x0157:
                    r2 = r9
                L_0x0158:
                    r0 = 2131755444(0x7f1001b4, float:1.9141767E38)
                    if (r2 != 0) goto L_0x0177
                    boolean r10 = com.contextlogic.wish.util.NetworkUtil.isNetworkAvailable()
                    if (r10 != 0) goto L_0x0171
                    com.contextlogic.wish.api.service.ApiService$ApiCallback r10 = r6
                    com.contextlogic.wish.application.WishApplication r1 = com.contextlogic.wish.application.WishApplication.getInstance()
                    java.lang.String r0 = r1.getString(r0)
                    r10.handleFailure(r9, r0)
                    goto L_0x01c9
                L_0x0171:
                    com.contextlogic.wish.api.service.ApiService$ApiCallback r10 = r6
                    r10.handleFailure(r9, r9)
                    goto L_0x01c9
                L_0x0177:
                    com.contextlogic.wish.api.ApiResponse r1 = new com.contextlogic.wish.api.ApiResponse     // Catch:{ Exception -> 0x01a6 }
                    r1.<init>(r2)     // Catch:{ Exception -> 0x01a6 }
                    boolean r2 = r1.isErrorResponse()     // Catch:{ Exception -> 0x01a6 }
                    if (r2 == 0) goto L_0x01a0
                    com.contextlogic.wish.api.service.ApiService r2 = com.contextlogic.wish.api.service.ApiService.this     // Catch:{ Exception -> 0x01a6 }
                    boolean r2 = r2.isSuccessLikeErrorResponse(r1)     // Catch:{ Exception -> 0x01a6 }
                    if (r2 != 0) goto L_0x01a0
                    com.contextlogic.wish.api.service.ApiService r2 = com.contextlogic.wish.api.service.ApiService.this     // Catch:{ Exception -> 0x01a6 }
                    boolean r2 = r2.checkForLogoutRequired(r1)     // Catch:{ Exception -> 0x01a6 }
                    if (r2 != 0) goto L_0x01c9
                    if (r10 == 0) goto L_0x0199
                    java.lang.String r10 = r1.getMessage()     // Catch:{ Exception -> 0x01a6 }
                    goto L_0x019a
                L_0x0199:
                    r10 = r9
                L_0x019a:
                    com.contextlogic.wish.api.service.ApiService$ApiCallback r2 = r6     // Catch:{ Exception -> 0x01a6 }
                    r2.handleFailure(r1, r10)     // Catch:{ Exception -> 0x01a6 }
                    goto L_0x01c9
                L_0x01a0:
                    com.contextlogic.wish.api.service.ApiService$ApiCallback r10 = r6     // Catch:{ Exception -> 0x01a6 }
                    r10.handleSuccess(r1)     // Catch:{ Exception -> 0x01a6 }
                    goto L_0x01c9
                L_0x01a6:
                    r10 = move-exception
                    boolean r1 = com.contextlogic.wish.util.NetworkUtil.isNetworkAvailable()
                    if (r1 != 0) goto L_0x01bb
                    com.contextlogic.wish.api.service.ApiService$ApiCallback r10 = r6
                    com.contextlogic.wish.application.WishApplication r1 = com.contextlogic.wish.application.WishApplication.getInstance()
                    java.lang.String r0 = r1.getString(r0)
                    r10.handleFailure(r9, r0)
                    goto L_0x01c9
                L_0x01bb:
                    com.contextlogic.wish.api.ApiRequest r0 = r5
                    java.lang.String r0 = r0.getUrl()
                    com.contextlogic.wish.api.service.standalone.LogErrorService.logApiRequestError(r0, r10)
                    com.contextlogic.wish.api.service.ApiService$ApiCallback r10 = r6
                    r10.handleFailure(r9, r9)
                L_0x01c9:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.api.service.ApiService.AnonymousClass1.onResponse(okhttp3.Call, okhttp3.Response):void");
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public Call startServiceForCall(ApiRequest apiRequest, ApiCallback apiCallback) {
        this.mRequestCount = 0;
        return queueRequest(apiRequest, apiCallback);
    }

    /* access modifiers changed from: private */
    public boolean checkForLogoutRequired(ApiResponse apiResponse) {
        if (apiResponse == null || apiResponse.getCode() != 2 || !AuthenticationDataCenter.getInstance().isLoggedIn()) {
            return false;
        }
        ApplicationEventManager.getInstance().triggerEvent(EventType.LOGOUT_REQUIRED, null, null);
        return true;
    }

    /* access modifiers changed from: private */
    public boolean isSuccessLikeErrorResponse(ApiResponse apiResponse) {
        int[] successLikeErrorCodes = getSuccessLikeErrorCodes();
        if (successLikeErrorCodes != null && successLikeErrorCodes.length > 0) {
            for (int i : successLikeErrorCodes) {
                if (i == apiResponse.getCode()) {
                    return true;
                }
            }
        }
        return false;
    }
}
