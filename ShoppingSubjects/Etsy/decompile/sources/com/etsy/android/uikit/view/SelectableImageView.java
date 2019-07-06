package com.etsy.android.uikit.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import com.etsy.android.lib.a.e;

public class SelectableImageView extends AppCompatImageView {
    private static final int COLOR_RES = e.sk_bg_transparency;
    private final int mColor = getResources().getColor(COLOR_RES);

    public SelectableImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SelectableImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setImageResource(int i) {
        super.setImageResource(i);
        updateSelectionDrawable(isSelected());
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        updateSelectionDrawable(isSelected());
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        updateSelectionDrawable(isSelected());
    }

    public void setSelected(boolean z) {
        super.setSelected(z);
        updateSelectionDrawable(z);
    }

    private void updateSelectionDrawable(boolean z) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        if (z) {
            drawable.clearColorFilter();
        } else {
            drawable.setColorFilter(this.mColor, Mode.SRC_ATOP);
        }
    }
}
