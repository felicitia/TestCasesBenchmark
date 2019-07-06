package com.contextlogic.wish.activity.commerceloan;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer.DrawableContainerState;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.cart.CartFragment;
import com.contextlogic.wish.activity.cart.billing.CartBaseBillingOptionSelectorView.CartBillingSection;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishCommerceLoanBannerSpec;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.ui.button.ThemedButton;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class CommerceLoanBannerView extends FrameLayout {
    private static int STATE_FOCUSED = 2;
    private static int STATE_NORMAL = 0;
    private static int STATE_PRESSED = 1;
    private NetworkImageView mBackgroundImage;
    private ThemedTextView mBannerDescription;
    private ThemedTextView mBannerTitle;
    private FrameLayout mContainer;
    private ThemedButton mTryItButton;

    public CommerceLoanBannerView(Context context) {
        this(context, null);
    }

    public CommerceLoanBannerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CommerceLoanBannerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.commerce_loan_banner_view, this);
        this.mContainer = (FrameLayout) findViewById(R.id.commerce_loan_banner_container);
        this.mBannerTitle = (ThemedTextView) findViewById(R.id.commerce_loan_banner_title);
        this.mBannerDescription = (ThemedTextView) findViewById(R.id.commerce_loan_banner_description);
        this.mTryItButton = (ThemedButton) findViewById(R.id.commerce_loan_try_it_button);
        this.mBackgroundImage = (NetworkImageView) findViewById(R.id.commerce_loan_banner_background_image);
    }

    public void setup(WishCommerceLoanBannerSpec wishCommerceLoanBannerSpec, final CartFragment cartFragment) {
        if (wishCommerceLoanBannerSpec != null) {
            int parseColor = Color.parseColor(wishCommerceLoanBannerSpec.getBackgroundColor());
            this.mContainer.setBackgroundColor(parseColor);
            this.mBannerTitle.setText(wishCommerceLoanBannerSpec.getTitle());
            this.mBannerDescription.setText(wishCommerceLoanBannerSpec.getDescription());
            this.mBackgroundImage.setImage(new WishImage(wishCommerceLoanBannerSpec.getBackgroundImageUrl()));
            this.mTryItButton.setText(wishCommerceLoanBannerSpec.getButtonText());
            setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_COMMERCE_LOAN_BANNER);
                    cartFragment.showBillingView(false, CartBillingSection.COMMERCE_LOAN);
                }
            });
            this.mTryItButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_COMMERCE_LOAN_BANNER);
                    cartFragment.showBillingView(false, CartBillingSection.COMMERCE_LOAN);
                }
            });
            setupButtonBackground(parseColor);
        }
    }

    private void setupButtonBackground(int i) {
        Drawable[] children = ((DrawableContainerState) ((StateListDrawable) this.mTryItButton.getBackground()).getConstantState()).getChildren();
        ((GradientDrawable) children[STATE_NORMAL]).setColor(i);
        ((GradientDrawable) children[STATE_PRESSED]).setColor(manipulateColor(i, 0.8f));
        ((GradientDrawable) children[STATE_FOCUSED]).setColor(manipulateColor(i, 0.8f));
    }

    private int manipulateColor(int i, float f) {
        return Color.argb(Color.alpha(i), Math.min(Math.round(((float) Color.red(i)) * f), 255), Math.min(Math.round(((float) Color.green(i)) * f), 255), Math.min(Math.round(((float) Color.blue(i)) * f), 255));
    }
}
