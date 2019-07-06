package com.crashlytics.android.core;

import android.util.Log;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.common.CommonUtils;

class BuildIdValidator {
    private final String buildId;
    private final boolean requiringBuildId;

    /* access modifiers changed from: protected */
    public String getMessage(String str, String str2) {
        return "This app relies on Crashlytics. Please sign up for access at https://fabric.io/sign_up,\ninstall an Android build tool and ask a team member to invite you to this app's organization.";
    }

    public BuildIdValidator(String str, boolean z) {
        this.buildId = str;
        this.requiringBuildId = z;
    }

    public void validate(String str, String str2) {
        if (CommonUtils.isNullOrEmpty(this.buildId) && this.requiringBuildId) {
            String message = getMessage(str, str2);
            Log.e("CrashlyticsCore", ".");
            Log.e("CrashlyticsCore", ".     |  | ");
            Log.e("CrashlyticsCore", ".     |  |");
            Log.e("CrashlyticsCore", ".     |  |");
            Log.e("CrashlyticsCore", ".   \\ |  | /");
            Log.e("CrashlyticsCore", ".    \\    /");
            Log.e("CrashlyticsCore", ".     \\  /");
            Log.e("CrashlyticsCore", ".      \\/");
            Log.e("CrashlyticsCore", ".");
            Log.e("CrashlyticsCore", message);
            Log.e("CrashlyticsCore", ".");
            Log.e("CrashlyticsCore", ".      /\\");
            Log.e("CrashlyticsCore", ".     /  \\");
            Log.e("CrashlyticsCore", ".    /    \\");
            Log.e("CrashlyticsCore", ".   / |  | \\");
            Log.e("CrashlyticsCore", ".     |  |");
            Log.e("CrashlyticsCore", ".     |  |");
            Log.e("CrashlyticsCore", ".     |  |");
            Log.e("CrashlyticsCore", ".");
            throw new CrashlyticsMissingDependencyException(message);
        } else if (!this.requiringBuildId) {
            Fabric.getLogger().d("CrashlyticsCore", "Configured not to require a build ID.");
        }
    }
}
