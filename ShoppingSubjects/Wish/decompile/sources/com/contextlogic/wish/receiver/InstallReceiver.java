package com.contextlogic.wish.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.contextlogic.wish.application.ApplicationPinger;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.util.PreferenceUtil;

public class InstallReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("referrer");
        if (!(WishApplication.getInstance() == null || stringExtra == null)) {
            if (PreferenceUtil.getString("AppReferrer") == null) {
                ApplicationPinger.sendServerPing(stringExtra, null, true, null);
            }
            PreferenceUtil.setString("AppReferrer", stringExtra);
        }
    }
}
