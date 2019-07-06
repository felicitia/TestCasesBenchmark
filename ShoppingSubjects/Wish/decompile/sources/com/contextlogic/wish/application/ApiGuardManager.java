package com.contextlogic.wish.application;

import android.app.Activity;
import android.os.Bundle;
import com.apiguard.AGCallbackInterface;
import com.apiguard.APIGuard;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.application.ActivityLifeCycleCallbackManager.ActivityLifeCycleEventListener;
import com.contextlogic.wish.application.ActivityLifeCycleCallbackManager.EventType;
import com.crashlytics.android.Crashlytics;
import java.io.IOException;
import java.security.cert.Certificate;
import java.util.HashMap;
import java.util.List;

public class ApiGuardManager implements AGCallbackInterface, ActivityLifeCycleEventListener {
    private static ApiGuardManager sInstance = new ApiGuardManager();
    private String mAidKey;
    private APIGuard mApiGuard = new APIGuard();
    private boolean mInitialized = false;

    public void checkCertificates(List<Certificate> list, String str) throws IOException {
    }

    public void logAGMessage(String str) {
    }

    public void reauthenticate() {
    }

    public static ApiGuardManager getInstance() {
        return sInstance;
    }

    public void initialize() {
        ActivityLifeCycleCallbackManager.getInstance().addActivityLifeCycleEventListener(this);
    }

    public void onActivityLifecycleEvent(EventType eventType, Activity activity, Bundle bundle) {
        if (eventType == EventType.CREATED && ExperimentDataCenter.getInstance().shouldUseApiGuard()) {
            String aidKey = getAidKey(activity);
            if (aidKey != null) {
                initApiGuard(aidKey);
                this.mInitialized = true;
                return;
            }
            this.mInitialized = false;
        }
    }

    private String getAidKey(Activity activity) {
        if (this.mAidKey == null) {
            try {
                this.mAidKey = activity.getApplicationInfo().metaData.getString("shape_aid");
            } catch (NullPointerException e) {
                Crashlytics.logException(e);
            }
            if (this.mAidKey == null) {
                Crashlytics.logException(new Exception("shape_aid not found in application meta data!"));
            }
        }
        return this.mAidKey;
    }

    public boolean canUseApiGuard() {
        return this.mInitialized;
    }

    public APIGuard getApiGuard() {
        return this.mApiGuard;
    }

    private void initApiGuard(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("UPDATEINTERVAL", Long.valueOf(14400));
        hashMap.put("KEYCACHETTL", Long.valueOf(1209600));
        hashMap.put("INTEGRITYCHECKURL", "https://main.cdn.wish.com/b3a4fdfb29f93b4730f39f9a88853205f7f41f8c5c5e242e94108446/v1/native-app/initialize");
        hashMap.put("GUARDEDDOMAINS", new String[]{"wish.com", ".wish.com", ".contextlogic.com"});
        hashMap.put("AID", str);
        this.mApiGuard.initializeWithProperties(WishApplication.getInstance(), hashMap, this);
    }
}
