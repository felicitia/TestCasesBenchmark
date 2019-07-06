package com.facebook.share.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.facebook.internal.CallbackManagerImpl.RequestCodeOffset;
import com.facebook.internal.g;
import com.facebook.share.b.a;
import com.facebook.share.c;
import com.facebook.share.model.ShareContent;

public final class ShareButton extends ShareButtonBase {
    public ShareButton(Context context) {
        super(context, null, 0, "fb_share_button_create", "fb_share_button_did_tap");
    }

    public ShareButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, "fb_share_button_create", "fb_share_button_did_tap");
    }

    public ShareButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, "fb_share_button_create", "fb_share_button_did_tap");
    }

    /* access modifiers changed from: protected */
    public int getDefaultStyleResource() {
        return a.com_facebook_button_share;
    }

    /* access modifiers changed from: protected */
    public int getDefaultRequestCode() {
        return RequestCodeOffset.Share.toRequestCode();
    }

    /* access modifiers changed from: protected */
    public g<ShareContent, c.a> getDialog() {
        if (getFragment() != null) {
            return new ShareDialog(getFragment(), getRequestCode());
        }
        if (getNativeFragment() != null) {
            return new ShareDialog(getNativeFragment(), getRequestCode());
        }
        return new ShareDialog(getActivity(), getRequestCode());
    }
}
