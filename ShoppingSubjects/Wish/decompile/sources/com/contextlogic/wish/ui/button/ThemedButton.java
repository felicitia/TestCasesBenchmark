package com.contextlogic.wish.ui.button;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.widget.Button;
import com.contextlogic.wish.util.FontUtil;

public class ThemedButton extends Button {
    public ThemedButton(Context context) {
        super(context);
        init();
    }

    public ThemedButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public ThemedButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        Typeface typefaceForStyle = FontUtil.getTypefaceForStyle(getTypeface() != null ? getTypeface().getStyle() : 0);
        if (typefaceForStyle != null) {
            setTypeface(typefaceForStyle);
        }
        if (VERSION.SDK_INT >= 21) {
            setStateListAnimator(null);
        }
    }
}
