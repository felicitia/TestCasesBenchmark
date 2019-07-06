package com.contextlogic.wish.ui.timer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import com.contextlogic.wish.R;
import com.contextlogic.wish.util.DateUtil;
import com.contextlogic.wish.util.DateUtil.TimeParts;
import com.contextlogic.wish.util.DateUtil.TimeUnit;
import com.contextlogic.wish.util.FontUtil;
import java.util.Date;

public class TimerTextView extends AppCompatTextView {
    private final String STRING_EXPIRED;
    private boolean mAttached;
    private HandlerThread mHandlerThread;
    protected Handler mOffUiHandler;
    /* access modifiers changed from: private */
    public boolean mPaused;
    protected long mPeriod;
    private boolean mShowHours;
    private Date mTargetDate;
    protected final Runnable mTicker;
    protected TimeParts mTimeParts;
    /* access modifiers changed from: private */
    public TimerWatcher mWatcher;
    protected float mWidth;

    public interface TimerWatcher {
        long getUpdatePeriod();

        void onCount(long j);

        void onCountdownComplete();
    }

    public static abstract class TimerWatcherAdapter implements TimerWatcher {
        public long getUpdatePeriod() {
            return 1000;
        }

        public void onCount(long j) {
        }

        public void onCountdownComplete() {
        }
    }

    public TimerTextView(Context context) {
        this(context, null);
    }

    public TimerTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TimerTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPeriod = 1000;
        this.mTicker = new Runnable() {
            public void run() {
                TimerTextView.this.updateTime();
                if (TimerTextView.this.mOffUiHandler != null && !TimerTextView.this.mPaused) {
                    TimerTextView.this.mOffUiHandler.postDelayed(TimerTextView.this.mTicker, TimerTextView.this.mPeriod);
                }
            }
        };
        this.STRING_EXPIRED = context.getString(R.string.expired);
        setGravity(17);
        setLines(1);
        Typeface typefaceForStyle = FontUtil.getTypefaceForStyle(getTypeface() != null ? getTypeface().getStyle() : 0);
        if (typefaceForStyle != null) {
            setTypeface(typefaceForStyle);
        }
    }

    public void setup(Date date, TimerWatcher timerWatcher) {
        this.mTargetDate = date;
        this.mTimeParts = DateUtil.getTimeDifferenceFromDate(this.mTargetDate, new Date(), this.mTimeParts, TimeUnit.HOUR);
        setup(date, timerWatcher, this.mTimeParts.hours > 0);
    }

    public void setup(Date date, TimerWatcher timerWatcher, boolean z) {
        int i = 0;
        this.mPaused = false;
        this.mTargetDate = date;
        this.mWatcher = timerWatcher;
        this.mTimeParts = DateUtil.getTimeDifferenceFromDate(this.mTargetDate, new Date(), this.mTimeParts, TimeUnit.HOUR);
        this.mShowHours = z;
        if (timerWatcher != null) {
            this.mPeriod = timerWatcher.getUpdatePeriod();
        }
        if (getTypeface() != null) {
            i = getTypeface().getStyle();
        }
        setTypeface(FontUtil.getTypefaceForStyle(i), i);
        updateTime();
        startTick();
    }

    /* access modifiers changed from: protected */
    public Handler getOffUiHandler() {
        if (this.mOffUiHandler == null) {
            this.mHandlerThread = new HandlerThread("TimerHandlerThread");
            this.mHandlerThread.start();
            this.mOffUiHandler = new Handler(this.mHandlerThread.getLooper());
        }
        return this.mOffUiHandler;
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"DefaultLocale"})
    public void updateTime() {
        final String str;
        if (this.mTargetDate != null && this.mAttached) {
            Date date = new Date();
            this.mTimeParts = DateUtil.getTimeDifferenceFromDate(this.mTargetDate, date, this.mTimeParts, TimeUnit.HOUR);
            final boolean isExpired = this.mTimeParts.isExpired();
            if (this.mWatcher != null) {
                this.mWatcher.onCount(this.mTargetDate.getTime() - date.getTime());
            }
            final float f = 0.0f;
            if (isExpired) {
                str = this.STRING_EXPIRED;
                if (getPaint() != null) {
                    f = getPaint().measureText(str);
                }
            } else if (this.mShowHours) {
                str = String.format("%02d:%02d:%02d", new Object[]{Long.valueOf(this.mTimeParts.hours), Long.valueOf(this.mTimeParts.minutes), Long.valueOf(this.mTimeParts.seconds)});
                if (getPaint() != null) {
                    f = getPaint().measureText(String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)}));
                }
            } else {
                str = String.format("%02d:%02d", new Object[]{Long.valueOf(this.mTimeParts.minutes + (this.mTimeParts.hours * 60)), Long.valueOf(this.mTimeParts.seconds)});
                if (getPaint() != null) {
                    f = getPaint().measureText(String.format("%02d:%02d", new Object[]{Integer.valueOf(0), Integer.valueOf(0)}));
                }
            }
            post(new Runnable() {
                public void run() {
                    if (f != TimerTextView.this.mWidth) {
                        TimerTextView.this.internalFixSize((int) f);
                    }
                    TimerTextView.this.setText(str);
                    if (isExpired) {
                        TimerTextView.this.quitHandlerThread();
                        if (TimerTextView.this.mWatcher != null) {
                            TimerWatcher access$200 = TimerTextView.this.mWatcher;
                            TimerTextView.this.mWatcher = null;
                            access$200.onCountdownComplete();
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: 0000 */
    public void internalFixSize(int i) {
        if (getLayoutParams() != null && getLayoutParams().width == -2) {
            this.mWidth = (float) i;
            int paddingLeft = i + getPaddingLeft() + getPaddingRight();
            setMinWidth(paddingLeft);
            setMaxWidth(paddingLeft);
        }
    }

    private void startTick() {
        if (this.mAttached && this.mOffUiHandler == null) {
            getOffUiHandler().post(this.mTicker);
        }
    }

    public void setIsPaused(boolean z) {
        this.mPaused = z;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!this.mAttached) {
            this.mAttached = true;
            startTick();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mAttached) {
            quitHandlerThread();
            this.mAttached = false;
        }
    }

    /* access modifiers changed from: private */
    public void quitHandlerThread() {
        if (this.mOffUiHandler != null) {
            this.mOffUiHandler.removeCallbacks(this.mTicker);
        }
        if (this.mHandlerThread != null) {
            this.mHandlerThread.quit();
            this.mHandlerThread = null;
            this.mOffUiHandler = null;
        }
    }

    public void safeSetLetterSpacing(float f) {
        if (VERSION.SDK_INT >= 21) {
            setLetterSpacing(f);
        }
    }
}
