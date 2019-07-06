package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.LocalApiService;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.util.DeviceUtil;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;

public class GetAdvertisingIdService extends LocalApiService<Void, String> {

    public interface SuccessCallback {
        void onSuccess(String str);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        if (DeviceUtil.getAdvertisingId() != null) {
            if (successCallback != null) {
                successCallback.onSuccess(DeviceUtil.getAdvertisingId());
            }
            return;
        }
        startService(new LocalApiCallback<Void, String>() {
            public String processRequest(Void... voidArr) {
                try {
                    Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(WishApplication.getInstance());
                    String id = advertisingIdInfo.getId();
                    try {
                        if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                            return null;
                        }
                    } catch (Throwable unused) {
                    }
                    return id;
                } catch (Throwable unused2) {
                    return null;
                }
            }

            public void processResult(String str) {
                if (str != null) {
                    if (successCallback != null) {
                        successCallback.onSuccess(str);
                    }
                } else if (defaultFailureCallback != null) {
                    defaultFailureCallback.onFailure(null);
                }
            }
        }, new Void[0]);
    }
}
