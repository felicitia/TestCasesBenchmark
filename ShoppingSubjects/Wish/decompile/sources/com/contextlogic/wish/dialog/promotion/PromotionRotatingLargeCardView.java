package com.contextlogic.wish.dialog.promotion;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishPromotionRotatingSpec.LargeCardSpec;
import com.contextlogic.wish.api.model.WishTextViewSpec;
import com.contextlogic.wish.api.model.WishTimerTextViewSpec;
import com.contextlogic.wish.ui.image.ContainerRestorable;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.timer.CountdownTimerView;
import com.contextlogic.wish.util.ColorUtil;
import com.contextlogic.wish.util.DisplayUtil;
import java.util.ArrayList;
import java.util.Iterator;

public class PromotionRotatingLargeCardView extends LinearLayout implements ImageRestorable {
    private ViewGroup mCard;
    private LinearLayout mImagesContainer;
    private CountdownTimerView mTimerView;
    private LinearLayout mTitleContainer;

    public PromotionRotatingLargeCardView(Context context) {
        super(context);
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.promotion_rotating_large_card_view, this);
        this.mCard = (ViewGroup) inflate.findViewById(R.id.promotion_rotating_large_card);
        this.mTitleContainer = (LinearLayout) inflate.findViewById(R.id.promotion_rotating_large_card_title_container);
        this.mImagesContainer = (LinearLayout) inflate.findViewById(R.id.promotion_rotating_large_card_image_container);
        setLayoutParams(new LayoutParams(getCardWidth(), -2));
    }

    public void setup(LargeCardSpec largeCardSpec) {
        if (largeCardSpec == null) {
            setVisibility(8);
            return;
        }
        this.mCard.setBackground(getCardBackgroundDrawable(largeCardSpec.getBackgroundColorString(), largeCardSpec.getHighlightColorString()));
        if (largeCardSpec.getHighlightColorString() != null && !largeCardSpec.getHighlightColorString().isEmpty()) {
            Drawable titleFillBackgroundDrawable = getTitleFillBackgroundDrawable(largeCardSpec.getHighlightColorString());
            if (titleFillBackgroundDrawable != null) {
                this.mTitleContainer.setBackground(titleFillBackgroundDrawable);
            }
        }
        addTitleTexts(this.mTitleContainer, largeCardSpec);
        addProductImageTiles(this.mImagesContainer, largeCardSpec.getProductImageUrls());
        if (largeCardSpec.shouldDrawShadow()) {
            drawShadow();
        } else {
            removeShadow();
        }
    }

    private void addTitleTexts(LinearLayout linearLayout, LargeCardSpec largeCardSpec) {
        if (linearLayout != null && largeCardSpec != null) {
            linearLayout.removeAllViews();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.two_padding);
            layoutParams.rightMargin = dimensionPixelSize;
            layoutParams.leftMargin = dimensionPixelSize;
            layoutParams.weight = 1.0f;
            if (largeCardSpec.getTitleTextSpec() != null) {
                ThemedTextView newTextViewFromSpec = WishTextViewSpec.newTextViewFromSpec(getContext(), largeCardSpec.getTitleTextSpec());
                newTextViewFromSpec.setLayoutParams(layoutParams);
                newTextViewFromSpec.setGravity(17);
                newTextViewFromSpec.setFontResizable(true);
                newTextViewFromSpec.setMaxLines(1);
                linearLayout.addView(newTextViewFromSpec);
            }
            if (largeCardSpec.getCategoryTextSpec() != null) {
                ThemedTextView newTextViewFromSpec2 = WishTextViewSpec.newTextViewFromSpec(getContext(), largeCardSpec.getCategoryTextSpec());
                newTextViewFromSpec2.setLayoutParams(layoutParams);
                newTextViewFromSpec2.setGravity(17);
                newTextViewFromSpec2.setFontResizable(true);
                newTextViewFromSpec2.setMaxLines(1);
                linearLayout.addView(newTextViewFromSpec2);
            }
            WishTimerTextViewSpec timerTextSpec = largeCardSpec.getTimerTextSpec();
            if (timerTextSpec != null) {
                if (timerTextSpec.shouldShowCountdown()) {
                    this.mTimerView = WishTimerTextViewSpec.newCountdownTimerViewFromSpec(getContext(), timerTextSpec);
                    if (this.mTimerView != null) {
                        this.mTimerView.startTimer();
                        this.mTimerView.setLayoutParams(layoutParams);
                        linearLayout.addView(this.mTimerView);
                    }
                } else {
                    ThemedTextView newTextViewFromSpec3 = WishTimerTextViewSpec.newTextViewFromSpec(getContext(), timerTextSpec);
                    if (newTextViewFromSpec3 != null) {
                        newTextViewFromSpec3.setLayoutParams(layoutParams);
                        linearLayout.addView(newTextViewFromSpec3);
                    }
                }
            }
        }
    }

    private Drawable getCardBackgroundDrawable(String str, String str2) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(ColorUtil.safeParseColor(str, -1));
        gradientDrawable.setCornerRadius(getResources().getDimension(R.dimen.promotion_rotating_card_corner_radius));
        int safeParseColor = ColorUtil.safeParseColor(str2, 0);
        if (safeParseColor != 0) {
            gradientDrawable.setStroke(getResources().getDimensionPixelOffset(R.dimen.promotion_rotating_card_highlight_radius), safeParseColor);
        }
        return gradientDrawable;
    }

    private Drawable getTitleFillBackgroundDrawable(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(ColorUtil.safeParseColor(str, -1));
        float dimension = getResources().getDimension(R.dimen.promotion_rotating_card_corner_radius);
        gradientDrawable.setCornerRadii(new float[]{0.0f, 0.0f, 0.0f, 0.0f, dimension, dimension, dimension, dimension});
        return gradientDrawable;
    }

    private void addProductImageTiles(LinearLayout linearLayout, ArrayList<String> arrayList) {
        if (linearLayout != null && arrayList != null && !arrayList.isEmpty()) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.promotion_rotating_card_product_image_size), getResources().getDimensionPixelSize(R.dimen.promotion_rotating_card_product_image_size));
            layoutParams.weight = 1.0f;
            linearLayout.removeAllViews();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                NetworkImageView networkImageView = new NetworkImageView(getContext());
                networkImageView.setImageUrl(str);
                networkImageView.setLayoutParams(layoutParams);
                linearLayout.addView(networkImageView);
            }
        }
    }

    private void drawShadow() {
        Drawable drawable = getResources().getDrawable(R.drawable.rounded_rect_shadow_bg);
        drawable.setAlpha(114);
        setBackground(drawable);
    }

    private void removeShadow() {
        setBackground(null);
    }

    public void releaseImages() {
        ContainerRestorable.releaseChildren(this.mImagesContainer);
        if (this.mTimerView != null) {
            this.mTimerView.pauseTimer();
        }
    }

    public void restoreImages() {
        ContainerRestorable.restoreChildren(this.mImagesContainer);
        if (this.mTimerView != null) {
            this.mTimerView.startTimer();
        }
    }

    private int getCardWidth() {
        float f;
        float displayWidth = (float) DisplayUtil.getDisplayWidth();
        if (displayWidth < ((float) DisplayUtil.getDisplayHeight())) {
            f = getResources().getFraction(R.fraction.dialog_min_width_minor, 1, 1);
        } else {
            f = getResources().getFraction(R.fraction.dialog_min_width_major, 1, 1);
        }
        return (int) (displayWidth * f);
    }
}
