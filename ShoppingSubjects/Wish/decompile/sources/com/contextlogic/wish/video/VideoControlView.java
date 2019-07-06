package com.contextlogic.wish.video;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Player.DefaultEventListener;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.Timeline.Window;
import java.util.Formatter;
import java.util.Locale;

public class VideoControlView extends FrameLayout {
    public static final SeekDispatcher DEFAULT_SEEK_DISPATCHER = new SeekDispatcher() {
        public boolean dispatchSeek(ExoPlayer exoPlayer, int i, long j) {
            exoPlayer.seekTo(i, j);
            return true;
        }
    };
    private final ComponentListener mComponentListener;
    private final Window mCurrentWindow;
    /* access modifiers changed from: private */
    public boolean mDragging;
    private final TextView mDurationView;
    private final StringBuilder mFormatBuilder;
    private final Formatter mFormatter;
    /* access modifiers changed from: private */
    public final Runnable mHideAction;
    private long mHideAtMs;
    private boolean mIsAttachedToWindow;
    /* access modifiers changed from: private */
    public final View mPauseButton;
    /* access modifiers changed from: private */
    public final View mPlayButton;
    /* access modifiers changed from: private */
    public SimpleExoPlayer mPlayer;
    /* access modifiers changed from: private */
    public final TextView mPositionView;
    private final SeekBar mProgressBar;
    private SeekDispatcher mSeekDispatcher;
    private final View mShadowView;
    private int mShowTimeoutMs;
    /* access modifiers changed from: private */
    public final AutoReleasableImageView mSoundButton;
    /* access modifiers changed from: private */
    public VideoPlayerStateChangedListener mStateChangedListener;
    private final View mTimeView;
    private final Runnable mUpdateProgressAction;
    private VisibilityListener mVisibilityListener;

    private final class ComponentListener extends DefaultEventListener implements OnClickListener, OnSeekBarChangeListener {
        private ComponentListener() {
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            VideoControlView.this.removeCallbacks(VideoControlView.this.mHideAction);
            VideoControlView.this.mDragging = true;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (z && VideoControlView.this.mPositionView != null) {
                VideoControlView.this.mPositionView.setText(VideoControlView.this.stringForTime(VideoControlView.this.positionValue(i)));
            }
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            VideoControlView.this.mDragging = false;
            if (VideoControlView.this.mPlayer != null) {
                VideoControlView.this.seekTo(VideoControlView.this.positionValue(seekBar.getProgress()));
            }
            VideoControlView.this.hideAfterTimeout();
        }

        public void onPlayerStateChanged(boolean z, int i) {
            if (VideoControlView.this.mStateChangedListener != null) {
                VideoControlView.this.mStateChangedListener.onStateChanged(i);
            }
            VideoControlView.this.updatePlayPauseButton();
            VideoControlView.this.updateProgress();
        }

        public void onPositionDiscontinuity(int i) {
            VideoControlView.this.updateNavigation();
            VideoControlView.this.updateProgress();
        }

        public void onTimelineChanged(Timeline timeline, Object obj, int i) {
            VideoControlView.this.updateNavigation();
            VideoControlView.this.updateProgress();
        }

