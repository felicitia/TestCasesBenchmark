package com.facebook.internal;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsIntent.Builder;

public class CustomTab {
    private Uri uri;

    public CustomTab(String str, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        String dialogAuthority = ServerProtocol.getDialogAuthority();
        StringBuilder sb = new StringBuilder();
        sb.append(ServerProtocol.getAPIVersion());
        sb.append("/");
        sb.append("dialog/");
        sb.append(str);
        this.uri = Utility.buildUri(dialogAuthority, sb.toString(), bundle);
    }

    public void openCustomTab(Activity activity, String str) {
        CustomTabsIntent build = new Builder().build();
        build.intent.setPackage(str);
        build.intent.addFlags(1073741824);
        build.launchUrl(activity, this.uri);
    }
}
