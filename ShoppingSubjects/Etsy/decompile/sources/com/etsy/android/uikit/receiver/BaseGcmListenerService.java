package com.etsy.android.uikit.receiver;

import android.os.Bundle;
import android.text.TextUtils;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.messaging.g;
import com.google.android.gms.gcm.GcmListenerService;

public abstract class BaseGcmListenerService extends GcmListenerService {
    private static final String TAG = f.a(BaseGcmListenerService.class);

    /* access modifiers changed from: protected */
    public abstract com.etsy.android.lib.messaging.f getIntentDelegate();

    public void onMessageReceived(String str, Bundle bundle) {
        if (TextUtils.equals(str, "etsynotify/delete")) {
            g.a(bundle);
            return;
        }
        f.c(TAG, "gcm onHandleIntent");
        String str2 = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Received: ");
        sb.append(bundle.toString());
        f.b(str2, sb.toString());
        if (bundle.get("alert") != null) {
            g.a(this, getIntentDelegate(), bundle, bundle.getString("alert"), bundle.getString("p"), bundle.getString("o"));
        }
    }
}
