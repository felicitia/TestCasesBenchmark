package com.contextlogic.wish.application;

import android.os.Handler;
import android.os.Looper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.WeakHashMap;

public class ApplicationEventManager {
    private static ApplicationEventManager sInstance = new ApplicationEventManager();
    private HashMap<EventCallbackWrapper, ArrayList<String>> mCallbackToEventMapping = new HashMap<>();
    private HashMap<String, ArrayList<EventCallbackWrapper>> mEventToCallbackMapping = new HashMap<>();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private WeakHashMap<ApplicationEventCallback, EventCallbackWrapper> mWrappedCallbackMapping = new WeakHashMap<>();

    public static class ApplicationEventBundle {
    }

    public interface ApplicationEventCallback {
        void onApplicationEventReceived(EventType eventType, String str, ApplicationEventBundle applicationEventBundle);
    }

    private static class EventCallbackWrapper implements ApplicationEventCallback {
        private WeakReference<ApplicationEventCallback> mCallbackWeakReference;

        public EventCallbackWrapper(ApplicationEventCallback applicationEventCallback) {
            this.mCallbackWeakReference = new WeakReference<>(applicationEventCallback);
        }

        public ApplicationEventCallback getCallback() {
            if (this.mCallbackWeakReference != null) {
                return (ApplicationEventCallback) this.mCallbackWeakReference.get();
            }
            return null;
        }

        public void onApplicationEventReceived(EventType eventType, String str, ApplicationEventBundle applicationEventBundle) {
            ApplicationEventCallback callback = getCallback();
            if (callback == null) {
                ApplicationEventManager.getInstance().removeWrappedCallback(null, null, this);
                this.mCallbackWeakReference = null;
                return;
            }
            callback.onApplicationEventReceived(eventType, str, applicationEventBundle);
        }
    }

    public enum EventType {
        LOGOUT_REQUIRED,
        DATA_CENTER_UPDATED,
        PRODUCT_WISH,
        PRODUCT_UNWISH,
        USER_FOLLOW,
        USER_UNFOLLOW,
        BADGE_SECTION_VIEWED,
        DAILY_GIVEAWAY_SPLASH_NOTIFICATION
    }

    private ApplicationEventManager() {
    }

    public static ApplicationEventManager getInstance() {
        return sInstance;
    }

    public void addCallback(EventType eventType, ApplicationEventCallback applicationEventCallback) {
        addCallback(eventType, null, applicationEventCallback);
    }

    public void addCallback(EventType eventType, String str, ApplicationEventCallback applicationEventCallback) {
        addCallback(buildEventKey(eventType, null), applicationEventCallback);
        if (str != null) {
            addCallback(buildEventKey(eventType, str), applicationEventCallback);
        }
    }

    private void addCallback(String str, ApplicationEventCallback applicationEventCallback) {
        if (str == null) {
            throw new IllegalArgumentException("Event key must not be null");
        }
        synchronized (this.mEventToCallbackMapping) {
            EventCallbackWrapper eventCallbackWrapper = (EventCallbackWrapper) this.mWrappedCallbackMapping.get(applicationEventCallback);
            if (eventCallbackWrapper == null) {
                eventCallbackWrapper = new EventCallbackWrapper(applicationEventCallback);
                this.mWrappedCallbackMapping.put(applicationEventCallback, eventCallbackWrapper);
            }
            ArrayList arrayList = (ArrayList) this.mCallbackToEventMapping.get(eventCallbackWrapper);
            if (arrayList == null) {
                arrayList = new ArrayList();
                this.mCallbackToEventMapping.put(eventCallbackWrapper, arrayList);
            }
            if (!arrayList.contains(str)) {
                arrayList.add(str);
                ArrayList arrayList2 = (ArrayList) this.mEventToCallbackMapping.get(str);
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList();
                    this.mEventToCallbackMapping.put(str, arrayList2);
                }
                arrayList2.add(eventCallbackWrapper);
            }
        }
    }

    public void removeCallback(ApplicationEventCallback applicationEventCallback) {
        removeCallback(null, null, applicationEventCallback);
    }

    public void removeCallback(EventType eventType, String str, ApplicationEventCallback applicationEventCallback) {
        removeWrappedCallback(eventType, str, null);
    }

    /* access modifiers changed from: private */
    public void removeWrappedCallback(EventType eventType, String str, EventCallbackWrapper eventCallbackWrapper) {
        removeWrappedCallback(buildEventKey(eventType, null), eventCallbackWrapper);
        if (str != null) {
            removeWrappedCallback(buildEventKey(eventType, str), eventCallbackWrapper);
        }
    }

    private void removeWrappedCallback(String str, EventCallbackWrapper eventCallbackWrapper) {
        synchronized (this.mEventToCallbackMapping) {
            ArrayList arrayList = (ArrayList) this.mCallbackToEventMapping.get(eventCallbackWrapper);
            if (arrayList != null) {
                if (str == null) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        String str2 = (String) it.next();
                        ArrayList arrayList2 = (ArrayList) this.mEventToCallbackMapping.get(str);
                        if (arrayList2 != null) {
                            arrayList2.remove(eventCallbackWrapper);
                        }
                    }
                    this.mEventToCallbackMapping.remove(eventCallbackWrapper);
                } else {
                    ArrayList arrayList3 = (ArrayList) this.mEventToCallbackMapping.get(str);
                    if (arrayList3 != null) {
                        arrayList3.remove(eventCallbackWrapper);
                    }
                    arrayList.remove(str);
                }
            }
        }
    }

    public void triggerEvent(EventType eventType, String str, ApplicationEventBundle applicationEventBundle) {
        triggerEvent(buildEventKey(eventType, null), eventType, str, applicationEventBundle);
        if (str != null) {
            triggerEvent(buildEventKey(eventType, str), eventType, str, applicationEventBundle);
        }
    }

    private void triggerEvent(String str, EventType eventType, String str2, ApplicationEventBundle applicationEventBundle) {
        if (applicationEventBundle == null) {
            applicationEventBundle = new ApplicationEventBundle();
        }
        if (str == null) {
            throw new IllegalArgumentException("Event key must not be null");
        }
        synchronized (this.mEventToCallbackMapping) {
            ArrayList arrayList = (ArrayList) this.mEventToCallbackMapping.get(str);
            if (arrayList != null) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    final EventCallbackWrapper eventCallbackWrapper = (EventCallbackWrapper) it.next();
                    Handler handler = this.mHandler;
                    final EventType eventType2 = eventType;
                    final String str3 = str2;
                    final ApplicationEventBundle applicationEventBundle2 = applicationEventBundle;
                    AnonymousClass1 r0 = new Runnable() {
                        public void run() {
                            eventCallbackWrapper.onApplicationEventReceived(eventType2, str3, applicationEventBundle2);
                        }
                    };
                    handler.post(r0);
                }
            }
        }
    }

    private String buildEventKey(EventType eventType, String str) {
        if (eventType == null) {
            return null;
        }
        if (str == null) {
            return eventType.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(eventType.toString());
        sb.append("%$%");
        sb.append(str);
        return sb.toString();
    }
}
