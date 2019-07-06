package com.etsy.android.ui.feedback;

import com.etsy.android.lib.core.http.url.a.b;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.models.apiv3.Feedback;
import com.etsy.android.uikit.nav.TrackingBaseActivity;
import kotlin.jvm.internal.p;

/* compiled from: FeedbackActivityModule.kt */
public final class a {
    public final TrackingBaseActivity a(FeedbackActivity feedbackActivity) {
        p.b(feedbackActivity, "activity");
        return feedbackActivity;
    }

    public final i a(com.etsy.android.lib.core.http.request.EtsyApiV3Request.a<Feedback> aVar, com.etsy.android.lib.core.http.request.d.a<Feedback> aVar2, v vVar) {
        p.b(aVar, "apiV3FeedbackBuilder");
        p.b(aVar2, "apiV3JobFeedbackBuilder");
        p.b(vVar, "session");
        return new i(aVar, aVar2, vVar);
    }

    public final com.etsy.android.lib.core.http.request.EtsyApiV3Request.a<Feedback> a() {
        com.etsy.android.lib.core.http.request.EtsyApiV3Request.a<Feedback> a = com.etsy.android.lib.core.http.request.EtsyApiV3Request.a.a(Feedback.class, b.d());
        p.a((Object) a, "EtsyApiV3Request.Builder…a, EtsyV3Urls.feedback())");
        return a;
    }

    public final com.etsy.android.lib.core.http.request.d.a<Feedback> b() {
        return new com.etsy.android.lib.core.http.request.d.a<>();
    }
}
