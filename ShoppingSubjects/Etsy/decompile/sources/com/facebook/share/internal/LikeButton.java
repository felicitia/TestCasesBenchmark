package com.facebook.share.internal;

import android.content.Context;
import android.util.AttributeSet;
import com.facebook.FacebookButtonBase;
import com.facebook.common.a.c;
import com.facebook.common.a.f;
import com.facebook.common.a.g;

@Deprecated
public class LikeButton extends FacebookButtonBase {
    /* access modifiers changed from: protected */
    public int getDefaultRequestCode() {
        return 0;
    }

    @Deprecated
    public LikeButton(Context context, boolean z) {
        super(context, null, 0, 0, "fb_like_button_create", "fb_like_button_did_tap");
        setSelected(z);
    }

    @Deprecated
    public void setSelected(boolean z) {
        super.setSelected(z);
        updateForLikeStatus();
    }

    /* access modifiers changed from: protected */
    public void configureButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super.configureButton(context, attributeSet, i, i2);
        updateForLikeStatus();
    }

    /* access modifiers changed from: protected */
    public int getDefaultStyleResource() {
        return g.com_facebook_button_like;
    }

    private void updateForLikeStatus() {
        if (isSelected()) {
            setCompoundDrawablesWithIntrinsicBounds(c.com_facebook_button_like_icon_selected, 0, 0, 0);
            setText(getResources().getString(f.com_facebook_like_button_liked));
            return;
        }
        setCompoundDrawablesWithIntrinsicBounds(c.com_facebook_button_icon, 0, 0, 0);
        setText(getResources().getString(f.com_facebook_like_button_not_liked));
    }
}
