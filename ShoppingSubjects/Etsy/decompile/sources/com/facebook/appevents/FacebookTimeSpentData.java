package com.facebook.appevents;

import android.os.Bundle;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger.FlushBehavior;
import com.facebook.internal.t;
import java.io.Serializable;
import java.util.Locale;
import org.apache.commons.lang3.time.DateUtils;

class FacebookTimeSpentData implements Serializable {
    private static final String a = FacebookTimeSpentData.class.getCanonicalName();
    private static final long[] b = {300000, 900000, 1800000, DateUtils.MILLIS_PER_HOUR, 21600000, 43200000, DateUtils.MILLIS_PER_DAY, 172800000, 259200000, 604800000, 1209600000, 1814400000, 2419200000L, 5184000000L, 7776000000L, 10368000000L, 12960000000L, 15552000000L, 31536000000L};
    private static final long serialVersionUID = 1;
    private String firstOpenSourceApplication;
    private int interruptionCount;
    private boolean isAppActive;
    private boolean isWarmLaunch;
    private long lastActivateEventLoggedTime;
    private long lastResumeTime;
    private long lastSuspendTime;
    private long millisecondsSpentInSession;

    private static class SerializationProxyV1 implements Serializable {
        private static final long serialVersionUID = 6;
        private final int interruptionCount;
        private final long lastResumeTime;
        private final long lastSuspendTime;
        private final long millisecondsSpentInSession;

        SerializationProxyV1(long j, long j2, long j3, int i) {
            this.lastResumeTime = j;
            this.lastSuspendTime = j2;
            this.millisecondsSpentInSession = j3;
            this.interruptionCount = i;
        }

        private Object readResolve() {
            FacebookTimeSpentData facebookTimeSpentData = new FacebookTimeSpentData(this.lastResumeTime, this.lastSuspendTime, this.millisecondsSpentInSession, this.interruptionCount);
            return facebookTimeSpentData;
        }
    }

    private static class SerializationProxyV2 implements Serializable {
        private static final long serialVersionUID = 6;
        private final String firstOpenSourceApplication;
        private final int interruptionCount;
        private final long lastResumeTime;
        private final long lastSuspendTime;
        private final long millisecondsSpentInSession;

        SerializationProxyV2(long j, long j2, long j3, int i, String str) {
            this.lastResumeTime = j;
            this.lastSuspendTime = j2;
            this.millisecondsSpentInSession = j3;
            this.interruptionCount = i;
            this.firstOpenSourceApplication = str;
        }

        private Object readResolve() {
            FacebookTimeSpentData facebookTimeSpentData = new FacebookTimeSpentData(this.lastResumeTime, this.lastSuspendTime, this.millisecondsSpentInSession, this.interruptionCount, this.firstOpenSourceApplication);
            return facebookTimeSpentData;
        }
    }

    private FacebookTimeSpentData(long j, long j2, long j3, int i) {
        a();
        this.lastResumeTime = j;
        this.lastSuspendTime = j2;
        this.millisecondsSpentInSession = j3;
        this.interruptionCount = i;
    }

    FacebookTimeSpentData() {
        a();
    }

    private FacebookTimeSpentData(long j, long j2, long j3, int i, String str) {
        a();
        this.lastResumeTime = j;
        this.lastSuspendTime = j2;
        this.millisecondsSpentInSession = j3;
        this.interruptionCount = i;
        this.firstOpenSourceApplication = str;
    }

    private Object writeReplace() {
        SerializationProxyV2 serializationProxyV2 = new SerializationProxyV2(this.lastResumeTime, this.lastSuspendTime, this.millisecondsSpentInSession, this.interruptionCount, this.firstOpenSourceApplication);
        return serializationProxyV2;
    }

    /* access modifiers changed from: 0000 */
    public void onSuspend(AppEventsLogger appEventsLogger, long j) {
        if (!this.isAppActive) {
            t.a(LoggingBehavior.APP_EVENTS, a, "Suspend for inactive app");
            return;
        }
        long j2 = j - this.lastResumeTime;
        long j3 = 0;
        if (j2 < 0) {
            t.a(LoggingBehavior.APP_EVENTS, a, "Clock skew detected");
        } else {
            j3 = j2;
        }
        this.millisecondsSpentInSession += j3;
        this.lastSuspendTime = j;
        this.isAppActive = false;
    }

    /* access modifiers changed from: 0000 */
    public void onResume(AppEventsLogger appEventsLogger, long j, String str) {
        if (c() || j - this.lastActivateEventLoggedTime > 300000) {
            Bundle bundle = new Bundle();
            bundle.putString("fb_mobile_launch_source", str);
            appEventsLogger.a("fb_mobile_activate_app", bundle);
            this.lastActivateEventLoggedTime = j;
            if (AppEventsLogger.a() != FlushBehavior.EXPLICIT_ONLY) {
                appEventsLogger.b();
            }
        }
        if (this.isAppActive) {
            t.a(LoggingBehavior.APP_EVENTS, a, "Resume for active app");
            return;
        }
        long j2 = 0;
        long j3 = b() ? j - this.lastSuspendTime : 0;
        if (j3 < 0) {
            t.a(LoggingBehavior.APP_EVENTS, a, "Clock skew detected");
        } else {
            j2 = j3;
        }
        if (j2 > DateUtils.MILLIS_PER_MINUTE) {
            a(appEventsLogger, j2);
        } else if (j2 > 1000) {
            this.interruptionCount++;
        }
        if (this.interruptionCount == 0) {
            this.firstOpenSourceApplication = str;
        }
        this.lastResumeTime = j;
        this.isAppActive = true;
    }

    private void a(AppEventsLogger appEventsLogger, long j) {
        Bundle bundle = new Bundle();
        bundle.putInt("fb_mobile_app_interruptions", this.interruptionCount);
        bundle.putString("fb_mobile_time_between_sessions", String.format(Locale.ROOT, "session_quanta_%d", new Object[]{Integer.valueOf(a(j))}));
        bundle.putString("fb_mobile_launch_source", this.firstOpenSourceApplication);
        appEventsLogger.a("fb_mobile_deactivate_app", (double) (this.millisecondsSpentInSession / 1000), bundle);
        a();
    }

    private static int a(long j) {
        int i = 0;
        while (i < b.length && b[i] < j) {
            i++;
        }
        return i;
    }

    private void a() {
        this.isAppActive = false;
        this.lastResumeTime = -1;
        this.lastSuspendTime = -1;
        this.interruptionCount = 0;
        this.millisecondsSpentInSession = 0;
    }

    private boolean b() {
        return this.lastSuspendTime != -1;
    }

    private boolean c() {
        boolean z = !this.isWarmLaunch;
        this.isWarmLaunch = true;
        return z;
    }
}
