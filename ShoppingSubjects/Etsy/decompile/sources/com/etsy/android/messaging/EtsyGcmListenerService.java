package com.etsy.android.messaging;

import android.app.Service;
import android.os.Bundle;
import com.etsy.android.lib.core.b.a;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.messaging.f;
import com.etsy.android.messaging.salesforce.c;
import com.etsy.android.uikit.receiver.BaseGcmListenerService;
import com.salesforce.marketingcloud.c.b;

public class EtsyGcmListenerService extends BaseGcmListenerService implements a {
    protected e mIntentDelegate = new e();
    h notificationRepo;

    public void onCreate() {
        super.onCreate();
        dagger.android.a.a((Service) this);
    }

    /* access modifiers changed from: protected */
    public f getIntentDelegate() {
        return this.mIntentDelegate;
    }

    public void onMessageReceived(String str, Bundle bundle) {
        if (!com.salesforce.marketingcloud.messages.push.a.a(bundle)) {
            super.onMessageReceived(str, bundle);
            this.notificationRepo.a(bundle);
        } else if (c.b() && v.a().e()) {
            com.salesforce.marketingcloud.c.a((b) new b(bundle));
        }
    }
}
