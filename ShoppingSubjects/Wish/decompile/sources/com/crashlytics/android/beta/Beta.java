package com.crashlytics.android.beta;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.cache.MemoryValueCache;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.DeviceIdentifierProvider;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.common.IdManager.DeviceIdentifierType;
import io.fabric.sdk.android.services.common.SystemCurrentTimeProvider;
import io.fabric.sdk.android.services.network.DefaultHttpRequestFactory;
import io.fabric.sdk.android.services.persistence.PreferenceStoreImpl;
import io.fabric.sdk.android.services.settings.BetaSettingsData;
import io.fabric.sdk.android.services.settings.Settings;
import io.fabric.sdk.android.services.settings.SettingsData;
import java.util.HashMap;
import java.util.Map;

public class Beta extends Kit<Boolean> implements DeviceIdentifierProvider {
    private final MemoryValueCache<String> deviceTokenCache = new MemoryValueCache<>();
    private final DeviceTokenLoader deviceTokenLoader = new DeviceTokenLoader();
    private UpdatesController updatesController;

    public String getIdentifier() {
        return "com.crashlytics.sdk.android:beta";
    }

    public String getVersion() {
        return "1.1.5.119";
    }

    /* access modifiers changed from: protected */
    @TargetApi(14)
    public boolean onPreExecute() {
        this.updatesController = createUpdatesController(VERSION.SDK_INT, (Application) getContext().getApplicationContext());
        return true;
    }

    /* access modifiers changed from: protected */
    public Boolean doInBackground() {
        Fabric.getLogger().d("Beta", "Beta kit initializing...");
        Context context = getContext();
        IdManager idManager = getIdManager();
        if (TextUtils.isEmpty(getBetaDeviceToken(context, idManager.getInstallerPackageName()))) {
            Fabric.getLogger().d("Beta", "A Beta device token was not found for this app");
            return Boolean.valueOf(false);
        }
        Fabric.getLogger().d("Beta", "Beta device token is present, checking for app updates.");
        BetaSettingsData betaSettingsData = getBetaSettingsData();
        BuildProperties loadBuildProperties = loadBuildProperties(context);
        if (canCheckForUpdates(betaSettingsData, loadBuildProperties)) {
            this.updatesController.initialize(context, this, idManager, betaSettingsData, loadBuildProperties, new PreferenceStoreImpl(this), new SystemCurrentTimeProvider(), new DefaultHttpRequestFactory(Fabric.getLogger()));
        }
        return Boolean.valueOf(true);
    }

    /* access modifiers changed from: 0000 */
    @TargetApi(14)
    public UpdatesController createUpdatesController(int i, Application application) {
        if (i >= 14) {
            return new ActivityLifecycleCheckForUpdatesController(getFabric().getActivityLifecycleManager(), getFabric().getExecutorService());
        }
        return new ImmediateCheckForUpdatesController();
    }

