package com.etsy.android.uikit.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.etsy.android.lib.a.e;
import com.etsy.android.lib.a.g;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.a.q;
import com.etsy.android.uikit.c;

public class RatingIconView extends LinearLayout {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int DEFAULT_NUM_STARS = 5;
    private Drawable mEmptyStar;
    private Drawable mFullStar;
    private Drawable mHalfStar;
    private int mNumStars = 5;
    private float mRating;
    private int mStarSize;
    private int mStarSpacing;

    public RatingIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public RatingIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        if (!isInEditMode()) {
            setOrientation(0);
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, q.RatingStarBar);
            this.mStarSize = obtainStyledAttributes.getDimensionPixelSize(q.RatingStarBar_starSize, 0);
            this.mStarSpacing = obtainStyledAttributes.getDimensionPixelSize(q.RatingStarBar_starSpacing, 0);
            this.mRating = (float) obtainStyledAttributes.getInt(q.RatingStarBar_rating, 0);
            obtainStyledAttributes.recycle();
            this.mEmptyStar = c.a(getContext(), g.sk_ic_star, e.star_empty);
            this.mFullStar = c.a(getContext(), g.sk_ic_star, e.star_filled);
            this.mHalfStar = new LayerDrawable(new Drawable[]{this.mEmptyStar, c.a(getContext(), g.sk_ic_halfstar, e.star_filled)});
            setRating(this.mRating);
        }
    }

    public void setNumStars(int i) {
        this.mNumStars = i;
        updateView();
    }

    public void setRating(float f) {
        this.mRating = f;
        if (this.mRating < 0.0f) {
            this.mRating = 0.0f;
        }
        updateView();
    }

    public float getRating() {
        return this.mRating;
    }

    private void updateView() {
        removeAllViews();
        if (this.mNumStars > 0) {
            int round = Math.round(this.mRating * 2.0f);
            int i = 0;
            while (i < this.mNumStars * 2) {
                ImageView imageView = new ImageView(getContext());
                LayoutParams layoutParams = new LayoutParams(this.mStarSize, this.mStarSize);
                if (i > 0) {
                    layoutParams.leftMargin = this.mStarSpacing;
                }
                imageView.setLayoutParams(layoutParams);
                int i2 = i + 2;
                if (i2 <= round) {
                    imageView.setImageDrawable(this.mFullStar);
                } else if (i > round) {
                    imageView.setImageDrawable(this.mEmptyStar);
                } else if (i + 1 == round) {
                    imageView.setImageDrawable(this.mHalfStar);
                } else {
                    imageView.setImageDrawable(this.mEmptyStar);
                }
                addView(imageView);
                i = i2;
            }
        }
        setContentDescription(getResources().getString(o.star_rating, new Object[]{Integer.valueOf(this.mNumStars)}));
    }
}
