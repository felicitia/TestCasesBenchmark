package com.contextlogic.wish.video;

import android.view.View;
import android.view.View.OnClickListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.image.ImageRestorable;

public class LoginActionVideoPopupDialogView extends VideoPopupDialogView implements ImageRestorable {
    protected AutoReleasableImageView mCancel;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.video_popup_dialog_view;
    }

    public LoginActionVideoPopupDialogView(BaseDialogFragment baseDialogFragment) {
        super(baseDialogFragment);
    }

    public View init() {
        View init = super.init();
        this.mCancel = (AutoReleasableImageView) init.findViewById(R.id.video_popup_dialog_fragment_x);
        this.mCancel.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_VIDEO_SPLASH_MANUAL_EXIT, LoginActionVideoPopupDialogView.this.getTimeElapsedLoggingExtras());
                LoginActionVideoPopupDialogView.this.dismiss();
            }
        });
        return init;
    }

    public VideoMode getVideoMode() {
        return VideoMode.NETWORK_VIDEO;
    }

    public void releaseImages() {
        if (this.mCancel != null) {
            this.mCancel.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mCancel != null) {
            this.mCancel.restoreImages();
        }
    }
}
