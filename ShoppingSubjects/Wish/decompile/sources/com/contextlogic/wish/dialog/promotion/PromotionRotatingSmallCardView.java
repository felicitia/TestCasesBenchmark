package com.contextlogic.wish.dialog.promotion;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishPromotionRotatingSpec.SmallCardSpec;
import com.contextlogic.wish.api.model.WishTextViewSpec;
import com.contextlogic.wish.api.model.WishTimerTextViewSpec;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.timer.CountdownTimerView;
import com.contextlogic.wish.util.ColorUtil;

public class PromotionRotatingSmallCardView extends FrameLayout implements ImageRestorable {
    private ViewGroup mCard;
    private ThemedTextView mCategoryText;
    private View mDivider;
    private CountdownTimerView mTimerView;
    private LinearLayout mTitleContainer;

    public PromotionRotatingSmallCardView(Context context) {
        super(context);
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.promotion_rotating_small_card_view, this);
        this.mCard = (ViewGroup) inflate.findViewById(R.id.promotion_rotating_small_card);
        this.mTitleContainer = (LinearLayout) inflate.findViewById(R.id.promotion_rotating_small_card_title_container);
        this.mCategoryText = (ThemedTextView) inflate.findViewById(R.id.promotion_rotating_small_card_category);
        this.mDivider = inflate.findViewById(R.id.promotion_rotating_small_card_divider);
    }

    public void setup(SmallCardSpec smallCardSpec) {
        if (smallCardSpec != null) {
            this.mCard.setBackground(getCardBackgroundDrawable(smallCardSpec.getBackgroundColorString(), smallCardSpec.getHighlightColorString()));
            if (smallCardSpec.getHighlightColorString() != null && !smallCardSpec.getHighlightColorString().isEmpty()) {
                this.mTitleContainer.removeAllViews();
                Drawable titleFillBackgroundDrawable = getTitleFillBackgroundDrawable(smallCardSpec.getHighlightColorString());
                if (titleFillBackgroundDrawable != null) {
                    this.mTitleContainer.setBackground(titleFillBackgroundDrawable);
                }
            }
            addTitleTexts(this.mTitleContainer, smallCardSpec);
            formatCategoryText(this.mCategoryText, smallCardSpec);
            int safeParseColor = ColorUtil.safeParseColor(smallCardSpec.getDividerColorString(), 0);
            if (safeParseColor != 0) {
                this.mDivider.setVisibility(0);
                this.mDivider.setBackgroundColor(safeParseColor);
            } else {
                this.mDivider.setVisibility(8);
            }
            if (smallCardSpec.shouldDrawShadow()) {
                drawShadow();
            } else {
                removeShadow();
            }
        }
    }

    private void formatCategoryText(ThemedTextView themedTextView, SmallCardSpec smallCardSpec) {
        if (smallCardSpec != null && themedTextView != null && smallCardSpec.getCategoryTextSpec() != null) {
            WishTextViewSpec.applyTextViewSpec(themedTextView, smallCardSpec.getCategoryTextSpec());
            themedTextView.setGravity(17);
            themedTextView.setFontResizable(true);
            themedTextView.setMaxLines(1);
        }
    }

    private void addTitleTexts(LinearLayout linearLayout, SmallCardSpec smallCardSpec) {
        if (linearLayout != null && smallCardSpec != null) {
            linearLayout.removeAllViews();
            LayoutParams layoutParams = new LayoutParams(-2, -2);
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.two_padding);
            layoutParams.rightMargin = dimensionPixelSize;
            layoutParams.leftMargin = dimensionPixelSize;
            if (smallCardSpec.getTitleTextSpec() != null) {
                ThemedTextView newTextViewFromSpec = WishTextViewSpec.newTextViewFromSpec(getContext(), smallCardSpec.getTitleTextSpec());
                newTextViewFromSpec.setLayoutParams(layoutParams);
                newTextViewFromSpec.setGravity(17);
                newTextViewFromSpec.setFontResizable(true);
                newTextViewFromSpec.setMaxLines(1);
                linearLayout.addView(newTextViewFromSpec);
            }
            WishTimerTextViewSpec timerTextSpec = smallCardSpec.getTimerTextSpec();
            if (timerTextSpec != null) {
                if (timerTextSpec.shouldShowCountdown()) {
                    this.mTimerView = WishTimerTextViewSpec.newCountdownTimerViewFromSpec(getContext(), timerTextSpec);
                    if (this.mTimerView != null) {
                        this.mTimerView.startTimer();
                        this.mTimerView.setLayoutParams(layoutParams);
                        linearLayout.addView(this.mTimerView);
                    }
                } else {
                    ThemedTextView newTextViewFromSpec2 = WishTimerTextViewSpec.newTextViewFromSpec(getContext(), timerTextSpec);
                    if (newTextViewFromSpec2 != null) {
                        newTextViewFromSpec2.setLayoutParams(layoutParams);
                        linearLayout.addView(newTextViewFromSpec2);
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

    private void drawShadow() {
        Drawable drawable = getResources().getDrawable(R.drawable.rounded_rect_shadow_bg);
        drawable.setAlpha(63);
        setBackground(drawable);
    }

    private void removeShadow() {
        setBackground(null);
    }

    private Drawable getTitleFillBackgroundDrawable(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(ColorUtil.safeParseColor(str, -1));
        float dimension = getResources().getDimension(R.dimen.promotion_rotating_card_corner_radius);
        gradientDrawable.setCornerRadii(new float[]{dimension, dimension, dimension, dimension, 0.0f, 0.0f, 0.0f, 0.0f});
        return gradientDrawable;
    }

    public void releaseImages() {
        if (this.mTimerView != null) {
            this.mTimerView.pauseTimer();
        }
    }

    public void restoreImages() {
        if (this.mTimerView != null) {
            this.mTimerView.startTimer();
        }
    }
}
