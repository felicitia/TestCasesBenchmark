package com.google.android.gms.measurement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.bu;
import com.google.android.gms.internal.measurement.dm;
import com.google.android.gms.internal.measurement.fg;
import com.google.android.gms.internal.measurement.zzka;
import java.util.List;
import java.util.Map;

@Keep
@Deprecated
public class AppMeasurement {
    @KeepForSdk
    public static final String CRASH_ORIGIN = "crash";
    @KeepForSdk
    public static final String FCM_ORIGIN = "fcm";
    @KeepForSdk
    public static final String FIAM_ORIGIN = "fiam";
    private final bu zzacv;

    @KeepForSdk
    public static class ConditionalUserProperty {
        @Keep
        @KeepForSdk
        public boolean mActive;
        @Keep
        @KeepForSdk
        public String mAppId;
        @Keep
        @KeepForSdk
        public long mCreationTimestamp;
        @Keep
        public String mExpiredEventName;
        @Keep
        public Bundle mExpiredEventParams;
        @Keep
        @KeepForSdk
        public String mName;
        @Keep
        @KeepForSdk
        public String mOrigin;
        @Keep
        @KeepForSdk
        public long mTimeToLive;
        @Keep
        public String mTimedOutEventName;
        @Keep
        public Bundle mTimedOutEventParams;
        @Keep
        @KeepForSdk
        public String mTriggerEventName;
        @Keep
        @KeepForSdk
        public long mTriggerTimeout;
        @Keep
        public String mTriggeredEventName;
        @Keep
        public Bundle mTriggeredEventParams;
        @Keep
        @KeepForSdk
        public long mTriggeredTimestamp;
        @Keep
        @KeepForSdk
        public Object mValue;

        public ConditionalUserProperty() {
        }

        public ConditionalUserProperty(ConditionalUserProperty conditionalUserProperty) {
            Preconditions.checkNotNull(conditionalUserProperty);
            this.mAppId = conditionalUserProperty.mAppId;
            this.mOrigin = conditionalUserProperty.mOrigin;
            this.mCreationTimestamp = conditionalUserProperty.mCreationTimestamp;
            this.mName = conditionalUserProperty.mName;
            if (conditionalUserProperty.mValue != null) {
                this.mValue = fg.b(conditionalUserProperty.mValue);
                if (this.mValue == null) {
                    this.mValue = conditionalUserProperty.mValue;
                }
            }
            this.mActive = conditionalUserProperty.mActive;
            this.mTriggerEventName = conditionalUserProperty.mTriggerEventName;
            this.mTriggerTimeout = conditionalUserProperty.mTriggerTimeout;
            this.mTimedOutEventName = conditionalUserProperty.mTimedOutEventName;
            if (conditionalUserProperty.mTimedOutEventParams != null) {
                this.mTimedOutEventParams = new Bundle(conditionalUserProperty.mTimedOutEventParams);
            }
            this.mTriggeredEventName = conditionalUserProperty.mTriggeredEventName;
            if (conditionalUserProperty.mTriggeredEventParams != null) {
                this.mTriggeredEventParams = new Bundle(conditionalUserProperty.mTriggeredEventParams);
            }
            this.mTriggeredTimestamp = conditionalUserProperty.mTriggeredTimestamp;
            this.mTimeToLive = conditionalUserProperty.mTimeToLive;
            this.mExpiredEventName = conditionalUserProperty.mExpiredEventName;
            if (conditionalUserProperty.mExpiredEventParams != null) {
                this.mExpiredEventParams = new Bundle(conditionalUserProperty.mExpiredEventParams);
            }
        }
    }

    @KeepForSdk
    public static final class a extends com.google.firebase.analytics.FirebaseAnalytics.a {
        public static final String[] a = {"app_clear_data", "app_exception", "app_remove", "app_upgrade", "app_install", "app_update", "firebase_campaign", "error", "first_open", "first_visit", "in_app_purchase", "notification_dismiss", "notification_foreground", "notification_open", "notification_receive", "os_update", "session_start", "user_engagement", "ad_exposure", "adunit_exposure", "ad_query", "ad_activeview", "ad_impression", "ad_click", "ad_reward", "screen_view", "ga_extra_parameter"};
        public static final String[] b = {"_cd", "_ae", "_ui", "_ug", "_in", "_au", "_cmp", "_err", "_f", "_v", "_iap", "_nd", "_nf", "_no", "_nr", "_ou", "_s", "_e", "_xa", "_xu", "_aq", "_aa", "_ai", "_ac", "_ar", "_vs", "_ep"};

