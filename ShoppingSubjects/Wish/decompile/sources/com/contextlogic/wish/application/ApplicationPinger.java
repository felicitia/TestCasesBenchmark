package com.contextlogic.wish.application;

import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.GetAdvertisingIdService;
import com.contextlogic.wish.api.service.standalone.GetAdvertisingIdService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.ServerPingService;
import com.contextlogic.wish.application.DeviceIdManager.DeviceIdCallback;
import com.contextlogic.wish.util.PreferenceUtil;

public class ApplicationPinger {
    public static void sendServerPing(String str, String str2, String str3) {
        sendServerPing(str, str2, false, str3);
    }

    public static void sendServerPing(final String str, final String str2, boolean z, final String str3) {
        if (z || !PreferenceUtil.getBoolean("ServerPingSent") || (str2 != null && !str2.isEmpty() && !PreferenceUtil.getBoolean("ServerAdvertisingPingSent"))) {
            DeviceIdManager.getInstance().registerDeviceIdCallback(new DeviceIdCallback() {
                public void onDeviceIdReceived(String str) {
                    PreferenceUtil.setBoolean("ServerPingSent", true);
                    if (str2 != null && !str2.isEmpty()) {
                        PreferenceUtil.setBoolean("ServerAdvertisingPingSent", true);
                    }
                    new ServerPingService().requestService(str, str2, str, str3, null, null);
                }
            });
        }
    }

    public static void sendAdvertisingPing() {
        if (!PreferenceUtil.getBoolean("ServerAdvertisingPingSent")) {
            new GetAdvertisingIdService().requestService(new SuccessCallback() {
                public void onSuccess(String str) {
                    ApplicationPinger.sendServerPing(null, str, null);
                }
            }, new DefaultFailureCallback() {
                public void onFailure(String str) {
                }
            });
        }
    }
}
