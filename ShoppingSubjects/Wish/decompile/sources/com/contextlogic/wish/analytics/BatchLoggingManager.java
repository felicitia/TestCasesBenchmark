package com.contextlogic.wish.analytics;

import com.contextlogic.wish.api.service.standalone.BatchLogService;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BatchLoggingManager {
    private static final BatchLoggingManager sInstance = new BatchLoggingManager();
    private final Gson mGson = new Gson();
    private final Runnable mLogRunnable = new Runnable() {
        public void run() {
            BatchLoggingManager.this.processQueuedEvents();
        }
    };
    private final List<HashMap<String, String>> mPendingEvents = new ArrayList();
    private final ScheduledExecutorService mScheduler = Executors.newScheduledThreadPool(1);

    public static BatchLoggingManager getInstance() {
        return sInstance;
    }

    private void addEvent(int i, String str, HashMap<String, String> hashMap) {
        HashMap hashMap2 = new HashMap();
        hashMap2.put("event_id", String.valueOf(i));
        if (str != null) {
            hashMap2.put("contest_id", str);
        }
        if (hashMap != null) {
            hashMap2.put("extra_info", this.mGson.toJson((Object) hashMap));
        }
        this.mPendingEvents.add(hashMap2);
    }

    public synchronized void trackEvent(int i, String str, HashMap<String, String> hashMap) {
        boolean isEmpty = this.mPendingEvents.isEmpty();
        addEvent(i, str, hashMap);
        if (isEmpty) {
            this.mScheduler.schedule(this.mLogRunnable, 1, TimeUnit.SECONDS);
        }
    }

    /* access modifiers changed from: private */
    public synchronized void processQueuedEvents() {
        new BatchLogService().requestService(this.mPendingEvents, null, null);
        this.mPendingEvents.clear();
    }
}
