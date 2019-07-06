package com.contextlogic.wish.ui.button;

import android.content.Context;
import android.util.AttributeSet;
import com.contextlogic.wish.R;

public class FollowButton extends ToggleLoadingButton {
    public FollowButton(Context context) {
        super(context, null);
    }

    public FollowButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
    }

    public FollowButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onUnselectedModeSet() {
        super.onUnselectedModeSet();
        this.buttonText.setText(getContext().getString(R.string.follow));
    }

    /* access modifiers changed from: protected */
    public void onSelectedModeSet() {
        super.onSelectedModeSet();
        this.buttonText.setText(getContext().getString(R.string.following));
    }
}
