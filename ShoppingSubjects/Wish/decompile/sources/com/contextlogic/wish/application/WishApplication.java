package com.contextlogic.wish.application;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Process;
import android.support.multidex.MultiDexApplication;
import android.telephony.TelephonyManager;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.AuthenticationDataCenter;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.application.ApplicationEventManager.ApplicationEventBundle;
import com.contextlogic.wish.application.ApplicationEventManager.ApplicationEventCallback;
import com.contextlogic.wish.application.ApplicationEventManager.EventType;
import com.contextlogic.wish.cache.StateStoreCache;
import com.contextlogic.wish.http.ImageHttpCache;
import com.contextlogic.wish.notifications.local.LocalNotificationManager;
import com.contextlogic.wish.payments.ThreatMetrix.ThreatMetrixManager;
import com.contextlogic.wish.payments.riskified.RiskifiedManager;
import com.contextlogic.wish.util.AppVersionUtil;
import com.contextlogic.wish.util.NotificationUtil;
import com.contextlogic.wish.util.PreferenceUtil;
import com.contextlogic.wish.util.ScreenshotUtil;
import com.crashlytics.android.Crashlytics.Builder;
import com.crashlytics.android.core.CrashlyticsCore;
import com.facebook.FacebookSdk;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.wish.android.shakedelegate.DeviceInfoShakeDelegateWrapper;
import com.wish.android.shakedelegate.DeviceInfoShakeDelegateWrapper.DeviceInfo;
import com.wish.android.shakedelegate.LogCatShakeDelegateWrapper;
import com.wish.android.shakedelegate.PhabShakeDelegate;
import com.wish.android.shaky.EmailShakeDelegate;
import com.wish.android.shaky.Shaky;
import io.fabric.sdk.android.Fabric;

public class WishApplication extends MultiDexApplication {
    private static WishApplication sInstance;
    private ApplicationEventCallback mExperimentDataCenterUpdatedCallback;
    /* access modifiers changed from: private */
    public boolean mHasSetupShakeToFeedback;
    private boolean mIsFirstFeedOpen;
    private boolean mIsFirstLoginSession;
    private RefWatcher mRefWatcher;

    public void onCreate() {
        super.onCreate();
        sInstance = this;
        this.mRefWatcher = LeakCanary.install(this);
        NotificationUtil.createNotificationChannel(this);
        try {
            Class.forName("android.os.AsyncTask");
        } catch (ClassNotFoundException unused) {
        }
        AppVersionUtil.markAppVersion();
        setupDataCenterUpdatedCallback();
        if (isActivityProcess()) {
            FacebookSdk.sdkInitialize(getApplicationContext());
            ImageHttpCache.getInstance().initialize();
            LocalNotificationManager.getInstance().initialize();
            ApiGuardManager.getInstance().initialize();
            MainFeedDefaultManager.getInstance().initialize();
            ApplicationPinger.sendServerPing(null, null, ((TelephonyManager) getBaseContext().getSystemService("phone")).getNetworkOperatorName());
            ApplicationPinger.sendAdvertisingPing();
            new AsyncTask<Void, Void, Void>() {
                /* access modifiers changed from: protected */
                public Void doInBackground(Void... voidArr) {
                    WishApplication.this.performAsyncInitializationTasks();
                    return null;
                }
            }.execute(new Void[0]);
        }
        this.mIsFirstLoginSession = PreferenceUtil.getString("LoggedInUser") == null;
        if (this.mIsFirstLoginSession) {
            WishAnalyticsLogger.trackEventOnce(WishAnalyticsEvent.IMPRESSION_FIRST_OPEN_APP);
        }
        PreferenceUtil.setBoolean("HideNotificationRedDot", false);
        this.mIsFirstFeedOpen = true;
    }

    private void setupDataCenterUpdatedCallback() {
        this.mExperimentDataCenterUpdatedCallback = new ApplicationEventCallback() {
            public void onApplicationEventReceived(EventType eventType, String str, ApplicationEventBundle applicationEventBundle) {
                if (WishApplication.this.mHasSetupShakeToFeedback) {
                    return;
                }
                if (ExperimentDataCenter.getInstance().showShakeToFeedback() || ProfileDataCenter.getInstance().isAdmin()) {
                    Shaky.with(WishApplication.this, new DeviceInfoShakeDelegateWrapper(new LogCatShakeDelegateWrapper(new PhabShakeDelegate(new EmailShakeDelegate(new String[]{"product-feedback-android@wish.com", "phab@wish.com"}).setSensitivityLevel(22)))).setDeviceInfo(WishApplication.this.buildDeviceInfo()));
                    WishApplication.this.mHasSetupShakeToFeedback = true;
                }
            }
        };
        ApplicationEventManager.getInstance().addCallback(EventType.DATA_CENTER_UPDATED, ExperimentDataCenter.getInstance().getClass().toString(), this.mExperimentDataCenterUpdatedCallback);
    }

    /* access modifiers changed from: private */
    public DeviceInfo buildDeviceInfo() {
        DeviceInfo deviceInfo = new DeviceInfo("com.contextlogic.wish", "release", 778, "4.22.6", "wish", false, AuthenticationDataCenter.getInstance().getUserId(), ProfileDataCenter.getInstance().getEmail());
        return deviceInfo;
    }

    /* access modifiers changed from: private */
    public void performAsyncInitializationTasks() {
        Fabric.with(this, new Builder().core(new CrashlyticsCore.Builder().disabled(false).build()).build());
        ScreenshotUtil.cleanupScreenshotDirectory();
        RiskifiedManager.getInstance().startBeacon();
        ThreatMetrixManager.getInstance().startProfiling();
        ActivityLifeCycleCallbackManager.getInstance().startMonitoring(this);
    }

    public RefWatcher getRefWatcher() {
        return this.mRefWatcher;
    }

    public void onLowMemory() {
        super.onLowMemory();
        StateStoreCache.getInstance().clearCache();
    }

    public static WishApplication getInstance() {
        return sInstance;
    }

    public static String getName() {
        return getInstance().getString(R.string.app_name);
    }

    public static String getAppType() {
        return getInstance().getString(R.string.app_type);
    }

    public static String getDeepLinkProtocol() {
        return getInstance().getString(R.string.deep_link_protocol);
    }

    public boolean isWishApp() {
        return getAppType().equals("wish");
    }

    public String getVersionNumber() {
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (NameNotFoundException unused) {
            return null;
        }
    }

    public boolean isFirstLoginSession() {
        return this.mIsFirstLoginSession;
    }

    private boolean isActivityProcess() {
        try {
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == Process.myPid() && getPackageName().equals(runningAppProcessInfo.processName)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    public boolean isFirstFeedOpen() {
        return this.mIsFirstFeedOpen;
    }

    public void setIsFirstFeedOpen(boolean z) {
        this.mIsFirstFeedOpen = z;
    }
}
