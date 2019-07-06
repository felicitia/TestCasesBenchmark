package com.wish.android.shakedelegate;

import android.app.Activity;
import android.net.Uri;
import com.wish.android.shaky.Result;
import com.wish.android.shaky.ShakeDelegate;
import java.io.File;

public class DeviceInfoShakeDelegateWrapper extends ShakeDelegateWrapper {
    private DeviceInfo deviceInfo;

    public static class DeviceInfo {
        public final String applicationId;
        public final String buildType;
        public final String flavor;
        public final boolean isDebug;
        public final String userEmail;
        public final String userId;
        public final int versionCode;
        public final String versionName;

        public DeviceInfo(String str, String str2, int i, String str3, String str4, boolean z, String str5, String str6) {
            this.applicationId = str;
            this.buildType = str2;
            this.versionCode = i;
            this.versionName = str3;
            this.flavor = str4;
            this.isDebug = z;
            this.userId = str5;
            this.userEmail = str6;
        }
    }

    public DeviceInfoShakeDelegateWrapper(ShakeDelegate shakeDelegate) {
        super(shakeDelegate);
    }

    public DeviceInfoShakeDelegateWrapper setDeviceInfo(DeviceInfo deviceInfo2) {
        this.deviceInfo = deviceInfo2;
        return this;
    }

    public void collectData(Activity activity, Result result) {
        super.collectData(activity, result);
        result.getAttachments().add(Uri.fromFile(FileHelper.writeStringToFile(new File(activity.getFilesDir(), "device_info.txt"), getDeviceInfoMessage())));
    }

    private String getDeviceInfoMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("ApplicationId: ");
        sb.append(this.deviceInfo.applicationId);
        sb.append("\nBuildType: ");
        sb.append(this.deviceInfo.buildType);
        sb.append("\nVersionCode: ");
        sb.append(this.deviceInfo.versionCode);
        sb.append("\nVersionName: ");
        sb.append(this.deviceInfo.versionName);
        sb.append("\nFlavor: ");
        sb.append(this.deviceInfo.flavor);
        sb.append("\nDebug: ");
        sb.append(this.deviceInfo.isDebug);
        sb.append("\nUserId: ");
        sb.append(this.deviceInfo.userId);
        sb.append("\nUserEmail: ");
        sb.append(this.deviceInfo.userEmail);
        sb.append("\n");
        return sb.toString();
    }
}
