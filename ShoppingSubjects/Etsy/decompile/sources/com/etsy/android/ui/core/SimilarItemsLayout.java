package com.etsy.android.ui.core;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewConfiguration;
import android.widget.HorizontalScrollView;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.util.l;

public class SimilarItemsLayout extends HorizontalScrollView {
    boolean mDidRequestSimilarItems = false;
    a mListener;
    Listing mListing;
    int mScreenHeight;
    int mTouchSlop;
    int[] mViewLocation = new int[2];

    public interface a {
        void a(Listing listing);
    }

    public SimilarItemsLayout(Context context) {
        super(context);
        init();
    }

    public SimilarItemsLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public SimilarItemsLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    @TargetApi(21)
    public SimilarItemsLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init();
    }

    private void init() {
        setWillNotDraw(false);
        this.mScreenHeight = new l(getContext()).e();
        this.mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getLocationOnScreen(this.mViewLocation);
        if (!this.mDidRequestSimilarItems && this.mListing != null && this.mListener != null && this.mViewLocation[1] - this.mScreenHeight <= this.mTouchSlop) {
            this.mListener.a(this.mListing);
            this.mDidRequestSimilarItems = true;
        }
        if (!this.mDidRequestSimilarItems) {
            invalidate();
        }
    }

    public void setListing(@Nullable Listing listing, @Nullable a aVar) {
        this.mListing = listing;
        this.mListener = aVar;
    }
}
