package com.contextlogic.wish.activity.camera.videoplayer;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.camera.MediaInfo;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ConfigDataCenter;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.ui.button.ThemedButton;
import com.contextlogic.wish.util.VideoUtil;
import com.contextlogic.wish.video.ControllableVideoPlayerView;
import com.contextlogic.wish.video.VideoPlayerStateChangedListener;
import com.google.android.exoplayer2.SimpleExoPlayer;

public class VideoPlayerFragment extends UiFragment<VideoPlayerActivity> implements VideoPlayerStateChangedListener {
    private ThemedButton mFinishButton;
    private SimpleExoPlayer mPlayer;
    private FrameLayout mVideoContainer;
    /* access modifiers changed from: private */
    public MediaInfo mVideoInfo;
    private RelativeLayout mVideoUploadingView;
    /* access modifiers changed from: private */
    public ControllableVideoPlayerView mVideoView;
    private ImageView mXButton;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.video_player_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mVideoContainer = (FrameLayout) findViewById(R.id.video_player_fragment_container);
        this.mVideoUploadingView = (RelativeLayout) findViewById(R.id.video_fragment_video_uploading_view);
        this.mXButton = (ImageView) findViewById(R.id.video_player_fragment_x_button);
        this.mFinishButton = (ThemedButton) findViewById(R.id.video_player_fragment_finish_button);
        withActivity(new ActivityTask<VideoPlayerActivity>() {
            public void performTask(VideoPlayerActivity videoPlayerActivity) {
                VideoPlayerFragment.this.mVideoInfo = videoPlayerActivity.getVideoInfo();
                if (VideoPlayerFragment.this.mVideoInfo == null) {
                    videoPlayerActivity.finishActivity();
                    return;
                }
                VideoPlayerFragment.this.mVideoView = new ControllableVideoPlayerView(videoPlayerActivity);
                VideoPlayerFragment.this.prepareVideoPlayer();
            }
        });
        setupClickListeners();
    }

    /* access modifiers changed from: private */
    public void prepareVideoPlayer() {
        this.mVideoView.setLayoutParams(new LayoutParams(-1, -1));
        this.mPlayer = VideoUtil.createLocalVideoPlayer(getBaseActivity(), this.mVideoInfo.getUri(), false);
        if (this.mPlayer == null) {
            withActivity(new ActivityTask<VideoPlayerActivity>() {
                public void performTask(final VideoPlayerActivity videoPlayerActivity) {
                    videoPlayerActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(videoPlayerActivity.getString(R.string.video_player_failure)), new BaseDialogCallback() {
                        public void onCancel(BaseDialogFragment baseDialogFragment) {
                        }

                        public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                            if (i == 3) {
                                videoPlayerActivity.finishActivity();
                            }
                        }
                    });
                }
            });
            return;
        }
        this.mVideoView.setPlayer(this.mPlayer);
        this.mVideoView.setStateChangedListener(this);
        this.mVideoView.hideSoundButton();
        this.mVideoContainer.addView(this.mVideoView, 0);
        this.mPlayer.setPlayWhenReady(true);
    }

    private void setupClickListeners() {
        ThemedButton themedButton = (ThemedButton) findViewById(R.id.video_player_fragment_finish_button);
        ((ImageView) findViewById(R.id.video_player_fragment_x_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CAMERA_DISCARD_VIDEO);
                VideoPlayerFragment.this.withActivity(new ActivityTask<VideoPlayerActivity>() {
                    public void performTask(VideoPlayerActivity videoPlayerActivity) {
                        videoPlayerActivity.finishActivity();
                    }
                });
            }
        });
        themedButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CAMERA_DISCARD_VIDEO);
                VideoPlayerFragment.this.withActivity(new ActivityTask<VideoPlayerActivity>() {
                    public void performTask(VideoPlayerActivity videoPlayerActivity) {
                        VideoPlayerFragment.this.uploadVideo(VideoPlayerFragment.this.mVideoInfo.getUri().getPath());
                    }
                });
            }
        });
    }

    public void uploadVideo(final String str) {
        this.mXButton.setVisibility(8);
        this.mFinishButton.setVisibility(8);
        this.mVideoView.hideController();
        this.mVideoUploadingView.setVisibility(0);
        withServiceFragment(new ServiceTask<BaseActivity, VideoPlayerServiceFragment>() {
            public void performTask(BaseActivity baseActivity, VideoPlayerServiceFragment videoPlayerServiceFragment) {
                String videoUploadUrl = ConfigDataCenter.getInstance().getVideoUploadUrl();
                if (videoUploadUrl != null) {
                    videoPlayerServiceFragment.uploadVideo(videoUploadUrl, str);
                } else {
                    baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(baseActivity.getString(R.string.failed_to_upload_video)));
                }
            }
        });
    }

    public void handleVideoUploadSuccess(final String str) {
        this.mVideoUploadingView.setVisibility(8);
        withActivity(new ActivityTask<VideoPlayerActivity>() {
            public void performTask(VideoPlayerActivity videoPlayerActivity) {
                videoPlayerActivity.startProductReviewActivity(VideoPlayerFragment.this.mVideoInfo, str);
            }
        });
    }

    public void handleVideoUploadFailure() {
        this.mXButton.setVisibility(0);
        this.mFinishButton.setVisibility(0);
        this.mVideoView.showController();
        this.mVideoUploadingView.setVisibility(8);
        withActivity(new ActivityTask<VideoPlayerActivity>() {
            public void performTask(VideoPlayerActivity videoPlayerActivity) {
                videoPlayerActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(WishApplication.getInstance().getString(R.string.failed_to_upload_video)));
            }
        });
    }

    public void onStateChanged(int i) {
        switch (i) {
            case 3:
                this.mXButton.setVisibility(0);
                this.mFinishButton.setVisibility(0);
                this.mVideoView.showController();
                return;
            case 4:
                this.mPlayer.setPlayWhenReady(false);
                this.mPlayer.seekTo(0);
                return;
            default:
                return;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mPlayer != null) {
            this.mPlayer.release();
        }
    }
}
