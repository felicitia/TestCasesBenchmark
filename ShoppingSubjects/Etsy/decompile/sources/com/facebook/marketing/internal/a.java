package com.facebook.marketing.internal;

import android.content.Context;
import android.os.Bundle;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.f;

/* compiled from: MarketingLogger */
public final class a {
    private final AppEventsLogger a;

    public a(Context context, String str) {
        this.a = AppEventsLogger.b(context, str);
    }

    public void a() {
        if (f.m() && f.n()) {
            Bundle bundle = new Bundle();
            bundle.putString("_codeless_action", "sdk_initialized");
            this.a.a("fb_codeless_debug", (Double) null, bundle);
        }
    }

    public void b() {
        if (f.m() && f.n()) {
            Bundle bundle = new Bundle();
            bundle.putString("_codeless_action", "gesture_triggered");
            this.a.a("fb_codeless_debug", (Double) null, bundle);
        }
    }

    public void c() {
        if (f.m() && f.n()) {
            Bundle bundle = new Bundle();
            bundle.putString("_codeless_action", "session_ready");
            this.a.a("fb_codeless_debug", (Double) null, bundle);
        }
    }

    public void a(String str) {
        if (f.m() && f.n()) {
            Bundle bundle = new Bundle();
            bundle.putString("_codeless_action", "indexing_start");
            bundle.putString("_activity_name", str);
            this.a.a("fb_codeless_debug", (Double) null, bundle);
        }
    }

    public void b(String str) {
        if (f.m() && f.n()) {
            Bundle bundle = new Bundle();
            bundle.putString("_codeless_action", "indexing_complete");
            bundle.putString("_activity_name", str);
            this.a.a("fb_codeless_debug", (Double) null, bundle);
        }
    }

    public void c(String str) {
        if (f.m() && f.n()) {
            Bundle bundle = new Bundle();
            bundle.putString("_codeless_action", "indexing_cancelled");
            bundle.putString("_activity_name", str);
            this.a.a("fb_codeless_debug", (Double) null, bundle);
        }
    }
}
