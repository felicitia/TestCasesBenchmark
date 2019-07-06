package com.contextlogic.wish.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.contextlogic.wish.R;

public class WishCardView extends FrameLayout {
    private int mCardColor;
    private float mCardCornerRadius;
    private GradientDrawable mCardDrawable;
    private int[] mContentPadding;
    private ViewGroup mContentView;
    private float mShadowAlpha;
    private Drawable mShadowDrawable;
    private int[] mShadowSize;
    private int mStrokeColor;
    private int mStrokeSize;

    /* access modifiers changed from: protected */
    public void bindViews(View view) {
    }

    /* access modifiers changed from: protected */
    public int getContentLayoutResourceId() {
        return 0;
    }

    public WishCardView(Context context) {
        this(context, null);
    }

    public WishCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WishCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        handleAttributeSet(context, attributeSet);
        init();
    }

    private void init() {
        LayoutInflater from = LayoutInflater.from(getContext());
        if (from != null) {
            if (getContentLayoutResourceId() == 0) {
                this.mContentView = new FrameLayout(getContext());
            } else {
                this.mContentView = (ViewGroup) from.inflate(getContentLayoutResourceId(), this, false);
                this.mContentPadding = new int[]{this.mContentView.getPaddingLeft(), this.mContentView.getPaddingTop(), this.mContentView.getPaddingRight(), this.mContentView.getPaddingBottom()};
            }
            super.addView(this.mContentView, 0, new LayoutParams(-1, -1));
            if (this.mContentView != null) {
                drawBackground();
                drawShadow();
                bindViews(this.mContentView);
            }
        }
    }

    private void handleAttributeSet(Context context, AttributeSet attributeSet) {
        if (context != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.WishCardView);
            this.mCardCornerRadius = obtainStyledAttributes.getDimension(1, getResources().getDimension(R.dimen.wish_card_default_corner));
            this.mCardColor = obtainStyledAttributes.getColor(0, -1);
            this.mStrokeColor = obtainStyledAttributes.getColor(8, ContextCompat.getColor(getContext(), R.color.wish_card_default_stroke));
            this.mStrokeSize = obtainStyledAttributes.getDimensionPixelSize(9, getResources().getDimensionPixelSize(R.dimen.wish_card_default_stroke));
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.wish_card_default_shadow);
            if (obtainStyledAttributes.hasValue(3)) {
                int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(3, dimensionPixelSize);
                this.mShadowSize = new int[]{dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2};
            } else {
                this.mShadowSize = new int[]{obtainStyledAttributes.getDimensionPixelSize(5, dimensionPixelSize), obtainStyledAttributes.getDimensionPixelSize(7, dimensionPixelSize), obtainStyledAttributes.getDimensionPixelSize(6, dimensionPixelSize), obtainStyledAttributes.getDimensionPixelSize(4, dimensionPixelSize)};
            }
            this.mShadowAlpha = obtainStyledAttributes.getFloat(2, 0.15f);
            obtainStyledAttributes.recycle();
        }
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        if (this.mContentView != null) {
            this.mContentView.addView(view, i, layoutParams);
        }
    }

    private void drawBackground() {
        if (this.mCardDrawable == null) {
            this.mCardDrawable = new GradientDrawable();
            this.mCardDrawable.setShape(0);
        }
        if (this.mContentPadding == null) {
            this.mContentPadding = new int[]{0, 0, 0, 0};
        }
        this.mCardDrawable.setColor(this.mCardColor);
        this.mCardDrawable.setCornerRadius(this.mCardCornerRadius);
        this.mCardDrawable.setStroke(this.mStrokeSize, this.mStrokeColor);
        this.mContentView.setPadding(this.mContentPadding[0] + this.mStrokeSize, this.mContentPadding[1] + this.mStrokeSize, this.mContentPadding[2] + this.mStrokeSize, this.mContentPadding[3] + this.mStrokeSize);
        this.mContentView.setBackground(this.mCardDrawable);
    }

    private void drawShadow() {
        this.mShadowDrawable = getResources().getDrawable(R.drawable.rounded_rect_shadow_bg);
        this.mShadowDrawable.setAlpha((int) (this.mShadowAlpha * 255.0f));
        super.setPadding(this.mShadowSize[0], this.mShadowSize[1], this.mShadowSize[2], this.mShadowSize[3]);
        setBackground(this.mShadowDrawable);
    }

    public void setShadowAlpha(float f) {
        if (this.mShadowDrawable != null) {
            this.mShadowAlpha = f;
            this.mShadowDrawable.setAlpha((int) (this.mShadowAlpha * 255.0f));
        }
    }

    public void setShadowSize(int i) {
        this.mShadowSize = new int[]{i, i, i, i};
        setPadding(i, i, i, i);
    }

    public void setCornerRadius(float f) {
        this.mCardCornerRadius = f;
        drawBackground();
    }

    public void setCardColor(int i) {
        this.mCardColor = i;
        drawBackground();
    }

    public void setStrokeSize(int i) {
        this.mStrokeSize = i;
        drawBackground();
    }

    public void setStrokeColor(int i) {
        this.mStrokeColor = i;
        drawBackground();
    }
}
