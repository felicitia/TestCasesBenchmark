package com.contextlogic.wish.video;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.loading.CircleProgressBar;
import com.google.android.exoplayer2.Player.DefaultEventListener;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.TextOutput;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.video.VideoListener;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public final class ControllableVideoPlayerView extends FrameLayout {
    /* access modifiers changed from: private */
    public final CircleProgressBar mCircleProgressBar;
    private final ComponentListener mComponentListener;
    /* access modifiers changed from: private */
    public final AspectRatioFrameLayout mContentFrame;
    private final VideoControlView mController;
    private int mControllerShowTimeoutMs;
    private final View mLoadingOverlay;
    /* access modifiers changed from: private */
    public SimpleExoPlayer mPlayer;
    /* access modifiers changed from: private */
    public Timer mProgressBarTimer;
    /* access modifiers changed from: private */
    public ResizeListener mResizeListener;
    /* access modifiers changed from: private */
    public final View mShutterView;
    private final View mSurfaceView;
    private boolean mUseController;

    private final class ComponentListener extends DefaultEventListener implements TextOutput, VideoListener {
        public void onCues(List<Cue> list) {
        }

        private ComponentListener() {
        }

        public void onVideoSizeChanged(int i, int i2, int i3, float f) {
            if (ControllableVideoPlayerView.this.mContentFrame != null) {
                ControllableVideoPlayerView.this.mContentFrame.setAspectRatio(i2 == 0 ? 1.0f : (((float) i) * f) / ((float) i2));
                if (ControllableVideoPlayerView.this.mResizeListener != null) {
                    ControllableVideoPlayerView.this.mResizeListener.onResize(i, i2, i3, f);
                }
            }
        }

        public void onRenderedFirstFrame() {
            if (ControllableVideoPlayerView.this.mShutterView != null) {
                ControllableVideoPlayerView.this.mShutterView.setVisibility(4);
                ControllableVideoPlayerView.this.hideProgressBar();
                if (ControllableVideoPlayerView.this.mProgressBarTimer != null) {
                    ControllableVideoPlayerView.this.mProgressBarTimer.cancel();
                    ControllableVideoPlayerView.this.mProgressBarTimer.purge();
                    ControllableVideoPlayerView.this.mCircleProgressBar.postInvalidate();
                    ControllableVideoPlayerView.this.mProgressBarTimer = null;
                }
            }
        }

        public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
            ControllableVideoPlayerView.this.updateForCurrentTrackSelections();
        }

        public void onPlayerStateChanged(boolean z, int i) {
            ControllableVideoPlayerView.this.maybeShowController(false);
        }
    }

    public interface ResizeListener {
        void onResize(int i, int i2, int i3, float f);
    }

    public ControllableVideoPlayerView(Context context) {
        this(context, null);
    }

    public ControllableVideoPlayerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ControllableVideoPlayerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(R.layout.exo_simple_player_view, this);
        this.mComponentListener = new ComponentListener();
        setDescendantFocusability(262144);
        this.mContentFrame = (AspectRatioFrameLayout) findViewById(R.id.exo_content_frame);
        boolean z = true;
        if (this.mContentFrame != null) {
            setResizeModeRaw(this.mContentFrame, 1);
        }
        this.mShutterView = findViewById(R.id.exo_shutter);
        if (this.mContentFrame != null) {
            LayoutParams layoutParams = new LayoutParams(-1, -1);
            this.mSurfaceView = new TextureView(context);
            this.mSurfaceView.setLayoutParams(layoutParams);
            this.mContentFrame.addView(this.mSurfaceView, 0);
        } else {
            this.mSurfaceView = null;
        }
        View findViewById = findViewById(R.id.exo_controller_placeholder);
        if (findViewById != null) {
            this.mController = new VideoControlView(context);
            this.mController.setLayoutParams(findViewById.getLayoutParams());
            ViewGroup viewGroup = (ViewGroup) findViewById.getParent();
            int indexOfChild = viewGroup.indexOfChild(findViewById);
            viewGroup.removeView(findViewById);
            viewGroup.addView(this.mController, indexOfChild);
        } else {
            this.mController = null;
        }
        this.mControllerShowTimeoutMs = this.mController != null ? 1500 : 0;
        if (this.mController == null) {
            z = false;
        }
        this.mUseController = z;
        hideController();
        this.mLoadingOverlay = new View(context);
        this.mLoadingOverlay.setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.translucent_black));
        this.mLoadingOverlay.setLayoutParams(new LayoutParams(-1, -1));
        this.mCircleProgressBar = new CircleProgressBar(context, attributeSet);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.video_circular_progress_bar_size), getResources().getDimensionPixelSize(R.dimen.video_circular_progress_bar_size));
        layoutParams2.gravity = 17;
        this.mCircleProgressBar.setLayoutParams(layoutParams2);
        this.mCircleProgressBar.setProgress(0.0f);
        addView(this.mLoadingOverlay);
        addView(this.mCircleProgressBar);
    }

    public void setVolume(float f) {
        this.mPlayer.setVolume(f);
        this.mController.updateSoundButton();
    }

    public void setShowProgressBar(boolean z) {
        this.mController.setShowProgressBar(z);
    }

    public void setStateChangedListener(VideoPlayerStateChangedListener videoPlayerStateChangedListener) {
        if (this.mController != null) {
            this.mController.setStateChangedListener(videoPlayerStateChangedListener);
        }
    }

    public void hideSoundButton() {
        if (this.mController != null) {
            this.mController.hideSoundButton();
        }
    }

    public SimpleExoPlayer getPlayer() {
        return this.mPlayer;
    }

    public void setPlayer(SimpleExoPlayer simpleExoPlayer) {
        if (this.mPlayer != simpleExoPlayer) {
            if (this.mPlayer != null) {
                this.mPlayer.removeVideoListener(this.mComponentListener);
                this.mPlayer.removeListener(this.mComponentListener);
                this.mPlayer.removeTextOutput(this.mComponentListener);
                this.mPlayer.setVideoSurface(null);
            }
            this.mPlayer = simpleExoPlayer;
            if (this.mUseController) {
                this.mController.setPlayer(simpleExoPlayer);
            }
            if (this.mShutterView != null) {
                this.mShutterView.setVisibility(0);
            }
            if (simpleExoPlayer != null) {
                if (this.mSurfaceView instanceof TextureView) {
                    simpleExoPlayer.setVideoTextureView((TextureView) this.mSurfaceView);
                } else if (this.mSurfaceView instanceof SurfaceView) {
                    simpleExoPlayer.setVideoSurfaceView((SurfaceView) this.mSurfaceView);
                }
                simpleExoPlayer.addVideoListener(this.mComponentListener);
                simpleExoPlayer.addListener(this.mComponentListener);
                simpleExoPlayer.addTextOutput(this.mComponentListener);
                maybeShowController(false);
                updateForCurrentTrackSelections();
                if (this.mProgressBarTimer != null) {
                    this.mProgressBarTimer.cancel();
                    this.mProgressBarTimer.purge();
                    this.mCircleProgressBar.postInvalidate();
                    this.mProgressBarTimer = null;
                }
                if (simpleExoPlayer.getBufferedPercentage() < 100) {
                    this.mProgressBarTimer = new Timer();
                    this.mProgressBarTimer.scheduleAtFixedRate(new TimerTask() {
                        public void run() {
                            ControllableVideoPlayerView.this.mCircleProgressBar.post(new Runnable() {
                                public void run() {
                                    if (ControllableVideoPlayerView.this.mPlayer != null) {
                                        ControllableVideoPlayerView.this.mCircleProgressBar.setProgress((float) ControllableVideoPlayerView.this.mPlayer.getBufferedPercentage());
                                    }
                                }
                            });
                        }
                    }, 0, 100);
                }
            } else {
                hideController();
            }
        }
    }

    public void setResizeMode(int i) {
        Assertions.checkState(this.mContentFrame != null);
        this.mContentFrame.setResizeMode(i);
    }

    public boolean getUseController() {
        return this.mUseController;
    }

    public void setUseController(boolean z) {
        Assertions.checkState(!z || this.mController != null);
        if (this.mUseController != z) {
            this.mUseController = z;
            if (z) {
                this.mController.setPlayer(this.mPlayer);
            } else if (this.mController != null) {
                this.mController.hide();
                this.mController.setPlayer(null);
            }
        }
    }

    public void showController() {
        if (this.mUseController) {
            maybeShowController(true);
        }
    }

    public void hideController() {
        if (this.mController != null) {
            this.mController.hide();
        }
    }

    public void showProgressBar() {
        this.mLoadingOverlay.setVisibility(0);
        this.mCircleProgressBar.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void hideProgressBar() {
        this.mLoadingOverlay.setVisibility(8);
        this.mCircleProgressBar.setVisibility(8);
    }

    public void setResizeListener(ResizeListener resizeListener) {
        this.mResizeListener = resizeListener;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mUseController || this.mPlayer == null || motionEvent.getActionMasked() != 0) {
            return false;
        }
        if (this.mController.isVisible()) {
            this.mController.hide();
        } else {
            maybeShowController(true);
        }
        return true;
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (!this.mUseController || this.mPlayer == null) {
            return false;
        }
        maybeShowController(true);
        return true;
    }

    public void maybeShowController(boolean z) {
        if (this.mUseController && this.mPlayer != null) {
            int playbackState = this.mPlayer.getPlaybackState();
            int i = 0;
            boolean z2 = true;
            boolean z3 = playbackState == 1 || playbackState == 4 || !this.mPlayer.getPlayWhenReady();
            if (!this.mController.isVisible() || this.mController.getShowTimeoutMs() > 0) {
                z2 = false;
            }
            VideoControlView videoControlView = this.mController;
            if (!z3) {
                i = this.mControllerShowTimeoutMs;
            }
            videoControlView.setShowTimeoutMs(i);
            if (z || z3 || z2) {
                this.mController.show();
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateForCurrentTrackSelections() {
        if (this.mPlayer != null) {
            TrackSelectionArray currentTrackSelections = this.mPlayer.getCurrentTrackSelections();
            int i = 0;
            while (i < currentTrackSelections.length) {
                if (this.mPlayer.getRendererType(i) != 2 || currentTrackSelections.get(i) == null) {
                    i++;
                } else {
                    return;
                }
            }
            if (this.mShutterView != null) {
                this.mShutterView.setVisibility(0);
            }
        }
    }

    private static void setResizeModeRaw(AspectRatioFrameLayout aspectRatioFrameLayout, int i) {
        aspectRatioFrameLayout.setResizeMode(i);
    }

    public void destroy() {
        if (this.mProgressBarTimer != null) {
            this.mProgressBarTimer.cancel();
            this.mProgressBarTimer.purge();
            this.mCircleProgressBar.postInvalidate();
            this.mProgressBarTimer = null;
        }
    }
}
