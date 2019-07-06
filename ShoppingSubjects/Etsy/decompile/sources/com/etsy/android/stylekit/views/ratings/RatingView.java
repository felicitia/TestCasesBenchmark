package com.etsy.android.stylekit.views.ratings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Dimension;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import com.etsy.android.stylekit.a.b;
import com.etsy.android.stylekit.a.h;
import com.etsy.android.stylekit.a.i;
import com.etsy.android.stylekit.a.j;
import com.etsy.android.stylekit.d;
import java.text.NumberFormat;

public class RatingView extends AppCompatTextView {
    private RatingDrawable mRatingDrawable;
    private int mReviewCount;
    @Dimension
    private int mReviewTextPadding;
    private boolean mShowIconsForZeroRating;
    private boolean mShowReviewCount;

    public RatingView(Context context) {
        super(context);
        init(null);
    }

    public RatingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public RatingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    private void init(@Nullable AttributeSet attributeSet) {
        setGravity(16);
        setIncludeFontPadding(false);
        setImportantForAccessibility(1);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, j.sk_RatingView);
        int i = obtainStyledAttributes.getInt(j.sk_RatingView_sk_maxRating, d.a(getContext(), b.token_rating_view__max_rating, 5));
        float f = obtainStyledAttributes.getFloat(j.sk_RatingView_sk_rating, 0.0f);
        RatingDrawable ratingDrawable = new RatingDrawable(getContext(), i, f, obtainStyledAttributes.getDimensionPixelSize(j.sk_RatingView_sk_iconSize, d.c(getContext(), b.token_rating_view__icon_size)), obtainStyledAttributes.getDimensionPixelSize(j.sk_RatingView_sk_iconSpacing, d.c(getContext(), b.token_rating_view__icon_spacing)));
        this.mRatingDrawable = ratingDrawable;
        this.mReviewTextPadding = obtainStyledAttributes.getDimensionPixelSize(j.sk_RatingView_sk_ratingTextPadding, d.c(getContext(), b.token_rating_view__review_count__text_padding));
        boolean z = obtainStyledAttributes.getBoolean(j.sk_RatingView_sk_showIconsForZeroRating, false);
        int i2 = obtainStyledAttributes.getInt(j.sk_RatingView_sk_reviewCount, 0);
        boolean z2 = obtainStyledAttributes.getBoolean(j.sk_RatingView_sk_showReviewCount, false);
        setCompoundDrawablePadding(this.mReviewTextPadding);
        setMaxHeight(this.mRatingDrawable.getIntrinsicHeight());
        showIconsForZeroRating(z);
        setRating(f);
        setReviewCount(i2);
        showReviewCount(z2);
        setupDrawables();
        setupReviewText();
        obtainStyledAttributes.recycle();
    }

    public void setRating(@FloatRange(from = 0.0d) float f) {
        if (this.mRatingDrawable.getRating() != f) {
            this.mRatingDrawable.setRating(f);
            setupDrawables();
            setupReviewText();
            invalidate();
        }
    }

    public void setReviewCount(@IntRange(from = 0) int i) {
        if (i != this.mReviewCount) {
            this.mReviewCount = i;
            setupReviewText();
            invalidate();
        }
    }

    private boolean ratingIconsVisible() {
        return this.mRatingDrawable.getRating() > 0.0f || this.mShowIconsForZeroRating;
    }

    public void setIconSize(@Dimension int i) {
        if (((float) i) != this.mRatingDrawable.getIconSize()) {
            this.mRatingDrawable.setIconSize(i);
            invalidate();
        }
    }

    public void showReviewCount(boolean z) {
        if (z != this.mShowReviewCount) {
            this.mShowReviewCount = z;
            setupReviewText();
        }
    }

    private void setupReviewText() {
        setText(this.mShowReviewCount ? getFormattedReviewCount() : "");
    }

    public void showIconsForZeroRating(boolean z) {
        if (z != this.mShowIconsForZeroRating) {
            this.mShowIconsForZeroRating = z;
            setupDrawables();
            setupReviewText();
            invalidate();
        }
    }

    private void setupDrawables() {
        setCompoundDrawablesWithIntrinsicBounds((this.mShowIconsForZeroRating || this.mRatingDrawable.getRating() != 0.0f) ? this.mRatingDrawable : null, null, null, null);
    }

    private String getFormattedReviewCount() {
        String format = NumberFormat.getIntegerInstance().format((long) this.mReviewCount);
        if (!ratingIconsVisible()) {
            format = getResources().getQuantityString(d.d(getContext(), b.token_rating_view__rating_count_plurals), this.mReviewCount, new Object[]{format});
        }
        return String.format("(%s)", new Object[]{format});
    }

    public int getReviewCount() {
        return this.mReviewCount;
    }

    public boolean isReviewCountVisible() {
        return this.mShowReviewCount;
    }

    public float getRating() {
        return this.mRatingDrawable.getRating();
    }

    public int getMaximumRating() {
        return this.mRatingDrawable.getMaxRating();
    }

    public float getIconVisualRating() {
        return this.mRatingDrawable.getIconVisualRating();
    }

    @SuppressLint({"StringFormatMatches"})
    public CharSequence getContentDescription() {
        String str;
        String str2;
        if (ratingIconsVisible()) {
            if (this.mShowReviewCount) {
                str2 = getResources().getQuantityString(h.content_description_rating_view_rating_and_reviews_count, this.mReviewCount, new Object[]{Float.valueOf(this.mRatingDrawable.getRating()), Integer.valueOf(this.mRatingDrawable.getMaxRating()), Integer.valueOf(this.mReviewCount)});
            } else {
                str2 = getResources().getString(i.content_description_rating_view, new Object[]{Float.valueOf(this.mRatingDrawable.getRating()), Integer.valueOf(this.mRatingDrawable.getMaxRating())});
            }
            return str2;
        }
        if (this.mShowReviewCount) {
            str = getResources().getQuantityString(d.d(getContext(), b.token_rating_view__rating_count_plurals), this.mReviewCount, new Object[]{Integer.valueOf(this.mReviewCount)});
        } else {
            str = null;
        }
        return str;
    }
}
