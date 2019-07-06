package com.etsy.android.uikit.view;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import com.etsy.android.lib.a.e;

public class ClickableImageView extends AppCompatImageView {
    private static final int COLOR_RES = e.sk_highlight;
    private final int mColor = getResources().getColor(COLOR_RES);

    public ClickableImageView(Context context) {
        super(context);
    }

    public ClickableImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ClickableImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = getDrawable();
        if (drawable != null) {
            int[] drawableState = getDrawableState();
            boolean z = false;
            int length = drawableState.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (drawableState[i] == 16842919) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            if (z) {
                drawable.setColorFilter(this.mColor, Mode.SRC_ATOP);
            } else {
                drawable.clearColorFilter();
            }
            invalidate();
        }
    }
}
