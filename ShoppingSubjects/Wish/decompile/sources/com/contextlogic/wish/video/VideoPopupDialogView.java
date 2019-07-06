package com.contextlogic.wish.video;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishVideoPopupSpec;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.util.VideoUtil;
import com.google.android.exoplayer2.SimpleExoPlayer;
import java.util.HashMap;

public abstract class VideoPopupDialogView extends RelativeLayout implements ImageRestorable, VideoPlayerStateChangedListener {
    protected BaseDialogFragment mFragment;
    protected SimpleExoPlayer mPlayer;
    protected long mPopupLaunchTime;
    protected FrameLayout mVideoContainer;
    protected VideoMode mVideoMode;
    protected WishVideoPopupSpec mVideoPopupSpec;
    protected VideoPlayerView mVideoView;

    protected enum VideoMode {
        NETWORK_VIDEO,
        RAW_VIDEO
    }

    /* access modifiers changed from: protected */
    public abstract int getLayoutResourceId();

    /* access modifiers changed from: protected */
    public abstract VideoMode getVideoMode();

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    public VideoPopupDialogView(BaseDialogFragment baseDialogFragment) {
        super(baseDialogFragment.getContext());
        this.mFragment = baseDialogFragment;
        init();
    }

    public void onStateChanged(int i) {
        switch (i) {
            case 3:
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_VIDEO_SPLASH_START_PLAYING, getTimeElapsedLoggingExtras());
                return;
            case 4:
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_VIDEO_SPLASH_FINISH_PLAYING, getTimeElapsedLoggingExtras());
                this.mFragment.dismiss();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public View init() {
        this.mVideoMode = getVideoMode();
        View inflate = LayoutInflater.from(getContext()).inflate(getLayoutResourceId(), this);
        this.mVideoContainer = (FrameLayout) inflate.findViewById(R.id.video_popup_video_player_container);
        this.mPopupLaunchTime = System.currentTimeMillis();
        return inflate;
    }

    public void setup(WishVideoPopupSpec wishVideoPopupSpec, int i, boolean z) {
        this.mVideoPopupSpec = wishVideoPopupSpec;
        this.mVideoView = new VideoPlayerView(this.mFragment.getBaseActivity());
        this.mPlayer = createPlayer(wishVideoPopupSpec);
        this.mVideoView.setPlayer(this.mPlayer);
        this.mVideoView.setStateChangedListener(this);
        this.mVideoView.setResizeMode(i);
        this.mVideoContainer.addView(this.mVideoView, 0);
        if (this.mPlayer != null) {
            this.mPlayer.setPlayWhenReady(z);
        }
    }

    public SimpleExoPlayer createPlayer(WishVideoPopupSpec wishVideoPopupSpec) {
        switch (this.mVideoMode) {
            case NETWORK_VIDEO:
                return VideoUtil.createMP4Player(this.mFragment.getBaseActivity(), wishVideoPopupSpec.getVideoResource(), wishVideoPopupSpec.shouldMuteAudio());
            case RAW_VIDEO:
                return VideoUtil.createRawVideoPlayer(this.mFragment.getBaseActivity(), getResources().getIdentifier(wishVideoPopupSpec.getVideoResource(), "raw", WishApplication.getInstance().getPackageName()), wishVideoPopupSpec.shouldMuteAudio());
            default:
                return null;
        }
    }

    public HashMap<String, String> getTimeElapsedLoggingExtras() {
        HashMap<String, String> loggingExtras = getLoggingExtras();
        loggingExtras.put("time_elapsed_after_launch", Long.toString(System.currentTimeMillis() - this.mPopupLaunchTime));
        return loggingExtras;
    }

    public HashMap<String, String> getLoggingExtras() {
        HashMap<String, String> hashMap = new HashMap<>();
        if (this.mVideoPopupSpec != null) {
            hashMap.put("video_url", this.mVideoPopupSpec.getVideoResource());
        }
        return hashMap;
    }

    public void dismiss() {
        if (this.mFragment != null) {
            this.mFragment.dismiss();
        }
        if (this.mPlayer != null) {
            this.mPlayer.setVolume(0.0f);
        }
    }
}
