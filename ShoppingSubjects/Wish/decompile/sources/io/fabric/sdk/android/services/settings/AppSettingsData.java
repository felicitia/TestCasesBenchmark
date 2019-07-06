package io.fabric.sdk.android.services.settings;

public class AppSettingsData {
    public final AppIconSettingsData icon;
    public final String identifier;
    public final String reportsUrl;
    public final String status;
    public final boolean updateRequired;
    public final String url;

    public AppSettingsData(String str, String str2, String str3, String str4, boolean z, AppIconSettingsData appIconSettingsData) {
        this.identifier = str;
        this.status = str2;
        this.url = str3;
        this.reportsUrl = str4;
        this.updateRequired = z;
        this.icon = appIconSettingsData;
    }
}
