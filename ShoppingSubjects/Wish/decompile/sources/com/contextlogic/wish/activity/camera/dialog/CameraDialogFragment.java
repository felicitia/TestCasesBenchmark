package com.contextlogic.wish.activity.camera.dialog;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.exampleugc.exampleugcintro.ExampleUgcIntroActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.button.ThemedButton;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class CameraDialogFragment<A extends BaseActivity> extends DialogFragment {
    private ThemedButton mButton;
    /* access modifiers changed from: private */
    public LinearLayout mContentContainer;
    private ThemedTextView mDescriptionTextView;
    private LinearLayout mMainContainer;
    private ThemedTextView mTitleTextView;
    /* access modifiers changed from: private */
    public AutoReleasableImageView mTooltip;
    /* access modifiers changed from: private */
    public int mXOffset;
    /* access modifiers changed from: private */
    public int mYOffset;

    public static CameraDialogFragment createCameraDialogFragment(int i, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt("ArgumentXOffset", i);
        bundle.putInt("ArgumentYOffset", i2);
        CameraDialogFragment cameraDialogFragment = new CameraDialogFragment();
        cameraDialogFragment.setArguments(bundle);
        return cameraDialogFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mXOffset = getArguments().getInt("ArgumentXOffset");
        this.mYOffset = getArguments().getInt("ArgumentYOffset");
        return layoutInflater.inflate(R.layout.camera_dialog_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mMainContainer = (LinearLayout) view.findViewById(R.id.camera_dialog_main_container);
        this.mTooltip = (AutoReleasableImageView) view.findViewById(R.id.camera_dialog_tooltip);
        this.mContentContainer = (LinearLayout) view.findViewById(R.id.camera_dialog_content_container);
        this.mTitleTextView = (ThemedTextView) view.findViewById(R.id.title_text);
        this.mDescriptionTextView = (ThemedTextView) view.findViewById(R.id.description_text);
        this.mButton = (ThemedButton) view.findViewById(R.id.try_it_out_button);
        setContent();
        setupListeners();
        setDialogPosition();
        performFadeAnimation();
    }

    private void setContent() {
        this.mTitleTextView.setText(WishApplication.getInstance().getString(R.string.share_your_purchase_earn_points));
        this.mDescriptionTextView.setText(WishApplication.getInstance().getString(R.string.how_was_your_recent_purchase));
    }

    private void setupListeners() {
        this.mButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_TRY_OUT_CAMERA_FEATURE_BUTTON);
                CameraDialogFragment.this.openExampleUgcIntroActivity();
                CameraDialogFragment.this.dismiss();
            }
        });
    }

    private void setDialogPosition() {
        this.mContentContainer.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                Window window = CameraDialogFragment.this.getDialog().getWindow();
                window.setGravity(51);
                window.setLayout(-1, -1);
                LayoutParams attributes = window.getAttributes();
                int dimension = (int) WishApplication.getInstance().getResources().getDimension(R.dimen.camera_dialog_fragment_tooltip_y_offset);
                CameraDialogFragment.this.mTooltip.setX((float) (CameraDialogFragment.this.mXOffset + ((int) WishApplication.getInstance().getResources().getDimension(R.dimen.camera_dialog_fragment_tooltip_x_offset))));
                CameraDialogFragment.this.mTooltip.setY((float) (CameraDialogFragment.this.mYOffset + dimension));
                CameraDialogFragment.this.mContentContainer.setY((float) (CameraDialogFragment.this.mYOffset + CameraDialogFragment.this.mTooltip.getHeight() + dimension));
                window.setAttributes(attributes);
                CameraDialogFragment.this.mContentContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void performFadeAnimation() {
        this.mMainContainer.setAlpha(0.0f);
        this.mMainContainer.animate().alpha(1.0f).setDuration(250).setListener(new AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                CameraDialogFragment.this.hideDialog();
            }
        });
    }

    /* access modifiers changed from: private */
    public void hideDialog() {
        this.mMainContainer.animate().alpha(0.0f).setStartDelay(5000).setListener(new AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                CameraDialogFragment.this.dismissAllowingStateLoss();
            }
        });
    }

    /* access modifiers changed from: private */
    public void openExampleUgcIntroActivity() {
        Activity activity = getActivity();
        activity.startActivity(new Intent(activity, ExampleUgcIntroActivity.class));
    }

    public void onStart() {
        super.onStart();
        getDialog().getWindow().setBackgroundDrawableResource(17170445);
    }
}
