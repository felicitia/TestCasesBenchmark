package com.contextlogic.wish.ui.text;

import android.content.Context;
import android.util.AttributeSet;
import com.contextlogic.wish.R;

public class ErrorableThemedEditText extends ThemedEditText {
    private static final int[] STATE_ERRORED = {R.attr.state_errored};
    private boolean mIsErrored;

    public ErrorableThemedEditText(Context context) {
        super(context);
    }

    public ErrorableThemedEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ErrorableThemedEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (this.mIsErrored) {
            mergeDrawableStates(onCreateDrawableState, STATE_ERRORED);
        }
        return onCreateDrawableState;
    }

    public void setErrored(boolean z) {
        this.mIsErrored = z;
    }

    public boolean getErrored() {
        return this.mIsErrored;
    }
}
