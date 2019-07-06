package com.contextlogic.wish.dialog.addtocart.sizecolorselector;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;

public abstract class BaseProductVariantSwatch extends FrameLayout {
    protected ThemedTextView mPriceLabel;
    protected GradientDrawable mWishExpressIdleRingBackground;
    protected GradientDrawable mWishExpressSelectedRingBackground;
    protected AutoReleasableImageView mWishExpressTruck;
    protected View mWishExpressTruckSelectedRing;

    public abstract int getLayoutResourceId();

    public BaseProductVariantSwatch(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(getLayoutResourceId(), this);
    }

    /* access modifiers changed from: 0000 */
    public void init() {
        this.mWishExpressTruck = (AutoReleasableImageView) findViewById(R.id.base_swatch_wish_express_icon);
        this.mWishExpressSelectedRingBackground = new GradientDrawable();
        this.mWishExpressSelectedRingBackground.mutate();
        this.mWishExpressSelectedRingBackground.setShape(1);
        this.mWishExpressSelectedRingBackground.setColor(getResources().getColor(R.color.white));
        this.mWishExpressSelectedRingBackground.setStroke(getResources().getDimensionPixelSize(R.dimen.size_color_selector_dialog_fragment_swatch_outline_thickness), getResources().getColor(R.color.wish_blue));
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.size_color_selector_dialog_fragment_swatch_wish_express_total_size) - getResources().getDimensionPixelSize(R.dimen.size_color_selector_dialog_fragment_swatch_selected_ring_thickness);
        this.mWishExpressSelectedRingBackground.setSize(dimensionPixelSize, dimensionPixelSize);
        this.mWishExpressIdleRingBackground = new GradientDrawable();
        this.mWishExpressIdleRingBackground.mutate();
        this.mWishExpressIdleRingBackground.setShape(1);
        this.mWishExpressIdleRingBackground.setColor(getResources().getColor(R.color.white));
        this.mWishExpressIdleRingBackground.setStroke(getResources().getDimensionPixelSize(R.dimen.size_color_selector_dialog_fragment_swatch_outline_thickness), getResources().getColor(R.color.gray5));
        this.mWishExpressIdleRingBackground.setSize(dimensionPixelSize, dimensionPixelSize);
        this.mWishExpressTruckSelectedRing = findViewById(R.id.base_swatch_wish_express_selected_ring);
        this.mWishExpressTruckSelectedRing.setBackground(this.mWishExpressIdleRingBackground);
        this.mPriceLabel = (ThemedTextView) findViewById(R.id.base_swatch_price_label);
    }

    public void setEnabled(boolean z) {
        if (z) {
            this.mWishExpressTruckSelectedRing.setBackground(this.mWishExpressIdleRingBackground);
            this.mPriceLabel.setTypeface(0);
            this.mPriceLabel.setTextColor(getResources().getColor(R.color.gray1));
        } else {
            this.mWishExpressTruckSelectedRing.setBackground(this.mWishExpressIdleRingBackground);
            this.mPriceLabel.setTypeface(0);
            this.mPriceLabel.setTextColor(getResources().getColor(R.color.gray4));
        }
        super.setEnabled(z);
    }

    public void setSelected(boolean z) {
        if (!z) {
            this.mWishExpressTruckSelectedRing.setBackground(this.mWishExpressIdleRingBackground);
            this.mPriceLabel.setTypeface(0);
            this.mPriceLabel.setTextColor(getResources().getColor(R.color.gray1));
        } else if (isEnabled()) {
            this.mWishExpressTruckSelectedRing.setBackground(this.mWishExpressSelectedRingBackground);
            this.mPriceLabel.setTypeface(1);
            this.mPriceLabel.setTextColor(getResources().getColor(R.color.wish_blue));
        } else {
            return;
        }
        super.setSelected(z);
    }

    public void setWishExpress(boolean z) {
        if (z) {
            this.mWishExpressTruckSelectedRing.setVisibility(0);
        } else {
            this.mWishExpressTruckSelectedRing.setVisibility(4);
        }
    }

    public void setPriceText(String str) {
        this.mPriceLabel.setText(str);
        this.mPriceLabel.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
    }
}
