package com.etsy.android.uikit.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.etsy.android.lib.a.c;

public class ReadStateRelativeLayout extends RelativeLayout {
    public static final int[] READ_STATE = {c.state_read};
    boolean mIsRead;

    public ReadStateRelativeLayout(Context context) {
        super(context);
    }

    public ReadStateRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (this.mIsRead) {
            mergeDrawableStates(onCreateDrawableState, READ_STATE);
        }
        return onCreateDrawableState;
    }

    public void setRead(boolean z) {
        this.mIsRead = z;
        refreshDrawableState();
    }
}
