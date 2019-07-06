package com.braintreepayments.api.internal;

import android.app.IntentService;
import android.content.Intent;
import com.braintreepayments.api.exceptions.InvalidArgumentException;
import com.braintreepayments.api.models.Authorization;
import com.braintreepayments.api.models.Configuration;
import org.json.JSONException;

public class AnalyticsIntentService extends IntentService {
    public AnalyticsIntentService() {
        super(AnalyticsIntentService.class.getSimpleName());
        setIntentRedelivery(true);
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        if (intent != null) {
            try {
                Authorization fromString = Authorization.fromString(intent.getStringExtra("com.braintreepayments.api.internal.AnalyticsIntentService.EXTRA_AUTHORIZATION"));
                AnalyticsSender.send(this, fromString, new BraintreeHttpClient(fromString), Configuration.fromJson(intent.getStringExtra("com.braintreepayments.api.internal.AnalyticsIntentService.EXTRA_CONFIGURATION")).getAnalytics().getUrl(), true);
            } catch (InvalidArgumentException | JSONException unused) {
            }
        }
    }
}
