package com.android.riskifiedbeacon;

import android.content.Context;

public interface RiskifiedBeaconMainInterface {
    void logSensitiveDeviceInfo();

    void startBeacon(String str, String str2, boolean z, Context context);

    void updateSessionToken(String str);
}