    public Map<DeviceIdentifierType, String> getDeviceIdentifiers() {
        String betaDeviceToken = getBetaDeviceToken(getContext(), getIdManager().getInstallerPackageName());
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(betaDeviceToken)) {
            hashMap.put(DeviceIdentifierType.FONT_TOKEN, betaDeviceToken);
        }
        return hashMap;
    }

    /* access modifiers changed from: 0000 */
    @TargetApi(11)
    public boolean isAppPossiblyInstalledByBeta(String str, int i) {
        if (i >= 11) {
            return "io.crash.air".equals(str);
        }
        return str == null;
    }

    /* access modifiers changed from: 0000 */
    public boolean canCheckForUpdates(BetaSettingsData betaSettingsData, BuildProperties buildProperties) {
        return (betaSettingsData == null || TextUtils.isEmpty(betaSettingsData.updateUrl) || buildProperties == null) ? false : true;
    }

    private String getBetaDeviceToken(Context context, String str) {
        if (isAppPossiblyInstalledByBeta(str, VERSION.SDK_INT)) {
            Fabric.getLogger().d("Beta", "App was possibly installed by Beta. Getting device token");
            try {
                String str2 = (String) this.deviceTokenCache.get(context, this.deviceTokenLoader);
                if ("".equals(str2)) {
                    return null;
                }
                return str2;
            } catch (Exception e) {
                Fabric.getLogger().e("Beta", "Failed to load the Beta device token", e);
                return null;
            }
        } else {
            Fabric.getLogger().d("Beta", "App was not installed by Beta. Skipping device token");
            return null;
        }
    }

    private BetaSettingsData getBetaSettingsData() {
        SettingsData awaitSettingsData = Settings.getInstance().awaitSettingsData();
        if (awaitSettingsData != null) {
            return awaitSettingsData.betaSettingsData;
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x007d A[SYNTHETIC, Splitter:B:24:0x007d] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0092 A[SYNTHETIC, Splitter:B:31:0x0092] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.crashlytics.android.beta.BuildProperties loadBuildProperties(android.content.Context r7) {
        /*
            r6 = this;
            r0 = 0
            android.content.res.AssetManager r7 = r7.getAssets()     // Catch:{ Exception -> 0x006c, all -> 0x0067 }
            java.lang.String r1 = "crashlytics-build.properties"
            java.io.InputStream r7 = r7.open(r1)     // Catch:{ Exception -> 0x006c, all -> 0x0067 }
            if (r7 == 0) goto L_0x0054
            com.crashlytics.android.beta.BuildProperties r1 = com.crashlytics.android.beta.BuildProperties.fromPropertiesStream(r7)     // Catch:{ Exception -> 0x004f }
            io.fabric.sdk.android.Logger r0 = io.fabric.sdk.android.Fabric.getLogger()     // Catch:{ Exception -> 0x004d }
            java.lang.String r2 = "Beta"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004d }
            r3.<init>()     // Catch:{ Exception -> 0x004d }
            java.lang.String r4 = r1.packageName     // Catch:{ Exception -> 0x004d }
            r3.append(r4)     // Catch:{ Exception -> 0x004d }
            java.lang.String r4 = " build properties: "
            r3.append(r4)     // Catch:{ Exception -> 0x004d }
            java.lang.String r4 = r1.versionName     // Catch:{ Exception -> 0x004d }
            r3.append(r4)     // Catch:{ Exception -> 0x004d }
            java.lang.String r4 = " ("
            r3.append(r4)     // Catch:{ Exception -> 0x004d }
            java.lang.String r4 = r1.versionCode     // Catch:{ Exception -> 0x004d }
            r3.append(r4)     // Catch:{ Exception -> 0x004d }
            java.lang.String r4 = ")"
            r3.append(r4)     // Catch:{ Exception -> 0x004d }
            java.lang.String r4 = " - "
            r3.append(r4)     // Catch:{ Exception -> 0x004d }
            java.lang.String r4 = r1.buildId     // Catch:{ Exception -> 0x004d }
            r3.append(r4)     // Catch:{ Exception -> 0x004d }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x004d }
            r0.d(r2, r3)     // Catch:{ Exception -> 0x004d }
            r0 = r1
            goto L_0x0054
        L_0x004d:
            r0 = move-exception
            goto L_0x0070
        L_0x004f:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x0070
        L_0x0054:
            if (r7 == 0) goto L_0x008e
            r7.close()     // Catch:{ IOException -> 0x005a }
            goto L_0x008e
        L_0x005a:
            r7 = move-exception
            io.fabric.sdk.android.Logger r1 = io.fabric.sdk.android.Fabric.getLogger()
            java.lang.String r2 = "Beta"
            java.lang.String r3 = "Error closing Beta build properties asset"
            r1.e(r2, r3, r7)
            goto L_0x008e
        L_0x0067:
            r7 = move-exception
            r5 = r0
            r0 = r7
            r7 = r5
            goto L_0x0090
        L_0x006c:
            r7 = move-exception
            r1 = r0
            r0 = r7
            r7 = r1
        L_0x0070:
            io.fabric.sdk.android.Logger r2 = io.fabric.sdk.android.Fabric.getLogger()     // Catch:{ all -> 0x008f }
            java.lang.String r3 = "Beta"
            java.lang.String r4 = "Error reading Beta build properties"
            r2.e(r3, r4, r0)     // Catch:{ all -> 0x008f }
            if (r7 == 0) goto L_0x008d
            r7.close()     // Catch:{ IOException -> 0x0081 }
            goto L_0x008d
        L_0x0081:
            r7 = move-exception
            io.fabric.sdk.android.Logger r0 = io.fabric.sdk.android.Fabric.getLogger()
            java.lang.String r2 = "Beta"
            java.lang.String r3 = "Error closing Beta build properties asset"
            r0.e(r2, r3, r7)
        L_0x008d:
            r0 = r1
        L_0x008e:
            return r0
        L_0x008f:
            r0 = move-exception
        L_0x0090:
            if (r7 == 0) goto L_0x00a2
            r7.close()     // Catch:{ IOException -> 0x0096 }
            goto L_0x00a2
        L_0x0096:
            r7 = move-exception
            io.fabric.sdk.android.Logger r1 = io.fabric.sdk.android.Fabric.getLogger()
            java.lang.String r2 = "Beta"
            java.lang.String r3 = "Error closing Beta build properties asset"
            r1.e(r2, r3, r7)
        L_0x00a2:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crashlytics.android.beta.Beta.loadBuildProperties(android.content.Context):com.crashlytics.android.beta.BuildProperties");
    }

    /* access modifiers changed from: 0000 */
    public String getOverridenSpiEndpoint() {
        return CommonUtils.getStringsFileValue(getContext(), "com.crashlytics.ApiEndpoint");
    }
}
