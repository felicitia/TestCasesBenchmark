package com.contextlogic.wish.ui.timer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.contextlogic.wish.util.DateUtil;
import com.contextlogic.wish.util.DateUtil.TimeParts;
import com.contextlogic.wish.util.DateUtil.TimeUnit;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public abstract class BaseCountdownTimerView extends LinearLayout {
    private DoneCallback mCallback;
    private CountCallback mCountCallback;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    private Date mTargetDate;
    protected TimeParts mTimeParts;
    private Timer mTimer;
    /* access modifiers changed from: private */
    public boolean mUpdatePosted;
    /* access modifiers changed from: private */
    public Runnable mUpdateRunnable;

    public interface CountCallback {
        void onCount(int i);
    }

    public interface DoneCallback {
        void onCountdownEnd();
    }

    /* access modifiers changed from: protected */
    public abstract void updateTimeUi(boolean z);

    public BaseCountdownTimerView(Context context) {
        super(context);
    }

    public BaseCountdownTimerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean isSetup() {
        return this.mTargetDate != null;
    }

    public void startTimer() {
        if (this.mTimer == null) {
            if (this.mUpdateRunnable == null) {
                this.mUpdateRunnable = new Runnable() {
                    public void run() {
                        synchronized (BaseCountdownTimerView.this.mLock) {
                            BaseCountdownTimerView.this.updateTime();
                            BaseCountdownTimerView.this.mUpdatePosted = false;
                        }
                    }
                };
            }
            AnonymousClass2 r2 = new TimerTask() {
                public void run() {
                    synchronized (BaseCountdownTimerView.this.mLock) {
                        if (!BaseCountdownTimerView.this.mUpdatePosted && BaseCountdownTimerView.this.getWindowVisibility() != 8) {
                            BaseCountdownTimerView.this.mUpdatePosted = BaseCountdownTimerView.this.post(BaseCountdownTimerView.this.mUpdateRunnable);
                        }
                    }
                }
            };
            this.mTimer = new Timer();
            this.mTimer.schedule(r2, 0, 250);
            updateTime();
        }
    }

    public void pauseTimer() {
        if (this.mTimer != null) {
            this.mTimer.cancel();
            this.mTimer = null;
            this.mUpdatePosted = false;
        }
    }

    public void setCountCallback(CountCallback countCallback) {
        this.mCountCallback = countCallback;
    }

    /* access modifiers changed from: private */
    public void updateTime() {
        if (isSetup()) {
            if (this.mTimeParts == null) {
                this.mTimeParts = new TimeParts();
            }
            this.mTimeParts = DateUtil.getTimeDifferenceFromDate(this.mTargetDate, new Date(), this.mTimeParts, getMaxTimeUnit());
            boolean isExpired = this.mTimeParts.isExpired();
            if (this.mCountCallback != null) {
                this.mCountCallback.onCount((int) ((this.mTimeParts.hours * 3600) + (this.mTimeParts.minutes * 60) + this.mTimeParts.seconds));
            }
            updateTimeUi(isExpired);
            if (isExpired && this.mCallback != null) {
                DoneCallback doneCallback = this.mCallback;
                this.mCallback = null;
                doneCallback.onCountdownEnd();
            }
        }
    }

    public void setup(Date date, DoneCallback doneCallback) {
        this.mTargetDate = date;
        this.mCallback = doneCallback;
        if (this.mTimeParts == null) {
            this.mTimeParts = new TimeParts();
        }
        this.mTimeParts = DateUtil.getTimeDifferenceFromDate(this.mTargetDate, new Date(), this.mTimeParts, getMaxTimeUnit());
    }

    /* access modifiers changed from: protected */
    public TimeUnit getMaxTimeUnit() {
        return TimeUnit.HOUR;
    }
}
