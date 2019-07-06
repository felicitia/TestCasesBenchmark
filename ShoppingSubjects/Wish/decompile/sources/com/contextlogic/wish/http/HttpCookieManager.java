package com.contextlogic.wish.http;

import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.contextlogic.wish.application.DeviceIdManager;
import com.contextlogic.wish.application.DeviceIdManager.DeviceIdCallback;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.util.DeviceUtil;
import com.contextlogic.wish.util.StringUtil;
import com.contextlogic.wish.util.TimezoneUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import okhttp3.Cookie;
import okhttp3.Cookie.Builder;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class HttpCookieManager implements CookieJar {
    private static HttpCookieManager sInstance = new HttpCookieManager();
    private Cookie mAdvertisingIdCookie;
    private Cookie mAppTypeCookie;
    private Cookie mBsidCookie;
    private Cookie mCapabilitiesCookie;
    private Cookie mDeviceIdCookie;
    private Cookie mLocaleCookie;
    private Object mLock = new Object();
    private Cookie mMobileAppCookie;
    private Cookie mSessionCookie;
    private Cookie mSupportImageUploadCookie;
    private Cookie mTimeZoneCookie;
    private Cookie mTimeZoneIdCookie;
    private Cookie mVersionCookie;
    private Cookie mXsrfCookie;

    private HttpCookieManager() {
        resetCookies();
    }

    public static HttpCookieManager getInstance() {
        return sInstance;
    }

    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        if (httpUrl.host() != null && httpUrl.host().contains(ServerConfig.getInstance().getServerHost())) {
            for (Cookie cookie : list) {
                if (cookie.name().equals("bsid")) {
                    setBsidCookie(cookie.value());
                } else if (cookie.name().equals("sweeper_session")) {
                    synchronized (this.mLock) {
                        if (this.mSessionCookie == null) {
                            setSessionCookie(cookie.value());
                        }
                    }
                } else {
                    continue;
                }
            }
        }
    }

    private Cookie createCookie(String str, String str2) {
        return new Builder().name(str).value(str2).domain(ServerConfig.getInstance().getServerHost()).build();
    }

    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        ArrayList arrayList = new ArrayList();
        if (httpUrl.host().contains(ServerConfig.getInstance().getServerHost())) {
            if (this.mXsrfCookie != null) {
                arrayList.add(this.mXsrfCookie);
            }
            if (this.mLocaleCookie != null) {
                arrayList.add(this.mLocaleCookie);
            }
            if (this.mBsidCookie != null) {
                arrayList.add(this.mBsidCookie);
            }
            if (this.mSessionCookie != null) {
                arrayList.add(this.mSessionCookie);
            }
            if (this.mTimeZoneCookie != null) {
                arrayList.add(this.mTimeZoneCookie);
            }
            if (this.mTimeZoneIdCookie != null) {
                arrayList.add(this.mTimeZoneIdCookie);
            }
        }
        return arrayList;
    }

    private void setXsrfCookie(String str) {
        if (str == null) {
            this.mXsrfCookie = null;
        } else {
            this.mXsrfCookie = createCookie("_xsrf", str);
        }
    }

    private void setSupportImageUploadCookie(String str) {
        if (str == null) {
            this.mSupportImageUploadCookie = null;
        } else {
            this.mSupportImageUploadCookie = createCookie("_supportImageUpload", str);
        }
    }

    private void setLocaleCookie(String str) {
        if (str == null) {
            this.mLocaleCookie = null;
        } else {
            this.mLocaleCookie = createCookie("_appLocale", str);
        }
    }

    private void setMobileAppCookie(String str) {
        if (str == null) {
            this.mMobileAppCookie = null;
        } else {
            this.mMobileAppCookie = createCookie("_mobileApp", str);
        }
    }

    private void setAppTypeCookie(String str) {
        if (str == null) {
            this.mAppTypeCookie = null;
        } else {
            this.mAppTypeCookie = createCookie("_app_type", str);
        }
    }

    private void setBsidCookie(String str) {
        if (str == null) {
            this.mBsidCookie = null;
        } else {
            this.mBsidCookie = createCookie("bsid", str);
        }
    }

    public void setSessionCookie(String str) {
        synchronized (this.mLock) {
            if (str == null) {
                try {
                    this.mSessionCookie = null;
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                this.mSessionCookie = createCookie("sweeper_session", str);
            }
        }
    }

    public Cookie getSessionCookie() {
        return this.mSessionCookie;
    }

    private void setVersionCookie(String str) {
        if (str == null) {
            this.mVersionCookie = null;
        } else {
            this.mVersionCookie = createCookie("_version", str);
        }
    }

    private void setCapabilitiesCookie(String str) {
        if (str == null) {
            this.mCapabilitiesCookie = null;
        } else {
            this.mCapabilitiesCookie = createCookie("_capabilities", str);
        }
    }

    /* access modifiers changed from: private */
    public void setDeviceIdCookie(String str) {
        if (str == null) {
            this.mDeviceIdCookie = null;
        } else {
            this.mDeviceIdCookie = createCookie("_app_device_id", str);
        }
    }

    private void setTimeZoneCookie(String str) {
        if (str == null) {
            this.mTimeZoneCookie = null;
        } else {
            this.mTimeZoneCookie = createCookie("_timezone", str);
        }
    }

    private void setTimeZoneIdCookie(String str) {
        this.mTimeZoneIdCookie = createCookie("_timezone_id", str);
    }

    private void setAdvertisingIdCookie(String str) {
        if (str == null) {
            this.mAdvertisingIdCookie = null;
        } else {
            this.mAdvertisingIdCookie = createCookie("_advertiser_id", str);
        }
    }

    public void updateAdvertisingId(String str) {
        setAdvertisingIdCookie(str);
    }

    public void resetCookies() {
        setSessionCookie(null);
        setBsidCookie(null);
        setSupportImageUploadCookie("true");
        setLocaleCookie(Locale.getDefault().toString());
        setXsrfCookie("1");
        setMobileAppCookie(DeviceUtil.getClientId());
        WishApplication.getInstance();
        setAppTypeCookie(WishApplication.getAppType());
        setVersionCookie(WishApplication.getInstance().getVersionNumber());
        setCapabilitiesCookie(StringUtil.join(DeviceUtil.getCapabilities(), ","));
        setTimeZoneCookie(TimezoneUtil.getCurrentTimezone());
        setTimeZoneIdCookie(TimezoneUtil.getCurrentTimeZoneId());
        DeviceIdManager.getInstance().registerDeviceIdCallback(new DeviceIdCallback() {
            public void onDeviceIdReceived(String str) {
                HttpCookieManager.this.setDeviceIdCookie(str);
            }
        });
        setAdvertisingIdCookie(DeviceUtil.getAdvertisingId());
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:2|3) */
    /* JADX WARNING: Code restructure failed: missing block: B:3:?, code lost:
        android.webkit.CookieSyncManager.createInstance(com.contextlogic.wish.application.WishApplication.getInstance());
        r0 = android.webkit.CookieSyncManager.getInstance();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0011, code lost:
        r0 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0005 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void clearWebViewCookies() {
        /*
            android.webkit.CookieSyncManager r0 = android.webkit.CookieSyncManager.getInstance()     // Catch:{ IllegalStateException -> 0x0005 }
            goto L_0x0012
        L_0x0005:
            com.contextlogic.wish.application.WishApplication r0 = com.contextlogic.wish.application.WishApplication.getInstance()     // Catch:{ Throwable -> 0x0011 }
            android.webkit.CookieSyncManager.createInstance(r0)     // Catch:{ Throwable -> 0x0011 }
            android.webkit.CookieSyncManager r0 = android.webkit.CookieSyncManager.getInstance()     // Catch:{ Throwable -> 0x0011 }
            goto L_0x0012
        L_0x0011:
            r0 = 0
        L_0x0012:
            android.webkit.CookieManager r1 = android.webkit.CookieManager.getInstance()
            if (r1 == 0) goto L_0x0020
            if (r0 == 0) goto L_0x0020
            r1.removeAllCookie()
            r0.sync()
        L_0x0020:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.http.HttpCookieManager.clearWebViewCookies():void");
    }

    public void prepareToSetupWebviewCookies() {
        CookieManager instance = CookieManager.getInstance();
        instance.removeSessionCookie();
        instance.setAcceptCookie(true);
        try {
            CookieSyncManager.createInstance(WishApplication.getInstance());
        } catch (IllegalStateException unused) {
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(25:0|(1:2)|3|(1:5)|6|(1:8)|9|(1:11)|12|(1:14)|15|(1:17)|18|(1:20)|21|(1:23)|24|(1:26)|27|(1:29)|30|31|32|33|35) */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:32:0x0066 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setupWebviewCookies() {
        /*
            r2 = this;
            android.webkit.CookieManager r0 = android.webkit.CookieManager.getInstance()
            okhttp3.Cookie r1 = r2.mBsidCookie
            if (r1 == 0) goto L_0x000d
            okhttp3.Cookie r1 = r2.mBsidCookie
            r2.addCookieToCookieManager(r0, r1)
        L_0x000d:
            okhttp3.Cookie r1 = r2.mLocaleCookie
            if (r1 == 0) goto L_0x0016
            okhttp3.Cookie r1 = r2.mLocaleCookie
            r2.addCookieToCookieManager(r0, r1)
        L_0x0016:
            okhttp3.Cookie r1 = r2.mMobileAppCookie
            if (r1 == 0) goto L_0x001f
            okhttp3.Cookie r1 = r2.mMobileAppCookie
            r2.addCookieToCookieManager(r0, r1)
        L_0x001f:
            okhttp3.Cookie r1 = r2.mAppTypeCookie
            if (r1 == 0) goto L_0x0028
            okhttp3.Cookie r1 = r2.mAppTypeCookie
            r2.addCookieToCookieManager(r0, r1)
        L_0x0028:
            okhttp3.Cookie r1 = r2.mSupportImageUploadCookie
            if (r1 == 0) goto L_0x0031
            okhttp3.Cookie r1 = r2.mSupportImageUploadCookie
            r2.addCookieToCookieManager(r0, r1)
        L_0x0031:
            okhttp3.Cookie r1 = r2.mSessionCookie
            if (r1 == 0) goto L_0x003a
            okhttp3.Cookie r1 = r2.mSessionCookie
            r2.addCookieToCookieManager(r0, r1)
        L_0x003a:
            okhttp3.Cookie r1 = r2.mVersionCookie
            if (r1 == 0) goto L_0x0043
            okhttp3.Cookie r1 = r2.mVersionCookie
            r2.addCookieToCookieManager(r0, r1)
        L_0x0043:
            okhttp3.Cookie r1 = r2.mAdvertisingIdCookie
            if (r1 == 0) goto L_0x004c
            okhttp3.Cookie r1 = r2.mAdvertisingIdCookie
            r2.addCookieToCookieManager(r0, r1)
        L_0x004c:
            okhttp3.Cookie r1 = r2.mDeviceIdCookie
            if (r1 == 0) goto L_0x0055
            okhttp3.Cookie r1 = r2.mDeviceIdCookie
            r2.addCookieToCookieManager(r0, r1)
        L_0x0055:
            okhttp3.Cookie r1 = r2.mCapabilitiesCookie
            if (r1 == 0) goto L_0x005e
            okhttp3.Cookie r1 = r2.mCapabilitiesCookie
            r2.addCookieToCookieManager(r0, r1)
        L_0x005e:
            android.webkit.CookieSyncManager r0 = android.webkit.CookieSyncManager.getInstance()     // Catch:{ IllegalStateException -> 0x0066 }
            r0.sync()     // Catch:{ IllegalStateException -> 0x0066 }
            goto L_0x0074
        L_0x0066:
            com.contextlogic.wish.application.WishApplication r0 = com.contextlogic.wish.application.WishApplication.getInstance()     // Catch:{ IllegalStateException -> 0x0074 }
            android.webkit.CookieSyncManager.createInstance(r0)     // Catch:{ IllegalStateException -> 0x0074 }
            android.webkit.CookieSyncManager r0 = android.webkit.CookieSyncManager.getInstance()     // Catch:{ IllegalStateException -> 0x0074 }
            r0.sync()     // Catch:{ IllegalStateException -> 0x0074 }
        L_0x0074:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.http.HttpCookieManager.setupWebviewCookies():void");
    }

    private void addCookieToCookieManager(CookieManager cookieManager, Cookie cookie) {
        if (cookie != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(cookie.name());
            sb.append("=");
            sb.append(cookie.value());
            sb.append("; domain=");
            sb.append(ServerConfig.getInstance().getServerHost());
            sb.append("; path=/");
            cookieManager.setCookie(ServerConfig.getInstance().getServerHost(), sb.toString());
        }
    }
}
