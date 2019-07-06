package com.contextlogic.wish.api.service;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.ApiService.CancellableApiCallback;
import com.contextlogic.wish.application.DeviceIdManager;
import com.contextlogic.wish.application.DeviceIdManager.DeviceIdCallback;
import com.contextlogic.wish.http.WishHttpClient;
import java.text.ParseException;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.Call;
import org.json.JSONException;

public abstract class MultiApiService extends ApiService {
    /* access modifiers changed from: private */
    public ConcurrentHashMap<String, CancellableApiCallback> mCallbackMap = new ConcurrentHashMap<>();
    /* access modifiers changed from: private */
    public ConcurrentHashMap<String, DeviceIdCallback> mDeviceIdCallbackMap = new ConcurrentHashMap<>();
    /* access modifiers changed from: private */
    public ConcurrentHashMap<String, Call> mRequestMap = new ConcurrentHashMap<>();

    public void cancelAllRequests() {
        for (Entry value : this.mCallbackMap.entrySet()) {
            ((CancellableApiCallback) value.getValue()).cancel();
        }
        this.mCallbackMap.clear();
        for (Entry value2 : this.mRequestMap.entrySet()) {
            WishHttpClient.getInstance().cancelRequest((Call) value2.getValue());
        }
        this.mRequestMap.clear();
        for (Entry value3 : this.mDeviceIdCallbackMap.entrySet()) {
            DeviceIdCallback deviceIdCallback = (DeviceIdCallback) value3.getValue();
            DeviceIdManager.getInstance().unregisterDeviceIdCallback(deviceIdCallback);
            deviceIdCallback.cancel();
        }
        this.mDeviceIdCallbackMap.clear();
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages(null);
        }
    }

    public boolean isPending(String str) {
        return this.mRequestMap.containsKey(str);
    }

    public int pendingRequestCount() {
        return this.mRequestMap.size();
    }

    /* access modifiers changed from: protected */
    public void startService(final ApiRequest apiRequest, final ApiCallback apiCallback) {
        final AnonymousClass1 r0 = new CancellableApiCallback() {
            public void handleFailure(ApiResponse apiResponse, String str) {
                if (!isCancelled()) {
                    MultiApiService.this.mRequestMap.remove(apiCallback.getCallIdentifier());
                    MultiApiService.this.mCallbackMap.remove(apiCallback.getCallIdentifier());
                    MultiApiService.this.mDeviceIdCallbackMap.remove(apiCallback.getCallIdentifier());
                    apiCallback.handleFailure(apiResponse, str);
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                if (!isCancelled()) {
                    MultiApiService.this.mRequestMap.remove(apiCallback.getCallIdentifier());
                    MultiApiService.this.mCallbackMap.remove(apiCallback.getCallIdentifier());
                    MultiApiService.this.mDeviceIdCallbackMap.remove(apiCallback.getCallIdentifier());
                    apiCallback.handleSuccess(apiResponse);
                }
            }

            public String getCallIdentifier() {
                return apiCallback.getCallIdentifier();
            }
        };
        AnonymousClass2 r1 = new DeviceIdCallback() {
            public void onDeviceIdReceived(String str) {
                if (!isCancelled()) {
                    apiRequest.addParameter("app_device_id", (Object) str);
                    Call access$301 = MultiApiService.super.startServiceForCall(apiRequest, r0);
                    MultiApiService.this.mCallbackMap.put(apiCallback.getCallIdentifier(), r0);
                    MultiApiService.this.mRequestMap.put(apiCallback.getCallIdentifier(), access$301);
                }
            }
        };
        this.mDeviceIdCallbackMap.put(apiCallback.getCallIdentifier(), r1);
        DeviceIdManager.getInstance().registerDeviceIdCallback(r1);
    }
}
