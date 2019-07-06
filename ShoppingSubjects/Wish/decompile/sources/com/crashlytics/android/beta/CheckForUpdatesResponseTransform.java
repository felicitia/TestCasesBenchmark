package com.crashlytics.android.beta;

import java.io.IOException;
import org.json.JSONObject;

class CheckForUpdatesResponseTransform {
    CheckForUpdatesResponseTransform() {
    }

    public CheckForUpdatesResponse fromJson(JSONObject jSONObject) throws IOException {
        if (jSONObject == null) {
            return null;
        }
        CheckForUpdatesResponse checkForUpdatesResponse = new CheckForUpdatesResponse(jSONObject.optString("url", null), jSONObject.optString("version_string", null), jSONObject.optString("display_version", null), jSONObject.optString("build_version", null), jSONObject.optString("identifier", null), jSONObject.optString("instance_identifier", null));
        return checkForUpdatesResponse;
    }
}
