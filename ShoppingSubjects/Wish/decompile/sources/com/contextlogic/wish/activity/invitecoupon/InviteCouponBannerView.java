package com.contextlogic.wish.activity.invitecoupon;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.invite.InviteCouponActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ConfigDataCenter;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishInviteCouponSpec;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;

public class InviteCouponBannerView extends LinearLayout {
    private Button mButton;
    private TextView mDetail;
    private AutoReleasableImageView mImage;
    private View mRootLayout;
    private TextView mTitle;

    public InviteCouponBannerView(Context context) {
        this(context, null);
    }

    public InviteCouponBannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (ExperimentDataCenter.getInstance().canSeeInviteCouponBanner()) {
            init();
        }
    }

    public void init() {
        WishInviteCouponSpec inviteCouponSpec = ConfigDataCenter.getInstance().getInviteCouponSpec();
        Context context = getContext();
        getContext();
        this.mRootLayout = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.invite_coupon_banner_view, this);
        this.mImage = (AutoReleasableImageView) this.mRootLayout.findViewById(R.id.invite_coupon_banner_view_image);
        this.mTitle = (TextView) this.mRootLayout.findViewById(R.id.invite_coupon_banner_view_title);
        this.mDetail = (TextView) this.mRootLayout.findViewById(R.id.invite_coupon_banner_view_detail);
        this.mButton = (Button) this.mRootLayout.findViewById(R.id.invite_coupon_banner_view_button);
        this.mTitle.setText(inviteCouponSpec.getTitle());
        this.mDetail.setText(inviteCouponSpec.getShortMessage());
        this.mImage.setImageDrawable(WishApplication.getInstance().getResources().getDrawable(R.drawable.invite_seal));
        this.mButton.setText(inviteCouponSpec.getShareButtonText());
        this.mButton.setAllCaps(false);
        this.mButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_INVITE_COUPON_BANNER_SEND);
                Intent intent = new Intent();
                intent.setClass(InviteCouponBannerView.this.getContext(), InviteCouponActivity.class);
                InviteCouponBannerView.this.getContext().startActivity(intent);
            }
        });
    }
}
