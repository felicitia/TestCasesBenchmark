package com.contextlogic.wish.video;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
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

public final class VideoPlayerView extends FrameLayout {
    /* access modifiers changed from: private */
    public final CircleProgressBar mCircleProgressBar;
    private final ComponentListener mComponentListener;
    /* access modifiers changed from: private */
    public final AspectRatioFrameLayout mContentFrame;
    private final View mLoadingOverlay;
    /* access modifiers changed from: private */
    public SimpleExoPlayer mPlayer;
    /* access modifiers changed from: private */
    public Timer mProgressBarTimer;
    /* access modifiers changed from: private */
    public final View mShutterView;
    /* access modifiers changed from: private */
    public VideoPlayerStateChangedListener mStateChangedListener;
    private final View mSurfaceView;

    private final class ComponentListener extends DefaultEventListener implements TextOutput, VideoListener {
        private VideoListener videoListener;

        public void onCues(List<Cue> list) {
        }

        private ComponentListener() {
        }

        public void setVideoListener(SimpleExoPlayer.VideoListener videoListener2) {
            this.videoListener = videoListener2;
        }

        public void onVideoSizeChanged(int i, int i2, int i3, float f) {
            if (VideoPlayerView.this.mContentFrame != null) {
                VideoPlayerView.this.mContentFrame.setAspectRatio(i2 == 0 ? 1.0f : (((float) i) * f) / ((float) i2));
            }
            if (this.videoListener != null) {
                this.videoListener.onVideoSizeChanged(i, i2, i3, f);
            }
        }

        public void onRenderedFirstFrame() {
            if (VideoPlayerView.this.mShutterView != null) {
                VideoPlayerView.this.mShutterView.setVisibility(4);
            }
            VideoPlayerView.this.hideProgressBar();
            if (VideoPlayerView.this.mProgressBarTimer != null) {
                VideoPlayerView.this.mProgressBarTimer.cancel();
                VideoPlayerView.this.mProgressBarTimer.purge();
                VideoPlayerView.this.mCircleProgressBar.postInvalidate();
                VideoPlayerView.this.mProgressBarTimer = null;
            }
            if (this.videoListener != null) {
                this.videoListener.onRenderedFirstFrame();
            }
        }

        public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
            VideoPlayerView.this.updateForCurrentTrackSelections();
        }

        public void onPlayerStateChanged(boolean z, int i) {
            if (VideoPlayerView.this.mStateChangedListener != null) {
                VideoPlayerView.this.mStateChangedListener.onStateChanged(i);
            }
        }
    }

    public VideoPlayerView(Context context) {
        this(context, null);
    }

    public VideoPlayerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VideoPlayerView(Context context, AttributeSet attributeSet, int i) {
        int i2;
        int i3;
        super(context, attributeSet, i);
        int i4 = R.layout.exo_simple_player_view;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, com.google.android.exoplayer2.R.styleable.PlayerView, 0, 0);
            try {
                i4 = obtainStyledAttributes.getResourceId(6, R.layout.exo_simple_player_view);
                i3 = obtainStyledAttributes.getInt(13, 2);
                i2 = obtainStyledAttributes.getInt(8, 0);
            } finally {
                obtainStyledAttributes.recycle();
            }
        } else {
            i3 = 2;
            i2 = 0;
        }
        LayoutInflater.from(context).inflate(i4, this);
        this.mComponentListener = new ComponentListener();
        setDescendantFocusability(262144);
        this.mContentFrame = (AspectRatioFrameLayout) findViewById(R.id.exo_content_frame);
        if (this.mContentFrame != null) {
            setResizeModeRaw(this.mContentFrame, i2);
        }
        this.mShutterView = findViewById(R.id.exo_shutter);
        if (this.mContentFrame == null || i3 == 0) {
            this.mSurfaceView = null;
        } else {
            LayoutParams layoutParams = new LayoutParams(-1, -1);
            this.mSurfaceView = i3 == 2 ? new TextureView(context) : new SurfaceView(context);
            this.mSurfaceView.setLayoutParams(layoutParams);
            this.mContentFrame.addView(this.mSurfaceView, 0);
        }
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

    public SimpleExoPlayer getPlayer() {
        return this.mPlayer;
    }

    public void setPlayer(SimpleExoPlayer simpleExoPlayer) {
        setPlayer(simpleExoPlayer, null);
    }

    public void setPlayer(SimpleExoPlayer simpleExoPlayer, SimpleExoPlayer.VideoListener videoListener) {
        if (this.mPlayer != simpleExoPlayer) {
            if (this.mPlayer != null) {
                this.mPlayer.removeTextOutput(this.mComponentListener);
                this.mPlayer.removeVideoListener(this.mComponentListener);
                this.mPlayer.removeListener(this.mComponentListener);
                this.mPlayer.setVideoSurface(null);
            }
            this.mPlayer = simpleExoPlayer;
            if (this.mShutterView != null) {
                this.mShutterView.setVisibility(0);
            }
            this.mComponentListener.setVideoListener(videoListener);
            if (simpleExoPlayer != null) {
                if (this.mSurfaceView instanceof TextureView) {
                    simpleExoPlayer.setVideoTextureView((TextureView) this.mSurfaceView);
                } else if (this.mSurfaceView instanceof SurfaceView) {
                    simpleExoPlayer.setVideoSurfaceView((SurfaceView) this.mSurfaceView);
                }
                simpleExoPlayer.addTextOutput(this.mComponentListener);
                simpleExoPlayer.addVideoListener(this.mComponentListener);
                simpleExoPlayer.addListener(this.mComponentListener);
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
                            VideoPlayerView.this.mCircleProgressBar.post(new Runnable() {
                                public void run() {
                                    if (VideoPlayerView.this.mPlayer != null) {
                                        VideoPlayerView.this.mCircleProgressBar.setProgress((float) VideoPlayerView.this.mPlayer.getBufferedPercentage());
                                    }
                                }
                            });
                        }
                    }, 0, 100);
                }
            }
        }
    }

    public void setResizeMode(int i) {
        Assertions.checkState(this.mContentFrame != null);
        this.mContentFrame.setResizeMode(i);
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

    public void setStateChangedListener(VideoPlayerStateChangedListener videoPlayerStateChangedListener) {
        this.mStateChangedListener = videoPlayerStateChangedListener;
    }

    public void showProgressBar() {
        this.mLoadingOverlay.setVisibility(0);
        this.mCircleProgressBar.setVisibility(0);
    }

    public void hideProgressBar() {
        this.mLoadingOverlay.setVisibility(8);
        this.mCircleProgressBar.setVisibility(8);
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