        public static String a(String str) {
            return fg.a(str, a, b);
        }
    }

    @KeepForSdk
    public interface b {
        @WorkerThread
        @KeepForSdk
        void a(String str, String str2, Bundle bundle, long j);
    }

    @KeepForSdk
    public interface c {
        @WorkerThread
        @KeepForSdk
        void a(String str, String str2, Bundle bundle, long j);
    }

    @KeepForSdk
    public static final class d extends com.google.firebase.analytics.FirebaseAnalytics.b {
        public static final String[] a = {"firebase_conversion", "engagement_time_msec", "exposure_time", "ad_event_id", "ad_unit_id", "firebase_error", "firebase_error_value", "firebase_error_length", "firebase_event_origin", "firebase_screen", "firebase_screen_class", "firebase_screen_id", "firebase_previous_screen", "firebase_previous_class", "firebase_previous_id", "message_device_time", "message_id", "message_name", "message_time", "previous_app_version", "previous_os_version", "topic", "update_with_analytics", "previous_first_open_count", "system_app", "system_app_update", "previous_install_count", "ga_event_id", "ga_extra_params_ct", "ga_group_name", "ga_list_length", "ga_index", "ga_event_name", "campaign_info_source", "deferred_analytics_collection"};
        public static final String[] b = {"_c", "_et", "_xt", "_aeid", "_ai", "_err", "_ev", "_el", "_o", "_sn", "_sc", "_si", "_pn", "_pc", "_pi", "_ndt", "_nmid", "_nmn", "_nmt", "_pv", "_po", "_nt", "_uwa", "_pfo", "_sys", "_sysu", "_pin", "_eid", "_epc", "_gn", "_ll", "_i", "_en", "_cis", "_dac"};

        public static String a(String str) {
            return fg.a(str, a, b);
        }
    }

    @KeepForSdk
    public static final class e extends com.google.firebase.analytics.FirebaseAnalytics.c {
        public static final String[] a = {"firebase_last_notification", "first_open_time", "first_visit_time", "last_deep_link_referrer", "user_id", "first_open_after_install", "lifetime_user_engagement"};
        public static final String[] b = {"_ln", "_fot", "_fvt", "_ldl", "_id", "_fi", "_lte"};

        public static String a(String str) {
            return fg.a(str, a, b);
        }
    }

    public AppMeasurement(bu buVar) {
        Preconditions.checkNotNull(buVar);
        this.zzacv = buVar;
    }

    @Keep
    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.WAKE_LOCK"})
    @Deprecated
    public static AppMeasurement getInstance(Context context) {
        return bu.a(context, null, null).i();
    }

    @Keep
    public void beginAdUnitExposure(@Size(min = 1) @NonNull String str) {
        this.zzacv.x().a(str);
    }

    @Keep
    @KeepForSdk
    public void clearConditionalUserProperty(@Size(max = 24, min = 1) @NonNull String str, @Nullable String str2, @Nullable Bundle bundle) {
        this.zzacv.h().c(str, str2, bundle);
    }

    /* access modifiers changed from: protected */
    @Keep
    @VisibleForTesting
    public void clearConditionalUserPropertyAs(@Size(min = 1) @NonNull String str, @Size(max = 24, min = 1) @NonNull String str2, @Nullable String str3, @Nullable Bundle bundle) {
        this.zzacv.h().a(str, str2, str3, bundle);
    }

    @Keep
    public void endAdUnitExposure(@Size(min = 1) @NonNull String str) {
        this.zzacv.x().b(str);
    }

    @Keep
    public long generateEventId() {
        return this.zzacv.k().g();
    }

    @Keep
    @Nullable
    public String getAppInstanceId() {
        return this.zzacv.h().H();
    }

    @KeepForSdk
    public Boolean getBoolean() {
        return this.zzacv.h().B();
    }

    @Keep
    @WorkerThread
    @KeepForSdk
    public List<ConditionalUserProperty> getConditionalUserProperties(@Nullable String str, @Nullable @Size(max = 23, min = 1) String str2) {
        return this.zzacv.h().a(str, str2);
    }

    /* access modifiers changed from: protected */
    @Keep
    @WorkerThread
    @VisibleForTesting
    public List<ConditionalUserProperty> getConditionalUserPropertiesAs(@Size(min = 1) @NonNull String str, @Nullable String str2, @Nullable @Size(max = 23, min = 1) String str3) {
        return this.zzacv.h().a(str, str2, str3);
    }

