package com.contextlogic.wish.activity.camera.videoplayer;

import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.UploadVideoService;
import com.contextlogic.wish.api.service.standalone.UploadVideoService.SuccessCallback;

public class VideoPlayerServiceFragment extends ServiceFragment {
    private UploadVideoService mUploadVideoService;

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mUploadVideoService = new UploadVideoService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mUploadVideoService.cancelAllRequests();
    }

    public void uploadVideo(String str, String str2) {
        this.mUploadVideoService.requestService(str, str2, new SuccessCallback() {
            public void onSuccess(final String str) {
                VideoPlayerServiceFragment.this.withUiFragment(new UiTask<BaseActivity, VideoPlayerFragment>() {
                    public void performTask(BaseActivity baseActivity, VideoPlayerFragment videoPlayerFragment) {
                        videoPlayerFragment.handleVideoUploadSuccess(str);
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                VideoPlayerServiceFragment.this.withUiFragment(new UiTask<BaseActivity, VideoPlayerFragment>() {
                    public void performTask(BaseActivity baseActivity, VideoPlayerFragment videoPlayerFragment) {
                        videoPlayerFragment.handleVideoUploadFailure();
                    }
                });
            }
        });
    }
}
