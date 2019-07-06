package io.fabric.sdk.android.services.settings;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.KitInfo;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.ResponseParser;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.util.Locale;

abstract class AbstractAppSpiCall extends AbstractSpiCall {
    public AbstractAppSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, HttpMethod httpMethod) {
        super(kit, str, str2, httpRequestFactory, httpMethod);
    }

    public boolean invoke(AppRequestData appRequestData) {
        HttpRequest applyMultipartDataTo = applyMultipartDataTo(applyHeadersTo(getHttpRequest(), appRequestData), appRequestData);
        StringBuilder sb = new StringBuilder();
        sb.append("Sending app info to ");
        sb.append(getUrl());
        Fabric.getLogger().d("Fabric", sb.toString());
        if (appRequestData.icon != null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("App icon hash is ");
            sb2.append(appRequestData.icon.hash);
            Fabric.getLogger().d("Fabric", sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            sb3.append("App icon size is ");
            sb3.append(appRequestData.icon.width);
            sb3.append("x");
            sb3.append(appRequestData.icon.height);
            Fabric.getLogger().d("Fabric", sb3.toString());
        }
        int code = applyMultipartDataTo.code();
        String str = "POST".equals(applyMultipartDataTo.method()) ? "Create" : "Update";
        StringBuilder sb4 = new StringBuilder();
        sb4.append(str);
        sb4.append(" app request ID: ");
        sb4.append(applyMultipartDataTo.header("X-REQUEST-ID"));
        Fabric.getLogger().d("Fabric", sb4.toString());
        StringBuilder sb5 = new StringBuilder();
        sb5.append("Result was ");
        sb5.append(code);
        Fabric.getLogger().d("Fabric", sb5.toString());
        return ResponseParser.parse(code) == 0;
    }

    private HttpRequest applyHeadersTo(HttpRequest httpRequest, AppRequestData appRequestData) {
        return httpRequest.header("X-CRASHLYTICS-API-KEY", appRequestData.apiKey).header("X-CRASHLYTICS-API-CLIENT-TYPE", "android").header("X-CRASHLYTICS-API-CLIENT-VERSION", this.kit.getVersion());
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x00ca  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private io.fabric.sdk.android.services.network.HttpRequest applyMultipartDataTo(io.fabric.sdk.android.services.network.HttpRequest r8, io.fabric.sdk.android.services.settings.AppRequestData r9) {
        /*
            r7 = this;
            java.lang.String r0 = "app[identifier]"
            java.lang.String r1 = r9.appId
            io.fabric.sdk.android.services.network.HttpRequest r8 = r8.part(r0, r1)
            java.lang.String r0 = "app[name]"
            java.lang.String r1 = r9.name
            io.fabric.sdk.android.services.network.HttpRequest r8 = r8.part(r0, r1)
            java.lang.String r0 = "app[display_version]"
            java.lang.String r1 = r9.displayVersion
            io.fabric.sdk.android.services.network.HttpRequest r8 = r8.part(r0, r1)
            java.lang.String r0 = "app[build_version]"
            java.lang.String r1 = r9.buildVersion
            io.fabric.sdk.android.services.network.HttpRequest r8 = r8.part(r0, r1)
            java.lang.String r0 = "app[source]"
            int r1 = r9.source
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            io.fabric.sdk.android.services.network.HttpRequest r8 = r8.part(r0, r1)
            java.lang.String r0 = "app[minimum_sdk_version]"
            java.lang.String r1 = r9.minSdkVersion
            io.fabric.sdk.android.services.network.HttpRequest r8 = r8.part(r0, r1)
            java.lang.String r0 = "app[built_sdk_version]"
            java.lang.String r1 = r9.builtSdkVersion
            io.fabric.sdk.android.services.network.HttpRequest r8 = r8.part(r0, r1)
            java.lang.String r0 = r9.instanceIdentifier
            boolean r0 = io.fabric.sdk.android.services.common.CommonUtils.isNullOrEmpty(r0)
            if (r0 != 0) goto L_0x004b
            java.lang.String r0 = "app[instance_identifier]"
            java.lang.String r1 = r9.instanceIdentifier
            r8.part(r0, r1)
        L_0x004b:
            io.fabric.sdk.android.services.settings.IconRequest r0 = r9.icon
            if (r0 == 0) goto L_0x00c6
            r0 = 0
            io.fabric.sdk.android.Kit r1 = r7.kit     // Catch:{ NotFoundException -> 0x0097, all -> 0x0094 }
            android.content.Context r1 = r1.getContext()     // Catch:{ NotFoundException -> 0x0097, all -> 0x0094 }
            android.content.res.Resources r1 = r1.getResources()     // Catch:{ NotFoundException -> 0x0097, all -> 0x0094 }
            io.fabric.sdk.android.services.settings.IconRequest r2 = r9.icon     // Catch:{ NotFoundException -> 0x0097, all -> 0x0094 }
            int r2 = r2.iconResourceId     // Catch:{ NotFoundException -> 0x0097, all -> 0x0094 }
            java.io.InputStream r1 = r1.openRawResource(r2)     // Catch:{ NotFoundException -> 0x0097, all -> 0x0094 }
            java.lang.String r0 = "app[icon][hash]"
            io.fabric.sdk.android.services.settings.IconRequest r2 = r9.icon     // Catch:{ NotFoundException -> 0x0092 }
            java.lang.String r2 = r2.hash     // Catch:{ NotFoundException -> 0x0092 }
            io.fabric.sdk.android.services.network.HttpRequest r0 = r8.part(r0, r2)     // Catch:{ NotFoundException -> 0x0092 }
            java.lang.String r2 = "app[icon][data]"
            java.lang.String r3 = "icon.png"
            java.lang.String r4 = "application/octet-stream"
            io.fabric.sdk.android.services.network.HttpRequest r0 = r0.part(r2, r3, r4, r1)     // Catch:{ NotFoundException -> 0x0092 }
            java.lang.String r2 = "app[icon][width]"
            io.fabric.sdk.android.services.settings.IconRequest r3 = r9.icon     // Catch:{ NotFoundException -> 0x0092 }
            int r3 = r3.width     // Catch:{ NotFoundException -> 0x0092 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ NotFoundException -> 0x0092 }
            io.fabric.sdk.android.services.network.HttpRequest r0 = r0.part(r2, r3)     // Catch:{ NotFoundException -> 0x0092 }
            java.lang.String r2 = "app[icon][height]"
            io.fabric.sdk.android.services.settings.IconRequest r3 = r9.icon     // Catch:{ NotFoundException -> 0x0092 }
            int r3 = r3.height     // Catch:{ NotFoundException -> 0x0092 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ NotFoundException -> 0x0092 }
            r0.part(r2, r3)     // Catch:{ NotFoundException -> 0x0092 }
            goto L_0x00b9
        L_0x0092:
            r0 = move-exception
            goto L_0x009b
        L_0x0094:
            r8 = move-exception
            r1 = r0
            goto L_0x00c0
        L_0x0097:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
        L_0x009b:
            io.fabric.sdk.android.Logger r2 = io.fabric.sdk.android.Fabric.getLogger()     // Catch:{ all -> 0x00bf }
            java.lang.String r3 = "Fabric"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bf }
            r4.<init>()     // Catch:{ all -> 0x00bf }
            java.lang.String r5 = "Failed to find app icon with resource ID: "
            r4.append(r5)     // Catch:{ all -> 0x00bf }
            io.fabric.sdk.android.services.settings.IconRequest r5 = r9.icon     // Catch:{ all -> 0x00bf }
            int r5 = r5.iconResourceId     // Catch:{ all -> 0x00bf }
            r4.append(r5)     // Catch:{ all -> 0x00bf }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x00bf }
            r2.e(r3, r4, r0)     // Catch:{ all -> 0x00bf }
        L_0x00b9:
            java.lang.String r0 = "Failed to close app icon InputStream."
            io.fabric.sdk.android.services.common.CommonUtils.closeOrLog(r1, r0)
            goto L_0x00c6
        L_0x00bf:
            r8 = move-exception
        L_0x00c0:
            java.lang.String r9 = "Failed to close app icon InputStream."
            io.fabric.sdk.android.services.common.CommonUtils.closeOrLog(r1, r9)
            throw r8
        L_0x00c6:
            java.util.Collection<io.fabric.sdk.android.KitInfo> r0 = r9.sdkKits
            if (r0 == 0) goto L_0x00f3
            java.util.Collection<io.fabric.sdk.android.KitInfo> r9 = r9.sdkKits
            java.util.Iterator r9 = r9.iterator()
        L_0x00d0:
            boolean r0 = r9.hasNext()
            if (r0 == 0) goto L_0x00f3
            java.lang.Object r0 = r9.next()
            io.fabric.sdk.android.KitInfo r0 = (io.fabric.sdk.android.KitInfo) r0
            java.lang.String r1 = r7.getKitVersionKey(r0)
            java.lang.String r2 = r0.getVersion()
            r8.part(r1, r2)
            java.lang.String r1 = r7.getKitBuildTypeKey(r0)
            java.lang.String r0 = r0.getBuildType()
            r8.part(r1, r0)
            goto L_0x00d0
        L_0x00f3:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.fabric.sdk.android.services.settings.AbstractAppSpiCall.applyMultipartDataTo(io.fabric.sdk.android.services.network.HttpRequest, io.fabric.sdk.android.services.settings.AppRequestData):io.fabric.sdk.android.services.network.HttpRequest");
    }

    /* access modifiers changed from: 0000 */
    public String getKitVersionKey(KitInfo kitInfo) {
        return String.format(Locale.US, "app[build][libraries][%s][version]", new Object[]{kitInfo.getIdentifier()});
    }

    /* access modifiers changed from: 0000 */
    public String getKitBuildTypeKey(KitInfo kitInfo) {
        return String.format(Locale.US, "app[build][libraries][%s][type]", new Object[]{kitInfo.getIdentifier()});
    }
}
