package com.contextlogic.wish.ui.starrating;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.ui.starrating.StarRatingView.Callback;
import com.contextlogic.wish.ui.starrating.StarRatingView.Size;

public class RedesignedBlueStarRatingView extends StarRatingView {
    public RedesignedBlueStarRatingView(Context context) {
        super(context);
    }

    public RedesignedBlueStarRatingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RedesignedBlueStarRatingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setup(final double d, Size size, final Callback callback) {
        int[] iArr = new int[5];
        int i = 0;
        while (i < 5) {
            int i2 = i + 1;
            double d2 = (double) i2;
            if (d >= d2) {
                iArr[i] = R.drawable.full_star_primary;
            } else {
                double d3 = d2 - d;
                if (d3 <= 0.25d) {
                    iArr[i] = R.drawable.full_star_primary;
                } else if (d3 <= 0.75d) {
                    iArr[i] = R.drawable.half_star_primary;
                } else {
                    iArr[i] = R.drawable.empty_star_primary;
                }
            }
            i = i2;
        }
        setupStarImages(iArr, size);
        this.mProductRatingText.setVisibility(8);
        if (callback != null) {
            setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    callback.onRatingClick(d);
                }
            });
        }
    }

    public void setup(int i, double d, Size size, Callback callback) {
        if (i > 0) {
            setup(d, size, callback);
        } else {
            setVisibility(0);
        }
    }

    /* access modifiers changed from: protected */
    public int spaceBetweenStars() {
        return getResources().getDimensionPixelSize(R.dimen.four_padding);
    }
}
