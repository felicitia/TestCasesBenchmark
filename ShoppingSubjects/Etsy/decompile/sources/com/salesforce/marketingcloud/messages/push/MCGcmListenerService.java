package com.salesforce.marketingcloud.messages.push;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.gcm.GcmListenerService;
import com.salesforce.marketingcloud.c;
import com.salesforce.marketingcloud.j;

public final class MCGcmListenerService extends GcmListenerService {
    private static final String a = j.a(MCGcmListenerService.class);

    public void onMessageReceived(String str, Bundle bundle) {
        j.a(a, "onMessageReceived - %s", str);
        if (c.c() || c.b()) {
            if (c.a() != null) {
                if (bundle == null) {
                    j.a(a, "gcm message data was null", new Object[0]);
                    return;
                }
                b.a((Context) this, bundle);
            }
            return;
        }
        j.d(a, "MarketingCloudSdk#init must be called in your application's onCreate", new Object[0]);
    }
}
