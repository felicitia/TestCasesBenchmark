package com.android.riskifiedbeacon;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.android.riskifiedbeacon.RxBeaconInterface.RXCoordinate;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

public class RxBeacon implements RxBeaconInterface {
    /* access modifiers changed from: private */
    public static String adId = null;
    /* access modifiers changed from: private */
    public static clientInfosRestClientUsingUrlHttpConnection client = null;
    private static boolean deviceInfoCollected = false;
    public static RxBeacon instance;
    ConnectivityManager connectivityManager;
    private String deviceInfo;
    RxLocationListener locListener;
    LocationManager locManager;
    /* access modifiers changed from: private */
    public Context mContext;
    NetworkInfo networkInfo;
    private boolean rxLog = false;
    private String shopDomain;
    private String token;

    class clientInfosRestClientUsage {
        clientInfosRestClientUsage() {
        }

        public void postClientInfos(String str, String str2) throws IOException {
            clientInfosRestClientUsingUrlHttpConnection access$300 = RxBeacon.client;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append('?');
            sb.append(str2.toString());
            access$300.logRequest(sb.toString());
        }
    }

    private RxBeacon() {
    }

    public static RxBeacon getInstance() {
        if (instance == null) {
            instance = new RxBeacon();
        }
        return instance;
    }

    public void RxLog(String str, String str2) {
        if (this.rxLog) {
            Log.d(str, str2);
        }
    }

    public void setToken(String str) {
        this.token = str;
        RxLog("RX_DEBUG", "Recollecting device info after session token update");
        deviceInfoCollected = false;
        collectDeviceInfo(adId);
    }

    public void initWithShop(String str, String str2, boolean z, Context context) {
        this.mContext = context.getApplicationContext();
        this.shopDomain = str;
        this.token = str2;
        this.rxLog = z;
        this.connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.networkInfo = this.connectivityManager.getActiveNetworkInfo();
        client = new clientInfosRestClientUsingUrlHttpConnection();
        getAdIdInNewThread();
    }

