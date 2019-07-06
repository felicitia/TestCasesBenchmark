package com.contextlogic.wish.dialog.addtocart.sizecolorselector;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import java.util.List;

public class ColorProductVariantSwatch extends BaseProductVariantSwatch {
    public static String[] FORCED_IMAGE_COLORS = {WishApplication.getInstance().getResources().getString(R.string.multicolor)};
    public static int[] FORCED_IMAGE_RES_IDS = {R.drawable.swatch_multicolor};
    private MultiColorCircle mColorCircle;
    private AutoReleasableImageView mForcedImageView;
    private View mSelectedRing;
    private GradientDrawable mSelectedRingBackground;

    public int getLayoutResourceId() {
        return R.layout.product_variant_color_swatch;
    }

    public static int getForcedImage(String str) {
        for (int i = 0; i < FORCED_IMAGE_COLORS.length; i++) {
            if (FORCED_IMAGE_COLORS[i].equalsIgnoreCase(str)) {
                return i;
            }
        }
        return -1;
    }

    public ColorProductVariantSwatch(Context context) {
        this(context, null);
    }

    public ColorProductVariantSwatch(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ColorProductVariantSwatch(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    /* access modifiers changed from: 0000 */
    public void init() {
        super.init();
        this.mColorCircle = (MultiColorCircle) findViewById(R.id.color_swatch_background_color);
        this.mSelectedRing = findViewById(R.id.color_swatch_selected_ring);
        this.mForcedImageView = (AutoReleasableImageView) findViewById(R.id.color_swatch_forced_image_view);
        this.mSelectedRingBackground = new GradientDrawable();
        this.mSelectedRingBackground.mutate();
        this.mSelectedRingBackground.setShape(1);
        this.mSelectedRingBackground.setColor(getResources().getColor(R.color.transparent));
        this.mSelectedRingBackground.setStroke(getResources().getDimensionPixelSize(R.dimen.size_color_selector_dialog_fragment_swatch_outline_thickness), getResources().getColor(R.color.wish_blue));
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.size_color_selector_dialog_fragment_color_swatch_ring_size) - getResources().getDimensionPixelOffset(R.dimen.size_color_selector_dialog_fragment_swatch_outline_thickness);
        this.mSelectedRingBackground.setSize(dimensionPixelOffset, dimensionPixelOffset);
        setSelected(false);
    }

    public void setEnabled(boolean z) {
        this.mColorCircle.setEnabled(z);
        this.mSelectedRing.setBackgroundColor(getResources().getColor(R.color.transparent));
        super.setEnabled(z);
    }

    public void setSelected(boolean z) {
        if (isEnabled()) {
            if (z) {
                this.mSelectedRing.setBackground(this.mSelectedRingBackground);
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PRODUCT_DETAILS_COLOR_SWATCH);
            } else {
                this.mSelectedRing.setBackgroundColor(getResources().getColor(R.color.transparent));
            }
            super.setSelected(z);
        }
    }

    private void hideColors() {
        this.mColorCircle.setVisibility(8);
        this.mForcedImageView.setVisibility(8);
    }

    public void setColors(List<Integer> list) {
        hideColors();
        this.mColorCircle.setVisibility(0);
        this.mColorCircle.setColors(list);
    }

    public void setForcedImage(int i, boolean z) {
        hideColors();
        this.mForcedImageView.setVisibility(0);
        this.mForcedImageView.setImageResource(i);
        this.mForcedImageView.setAlpha(z ? 1.0f : 0.25f);
    }
}