    @Keep
    @Nullable
    public String getCurrentScreenClass() {
        dm C = this.zzacv.s().C();
        if (C != null) {
            return C.b;
        }
        return null;
    }

    @Keep
    @Nullable
    public String getCurrentScreenName() {
        dm C = this.zzacv.s().C();
        if (C != null) {
            return C.a;
        }
        return null;
    }

    @KeepForSdk
    public Double getDouble() {
        return this.zzacv.h().F();
    }

    @Keep
    @Nullable
    public String getGmpAppId() {
        if (this.zzacv.p() != null) {
            return this.zzacv.p();
        }
        try {
            return GoogleServices.getGoogleAppId();
        } catch (IllegalStateException e2) {
            this.zzacv.r().h_().a("getGoogleAppId failed with exception", e2);
            return null;
        }
    }

    @KeepForSdk
    public Integer getInteger() {
        return this.zzacv.h().E();
    }

    @KeepForSdk
    public Long getLong() {
        return this.zzacv.h().D();
    }

    @Keep
    @WorkerThread
    @KeepForSdk
    public int getMaxUserProperties(@Size(min = 1) @NonNull String str) {
        this.zzacv.h();
        Preconditions.checkNotEmpty(str);
        return 25;
    }

    @KeepForSdk
    public String getString() {
        return this.zzacv.h().C();
    }

    /* access modifiers changed from: protected */
    @Keep
    @WorkerThread
    @VisibleForTesting
    public Map<String, Object> getUserProperties(@Nullable String str, @Nullable @Size(max = 24, min = 1) String str2, boolean z) {
        return this.zzacv.h().a(str, str2, z);
    }

    @WorkerThread
    @KeepForSdk
    public Map<String, Object> getUserProperties(boolean z) {
        List<zzka> b2 = this.zzacv.h().b(z);
        ArrayMap arrayMap = new ArrayMap(b2.size());
        for (zzka zzka : b2) {
            arrayMap.put(zzka.name, zzka.getValue());
        }
        return arrayMap;
    }

    /* access modifiers changed from: protected */
    @Keep
    @WorkerThread
    @VisibleForTesting
    public Map<String, Object> getUserPropertiesAs(@Size(min = 1) @NonNull String str, @Nullable String str2, @Nullable @Size(max = 23, min = 1) String str3, boolean z) {
        return this.zzacv.h().a(str, str2, str3, z);
    }

    public final void logEvent(@Size(max = 40, min = 1) @NonNull String str, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.zzacv.h().a("app", str, bundle, true);
    }

    @Keep
    public void logEventInternal(String str, String str2, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.zzacv.h().a(str, str2, bundle);
    }

    @KeepForSdk
    public void logEventInternalNoInterceptor(String str, String str2, Bundle bundle, long j) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.zzacv.h().a(str, str2, bundle, j);
    }

    @KeepForSdk
    public void registerOnMeasurementEventListener(c cVar) {
        this.zzacv.h().a(cVar);
    }

    @Keep
    @KeepForSdk
    public void setConditionalUserProperty(@NonNull ConditionalUserProperty conditionalUserProperty) {
        this.zzacv.h().a(conditionalUserProperty);
    }

    /* access modifiers changed from: protected */
    @Keep
    @VisibleForTesting
    public void setConditionalUserPropertyAs(@NonNull ConditionalUserProperty conditionalUserProperty) {
        this.zzacv.h().b(conditionalUserProperty);
    }

    @WorkerThread
    @KeepForSdk
    public void setEventInterceptor(b bVar) {
        this.zzacv.h().a(bVar);
    }

    @Deprecated
    public void setMeasurementEnabled(boolean z) {
        this.zzacv.h().a(z);
    }

    public final void setMinimumSessionDuration(long j) {
        this.zzacv.h().a(j);
    }

    public final void setSessionTimeoutDuration(long j) {
        this.zzacv.h().b(j);
    }

    public final void setUserProperty(@Size(max = 24, min = 1) @NonNull String str, @Nullable @Size(max = 36) String str2) {
        int c2 = this.zzacv.k().c(str);
        if (c2 != 0) {
            this.zzacv.k();
            this.zzacv.k().a(c2, "_ev", fg.a(str, 24, true), str != null ? str.length() : 0);
            return;
        }
        setUserPropertyInternal("app", str, str2);
    }

    @KeepForSdk
    public void setUserPropertyInternal(String str, String str2, Object obj) {
        this.zzacv.h().a(str, str2, obj);
    }

    @KeepForSdk
    public void unregisterOnMeasurementEventListener(c cVar) {
        this.zzacv.h().b(cVar);
    }
}
