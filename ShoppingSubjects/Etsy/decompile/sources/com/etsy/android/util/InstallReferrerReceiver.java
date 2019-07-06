package com.etsy.android.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.logger.f;
import com.etsy.android.ui.nav.NotificationActivity;
import com.google.ads.conversiontracking.InstallReceiver;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

public class InstallReferrerReceiver extends BroadcastReceiver {
    private static final String TAG = f.a(InstallReferrerReceiver.class);

    public void onReceive(Context context, Intent intent) {
        forwardToReceivers(context, intent);
        HashMap hashMap = new HashMap();
        try {
            if (intent.hasExtra("referrer") && intent.getStringExtra("referrer") != null) {
                for (String split : intent.getStringExtra("referrer").split("&")) {
                    String[] split2 = split.split("=");
                    hashMap.put(URLDecoder.decode(split2[0], "UTF-8"), split2.length > 1 ? URLDecoder.decode(split2[1], "UTF-8") : "");
                }
            }
        } catch (UnsupportedEncodingException e) {
            f.e(TAG, "URLDecoder could not decode referrer data", e);
        }
        EtsyApplication.get().getAnalyticsTracker().a("installed_app_from_play_store", null);
        if (hashMap.containsKey("uri")) {
            Uri a = c.a(hashMap);
            if (a != null && !a.getHost().equals("home")) {
                Intent intent2 = new Intent("android.intent.action.VIEW", a.buildUpon().appendQueryParameter(NotificationActivity.ETSY_DEFERRED_PARAM, "1").build());
                intent2.setClass(context, NotificationActivity.class);
                intent2.addFlags(ErrorDialogData.BINDER_CRASH);
                context.startActivity(intent2);
            }
        }
    }

    private void forwardToReceivers(Context context, Intent intent) {
        new InstallReceiver().onReceive(context, intent);
    }
}
