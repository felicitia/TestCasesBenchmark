package com.contextlogic.wish.activity.settings.changephonenumber;

import android.content.Context;
import android.util.AttributeSet;
import com.contextlogic.wish.R;
import com.contextlogic.wish.ui.button.ToggleLoadingButton;

public class GetResetKeyButton extends ToggleLoadingButton {
    public GetResetKeyButton(Context context) {
        super(context, null);
    }

    public GetResetKeyButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
    }

    public GetResetKeyButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onUnselectedModeSet() {
        super.onUnselectedModeSet();
        setTypeface(1);
        setText(getResources().getString(R.string.get_reset_key));
    }

    /* access modifiers changed from: protected */
    public void onSelectedModeSet() {
        super.onSelectedModeSet();
        setTypeface(0);
    }
}
