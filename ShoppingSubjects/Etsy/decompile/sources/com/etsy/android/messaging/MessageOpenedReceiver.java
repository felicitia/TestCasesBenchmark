package com.etsy.android.messaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.text.TextUtils;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.b;
import com.etsy.android.lib.models.ResponseConstants;
import java.util.HashMap;

public class MessageOpenedReceiver extends BroadcastReceiver {
    private static final String DEFAULT_URI = "etsy://default-push-landing";
    public static final String ET_PUSH_OPEN_INTENT = "et_push_open_intent";

    public void onReceive(Context context, Intent intent) {
        if (intent.getExtras().get(ET_PUSH_OPEN_INTENT) instanceof Intent) {
            Bundle extras = ((Intent) intent.getExtras().get(ET_PUSH_OPEN_INTENT)).getExtras();
            trackPushOpened("salesforce_notification_open", extras.getString(ResponseConstants.DEEP_LINK), extras.getString("title"), extras.getString("alert"), extras);
        }
    }

    private void trackPushOpened(String str, String str2, String str3, String str4, Bundle bundle) {
        if (TextUtils.isEmpty(str2)) {
            str2 = DEFAULT_URI;
        }
        final String str5 = str2;
        final String string = bundle.getString("utm_source", ResponseConstants.OTHER);
        final String string2 = bundle.getString("utm_medium", "push");
        final String string3 = bundle.getString("utm_campaign", ResponseConstants.OTHER);
        final String buildPushTrackingLoc = buildPushTrackingLoc(str5, string, string2, string3);
        b analyticsTracker = EtsyApplication.get().getAnalyticsTracker();
        final String str6 = str3;
        final String str7 = str4;
        AnonymousClass1 r0 = new HashMap<AnalyticsLogAttribute, Object>() {
            {
                put(AnalyticsLogAttribute.NOTIFICATION_URI, str5);
                put(AnalyticsLogAttribute.NOTIFICATION_TITLE, str6 != null ? str6 : "");
                put(AnalyticsLogAttribute.NOTIFICATION_CONTENT, str7 != null ? str7 : "");
                put(AnalyticsLogAttribute.UTM_SOURCE, string);
                put(AnalyticsLogAttribute.UTM_MEDIUM, string2);
                put(AnalyticsLogAttribute.UTM_CAMPAIGN, string3);
                put(AnalyticsLogAttribute.LOC, buildPushTrackingLoc);
            }
        };
        analyticsTracker.a(str, r0);
    }

    private static String buildPushTrackingLoc(String str, String str2, String str3, String str4) {
        Builder buildUpon = Uri.parse(str).buildUpon();
        buildUpon.appendQueryParameter("utm_source", str2);
        buildUpon.appendQueryParameter("utm_medium", str3);
        buildUpon.appendQueryParameter("utm_campaign", str4);
        return buildUpon.build().toString();
    }
}
