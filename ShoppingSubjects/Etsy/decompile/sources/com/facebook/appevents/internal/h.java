package com.facebook.appevents.internal;

import android.content.Context;
import android.os.Bundle;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.AppEventsLogger.FlushBehavior;
import com.facebook.internal.t;
import java.util.Locale;
import org.apache.commons.lang3.time.DateUtils;

/* compiled from: SessionLogger */
class h {
    private static final String a = h.class.getCanonicalName();
    private static final long[] b = {300000, 900000, 1800000, DateUtils.MILLIS_PER_HOUR, 21600000, 43200000, DateUtils.MILLIS_PER_DAY, 172800000, 259200000, 604800000, 1209600000, 1814400000, 2419200000L, 5184000000L, 7776000000L, 10368000000L, 12960000000L, 15552000000L, 31536000000L};

    h() {
    }

    public static void a(Context context, String str, i iVar, String str2) {
        String iVar2 = iVar != null ? iVar.toString() : "Unclassified";
        Bundle bundle = new Bundle();
        bundle.putString("fb_mobile_launch_source", iVar2);
        f fVar = new f(str, str2, null);
        fVar.a("fb_mobile_activate_app", bundle);
        if (AppEventsLogger.a() != FlushBehavior.EXPLICIT_ONLY) {
            fVar.b();
        }
    }

    public static void a(Context context, String str, g gVar, String str2) {
        Long valueOf = Long.valueOf(gVar.f() - gVar.c().longValue());
        if (valueOf.longValue() < 0) {
            valueOf = Long.valueOf(0);
            a();
        }
        Long valueOf2 = Long.valueOf(gVar.h());
        if (valueOf2.longValue() < 0) {
            a();
            valueOf2 = Long.valueOf(0);
        }
        Bundle bundle = new Bundle();
        bundle.putInt("fb_mobile_app_interruptions", gVar.d());
        bundle.putString("fb_mobile_time_between_sessions", String.format(Locale.ROOT, "session_quanta_%d", new Object[]{Integer.valueOf(a(valueOf.longValue()))}));
        i i = gVar.i();
        bundle.putString("fb_mobile_launch_source", i != null ? i.toString() : "Unclassified");
        bundle.putLong("_logTime", gVar.c().longValue() / 1000);
        new f(str, str2, null).a("fb_mobile_deactivate_app", (double) (valueOf2.longValue() / 1000), bundle);
    }

    private static void a() {
        t.a(LoggingBehavior.APP_EVENTS, a, "Clock skew detected");
    }

    private static int a(long j) {
        int i = 0;
        while (i < b.length && b[i] < j) {
            i++;
        }
        return i;
    }
}
