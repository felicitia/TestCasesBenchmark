package com.contextlogic.wish.api;

import android.text.TextUtils;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ServerConfig;
import com.contextlogic.wish.payments.ThreatMetrix.ThreatMetrixManager;
import com.contextlogic.wish.payments.riskified.RiskifiedManager;
import com.contextlogic.wish.util.DeviceUtil;
import com.contextlogic.wish.util.PreferenceUtil;
import java.util.ArrayList;
import java.util.Iterator;
import okhttp3.FormBody.Builder;
import okhttp3.Request;

public class ApiRequest {
    private String mEndpoint;
    private Builder mParamBuilder;
    protected Request.Builder mRequestBuilder;
    private String mUrl;

    public ApiRequest() {
        this(null);
    }

    public ApiRequest(String str) {
        this.mRequestBuilder = new Request.Builder();
        addUserAgentHeader();
        this.mParamBuilder = new Builder();
        if (!TextUtils.isEmpty(str)) {
            this.mEndpoint = str;
            setUrl(constructApiUrl(str));
        }
    }

    private void addUserAgentHeader() {
        try {
            this.mRequestBuilder.addHeader("User-Agent", getUserAgent());
        } catch (IllegalArgumentException unused) {
            String userAgent = getUserAgent();
            if (userAgent != null) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < userAgent.length(); i++) {
                    char charAt = userAgent.charAt(i);
                    if (charAt > 31 && charAt < 127) {
                        sb.append(charAt);
                    }
                }
                String sb2 = sb.toString();
                PreferenceUtil.setString("UserAgent", sb2);
                this.mRequestBuilder.addHeader("User-Agent", sb2);
            }
        }
    }

    public void setUrl(String str) {
        this.mUrl = str;
        this.mRequestBuilder = this.mRequestBuilder.url(this.mUrl);
    }

    public String getUrl() {
        return this.mUrl;
    }

    private String getUserAgent() {
        String string = PreferenceUtil.getString("UserAgent");
        if (string == null) {
            string = System.getProperty("http.agent");
        }
        return string == null ? "Wish Application for Android" : string;
    }

    public Request buildRequest() {
        prepareGlobalParameters();
        return this.mRequestBuilder.post(this.mParamBuilder.build()).build();
    }

    public <T> void addParameter(String str, ArrayList<T> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            addParameter(str, it.next());
        }
    }

    public <T> void addParameter(String str, T[] tArr) {
        for (T addParameter : tArr) {
            addParameter(str, (Object) addParameter);
        }
    }

    public void addParameter(String str, Object obj) {
        if (obj != null) {
            this.mParamBuilder.add(str, String.valueOf(obj));
        }
    }

    public void addParameter(String str, int i) {
        addParameter(str, (Object) Integer.valueOf(i));
    }

    public void addParameter(String str, double d) {
        addParameter(str, (Object) Double.valueOf(d));
    }

    public void addParameter(String str, long j) {
        addParameter(str, (Object) Long.valueOf(j));
    }

    public void addParameter(String str, boolean z) {
        addParameter(str, (Object) z ? "true" : "false");
    }

    private void prepareGlobalParameters() {
        addParameter("_xsrf", (Object) "1");
        addParameter("_client", (Object) DeviceUtil.getClientId());
        addParameter("_capabilities[]", DeviceUtil.getCapabilities());
        WishApplication.getInstance();
        addParameter("_app_type", (Object) WishApplication.getAppType());
        String sessionToken = RiskifiedManager.getInstance().getSessionToken();
        if (sessionToken != null) {
            addParameter("_riskified_session_token", (Object) sessionToken);
        }
        String sessionToken2 = ThreatMetrixManager.getInstance().getSessionToken();
        if (sessionToken2 != null) {
            addParameter("_threat_metrix_session_token", (Object) sessionToken2);
        }
        if (DeviceUtil.getAdvertisingId() != null) {
            addParameter("advertiser_id", (Object) DeviceUtil.getAdvertisingId());
        }
        String versionNumber = WishApplication.getInstance().getVersionNumber();
        if (versionNumber != null) {
            addParameter("_version", (Object) versionNumber);
        }
        if (DeviceUtil.getDeviceModel() != null) {
            addParameter("app_device_model", (Object) DeviceUtil.getDeviceModel());
        }
    }

    public static String constructApiUrl(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("https://");
        sb.append(ServerConfig.getInstance().getServerHost());
        sb.append("/api/");
        sb.append(str);
        return sb.toString();
    }

    public String getApiEndpointPath() {
        StringBuilder sb = new StringBuilder();
        sb.append("/api/");
        sb.append(this.mEndpoint);
        return sb.toString();
    }
}
