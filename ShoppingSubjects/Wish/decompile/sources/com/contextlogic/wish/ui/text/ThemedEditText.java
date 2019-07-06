package com.contextlogic.wish.ui.text;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;
import com.contextlogic.wish.util.FontUtil;

public class ThemedEditText extends EditText {
    public ThemedEditText(Context context) {
        super(context);
        init();
    }

    public ThemedEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public ThemedEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        Typeface typefaceForStyle = FontUtil.getTypefaceForStyle(getTypeface() != null ? getTypeface().getStyle() : 0);
        if (typefaceForStyle != null) {
            setTypeface(typefaceForStyle);
        }
    }

    public void setTextSize(int i, float f) {
        super.setTextSize(i, f);
    }
}
