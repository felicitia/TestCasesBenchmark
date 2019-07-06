package com.android.riskifiedbeacon;

import android.content.Context;

public class RiskifiedBeaconMain implements RiskifiedBeaconMainInterface {
    RxBeaconInterface RXBeacon = RxBeacon.getInstance();

    public void startBeacon(String str, String str2, boolean z, Context context) {
        if (str == null || context == null) {
            this.RXBeacon.RxLog("RX_INFO", "Cannot initialize class 'RiskifiedBeaconMain', parameters shopDomain and appContext cannot be null");
            return;
        }
        try {
            this.RXBeacon.initWithShop(str, str2, z, context);
        } catch (Exception unused) {
        }
    }

    public void logSensitiveDeviceInfo() {
        this.RXBeacon.logAccountInfo();
    }

    public void updateSessionToken(String str) {
        this.RXBeacon.setToken(str);
    }
}
