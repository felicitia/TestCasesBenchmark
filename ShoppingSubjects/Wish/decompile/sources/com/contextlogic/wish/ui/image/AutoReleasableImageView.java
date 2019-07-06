package com.contextlogic.wish.ui.image;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.contextlogic.wish.R;

public class AutoReleasableImageView extends AspectRatioImageView {
    public AutoReleasableImageView(Context context) {
        super(context);
        init(null);
    }

    public AutoReleasableImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public AutoReleasableImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.AutoReleasableImageView);
            int color = obtainStyledAttributes.getColor(0, 0);
            if (color != 0) {
                super.setPlaceholderBackground(color);
            }
            int resourceId = obtainStyledAttributes.getResourceId(1, 0);
            if (resourceId != 0) {
                setImageResource(resourceId);
            }
        }
    }
}
