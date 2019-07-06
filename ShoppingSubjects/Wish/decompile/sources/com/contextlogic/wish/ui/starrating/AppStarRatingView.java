package com.contextlogic.wish.ui.starrating;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.starrating.StarRatingView.Callback;
import com.contextlogic.wish.ui.starrating.StarRatingView.Size;
import java.util.ArrayList;

public class AppStarRatingView extends StarRatingView {
    /* access modifiers changed from: private */
    public Callback mCallback;
    private int mSelectedStarResId;
    /* access modifiers changed from: private */
    public int mSelection;
    private Size mSize;
    private int mSpaceBetweenStars;
    private int mUnselectedStarResId;

    public AppStarRatingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.AppStarRatingView, 0, 0);
        try {
            this.mSelectedStarResId = obtainStyledAttributes.getResourceId(0, R.drawable.app_rate_selected_star);
            this.mUnselectedStarResId = obtainStyledAttributes.getResourceId(2, R.drawable.app_rate_unselected_star);
            this.mSpaceBetweenStars = obtainStyledAttributes.getResourceId(1, R.dimen.eight_padding);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    /* access modifiers changed from: private */
    public void setup(int i) {
        int[] iArr = new int[5];
        int i2 = 0;
        while (i2 < 5) {
            int i3 = i2 + 1;
            if (i3 <= i) {
                iArr[i2] = this.mSelectedStarResId;
            } else {
                iArr[i2] = this.mUnselectedStarResId;
            }
            i2 = i3;
        }
        this.mProductRatingText.setVisibility(8);
        setupStarImages(iArr, this.mSize);
    }

    public void setup(int i, Size size, Callback callback) {
        this.mCallback = callback;
        this.mSize = size;
        setup(i);
        setupClickListeners();
    }

    private void setupClickListeners() {
        ArrayList starViews = getStarViews();
        final int i = 0;
        while (i < starViews.size()) {
            i++;
            ((ImageView) starViews.get(i)).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    AppStarRatingView.this.setup(i);
                    AppStarRatingView.this.mSelection = i;
                    if (AppStarRatingView.this.mCallback != null) {
                        AppStarRatingView.this.mCallback.onRatingClick((double) i);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public int spaceBetweenStars() {
        return WishApplication.getInstance().getResources().getDimensionPixelSize(this.mSpaceBetweenStars);
    }

    public int getSelection() {
        return this.mSelection;
    }
}
