package io.fabric.sdk.android.services.settings;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

class DefaultSettingsSpiCall extends AbstractSpiCall implements SettingsSpiCall {
    /* access modifiers changed from: 0000 */
    public boolean requestWasSuccessful(int i) {
        return i == 200 || i == 201 || i == 202 || i == 203;
    }

    public DefaultSettingsSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory) {
        this(kit, str, str2, httpRequestFactory, HttpMethod.GET);
    }

    DefaultSettingsSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, HttpMethod httpMethod) {
        super(kit, str, str2, httpRequestFactory, httpMethod);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0072  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject invoke(io.fabric.sdk.android.services.settings.SettingsRequest r6) {
        /*
            r5 = this;
            r0 = 0
            java.util.Map r1 = r5.getQueryParamsFor(r6)     // Catch:{ all -> 0x006f }
            io.fabric.sdk.android.services.network.HttpRequest r2 = r5.getHttpRequest(r1)     // Catch:{ all -> 0x006f }
            io.fabric.sdk.android.services.network.HttpRequest r0 = r5.applyHeadersTo(r2, r6)     // Catch:{ all -> 0x006c }
            io.fabric.sdk.android.Logger r6 = io.fabric.sdk.android.Fabric.getLogger()     // Catch:{ all -> 0x006f }
            java.lang.String r2 = "Fabric"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x006f }
            r3.<init>()     // Catch:{ all -> 0x006f }
            java.lang.String r4 = "Requesting settings from "
            r3.append(r4)     // Catch:{ all -> 0x006f }
            java.lang.String r4 = r5.getUrl()     // Catch:{ all -> 0x006f }
            r3.append(r4)     // Catch:{ all -> 0x006f }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x006f }
            r6.d(r2, r3)     // Catch:{ all -> 0x006f }
            io.fabric.sdk.android.Logger r6 = io.fabric.sdk.android.Fabric.getLogger()     // Catch:{ all -> 0x006f }
            java.lang.String r2 = "Fabric"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x006f }
            r3.<init>()     // Catch:{ all -> 0x006f }
            java.lang.String r4 = "Settings query params were: "
            r3.append(r4)     // Catch:{ all -> 0x006f }
            r3.append(r1)     // Catch:{ all -> 0x006f }
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x006f }
            r6.d(r2, r1)     // Catch:{ all -> 0x006f }
            org.json.JSONObject r6 = r5.handleResponse(r0)     // Catch:{ all -> 0x006f }
            if (r0 == 0) goto L_0x006b
            io.fabric.sdk.android.Logger r1 = io.fabric.sdk.android.Fabric.getLogger()
            java.lang.String r2 = "Fabric"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Settings request ID: "
            r3.append(r4)
            java.lang.String r4 = "X-REQUEST-ID"
            java.lang.String r0 = r0.header(r4)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r1.d(r2, r0)
        L_0x006b:
            return r6
        L_0x006c:
            r6 = move-exception
            r0 = r2
            goto L_0x0070
        L_0x006f:
            r6 = move-exception
        L_0x0070:
            if (r0 == 0) goto L_0x0092
            io.fabric.sdk.android.Logger r1 = io.fabric.sdk.android.Fabric.getLogger()
            java.lang.String r2 = "Fabric"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Settings request ID: "
            r3.append(r4)
            java.lang.String r4 = "X-REQUEST-ID"
            java.lang.String r0 = r0.header(r4)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r1.d(r2, r0)
        L_0x0092:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.fabric.sdk.android.services.settings.DefaultSettingsSpiCall.invoke(io.fabric.sdk.android.services.settings.SettingsRequest):org.json.JSONObject");
    }

    /* access modifiers changed from: 0000 */
    public JSONObject handleResponse(HttpRequest httpRequest) {
        int code = httpRequest.code();
        StringBuilder sb = new StringBuilder();
        sb.append("Settings result was: ");
        sb.append(code);
        Fabric.getLogger().d("Fabric", sb.toString());
        if (requestWasSuccessful(code)) {
            return getJsonObjectFrom(httpRequest.body());
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Failed to retrieve settings from ");
        sb2.append(getUrl());
        Fabric.getLogger().e("Fabric", sb2.toString());
        return null;
    }

    private JSONObject getJsonObjectFrom(String str) {
        try {
            return new JSONObject(str);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to parse settings JSON from ");
            sb.append(getUrl());
            Fabric.getLogger().d("Fabric", sb.toString(), e);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Settings response ");
            sb2.append(str);
            Fabric.getLogger().d("Fabric", sb2.toString());
            return null;
        }
    }

    private Map<String, String> getQueryParamsFor(SettingsRequest settingsRequest) {
        HashMap hashMap = new HashMap();
        hashMap.put("build_version", settingsRequest.buildVersion);
        hashMap.put("display_version", settingsRequest.displayVersion);
        hashMap.put("source", Integer.toString(settingsRequest.source));
        if (settingsRequest.iconHash != null) {
            hashMap.put("icon_hash", settingsRequest.iconHash);
        }
        String str = settingsRequest.instanceId;
        if (!CommonUtils.isNullOrEmpty(str)) {
            hashMap.put("instance", str);
        }
        return hashMap;
    }

    private HttpRequest applyHeadersTo(HttpRequest httpRequest, SettingsRequest settingsRequest) {
        applyNonNullHeader(httpRequest, "X-CRASHLYTICS-API-KEY", settingsRequest.apiKey);
        applyNonNullHeader(httpRequest, "X-CRASHLYTICS-API-CLIENT-TYPE", "android");
        applyNonNullHeader(httpRequest, "X-CRASHLYTICS-API-CLIENT-VERSION", this.kit.getVersion());
        applyNonNullHeader(httpRequest, "Accept", "application/json");
        applyNonNullHeader(httpRequest, "X-CRASHLYTICS-DEVICE-MODEL", settingsRequest.deviceModel);
        applyNonNullHeader(httpRequest, "X-CRASHLYTICS-OS-BUILD-VERSION", settingsRequest.osBuildVersion);
        applyNonNullHeader(httpRequest, "X-CRASHLYTICS-OS-DISPLAY-VERSION", settingsRequest.osDisplayVersion);
        applyNonNullHeader(httpRequest, "X-CRASHLYTICS-ADVERTISING-TOKEN", settingsRequest.advertisingId);
        applyNonNullHeader(httpRequest, "X-CRASHLYTICS-INSTALLATION-ID", settingsRequest.installationId);
        applyNonNullHeader(httpRequest, "X-CRASHLYTICS-ANDROID-ID", settingsRequest.androidId);
        return httpRequest;
    }

    private void applyNonNullHeader(HttpRequest httpRequest, String str, String str2) {
        if (str2 != null) {
            httpRequest.header(str, str2);
        }
    }
}
