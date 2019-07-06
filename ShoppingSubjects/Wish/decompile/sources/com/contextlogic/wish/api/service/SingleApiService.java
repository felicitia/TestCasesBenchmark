package com.contextlogic.wish.api.service;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.ApiService.CancellableApiCallback;
import com.contextlogic.wish.application.DeviceIdManager;
import com.contextlogic.wish.application.DeviceIdManager.DeviceIdCallback;
import com.contextlogic.wish.http.WishHttpClient;
import java.text.ParseException;
import okhttp3.Call;
import org.json.JSONException;

public abstract class SingleApiService extends ApiService {
    /* access modifiers changed from: private */
    public CancellableApiCallback mApiCallback;
    private DeviceIdCallback mDeviceIdCallback;
    /* access modifiers changed from: private */
    public boolean mPendingPostedResponse;
    /* access modifiers changed from: private */
    public Call mRequestCall;

    /* access modifiers changed from: protected */
    public boolean willPostResponses() {
        return true;
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
        if (this.mDeviceIdCallback != null) {
            DeviceIdManager.getInstance().unregisterDeviceIdCallback(this.mDeviceIdCallback);
            this.mDeviceIdCallback.cancel();
            this.mDeviceIdCallback = null;
        }
        this.mPendingPostedResponse = false;
    }

    public boolean isPending() {
        return this.mRequestCall != null || this.mPendingPostedResponse;
    }

    public void postRunnable(final Runnable runnable) {
        this.mHandler.post(new Runnable() {
            public void run() {
                SingleApiService.this.mPendingPostedResponse = false;
                runnable.run();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void startService(final ApiRequest apiRequest, final ApiCallback apiCallback) {
        cancelAllRequests();
        this.mApiCallback = new CancellableApiCallback() {
            public void handleFailure(ApiResponse apiResponse, String str) {
                if (!isCancelled()) {
                    if (SingleApiService.this.willPostResponses()) {
                        SingleApiService.this.mPendingPostedResponse = true;
                    }
                    SingleApiService.this.mRequestCall = null;
                    SingleApiService.this.mApiCallback = null;
                    apiCallback.handleFailure(apiResponse, str);
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                if (!isCancelled()) {
                    if (SingleApiService.this.willPostResponses()) {
                        SingleApiService.this.mPendingPostedResponse = true;
                    }
                    SingleApiService.this.mRequestCall = null;
                    SingleApiService.this.mApiCallback = null;
                    apiCallback.handleSuccess(apiResponse);
                }
            }

            public String getCallIdentifier() {
                return apiCallback.getCallIdentifier();
            }
        };
        this.mDeviceIdCallback = new DeviceIdCallback() {
            public void onDeviceIdReceived(String str) {
                if (!isCancelled()) {
                    apiRequest.addParameter("app_device_id", (Object) str);
                    SingleApiService.this.mRequestCall = SingleApiService.super.startServiceForCall(apiRequest, SingleApiService.this.mApiCallback);
                }
            }
        };
        DeviceIdManager.getInstance().registerDeviceIdCallback(this.mDeviceIdCallback);
    }
}
