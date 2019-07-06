package com.android.riskifiedbeacon;

import android.content.Context;

public interface RxBeaconInterface {

    public static class RXCoordinate {
        public Double latitude;
        public Double longitude;

        public RXCoordinate(Double d, Double d2) {
            this.latitude = d;
            this.longitude = d2;
        }
    }

    void RxLog(String str, String str2);

    void initWithShop(String str, String str2, boolean z, Context context);

    void logAccountInfo();

    void setToken(String str);
}
