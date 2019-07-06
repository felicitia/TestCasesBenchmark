package com.contextlogic.wish.api.service;

import android.os.Handler;
import android.os.Looper;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.WishHttpClient;
import com.contextlogic.wish.util.NetworkUtil;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ExternalJsonApiService {
    /* access modifiers changed from: private */
    public CancellableApiCallback mApiCallback;
    protected Handler mHandler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public Call mRequestCall;

    public abstract class CancellableApiCallback implements ExternalJsonApiCallback {
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

    public interface DefaultFailureCallback {
        void onFailure(String str);
    }

    protected interface ExternalJsonApiCallback {
        void handleFailure(String str);

        void handleSuccess(JSONObject jSONObject) throws JSONException;
    }

    public void cancelAllRequests() {
        if (this.mApiCallback != null) {
            this.mApiCallback.cancel();
            this.mApiCallback = null;
        }
        if (this.mRequestCall != null) {
            WishHttpClient.getInstance().cancelRequest(this.mRequestCall);
            this.mRequestCall = null;
        }
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages(null);
        }
    }

    /* access modifiers changed from: protected */
    public void postRunnable(Runnable runnable) {
        this.mHandler.post(runnable);
    }

    /* access modifiers changed from: protected */
    public Call startService(ApiRequest apiRequest, final ExternalJsonApiCallback externalJsonApiCallback) {
        cancelAllRequests();
        final AnonymousClass1 r0 = new CancellableApiCallback() {
            public void handleFailure(String str) {
                if (!isCancelled()) {
                    ExternalJsonApiService.this.mRequestCall = null;
                    ExternalJsonApiService.this.mApiCallback = null;
                    externalJsonApiCallback.handleFailure(str);
                }
            }

            public void handleSuccess(JSONObject jSONObject) throws JSONException {
                if (!isCancelled()) {
                    ExternalJsonApiService.this.mRequestCall = null;
                    ExternalJsonApiService.this.mApiCallback = null;
                    externalJsonApiCallback.handleSuccess(jSONObject);
                }
            }
        };
        this.mApiCallback = r0;
        Call startRequest = WishHttpClient.getInstance().startRequest(apiRequest.buildRequest(), new Callback() {
            public void onFailure(Call call, IOException iOException) {
                if (!NetworkUtil.isNetworkAvailable()) {
                    r0.handleFailure(WishApplication.getInstance().getString(R.string.device_lost_network));
                } else {
                    r0.handleFailure(null);
                }
            }

            /* JADX WARNING: Removed duplicated region for block: B:29:0x0049 A[SYNTHETIC, Splitter:B:29:0x0049] */
            /* JADX WARNING: Removed duplicated region for block: B:33:0x004e A[SYNTHETIC, Splitter:B:33:0x004e] */
            /* JADX WARNING: Removed duplicated region for block: B:47:0x0065 A[SYNTHETIC, Splitter:B:47:0x0065] */
            /* JADX WARNING: Removed duplicated region for block: B:51:0x006a A[SYNTHETIC, Splitter:B:51:0x006a] */
            /* JADX WARNING: Removed duplicated region for block: B:64:0x0082  */
            /* JADX WARNING: Removed duplicated region for block: B:68:0x009c A[SYNTHETIC, Splitter:B:68:0x009c] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onResponse(okhttp3.Call r5, okhttp3.Response r6) throws java.io.IOException {
                /*
                    r4 = this;
                    r5 = 0
                    java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x0061, all -> 0x0045 }
                    okhttp3.ResponseBody r1 = r6.body()     // Catch:{ Throwable -> 0x0061, all -> 0x0045 }
                    java.io.InputStream r1 = r1.byteStream()     // Catch:{ Throwable -> 0x0061, all -> 0x0045 }
                    java.lang.String r2 = "UTF-8"
                    r0.<init>(r1, r2)     // Catch:{ Throwable -> 0x0061, all -> 0x0045 }
                    com.google.gson.stream.JsonReader r1 = new com.google.gson.stream.JsonReader     // Catch:{ Throwable -> 0x0043, all -> 0x003e }
                    r1.<init>(r0)     // Catch:{ Throwable -> 0x0043, all -> 0x003e }
                    com.contextlogic.wish.http.JsonObjectParser r2 = new com.contextlogic.wish.http.JsonObjectParser     // Catch:{ Throwable -> 0x0063, all -> 0x0038 }
                    r2.<init>()     // Catch:{ Throwable -> 0x0063, all -> 0x0038 }
                    org.json.JSONObject r2 = r2.parseJsonObject(r1)     // Catch:{ Throwable -> 0x0063, all -> 0x0038 }
                    if (r1 == 0) goto L_0x0023
                    r1.close()     // Catch:{ Throwable -> 0x0023 }
                L_0x0023:
                    if (r0 == 0) goto L_0x0028
                    r0.close()     // Catch:{ Throwable -> 0x0028 }
                L_0x0028:
                    if (r6 == 0) goto L_0x007d
                    okhttp3.ResponseBody r0 = r6.body()
                    if (r0 == 0) goto L_0x007d
                    okhttp3.ResponseBody r6 = r6.body()     // Catch:{ Throwable -> 0x007d }
                    r6.close()     // Catch:{ Throwable -> 0x007d }
                    goto L_0x007d
                L_0x0038:
                    r5 = move-exception
                    r3 = r0
                    r0 = r5
                    r5 = r1
                    r1 = r3
                    goto L_0x0047
                L_0x003e:
                    r1 = move-exception
                    r3 = r1
                    r1 = r0
                    r0 = r3
                    goto L_0x0047
                L_0x0043:
                    r1 = r5
                    goto L_0x0063
                L_0x0045:
                    r0 = move-exception
                    r1 = r5
                L_0x0047:
                    if (r5 == 0) goto L_0x004c
                    r5.close()     // Catch:{ Throwable -> 0x004c }
                L_0x004c:
                    if (r1 == 0) goto L_0x0051
                    r1.close()     // Catch:{ Throwable -> 0x0051 }
                L_0x0051:
                    if (r6 == 0) goto L_0x0060
                    okhttp3.ResponseBody r5 = r6.body()
                    if (r5 == 0) goto L_0x0060
                    okhttp3.ResponseBody r5 = r6.body()     // Catch:{ Throwable -> 0x0060 }
                    r5.close()     // Catch:{ Throwable -> 0x0060 }
                L_0x0060:
                    throw r0
                L_0x0061:
                    r0 = r5
                    r1 = r0
                L_0x0063:
                    if (r1 == 0) goto L_0x0068
                    r1.close()     // Catch:{ Throwable -> 0x0068 }
                L_0x0068:
                    if (r0 == 0) goto L_0x006d
                    r0.close()     // Catch:{ Throwable -> 0x006d }
                L_0x006d:
                    if (r6 == 0) goto L_0x007c
                    okhttp3.ResponseBody r0 = r6.body()
                    if (r0 == 0) goto L_0x007c
                    okhttp3.ResponseBody r6 = r6.body()     // Catch:{ Throwable -> 0x007c }
                    r6.close()     // Catch:{ Throwable -> 0x007c }
                L_0x007c:
                    r2 = r5
                L_0x007d:
                    r6 = 2131755444(0x7f1001b4, float:1.9141767E38)
                    if (r2 != 0) goto L_0x009c
                    boolean r0 = com.contextlogic.wish.util.NetworkUtil.isNetworkAvailable()
                    if (r0 != 0) goto L_0x0096
                    com.contextlogic.wish.api.service.ExternalJsonApiService$CancellableApiCallback r5 = r0
                    com.contextlogic.wish.application.WishApplication r0 = com.contextlogic.wish.application.WishApplication.getInstance()
                    java.lang.String r6 = r0.getString(r6)
                    r5.handleFailure(r6)
                    goto L_0x00bb
                L_0x0096:
                    com.contextlogic.wish.api.service.ExternalJsonApiService$CancellableApiCallback r6 = r0
                    r6.handleFailure(r5)
                    goto L_0x00bb
                L_0x009c:
                    com.contextlogic.wish.api.service.ExternalJsonApiService$CancellableApiCallback r0 = r0     // Catch:{ Throwable -> 0x00a2 }
                    r0.handleSuccess(r2)     // Catch:{ Throwable -> 0x00a2 }
                    goto L_0x00bb
                L_0x00a2:
                    boolean r0 = com.contextlogic.wish.util.NetworkUtil.isNetworkAvailable()
                    if (r0 != 0) goto L_0x00b6
                    com.contextlogic.wish.api.service.ExternalJsonApiService$CancellableApiCallback r5 = r0
                    com.contextlogic.wish.application.WishApplication r0 = com.contextlogic.wish.application.WishApplication.getInstance()
                    java.lang.String r6 = r0.getString(r6)
                    r5.handleFailure(r6)
                    goto L_0x00bb
                L_0x00b6:
                    com.contextlogic.wish.api.service.ExternalJsonApiService$CancellableApiCallback r6 = r0
                    r6.handleFailure(r5)
                L_0x00bb:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.api.service.ExternalJsonApiService.AnonymousClass2.onResponse(okhttp3.Call, okhttp3.Response):void");
            }
        });
        this.mRequestCall = startRequest;
        return startRequest;
    }
}
