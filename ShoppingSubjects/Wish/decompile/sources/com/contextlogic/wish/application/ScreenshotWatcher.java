package com.contextlogic.wish.application;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.contextlogic.wish.application.ForegroundWatcher.ForegroundListener;
import com.crashlytics.android.Crashlytics;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ScreenshotWatcher implements ForegroundListener {
    private static ScreenshotWatcher sInstance = new ScreenshotWatcher();
    /* access modifiers changed from: private */
    public Handler mHandler;
    private HandlerThread mHandlerThread = new HandlerThread(getClass().getCanonicalName());
    /* access modifiers changed from: private */
    public boolean mIsPaused;
    /* access modifiers changed from: private */
    public boolean mIsTakingScreenshot = false;
    /* access modifiers changed from: private */
    public List<ScreenshotListener> mListeners;
    private Handler mMainLooperHandler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public Runnable mScreenshotPoller;

    public interface ScreenshotListener {
        void onScreenshotTaken();
    }

    public void onLoggedInForeground() {
    }

    private ScreenshotWatcher() {
        this.mHandlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        this.mScreenshotPoller = null;
        this.mListeners = new CopyOnWriteArrayList();
        this.mIsPaused = true;
        ForegroundWatcher.getInstance().addListener(this);
        if (ForegroundWatcher.getInstance().isForeground()) {
            onForeground();
        }
    }

    public static ScreenshotWatcher getInstance() {
        return sInstance;
    }

    public void addListener(ScreenshotListener screenshotListener) {
        if (!this.mListeners.contains(screenshotListener)) {
            this.mListeners.add(screenshotListener);
        }
    }

    public void removeListener(ScreenshotListener screenshotListener) {
        this.mListeners.remove(screenshotListener);
    }

    public void onForeground() {
        if (this.mIsPaused) {
            this.mIsPaused = false;
            this.mScreenshotPoller = new Runnable() {
                public void run() {
                    if (!ScreenshotWatcher.this.mIsPaused && ScreenshotWatcher.this.mScreenshotPoller == this) {
                        try {
                            boolean z = false;
                            Iterator it = ((ActivityManager) WishApplication.getInstance().getSystemService("activity")).getRunningServices(200).iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    if (((RunningServiceInfo) it.next()).process.equals("com.android.systemui:screenshot")) {
                                        z = true;
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            }
                            boolean access$200 = ScreenshotWatcher.this.mIsTakingScreenshot;
                            ScreenshotWatcher.this.mIsTakingScreenshot = z;
                            if (!access$200 && z) {
                                ScreenshotWatcher.this.alertListenersOnScreenshotTaken();
                            }
                            ScreenshotWatcher.this.mHandler.postDelayed(ScreenshotWatcher.this.mScreenshotPoller, 1500);
                        } catch (Throwable th) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("com.android.systemui:screenshot");
                            sb.append(th.getMessage());
                            sb.append(th.toString());
                            Crashlytics.logException(new Exception(sb.toString()));
                        }
                    }
                }
            };
            this.mHandler.postDelayed(this.mScreenshotPoller, 1500);
        }
    }

    public void onBackground() {
        this.mIsPaused = true;
        if (this.mHandler != null && this.mScreenshotPoller != null) {
            this.mHandler.removeCallbacks(this.mScreenshotPoller);
            this.mScreenshotPoller = null;
        }
    }

    /* access modifiers changed from: private */
    public void alertListenersOnScreenshotTaken() {
        this.mMainLooperHandler.post(new Runnable() {
            public void run() {
                for (ScreenshotListener onScreenshotTaken : ScreenshotWatcher.this.mListeners) {
                    try {
                        onScreenshotTaken.onScreenshotTaken();
                    } catch (Throwable unused) {
                    }
                }
            }
        });
    }
}
