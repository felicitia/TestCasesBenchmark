package com.contextlogic.wish.activity.invitecoupon;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ConfigDataCenter;
import com.contextlogic.wish.api.model.WishInviteCouponSpec;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.ClipboardUtil;
import com.contextlogic.wish.util.IntentUtil;
import com.contextlogic.wish.util.PreferenceUtil;

public class InviteCouponView extends LinearLayout {
    private LinearLayout mCodeContainer;
    private ThemedTextView mCodeText;
    /* access modifiers changed from: private */
    public ThemedTextView mCopiedText;
    private AutoReleasableImageView mImage;
    private ThemedTextView mMessageText;
    /* access modifiers changed from: private */
    public CheckBox mNeverShowCheckBox;
    private OnDismissListener mOnDismissListener;
    /* access modifiers changed from: private */
    public OnNeverShowListener mOnNeverShowListener;
    private LinearLayout mRemindContainer;
    private ThemedTextView mRemindText;
    private View mRootLayout;
    private ThemedTextView mSendButton;
    private ThemedTextView mTitleText;

    public interface OnDismissListener {
        void onDismiss();
    }

    public interface OnNeverShowListener {
        void onNeverShow();
    }

    public InviteCouponView(Context context) {
        this(context, null);
    }

    public InviteCouponView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public void init() {
        Context context = getContext();
        getContext();
        this.mRootLayout = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.invite_coupon_view, this);
        setOrientation(1);
        this.mImage = (AutoReleasableImageView) this.mRootLayout.findViewById(R.id.invite_coupon_view_image);
        this.mTitleText = (ThemedTextView) this.mRootLayout.findViewById(R.id.invite_coupon_view_title);
        this.mMessageText = (ThemedTextView) this.mRootLayout.findViewById(R.id.invite_coupon_view_message);
        this.mCopiedText = (ThemedTextView) this.mRootLayout.findViewById(R.id.invite_coupon_view_copied);
        this.mCodeText = (ThemedTextView) this.mRootLayout.findViewById(R.id.invite_coupon_view_code);
        this.mCodeContainer = (LinearLayout) this.mRootLayout.findViewById(R.id.invite_coupon_view_code_container);
        this.mSendButton = (ThemedTextView) this.mRootLayout.findViewById(R.id.invite_coupon_view_send_button);
        this.mRemindText = (ThemedTextView) this.mRootLayout.findViewById(R.id.invite_coupon_view_remind);
        this.mNeverShowCheckBox = (CheckBox) this.mRootLayout.findViewById(R.id.invite_coupon_view_checkbox);
        this.mRemindContainer = (LinearLayout) this.mRootLayout.findViewById(R.id.invite_coupon_view_remind_container);
    }

    private void setupForFullScreen() {
        Resources resources = WishApplication.getInstance().getResources();
        WishInviteCouponSpec inviteCouponSpec = ConfigDataCenter.getInstance().getInviteCouponSpec();
        this.mImage.getLayoutParams().height = (int) resources.getDimension(R.dimen.invite_coupon_fragment_image_size);
        this.mImage.getLayoutParams().width = (int) resources.getDimension(R.dimen.invite_coupon_fragment_image_size);
        this.mSendButton.getLayoutParams().width = (int) resources.getDimension(R.dimen.invite_coupon_fragment_button_width);
        this.mCodeContainer.getLayoutParams().width = (int) resources.getDimension(R.dimen.invite_coupon_fragment_button_width);
        this.mTitleText.setTextSize(0, resources.getDimension(R.dimen.text_size_large_title));
        this.mMessageText.setText(inviteCouponSpec.getMessage());
    }

    public void hideDismiss() {
        this.mRemindText.setVisibility(8);
        this.mRemindContainer.setVisibility(8);
    }

    public void setup(boolean z) {
        final WishInviteCouponSpec inviteCouponSpec = ConfigDataCenter.getInstance().getInviteCouponSpec();
        if (inviteCouponSpec != null) {
            this.mTitleText.setText(inviteCouponSpec.getTitle());
            this.mMessageText.setText(inviteCouponSpec.getShortMessage());
            this.mCodeText.setText(inviteCouponSpec.getCode());
            this.mCodeContainer.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_INVITE_BY_COUPON_COPY);
                    ClipboardUtil.copyToClipboard(inviteCouponSpec.getCode(), inviteCouponSpec.getCode());
                    InviteCouponView.this.animateCopiedText();
                }
            });
            this.mSendButton.setText(inviteCouponSpec.getShareButtonText());
            this.mSendButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_INVITE_BY_COUPON_SEND_INVITES);
                    InviteCouponView.this.getContext().startActivity(IntentUtil.getShareIntent(inviteCouponSpec.getShareSubject(), inviteCouponSpec.getShareText()));
                }
            });
            this.mRemindText.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_INVITE_COUPON_REMIND);
                    InviteCouponView.this.finishView();
                }
            });
            this.mNeverShowCheckBox.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_INVITE_COUPON_NEVER_SHOW);
                    if (InviteCouponView.this.mOnNeverShowListener == null) {
                        PreferenceUtil.setBoolean("NeverShowInviteCouponPopup", true);
                    } else {
                        InviteCouponView.this.mOnNeverShowListener.onNeverShow();
                    }
                    InviteCouponView.this.mNeverShowCheckBox.setClickable(false);
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            InviteCouponView.this.finishView();
                        }
                    }, 1000);
                }
            });
            if (z) {
                setupForFullScreen();
                return;
            }
            return;
        }
        finishView();
    }

    /* access modifiers changed from: private */
    public void animateCopiedText() {
        this.mCopiedText.setText(WishApplication.getInstance().getString(R.string.copied));
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(200);
        scaleAnimation.setInterpolator(new DecelerateInterpolator());
        final ScaleAnimation scaleAnimation2 = new ScaleAnimation(1.5f, 1.0f, 1.5f, 1.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation2.setDuration(500);
        scaleAnimation2.setInterpolator(new BounceInterpolator());
        scaleAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                InviteCouponView.this.mCopiedText.startAnimation(scaleAnimation2);
            }
        });
        this.mCopiedText.startAnimation(scaleAnimation);
    }

    /* access modifiers changed from: private */
    public void finishView() {
        this.mOnDismissListener.onDismiss();
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }

    public void setOnNeverShowListener(OnNeverShowListener onNeverShowListener) {
        this.mOnNeverShowListener = onNeverShowListener;
    }
}
