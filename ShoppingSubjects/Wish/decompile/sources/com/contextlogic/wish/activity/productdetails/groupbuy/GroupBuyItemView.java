package com.contextlogic.wish.activity.productdetails.groupbuy;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.productdetails.groupbuy.GroupBuyView.BuyCallback;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishGroupBuyRowInfo;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.timer.CountdownTimerView;
import com.contextlogic.wish.ui.view.ProfileImageView;

public class GroupBuyItemView extends LinearLayout {
    private RelativeLayout mBackgroundContainer;
    private ThemedTextView mButton;
    private View mFadeView;
    private ThemedTextView mPrice;
    private ProfileImageView mProfileImageView;
    private ThemedTextView mSubText;
    private CountdownTimerView mTimerView;
    private ThemedTextView mTitle;

    public GroupBuyItemView(Context context) {
        super(context);
        init();
    }

    public GroupBuyItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public GroupBuyItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.product_details_group_buy_item_view, this);
        setOrientation(1);
        setGravity(17);
        setLayoutParams(new LayoutParams(-1, -2));
        this.mTimerView = (CountdownTimerView) inflate.findViewById(R.id.product_details_group_buy_item_view_timer);
        this.mProfileImageView = (ProfileImageView) inflate.findViewById(R.id.product_details_group_buy_item_view_profile_image_view);
        this.mTitle = (ThemedTextView) inflate.findViewById(R.id.product_details_group_buy_item_view_title);
        this.mSubText = (ThemedTextView) inflate.findViewById(R.id.product_details_group_buy_item_view_sub_text);
        this.mPrice = (ThemedTextView) inflate.findViewById(R.id.product_details_group_buy_item_view_price);
        this.mButton = (ThemedTextView) inflate.findViewById(R.id.product_details_group_buy_item_view_button);
        this.mFadeView = inflate.findViewById(R.id.product_details_group_buy_item_view_user_image_fade);
        this.mBackgroundContainer = (RelativeLayout) inflate.findViewById(R.id.product_details_group_buy_item_bg_container);
    }

    public void setup(final WishGroupBuyRowInfo wishGroupBuyRowInfo, String str, final BuyCallback buyCallback) {
        if (wishGroupBuyRowInfo.getExpiry() != null) {
            this.mTimerView.setDigitPadding(0);
            this.mTimerView.setColonPadding(WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.four_padding));
            this.mTimerView.setup(wishGroupBuyRowInfo.getExpiry(), getResources().getDimensionPixelSize(R.dimen.add_to_cart_offer_group_buy_row_timer_height), getResources().getColor(R.color.main_primary), getResources().getColor(R.color.white), getResources().getColor(R.color.white));
            this.mTimerView.startTimer();
        } else {
            this.mTimerView.setVisibility(8);
        }
        this.mProfileImageView.setup(wishGroupBuyRowInfo.getUserImage(), wishGroupBuyRowInfo.getUserName());
        this.mTitle.setText(wishGroupBuyRowInfo.getTitle());
        if (wishGroupBuyRowInfo.getMessage() == null) {
            this.mSubText.setVisibility(8);
        } else {
            this.mSubText.setText(wishGroupBuyRowInfo.getMessage());
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mPrice.getLayoutParams();
        if (wishGroupBuyRowInfo.getButtonText() == null || buyCallback == null) {
            this.mButton.setVisibility(8);
            layoutParams.addRule(11);
        } else {
            this.mButton.setText(wishGroupBuyRowInfo.getButtonText());
            this.mButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    buyCallback.onBuy(wishGroupBuyRowInfo.getGroupBuyId());
                }
            });
            layoutParams.addRule(0, R.id.product_details_group_buy_item_view_button);
        }
        this.mPrice.setText(str);
        this.mPrice.setLayoutParams(layoutParams);
        if (ExperimentDataCenter.getInstance().shouldSeeGroupBuyRedesign()) {
            this.mPrice.setVisibility(8);
        }
    }

    public void releaseImages() {
        if (this.mProfileImageView != null) {
            this.mProfileImageView.releaseImages();
        }
        if (this.mTimerView != null) {
            this.mTimerView.pauseTimer();
        }
    }

    public void restoreImages() {
        if (this.mProfileImageView != null) {
            this.mProfileImageView.restoreImages();
        }
        if (this.mTimerView != null) {
            this.mTimerView.startTimer();
        }
    }

    public void fadeView() {
        this.mFadeView.setVisibility(0);
        this.mBackgroundContainer.setBackground(WishApplication.getInstance().getResources().getDrawable(R.drawable.faded_group_buy_tile_bg));
    }

    public void learnMoreSetup() {
        this.mTimerView.setVisibility(8);
        this.mButton.setClickable(false);
    }
}
