package com.contextlogic.wish.application;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import com.contextlogic.wish.social.facebook.FacebookManager;
import com.contextlogic.wish.util.NotificationUtil;
import com.contextlogic.wish.util.PreferenceUtil;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ForegroundWatcher {
    private static ForegroundWatcher sInstance = new ForegroundWatcher();
    private Runnable mForegroundPoller = null;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public boolean mIsForeground = false;
    /* access modifiers changed from: private */
    public boolean mIsLoggedInForeground;
    /* access modifiers changed from: private */
    public boolean mIsPaused = false;
    private List<ForegroundListener> mListeners = new CopyOnWriteArrayList();

    public interface ForegroundListener {
        void onBackground();

        void onForeground();

        void onLoggedInForeground();
    }

    private ForegroundWatcher() {
    }

    public static ForegroundWatcher getInstance() {
        return sInstance;
    }

    public void addListener(ForegroundListener foregroundListener) {
        this.mListeners.add(foregroundListener);
    }

    public void removeListener(ForegroundListener foregroundListener) {
        this.mListeners.remove(foregroundListener);
    }

    public boolean isForeground() {
        return this.mIsForeground;
    }

    public void onActivityResumed(Activity activity) {
        this.mIsPaused = false;
        boolean z = !this.mIsForeground;
        this.mIsForeground = true;
        if (this.mForegroundPoller != null) {
            this.mHandler.removeCallbacks(this.mForegroundPoller);
            this.mForegroundPoller = null;
        }
        if (z) {
            NotificationUtil.clearAllLocalNotifications();
            alertListenersOnForeground();
            new AsyncTask<Void, Void, Void>() {
                /* access modifiers changed from: protected */
                public Void doInBackground(Void... voidArr) {
                    FacebookManager.getInstance().trackResume();
                    return null;
                }
            }.execute(new Void[0]);
        }
    }

    public void onActivityLoggedInResumed(Activity activity) {
        boolean z = !this.mIsLoggedInForeground;
        this.mIsLoggedInForeground = true;
        if (z) {
            alertListenersOnLoggedInForeground();
        }
    }

    public void onActivityLogout(Activity activity) {
        this.mIsLoggedInForeground = false;
    }

    public void onActivityPaused(Activity activity) {
        this.mIsPaused = true;
        if (this.mForegroundPoller != null) {
            this.mHandler.removeCallbacks(this.mForegroundPoller);
            this.mForegroundPoller = null;
        }
        this.mForegroundPoller = new Runnable() {
            public void run() {
                if (ForegroundWatcher.this.mIsForeground && ForegroundWatcher.this.mIsPaused) {
                    ForegroundWatcher.this.mIsForeground = false;
                    ForegroundWatcher.this.mIsLoggedInForeground = false;
                    ForegroundWatcher.this.alertListenersOnBackground();
                }
            }
        };
        this.mHandler.postDelayed(this.mForegroundPoller, 500);
    }

    private void alertListenersOnForeground() {
        for (ForegroundListener onForeground : this.mListeners) {
            try {
                onForeground.onForeground();
            } catch (Throwable unused) {
            }
        }
    }

    private void alertListenersOnLoggedInForeground() {
        PreferenceUtil.setLong("AppForegroundCount", getGlobalForegroundCount() + 1);
        for (ForegroundListener onLoggedInForeground : this.mListeners) {
            try {
                onLoggedInForeground.onLoggedInForeground();
            } catch (Throwable unused) {
            }
        }
    }

    /* access modifiers changed from: private */
    public void alertListenersOnBackground() {
        for (ForegroundListener onBackground : this.mListeners) {
            try {
                onBackground.onBackground();
            } catch (Throwable unused) {
            }
        }
    }

    public long getGlobalForegroundCount() {
        return PreferenceUtil.getLong("AppForegroundCount", 0);
    }
}