        public void onClick(View view) {
            if (VideoControlView.this.mPlayer != null) {
                if (VideoControlView.this.mPlayButton == view) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_VIDEO_CONTROL_PLAY);
                    VideoControlView.this.mPlayer.setPlayWhenReady(true);
                } else if (VideoControlView.this.mPauseButton == view) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_VIDEO_CONTROL_PAUSE);
                    VideoControlView.this.mPlayer.setPlayWhenReady(false);
                } else if (VideoControlView.this.mSoundButton == view) {
                    if (VideoControlView.this.mPlayer.getVolume() == 0.0f) {
                        VideoControlView.this.mPlayer.setVolume(1.0f);
                    } else {
                        VideoControlView.this.mPlayer.setVolume(0.0f);
                    }
                    VideoControlView.this.updateSoundButton();
                }
            }
            VideoControlView.this.hideAfterTimeout();
        }
    }

    public interface SeekDispatcher {
        boolean dispatchSeek(ExoPlayer exoPlayer, int i, long j);
    }

    public interface VisibilityListener {
        void onVisibilityChange(int i);
    }

    private static boolean isHandledMediaKey(int i) {
        return i == 90 || i == 89 || i == 85 || i == 126 || i == 127 || i == 87 || i == 88;
    }

    public VideoControlView(Context context) {
        this(context, null);
    }

    public VideoControlView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VideoControlView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mUpdateProgressAction = new Runnable() {
            public void run() {
                VideoControlView.this.updateProgress();
            }
        };
        this.mHideAction = new Runnable() {
            public void run() {
                VideoControlView.this.hide();
            }
        };
        this.mShowTimeoutMs = 1500;
        this.mCurrentWindow = new Window();
        this.mFormatBuilder = new StringBuilder();
        this.mFormatter = new Formatter(this.mFormatBuilder, Locale.getDefault());
        this.mComponentListener = new ComponentListener();
        this.mSeekDispatcher = DEFAULT_SEEK_DISPATCHER;
        LayoutInflater.from(context).inflate(R.layout.video_control_view, this);
        setDescendantFocusability(262144);
        this.mShadowView = findViewById(R.id.video_shadow);
        this.mTimeView = findViewById(R.id.video_time);
        this.mDurationView = (TextView) findViewById(R.id.exo_duration);
        this.mPositionView = (TextView) findViewById(R.id.exo_position);
        this.mProgressBar = (SeekBar) findViewById(R.id.exo_progress);
        if (this.mProgressBar != null) {
            this.mProgressBar.setOnSeekBarChangeListener(this.mComponentListener);
            this.mProgressBar.setMax(1000);
            this.mProgressBar.setPadding(0, 0, 0, 0);
        }
        this.mPlayButton = findViewById(R.id.exo_play);
        if (this.mPlayButton != null) {
            this.mPlayButton.setOnClickListener(this.mComponentListener);
        }
        this.mPauseButton = findViewById(R.id.exo_pause);
        if (this.mPauseButton != null) {
            this.mPauseButton.setOnClickListener(this.mComponentListener);
        }
        this.mSoundButton = (AutoReleasableImageView) findViewById(R.id.video_sound);
        if (this.mSoundButton != null) {
            this.mSoundButton.setOnClickListener(this.mComponentListener);
            updateSoundButton();
        }
    }

    public SimpleExoPlayer getPlayer() {
        return this.mPlayer;
    }

    public void setPlayer(SimpleExoPlayer simpleExoPlayer) {
        if (this.mPlayer != simpleExoPlayer) {
            if (this.mPlayer != null) {
                this.mPlayer.removeListener(this.mComponentListener);
            }
            this.mPlayer = simpleExoPlayer;
            if (simpleExoPlayer != null) {
                simpleExoPlayer.addListener(this.mComponentListener);
            }
            updateAll();
        }
    }

    public void setStateChangedListener(VideoPlayerStateChangedListener videoPlayerStateChangedListener) {
        this.mStateChangedListener = videoPlayerStateChangedListener;
    }

    public void setShowProgressBar(boolean z) {
        if (z) {
            this.mShadowView.setVisibility(0);
            this.mTimeView.setVisibility(0);
            this.mProgressBar.setVisibility(0);
            return;
        }
        this.mShadowView.setVisibility(8);
        this.mTimeView.setVisibility(8);
        this.mProgressBar.setVisibility(8);
    }

    public void setVisibilityListener(VisibilityListener visibilityListener) {
        this.mVisibilityListener = visibilityListener;
    }

    public void setSeekDispatcher(SeekDispatcher seekDispatcher) {
        if (seekDispatcher == null) {
            seekDispatcher = DEFAULT_SEEK_DISPATCHER;
        }
        this.mSeekDispatcher = seekDispatcher;
    }

    public int getShowTimeoutMs() {
        return this.mShowTimeoutMs;
    }

    public void setShowTimeoutMs(int i) {
        this.mShowTimeoutMs = i;
    }

    public void show() {
        if (!isVisible()) {
            fadeIn();
            if (this.mVisibilityListener != null) {
                this.mVisibilityListener.onVisibilityChange(getVisibility());
            }
            updateAll();
            requestPlayPauseFocus();
        }
        hideAfterTimeout();
    }

    public void hide() {
        if (isVisible()) {
            fadeOut();
            if (this.mVisibilityListener != null) {
                this.mVisibilityListener.onVisibilityChange(getVisibility());
            }
            removeCallbacks(this.mUpdateProgressAction);
            removeCallbacks(this.mHideAction);
            this.mHideAtMs = -9223372036854775807L;
        }
    }

    private void fadeIn() {
        setVisibility(0);
        animate().alpha(1.0f).setDuration(250).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
            }
        });
    }

    private void fadeOut() {
        animate().alpha(0.0f).setDuration(600).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                VideoControlView.this.setVisibility(8);
            }
        });
    }

    public boolean isVisible() {
        return getVisibility() == 0;
    }

    /* access modifiers changed from: private */
    public void hideAfterTimeout() {
        removeCallbacks(this.mHideAction);
        if (this.mShowTimeoutMs > 0) {
            this.mHideAtMs = SystemClock.uptimeMillis() + ((long) this.mShowTimeoutMs);
            if (this.mIsAttachedToWindow) {
                postDelayed(this.mHideAction, (long) this.mShowTimeoutMs);
                return;
            }
            return;
        }
        this.mHideAtMs = -9223372036854775807L;
    }

    private void updateAll() {
        updatePlayPauseButton();
        updateNavigation();
        updateProgress();
        updateSoundButton();
    }

    /* access modifiers changed from: private */
    public void updatePlayPauseButton() {
        boolean z;
        if (isVisible() && this.mIsAttachedToWindow) {
            boolean z2 = true;
            int i = 0;
            boolean z3 = this.mPlayer != null && this.mPlayer.getPlayWhenReady();
            if (this.mPlayButton != null) {
                z = (z3 && this.mPlayButton.isFocused()) | false;
                this.mPlayButton.setVisibility(z3 ? 8 : 0);
            } else {
                z = false;
            }
            if (this.mPauseButton != null) {
                if (z3 || !this.mPauseButton.isFocused()) {
                    z2 = false;
                }
                z |= z2;
                View view = this.mPauseButton;
                if (!z3) {
                    i = 8;
                }
                view.setVisibility(i);
            }
            if (z) {
                requestPlayPauseFocus();
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateNavigation() {
        if (isVisible() && this.mIsAttachedToWindow) {
            Timeline currentTimeline = this.mPlayer != null ? this.mPlayer.getCurrentTimeline() : null;
            boolean z = false;
            if (currentTimeline != null && !currentTimeline.isEmpty()) {
                currentTimeline.getWindow(this.mPlayer.getCurrentWindowIndex(), this.mCurrentWindow);
                z = this.mCurrentWindow.isSeekable;
            }
            if (this.mProgressBar != null) {
                this.mProgressBar.setEnabled(z);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateProgress() {
        long j;
        if (isVisible() && this.mIsAttachedToWindow) {
            long j2 = 0;
            long duration = this.mPlayer == null ? 0 : this.mPlayer.getDuration();
            long currentPosition = this.mPlayer == null ? 0 : this.mPlayer.getCurrentPosition();
            if (this.mDurationView != null) {
                this.mDurationView.setText(stringForTime(duration));
            }
            if (this.mPositionView != null && !this.mDragging) {
                this.mPositionView.setText(stringForTime(currentPosition));
            }
            if (this.mProgressBar != null) {
                if (!this.mDragging) {
                    this.mProgressBar.setProgress(progressBarValue(currentPosition));
                }
                if (this.mPlayer != null) {
                    j2 = this.mPlayer.getBufferedPosition();
                }
                this.mProgressBar.setSecondaryProgress(progressBarValue(j2));
            }
            removeCallbacks(this.mUpdateProgressAction);
            int playbackState = this.mPlayer == null ? 1 : this.mPlayer.getPlaybackState();
            if (!(playbackState == 1 || playbackState == 4)) {
                if (!this.mPlayer.getPlayWhenReady() || playbackState != 3) {
                    j = 1000;
                } else {
                    j = 1000 - (currentPosition % 1000);
                    if (j < 200) {
                        j += 1000;
                    }
                }
                postDelayed(this.mUpdateProgressAction, j);
            }
        }
    }

    private void requestPlayPauseFocus() {
        boolean z = this.mPlayer != null && this.mPlayer.getPlayWhenReady();
        if (!z && this.mPlayButton != null) {
            this.mPlayButton.requestFocus();
        } else if (z && this.mPauseButton != null) {
            this.mPauseButton.requestFocus();
        }
    }

    /* access modifiers changed from: private */
    public String stringForTime(long j) {
        if (j == -9223372036854775807L) {
            j = 0;
        }
        long j2 = (j + 500) / 1000;
        long j3 = j2 % 60;
        long j4 = (j2 / 60) % 60;
        long j5 = j2 / 3600;
        this.mFormatBuilder.setLength(0);
        if (j5 > 0) {
            return this.mFormatter.format("%d:%02d:%02d", new Object[]{Long.valueOf(j5), Long.valueOf(j4), Long.valueOf(j3)}).toString();
        }
        return this.mFormatter.format("%02d:%02d", new Object[]{Long.valueOf(j4), Long.valueOf(j3)}).toString();
    }

    private int progressBarValue(long j) {
        long duration = this.mPlayer == null ? -9223372036854775807L : this.mPlayer.getDuration();
        if (duration == -9223372036854775807L || duration == 0) {
            return 0;
        }
        return (int) ((j * 1000) / duration);
    }

    /* access modifiers changed from: private */
    public long positionValue(int i) {
        long duration = this.mPlayer == null ? -9223372036854775807L : this.mPlayer.getDuration();
        if (duration == -9223372036854775807L) {
            return 0;
        }
        return (duration * ((long) i)) / 1000;
    }

    private void previous() {
        Timeline currentTimeline = this.mPlayer.getCurrentTimeline();
        if (!currentTimeline.isEmpty()) {
            int currentWindowIndex = this.mPlayer.getCurrentWindowIndex();
            currentTimeline.getWindow(currentWindowIndex, this.mCurrentWindow);
            if (currentWindowIndex <= 0 || (this.mPlayer.getCurrentPosition() > 3000 && (!this.mCurrentWindow.isDynamic || this.mCurrentWindow.isSeekable))) {
                seekTo(0);
            } else {
                seekTo(currentWindowIndex - 1, -9223372036854775807L);
            }
        }
    }

    private void next() {
        Timeline currentTimeline = this.mPlayer.getCurrentTimeline();
        if (!currentTimeline.isEmpty()) {
            int currentWindowIndex = this.mPlayer.getCurrentWindowIndex();
            if (currentWindowIndex < currentTimeline.getWindowCount() - 1) {
                seekTo(currentWindowIndex + 1, -9223372036854775807L);
            } else if (currentTimeline.getWindow(currentWindowIndex, this.mCurrentWindow, false).isDynamic) {
                seekTo(currentWindowIndex, -9223372036854775807L);
            }
        }
    }

    public void seekTo(long j) {
        seekTo(this.mPlayer.getCurrentWindowIndex(), j);
    }

    private void seekTo(int i, long j) {
        if (!this.mSeekDispatcher.dispatchSeek(this.mPlayer, i, j)) {
            updateProgress();
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mIsAttachedToWindow = true;
        if (this.mHideAtMs != -9223372036854775807L) {
            long uptimeMillis = this.mHideAtMs - SystemClock.uptimeMillis();
            if (uptimeMillis <= 0) {
                hide();
            } else {
                postDelayed(this.mHideAction, uptimeMillis);
            }
        }
        updateAll();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mIsAttachedToWindow = false;
        removeCallbacks(this.mUpdateProgressAction);
        removeCallbacks(this.mHideAction);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        boolean z = dispatchMediaKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent);
        if (z) {
            show();
        }
        return z;
    }

    public boolean dispatchMediaKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (this.mPlayer == null || !isHandledMediaKey(keyCode)) {
            return false;
        }
        if (keyEvent.getAction() == 0) {
            switch (keyCode) {
                case 85:
                    this.mPlayer.setPlayWhenReady(!this.mPlayer.getPlayWhenReady());
                    break;
                case 87:
                    next();
                    break;
                case 88:
                    previous();
                    break;
                case 126:
                    this.mPlayer.setPlayWhenReady(true);
                    break;
                case 127:
                    this.mPlayer.setPlayWhenReady(false);
                    break;
            }
        }
        show();
        return true;
    }

    public void updateSoundButton() {
        if (this.mSoundButton != null && this.mPlayer != null) {
            if (this.mPlayer.getVolume() == 0.0f) {
                this.mSoundButton.setImageResource(R.drawable.video_sound_off);
            } else {
                this.mSoundButton.setImageResource(R.drawable.video_sound_on);
            }
        }
    }

    public void hideSoundButton() {
        if (this.mSoundButton != null) {
            this.mSoundButton.setVisibility(8);
        }
    }
}
