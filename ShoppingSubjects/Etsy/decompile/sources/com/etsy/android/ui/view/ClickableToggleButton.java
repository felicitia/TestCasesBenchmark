package com.etsy.android.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ToggleButton;

public class ClickableToggleButton extends ToggleButton {
    public ClickableToggleButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public ClickableToggleButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ClickableToggleButton(Context context) {
        super(context);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled() || !isClickable()) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }
}
