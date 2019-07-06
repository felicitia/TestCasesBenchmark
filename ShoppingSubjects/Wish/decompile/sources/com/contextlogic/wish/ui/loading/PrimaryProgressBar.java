package com.contextlogic.wish.ui.loading;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import com.contextlogic.wish.R;

public class PrimaryProgressBar extends ProgressBar {
    public PrimaryProgressBar(Context context) {
        super(context);
        init();
    }

    public PrimaryProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public PrimaryProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        setColorFilter(R.color.main_primary);
    }

    public void setColorFilter(int i) {
        Drawable indeterminateDrawable = getIndeterminateDrawable();
        if (indeterminateDrawable != null) {
            indeterminateDrawable.setColorFilter(getResources().getColor(i), Mode.SRC_IN);
        }
    }
}
