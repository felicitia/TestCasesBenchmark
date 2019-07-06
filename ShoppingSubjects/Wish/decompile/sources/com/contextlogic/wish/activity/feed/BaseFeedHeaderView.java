package com.contextlogic.wish.activity.feed;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public abstract class BaseFeedHeaderView extends FrameLayout {
    public abstract void releaseImages();

    public abstract void restoreImages();

    public BaseFeedHeaderView(Context context) {
        super(context);
    }

    public BaseFeedHeaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
