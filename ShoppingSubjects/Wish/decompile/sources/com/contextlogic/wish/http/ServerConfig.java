package com.contextlogic.wish.http;

import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.util.PreferenceUtil;

public class ServerConfig {
    private static ServerConfig sInstance = new ServerConfig();
    private String mApiCountryCode;
    private String mApiPassword;
    private String mApiUsername;
    private String mServerHost;

    private ServerConfig() {
        String string = PreferenceUtil.getString("DevSettingsServerPath");
        if (string == null) {
            string = WishApplication.getInstance().getString(R.string.server_host);
        }
        this.mApiUsername = PreferenceUtil.getString("DevSettingsUsername");
        this.mApiPassword = PreferenceUtil.getString("DevSettingsPassword");
        this.mApiCountryCode = PreferenceUtil.getString("DevSettingsCountryCode");
        setServerHost(string);
    }

    public static ServerConfig getInstance() {
        return sInstance;
    }

    public String getServerHost() {
        return this.mServerHost;
    }

    public void setServerHost(String str) {
        boolean z = this.mServerHost != null;
        this.mServerHost = str;
        PreferenceUtil.setString("DevSettingsServerPath", this.mServerHost);
        if (z) {
            HttpCookieManager.getInstance().resetCookies();
        }
    }

    public String getApiUsername() {
        return this.mApiUsername;
    }

    public String getApiPassword() {
        return this.mApiPassword;
    }

    public void setApiCredentials(String str, String str2) {
        this.mApiUsername = str;
        this.mApiPassword = str2;
        PreferenceUtil.setString("DevSettingsUsername", str);
        PreferenceUtil.setString("DevSettingsPassword", str2);
    }

    public String getApiCountryCode() {
        return this.mApiCountryCode;
    }

    public void setApiCountryCode(String str) {
        this.mApiCountryCode = str;
        PreferenceUtil.setString("DevSettingsCountryCode", str);
    }

    public String generateUrl(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("https://");
        sb.append(getServerHost());
        sb.append("/");
        sb.append(str);
        return sb.toString();
    }

    public boolean isTestHost() {
        return this.mServerHost != null && (this.mServerHost.contains("testing.wish.com") || this.mServerHost.contains(".corp.contextlogic.com") || this.mServerHost.contains("sandbox.wish.com"));
    }
}
