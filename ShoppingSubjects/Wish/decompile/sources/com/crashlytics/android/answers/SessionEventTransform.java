package com.crashlytics.android.answers;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import io.fabric.sdk.android.services.events.EventTransform;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

class SessionEventTransform implements EventTransform<SessionEvent> {
    SessionEventTransform() {
    }

    public byte[] toBytes(SessionEvent sessionEvent) throws IOException {
        return buildJsonForEvent(sessionEvent).toString().getBytes("UTF-8");
    }

    @TargetApi(9)
    public JSONObject buildJsonForEvent(SessionEvent sessionEvent) throws IOException {
        try {
            JSONObject jSONObject = new JSONObject();
            SessionEventMetadata sessionEventMetadata = sessionEvent.sessionEventMetadata;
            jSONObject.put("appBundleId", sessionEventMetadata.appBundleId);
            jSONObject.put("executionId", sessionEventMetadata.executionId);
            jSONObject.put("installationId", sessionEventMetadata.installationId);
            jSONObject.put("androidId", sessionEventMetadata.androidId);
            jSONObject.put("advertisingId", sessionEventMetadata.advertisingId);
            jSONObject.put("limitAdTrackingEnabled", sessionEventMetadata.limitAdTrackingEnabled);
            jSONObject.put("betaDeviceToken", sessionEventMetadata.betaDeviceToken);
            jSONObject.put("buildId", sessionEventMetadata.buildId);
            jSONObject.put("osVersion", sessionEventMetadata.osVersion);
            jSONObject.put("deviceModel", sessionEventMetadata.deviceModel);
            jSONObject.put("appVersionCode", sessionEventMetadata.appVersionCode);
            jSONObject.put("appVersionName", sessionEventMetadata.appVersionName);
            jSONObject.put("timestamp", sessionEvent.timestamp);
            jSONObject.put("type", sessionEvent.type.toString());
            if (sessionEvent.details != null) {
                jSONObject.put("details", new JSONObject(sessionEvent.details));
            }
            jSONObject.put("customType", sessionEvent.customType);
            if (sessionEvent.customAttributes != null) {
                jSONObject.put("customAttributes", new JSONObject(sessionEvent.customAttributes));
            }
            jSONObject.put("predefinedType", sessionEvent.predefinedType);
            if (sessionEvent.predefinedAttributes != null) {
                jSONObject.put("predefinedAttributes", new JSONObject(sessionEvent.predefinedAttributes));
            }
            return jSONObject;
        } catch (JSONException e) {
            if (VERSION.SDK_INT >= 9) {
                throw new IOException(e.getMessage(), e);
            }
            throw new IOException(e.getMessage());
        }
    }
}
