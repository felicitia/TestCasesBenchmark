package com.contextlogic.wish.dialog.screenshot;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishScreenshotShareInfo;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.DisplayUtil;
import com.contextlogic.wish.util.IntentUtil;
import com.contextlogic.wish.util.ScreenshotUtil;

public class ScreenshotShareDialogFragment<A extends BaseActivity> extends BaseDialogFragment<A> {
    public boolean isCancelable() {
        return false;
    }

    public static ScreenshotShareDialogFragment<BaseActivity> createScreesnhotShareDialog(Uri uri, WishScreenshotShareInfo wishScreenshotShareInfo) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("ArgumentImageUri", uri);
        bundle.putParcelable("ArgumentScreenshotShareInfo", wishScreenshotShareInfo);
        ScreenshotShareDialogFragment<BaseActivity> screenshotShareDialogFragment = new ScreenshotShareDialogFragment<>();
        screenshotShareDialogFragment.setArguments(bundle);
        return screenshotShareDialogFragment;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        final Uri uri = (Uri) getArguments().getParcelable("ArgumentImageUri");
        final WishScreenshotShareInfo wishScreenshotShareInfo = (WishScreenshotShareInfo) getArguments().getParcelable("ArgumentScreenshotShareInfo");
        if (uri == null || wishScreenshotShareInfo == null) {
            return null;
        }
        View inflate = layoutInflater.inflate(R.layout.screenshot_share_dialog, viewGroup, false);
        ((NetworkImageView) inflate.findViewById(R.id.screenshot_share_dialog_image)).setImageUrl(uri.toString());
        ((ThemedTextView) inflate.findViewById(R.id.screenshot_share_dialog_message)).setText(wishScreenshotShareInfo.getMessage());
        ThemedTextView themedTextView = (ThemedTextView) inflate.findViewById(R.id.screenshot_share_dialog_send_button);
        themedTextView.setText(wishScreenshotShareInfo.getSendText());
        themedTextView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ScreenshotShareDialogFragment.this.withActivity(new ActivityTask<A>() {
                    public void performTask(A a) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SCREENSHOT_SHARE_DIALOG_SEND);
                        try {
                            Intent shareIntent = IntentUtil.getShareIntent(null, wishScreenshotShareInfo.getShareText(), ScreenshotUtil.getPublicScreenshotUri(uri), "image/jpeg");
                            if (shareIntent != null) {
                                a.startActivity(shareIntent);
                            }
                        } catch (Throwable unused) {
                        }
                        a.shareEventTriggered();
                        ScreenshotShareDialogFragment.this.cancel();
                    }
                });
            }
        });
        ThemedTextView themedTextView2 = (ThemedTextView) inflate.findViewById(R.id.screenshot_share_dialog_cancel_button);
        themedTextView2.setText(wishScreenshotShareInfo.getCancelText());
        themedTextView2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SCREENSHOT_SHARE_DIALOG_CANCEL);
                ScreenshotShareDialogFragment.this.cancel();
            }
        });
        return inflate;
    }

    public int getDialogWidth() {
        return (int) (getResources().getFraction(R.fraction.dialog_min_width_major, 1, 1) * ((float) DisplayUtil.getDisplayWidth()));
    }
}
