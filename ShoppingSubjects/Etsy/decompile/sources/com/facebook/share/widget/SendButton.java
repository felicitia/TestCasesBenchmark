package com.facebook.share.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.facebook.internal.CallbackManagerImpl.RequestCodeOffset;
import com.facebook.internal.g;
import com.facebook.share.b.a;
import com.facebook.share.c;
import com.facebook.share.model.ShareContent;

public final class SendButton extends ShareButtonBase {
    public SendButton(Context context) {
        super(context, null, 0, "fb_send_button_create", "fb_send_button_did_tap");
    }

    public SendButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, "fb_send_button_create", "fb_send_button_did_tap");
    }

    public SendButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, "fb_send_button_create", "fb_send_button_did_tap");
    }

    /* access modifiers changed from: protected */
    public int getDefaultStyleResource() {
        return a.com_facebook_button_send;
    }

    /* access modifiers changed from: protected */
    public int getDefaultRequestCode() {
        return RequestCodeOffset.Message.toRequestCode();
    }

    /* access modifiers changed from: protected */
    public g<ShareContent, c.a> getDialog() {
        if (getFragment() != null) {
            return new a(getFragment(), getRequestCode());
        }
        if (getNativeFragment() != null) {
            return new a(getNativeFragment(), getRequestCode());
        }
        return new a(getActivity(), getRequestCode());
    }
}
