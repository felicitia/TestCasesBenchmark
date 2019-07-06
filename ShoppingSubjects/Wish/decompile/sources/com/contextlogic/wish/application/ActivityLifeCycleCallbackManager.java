package com.contextlogic.wish.application;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActivityLifeCycleCallbackManager {
    private static ActivityLifeCycleCallbackManager sInstance;
    private ActivityLifecycleCallbacks mCallbacks;
    private List<WeakReference<ActivityLifeCycleEventListener>> mListeners;

    public interface ActivityLifeCycleEventListener {
        void onActivityLifecycleEvent(EventType eventType, Activity activity, Bundle bundle);
    }

    public enum EventType {
        CREATED,
        STARTED,
        RESUMED,
        PAUSED,
        STOPPED,
        SAVED_INSTANCE_STATE,
        DESTROYED
    }

    public static ActivityLifeCycleCallbackManager getInstance() {
        if (sInstance == null) {
            sInstance = new ActivityLifeCycleCallbackManager();
        }
        return sInstance;
    }

    /* access modifiers changed from: 0000 */
    public void startMonitoring(Application application) {
        application.registerActivityLifecycleCallbacks(getCallbacks());
    }

    public void addActivityLifeCycleEventListener(ActivityLifeCycleEventListener activityLifeCycleEventListener) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList();
        }
        this.mListeners.add(new WeakReference(activityLifeCycleEventListener));
    }

    private ActivityLifecycleCallbacks getCallbacks() {
        if (this.mCallbacks == null) {
            this.mCallbacks = new ActivityLifecycleCallbacks() {
                public void onActivityCreated(Activity activity, Bundle bundle) {
                    ActivityLifeCycleCallbackManager.this.processEvent(EventType.CREATED, activity, bundle);
                }

                public void onActivityStarted(Activity activity) {
                    ActivityLifeCycleCallbackManager.this.processEvent(EventType.STARTED, activity, null);
                }

                public void onActivityResumed(Activity activity) {
                    ActivityLifeCycleCallbackManager.this.processEvent(EventType.RESUMED, activity, null);
                }

                public void onActivityPaused(Activity activity) {
                    ActivityLifeCycleCallbackManager.this.processEvent(EventType.PAUSED, activity, null);
                }

                public void onActivityStopped(Activity activity) {
                    ActivityLifeCycleCallbackManager.this.processEvent(EventType.STOPPED, activity, null);
                }

                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                    ActivityLifeCycleCallbackManager.this.processEvent(EventType.SAVED_INSTANCE_STATE, activity, bundle);
                }

                public void onActivityDestroyed(Activity activity) {
                    ActivityLifeCycleCallbackManager.this.processEvent(EventType.DESTROYED, activity, null);
                }
            };
        }
        return this.mCallbacks;
    }

    /* access modifiers changed from: private */
    public void processEvent(EventType eventType, Activity activity, Bundle bundle) {
        for (ActivityLifeCycleEventListener onActivityLifecycleEvent : getAliveListeners()) {
            onActivityLifecycleEvent.onActivityLifecycleEvent(eventType, activity, bundle);
        }
    }

    private List<ActivityLifeCycleEventListener> getAliveListeners() {
        if (this.mListeners == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (WeakReference weakReference : this.mListeners) {
            if (weakReference.get() != null) {
                arrayList.add(weakReference.get());
            } else {
                arrayList2.add(weakReference);
            }
        }
        this.mListeners.removeAll(arrayList2);
        return arrayList;
    }
}