    /* access modifiers changed from: private */
    public void collectDeviceInfo(String str) {
        if (deviceInfoCollected) {
            RxLog("RX_INFO", "collectDeviceInfo already called");
            return;
        }
        this.deviceInfo = new String();
        adId = str;
        try {
            PackageInfo packageInfo = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0);
            StringBuilder sb = new StringBuilder();
            sb.append(this.deviceInfo);
            sb.append("app_version=");
            sb.append(packageInfo.versionName);
            sb.append("&");
            this.deviceInfo = sb.toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.deviceInfo);
            sb2.append("model=");
            sb2.append(URLEncoder.encode(Build.MODEL, "utf-8"));
            sb2.append("&");
            this.deviceInfo = sb2.toString();
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException unused) {
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append(this.deviceInfo);
        sb3.append("beacon_version=1.3.0&");
        this.deviceInfo = sb3.toString();
        StringBuilder sb4 = new StringBuilder();
        sb4.append(this.deviceInfo);
        sb4.append("old_riskified_cookie=");
        sb4.append(UniqueIdBuilder.getDeviceId(this.mContext));
        sb4.append("&");
        this.deviceInfo = sb4.toString();
        StringBuilder sb5 = new StringBuilder();
        sb5.append(this.deviceInfo);
        sb5.append("riskified_cookie=");
        sb5.append(str);
        sb5.append("&");
        this.deviceInfo = sb5.toString();
        StringBuilder sb6 = new StringBuilder();
        sb6.append(this.deviceInfo);
        sb6.append("name=");
        sb6.append(Build.PRODUCT);
        sb6.append("&");
        this.deviceInfo = sb6.toString();
        StringBuilder sb7 = new StringBuilder();
        sb7.append(this.deviceInfo);
        sb7.append("system_version=");
        sb7.append(Integer.toString(VERSION.SDK_INT));
        sb7.append("&");
        this.deviceInfo = sb7.toString();
        StringBuilder sb8 = new StringBuilder();
        sb8.append(this.deviceInfo);
        sb8.append("system_name=");
        sb8.append(VERSION.CODENAME);
        sb8.append("&");
        this.deviceInfo = sb8.toString();
        StringBuilder sb9 = new StringBuilder();
        sb9.append(this.deviceInfo);
        sb9.append("shop=");
        sb9.append(this.shopDomain);
        sb9.append("&");
        this.deviceInfo = sb9.toString();
        StringBuilder sb10 = new StringBuilder();
        sb10.append(this.deviceInfo);
        sb10.append("lang=");
        sb10.append(this.mContext.getResources().getConfiguration().locale);
        sb10.append("&");
        this.deviceInfo = sb10.toString();
        StringBuilder sb11 = new StringBuilder();
        sb11.append(this.deviceInfo);
        sb11.append("cart_id=");
        sb11.append(this.token);
        sb11.append("&");
        this.deviceInfo = sb11.toString();
        StringBuilder sb12 = new StringBuilder();
        sb12.append(this.deviceInfo);
        sb12.append("source=android&");
        this.deviceInfo = sb12.toString();
        addCarrierInfo();
        getLocationInfo();
        logDeviceInfo();
        deviceInfoCollected = true;
    }

    private void getAdIdInNewThread() {
        if (!deviceInfoCollected) {
            new AsyncTask<Void, Void, Void>() {
                /* access modifiers changed from: protected */
                public Void doInBackground(Void... voidArr) {
                    try {
                        Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(RxBeacon.this.mContext);
                        RxBeacon.adId = advertisingIdInfo.getId();
                        advertisingIdInfo.isLimitAdTrackingEnabled();
                    } catch (Exception unused) {
                    }
                    return null;
                }

                /* access modifiers changed from: protected */
                public void onPostExecute(Void voidR) {
                    RxBeacon.this.RxLog("RX_DEBUG", "Sending device info after getting adId");
                    RxBeacon.this.collectDeviceInfo(RxBeacon.adId);
                }
            }.execute(new Void[0]);
        }
    }

    private void logDeviceInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Collected device info: ");
        sb.append(this.deviceInfo.toString());
        RxLog("RX_INFO", sb.toString());
        logParams(this.deviceInfo, "https://c.riskified.com/device_infos.json");
    }

    private void getLocationInfo() {
        if (this.locListener == null && this.locManager == null) {
            try {
                this.locManager = (LocationManager) this.mContext.getSystemService("location");
                this.locListener = new RxLocationListener(instance);
                this.locManager.requestLocationUpdates("gps", 0, 0.0f, this.locListener);
                this.locManager.requestLocationUpdates("network", 0, 0.0f, this.locListener);
            } catch (Exception unused) {
                RxLog("RX_DEBUG", "No premissions to access location manager");
            }
        }
    }

    public void logAccountInfo() {
        Account[] accounts;
        HashMap hashMap = new HashMap();
        try {
            Context context = this.mContext;
            Context context2 = this.mContext;
            for (Account account : ((AccountManager) context.getSystemService("account")).getAccounts()) {
                hashMap.put(account.type, account.name);
            }
        } catch (Exception unused) {
            RxLog("RX_DEBUG", "No premissions to access account manager");
        }
        String substring = hashMap.size() == 0 ? "" : hashMap.toString().substring(1, hashMap.toString().length() - 1);
        StringBuilder sb = new StringBuilder();
        sb.append(this.deviceInfo);
        sb.append("socials=");
        sb.append(substring);
        sb.append("&");
        this.deviceInfo = sb.toString();
        logDeviceInfo();
    }

    private void addCarrierInfo() {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
            StringBuilder sb = new StringBuilder();
            sb.append(this.deviceInfo);
            sb.append("carrierName=");
            sb.append(telephonyManager.getNetworkOperatorName());
            sb.append("&");
            this.deviceInfo = sb.toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.deviceInfo);
            sb2.append("isoCountryCode=");
            sb2.append(telephonyManager.getNetworkCountryIso());
            sb2.append("&");
            this.deviceInfo = sb2.toString();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(this.deviceInfo);
            sb3.append("mobileNetworkCode=");
            sb3.append(telephonyManager.getSimOperator());
            sb3.append("&");
            this.deviceInfo = sb3.toString();
        } catch (Exception unused) {
            RxLog("RX_DEBUG", "No premissions to access telephony manager");
        }
    }

    public void gotLocationInfo(RXCoordinate rXCoordinate) {
        if (rXCoordinate.latitude != null && rXCoordinate.longitude != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.deviceInfo);
            sb.append("latitude=");
            sb.append(rXCoordinate.latitude.toString());
            sb.append("&");
            this.deviceInfo = sb.toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.deviceInfo);
            sb2.append("longitude=");
            sb2.append(rXCoordinate.longitude.toString());
            sb2.append("&");
            this.deviceInfo = sb2.toString();
            RxLog("RX_DEBUG", "Updating location");
            logDeviceInfo();
            removeLocUpdates();
        }
    }

    private void logParams(String str, String str2) {
        try {
            new clientInfosRestClientUsage().postClientInfos(str2, str);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to log request URL: ");
            sb.append(e.getMessage());
            RxLog("RX_DEBUG", sb.toString());
        }
    }

    public void removeLocUpdates() {
        if (this.locManager != null) {
            this.locManager.removeUpdates(this.locListener);
            this.locManager = null;
        }
        if (this.locListener != null) {
            this.locListener = null;
        }
    }
}
