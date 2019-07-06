package com.contextlogic.wish.video;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishProductVideoInfo;
import com.contextlogic.wish.video.ControllableVideoPlayerView.ResizeListener;
import com.google.android.exoplayer2.SimpleExoPlayer;

public class FullScreenVideoPlayerView extends FrameLayout implements ResizeListener {
    private View mBackground;
    private ControllableVideoPlayerView mVideoPlayerView;

    public FullScreenVideoPlayerView(Context context) {
        this(context, null);
    }

    public FullScreenVideoPlayerView(Context context, AttributeSet attributeSet) {
        this(context, null, 0);
    }

    public FullScreenVideoPlayerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mVideoPlayerView = new ControllableVideoPlayerView(context);
        addView(this.mVideoPlayerView);
    }

    public void setPlayer(SimpleExoPlayer simpleExoPlayer) {
        this.mVideoPlayerView.setPlayer(simpleExoPlayer);
    }

    public void setVideoInfo(WishProductVideoInfo wishProductVideoInfo) {
        LayoutParams layoutParams;
        int i = Resources.getSystem().getDisplayMetrics().widthPixels;
        if (wishProductVideoInfo.getAspectRatio() > 0.0d) {
            LayoutParams layoutParams2 = new LayoutParams(-1, (int) (((double) i) / wishProductVideoInfo.getAspectRatio()));
            layoutParams2.gravity = 17;
            layoutParams = layoutParams2;
        } else {
            this.mVideoPlayerView.setResizeListener(this);
            layoutParams = new LayoutParams(-1, i);
            this.mBackground = new View(getContext());
            layoutParams.gravity = 17;
            this.mBackground.setLayoutParams(layoutParams);
            this.mBackground.setBackgroundColor(getResources().getColor(R.color.black));
            this.mVideoPlayerView.addView(this.mBackground, 0);
        }
        layoutParams.gravity = 17;
        this.mVideoPlayerView.setLayoutParams(layoutParams);
    }

    public void showController() {
        this.mVideoPlayerView.showController();
    }

    public void onResize(int i, int i2, int i3, float f) {
        if (i2 >= i) {
            this.mVideoPlayerView.setResizeMode(2);
        }
    }

    public void destroy() {
        if (this.mVideoPlayerView != null) {
            this.mVideoPlayerView.destroy();
        }
    }

    public void showProgressBar() {
        if (this.mVideoPlayerView != null) {
            this.mVideoPlayerView.showProgressBar();
        }
    }
}
