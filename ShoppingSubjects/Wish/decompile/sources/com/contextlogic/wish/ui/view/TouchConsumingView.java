package com.contextlogic.wish.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class TouchConsumingView extends FrameLayout {
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public TouchConsumingView(Context context) {
        super(context);
    }

    public TouchConsumingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TouchConsumingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
