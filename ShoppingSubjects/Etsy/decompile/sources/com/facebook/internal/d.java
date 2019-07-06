package com.facebook.internal;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsIntent.Builder;
import com.facebook.f;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

/* compiled from: CustomTab */
public class d {
    private Uri a;

    public d(String str, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        String a2 = x.a();
        StringBuilder sb = new StringBuilder();
        sb.append(f.g());
        sb.append("/");
        sb.append("dialog/");
        sb.append(str);
        this.a = z.a(a2, sb.toString(), bundle);
    }

    public void a(Activity activity, String str) {
        CustomTabsIntent build = new Builder().build();
        build.intent.setPackage(str);
        build.intent.addFlags(ErrorDialogData.SUPPRESSED);
        build.launchUrl(activity, this.a);
    }
}
