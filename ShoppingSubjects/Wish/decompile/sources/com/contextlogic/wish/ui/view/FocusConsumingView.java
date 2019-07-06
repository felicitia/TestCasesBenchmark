package com.contextlogic.wish.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class FocusConsumingView extends View {
    public FocusConsumingView(Context context) {
        super(context);
        init();
    }

    public FocusConsumingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public FocusConsumingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        setFocusable(true);
        setFocusableInTouchMode(true);
    }
}
